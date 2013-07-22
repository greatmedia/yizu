package com.yizu.proj.sys.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

import simpleWebFrame.util.MD5Util;


public class LoginFilter implements Filter  {
	private FilterConfig filterconfig = null;
	public void init(FilterConfig filterconfig) {
		this.filterconfig = filterconfig;
		if (filterconfig != null) {
			log("init success!");
		}
	}

	/**
	 * Filter的主体方法，验证cookie的有效性
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		javax.servlet.http.Cookie[] cookie = req.getCookies();
		String result = "fail";
		String userid = "";
		String path = req.getContextPath();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
		if (cookie != null  ) {
			for (int i = 0; i < cookie.length; i++) {
//				System.out.println(cookie[i].getName());
//				System.out.println(cookie[i].getMaxAge());
				//cookie[i].getMaxAge() > 0 && 
				if (cookie[i].getName().equals("userid")) {
					result = "1";
					userid =  cookie[i].getValue();
//					MD5Util.MD5(userid);
				}
				//cookie[i].getMaxAge() > 0 && 
				if(cookie[i].getName().equals("cookieUserId")){
					result = "1";
					userid =  cookie[i].getValue();
				}
			}
		}

		if ("fail".equals(result)) {
			req.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			res.sendRedirect(basePath+"index/"+userid);
		}
	}
	 public static String doGet(String url, String queryString) { 
         String response = null; 
         HttpClient client = new HttpClient(); 
         HttpMethod method = new GetMethod(url); 
         try { 
                 if (StringUtils.isNotBlank(queryString)) 
                         method.setQueryString(URIUtil.encodeQuery(queryString)); 
                 client.executeMethod(method); 
                 if (method.getStatusCode() == HttpStatus.SC_OK) { 
                         response = method.getResponseBodyAsString(); 
                 } 
         } catch (URIException e) { 
                 //log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e); 
         } catch (IOException e) { 
                 //log.error("执行HTTP Get请求" + url + "时，发生异常！", e); 
         } finally { 
                 method.releaseConnection(); 
         } 
         return response; 
	 } 
	public void destroy() {
		this.filterconfig = null;

	}
	/**
	 * 
	 * @param log
	 */
	private void log(String log) {
		filterconfig.getServletContext().log(log);
	}

}
