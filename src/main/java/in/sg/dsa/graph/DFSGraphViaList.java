package in.sg.dsa.graph;

/*
 * An implementation of Depth First Search (DFS) for a graph using a custom adjacency list. NOTE:
 * This is a procedural-style implementation with known issues. For a more robust, object-oriented
 * version, please see {@link DFSGraph}.
 *
 * <p>Algorithm: DFS explores as far as possible along each branch before backtracking. It uses a
 * 'visited' array to avoid processing the same node more than once. The traversal can be
 * implemented recursively, which naturally uses a stack (the call stack).
 *
 * <p>Flaws in this implementation: - The main loop only traverses the first connected component of
 * the graph. - The use of static global variables makes the code less modular and reusable.
 *
 * <p>Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges,
 * because each vertex and each edge is visited once.
 *
 * <p>Space Complexity: O(V + E) for storing the graph in a custom adjacency list. The recursion
 * stack space is O(H) where H is the maximum depth of the recursion, which is O(V) in the worst
 * case.
 */
import in.sg.dsa.graph.utils.GraphNode;
import java.util.Scanner;

// Time Complexity: O(V+E)-LinkedList, O(V^2)-> Matrix

// Space Complexity: O(V)=>Worst Case

public class DFSGraphViaList {

  private static final Scanner sc = new Scanner(System.in);
  private static boolean[] visited;
  private static boolean[] isConnected;
  private static GraphNode[] graph;

  public static void main(String[] args) {
    int vertices;
    System.out.println("Enter the number of vertices:");
    vertices = sc.nextInt();
    visited = new boolean[vertices + 1];
    isConnected = new boolean[vertices + 1];
    initializeGraph(vertices);
    printGraph(vertices);
    System.out.println("\n\nDepth First Search:");
    for (int i = 1; i <= vertices; i++) {
      if (isConnected[i]) {
        dfs(i);
        break;
      }
    }
    System.out.println();
  }

  private static void initializeGraph(int vertices) {
    int u, v, i;
    graph = new GraphNode[vertices + 1];
    for (i = 1; i <= vertices; i++) {
      graph[i] = new GraphNode();
      graph[i].setData(i);
    }
    System.out.println("\n\nEnter the edges (Vertices of the corresponding edges):");
    while (true) {
      System.out.println("\n\nPress '-1' to finish entering the edges.");
      System.out.println("\nEnter the first vertex of the edge:");
      u = sc.nextInt();
      if (u == -1) {
        break;
      }
      System.out.println("Enter the second vertex of the edge:");
      v = sc.nextInt();
      if (v == -1) {
        break;
      }
      if (u < 1 || u > vertices || v < 1 || v > vertices) {
        System.out.println("\n\nError! Invalid Input! Try Again!");
        continue;
      }
      isConnected[u] = true;
      isConnected[v] = true;
      addToList(u, v);
    }
  }

  private static void printGraph(int vertices) throws NullPointerException {
    System.out.println("\n\nGraph:");
    for (int i = 1; i <= vertices; i++) {
      GraphNode iterator = graph[i];
      while (iterator != null) {
        System.out.print(iterator.getData());
        if (iterator.getNext() != null) {
          System.out.print(" --> ");
        }
        iterator = iterator.getNext();
      }
      System.out.println();
    }
  }

  private static void addToList(int u, int v) {
    GraphNode newGraphNodeFirst = new GraphNode();
    GraphNode newGraphNodeSecond = new GraphNode();
    newGraphNodeFirst.setData(u);
    newGraphNodeSecond.setData(v);
    GraphNode iterator = graph[u];
    while (iterator.getNext() != null) {
      iterator = iterator.getNext();
    }
    iterator.setNext(newGraphNodeSecond);
    iterator = graph[v];
    while (iterator.getNext() != null) {
      iterator = iterator.getNext();
    }
    iterator.setNext(newGraphNodeFirst);
  }

  private static void dfs(int vertex) throws NullPointerException {
    System.out.print(vertex + "\t");
    visited[vertex] = true;
    GraphNode iterator = graph[vertex];
    while (iterator != null) {
      if (!visited[iterator.getData()]) {
        dfs(iterator.getData());
      }
      iterator = iterator.getNext();
    }
  }
}
