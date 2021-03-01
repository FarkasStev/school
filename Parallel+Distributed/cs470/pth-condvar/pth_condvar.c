/*
 * CS 470 Example: condition variables
 *
 * Based on example from  http://pages.cs.wisc.edu/~remzi/OSTEP/threads-cv.pdf
 */

#include <stdio.h>
#include <stdbool.h>
#include <pthread.h>

// simulation parameters
//
#define BUFMAX 5
#define NPRODS 10
#define NCONSS 10
#define NITEMS 50

// work queue parameters
//
int buffer[BUFMAX];
int fill_idx = 0;
int use_idx = 0;
int count = 0;

// work queue operations
//

void put(int value)
{
    buffer[fill_idx] = value;
    fill_idx = (fill_idx + 1) % BUFMAX;
    count++;
}

int get()
{
    int tmp = buffer[use_idx];
    use_idx = (use_idx + 1) % BUFMAX;
    count--;
    return tmp;
}

// multithreading parameters
//
pthread_cond_t  not_full  = PTHREAD_COND_INITIALIZER;
pthread_cond_t  not_empty = PTHREAD_COND_INITIALIZER;
pthread_cond_t  done      = PTHREAD_COND_INITIALIZER;
pthread_mutex_t queue     = PTHREAD_MUTEX_INITIALIZER;
int items_remaining = NITEMS;

// multithreading work routines
//

void *producer(void *arg)
{
    int id = (int)(long)arg;
    int items = NITEMS/NPRODS;
    for (int i = items*id; i < items*(id+1); i++) {
        pthread_mutex_lock(&queue);
        while (count == BUFMAX) {
            pthread_cond_wait(&not_full, &queue);
        }
        put(i);
        printf("producer #%d added item #%d [count=%d]\n", id, i, count);
        pthread_cond_signal(&not_empty);
        pthread_mutex_unlock(&queue);
    }
    return NULL;
}

void *consumer(void *arg)
{
    int id = (int)(long)arg;
    pthread_mutex_lock(&queue);
    while (items_remaining > 0) {
        while (items_remaining > 0 && count == 0) {
            printf("consumer #%d waiting [count=%d]\n", id, count);
            pthread_cond_wait(&not_empty, &queue);
            printf("consumer #%d awake [count=%d]\n", id, count);
        }
        if (count > 0) {
            int tmp = get();
            items_remaining--;
            pthread_cond_signal(&not_full);
            printf("consumer #%d removed item #%d [count=%d]\n", id, tmp, count);
        }
    }
    pthread_mutex_unlock(&queue);
    printf("consumer #%d done\n", id);
    pthread_cond_signal(&done);
    return NULL;
}

// entry point
//
int main()
{
    pthread_t producers[NPRODS];
    pthread_t consumers[NCONSS];

    printf("master: initializing\n");

    // start consumers
    for (int i = 0; i < NCONSS; i++) {
        pthread_create(&consumers[i], NULL, consumer, (void*)(long)i);
    }

    // start producers
    for (int i = 0; i < NPRODS; i++) {
        pthread_create(&producers[i], NULL, producer, (void*)(long)i);
    }

    // wait until producers are done
    printf("master: waiting for producers to finish\n");
    for (int i = 0; i < NPRODS; i++) {
        pthread_join(producers[i], NULL);
    }

    // wait until consumers are done
    printf("master: waiting for work to finish\n");
    pthread_mutex_lock(&queue);
    while (items_remaining > 0) {
        pthread_cond_wait(&done, &queue);
    }
    pthread_mutex_unlock(&queue);

    // wake any waiting consumers
    pthread_mutex_lock(&queue);
    pthread_cond_broadcast(&not_empty);
    pthread_mutex_unlock(&queue);

    // wait for consumers to terminate
    printf("master: waiting for thread terminations\n");
    for (int i = 0; i < NCONSS; i++) {
        pthread_join(consumers[i], NULL);
    }

    printf("master: done.\n");
    return 0;
}

