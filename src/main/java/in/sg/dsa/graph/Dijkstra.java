package in.sg.dsa.graph;

import in.sg.dsa.graph.utils.GraphEdge;
import in.sg.dsa.graph.utils.GraphNode;
import java.util.*;

/*

Dijkstra's Algorithm gives the shortest path in a weighted graph.
For unweighted graph, simple BFS works but for weighted graph Dijkstra is the way to go.

Approach:
- We will create a distance array for all vertices currently having distance as Integer.MAX_VALUE for all vertices.
- This distance array denotes the shortest distance from source node to all the other nodes in the graph.
- In starting all the distances are MAX_VALUE i.e. infinite.
- We will start from source node and mark its value in distance array as 0 and enqueue it to a queue.
- Now, this queue should be a priority queue. (Or without priority queue, it can also be done, but it won't be an optimised approach).
- The priority in queue depends on the distance of the node from the source node i.e. the value of distance array.
- Now, we will execute a loop until queue is empty.
  - We will dequeue a node from the queue. Let's say the dequeued node is q.
  - We will check all the neighbours of the node. We will find distance to each neighbour. The distance will be value of distance[q] + weight[neighbour-q edge].
  - If the distance is less than the distance mentioned in the distance array. We have found a shorter path. Hence, we will update the distance array value of that neighbour. This is also called relaxing.
  - We will enqueue the neighbours for which we found the shorter path.
- Now, the distance array will contain the shortest paths from source nodes to all the nodes.

 Dijkstra doesn't work with negative edges.

 The difference in Dijkstra and Bellman Ford is that Bellman ford relaxes each edge for each vertex & that's why its complexity goes to O(EV)
 but in Dijkstra, we choose the min weight vertex and enqueue it to the queue & then relax all its neighbours.

Time Complexity:
  When we use adjacent matrix, the time complexity will be O(V^2).

  When we use adjacency list, the time complexity will be O((V+E)*log(V)). O(V+E) for traversing and on each traverse, we are dequeuing from the queue.
  The queue is priority queue. So, it will take O(logV) time for heapify.
*/

public class Dijkstra {

  private final int V;
  private final GraphNode[] adj; // adjacency list using GraphNode
  private final Map<Integer, Integer>[] weights; // weights[u].get(v) = weight

  @SuppressWarnings("unchecked")
  public Dijkstra(int V) {
    this.V = V;
    adj = new GraphNode[V];
    weights = new HashMap[V];
    for (int i = 0; i < V; i++) {
      weights[i] = new HashMap<>();
    }
  }

  public void addEdge(int u, int v, int weight) {
    // Add to adjacency list for u
    GraphNode nodeU = new GraphNode();
    nodeU.setData(v);
    nodeU.setNext(adj[u]);
    adj[u] = nodeU;

    // Add to adjacency list for v (undirected graph)
    GraphNode nodeV = new GraphNode();
    nodeV.setData(u);
    nodeV.setNext(adj[v]);
    adj[v] = nodeV;

    // Store weights
    weights[u].put(v, weight);
    weights[v].put(u, weight);
  }

  public void dijkstra(int src) {
    PriorityQueue<GraphEdge> pq = new PriorityQueue<>();
    int[] dist = new int[V];
    int[] parent = new int[V];

    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);

    dist[src] = 0;
    pq.add(new GraphEdge(src, src, 0));

    while (!pq.isEmpty()) {
      GraphEdge edge = pq.poll();
      int u = edge.getDest();

      GraphNode neighbor = adj[u];
      while (neighbor != null) {
        int v = neighbor.getData();
        int weight = weights[u].get(v);

        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
          dist[v] = dist[u] + weight;
          parent[v] = u;
          pq.add(new GraphEdge(u, v, dist[v]));
        }
        neighbor = neighbor.getNext();
      }
    }

    printSolution(src, dist, parent);
  }

  private void printSolution(int src, int[] dist, int[] parent) {
    System.out.println("Vertex\t Distance\tPath");
    for (int i = 0; i < V; i++) {
      if (i != src) {
        System.out.print(i + " \t\t ");
        if (dist[i] == Integer.MAX_VALUE) {
          System.out.print("Unreachable");
        } else {
          System.out.print(dist[i] + "\t\t");
          printPath(i, parent);
        }
        System.out.println();
      }
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
    Dijkstra g = new Dijkstra(n);

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter an edge.");
      System.out.println("Press '2' to run Dijkstra's algorithm.");
      System.out.println("Press '3' to exit.");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the source vertex: ");
          int u = scanner.nextInt();
          System.out.print("Enter the destination vertex: ");
          int v = scanner.nextInt();
          System.out.print("Enter the weight: ");
          int w = scanner.nextInt();
          if (u < 0 || v < 0 || u >= n || v >= n || w < 0) {
            System.out.println("Invalid input. Please try again.");
            continue;
          }
          g.addEdge(u, v, w);
          break;
        case 2:
          System.out.print("Enter the source vertex for Dijkstra: ");
          int src = scanner.nextInt();
          if (src < 0 || src >= n) {
            System.out.println("Invalid source vertex.");
            continue;
          }
          g.dijkstra(src);
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
