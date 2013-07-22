package com.yizu.proj.sys.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yizu.proj.sys.beans.gen.FeiWenBase;

public class FeiWen extends FeiWenBase{
	private UserInfo userinfo;
	
	private List<FeiWenComment> feiWenComment;
	private String createdatetimeWhat;
	private String createdate;
	
	public String getCreatedatetimeWhat() {
		return createdatetimeString(getCreatedatetime());
	}

	public void setCreatedatetimeWhat(String createdatetimeWhat) {
		this.createdatetimeWhat = createdatetimeWhat;
	}

	public String getCreatedate() {
		return createdateString(getCreatedatetime());
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public List<FeiWenComment> getFeiWenComment() {
		return feiWenComment;
	}

	public void setFeiWenComment(List<FeiWenComment> feiWenComment) {
		this.feiWenComment = feiWenComment;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public String createdateString(Date create)
	{
		String result = "";
		try {

	    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
			result = tempDate.format(create);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	public String createdatetimeString(Date create)
	{
		String result = "";
		try {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
			long nh = 1000 * 60 * 60;// 一小时的毫秒数
			long nm = 1000 * 60;// 一分钟的毫秒数
			long ns = 1000;// 一秒钟的毫秒数
			long diff = new Date().getTime() - create.getTime();
			long a = new Date().getTime();
			System.out.println(diff);
			if (diff > ns) {
				long nss = diff / ns;
				result = "发表于" + nss + "秒钟前";
			}
			if (diff > nm) {
				long nms = diff / nm;
				result = "发表于" + nms + "分钟前";
			}
			if (diff > nh) {
				long nhs = diff / nh;
				result = "发表于" + nhs + "小时前";
			}
			if (diff > nd) {
				long nds = diff / nd;
				result = "发表于" + nds + "天前";
			}
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
}