targetWeight = int(input())
lines = int(input())
items = []
for x in range(lines):
    items.append(input().split())


items.sort(key=lambda item: int(item[1])/int(item[2]))

def fractionalKnapsack(items, weight):
    nums[len(items)]
    if (weight == 0):
        return nums
    else:

        for x in range(len(items)):
            if items[x[1]] < weight:
                nums = fractionalKnapsack(items, weight - items[x[1]])
                return ++nums[x]
              
       
output = fractionalKnapsack(items, targetWeight)

for x in range(len(output)):
    print(items[x[0]] + '{' + output[x] + '}' + ' ')


