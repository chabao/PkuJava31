public class Solution {
    public int titleToNumber(String s) {
        int len = s.length();
        if(s == null || len == 0)return 0;
        double column = 0;
        //����ʵ����26���ƣ��Ӹ�λ��ʼһ���ۼӼ���
        for(int i=len-1; i>=0; i--){
            column += (s.charAt(i) - 'A' + 1) * Math.pow(26.0,len-1.0-i);
        }
        return (int)column;
    }
}