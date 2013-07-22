package com.yizu.proj.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.yizu.proj.base.action.BaseForm;

public class Page<T> {
	private String pageStr;

	private List<T> data;

	public static final String PAGE_PARAM_NAME = "curPage";

	public static final String PAGESIZE_PARAM_NAME = "rowsPerPage";

	private int curPage = 1;

	// private int pageCount=1;
	private int rowsPerPage = 15;

	private int rowCount;

	private List<ParameterItem> params = new ArrayList<ParameterItem>();

	private String url;

	private String urlEncodingCharset = "UTF-8";

	private BaseForm form;

	public String getUrlEncodingCharset() {
		return urlEncodingCharset;
	}

	public void setUrlEncodingCharset(String urlEncodingCharset) {
		this.urlEncodingCharset = urlEncodingCharset;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Page() {
		this.url = ServletActionContext.getRequest().getRequestURI();
	}

	/**
	 * 单页显示全部内容
	 * 
	 * @param source
	 */
	public Page(List<T> data) {
		this();
		this.data = data;
		this.setCurPage(1);
		this.setRowsPerPage(data.size());
		this.setRowCount(data.size());
		this.url = ServletActionContext.getRequest().getRequestURI();
	}

	/**
	 * 分页构造函数
	 * 
	 * @param source
	 *            分页内容
	 * @param curPage
	 *            当前页
	 * @param rowCountPerPage
	 *            总页数
	 * @param rowcount
	 *            总记录数
	 */
	public Page(List<T> source, int curPage, int rowCountPerPage, int rowcount) {
		this();
		this.data = source;
		this.curPage = curPage;
		this.rowsPerPage = rowCountPerPage;
		this.rowCount = rowcount;
	}

	public Page(Object baseForm) {
		this();
		form = (BaseForm) baseForm;
	}

	public int getPageCount() {
		return getRowCount() % getRowsPerPage() == 0 ? getRowCount() / getRowsPerPage() : getRowCount() / getRowsPerPage() + 1;
	}

	/*
	 * private void setPageCount(int pageCount) { this.pageCount = pageCount; }
	 */

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	@JSON(serialize = false)
	private void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public void addParams(List<ParameterItem> params) {
		this.params.addAll(params);
	}

	public void addParam(ParameterItem param) {
		this.params.add(param);
	}

	@JSON(serialize = false)
	public String getPageStr() throws UnsupportedEncodingException {
		String pageStr;
		String u = "?";
		if (params != null) {
			for (ParameterItem p : this.params) {
				try {
					u += p.urlParameterFormat(this.getUrlEncodingCharset()) + "&";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (!Pattern.matches("^.*?\\bstp\\b.*$", u)) {
			u += "stp=" + form.getStp() + "&";
		}
		u = (url + u).replace("//", "/");
		pageStr = "共" + this.getRowCount() + "条记录&nbsp;&nbsp;" + getPageCount() + "页";
		pageStr += "&nbsp;每页<input type='text' class='numberInput' style='width:25px;' maxlength=3 id='pagetion_pagesize' value='" + this.rowsPerPage + "'/>";
		pageStr += "<input type='button' value='GO' onclick='pagesizeChanged()'/>";
		pageStr += "&nbsp;&nbsp;当前为第" + this.getCurPage() + "页";
		if (curPage != 1) {
			pageStr += "&nbsp;&nbsp;<a href='" + u + PAGE_PARAM_NAME + "=" + (curPage - 1) + "&rowsPerPage=" + this.getRowsPerPage() + "'>上一页</a>";
		} else {
			pageStr += "&nbsp;&nbsp;<span style='color:#333333;'>上一页</span>";
		}
		if (curPage < this.getPageCount()) {
			pageStr += "&nbsp;&nbsp;<a href='" + u + PAGE_PARAM_NAME + "=" + (curPage + 1) + "&rowsPerPage=" + this.getRowsPerPage() + "'>下一页</a>";
		} else {
			pageStr += "&nbsp;&nbsp;<span style='color:#333333;'>下一页</span>";
		}
		pageStr += "&nbsp;&nbsp;<select onchange='window.location.href=this.value'>";
		for (int i = 1; i <= this.getPageCount(); i++) {
			if (i == curPage) {
				pageStr += "<option selected value='" + u + PAGE_PARAM_NAME + "=" + i + "&rowsPerPage=" + this.getRowsPerPage() + "'>第" + i + "页</option>";
			} else {
				pageStr += "<option value='" + u + PAGE_PARAM_NAME + "=" + i + "&rowsPerPage=" + this.getRowsPerPage() + "'>第" + i + "页</option>";
			}
		}
		pageStr += "</select>";
		String js = "<script type='text/javascript'>";
		js += "var pagetion_u='" + u + "';";
		js += "var pagetion_url;";
		js += "function pagesizeChanged(){if(/^\\d{1,3}$/.test(document.getElementById('pagetion_pagesize').value)&&parseInt(document.getElementById('pagetion_pagesize').value)>0){"
				+ "pagetion_url=pagetion_u+'"
				+ Page.PAGE_PARAM_NAME
				+ "=1&"
				+ Page.PAGESIZE_PARAM_NAME
				+ "='+document.getElementById('pagetion_pagesize').value;"
				+ "}else{"
				+ "pagetion_url=pagetion_u+'"
				+ Page.PAGE_PARAM_NAME
				+ "=1&"
				+ Page.PAGESIZE_PARAM_NAME
				+ "="
				+ this.rowsPerPage
				+ "';"
				+ "}"
				+ "window.location.href=pagetion_url;}";
		js += "</script>";
		pageStr += js;
		return pageStr;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

}
