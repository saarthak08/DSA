import java.util.Arrays;
import java.util.Scanner;

public class IntegerKnapsackRecursive {
  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n;
    n = sc.nextInt();
    int[] wt = new int[n];
    int[] v = new int[n];
    int C = sc.nextInt();
    for (int i = 0; i < n; i++) {
      wt[i] = sc.nextInt();
    }
    for (int i = 0; i < n; i++) {
      v[i] = sc.nextInt();
    }
    int[] dp = new int[n];
    Arrays.fill(dp, -1);
    System.out.println(maxCap(n, C, wt, v, dp, 0));
  }

  public static int maxCap(int n, int C, int[] wt, int[] v, int[] dp, int i) {

    if (i >= n || wt[i] > C) {
      return 0;
    }
    if (dp[i] != -1) {
      return dp[i];
    }
    dp[i] = Math.max(v[i] + maxCap(n, C - wt[i], wt, v, dp, i), maxCap(n, C, wt, v, dp, i + 1));
    return dp[i];
  }
}