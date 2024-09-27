public class Solution {
    public int addDigits(int num) {
        //int digitroot;
        //digitroot=(num-1)%9+1;
        //return digitroot;
        
		
		int sum=0;
		while(num>=10)
		{  
			while(num!=0)
			{   
				sum=num%10+sum;
				num=num/10;
				
			}
			
			num=sum;
			sum=0;
			
		}
	return num;
    }
}