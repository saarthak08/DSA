import java.util.Scanner;

public class CoinChange {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int w = sc.nextInt();
    int n = sc.nextInt();
    int[] coins = new int[n];
    for (int i = 0; i < n; i++) {
      coins[i] = sc.nextInt();
    }
    int[] memo = new int[w + 1];
    memo[0] = 0;
    for (int i = 1; i < w + 1; i++) {
      int min = Integer.MAX_VALUE;
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          min = Math.min(min, 1 + memo[i - coins[j]]);
        }
      }
      memo[i] = min;
    }
    System.out.println(memo[w]);
  }
}
