package java_codes.dp;

import java.util.Scanner;

public class IntegerKnapsackIterative {

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

    int[] memo = new int[C + 1];
    memo[0] = 0;

    for (int i = 1; i <= C; i++) {
      int max_no = 0;
      for (int j = 0; j < n; j++) {
        if (w[j] <= i) {
          max_no = Math.max(max_no, v[j] + memo[i - w[j]]);
        }
      }
      memo[i] = max_no;
    }

    System.out.println("Max: " + memo[C]);
    scanner.close();
  }
}
