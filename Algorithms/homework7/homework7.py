numLines = int(input())
scan = []

for i in range(numLines):
    scan.append(input().split())



def findLand(x, y):
    if int(scan[x][y]) == 0:
        return 0
    else:
        scan[x][y] = "0"
        size = 1
        if x > 0:
            size += findLand(x-1, y)
        if y > 0:
            size += findLand (x, y-1)
        if x < len(scan) - 1:
            size += findLand(x+1, y)
        if y < len(scan[0]) - 1:
            size += findLand(x, y+1)
        return size

biggest = 0

for column in range(len(scan)):
    for row in range(len(scan[0])):
        curr = findLand(row, column)
        if curr > biggest:
            biggest = curr
    
print(biggest)


