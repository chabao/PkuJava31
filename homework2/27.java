public class Solution {
    public int removeElement(int[] nums, int val) {
      //int length=nums.length;
		//int i;
		//int j=length-1;
	//	for(i=0;i<length;i++)
	//	{
			//if(nums[i]==val) {nums[i]=nums[j];j--;}
	//	}
	//	return j+1;
		
		
		int length=nums.length;
		int i=0;
		
		if(length<1) return 0;//只有一个数字在数组中
		
		int j=length-1;
		
		while(i<=j)
		{
			if(nums[i]==val)
			{
				while(nums[j]==val&&j>i) //	while(nums[j]==val)
				{j--;}
		     	nums[i]=nums[j];
		    	j--;
			}
			
			i++;
		}
		return j+1;
		
    }
}