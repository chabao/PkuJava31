public class Solution {
    public int climbStairs(int n) {
        
        if(n<=3) return n;
        else{ 
                int f1=1,f2=2;int temp;
                for(int i=0;i<n-2;i++)
                {
                    temp=f2;
                    f2=f2+f1;
                    f1=temp;
                    
                }
               return f2; 
        }
    }
}