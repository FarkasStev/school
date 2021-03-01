/**
 * mc_pi.c
 *
 * CS 470 OpenMP Lab
 * Based on IPP Programming Assignment 5.2
 *
 * Names: Steven Farkas
 *
 * My new version does not scale terribly well and I am not positive why. It does however
 * estimate Pi relativley accurately. It was a little a simpler to work with than pthreads
 * although because of the simpleness some of the issues were a little more difficult to 
 * diagnose than I had expected. Im sure my results would have been a little more comparable
 * to the pthreads version if I had tweaked it a little more.
 * 
 * SERIAL:
 * Estimated pi: 3.141677e+00   Time elapsed: 3.523s
 * PARALLEL:
 * Estimated pi: 3.141677e+00   Time elapsed: 2.966s
 * Estimated pi: 3.141677e+00   Time elapsed: 4.969s
 * Estimated pi: 3.141593e+00   Time elapsed: 5.220s
 * Estimated pi: 3.141547e+00   Time elapsed: 5.458s
 * Estimated pi: 3.141526e+00   Time elapsed: 5.516s
 *
 *
 */

#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#ifdef _OPENMP
#include <omp.h>
#endif
#include "timer.h"

long total_darts = 0;           // dart count
long darts_in_circle = 0;       // number of hits

void throw_darts()
{
#   pragma omp parallel
    {
        // seed pseudo-random number generator
#       ifdef _OPENMP
        unsigned long seed = omp_get_thread_num();
#       else
        unsigned long seed = 0;
#       endif

#       pragma omp for 
        for (long dart = 0; dart < total_darts; dart++) {

            // throw a dart by generating a random (x,y) coordinate pair
            // using a basic linear congruential generator (LCG) algorithm
            // (see https://en.wikipedia.org/wiki/Linear_congruential_generator)
            //
            seed = (1103515245*seed + 12345) % (1<<31);
            double x = (double)seed / (double)ULONG_MAX;
            seed = (1103515245*seed + 12345) % (1<<31);
            double y = (double)seed / (double)ULONG_MAX;
            double dist_sq = x*x + y*y;

            // update hit tracker
            if (dist_sq <= 1.0) {
#               pragma omp atomic 
                darts_in_circle++;
            }
        }
    }
}

int main(int argc, char* argv[])
{
    // check and parse command-line arguments
    if (argc != 2) {
        printf("Usage: %s <num-darts>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    total_darts = strtoll(argv[1], NULL, 10);

    START_TIMER(darts)

    // simulate dart throws
    throw_darts();

    STOP_TIMER(darts)

    // calculate pi
    double pi_est = 4 * darts_in_circle / ((double)total_darts);
    printf("Estimated pi: %e   Time elapsed: %.3lfs\n",
            pi_est, GET_TIMER(darts));

    // clean up
    return EXIT_SUCCESS;
}


