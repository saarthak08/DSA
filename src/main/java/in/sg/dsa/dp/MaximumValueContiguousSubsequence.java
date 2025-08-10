package in.sg.dsa.dp;

import java.util.Scanner;

public class MaximumValueContiguousSubsequence {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of elements: ");
    int n = scanner.nextInt();
    int[] a = new int[n];
    System.out.println("Enter the elements: ");
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }

    if (n == 0) {
      System.out.println("Maximum Contiguous Sum: 0");
      scanner.close();
      return;
    }

    int maxSoFar = a[0];
    int maxEndingHere = a[0];

    for (int i = 1; i < n; i++) {
      maxEndingHere = Math.max(a[i], maxEndingHere + a[i]);
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }

    System.out.println("Maximum Contiguous Sum: " + maxSoFar);
    scanner.close();
  }
}
