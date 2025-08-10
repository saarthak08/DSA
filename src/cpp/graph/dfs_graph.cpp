#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Performs a Depth-First Search (DFS) traversal on a graph.
 *
 * @param u The current vertex being visited.
 * @param adj The adjacency list of the graph.
 * @param visited A vector to keep track of visited nodes.
 */
void dfs(int u, const vector<vector<int>> &adj, vector<bool> &visited) {
  // Mark the current node as visited and print it
  visited[u] = true;
  cout << u << " ";

  // Recur for all the vertices adjacent to this vertex
  for (int v : adj[u]) {
    if (!visited[v]) {
      dfs(v, adj, visited);
    }
  }
}

int main() {
  int n, e;
  cout << "Enter the number of vertices: ";
  cin >> n;
  cout << "Enter the number of edges: ";
  cin >> e;

  vector<vector<int>> adj(n);
  cout << "Enter the edges (u v) for an undirected graph:" << endl;
  for (int i = 0; i < e; ++i) {
    int u, v;
    cin >> u >> v;
    if (u >= 0 && u < n && v >= 0 && v < n) {
      adj[u].push_back(v);
      adj[v].push_back(u); // For undirected graph
    } else {
      cout << "Invalid edge, please try again." << endl;
      i--;
    }
  }

  vector<bool> visited(n, false);

  cout << "\nDepth-First Search Traversal:" << endl;
  // This loop ensures that all components of a disconnected graph are visited.
  for (int i = 0; i < n; ++i) {
    if (!visited[i]) {
      dfs(i, adj, visited);
    }
  }
  cout << endl;

  return 0;
}