targetWeight = int(input())
lines = int(input())
items = []
for x in range(lines):
    items.append(input().split())

sortedItems = items
sortedItems = sorted(items, key=lambda item: int(item[1])/int(item[2]), reverse=True)
def fractionalKnapsack(items, weight):
    nums = {}
    if weight == 0:
        return nums
    else:

        for x in range(lines):
            if int(items[x][2]) > 0:
                if int(items[x][2]) <= weight:
                    weightLoss = int(items[x][2])
                    items[x][2] = 0
                    nums = fractionalKnapsack(items, weight - weightLoss)
                    nums[items[x][0]] = weightLoss 
                else:
                    nums[items[x][0]] = int(items[x][2]) - weight
                return nums 
result = fractionalKnapsack(sortedItems, targetWeight)
for x in range(len(items)):
    print(items[x][0] + '(' + str(result[items[x][0]]) + ')', end=' ')




