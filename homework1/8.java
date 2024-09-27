public class Solution {
    public int myAtoi(String str) {
        
      
		int length=str.length();
		//int newstr=0;错误1，不该用int型存储结果
		long newstr=0;
		int result=0;
		
		int i,j;
		int flag1=0;
		int sign=0;
		
		
		long min=Integer.MIN_VALUE;
		long max=Integer.MAX_VALUE;
		//System.out.println(min*(-1));
		//错误2，int整型时的max和min，max+1会溢出，而不是|max+1|;min*(-1)会溢出
		 
		
		
		for(i=0;i<length-1;i++)	
		{  
		    if(str.charAt(i)==' ')  continue;                                //遇到空格时，继续遍历
		    
		    
		    else if(str.charAt(i)=='-'||str.charAt(i)=='+')
			{   if(str.charAt(i)=='-') sign=1;
		    	if(str.charAt(i+1)>='0'&&str.charAt(i+1)<='9')      //有+ -号的情况：后面必须直接为数字
			     { flag1=i+1;break;}
			    else 
			    {   result=(int)newstr;
			        return result; }}
			
		    
			else if(str.charAt(i)>='0'&&str.charAt(i)<='9')                  //无符号的情况，直接标记
				{
				    flag1=i;break;
				}
			else
			{
			        result=(int)newstr;
			        return result;
			        
			 }
		}
		
		
		
		if(i>=length) {result=(int)newstr;return result;}          //判断String中有没有数字或+ -号，如果没有返回0
		else
		 {
			for(j=flag1;j<length;j++)                                     //如果有，就把字符型转化为整型数字
		    {  			
			  if(str.charAt(j)>='0'&&str.charAt(j)<='9')
			  {
				  newstr=newstr*10+(str.charAt(j)-'0');
				
				  
				   
				  if((newstr>max)&&(sign==0)){newstr=max;break;}
				  else if(newstr>(max+1)&&sign==1){newstr=min*(-1);break;}  //超出最低值的范围
	    	  }
			  else break;
		    }
		    
			if(sign==1)newstr=newstr*(-1);                               //如果之前的符号是“-”，应该输出为负
			{result=(int)newstr;return result;}
			
		}
    }
}