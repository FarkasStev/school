
def binary_search(A, q):
  return binary_search_helper(A, q, 0, len(A))

# Searches a subarray of a sorted array A for an element q. 
# The subarray is the array starting at index start and ending
# on index end-1.
def binary_search_helper(A, q, start, end):
  # The length of the subarray
  lenA = end - start

  # If the array is empty, q has no index
  if lenA <= 0:
    return -1
  # If the array has one element, then return 0 if the element is q, otherwise -1
  elif lenA == 1:
    return -1 if A[start] != q else 0
  
  # Get the offset from start to the middle of the subarray
  m = start + lenA // 2

  # If the middle element is q, Eureka, we found it at index m!
  if A[m] == q:
    return m
  # If the q is larger than A[m], then we reduce the problem to finding the 
  # index in the first half of the array
  elif  (A[end - 1] < q) or (A[m] > q):
    # Recursion Fairy dear, please tell us what index q is in the
    # first half of the array, kthxbye
    return binary_search_helper(A, q, start, m)
  # The third case is when A[m] < q. If q is in there, it must be in the
  # right half of the array
  else:
    # Reduce the problem to searching the right half of the array. 
    # Recursion fairy, what index is q in the right half of the array?
    return binary_search_helper(A, q, m, end)

line = input().split()
nums = []
for item in line:
  nums.append(int(item))
target = int(input())

print(binary_search(nums, target))