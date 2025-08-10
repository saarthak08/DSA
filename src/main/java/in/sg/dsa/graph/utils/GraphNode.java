package in.sg.dsa.graph.utils;

/*
 * Represents a node in a linked list, typically used for building an adjacency list representation
 * of a graph. Each node contains an integer data value (representing a vertex) and a reference to
 * the next node in the list.
 */
import java.util.Objects;

public class GraphNode {
  private int data;
  private GraphNode next;

  public GraphNode() {}

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public GraphNode getNext() {
    return next;
  }

  public void setNext(GraphNode next) {
    this.next = next;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    GraphNode graphNode = (GraphNode) o;
    return data == graphNode.data && Objects.equals(next, graphNode.next);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, next);
  }

  @Override
  public String toString() {
    return "GraphNode{" + "data=" + data + ", next=" + next + '}';
  }
}
