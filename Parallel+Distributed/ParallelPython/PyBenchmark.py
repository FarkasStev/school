import time
import random
import sys
from multiprocessing import Pool
import psutil



def rand_mult(A, B, C, n):
    
    for i in range(n):
        for j in range(n):
            C[i] = A[i] * B[j]

    
    
    
    


if __name__ == "__main__":

    if len(sys.argv) < 2:
        print("Invalid format: python3 rand_thread <Thread number> <Number of args>")
        sys.exit(1)

    numP = int(sys.argv[1])
    numA = int(sys.argv[2])

    A = [random.randint(0,100) for i in range(numA)]
    B = [random.randint(0,100) for i in range(numA)]
    C = [0 for i in range(numA)]

    data = (A, B, C, numA)
    
    starttime = time.time()
    pool = Pool(processes = numP)
    pool.starmap(rand_mult, [data])
    endtime = time.time()
    pool.close()
    pool.join()
    
    print(f"Time taken {endtime-starttime} seconds")