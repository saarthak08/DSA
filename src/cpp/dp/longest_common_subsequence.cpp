#include <algorithm> // For std::reverse
#include <iostream>
#include <string>
#include <vector>

using namespace std;

/**
 * @brief Finds the longest common subsequence (LCS) of two strings.
 *
 * This function uses dynamic programming to find both the length of the LCS
 * and the LCS string itself.
 *
 * @param s1 The first string.
 * @param s2 The second string.
 */
void findLCS(const string &s1, const string &s2) {
  int m = s1.length();
  int n = s2.length();

  // dp[i][j] will store the length of LCS of s1[0..i-1] and s2[0..j-1]
  vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

  // Build the DP table in a bottom-up manner
  for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
      if (s1[i - 1] == s2[j - 1]) {
        dp[i][j] = 1 + dp[i - 1][j - 1];
      } else {
        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }

  cout << "\nLength of LCS: " << dp[m][n] << endl;

  // Reconstruct the LCS string by backtracking from dp[m][n]
  string lcs_string = "";
  int i = m, j = n;
  while (i > 0 && j > 0) {
    // If current characters in s1 and s2 are same, then
    // current character is part of LCS
    if (s1[i - 1] == s2[j - 1]) {
      lcs_string += s1[i - 1];
      i--;
      j--;
    }
    // If not same, then find the larger of two and
    // go in the direction of larger value
    else if (dp[i - 1][j] > dp[i][j - 1]) {
      i--;
    } else {
      j--;
    }
  }

  // Reverse the LCS string to get the correct order
  reverse(lcs_string.begin(), lcs_string.end());

  cout << "LCS Sequence: " << lcs_string << endl;
}

int main() {
  string s1, s2;
  cout << "Enter first string: ";
  cin >> s1;
  cout << "Enter second string: ";
  cin >> s2;

  findLCS(s1, s2);

  return 0;
}