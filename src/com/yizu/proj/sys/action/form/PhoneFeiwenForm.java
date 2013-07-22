package com.yizu.proj.sys.action.form;

import java.io.File;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.UserInfo;

public class PhoneFeiwenForm extends BaseForm<UserInfo>{
	/**飞闻类型ID*/
	private String tid;
	/**飞闻ID*/
	private String fwid;
	/**邮箱*/
	private String email;
	/**密码*/
	private String pwd;
	private UserInfo user;
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	/**登录类型*/
	private String logtype;
	/**内容*/
	private String content;
	/**授权码*/
	private String oaid;
	private File[] upFile; //待上传的文件
    private String[] upFileFileName;;  //待上传的真实文件名
	public String getOaid() {
		return oaid;
	}
	public File[] getUpFile() {
		return upFile;
	}
	public void setUpFile(File[] upFile) {
		this.upFile = upFile;
	}
	public String[] getUpFileFileName() {
		return upFileFileName;
	}
	public void setUpFileFileName(String[] upFileFileName) {
		this.upFileFileName = upFileFileName;
	}
	public void setOaid(String oaid) {
		this.oaid = oaid;
	}
	public String getOatype() {
		return oatype;
	}
	public void setOatype(String oatype) {
		this.oatype = oatype;
	}
	/**授权类型*/
	private String oatype;
	/**当前页数*/
	private int pageNum;
	/**每页显示行数*/
	private int pageSize;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		if(pageSize>30)
		{
			pageSize = 50;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getFwid() {
		return fwid;
	}
	public void setFwid(String fwid) {
		this.fwid = fwid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLogtype() {
		return logtype;
	}
	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
