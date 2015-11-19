import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

public class CountWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strFile = "E:\\1.txt";
		String strFile2 = "E:\\2.txt";
		double nWordsInFile1Counts = 0.0;
		double nWordsInFile2Counts = 0.0;
		
		Map<String, Integer> map1 = readTxtFile(strFile);
		Map<String, Integer> map2 = readTxtFile(strFile2);
		
		System.out.println("�ļ�1�еĵ����У�");
		for(Map.Entry<String, Integer> entry:map1.entrySet())
		{
			System.out.println(entry.getKey()+" ");
			nWordsInFile1Counts += entry.getValue();
		}
		System.out.println("�ļ�1�еĵ����ܸ�����" + nWordsInFile1Counts);
		System.out.println("�ļ�2�еĵ����У�");
		for(Map.Entry<String, Integer> entry:map2.entrySet())
		{
			System.out.println(entry.getKey()+" ");
			nWordsInFile2Counts += entry.getValue();
		}
		System.out.println("�ļ�2�еĵ����ܸ�����" + nWordsInFile2Counts);
		
		System.out.println("ͬʱ�����������ļ��еĵ����У�");
		for(Map.Entry<String, Integer> entry1 : map1.entrySet())
		{
			if(map2.containsKey(entry1.getKey()) )
			{
				System.out.println(entry1.getKey() + " ���ļ�1����ռ����Ϊ��" + 
						entry1.getValue()/nWordsInFile1Counts );
				System.out.println("���ļ�2����ռ����Ϊ��" +
						map2.get(entry1.getKey()).intValue()/nWordsInFile2Counts);
			}
		}
	}
	
	public static Map<String, Integer> readTxtFile(String strFilePath)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		try{
			String encoding = "GBK";
			File file = new File(strFilePath);
			if(file.isFile() && file.exists())  //�ж��ļ��Ƿ����
			{
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				StringBuffer strbuffer = new StringBuffer();
				
				String str = null;
				while((str = bufferedReader.readLine()) != null)
				{
					strbuffer.append(str);
				}
				StringTokenizer strToken = new StringTokenizer(strbuffer.toString(), " ");
				while(strToken.hasMoreTokens())
				{
					String strLetter = strToken.nextToken();
					int count;
					if(map.get(strLetter) == null)
					{
						count = 1;
					}
					else
					{
						count = map.get(strLetter).intValue() + 1;
					}
					map.put(strLetter, count);
				}
			}
			else
			{
				System.out.println("�ļ������ڣ�");
			}
		}catch(Exception e){
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
		return map;
	}

}
