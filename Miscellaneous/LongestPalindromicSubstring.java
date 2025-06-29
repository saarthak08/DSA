public class LongestPalindromicSubstring {

  private static String LongestPalindromicSubString(String s, int len) {
    int maxLength = 0, currLength = 0;
    String maxString = "", currString = "";

    if (len == 1) {
      return s;
    }

    for (int i = 0; i < len - 1; i++) {
      currString = expand(s, i, i);
      if (currString.length() > currLength) {
        maxString = currString;
        currLength = currString.length();
      }
      currString = expand(s, i, i + 1);
      if (currString.length() > currLength) {
        maxString = currString;
        currLength = currString.length();
      }
    }
    return maxString;
  }

  private static String expand(String s, int low, int high) {
    int len = s.length();
    while (low >= 0 && high < len && (s.charAt(low) == s.charAt(high))) {
      low--;
      high++;
    }
    return s.substring(low + 1, high);
  }

  public static void main(String[] args) {
    String str = "forgeeksskeegfor";

    System.out.println("Longest Palindromic SubString of " + str + " is "
      + LongestPalindromicSubString(str, str.length() - 1));
  }
}
