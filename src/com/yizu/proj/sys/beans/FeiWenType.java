package com.yizu.proj.sys.beans;

import java.util.Date;
import java.util.List;

import com.yizu.proj.sys.beans.gen.FeiWenTypeBase;

public class FeiWenType extends FeiWenTypeBase{
	private UserInfo userinfo;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public List<FeiWen> feiWen;

	public List<FeiWen> getFeiWen() {
		return feiWen;
	}

	public void setFeiWen(List<FeiWen> feiWen) {
		this.feiWen = feiWen;
	}

}