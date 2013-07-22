package com.yizu.proj.sys.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.WeixinForm;
import com.yizu.proj.sys.beans.FeiWen;
import com.yizu.proj.sys.beans.FeiWenExample;
import com.yizu.proj.sys.service.FeiWenService;
import com.yizu.proj.sys.service.FeiWenTypeService;
import com.yizu.proj.utils.FeiwenResultCode;
import com.yizu.proj.utils.SHA1;

/**
 * @author 龙流平(LongLiuPing) 微博：http://t.qq.com/longliuping
 * @version 创建时间：2013-2-19 下午04:23:42
 */
@Scope("prototype")
@Controller("weixinAction")
public class WeixinAction extends BaseAction implements ModelDriven {
	private WeixinForm form = new WeixinForm();
	@Autowired
	private FeiWenService feiWenService;
	@Autowired
	private FeiWenTypeService feiWenTypeService;

	//
	public void weixinCheck() {
		try {
			// http://blog.csdn.net/wangqianjiao/article/details/8469780
			String signature = form.getSignature();
			String timestamp = form.getTimestamp();
			String nonce = form.getNonce();
			String echostr = form.getEchostr();
			String token = "169gold";
			String[] str = { token, timestamp, nonce };
			Arrays.sort(str);
			String bigStr = str[0] + str[1] + str[2];
			String digest = new SHA1().getDigestOfString(bigStr.getBytes())
					.toLowerCase();
			if (digest.equals(signature)) {
				ActionContext ct = ActionContext.getContext();
				HttpServletResponse response = (HttpServletResponse) ct
						.get(ServletActionContext.HTTP_RESPONSE);
				response.setCharacterEncoding("UTF-8");
				HttpServletRequest request = (HttpServletRequest) ct
						.get(ServletActionContext.HTTP_REQUEST);
				response.setCharacterEncoding("UTF-8");
				BufferedReader sis = request.getReader();
				char[] buf = new char[1024];
				int len = 0;
				StringBuffer sb = new StringBuffer();
				while ((len = sis.read(buf)) != -1) {
					sb.append(buf, 0, len);
				}
				// System.out.println(sb.toString()+";;;;;;;");
				response.setContentType("text/xml");
				response
						.getWriter()
						.print(
								"<xml>"
										+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
										+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
										+ "<CreateTime>1348831860</CreateTime>"
										+ "<MsgType><![CDATA[text]]></MsgType>"
										+ "<Content><![CDATA[this is a test]]></Content>"
										+ "<MsgId>1234567890123456</MsgId>"
										+ "</xml>");
				response.getWriter().print(echostr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			wxWriter("500");
		}
	}

	private void wxWriter(String str) {
		try {
			ActionContext ct = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse) ct
					.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void textWeixin() {
		try {
			// http://blog.csdn.net/wangqianjiao/article/details/8469780
			wxWriter("<xml>" + "<ToUserName><![CDATA[toUser]]></ToUserName>"
					+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
					+ "<CreateTime>1348831860</CreateTime>"
					+ "<MsgType><![CDATA[text]]></MsgType>"
					+ "<Content><![CDATA[this is a test]]></Content>"
					+ "<MsgId>1234567890123456</MsgId>" + "</xml>");
		} catch (Exception e) {
			// TODO: handle exception
			wxWriter("500");
		}
	}

	public void imageWeixin() {
		try {
			// http://blog.csdn.net/wangqianjiao/article/details/8469780
			wxWriter("<xml>" + "<ToUserName><![CDATA[toUser]]></ToUserName>"
					+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
					+ "<CreateTime>1348831860</CreateTime>"
					+ "<MsgType><![CDATA[image]]></MsgType>"
					+ "<PicUrl><![CDATA[this is a url]></PicUrl>"
					+ "<MsgId>1234567890123456</MsgId>" + "</xml>");
		} catch (Exception e) {
			// TODO: handle exception
			wxWriter("500");
		}
	}

	public void localWeixin() {
		try {
			// http://blog.csdn.net/wangqianjiao/article/details/8469780
			wxWriter("<xml>" + "<ToUserName><![CDATA[toUser]]></ToUserName>"
					+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
					+ "<CreateTime>1351776360</CreateTime>"
					+ "<MsgType><![CDATA[location]]></MsgType>"
					+ "<Location_X>23.134521</Location_X>"
					+ "<Location_Y>113.358803</Location_Y>"
					+ "<Scale>20</Scale>" + "<Label><![CDATA[位置信息]]></Label>"
					+ "<MsgId>1234567890123456</MsgId>" + "</xml>");
		} catch (Exception e) {
			// TODO: handle exception
			wxWriter("500");
		}
	}

	public void linkWeixin() {
		try {
			// http://blog.csdn.net/wangqianjiao/article/details/8469780
			wxWriter("<xml>" + "<ToUserName><![CDATA[toUser]]></ToUserName>"
					+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
					+ "<CreateTime>1351776360</CreateTime>"
					+ "<MsgType><![CDATA[link]]></MsgType>"
					+ "<Title><![CDATA[公众平台官网链接]]></Title>"
					+ "<Description><![CDATA[公众平台官网链接]]></Description>"
					+ "<Url><![CDATA[url]]></Url>"
					+ "<MsgId>1234567890123456</MsgId>" + "</xml> ");
		} catch (Exception e) {
			// TODO: handle exception
			wxWriter("500");
		}
	}

	public void eventPushWeixin() {
		try {
			// http://blog.csdn.net/wangqianjiao/article/details/8469780
			wxWriter("<xml>" + "<ToUserName><![CDATA[toUser]]></ToUserName>"
					+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
					+ "<CreateTime>1351776360</CreateTime>"
					+ "<MsgType><![CDATA[link]]></MsgType>"
					+ "<Title><![CDATA[公众平台官网链接]]></Title>"
					+ "<Description><![CDATA[公众平台官网链接]]></Description>"
					+ "<Url><![CDATA[url]]></Url>"
					+ "<MsgId>1234567890123456</MsgId>" + "</xml> ");
		} catch (Exception e) {
			// TODO: handle exception
			wxWriter("500");
		}
	}

	public String searchFeiWen() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			String title = form.getId();
			if (title.indexOf("娱乐") != -1) {
				feiWenExample.createCriteria().andTidEqualTo("1");
			} else if (title.indexOf("运动") != -1) {
				feiWenExample.createCriteria().andTidEqualTo("2");
			} else if (title.indexOf("社会") != -1) {
				feiWenExample.createCriteria().andTidEqualTo("3");
			} else if (title.indexOf("商业") != -1) {
				feiWenExample.createCriteria().andTidEqualTo("4");
			} else if (title.indexOf("自然") != -1) {
				feiWenExample.createCriteria().andTidEqualTo("5");
			} else if (title.indexOf("才俊") != -1) {
				feiWenExample.createCriteria().andTidEqualTo("6");
			} else {
				feiWenExample.createCriteria().andTitleLike("%"+form.getId()+"%");
			}
			feiWenExample.setOrderByClause(" createdatetime ");
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(10);
			List<FeiWen> feiwens = feiWenService.selectByExample(feiWenExample);
			json.put("feiwens", feiwens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("success", false, json);
		}
		return JSON_PAGE;
	}
	public String detail()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWen feiwen = (FeiWen) feiWenService.selectByPrimaryKey(form.getId(), FeiWen.class);
			if(feiwen != null)
			{
				form.setFeiwen(feiwen);
			}
		} catch (Exception e) {
		}
		return "detail";
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}
}
