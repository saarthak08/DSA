package in.sg.dsa.dp;

import java.util.Scanner;

public class LongestCommonSubsequence {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter first string: ");
    String a = scanner.next();
    System.out.print("Enter second string: ");
    String b = scanner.next();
    lcs(a, b);
    scanner.close();
  }

  public static void lcs(String a, String b) {
    int m = a.length();
    int n = b.length();
    int[][] memo = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          memo[i][j] = 0;
        } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
          memo[i][j] = memo[i - 1][j - 1] + 1;
        } else {
          memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
        }
      }
    }

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        System.out.print(memo[i][j] + "\t");
      }
      System.out.println();
    }

    System.out.println("LCS: " + memo[m][n]);
    System.out.print("Sequence: ");

    StringBuilder c = new StringBuilder();
    int i = m, j = n;
    while (i > 0 && j > 0) {
      if (a.charAt(i - 1) == b.charAt(j - 1)) {
        c.append(a.charAt(i - 1));
        i--;
        j--;
      } else if (memo[i - 1][j] > memo[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    System.out.println(c.reverse());
  }
}
