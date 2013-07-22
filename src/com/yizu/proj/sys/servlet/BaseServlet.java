package com.yizu.proj.sys.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * serlvet访问基础类
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected final Logger log = Logger.getLogger(getClass());

	protected static final String CONTENT_TYPE = "text/html; charset=utf-8";
	
	protected int pageNum =1; //页码
	
	protected int pageSize = 32; //每页显示条数
	
	protected String type;   //访问类型
	
	protected int show = 1;  //是否显示 1 显示 0 不显示
	
	protected boolean flag;  //成功失败标识
	
	protected int id;   //id
	
	protected int ids;  //ids
	
	protected String content;  //传入的内容
	
    public BaseServlet() {
    	
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pnum = request.getParameter("pageNum");
		String psize = request.getParameter("pageSize");
		String paramid    = request.getParameter("id");
		String paramids   = request.getParameter("ids");
		type = request.getParameter("type");
		content = request.getParameter("content");
		if(!StringUtils.isEmpty(pnum)){
			pageNum = Integer.parseInt(pnum);
		}
		if(!StringUtils.isEmpty(psize)){
			pageSize = Integer.parseInt(psize);
		}
		if(!StringUtils.isEmpty(paramid)){
			id = Integer.parseInt(paramid);
		}
		if(!StringUtils.isEmpty(paramids)){
			ids = Integer.parseInt(paramids);
		}
		log.info("pageNum="+pageNum);
		log.info("pageSize="+pageSize);
		log.info("type="+type);
		log.info("id="+id);
		log.info("ids="+ids);
	}

	/**
	 * 响应页面请求结果
	 * @param response HttpServletResponse实例对象
	 * @param result 返回结果
	 * @throws IOException
	 * */
	public void printResult(HttpServletResponse response, String result){
        try {
            response.setContentType(CONTENT_TYPE);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0L);
            
            PrintWriter out = response.getWriter();
            out.println(result);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error(e.toString());
        }
    }
}
