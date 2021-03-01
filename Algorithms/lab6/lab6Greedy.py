targetWeight = int(input())
lines = int(input())
items = [[]]
for x in range(lines):
    items[x] = input()

def fractionalKnapsack(items, weight):
    if (weight == 0):
        return 0
    else:
        