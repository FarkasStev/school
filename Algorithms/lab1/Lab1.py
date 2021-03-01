#1. I used a dictionary to store the translations and a list to store
#   the ouput as it was being generated
#2. I used the Dictionary get method and the List append method
#3. The get method is O(n) in the worst case and the list append method 
#   is O(1)
#4. My algorithms runtime is O(n)
#5. My algorithm words by storing the translations of each word in a dictionary
#   and then looking at the line to translate and attempting to translate each
#   word before printing it to the output 

dictWords = {}
num = int(input())
for x in range(num):
    line = input()
    lines = line.split()
    dictWords[lines[1]] = lines[0]
line = input()
words = line.split()
output = []
for i in range(len(words)):
    if dictWords.get(words[i]) != None:
        output.append(dictWords.get(words[i]))
    else:
        output.append("???")
print(' '.join(output), end='')



