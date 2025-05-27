package PrefixSum;

import java.util.Scanner;

/**
 * // What is Prefix Sum?
 * // The prefix sum of an array is a new array where the i-th element is the
 * sum of all elements in the original array up to and including the i-th
 * element.
 * // It's a useful technique for efficiently calculating the sum of any
 * subarray in constant time after the prefix sum array is computed.
 * //
 * // What does this code do?
 * // This code takes an array of integers as input and calculates its prefix
 * sum array.
 * // It iterates through the input array, and for each element, it calculates
 * the sum of all preceding elements (including the current one)
 * // and stores it in the corresponding position of the 'prefixSum' array.
 * // Finally, it prints the calculated prefix sum array.
 * //
 * // Time Complexity: O(N), where N is the number of elements in the input
 * array. The code iterates through the array twice: once to read input and once
 * to calculate the prefix sum.
 * // Space Complexity: O(N), as it creates a new array 'prefixSum' of the same
 * size as the input array to store the prefix sums.
 */
public class PrefixSum {
  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    int[] prefixSum = new int[n];
    if (n > 0) {
      prefixSum[0] = arr[0];
    }
    for (int i = 1; i < n; i++) {
      prefixSum[i] = prefixSum[i - 1] + arr[i];
    }
    System.out.println("Prefix Sum: ");
    for (int i = 0; i < n; i++) {
      System.out.print(prefixSum[i] + " ");
    }
    System.out.println();
  }
}
