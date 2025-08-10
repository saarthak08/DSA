package java_codes.graph.utils;

/*
 * Represents a weighted edge in a graph. This class connects two vertices, 'u' and 'v', with an
 * associated 'weight'. It implements the Comparable interface to allow edges to be sorted based on
 * their weight, which is crucial for algorithms like Kruskal's and Prim's.
 */
import java.util.Objects;

public class GraphEdge implements Comparable<GraphEdge> {
  private int src;
  private int dest;
  private int weight;

  public GraphEdge(int src, int dest, int weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  public GraphEdge() {}

  public int compareTo(GraphEdge that) {
    return this.weight - that.weight;
  }

  public int getSrc() {
    return src;
  }

  public void setSrc(int src) {
    this.src = src;
  }

  public int getDest() {
    return dest;
  }

  public void setDest(int dest) {
    this.dest = dest;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    GraphEdge graphEdge = (GraphEdge) o;
    return src == graphEdge.src && dest == graphEdge.dest && weight == graphEdge.weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(src, dest, weight);
  }

  @Override
  public String toString() {
    return "GraphEdge{" + "u=" + src + ", v=" + dest + ", weight=" + weight + '}';
  }
}
