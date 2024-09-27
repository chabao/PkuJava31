import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
 
public class Wordcount {

	public static void main(String[] args) {
		try {
			File filea = new File("F:\\A.txt");
			File fileb = new File("F:\\B.txt");
			
			String s1=null,s2=null;
			//分别记录文件A和文件B中的单词总数
			int counta = 0 , countb = 0;
			//记录文件A和文件B中单词交集的个数，并集的个数
			int cj = 0 , cb = 0;
			
			BufferedReader br1 = new BufferedReader(new FileReader(filea));
			BufferedReader br2 = new BufferedReader(new FileReader(fileb));
			
			
             //StringBuffer bu1 = new StringBuffer();
			 
             //StringBuffer bu2 = new StringBuffer();
			 String bu1 = new String();
			 String bu2 = new String();
             
             while ((s1 = br1.readLine()) != null) {
                     bu1=bu1+s1;
                
             }
             while ((s2 = br2.readLine()) != null) {
            	 bu2=bu2+s2;
             }
             
        
				
			//用hashset集合来记录文件A,B的并集
			HashSet<String> hs = new HashSet<>();
			//用hashmap集合来记录文件A,B的交集
			HashMap<String, Boolean> hm = new HashMap<>();
				
				
				StringTokenizer st1 = new StringTokenizer(bu1,",.!() \t\f\r");
				StringTokenizer st2 = new StringTokenizer(bu2,",.!() \t\f\r");
		
	             while (st1.hasMoreTokens()) {
	                 String word = st1.nextToken();
	                 
	                 if (!hs.contains(word)) {
							hs.add(word);
							
							hm.put(word, false);
						}
						++counta;
	             }
	             
	             while (st2.hasMoreTokens()) {
	                 String word = st2.nextToken();
	                 
	                 if (!hs.contains(word)) {
							hs.add(word);
							
						  } 
	                 else {
							hm.put(word, true);
						  }
						++countb;
	             }

	            
	            
	            System.out.print("文件A和B的并集是：");
			    Iterator<String> it1 = hs.iterator();	
				while (it1.hasNext()) {
					System.out.print(it1.next() + " ");
					++cb;
				}
				System.out.println();
				System.out.println("文件A和B的并集一共有" + cb + "个单词");
				System.out.println("------------------------------");
 
				Iterator<String> it2 = hm.keySet().iterator();
				System.out.print("文件A和B的交集是：");
				String word=null;
				while (it2.hasNext()) {
					if (hm.get(word= it2.next())) {
						System.out.print(word + " ");
					
							++cj;
					}
				}
				System.out.println();
				System.out.println("文件A和B的交集一共有" + cj + "个单词");
				System.out.println("------------------------------");
				
				System.out.println("文件A总共有" + counta + "个单词。文件B总共有" + countb +"个单词");
	            System.out.println("------------------------------");
	            double r1=((counta - cj)*1.0/counta)*100,r2=((countb-cj)*1.0/countb)*100;
			    System.out.println("文件A的特有单词占A的单词总数的百分比为"+String.format("%1$.2f", r1)+ "%，文件B的特有单词占B的单词总数的百分比为" + String.format("%1$.2f", r2)+"%"); 
				
			    br1.close();
				br2.close();
		 
     } catch (FileNotFoundException e) {
        System.out.println("文件未找到~！");
     } catch (IOException e) {
        System.out.p
rintln("文件读异常~！");
     }
		
		
	}

}
