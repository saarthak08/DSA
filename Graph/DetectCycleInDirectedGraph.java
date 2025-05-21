/*
 * DetectCycleInDirectedGraph
 *
 * This program detects whether a given directed graph contains a cycle.
 * The graph is represented using an adjacency list, and Depth-First Search (DFS)
 * is used to traverse the graph. A recursion stack is used to detect back edges,
 * which indicate cycles in directed graphs.
 *
 * Algorithm Explanation:
 * - We use DFS to explore the graph starting from each unvisited node.
 * - A `visited[]` array ensures that we don't revisit already processed nodes.
 * - A `recursionStack[]` keeps track of the current path of the DFS. If we revisit
 *   a node that's in the current recursion stack, a cycle is detected.
 *
 * Time Complexity:
 * - Building the adjacency list: O(V + E)
 * - DFS traversal: O(V + E) in total, since each vertex and edge is visited once.
 *
 * Space Complexity:
 * - Adjacency list: O(V + E)
 * - visited[] and recursionStack[] arrays: O(V)
 * - Call stack in worst case (DFS): O(V)
 */

import java.util.ArrayList;
import java.util.Scanner;

public class DetectCycleInDirectedGraph {
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
    // Time Complexity for initialization: O(V)
    boolean[] visited = new boolean[v];
    boolean[] recursionStack = new boolean[v];

    for (int i = 0; i < v; i++) {
      if (!visited[i]) {
        if (dfs(i, adjList, visited, recursionStack)) {
          System.out.println("Graph contains cycle");
          sc.close(); // Close the scanner
          return;
        }
      }
    }

    System.out.println("Graph doesn't contain any cycle");
    sc.close(); // Close the scanner
  }

  private static boolean dfs(int vertex, ArrayList<Integer>[] adjList, boolean[] visited, boolean[] recursionStack) {
    // If the current vertex is already in the recursion stack, a cycle is detected
    if (recursionStack[vertex]) {
      return true;
    }

    // Mark the current vertex as visited and add it to the current recursion stack
    visited[vertex] = true;
    recursionStack[vertex] = true;

    // Explore all neighbors
    for (Integer neighbour : adjList[vertex]) {
      // If the neighbor has not been visited, recursively call DFS
      if (!visited[neighbour]) {
        if (dfs(neighbour, adjList, visited, recursionStack)) {
          return true;
        }
      }
      // Else if the neighbor is in the current recursion stack, a cycle is detected
      else if (recursionStack[neighbour]) {
        return true;
      }
    }

    // Remove the current vertex from the recursion stack after its DFS completes
    recursionStack[vertex] = false;
    return false;
  }
}
