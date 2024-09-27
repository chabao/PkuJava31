public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
	    List<List<Integer>> ll = new ArrayList<List<Integer>>();
		
		int length=nums.length;
		Arrays.sort(nums);
		
		
		int i;
	
		if(length<3) System.out.println("return l");//return l;
		
		/*
		int[] res = new int[3];
        res[0] = -1;
        res[1] = -1;
        res[2] = -1;
        */
        
        for (i=0;i<length-2;i++)
        { 
        	
        	/*if(nums[i]!=nums[i+1]){
        	
            HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
           
        	int target=nums[i];
        	
            for(j=i+1;j<length;j++)
        	{
                  	List<Integer> list = new ArrayList<Integer>();
		        		if (h.containsKey((-target)-nums[j]))
		        		{
		        			res[0]=i;
		        			res[1]=h.get((-target)-nums[j]);
		        			res[2]=j; 
		        			
		        	        list.add(nums[res[0]]);
		        	        list.add(nums[res[1]]);
		        	        list.add(nums[res[2]]);
		        	        l.add(list);
		        			
		                }
		        		else  h.put(nums[j], j);
            	
            }使用hashmap，可以得到所有的对，但是会导致顺序错误，所以不能使用*/

        	
        	 if (i==0||(i>0&&nums[i]!=nums[i-1]))//除去target重复的结果
        	                                     //必须是nums[i]!=nums[i-1]!!!!,不能是nums[i]!=nums[i+1]，因为这个会漏掉0,0,0
        	    {
			        	int target=-(nums[i]);
			        	
			        	int l=i+1;
			        	int r=length-1;
			        	
			        	while(l<r)
			        	{
			        		List<Integer> list = new ArrayList<Integer>();
			        		
			        		if(nums[l]+nums[r]==target)
			        		{
			        			list.add(nums[i]);
			        	        list.add(nums[l]);
			        	        list.add(nums[r]);
			        	        
			        	        ll.add(list);
			        	        while(l<r&&nums[l]==nums[l+1])l++;//l<r必须在==之前，否则会数组溢出
			        	        while(l<r&&nums[r]==nums[r-1])r--;//while(l<r&&nums[l]==nums[l-1])l++;
			        	                                          //while(l<r&&nums[r]==nums[r+1])r--;
 			        	                                          //这个会Runtime Error,也不行
 			        	        l++;r--;
			        		}
			        		else if(nums[l]+nums[r]>target)
			        		{
			        			
			        	        r--;
			        		}
			        		else if(nums[l]+nums[r]<target)
			        		{
			        			
			        	        l++;
			        		}
			        		
			        	}
        		
        	    }
		      
        	    
        	    
        	}     
            
        /*if(count>=3)
        {   List<Integer> list = new ArrayList<Integer>();
        	list.add(0);
	        list.add(0);
	        list.add(0);
	        
	        ll.add(list);
        }*/
            
        
      return ll;
       
    }
}