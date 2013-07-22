$(function(){
	var realurl = location.href;
	var tag = realurl.substring(realurl.lastIndexOf("=")+1);
//	sess();
	showleft(tag);
	selectContentme();
});

function showleft(tag){
	if(tag==1){
		document.title = "我的发言";$("#1").addClass("select_now");$("#2").removeClass();$("#3").removeClass();$("#4").removeClass();$("#5").removeClass();$("#6").removeClass();$("#7").removeClass();$("#8").removeClass();
		$("#TAtopic").css("display","block");$("#TAvote").css("display","none");$("#TAcircle").css("display","none");$("#TAcreate").css("display","none");$("#userset").css("display","none");$("#userpassword").css("display","none");$("#Menotice").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==2){
		document.title = "我创建的投票";$("#2").addClass("select_now");$("#1").removeClass();$("#3").removeClass();$("#4").removeClass();$("#5").removeClass();$("#6").removeClass();$("#7").removeClass();$("#8").removeClass();
		$("#TAvote").css("display","block");$("#TAtopic").css("display","none");$("#TAcircle").css("display","none");$("#TAcreate").css("display","none");$("#userset").css("display","none");$("#userpassword").css("display","none");$("#Menotice").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==3){
		document.title = "我加入的圈子";$("#3").addClass("select_now");$("#1").removeClass();$("#2").removeClass();$("#4").removeClass();$("#5").removeClass();$("#6").removeClass();$("#7").removeClass();$("#8").removeClass();
		$("#TAcircle").css("display","block");$("#TAtopic").css("display","none");$("#TAvote").css("display","none");$("#TAcreate").css("display","none");$("#userset").css("display","none");$("#userpassword").css("display","none");$("#Menotice").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==4){
		document.title = "我创建的圈子";$("#4").addClass("select_now");$("#1").removeClass();$("#2").removeClass();$("#3").removeClass();$("#5").removeClass();$("#6").removeClass();$("#7").removeClass();$("#8").removeClass();
		$("#TAcreate").css("display","block");$("#TAtopic").css("display","none");$("#TAvote").css("display","none");$("#TAcircle").css("display","none");$("#userset").css("display","none");$("#userpassword").css("display","none");$("#Menotice").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==5){
		document.title = "修改资料";$("#5").addClass("select_now");$("#1").removeClass();$("#2").removeClass();$("#3").removeClass();$("#4").removeClass();$("#6").removeClass();$("#7").removeClass();$("#8").removeClass();
		$("#userset").css("display","block");$("#TAcreate").css("display","none");$("#TAtopic").css("display","none");$("#TAvote").css("display","none");$("#TAcircle").css("display","none");$("#userpassword").css("display","none");$("#Menotice").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==6){
		document.title = "修改密码";$("#6").addClass("select_now");$("#1").removeClass();$("#2").removeClass();$("#3").removeClass();$("#4").removeClass();$("#5").removeClass();$("#7").removeClass();$("#8").removeClass();
		$("#userpassword").css("display","block");$("#userset").css("display","none");$("#TAcreate").css("display","none");$("#TAtopic").css("display","none");$("#TAvote").css("display","none");$("#TAcircle").css("display","none");$("#Menotice").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==7){
		document.title = "最新动态";$("#7").addClass("select_now");$("#1").removeClass();$("#2").removeClass();$("#3").removeClass();$("#4").removeClass();$("#5").removeClass();$("#6").removeClass();$("#8").removeClass();
		$("#Menotice").css("display","block");$("#userset").css("display","none");$("#TAcreate").css("display","none");$("#TAtopic").css("display","none");$("#TAvote").css("display","none");$("#TAcircle").css("display","none");$("#userpassword").css("display","none");$("#myVisitor").css("display","none");
	}else if(tag==8){
		document.title = "我的访客";$("#8").addClass("select_now");$("#1").removeClass();$("#2").removeClass();$("#3").removeClass();$("#4").removeClass();$("#5").removeClass();$("#6").removeClass();$("#7").removeClass();
		$("#myVisitor").css("display","block");$("#Menotice").css("display","none");$("#userset").css("display","none");$("#TAcreate").css("display","none");$("#TAtopic").css("display","none");$("#TAvote").css("display","none");$("#TAcircle").css("display","none");$("#userpassword").css("display","none");
	}
}
var pageNumber = 1;
var userid;
function selectContentme(){
	var selectContentme="centerAction_selectContentme.do";
	$.ajax({
		url:selectContentme,
		data:{pageNum : pageNumber,pageSize : 12},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var user = data.data.user;
			if(user==null){
				alert("您还没有登录,请登录后查看.");
				location.href = basePath;
			}
			userid = user.userid;
//			if(user.website != null && user.website != ""){
//				$("#user_bj").attr({"src": user.website,"width":"720","height":"295"});
//			}else{
//				$("#user_bj").attr("src", "images/gerenzhongxin_bj.jpg");
//			}
//			$("#userimg").attr({"src":user.image,"width":"80","height":"80"});//头像
//			$("#nick").html(user.nick)//名字
//			
//			if(user.sex !=null && user.sex != "" ){
//				if(user.sex == "2"){$("#usersex").html("逸千金");}else{$("#usersex").html("逸骑士");}
//			}else{
//				$("#usersex").html("逸骑士");
//			}
//			if(user.address !=null && user.address != "" ){$("#useraddress").html(""+user.address+"")}
//			if(user.hobby !=null && user.hobby != "" ){$("#userhobby").html(""+user.hobby+"")}
//			
//			$("#aboutme").html(user.what);//关于自己
//			$("#huatiCount").html(data.data.count);	//发言数
//			$("#circleCount").html(data.data.circleCount);//我创建的圈子数
//			$("#myvoteCount").html(data.data.myvoteCount);//我创建的投票数
//			//ta的发言
//			var hishuati = data.data.circleDetailInfos;
//			if(data.data.count<=0){
//				$("#hesaid").html("<li><div class='u_cont'><h3>还没有发言哦！</h3></div></li>");
//			}
//			if(data.data.count>12){
//				var paginate = new pagination();
//				paginate.printPagination(pageNumber, data.data.count, 12, 'topic_pl_page',userid,1);
//			}
//			for(var k = 0;k<hishuati.length;k++){
//				var def1 = hishuati[k].def1;//话题
//				var def2 = hishuati[k].def2;//PK话题
//				var img;
//				var cid = hishuati[k].circleid;
//				if(hishuati[k].circleDetailImg[0] ==null){
//					img = "images/22222.gif";
//				}else{
//					img = hishuati[k].circleDetailImg[0].middleimg;
//					if(img == ""){img = "images/22222.gif";}
//				}
//				
//				if(def1==1){
//					var url ;
//					if(def2==1){
//						url = basePath +"topic_pk/"+hishuati[k].circledetailid;
//					}else{
//						url = basePath +"topic_tu/"+hishuati[k].circledetailid;
//					}
//					var str = "<li>" +
//					"<div class=\"speak_left\"><a target='_blank' href=\""+url+"\"><img src=\""+img+"\" width='48px' height='48px'  /></a></div>" +
//					"<div class=\"speak_right\" >" +
//					 "<div class=\"speak_time\">"+hishuati[k].createdatetime+"</div>"+
//					" <p><a target='_blank' href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
//					"<span>"+hishuati[k].comcount+"回复&nbsp;|&nbsp;<strong>"+hishuati[k].visitcount+"查看</strong></span>" +
//					"</div>" +
//					"</li>";
//				}else{
//					var str = "<li>" +
//							"<div class=\"speak_left\"><a target='_blank' href=\""+basePath+"det/"+cid+"/"+hishuati[k].circledetailid+".html\"><img src=\""+img+"\" width='48px' height='48px' /></a></div>" +
//							" <div class=\"speak_right\" >" +
//							" <div class=\"speak_time\">"+hishuati[k].createdatetime+"</div>" +
//							" <p><a target='_blank' href=\""+basePath+"det/"+cid+"/"+hishuati[k].circledetailid+".html\">"+hishuati[k].title+"</a></p>" +
//							"<span>"+hishuati[k].comcount+"回复&nbsp;|&nbsp;<strong>"+hishuati[k].visitcount+"查看</strong></span>" +
//							"</div>" +
//							"</li>";
//				}
//				$("#hesaid").append(str);;
//			}
//			
//			//创建的投票
//			var voteList = data.data.voteList;
//			if(data.data.myvoteCount<=0){
//				$("#TAvote").html("<p>还没创建投票......</p>");
//			}
//			if(data.data.myvoteCount>12){
//				var paginate = new pagination();
//				paginate.printPagination(pageNumber, data.data.myvoteCount, 12, 'vote_pl_page',userid,2);
//			}
//			for(var l = 0;l<voteList.length;l++){
//				var votestr = "<li>" +
//						" <div class=\"speak_left\"><a target='_blank' href=\""+basePath+"votes/"+voteList[l].voteId+".html\"><img src=\""+voteList[l].myVoteInfoImage[0].imgAddress+"\" width='48px' height='48px'/></a></div>" +
//						"<div class=\"speak_right\" >" +
//						"<h2><a target='_blank' href=\""+basePath+"votes/"+voteList[l].voteId+".html\">"+voteList[l].title+"</a></h2>" +
//						"<div class=\"speak_time\">"+voteList[l].createdate+"</div>" +
//						"<span>"+voteList[l].commentcount+"回复&nbsp;|&nbsp;<strong>"+voteList[l].gzcount+"关注</strong></span>" +
//						"</div>" +
//						"</li>";
//				$("#hevote").append(votestr);
//			}
//			
//			//加入的圈子
//			var add_circles = data.data.add_circles;
//			var addCount = data.data.addcircleCount;
//			if(addCount<=0){$("#TAcircle").html("<p>还没有加入任何圈子哦！</p>");}
//			if(addCount>12){
//				var paginate = new pagination();
//				paginate.printPagination(pageNumber, data.data.addcircleCount, 12, 'circle_pl_page',userid,3);
//			}
//			for(var i = 0;i<add_circles.length;i++){
//				if(add_circles[i].circleInfo != null){
//					var str;
//					if((i+1)%4==0){
//						str ="<li style=\"margin-right:0px;\"><a target='_blank' href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='148px' height='148px' /></a>" +
//						"<span><a href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\">"+add_circles[i].circleInfo.circlename+"</a></span></li>";
//					}else{
//						str ="<li><a target='_blank' href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='148px' height='148px' /></a>" +
//						"<span><a href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\">"+add_circles[i].circleInfo.circlename+"</a></span></li>";
//					}
//					$("#hecircle").append(str);
//				}
//			}
//			//创建的圈子
//			var createCirlce = data.data.createCircle;
//			if(data.data.circleCount<=0){$("#TAcreate").html("<p>还没创建圈子......<br />创建一个圈子，<br />邀请更多志同道合的人来加入吧！</p>	");}
//			if(data.data.circleCount>=12){
//				var paginate = new pagination();
//				paginate.printPagination(pageNumber,data.data.circleCount, 12, 'create_pl_page',userid,4);
//			}
//			for(var j = 0; j<createCirlce.length;j++){
//					var str;
//					if((j+1)%4==0){
//						str ="<li style=\"margin-right:0px;\"><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='148px' height='148px' /></a>" +
//						"<span><a href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\">"+createCirlce[j].circlename+"</a></span></li>";
//					}else{
//						str ="<li><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='148px' height='148px' /></a>" +
//						"<span><a href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\">"+createCirlce[j].circlename+"</a></span></li>";
//					}
//					$("#hecreate").append(str);
//			}
			//访客
			if(data.data.vCount<=0){$("#myVisitor").html("<p>还没有访客......</p>	");}
			if(data.data.vCount>=12){
				var paginate = new pagination();
				paginate.printPagination(pageNumber,data.data.vCount, 12, 'visitor_page',userid,6);
			}
			var viList = data.data.viList;
//			alert(viList.length);
			for(var v = 0;v <viList.length; v++){
				var str = "<li>" +
					"<div class=\"speak_left\"><a target='_blank' href=\""+basePath+"user/"+viList[v].visitoruserid+".html\"><img src=\""+viList[v].userinfo.image+"\" width='48px' height='48px' /></a></div>" +
					" <div class=\"speak_right\" >" +
					" <p><a target='_blank' href=\""+basePath+"user/"+viList[v].visitoruserid+".html\">"+viList[v].visitorname+"</a></p>" +
					"<span style=\"line-height: 30px;\">"+viList[v].visittime+"</span>" +
					"</div>" +
					"</li>";
				$("#visitor").append(str);
			}
			//我的最新通知
			var informCount = data.data.informCount;
			if(informCount<=0){
				var str ="<li>" +
				"<div class=\"notice_readed\">" +
				"<div class=\"notice_left\">" +
				"<p>没有最新动态</p>"+
				"</div>" +
				"</li>";
				$("#notice").append(str);
			}
			if(informCount > 12){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, informCount, 12, 'notice_page',userid,5);
			}
			var inform = data.data.informList;
//			alert(inform.length);
			for ( var o = 0; o < inform.length; o++) {
				 var informtype = inform[o].informtype;//通知类型
				 if(informtype == 1 ){//为 1 圈子有新加入的成员
					 if(inform[o].circleinfo == null){ continue;}
					var str ="<li>" +
						"<div class=\"notice_readed\">" +
						"<div class=\"notice_left\">" +
						"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
						"<p>加入了您所在的圈子</p><a href=\""+basePath+"cir2/1/"+inform[o].circleid+".html\">"+inform[o].circleinfo.circlename+"</a>" +
						"</div>" +
						"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
						"</div>" +
						"</li>";
					$("#notice").append(str);
				 }else if(informtype == 2){//为2 圈子内容被评论
					 var str = "<li>" +
				 		"<div class=\"notice_readed\">" +
				 		"<div class=\"notice_left\">" +
				 		"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
				 		"<p>评论了您发表的内容<a href=\""+basePath+"det/"+inform[o].circleid+"/"+inform[o].circledetailid+".html\">"+inform[o].detail.title+"</a></p>" +
				 		"</div>" +
				 		"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
				 		"</div>" +
				 		"</li>";
					 $("#notice").append(str);
				 }else if(informtype == 3 ){//为3 话题被评论
					 var str = "<li>" +
				 		"<div class=\"notice_readed\">" +
				 		"<div class=\"notice_left\">" +
				 		"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
				 		"<p>评论了您发表的话题<a href=\""+basePath+"topic_tu/"+inform[o].circledetailid+".html\">"+inform[o].detail.title+"</a></p>" +
				 		"</div>" +
				 		"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
				 		"</div>" +
				 		"</li>";
					 $("#notice").append(str);
				 }else if(informtype == 4){//话题被关注
					 var str = "<li>" +
				 		"<div class=\"notice_readed\">" +
				 		"<div class=\"notice_left\">" +
				 		"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
				 		"<p>关注了您的话题<a href=\""+basePath+"topic_tu/"+inform[o].circledetailid+"\">"+inform[o].detail.title+"</a></p>" +
				 		"</div>" +
				 		"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
				 		"</div>" +
				 		"</li>";
					 $("#notice").append(str);
				 }
			}
		},error : function(data){
		}
	});
}
//分页
function flipPage(rows,pageNumber,id,ttp) {
	if(ttp == 1){gettopicnextme(rows,pageNumber,id,ttp);}
	if(ttp == 2){getvotenextme(rows,pageNumber,id,ttp);}
	if(ttp == 3){getcirclenextme(rows,pageNumber,id,ttp);}
	if(ttp == 4){getcreatenextme(rows,pageNumber,id,ttp);}
	if(ttp == 5){getnoticenextme(rows,pageNumber,id,ttp);}
	if(ttp == 6){getvisitornextme(rows,pageNumber,id,ttp);}
}
//访客
function getvisitornextme(rows,pageNumber,id,ttp){
	var getvisitornextURL = "centerAction_getvisitornextme.do";
	$.ajax({
		url : getvisitornextURL,
		data : {pageNum : pageNumber , pageSize : rows},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#visitor").html("");
			var paginate = new pagination();
			paginate.printPagination(pageNumber, data.data.vCount, rows, 'visitor_page',id,6);
			var viList = data.data.viList;
			for(var v = 0;v<viList.length;v++){
				var str = "<li>" +
						"<div class=\"speak_left\"><a target='_blank' href=\""+basePath+"user/"+viList[v].visitoruserid+".html\"><img src=\""+viList[v].userinfo.image+"\" width='48px' height='48px' /></a></div>" +
						" <div class=\"speak_right\" >" +
						" <p><a target='_blank' href=\""+basePath+"user/"+viList[v].visitoruserid+".html\">"+viList[v].visitorname+"</a></p>" +
						"<span style=\"line-height: 30px;\">"+viList[v].visittime+"</span>" +
						"</div>" +
						"</li>";
				$("#visitor").append(str);
			}
		},
		error : function(data){
			
		}
	});	
}
//发言分页
function gettopicnextme(rows,pageNumber,id,ttp){
	var gettopicnextURL = "centerAction_gettopicnextme.do";
	$.ajax({
		url : gettopicnextURL,
		data : {pageNum : pageNumber , pageSize : rows},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#hesaid").html("");
			var hishuati = data.data.circleDetailInfos;
			var paginate = new pagination();
			paginate.printPagination(pageNumber, data.data.count, rows, 'topic_pl_page',id,1);
			for(var k = 0;k<hishuati.length;k++){
				var def1 = hishuati[k].def1;//话题
				var def2 = hishuati[k].def2;//PK话题
				var img;
				if(hishuati[k].circleDetailImg[0] ==null){
					img = "images/22222.gif";
				}else{
					img = hishuati[k].circleDetailImg[0].middleimg;
					if(img == ""){img = "images/22222.gif";}
				}
				
				if(def1==1){
					var url ;
					if(def2==1){
						url = basePath +"topic_pk/"+hishuati[k].circledetailid;
					}else{
						url = basePath +"topic_tu/"+hishuati[k].circledetailid;
					}
					var str = "<li>" +
					"<div class=\"speak_left\"><a target='_blank' href=\""+url+"\"><img src=\""+img+"\" width='48px' height='48px'  /></a></div>" +
					"<div class=\"speak_right\" >" +
					 "<div class=\"speak_time\">"+hishuati[k].createdatetime+"</div>"+
					" <p><a target='_blank' href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
					"<span>"+hishuati[k].comcount+"回复&nbsp;|&nbsp;<strong>"+hishuati[k].visitcount+"查看</strong></span>" +
					"</div>" +
					"</li>";
				}else{
					var str = "<li>" +
							"<div class=\"speak_left\"><a target='_blank' href=\""+basePath+"det/"+hishuati[k].circleid+"/"+hishuati[k].circledetailid+".html\"><img src=\""+img+"\" width='48px' height='48px' /></a></div>" +
							" <div class=\"speak_right\" >" +
							" <div class=\"speak_time\">"+hishuati[k].createdatetime+"</div>" +
							" <p><a target='_blank' href=\""+basePath+"det/"+hishuati[k].circleid+"/"+hishuati[k].circledetailid+".html\">"+hishuati[k].title+"</a></p>" +
							"<span>"+hishuati[k].comcount+"回复&nbsp;|&nbsp;<strong>"+hishuati[k].visitcount+"查看</strong></span>" +
							"</div>" +
							"</li>";
				}
				$("#hesaid").append(str);
			}
		},
		error : function(data){
			
		}
	});	
}
//wo的投票分页
function getvotenextme(rows,pageNumber,id,ttp){
	var getvotenextURL = "centerAction_getvotenextme.do";
	$.ajax({
		url : getvotenextURL,
		data : {pageNum : pageNumber , pageSize : rows},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#hevote").html("");
			var voteList = data.data.voteList;
			var paginate = new pagination();
			paginate.printPagination(pageNumber, data.data.myvoteCount, 12, 'vote_pl_page',userid,2);
			for(var l = 0;l<voteList.length;l++){
				var votestr = "<li>" +
						" <div class=\"speak_left\"><a target='_blank' href=\""+basePath+"votes/"+voteList[l].voteId+".html\"><img src=\""+voteList[l].myVoteInfoImage[0].imgAddress+"\" width='48px' height='48px'/></a></div>" +
						"<div class=\"speak_right\" >" +
						"<h2><a target='_blank' href=\""+basePath+"votes/"+voteList[l].voteId+".html\">"+voteList[l].title+"</a></h2>" +
						"<div class=\"speak_time\">"+voteList[l].createdate+"</div>" +
						"<span>"+voteList[l].commentcount+"回复&nbsp;|&nbsp;<strong>"+voteList[l].gzcount+"关注</strong></span>" +
						"</div>" +
						"</li>";
				$("#hevote").append(votestr);
			}
		},
		error : function(data){
			
		}
	});	
}

//wo加入的圈子分页
function getcirclenextme(rows,pageNumber,id,ttp){
	var getcirclenextURL = "centerAction_getcirclenextme.do";
	$.ajax({
		url : getcirclenextURL,
		data : {pageNum : pageNumber , pageSize : rows},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#hecircle").html("");
			var add_circles = data.data.add_circles;
			var paginate = new pagination();
			paginate.printPagination(pageNumber, data.data.addcircleCount, 12, 'circle_pl_page',userid,3);
			for(var i = 0;i<add_circles.length;i++){
				if(add_circles[i].circleInfo != null){
					var str;
					if((i+1)%4==0){
						str ="<li style=\"margin-right:0px;\"><a target='_blank' href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='148px' height='148px' /></a>" +
						"<span><a href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\">"+add_circles[i].circleInfo.circlename+"</a></span></li>";
					}else{
						str ="<li><a target='_blank' href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='148px' height='148px' /></a>" +
						"<span><a href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\">"+add_circles[i].circleInfo.circlename+"</a></span></li>";
					}
					$("#hecircle").append(str);
				}
			}
		},
		error : function(data){
			
		}
	});	
}
//wo创建的圈子分页
function getcreatenextme(rows,pageNumber,id,ttp){
	var getcreatenextURL = "centerAction_getcreatenextme.do";
	$.ajax({
		url : getcreatenextURL,
		data : {pageNum : pageNumber , pageSize : rows},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#hecreate").html("");
			var createCirlce = data.data.createCircle;
			var paginate = new pagination();
			paginate.printPagination(pageNumber, data.data.circleCount, 12, 'create_pl_page',userid,4);
			for(var j = 0; j<createCirlce.length;j++){
				var str;
				if((j+1)%4==0){
					str ="<li style=\"margin-right:0px;\"><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='148px' height='148px' /></a>" +
					"<span><a href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\">"+createCirlce[j].circlename+"</a></span></li>";
				}else{
					str ="<li><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='148px' height='148px' /></a>" +
					"<span><a href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\">"+createCirlce[j].circlename+"</a></span></li>";
				}
				$("#hecreate").append(str);
		}
		},
		error : function(data){
			
		}
	});	
}
//最新通知分页
function getnoticenextme(rows,pageNumber,id,ttp){
	var getnoticenextURL = "centerAction_getnoticenextme.do";
	$.ajax({
		url : getnoticenextURL,
		data : {pageNum : pageNumber , pageSize : rows},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#notice").html("");
			var informCount = data.data.informCount;
			if(informCount > 12){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, informCount, 12, 'notice_page',userid,5);
			}
			var inform = data.data.informList;
			for ( var o = 0; o < inform.length; o++) {
				 var informtype = inform[o].informtype;//通知类型
				 if(informtype == 1 ){//为 1 圈子有新加入的成员
					var str ="<li>" +
						"<div class=\"notice_readed\">" +
						"<div class=\"notice_left\">" +
						"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
						"<p>加入了我创建的圈子</p><a href=\""+basePath+"cir2/1/"+inform[o].circleid+".html\">"+inform[o].circleinfo.circlename+"</a>" +
						"</div>" +
						"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
						"</div>" +
						"</li>";
					$("#notice").append(str);
				 }else if(informtype == 2){//为2 圈子内容被评论
					 var str = "<li>" +
				 		"<div class=\"notice_readed\">" +
				 		"<div class=\"notice_left\">" +
				 		"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
				 		"<p>评论了您发表的内容<a href=\""+basePath+"det/"+inform[o].circleid+"/"+inform[o].circledetailid+".html\">"+inform[o].detail.title+"</a></p>" +
				 		"</div>" +
				 		"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
				 		"</div>" +
				 		"</li>";
					 $("#notice").append(str);
				 }else if(informtype == 3 ){//为3 话题被评论
					 var str = "<li>" +
				 		"<div class=\"notice_readed\">" +
				 		"<div class=\"notice_left\">" +
				 		"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
				 		"<p>评论了您发表的话题<a href=\""+basePath+"topic_tu/"+inform[o].circledetailid+".html\">"+inform[o].detail.title+"</a></p>" +
				 		"</div>" +
				 		"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
				 		"</div>" +
				 		"</li>";
					 $("#notice").append(str);
				 }else if(informtype == 4){//话题被关注
					 var str = "<li>" +
				 		"<div class=\"notice_readed\">" +
				 		"<div class=\"notice_left\">" +
				 		"<a class=\"not_img\" href=\""+basePath+"user/"+inform[o].userid+".html\"><img src=\""+inform[o].userinfo.image+"\" title=\""+inform[o].userinfo.nick+"\" style=\"width:48px; height:48px;\"/></a>" +
				 		"<p>关注了您的话题<a href=\""+basePath+"topic_tu/"+inform[o].circledetailid+".html\">"+inform[o].detail.title+"</a></p>" +
				 		"</div>" +
				 		"<div class=\"notice_time\">"+inform[o].createtime+"</div>" +
				 		"</div>" +
				 		"</li>";
					 $("#notice").append(str);
				 }
			}	 
		},
		error : function(data){
			
		}
	});	
}

function setData()
{
		var email = $("#useremailemail").val();
		email = $.trim(email);
		if(email.length<1)
		{
			alert("邮箱不能为空..");
			return false;
		}
		var reMail = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
		var isem = reMail.test(email);
		if(!isem)
		{
			alert("邮箱地址填写错误！");
			return false;
		}
		var nick = $("#username").val();
		if(nick.length<1)
		{
			alert("昵称不能为空..");
			return false;
		}
		if(nick.length>=14)
		{
			alert("昵称过长..");
			return false;
		}
		var birthday = $("#userbirthday1").val();
//		alert(birthday.length);
//		if(birthday.length < 1 || birthday.length > 3){
//			alert("生日填写不符合要求..");
//			return false;
//		}
		var about = $("#num_txt").val();
		if(about.length>70)
		{
			alert("关于自己内容过长..");
			return false;
		}
		$.ajax({
			 url : "centerAction_setData.do", 
			 type : 'post', 
			 data:{id : nick,ids : about, image: $("#image").val(),email : email,
					birthday:  birthday,address:$("#useraddress1").val(), hobby: $("#userhobby1").val(), declaration: $("#userdeclaration").val()},
			 dataType : 'json', 
			 beforeSend : function(data) 
			 { 
			 }, 
			 success : function(data) 
			 { 
				 var result = data.data.result;
				 alert(result);
				 location.href = location.href;
			 }, 
			 error : function(data) 
			 { 
//				 alert("您输入的内容过长，请简单的描述自己！");
				 location.href = location.href;
			 }
		 });
}