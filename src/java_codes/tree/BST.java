package java_codes.tree;

import java_codes.tree.utils.TreeNode;
import java.util.Scanner;

/**
 * Implementation of a Binary Search Tree (BST).
 *
 * <p>
 * A Binary Search Tree is a node-based binary tree data structure which has the
 * following
 * properties: - The left subtree of a node contains only nodes with keys lesser
 * than the node’s
 * key. - The right subtree of a node contains only nodes with keys greater than
 * the node’s key. -
 * The left and right subtree each must also be a binary search tree.
 *
 * <p>
 * Time Complexity (for a balanced tree): - Search: O(log n) - Insertion: O(log
 * n) - Deletion:
 * O(log n)
 *
 * <p>
 * Time Complexity (for a skewed tree - worst case): - All operations: O(n)
 *
 * <p>
 * Space Complexity: O(n) for storing n nodes. The recursion stack for
 * operations is O(h) where h
 * is the height.
 */
public class BST {

  private TreeNode root;

  public BST() {
    this.root = null;
  }

  /**
   * Public method to insert a new key into the BST.
   *
   * @param key The key to insert.
   */
  public void insert(int key) {
    root = insertRec(root, key);
  }

  /** A recursive function to insert a new key in BST. */
  private TreeNode insertRec(TreeNode root, int key) {
    // If the tree is empty, return a new node
    if (root == null) {
      root = new TreeNode(key);
      return root;
    }

    // Otherwise, recur down the tree
    if (key < root.getData()) {
      root.setLeft(insertRec(root.getLeft(), key));
    } else if (key > root.getData()) {
      root.setRight(insertRec(root.getRight(), key));
    }

    // return the (unchanged) node pointer
    return root;
  }

  /**
   * Public method to search for a key in the BST.
   *
   * @param key The key to search for.
   * @return true if the key is found, false otherwise.
   */
  public boolean search(int key) {
    return searchRec(root, key);
  }

  /** A recursive function to search for a key in BST. */
  private boolean searchRec(TreeNode root, int key) {
    // Base Cases: root is null or key is present at root
    if (root == null) {
      return false;
    }
    if (root.getData() == key) {
      return true;
    }

    // Key is greater than root's key
    if (root.getData() < key) {
      return searchRec(root.getRight(), key);
    }

    // Key is smaller than root's key
    return searchRec(root.getLeft(), key);
  }

  /** Public method for pre-order traversal of the BST. */
  public void preOrderTraversal() {
    preOrderRec(root);
  }

  private void preOrderRec(TreeNode root) {
    if (root != null) {
      System.out.print(root.getData() + " ");
      preOrderRec(root.getLeft());
      preOrderRec(root.getRight());
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BST bst = new BST();

    System.out.print("Enter the number of nodes to insert: ");
    int n = sc.nextInt();
    System.out.println("Enter the nodes:");
    for (int i = 0; i < n; i++) {
      bst.insert(sc.nextInt());
    }

    System.out.println("\nPre-order Traversal of the BST:");
    bst.preOrderTraversal();

    System.out.print("\n\nEnter the value to search for in the tree: ");
    int input = sc.nextInt();
    boolean result = bst.search(input);

    System.out.println(
        result
            ? "Given number is present in the tree."
            : "Given number is not present in the tree.");

    sc.close();
  }
}
