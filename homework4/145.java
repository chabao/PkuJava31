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
    public List<Integer> postorderTraversal(TreeNode root) {
        //����LinkedList��Ϊջ
        LinkedList<TreeNode> ldl = new LinkedList<>();
        //����HashMap����¼�ý���Ƿ��Ѿ����ʹ������ӽ��
        HashMap<TreeNode,Boolean> hm = new HashMap<>();
        List<Integer> result = new LinkedList<>();
        if(root == null)return result;
        TreeNode temp = root;
        //ջ�͵�ǰ��㶼Ϊ�գ�����
        while(temp != null || ldl.peekLast() != null){
            if(temp != null){
                //���û�з��ʹ��ý��
                if(!hm.containsKey(temp)){ 
                    //��ӵ�Map
                    hm.put(temp,false);
                    //��ջ
                    ldl.addLast(temp);
                    //����������
                    temp = temp.left;
                }else{
                    //���ʹ��ý��,����������
                    temp = temp.right;
                }
            }
            else{
                //�õ�ջ����㣬����ջ
                temp = ldl.peekLast();
                //���û�з�����������
                if(hm.get(temp) == false){
                    //����valueֵ��Ϊtrue��������������
                    hm.put(temp,true);
                    temp = temp.right;
                }else{
                    //������ʹ���������������ý���valueֵ
                    result.add(new Integer(temp.val));
                    //���ý���ջ��ȥ�������õ��µ�ջ����㣬����ջ
                    ldl.pollLast();
                    temp = ldl.peekLast(); 
                }
            }
        }
        return result;
    }
}