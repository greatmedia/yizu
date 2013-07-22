package com.yizu.proj.base.action;

import com.yizu.proj.sys.beans.UserInfo;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yizu.proj.base.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public static final String JSON_PAGE = "jsonPage";
	private String message;
	public String joinCircleCount;
	public String pinglunCircleCount;
	public String topicPinglunCount;
	public String gzTopicCount;

	
	public UserInfo currentUser;

	@Autowired
	protected BaseService baseService;

	
	

	public String getJoinCircleCount() {
		return getSession().getAttribute("joinCircleCount") != null ? (String)getSession().getAttribute("joinCircleCount") : null;
	}

	public void setJoinCircleCount(String joinCircleCount) {
		getSession().setAttribute("joinCircleCount", joinCircleCount);
	}

	public String getPinglunCircleCount() {
		return getSession().getAttribute("pinglunCircleCount") != null ? (String) getSession().getAttribute("pinglunCircleCount") : null;
	}

	public void setPinglunCircleCount(String pinglunCircleCount) {
		getSession().setAttribute("pinglunCircleCount", pinglunCircleCount);
	}

	public String getTopicPinglunCount() {
		return getSession().getAttribute("topicPinglunCount") != null ? (String) getSession().getAttribute("topicPinglunCount") : null;
	}

	public void setTopicPinglunCount(String topicPinglunCount) {
		getSession().setAttribute("topicPinglunCount", topicPinglunCount);
	}

	public String getGzTopicCount() {
		return getSession().getAttribute("gzTopicCount") != null ? (String) getSession().getAttribute("gzTopicCount") : null;
	}

	public void setGzTopicCount(String gzTopicCount) {
		getSession().setAttribute("gzTopicCount", gzTopicCount);
	}

		

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public final String ALERT_ERROR = "alertError";
	// 用于显示简单提示信息的跳转
	public final String SHOW_SIMPLE_MESSAGE = "show_simple_message";
	/**
	 * 在类似修改、添加这样的请求中一般分为两步，先是去往修改或新增页面，然后是提交保存<br/>
	 * 为了方便权限控制，两步请求均使用同一个action方法，仅通过参数中是否带to=1参数来区分<br/>
	 * 带to参数则程序经过一些必要验证和数据加载后返回此字符串TO，struts2将跳转到对应的页面<br/>
	 * 如userAction_add.action?to=1则跳转到add.jsp，userAction_add.action则跳转到add_suc.jsp
	 */
	public final String TO = "to";

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public UserInfo getCurrentUser() {
		return getSession().getAttribute("user") != null ? (UserInfo) getSession().getAttribute("user") : null;
	}

	public void setCurrentUser(UserInfo currentUser) {
		getSession().setAttribute("user", currentUser);
	}

	public String getContextPath() {
		 String path = getRequest().getContextPath();
		 return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort() + path + "/";
	}
	
}
