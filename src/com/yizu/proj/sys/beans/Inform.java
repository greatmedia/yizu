package com.yizu.proj.sys.beans;

import com.yizu.proj.sys.beans.gen.InformBase;

public class Inform extends InformBase{
	private UserInfo userinfo;
	private CircleInfo circleinfo;
	private CircleDetailInfo detail;
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public CircleInfo getCircleinfo() {
		return circleinfo;
	}
	public void setCircleinfo(CircleInfo circleinfo) {
		this.circleinfo = circleinfo;
	}
	public CircleDetailInfo getDetail() {
		return detail;
	}
	public void setDetail(CircleDetailInfo detail) {
		this.detail = detail;
	}
	
}