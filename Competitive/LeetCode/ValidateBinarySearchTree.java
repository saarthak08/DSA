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

/*
My Solution below isn't optimal. The more optimal solution can be found on LeetCode.


*/
class Solution {
    public boolean isValidBST(TreeNode root) {
       if(root!=null) {
           boolean right=isValidBST(root.right);
           if(!right) {
               return right;
           }
           boolean left=isValidBST(root.left);
           if(!left) {
               return left;
           }
           if(root.right!=null){
              right=validateNode(root.right,root.val,false);
               if(!right) {
                    return right;
                }
           } 
           if(root.left!=null) {
               left=validateNode(root.left,root.val,true);
               if(!left) {
                    return left;
                }
           }
       }
        return true;
    }
    
    
    public boolean validateNode(TreeNode root,int val,boolean left) {
        if(root!=null) {
            boolean x=validateNode(root.left,val,left);
            if(!x) {
                return x;
            }
            x=validateNode(root.right,val,left);
            if(!x) {
                return x;
            }
            if(left) {
                if(root.val<val) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if(root.val>val) {
                    return true;
                } else {
                    return false;
                }            
            }
        } 
        return true;
    }
}