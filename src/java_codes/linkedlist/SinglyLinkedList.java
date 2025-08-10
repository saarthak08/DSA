package java_codes.linkedlist;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Singly Linked List.
 *
 * <p>A singly linked list is a linear data structure where each element is a separate object. Each
 * element (a node) contains a value and a reference to the next node in the sequence.
 *
 * <p>Time Complexity: - Access: O(n) - Search: O(n) - Insertion (at beginning): O(1) - Insertion
 * (at end): O(n) - Can be O(1) if a tail pointer is maintained. - Deletion (at beginning): O(1) -
 * Deletion (at end): O(n)
 *
 * <p>Space Complexity: O(n) for storing n nodes.
 */
public class SinglyLinkedList {

  private Node head;
  private int size;

  private static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
    }
  }

  public SinglyLinkedList() {
    this.head = null;
    this.size = 0;
  }

  public void addFirst(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
    size++;
  }

  public void addLast(int data) {
    Node newNode = new Node(data);
    if (head == null) {
      head = newNode;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    size++;
  }

  public boolean insertAfter(int key, int data) {
    Node current = head;
    while (current != null && current.data != key) {
      current = current.next;
    }
    if (current != null) {
      Node newNode = new Node(data);
      newNode.next = current.next;
      current.next = newNode;
      size++;
      return true;
    }
    return false; // key not found
  }

  public int removeFirst() {
    if (head == null) throw new NoSuchElementException();
    int data = head.data;
    head = head.next;
    size--;
    return data;
  }

  public int removeLast() {
    if (head == null) throw new NoSuchElementException();
    if (head.next == null) {
      int data = head.data;
      head = null;
      size--;
      return data;
    }
    Node current = head;
    while (current.next.next != null) {
      current = current.next;
    }
    int data = current.next.data;
    current.next = null;
    size--;
    return data;
  }

  public boolean remove(int key) {
    if (head == null) return false;
    if (head.data == key) {
      head = head.next;
      size--;
      return true;
    }
    Node current = head;
    while (current.next != null && current.next.data != key) {
      current = current.next;
    }
    if (current.next != null) {
      current.next = current.next.next;
      size--;
      return true;
    }
    return false; // key not found
  }

  /**
   * Sorts the linked list using a bubble sort algorithm (O(n^2)). This method is inefficient for
   * large lists and is provided for demonstration. It sorts by swapping the data within the nodes.
   */
  public void sort() {
    if (head == null || head.next == null) return;

    boolean swapped;
    Node current;
    Node last = null;

    do {
      swapped = false;
      current = head;

      while (current.next != last) {
        if (current.data > current.next.data) {
          int temp = current.data;
          current.data = current.next.data;
          current.next.data = temp;
          swapped = true;
        }
        current = current.next;
      }
      last = current;
    } while (swapped);
  }

  public void clear() {
    head = null;
    size = 0;
  }

  public void display() {
    if (head == null) {
      System.out.println("List is empty.");
      return;
    }
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " -> ");
      current = current.next;
    }
    System.out.println("NULL");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    SinglyLinkedList list = new SinglyLinkedList();
    int option;

    do {
      System.out.println("\n\n *****MAIN MENU *****");
      System.out.println("1: Create a list");
      System.out.println("2: Display the list");
      System.out.println("3: Add a node at the beginning");
      System.out.println("4: Add a node at the end");
      System.out.println("5: Add a node after a given node");
      System.out.println("6: Delete a node from the beginning");
      System.out.println("7: Delete a node from the end");
      System.out.println("8: Delete a given node");
      System.out.println("9: Delete the entire list");
      System.out.println("10: Sort the list");
      System.out.println("11: EXIT");
      System.out.print("\nEnter your option : ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.print("Enter number of elements: ");
          int n = scanner.nextInt();
          for (int i = 0; i < n; i++) {
            System.out.print("Enter data: ");
            list.addLast(scanner.nextInt());
          }
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
          System.out.print("Enter value after which to insert: ");
          int key_a = scanner.nextInt();
          System.out.print("Enter data to insert: ");
          int data_a = scanner.nextInt();
          if (!list.insertAfter(key_a, data_a)) System.out.println("Node not found.");
          break;
        case 6:
          try {
            System.out.println("Deleted: " + list.removeFirst());
          } catch (NoSuchElementException e) {
            System.out.println("List is empty.");
          }
          break;
        case 7:
          try {
            System.out.println("Deleted: " + list.removeLast());
          } catch (NoSuchElementException e) {
            System.out.println("List is empty.");
          }
          break;
        case 8:
          System.out.print("Enter value to delete: ");
          int key_d = scanner.nextInt();
          if (!list.remove(key_d)) System.out.println("Node not found.");
          break;
        case 9:
          list.clear();
          System.out.println("List cleared.");
          break;
        case 10:
          list.sort();
          System.out.println("List sorted.");
          break;
        case 11:
          break;
        default:
          System.out.println("Invalid option.");
      }
    } while (option != 11);
    scanner.close();
  }
}
