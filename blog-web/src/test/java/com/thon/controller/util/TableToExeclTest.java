/**
 * 
 */
package com.thon.controller.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @file TableToExeclTest.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Oct 21, 2013 3:36:30 PM
 * @description Html Table to Execl
 */
public class TableToExeclTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getTableHtml();
	}

	public static void  getTableHtml() {
		Document doc;
		try {
			String html = "<table class=\"table table-striped\"><thead><tr><th colspan=\"2\" style=\"width:20%;\">公司</th><th>好滋味</th><th>走四方</th><th>新希望</th><th>新东方</th></tr></thead><tbody><tr><td rowspan=\"3\" style=\"width:8%;\">产品1</td><td>组织市场</td><td>5.0</td><td>0.0</td><td>7.0</td><td>0.0</td></tr></tbody></table>";
			doc = Jsoup.parse(html);
			Element table = doc.select("table").first();
			System.out.println(table.html());
			System.out.println(table.outerHtml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
