package java_codes.sortingsearching;

import java.util.Arrays;
import java.util.Scanner;

public final class BubbleSort {

  // Private constructor to prevent instantiation of this utility class.
  private BubbleSort() {}

  /**
   * Sorts an array in ascending order using the Bubble Sort algorithm. This implementation includes
   * an optimization to terminate early if the array becomes sorted before all passes are complete.
   *
   * @param arr The array to be sorted.
   */
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int n = arr.length;
    boolean swapped;
    for (int i = 0; i < n - 1; i++) {
      swapped = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          // Swap arr[j] and arr[j+1]
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }
      }
      // If no two elements were swapped by inner loop, then break
      if (!swapped) {
        break;
      }
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
