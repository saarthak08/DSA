package in.sg.dsa.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Checks for balanced parentheses in an expression using a stack.
 *
 * <p>An expression is balanced if: 1. For every opening bracket, there is a corresponding closing
 * bracket. 2. The pairs of brackets are correctly nested.
 *
 * <p>This algorithm uses a stack (implemented with ArrayDeque) to keep track of opening brackets.
 *
 * <p>Time Complexity: O(n) because we traverse the given string one character at a time. Space
 * Complexity: O(n) in the worst case, where all characters are opening brackets.
 */
public final class ParenthesisMatching {

  private ParenthesisMatching() {}

  /**
   * Checks if the brackets in a given expression are balanced.
   *
   * @param expr The expression string.
   * @return true if the brackets are balanced, false otherwise.
   */
  public static boolean isBalanced(String expr) {
    // Using ArrayDeque is recommended for stack implementation in modern Java.
    Deque<Character> stack = new ArrayDeque<>();

    for (char x : expr.toCharArray()) {
      if (x == '(' || x == '[' || x == '{') {
        // Push the element in the stack
        stack.push(x);
        continue;
      }

      // If current character is not opening bracket,
      // then it must be closing. So stack cannot be empty at this point.
      if (stack.isEmpty()) {
        return false;
      }

      char check;
      switch (x) {
        case ')':
          check = stack.pop();
          if (check == '{' || check == '[') return false;
          break;

        case '}':
          check = stack.pop();
          if (check == '(' || check == '[') return false;
          break;

        case ']':
          check = stack.pop();
          if (check == '(' || check == '{') return false;
          break;
      }
    }

    // Check Empty Stack
    return (stack.isEmpty());
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter an expression: ");
    String expression = scanner.nextLine();

    if (isBalanced(expression)) {
      System.out.println("Valid expression");
    } else {
      System.out.println("Invalid expression");
    }

    scanner.close();
  }
}
