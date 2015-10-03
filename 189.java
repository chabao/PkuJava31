public class Solution {
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0)return;
        int n = nums.length;
        k = k%n;
        if(k == 0)return;
	/*
	*�Ƚ��������嵹��
	*�ٽ�ǰK��Ԫ�ص���
	*Ȼ�󽫺���ĵ���
	*/	
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }
    public void reverse(int[] nums,int left,int right){
        int temp = 0;
        while(left < right){
            temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] =temp;
        }
    }
}