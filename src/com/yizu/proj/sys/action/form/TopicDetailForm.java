package com.yizu.proj.sys.action.form;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.CircleInfo;

public class TopicDetailForm extends BaseForm<CircleInfo>{
	private int pageNum;
	/** 每页显示行数 */
	private int pageSize;
	/** 评论类型  红方 蓝方 */
	public int comType;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**评论内容*/
	public String content;
	private int uid;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getComType() {
		return comType;
	}
	public void setComType(int comType) {
		this.comType = comType;
	}
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
}
