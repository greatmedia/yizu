package com.yizu.proj.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtils {
	protected static final Logger LOG = LoggerFactory.getLogger(IpUtils.class);

	public static String getClientIp(HttpServletRequest request) {
		try {
			String ip = request.getHeader("x-forwarded-for");
			if (LOG.isDebugEnabled()) {
				LOG.debug("x-forwarded-for = {}", ip);
			}
			if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
				ip = request.getHeader("Proxy-Client-IP");
				if (LOG.isDebugEnabled()) {
					LOG.debug("Proxy-Client-IP = {}", ip);
				}
			}
			if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (LOG.isDebugEnabled()) {
					LOG.debug("WL-Proxy-Client-IP = {}", ip);
				}
			}
			if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
				ip = request.getRemoteAddr();
				if (LOG.isDebugEnabled()) {
					LOG.debug("RemoteAddr-IP = {}", ip);
				}
			}
			if (StringUtils.isNotEmpty(ip)) {
				ip = ip.split(",")[0];
			}
			return ip;
		} catch (Exception e) {
			
		}
		return request.getRemoteAddr();
	}
}