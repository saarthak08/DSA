#include <algorithm> // For std::max
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Solves the Unbounded Knapsack problem using dynamic programming.
 *
 * Given a set of items, each with a weight and a value, and a knapsack of
 * a maximum capacity, determine the maximum value that can be obtained.
 * In the unbounded version, any number of instances of an item can be taken.
 *
 * @param weights Vector of item weights.
 * @param values Vector of item values.
 * @param capacity The maximum capacity of the knapsack.
 * @return The maximum possible value.
 */
int unboundedKnapsack(const vector<int> &weights, const vector<int> &values,
                      int capacity) {
  // dp[i] will be storing the maximum value that can be achieved with a
  // knapsack of capacity i.
  vector<int> dp(capacity + 1, 0);

  // Build table dp[] in bottom up manner.
  for (int i = 1; i <= capacity; i++) {
    for (size_t j = 0; j < weights.size(); j++) {
      // If item j can be included in the knapsack
      if (weights[j] <= i) {
        // Consider including item j and find the maximum value
        dp[i] = max(dp[i], dp[i - weights[j]] + values[j]);
      }
    }
  }

  return dp[capacity];
}

int main() {
  int n;
  cout << "Enter the number of item types: ";
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

  int maxValue = unboundedKnapsack(weights, values, capacity);

  cout << "Maximum value possible: " << maxValue << endl;

  return 0;
}