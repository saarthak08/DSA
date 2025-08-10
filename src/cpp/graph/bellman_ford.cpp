#include <climits> // For INT_MAX
#include <iostream>
#include <vector>

using namespace std;

// Struct to represent a weighted edge in the graph
struct Edge {
  int source, destination, weight;
};

/**
 * @brief Finds the shortest paths from a source vertex to all other vertices
 *        using the Bellman-Ford algorithm. Also detects negative weight cycles.
 *
 * @param V The number of vertices in the graph.
 * @param edges A vector of all edges in the graph.
 * @param source The source vertex.
 */
void bellmanFord(int V, const vector<Edge> &edges, int source) {
  // Step 1: Initialize distances from source to all other vertices as INFINITE
  vector<int> distance(V, INT_MAX);
  distance[source] = 0;

  // Step 2: Relax all edges |V| - 1 times.
  for (int i = 1; i <= V - 1; i++) {
    for (const auto &edge : edges) {
      int u = edge.source;
      int v = edge.destination;
      int w = edge.weight;
      if (distance[u] != INT_MAX && distance[u] + w < distance[v]) {
        distance[v] = distance[u] + w;
      }
    }
  }

  // Step 3: Check for negative-weight cycles.
  for (const auto &edge : edges) {
    int u = edge.source;
    int v = edge.destination;
    int w = edge.weight;
    if (distance[u] != INT_MAX && distance[u] + w < distance[v]) {
      cout << "\nGraph contains a negative weight cycle!" << endl;
      return;
    }
  }

  // Print the result
  cout << "\nVertex distance from source " << source << ":" << endl;
  for (int i = 0; i < V; i++) {
    cout << i << "\t\t"
         << (distance[i] == INT_MAX ? "INF" : to_string(distance[i])) << endl;
  }
}

int main() {
  int V, E;
  cout << "Enter the number of vertices: ";
  cin >> V;
  cout << "Enter the number of edges: ";
  cin >> E;

  vector<Edge> edges;
  cout << "Enter the edges (source destination weight):" << endl;
  for (int i = 0; i < E; ++i) {
    int u, v, w;
    cin >> u >> v >> w;
    if (u >= 0 && u < V && v >= 0 && v < V) {
      edges.push_back({u, v, w});
    } else {
      cout << "Invalid edge, please try again." << endl;
      i--;
    }
  }

  int source;
  cout << "Enter the source vertex: ";
  cin >> source;

  if (source >= 0 && source < V) {
    bellmanFord(V, edges, source);
  } else {
    cout << "Invalid source vertex." << endl;
    n
  }

  return 0;
}