import java.util.Scanner;

public class SelectionSort {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    sort(arr, n);

    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static void sort(int[] arr, int n) {
    for (int i = 0; i < n; i++) {
      int sm = Integer.MAX_VALUE;
      int smIndex = -1;
      for (int j = i; j < n; j++) {
        if (arr[j] < sm) {
          sm = arr[j];
          smIndex = j;
        }
      }
      if (smIndex != -1) {
        int temp = arr[i];
        arr[i] = arr[smIndex];
        arr[smIndex] = temp;
      }
    }
  }
}
