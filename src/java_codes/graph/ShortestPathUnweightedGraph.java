package java_codes.graph;

import java.util.*;

/*
If we do BFS on a graph, we will find the shortest path to that vertex.
- Create a distance array for all the vertices and give value -1 to every index.
- Start from source vertex. Enqueue it. Update it value in distance array to 0.
- Now, while queue is not empty, do the following
- Dequeue from queue and go through all neighbours of dequeued node.
- For all neighbours, if the distance of the neighbour is not updated (i.e. distance[i]!=-1), update the distance to (distance of dequeued node + 1)
- In this way, the distance array will contain the shortest distance of the index from source node.

Time Complexity: O(E+V).
*/

public class ShortestPathUnweightedGraph {

  private final int v;
  private final List<List<Integer>> adj;

  public ShortestPathUnweightedGraph(int v) {
    this.v = v;
    adj = new ArrayList<>(v);
    for (int i = 0; i < v; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdge(int u, int v) {
    adj.get(u).add(v);
    adj.get(v).add(u); // For undirected graph
  }

  public void findShortestPath(int src, int dest) {
    Queue<Integer> queue = new ArrayDeque<>();
    int[] dist = new int[v];
    int[] parent = new int[v];

    Arrays.fill(dist, -1);
    Arrays.fill(parent, -1);

    dist[src] = 0;
    queue.add(src);

    boolean found = false;
    while (!queue.isEmpty()) {
      int u = queue.poll();

      if (u == dest) {
        found = true;
        break;
      }

      for (int v : adj.get(u)) {
        if (dist[v] == -1) {
          dist[v] = dist[u] + 1;
          parent[v] = u;
          queue.add(v);
        }
      }
    }

    if (found) {
      System.out.println("\nShortest distance from " + src + " to " + dest + " is: " + dist[dest]);
      System.out.print("Path: ");
      printPath(dest, parent);
      System.out.println();
    } else {
      System.out.println("\nNo path found from " + src + " to " + dest);
    }
  }

  private void printPath(int currentVertex, int[] parent) {
    if (currentVertex == -1) {
      return;
    }
    printPath(parent[currentVertex], parent);
    System.out.print(currentVertex + " ");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();
    ShortestPathUnweightedGraph g = new ShortestPathUnweightedGraph(n);

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter an edge.");
      System.out.println("Press '2' to find the shortest path.");
      System.out.println("Press '3' to exit.");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the source vertex: ");
          int u = scanner.nextInt();
          System.out.print("Enter the destination vertex: ");
          int v = scanner.nextInt();
          if (u < 0 || v < 0 || u >= n || v >= n) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
          g.addEdge(u, v);
          break;
        case 2:
          System.out.print("Enter the source vertex: ");
          int src = scanner.nextInt();
          System.out.print("Enter the destination vertex: ");
          int dest = scanner.nextInt();
          if (src < 0 || dest < 0 || src >= n || dest >= n) {
            System.out.println("Invalid source or destination. Please try again.");
            continue;
          }
          g.findShortestPath(src, dest);
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
