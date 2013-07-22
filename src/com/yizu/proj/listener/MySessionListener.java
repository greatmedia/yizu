package com.yizu.proj.listener;

import java.util.Hashtable;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yizu.proj.dto.UserDto;
import com.yizu.proj.utils.UserOnlineUtils;

/**
 * 监听在线用户
 * 
 * @author Administrator
 * @date 2012-6-1 下午02:03:19
 * @version V1.0
 */
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	public static final Logger LOG = LoggerFactory.getLogger(MySessionListener.class);

	public void sessionCreated(HttpSessionEvent event) {
		UserOnlineUtils.getInstance().setUserOnlineSize(UserOnlineUtils.getInstance().getUserOnlineSize() + 1);
		
		Hashtable<String, UserDto> onlineUser = UserOnlineUtils.getInstance().getOnlineUser();
		onlineUser.put(event.getSession().getId(), new UserDto());

		if (LOG.isDebugEnabled()) {
			LOG.debug(event.getSession().getId() + ":游客身份进入！" + onlineUser.size());
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		UserOnlineUtils.getInstance().setUserOnlineSize(UserOnlineUtils.getInstance().getUserOnlineSize() - 1);
		
		Hashtable<String, UserDto> onlineUser = UserOnlineUtils.getInstance().getOnlineUser();
		onlineUser.remove(event.getSession().getId());

		if (LOG.isDebugEnabled()) {
			LOG.debug(event.getSession().getId() + ":删除在线用户标志！");
		}
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		
		if (StringUtils.isNotBlank(event.getName()) && event.getName().equals("user")) {
			Hashtable<String, UserDto> onlineUser = UserOnlineUtils.getInstance().getOnlineUser();
			// 添加user属性
			if (onlineUser.containsKey(event.getSession().getId())) {
				UserDto currUser = (UserDto) event.getSession().getAttribute("user");
				onlineUser.put(event.getSession().getId(), currUser);

				if (LOG.isDebugEnabled()) {
					LOG.debug(event.getSession().getId() + ":用户执行登录，用户名【" + currUser.getNick() + "】");
				}
			} else {
				// do nothing
			}
		}
		
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (StringUtils.isNotBlank(event.getName()) && event.getName().equals("user")) {
			Hashtable<String, UserDto> onlineUser = UserOnlineUtils.getInstance().getOnlineUser();
			if (onlineUser.containsKey(event.getSession().getId())) {
				// 更新为游客身份
				onlineUser.put(event.getSession().getId(), new UserDto());

				if (LOG.isDebugEnabled()) {
					LOG.debug(event.getSession().getId() + ":已经退出，标记为游客身份！");
				}

			} else {
				// do nothing
			}
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {

	}
}
