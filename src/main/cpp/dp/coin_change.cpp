#include <climits> // For INT_MAX
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Solves the unbounded coin change problem using dynamic programming.
 *
 * The problem is to find the minimum number of coins required to make a certain
 * amount of change using a given set of coin denominations. Each coin
 * denomination can be used an unlimited number of times.
 *
 * @param denominations A vector containing the available coin denominations.
 * @param amount The target amount to make change for.
 * @return The minimum number of coins required, or -1 if it's not possible.
 */
int minCoinChange(const vector<int> &denominations, int amount) {
  // dp[i] will be storing the minimum number of coins required for amount i.
  // Initialize dp array with a value that represents infinity.
  vector<int> dp(amount + 1, amount + 1);

  // Base case: 0 coins are needed to make an amount of 0.
  dp[0] = 0;

  // Compute minimum coins required for all values from 1 to amount.
  for (int i = 1; i <= amount; i++) {
    // Go through all coins smaller than or equal to i.
    for (int coin : denominations) {
      if (coin <= i) {
        dp[i] = min(dp[i], dp[i - coin] + 1);
      }
    }
  }

  // If dp[amount] is still the 'infinity' value, it's not possible to make
  // that amount. Otherwise, dp[amount] holds the answer.
  return dp[amount] > amount ? -1 : dp[amount];
}

int main() {
  int n;
  cout << "Enter the number of denominations: ";
  cin >> n;

  vector<int> denominations(n);
  cout << "Enter the values of denominations:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> denominations[i];
  }

  int amount;
  cout << "Enter the total amount to be changed: ";
  cin >> amount;

  int minCoins = minCoinChange(denominations, amount);

  if (minCoins == -1) {
    cout << "It's not possible to make the amount with the given denominations."
         << endl;
  } else {
    cout << "Minimum number of coins required: " << minCoins << endl;
  }

  return 0;
}