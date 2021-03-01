import re

def maxMin(nums):
    if (len(nums) == 3):
        if nums[1] == '+':
            return [int(nums[0]) + int(nums[2]), int(nums[0]) + int(nums[2])]
        elif nums[1] == '-':
            return [int(nums[0]) - int(nums[2]), int(nums[0]) - int(nums[2])]
        elif nums[1] == '*':
            return [int(nums[0]) * int(nums[2]), int(nums[0]) * int(nums[2])]
    elif (len(nums) == 1):
        return [nums[0], nums[0]]
    else:
        max = float('-inf')
        min = float('inf')
        temp = [min, max]

        for x in range(len(nums)):
            if (nums[x] == '+'):
                temp[0] = int(maxMin(nums[:x])[0]) + int(maxMin(nums[x+1:])[0])
                temp[1] = int(maxMin(nums[:x])[1]) + int(maxMin(nums[x+1:])[1])
            elif (nums[x] == '-'):
                temp[0] = int(maxMin(nums[:x])[0]) - int(maxMin(nums[x+1:])[0])
                temp[1] = int(maxMin(nums[:x])[1]) - int(maxMin(nums[x+1:])[1])
            elif (nums[x] == '*'):
                temp[0] = int(maxMin(nums[:x])[0]) * int(maxMin(nums[x+1:])[0])
                temp[1] = int(maxMin(nums[:x])[1]) * int(maxMin(nums[x+1:])[1])
            if (temp[0] < min):
                min = temp[0]
            if (temp[1] > max):
                max = temp[1]
        return [min, max]




output = maxMin(re.split('(\+|\*|\-)', input()))
print(str(output[0]) + ' ' + str(output[1]))