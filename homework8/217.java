public class Solution {
    public boolean containsDuplicate(int[] nums) {
        
        int length=nums.length;
		int i;
		Arrays.sort(nums);
;		boolean flag=false;
		
		for(i=0;i<length;i++)
		{
			if(i<length-1&& nums[i]==nums[i+1])
			flag=true;
			
		}
		
          return flag;
    }
}