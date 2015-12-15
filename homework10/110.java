public class Solution {
    public boolean isBalanced(TreeNode root) {
        //ά��һ�����У�ÿ���ڵ�����У��ж�ÿ���ڵ��ƽ����
        LinkedList<TreeNode> ll = new LinkedList<>();
        if(root != null)ll.add(root);
        while(!ll.isEmpty()){
            TreeNode temp = ll.pollFirst();
            if(temp.left != null){
                ll.add(temp.left);
            }
            if(temp.right != null){
                ll.add(temp.right);
            }
            if(Math.abs(depth(temp.left) - depth(temp.right)) > 1){
                return false;
            }
        }
        return true;
    }
    //��ý������
    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        return Math.max(depth(node.left),depth(node.right)) + 1;
    }
}