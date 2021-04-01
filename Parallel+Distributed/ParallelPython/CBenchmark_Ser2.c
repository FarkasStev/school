#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/time.h>
#include "timer.h"



void rand_multiplication(int n) {

    struct timeval start, end;
    int A[n], B[n], C[n], i;
    memset(C, 0, n);

    for(int i = 0; i < n; i++) {

        A[i] = i;
    }

    for(int i = 0; i < n; i++) {

        B[i] = i;
    }

    START_TIMER(MatrixMult);

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++)
            C[i] = A[i] * B[j];
    }

   STOP_TIMER(MatrixMult);

    printf("Work took %8.4fs seconds\n", GET_TIMER(MatrixMult));
    
}

int main(int argc, char *argv[]) {

    int numT, numA;

    if (argc != 2) {
        fprintf(stderr, "Invalid format: OMP_NUM_THREADS= <Number of Arguments> \n");
        return EXIT_FAILURE;
    }

    
    numA = atoi(argv[1]);
    rand_multiplication(numA);

    return 0;
}