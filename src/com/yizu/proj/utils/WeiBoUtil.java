package com.yizu.proj.utils;

import com.yizu.proj.config.AppParametersConfig;

public class WeiBoUtil {
	public static String APP_ID = AppParametersConfig.getParameter("weibo.appid");
	public static String APP_Sercet = AppParametersConfig.getParameter("weibo.sercet");
	public static String redirect_URI = "http://www.1mily.com/authorizeAction_weiBoLoginResult.do";
	public static String baseURL = "https://api.weibo.com/2/";
	public static String accessTokenURL = "https://api.weibo.com/oauth2/access_token";
	public static String authorizeURL = "https://api.weibo.com/oauth2/authorize";

}
