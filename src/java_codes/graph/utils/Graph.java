package java_codes.graph.utils;

import java.util.List;

public interface Graph {

  public int getVertexCount();

  public void addEdge(int u, int v);

  public void addEdge(int u, int v, int weight);

  public List<GraphEdge> getNeighbors(int vertex);

  public boolean isDirected();

  public boolean isWeighted();
}
