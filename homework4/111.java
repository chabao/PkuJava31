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
    public int minDepth(TreeNode root) {
        if(root == null)return 0;
        int depth = 1;
        //�������У�ʹ���Ľ����һ�����
        LinkedList<TreeNode> ldl = new LinkedList<>();
        ldl.addLast(root);
        TreeNode temp;
        //��¼��һ��Ľ����
        int last_count = 1;
        //��¼��һ��Ľ����
        int this_count = 0;
        while((temp = ldl.pollFirst()) != null){
            //���г�һ�����,��һ��Ľ������һ
            --last_count;
            //�˽ڵ�û���ӽ�㣬�Ѿ�����Ҷ�ӽ��
            if(temp.left == null && temp.right == null)return depth;
            //�������ӣ�����У��˲�������һ
            if(temp.left != null){
                ldl.addLast(temp.left);
                this_count += 1;
                
            }
            //�����Һ��ӣ�����У��˲�������һ
            if(temp.right != null){
                ldl.addLast(temp.right);
                this_count += 1;
            }
            //����˽��Ϊ��һ�����һ����㣬��ȼ�һ
            if(last_count == 0){
                last_count = this_count;
                this_count = 0;
                depth += 1;
            }
        }
        return depth;
    }
}