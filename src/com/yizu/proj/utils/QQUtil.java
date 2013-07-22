package com.yizu.proj.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.yizu.proj.config.AppParametersConfig;

public class QQUtil {
	public static String APP_ID = AppParametersConfig.getParameter("qq.appid");
	public static String APP_Key = AppParametersConfig.getParameter("qq.appkey");
	public static String REDIRECT_URI = "http://www.1mily.com/inc/temp.jsp";
	public static String QQ_LOGIN_URL = "https://graph.qq.com/oauth2.0/authorize";

	public static String login_URL = "";
	static {
		try {
			login_URL = QQUtil.QQ_LOGIN_URL + "?response_type=token&client_id=" 
				+ QQUtil.APP_ID + "&redirect_uri="
				+ URLEncoder.encode(REDIRECT_URI, "utf-8")
				+ "&scope=get_user_info";
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
