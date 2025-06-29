import java.util.Scanner;

public class BST {
  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of nodes in the tree: ");
    int n = sc.nextInt();
    int[] nodes = new int[n];
    System.out.println("Enter the nodes of the tree: ");
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = sc.nextInt();
    }
    TreeNode head = createTree(nodes);
    System.out.println("Pre Order Traversal of the tree: ");
    preOrderTraversal(head);
    System.out.println("\nEnter the value to search for in the tree: ");
    int input = sc.nextInt();
    boolean result = searchInTree(head, input);
    System.out
      .println(result ? "Given number is present in the tree." : "Given number is not present in the tree.");
  }

  private static TreeNode createTree(int[] arr) {
    if (arr.length == 0) {
      return null;
    }
    TreeNode head = new TreeNode(arr[0]);
    for (int i = 1; i < arr.length; i++) {
      addNodeToTree(head, arr[i]);
    }
    return head;
  }

  private static void addNodeToTree(TreeNode head, int data) {
    TreeNode current = head;
    TreeNode preCurrent = null;
    while (current != null) {
      if (data >= current.data) {
        preCurrent = current;
        current = current.right;
      } else {
        preCurrent = current;
        current = current.left;
      }
    }
    if (data >= preCurrent.data) {
      preCurrent.right = new TreeNode(data);
    } else {
      preCurrent.left = new TreeNode(data);
    }
  }

  private static void preOrderTraversal(TreeNode head) {
    if (head == null) {
      return;
    }
    System.out.print(head.data + "\t");
    preOrderTraversal(head.left);
    preOrderTraversal(head.right);
  }

  private static boolean searchInTree(TreeNode head, int input) {
    if (head == null) {
      return false;
    }
    if (input == head.data) {
      return true;
    }
    if (input >= head.data) {
      return searchInTree(head.right, input);
    } else {
      return searchInTree(head.left, input);
    }
  }
}

class TreeNode {
  int data;
  TreeNode left;
  TreeNode right;

  public TreeNode(int data) {
    this.data = data;
  }
}
