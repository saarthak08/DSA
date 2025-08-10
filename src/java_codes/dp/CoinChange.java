package java_codes.dp;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the total value to be changed: ");
    int C = scanner.nextInt();
    System.out.print("Enter the number of denominations: ");
    int n = scanner.nextInt();
    int[] w = new int[n];
    System.out.println("Enter the values of denominations: ");
    for (int i = 0; i < n; i++) {
      w[i] = scanner.nextInt();
    }

    int[] memo = new int[C + 1];
    Arrays.fill(memo, C + 1); // Initialize with a value larger than any possible number of coins
    memo[0] = 0;

    for (int i = 1; i <= C; i++) {
      for (int j = 0; j < n; j++) {
        if (w[j] <= i) {
          memo[i] = Math.min(memo[i], 1 + memo[i - w[j]]);
        }
      }
    }

    System.out.println("Memo:");
    for (int j = 0; j <= C; j++) {
      System.out.print(memo[j] + "\t");
    }
    System.out.println(
        "\nMin: "
            + (memo[C] > C
                ? -1
                : memo[C])); // If memo[C] is still the large value, it's not possible
    scanner.close();
  }
}
