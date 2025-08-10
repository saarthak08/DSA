package java.sortingsearching;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Contains implementations of the Binary Search algorithm.
 *
 * <p>Binary search is an efficient algorithm for finding an item from a sorted list of items. It
 * works by repeatedly dividing in half the portion of the list that could contain the item, until
 * you've narrowed down the possible locations to just one.
 *
 * <p>Pre-requisite: The input array must be sorted.
 *
 * <p>Time Complexity: O(log n) because the search space is halved in each step. Space Complexity: -
 * Iterative: O(1) - Recursive: O(log n) for the recursion call stack.
 */
public final class BinarySearch {

  private BinarySearch() {}

  /**
   * Performs an iterative binary search.
   *
   * @param arr The sorted array to search in.
   * @param x The element to search for.
   * @return The index of the element if found, otherwise -1.
   */
  public static int iterativeSearch(int[] arr, int x) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
      // Using (l + (r - l) / 2) to prevent potential overflow
      int m = l + (r - l) / 2;

      // Check if x is present at mid
      if (arr[m] == x) return m;

      // If x greater, ignore left half
      if (arr[m] < x) l = m + 1;
      // If x is smaller, ignore right half
      else r = m - 1;
    }
    // if we reach here, then element was not present
    return -1;
  }

  /**
   * Performs a recursive binary search.
   *
   * @param arr The sorted array to search in.
   * @param x The element to search for.
   * @return The index of the element if found, otherwise -1.
   */
  public static int recursiveSearch(int[] arr, int x) {
    return recursiveSearch(arr, 0, arr.length - 1, x);
  }

  private static int recursiveSearch(int[] arr, int l, int r, int x) {
    if (r >= l) {
      int m = l + (r - l) / 2;

      if (arr[m] == x) return m;

      if (arr[m] > x) return recursiveSearch(arr, l, m - 1, x);

      return recursiveSearch(arr, m + 1, r, x);
    }
    return -1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of elements in the array: ");
    int n = sc.nextInt();
    int[] arr = new int[n];
    System.out.println("Enter the elements of the array in sorted form:");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    System.out.println("The array is: " + Arrays.toString(arr));
    System.out.print("Enter the number to be searched: ");
    int data = sc.nextInt();

    // Demonstrate Iterative Search
    int iterativeIndex = iterativeSearch(arr, data);
    if (iterativeIndex != -1) {
      System.out.println("(Iterative) Element found at index: " + iterativeIndex);
    } else {
      System.out.println("(Iterative) Element not found.");
    }

    // Demonstrate Recursive Search
    int recursiveIndex = recursiveSearch(arr, data);
    if (recursiveIndex != -1) {
      System.out.println("(Recursive) Element found at index: " + recursiveIndex);
    } else {
      System.out.println("(Recursive) Element not found.");
    }

    sc.close();
  }
}
