package java_codes.heap;

import java.util.Arrays;
import java.util.Scanner;

// Time Complexity: O(n*log(n)).
/* Approach:  Build a heap and then, remove the first element of the heap by placing it at the last of the array and
then reduce the size of heap & heapify (percolateDown).
*/
public final class HeapSort {

  // Private constructor to prevent instantiation of this utility class.
  private HeapSort() {}

  /**
   * Sorts an array in ascending order using the Heap Sort algorithm.
   *
   * @param arr The array to be sorted.
   */
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int n = arr.length;

    // Build a max-heap from the array.
    // The last non-leaf node is at index (n / 2) - 1.
    for (int i = (n / 2) - 1; i >= 0; i--) {
      heapify(arr, n, i);
    }

    // One by one, extract elements from the heap.
    for (int i = n - 1; i > 0; i--) {
      // Move the current root (max element) to the end.
      swap(arr, 0, i);

      // Call max heapify on the reduced heap.
      heapify(arr, i, 0);
    }
  }

  /**
   * To heapify a subtree rooted with node i which is an index in arr[]. n is the size of the heap.
   */
  private static void heapify(int[] arr, int n, int i) {
    int largest = i; // Initialize largest as root
    int left = 2 * i + 1; // left child
    int right = 2 * i + 2; // right child

    // If left child is larger than root
    if (left < n && arr[left] > arr[largest]) {
      largest = left;
    }

    // If right child is larger than largest so far
    if (right < n && arr[right] > arr[largest]) {
      largest = right;
    }

    // If largest is not root
    if (largest != i) {
      swap(arr, i, largest);

      // Recursively heapify the affected sub-tree
      heapify(arr, n, largest);
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
