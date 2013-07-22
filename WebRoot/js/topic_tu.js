var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var tid = realurl.substring(idindexof + 1,realurl.search(".html"));
topicDetail();
var islogin,t_end_li,u_end_li,user;//热门话题结束标签、热门用户结束标签、当前用户
function topicDetail()
{
	var topicDetailURL = "topicDetailAction_topicDetail.do";
	$.ajax({
		url:topicDetailURL,
		data:{id : tid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			
			islogin = data.data.islogin;
			/**
			 * 话题详细
			 */
			var circleDetailInfo = data.data.circleDetailInfo;
			var commentinfo =  circleDetailInfo.circleCommentInfo;
			
			if(circleDetailInfo==null)
			{
				alert("该话题不存在，点击确定返回话题首页！");
				var url = "quanzi/newTopics.jsp";
				location.href = basePath+url;
				return false;
			}
			var def1 = circleDetailInfo.def1;
			var def2 = circleDetailInfo.def2;
			if(def1!=1 || def2==1)
			{
				alert("该话题不存在，点击确定返回话题首页！");
				var url = "quanzi/newTopics.jsp";
				location.href = basePath+url;
				return false;
			}
			document.title = circleDetailInfo.title;
			var userImg = circleDetailInfo.userinfo.image;
			var userid = circleDetailInfo.userinfo.userid;
			if(userImg.length<5)
			{
				userImg = circleDetailInfo.otheraccountuserimage;
			}
			var tid = circleDetailInfo.circledetailid;
			$("#userImg").attr("src",userImg);
			$("#userImg_a").attr("href","user/"+userid+".html");
			$("#userImg_a").attr("target","_blank");
			
			$("#topicTitle").html(circleDetailInfo.title);
			
			$("#userNick").html(circleDetailInfo.userinfo.nick);
			$("#userNick").attr("href","user/"+userid+".html");
			$("#userNick").attr("target","_blank");
			
			$("#topicCreateDateTime").append(circleDetailInfo.createdatetime);
			var isgz = circleDetailInfo.isgz;
			var gzimg = "";
			if(isgz == 1)
			{
				gzimg = "images/pro_importan_1t.gif";
			}else{
				gzimg = "images/pro_important.gif";
			}
			
			$("#isgzImg_a").html("<img src=\""+gzimg+"\" onclick=\"gzTopic('"+tid+"')\" />");
			$("#topicContent").html(circleDetailInfo.circlecontent);
			var circleImg = circleDetailInfo.circleDetailImg;
			$("#topicImg").html("");
			var picPath ="";
			for ( var i = 0; i < circleImg.length; i++) {
				$("#topicImg").append("<img src="+circleImg[i].bigimg+" onload=\"scaleImage(this,646,10000)\" ></br>");
				picPath = circleImg[0].bigimg;
			}
			picPath = "1mily.com/"+picPath;
			var str =
			"<script type=\"text/javascript\" >"+
			"var jiathis_config={"+
			"pic:\""+picPath+"\","+
			"data_track_clickback:false,"+
			"appkey:{"+
			"\"tsina\":\"2614308697\",\"renren\":\"184905\",\"t163\":\"mBjMmCCPvbKOvvQI\",\"tqq\":\"100255656\" "+
			"}"+
			"}"+
			"</script>";
			$("#fenxiang").append(str);
			if(islogin)
			{
				user = data.data.nowuser;
				var nowUserImg = user.image;
				if(nowUserImg.length<5)
				{
					nowUserImg = nowUserImg.otheraccountuserimage;
				}
				$("#nowUserImg").attr("src",nowUserImg);
			}
			$("#plContent").val("");
			//-------------------------所有评论---------------------------------
			$("#comment").html("");
			var circleCommentInfo = circleDetailInfo.circleCommentInfo;
			if(circleCommentInfo.length<=0)
			{
				$("#pk_center").css("display","none");
			}
//			alert(circleCommentInfo.length);
			if(circleCommentInfo.length<50)
			{
				$("#moreComment").html("");
			}
			for ( var c = 0; c < circleCommentInfo.length; c++) {
				var ccid = circleCommentInfo[c].ccid;
				var cuserimg = circleCommentInfo[c].userinfo.image;
				if(cuserimg.length<5)
				{
					cuserimg = circleCommentInfo[c].userinfo.otheraccountuserimage;
				}
				var nick = circleCommentInfo[c].userinfo.nick;
				var uid = circleCommentInfo[c].userinfo.userid;
				var commentinfo =circleCommentInfo[c].commentinfo;
				var createdate = circleCommentInfo[c].createdate;
				var def1 = circleCommentInfo[c].def1;
				var def3 = circleCommentInfo[c].def3;
				if(def3==null || def3==""){
					def3=0;
				}
				createCommentHTML(ccid,uid,c,cuserimg,nick,commentinfo,createdate,def1,def3);
				/**
				 * 如果用户登录 并且是自己发表的评论  添加编辑和删除按钮
				 */
				if(islogin)
				{
					user = data.data.nowuser;
					if(uid == user.userid)
					{
						delete_edit_topicHTML(ccid);
					}
				}	
			}
			
			//-------------------------热门用户---------------------------------
			var hotUser = data.data.userTopics;
			$("#hotUser").html("");
			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				var userid = hotUser[u].userinfo.userid;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var nick = hotUser[u].userinfo.nick;
				var count =  hotUser[u].count;
				u_end_li = userid;
				moreUserHTML(userimg,nick,count,userid);
			}
			$("#li"+u_end_li).css("border-bottom","none");
			//-------------------------热门话题---------------------------------
			var hotTopics = data.data.hotTopics;
			$("#hotTopics").html("");
			var tsize = hotTopics.length;
			for ( var h = 0; h < hotTopics.length; h++) {
				var uimg = hotTopics[h].userinfo.image;
				var userid = hotTopics[h].userinfo.userid;
				if(uimg.length<5)
				{
					uimg = hotTopics[h].userinfo.otheraccountuserimage;
				}
				var isgzimg = hotTopics[h].isgz;
				var title = hotTopics[h].title;
				var tid = hotTopics[h].circledetailid;
				var isgz = hotTopics[h].isgz;
				var def3 = hotTopics[h].def3;
				var cuser = hotTopics[h].gztopics;
//				alert(cuser.userinfo.nick);
				var gzuserid = "";
				var gzusernick = "";
				if(cuser.userinfo != null)
				{
					gzuserid = cuser.userinfo.userid;
					gzusernick = cuser.userinfo.nick;
				}
				t_end_li = tid;
				hoteHTML(tid,isgz,uimg,title,def3,userid,gzuserid,gzusernick);
			}
			$("#li"+t_end_li).css("border-bottom","none");
			var countHotUser = data.data.countHotUser;
			if(countHotUser<7)
			{
				$("#user_more").html("");
			}
			var countHotTopic = data.data.countHotTopic;
			if(countHotTopic<7)
			{
				$("#topic_more").html("");
			}
			
		},error:function(data)
		{
			var url = "quanzi/newTopics.jsp";
			location.href = basePath+url;
			return false;
		}
	});
}
/**
 * 删除按钮
 * @param ccid
 */
function delete_edit_topicHTML(ccid)
{
	$("#tu_name_left"+ccid).append("<div style=\"display: block;\" class=\"tu_numeber\" id=\"delete"+ccid+"\"><a href=\"javascript:void(0);\" onclick=\"deleteComment('"+ccid+"')\">删除</a></div>");
}
/**
 * 一个评论的所有的回复
 */
var cc = "";
function getComtocom(ccid,uid)
{
	if(cc != ccid){
		var getComtocomURl="topicDetailAction_getComtocom.do";
		$.ajax({
			url:getComtocomURl,
			data:{id : ccid,uid: uid},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
 				var comtocoms  = data.data.comtocoms;
 				$("#tu_con"+ccid+"").html("");
				if(comtocoms.length<1 || comtocoms == null)
				{
					var str =
						"<div class=\"tu_pinglun\" id=\"tu_pinglun"+ccid+"\" >"+
						"<div class=\"pro_pk_input\"><input class=\"pk_2\" id=\"pk_2"+ccid+"\" /></div>"+
						"<div class=\"pro_pk_img2\" ><a><img src=\"images/pro_important.png\" onclick=\"comtocom('"+ccid+"',"+uid+")\" /></a></div>"+
						"</div>";
					$("#tu_con"+ccid+"").append(str);
					$("#divcom"+ccid+"").css("display","block");
					if(cc != ""){
						$("#divcom"+cc+"").css("display","none");
					}
					cc = ccid;
					return false;
				}
				for ( var i = 0; i < comtocoms.length; i++) {
					var userimg = comtocoms[i].userinfo.image;
					var ctid = comtocoms[i].ctid;
					if(userimg.length<5)
					{
						userimg = comtocoms[i].userinfo.otheraccountuserimage;
					}
					var len = comtocoms.length;
					var nick = comtocoms[i].userinfo.nick;
					var createtime =  comtocoms[i].createtime;
					var content = comtocoms[i].content;
					var userid = comtocoms[i].userinfo.userid;
					comtocomHTML(ccid,ctid,userimg,nick,createtime,content,len,userid);
				}
				var str =
					"<div class=\"tu_pinglun\" id=\"tu_pinglun"+ccid+"\" >"+
					"<div class=\"pro_pk_input\"><input class=\"pk_2\" id=\"pk_2"+ccid+"\" /></div>"+
					"<div class=\"pro_pk_img2\" ><a><img src=\"images/pro_important.png\" onclick=\"comtocom('"+ccid+"',"+uid+")\" /></a></div>"+
					"</div>";
				$("#tu_con"+ccid+"").append(str);
				$("#divcom"+ccid+"").css("display","block");
				if(cc != ""){
					$("#divcom"+cc+"").css("display","none");
				}
				cc = ccid;
			}
		});
	}else{
		$("#tu_con"+ccid+"").html("");
		$("#divcom"+ccid+"").css("display","none");
		if(cc != ""){
			$("#divcom"+cc+"").css("display","none");
		}
		cc="";
	}
}
/**
 * 更多的热门用户
 */
var mu = 2;
var moreUserEND = false;
function moreUser()
{
	if(moreUserEND == false)
	{
	var moreUserURl="topicAction_moreUser.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 7,pageNum : mu},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#li"+u_end_li).removeAttr("style");
			var hotUser = data.data.userTopics;
			if(hotUser.length<7)
			{
				$("#user_more_a").html("没有更多了");
				moreUserEND = true;
			}
			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var count = hotUser[u].count;
				var nick = hotUser[u].userinfo.nick;
				var userid = hotUser[u].userinfo.userid;
				u_end_li = userid;
				moreUserHTML(userimg,nick,count,userid);
			}
			$("#li"+u_end_li).css("border-bottom","none");
		},
		error:function(data)
		{
		}
	});
	mu++;
	}
}
/**
 * 更多的热门话题
 */
var mt = 1;
var morehotTopicsEND = false;
function morehotTopics()
{
	if(morehotTopicsEND==false)
	{
	mt++;
	var moreUserURl="topicDetailAction_moreTopics.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 50,pageNum : mt},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var hotTopics = data.data.hotTopics;
			$("#li"+t_end_li).removeAttr("style");
			for ( var h = 0; h < hotTopics.length; h++) {
				var uimg = hotTopics[h].userinfo.image;
				var userid = hotTopics[h].userinfo.userid;
				if(uimg.length<5)
				{
					uimg = hotTopics[h].userinfo.otheraccountuserimage;
				}
				var cuser = hotTopics[h].gztopics;
				var gzuserid = "";
				var gzusernick = "";
				if(cuser!=null)
				{
					gzuserid = cuser.userinfo.userid;
					gzusernick = cuser.userinfo.nick;
				}
				var title = hotTopics[h].title;
				var isgz = hotTopics[h].isgz;
				var tid = hotTopics[h].circledetailid;
				var def3 = hotTopics[h].def3;
				t_end_li = tid;
				hoteHTML(tid,isgz,uimg,title,def3,userid,gzuserid,gzusernick);
			}
			$("#li"+t_end_li).css("border-bottom","none");
			if(hotTopics.length<7)
			{
				$("#topic_more").html("没有更多了");
				morehotTopicsEND = true;
			}
				
		},
		error:function(data)
		{
		}
	});
	}
}
/**
 * 更多评论
 */
var mc = 2;
var commEnd = false;
function moreComment()
{
	if(commEnd == false)
	{
	var moreCommentURl="topicDetailAction_moreComment.do";
	$.ajax({
		url:moreCommentURl,
		data:{id : tid, pageSize : 50,pageNum : mc},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var circleCommentInfos = data.data.circleCommentInfos;
			for ( var i = 0; i < circleCommentInfos.length; i++) 
			{
				var ccid = circleCommentInfos[i].ccid;
				if(circleCommentInfos[i].userinfo == null){continue;}
				var cuserimg = circleCommentInfos[i].userinfo.image;
				var nick = circleCommentInfos[i].userinfo.nick;
				var uid = circleCommentInfos[i].userinfo.userid;
				var commentinfo = circleCommentInfos[i].commentinfo;
				var createdate = circleCommentInfos[i].createdate;
				var def1 = circleCommentInfos[i].def1;
				var def3 = circleCommentInfos[i].def3;
				if(def3 == null || def3 == ""){
					def3 = 0;
				}
				createCommentHTML(ccid,uid,i,cuserimg,nick,commentinfo,createdate,def1,def3);
				if(islogin)
				{
					if(uid == user.userid)
					{
						delete_edit_topicHTML(ccid);
					}
				}
			}
			if(circleCommentInfos.length<50)
			{
				commEnd = true;
				$("#moreComment").html("没有更多评论了");
			}
		},
		error:function(data)
		{
		}
	});
	mc++;
	}else{
		return false;
	}
}
/**
 * 评论话题
 */
function submitComment(s)
{
	if(!islogin)
	{
		alert("你还没有登录！");
		$("#window_dl").css("display","block");
		return false;
	}
	var content = $("#plContent").val();
	if(content=='<span style="font-size:14px;"></span>&nbsp;'){
		alert("评论内容不能为空！");
		s = true;
		return false;
	}
	if(content.length = 0)
	{
		alert("评论内容不能为空！");  
		s = true;
		return false;
	}
	if(content.length >= 8000)
	{
		alert("评论内容不能超过8000字！");
		s = true;
		return false;
	}
	var insertCommentURl="topicAction_insertComment.do";
	$.ajax({
		url:insertCommentURl,
		data:{tid : tid,comcontent : content},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			alert("评论成功！");
			$("#pk_center").css("display","block");
			$("#plContent").val("");
			location.href = realurl;
		},
		error:function(data)
		{
		}
	});
	return 1;
}
/**
 * 评论后刷新评论列表
 */
function pgaeComment()
{
	var moreCommentURl="topicDetailAction_moreComment.do";
	$.ajax({
		url:moreCommentURl,
		data:{id : tid, pageSize : 5,pageNum : 1},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#comment").html("");
			var circleCommentInfos = data.data.circleCommentInfos;
			for ( var i = 0; i < circleCommentInfos.length; i++) 
			{
				var ccid = circleCommentInfos[i].ccid;
				var cuserimg = circleCommentInfos[i].userinfo.image;
				var nick = circleCommentInfos[i].userinfo.nick;
				var uid = circleCommentInfos[i].userinfo.userid;
				var commentinfo = circleCommentInfos[i].commentinfo;
				var createdate = circleCommentInfos[i].createdate;
				var def1 = circleCommentInfos[i].def1;
				var def3 = circleCommentInfos[i].def3;
				if(def3 == null || def3 == ""){
					def3 = 0;
				}
				createCommentHTML(ccid,uid,i,cuserimg,nick,commentinfo,createdate,def1);
			}
		},
		error:function(data)
		{
		}
	});
}
/**
 * 回复评论
 * @param index
 */
function comtocom(ccid,uid)
{
	if(!islogin)
	{
		alert("你还没有登录！");
		$("#window_dl").css("display","block");
		return false;
	}
	
	var content = $("#pk_2"+ccid).val();//评论的内容
	if(content.length <= 0)
	{
		alert("评论内容不能为空！");
		return false;
	}
	if(content.length >= 200)
	{
		alert("评论内容太长了！");
		return false;
	}
	var insertCommentURl="topicAction_comtossss.do";
	$.ajax({
		url:insertCommentURl,
		data:{tid : ccid,comcontent : content,userId : uid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			alert("评论成功！");
			$("#pk_2"+ccid).val("");
			cc = "";
			topicDetail();
		},
		error:function(data)
		{
		}
	});
}
/**
 * 关注话题
 * @param tid 话题ID
 */
function gzTopic(tids)
{
	var gzTopicURl="gztopicsAction_gzTopic.do";
	$.ajax({
		url:gzTopicURl,
		data:{tid : tids},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			islogin = data.data.islogin;
			var result = data.data.result;
			if(islogin)
			{
//				$("#gzid"+tid).attr("src","images/pro_importan_1t.gif");
				$("#gzid_a"+tids).html("<img src='images/pro_importan_1t.gif'/>");
				alert(data.data.result);
			}else{
				alert(data.data.result);
				$("#window_dl").css("display","block");
			}
		},
		error:function(data)
		{
			alert("关注失败！");
		}
	});
}

var i = 1;
/**
 * 显示隐藏评论框
 * @param ccid
 */
function isShow(ccid)
{
	if(i%2==0)
	{
		$("#"+ccid).css("display","none");
	}else{
		$("#"+ccid).css("display","block");
	}
	i++;
}
/**
 * 赞同
 * @param ccid 评论ID
 */
function agreeComment(ccid)
{
	var agreeURL = "topicDetailAction_agree.do";
	$.ajax({  
		url:agreeURL,
		data:{id : ccid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var r = data.data.r;
			if(r==0)
			{
				alert("你还没有登录！");
				$("#window_dl").css("display","block");
			}
			if(r==1)
			{
				alert("你已经赞过该评论！");
			}
			if(r==2)
			{
				alert("赞同成功！");
				$("#tu_zan"+ccid).html(parseInt($("#tu_zan"+ccid).html())+1);
			}
		},
		error:function(data)
		{
			alert("赞同失败"+data);
		}
	});
}
/**
 * 不赞同
 * @param ccid 评论ID
 */
function notAgreeComment(ccid)
{
	var notAgreeURL = "topicDetailAction_notAgree.do";
	$.ajax({  
		url:notAgreeURL,
		data:{id : ccid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var r = data.data.r;
			if(r==0)
			{
				alert("你还没有登录！");
				$("#window_dl").css("display","block");
			}
			if(r==1)
			{
				alert("你已经踩过该评论！");
			}
			if(r==2)
			{
				alert("踩一个成功！");
			}
		},
		error:function(data)
		{
		}
	});
}
/**
 * 鼠标
 * @param obj
 * @param ccid
 */
function over(obj,ccid)
{
	$("#"+obj+ccid).css("display","block");
}
/**
 * 鼠标
 * @param obj
 * @param ccid
 */
function out(obj,ccid)
{
	$("#"+obj+ccid).css("display","none");
}
/**
 * 评论
 * @param ccid 评论ID
 * @param index 每一次for喜欢的索引
 * @param cuserimg 创建话题的用户头像
 * @param nick 创建话题的用户昵称
 * @param commentinfo 评论内容
 * @param createdate 评论时间
 * @param def1 赞同数量
 */
function createCommentHTML(ccid,uid,index,cuserimg,nick,commentinfo,createdate,def1,def3)
{
	
	var commentHTML="  <div class=\"tu_center\" >"+
    "<div class=\"tu_name_left\" id=\"tu_name_left"+ccid+"\">"+//onmouseover=\"over('delete','"+ccid+"');\" onmouseout=\"out('delete','"+ccid+"');\"
       "<h3 id=\"comment"+ccid+"\"><a href=\"user/"+uid+".html\" target=\"_blank\">"+nick+"</a></h3>"+
       "<p>"+commentinfo+"</p>"+
       "<div class=\"tu_l_1\" id=\"tu_l_1"+ccid+"\">"+
          "<span>"+createdate+"</span>"+
          "<div class=\"tu_zan\"><a id=\"tu_zan"+ccid+"\">"+def1+"</a>条赞同</div>"+
          "<div class=\"tu_huifu\" ><a href=\"javascript:void(0);\"  onclick=\"getComtocom('"+ccid+"',"+uid+")\">"+def3+"条回复</a></div>"+
       "</div>"+
    "</div>"+
    "<div class=\"tu_name_right\"><a href=\"user/"+uid+".html\" target=\"_blank\"><img width='48px' height='48px' src=\""+cuserimg+"\" /></a></div>"+
    
    "<!--修改的代码 -->"+
    "<div class=\"tu_pinglun\" id=\"tu_pinglun"+ccid+"\" style=\"display: none;\" >"+
    	"<div class=\"pro_pk_input\"> <textarea rows=\"3\" cols=\"10\" id=\"edittopic\"></textarea></div>"+
    	"<div class=\"pro_pk_img2\" ><a><img src=\"images/pro_important.png\" onclick=\"comtocom('"+ccid+"',"+uid+")\" /></a></div>"+
    "</div>"+
    "<div class=\"tu_top\" id=\"divcom"+ccid+"\" style=\"display:none\">" +
    "<div class=\"tu_con\" id=\"tu_con"+ccid+"\"></div>"+
    "</div>"+
    "<div class=\"ding_ue\" ><a onmouseover=\"over('ding_ue','"+ccid+"')\" onmouseout=\"out('ding_ue','"+ccid+"')\"><img onclick=\"agreeComment('"+ccid+"');\" src=\"images/ding_ue1.gif\" /></a></div>"+
    "<div class=\"ding_ue_1\"><a><img id=\"ding_ue"+ccid+"\" style=\"display: none;\" src=\"images/ue_zhan.png\" /></a></div>"+
    "<div class=\"ding_ue1\"><a onmouseover=\"over('ding_ue1','"+ccid+"')\" onmouseout=\"out('ding_ue1','"+ccid+"')\"><img onclick=\"notAgreeComment('"+ccid+"');\" src=\"images/ding_ue.gif\"  /></a></div>"+
    "<div class=\"ding_ue_2\" ><a><img id=\"ding_ue1"+ccid+"\" style=\"display: none;\" src=\"images/ue_zhan1.png\" /></a></div>"+  
"</div>";
	$("#comment").append(commentHTML);
	$("#edit"+ccid).css("display","none");
	$("#delete"+ccid).css("display","none");
}
/**
 * 编辑评论
 * @param ccid
 */
function editComment(ccid)
{
//	alert("编辑"+ccid);
//	var comment = $("#edit_comment"+ccid).html();
//	if(comment.length<0)
//	{
//		alert("评论内容不能为空！");
//		return false;
//	}
//	$("#edit_pinglune05a1a40-9c3a-44e5-8d2f-f57d6510ad04").css("display","none");
	$("#edit_pinglun"+ccid).css("display","block");
	var contente = $("#comment_html"+ccid).val();
//	var editCommentURL = "topicDetailAction_editComment.do";
//	$.ajax({
//		url:editCommentURL,
//		data:{id : ccid,content : contente},
//		type:"POST",
//		dataType:"json",
//		success:function(data)
//		{
//			var islogin = data.data.islogin; 
//			var result = data.data.result;
//			if(!islogin)
//			{
//				alert(result);
//				$("#window_dl").css("display","block");
//				return false;
//			}else{
//				alert(result);
//				pgaeComment();
//			}
//		},
//		error:function(data)
//		{
//			alert("修改失败！");
//		}
//	});
}
/**
 * 删除评论
 * @param ccid
 */
function deleteComment(ccid)
{
	if(window.confirm('你确定要删除该条评论！')){
		var deleteCommentURL = "topicDetailAction_deleteComment.do";
		$.ajax({
			url:deleteCommentURL,
			data:{id : ccid,ids : tid},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
				var islogin = data.data.islogin; 
				var result = data.data.result;
				if(!islogin)
				{
					alert(result);
					$("#window_dl").css("display","block");
					return false;
				}else{
					alert(result);
					topicDetail();
				}
			},
			error:function(data)
			{
				alert("删除失败...");
			}
		});
     }else{
        return false;
    }
}

/**
 * 热门话题
 * @param tid 话题ID
 * @param uimg 用户头像
 * @param title 标题
 * @param def3 关注数量
 * @param userid 用户ID
 */
function hoteHTML(tid,isgz,uimg,title,def3,userid,gzuserid,gzusernick)
{
	var isgzImg = "";
	if(isgz==1)
	{
		isgzImg = "images/pro_importan_1t.gif";
	}else{
		isgzImg = "images/pro_important.gif";
	}
	var qtitle = title;
	if(title.length>14)
	{
		title = title.substring(0,14);
		title = title+"..";
	}
	var unick = gzusernick;
	if(gzusernick.length>4)
	{
		gzusernick = gzusernick.substring(0,4);
		gzusernick = gzusernick+".";
	}
	var hotuHTML = "<li id=\"li"+tid+"\">"+
//               "<div class=\"pk_about_l\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img onclick=\"gz('"+tid+"')\" src=\""+uimg+"\" width='25px' /></a></div>"+
               "<div class=\"pk_about_r\">"+
                   "<h4><a title="+qtitle+" href=\"topic_tu/"+tid+".html\" target=\"_blank\">"+title+"</a></h4>"+
                   "<span><a target=\"_blank\" href=\"user/"+gzuserid+".html\">"+def3+"人</a>&nbsp;关注</span>"+
//                   "<p><a title="+unick+" href=\"javascript:void()\">"+gzusernick+"</a> &nbsp;关注了 &nbsp;该话题</p>"+
               "</div>"+
//               "<div class=\"pk_guanzhu1\"><a href=\"javascript:void(0)\" id=\"gzid_a"+tid+"\"><img src=\""+isgzImg+"\" onclick=\"gzTopic('"+tid+"')\" /></a></div>"+
            "</li>";
	$("#hotTopics").append(hotuHTML);
}

/**
 * 回复
 * @param ccid 评论ID
 * @param ctid 回复ID
 * @param userimg 用户头像
 * @param nick 用户昵称
 * @param createtime 回复时间
 * @param content 回复内容
 * @param len 长度
 * @param userid 用户ID
 */
function comtocomHTML(ccid,ctid,userimg,nick,createtime,content,len,userid)
{
	var showcomtocomHTML = 
        "<ul>"+
            "<li>"+
              "<div class=\"tu_con_l\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img width='30px' height='30px' src=\""+userimg+"\" /></a></div>"+
               "<div class=\"tu_con_r\">"+
                   "<div class=\"tu_con_m\" >"+
                       "<h4><a href=\"user/"+userid+".html\" target=\"_blank\">"+nick+"</a></h4>"+
                       "<strong>"+createtime+"</strong>"+
                   "</div>"+
                   "<p>"+content+"</p>"+
               "</div>"+
            "</li>"+
        "</ul> ";
	$("#tu_con"+ccid+"").append(showcomtocomHTML);
}
/**
 * 热门用户
 * @param userimg 用户头像
 * @param nick 用户昵称
 * @param count 关注数量
 * @param count 用户ID
 */
function moreUserHTML(userimg,nick,count,userid)
{
	var hotuserHTML="<li id=\"li"+userid+"\">"+
    "<div class=\"topic_r_img\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
    "<p>"+
        "<span><a href=\"user/"+userid+".html\" target=\"_blank\">"+nick+"</a></span>"+
        "<strong>参与了"+count+"个话题讨论</strong>"+
    "</p>"+
"</li>";
	$("#hotUser").append(hotuserHTML);
}