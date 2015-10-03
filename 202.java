public class Solution {
    public boolean isHappy(int n) {
        if(n <= 0)return false;
        if(n == 1)return true;
        int sum = 0;
        //����set��¼ѭ���г��ֵ���
        HashSet<Integer> hs = new HashSet<Integer>();
        while(true){
            sum = squareSum(n);
            if(sum == 1)return true;
            //���ƽ�����ظ������ˣ������false
            else if(hs.contains(sum))return false;
            hs.add(sum);
            n = sum;
        }
    }
    //����ƽ����
    public int squareSum(int n){
        int result = 0;
        int digits = 0;
        while(n != 0){
            digits = n%10;
            n /= 10;
            result += (digits*digits);
        }
        return result;
    }
}