public class Solution {
    public int addDigits(int num) {
        //��Ϊ��1�������numֵ�������˹���������ظ���1-9.����mod 9����
        //ֻ��õ�0-8������num-1�����mod 9���㣬Ȼ���1
        return (num-1)%9 + 1;
    }
}