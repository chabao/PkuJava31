public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            //��λΪ1����ôn&1����1
            if((n&1) == 1) ++count;
            //�޷�������
            n >>>= 1;
        }
        return count;
    }
}