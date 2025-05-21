class Solution {
  public int myAtoi(String s) {
    int i = 0;
    long num = 0;
    boolean neg = false;
    boolean sign = false;
    boolean space = false;
    while (i < s.length()) {
      if (s.charAt(i) == '-' && num == 0 && !sign) {
        sign = true;
        neg = true;
        space = true;
        i++;
        continue;
      } else if (s.charAt(i) == '+' && num == 0 && !sign) {
        sign = true;
        neg = false;
        space = true;
        i++;
        continue;
      }
      if (s.charAt(i) == ' ' && !space) {
        i++;
        continue;
      }

      if (!space) space = true;
      if (!sign) sign = true;
      if (!isNumeric(s.charAt(i))) break;
      num *= 10;
      num += convertNumeric(s.charAt(i));

      if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
        break;
      }

      i++;
    }

    if (neg) num *= -1;
    if (num > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    } else if (num < Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return (int) num;
  }

  public boolean isNumeric(char c) {
    return (c >= '0' && c <= '9');
  }

  public int convertNumeric(char c) {
    //0-9
    return (int) c - '0';
  }
}