class Solution {
  public int reverse(int x) {
    int n = Math.abs(x), k = 0;
    long res = 0;
    while (n > 0) {
      k = n % 10;
      if (((res * 10 + k) > Integer.MAX_VALUE) || ((res * 10 + k) < (Integer.MIN_VALUE))) {
        return 0;
      }
      res = res * 10 + k;
      n = n / 10;
    }
    if (x < 0) {
      res = -res;
    }
    return (int) res;
  }
}