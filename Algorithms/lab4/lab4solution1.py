

n = int(input()) # Get the number of input lines
cases = [input() for _ in range(n)] # Get the input lines


memos = {} 

# Predicate
def isPalindrome(s):
  return s[::-1] == s

# Recursive palindromic subdivision counter
def countPalindromicSubdivisions(s):
  if (memos.get(s) != None):
    return memos.get(s)
  else:
    if len(s) <= 1:
      return 1
    subdivs = 0
    for i in range(1, len(s)+1):
      if isPalindrome(s[0:i]):
        subdivs += countPalindromicSubdivisions(s[i:])
    memos[s] = subdivs
    return subdivs



# Count the number of palindromic subdivisions for each case
for c in cases:
  print(countPalindromicSubdivisions(c))
  # print(returnPalindromicSubdivisions(c))
