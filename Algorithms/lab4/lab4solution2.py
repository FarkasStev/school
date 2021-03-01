

n = int(input()) # Get the number of input lines
cases = [input() for _ in range(n)] # Get the input lines


memos = {} 
memos.

# Predicate
def isPalindrome(s):
  return s[::-1] == s

# Recursive palindromic subdivision counter
def countPalindromicSubdivisions(s, x):
  if (memos.get(x) != None):
    return memos.get(x)
  else:
    if len(s) - x <= 1:
      memos[x] = 1
      return memos[x]

    subdivs = 0
    for i in range(x + 1, len(s)+ 1):
      if isPalindrome(s[x:i]):
        subdivs += countPalindromicSubdivisions(s, i)
    memos[x] = subdivs
    return subdivs



# Count the number of palindromic subdivisions for each case
for c in cases:
  print(countPalindromicSubdivisions(c, 0))
  # print(returnPalindromicSubdivisions(c))
