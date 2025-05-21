class Solution {
  public boolean isPalindrome(int x) {
    String k = String.valueOf(x);
    int i = 0, j = k.length() - 1;
    for (; i < k.length() / 2; i++, j--) {
      if (k.charAt(i) != k.charAt(j)) {
        return false;
      }
    }
    return true;
  }
}