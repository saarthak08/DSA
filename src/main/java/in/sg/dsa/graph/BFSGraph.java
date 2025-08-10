package in.sg.dsa.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// Time Complexity: O(V+E)-LinkedList, O(V^2)-> Matrix

// Space Complexity: Breadth of Queue: O(V)=>Worst Case

public class BFSGraph {

  private final int V;
  private final List<List<Integer>> adj;

  public BFSGraph(int V) {
    this.V = V;
    adj = new ArrayList<>(V);
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdge(int u, int v) {
    adj.get(u).add(v);
    adj.get(v).add(u); // For undirected graph
  }

  public void printGraph() {
    System.out.println("Adjacency List:");
    for (int i = 0; i < V; i++) {
      System.out.print(i + ": ");
      for (int neighbor : adj.get(i)) {
        System.out.print(neighbor + " ");
      }
      System.out.println();
    }
  }

  public void bfs() {
    System.out.println("\nBFS of the Graph:");
    boolean[] visited = new boolean[V];
    for (int i = 0; i < V; ++i) {
      if (!visited[i]) {
        bfsUtil(i, visited);
      }
    }
    System.out.println();
  }

  private void bfsUtil(int startNode, boolean[] visited) {
    Queue<Integer> queue = new ArrayDeque<>();
    visited[startNode] = true;
    queue.offer(startNode);

    while (!queue.isEmpty()) {
      int v = queue.poll();
      System.out.print(v + " ");

      for (int neighbor : adj.get(v)) {
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          queue.offer(neighbor);
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();
    BFSGraph g = new BFSGraph(n);

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter an edge.");
      System.out.println("Press '2' to print the graph.");
      System.out.println("Press '3' to perform BFS.");
      System.out.println("Press '4' to exit.");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the first vertex: ");
          int u = scanner.nextInt();
          System.out.print("Enter the second vertex: ");
          int v = scanner.nextInt();
          if (u < 0 || v < 0 || u >= n || v >= n) {
            System.out.println("Invalid vertex. Please try again.");
            continue;
          }
          g.addEdge(u, v);
          break;
        case 2:
          g.printGraph();
          break;
        case 3:
          g.bfs();
          break;
        case 4:
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}
