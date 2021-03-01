def isPalindrome(s):
    return s == s[::-1]

def palindrome(partition):
    if len(partition) <= 1:
        return 1
    else:
        numPartitions = 0
        for x in range(1, len(partition) + 1):
            if isPalindrome(partition[0:x]):
                numPartitions += palindrome(partition[x:])
            
        return numPartitions


numInputs = int (input())
output = []
for x in range(numInputs):
    output.append(str(palindrome(input())))

print(*output, sep='\n', end='')



