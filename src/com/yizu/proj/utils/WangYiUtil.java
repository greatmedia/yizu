package com.yizu.proj.utils;

import java.net.URLEncoder;

import com.yizu.proj.config.AppParametersConfig;

import t4j.TBlog;
import t4j.TBlogException;
import t4j.http.RequestToken;


public class WangYiUtil {
	public static  TBlog tblog;
	public static  RequestToken requestToken;
	public static String getURL()
	{
		String login_URL = null;
		try {
			tblog = new TBlog();
			requestToken = tblog.getOAuthRequestToken();
			login_URL = requestToken.getAuthenticationURL();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return login_URL;
	}


}
