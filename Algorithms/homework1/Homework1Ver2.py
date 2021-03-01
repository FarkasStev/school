forestSounds = input().split()
numLines = int(input())
translation =  {}
animalsHeard = []
foxSounds = []
for i in range(numLines):
    line = input().split()
    translation[line[0]] =  line[2]

for sound in forestSounds:
    for i, j in translation.items():
        if sound == j:
            if animalsHeard.count(i) < 1:
                animalsHeard.append(i)
                
for i, j in translation.items():
    while forestSounds.count(j) > 0:
        forestSounds.remove(j)

input()
print("what the fox says: " + " ".join(forestSounds))
print("also heard: " + " ".join(animalsHeard), end='')