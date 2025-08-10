package java.arraysstrings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Sorts an array containing only 0s, 1s, and 2s.
 *
 * <p>This problem is a classic example that can be solved efficiently in a single pass using the
 * Dutch National Flag algorithm.
 *
 * <p>Algorithm: Dutch National Flag The algorithm uses three pointers: `low`, `mid`, and `high`.
 * The array is divided into four sections: - `arr[0...low-1]` contains all 0s. - `arr[low...mid-1]`
 * contains all 1s. - `arr[mid...high]` is the unknown section. - `arr[high+1...n-1]` contains all
 * 2s.
 *
 * <p>We iterate with the `mid` pointer from the beginning to the end (`mid <= high`): 1. If
 * `arr[mid]` is 0, we swap it with `arr[low]` and increment both `low` and `mid`. 2. If `arr[mid]`
 * is 1, we just increment `mid` as it's in the correct place. 3. If `arr[mid]` is 2, we swap it
 * with `arr[mid]` and `arr[high]` and decrement `high`. We do not increment `mid` because the
 * element swapped from `high` needs to be processed.
 *
 * <p>Time Complexity: O(n), as the array is traversed only once. Space Complexity: O(1), as the
 * sorting is done in-place.
 */
public final class SortArrayOfZerosOnesTwos {

  private SortArrayOfZerosOnesTwos() {}

  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int low = 0;
    int mid = 0;
    int high = arr.length - 1;

    while (mid <= high) {
      switch (arr[mid]) {
        case 0:
          swap(arr, low, mid);
          low++;
          mid++;
          break;
        case 1:
          mid++;
          break;
        case 2:
          swap(arr, mid, high);
          high--;
          break;
      }
    }
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of elements: ");
    int n = sc.nextInt();
    int[] arr = new int[n];
    System.out.println("Enter the elements (only 0s, 1s, and 2s):");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    System.out.println("Original array: " + Arrays.toString(arr));
    sort(arr);
    System.out.println("Sorted array:   " + Arrays.toString(arr));

    sc.close();
  }
}
