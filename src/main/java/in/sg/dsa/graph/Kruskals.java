package in.sg.dsa.graph;

import in.sg.dsa.graph.utils.GraphEdge;
import java.util.*;

/*
 * This code implements Kruskal's algorithm to find the Minimum Spanning Tree (MST) of a weighted, undirected in.sg.dsa.graph.
 *
 * Algorithm Steps:
 * 1. Sort all the edges of the in.sg.dsa.graph in non-decreasing order of their weights.
 * 2. Initialize a disjoint-set data structure where each vertex is in its own set.
 * 3. Iterate through the sorted edges. For each edge (u, v) with weight w:
 * a. Find the sets that u and v belong to using the 'findParent' operation.
 * b. If u and v belong to different sets, it means adding this edge will not create a cycle.
 * c. Add the edge (u, v) to the MST.
 * d. Merge the sets containing u and v using the 'union' operation.
 * 4. Continue until V-1 edges have been added to the MST, where V is the number of vertices in the in.sg.dsa.graph.
 *
 * Time Complexity:
 * 1. Sorting the edges takes O(E log E) time, where E is the number of edges.
 * 2. The while loop iterates at most E times.
 * 3. Inside the loop, the 'findParent' and 'union' operations using path compression and union by rank take almost constant time on average, effectively O(alpha(V)), where alpha is the inverse Ackermann function (a very slowly growing function, practically constant).
 * 4. Adding edges to the MST list takes O(1) on average.
 * Therefore, the overall time complexity is dominated by the sorting step, which is O(E log E). In a connected in.sg.dsa.graph, E is at least V-1 and at most V(V-1)/2, so O(E log E) is equivalent to O(E log V).
 *
 * Space Complexity:
 * 1. The 'parent' array of size V is used for the disjoint-set data structure, taking O(V) space.
 * 2. The 'rank' array of size V is also used for the disjoint-set data structure, taking O(V) space.
 * 3. The 'PriorityQueue' to store the edges can hold up to E edges, taking O(E) space.
 * 4. The 'mst' list to store the edges of the MST can hold up to V-1 edges, taking O(V) space.
 * Therefore, the overall space complexity is O(V + E).
 */

public class Kruskals {
  private static final Scanner sc = new Scanner(System.in);
  private static int[] parent;
  private static int[] rank;

  private static void union(GraphEdge edge) {
    int u = edge.getSrc() - 1;
    int v = edge.getDest() - 1;
    int parentU = findParent(u);
    int parentV = findParent(v);
    if (rank[parentU] > rank[parentV]) {
      parent[parentV] = parentU;
    } else if (rank[parentU] < rank[parentV]) {
      parent[parentU] = parentV;
    } else {
      parent[parentV] = parentU;
      rank[parentU] += 1;
    }
  }

  private static int findParent(int node) {
    if (parent[node] == node) {
      return node;
    }
    int parentNode = findParent(parent[node]);
    parent[node] = parentNode;
    return parentNode;
  }

  public static void main(String[] args) {
    System.out.println("Enter the number of vertices: ");
    int v = sc.nextInt();
    System.out.println("Enter the number of edges: ");
    int e = sc.nextInt();
    parent = new int[v];
    for (int i = 0; i < v; i++) {
      parent[i] = i;
    }
    rank = new int[v];

    PriorityQueue<GraphEdge> queue = new PriorityQueue<>();

    for (int i = 0; i < e; i++) {
      System.out.println("Enter details of the edge " + (i + 1));
      System.out.println("Enter the first vertex of the edge: ");
      GraphEdge edge = new GraphEdge();
      edge.setSrc(sc.nextInt());
      System.out.println("Enter the second vertex of the edge: ");
      edge.setDest(sc.nextInt());
      System.out.println("Enter the weight of the edge: ");
      edge.setWeight(sc.nextInt());
      queue.offer(edge);
    }

    ArrayList<GraphEdge> mst = new ArrayList<>();

    while (mst.size() != v - 1 && !queue.isEmpty()) {
      GraphEdge edge = queue.poll();
      int parentU = findParent(edge.getSrc() - 1);
      int parentV = findParent(edge.getDest() - 1);
      if (parentU != parentV) {
        union(edge);
        mst.add(edge);
      }
    }

    if (mst.size() != v - 1) {
      System.out.println("Graph is not connected. MST cannot be formed.");
    } else {
      System.out.println("MST: ");
      for (GraphEdge edge : mst) {
        System.out.println(edge.getSrc() + " " + edge.getDest() + " " + edge.getWeight());
      }
    }
  }
}
