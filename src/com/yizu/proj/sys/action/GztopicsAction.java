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
import com.renren.api.client.services.UserService;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.GztopicsForm;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.Gztopics;
import com.yizu.proj.sys.beans.GztopicsExample;
import com.yizu.proj.sys.beans.Inform;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.service.CircleCommentInfoService;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.GztopicsService;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.utils.Contents;

@Scope("prototype")
@Controller("gztopicsAction")
public class GztopicsAction extends BaseAction implements ModelDriven{
	private static final long serialVersionUID = 1L;
	private GztopicsForm form = new GztopicsForm();
	@Autowired
	private GztopicsService gztopicsService;
	@Autowired
	private InformService informService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired 
	private CircleDetailInfoService circleDetailInfoService;
	@Autowired 
	private CircleCommentInfoService circleCommentInfoService;
	public String getAll()
	{
		System.out.println("----------------------------------------");
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			GztopicsExample gztopicsExample = new GztopicsExample();
			gztopicsExample.createCriteria();
			List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
			json.put("gztopics", gztopics);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JSON_PAGE;
	}
	/**
	 * 关注话题
	 * @return
	 */
	public String gzTopic()
	{
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			boolean islogin = false;
			if (getCurrentUser() != null) {
				islogin = true;
				json.put("islogin", islogin);
				
				UserInfo user = getCurrentUser();
				GztopicsExample gztopicsExample = new GztopicsExample();
				System.out.println(form.getTid());
				System.out.println(user.getUserid());
				gztopicsExample.createCriteria().andCircledetailidEqualTo(form.getTid()).andUseridEqualTo(user.getUserid());
				int gzSize = gztopicsService.countByExample(gztopicsExample);
				if(gzSize>0)
				{
					json.put("result", "你已经关注该话题了！");
					form.setJsonMsg("你已经关注该话题了！", true, json);
				}else{
					GztopicsExample gztopicsExample2 = new GztopicsExample();
					gztopicsExample2.createCriteria().andUseridEqualTo(user.getUserid());
					int usergz = gztopicsService.countByExample(gztopicsExample2);
//					if(usergz>100)
//					{
//						json.put("result", "你最多只能关注100个话题！");
//						form.setJsonMsg("你最多只能关注100个话题！", true, json);
//					}else{
						Gztopics gztopics = new Gztopics();
						gztopics.setGzid(String.valueOf(UUID.randomUUID()));
						gztopics.setCreatetime(new Date());
						gztopics.setUserid(user.getUserid());
						gztopics.setCircledetailid(form.getTid());
						gztopics.setDef1("0");
						gztopics.setDef2("0");
						gztopics.setDef3("0");
						gztopics.setDef4("0");
						int result = gztopicsService.insert(gztopics);
						
						//更新话题关注数量
						CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getTid(), CircleDetailInfo.class);
						if(circleDetailInfo.getDef3()==null)
						{
							circleDetailInfo.setDef3("0");
						}
						
						informGztopics(circleDetailInfo);
						
						circleDetailInfo.setDef3(String.valueOf(Integer.parseInt(circleDetailInfo.getDef3())+1));
						int resdetail = circleDetailInfoService.updateByPrimaryKey(circleDetailInfo);
						if(result>0)
						{
							json.put("result", "关注成功！");
							form.setJsonMsg("关注成功！", true, json);
						}else{
							json.put("result", "关注失败！");
							form.setJsonMsg("关注话题失败！", true, json);
						}
//					}
				}
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				json.put("result", "您还未登录，请先登录吧！");
				form.setJsonMsg("您还未登录，请先登录吧！", true, json);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 关注话题通知
	 * @param circleDetailInfo
	 */
	public void informGztopics(CircleDetailInfo circleDetailInfo)
	{
		try {
			UserInfo user = getCurrentUser();
			Inform inform = new Inform();
			inform.setIfid(UUID.randomUUID()+"");
			inform.setCircleid(circleDetailInfo.getCircleid());
			inform.setUserid(user.getUserid());
			inform.setTouserid(circleDetailInfo.getUserid());
			inform.setCircledetailid(circleDetailInfo.getCircledetailid());
			inform.setGzid(Contents.DEF_NULL);
			inform.setCircledetailtype(Contents.TOPIC);
			inform.setCreatetime(new Date());
			inform.setInformcontent(Contents.DEF_NULL);
			inform.setInformtype(Contents.GZ_TOPIC);
			inform.setIsread(null);
			inform.setDef1(Contents.DEF_NULL);
			inform.setDef2(Contents.DEF_NULL);
			inform.setDef3(Contents.DEF_NULL);
			inform.setDef4(Contents.DEF_NULL);
			int informres = informService.insert(inform);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}

}
