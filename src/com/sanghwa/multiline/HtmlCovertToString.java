package com.sanghwa.multiline;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;


/**
 * @author : ddavid
 * @version $Id: HtmlCovertToString.java
 */
public class HtmlCovertToString {
	
	
	public static String getConvertedString(String path, HashMap<String, String> map){
		return getChangedStr(getFileString(path), map);
	}

	public static String getFileString(String path) {
		FileInputStream fi = null;

		String str = "";

		try {
			fi = new FileInputStream(path);
			str = IOUtils.toString(fi, "UTF-8");			
			System.out.println("################ BeforeChanged ############");
			System.out.println(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			org.apache.commons.io.IOUtils.closeQuietly(fi);
		}

		return str;
	}

	public static String getChangedStr(String str, HashMap<String, String> map) {

		StringBuffer output = new StringBuffer();

		Pattern pattern = Pattern.compile("\\{\\{[a-zA-Z0-0]+\\}\\}",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		// using Matcher find(), group(), start() and end() methods
		int currentIndex = 0;

		while (matcher.find()) {

			String match = matcher.group();
			match = match.substring(2, match.length() - 2); // remove {{ }}

			output.append(str.substring(currentIndex, matcher.start()));

			if (map.containsKey(match)) {
				output.append(map.get(match));
			} else {
				output.append(matcher.group());
			}
			currentIndex = matcher.end();

			/*
			 * System.out.println("Found the text " + matcher.group() +
			 * " starting at " + matcher.start() + " index and ending at index "
			 * + matcher.end());
			 */
		}
		output.append(str.substring(currentIndex, str.length()));

		return output.toString();
	}
}
