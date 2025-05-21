/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
  public ListNode reverseList(ListNode head) {
    ListNode temp = head;
    if (head == null) {
      return null;
    }
    while (temp.next != null) {
      temp = temp.next;
    }
    reverse(head);
    return temp;
  }

  public ListNode reverse(ListNode node) {
    if (node != null) {
      ListNode node1 = reverse(node.next);
      if (node1 != null) {
        node1.next = node;
        node.next = null;
      }
    }
    return node;
  }
}