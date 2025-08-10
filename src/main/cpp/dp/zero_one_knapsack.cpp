#include <algorithm> // For std::max
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Solves the 0/1 Knapsack problem using dynamic programming.
 *
 * Given a set of items, each with a weight and a value, determine which
 * items to include in a collection so that the total weight is less than or
 * equal to a given limit and the total value is as large as possible.
 * Each item can only be included once.
 *
 * @param weights Vector of item weights.
 * @param values Vector of item values.
 * @param capacity The maximum capacity of the knapsack.
 * @return The maximum possible value.
 */
int zeroOneKnapsack(const vector<int> &weights, const vector<int> &values,
                    int capacity) {
  int n = weights.size();
  // dp[i][w] will be storing the maximum value that can be obtained using
  // first i items and a knapsack of capacity w.
  vector<vector<int>> dp(n + 1, vector<int>(capacity + 1, 0));

  // Build table dp[][] in bottom up manner
  for (int i = 1; i <= n; i++) {
    for (int w = 1; w <= capacity; w++) {
      // If the weight of the i-th item is more than the current capacity w,
      // we can't include it.
      if (weights[i - 1] > w) {
        dp[i][w] = dp[i - 1][w];
      } else {
        // Otherwise, we find the maximum value by either:
        // 1. Not including the i-th item (value is dp[i-1][w])
        // 2. Including the i-th item (value is values[i-1] + dp[i-1][w -
        // weights[i-1]])
        dp[i][w] =
            max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
      }
    }
  }

  return dp[n][capacity];
}

int main() {
  int n;
  cout << "Enter the number of items: ";
  cin >> n;

  vector<int> weights(n);
  vector<int> values(n);

  cout << "Enter the weights of the items:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> weights[i];
  }

  cout << "Enter the values of the items:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> values[i];
  }

  int capacity;
  cout << "Enter capacity (Max Weight): ";
  cin >> capacity;

  int maxValue = zeroOneKnapsack(weights, values, capacity);

  cout << "Maximum Value Possible: " << maxValue << endl;

  return 0;
}