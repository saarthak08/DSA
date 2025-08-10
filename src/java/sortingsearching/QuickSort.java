package java.sortingsearching;

import java.util.Arrays;
import java.util.Scanner;

/*
Time Complexity: O(n^2) - Worst Case. O(nLogn) - Best Case.
Approach: Pivot is selected from the array.
A pivot is an element which is already sorted according to array.
Then array is divided into halves. One is before pivot and one is after pivot.
Same thing is done recursively.

Approach: Partition Function:
Pivotal element is chosen as first element.
And then, going from left side to right side & right side to left side simultaneously till the middle element,
check that if any element going from left to right is greater than pivotal element, then swap it with any element which is smaller than pivotal element going from right to left.
After this has completed, replace the index of right to left pointer with pivotal element and then, return the index of right to left pointer. This is the pivot position.

Quick Sort worst case complexity can be improved by:
1) Choosing Randomised pivotal element in partition function instead of first element as if the array is already sorted or reverse sorted, the partition always come at first or last. Thus making complexity O(n^2).
2) Choosing a partition which will always be in the middle i.e. median. For median, we can take approximate median by calculating median of 3 medians.

Still, Quick Sort's worst cannot be completely reduced to O(nLogn) yet it is considered to be the best sorting algorithm.
*/

public final class QuickSort {

  // Private constructor to prevent instantiation of this utility class.
  private QuickSort() {}

  /**
   * Sorts an array in ascending order using the QuickSort algorithm.
   *
   * @param arr The array to be sorted.
   */
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      // pi is partitioning index, arr[pi] is now at right place
      int pi = partition(arr, low, high);

      // Recursively sort elements before partition and after partition
      quickSort(arr, low, pi - 1);
      quickSort(arr, pi + 1, high);
    }
  }

  /**
   * This function takes last element as pivot, places the pivot element at its correct position in
   * sorted array, and places all smaller (smaller than pivot) to left of pivot and all greater
   * elements to right of pivot. This is the Lomuto partition scheme.
   */
  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than or equal to pivot
      if (arr[j] <= pivot) {
        i++;
        // swap arr[i] and arr[j]
        swap(arr, i, j);
      }
    }

    // swap arr[i+1] and arr[high] (or pivot)
    swap(arr, i + 1, high);

    return i + 1;
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
