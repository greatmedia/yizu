package com.yizu.proj.sys.action.form;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.Gztopics;

public class GztopicsForm extends BaseForm<Gztopics>{
	public String tid;//话题ID
	public String ccid;//评论ID
	private int pageNum;
	/** 每页显示行数 */
	private int pageSize;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
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
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
}
