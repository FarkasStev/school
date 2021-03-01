/*
 * hellos.c
 *
 * CS 470 Activity
 * Original unsynchronized version
 *
 * Compile with --std=c99
 */

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/*
 * main thread work function
 */
void* thread_work(void* tid)
{
    printf("Hello from thread #%ld!\n", (long)tid);
    printf("Goodbye from thread #%ld!\n", (long)tid);
    return NULL;
}

int main(int argc, char* argv[])
{
    // check and parse command line options
    if (argc != 2) {
        printf("Usage: %s <nthreads>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    int nthreads = strtol(argv[1], NULL, 10);
    pthread_t* threads = (pthread_t*)malloc(sizeof(pthread_t) * nthreads);

    // spawn worker threads
    for (int t = 0; t < nthreads; t++) {
        pthread_create(&threads[t], NULL, thread_work, (void*)(long)t);
    }

    printf("Hello from the master thread!\n");

    // join worker threads
    for (int t = 0; t < nthreads; t++) {
        pthread_join(threads[t], NULL);
    }

    printf("Goodbye from the master thread!\n");

    free(threads);
    return(EXIT_SUCCESS);
}
