package com.yizu.proj.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.yizu.proj.config.AppParametersConfig;

public class TaobaoUtil {
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',

	'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public final static String APP_KEY = AppParametersConfig.getParameter("top.appkey");

	public final static String APP_SERCET = AppParametersConfig.getParameter("top.sercet");

	public final static String APP_URL = AppParametersConfig.getParameter("top.url");

	public final static String login_URL = "http://container.api.taobao.com/container/identify?";
	public final static String logoff_URL = "http://container.api.taobao.com/container/logoff";

	/**
	 * 
	 * 对参数进行MD5
	 * 
	 * 
	 * 
	 * @param params
	 * 
	 * 排好序的参数Map
	 * 
	 * @param secret
	 * 
	 * 应用的密
	 * 
	 * @return MD5签名字符
	 * 
	 * @throws java.io.UnsupportedEncodingException
	 * 
	 */

	public static String sign(final TreeMap<String, String> params,

	String secret) throws UnsupportedEncodingException {

		if (null == params || params.isEmpty()) {

			return null;

		}

		if (isBlank(secret)) {

			return null;

		}

		StringBuilder sb = new StringBuilder();

		sb.append(secret);

		for (Iterator<Entry<String, String>> it = params.entrySet().iterator(); it

		.hasNext();) {

			Entry<String, String> entry = it.next();

			sb.append(entry.getKey()).append(defaultString(entry.getValue()));

		}

		sb.append(secret);

		byte[] bytes = sb.toString().getBytes("UTF-8");

		return md5Hex(bytes).toUpperCase();

	}

	public static boolean isBlank(String str) {

		int strLen;

		if (str == null || (strLen = str.length()) == 0) {

			return true;

		}

		for (int i = 0; i < strLen; i++) {

			if ((Character.isWhitespace(str.charAt(i)) == false)) {

				return false;

			}

		}

		return true;

	}

	public static String defaultString(String str) {

		return str == null ? "" : str;

	}

	private static String md5Hex(byte[] bytes) {

		return new String(encodeHex(getMd5Digest().digest(bytes)));

	}

	private static MessageDigest getMd5Digest() {

		try {

			return MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage());

		}

	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.

		for (int i = 0, j = 0; i < l; i++) {

			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];

			out[j++] = DIGITS[0x0F & data[i]];

		}

		return out;

	}

}
