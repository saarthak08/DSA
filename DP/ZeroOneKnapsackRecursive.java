import java.util.Scanner;

public class ZeroOneKnapsackRecursive {

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n;
    n = sc.nextInt();
    int[] wt = new int[n];
    int[] v = new int[n];
    int C;
    C = sc.nextInt();
    for (int i = 0; i < n; i++) {
      wt[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++) {
      v[i] = sc.nextInt();
    }
    int[][] dp = new int[n + 1][C + 1];
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < C + 1; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = -1;
        }
      }
    }

    System.out.println(maxCap(n, C, wt, v, dp, 1));
  }

  public static int maxCap(int n, int C, int[] wt, int[] v, int[][] dp, int i) {
    if (i > n || wt[i - 1] > C) {
      return 0;
    }
    if (dp[i][C] != -1) {
      return dp[i][C];
    }
    dp[i][C] = Math.max(v[i - 1] + maxCap(n, C - wt[i - 1], wt, v, dp, i + 1), maxCap(n, C, wt, v, dp, i + 1));
    return dp[i][C];
  }
}
