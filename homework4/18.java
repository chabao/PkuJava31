public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
     
       List<List<Integer>> ll = new ArrayList<List<Integer>>();
			
			int length=nums.length;
			int i,j;
		    if(length<4) return ll;
		    else Arrays.sort(nums);
			
			
	        for (j=0;j<length-3;j++)
	        {     
	        	if(j>0&&nums[j]==nums[j-1])continue;
	        	for(i=j+1;i<nums.length-2;i++)
	        	{
	                 if (i>j+1&&nums[i]==nums[i-1])continue;
		        	    
					        	int sum=target-(nums[j]+nums[i]);
					        	
					        	int l=i+1;
					        	int r=length-1;
					        	
					        	while(l<r)
					        	{
					        		List<Integer> list = new ArrayList<Integer>();
					        		
					        		if(nums[l]+nums[r]==sum)
					        		{
					        			list.add(nums[j]);
					        			list.add(nums[i]);
					        	        list.add(nums[l]);
					        	        list.add(nums[r]);
					        	        
					        	        ll.add(list);
					        	        while(l<r&&nums[l]==nums[l+1])l++;
					        	        while(l<r&&nums[r]==nums[r-1])r--;
					        	                                          
		 			        	                                         
		 			        	        l++;r--;
					        		}
					        		else if(nums[l]+nums[r]>sum)
					        		{
					        			
					        	        r--;
					        		}
					        		else if(nums[l]+nums[r]<sum)
					        		{
					        			
					        	        l++;
					        		}
					        		
					        	}
		        		
		        	    
			      
	        	 }  
	        	    
	        	}     
	            
	   return ll;
    }
}