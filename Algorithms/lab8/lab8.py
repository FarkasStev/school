numVertexes = int(input())
graph = []
status = []
for i in range(numVertexes - 1):
    graph.append(input().split())
    status.append("NEW")

def acyclic(curr):
    if status[curr] == "VISITED":
        return False
    elif status[curr] == "ACTIVE":
        return False
    else:
        for i in range(1, len(graph[curr])):
            print(curr)
            print(": ACTIVE")
            status[curr] = "ACTIVE"
            if (not acyclic(int(graph[curr][i]))):
                return False
        status[curr] = "VISITED"
        return True
print(acyclic(0))