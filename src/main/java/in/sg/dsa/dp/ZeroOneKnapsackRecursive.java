package in.sg.dsa.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solves the 0/1 Knapsack problem using a recursive approach with memoization (Top-Down Dynamic
 * Programming).
 *
 * <p>Problem Statement: Given a set of items, each with a weight and a value, determine the number
 * of each item to include in a collection so that the total weight is less than or equal to a given
 * limit and the total value is as large as possible. In the 0/1 version, you can either take an
 * item or leave it (you cannot take a fraction of an item).
 *
 * <p>Algorithm: The problem is solved by considering each item one by one. For each item, we have
 * two choices: 1. Include the item: If the item's weight is not more than the remaining knapsack
 * capacity, we add its value and recursively solve for the remaining items with the reduced
 * capacity. 2. Exclude the item: We don't include the item and recursively solve for the remaining
 * items with the same capacity. The maximum of these two choices is our answer for the current
 * state.
 *
 * <p>Memoization is used to store the results of subproblems (for a given number of items and
 * capacity) to avoid re-computation, which significantly improves performance over a plain
 * recursive solution.
 *
 * <p>Time Complexity: O(N * C) where N is the number of items and C is the knapsack capacity. Each
 * state (index, capacity) is computed only once.
 *
 * <p>Space Complexity: O(N * C) for the memoization table (dp array) + O(N) for the recursion stack
 * depth.
 */
public final class ZeroOneKnapsackRecursive {

  private ZeroOneKnapsackRecursive() {}

  public static int solve(int[] weights, int[] values, int capacity) {
    if (weights == null || values == null || weights.length != values.length || capacity < 0) {
      return 0;
    }
    int n = weights.length;
    // DP table to store results of subproblems. Initialize with -1 (uncomputed).
    int[][] dp = new int[n][capacity + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return knapsackRec(weights, values, n - 1, capacity, dp);
  }

  private static int knapsackRec(int[] weights, int[] values, int index, int capacity, int[][] dp) {
    // Base case: no items left or no capacity left
    if (index < 0 || capacity == 0) {
      return 0;
    }

    // If the result for this state is already computed, return it
    if (dp[index][capacity] != -1) {
      return dp[index][capacity];
    }

    // If weight of the current item is more than knapsack capacity, we can't include it
    if (weights[index] > capacity) {
      dp[index][capacity] = knapsackRec(weights, values, index - 1, capacity, dp);
      return dp[index][capacity];
    }

    // Return the maximum of two cases:
    // 1. N-th item included
    // 2. N-th item not included
    int includeItem =
        values[index] + knapsackRec(weights, values, index - 1, capacity - weights[index], dp);
    int excludeItem = knapsackRec(weights, values, index - 1, capacity, dp);

    dp[index][capacity] = Math.max(includeItem, excludeItem);
    return dp[index][capacity];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter the number of items: ");
    int n = sc.nextInt();

    int[] wt = new int[n];
    int[] v = new int[n];

    System.out.println("Enter the weights of the items:");
    for (int i = 0; i < n; i++) {
      wt[i] = sc.nextInt();
    }

    System.out.println("Enter the values of the items:");
    for (int i = 0; i < n; i++) {
      v[i] = sc.nextInt();
    }

    System.out.print("Enter knapsack capacity: ");
    int C = sc.nextInt();

    System.out.println("Maximum value that can be obtained is: " + solve(wt, v, C));

    sc.close();
  }
}
