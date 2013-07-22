package com.yizu.proj.utils;

public class ContentLength {
	public static String selectcontent(String content){
		String shortcontent = "";
		if(content.startsWith("<embed")){
			shortcontent = content;
		}else{
			if(content.length() > 150){
				shortcontent = content.substring(0,150);
				shortcontent =shortcontent + "......";
			}else{
				shortcontent = content;
			}
		}
		return shortcontent; 
	}
}
