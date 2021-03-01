
# mergesort coded by Bowers from Jeff Erickson's pseudocode'
def mergesort(A):
    inversions = 0
    if len(A) > 1:
        # Get the mid point
        m = len(A) // 2

        # Get the left and right halves
        left, right = A[:m], A[m:]

        # sort the left and right halves
        inversions+=mergesort(left)
        inversions+=mergesort(right)

        # Copy the sorted left and right halves back to A. 
        for i in range(m):
            A[i] = left[i]
        for j in range(m, len(A)):
            A[j] = right[j - m]
        
        # Run the merge operation on A
        merge(A,m)
    return inversions

def merge(A, m):
    i, j = 0, m
    inversions = 0
    n = len(A)
    B = [0 for _ in range(n)]
    for k in range(n):
        if j >= n:
            B[k] = A[i]
            i += 1
            
        elif i >= m:
            B[k] = A[j]
            j += 1
            
        elif A[i] <= A[j]:
            B[k] = A[i]
            i += 1
            
        else:
            B[k] = A[j]
            j += 1
            inversions+= 1
            
    for k in range(n):
        A[k] = B[k]
    return inversions

line = input().split()
nums = []
for item in line:
    nums.append(int(item))
print (mergesort(nums))