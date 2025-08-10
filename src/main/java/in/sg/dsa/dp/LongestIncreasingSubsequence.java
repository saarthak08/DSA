package in.sg.dsa.dp;

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of elements: ");
    int n = scanner.nextInt();
    int[] a = new int[n];
    System.out.println("Enter the numbers: ");
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }

    int[] m = new int[n];
    Arrays.fill(m, 1);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[i] > a[j]) {
          m[i] = Math.max(m[i], m[j] + 1);
        }
      }
    }

    int max = 0;
    System.out.println("Array: ");
    for (int i = 0; i < n; i++) {
      if (max < m[i]) {
        max = m[i];
      }
      System.out.print(m[i] + " ");
    }
    System.out.println("\nLIS: " + max);
    scanner.close();
  }
}
