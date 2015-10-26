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
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> ldl = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        if(root == null)return result;
        TreeNode temp = root;
        while(temp != null || ldl.peekLast() != null){
            if(temp != null){
		//����������ȴ���������ջ
                result.add(new Integer(temp.val));
                ldl.addLast(temp);
		//����������
                temp = temp.left;
            }
            else{
		//��ջ��������������
                temp = ldl.pollLast();
                temp = temp.right;
            }
        }
        return result;
    }
}