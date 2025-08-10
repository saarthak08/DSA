#include <iostream>
#include <string>

// Using the standard namespace for cout, cin, string
using namespace std;

/**
 * @brief Recursively generates and prints all permutations of a string.
 *
 * This function works by picking each character of the string `str`, appending
 * it to the `prefix`, and then recursively calling itself with the remaining
 * characters.
 *
 * @param str The remaining characters to be permuted.
 * @param prefix The current prefix of the permutation being built.
 */
void perm(string str, string prefix);

int main() {
  string input_string;
  cout << "Enter a string: ";
  cin >> input_string;
  cout << "All permutations are:" << endl;
  perm(input_string, "");
  return 0;
}

void perm(string str, string prefix) {
  // Base case: If the string is empty, the prefix is a full permutation.
  if (str.length() == 0) {
    cout << prefix << endl;
  } else {
    // Recursive step: Iterate through all characters in the string.
    for (int i = 0; i < str.length(); i++) {
      // Form the remaining string by removing the character at index i.
      string rem = str.substr(0, i) + str.substr(i + 1);
      // Recursively call perm with the remaining string and updated prefix.
      perm(rem, prefix + str[i]);
    }
  }
}