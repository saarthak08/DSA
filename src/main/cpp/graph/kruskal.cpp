#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

// Represents a weighted edge in the graph
struct Edge {
  int source, destination, weight;
};

// Represents a subset for Union-Find
struct Subset {
  int parent;
  int rank;
};

// Comparison function to sort edges based on their weight
bool compareEdges(const Edge &a, const Edge &b) { return a.weight < b.weight; }

// Finds set of an element i (uses path compression)
int find(vector<Subset> &subsets, int i) {
  if (subsets[i].parent != i) {
    subsets[i].parent = find(subsets, subsets[i].parent);
  }
  return subsets[i].parent;
}

// Performs union of two sets of x and y (uses union by rank)
void unite(vector<Subset> &subsets, int x, int y) {
  int xroot = find(subsets, x);
  int yroot = find(subsets, y);

  if (subsets[xroot].rank < subsets[yroot].rank) {
    subsets[xroot].parent = yroot;
  } else if (subsets[xroot].rank > subsets[yroot].rank) {
    subsets[yroot].parent = xroot;
  } else {
    subsets[yroot].parent = xroot;
    subsets[xroot].rank++;
  }
}

/**
 * @brief Finds the Minimum Spanning Tree (MST) of a graph using Kruskal's
 * algorithm.
 *
 * @param V The number of vertices.
 * @param edges A vector of all edges in the graph.
 */
void kruskalMST(int V, vector<Edge> &edges) {
  vector<Edge> result; // This will store the resultant MST
  int i = 0;           // An index variable, used for sorted edges
  int e = 0;           // An index variable, used for result[]

  // Step 1: Sort all the edges in non-decreasing order of their weight.
  sort(edges.begin(), edges.end(), compareEdges);

  // Allocate memory for creating V subsets
  vector<Subset> subsets(V);
  for (int v = 0; v < V; ++v) {
    subsets[v].parent = v;
    subsets[v].rank = 0;
  }

  // Number of edges to be taken is equal to V-1
  while (e < V - 1 && i < edges.size()) {
    // Step 2: Pick the smallest edge. And increment the index for next
    // iteration.
    Edge next_edge = edges[i++];

    int x = find(subsets, next_edge.source);
    int y = find(subsets, next_edge.destination);

    // If including this edge doesn't cause a cycle, include it in the result
    // and increment the index of result for next edge.
    if (x != y) {
      result.push_back(next_edge);
      unite(subsets, x, y);
      e++;
    }
  }

  // Print the contents of MST
  cout << "\nEdges in the Minimum Spanning Tree:" << endl;
  int minimumCost = 0;
  for (const auto &edge : result) {
    cout << edge.source << " -- " << edge.destination << " == " << edge.weight
         << endl;
    minimumCost += edge.weight;
  }
  cout << "Minimum Cost: " << minimumCost << endl;
}

int main() {
  int V, E;
  cout << "Enter the number of vertices: ";
  cin >> V;
  cout << "Enter the number of edges: ";
  cin >> E;

  vector<Edge> edges(E);
  cout << "Enter the edges (source destination weight):" << endl;
  for (int i = 0; i < E; ++i) {
    cin >> edges[i].source >> edges[i].destination >> edges[i].weight;
  }

  kruskalMST(V, edges);

  return 0;
}