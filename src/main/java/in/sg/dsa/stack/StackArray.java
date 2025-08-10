package in.sg.dsa.stack;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Stack using a fixed-size array.
 *
 * <p>A stack is a linear data structure which follows a particular order in which the operations
 * are performed. The order is Last In First Out (LIFO).
 *
 * <p>Time Complexity: - Push: O(1) - Pop: O(1) - Peek: O(1)
 *
 * <p>Space Complexity: O(N) where N is the capacity of the stack.
 */
public class StackArray {

  private final int[] arr;
  private int top;
  private final int capacity;

  public StackArray(int capacity) {
    this.capacity = capacity;
    this.arr = new int[capacity];
    this.top = -1;
  }

  public boolean isFull() {
    return top == capacity - 1;
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public void push(int item) {
    if (isFull()) {
      throw new IllegalStateException("STACK OVERFLOW");
    }
    arr[++top] = item;
    System.out.println(item + " pushed to stack.");
  }

  public int pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("STACK UNDERFLOW");
    }
    return arr[top--];
  }

  public int peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("STACK IS EMPTY");
    }
    return arr[top];
  }

  public void display() {
    if (isEmpty()) {
      System.out.println("STACK IS EMPTY");
      return;
    }
    System.out.println("Stack elements:");
    for (int i = top; i >= 0; i--) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the capacity of the stack: ");
    int capacity = scanner.nextInt();
    StackArray stack = new StackArray(capacity);
    int option;

    do {
      System.out.println("\n*****MAIN MENU*****");
      System.out.println("1. PUSH");
      System.out.println("2. POP");
      System.out.println("3. PEEK");
      System.out.println("4. DISPLAY");
      System.out.println("5. EXIT");
      System.out.print("Enter your option: ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Enter the number to be pushed on stack: ");
          try {
            stack.push(scanner.nextInt());
          } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 2:
          try {
            System.out.println("The value deleted from stack is: " + stack.pop());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 3:
          try {
            System.out.println("The value stored at top of stack is: " + stack.peek());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 4:
          stack.display();
          break;
        case 5:
          break;
        default:
          System.out.println("Invalid option.");
      }
    } while (option != 5);
    scanner.close();
  }
}
