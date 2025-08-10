package java.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class FordFulkerson {

  private final int V;

  public FordFulkerson(int V) {
    this.V = V;
  }

  /**
   * Implements the Edmonds-Karp algorithm (a specific implementation of Ford-Fulkerson) using BFS
   * to find augmenting paths in the residual graph.
   *
   * @param graph The capacity graph.
   * @param s The source vertex.
   * @param t The sink (terminal) vertex.
   * @return The maximum possible flow from source to sink.
   */
  public int fordFulkerson(int[][] graph, int s, int t) {
    int u, v;

    // Create a residual graph and fill it with the capacities of the original graph.
    int[][] rGraph = new int[V][V];
    for (u = 0; u < V; u++) {
      System.arraycopy(graph[u], 0, rGraph[u], 0, V);
    }

    // This array is filled by BFS and stores the path.
    int[] parent = new int[V];

    int maxFlow = 0; // There is no flow initially

    // Augment the flow while there is a path from source to sink
    while (bfs(rGraph, s, t, parent)) {
      // Find the maximum flow through the path found.
      int pathFlow = Integer.MAX_VALUE;
      for (v = t; v != s; v = parent[v]) {
        u = parent[v];
        pathFlow = Math.min(pathFlow, rGraph[u][v]);
      }

      // Update residual capacities of the edges and reverse edges along the path
      for (v = t; v != s; v = parent[v]) {
        u = parent[v];
        rGraph[u][v] -= pathFlow;
        rGraph[v][u] += pathFlow;
      }

      // Add path flow to overall flow
      maxFlow += pathFlow;
    }

    return maxFlow;
  }

  /**
   * A BFS-based function to find if there is a path from source 's' to sink 't' in the residual
   * graph. Also fills parent[] to store the path.
   *
   * @param rGraph The residual graph.
   * @param s The source vertex.
   * @param t The sink vertex.
   * @param parent The array to store the augmenting path.
   * @return true if a path is found, false otherwise.
   */
  private boolean bfs(int[][] rGraph, int s, int t, int[] parent) {
    boolean[] visited = new boolean[V];
    Arrays.fill(visited, false);

    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(s);
    visited[s] = true;
    parent[s] = -1;

    while (!queue.isEmpty()) {
      int u = queue.poll();

      for (int v = 0; v < V; v++) {
        if (!visited[v] && rGraph[u][v] > 0) {
          queue.add(v);
          parent[v] = u;
          visited[v] = true;
        }
      }
    }

    // If we reached sink in BFS starting from source, then return true, else false
    return (visited[t]);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();
    FordFulkerson ff = new FordFulkerson(n);
    int[][] graph = new int[n][n];

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter an edge.");
      System.out.println("Press '2' to calculate Max Flow.");
      System.out.println("Press '3' to exit.");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the source vertex: ");
          int u = scanner.nextInt();
          System.out.print("Enter the destination vertex: ");
          int v = scanner.nextInt();
          System.out.print("Enter the capacity: ");
          int capacity = scanner.nextInt();
          if (u < 0 || v < 0 || u >= n || v >= n || capacity < 0) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
          graph[u][v] = capacity;
          break;
        case 2:
          System.out.print("Enter the source vertex: ");
          int s = scanner.nextInt();
          System.out.print("Enter the sink vertex: ");
          int t = scanner.nextInt();
          if (s < 0 || t < 0 || s >= n || t >= n) {
            System.out.println("Invalid source or sink. Please try again.");
            continue;
          }
          System.out.println("The maximum possible flow is " + ff.fordFulkerson(graph, s, t));
          break;
        case 3:
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}
