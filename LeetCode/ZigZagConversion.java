class Solution {
  public String convert(String s, int numRows) {
    String[] x = new String[numRows];
    for (int i = 0; i < numRows; i++) {
      x[i] = "";
    }
    int j = 0, k = 0;
    boolean ascend = true;
    if (numRows == 1) {
      return s;
    }
    for (int i = 0; i < s.length(); i++) {
      if (j == 0) {
        ascend = true;
      } else if (j == numRows - 1) {
        ascend = false;
      }
      if (ascend) {
        x[j] += String.valueOf(s.charAt(i));
        j++;
      } else {
        x[j] += String.valueOf(s.charAt(i));
        j--;
        k++;
      }
    }
    String result = "";
    for (int i = 0; i < numRows; i++) {
      result += x[i];
    }
    return result;
  }
}