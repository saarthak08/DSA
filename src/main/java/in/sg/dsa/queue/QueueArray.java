package in.sg.dsa.queue;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Queue using a fixed-size circular array.
 *
 * <p>A queue is a linear data structure which follows a particular order in which the operations
 * are performed. The order is First In First Out (FIFO).
 *
 * <p>This implementation uses a circular array, which is more efficient than a simple array as it
 * reuses the empty space at the beginning of the array.
 *
 * <p>Time Complexity: - Enqueue (insert): O(1) - Dequeue (delete): O(1) - Peek (first element):
 * O(1)
 *
 * <p>Space Complexity: O(N) where N is the capacity of the queue.
 */
public class QueueArray {

  private final int[] arr;
  private int front;
  private int rear;
  private int size;
  private final int capacity;

  public QueueArray(int capacity) {
    this.capacity = capacity;
    this.arr = new int[capacity];
    this.front = -1;
    this.rear = -1;
    this.size = 0;
  }

  public boolean isFull() {
    return size == capacity;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void enqueue(int item) {
    if (isFull()) {
      System.out.println("OVERFLOW: Queue is full.");
      return;
    }
    if (isEmpty()) {
      front = 0;
    }
    rear = (rear + 1) % capacity;
    arr[rear] = item;
    size++;
    System.out.println(item + " enqueued to queue.");
  }

  public int dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("UNDERFLOW: Queue is empty.");
    }
    int item = arr[front];
    if (front == rear) { // Queue becomes empty
      front = -1;
      rear = -1;
    } else {
      front = (front + 1) % capacity;
    }
    size--;
    return item;
  }

  public int peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return arr[front];
  }

  public int last() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return arr[rear];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the capacity of the queue: ");
    int capacity = scanner.nextInt();
    QueueArray queue = new QueueArray(capacity);
    int option;

    do {
      System.out.println("\n\n ***** MAIN MENU *****");
      System.out.println("1. Insert an element (Enqueue)");
      System.out.println("2. Delete an element (Dequeue)");
      System.out.println("3. Peek First element");
      System.out.println("4. Peek Last Element");
      System.out.println("5. EXIT");
      System.out.print("Enter your option : ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Enter the number to be inserted: ");
          queue.enqueue(scanner.nextInt());
          break;
        case 2:
          try {
            System.out.println("The number deleted is : " + queue.dequeue());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 3:
          try {
            System.out.println("The first value in queue is : " + queue.peek());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 4:
          try {
            System.out.println("The last value in queue is : " + queue.last());
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
