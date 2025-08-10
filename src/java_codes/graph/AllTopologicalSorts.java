package java_codes.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Initialize all vertices as unvisited.
Now choose vertex which is unvisited and has zero indegree and decrease indegree of all those vertices by 1 (corresponding to removing edges) now add this vertex to result and call the recursive function again and backtrack.
After returning from function reset values of visited, result and indegree for enumeration of other possibilities.
*/

public class AllTopologicalSorts {

  private final int v;
  private final List<List<Integer>> adjList;

  public AllTopologicalSorts(int v) {
    this.v = v;
    adjList = new ArrayList<>(v);
    for (int i = 0; i < v; i++) {
      adjList.add(new ArrayList<>());
    }
  }

  public void addEdge(int u, int v) {
    adjList.get(u).add(v);
  }

  public void allTopologicalSorts() {
    boolean[] visited = new boolean[v];
    int[] indegree = new int[v];
    for (int i = 0; i < v; i++) {
      for (int to : adjList.get(i)) {
        indegree[to]++;
      }
    }

    List<Integer> result = new ArrayList<>();
    System.out.println("\nAll Topological Sorts: ");
    allTopologicalSortsUtil(visited, result, indegree);
  }

  private void allTopologicalSortsUtil(boolean[] visited, List<Integer> result, int[] indegree) {
    boolean flag = false;
    for (int i = 0; i < v; i++) {
      if (!visited[i] && indegree[i] == 0) {
        visited[i] = true;
        result.add(i);
        for (int to : adjList.get(i)) {
          indegree[to]--;
        }

        allTopologicalSortsUtil(visited, result, indegree);

        visited[i] = false;
        result.remove(result.size() - 1);
        for (int to : adjList.get(i)) {
          indegree[to]++;
        }
        flag = true;
      }
    }
    if (!flag) {
      for (int i : result) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();
    AllTopologicalSorts g = new AllTopologicalSorts(n);

    while (true) {
      System.out.println("\n******* MENU *******");
      System.out.println("Press '1' to enter edge.");
      System.out.println("Press '2' to find all topological sorts.");
      System.out.println("Press '3' to exit.");
      int x = scanner.nextInt();
      if (x == 3) {
        break;
      } else if (x == 2) {
        g.allTopologicalSorts();
      } else if (x == 1) {
        System.out.print("Enter the first vertex of the edge: ");
        int u = scanner.nextInt();
        System.out.print("Enter the second vertex of the edge: ");
        int v = scanner.nextInt();
        if (u < 0 || v < 0 || u >= n || v >= n) {
          System.out.println("Invalid Input. Try Again!");
          continue;
        }
        g.addEdge(u, v);
      } else {
        System.out.println("Invalid Input. Try Again!");
      }
    }
    scanner.close();
  }
}
