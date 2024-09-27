public class Solution {
    public String intToRoman(int num) {
        
        int i,j;
		String Integer=new String(); 
	    int B[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
	    String Roma[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	   
	    for(i=0;i<20;i++)
	     for(j=0;j<13;j++)
	        if((num/B[j])>0)
	        { 
	        	Integer=Integer+Roma[j];
	            num=num-B[j];
	            break;
	        }
       
        return Integer;  

    }
}