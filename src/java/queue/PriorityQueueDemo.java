package java.queue;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Demonstrates the use of Java's built-in PriorityQueue.
 *
 * <p>A priority queue is an abstract data type similar to a regular queue or stack data structure
 * in which each element additionally has a "priority" associated with it. In a priority queue, an
 * element with high priority is served before an element with low priority.
 *
 * <p>Java's `java.util.PriorityQueue` is implemented using a min-heap, which provides efficient
 * O(log n) time complexity for add (enqueue) and poll (dequeue) operations.
 *
 * <p>This is a much more efficient implementation than using a sorted linked list, where insertion
 * takes O(n) time.
 *
 * <p>Time Complexity (Heap-based): - Add (insert): O(log n) - Poll (delete min): O(log n) - Peek
 * (find min): O(1)
 *
 * <p>Space Complexity: O(n) for storing n elements.
 */
public class PriorityQueueDemo {

  // An inner class to hold data and its priority.
  // It implements Comparable to define its natural ordering based on priority.
  static class Item implements Comparable<Item> {
    private final int value;
    private final int priority;

    public Item(int value, int priority) {
      this.value = value;
      this.priority = priority;
    }

    public int getValue() {
      return value;
    }

    public int getPriority() {
      return priority;
    }

    @Override
    public int compareTo(Item other) {
      // Lower number means higher priority
      return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
      return value + "[priority=" + priority + "]";
    }
  }

  public static void main(String[] args) {
    // A min-priority queue that orders Items based on their natural order (priority)
    PriorityQueue<Item> pq = new PriorityQueue<>();
    Scanner scanner = new Scanner(System.in);
    int option;

    do {
      System.out.println("\n***** MAIN MENU *****");
      System.out.println("1. INSERT");
      System.out.println("2. DELETE (highest priority / min element)");
      System.out.println("3. PEEK (highest priority / min element)");
      System.out.println("4. EXIT");
      System.out.print("Enter your option : ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Enter the value and its priority: ");
          int val = scanner.nextInt();
          int pri = scanner.nextInt();
          pq.add(new Item(val, pri));
          System.out.println("Inserted " + val + " with priority " + pri);
          break;
        case 2:
          if (pq.isEmpty()) {
            System.out.println("UNDERFLOW: Queue is empty.");
          } else {
            Item deletedItem = pq.poll();
            System.out.println("Deleted item is: " + deletedItem);
          }
          break;
        case 3:
          if (pq.isEmpty()) {
            System.out.println("QUEUE IS EMPTY");
          } else {
            System.out.println("Min element is: " + pq.peek());
          }
          break;
        case 4:
          break;
        default:
          System.out.println("Invalid option.");
      }
    } while (option != 4);
    scanner.close();
  }
}
