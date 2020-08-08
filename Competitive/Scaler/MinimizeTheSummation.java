public class MinimizeTheSummation {

  public static void main(String[] args) {
    int A = 4;
    int[][] B = { { 1, 2, -1000000000 }, { 2, 3, -1000000000 }, { 3, 4, -1000000000 } };
    int c = 0;
    System.out.println(solve(A, B, c));
  }

  public static int solve(int A, int[][] B, int C) {
    long m = 1000000007;
    int edgesCount[] = new int[A - 1];
    for (int i = 0; i < A - 1; i++) {
      edgesCount[i]++;
      int temp = B[i][0];
      while (temp != 1) {
        for (int j = 0; j < A - 1; j++) {
          if (B[j][1] == temp) {
            edgesCount[j]++;
            temp = B[j][0];
            break;
          }
        }
      }
    }
    while (C > 0) {
      int maxCount = Integer.MIN_VALUE;
      int maxIndex = -1;
      for (int i = 0; i < A - 1; i++) {
        if ((edgesCount[i] * B[i][2]) > maxCount) {
          maxCount = edgesCount[i] * B[i][2];
          maxIndex = i;
        } else if (edgesCount[i] == maxCount && maxCount != 0) {
          if (B[i][2] > B[maxIndex][2]) {
            maxIndex = i;
          }
        }
      }
      B[maxIndex][2] = 0;
      C--;
    }
    long[] values = new long[A + 1];
    boolean[] marked = new boolean[A + 1];
    long sum = 0;
    boolean flag = false;
    for (int i = 0; i < A - 1; i++) {
      if (B[i][0] == 1) {
        flag = true;
        sum = ((sum) + (B[i][2]));
        marked[B[i][1]] = true;
        values[B[i][1]] = B[i][2];
      } else {
        values[B[i][1]] = i;
      }
    }
    for (int i = 2; i < A + 1; i++) {
      if (!marked[i] && flag) {
        int index = (int) values[i];
        values[i] = B[index][2] + values[B[index][0]];
        sum = sum + values[i];
      }
    }
    if (!flag) {
      return 0;
    }
    return (int) (sum % m);
  }
}
