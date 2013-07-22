package com.yizu.proj.utils;

import java.net.URLEncoder;

import com.yizu.proj.config.AppParametersConfig;

public class RenrenUtil {
	public static String APP_ID = AppParametersConfig.getParameter("renren.appid");
	
	public static String APP_KEY = AppParametersConfig.getParameter("renren.appkey");

	public static String APP_SERCET = AppParametersConfig.getParameter("renren.sercet");
	
	public static String redirectUri = "http://www.1mily.com/authorizeAction_renRenLoginResult.do";
	
	public static String login_URL = "";
//	public static String logoff_URL = "http://container.api.taobao.com/container/logoff";
	
	static {
		try {
			login_URL = "https://graph.renren.com/oauth/authorize?client_id=" + APP_KEY 
					+ "&response_type=code&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8")+ "&display=page";
			
		} catch (Exception e) {}
	}

}
