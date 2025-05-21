class Solution {
  public String longestCommonPrefix(String[] strs) {
    int lowestLength = Integer.MAX_VALUE;
    if (strs.length == 0) {
      return "";
    }
    String smallestString = "";
    for (int i = 0; i < strs.length; i++) {
      if (lowestLength > strs[i].length()) {
        lowestLength = strs[i].length();
        smallestString = strs[i];
      }
    }
    String str = "";
    boolean flag = false;
    for (int i = 0; i < lowestLength; i++) {
      for (int j = 0; j < strs.length; j++) {
        if (strs[j].charAt(i) != smallestString.charAt(i)) {
          flag = true;
          break;
        }
      }
      if (flag) {
        break;
      } else {
        str = str + smallestString.charAt(i);
      }
    }
    return str;
  }
}