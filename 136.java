public class Solution {
    public int singleNumber(int[] nums) {
        //a^b = b^a, a^a =0,0^a=a,���Խ�����Ԫ��ȫ������������
        //������ʣ�µ�����Ԫ�ء�
        int elem = 0;
        for(int i=0; i<nums.length; i++){
            elem = elem ^ nums[i];
        }
        return elem;
    }
}