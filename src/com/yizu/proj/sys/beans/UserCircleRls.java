package com.yizu.proj.sys.beans;

import java.util.List;

import com.yizu.proj.sys.beans.gen.UserCircleRlsBase;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_circle_rls
*/
public class UserCircleRls extends UserCircleRlsBase {
	/**创建圈子的用户信息*/
	private UserInfo userinfo;
	private CircleInfo circleInfo;

	public CircleInfo getCircleInfo() {
		return circleInfo;
	}

	public void setCircleInfo(CircleInfo circleInfo) {
		this.circleInfo = circleInfo;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	
}