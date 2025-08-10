#include <iostream>
#include <numeric> // For std::iota
#include <vector>

using namespace std;

/**
 * @brief Recursively finds and prints all topological sorts of a Directed
 * Acyclic Graph (DAG).
 *
 * This function uses a backtracking approach. It finds a node with an in-degree
 * of 0, adds it to the current path, and recursively explores the remaining
 * graph.
 *
 * @param adj The adjacency list of the graph.
 * @param in_degree A vector storing the in-degrees of all vertices.
 * @param path A vector storing the current topological sort path being built.
 * @param visited A vector to keep track of visited nodes in the current path.
 */
void findAllTopologicalSorts(vector<vector<int>> &adj, vector<int> &in_degree,
                             vector<int> &path, vector<bool> &visited) {
  bool flag = false;

  for (size_t i = 0; i < adj.size(); ++i) {
    // Find a vertex with 0 in-degree that has not been visited yet.
    if (in_degree[i] == 0 && !visited[i]) {
      // Include current vertex in path and mark as visited
      path.push_back(i);
      visited[i] = true;

      // Decrease in-degree for all its neighbors
      for (int neighbor : adj[i]) {
        in_degree[neighbor]--;
      }

      // Recur for the remaining graph
      findAllTopologicalSorts(adj, in_degree, path, visited);

      // Backtrack: reset visited, path, and in_degree
      visited[i] = false;
      path.pop_back();
      for (int neighbor : adj[i]) {
        in_degree[neighbor]++;
      }

      flag = true;
    }
  }

  // If no vertex with 0 in-degree was found, we have a complete topological
  // sort.
  if (!flag) {
    for (int node : path) {
      cout << node << " ";
    }
    cout << endl;
  }
}

int main() {
  int n, e;
  cout << "Enter the number of vertices: ";
  cin >> n;
  cout << "Enter the number of edges: ";
  cin >> e;

  vector<vector<int>> adj(n);
  vector<int> in_degree(n, 0);

  cout << "Enter the edges (u v):" << endl;
  for (int i = 0; i < e; ++i) {
    int u, v;
    cin >> u >> v;
    if (u >= 0 && u < n && v >= 0 && v < n) {
      adj[u].push_back(v);
      in_degree[v]++;
    } else {
      cout << "Invalid edge, please try again." << endl;
      i--; // Decrement i to re-enter the edge
    }
  }

  vector<int> path;
  vector<bool> visited(n, false);

  cout << "\nAll Topological Sorts:" << endl;
  findAllTopologicalSorts(adj, in_degree, path, visited);

  return 0;
}