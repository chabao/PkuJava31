/*
 *�ĸ�ָ���ǣ�����Ϊi,s,k,j
 */
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len<3)return result;
        Arrays.sort(nums);
        for(int i=0; i<len-3; i++){
            if(i!=0 && nums[i] == nums[i-1])continue;//ȥ���ظ���
            for(int j=len-1; j>=i+3; j--){
                if(j!=len-1 && nums[j] == nums[j+1])continue;//ȥ���ظ���
                int target_temp = target - nums[i] - nums[j];
                int s = i+1;
                int k = j-1;
                while(s<k){
                    //�ҵ����ϵ�Ԫ�أ�����result
                    if(nums[s]+nums[k] == target_temp){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[s]);
                        temp.add(nums[k]);
                        temp.add(nums[j]);
                        result.add(temp);
                        while(k>s && nums[s] == nums[s+1])s++;
                        while(k>s && nums[k] == nums[k-1])k--;
                        s++;
                        k--;
                    //�������target��k--
                    }else if(nums[s] +nums[k] >target_temp){
                        while(k>s && nums[k] == nums[k-1])k--;
                        k--;
                    //���С��target��s++
                    }else{
                        while(k>s && nums[s] == nums[s+1])s++;
                        s++;
                    }
                }
            }
        }
        return result;
    }
}