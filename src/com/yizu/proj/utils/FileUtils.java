package com.yizu.proj.utils;

import org.apache.commons.lang.StringUtils;

public class FileUtils {
	private static final String SEPARATOR = "/";
	
	public static String padRightSeparator(String path) {
		if(!path.endsWith(String.valueOf(SEPARATOR))) {
			path = path + SEPARATOR;
		}
		return path;
	}
	
	public static String toUploadPath(String webPath) {
		if(StringUtils.isBlank(webPath)) {
			return webPath;
		}
		
		int idx = webPath.lastIndexOf( "/upload" );
		return webPath.substring(idx);
	}
}
