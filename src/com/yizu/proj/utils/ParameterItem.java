package com.yizu.proj.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParameterItem {
	private String parameterName;

	private Object parameterValue;

	private Urlformatable urlformatable;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Object getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Urlformatable getUrlformatable() {
		return urlformatable;
	}

	public void setUrlformatable(Urlformatable urlformatable) {
		this.urlformatable = urlformatable;
	}

	public ParameterItem(String pname, Object pvalue) {
		parameterName = pname;
		parameterValue = pvalue;
	}

	public ParameterItem(String pname, Object pvalue, Urlformatable format) {
		parameterName = pname;
		parameterValue = pvalue;
		urlformatable = format;
	}

	public String urlParameterFormat(String charset) throws UnsupportedEncodingException {
		if (this.urlformatable != null) {
			return urlformatable.format(parameterName, parameterValue, charset);
		}
		return defaultFormat(parameterName, parameterValue, charset);
	}

	public String defaultFormat(String key, Object value, String charset) throws UnsupportedEncodingException {
		if (value == null) {
			return key + "+";
		}
		if (value instanceof Date) {
			return key + "=" + new SimpleDateFormat("yyyy-MM-dd").format((Date) value);
		}
		if (value.getClass().isArray()) {
			String r = "";
			for (int i = 0; i < Array.getLength(value); i++) {
				r += "&" + key + "=" + Array.get(value, i).toString();
			}
			return r.length() > 0 ? r.substring(1) : "";
		}
		return key + "=" + URLEncoder.encode(value.toString(), charset);
	}

}
