package java_codes.sortingsearching;

import java.util.Arrays;
import java.util.Scanner;

/*
Time Complexity: Worst Case: O(n^2), Best Case: O(n).
Approach: Each element is compared to all the elements previous to it in the array starting from 2nd element &
then the element is inserted at right position among the elements.
*/

public final class InsertionSort {

  // Private constructor to prevent instantiation of this utility class.
  private InsertionSort() {}

  /**
   * Sorts an array in ascending order using the Insertion Sort algorithm.
   *
   * @param arr The array to be sorted.
   */
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    for (int i = 1; i < arr.length; i++) {
      int key = arr[i];
      int j = i - 1;

      /* Move elements of arr[0..i-1], that are
      greater than key, to one position ahead
      of their current position */
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of elements: ");
    int n = sc.nextInt();
    int[] arr = new int[n];
    System.out.println("Enter the elements:");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    System.out.println("Original array: " + Arrays.toString(arr));
    sort(arr);
    System.out.println("Sorted array:   " + Arrays.toString(arr));

    sc.close();
  }
}
