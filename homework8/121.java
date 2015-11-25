public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0)return 0;
        //��¼��i����ǰ����ͼ�
        int minPrice = prices[0];
        //��¼�������
        int maxProfit = 0;
        for(int i=1; i<len; i++){
            //������ͼ�
            if(prices[i-1] < minPrice){
                minPrice = prices[i-1];
            }
            //�����������
            if(maxProfit < prices[i] - minPrice){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
