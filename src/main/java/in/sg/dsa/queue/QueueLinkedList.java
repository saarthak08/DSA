package in.sg.dsa.queue;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Queue using a Singly Linked List.
 *
 * <p>A queue is a linear data structure which follows a particular order in which the operations
 * are performed. The order is First In First Out (FIFO).
 *
 * <p>This implementation uses a linked list with front and rear pointers, allowing for efficient
 * O(1) time complexity for both enqueue and dequeue operations.
 *
 * <p>Time Complexity: - Enqueue (insert): O(1) - Dequeue (delete): O(1) - Peek (first element):
 * O(1)
 *
 * <p>Space Complexity: O(n) for storing n elements in the list.
 */
public class QueueLinkedList {

  private Node front;
  private Node rear;
  private int size;

  private static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
    }
  }

  public QueueLinkedList() {
    this.front = null;
    this.rear = null;
    this.size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void enqueue(int data) {
    Node newNode = new Node(data);
    if (isEmpty()) {
      front = rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
    size++;
    System.out.println(data + " enqueued to queue.");
  }

  public int dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("UNDERFLOW: Queue is empty.");
    }
    int data = front.data;
    front = front.next;
    if (front == null) { // If list becomes empty
      rear = null;
    }
    size--;
    return data;
  }

  public int peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return front.data;
  }

  public int peekLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return rear.data;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    QueueLinkedList queue = new QueueLinkedList();
    int option;

    do {
      System.out.println("\n*****MAIN MENU*****");
      System.out.println("1. INSERT");
      System.out.println("2. DELETE");
      System.out.println("3. FIRST ELEMENT");
      System.out.println("4. LAST ELEMENT");
      System.out.println("5. EXIT");
      System.out.print("Enter your option : ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Enter the number to insert in the queue: ");
          queue.enqueue(scanner.nextInt());
          break;
        case 2:
          try {
            System.out.println("The value being deleted is : " + queue.dequeue());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 3:
          try {
            System.out.println("The value at front of queue is : " + queue.peek());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 4:
          try {
            System.out.println("The value at rear of queue is : " + queue.peekLast());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
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
