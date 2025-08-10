package in.sg.dsa.stack;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Stack using a Singly Linked List.
 *
 * <p>A stack is a linear data structure which follows a particular order in which the operations
 * are performed. The order is Last In First Out (LIFO). This implementation uses a linked list as
 * the underlying data structure, which allows the stack to grow dynamically without a fixed
 * capacity.
 *
 * <p>Time Complexity: - Push: O(1) - Pop: O(1) - Peek: O(1)
 *
 * <p>Space Complexity: O(n) for storing n elements in the list.
 */
public class StackLinkedList {

  private Node top;
  private int size;

  private static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
    }
  }

  public StackLinkedList() {
    this.top = null;
    this.size = 0;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public void push(int data) {
    Node newNode = new Node(data);
    newNode.next = top;
    top = newNode;
    size++;
    System.out.println(data + " pushed to stack.");
  }

  public int pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("STACK UNDERFLOW");
    }
    int data = top.data;
    top = top.next;
    size--;
    return data;
  }

  public int peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("STACK IS EMPTY");
    }
    return top.data;
  }

  public void display() {
    if (isEmpty()) {
      System.out.println("STACK IS EMPTY");
      return;
    }
    System.out.println("Stack elements (from top to bottom):");
    Node current = top;
    while (current != null) {
      System.out.println(current.data);
      current = current.next;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StackLinkedList stack = new StackLinkedList();
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
          stack.push(scanner.nextInt());
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
            System.out.println("The value at the top of stack is: " + stack.peek());
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
