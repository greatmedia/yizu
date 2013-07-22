package com.yizu.proj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceContent {
	public static String replaceHtmlFromContent(String content){
		Pattern p = Pattern.compile("<([^>]*)>");
		Matcher m = p.matcher(content);
		String s = m.replaceAll("");
		if(s.equals("") || s.equals("&nbsp;") || s.trim().equals("")){
			s="...";
		}
		return s;
	}
}
