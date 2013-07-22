package com.yizu.proj.sys.action;

import groovy.ui.Console;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.config.AppParametersConfig;
import com.yizu.proj.sys.action.form.CircleInfoForm;
import com.yizu.proj.sys.beans.CiecleDetailImg;
import com.yizu.proj.sys.beans.CiecleDetailImgExample;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.CircleInfoExample;
import com.yizu.proj.sys.beans.Circlerecommend;
import com.yizu.proj.sys.beans.CirclerecommendExample;
import com.yizu.proj.sys.beans.Circletag;
import com.yizu.proj.sys.beans.CircletagExample;
import com.yizu.proj.sys.beans.Circletagrelate;
import com.yizu.proj.sys.beans.CircletagrelateExample;
import com.yizu.proj.sys.beans.TagTopic;
import com.yizu.proj.sys.beans.TagTopicExample;
import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserCircleRlsExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.service.CiecleDetailImgService;
import com.yizu.proj.sys.service.CircleInfoService;
import com.yizu.proj.sys.service.CirclerecommendService;
import com.yizu.proj.sys.service.CircletagService;
import com.yizu.proj.sys.service.CircletagrelateService;
import com.yizu.proj.sys.service.TagTopicService;
import com.yizu.proj.sys.service.UserCircleRlsService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.utils.DateUtil;

@Scope("prototype")
@Controller("circleInfoAction")
public class CircleInfoAction extends BaseAction implements ModelDriven {

    private static final long serialVersionUID = -1517391340327305523L;
    private CircleInfoForm form = new CircleInfoForm();

    @Autowired
    private CircleInfoService circleInfoService;
    @Autowired
    private UserCircleRlsService userCircleRlsService;
    @Autowired 
    private UserInfoService userInfoService;
    @Autowired
    private TagTopicService tagTopicService;
    @Autowired
    private CircletagService circletagService;
    @Autowired
    private CircletagrelateService circletagrelateService;
    @Autowired
    private CiecleDetailImgService ciecleDetailImgService;
    @Autowired
    private CirclerecommendService circlerecommendService;
    /**
     * 删除刚刚上传的文件，删除失败，不用处理
     * @return
     */
    public String ajaxDeleteCurrentFile(){
    	try {
    		String path = AppParametersConfig.getParameter("file.base.path");
			File file = new File(path + form.getFilename());
			if (file != null && file.isFile()) {
				FileUtils.deleteQuietly(file);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
    	form.setJsonMsg("删除成功", true, null);
		return JSON_PAGE;
	}
    
    public String ajaxDeleteOldFile(){
    	try {
    		if (StringUtils.isBlank(form.getId())) {
				form.setJsonMsg("没找到当前图片，请刷新后重试！", false, null);
				return JSON_PAGE;
			}
    		
    		CiecleDetailImg ciecleDetailImg = (CiecleDetailImg)baseService.selectByPrimaryKey(form.getId(), CiecleDetailImg.class);
    		if (ciecleDetailImg == null) {
    			form.setJsonMsg("您删除的图片不存在，或者已被管理员删除，请刷新后重试！", false, null);
				return JSON_PAGE;
			}
    		
    		CircleDetailInfo circleDetailInfo = (CircleDetailInfo)baseService.selectByPrimaryKey(ciecleDetailImg.getCircledetailid(), CircleDetailInfo.class);
    		if (circleDetailInfo == null) {
    			form.setJsonMsg("当前图片所在的圈子详细已经被删除，请刷新后重试！", false, null);
				return JSON_PAGE;
			}
    		
    		if (!circleDetailInfo.getUserid().equals(getCurrentUser().getUserid())) {
    			form.setJsonMsg("该图片不是您上传的，不能删除！", false, null);
				return JSON_PAGE;
			}
    		
    		baseService.deleteByPrimaryKey(ciecleDetailImg.getCdid(), CiecleDetailImg.class);
			form.setJsonMsg("删除成功", true, null);
			
			String path = AppParametersConfig.getParameter("file.base.path");
    		
    		File bigCircleDetialImagePath = new File(path+ciecleDetailImg.getBigimg());
        	File middleCircleDetialImagePath = new File(path+ciecleDetailImg.getMiddleimg());
        	File smallICircleDetialmagePath = new File(path+ciecleDetailImg.getSmallimg());
        	
			try {
				if (bigCircleDetialImagePath != null && bigCircleDetialImagePath.isFile()) {
					FileUtils.deleteQuietly(bigCircleDetialImagePath);
				}
				if (middleCircleDetialImagePath != null && middleCircleDetialImagePath.isFile()) {
					FileUtils.deleteQuietly(middleCircleDetialImagePath);
				}
				if (smallICircleDetialmagePath != null && smallICircleDetialmagePath.isFile()) {
					FileUtils.deleteQuietly(smallICircleDetialmagePath);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("删除失败，请稍后重试！", true, null);
		}
		return JSON_PAGE;
	}

    
    public String swfUpload(){
    	byte[] imgBufTemp = new byte[102401];
    	StringBuilder json = new StringBuilder();
		InputStream stream = null;
		BufferedOutputStream bos = null;
		String fileUrl = "";
		try {
			if (ServletFileUpload.isMultipartContent(getRequest())) {
				if(form.getFiledata() != null){
					stream = new FileInputStream(form.getFiledata());
					String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + RandomUtils.nextInt();
					// 得到文件的扩展名(无扩展名时将得到全名)
					String ext = form.getFilename().substring(form.getFilename().lastIndexOf(".") + 1);
					String fileName = prefix + "." + ext;
					String savePath = AppParametersConfig.getParameter("file.tmp.path") + fileName;
					fileUrl = "uploadPath/tmp/" + fileName;
					bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
					int length;
					while ((length = stream.read(imgBufTemp)) != -1) {
						bos.write(imgBufTemp, 0, length);
					}
				}
				
				json.append("{");
				json.append("'");
				json.append("fileUrl");
				json.append("':'");
				json.append(fileUrl.toString());
				json.append("'");
				Enumeration<String> pNames = getRequest().getParameterNames();
				String pName;
				while (pNames.hasMoreElements()) {
					json.append(",");
					pName = (String) pNames.nextElement();
					json.append("'");
					json.append(pName);
					json.append("':'");
					json.append(getRequest().getParameter(pName));
					json.append("'");
				}
				json.append("}");
				
				form.setJsonText(json.toString());
			}
		} catch (IOException e) {
			  LOG.error(e.getMessage(), e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				}
			}
		}
    	return JSON_PAGE;
    }
    
    public String uploadFile(){
        try{
            File uploadPathDir = new File(AppParametersConfig.getParameter("file.tmp.path"));
            if(!uploadPathDir.exists()) uploadPathDir.mkdirs();
            FileOutputStream fos = null;
            FileInputStream fis = null;
            File[] files = form.getUpFile();

            //只有一个图片附件
            if (form.getUpFile() != null && form.getUpFile().length > 0) {
                // 拼接上传文件的新文件名
                int point = form.getUpFileFileName()[0].lastIndexOf(".");
                StringBuffer newName = new StringBuffer();
                newName.append(System.currentTimeMillis());
                newName.append(0);
                newName.append(form.getUpFileFileName()[0].substring(point));

                File f = new File(uploadPathDir, newName.toString());

                // 以服务器的文件保存地址和原文件名建立上传文件输出流
                fos = new FileOutputStream( f );
                fis = new FileInputStream(files[0]);
                byte[] buffer = new byte[1024 * 1024];
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();// 注意：流应当关闭。
                fis.close();

                form.setJsonMsg("上传成功！", true, "uploadPath/tmp/" + newName.toString());
            } else{
                form.setJsonMsg("上传失败,请选择文件！", true, null);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("上传失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
    }
    
    private String checkDirExists(){
    	String currDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
    	
    	File bigImagePath = new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath")+currDate+"/");
    	File middleImagePath = new File(AppParametersConfig.getParameter("file.circleInfo.middleImagePath")+currDate+"/");
    	File smallImagePath = new File(AppParametersConfig.getParameter("file.circleInfo.smallImagePath")+currDate+"/");
    	
    	File bigCircleDetialImagePath = new File(AppParametersConfig.getParameter("file.circleDetial.bigImagePath")+currDate+"/");
    	File middleCircleDetialImagePath = new File(AppParametersConfig.getParameter("file.circleDetial.middleImagePath")+currDate+"/");
    	File smallICircleDetialmagePath = new File(AppParametersConfig.getParameter("file.circleDetial.smallImagePath")+currDate+"/");
    	
        if(!bigImagePath.exists()) bigImagePath.mkdirs();
        if(!middleImagePath.exists()) middleImagePath.mkdirs();
        if(!smallImagePath.exists()) smallImagePath.mkdirs();
        if(!bigCircleDetialImagePath.exists()) bigImagePath.mkdirs();
        if(!middleCircleDetialImagePath.exists()) middleImagePath.mkdirs();
        if(!smallICircleDetialmagePath.exists()) smallImagePath.mkdirs();
        
        return currDate;
    }
    

    public String create() {
        try {
        	if(getCurrentUser()==null)
        	{
        		return "index";
        	}else{
	        	TagTopicExample tagtopic = new TagTopicExample();
	        	tagtopic.createCriteria().andDisplayEqualTo(1);
	        	List<TagTopic> tag = tagTopicService.selectByExample(tagtopic);
	        	form.setTag(tag);
        	}
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "chuangjianquanzi";
    }
    public String chuangjianquanzi() {
        try {
//        	UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(1, UserInfo.class);
//        	setCurrentUser(user);
        	if(getCurrentUser()==null)
        	{
        		return "index";
        	}else{
	        	TagTopicExample tagtopic = new TagTopicExample();
	        	List<TagTopic> tag = tagTopicService.selectByExample(tagtopic);
	        	form.setTag(tag);
//	        	CircletagExample circletagExample = new CircletagExample();
//	        	circletagExample.setLimitStart(0);
//	        	circletagExample.setRowsPerPage(20);
//	        	List<Circletag> circletags = circletagService.selectByExample(circletagExample);
//	        	form.setCircleTags(circletags);
        	}
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "chuangjianquanzi";
    }
    public String ajaxSearchTag()
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	try {
        	CircletagExample circletagExample = new CircletagExample();
        	circletagExample.createCriteria().andTagnameLike("%"+form.getId().trim()+"%");
        	circletagExample.setLimitStart(0);
        	circletagExample.setRowsPerPage(10);
        	List<Circletag> circletags = circletagService.selectByExample(circletagExample);
        	json.put("circletags", circletags);
        	form.setJsonMsg("success", true, json);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
		return JSON_PAGE;
    }
    public String ajaxCreateTag(){
    	Map<String, Object> json = new HashMap<String, Object>();
    	try {
    		CircletagExample circletagExample = new CircletagExample();
    		circletagExample.createCriteria().andTagnameEqualTo(form.getId().trim());
    		List circletags = circletagService.selectByExample(circletagExample);
    		if(circletags.size()<=0)
    		{
    			String tagName = form.getId().trim();
    			if(tagName.length()<30)
    			{
    				Circletag circletag = new Circletag();
    	    		circletag.setTagname(tagName);
    	    		circletag.setTagcreatetime(new Date());
    	    		int s = circletagService.insert(circletag);
    	    		circletags = circletagService.selectByExample(circletagExample);
    			}
    		}
    		json.put("circletags", circletags);
    		form.setJsonMsg("success", true, json);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("success", true, json);
        }
		return JSON_PAGE;
    }
    private int getTagid(String title)
    {
    	int tid = 0;
    	try {
    		CircletagExample circletagExample = new CircletagExample();
    		circletagExample.createCriteria().andTagnameEqualTo(title);
    		List circletags = circletagService.selectByExample(circletagExample);
    		if(circletags.size()<=0)
    		{
    			if(title.length()<30)
    			{
    				Circletag circletag = new Circletag();
    	    		circletag.setTagname(title);
    	    		circletag.setTagcreatetime(new Date());
    	    		int s = circletagService.insert(circletag);
    			}
    			circletags = circletagService.selectByExample(circletagExample);
    			Circletag circletag = (Circletag) circletags.get(0);
    			tid = circletag.getCtid();
    		}else{
    			Circletag circletag = (Circletag) circletags.get(0);
    			tid = circletag.getCtid();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return tid;
    }
    public String ajaxCreatequanzi()
    {
    	try {
    		if(getCurrentUser()==null)
        	{
        		form.setJsonMsg("您还未登录，请先登录吧！", false, null);
        		return JSON_PAGE;
        	}
    		String circlename = form.getInstance().getCirclename();
    		if(circlename=="给你的圈子取个名字吧"|| circlename=="" || circlename==null || circlename.length()<=0)
    	    {
    	        form.setJsonMsg("请填写圈子名称", false, null);
    	        return JSON_PAGE;
    	    }
    	    if(circlename.length()>=60)
    	    {
    	        form.setJsonMsg("标题长度不能超过60个字", false, null);
    	        return JSON_PAGE;
    	    }
    	    String circletag = form.getInstance().getCircletag();
    	    if(circletag=="每个关键字用空格隔开"|| circletag=="" || circletag==null || circletag.length()<=0)
    	    {
    	    	form.setJsonMsg("请填写关键字", false, null);
    	    	return JSON_PAGE;
    	    }
    	    
    	    
//    	    String[] str = circletag.split(" ");
//    	    String strs = "";
//    	    if(str.length>3)
//    	    {
//    	        form.setJsonMsg("圈子关键字不能超过3个", false, null);
//    	        return JSON_PAGE;
//    	    }
    	    
    	    
    	    
    	    String summary = form.getInstance().getSummary();
    	    if(summary=="" || summary==null || summary=="每个关键字用空格隔开" || summary.length()<=0)
    	    {
    	    	form.setJsonMsg("请填写圈子简介", false, null);
    	    	return JSON_PAGE;
    	    }
    	    if(summary.length()>=2000)
    	    {
    	        form.setJsonMsg("标题长度不能超过200个字", false, null);
    	        return JSON_PAGE;
    	    }
//    	    String ids = form.getIds();
//    	    if(ids==null || ids.length()<=0 || ids=="")
//    	    {
//    	        form.setJsonMsg("请选择圈子标签!", false, null);
//    	        return JSON_PAGE;
//    	    }
    	    String imageName = form.getInstance().getCirclesmallimg();
    	    if(imageName.length()<10)
    	    {
    	        form.setJsonMsg("请上传一张图片", false, null);
    	        return JSON_PAGE;
    	    }
    	    File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
            if (LOG.isDebugEnabled()) {
        		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
			}
            String currDate = checkDirExists() + "/";
            try {
        	   FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath")+currDate+imageName));
               thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleInfo.middleImagePath"), AppParametersConfig.getParameter("file.circleInfo.smallImagePath"));
               FileUtils.deleteQuietly(temFile);
            } catch (Exception e) {
			// TODO: handle exception
            }
    		CircleInfo circleInfo = new CircleInfo();
    		UUID ccid = UUID.randomUUID();
    		circleInfo.setUserid(getCurrentUser().getUserid());
    		circleInfo.setCircleid(ccid.toString());
    		circleInfo.setCirclename(circlename);
    		circleInfo.setCirclebigimg("uploadPath/circle/big/" + currDate + imageName);
    		circleInfo.setCirclemiddleimg("uploadPath/circle/middle/" + currDate + imageName);
    		circleInfo.setCirclesmallimg("uploadPath/circle/small/" + currDate + imageName);
    		circleInfo.setSummary(summary);
    		circleInfo.setCircletag(circletag);
    		circleInfo.setDef1(form.getInstance().getDef1());
    		circleInfo.setCreatedatetime(new Date());
    		circleInfo.setJoincount(1);
    		circleInfo.setComcount(0);
    		circleInfo.setVisitcount(0);
    		circleInfo.setCsort(0);
    		circleInfo.setIstop(0);
    		circleInfo.setDisplay(0);
    		circleInfo.setDef3(form.getInstance().getDef3());
    		try {
    			int circlei = circleInfoService.insert(circleInfo);
        		UserCircleRls ucr = new UserCircleRls();
                UserInfo user = getCurrentUser();
				ucr.setCircleid(String.valueOf(ccid));
				ucr.setJoindatetime(new Date());
				ucr.setJtype("1");
				ucr.setUcid(UUID.randomUUID()+"");
				ucr.setUcRole(1);
				ucr.setUserid(user.getUserid());
				try {
					int res = userCircleRlsService.insert(ucr);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				String title1 = form.getTitle1();
				String title2 = form.getTitle2();
				String title3 = form.getTitle3();
				String title4 = form.getTitle4();
				int tid1 = getTagid(title1);
				int tid2 = getTagid(title2);
				int tid3 = getTagid(title3);
				int tid4 = getTagid(title4);
				for (int i = 0; i < 4; i++) {
					Circletagrelate circletagrelate = new Circletagrelate();
					circletagrelate.setId(UUID.randomUUID()+"");
					circletagrelate.setCircleid(String.valueOf(ccid));
					if(i==0){circletagrelate.setCtid(tid1);}
					if(i==1){circletagrelate.setCtid(tid2);}
					if(i==2){circletagrelate.setCtid(tid3);}
					if(i==3){circletagrelate.setCtid(tid4);}
					circletagrelate.setDef1(i+"");
					try {
						int ar = circletagrelateService.insert(circletagrelate);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				if(circlei>0)
				{
					Map<String, Object> json = new HashMap<String, Object>();
					json.put("circleid", String.valueOf(ccid));
					form.setJsonMsg("创建圈子成功！", true, json);
					return JSON_PAGE;
				}else{
        	        form.setJsonMsg("创建圈子失败！", false, null);
        	        return JSON_PAGE;
				}
    		} catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
//    	try {
//        	if(getCurrentUser()==null)
//        	{
//        		form.setJsonMsg("您还未登录，请先登录吧！", false, null);
//        		return JSON_PAGE;
//        	}else{
//        		String circlename = form.getInstance().getCirclename();
//        		if(circlename=="给你的圈子取个名字吧"|| circlename=="" || circlename==null || circlename.length()<=0)
//        	    {
//        	        form.setJsonMsg("请填写圈子名称", false, null);
//        	        return JSON_PAGE;
//        	    }
//        	    if(circlename.length()>=60)
//        	    {
//        	        form.setJsonMsg("标题长度不能超过60个字", false, null);
//        	        return JSON_PAGE;
//        	    }
//        	    String circletag = form.getInstance().getCircletag();
//        	    if(circletag=="每个关键字用空格隔开"|| circletag=="" || circletag==null || circletag.length()<=0)
//        	    {
//        	    	form.setJsonMsg("请填写关键字", false, null);
//        	    	return JSON_PAGE;
//        	    }
        	    
        	    
//        	    String[] str = circletag.split(" ");
//        	    String strs = "";
//        	    if(str.length>3)
//        	    {
//        	        form.setJsonMsg("圈子关键字不能超过3个", false, null);
//        	        return JSON_PAGE;
//        	    }
        	    
        	    
        	    
//        	    String summary = form.getInstance().getSummary();
//        	    if(summary=="" || summary==null || summary=="每个关键字用空格隔开" || summary.length()<=0)
//        	    {
//        	    	form.setJsonMsg("请填写圈子简介", false, null);
//        	    	return JSON_PAGE;
//        	    }
//        	    if(summary.length()>=2000)
//        	    {
//        	        form.setJsonMsg("标题长度不能超过200个字", false, null);
//        	        return JSON_PAGE;
//        	    }
//        	    String ids = form.getIds();
//        	    if(ids==null || ids.length()<=0 || ids=="")
//        	    {
//        	        form.setJsonMsg("请选择圈子标签!", false, null);
//        	        return JSON_PAGE;
//        	    }
//        	    String imageName = form.getInstance().getCirclesmallimg();
//        	    if(imageName.length()<10)
//        	    {
//        	        form.setJsonMsg("请上传一张图片", false, null);
//        	        return JSON_PAGE;
//        	    }
//        	    File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
//                if (LOG.isDebugEnabled()) {
//            		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
//    			}
//                String currDate = checkDirExists() + "/";
//                try {
//            	   FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath")+currDate+imageName));
//                   thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleInfo.middleImagePath"), AppParametersConfig.getParameter("file.circleInfo.smallImagePath"));
//                   FileUtils.deleteQuietly(temFile);
//                } catch (Exception e) {
//				// TODO: handle exception
//                }
//        		CircleInfo circleInfo = new CircleInfo();
//        		UUID ccid = UUID.randomUUID();
//        		circleInfo.setUserid(getCurrentUser().getUserid());
//        		circleInfo.setCircleid(ccid.toString());
//        		circleInfo.setCirclename(circlename);
//        		circleInfo.setCirclebigimg("uploadPath/circle/big/" + currDate + imageName);
//        		circleInfo.setCirclemiddleimg("uploadPath/circle/middle/" + currDate + imageName);
//        		circleInfo.setCirclesmallimg("uploadPath/circle/small/" + currDate + imageName);
//        		circleInfo.setSummary(summary);
//        		circleInfo.setCircletag(circletag);
//        		circleInfo.setDef1(form.getInstance().getDef1());
//        		circleInfo.setCreatedatetime(new Date());
//        		circleInfo.setJoincount(1);
//        		circleInfo.setComcount(0);
//        		circleInfo.setVisitcount(0);
//        		circleInfo.setCsort(0);
//        		circleInfo.setIstop(0);
//        		circleInfo.setDisplay(0);
//        		circleInfo.setDef3(form.getInstance().getDef3());
//        		try {
//        			int circlei = circleInfoService.insert(circleInfo);
//	        		UserCircleRls ucr = new UserCircleRls();
//	                UserInfo user = getCurrentUser();
//					ucr.setCircleid(String.valueOf(ccid));
//					ucr.setJoindatetime(new Date());
//					ucr.setJtype("1");
//					ucr.setUcid(UUID.randomUUID()+"");
//					ucr.setUcRole(1);
//					ucr.setUserid(user.getUserid());
//					try {
//						int res = userCircleRlsService.insert(ucr);
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//					String[] tids = form.getIds().split(",");
//					for(int i = 0;i<tids.length;i++){
//						String tid = tids[i].trim();
//						Circletagrelate circletagrelate = new Circletagrelate();
//						circletagrelate.setId(UUID.randomUUID()+"");
//						circletagrelate.setCircleid(String.valueOf(ccid));
//						circletagrelate.setCtid(Integer.parseInt(tid));
//						circletagrelate.setDef1(i+"");
//						try {
//							int ar = circletagrelateService.insert(circletagrelate);
//						} catch (Exception e) {
//							// TODO: handle exception
//						}
//					}
//					if(circlei>0)
//					{
//						Map<String, Object> json = new HashMap<String, Object>();
//						json.put("circleid", String.valueOf(ccid));
//						form.setJsonMsg("创建圈子成功！", true, json);
//						return JSON_PAGE;
//					}else{
//	        	        form.setJsonMsg("创建圈子失败！", false, null);
//	        	        return JSON_PAGE;
//					}
//        		} catch (Exception e) {
//					// TODO: handle exception
//        			 LOG.error(e.getMessage(), e);
//        	         form.setJsonMsg("创建圈子失败！", false, null);
//        	         return JSON_PAGE;
//				}
//        	}
//        	
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//            form.setJsonMsg("创建圈子失败！", false, null);
//        }
//    	
//        return JSON_PAGE;
    }
    public String ajaxCreate() {
        try {
            if (getCurrentUser() == null) {
                form.setJsonMsg("您还未登录，请先登录吧！", false, null);
            } else{
                if(form != null && form.getInstance() != null) {
                	UUID ccid = UUID.randomUUID();
                    form.getInstance().setCircleid(String.valueOf(ccid));
                    form.getInstance().setCreatedatetime(new Date());
                    form.getInstance().setUserid(getCurrentUser().getUserid());
                    form.getInstance().setJoincount(1);
                    form.getInstance().setComcount(0);
                    form.getInstance().setVisitcount(0);
                    form.getInstance().setCsort(0);
                    form.getInstance().setIstop(0);
                    form.getInstance().setDisplay(0);
                    
                    
                    UserCircleRls ucr = new UserCircleRls();
                    UserInfo user = getCurrentUser();
					ucr.setCircleid(String.valueOf(ccid));
					ucr.setJoindatetime(new Date());
					ucr.setJtype("1");
					ucr.setUcid(UUID.randomUUID()+"");
					ucr.setUcRole(1);
					ucr.setUserid(user.getUserid());
					int res = userCircleRlsService.insert(ucr);
                    
                    String imageName = form.getInstance().getCirclebigimg();
                    if (imageName.indexOf("/") != -1) {
                    	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
					}
                    
                    String currDate = checkDirExists() + "/";
                    
                    if (LOG.isDebugEnabled()) {
                		LOG.debug("currDate：" + currDate);
        			}
                    
                    File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
                    if (LOG.isDebugEnabled()) {
                		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
        			}
                    
                    FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath")+currDate+imageName));
                    
                    thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleInfo.middleImagePath"), AppParametersConfig.getParameter("file.circleInfo.smallImagePath"));
                    
                    FileUtils.deleteQuietly(temFile);
                    
                    form.getInstance().setCirclebigimg("uploadPath/circle/big/" + currDate + imageName);
                    form.getInstance().setCirclemiddleimg("uploadPath/circle/middle/" + currDate + imageName);
                    form.getInstance().setCirclesmallimg("uploadPath/circle/small/" + currDate + imageName);
                    int i = circleInfoService.insert(form.getInstance());
                    
                    
                    //创建圈子的时候 往user_circle_rls表里 添加一条信息  当前用户圈子id  Uc_role（角色）=1  Jtype=1
                    Map<String,Object> json = new HashMap<String,Object>();
                    if(i==1){
             			json.put("ccid", String.valueOf(ccid));
                    }
                    form.setJsonMsg("添加成功，赶快为圈子添加内容吧！", true, json);
                } else{
                    form.setJsonMsg("您输入的要素不足，请重新输入！", false, null);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("添加失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
    }
    
    public String update() {
    	try {
    		if (getCurrentUser() != null) {
    			if (StringUtils.isNotBlank(form.getId())) {
        			CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(form.getId(), CircleInfo.class); 
        			if (circleInfo != null) {
        				if(getCurrentUser().getUserid().equals(circleInfo.getUserid()))
        				{
        				TagTopicExample tagtopic = new TagTopicExample();
        				tagtopic.createCriteria().andDisplayEqualTo(1);
        	        	List<TagTopic> tag = tagTopicService.selectByExample(tagtopic);
        	        	form.setTag(tag);
        				CircletagrelateExample circletagrelateExample = new CircletagrelateExample(); 
        				circletagrelateExample.createCriteria().andCircleidEqualTo(circleInfo.getCircleid());
        				circletagrelateExample.setOrderByClause(" ctid ");
        				List<Circletagrelate> tagCircletagrelates = circletagrelateService.selectByExample(circletagrelateExample);
        				form.setCircletagrelateList(tagCircletagrelates);
        				
        				//推荐圈子6个
        				CirclerecommendExample recommendExample = new CirclerecommendExample();
        				recommendExample.createCriteria().andCircleidEqualTo(form.getId());
        				recommendExample.setOrderByClause(" sort ");
        				List<Circlerecommend> circlerecommend = circlerecommendService.selectByExample(recommendExample);
        				form.setCirclerecommend(circlerecommend);
        				
        				//所有圈子
        				CircleInfoExample circleinfoExample = new CircleInfoExample();
        				circleinfoExample.setOrderByClause(" createDateTime desc");
        				List<CircleInfo> circleinfos = circleInfoService.selectByExample(circleinfoExample);
        				form.setCircleInfoList(circleinfos);
        				
        				form.setInstance(circleInfo);	
        				}else{return "index";}
        			}else{return "index";}
        		}else{return "index";}
    		}else{return "index";}
		} catch (Exception e) {
			  LOG.error(e.getMessage(), e);
			  return "index";
		}
		return "updatecircle";
    }
    public String ajaxUpdatequanzi()
    {
    	try {
    		if (getCurrentUser() == null) {
              form.setJsonMsg("您还未登录，请先登录吧！", false, null);
              return JSON_PAGE;
            }
          if(form != null && form.getInstance() != null) {
        	CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(form.getInstance().getCircleid(), CircleInfo.class); 
			if (circleInfo == null) {
				form.setJsonMsg("您修改的内容已经删除，请刷新后重试！", false, null);
				return JSON_PAGE;
			}
			UserInfo user = getCurrentUser();
			if(!circleInfo.getUserid().equals(user.getUserid()))
			{
        		form.setJsonMsg("亲，这个圈子不是你创建的不能修改哦！", false, null);
				return JSON_PAGE;
			}
			String circlename = form.getInstance().getCirclename();
    		if(circlename=="给你的圈子取个名字吧"|| circlename=="" || circlename==null || circlename.length()<=0)
    	    {
    	        form.setJsonMsg("请填写圈子名称", false, null);
    	        return JSON_PAGE;
    	    }
    	    if(circlename.length()>=60)
    	    {
    	        form.setJsonMsg("标题长度不能超过60个字", false, null);
    	        return JSON_PAGE;
    	    }
    	    
    	    String circletag = form.getInstance().getCircletag();
    	    if(circletag=="每个关键字用空格隔开"|| circletag=="" || circletag==null || circletag.length()<=0)
    	    {
    	    	form.setJsonMsg("请填写关键字", false, null);
    	    	return JSON_PAGE;
    	    }
			String summary = form.getInstance().getSummary();
    	    if(summary=="" || summary==null || summary=="每个关键字用空格隔开" || summary.length()<=0)
    	    {
    	    	form.setJsonMsg("请填写圈子简介", false, null);
    	    	return JSON_PAGE;
    	    }
//			String ids = form.getIds();
//    	    if(ids==null || ids.length()<=0 || ids=="")
//    	    {
//    	        form.setJsonMsg("请选择圈子标签!", false, null);
//    	        return JSON_PAGE;
//    	    }
        	circleInfo.setUpdatedatetime(new Date());
        	circleInfo.setCirclename(form.getInstance().getCirclename());
        	circleInfo.setCircletag(form.getInstance().getCircletag());
        	circleInfo.setSummary(form.getInstance().getSummary());
        	circleInfo.setDef1(form.getInstance().getDef1());
        	circleInfo.setDef3(form.getInstance().getDef3());
            if (!StringUtils.trimToEmpty(form.getInstance().getCirclebigimg()).equals(StringUtils.trimToEmpty(circleInfo.getCirclebigimg()))) {
            	
            	if (LOG.isDebugEnabled()) {
					LOG.debug("图片有更新，原始图片路径：#0,新图片路径：#1", new String[]{circleInfo.getCirclebigimg(), form.getInstance().getCirclebigimg()});
				}
            	
        	    String imageName = form.getInstance().getCirclebigimg();
				if (imageName.indexOf("/") != -1) {
					imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") + 1);
				}

				String currDate = checkDirExists() + "/";

				if (LOG.isDebugEnabled()) {
					LOG.debug("currDate：" + currDate);
				}

				File temFile = new File(AppParametersConfig.getParameter("file.tmp.path") + imageName);
				if (LOG.isDebugEnabled()) {
					LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
				}

				FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath") + currDate + imageName));

				thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleInfo.middleImagePath"), AppParametersConfig
						.getParameter("file.circleInfo.smallImagePath"));
				FileUtils.deleteQuietly(temFile);
				circleInfo.setCirclebigimg("uploadPath/circle/big/" + currDate + imageName);
				circleInfo.setCirclemiddleimg("uploadPath/circle/middle/" + currDate + imageName);
				circleInfo.setCirclesmallimg("uploadPath/circle/small/" + currDate + imageName);
			}else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("修改圈子信息，未更新图片！");
				}
			}
            String title1 = form.getTitle1();
			String title2 = form.getTitle2();
			String title3 = form.getTitle3();
			String title4 = form.getTitle4();
			int tid1 = getTagid(title1);
			int tid2 = getTagid(title2);
			int tid3 = getTagid(title3);
			int tid4 = getTagid(title4);
			CircletagrelateExample circletagrelateExample = new CircletagrelateExample();
            circletagrelateExample.createCriteria().andCircleidEqualTo(circleInfo.getCircleid());
            circletagrelateService.deleteByExample(circletagrelateExample);
			for (int i = 0; i < 4; i++) {
				Circletagrelate circletagrelate = new Circletagrelate();
				circletagrelate.setId(UUID.randomUUID()+"");
				circletagrelate.setCircleid(circleInfo.getCircleid());
				if(i==0){circletagrelate.setCtid(tid1);}
				if(i==1){circletagrelate.setCtid(tid2);}
				if(i==2){circletagrelate.setCtid(tid3);}
				if(i==3){circletagrelate.setCtid(tid4);}
				circletagrelate.setDef1(i+"");
				try {
					int ar = circletagrelateService.insert(circletagrelate);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
            
            String[] recommendId = form.getRecommendId().split(",");
            CirclerecommendExample circlerecommendExample = new CirclerecommendExample();
            circlerecommendExample.createCriteria().andCircleidEqualTo(circleInfo.getCircleid());
            circlerecommendService.deleteByExample(circlerecommendExample);
            for(int r = 0;r < recommendId.length; r++ ){
            	Circlerecommend recommend = new Circlerecommend();
            	recommend.setCrid(UUID.randomUUID()+"");
            	recommend.setCircleid(circleInfo.getCircleid());
            	recommend.setRecommendid(recommendId[r]);
            	recommend.setSort(r);
            	recommend.setDatetime(new Date());
            	circlerecommendService.insert(recommend);
            }
            circleInfoService.updateByPrimaryKeySelective(circleInfo);
            form.setJsonMsg("修改圈子信息成功，赶快为圈子添加内容吧！", true, null);
        } else{
            form.setJsonMsg("您输入的要素不足，请重新输入！", false, null);
        }
    		return JSON_PAGE;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			form.setJsonMsg("修改圈子信息失败，请稍候重试！", false, null);
    		return JSON_PAGE;
		}
		
//    	try {
//            if (getCurrentUser() == null) {
//                form.setJsonMsg("您还未登录，请先登录吧！", false, null);
//            } else{
//                if(form != null && form.getInstance() != null) {
//                	CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(form.getInstance().getCircleid(), CircleInfo.class); 
//        			if (circleInfo == null) {
//        				form.setJsonMsg("您修改的内容已经删除，请刷新后重试！", false, null);
//        				return JSON_PAGE;
//        			}
//        			UserInfo user = getCurrentUser();
//        			if(!circleInfo.getUserid().equals(user.getUserid()))
//        			{
//                		form.setJsonMsg("亲，这个圈子不是你创建的不能修改哦！", false, null);
//        				return JSON_PAGE;
//					}
//        			String circlename = form.getInstance().getCirclename();
//            		if(circlename=="给你的圈子取个名字吧"|| circlename=="" || circlename==null || circlename.length()<=0)
//            	    {
//            	        form.setJsonMsg("请填写圈子名称", false, null);
//            	        return JSON_PAGE;
//            	    }
//            	    if(circlename.length()>=60)
//            	    {
//            	        form.setJsonMsg("标题长度不能超过60个字", false, null);
//            	        return JSON_PAGE;
//            	    }
//            	    
//            	    String circletag = form.getInstance().getCircletag();
//            	    if(circletag=="每个关键字用空格隔开"|| circletag=="" || circletag==null || circletag.length()<=0)
//            	    {
//            	    	form.setJsonMsg("请填写关键字", false, null);
//            	    	return JSON_PAGE;
//            	    }
//        			String summary = form.getInstance().getSummary();
//            	    if(summary=="" || summary==null || summary=="每个关键字用空格隔开" || summary.length()<=0)
//            	    {
//            	    	form.setJsonMsg("请填写圈子简介", false, null);
//            	    	return JSON_PAGE;
//            	    }
//        			String ids = form.getIds();
//            	    if(ids==null || ids.length()<=0 || ids=="")
//            	    {
//            	        form.setJsonMsg("请选择圈子标签!", false, null);
//            	        return JSON_PAGE;
//            	    }
//                	circleInfo.setUpdatedatetime(new Date());
//                	circleInfo.setCirclename(form.getInstance().getCirclename());
//                	circleInfo.setCircletag(form.getInstance().getCircletag());
//                	circleInfo.setSummary(form.getInstance().getSummary());
//                	circleInfo.setDef1(form.getInstance().getDef1());
//                	circleInfo.setDef3(form.getInstance().getDef3());
//                    if (!StringUtils.trimToEmpty(form.getInstance().getCirclebigimg()).equals(StringUtils.trimToEmpty(circleInfo.getCirclebigimg()))) {
//                    	
//                    	if (LOG.isDebugEnabled()) {
//							LOG.debug("图片有更新，原始图片路径：#0,新图片路径：#1", new String[]{circleInfo.getCirclebigimg(), form.getInstance().getCirclebigimg()});
//						}
//                    	
//                	    String imageName = form.getInstance().getCirclebigimg();
//						if (imageName.indexOf("/") != -1) {
//							imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") + 1);
//						}
//
//						String currDate = checkDirExists() + "/";
//
//						if (LOG.isDebugEnabled()) {
//							LOG.debug("currDate：" + currDate);
//						}
//
//						File temFile = new File(AppParametersConfig.getParameter("file.tmp.path") + imageName);
//						if (LOG.isDebugEnabled()) {
//							LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
//						}
//
//						FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath") + currDate + imageName));
//
//						thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleInfo.middleImagePath"), AppParametersConfig
//								.getParameter("file.circleInfo.smallImagePath"));
//						FileUtils.deleteQuietly(temFile);
//						circleInfo.setCirclebigimg("uploadPath/circle/big/" + currDate + imageName);
//						circleInfo.setCirclemiddleimg("uploadPath/circle/middle/" + currDate + imageName);
//						circleInfo.setCirclesmallimg("uploadPath/circle/small/" + currDate + imageName);
//					}else {
//						if (LOG.isDebugEnabled()) {
//							LOG.debug("修改圈子信息，未更新图片！");
//						}
//					}
//                    String[] tids = form.getIds().split(",");
//                    CircletagrelateExample circletagrelateExample = new CircletagrelateExample();
//                    circletagrelateExample.createCriteria().andCircleidEqualTo(circleInfo.getCircleid());
//                    circletagrelateService.deleteByExample(circletagrelateExample);
//					int[] ctid= new int[tids.length];
//					for(int i = 0;i<tids.length;i++){
//						ctid[i]=Integer.parseInt(tids[i]);
//					}
//                    for(int j = 0;j<ctid.length;j++){
//						Circletagrelate circletagrelate = new Circletagrelate();
//						circletagrelate.setId(UUID.randomUUID()+"");
//						circletagrelate.setCircleid(circleInfo.getCircleid());
//						circletagrelate.setCtid(ctid[j]);
//						circletagrelate.setDef1(j+"");
//						try {
//							circletagrelateService.insert(circletagrelate);
//						} catch (Exception e) {
//							// TODO: handle exception
//						}
//					}
//                    
//                    String[] recommendId = form.getRecommendId().split(",");
//                    CirclerecommendExample circlerecommendExample = new CirclerecommendExample();
//                    circlerecommendExample.createCriteria().andCircleidEqualTo(circleInfo.getCircleid());
//                    circlerecommendService.deleteByExample(circlerecommendExample);
//                    for(int r = 0;r < recommendId.length; r++ ){
//                    	Circlerecommend recommend = new Circlerecommend();
//                    	recommend.setCrid(UUID.randomUUID()+"");
//                    	recommend.setCircleid(circleInfo.getCircleid());
//                    	recommend.setRecommendid(recommendId[r]);
//                    	recommend.setSort(r);
//                    	recommend.setDatetime(new Date());
//                    	circlerecommendService.insert(recommend);
//                    }
//                    circleInfoService.updateByPrimaryKeySelective(circleInfo);
//                    form.setJsonMsg("修改圈子信息成功，赶快为圈子添加内容吧！", true, null);
//                } else{
//                    form.setJsonMsg("您输入的要素不足，请重新输入！", false, null);
//                }
//            }
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//            form.setJsonMsg("修改圈子信息失败，请稍候重试！", false, null);
//        }
//        return JSON_PAGE;
    }
    public String ajaxUpdate() {
        try {
            if (getCurrentUser() == null) {
                form.setJsonMsg("您还未登录，请先登录吧！", false, null);
            } else{
                if(form != null && form.getInstance() != null) {
                	
                	CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(form.getInstance().getCircleid(), CircleInfo.class); 
        			if (circleInfo == null) {
        				form.setJsonMsg("您修改的内容已经删除，请刷新后重试！", false, null);
        				return JSON_PAGE;
        			}
        			UserInfo user = getCurrentUser();
//                	if (circleInfo.getUserid() != getCurrentUser().getUserid()) {
        			if(!circleInfo.getUserid().equals(user.getUserid()))
        			{
                		form.setJsonMsg("亲，这个圈子不是你创建的不能修改哦！", false, null);
        				return JSON_PAGE;
					}
                	
                	
                	circleInfo.setUpdatedatetime(new Date());
                	circleInfo.setCirclename(form.getInstance().getCirclename());
                	circleInfo.setCircletag(form.getInstance().getCircletag());
                	circleInfo.setSummary(form.getInstance().getSummary());
                	circleInfo.setDef1(form.getInstance().getDef1());
                	
                    
                    if (!StringUtils.trimToEmpty(form.getInstance().getCirclebigimg()).equals(StringUtils.trimToEmpty(circleInfo.getCirclebigimg()))) {
                    	
                    	if (LOG.isDebugEnabled()) {
							LOG.debug("图片有更新，原始图片路径：#0,新图片路径：#1", new String[]{circleInfo.getCirclebigimg(), form.getInstance().getCirclebigimg()});
						}
                    	
                	    String imageName = form.getInstance().getCirclebigimg();
						if (imageName.indexOf("/") != -1) {
							imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") + 1);
						}

						String currDate = checkDirExists() + "/";

						if (LOG.isDebugEnabled()) {
							LOG.debug("currDate：" + currDate);
						}

						File temFile = new File(AppParametersConfig.getParameter("file.tmp.path") + imageName);
						if (LOG.isDebugEnabled()) {
							LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
						}

						FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleInfo.bigImagePath") + currDate + imageName));

						thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleInfo.middleImagePath"), AppParametersConfig
								.getParameter("file.circleInfo.smallImagePath"));

						FileUtils.deleteQuietly(temFile);
						
						
						circleInfo.setCirclebigimg("uploadPath/circle/big/" + currDate + imageName);
						circleInfo.setCirclemiddleimg("uploadPath/circle/middle/" + currDate + imageName);
						circleInfo.setCirclesmallimg("uploadPath/circle/small/" + currDate + imageName);
					}else {
						if (LOG.isDebugEnabled()) {
							LOG.debug("修改圈子信息，未更新图片！");
						}
					}
                 
                    circleInfoService.updateByPrimaryKeySelective(circleInfo);
                    
                    
                    form.setJsonMsg("修改圈子信息成功，赶快为圈子添加内容吧！", true, null);
                } else{
                    form.setJsonMsg("您输入的要素不足，请重新输入！", false, null);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("修改圈子信息失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
    }
    
    public void thumnail(String imageName, File temFile, String currDate, String middleImagePath, String smallImagePath) throws IOException{
    	try {
    		Float middleScale = 1f;
            Float smallScale = 1f;
			Iterator readers = ImageIO.getImageReadersByFormatName(StringUtils.substringAfterLast(imageName, "."));
			ImageReader reader = (ImageReader) readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(temFile);
			reader.setInput(iis, true);
			
			int middleWidth = 198;
            int smallWidth = 100;
			int temWidth = reader.getWidth(0);
			
			middleScale = Float.valueOf(middleWidth) / Float.valueOf(temWidth);
			smallScale = Float.valueOf(smallWidth) / Float.valueOf(temWidth);
			
			if (middleScale >= 1) {
				 FileUtils.copyFile(temFile, new File(middleImagePath+currDate+imageName));
			}else {
				Thumbnails.of(temFile.getAbsolutePath()).scale(middleScale).toFile(middleImagePath+currDate+imageName);
			}
			if (smallScale >= 1) {
				 FileUtils.copyFile(temFile, new File(smallImagePath+currDate+imageName));
			}else {
				Thumbnails.of(temFile.getAbsolutePath()).scale(smallScale).toFile(smallImagePath+currDate+imageName);
			}
			
		} catch (IOException e) {
			 FileUtils.copyFile(temFile, new File(middleImagePath+currDate+imageName));
			 FileUtils.copyFile(temFile, new File(smallImagePath+currDate+imageName));
			e.printStackTrace();
		}
    }
    
    public String circleInfoPublish(){
    	try {
    		if(getCurrentUser()==null)
    		{
    			return "index";
    		}else{
//    		if (StringUtils.isNotBlank(form.getId())) {
//    			UserInfo user = getCurrentUser();
//    			CircleInfoExample circleInfoExample = new CircleInfoExample();
//    			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
//    					.selectByPrimaryKey(form.getId(), CircleInfo.class);
//    			circleInfoExample.createCriteria();
//    			form.setCircleleft(center_circleInfo);
//    			if (user != null) {
//    				// 是否已经加入该圈子
//    				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
//    				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
//    						.andUseridEqualTo(user.getUserid());
//    				int isAddCircle = 0;
//    				isAddCircle = userCircleRlsService.countByExample(ucrexample);
//    				System.out.println("已经登录===是否已经加入该圈子" + isAddCircle);
//    				form.setIslogin("have");
//    				form.setIsAddCircle(isAddCircle);
//    			}
//    			form.setUser(user);
//    			// 最新加入圈子
//    			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
//    			userCircleRlsExample.createCriteria()
//    					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
//    			userCircleRlsExample.setLimitStart(0);
//    			userCircleRlsExample.setRowsPerPage(5);
//    			userCircleRlsExample.setOrderByClause("joindatetime desc");
//
//    			List<UserCircleRls> userCircleRlss = userCircleRlsService
//    					.selectByExample(userCircleRlsExample);
//    			form.setUserCircleRlss(userCircleRlss);
    			String str = form.getId();
    			String did = null;
    			if(!(str.indexOf(".html")<=0))
    			{
    				int idindexof = str.lastIndexOf(".html");
    				did = str.substring(0,idindexof);
    			}else{
    				did = str;
    			}
    			CircletagrelateExample circleExample = new CircletagrelateExample();
    			circleExample.createCriteria().andCircleidEqualTo(did);
    			List<Circletagrelate> reList = circletagrelateService.selectByExample(circleExample);
    			form.setCircletagrelateList(reList);
    			CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(did, CircleInfo.class); 
    			if (circleInfo != null) {
    				form.setInstance(circleInfo);	
    				return SUCCESS;
				}
    		}
//    		}
		} catch (Exception e) {
			 LOG.error(e.getMessage(), e);
		}
		return "index";
    }
    
    public String ajaxCreateDetail(){
    	try {
			if (getCurrentUser() == null) {
			    form.setJsonMsg("您还未登录，请先登录吧！", false, null);
			    return JSON_PAGE;
			}
			
//			if (StringUtils.isBlank(form.getImages())) {
//				 form.setJsonMsg("亲，您还没上传图片！", false, null);
//			     return JSON_PAGE;
//			}
			String str = form.getCircleDetail().getCircleid();
			String did = null;
			if(!(str.indexOf(".html")<=0))
			{
				int idindexof = str.lastIndexOf(".html");
				did = str.substring(0,idindexof);
				form.getCircleDetail().setCircleid(did);
			}else{
				did = str;
				form.getCircleDetail().setCircleid(did);
			}
			CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(form.getCircleDetail().getCircleid(), CircleInfo.class); 
			if (circleInfo == null) {
				 form.setJsonMsg("亲，这个圈子已经被删除了哦！", false, null);
			     return JSON_PAGE;
			}
			
			form.getCircleDetail().setCircledetailid(String.valueOf(UUID.randomUUID()));
			form.getCircleDetail().setCreatedatetime(new Date());
			form.getCircleDetail().setUpdatedatetime(new Date());
			form.getCircleDetail().setUserid(getCurrentUser().getUserid());
			form.getCircleDetail().setComcount(0);
			form.getCircleDetail().setVisitcount(0);
			form.getCircleDetail().setIstop(0);
			form.getCircleDetail().setDisplay(1);
			String type = form.getTopictype();
			if(Integer.parseInt(type) == 1 ){
				form.getCircleDetail().setDef1(null);
				
			}else if(Integer.parseInt(type) == 2 ){
				form.getCircleDetail().setDef1("1");
			}else if(Integer.parseInt(type) == 3){
				form.getCircleDetail().setDef1("1");
				form.getCircleDetail().setDef2("1");
			}
			
			int result = baseService.insert(form.getCircleDetail());
			if (result < 1) {
				  form.setJsonMsg("添加失败，请稍候重试！", false, null);
				  return JSON_PAGE;
			}
//			if(form.getCircleDetail().getDef1()!=null)
//			{
//				UserInfo user = getCurrentUser();
//				user.setTopicnumber((user.getTopicnumber())+1);
//				int results = userInfoService.updateByPrimaryKey(user);
//			}
			if (LOG.isDebugEnabled()) {
        		LOG.debug("保存圈子详细完成！");
			}
			if(form.getImages().length()>0){
				for (String strImage : form.getImages().split(",")) {
					try {
						String imageName = strImage;
	                    if (imageName.indexOf("/") != -1) {
	                    	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
						}
	                    
	                    String currDate = checkDirExists() + "/";
	                    if(imageName.equals("close_ico.gif"))
	                    {
	                    	
	                    }else{
	                    	 File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
	                         if (LOG.isDebugEnabled()) {
	                     		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
	             			}
	                         
	                         FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleDetail.bigImagePath")+currDate+imageName));
	                         thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleDetail.middleImagePath"), AppParametersConfig.getParameter("file.circleDetail.smallImagePath"));
	                         FileUtils.deleteQuietly(temFile);
	                         
	                         
	     					CiecleDetailImg ci = new CiecleDetailImg();
	     					ci.setCdid(String.valueOf(UUID.randomUUID()));
	     					ci.setCircledetailid(form.getCircleDetail().getCircledetailid());
	     					ci.setBigimg("uploadPath/detail/big/" + currDate + imageName);
	     					ci.setMiddleimg("uploadPath/detail/middle/" + currDate + imageName);
	     					ci.setSmallimg("uploadPath/detail/small/" + currDate + imageName);
	     					ci.setUploadtime(new Date());
	     					
	     					baseService.insert(ci);
	                    }
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
			else{
				CiecleDetailImg ci = new CiecleDetailImg();
				ci.setCdid(String.valueOf(UUID.randomUUID()));
				ci.setCircledetailid(form.getCircleDetail().getCircledetailid());
				ci.setUploadtime(new Date());
				
				baseService.insert(ci);
			}
			
			circleInfo.setComcount(circleInfo.getComcount() + 1);
			baseService.updateByPrimaryKey(circleInfo);
			
			form.setJsonMsg("创建成功，赶紧去看看吧！", true, null);
			
    	} catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("添加失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
    }
    
    public String updateCircleDetail(){
    	try {
    		UserInfo user = getCurrentUser();
    		if(user!=null)
    		{
    			CircleDetailInfo circleDetailInfo = (CircleDetailInfo)baseService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
    			if (circleDetailInfo != null) {
    				//如果是自己创建的
    				if(circleDetailInfo.getUserid().equals(user.getUserid()))
    				{
	    				form.setCircleDetail(circleDetailInfo);	
	    				CircletagrelateExample circleExample = new CircletagrelateExample();
	        			circleExample.createCriteria().andCircleidEqualTo(circleDetailInfo.getCircleid());
	        			List<Circletagrelate> reList = circletagrelateService.selectByExample(circleExample);
	        			form.setCircletagrelateList(reList);
	    				return SUCCESS;
    				}
				}else{
					return "index";
				}
    		}else{
    			return "index";
    		}
		} catch (Exception e) {
			 LOG.error(e.getMessage(), e);
			 return "index";
		}
		return "index";
    }
    
    public String ajaxUpdateDetail(){
    	try {
    		UserInfo user = getCurrentUser();
			if (getCurrentUser() == null) {
			    form.setJsonMsg("您还未登录，请先登录吧！", false, null);
			    return JSON_PAGE;
			}
			
			
			CircleInfo circleInfo = (CircleInfo)circleInfoService.selectByPrimaryKey(form.getCircleDetail().getCircleid(), CircleInfo.class); 
			if (circleInfo == null) {
				 form.setJsonMsg("亲，这个圈子已经被删除了哦,圈子详细已经被锁定不能修改了！", false, null);
			     return JSON_PAGE;
			}
			
			CircleDetailInfo circleDetailInfo = (CircleDetailInfo)baseService.selectByPrimaryKey(form.getCircleDetail().getCircledetailid(), CircleDetailInfo.class);
			if (circleDetailInfo == null) {
				form.setJsonMsg("亲，这个圈子详细已经被删除不能修改，请刷新后重试！", false, null);
			    return JSON_PAGE;
			}
			
//			if (circleDetailInfo.getUserid() != getCurrentUser().getUserid()) {
//        		form.setJsonMsg("亲，这个圈子详不是你创建的不能修改哦！", false, null);
//				return JSON_PAGE;
//			}
			System.out.println("------------circleDetailInfo.getUserid()--------"+circleDetailInfo.getUserid());
			System.out.println("------------user.getUserid()--------"+user.getUserid());
			
			if(!circleDetailInfo.getUserid().equals(user.getUserid()))
			{
				form.setJsonMsg("亲，这个圈子话题不是你创建的不能修改哦！", false, null);
				return JSON_PAGE;
			}
			circleDetailInfo.setTitle(form.getCircleDetail().getTitle());
			circleDetailInfo.setCirclecontent(form.getCircleDetail().getCirclecontent());
			circleDetailInfo.setUpdatedatetime(new Date());
			circleDetailInfo.setCtag(form.getCircleDetail().getCtag());
			circleDetailInfo.setDef4(form.getCircleDetail().getDef4());
			String type = form.getTopictype();
			if(Integer.parseInt(type) == 1 ){
				circleDetailInfo.setDef1(null);
			}else if(Integer.parseInt(type) == 2 ){
				circleDetailInfo.setDef1("1");
			}else if(Integer.parseInt(type) == 3){
				circleDetailInfo.setDef1("1");
				circleDetailInfo.setDef2("1");
			}
			baseService.updateByPrimaryKeySelective(circleDetailInfo);
			
			if (LOG.isDebugEnabled()) {
        		LOG.debug("修改圈子详细完成！");
			}
			if(StringUtils.trimToEmpty(form.getImages()).length() > 0){
			for (String strImage : form.getImages().split(",")) {
				try {
					String imageName = strImage;
                    if (imageName.indexOf("/") != -1) {
                    	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
					}
                    
                    File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
                    
                    String currDate = checkDirExists() + "/";
                    
                    if (LOG.isDebugEnabled()) {
                		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
        			}
                    
                    FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.circleDetail.bigImagePath")+currDate+imageName));
                    thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleDetail.middleImagePath"), AppParametersConfig.getParameter("file.circleDetail.smallImagePath"));
                    FileUtils.deleteQuietly(temFile);
                    
                    if(circleDetailInfo.getCircleDetailImg().size() > 0){
                    	for(CiecleDetailImg img : circleDetailInfo.getCircleDetailImg()){
                    		img.setBigimg("uploadPath/detail/big/" + currDate + imageName);
                    		img.setMiddleimg("uploadPath/detail/middle/" + currDate + imageName);
                    		img.setSmallimg("uploadPath/detail/small/" + currDate + imageName);
                    		img.setUploadtime(new Date());
                    		ciecleDetailImgService.updateByPrimaryKey(img);
                    	}
                    }else{
	                    CiecleDetailImg ci = new CiecleDetailImg();
						ci.setCdid(String.valueOf(UUID.randomUUID()));
						ci.setCircledetailid(form.getCircleDetail().getCircledetailid());
						ci.setBigimg("uploadPath/detail/big/" + currDate + imageName);
						ci.setMiddleimg("uploadPath/detail/middle/" + currDate + imageName);
						ci.setSmallimg("uploadPath/detail/small/" + currDate + imageName);
						ci.setUploadtime(new Date());
						baseService.insert(ci);
                    }
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
			}
//			circleInfo.setComcount(circleInfo.getComcount() + 1);
			baseService.updateByPrimaryKey(circleInfo);
			
			form.setJsonMsg("修改详细成功，赶紧去看看吧！", true, null);
			
    	} catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("修改圈子详细失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
    }
    

    public String getJsonPage(){
        //第一个参数，提示用户，第二个参数：标识操作是否成功true/false  第三个参数为obj对象，不用转换为json格式
        form.setJsonMsg("第一个参数，提示用户，第二个参数：标识操作是否成功true/false  第三个参数为obj对象，不用转换为json格式", true, UserInfoExample.class);
        return JSON_PAGE;
    }

    public CircleInfoForm getModel() {
        return form;
    }
    
}
