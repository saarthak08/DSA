/*
 * A complete tree is a tree whose all leves are complete except the last level in which nodes should be filled
 * from left to right.
 *
 * A full binary tree is a tree whose nodes have either 0 or 2 children.
 *
 * A perfect binary tree is a tree in which all nodes have two children and all nodes are at same level.
 */

import java.util.ArrayDeque;
import java.util.Scanner;

public class Tree {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int input = 0;
    Node head = null;
    while (input != -1) {
      System.out.println(
        "\nEnter 1 to build the tree\nEnter 2 for pre order traversal\nEnter 3 for post order traversal\nEnter 4 for in order traversal\nEnter 5 for level order traversal\nEnter -1 to exit\n");

      input = sc.nextInt();
      switch (input) {
        case 1:
          head = new Node();
          buildTree(head);
          break;
        case 2:
          System.out.println("\nThe pre-order traversal of the tree: ");
          preOrderTraversal(head);
          break;
        case 3:
          System.out.println("\nThe post-order traversal of the tree: ");
          postOrderTraversal(head);
          break;
        case 4:
          System.out.println("\nThe in-order traversal of the tree: ");
          inOrderTraversal(head);
          break;
        case 5:
          System.out.println("\nThe level-order traversal of the tree: ");
          levelOrderTraversal(head);
          break;
        case -1:
          return;
        default:
          System.out.println("Error! Invalid input. Please try again.");
      }
    }
  }

  private static void buildTree(Node head) {
    if (head == null) {
      return;
    }
    System.out.println("Enter the value of node to be added: ");
    head.data = sc.nextInt();
    System.out.println("Enter 1 to enter the left child of " + head.data
      + ".\nEnter any other number to make the left child NULL");
    int input = sc.nextInt();
    if (input == 1) {
      head.left = new Node();
      buildTree(head.left);
    }
    System.out.println("Enter 1 to enter the right child of " + head.data
      + ".\nEnter any other number to make the right child NULL");
    input = sc.nextInt();
    if (input == 1) {
      head.right = new Node();
      buildTree(head.right);
    }
  }

  private static void preOrderTraversal(Node head) {
    if (head == null) {
      return;
    }
    System.out.print(head.data + "\t");
    preOrderTraversal(head.left);
    preOrderTraversal(head.right);
  }

  private static void postOrderTraversal(Node head) {
    if (head == null) {
      return;
    }
    postOrderTraversal(head.left);
    postOrderTraversal(head.right);
    System.out.print(head.data + "\t");
  }

  private static void inOrderTraversal(Node head) {
    if (head == null) {
      return;
    }
    inOrderTraversal(head.left);
    System.out.print(head.data + "\t");
    inOrderTraversal(head.right);
  }

  private static void levelOrderTraversal(Node head) {
    if (head == null) {
      return;
    }
    ArrayDeque<Node> queue = new ArrayDeque<Node>();
    queue.offer(head);
    while (!queue.isEmpty()) {
      Node dequeuedNode = queue.poll();
      System.out.print(dequeuedNode.data + "\t");
      if (dequeuedNode.left != null) {
        queue.offer(dequeuedNode.left);
      }
      if (dequeuedNode.right != null) {
        queue.offer(dequeuedNode.right);
      }
    }
  }
}

class Node {
  int data;
  Node left;
  Node right;
}
