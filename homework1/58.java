public class Solution {
    public int lengthOfLastWord(String s) {
       int length=s.length();
		
		int i,j;
		int flag1=0;
		int flag2=0;
		
	if(length==0)return 0;
		
	else{	for(i=length-1;i>=0;i--)
		{  
			//if(s.charAt(i)==' ')flag1=i+1;
			//else flag2=1;
			
			if(s.charAt(i)!=' ')
			{
			    flag1=i;
				break; 
			    
			}
			
			
		}
		for(j=flag1;j>=0;j--)
		{  
			//if(s.charAt(i)==' ')flag1=i+1;
			//else flag2=1;
			
			if(s.charAt(j)==' ')
			{
			    flag2=j;
		    	break;
			    
			}
			
		}
		//if(flag2==0) System.out.println(0);
		//else if(flag1==0) System.out.println(length);
		//else System.out.println(length-flag1);
		
		if(i<0) return 0;
		else if(j<0) return(flag1+1);
		else return(flag1-flag2);
}
    }    
}