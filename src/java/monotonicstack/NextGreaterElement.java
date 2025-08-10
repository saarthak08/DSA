package java.monotonicstack;

import java.util.*;

/**
 * What is a Monotonic Stack? A monotonic stack is a stack whose elements are either in strictly
 * increasing or strictly decreasing order. When we iterate through a sequence of elements, we
 * maintain this monotonicity property by popping elements from the stack that violate the order
 * before pushing the current element. What does this code do? This code takes an array of integers
 * as input and finds the "Next Greater Element" (NGE) for each element in the array. The Next
 * Greater Element for an element x is the first element to its right in the array that is greater
 * than x. If no such element exists, the NGE is considered -1. Time Complexity: O(N), where N is
 * the number of elements in the input array. Each element is pushed onto and popped off the stack
 * at most once. Space Complexity: O(N), in the worst case, the stack can hold all the elements of
 * the input array. The output array 'nge' also takes O(N) space.
 */
public class NextGreaterElement {

  public static int[] findNextGreaterElements(int[] arr) {
    if (arr == null) {
      return null;
    }
    int n = arr.length;
    int[] nge = new int[n];
    // Deque is the recommended interface for stack-like operations in modern Java.
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = n - 1; i >= 0; i--) {
      // While stack is not empty and top is smaller than or equal to the current element
      while (!stack.isEmpty() && stack.peek() <= arr[i]) {
        stack.pop();
      }

      // If stack is empty, no greater element exists to the right
      if (stack.isEmpty()) {
        nge[i] = -1;
      } else {
        nge[i] = stack.peek();
      }

      // Push current element onto the stack for subsequent elements
      stack.push(arr[i]);
    }
    return nge;
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

    int[] nge = findNextGreaterElements(arr);

    System.out.println("Next greater elements: " + Arrays.toString(nge));

    sc.close();
  }
}
