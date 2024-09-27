public class Solution {
    public int removeDuplicates(int[] nums) {
      
        int length=nums.length;
		int i=0,j=0;
		int count=0;
		
		if(length<=1){return length;}
		else{
		while(i<length)
		{
			j++;
			
			while(j<length&&nums[i]>=nums[j])
			{
				j++;
				
			}
		
			if(j==length&&nums[i]==nums[j-1])break;
			if(j<length&&(j>i+1))
			{
				nums[i+1]=nums[j];
				
			}
			
			
			i++;
			count++;
		
			
		}
		}
		 
			    return count+1;
		
		 
	
        
    }
}
