/*
Time Complexity:
The overall time complexity of the Topological Sort using DFS is O(V + E), where V is the number of vertices and E is the number of edges.
This is because:
1. Initializing the adjacency list and visited array takes O(V) time.
2. Building the adjacency list by reading edges takes O(E) time.
3. The DFS function visits each vertex and each edge exactly once. The outer loop iterates through all vertices, and the `dfs` function explores their connected components. In total, every vertex and every edge is processed once.
4. Popping elements from the stack takes O(V) time.

Space Complexity:
The overall space complexity is O(V + E).
This is because:
1. The adjacency list `adjList` requires O(V + E) space to store the graph (V for the array of lists, and E for the total number of elements in all lists).
2. The `visited` array requires O(V) space.
3. The `stack` requires O(V) space in the worst case (e.g., a linear graph where all vertices are pushed onto the stack before any are popped).
4. The recursion stack for DFS also requires O(V) space in the worst case (for a skewed graph).
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSortDFS {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of vertices: ");
    int v = sc.nextInt();
    System.out.println("Enter the number of edges: ");
    int e = sc.nextInt();

    // Adjacency list to represent the graph
    // Time Complexity for initialization: O(V)
    ArrayList<Integer>[] adjList = new ArrayList[v];
    for (int i = 0; i < v; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Reading graph edges
    // Time Complexity for reading edges: O(E)
    for (int i = 0; i < e; i++) {
      System.out.println("Enter details of the edge " + (i + 1));
      System.out.println("Enter the first vertex of the edge: ");
      int v1 = sc.nextInt();
      System.out.println("Enter the second vertex of the edge: ");
      int v2 = sc.nextInt();

      if (v1 < 1 || v1 > v || v2 < 1 || v2 > v) {
        System.out.println("Invalid vertex number. Try again.");
        i--; // Decrement i to re-enter details for the same edge
        continue;
      }

      // Adjusting to 0-based indexing
      adjList[v1 - 1].add(v2 - 1);
    }

    // Visited array to keep track of visited vertices during DFS
    // Stack to store the topological order
    // Time Complexity for initialization: O(V)
    boolean[] visited = new boolean[v];
    Stack<Integer> stack = new Stack<>();

    // Iterate through all vertices to ensure all components are visited
    // Time Complexity for this loop: O(V + E) because each vertex and edge is
    // visited once
    for (int i = 0; i < v; i++) {
      if (!visited[i]) {
        dfs(i, adjList, visited, stack);
      }
    }

    System.out.println("Topological Sort:");
    // Pop elements from the stack to get the topological order
    // Time Complexity for popping from stack: O(V)
    while (!stack.isEmpty()) {
      System.out.print((stack.pop() + 1) + " ");
    }
    System.out.println(); // For a new line at the end
    sc.close(); // Close the scanner
  }

  /**
   * Performs a Depth First Search (DFS) traversal to find the topological order.
   * This function is called recursively.
   *
   * @param vertex  The current vertex being visited.
   * @param adjList The adjacency list representation of the graph.
   * @param visited A boolean array to keep track of visited vertices.
   * @param stack   A stack to store the vertices in topological order.
   *                <p>
   *                Time Complexity of DFS: O(V + E)
   *                Each vertex is visited once, and for each vertex, its adjacent
   *                edges are traversed once.
   *                Space Complexity of DFS (recursive call stack): O(V) in the
   *                worst case (skewed graph).
   */
  private static void dfs(int vertex, ArrayList<Integer>[] adjList, boolean[] visited, Stack<Integer> stack) {
    visited[vertex] = true; // Mark the current vertex as visited

    // Recur for all the vertices adjacent to this vertex
    for (Integer n : adjList[vertex]) {
      if (!visited[n]) {
        dfs(n, adjList, visited, stack);
      }
    }
    // Push current vertex to stack which stores result
    // This is done after all its neighbors (and their subgraphs) are processed
    stack.push(vertex);
  }
}
