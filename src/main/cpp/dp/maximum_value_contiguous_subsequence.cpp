#include <algorithm> // For std::max
#include <climits>   // For INT_MIN
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Finds the maximum sum of a contiguous subarray using Kadane's
 * Algorithm.
 *
 * Kadane's Algorithm iterates through the array, keeping track of the maximum
 * sum ending at the current position and the overall maximum sum found so far.
 *
 * @param arr The input vector of numbers.
 * @return The maximum contiguous subarray sum.
 */
int maxSubarraySum(const vector<int> &arr) {
  if (arr.empty()) {
    return 0; // Or throw an exception, depending on desired behavior
  }

  int max_so_far = arr[0];
  int max_ending_here = arr[0];

  for (size_t i = 1; i < arr.size(); i++) {
    // The maximum sum ending at position i is either the element itself
    // or the element plus the maximum sum ending at the previous position.
    max_ending_here = max(arr[i], max_ending_here + arr[i]);

    // Update the overall maximum sum found so far.
    max_so_far = max(max_so_far, max_ending_here);
  }

  return max_so_far;
}

int main() {
  int n;
  cout << "Enter the number of elements: ";
  cin >> n;

  vector<int> arr(n);
  cout << "Enter the elements:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }

  int max_sum = maxSubarraySum(arr);

  cout << "\nMaximum Contiguous Subarray Sum: " << max_sum << endl;

  return 0;
}