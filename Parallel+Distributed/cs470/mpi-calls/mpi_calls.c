/*
 * mpi_calls.c
 *
 * CS 470 MPI Communication Lab
 *
 * Name(s): 
 *
 * The goal of this exercise is to gain experience using MPI collective and
 * point-to-point communication calls. This program is meant to be run with four
 * MPI processes. Each process begins with four local "seed" values. Your goal
 * is to use MPI operations to move and manipulate these seed values to produce
 * the desired outputs.
 *
 * To compile, run "make". To run:
 *
 *    salloc -n 4 mpirun ./mpi_calls
 *
 * You must make the "Result" lines in the output match the following:
 *
 *    Result 1: [  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 ]
 *    Result 2: [ 16 15 14 13 ] [ 12 11 10  9 ] [  8  7  6  5 ] [  4  3  2  1 ]
 *    Result 3: [  0  1  2  3 ] [  0  1  2  3 ] [  0  1  2  3 ] [  0  1  2  3 ]
 *    Result 4: [  0  4  8 12 ] [  0  4  8 12 ] [  0  4  8 12 ] [  0  4  8 12 ]
 *    Result 5: [  0  4  8 12 ] [  1  5  9 13 ] [  2  6 10 14 ] [  3  7 11 15 ]
 *    Result 6: [ 24 28 32 36 ] [ 24 28 32 36 ] [ 24 28 32 36 ] [ 24 28 32 36 ]
 *    Result 7: [ 12 13 14 15 ] [  0  1  2  3 ] [  4  5  6  7 ] [  8  9 10 11 ]
 *    Result 8: [ 120 ]
 *    Result 9: [ 715 ]
 *   Result 10: [ 30 ]
 *
 * Restriction: You may ONLY add MPI calls and/or declarations; you may not add
 * any other code. See the code for hints on how to accomplish this. If there
 * are multiple solutions for a given result, please choose whichever solution
 * you believe is most efficient, most simple, or most elegant (in that order).
 *
 * Please submit your modified mpi_calls.c file on the appropriate Canvas
 * assignment. If you worked in a group, please submit ONLY one copy and make
 * sure that all names are included above.
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mpi.h>

#define NSEEDS 4

int my_rank;
int nprocs;

int *local_seeds;
int *tmp;

/*
 * Allocate an array on the heap of the given size and initialize it to zeroes.
 */
int* allocate(int size)
{
    int *arr = (int*)malloc(sizeof(int) * size);
    if (!arr) {
        printf("ERROR: out of memory!\n");
        exit(EXIT_FAILURE);
    }
    memset(arr, 0, sizeof(int) * size);
    return arr;
}

/*
 * Print a local array from rank 0.
 */
void print_local_array(const char *label, int *arr, int size)
{
    if (my_rank == 0) {
        printf("%20s: [ ", label);
        for (int i = 0; i < size; i++) {
            printf("%2d ", arr[i]);
        }
        printf("]\n");
    }
}

/*
 * Print a distributed array by gathering the results to rank 0.
 */
void print_distributed_array(const char *label, int *arr, int size)
{
    int tmp[size*nprocs];
    MPI_Gather(arr, size, MPI_INT,
               tmp, size, MPI_INT, 0, MPI_COMM_WORLD);
    if (my_rank == 0) {
        printf("%20s: ", label);
        for (int i = 0; i < size*nprocs; i++) {
            if (i % NSEEDS == 0) {
                printf("[ ");
            }
            printf("%2d ", tmp[i]);
            if (i % NSEEDS == NSEEDS-1) {
                printf("] ");
            }
        }
        printf("\n");
    }
}

int main(int argc, char *argv[])
{
    /* INITIALIZATION */

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
    MPI_Comm_size(MPI_COMM_WORLD, &nprocs);

    if (nprocs != 4) {
        if (my_rank == 0) {
            printf("ERROR: Must be run with exactly four MPI processes.\n");
        }
        MPI_Finalize();
        exit(EXIT_FAILURE);
    }

    local_seeds = allocate(NSEEDS);
    for (int i = 0; i < NSEEDS; i++) {
        local_seeds[i] = NSEEDS*my_rank + i;
    }
    print_distributed_array("Start - Seeds", local_seeds, NSEEDS);


    /* RESULT 1 */

    tmp = allocate(NSEEDS*nprocs);
    // hint: this expected output looks very similar to the original seed output
    print_local_array("Result 1", tmp, NSEEDS*nprocs);
    free(tmp);


    /* RESULT 2 */

    int *refs = allocate(NSEEDS*nprocs);
    if (my_rank == 0) {
        int max = NSEEDS * nprocs;
        for (int i = 0; i < max; i++) {
            refs[i] = max-i;
        }
    }
    tmp = allocate(NSEEDS);
    // hint: call print_local_array on refs to see what it looks like
    print_distributed_array("Result 2", tmp, NSEEDS);
    free(tmp);
    free(refs);


    /* RESULT 3 */

    tmp = allocate(NSEEDS);
    memcpy(tmp, local_seeds, sizeof(int)*NSEEDS);
    // hint: send rank 0's seeds to everyone
    print_distributed_array("Result 3", tmp, NSEEDS);
    free(tmp);


    /* RESULT 4 */

    tmp = allocate(NSEEDS);
    // hint: everyone needs a copy of the first seed from each process
    print_distributed_array("Result 4", tmp, NSEEDS);
    free(tmp);


    /* RESULT 5 */

    tmp = allocate(NSEEDS);
    // hint: do a matrix transpose!
    print_distributed_array("Result 5", tmp, NSEEDS);
    free(tmp);


    /* RESULT 6 */

    tmp = allocate(NSEEDS);
    // hint: sum corresponding seeds; everyone needs the results
    print_distributed_array("Result 6", tmp, NSEEDS);
    free(tmp);


    /* RESULT 7  */

    tmp = allocate(NSEEDS);
    // hint: rotate seeds to the right by one process
    print_distributed_array("Result 7", tmp, NSEEDS);
    free(tmp);


    /* RESULT 8 */

    int local_sum = 0, global_sum = 0;
    for (int i = 0; i < NSEEDS; i++) {
        local_sum += local_seeds[i];
    }
    // hint: use local_sum
    print_local_array("Result 8", &global_sum, 1);


    /* RESULT 9 */

    int a = 0, b = 0, c = 0;
    switch (my_rank) {
        case 0:
            // hint: what is the prime factorization of 715?
            break;
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
    }
    int prod = a * b * c;
    print_local_array("Result 9", &prod, 1);


    /* RESULT 10 */

    int sum;
    print_local_array("Result 10", &sum, 1);


    /* CLEANUP */

    MPI_Finalize();
    free(local_seeds);
    return EXIT_SUCCESS;
}
