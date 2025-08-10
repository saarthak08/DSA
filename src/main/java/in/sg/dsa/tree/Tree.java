package in.sg.dsa.tree;

/*
 * A utility and demonstration class for basic Binary Tree operations. This class provides static
 * methods to build a tree from user input and perform various standard traversals.
 *
 * <p>Tree Definitions: - A complete tree is a tree whose all levels are complete except the last
 * level, in which TreeNodes should be filled from left to right. - A full binary tree is a tree
 * whose TreeNodes have either 0 or 2 children. - A perfect binary tree is a tree in which all
 * TreeNodes have two children and all leaf nodes are at the same level.
 */
import in.sg.dsa.tree.utils.TreeNode;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Tree {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int input = 0;
    TreeNode head = null;
    while (input != -1) {
      System.out.println(
          "\nEnter 1 to build the tree\nEnter 2 for pre order traversal\nEnter 3 for post order traversal\nEnter 4 for in order traversal\nEnter 5 for level order traversal\nEnter -1 to exit\n");

      input = sc.nextInt();
      switch (input) {
        case 1:
          head = new TreeNode();
          buildTree(head);
          break;
        case 2:
          System.out.println("\nThe pre-order traversal of the in.sg.dsa.tree: ");
          preOrderTraversal(head);
          break;
        case 3:
          System.out.println("\nThe post-order traversal of the in.sg.dsa.tree: ");
          postOrderTraversal(head);
          break;
        case 4:
          System.out.println("\nThe in-order traversal of the in.sg.dsa.tree: ");
          inOrderTraversal(head);
          break;
        case 5:
          System.out.println("\nThe level-order traversal of the in.sg.dsa.tree: ");
          levelOrderTraversal(head);
          break;
        case -1:
          return;
        default:
          System.out.println("Error! Invalid input. Please try again.");
      }
    }
  }

  private static void buildTree(TreeNode head) {
    if (head == null) {
      return;
    }
    System.out.println("Enter the value of TreeNode to be added: ");
    head.setData(sc.nextInt());
    System.out.println(
        "Enter 1 to enter the left child of "
            + head.getData()
            + ".\nEnter any other number to make the left child NULL");
    int input = sc.nextInt();
    if (input == 1) {
      head.setLeft(new TreeNode());
      buildTree(head.getLeft());
    }
    System.out.println(
        "Enter 1 to enter the right child of "
            + head.getData()
            + ".\nEnter any other number to make the right child NULL");
    input = sc.nextInt();
    if (input == 1) {
      head.setRight(new TreeNode());
      buildTree(head.getRight());
    }
  }

  private static void preOrderTraversal(TreeNode head) {
    if (head == null) {
      return;
    }
    System.out.print(head.getData() + "\t");
    preOrderTraversal(head.getLeft());
    preOrderTraversal(head.getRight());
  }

  private static void postOrderTraversal(TreeNode head) {
    if (head == null) {
      return;
    }
    postOrderTraversal(head.getLeft());
    postOrderTraversal(head.getRight());
    System.out.print(head.getData() + "\t");
  }

  private static void inOrderTraversal(TreeNode head) {
    if (head == null) {
      return;
    }
    inOrderTraversal(head.getLeft());
    System.out.print(head.getData() + "\t");
    inOrderTraversal(head.getRight());
  }

  private static void levelOrderTraversal(TreeNode head) {
    if (head == null) {
      return;
    }
    ArrayDeque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(head);
    while (!queue.isEmpty()) {
      TreeNode dequeuedTreeNode = queue.poll();
      System.out.print(dequeuedTreeNode.getData() + "\t");
      if (dequeuedTreeNode.getLeft() != null) {
        queue.offer(dequeuedTreeNode.getLeft());
      }
      if (dequeuedTreeNode.getRight() != null) {
        queue.offer(dequeuedTreeNode.getRight());
      }
    }
  }
}
