package java.arraysstrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PowerSet {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of numbers you want in array: ");
    int n = scanner.nextInt();
    int[] arr = new int[n];
    System.out.println("Enter the numbers: ");
    for (int i = 0; i < n; i++) {
      arr[i] = scanner.nextInt();
    }

    List<List<Integer>> ps = new ArrayList<>();
    powerSet(arr, n, new ArrayList<>(), ps);

    System.out.println("The Power Set: ");
    // Print subsets in a more conventional order
    for (int i = ps.size() - 1; i >= 0; i--) {
      System.out.println(ps.get(i));
    }
    scanner.close();
  }

  /**
   * Time Complexity: O(2^n) Approach: For each element, it is either selected or not selected. T(n)
   * = 2T(n-1) + c
   *
   * @param input The input array.
   * @param n The current size of the array being considered.
   * @param current The current subset being built.
   * @param ps The list to store all generated subsets.
   */
  public static void powerSet(int[] input, int n, List<Integer> current, List<List<Integer>> ps) {
    if (n == 0) {
      ps.add(new ArrayList<>(current));
      return;
    }

    // Element Not Selected
    powerSet(input, n - 1, current, ps);

    // Element Selected
    current.add(input[n - 1]);
    powerSet(input, n - 1, current, ps);
    // Backtrack
    current.removeLast();
  }
}
