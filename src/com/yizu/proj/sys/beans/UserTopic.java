package com.yizu.proj.sys.beans;

import com.yizu.proj.sys.beans.gen.UserTopicBase;

public class UserTopic extends UserTopicBase{
	/**创建圈子的用户信息*/
	private UserInfo userinfo;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
}
