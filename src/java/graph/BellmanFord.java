package java.graph;

import in.sg.dsa.graph.utils.GraphEdge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Bellman Ford works for negative cycles too.
// Time Complexity: O(EV)

/*
The difference in Dijkstra and Bellman Ford is that Bellman ford relaxes each edge for each vertex & that's why its complexity goes to O(EV)
but in Dijkstra, we choose the min weight vertex and enqueue it to the queue & then relax all its neighbours.

1) This step initializes distances from source to all vertices as infinite and distance to source itself as 0. Create an array dist[] of size |V| with all values as infinite except dist[src] where src is source vertex.

2) This step calculates shortest distances. Do following |V|-1 times when |V| is the number of vertices in given graph.
Do following for each edge u-v
If dist[v] > dist[u] + weight of edge uv, then update dist[v]
dist[v] = dist[u] + weight of edge uv

3) This step reports if there is a negative weight cycle in graph. Do following for each edge u-v.
If dist[v] > dist[u] + weight of edge uv, then “Graph contains negative weight cycle”
The idea of step 3 is, step 2 guarantees shortest distances if graph doesn’t contain negative weight cycle. If we iterate through all edges one more time and get a shorter path for any vertex, then there is a negative weight cycle

*/

public class BellmanFord {

  private final int v;
  private int e;
  private final List<GraphEdge> graphEdges;

  public BellmanFord(int v) {
    this.v = v;
    e = 0;
    graphEdges = new ArrayList<>();
  }

  public void addGraphEdge(int u, int v, int w) {
    graphEdges.add(new GraphEdge(u, v, w));
    e++;
  }

  public void bellmanFord(int src) {
    int[] dist = new int[v];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    // Step 2: Relax all GraphEdges |V| - 1 times.
    for (int i = 1; i < v; ++i) {
      for (int j = 0; j < e; ++j) {
        int u = graphEdges.get(j).getSrc();
        int v = graphEdges.get(j).getDest();
        int weight = graphEdges.get(j).getWeight();
        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
          dist[v] = dist[u] + weight;
        }
      }
    }

    // Step 3: check for negative-weight cycles.
    for (int j = 0; j < e; ++j) {
      int u = graphEdges.get(j).getSrc();
      int v = graphEdges.get(j).getDest();
      int weight = graphEdges.get(j).getWeight();
      if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
        System.out.println("Graph contains negative weight cycle");
        return;
      }
    }

    printArr(dist, v);
  }

  void printArr(int[] dist, int V) {
    System.out.println("Vertex Distance from Source");
    for (int i = 0; i < V; ++i) {
      System.out.println(i + "\t\t" + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();
    BellmanFord g = new BellmanFord(n);

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter GraphEdge.");
      System.out.println("Press '2' to run Bellman-Ford.");
      System.out.println("Press '3' to exit.");
      int x = scanner.nextInt();
      if (x == 3) {
        break;
      } else if (x == 2) {
        System.out.print("Enter source node: ");
        int source = scanner.nextInt();
        if (source < 0 || source >= n) {
          System.out.println("Invalid Input. Try Again!");
          continue;
        }
        g.bellmanFord(source);
      } else if (x == 1) {
        System.out.print("Enter the first vertex of the GraphEdge: ");
        int u = scanner.nextInt();
        System.out.print("Enter the second vertex of the GraphEdge: ");
        int v = scanner.nextInt();
        System.out.print("Enter the weight of the GraphEdge: ");
        int w = scanner.nextInt();
        if (u < 0 || v < 0 || u >= n || v >= n) {
          System.out.println("Invalid Input. Try Again!");
          continue;
        }
        g.addGraphEdge(u, v, w);
      } else {
        System.out.println("Invalid Input. Try Again!");
      }
    }
    scanner.close();
  }
}
