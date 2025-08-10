#include <climits>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

// Define a pair for storing vertex and its key (weight) in the priority queue
using iPair = pair<int, int>;

/**
 * @brief Finds the Minimum Spanning Tree (MST) of a graph using Prim's
 * algorithm.
 *
 * @param V The number of vertices.
 * @param adj The adjacency list of the graph, where adj[u] contains pairs
 *            of {v, w} for edges from u to v with weight w.
 */
void primMST(int V, const vector<vector<iPair>> &adj) {
  // Create a priority queue to store vertices that are being preprocessed.
  // The pair is (weight, vertex). We use greater<iPair> to make it a min-heap.
  priority_queue<iPair, vector<iPair>, greater<iPair>> pq;

  // Create a vector for keys and initialize all keys as infinite
  vector<int> key(V, INT_MAX);
  // Array to store constructed MST
  vector<int> parent(V, -1);
  // To keep track of vertices included in MST
  vector<bool> inMST(V, false);

  // Start with the first vertex
  int src = 0;
  pq.push({0, src});
  key[src] = 0;

  while (!pq.empty()) {
    // The first vertex in pair is the minimum key vertex,
    // extract it from priority queue.
    int u = pq.top().second;
    pq.pop();

    // Include the dequeued node in MST
    inMST[u] = true;

    // 'i' is used to get all adjacent vertices of a vertex
    for (const auto &edge : adj[u]) {
      int v = edge.first;
      int weight = edge.second;

      // If v is not in MST and weight of (u,v) is smaller
      // than current key of v
      if (!inMST[v] && key[v] > weight) {
        // Updating key of v
        key[v] = weight;
        pq.push({key[v], v});
        parent[v] = u;
      }
    }
  }

  // Print the constructed MST
  cout << "\nEdges in the Minimum Spanning Tree:" << endl;
  int minimumCost = 0;
  for (int i = 1; i < V; ++i) {
    if (parent[i] != -1) {
      cout << parent[i] << " -- " << i << " == " << key[i] << endl;
      minimumCost += key[i];
    }
  }
  cout << "Minimum Cost: " << minimumCost << endl;
}

int main() {
  int V, E;
  cout << "Enter the number of vertices: ";
  cin >> V;
  cout << "Enter the number of edges: ";
  cin >> E;

  vector<vector<iPair>> adj(V);
  cout << "Enter the edges (source destination weight):" << endl;
  for (int i = 0; i < E; ++i) {
    int u, v, w;
    cin >> u >> v >> w;
    if (u >= 0 && u < V && v >= 0 && v < V && w >= 0) {
      adj[u].push_back({v, w});
      adj[v].push_back({u, w}); // For undirected graph
    } else {
      cout << "Invalid edge or negative weight, please try again." << endl;
      i--;
    }
  }

  primMST(V, adj);

  return 0;
}