/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0,val;
        ListNode node,resultHead;
        node=new ListNode(0);
        resultHead=node;
        while(l1!=null||l2!=null){
            if(l2==null&&l1!=null){
                val=l1.val+carry;
                l1=l1.next;
            }
            else if(l2!=null&&l1==null){
                val=l2.val+carry;
                l2=l2.next;
            }
            else{
                val=l1.val+l2.val+carry;
                l1=l1.next;
                l2=l2.next;
            }
            if(val>9){
                val=val%10;
                carry=1;
            }
            else{
                carry=0;
            }
            node.next =new ListNode(val);
            node=node.next;
        }
        if(carry==1){
            node.next=new ListNode(1);
            node=node.next;
        }
        return resultHead.next;
    }
}