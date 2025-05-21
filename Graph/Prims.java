/*
 * Prim's Algorithm for finding the Minimum Spanning Tree (MST) of a connected, undirected, weighted graph.
 *
 * Explanation:
 * - The graph is represented using an adjacency list.
 * - The algorithm starts from vertex 1 (0-th index in array).
 * - It uses a priority queue (min-heap) to always pick the smallest edge that connects a visited node to an unvisited one.
 * - The process continues until all vertices are included in the MST or until no more valid edges are available.
 *
 * Time Complexity:
 * - Let V be the number of vertices and E the number of edges.
 * - Each vertex is added to the MST once, and each edge is processed at most once.
 * - Adding/removing from the priority queue takes O(log E) time.
 * - So overall complexity is: O(E log E) or O(E log V), depending on implementation and edge distribution.
 *
 * Space Complexity:
 * - Adjacency list: O(V + E)
 * - Priority queue: O(E)
 * - Visited array and MST list: O(V)
 * - Total: O(V + E)
 */

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prims {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of vertices: ");
    int v = sc.nextInt();
    System.out.println("Enter the number of edges: ");
    int e = sc.nextInt();

    ArrayList<GraphEdge>[] adjList = new ArrayList[v];
    for (int i = 0; i < v; i++) {
      adjList[i] = new ArrayList<>();
    }

    for (int i = 0; i < e; i++) {
      System.out.println("Enter details of the edge " + (i + 1));
      System.out.println("Enter the first vertex of the edge: ");
      int v1 = sc.nextInt();
      System.out.println("Enter the second vertex of the edge: ");
      int v2 = sc.nextInt();
      System.out.println("Enter the weight of the edge: ");
      int w = sc.nextInt();

      if (v1 < 1 || v1 > v || v2 < 1 || v2 > v) {
        System.out.println("Invalid vertex number. Try again.");
        i--;
        continue;
      }

      adjList[v1 - 1].add(new GraphEdge(v1, v2, w));
      adjList[v2 - 1].add(new GraphEdge(v2, v1, w));
    }

    boolean[] visited = new boolean[v];
    PriorityQueue<GraphEdge> queue = new PriorityQueue<>();

    visited[0] = true;
    for (GraphEdge edge : adjList[0]) {
      queue.offer(edge);
    }

    ArrayList<GraphEdge> mst = new ArrayList<>();

    while (!queue.isEmpty() && mst.size() != v - 1) {
      GraphEdge edge = queue.poll();
      if (!visited[edge.v2 - 1]) {
        mst.add(edge);
        visited[edge.v2 - 1] = true;
        for (GraphEdge nextEdge : adjList[edge.v2 - 1]) {
          if (!visited[nextEdge.v2 - 1]) {
            queue.offer(nextEdge);
          }
        }
      }
    }

    if (mst.size() != v - 1) {
      System.out.println("Graph is not connected. MST cannot be formed.");
    } else {
      System.out.println("MST: ");
      for (GraphEdge edge : mst) {
        System.out.println(edge.v1 + " " + edge.v2 + " " + edge.w);
      }
    }
  }
}

class GraphEdge implements Comparable<GraphEdge> {
  int v1;
  int v2;
  int w;

  public GraphEdge(int v1, int v2, int w) {
    this.v1 = v1;
    this.v2 = v2;
    this.w = w;
  }

  public int compareTo(GraphEdge that) {
    return this.w - that.w;
  }
}
