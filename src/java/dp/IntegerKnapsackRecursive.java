package java.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solves the Unbounded Knapsack problem using a recursive approach with memoization (Top-Down DP).
 *
 * <p>Problem Statement: Given a set of items, each with a weight and a value, determine the number
 * of each item to include in a collection so that the total weight is less than or equal to a given
 * limit and the total value is as large as possible. In the Unbounded version, you can take an
 * unlimited number of instances of each item.
 *
 * <p>Algorithm: The approach is similar to the 0/1 Knapsack. For each item, we have two choices: 1.
 * Include the item: If the item's weight is not more than the remaining capacity, we add its value
 * and recursively solve for the *same item* with the reduced capacity. This is the key difference
 * from the 0/1 version, as we can reuse the item. 2. Exclude the item: We don't include the item
 * and recursively solve for the *next* item with the same capacity. The maximum of these two
 * choices is the answer.
 *
 * <p>Memoization is used to store the results of subproblems (index, capacity) to avoid redundant
 * calculations.
 *
 * <p>Time Complexity: O(N * C) where N is the number of items and C is the knapsack capacity. Space
 * Complexity: O(N * C) for the memoization table + O(N) for the recursion stack.
 */
public final class IntegerKnapsackRecursive {

  private IntegerKnapsackRecursive() {}

  public static int solve(int[] weights, int[] values, int capacity) {
    if (weights == null || values == null || weights.length != values.length || capacity < 0) {
      return 0;
    }
    int n = weights.length;
    int[][] dp = new int[n][capacity + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return knapsackRec(weights, values, n - 1, capacity, dp);
  }

  private static int knapsackRec(int[] weights, int[] values, int index, int capacity, int[][] dp) {
    if (index < 0 || capacity <= 0) {
      return 0;
    }

    if (dp[index][capacity] != -1) {
      return dp[index][capacity];
    }

    // Case 1: Exclude the current item
    int excludeItem = knapsackRec(weights, values, index - 1, capacity, dp);

    // Case 2: Include the current item (if possible)
    int includeItem = 0;
    if (weights[index] <= capacity) {
      // Note: We recurse on the *same* index because we can take the item again
      includeItem =
          values[index] + knapsackRec(weights, values, index, capacity - weights[index], dp);
    }

    dp[index][capacity] = Math.max(includeItem, excludeItem);
    return dp[index][capacity];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter the number of item types: ");
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
