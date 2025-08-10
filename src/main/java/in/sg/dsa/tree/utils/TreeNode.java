package in.sg.dsa.tree.utils;

/*
 * Represents a node in a binary tree. Each node contains an integer data value and references to
 * its left and right children. Implements Comparable to allow nodes to be compared based on their
 * data value.
 */
import java.util.Objects;

public class TreeNode implements Comparable<TreeNode> {
  private int data;
  private TreeNode left;
  private TreeNode right;

  public TreeNode(int data) {
    this.data = data;
  }

  public TreeNode() {}

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public TreeNode getLeft() {
    return left;
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public TreeNode getRight() {
    return right;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    TreeNode treeNode = (TreeNode) o;
    return data == treeNode.data
        && Objects.equals(left, treeNode.left)
        && Objects.equals(right, treeNode.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, left, right);
  }

  @Override
  public String toString() {
    return "TreeNode{" + "data=" + data + ", left=" + left + ", right=" + right + '}';
  }

  @Override
  public int compareTo(TreeNode that) {
    return this.data - that.data;
  }
}
