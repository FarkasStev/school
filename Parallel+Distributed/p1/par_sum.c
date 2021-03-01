/*
 * par_sum.c
 *
 * CS 470 Project 1 (Pthreads)
 * Parallel version
 * 
 * Steven Farkas and Madison Jones
 *
 * Compile with --std=c99
 */

#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

struct Linked
{
    long value;
    struct Linked *next;
};

// aggregate variables
long global_sum = 0;
long global_odd = 0;
long global_min = INT_MAX;
long global_max = INT_MIN;
long list_size = 0;
struct Linked *head;
struct Linked *tail;
bool done = false;

pthread_cond_t list_size_cv = PTHREAD_COND_INITIALIZER;

pthread_mutex_t sum_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t odd_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t min_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t max_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t cv_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t tail_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t head_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t size_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t done_mutex = PTHREAD_MUTEX_INITIALIZER;

// function prototypes
void update();

/*
 * Update local variable then call sync to update global vars
 */
void update()
{
    long number;

    // get data from head and update pointers
    pthread_mutex_lock(&head_mutex);
    pthread_mutex_lock(&tail_mutex);

    if (head == NULL && tail != NULL)
    {
        number = tail->value;
        head = NULL;
        pthread_mutex_unlock(&tail_mutex);
        pthread_mutex_unlock(&head_mutex);
    }
    else if (head == NULL)
    {
        pthread_mutex_unlock(&tail_mutex);
        printf("List size is %ld\n", list_size);
        pthread_mutex_unlock(&head_mutex);
        return;
    }
    else
    {
        pthread_mutex_unlock(&tail_mutex);
        number = head->value;
        head = head->next;
        pthread_mutex_unlock(&head_mutex);
    }

    // simulate computation
    sleep(number);

    // update aggregate variables
    pthread_mutex_lock(&sum_mutex);
    global_sum += number;
    pthread_mutex_unlock(&sum_mutex);

    pthread_mutex_lock(&odd_mutex);
    if (number % 2 == 1)
    {
        global_odd++;
    }
    pthread_mutex_unlock(&odd_mutex);

    pthread_mutex_lock(&min_mutex);
    if (number < global_min)
    {
        global_min = number;
    }
    pthread_mutex_unlock(&min_mutex);

    pthread_mutex_lock(&max_mutex);
    if (number > global_max)
    {
        global_max = number;
    }
    pthread_mutex_unlock(&max_mutex);
}

/*
 * Thread startup method, used to check condition variable
 * and if applicable call the update method
 */
void *thread_workload(void *args)
{
    pthread_mutex_lock(&size_mutex);
    pthread_mutex_lock(&done_mutex);
    while (done == false || list_size > 0)
    {

        pthread_mutex_unlock(&done_mutex);
        pthread_mutex_unlock(&size_mutex);

        pthread_mutex_lock(&size_mutex);
        pthread_mutex_lock(&done_mutex);
        if (list_size > 0)
        {
            // update

            //decrement size
            list_size--;
            pthread_mutex_unlock(&done_mutex);
            pthread_mutex_unlock(&size_mutex);
            update();
        }
        else if (done == false)
        {
            // wait
            pthread_mutex_unlock(&done_mutex);
            pthread_mutex_unlock(&size_mutex);
            pthread_mutex_lock(&cv_mutex);
            pthread_cond_wait(&list_size_cv, &cv_mutex);
            pthread_mutex_unlock(&cv_mutex);
        }

        pthread_mutex_lock(&size_mutex);
        pthread_mutex_lock(&done_mutex);
    }
    pthread_mutex_unlock(&done_mutex);
    pthread_mutex_unlock(&size_mutex);

    return NULL;
}

int main(int argc, char *argv[])
{
    // check and parse command line options
    if (argc != 3)
    {
        printf("Usage: par_sum <infile> <number_of_threads>\n");
        exit(EXIT_FAILURE);
    }
    char *fn = argv[1];

    // open input file
    FILE *fin = fopen(fn, "r");
    if (!fin)
    {
        printf("ERROR: Could not open %s\n", fn);
        exit(EXIT_FAILURE);
    }

    int num_threads = atoi(argv[2]);

    if (num_threads < 1)
    {
        printf("ERROR: Invalid number of threads: %d\n", num_threads);
        exit(EXIT_FAILURE);
    }

    char action;
    long num;
    pthread_t workers[num_threads];
    head = malloc(sizeof(struct Linked));
    tail = malloc(sizeof(struct Linked));

    // Initialize worker threads
    for (int i = 0; i < num_threads; i++)
    {
        pthread_create(&(workers[i]), NULL, thread_workload, NULL);
    }

    while (fscanf(fin, "%c %ld\n", &action, &num) == 2)
    {
        // check for invalid action parameters
        if (num < 1)
        {
            printf("ERROR: Invalid action parameter: %ld\n", num);
            exit(EXIT_FAILURE);
        }

        if (action == 'p')
        { // process

            // update linked list

            // Create temporary value for node based on given
            struct Linked *temp = malloc(sizeof(struct Linked));
            temp->value = num;
            temp->next = NULL;
            //printf("initial size check\n");
            pthread_mutex_lock(&size_mutex);

            pthread_mutex_lock(&head_mutex);

            // add first job to list
            if (list_size == 0 || head == NULL)
            {
                pthread_mutex_lock(&tail_mutex);

                head = temp;
                tail = temp;

                list_size++;
                pthread_mutex_unlock(&tail_mutex);
                pthread_mutex_unlock(&head_mutex);
                pthread_mutex_unlock(&size_mutex);
            }
            else
            {
                // Add any other jobs to the tail
                pthread_mutex_lock(&tail_mutex);
                tail->next = temp;
                tail = temp;
                list_size++;
                pthread_mutex_unlock(&head_mutex);

                pthread_mutex_unlock(&tail_mutex);
                pthread_mutex_unlock(&size_mutex);
            }

            // update size
            pthread_mutex_lock(&cv_mutex);
            pthread_cond_signal(&list_size_cv);
            pthread_mutex_unlock(&cv_mutex);
        }
        else if (action == 'w')
        {
            // wait
            sleep(num);
        }
        else
        {
            printf("ERROR: Unrecognized action: '%c'\n", action);
            exit(EXIT_FAILURE);
        }
    }
    fclose(fin);

    // Update global to indicate list is done
    pthread_mutex_lock(&done_mutex);
    done = true;
    pthread_mutex_unlock(&done_mutex);

    // Broadcast to all waiting threads
    pthread_mutex_lock(&cv_mutex);
    pthread_cond_broadcast(&list_size_cv);
    pthread_mutex_unlock(&cv_mutex);

    // Join all worker threads
    for (int i = 0; i < num_threads; i++)
    {
        pthread_join(workers[i], NULL);
    }

    // print results
    printf("%ld %ld %ld %ld\n", global_sum, global_odd, global_min, global_max);

    // clean up and return
    return (EXIT_SUCCESS);
}
