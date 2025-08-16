package java_codes.graph.utils;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjacencyList implements Graph {
  private final int V;
  private final List<List<GraphEdge>> adj;
  private final boolean isDirected;
  private final boolean isWeighted;

  public GraphAdjacencyList(int V, boolean isDirected, boolean isWeighted) {
    this.V = V;
    this.isDirected = isDirected;
    this.isWeighted = isWeighted;
    adj = new ArrayList<>(V);
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
  }

  @Override
  public int getVertexCount() {
    return V;
  }

  @Override
  public void addEdge(int u, int v) {
    if (isWeighted) {
      throw new UnsupportedOperationException("Cannot add unweighted edge to a weighted graph.");
    }
    adj.get(u).add(new GraphEdge(u, v, 1)); // Default weight 1 for unweighted
    if (!isDirected) {
      adj.get(v).add(new GraphEdge(v, u, 1));
    }
  }

  @Override
  public void addEdge(int u, int v, int weight) {
    if (!isWeighted) {
      throw new UnsupportedOperationException("Cannot add weighted edge to an unweighted graph.");
    }
    adj.get(u).add(new GraphEdge(u, v, weight));
    if (!isDirected) {
      adj.get(v).add(new GraphEdge(v, u, weight));
    }
  }

  @Override
  public List<GraphEdge> getNeighbors(int vertex) {
    return adj.get(vertex);
  }

  @Override
  public boolean isDirected() {
    return isDirected;
  }

  @Override
  public boolean isWeighted() {
    return isWeighted;
  }
}
