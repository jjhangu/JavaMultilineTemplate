package com.sanghwa.multiline;

import java.util.HashMap;

public class Main {
	public static void main(String args[]){
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "david");
		map.put("age", "15");
		map.put("black", "red");
		String converted = HtmlCovertToString.getConvertedString(System.getProperty("user.dir")+"/src/com/sanghwa/multiline/"+"test.html", map);
		System.out.println("################ ChangedAfter ############");
		System.out.println(converted);
		
		
		
	}
}
