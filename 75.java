public class Solution {
    
	public void sortColors(int[] nums) {
       
	       if(nums == null || nums.length == 0 || nums.length == 1)return;

       	       int n = nums.length;

      	       int left = 0;//leftָ���˵�һ��1��λ��

      	       int right = n - 1;//rightָ�����ұߵ�һ����2��λ��

      	       int i=0;

      	       while(i <= right){

           		if(nums[i] == 0){

               			swap(nums,left,i);

               			left++;

               			i++;

           		}

          		 else if(nums[i] == 1)i++;

          		 else if(nums[i] == 2){

              		 	swap(nums,i,right);

              		 	right--;

           		}

       		}

    	}
    
	public void swap(int[] nums,int i,int j){

        
        int temp = nums[i];

        	nums[i] = nums[j];

        	nums[j] = temp;

    	}
}