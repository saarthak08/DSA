package java_codes.tree;

import java.util.Scanner;

/*

AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right subtrees cannot be more than one for all nodes.
AVL tree are used for reducing insertion deletion times to O(Logn) in every BST.
In normal BST, if tree is skew, then TC: O(n).

When performing an operation, four cases arise if AVL condition is not matched:
Re-balance the tree by performing appropriate rotations on the subtree rooted with z. There can be 4 possible cases that needs to be handled as x, y and z can be arranged in 4 ways. Following are the possible 4 arrangements:
a) y is left child of z and x is left child of y (Left Left Case)
b) y is left child of z and x is right child of y (Left Right Case)
c) y is right child of z and x is right child of y (Right Right Case)
d) y is right child of z and x is left child of y (Right Left Case)

Following are the operations to be performed in above mentioned 4 cases. In all of the cases, we only need to re-balance the subtree rooted with z and the complete tree becomes balanced as the height of subtree (After appropriate rotations) rooted with z becomes same as it was before insertion. (See this video lecture for proof)

a) Left Left Case

T1, T2, T3 and T4 are subtrees.
         z                                      y
        / \                                   /   \
       y   T4      Right Rotate (z)          x      z
      / \          - - - - - - - - ->      /  \    /  \
     x   T3                               T1  T2  T3  T4
    / \
  T1   T2
b) Left Right Case

     z                               z                           x
    / \                            /   \                        /  \
   y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
  / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
T1   x                          y    T3                    T1  T2 T3  T4
    / \                        / \
  T2   T3                    T1   T2
c) Right Right Case

  z                                y
 /  \                            /   \
T1   y     Left Rotate(z)       z      x
    /  \   - - - - - - - ->    / \    / \
   T2   x                     T1  T2 T3  T4
       / \
     T3  T4
d) Right Left Case

   z                            z                            x
  / \                          / \                          /  \
T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
    / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
   x   T4                      T2   y                  T1  T2  T3  T4
  / \                              /  \
T2   T3                           T3   T4

*/

public class AVLTree {

  private Node root;

  // Node class for the AVL Tree
  private static class Node {
    int key;
    int height;
    Node left, right;

    Node(int key) {
      this.key = key;
      this.height = 1; // New node is initially added at leaf
    }
  }

  // Public method to insert a key
  public void insert(int key) {
    root = insert(root, key);
  }

  // Get height of a node
  private int height(Node n) {
    return (n == null) ? 0 : n.height;
  }

  // Get balance factor of a node
  private int getBalance(Node n) {
    if (n == null) {
      return 0;
    }
    return height(n.left) - height(n.right);
  }

  // Right rotate subtree rooted with y
  private Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    // Perform rotation
    x.right = y;
    y.left = T2;

    // Update heights
    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    // Return new root
    return x;
  }

  // Left rotate subtree rooted with x
  private Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    // Perform rotation
    y.left = x;
    x.right = T2;

    // Update heights
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;

    // Return new root
    return y;
  }

  // Recursive function to insert a key
  private Node insert(Node node, int key) {
    // 1. Perform standard BST insertion
    if (node == null) {
      return (new Node(key));
    }

    if (key < node.key) {
      node.left = insert(node.left, key);
    } else if (key > node.key) {
      node.right = insert(node.right, key);
    } else { // Duplicate keys not allowed
      return node;
    }

    // 2. Update height of this ancestor node
    node.height = 1 + Math.max(height(node.left), height(node.right));

    // 3. Get the balance factor of this ancestor node
    int balance = getBalance(node);

    // 4. If the node becomes unbalanced, then there are 4 cases

    // Left Left Case
    if (balance > 1 && key < node.left.key) {
      return rightRotate(node);
    }

    // Right Right Case
    if (balance < -1 && key > node.right.key) {
      return leftRotate(node);
    }

    // Left Right Case
    if (balance > 1 && key > node.left.key) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && key < node.right.key) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    // return the (unchanged) node pointer
    return node;
  }

  // Pre-order traversal for testing
  public void preOrder() {
    preOrder(root);
  }

  private void preOrder(Node node) {
    if (node != null) {
      System.out.print(node.key + " ");
      preOrder(node.left);
      preOrder(node.right);
    }
  }

  public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    Scanner scanner = new Scanner(System.in);
    int x = 0;

    while (x != 3) {
      System.out.println("\n\n******* MENU *******");
      System.out.println("Press '1' to insert a node.");
      System.out.println("Press '2' to print pre-order traversal.");
      System.out.println("Press '3' to exit.");
      x = scanner.nextInt();

      switch (x) {
        case 1:
          System.out.print("Enter the node value to be inserted: ");
          int n = scanner.nextInt();
          tree.insert(n);
          break;
        case 2:
          System.out.println("\nPre-Order Traversal of the Tree:");
          tree.preOrder();
          System.out.println();
          break;
        case 3:
          break;
        default:
          System.out.println("Invalid Input. Try Again!");
          break;
      }
    }
    scanner.close();
  }
}
