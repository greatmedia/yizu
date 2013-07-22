package com.yizu.proj.sys.beans;

import com.yizu.proj.sys.beans.gen.UsertalkBase;

public class Usertalk extends UsertalkBase{
	/**创建圈子的用户信息*/
	private UserInfo userinfo;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
}
