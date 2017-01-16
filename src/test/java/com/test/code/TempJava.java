package com.test.code;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Reporter;

public class TempJava {

	
	public static void main(String str[]){
		File file = new File("");
		String filePath = file.getAbsolutePath();
		  
		String url = "<img src='"+filePath+"\\"+"123.png' hight='100' />";
		
		System.out.println(getImageString(url));
		
	}
	
	
	public static String getImageString(String s)
	{
	    String regex = "(<img(.*?)/>)";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    while (matcher.find()) {
	        String group = matcher.group(1);
	        System.out.println("group: " + group);
	        //可根据实际情况多个图片 全部一起return
	        return group;
	    }
	    return "";
	}
	
}
