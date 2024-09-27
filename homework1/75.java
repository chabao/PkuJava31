public class Solution {
    public void sortColors(int[] nums) {

        int n=nums.length;
		
		int i;
     	int left=0;
		int right=n-1;
			
		for(i=0;i<=right;)
		{
			if(nums[i]==2)
			{
				int temp=nums[right];
				nums[right]=nums[i];
				nums[i]=temp;
				right--;
			}
			
		else if(nums[i]==0)
			{
				int temp=nums[left];
				nums[left]=nums[i];
				nums[i]=temp;
				left++;
				i++;
			}
			
		else if(nums[i]==1)    //一开始缺了else就会有溢出
			{
				i++;
			}
		
				
		
		}
		for(int j=0;j<n;j++)
			System.out.print(nums[j]);
    }
}