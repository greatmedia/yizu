//var mainid;//圈主用户ID；
var c;
function getggdiv(tid)
{
	if(tid=='3a607484-620d-438d-9b9c-cac9f0d50f36')
	{
		ggdiv("http://www.7979la.com/","7979la.jpg");
	}
	else if(tid=='f5e49d3e-49c2-461a-bf4e-afb41c06ffe9')
	{
		ggdiv("http://www.sogocn.cn/","sogocn.jpg");
	}
	else if(tid=="810c2627-7268-428f-961f-72db44f3f3b6")
	{
		ggdiv("http://99.com.cn/","99.gif");
	}else if(tid=="3e45bb2e-d377-4cfa-a522-3a4324d60294")
	{
		ggdiv("http://www.pifa186.com/","pifa186.jpg");
	}else if(tid=="24bc4d92-ef0a-437b-9583-61dc4f5e6828")
	{
		ggdiv("http://www.kingdeeit.com/","kingdeeit.jpg");
	}
	else if(tid=="3f2f2344-e68b-407a-939e-a6d464a8439d")
	{
		ggdiv("http://xiandieli.tmall.com/","xiandieli.jpg");
	}
	else if(tid=="1a551520-23b6-4434-aef1-7d2e2f58ae8f")
	{
		ggdiv("http://www.w3c-net.com/","w3c_net.gif");
	}
	else if(tid=="e35455d2-8738-4068-96ed-e6eff3b6c030")
	{
		ggdiv("http://item.taobao.com/item.htm?id=17741639993","taobao-226-270.jpg");
	}
	else if(tid=="1339406727475")
	{
		ggdiv("http://reg.2177s.com/?u=1084&l=6","2177s.jpg");
	}
	else if(tid=="e66aea87-d678-4a88-9262-566f0ef00b21")
	{
		ggdiv("http://www.qiandu6.com/","qiandu6.jpg");
	}
	else if(tid=="4fa49761-d812-46ae-a6b4-71bbfa8fa883")
	{
		ggdiv("http://www.310win.com/","310win.jpg");
	}
	else if(tid=="1339493969319")
	{
		ggdiv("http://www.0261.com/reg/index.html?gid=312&st=1314","0261.gif");
	}
	else if(tid=="84ac5f7e-d217-4157-9a16-33caa4b79aff")
	{
		ggdiv("http://china.findlaw.cn/","findlaw.jpg");
	}
	else if(tid=="26677a5b-7743-442a-bd4a-4b03fddbdc49")
	{
		ggdiv("http://stdsw.1414.cn/","1414.jpg");
	}
	else if(tid=="1339489615662")
	{
		ggdiv("http://esf.sh.soufun.com/?utm_source=shhezuo&utm_medium=click&utm_term=wum_sh&utm_content=sh1mily&utm_campaign=201301061mily","esf.sh.soufun.jpg");
	}
	else if(tid=="78814533-fe58-4a7a-a30f-5122ec21af50")
	{
		ggdiv("http://www.chinainv.org/","chinainv.jpg");
	}
	else if(tid=="9ac2d13d-9f86-4f6e-a9b5-3a87abcf5b46")
	{
		ggdiv("http://www.sogoulm.com/","sogoulm.jpg");
	}
	else{
		$("#ggcirclediv").css("display","none");
	}
}
function ggdiv(href,img)
{var str="<a href=\""+href+"\" target=\"_blank\"><img width=\"228\" src=\"gg/"+img+"\"></a>";$("#ggcirclediv").html(str);}
function getCircle(cid)
{
	if(cid.indexOf('.html')<=0)
	{
		
	}else{
		var realurl = cid,ttyp;
		var idindexof = realurl.lastIndexOf(".html");
		cid = realurl.substring(0,idindexof);
	}
	c = cid;
	var circleURL = "circleDetail_getCircle.do";
	$.ajax({
		url:circleURL,
		data:{id : cid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#circle_img").html("");
			islogin = data.data.islogin;
			var circle = data.data.center_circleInfo;
			getggdiv(cid);
			//最新加入圈子成员
			$("#newAddMember").html("");
			var newMember = data.data.userCircleRlss;
			if(newMember.length<1)
			{
				$("#newAddMember").append("本圈子还没有最新的成员..");
			}
			for(var i =0;i<newMember.length;i++)
			{
				var userImg = newMember[i].userinfo.image;
				if(userImg.length<2)
				{
					userImg = newMember[i].userinfo.otheraccountuserimage;
				}
				if(i<4)
				{
					$("#newAddMember").append("<li><a target=\"_blank\" href=\"user/"+newMember[i].userinfo.userid+".html\"><img src=\""+userImg+"\" title=\""+newMember[i].userinfo.nick+"\" /></a></li>");
				}
				if(i==4)
				{
					$("#newAddMember").append("<li target=\"_blank\" style=\"margin:0px;\"><a href=\"user/"+newMember[i].userinfo.userid+".html\"><img src=\""+userImg+"\" title=\""+newMember[i].userinfo.nick+"\" /></a></li>");
				}
				
			}
			
			
			if(islogin != 'not')
			{
				
				//已经登录
				$("#join").css("display","block");
				var user = data.data.user;
				circleUser = data.data.user;
				var isAddCircle = data.data.isAddCircle;
				$("#fabu").css("display","block");
				if(isAddCircle<=0)
				{
					//没有加入圈子
					$("#fabu").css("display","none");
					$("#join").css("display","block");
					$("#a_join").css("display","none");
					$("#quit").css("display","none");
				}else{
					//发布
					$("#join").css("display","none");
					$("#a_join").css("display","block");
					$("#quit").css("display","block");
				}
				$("#publish").html("<img src=\"images/btn_02.gif\" />");
				var img = user.image;
				var uuid = user.userid;
				var ccid = circle.userinfo.userid;
//				nowuser(uuid,ccid);
			}else{
				//没有登录
				$("#quit").css("display","none");
				$("#fabu").css("display","none");
			}
			
//			mainid = circle.userinfo.userid;
			var u = "<a target=\"_blank\" href=\"circle_user/"+circle.circleid+".html\">全部成员>></a>";
			$("#alluser").html(u);
			$("#circletag").html(circle.circletag);
			$("#createUserId").html(circle.userinfo.nick);
			$("#circleName").html(circle.circlename);
			$("#joincount").html("<a target=\"_blank\" href=\"circle_user/"+circle.circleid+".html\">"+circle.joincount+"</a>");
			$("#comCount").html("<a target=\"_blank\" href=\"circle/"+circle.circleid+".html\">"+circle.comcount+"</a>");
			$("#circleSummary").html(circle.summary);
			$("#circle_img").html("<a target=\"_blank\" href=\"circle/"+circle.circleid+".html\"><img src='"+circle.circlemiddleimg+"' width='198' title=\"返回圈子话题列表\" /></a>");
			$("#circleMain").html("<a target=\"_blank\" href=\"user/"+circle.userinfo.userid+".html\">"+circle.userinfo.nick+"</a>");
			
			var count = data.data.isAddCircle;
			var mainHead = circle.userinfo.image;
			var mainid = circle.userinfo.userid;
			if(mainHead.length<2)
			{
				mainHead = circle.userinfo.otheraccountuserimage;
			}
			$("#circleMainHead").html("<a target=\"_blank\" href=\"user/"+mainid+".html\"><img src='"+mainHead+"'></a>");
			var c_tags = circle.circletag;
			var c_tag = c_tags.replace("，",",").split(",");
			if(user!=null)
			{
				if(mainid == user.userid)
				{
					$("#a_join").css("display","block");
					$("#quit").css("display","none");
					$("#join").css("display","none");
					$("#fabu").css("display","block");
				}
			}
		},
		error:function(data){
		}
	});
}


//最新加入圈子的成员
function newAddMember(id)
{
//	 var newAddMemberURL = "circleDetail_newAddMember.do";
//	 $.ajax({  
//			url:newAddMemberURL,
//			data:{id : id},
//			type:"POST",
//			dataType:"json",
//			success:function(data)
//			{
//				$("#newAddMember").html("");
//				var newMember = data.data.userCircleRlss;
//				if(newMember.length<1)
//				{
//					$("#newAddMember").append("本圈子还没有最新的成员..");
//				}
//				for(var i =0;i<newMember.length;i++)
//				{
//					var userImg = newMember[i].userinfo.image;
//					if(userImg.length<2)
//					{
//						userImg = newMember[i].userinfo.otheraccountuserimage;
//					}
//					if(i<4)
//					{
//						$("#newAddMember").append("<li><a target=\"_blank\" href=\"user/"+newMember[i].userinfo.userid+"\"><img src=\""+userImg+"\" title=\""+newMember[i].userinfo.nick+"\" /></a></li>");
//					}
//					if(i==4)
//					{
//						$("#newAddMember").append("<li target=\"_blank\" style=\"margin:0px;\"><a href=\"user/"+newMember[i].userinfo.userid+"\"><img src=\""+userImg+"\" title=\""+newMember[i].userinfo.nick+"\" /></a></li>");
//					}
//					
//				}
//			},
//			error:function(){
//			}
//		}); 
}

//加入圈子
function join_or_quit_quanzi(did,ttyp){
	if(did.indexOf('.html')<=0)
	{
		
	}else{
		var realurl = did,ttyp;
		var idindexof = realurl.lastIndexOf(".html");
		did = realurl.substring(0,idindexof);
	}
	if(islogin=='not')
	{
		alert("您还没有登录，登录之后才能加入圈子..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	$("#wait").css("display","block");
	$("#join").css("display","none");
	$("#a_join").css("display","none");
	var joinquanziURL = "jsonInfoAction_joinOrQuitQuanzi.do";
	$.ajax({                 
		url: joinquanziURL,  
		data : {id : did ,type : ttyp},
		type: "POST",     
		dataType : "json",
		async:false,
		success: function(data) {
			//json.put("cadd", "add");
			$("#wait").css("display","none");
			var flag = data.flag;
			if(ttyp == 1){
				//加入圈子
				if(flag){
					alert("加入圈子成功！");
					getCircle(c);
					showjiaru_tuichu();
					newAddMember(c);
					$("#wait").css("display","none");
					$("#fabu").css("display","block");
					$("#join").css("display","none");
					$("#a_join").css("display","block");
					$("#quit").css("display","block");
				}else{
					alert("你已经加入该圈子了！");
					$("#wait").css("display","none");
					getCircle(c);
					myScroll();
//					$("#window_dl").css("display","block");
				}
			}else if(ttyp == 2){
				//退出圈子
				if(flag){
					alert("退出圈子成功！");
					getCircle(c);
					newAddMember(c);
					$("#wait").css("display","none");
					$("#fabu").css("display","none");
					$("#join").css("display","block");
					$("#a_join").css("display","none");
					$("#quit").css("display","none");
				}else{
					alert("退出圈子失败！");
					$("#wait").css("display","none");
					showjiaru_tuichu();
				}
			}
		},
		error:function(data){
			$("#wait").css("display","none");
		}
	});
}
