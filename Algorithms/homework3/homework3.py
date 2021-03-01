nums = input().split()
target = int(input())  

def rocket(nums, sum):
    if sum == target:
        return 0;
    else:
        count = 0
        for i in range(1, len(nums) + 1):
            while sum + int(nums[-i]) <= target:
                sum += int(nums[-i])
                count +=1 
            count += rocket(nums[0:-i], sum + int(nums[-i]))
        return count


print(rocket(nums, 0), end="")
print(" rocket sections minimum", end="")