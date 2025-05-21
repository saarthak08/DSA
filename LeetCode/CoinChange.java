class Solution {
  public int coinChange(int[] coins, int amount) {
    int[] memo = new int[amount + 1];
    for (int i = 1; i < amount + 1; i++) {
      memo[i] = amount + 1;
    }
    memo[0] = 0;
    int minNo = amount + 1;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          memo[i] = min(memo[i], 1 + memo[i - coins[j]]);
        }
      }
    }
    if (memo[amount] == amount + 1) {
      return -1;
    }
    return memo[amount];
  }

  public int min(int a, int b) {
    return a > b ? b : a;
  }
}