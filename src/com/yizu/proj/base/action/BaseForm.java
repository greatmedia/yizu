package com.yizu.proj.base.action;

import com.yizu.proj.utils.DateJsonValueProcessor;
import net.sf.json.JSONObject;

import com.yizu.proj.utils.JsonWrapper;
import com.yizu.proj.utils.Page;
import net.sf.json.JsonConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class BaseForm<T> {
//	HttpServletRequest request = ServletActionContext.getRequest();
//	String ip = request.getRemoteAddr();
	private String ip;
	private String id;
    private String ids;
    private Page<T> pageSource = new Page(this);
    private Integer curPage = 1;
    private Integer rowsPerPage = 15;
    private String to;
    private String stp;
    private String stp2;

    private String popMessage;
    private boolean resultBoolean;
    private String jsonText;
    private String datetimeid;
    
    public String getDatetimeid() {
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}

	public void setDatetimeid(String datetimeid) {
		this.datetimeid = datetimeid;
	}

	public String getIp() {
    	HttpServletRequest request = ServletActionContext.getRequest();
		return ip = request.getRemoteAddr();
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
    public String getJsonText() {
        return this.jsonText;
    }

    public void setJsonText(String jsonText) {
    	System.out.println(jsonText);
        this.jsonText = jsonText;
    }

    /**
     * 是否功能入口点，即stp参数为空
     *
     * @return
     */
    public boolean isEntrance() {
        return stp == null || "".equals(stp);
    }

    /**
     * 判断当前请求是否为某步骤
     *
     * @param stepName
     * @return
     */
    public boolean isStep(String stepName) {
        return stepName.equals(stp);
    }

    public Page<T> getPageSource() {
        return pageSource;
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setPageSource(Page<T> pageSource) {
        this.pageSource = pageSource;
    }

    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowPerPage) {
        if (rowPerPage == null || rowPerPage < 0) {
            this.rowsPerPage = pageSource.getRowsPerPage();
        } else {
            this.rowsPerPage = rowPerPage;
            pageSource.setRowsPerPage(rowPerPage);
        }
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        if (curPage == null || curPage < 0) {
            curPage = 1;
        }
        this.curPage = curPage;
        pageSource.setCurPage(curPage);
    }

    public static void main(String[] args) {
        int y = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int m = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        System.out.println(y + ":" + m);
    }

    public String getStp2() {
        return stp2;
    }

    public void setStp2(String stp2) {
        this.stp2 = stp2;
    }

    public void setJsonMsg(Object data) {
        setJsonMsg(this.popMessage, this.resultBoolean, data);
    }
    
    public void setJsonMsg(String msg, boolean flag, Object data, int status) {
    	JsonWrapper wrapper = new JsonWrapper(flag, msg, data, status);
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("EEE MMM dd HH:mm:ss Z yyyy",Locale.US));
        setJsonText(JSONObject.fromObject(wrapper, config).toString());
    }

    public void setJsonMsg(String msg, boolean flag, Object data) {
        JsonWrapper wrapper = new JsonWrapper(flag, msg, data);
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        setJsonText(JSONObject.fromObject(wrapper, config).toString());
    }

    public void setJsonMsg() {
        setJsonMsg(this.popMessage, this.resultBoolean, null);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    /*
      * public T getInstance() { return instance; }
      *
      * public void setInstance(T instance) { this.instance = instance; }
      */
}