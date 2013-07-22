var topicTitle;
var id;
//did = getQueryStringRegExp("did");
//var circleId = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var tid = realurl.substring(idindexof + 1);
topic();
var user;
var cird,index;
function topic()
{
//	hot();
	//某一个话题的信息
	var detailURL = "circleDetail_getDetail.do";
	$.ajax({
		url:detailURL,
		data:{id : tid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var topic = data.data.circleDetailInfo;
			cird = eval(topic);
			user = data.data.user;
			document.title=cird.title;//标题
			topicTitle=cird.title;
			$("#title").html(topicTitle);//标题
			var tags = cird.ctag;
			if(tags.length < 2 || tags == null)
			{
				$("#tag").css("display","none");
			}else{
				tags=tags.replace("  ",",");
				tags=tags.replace(" ",",");
				tags=tags.split(",");
				
				for(var i=0;i<tags.length;i++)
				{
					$("#tag").append("<li><a>"+tags[i]+"</a></li>");
				}
			}
			//内容
			$("#content").html(topic.circlecontent);
			id=topic.circledetailid;
			
			//热门圈子
			$("#hotCircle").html("");
			var hotCir = data.data.hotcCircleInfos;
			for(var u=0;u<hotCir.length;u++)
			{
				$("#hotCircle").append("<a target=\"_blank\" href=\"circle/"+hotCir[u].circleid+"\"><li><div class=\"h_right_img\"><img src=\""+hotCir[u].circlemiddleimg+"\" width='51px' height='51px' /></div><div class=\"h_right_t\">"+hotCir[u].circlename+"</div></li></a>");
			}
			
			
			showImgs(id);//获取本话题下的全部图片
			showComments(id);//获取本话题下的所有评论
		},error:function(data)
		{
			alert("eee");
		}
	});
}

//获取did和circleId
function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}

//一个话题的所有的图片信息
function showImgs(did)
{

	$("#topicImg").html("");
	var dimg = cird.circleDetailImg;
	for(var i =0;i<dimg.length;i++)
	{
		var img = new Image();
		img.src = dimg[i].bigimg;
		
		var w = img.width;
		if(w==0){
			w = img.width;
		}
//		alert(w);
		if(w<=680){
			var image="<a><img src=\""+img.src+"\" id=\"image"+i+"\"/></a>";
		}else{
			var image="<a><img src=\""+img.src+"\" width=\"680px\" id=\"image"+i+"\"/></a>";
		}
		$("#topicImg").append(image);
	}
}

//一个话题的所有的评论
function showComments(tid)
{
	var commentsURL = "circleDetail_getTopicComment.do";
	$("#commentDiv").html("<div class=\"profile_page\" align=\"center\"><img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" /></div>");
	$.ajax({
		url:commentsURL,
		data:{id : tid ,pageSize:10000,pageNum:0},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#commentDiv").html("");//onclick=\"breakPage();\"
			var commentCount = data.data.commentCountById;//评论总数
			var comments = data.data.circleCommentInfos;//所有的评论ccid
			var dataSize = commentCount;//评论条数
			var com = "<div class=\"h_quesion\"><h3>添加评论</h3><textarea  id='commentValue' class=\"h_uat_2\"></textarea><p><a onclick=\"subjectContent('"+tid+"');\" href='javascript:void();'><img src=\"images/sub_pingl.gif\" /></a></p></div>";
			$("#commentDiv").html(com);
			for(var k=0;k<comments.length;k++)
			{
				var cid =  comments[k].ccid;
				var userHead = comments[k].userinfo.image;//评论人的头像
				var name=comments[k].userinfo.nick;//评论人的名字
				var userId=comments[k].userinfo.userid;
				var date=comments[k].createdate;//评论的时间
				var info=comments[k].commentinfo;
				
				var agreecount = comments[k].def1;
				if(!(agreecount>=1))
				{
					agreecount = 0;
				}
				if(userHead.length<2)
				{
					userHead = comments[k].userinfo.otheraccountuserimage;
				}
//				alert("tid="+tid);
				var comment="<div class=\"h_pinglun\">"+
								"<div class=\"h_name\">"+
									"<a target=\"_blank\" href=\"user/"+comments[k].userinfo.userid+"\">"+name+"</a>"+
								"</div>"+
								"<p>"+name+": "+info+"</p>"+
								"<div class=\"h_time\"><span>"+date+"</span>&nbsp;&nbsp;&nbsp;<span>赞同(<strong>&nbsp;<a>"+agreecount+"</a>&nbsp;</strong>)</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href=\"javascript:void(0);\" id='k"+k+"' onclick='comtocom("+k+")'>回复</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href='javascript:void(0);' onclick=\"allComToCom('"+cid+"',"+userId+","+k+")\">所有回复</a></span></div>"+
								"<div id=\"showcom"+k+"\" class=\"h_time\" style=\"display:none\"></div>"+
								"<div id='comtocom"+k+"' style=\"display:none;\" class=\"h_quesioncom\"><textarea  id='textcom"+k+"' class=\"h_uat_2\"></textarea><p><a onclick=\" submitCom('"+cid+"',"+userId+","+k+")\" href='javascript:void();'><img src=\"images/sub_pingl.gif\" /></a></p></div>"+
								"<div class=\"n_ding\" ><a href=\"javascript:void(0);\"><img src='images/agree1.gif' onmouseOver=\"this.src='images/agree2.gif'\" onmouseOut=\"this.src='images/agree1.gif'\" onclick='agree(\""+comments[k].ccid+"\");'/></a></div>"+
								"<div class=\"n_ding1\"><a href=\"javascript:void(0);\"><img src='images/agree3.gif' onmouseOver=\"this.src='images/agree4.gif'\" onmouseOut=\"this.src='images/agree3.gif'\" onclick='notAgree(\""+comments[k].ccid+"\");'/></a></div>"+
								"</div>";
                $("#commentDiv").append(comment);
			}
		},
		error:function(data)
		{
		}
	});
}
//显示与关闭评论框
var i=0;
function comtocom(k){
	$("#comtocom"+k+"").toggle(i++ %2==0);
}
//评论 已有的评论
//tid话题id,userId:被评论的用户id
function submitCom(tid,userId,k){
	if(user=='not')
	{
		alert("您还没有登录，登录之后才能评论..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	var contents = $("#textcom"+k+"").val();//评论的内容
	if(contents.length <= 0)
	{
		alert("评论内容不能为空！");
		return false;
	}
	if(contents.length >= 200)
	{
		alert("您的评论过长，请控制在200字以内！");
		return false;
	}
	var comtocomURl="topicAction_comtossss.do";
	$.ajax({  
		url:comtocomURl,
		data:{tid : tid,userId :userId,comcontent : contents},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#comtocom"+k+"").css("display","none");
			showCom(tid,userId,k);
		},
		error:function(){
			alert("comtocomt____error");
		}
	});
}
//查询对评论的回复

function showCom(tid,userId,k){
	$("#showcom"+k+"").html("");
	var showComURL="topicAction_showcom.do";
	$.ajax({  
		url:showComURL,
		data:{tid : tid,userId :userId},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var comlist=data.data.comList;
			var comlen=comlist.length;
			for(var i=0;i<comlen;i++){
				var name=comlist[i].userinfo.nick;
				var contents=comlist[i].content;
				$("#showcom"+k+"").css("display","block").append(name+":").append(contents+"<br />").fadeTo("normal",500);
			}
		},
		error:function(){
			alert("comtocomt____error");
		}
	});
}
//展开所有评论
var n=0;
function allComToCom(tid,userId,k){
	$("#showcom"+k+"").toggle(function(){
		if(n++ %2==0){
			var showComURL="topicAction_showcom.do";
			$.ajax({  
				url:showComURL,
				data:{tid : tid,userId :userId},
				type:"POST",
				dataType:"json",
				success:function(data)
				{
					var comlist=data.data.comList;
					var comlen=comlist.length;
					for(var i=0;i<comlen;i++){
						var name=comlist[i].userinfo.nick;
						var contents=comlist[i].content;
						$("#showcom"+k+"").css("display","block").append(name+":").append(contents+"<br />").fadeTo("normal",500);
					}
				},
				error:function(){
					alert("comtocomt____error");
				}
			});
		}else{
			$("#showcom"+k+"").html("");
		}
	});
	
}
//热门圈子
function hot()
{
//	var hotCircleURL = "circleDetail_hotCircleTop3.do";
//	$.ajax({  
//		url:hotCircleURL,
//		data:{id : $("#id").val()},
//		type:"POST",
//		dataType:"json",
//		success:function(data)
//		{
//			$("#hotCircle").html("");
//			var hotCir = data.data.hotcCircleInfos;
//			for(var u=0;u<hotCir.length;u++)
//			{
//				$("#hotCircle").append("<a target=\"_blank\" href=\"circle/"+hotCir[u].circleid+"\"><li><div class=\"h_right_img\"><img src=\""+hotCir[u].circlemiddleimg+"\" width='51px' height='51px' /></div><div class=\"h_right_t\">"+hotCir[u].circlename+"</div></li></a>");
//			}
//		},
//		error:function(){
//		}
//	});
}
//赞同
function agree(ccid)
{
	var agreeURL = "agreeAction_agreeCount.do";
	$.ajax({  
		url:agreeURL,
		data:{id : ccid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var user = data.data.user;
			if(user=='usernull')
			{
				alert("您还没有登录...");
				myScroll();
				$("#window_dl").css("display","block");
				return false;
			}
			var agree = data.data.agree;
			if(agree=='agree')
			{
				alert("您已经赞同了...");
				return false;
			}
			showComments(tid);
		},
		error:function(){
		}
	});
}
//不赞同
function notAgree(ccid)
{
	var notAgree = "agreeAction_notAgreeCount.do";
	$.ajax({  
		url:notAgree,
		data:{id : ccid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var user = data.data.user;
			if(user=='usernull')
			{
				alert("您还没有登录...");
				myScroll();
				$("#window_dl").css("display","block");
				return false;
			}
			var agree = data.data.agree;
			if(agree=='agree')
			{
				alert("您已经不赞同了...");
				return false;
			}
			showComments(tid);
		},
		error:function(){
		}
	});
}
//提交评论
function subjectContent(detailid)
{
	if(user=='not')
	{
		alert("您还没有登录，登录之后才能评论..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	var contents = $("#commentValue").val();//评论的内容
	if(contents.length <= 0)
	{
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>评论内容不能为空..</font></b></h3>");
		return false;
	}
	if(contents.length >= 200)
	{
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>评论内容过长..</font></b></h3>");
		return false;
	}
	$("#msg").html("");
	var commentURL =  "circleDetail_insertComment.do?id="+detailid;
	$.ajax({  
		url:commentURL,
		data:{content : contents},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#commentValue").val("");
			var result = data.data.result;
			if(result>=1)
			{
				showComments(tid);
				$("#commentValue").val("");
			}
			if(result<1)
			{
				alert("评论失败...");
			}
		},
		error:function(){
			
		}
	}); 
}

