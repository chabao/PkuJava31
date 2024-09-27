/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        int length;
        //if(root!=null)
        //length=Math.max(maxDepth(root.left),maxDepth(root.right));
        //else length=0;
        //return length;
        if (root == null) return 0;
        else if (root.right != null||root.left != null) 
         { length = Math.max(maxDepth(root.left),maxDepth(root.right));
           return (length+1); }
           else return 1;
    }
}