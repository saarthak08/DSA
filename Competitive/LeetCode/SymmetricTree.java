/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root!=null) {
            return isMirror(root.left,root.right);
        }
        return true;
    }
    
    public boolean isMirror(TreeNode p, TreeNode q) {
        if(p!=null&&q!=null) {
            return (p.val==q.val) && isMirror(p.left,q.right) && isMirror(p.right,q.left);
        } else if(p==null&&q==null) {
            return true;
        } else {
            return false;
        }
    }
}