#include <algorithm>
#include <climits>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

/**
 * @brief Finds if a path exists from source to sink in the residual graph using
 * BFS.
 *
 * @param rGraph The residual graph.
 * @param s The source vertex.
 * @param t The sink vertex.
 * @param parent A vector to store the path.
 * @return true if a path is found, false otherwise.
 */
bool bfs(const vector<vector<int>> &rGraph, int s, int t, vector<int> &parent) {
  int V = rGraph.size();
  vector<bool> visited(V, false);
  queue<int> q;

  q.push(s);
  visited[s] = true;
  parent[s] = -1;

  while (!q.empty()) {
    int u = q.front();
    q.pop();

    for (int v = 0; v < V; v++) {
      if (!visited[v] && rGraph[u][v] > 0) {
        q.push(v);
        parent[v] = u;
        visited[v] = true;
      }
    }
  }
  // If we reached sink in BFS starting from source, then return true
  return visited[t];
}

/**
 * @brief Implements the Edmonds-Karp algorithm to find the maximum flow.
 *
 * This is an implementation of the Ford-Fulkerson method that uses BFS to
 * find augmenting paths in the residual graph.
 *
 * @param graph The capacity graph represented by an adjacency matrix.
 * @param s The source vertex.
 * @param t The sink vertex.
 * @return The maximum possible flow from source to sink.
 */
int fordFulkerson(const vector<vector<int>> &graph, int s, int t) {
  int V = graph.size();
  vector<vector<int>> rGraph = graph; // Create a residual graph
  vector<int> parent(V);
  int max_flow = 0;

  // Augment the flow while there is a path from source to sink
  while (bfs(rGraph, s, t, parent)) {
    // Find the maximum flow through the path found (bottleneck capacity)
    int path_flow = INT_MAX;
    for (int v = t; v != s; v = parent[v]) {
      int u = parent[v];
      path_flow = min(path_flow, rGraph[u][v]);
    }

    // Update residual capacities of the edges and reverse edges
    for (int v = t; v != s; v = parent[v]) {
      int u = parent[v];
      rGraph[u][v] -= path_flow;
      rGraph[v][u] += path_flow;
    }

    // Add path flow to overall flow
    max_flow += path_flow;
  }

  return max_flow;
}

int main() {
  int V, E;
  cout << "Enter the number of vertices: ";
  cin >> V;
  cout << "Enter the number of edges: ";
  cin >> E;

  vector<vector<int>> graph(V, vector<int>(V, 0));
  cout << "Enter the edges (source destination capacity):" << endl;
  for (int i = 0; i < E; ++i) {
    int u, v, capacity;
    cin >> u >> v >> capacity;
    if (u >= 0 && u < V && v >= 0 && v < V && capacity >= 0) {
      graph[u][v] = capacity;
    } else {
      cout << "Invalid edge or capacity, please try again." << endl;
      i--;
    }
  }

  int source, sink;
  cout << "Enter the source vertex: ";
  cin >> source;
  cout << "Enter the sink vertex: ";
  cin >> sink;

  if (source >= 0 && source < V && sink >= 0 && sink < V) {
    cout << "\nThe maximum possible flow is "
         << fordFulkerson(graph, source, sink) << endl;
  } else {
    cout << "Invalid source or sink vertex." << endl;
  }

  return 0;
}