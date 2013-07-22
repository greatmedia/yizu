package com.yizu.proj.sys.action;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import com.yizu.proj.sys.beans.UserInfo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

import org.apache.struts2.ServletActionContext;

public class OnLineUserListener implements HttpSessionAttributeListener {
	public static LinkedList<Integer> onLineUserList = new LinkedList<Integer>();

	public static boolean findUserOnLine(Integer userId) {
		return onLineUserList.contains(userId);
	}
	public void attributeAdded(HttpSessionBindingEvent se) {
		if ("user".equals(se.getName())) {
			UserInfo user = (UserInfo) se.getValue();
			if(!isExistSession(user.getUserid()))
			{
				onLineUserList.add(user.getUserid());
			}
		}
	}
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if ("user".equals(se.getName())) {
			UserInfo user = (UserInfo) se.getValue();
			onLineUserList.remove(user.getUserid());
		}
	}
	
	public void attributeReplaced(HttpSessionBindingEvent se) {
	}
	public boolean isExistSession(int uid)
	{
		return onLineUserList.contains(uid);
	}
}