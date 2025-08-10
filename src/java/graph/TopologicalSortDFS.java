package java.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSortDFS {

  private final int V;
  private final List<List<Integer>> adj;

  public TopologicalSortDFS(int V) {
    this.V = V;
    adj = new ArrayList<>(V);
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdge(int u, int v) {
    adj.get(u).add(v);
  }

  public void topologicalSort() {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[V];
    boolean[] recursionStack = new boolean[V]; // To detect cycles

    for (int i = 0; i < V; i++) {
      if (!visited[i]) {
        if (topologicalSortUtil(i, visited, recursionStack, stack)) {
          System.out.println("\nGraph contains a cycle! No topological sort possible.");
          return;
        }
      }
    }

    System.out.println("\nTopological Sort:");
    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
    System.out.println();
  }

  private boolean topologicalSortUtil(
      int v, boolean[] visited, boolean[] recursionStack, Stack<Integer> stack) {
    visited[v] = true;
    recursionStack[v] = true;

    for (Integer neighbor : adj.get(v)) {
      if (!visited[neighbor]) {
        if (topologicalSortUtil(neighbor, visited, recursionStack, stack)) {
          return true; // Cycle detected
        }
      } else if (recursionStack[neighbor]) {
        return true; // Cycle detected
      }
    }
    recursionStack[v] = false; // Remove the vertex from recursion stack
    stack.push(v);
    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();
    TopologicalSortDFS g = new TopologicalSortDFS(n);

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter an edge.");
      System.out.println("Press '2' to perform Topological Sort.");
      System.out.println("Press '3' to exit.");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the source vertex: ");
          int u = scanner.nextInt();
          System.out.print("Enter the destination vertex: ");
          int v = scanner.nextInt();
          if (u < 0 || v < 0 || u >= n || v >= n) {
            System.out.println("Invalid vertex. Please try again.");
            continue;
          }
          g.addEdge(u, v);
          break;
        case 2:
          g.topologicalSort();
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
