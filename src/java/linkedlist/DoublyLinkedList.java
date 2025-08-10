package java.linkedlist;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implementation of a Doubly Linked List.
 *
 * <p>A doubly linked list is a linked data structure that consists of a set of sequentially linked
 * records called nodes. Each node contains three fields: two link fields (references to the
 * previous and to the next node in the sequence of nodes) and one data field.
 *
 * <p>Time Complexity: - Access: O(n) - Search: O(n) - Insertion (at beginning or end): O(1) -
 * Insertion (at middle): O(n) - Deletion (at beginning or end): O(1) - Deletion (at middle): O(n)
 *
 * <p>Space Complexity: O(n) for storing n nodes.
 */
public class DoublyLinkedList {

  private Node head;
  private Node tail;
  private int size;

  private static class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
      this.data = data;
    }
  }

  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void addFirst(int data) {
    Node newNode = new Node(data);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  public void addLast(int data) {
    Node newNode = new Node(data);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  public boolean insertAfter(int key, int data) {
    Node current = head;
    while (current != null && current.data != key) {
      current = current.next;
    }

    if (current == null) return false; // key not found
    if (current == tail) {
      addLast(data);
    } else {
      Node newNode = new Node(data);
      newNode.next = current.next;
      newNode.prev = current;
      current.next.prev = newNode;
      current.next = newNode;
      size++;
    }
    return true;
  }

  public boolean insertBefore(int key, int data) {
    Node current = head;
    while (current != null && current.data != key) {
      current = current.next;
    }

    if (current == null) return false; // key not found
    if (current == head) {
      addFirst(data);
    } else {
      Node newNode = new Node(data);
      newNode.prev = current.prev;
      newNode.next = current;
      current.prev.next = newNode;
      current.prev = newNode;
      size++;
    }
    return true;
  }

  public int removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();
    int data = head.data;
    head = head.next;
    if (head == null) {
      tail = null;
    } else {
      head.prev = null;
    }
    size--;
    return data;
  }

  public int removeLast() {
    if (isEmpty()) throw new NoSuchElementException();
    int data = tail.data;
    tail = tail.prev;
    if (tail == null) {
      head = null;
    } else {
      tail.next = null;
    }
    size--;
    return data;
  }

  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  public void display() {
    if (isEmpty()) {
      System.out.println("List is empty.");
      return;
    }
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " <-> ");
      current = current.next;
    }
    System.out.println("NULL");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    DoublyLinkedList list = new DoublyLinkedList();
    int option;

    do {
      System.out.println("\n\n *****MAIN MENU *****");
      System.out.println("1: Create a list");
      System.out.println("2: Display the list");
      System.out.println("3: Add a node at the beginning");
      System.out.println("4: Add a node at the end");
      System.out.println("5: Add a node before a given node");
      System.out.println("6: Add a node after a given node");
      System.out.println("7: Delete a node from the beginning");
      System.out.println("8: Delete a node from the end");
      System.out.println("9: Delete the entire list");
      System.out.println("10: EXIT");
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
          System.out.print("Enter value before which to insert: ");
          int key_b = scanner.nextInt();
          System.out.print("Enter data to insert: ");
          int data_b = scanner.nextInt();
          if (!list.insertBefore(key_b, data_b)) System.out.println("Node not found.");
          break;
        case 6:
          System.out.print("Enter value after which to insert: ");
          int key_a = scanner.nextInt();
          System.out.print("Enter data to insert: ");
          int data_a = scanner.nextInt();
          if (!list.insertAfter(key_a, data_a)) System.out.println("Node not found.");
          break;
        case 7:
          try {
            System.out.println("Deleted: " + list.removeFirst());
          } catch (NoSuchElementException e) {
            System.out.println("List is empty.");
          }
          break;
        case 8:
          try {
            System.out.println("Deleted: " + list.removeLast());
          } catch (NoSuchElementException e) {
            System.out.println("List is empty.");
          }
          break;
        case 9:
          list.clear();
          System.out.println("List cleared.");
          break;
        case 10:
          break;
        default:
          System.out.println("Invalid option.");
      }
    } while (option != 10);
    scanner.close();
  }
}
