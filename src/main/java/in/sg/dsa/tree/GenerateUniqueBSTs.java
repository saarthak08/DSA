package in.sg.dsa.tree;

import in.sg.dsa.tree.utils.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generates all structurally unique Binary Search Trees (BSTs) for keys from 1 to n.
 *
 * <p>This problem is a classic example of dynamic programming and is related to Catalan numbers.
 * The number of unique BSTs for n keys is the n-th Catalan number.
 *
 * <p>Algorithm: The core idea is to iterate through each number `i` from 1 to `n` and consider it
 * as the root of a BST. When `i` is the root, all numbers from `1` to `i-1` must be in the left
 * subtree, and all numbers from `i+1` to `n` must be in the right subtree.
 *
 * <p>We recursively solve the problem for the left and right subtrees. The total number of unique
 * trees is the sum of possibilities for each root `i`.
 *
 * <p>To optimize, we use memoization (a top-down DP approach) to store the list of generated
 * subtrees for a given range of keys (e.g., from `start` to `end`), avoiding redundant
 * computations.
 *
 * <p>Time Complexity: O(n * C_n) where C_n is the n-th Catalan number. The complexity is high
 * because we are constructing all possible trees. The number of trees grows very quickly. Space
 * Complexity: O(n * C_n) to store all the generated unique BSTs.
 */
public class GenerateUniqueBSTs {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of keys (n): ");
    int n = sc.nextInt();

    if (n <= 0) {
      System.out.println("Please enter a positive number.");
      return;
    }

    List<TreeNode> allTrees = generateTrees(n);

    System.out.println(
        "\nAll " + allTrees.size() + " Possible Binary Search Trees (Pre-order Traversal):");
    int count = 1;
    for (TreeNode root : allTrees) {
      System.out.print(count++ + ": ");
      preorderTraversal(root);
      System.out.println();
    }
    sc.close();
  }

  /** Public method to start the generation of trees for keys 1 to n. */
  public static List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    // DP table to store results of subproblems
    List<TreeNode>[][] dp = new List[n + 1][n + 1];
    return generateTreesRec(1, n, dp);
  }

  /** Recursive helper function with memoization. */
  private static List<TreeNode> generateTreesRec(int start, int end, List<TreeNode>[][] dp) {
    List<TreeNode> trees = new ArrayList<>();

    // Base case: if start > end, the subtree is empty (represented by null)
    if (start > end) {
      trees.add(null);
      return trees;
    }

    // If the result for this subproblem is already computed, return it
    if (dp[start][end] != null) {
      return dp[start][end];
    }

    // Iterate through all numbers from start to end to use as the root
    for (int i = start; i <= end; i++) {
      // Recursively generate all possible left subtrees
      List<TreeNode> leftSubtrees = generateTreesRec(start, i - 1, dp);
      // Recursively generate all possible right subtrees
      List<TreeNode> rightSubtrees = generateTreesRec(i + 1, end, dp);

      // Combine each left subtree with each right subtree
      for (TreeNode left : leftSubtrees) {
        for (TreeNode right : rightSubtrees) {
          TreeNode root = new TreeNode(i);
          root.setLeft(left);
          root.setRight(right);
          trees.add(root);
        }
      }
    }

    // Memoize the result
    dp[start][end] = trees;
    return trees;
  }

  /** Utility function to print pre-order traversal of a tree. */
  private static void preorderTraversal(TreeNode root) {
    if (root != null) {
      System.out.print(root.getData() + " ");
      preorderTraversal(root.getLeft());
      preorderTraversal(root.getRight());
    }
  }
}
