package com.yizu.proj.sys.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.CircleCommentInfoAgreeForm;
import com.yizu.proj.sys.beans.CircleCommentInfo;
import com.yizu.proj.sys.beans.CircleCommentInfoAgree;
import com.yizu.proj.sys.beans.CircleCommentInfoAgreeExample;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.service.CircleCommentInfoAgreeService;
import com.yizu.proj.sys.service.CircleCommentInfoService;
import com.yizu.proj.sys.service.UserInfoService;

@Scope("prototype")
@Controller("agreeAction")
public class CircleCommentInfoAgreeAction extends BaseAction implements
		ModelDriven {
	private CircleCommentInfoAgreeForm form = new CircleCommentInfoAgreeForm();
	@Autowired
	private CircleCommentInfoAgreeService circleCommentInfoAgreeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CircleCommentInfoService circleCommentInfoService;
	//赞成
	public String agreeCount()
	{
		
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
//			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(1,UserInfo.class);
//			setCurrentUser(user);
			if(getCurrentUser()==null)
			{
				json.put("user", "usernull");
				form.setJsonMsg("您还没有登录...", true, json);
				return JSON_PAGE;
			}
			System.out.println(form.getId());
			
			CircleCommentInfoAgreeExample agreeExmple = new CircleCommentInfoAgreeExample();
			agreeExmple.createCriteria().andUseridEqualTo(user.getUserid()).andCcidEqualTo(form.getId());
			List<CircleCommentInfoAgree> agrees = circleCommentInfoAgreeService.selectByExample(agreeExmple);
			if(agrees.size()>0)
			{
				json.put("agree", "agree");
				form.setJsonMsg("您已经赞同了...", true, json);
				return JSON_PAGE;
			}
			CircleCommentInfoAgree agree = new CircleCommentInfoAgree();
			agree.setAid(String.valueOf(UUID.randomUUID()));
			agree.setCcid(form.getId());
			agree.setUserid(user.getUserid());
			agree.setAgreedate(new Date());
			int result = circleCommentInfoAgreeService.insert(agree);
			System.out.println(form.getId());
			CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(form.getId(), CircleCommentInfo.class);
			String df1 = circleCommentInfo.getDef1();
			if(df1==null)
			{
				df1="0";
				circleCommentInfo.setDef1("0");
			}
			int df = Integer.parseInt(df1);
			df = df+1;
			circleCommentInfo.setDef1(String.valueOf(df));
			int count = circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
			if(result>0 && count>0)
			{
				json.put("count", count);
				form.setJsonMsg("success", true, json);
				//form.setJsonMsg("成功", false, null);
				return JSON_PAGE;
			}else{
				form.setJsonMsg("失败", false, null);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常CircleCommentInfoAgreeAction类的方法agreeCount：" + e.getMessage());
		}
		return JSON_PAGE;
	}
	//不赞成
	public String notAgreeCount()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			if(getCurrentUser()==null)
			{
				json.put("user", "usernull");
				form.setJsonMsg("您还没有登录...", true, json);
				return JSON_PAGE;
			}
			System.out.println(form.getId());
			CircleCommentInfoAgreeExample agreeExmple = new CircleCommentInfoAgreeExample();
			agreeExmple.createCriteria().andUseridEqualTo(user.getUserid()).andCcidEqualTo(form.getId());
			List<CircleCommentInfoAgree> agrees = circleCommentInfoAgreeService.selectByExample(agreeExmple);
			if(agrees.size()>0)
			{
				json.put("agree", "agree");
				form.setJsonMsg("您已经不赞同了...", true, json);
				return JSON_PAGE;
			}
			CircleCommentInfoAgree agree = new CircleCommentInfoAgree();
			agree.setAid(String.valueOf(UUID.randomUUID()));
			agree.setCcid(form.getId());
			agree.setUserid(user.getUserid());
			agree.setAgreedate(new Date());
			int result = circleCommentInfoAgreeService.insert(agree);
			if(result>0)
			{
				form.setJsonMsg("成功", false, null);
				return JSON_PAGE;
			}else{
				form.setJsonMsg("失败", false, null);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常CircleCommentInfoAgreeAction类的方法disfavourCount：" + e.getMessage());
		}
		return JSON_PAGE;
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		
		return form;
	}

}
