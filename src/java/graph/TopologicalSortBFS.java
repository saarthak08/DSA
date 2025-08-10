package java.graph;

/*
Time Complexity:
The overall time complexity of Kahn's Algorithm for Topological Sort is O(V + E), where V is the number of vertices and E is the number of edges.
This is because:
1. Initializing the adjacency list and indegree array takes O(V) time.
2. Building the adjacency list and calculating initial indegrees by reading edges takes O(E) time.
3. The BFS traversal: Each vertex is enqueued and dequeued at most once. When a vertex is dequeued, its outgoing edges are traversed. In total, each edge is processed once (when its source vertex is dequeued). Thus, the BFS part is O(V + E).
4. Populating the initial queue and iterating through the result list takes O(V) time.

Space Complexity:
The overall space complexity is O(V + E).
This is because:
1. The adjacency list `adjList` requires O(V + E) space to store the in.sg.dsa.graph (V for the array of lists, and E for the total number of elements in all lists).
2. The `indegree` array requires O(V) space.
3. The `queue` can store up to O(V) vertices in the worst case.
4. The `result` ArrayList also stores O(V) vertices.
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class TopologicalSortBFS {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of vertices: ");
    int v = sc.nextInt();
    System.out.println("Enter the number of edges: ");
    int e = sc.nextInt();

    // Adjacency list to represent the in.sg.dsa.graph
    ArrayList<Integer>[] adjList = new ArrayList[v];
    for (int i = 0; i < v; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Array to store the in-degree of each vertex
    int[] indegree = new int[v];

    // Reading in.sg.dsa.graph edges and populating adjacency list and in-degree array
    for (int i = 0; i < e; i++) {
      System.out.println("Enter details of the edge " + (i + 1));
      System.out.println("Enter the first vertex of the edge: ");
      int v1 = sc.nextInt();
      System.out.println("Enter the second vertex of the edge: ");
      int v2 = sc.nextInt();

      // Input validation for vertex numbers
      if (v1 < 1 || v1 > v || v2 < 1 || v2 > v) {
        System.out.println("Invalid vertex number. Try again.");
        i--; // Decrement i to re-enter details for the same edge
        continue;
      }

      // Adjusting to 0-based indexing for internal representation
      adjList[v1 - 1].add(v2 - 1);
      indegree[v2 - 1]++; // Increment in-degree of the destination vertex
    }

    // Queue for BFS traversal (Kahn's algorithm)
    ArrayDeque<Integer> queue = new ArrayDeque<>();

    // Add all vertices with an in-degree of 0 to the queue
    for (int i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    // List to store the topological sort result
    ArrayList<Integer> result = new ArrayList<>();
    // Counter for visited nodes, used to detect cycles
    int visitedNodes = 0;

    // Perform BFS traversal
    while (!queue.isEmpty()) {
      visitedNodes++; // Increment count of processed nodes
      int node = queue.poll(); // Dequeue a vertex
      result.add(node); // Add it to the topological sort result

      // For each neighbor of the dequeued node
      for (Integer neighbor : adjList[node]) {
        indegree[neighbor]--; // Decrement its in-degree
        // If neighbor's in-degree becomes 0, add it to the queue
        if (indegree[neighbor] == 0) {
          queue.offer(neighbor);
        }
      }
    }

    // Cycle detection: If not all nodes were visited, a cycle exists
    if (visitedNodes != v) {
      System.out.println("Graph contains cycle");
      return; // Exit if a cycle is detected
    }

    System.out.println("Topological Sort:");
    // Print the topological sort result (adjusting back to 1-based indexing for
    // user clarity)
    for (Integer node : result) {
      System.out.print((node + 1) + " "); // Changed to node + 1 for 1-based output
    }
    System.out.println(); // For a new line at the end
    sc.close(); // Close the scanner to release resources
  }
}
