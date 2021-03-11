numPlants = int(input())
plantStrings = input().split()
plants = []
for plantString in plantStrings:
    plants.append(int(plantString))

plants.sort(reverse=True)
partyDay = 2
currDay = 1

for plant in plants:
    if currDay + plant > partyDay:
        partyDay = currDay + plant

print(partyDay + 2)
