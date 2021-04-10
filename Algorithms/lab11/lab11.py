#
# Implementation of Dijkstra's Algorithm for 
# CS 412 Lab 10. 
#
# Author: John C. Bowers
# Version: March 26, 2021
#
from heapq import *

# Initialize the SSSP data structures
# we don't need pred here, since we
# only care about distances, not paths
def initSSSP(G, s):
    dist = [float('inf') for _ in G]
    dist[s] = 0
    return dist

# Dijkstra's algorithm
#
# input: Graph G as adjacency list with weights, 
#        each edge in G[u] is represented by a tuple (v, w)
#        representing an edge from u to v of weight w. 
#        s is the index of the start node
# returns:
#   The dist[u] lookup array for the distance
#   from s to each node, which is float('inf') if 
#   no path exists. 
#
def BellmanFord(G, s):
    dist = initSSSP(G, s)

    def is_tense(u, i):
        nonlocal G, dist
        v, wuv = G[u][i]
        return dist[u] + wuv < dist[v]

    def tense_edges():
        nonlocal G
        return[(u,i) for u in range(len(G)) for i in range(len(G[u])) if is_tense(u,i)]

    def relax(u, i):
        nonlocal G, dist
        v, wuv = G[u][i]
        dist[v] = dist[u] + wuv

    for _ in range(len(G)-1):
        for u in range(len(G)):
            for i in range(len(G[u])):
                if is_tense(u,i):
                    relax(u,i)
    
    if len(tense_edges()) > 0:
        return None
    else:
        return dist


# Read the first line of input
n, m, q = map(int, input().split())

# Build a graph adjacency list data structure
graph = [[] for _ in range(n)]

# Read in the graph
for _ in range(m):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

# Read and process each of the queries
for _ in range(q):
    s, t = map(int, input().split())
    dist = BellmanFord(graph, s)
    if dist[t] != float('inf'):
        print(dist[t])
    else:
        print("Impossible")