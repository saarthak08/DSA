#include <iostream>
#include <queue>
#include <vector>

using namespace std;

/**
 * @brief Performs a Breadth-First Search (BFS) traversal on a graph.
 *
 * @param startNode The node to start the traversal from.
 * @param adj The adjacency list of the graph.
 * @param visited A vector to keep track of visited nodes.
 */
void bfs(int startNode, const vector<vector<int>> &adj, vector<bool> &visited) {
  queue<int> q;

  // Mark the current node as visited and enqueue it
  visited[startNode] = true;
  q.push(startNode);

  while (!q.empty()) {
    // Dequeue a vertex from queue and print it
    int u = q.front();
    cout << u << " ";
    q.pop();

    // Get all adjacent vertices of the dequeued vertex u.
    // If an adjacent has not been visited, then mark it visited
    // and enqueue it.
    for (int v : adj[u]) {
      if (!visited[v]) {
        visited[v] = true;
        q.push(v);
      }
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

  cout << "\nBreadth-First Search Traversal:" << endl;
  // This loop ensures that all components of a disconnected graph are visited.
  for (int i = 0; i < n; ++i) {
    if (!visited[i]) {
      bfs(i, adj, visited);
    }
  }
  cout << endl;

  return 0;
}