package java.sortingsearching;

import java.util.Scanner;

/*
 * The idea behind merge sort is divide the array into the parts recursively and then sort both the divided arrays
 * and then merge the sorted arrays. So, we will divide the arrays recursively to a point that only two elements will remain.
 * Now we will sort them in constant time. Now, we will increase join two already two elements array likewise.
 * T(n) = 2T(n/2) + n;
 */

public class MergeSort {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    mergeSort(0, n - 1, arr);

    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static void mergeSort(int l, int r, int[] arr) {
    if (l < r) {
      int m = (l + r) / 2;
      mergeSort(l, m, arr);
      mergeSort(m + 1, r, arr);
      merge(l, m, r, arr);
    }
  }

  private static void merge(int l, int m, int r, int[] arr) {
    int[] tempArr = new int[r - l + 1];
    int i = l;
    int j = m + 1;
    int k = 0;
    while (i <= m || j <= r) {
      if (i <= m && j <= r) {
        if (arr[i] > arr[j]) {
          tempArr[k++] = arr[j++];
        } else {
          tempArr[k++] = arr[i++];
        }
      } else if (i > m && j <= r) {
        tempArr[k++] = arr[j++];
      } else if (i <= m && j > r) {
        tempArr[k++] = arr[i++];
      }
    }
    for (i = 0; i <= r - l; i++) {
      arr[i + l] = tempArr[i];
    }
  }
}
