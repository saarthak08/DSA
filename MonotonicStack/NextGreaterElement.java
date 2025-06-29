package MonotonicStack;

import java.util.*;

/**
 * // What is a Monotonic Stack?
 * // A monotonic stack is a stack whose elements are either in strictly
 * increasing or strictly decreasing order.
 * // When we iterate through a sequence of elements, we maintain this
 * monotonicity property by popping elements
 * // from the stack that violate the order before pushing the current element.
 * //
 * // What does this code do?
 * // This code takes an array of integers as input and finds the "Next Greater
 * Element" (NGE) for each element in the array.
 * // The Next Greater Element for an element x is the first element to its
 * right in the array that is greater than x.
 * // If no such element exists, the NGE is considered -1.
 * //
 * // Time Complexity: O(N), where N is the number of elements in the input
 * array. Each element is pushed onto and popped off the stack at most once.
 * // Space Complexity: O(N), in the worst case, the stack can hold all the
 * elements of the input array. The output array 'nge' also takes O(N) space.
 */
public class NextGreaterElement {
  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    int[] nge = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] > stack.peek()) {
        stack.pop();
      }
      if (stack.isEmpty()) {
        nge[i] = -1;
      } else {
        nge[i] = stack.peek();
      }
      stack.push(arr[i]);
    }
    System.out.println("Next greater elements: ");
    for (int i = 0; i < n; i++) {
      System.out.print(nge[i] + " ");
    }
    System.out.println();
  }
}
