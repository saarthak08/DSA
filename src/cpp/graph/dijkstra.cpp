#include <climits>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

// Define a pair for storing vertex and its distance in the priority queue
using iPair = pair<int, int>;

/**
 * @brief Finds the shortest paths from a source vertex to all other vertices
 *        using Dijkstra's algorithm.
 *
 * This implementation uses a priority queue to efficiently find the vertex
 * with the minimum distance to visit next. It is suitable for graphs with
 * non-negative edge weights.
 *
 * @param V The number of vertices in the graph.
 * @param adj The adjacency list of the graph, where adj[u] contains pairs
 *            of {v, w} for edges from u to v with weight w.
 * @param src The source vertex.
 */
void dijkstra(int V, const vector<vector<iPair>> &adj, int src) {
  // Create a priority queue to store vertices that are being preprocessed.
  // The pair is (distance, vertex). We use greater<iPair> to make it a
  // min-heap.
  priority_queue<iPair, vector<iPair>, greater<iPair>> pq;

  // Create a vector for distances and initialize all distances as infinite.
  vector<int> dist(V, INT_MAX);

  // Insert source itself in priority queue and initialize its distance as 0.
  pq.push({0, src});
  dist[src] = 0;

  while (!pq.empty()) {
    // The first vertex in pair is the minimum distance vertex,
    // extract it from priority queue.
    int u = pq.top().second;
    pq.pop();

    // 'i' is used to get all adjacent vertices of a vertex
    for (const auto &edge : adj[u]) {
      int v = edge.first;
      int weight = edge.second;

      // If there is a shorter path to v through u.
      if (dist[v] > dist[u] + weight) {
        // Updating distance of v
        dist[v] = dist[u] + weight;
        pq.push({dist[v], v});
      }
    }
  }

  // Print the calculated shortest distances
  cout << "\nVertex distance from source " << src << ":" << endl;
  for (int i = 0; i < V; ++i) {
    cout << i << "\t\t" << (dist[i] == INT_MAX ? "INF" : to_string(dist[i]))
         << endl;
  }
}

int main() {
  int V, E;
  cout << "Enter the number of vertices: ";
  cin >> V;
  cout << "Enter the number of edges: ";
  cin >> E;

  // Adjacency list where adj[u] contains pairs of {v, weight}
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

  int source;
  cout << "Enter the source vertex: ";
  cin >> source;

  if (source >= 0 && source < V) {
    dijkstra(V, adj, source);
  } else {
    cout << "Invalid source vertex." << endl;
  }

  return 0;
}