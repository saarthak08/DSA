package java_codes.linkedlist;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Circular Singly Linked List.
 *
 * <p>A circular linked list is a linked list where all nodes are connected to form a circle. There
 * is no NULL at the end. A circular linked list can be a singly circular linked list or a doubly
 * circular linked list.
 *
 * <p>This implementation uses a `tail` reference. The `tail.next` points to the head of the list.
 * This allows for O(1) insertion at both the beginning and the end.
 *
 * <p>Time Complexity: - Access: O(n) - Search: O(n) - Insertion (at beginning or end): O(1) -
 * Insertion (at middle): O(n) - Deletion (at beginning): O(1) - Deletion (at end): O(n) - Deletion
 * (at middle): O(n)
 *
 * <p>Space Complexity: O(n) for storing n nodes.
 */
public class CircularLinkedList {

  private Node tail;
  private int size;

  private static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
    }
  }

  public CircularLinkedList() {
    this.tail = null;
    this.size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void addFirst(int data) {
    Node newNode = new Node(data);
    if (isEmpty()) {
      tail = newNode;
      tail.next = tail;
    } else {
      newNode.next = tail.next;
      tail.next = newNode;
    }
    size++;
  }

  public void addLast(int data) {
    Node newNode = new Node(data);
    if (isEmpty()) {
      tail = newNode;
      tail.next = tail;
    } else {
      newNode.next = tail.next;
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  public int removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    Node head = tail.next;
    int data = head.data;
    if (tail == head) { // if only one node
      tail = null;
    } else {
      tail.next = head.next;
    }
    size--;
    return data;
  }

  public int removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty.");
    }
    int data = tail.data;
    if (tail.next == tail) { // if only one node
      tail = null;
    } else {
      Node current = tail.next;
      while (current.next != tail) {
        current = current.next;
      }
      current.next = tail.next;
      tail = current;
    }
    size--;
    return data;
  }

  public boolean removeAfter(int key) {
    if (isEmpty()) {
      return false;
    }
    Node current = tail.next;
    Node nodeToDelete = null;
    // Find the node with the given key
    do {
      if (current.data == key) {
        nodeToDelete = current.next;
        break;
      }
      current = current.next;
    } while (current != tail.next);

    if (nodeToDelete != null && nodeToDelete != tail.next) { // Cannot delete head this way
      current.next = nodeToDelete.next;
      if (nodeToDelete == tail) { // if we are deleting the tail
        tail = current;
      }
      size--;
      return true;
    }
    return false;
  }

  public void clear() {
    tail = null;
    size = 0;
  }

  public void display() {
    if (isEmpty()) {
      System.out.println("List is empty.");
      return;
    }
    Node current = tail.next; // Start from head
    do {
      System.out.print(current.data + " -> ");
      current = current.next;
    } while (current != tail.next);
    System.out.println("(head)");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    CircularLinkedList list = new CircularLinkedList();
    int option;

    do {
      System.out.println("\n***** MAIN MENU *****");
      System.out.println("1: Create a list (by adding to end)");
      System.out.println("2: Display the list");
      System.out.println("3: Add a node at the beginning");
      System.out.println("4: Add a node at the end");
      System.out.println("5: Delete a node from the beginning");
      System.out.println("6: Delete a node from the end");
      System.out.println("7: Delete a node after a given node");
      System.out.println("8: Delete the entire list");
      System.out.println("9: EXIT");
      System.out.print("\nEnter your option : ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Enter number of elements to add: ");
          int count = scanner.nextInt();
          for (int i = 0; i < count; i++) {
            System.out.print("Enter data: ");
            list.addLast(scanner.nextInt());
          }
          System.out.println("CIRCULAR LINKED LIST CREATED");
          break;
        case 2:
          list.display();
          break;
        case 3:
          System.out.print("Enter data: ");
          list.addFirst(scanner.nextInt());
          break;
        case 4:
          System.out.print("Enter data: ");
          list.addLast(scanner.nextInt());
          break;
        case 5:
          try {
            System.out.println("Deleted: " + list.removeFirst());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 6:
          try {
            System.out.println("Deleted: " + list.removeLast());
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 7:
          System.out.print("Enter the value after which to delete: ");
          int val = scanner.nextInt();
          if (list.removeAfter(val)) {
            System.out.println("Node deleted.");
          } else {
            System.out.println("Node not found or cannot be deleted.");
          }
          break;
        case 8:
          list.clear();
          System.out.println("CIRCULAR LINKED LIST DELETED");
          break;
        case 9:
          break;
        default:
          System.out.println("Invalid option!");
      }
    } while (option != 9);
    scanner.close();
  }
}
