import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Dijkstra {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int V;
    System.out.println("Enter the number of vertices: ");
    V = sc.nextInt();
    ArrayDeque<Integer>[] graph = new ArrayDeque[V + 1];
    for (int i = 0; i < V + 1; i++) {
      graph[i] = new ArrayDeque<Integer>();
    }
    int[][] weight = new int[V + 1][V + 1];
    System.out.println("Enter the number of edges: ");
    int E = sc.nextInt();
    for (int i = 0; i < E; i++) {
      System.out.println("Enter the first node:");
      int u = sc.nextInt();
      System.out.println("Enter the second node:");
      int v = sc.nextInt();
      System.out.println("Enter the weight of the edge:");
      int w = sc.nextInt();
      graph[u].offerLast(v);
      graph[v].offerLast(u);
      weight[u][v] = w;
      weight[v][u] = w;
    }
    dijkstra(graph, V, E, weight);
  }

  static void dijkstra(Deque<Integer>[] graph, int V, int E, int[][] weight) {
    int[] distance = new int[V + 1];
    PriorityQueue<Node> queue = new PriorityQueue<>(V + 1, new NodeComparator());
    for (int i = 1; i <= V; i++) {
      distance[i] = Integer.MAX_VALUE;
    }
    System.out.println("Enter the source node:");
    int source = sc.nextInt();
    Node p = new Node(source, 0);
    distance[source] = 0;
    queue.offer(p);
    while (!queue.isEmpty()) {
      Node x = queue.poll();
      int u = x.index;
      Iterator<Integer> itr = graph[u].iterator();
      System.out.println(x.index);
      while (itr.hasNext()) {
        int v = itr.next();
        if (weight[u][v] + distance[u] < distance[v]) {
          distance[v] = weight[u][v] + distance[u];
          queue.offer(new Node(v, distance[v]));
        }
      }
    }
    System.out.println("Distance Array: ");
    for (int i = 1; i < V + 1; i++) {
      System.out.print(distance[i] + " ");
    }
  }
}

class Node {
  int index;
  int distanceFromSource;

  public Node(int v1, int v2) {
    this.index = v1;
    this.distanceFromSource = v2;
  }
}

class NodeComparator implements Comparator<Node> {
  @Override
  public int compare(Node p1, Node p2) {
    return p1.distanceFromSource - p2.distanceFromSource;
  }
}
