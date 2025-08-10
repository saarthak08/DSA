package java_codes.tree;

import java_codes.tree.utils.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generates all structurally unique Binary Trees given an in-order traversal
 * sequence.
 *
 * <p>
 * For a sequence from 1 to n, this problem is equivalent to generating all
 * unique Binary Search
 * Trees (BSTs), as the in-order traversal of such a BST is always 1...n. The
 * number of such unique
 * trees is the n-th Catalan number.
 *
 * <p>
 * Algorithm: The core idea is to iterate through each element `inorder[i]` and
 * consider it as
 * the root. When `inorder[i]` is the root, all elements in the in-order
 * traversal before it must
 * form the left subtree, and all elements after it must form the right subtree.
 *
 * <p>
 * We recursively solve the problem for the left and right sub-arrays to
 * generate all possible
 * left and right subtrees, and then combine them under the current root.
 *
 * <p>
 * Time Complexity: O(n * C_n) where C_n is the n-th Catalan number. The
 * complexity is high
 * because we are constructing all possible trees. Space Complexity: O(n * C_n)
 * to store all the
 * generated unique trees.
 */
public class BinaryTreeFromInorderTraversal {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of nodes (n): ");
    int n = sc.nextInt();

    if (n <= 0) {
      System.out.println("Please enter a positive number.");
      sc.close();
      return;
    }

    // Create an in-order traversal array from 1 to n
    int[] inorder = new int[n];
    for (int i = 0; i < n; i++) {
      inorder[i] = i + 1;
    }

    List<TreeNode> trees = getTrees(inorder, 0, n - 1);

    System.out.println("\nPre-order traversals of all " + trees.size() + " possible binary trees:");
    int count = 1;
    for (TreeNode root : trees) {
      System.out.print(count++ + ": ");
      preOrderTraversal(root);
      System.out.println();
    }
    sc.close();
  }

  /**
   * Recursively generates all unique binary trees for the given in-order
   * subarray.
   *
   * @param inorder The array of in-order keys.
   * @param start   The start index of the subarray.
   * @param end     The end index of the subarray.
   * @return A list of root nodes for all possible unique trees.
   */
  public static List<TreeNode> getTrees(int[] inorder, int start, int end) {
    List<TreeNode> trees = new ArrayList<>();

    if (start > end) {
      trees.add(null);
      return trees;
    }

    for (int i = start; i <= end; i++) {
      // Generate all possible left and right subtrees
      List<TreeNode> leftTrees = getTrees(inorder, start, i - 1);
      List<TreeNode> rightTrees = getTrees(inorder, i + 1, end);

      // Combine them under the current root
      for (TreeNode left : leftTrees) {
        for (TreeNode right : rightTrees) {
          TreeNode root = new TreeNode(inorder[i]);
          root.setLeft(left);
          root.setRight(right);
          trees.add(root);
        }
      }
    }
    return trees;
  }

  /** Utility function to print pre-order traversal of a tree. */
  public static void preOrderTraversal(TreeNode root) {
    if (root != null) {
      System.out.print(root.getData() + " ");
      preOrderTraversal(root.getLeft());
      preOrderTraversal(root.getRight());
    }
  }
}
