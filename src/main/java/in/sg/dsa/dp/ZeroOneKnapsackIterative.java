package in.sg.dsa.dp;

import java.util.Scanner;

public class ZeroOneKnapsackIterative {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter capacity (Max Weight): ");
    int C = scanner.nextInt();
    System.out.print("Enter the number of elements: ");
    int n = scanner.nextInt();
    int[] w = new int[n];
    int[] v = new int[n];
    System.out.println("Enter the weights of the elements: ");
    for (int i = 0; i < n; i++) {
      w[i] = scanner.nextInt();
    }
    System.out.println("Enter the values of the elements: ");
    for (int i = 0; i < n; i++) {
      v[i] = scanner.nextInt();
    }

    int[][] memo = new int[n + 1][C + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= C; j++) {
        if (i == 0 || j == 0) {
          memo[i][j] = 0;
        } else if (w[i - 1] <= j) {
          memo[i][j] = Math.max(v[i - 1] + memo[i - 1][j - w[i - 1]], memo[i - 1][j]);
        } else {
          memo[i][j] = memo[i - 1][j];
        }
      }
    }

    System.out.println("Memoization Table:");
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= C; j++) {
        System.out.print(memo[i][j] + "\t");
      }
      System.out.println();
    }

    System.out.println("Maximum Value Possible: " + memo[n][C]);
    scanner.close();
  }
}
