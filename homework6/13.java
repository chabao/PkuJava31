public class Solution {
    public int romanToInt(String s) {
      int i,j;
		//String s="MDCXCV"; 
		int length=s.length();
		int num=0;
	    int B[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
	    String Roma[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	   
	    for(i=0;i<length;i++)
	    {
	    	char s1,s2;
	    	 if(i<length-1)
	         {s1=s.charAt(i);
	          s2=s.charAt(i+1);}
	    	 else 
	    	 {s1=s.charAt(i);
	          s2='Q';}
	         
	    	for(j=0;j<13;j++)
	       {  
	    	 int l=Roma[j].length();
	         
			if(l==2)
	         {
	           char r1=Roma[j].charAt(0);
	           char r2=Roma[j].charAt(1);
	           if(s1==r1&&s2==r2)
	            {
	        	   num=num+B[j];
	               i++;
	               break;
	            }
	          }
 	         
	         else if(l==1)
	          {
	        	 char r1=Roma[j].charAt(0);
	        	 if(s1==r1)
	               { 
	        		 num=num+B[j];
	                 break;
	               }
	
	          }
	    
                     
   
	       }
	    }
	   
      return num;
     
    }
}