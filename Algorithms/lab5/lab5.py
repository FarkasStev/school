
n = int(input()) # Get the number of input lines
cases = [input() for _ in range(n)] # Get the input lines

# Predicate
def isPalindrome(s):
  return s[::-1] == s

def countPalindromicSubdivisions(s):
  memo = [None for _ in range(len(s)+1)]

  # Iterative palindromic subdivision counter
  def countPalindromicSubdivisions_helper(I):
    for i in range (0, len(s)+1):
        memo[i] = 1
    for j in range(len(s), -1):
        subdivs = 0
        for i in range(1, len(s)+1):
            if isPalindrome(s[0:i]):
                subdivs += memo[i]
        memo[j] = subdivs

    return memo[0]


  # Recursive palindromic subdivision counter
  def rCountPalindromicSubdivisions_helper(I):
    if memo[I] != None:
      return memo[I]
    if I == len(s):
      memo[I] = 1
    else:
      subdivs = 0
      for i in range(I+1, len(s)+1):
        if isPalindrome(s[I:i]):
          subdivs += rCountPalindromicSubdivisions_helper(i)
      memo[I] = subdivs
    return memo[I]

  return countPalindromicSubdivisions_helper(0)


# Count the number of palindromic subdivisions for each case
for c in cases:
  print(countPalindromicSubdivisions(c))
