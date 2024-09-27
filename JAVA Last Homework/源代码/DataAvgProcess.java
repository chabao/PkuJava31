import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class DataProcess {

	// house pri data map
	static Map<String, Integer> mHousePri = new HashMap<String, Integer>();
	static Map<String, Integer> mAeraNum = new HashMap<String, Integer>();
	static Map<String, Double> mAveHousePri = new HashMap<String, Double>();
	// salary data map
	static Map<String, Double> mSalary = new HashMap<String, Double>();
	static Map<String, Integer> mPosionNum = new HashMap<String, Integer>();
	static Map<String, Double> mAveSalary = new HashMap<String, Double>();
	
	//
	static Map<String, Double> mSalary2Square = new HashMap<String, Double>();
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		String fileName = "E://Test//houseprice.csv";
		csvhousepri2Average( fileName );
		
		String salaryFileName = "E://Test//工资.csv";
		csvsalary2Average( salaryFileName );
		
		String yearSalarytoSquare = "E://Test//yearSalarytoSquare.csv";
		yearSalarytoSquare(yearSalarytoSquare);
		
		System.out.println("Process Success!");
	}
	
	public static void yearSalarytoSquare(String strFileName) throws Exception {
		// 结果(每年工资能买几平米)写文件: strFileName
		try{
			CsvWriter csvWt = new CsvWriter(strFileName, ',', Charset.forName("UTF-8") );
			
			Set<String> set = mSalary.keySet();
			for( String str : set){
				
				if( mHousePri.containsKey( str )){
					double yearSalary = (mSalary.get(str) / mPosionNum.get(str)) * 1000 * 12;
					double housePri = mHousePri.get(str) / mAeraNum.get(str);
					
					mSalary2Square.put(str,  yearSalary / housePri);
				}
				else
					continue;
			} // end of for

			//Map<String, Double> 排序
			mSalary2Square = sortMapByValue(mSalary2Square);
			
			set = mSalary2Square.keySet();
			
			for( String str : set){
				
				csvWt.write( str );
				
				csvWt.write( String.valueOf( new java.text.DecimalFormat("#.00").format( mSalary2Square.get(str).doubleValue() ) ) 
						+ "㎡");
				
				csvWt.endRecord();
			} // end of for
			
			csvWt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		
	}
	
	public static double getAverageofRange(String strRange){
		int i;
		int nlowNum = 0;
		int nhighNum = 0;
		
		for(i=0; i<strRange.length(); i++)
		{
			if( Character.isDigit(strRange.charAt(i)) ){
				nlowNum = nlowNum * 10;
				nlowNum += (strRange.charAt(i) - '0');
			}
			else
				break;
		}
		for(i=i+2; i<strRange.length(); i++){
			if(  Character.isDigit(strRange.charAt(i)) ){
				nhighNum = nhighNum * 10;
				nhighNum += (strRange.charAt(i) - '0');
			}
		}
		
		return (double)(nhighNum+nlowNum) / 2;
	}
	
	public static void csvsalary2Average(String strFileName) throws Exception {
		
		new File(strFileName).createNewFile();
		
		try{
			CsvReader csvRd = new CsvReader(strFileName, ',', Charset.forName("GBK"));
			CsvWriter csvWt = new CsvWriter("E:\\Test\\salaryAve.csv", ',', Charset.forName("UTF-8"));
			
			//csvRd.readHeaders();
			
			//逐条读取数据直到读完
			while( csvRd.readRecord() ){	
				// 读取城市在map中
				if( mSalary.containsKey(csvRd.get(0)) && mPosionNum.containsKey(csvRd.get(0)) ){  
					double nSalary = mSalary.get( csvRd.get(0) ); 
					int nNum = mPosionNum.get( csvRd.get(0) );
					
					nSalary += getAverageofRange( csvRd.get(1) );
					
					mSalary.put(csvRd.get(0), nSalary);
					mPosionNum.put( csvRd.get(0), nNum+1);
				}
				else{
					mSalary.put( csvRd.get(0), getAverageofRange(csvRd.get(1)) );
					mPosionNum.put( csvRd.get(0), 1 );
				}
			} // end of while
			
			// Average Salary 写Map
			Set<String> set = mSalary.keySet();
			for( String str : set){
				mAveSalary.put( str, mSalary.get(str).doubleValue() / mPosionNum.get(str) );
			} // end of for
						
			//Map<String, Double> 按值排序
			mAveSalary = sortMapByValue(mAveSalary);
			
			// map写文件: salaryAve.csv
			set = mAveSalary.keySet();
			
			for( String str : set){
				csvWt.write( str );
				csvWt.write( String.valueOf( 
						new java.text.DecimalFormat("#.00").format( mAveSalary.get(str).doubleValue()) 
						) + "k");
				
				csvWt.endRecord();
			} // end of for
			
			csvWt.close();
			
		} finally{
			new File(strFileName).delete();
		}
		
	} // end of csvsalary2Average
	
	
	public static void csvhousepri2Average(String strFileName) throws Exception {
		
		new File(strFileName).createNewFile();
		
		try{
			CsvReader csvRd = new CsvReader(strFileName, ',', Charset.forName("GBK"));
			CsvWriter csvWt = new CsvWriter("E:\\Test\\housepriAve.csv", ',', Charset.forName("UTF-8"));
			
			csvRd.readHeaders();
			
			//逐条读取数据直到读完
			while( csvRd.readRecord() ){
				
				// 读取城市在map中
				if( mHousePri.containsKey(csvRd.get(0)) && mAeraNum.containsKey(csvRd.get(0)) ){  
					int nPri = mHousePri.get( csvRd.get(0) ); 
					int nNum = mAeraNum.get( csvRd.get(0) );
					
					nPri += Integer.valueOf( csvRd.get(2) );
					
					mHousePri.put(csvRd.get(0), nPri);
					mAeraNum.put( csvRd.get(0), nNum+1);
				}
				else{
					mHousePri.put( csvRd.get(0), Integer.valueOf(csvRd.get(2)) );
					mAeraNum.put( csvRd.get(0), 1 );
				}
			} // end of while
			
			// Ave House Pri 写Map
			Set<String> set = mHousePri.keySet();
			for( String str : set){
				mAveHousePri.put( str, mHousePri.get(str).doubleValue() / mAeraNum.get(str) );
			} // end of for
			
			//Map<String, Double> 按值排序
			mAveHousePri = sortMapByValue(mAveHousePri);
			
			// map写文件: housepriAve.csv
			set = mAveHousePri.keySet();
			
			for( String str : set){
				csvWt.write( str );
				csvWt.write( String.valueOf( 
						new java.text.DecimalFormat("#.00").format( mAveHousePri.get(str).doubleValue()) ));
				csvWt.endRecord();
			} // end of for
			
			csvWt.close();
		}finally {
			new File(strFileName).delete();
		}
		
	}
	
	public static Map<String, Double> sortMapByValue(Map<String, Double> oriMap) {  
	    Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();  
	    if (oriMap != null && !oriMap.isEmpty()) {  
	        List<Map.Entry<String, Double>> entryList = new ArrayList<Map.Entry<String, Double>>(oriMap.entrySet());  
	        Collections.sort(entryList,  
	                new Comparator<Map.Entry<String, Double>>() {  
	                    public int compare(Entry<String, Double> entry1,  
	                            Entry<String, Double> entry2) {  
	                        double value1 = 0, value2 = 0;  
	                        try {  
	                            value1 = entry1.getValue();  
	                            value2 = entry2.getValue();  
	                        } catch (NumberFormatException e) {  
	                            value1 = 0;  
	                            value2 = 0;  
	                        }  
	                        if( (value2 - value1) > 0)
	                        	return 1;
	                        else if( (value2 - value1) < 0)
	                        	return -1;
	                        else
	                        	return 0;  
	                    }  
	                });  
	        Iterator<Map.Entry<String, Double>> iter = entryList.iterator();  
	        Map.Entry<String, Double> tmpEntry = null;  
	        while (iter.hasNext()) {  
	            tmpEntry = iter.next();  
	            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
	        }  
	    }  
	    return sortedMap;  
	}  

}
