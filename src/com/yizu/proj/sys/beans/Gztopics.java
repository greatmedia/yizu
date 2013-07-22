package com.yizu.proj.sys.beans;

import java.util.Date;

import com.yizu.proj.sys.beans.gen.GztopicsBase;

public class Gztopics extends GztopicsBase{
	/**创建圈子的用户信息*/
	private UserInfo userinfo;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
}