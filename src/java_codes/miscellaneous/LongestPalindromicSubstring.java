package java_codes.miscellaneous;

import java.util.Scanner;

/**
 * Finds the longest palindromic substring in a given string.
 *
 * <p>Algorithm: Expand Around Center The core idea is to treat each character and each space
 * between characters as a potential center of a palindrome. There are 2n-1 such centers (n
 * characters and n-1 spaces).
 *
 * <p>For each center, we expand outwards (one character to the left, one to the right) as long as
 * the characters match. We keep track of the longest palindrome found so far.
 *
 * <p>Time Complexity: O(n^2) There are 2n-1 centers. Expanding from each center can take up to O(n)
 * time in the worst case.
 *
 * <p>Space Complexity: O(1) The algorithm uses a constant amount of extra space (if the output
 * string is not considered).
 */
public final class LongestPalindromicSubstring {

  private LongestPalindromicSubstring() {}

  public static String find(String s) {
    if (s == null || s.isEmpty()) {
      return "";
    }

    int start = 0;
    int end = 0;

    for (int i = 0; i < s.length(); i++) {
      // For odd length palindromes (like "aba")
      int len1 = expandAroundCenter(s, i, i);
      // For even length palindromes (like "abba")
      int len2 = expandAroundCenter(s, i, i + 1);

      int len = Math.max(len1, len2);

      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private static int expandAroundCenter(String s, int left, int right) {
    int L = left;
    int R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
      L--;
      R++;
    }
    return R - L - 1;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String str = scanner.nextLine();

    System.out.println("Longest Palindromic Substring of \"" + str + "\" is \"" + find(str) + "\"");
    scanner.close();
  }
}
