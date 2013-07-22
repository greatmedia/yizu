package com.yizu.proj.sys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.yizu.proj.base.service.BaseService;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.CircleInfoExample;
import com.yizu.proj.sys.service.CircleInfoService;

/**
 * 首页
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private BaseService baseService;
	@Autowired
	private CircleInfoService circleInfoService;
	
    public IndexServlet() {
        super();
    }

    
    
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
		try {
			CircleInfoExample cexample = new CircleInfoExample();
			cexample.setLimitStart((pageNum-1)*pageSize);
			cexample.setRowsPerPage(pageSize);
			List<CircleInfo> clist = circleInfoService.selectByExample(cexample);
			System.out.println("========="+clist.size());
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			CircleInfoExample cexample = new CircleInfoExample();
			cexample.setLimitStart((pageNum-1)*pageSize);
			cexample.setRowsPerPage(pageSize);
			List<CircleInfo> clist = baseService.selectByExample(cexample);
			System.out.println("========="+clist.size());
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
