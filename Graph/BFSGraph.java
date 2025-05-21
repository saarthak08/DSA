import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BFSGraph {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of vertices: ");
    int v = sc.nextInt();
    int[][] graph = new int[v][v];
    System.out.println("Enter the number of edges: ");
    int e = sc.nextInt();
    for (int i = 0; i < e; i++) {
      System.out.println("Enter the vertex 1 for edge " + (i + 1));
      int v1 = sc.nextInt();
      while (v1 < 1 || v1 > v) {
        System.out.println("Invalid vertex entered. Please enter again");
        v1 = sc.nextInt();
      }
      System.out.println("Enter the vertex 2 for edge " + (i + 1));
      int v2 = sc.nextInt();
      while (v2 < 1 || v2 > v) {
        System.out.println("Invalid vertex entered. Please enter again");
        v2 = sc.nextInt();
      }
      graph[v1 - 1][v2 - 1] = 1;
      graph[v2 - 1][v1 - 1] = 1;
    }
    printGraph(graph);
    bfs(graph);
  }

  private static void printGraph(int[][] graph) {
    System.out.println("Graph entered: ");
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        System.out.print(graph[i][j] + "\t");
      }
      System.out.println();
    }
  }

  private static void bfs(int[][] graph) {
    System.out.println("\nBFS of the Graph: ");
    Queue<Integer> queue = new ArrayDeque<Integer>();
    boolean[] visited = new boolean[graph.length];
    queue.offer(0);
    visited[0] = true;
    while (!queue.isEmpty()) {
      int v = queue.poll();
      System.out.print((v + 1) + "\t");
      for (int i = 0; i < graph[v].length; i++) {
        if (!visited[i] && graph[v][i] == 1) {
          visited[i] = true;
          queue.offer(i);
        }
      }
    }
    System.out.println();
  }
}
