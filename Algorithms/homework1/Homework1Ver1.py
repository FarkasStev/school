forestSounds = input().split()
numLines = int(input())
translation =  []
animalsHeard = []
foxSounds = []
for i in range(numLines):
    line = input().split()
    translation.append([line[0], line[2]])

for sound in forestSounds:
    for i in range(len(translation)):
        if sound == translation[i][1]:
            if animalsHeard.count(translation[i][0]) < 1:
                animalsHeard.append(translation[i][0])
                
for sound in translation:
    while forestSounds.count(sound[1]) > 0:
        forestSounds.remove(sound[1])

input()
print("what the fox says: " + " ".join(forestSounds))
print("also heard: " + " ".join(animalsHeard), end='')

