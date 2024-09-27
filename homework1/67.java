public class Solution {
    public String addBinary(String a, String b) {
        
        int length1=a.length();
		int length2=b.length();
		//int maxLength=(length1>length2)?length1:length2;
		//int[] sum=new int[maxLength];
        String sum="";
		int i=length1-1;
		int j=length2-1;
		//System.out.println(i);
		//System.out.println(j);
		int k=1;
		int jinwei=0; 
		int he=0;
		int x,y;
		while(i>=0||j>=0)
		{
			if(i>=0) x=a.charAt(i)-'0';else x=0;
			if(j>=0) y=b.charAt(j)-'0';else y=0;
			
			if(x==1&&y==1&&jinwei==1) {he=1;jinwei=1;}
			else if(x==1&&y==1&&jinwei==0) {he=0;jinwei=1;}
			else if(x==0&&y==0){he=jinwei;jinwei=0;}
			else if(jinwei==0){he=1;jinwei=0;}
			else if(jinwei==1){he=0;jinwei=1;}
		    //sum[maxLength-k]=he;
			 sum=(char)(he + '0')+sum;
			k++;
		    i--;
		    j--;
		    //System.out.print(x);
		    //System.out.println(y);
		    //System.out.print(he);
		    //System.out.println(jinwei);
		    //System.out.print(sum);
		 
		}
              
		    if(jinwei==1) sum=(char)(jinwei + '0')+sum;
		    return sum;
    }
}