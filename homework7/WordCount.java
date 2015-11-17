import java.io.*;
import java.util.*;

public class WordCount {
	public static void main(String args[]) {
		File file_a = new File("F:\\a.txt");
		File file_b = new File("F:\\b.txt");
		String temp = null;
		//��¼�ļ�A,B�ĵ�����������ȥ�أ�
		int count_a = 0 , count_b = 0;
		try (BufferedReader br1 = new BufferedReader(new FileReader(file_a));
				BufferedReader br2 = new BufferedReader(new FileReader(file_b));
				FileWriter fw1 = new FileWriter("F:\\c.txt");
				FileWriter fw2 = new FileWriter("F:\\d.txt")) {
			//��hashset��������¼�ļ�A,B�Ĳ���
			HashSet<String> hs = new HashSet<>();
			//��hashmap��������¼�ļ�A,B�Ľ���
			HashMap<String, Boolean> hm = new HashMap<>();
			while ((temp = br1.readLine()) != null) {
				String[] words = temp.split(" ");
				for (String word : words) {
					//ȥ��
					if (!hs.contains(word)) {
						hs.add(word);
						hm.put(word, false);
					}
					++count_a;
				}
			}
			while ((temp = br2.readLine()) != null) {
				String[] words = temp.split(" ");
				for (String word : words) {
					if (!hs.contains(word)) {
						hs.add(word);
					} else {
						hm.put(word, true);
					}
					++count_b;
				}
			}
			Iterator<String> it1 = hs.iterator();
			//i��j�ֱ��¼�����������ĵ��ʸ���
			int i=0,j=0;
			while (it1.hasNext()) {
				fw1.write(it1.next() + " ");
				++i;
			}
			System.out.println("�����ļ�����ɹ���һ��" + i + "������");
			Iterator<String> it2 = hm.keySet().iterator();
			while (it2.hasNext()) {
				if (hm.get(temp = it2.next())) {
					fw2.write(temp + " ");
					++j;
				}
			}
			System.out.println("�����ļ�����ɹ���һ��" + j + "������");
			System.out.println("�ļ�A��" + count_a + "�����ʡ��ļ�B��" + count_b +"������");
			System.out.println("�ļ�A�����е�����"+ (count_a - j) + "�����ļ�B�����е�����" + (count_b-j)+"��");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
	}
}