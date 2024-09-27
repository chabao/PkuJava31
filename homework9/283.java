public class Solution {
    public void moveZeroes(int[] nums) {
       
       int length=nums.length;
		int i=0,j=0;
		
		
		for(;i<length;i++)
		{
			if(nums[i]!=0)continue;
			else 
				{
				 j=i+1;
				 while(j<length&&nums[j]==0){j++;}
				 if(j<length)
				 {
			      int temp;
				  temp=nums[i];
				  nums[i]=nums[j];
				  nums[j]=temp;
				 }
				}
			
		}


    }
}