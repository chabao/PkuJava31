package tong;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tests {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		File input = new File("G:/input.html");
		int size = 0;
		LinkedList<String> ll = new LinkedList<>();
		try(Connection conn =DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/data", "root", "111111");
				PreparedStatement pstmt = conn
						.prepareStatement("insert into HousePrice values(?,?,?)")){
		Document doc = Jsoup.parse(input,"GBK");
		Element div = doc.select("div.citys").first();
		Elements dds = div.getElementsByTag("li");
		
		for(Element dd : dds){
			Elements links = dd.getElementsByTag("a");
			for(Element link : links){
				String current = link.attr("href");
				if(!ll.contains(current)){
					ll.add(current);
					Document temp =Jsoup.connect(current).timeout(30000).get();
					Elements lis = temp.select("li.first");
					for(Element li : lis){
						String data = li.text();
						int index1 = data.indexOf("房价");
						int index2 = data.indexOf("元/㎡");
						if(index2 == -1)index2 = index1+8;
						String city = link.text();
						String area = data.substring(0, index1);
						String price = data.substring(index1+6, index2);
						pstmt.setString(1, city);
						pstmt.setString(2, area);
						pstmt.setString(3, price);
						pstmt.executeUpdate();
						System.out.println("成功插入一条数据");
						size++;
					}
				}
			}
		}
		System.out.println("总共插入" + size + "条数据");
	}
}
}