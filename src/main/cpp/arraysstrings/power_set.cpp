#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

/**
 * @brief Recursively generates the power set of a given set of numbers.
 *
 * The power set is the set of all possible subsets of a set. This function
 * explores the two choices for each element: either include it in the current
 * subset or not include it.
 *
 * @param input The original array of numbers.
 * @param index The current index in the input array being considered.
 * @param currentSubset The subset being built in the current recursion path.
 * @param allSubsets A vector to store all generated subsets.
 */
void generatePowerSet(const vector<int> &input, int index,
                      vector<int> &currentSubset,
                      vector<vector<int>> &allSubsets) {
  // Base case: when all elements have been considered, add the current subset
  // to the list of all subsets.
  if (index == input.size()) {
    allSubsets.push_back(currentSubset);
    return;
  }

  // Case 1: Don't include the element at the current index.
  generatePowerSet(input, index + 1, currentSubset, allSubsets);

  // Case 2: Include the element at the current index.
  currentSubset.push_back(input[index]);
  generatePowerSet(input, index + 1, currentSubset, allSubsets);

  // Backtrack: remove the element to explore other possibilities.
  currentSubset.pop_back();
}

int main() {
  int n;
  cout << "Enter the number of elements in the set: ";
  cin >> n;

  vector<int> input(n);
  cout << "Enter the elements:" << endl;
  for (int i = 0; i < n; i++) {
    cin >> input[i];
  }

  vector<vector<int>> allSubsets;
  vector<int> currentSubset;

  generatePowerSet(input, 0, currentSubset, allSubsets);

  cout << "\nThe Power Set is:" << endl;
  for (const auto &subset : allSubsets) {
    cout << "{ ";
    for (int val : subset) {
      cout << val << " ";
    }
    cout << "}" << endl;
  }

  return 0;
}