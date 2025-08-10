#include <algorithm> // For std::max_element
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Finds the length of the Longest Increasing Subsequence (LIS) in an
 * array.
 *
 * This function uses a dynamic programming approach with O(n^2) time
 * complexity.
 *
 * @param arr The input vector of numbers.
 * @return The length of the LIS.
 */
int findLIS(const vector<int> &arr) {
  int n = arr.size();
  if (n == 0) {
    return 0;
  }

  // dp[i] will be storing the length of the LIS ending at index i
  vector<int> dp(n, 1);

  for (int i = 1; i < n; i++) {
    for (int j = 0; j < i; j++) {
      // If the current element is greater than a previous element,
      // it's a candidate for extending that subsequence.
      if (arr[i] > arr[j]) {
        dp[i] = max(dp[i], dp[j] + 1);
      }
    }
  }

  // The length of the LIS is the maximum value in the dp array.
  return *max_element(dp.begin(), dp.end());
}

int main() {
  int n;
  cout << "Enter the number of elements: ";
  cin >> n;

  vector<int> arr(n);
  cout << "Enter the numbers:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }

  int lis_length = findLIS(arr);

  cout << "\nLength of the Longest Increasing Subsequence: " << lis_length
       << endl;

  return 0;
}