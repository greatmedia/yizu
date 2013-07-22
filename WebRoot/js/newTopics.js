var islogin;
function topicClick(flag){
	if(flag==1){
		$("#new").addClass("topic_active");
		$("#hot").removeClass("topic_active");
		$("#pk").removeClass("topic_active");
		$("#fabu").removeClass("topic_active");
		gett3new();
		$("#hotTopics").css("display","none");
		$("#newTopics").css("display","block");
		$("#pkTopics").css("display","none");
		$("#pic_nav_new").css("display","block");
		$("#pic_nav_hot").css("display","none");
		$("#fabuTopic").css("display","none");
	}
	if(flag==2){
		$('#new').removeClass("topic_active");
		$("#hot").addClass("topic_active");
		$("#pk").removeClass("topic_active");
		$("#fabu").removeClass("topic_active");
		gett3hot();
		$("#newTopics").css("display","none");
		$("#hotTopics").css("display","block");
		$("#pkTopics").css("display","none");
		
		$("#pic_nav_new").css("display","none");
		$("#pic_nav_hot").css("display","block");
		$("#fabuTopic").css("display","none");
	}
	if(flag==3){
		$("#new").removeClass("topic_active");
		$("#hot").removeClass("topic_active");
		$("#pk").addClass("topic_active");
		$("#fabu").removeClass("topic_active");
		getTopicPK(10,1,1);
		$("#newTopics").css("display","none");
		$("#hotTopics").css("display","none");
		$("#pkTopics").css("display","block");
		$("#fabuTopic").css("display","none");
	}
	if(flag==4){
		if(!islogin)
		{
			alert("你还没有登录！");
			$("#window_dl").css("display","block");
			return false;
		}
		$("#new").removeClass("topic_active");
		$("#hot").removeClass("topic_active");
		$("#pk").removeClass("topic_active");
		$("#fabu").addClass("topic_active");
		
		$("#newTopics").css("display","none");
		$("#hotTopics").css("display","none");
		$("#pkTopics").css("display","none");
		$("#fabuTopic").css("display","block");
	}
}
var nowtype=0;
var qid;
var u_end_li;
$(function(){
	gett3new();
}); 
//3个最新的话题
function gett3new()
{
	var newPageTopicsURl="topicAction_getTopic3new.do";
	$.ajax({
		url:newPageTopicsURl,
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			islogin = data.data.islogin;
			var new3Topics = data.data.new3Topics;
			shownewTopics(new3Topics,islogin,1);
			
		},
		error:function(data)
		{
		}
	});
}
//3个最热的话题
function gett3hot()
{
	var newPageTopicsURl="topicAction_getTopic3hot.do";
	$.ajax({
		url:newPageTopicsURl,
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#hotTopics").html(" ");
			
			islogin = data.data.islogin;
			var hot3Topics = data.data.hot3Topics;
			showhotTopics(hot3Topics,islogin,2);
			
		},
		error:function(data)
		{
		}
	});
}
function shownewTopics(hot3Topics,islogin,flag){
	$("#newTopics").html("");
	//最上边的三个话题
	for(var t=0;t<hot3Topics.length;t++)
	{
		var title =  hot3Topics[t].title;
		if(title.length>40)
		{
			title = title.substring(0,40);
			title = title+"...";
		}
		var style = "display: none;";
		if(t==0)
		{
			style = "display: block;";
		}
		
		var circleCommentInfo = hot3Topics[t].circleCommentInfo;
		var content="";
		for(var c = 0;c<circleCommentInfo.length;c++)
		{
			content = circleCommentInfo[c].commentinfo;
		}
		if(content.length>50)
		{
			content = content.substring(0,50);
			content = content+"...";
		}
		var circleDetailImg = hot3Topics[t].circleDetailImg;
		for(var j =0;j<circleDetailImg.length;j++)
		{
			var tid = hot3Topics[t].circledetailid;
			if(j>0)
			{
				break;
			}
			var bimg = circleDetailImg[j].bigimg;
			var imgHTML = "<li>"+
		    "<a onmouseover=\"tover('"+bimg+"','"+tid+"','"+title+"',"+t+","+flag+")\" href=\"topic_tu/"+tid+".html\"><img src=\""+bimg+"\" style=\"width:234px;\" /></a>"+
		    "<p>"+title+"</p>"+
		    "</li>";
			$("#pic_roav_new").append(imgHTML);
			var p = "<li><a href=\"javascript:void();\" id=\"bimg_new\"></a></li><div class=\"topic_yinying\" >"+
			"<p id=\"dddddddd_new\"></p>"+
			"</div>";
			$("#b_new").html(p);
			var str = "<a href=\"topic_tu/"+tid+".html\">"+title+"</a>";
			$("#dddddddd_new").append(str);
			$("#dddddddd_new").css({"font-size":"18px","line-height":"22px"});
			qid =tid;
			var img1 = document.createElement("img");
			img1.setAttribute("src",bimg);
			var img1 = "<a href=\"topic_tu/"+qid+".html\"> <img src='"+bimg+"'/></a>";
			$("#bimg_new").append(img1);
			
			/**
			 * 是否显示评论div
			 */
		}
	}
	getTopicnew(10,1,1);
}
function showhotTopics(hot3Topics,islogin,flag){
	$("#hotTopics").html("");
	//最上边的三个话题
	for(var t=0;t<hot3Topics.length;t++)
	{
		var title =  hot3Topics[t].title;
		if(title.length>40)
		{
			title = title.substring(0,40);
			title = title+"...";
		}
		var style = "display: none;";
		if(t==0)
		{
			style = "display: block;";
		}
		
		var circleCommentInfo = hot3Topics[t].circleCommentInfo;
		var content="";
		for(var c = 0;c<circleCommentInfo.length;c++)
		{
			content = circleCommentInfo[c].commentinfo;
		}
		if(content.length>50)
		{
			content = content.substring(0,50);
			content = content+"...";
		}
		var circleDetailImg = hot3Topics[t].circleDetailImg;
		for(var j =0;j<circleDetailImg.length;j++)
		{
			var tid = hot3Topics[t].circledetailid;
			if(j>0)
			{
				break;
			}
			var bimg = circleDetailImg[j].bigimg;
			var imgHTML = "<li>"+
		    "<a onmouseover=\"tover('"+bimg+"','"+tid+"','"+title+"',"+t+","+flag+")\" href=\"topic_tu/"+tid+".html\"><img src=\""+bimg+"\" style=\"width:234px;\" /></a>"+
		    "<p>"+title+"</p>"+
		    "</li>";
			$("#pic_roav_hot").append(imgHTML);
			var p = "<li><a href=\"javascript:void();\" id=\"bimg_hot\"></a></li><div class=\"topic_yinying\" >"+
			"<p id=\"dddddddd_hot\"></p>"+
//			"<div class=\"ipro_input\" id=\"pinglunDIV_hot\">"+
//			   "<a href=\"javascript:void();\" id=\"nowUserImg_hot\" ></a>"+
//			   "<div class=\"ipro_input_y\">"+
//			   "<input type=\"text\" class=\"topic_input_1\" id=\"plcon_hot\" />"+
//			   "</div>"+
//			   "<div class=\"ipro_input_r\"><a href=\"javascript:void(0);\"><img onclick=\"comment("+flag+");\" src=\"images/pro_important.png\" /></a></div>"+
//			"</div>"+
			"</div>";
			$("#b_hot").html(p);
			var str = "<a href=\"topic_tu/"+tid+".html\">"+title+"</a>";
			$("#dddddddd_hot").append(str);
			$("#dddddddd_hot").css({"font-size":"18px","line-height":"22px"});
			qid =tid;
			var img1 = document.createElement("img");
			img1.setAttribute("src",bimg);
			var img1 = "<a href=\"topic_tu/"+qid+".html\"> <img src='"+bimg+"'/></a>";
			$("#bimg_hot").append(img1);
		}
	}
	getTopichot(10,1,1);
}
/**
 * 最新话题
 * @param rows
 * @param pageNumber
 * @param ttp
 */
function getTopicnew(rows,pageNumber,ttp)
{
	nowtype = 1;
	var getIndexTopicURL = "topicAction_getIndexTopicnew.do";
	$.ajax({
		url:getIndexTopicURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#loading").css("display","none");
			$("#b_new").html(html);   
			$("#b_hot").html(html);   
			var count = data.data.count;
			islogin = data.data.islogin;
			/**
			 * 当前用户登录
			 */
			if(islogin)
			{
				var nowuser = data.data.nowuser;
				uimg = nowuser.image;
				if(uimg.length>5)
				{
					uimg = nowuser.otheraccountuserimage;
				}
				var img = "<img  style=\"width:30px; height:30px;\" src=\""+uimg+"\" />";
				$("#nowUserImg_new").html(img);
				$("#nowUserImg_hot").html(img);
			}
			
//--------------------讨论用户数量---------------------------------------------
//			var countuser = data.data.userCount;
//			getTopicUser(countuser);
			
//--------------------热门用户---------------------------------------------
			var hotUser = data.data.userTopics;
			if(hotUser.length<7)
			{
				$("#topic_more").html("没有更多了");
			}
			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var userid = hotUser[u].userinfo.userid;
				var nick = hotUser[u].userinfo.nick;
				var count =  hotUser[u].count;
				u_end_li = userid;
				var hotuserHTML="<li id=\"li"+userid+"\">"+
                "<div class=\"topic_r_img\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
                "<p>"+
                    "<span><a href=\"user/"+userid+".html\" target=\"_blank\">"+nick+"</a></span>"+
                    "<strong>参与了"+count+"个话题讨论</strong>"+
                "</p>"+
            "</li>";
				$("#hotUser").append(hotuserHTML);
			}
			$("#li"+u_end_li).css("border-bottom","none");
			var hotUserCount = data.data.hotUserCount;
			if(hotUserCount<=7)
			{
				$("#topic_more").html("");
			}
//--------------------最新话题---------------------------------------------
			var allTopics = data.data.allTopics;
			for(var i = 0;i<allTopics.length;i++)
			{
				/**
				 * 判断用户头像
				 */
				var userimg = allTopics[i].userinfo.image;
				if(userimg.length<5)
				{
					userimg = allTopics[i].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  allTopics[i].def2;
				/**
				 * 判断内容
				 */
				var content = allTopics[i].circlecontent;
				if(content.length>83)
				{
					content = content.substring(0,83);
					content = content + "...";
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =allTopics[i].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgz = 0;
					isgzimg = "images/pro_important.gif";
				}
				
				var tid = allTopics[i].circledetailid;
				var title = allTopics[i].title;
				var nick = allTopics[i].userinfo.nick;
				var userid = allTopics[i].userinfo.userid;
				var datetime = allTopics[i].createdatetime;
//				var comcount = allTopics[i].comcount;
				var html = "newTopics";
				var nowCount = allTopics.length;
				var circleCommentInfo = allTopics[i].circleCommentInfo;
				var answnick;
				for ( var j = 0; j < circleCommentInfo.length; j++) {
					if(circleCommentInfo[j].userinfo !=null ){
						answnick = circleCommentInfo[j].userinfo.nick;
					}
				}
				if(circleCommentInfo.length<=0)
				{
					answnick="";
				}
				var circleDetailImg = allTopics[i].circleDetailImg;
//				var comcount = circleCommentInfo.length;
				var comcount = allTopics[i].comcount;
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,nowCount,i,1,answnick,circleDetailImg,rows,pageNumber,count,userid);
			}
			var count = data.data.count;
			if(count>10){
				var endHTML="<div id='m11' style=\"text-align: center;\" class='pro_more'><p onclick=''><a onclick='newPageTopics("+rows+","+pageNumber+","+ttp+")'>更多最新话题</a></p></div>";
				$("#newTopics").append(endHTML);
			}
		},
		error:function(data)
		{
		}
	});
}

/**
 * 最热话题
 * @param rows
 * @param pageNumber
 * @param ttp
 */
function getTopichot(rows,pageNumber,ttp){
	nowtype = 2;
	var getIndexTopicURL = "topicAction_getIndexTopichot.do";
	$.ajax({
		url:getIndexTopicURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#loading").css("display","none");
			$("#b_hot").html(html);   
			var count = data.data.count;
			islogin = data.data.islogin;
			/**
			 * 当前用户登录
			 */
			if(islogin)
			{
				var nowuser = data.data.nowuser;
				uimg = nowuser.image;
				if(uimg.length>5)
				{
					uimg = nowuser.otheraccountuserimage;
				}
				var img = "<img  style=\"width:30px; height:30px;\" src=\""+uimg+"\" />";
				$("#nowUserImg_new").html(img);
				$("#nowUserImg_hot").html(img);
			}
//--------------------热门话题---------------------------------------------
			var hotTopics = data.data.hotTopics;
			for ( var h = 0; h < hotTopics.length; h++) {
				/**
				 * 判断用户头像
				 */
				var userimg = hotTopics[h].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotTopics[h].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  hotTopics[h].def2;
				/**
				 * 判断内容
				 */
				var content = hotTopics[h].circlecontent;
				if(content.length>70)
				{
					content = content.substring(0,70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =hotTopics[h].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgz = 0;
					isgzimg = "images/pro_important.gif";
				}
				var tid = hotTopics[h].circledetailid;
				var title = hotTopics[h].title;
				var nick = hotTopics[h].userinfo.nick;
				var userid = hotTopics[h].userinfo.userid;
				var datetime = hotTopics[h].createdatetime;
				var html = "hotTopics";
				var nowCount = hotTopics.length;
				
				var circleCommentInfo = hotTopics[h].circleCommentInfo;
//				alert();
				var answnick;
				for ( var j = 0; j < circleCommentInfo.length; j++) {
					if(circleCommentInfo[j].userinfo != null){
					answnick = circleCommentInfo[j].userinfo.nick;
					}
				}
				if(circleCommentInfo.length<=0)
				{
					answnick="";
				}
				var comcount = hotTopics[h].comcount;
				var circleDetailImg = hotTopics[h].circleDetailImg;
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,nowCount,h,2,answnick,circleDetailImg,rows,pageNumber,count,userid);
			}
			var endHTML="<div id='m21' style=\"text-align: center;\" class='pro_more'><p onclick=''><a onclick='hotPageTopics("+rows+","+pageNumber+","+ttp+")'>更多热门话题</a></p></div>";
			$("#hotTopics").append(endHTML);
		},
		error:function(data)
		{
		}
	});
}

/**
 * PK话题
 * @param rows
 * @param pageNumber
 * @param ttp
 */
function getTopicPK(rows,pageNumber,ttp){
	nowtype = 3;
	var getIndexTopicURL = "topicAction_getIndexTopicPK.do";
	$.ajax({
		url:getIndexTopicURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#pkTopics").html("");
			islogin = data.data.islogin;
			/**
			 * 当前用户登录
			 */
			if(islogin)
			{
				var nowuser = data.data.nowuser;
				uimg = nowuser.image;
				if(uimg.length>5)
				{
					uimg = nowuser.otheraccountuserimage;
				}
				var img = "<img  style=\"width:30px; height:30px;\" src=\""+uimg+"\" />";
				$("#nowUserImg_new").html(img);
				$("#nowUserImg_hot").html(img);
			}
//--------------------PK话题---------------------------------------------
			var pkTopics = data.data.pkTopics;
			var pkcount = data.data.pkcount;
			if(pkTopics.length<=0)
			{
				$("#pk").css("display","none");
			}
			for ( var k = 0; k < pkTopics.length; k++) {
				/**
				 * 判断用户头像
				 */
				var userimg = pkTopics[k].userinfo.image;
				if(userimg.length<5)
				{
					userimg = pkTopics[k].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  pkTopics[k].def2;
				/**
				 * 判断内容
				 */
				var content = pkTopics[k].circlecontent;
				if(content.length>70)
				{
					content = content.substring(0,70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =pkTopics[k].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgz = 0;
					isgzimg = "images/pro_important.gif";
				}
				
				
				var tid = pkTopics[k].circledetailid;
				var title = pkTopics[k].title;
				var nick = pkTopics[k].userinfo.nick;
				var userid = pkTopics[k].userinfo.userid;
				var datetime = pkTopics[k].createdatetime;
//				var comcount = pkTopics[k].comcount;
				var html = "pkTopics";
				var nowCount = pkTopics.length;
				
				var circleCommentInfo = pkTopics[k].circleCommentInfo;
				var answnick;
				for ( var j = 0; j < circleCommentInfo.length; j++) {
					if(circleCommentInfo[j].userinfo!=null){
						answnick = circleCommentInfo[j].userinfo.nick;
					}
				}
				if(circleCommentInfo.length<=0)
				{
					answnick="";
				}
//				var comcount = circleCommentInfo.length;
				var comcount = pkTopics[k].comcount;
				var circleDetailImg = pkTopics[k].circleDetailImg;
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,nowCount,k,3,answnick,circleDetailImg,rows,pageNumber,pkcount,userid);
			}
			var endHTML="<div id='m31' style=\"text-align: center;\" class='pro_more'><p onclick=''><a onclick='pkPageTopics("+rows+","+pageNumber+","+ttp+")'>更多PK话题</a></p></div>";
			if(pkcount>10){
				$("#pkTopics").append(endHTML);
			}
		},
		error:function(data)
		{
		}
	});
}


/**
 * 写入HTML
 * @param tid 话题ID
 * @param title 话题标题
 * @param isgz 是否已经关注该话题
 * @param isgzimg 是否是PK话题图片
 * @param content 话题内容
 * @param ispk 是否是PK话题
 * @param userimg 发表话题的头像
 * @param nick 发表话题的用户昵称
 * @param datetime 发表时间
 * @param comcount 话题评论的数量
 * @param html 要写入到的div 
 * @param count 话题数量
 * @param index 索引
 * @param type 类型
 * @param answnick 回答问题的用户昵称
 * @param circleDetailImg 图片
 * @param rows 多少数据
 * @param pageNumber 当前页
 * @param counts 数量
 * @param userid 用户ID
 */
function writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,count,index,type,answnick,circleDetailImg,rows,pageNumber,counts,userid)
{
	var topic_subnav = document.createElement("div");
	topic_subnav.className="topic_subnav";
	$("#"+html+"").append(topic_subnav);
	
	
	var topic_l = document.createElement("div");
	topic_l.className="topic_l";
	topic_subnav.appendChild(topic_l);
	
	var topic_l_a = document.createElement("a");
	topic_l_a.href="user/"+userid+".html";
	topic_l_a.target="_blank";
	topic_l.appendChild(topic_l_a);
	
	var topic_l_a_img = document.createElement("img");
	topic_l_a_img.src=userimg;
	topic_l_a_img.wdith="50";
	topic_l_a_img.height="50";
	topic_l_a.appendChild(topic_l_a_img);

	var topic_r1 = document.createElement("div");
	topic_r1.className="topic_r1";
	topic_subnav.appendChild(topic_r1);
	
	var topic_r = document.createElement("div");
	topic_r.className="topic_r";
	topic_r1.appendChild(topic_r);
	if(ispk!=1)
	{
		var pro_tiilt_a = document.createElement("a");
		pro_tiilt_a.target="_blank";
		pro_tiilt_a.href="topic_tu/"+tid+".html";
		topic_r.appendChild(pro_tiilt_a);
		
		var pro_tiilt = document.createElement("div");
		pro_tiilt.className="pro_tiilt";
		pro_tiilt_a.appendChild(pro_tiilt);

		var pro_tiilt_h2 = document.createElement("h2");
		pro_tiilt_h2.innerHTML=title;
		pro_tiilt.appendChild(pro_tiilt_h2);

		var top_impo = document.createElement("div");
		top_impo.className="top_impo";
		pro_tiilt.appendChild(top_impo);

		var top_impo_a = document.createElement("a");
		top_impo_a.id="top_impo_a"+type+tid;
		top_impo_a.href="javascript:void(0)";
//		top_impo_a.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
		top_impo.appendChild(top_impo_a);

//		var top_impo_a_img = document.createElement("img");
//		top_impo_a_img.id="isimg"+type+tid;
//		top_impo_a_img.src=isgzimg;
//		top_impo_a_img.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
////		top_impo_a_img.title="ssssssssssssssssssssssssss";
//		top_impo_a.appendChild(top_impo_a_img);
		var img = "<img src=\""+isgzimg+"\" id=\"isimg"+type+tid+"\" onclick=\"gzTopic('"+tid+"',"+isgz+","+type+");\"  />";
//		alert(img);
		$("#top_impo_a"+type+tid).append(img);
		
		var topic_r_span = document.createElement("span");
	
		topic_r_span.id="userTopic"+type+tid;
	
		topic_r.appendChild(topic_r_span);
		
		var topic_r_span_strong = document.createElement("strong");
		topic_r_span.appendChild(topic_r_span_strong);
		
		var topic_r_span_strong_a = document.createElement("a");
		topic_r_span_strong_a.href="user/"+userid+".html";
		topic_r_span_strong_a.target="_blank";
		topic_r_span_strong_a.innerHTML=nick;
		topic_r_span_strong.appendChild(topic_r_span_strong_a);
	
		$("#userTopic"+type+tid).append("&nbsp;&nbsp;发表于"+datetime+"");
		
		var p = document.createElement("div");
		p.className = "topic_r_p";
		p.innerHTML=content;
		topic_r.appendChild(p);
		if(circleDetailImg.length>0)
		{
			for ( var z = 0; z < circleDetailImg.length; z++) {
				if(z>0)
				{
					break;
				}
				var topic_move = document.createElement("div");
				topic_move.className="topic_move";
				topic_r1.appendChild(topic_move);
				
				var topic_move_div = document.createElement("div");
				topic_move_div.id = "topic_move_div"+type+tid;
				topic_move.appendChild(topic_move_div);
				var topic_move_div_img = "<a href=\"topic_tu/"+tid+".html\" target=\"_blank\"><img src="+circleDetailImg[z].bigimg+" width = '645px' /></a>";
				$("#topic_move_div"+type+tid).html(topic_move_div_img);
//				var topic_move_div_img = document.createElement("img");
//				topic_move_div_img.src=circleDetailImg[z].bigimg;
//				topic_move_div_img.setAttribute("onload","scaleImage(this,645,10000)");
//				topic_move_div_img.width="645px";
//				topic_move_div.appendChild(topic_move_div_img);
//				topic_move_div.appendChild(topic_move_div_img);
			}
		}
		var topic_b = document.createElement("div");
		topic_b.className="topic_b";
		topic_r1.appendChild(topic_b);
		
		var topic_b_p = document.createElement("p");

		topic_b_p.id="answ"+type+tid;
		
		
		
		topic_b.appendChild(topic_b_p);
		
		var topic_b_p_span = document.createElement("span");
		topic_b_p.appendChild(topic_b_p_span);
		
		var topic_b_p_span_a = document.createElement("a");
		topic_b_p_span_a.innerHTML=answnick;
		topic_b_p_span_a.target="_blank";
		topic_b_p_span_a.href="user/"+userid+".html";
		topic_b_p_span.appendChild(topic_b_p_span_a);
		$("#answ"+type+tid).append("&nbsp;&nbsp;参与了此话题讨论");
		
		var pro_b_pingl =  document.createElement("div");
		pro_b_pingl.id="ad"+type+tid;
		pro_b_pingl.className="pro_b_pingl";
		topic_b.appendChild(pro_b_pingl);
		
		var pro_b_pingl_imgs = document.createElement("img");
		pro_b_pingl_imgs.src="images/pro_pinglun.gif";
		pro_b_pingl.appendChild(pro_b_pingl_imgs);
		
		$("#ad"+type+tid).append("&nbsp;&nbsp;"+comcount+"&nbsp;条评论");
	}else{
		var topic_r_pk = document.createElement("div");
		topic_r_pk.className="topic_r_pk";
		topic_r.appendChild(topic_r_pk);
		var topic_r_pk_img = document.createElement("img");
		topic_r_pk_img.src="images/pro_PK.png";
		topic_r_pk.appendChild(topic_r_pk_img);
	
		var topic_r_pinglun = document.createElement("div");
		topic_r_pinglun.className="topic_r_pinglun";
		topic_r.appendChild(topic_r_pinglun);
		
		var pro_tiilt = document.createElement("div");
		pro_tiilt.className="pro_tiilt";
		topic_r_pinglun.appendChild(pro_tiilt);

		var pro_tiilt_a = document.createElement("a");
		pro_tiilt_a.target="_blank";
		pro_tiilt_a.href="topic_pk/"+tid+".html";
		pro_tiilt.appendChild(pro_tiilt_a);
		
		var pro_tiilt_h2 = document.createElement("h2");
		pro_tiilt_h2.innerHTML=title;
		pro_tiilt_a.appendChild(pro_tiilt_h2);

		var top_impo = document.createElement("div");
		top_impo.className="top_impo";
		pro_tiilt.appendChild(top_impo);

		var top_impo_a = document.createElement("a");
		top_impo_a.href="javascript:void(0)";
//		top_impo_a.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
		top_impo.appendChild(top_impo_a);

		var top_impo_a_img = document.createElement("img");
		top_impo_a_img.id="isimg"+type+tid;
		top_impo_a_img.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
		top_impo_a_img.src=isgzimg;
		top_impo_a.appendChild(top_impo_a_img);
		
		var topic_r_span = document.createElement("span");
		topic_r_span.id="userTopic"+type+tid;
		topic_r_pinglun.appendChild(topic_r_span);
		
		var topic_r_span_strong = document.createElement("strong");
		topic_r_span.appendChild(topic_r_span_strong);
		
		var topic_r_span_strong_a = document.createElement("a");
		topic_r_span_strong_a.target="_blank";
		topic_r_span_strong_a.href="user/"+userid+".html";
		topic_r_span_strong_a.innerHTML=nick;
		
		topic_r_span_strong.appendChild(topic_r_span_strong_a);
		
		$("#userTopic"+type+tid).append("&nbsp;&nbsp;发表于"+datetime+"");
		
		var topic_r_p = document.createElement("div");
		topic_r_p.className = "topic_r_p";
		topic_r_p.innerHTML=content;
		topic_r.appendChild(topic_r_p);
		
		for ( var z = 0; z < circleDetailImg.length; z++) {
			if(z>0)
			{
				break;
			}
			
			var topic_move = document.createElement("div");
			topic_move.className="topic_move";
//			topic_r_p.appendChild(topic_move);
//			topic_r.appendChild(topic_move);topic_r1
			topic_r1.appendChild(topic_move);
			
			
			var topic_move_div = document.createElement("div");
			topic_move_div.id="move"+type+tid;
			topic_move.appendChild(topic_move_div);
			
			
			topic_move.appendChild(topic_move_div);
			var topic_move_div_img = "<a href=\"topic_pk/"+tid+".html\" target=\"_blank\"><img src="+circleDetailImg[z].bigimg+" width = '645px' /></a>";
			$("#move"+type+tid).html(topic_move_div_img);
//			 onload="+scaleImage(this,645,10000)+"
//			topic_move_div.appendChild(topic_move_div_img);
			
		}
		
		var topic_b = document.createElement("div");
		topic_b.className="topic_b";
		topic_r1.appendChild(topic_b);
		
		var topic_b_p = document.createElement("p");
		topic_b_p.id="answ"+type+tid;
		topic_b.appendChild(topic_b_p);
		
		var topic_b_p_span = document.createElement("span");
		topic_b_p.appendChild(topic_b_p_span);
		
		var topic_b_p_span_a = document.createElement("a");
		topic_b_p_span_a.innerHTML=answnick;
//		topic_b_p_span.href="javascript:void()";
		topic_b_p_span_a.target="_blank";
		topic_b_p_span_a.href="user/"+userid+".html";
		topic_b_p_span.appendChild(topic_b_p_span_a);
		
		$("#answ"+type+tid).append("&nbsp;&nbsp;参与了此话题讨论");
		
		var pro_b_pingl =  document.createElement("div");
		pro_b_pingl.id="ad"+type+tid;
		pro_b_pingl.className="pro_b_pingl";
		topic_b.appendChild(pro_b_pingl);
		
		var pro_b_pingl_imgs = document.createElement("img");
		pro_b_pingl_imgs.src="images/pro_pinglun.gif";
		pro_b_pingl.appendChild(pro_b_pingl_imgs);
		
		$("#ad"+type+tid).append("&nbsp;&nbsp;"+comcount+"&nbsp;条评论");
	}
	$("#a").html("");
//--------------------页面尾部代码---------------------------------------------
	if(counts>=1)
	{
		if(count==(index+1))
		{
			var t="";
			var id = "";
			if(type==1)
			{
				su="newPageTopics("+rows+")";
				t = "更多最新话题";
				id="m1";
			}
			if(type==2)
			{
				su="hotPageTopics()";
				t = "更多热门话题";
				id="m2";
			}
			if(type==3)
			{
				su="pkPageTopics()";
				t = "更多PK话题";
				id="m3";
				
			}
//			var endHTML="<div id='"+id+"' style=\"text-align: center;\" ><p onclick=''><a onclick='"+su+"'>"+t+"</a></p></div>";
//			var endHTML = "<div class=\"pro_more\" >"+
//			   "<ul id=\"a"+type+"\">"+
//			   "</ul>"+
//			   "</div>";
//			$("#"+html+"").append(endHTML);
//			var paginate = new pagsination();
//			paginate.printPagination(2, counts, rows, "a"+type+"",1,type);
		}
	}
}
function pagsination() {
	this.printPagination = function(pageNum, totalRecords, numPerPage, divObj,id,ttp) {  //当前页     总数     没有显示数
		var pageHtml = '';
		var pageNum = pageNum;
		if (null == pageNum || '' == pageNum) {
			$('#' + divObj).html('');
			return;
		}
		if (pageNum < 1) {
			$('#' + divObj).html('');
			return;
		}
		var totalNum = totalRecords;
		if (totalNum <= 0) {
			$('#' + divObj).html('');
			return;
		}
		var numPerPage = numPerPage;
		var totalPages = (totalNum % numPerPage) == 0
				? (totalNum / numPerPage)
				: (totalNum / numPerPage + 1);
		totalPages = parseInt(totalPages, 0);
		if (pageNum > totalPages) {
			pageNum = totalPages;
		}
//		pageHtml +='<a href="javascript:void(0);" onclick="flipPage('+numPerPage+',1,\''+id+'\',\''+ttp+'\');" >首页</a>';
		if (pageNum > 1) {
			var prePageNum = pageNum - 1;
//			pageHtml += '<a href="javascript:void(0);" onclick="flipPage('+numPerPage+','+ prePageNum +',\''+id+'\',\''+ttp+'\');">上一页&gt; </a>';
		} else if (pageNum == 1) {
//			pageHtml += '<a href="javascript:void(0);">上一页&lt; </a>';
		}
		if (0 < pageNum && pageNum <= 3) {
			if (totalPages <= 7) {
//				alert(pageNum);
				for (var j = 1; j <= totalPages; j++) {
					if (j == pageNum) {
//						alert("ssss"+j);
//						pageHtml += '<span class="current">' + j+ '</span>';
						pageHtml += '<li><a><img src="images/pro_dianhover.gif" title="第'+j+'页"/></a></li>';
					} else {
//						pageHtml += '<a href="javascript:void(0);"  onclick="flipPage('+numPerPage+',' + j + ',\''+id+'\',\''+ttp+'\');">' + j + '</a>';<img src="images/pro_dian.gif"/>
						pageHtml += '<li><a href="javascript:void(0);"  onclick="flipPage('+numPerPage+',' + j + ',\''+id+'\',\''+ttp+'\');"><img src="images/pro_dian.gif" title="第'+j+'页" /></li></a></li>';
					}
				}
			} else {
				for (var j = 1; j <= 7; j++) {
					if (j == pageNum) {
						pageHtml += '<li><a><img src="images/pro_dianhover.gif" title="第'+j+'页"/></a></li>';
					} else {
						pageHtml += '<li><a href="javascript:void(0);"  onclick="flipPage('+numPerPage+','+ j + ',\''+id+'\',\''+ttp+'\');"></a></li>';
					}
				}
			}
		}
		if (pageNum >= 4) {
			if (pageNum <= totalPages - 3) {
				var p = 3;
				for (var i = -p; i <= p; i++) {
					var page = pageNum + i;
					if (page > 0 && page <= totalPages) {
						if (page == pageNum) {
//							pageHtml += '<span class="current">' + page+ '</span>';
							pageHtml += '<li><a><img src="images/pro_dianhover.gif"/></a></li>';
						} else {
							pageHtml += '<li><a href="javascript:void(0);"  onclick="flipPage('+numPerPage+','+ page + ',\''+id+'\',\''+ttp+'\');">' + page + '</a></li>';
						}
					} else if (page > totalPages) {
					}
				}
			} else if (pageNum <= totalPages && pageNum >= totalPages - 3) {
				for (var i = totalPages - 7 + 1; i <= totalPages; i++) {
					if (i > 0 && i <= totalPages) {
						if (i == pageNum) {
//							pageHtml += '<span class="current">' + i+ '</span>';
//							pageHtml += '<li><img src="images/pro_dianhover.gif"/></li>';
						} else {
							pageHtml += '<a href="javascript:void(0);"  onclick="flipPage('+numPerPage+','+ i + ',\''+id+'\',\''+ttp+'\');">' + i + '</a>';
						}
					}
				}
			}
		}
		
		if (pageNum < totalPages) {
			var nextPage = pageNum + 1;
//			pageHtml += '<a href="javascript:void(0);" onclick="flipPage('+numPerPage+','+ nextPage + ',\''+id+'\',\''+ttp+'\');">下一页&gt; </a>';
		} else if (pageNum == totalPages) {
//			pageHtml += '<a href="javascript:void(0);">下一页&gt; </a>';
		}else{
//			pageHtml += '<a href="javascript:void(0);">下一页&gt; </a>';
		}
//		pageHtml +='<a href="javascript:void(0);" onclick="flipPage('+numPerPage+','+totalPages+',\''+id+'\',\''+ttp+'\');" >尾页</span>';
		$('#' + divObj).html(pageHtml);
	};
}
/**
 * 用户数量
 * @param countuser
 */
function getTopicUser(countuser)
{
//	$("#nums").html("");
	if(countuser>=0 && countuser<10)
	{
		$("#nums").append("<li><img src=\"images/time_"+countuser+".gif\" /></li>");
	}
	if(countuser>=10 && countuser<100)
	{
			var s=Math.floor(countuser/10);
			var g = countuser%10;
			
			$("#nums").append("<li><img src=\"images/time_"+s+".gif\" /></li>");
			$("#nums").append("<li><img src=\"images/time_"+g+".gif\" /></li>");
	}
	if(countuser>=100 && countuser<1000)
	{
		var b = Math.floor(countuser/100);
		var s = Math.floor((countuser/10)%10);
		var g = countuser%10;
		$("#nums").append("<li><img src=\"images/time_"+b+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+s+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+g+".gif\" /></li>");
	}
	if(countuser>=1000 && countuser<10000)
	{
		var q = Math.floor(countuser/1000);
		var b = Math.floor((countuser/100)%10);
		var s = Math.floor((countuser/10)%10);
		var g = countuser%10;
		$("#nums").append("<li><img src=\"images/time_"+q+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+b+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+s+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+g+".gif\" /></li>");
	}
	if(countuser>=10000 && countuser<100000)
	{
		var w = Math.floor(countuser/10000);
		var q = Math.floor((countuser/1000)%10);
		var b = Math.floor((countuser/100)%10);
		var s = Math.floor((countuser/10)%10);
		var g = countuser%10;
		$("#nums").append("<li><img src=\"images/time_"+w+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+q+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+b+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+s+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+g+".gif\" /></li>");
	}
	if(countuser>=100000 && countuser<1000000)
	{
		var sw = Math.floor(countuser/100000);
		var w = Math.floor((countuser/10000)%10);
		var q = Math.floor((countuser/1000)%10);
		var b = Math.floor((countuser/100)%10);
		var s = Math.floor((countuser/10)%10);
		var g = countuser%10;
		$("#nums").append("<li><img src=\"images/time_"+sw+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+w+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+q+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+b+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+s+".gif\" /></li>");
		$("#nums").append("<li><img src=\"images/time_"+g+".gif\" /></li>");
	}
}
/**
 * 关注话题
 * @param tid
 */
function gzTopic(tid,isgz,type)
{
//	if(!islogin)
//	{
//		alert("你还没有登录！");
////		myScroll();
//		$("#window_dl").css("display","block");
//		return false;
//	}
	if(isgz==1)
	{
		alert("你已经关注该话题了！");
		return false;
	}
	var gzTopicURl="gztopicsAction_gzTopic.do";
	$.ajax({
		url:gzTopicURl,
		data:{tid : tid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			islogin = data.data.islogin;
			var result = data.data.result;
			if(islogin)
			{
				$("#isimg"+1+tid).attr("src","images/pro_importan_1t.gif");
				$("#isimg"+2+tid).attr("src","images/pro_importan_1t.gif");
				$("#isimg"+3+tid).attr("src","images/pro_importan_1t.gif");
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
/**
 * 热门话题
 * @param rows
 * @param pageNumber
 * @param ttp
 */
function getHotTopics(rows,pageNumber,ttp)
{
//	$("#m2").html("");
	var getHotTopicsURl="topicAction_hotPageTopics.do";
	$.ajax({
		url:getHotTopicsURl,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			
			var hotTopics = data.data.hotTopics;
			var count = data.data.count;
			if(count >10){
				var paginate = new pagsination();
				paginate.printPagination(pageNumber, count, rows, 'a2',1,ttp);
			}else{
				$("#a2").html("");
			}
			for ( var i = 0; i < hotTopics.length; i++) {
				/**
				 * 判断用户头像
				 */
				var userimg = hotTopics[i].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotTopics[i].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  hotTopics[i].def2;
				/**
				 * 判断内容
				 */
				var content = hotTopics[i].circlecontent;
				if(content.length>70)
				{
					content = content.substring(0,70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =hotTopics[i].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgzimg = "images/pro_important.gif";
				}
				
				var tid = hotTopics[i].circledetailid;
				var title = hotTopics[i].title;
				var nick = hotTopics[i].userinfo.nick;
				var userid = hotTopics[i].userinfo.userid;
				var datetime = hotTopics[i].createdatetime;
				var comcount = hotTopics[i].comcount;
				var html = "hotTopics";
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,rows,pageNumber,count,userid);
			}
		},
		error:function(data)
		{
		}
	});
}

function tover(bimg,tid,aaaa,t,flag)
{
//	alert(bimg+" "+tid+" "+aaaa+"  "+t+"  "+flag);
	qid = tid;
	var img = "<a href=\"topic_tu/"+qid+".html\"><img src='"+bimg+"' /></a>";
	if(flag==1){
		$("#bimg_new").html(img);
	}else if(flag==2){$("#bimg_hot").html(img);}
	
	if(aaaa.length>80)
	{
		aaaa.substring(0,80);
		aaaa = aaaa+"...";
	}
	
	if(flag==1){
		$("dddddddd_new").css("display","none");
		var str = "<a href=\"topic_tu/"+tid+".html\">"+aaaa+"</a>";
		$("#dddddddd_new").html(str);
		$("#dddddddd_new").css({"font-size":"18px","line-height":"22px"});
		$("#dddddddd_new").css("display","block");
	}else if(flag==2){
		$("#dddddddd_hot").css("display","none");
		var str = "<a href=\"topic_tu/"+tid+".html\">"+aaaa+"</a>";
		$("#dddddddd_hot").html(str);
		$("#dddddddd_hot").css({"font-size":"18px","line-height":"22px"});
		$("#dddddddd_hot").css("display","block");
	}
	
}
/**
 * 后台检查当前用户是否已经登录
 */
function checkUserLogin()
{
	var checkUserLoginURl="centerAction_checkUserLogin.do";
	$.ajax({
		url:checkUserLoginURl,
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var userLogin = data.data.userLogin;
			if(userLogin)
			{
				return true;
			}else{
				alert("你还没有登录！");
				$("#window_dl").css("display","block");
				return false;
			}
		},
		error:function(data)
		{
			return false;
		}
	});
}
/**
 * 评论
 * @returns {Boolean}
 */
function comment(flag)
{
	if(!islogin)
	{
		alert("你还没有登录！");
		$("#window_dl").css("display","block");
		return false;
	}
	var plcon ="";
	if(flag==1){
		plcon = $("#plcon_new").val();//评论的内容
	}else{
		plcon = $("#plcon_hot").val();//评论的内容
	}
	
	if(plcon.length <= 0)
	{

		alert("评论内容不能为空！");
		return false;
	}
	if(plcon.length >= 200)
	{
		alert("评论内容太长了！");
		return false;
	}
	var insertCommentURl="topicAction_insertComment.do";
	$.ajax({
		url:insertCommentURl,
		data:{tid : qid,comcontent : plcon},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			alert("评论成功！");
			$("#plcon").val("");
			$("#ccidComment").html(plcon);
		},
		error:function(data)
		{
			alert(data.msg);
		}
	});
	
}
/**
 * 更多的热门用户
 */
var page = 1;
function moreUser()
{
	page++;
	var moreUserURl="topicAction_moreUser.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 7,pageNum : page},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#li"+u_end_li).removeAttr("style");
			var hotUser = data.data.userTopics;
			if(hotUser.length<7)
			{
				$("#topic_more").html("没有更多了");
			}
			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var userid = hotUser[u].userinfo.userid;
				u_end_li = userid;
				var hotuserHTML="<li id = \"li"+userid+"\">"+
                "<div class=\"topic_r_img\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
                "<p>"+
                    "<span><a href=\"user/"+userid+".html\" target=\"_blank\">"+hotUser[u].userinfo.nick+"</a></span>"+
                    "<strong>参与了"+hotUser[u].count+"个话题讨论</strong>"+
                "</p>"+
            "</li>";
				$("#hotUser").append(hotuserHTML);
			}
			$("#li"+u_end_li).css("border-bottom","none");
		},
		error:function(data)
		{
		}
	});
}
var pi = 1;
function pkPageTopics(rows,pageNumber,ttp)
{
	pi = pi+1;
	pageNumber = pi;
	var pkPageTopicsURl="topicAction_pkPageTopics.do";
	$.ajax({
		url:pkPageTopicsURl,
		data:{pageSize : rows,pageNum : pageNumber},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var s = pi-1;
			$("#m3"+s).html("");
//			$("#pkTopics").html("");
//			var hotUser = data.data.userTopics;
			var pkTopics = data.data.pkTopics;
			var pkcount = data.data.pkcount;
			var count = data.data.pkcount;
			if(count >10){
				var paginate = new pagsination();
				paginate.printPagination(pageNumber, count, rows, 'a2',1,ttp);
			}else{
				$("#a3").html("");
			}
			for ( var k = 0; k < pkTopics.length; k++) {
				/**
				 * 判断用户头像
				 */
				var userimg = pkTopics[k].userinfo.image;
				if(userimg.length<5)
				{
					userimg = pkTopics[k].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  pkTopics[k].def2;
				/**
				 * 判断内容
				 */
				var content = pkTopics[k].circlecontent;
				if(content.length>70)
				{
					content = content.substring(0,70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =pkTopics[k].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgzimg = "images/pro_important.gif";
				}
				
				var tid = pkTopics[k].circledetailid;
				var title = pkTopics[k].title;
				var nick = pkTopics[k].userinfo.nick;
				var userid = pkTopics[k].userinfo.userid;
				var datetime = pkTopics[k].createdatetime;
				var comcount = pkTopics[k].comcount;
				var html = "pkTopics";
				var nowCount = pkTopics.length;
				
				var circleCommentInfo = pkTopics[k].circleCommentInfo;
				var answnick;
				for ( var j = 0; j < circleCommentInfo.length; j++) {
					answnick = circleCommentInfo[j].userinfo.nick;
				}
				if(circleCommentInfo.length<=0)
				{
					answnick="";
				}
//				var comcount = circleCommentInfo.length;
				var comcount = pkTopics[k].comcount;
				var circleDetailImg = pkTopics[k].circleDetailImg;
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,nowCount,k,3,answnick,circleDetailImg,rows,pageNumber,pkcount,userid);
			}
			var t="";
			var endHTML="";
			if(pkTopics.length>=3)
			{
				t="更多PK话题";
				endHTML="<div id='m3"+pi+"' style=\"text-align: center;\" class='pro_more'><p onclick=''><a onclick='pkPageTopics("+rows+","+pageNumber+","+ttp+")'>"+t+"</a></p></div>";
			}else{
				t="没有更多PK话题了";
				endHTML="<div id='m3"+pi+"' style=\"text-align: center;\" class='pro_more'><p onclick=''><a>"+t+"</a></p></div>";
			}
			$("#pkTopics").append(endHTML);
		},
		error:function(data)
		{
		}
	});
}
var ni = 1;
function newPageTopics(rows,pageNumber,ttp)
{
	ni = ni+1;
	pageNumber = ni;
	var newPageTopicsURl="topicAction_newPageTopics.do";
	$.ajax({
		url:newPageTopicsURl,
		data:{pageSize : rows,pageNum : pageNumber},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var s = ni-1;
			$("#m1"+s).html("");
//			$("#newTopics").html("");
			var allTopics = data.data.allTopics;
			var count = data.data.count;
			if(count >10){
				var paginate = new pagsination();
				paginate.printPagination(pageNumber, count, rows, 'a1',1,ttp);
			}else{
				$("#a1").html("");
			}
			for(var i = 0;i<allTopics.length;i++)
			{
				/**
				 * 判断用户头像
				 */
				var userimg = allTopics[i].userinfo.image;
				if(userimg.length<5)
				{
					userimg = allTopics[i].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  allTopics[i].def2;
				/**
				 * 判断内容
				 */
				var content = allTopics[i].circlecontent;
				if(content.length>70)
				{
					content = content.substring(0,70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =allTopics[i].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgzimg = "images/pro_important.gif";
				}
				
				var tid = allTopics[i].circledetailid;
				var title = allTopics[i].title;
				var nick = allTopics[i].userinfo.nick;
				var userid = allTopics[i].userinfo.userid;
				var datetime = allTopics[i].createdatetime;
//				var comcount = allTopics[i].comcount;
				var html = "newTopics";
				var nowCount = allTopics.length;
				var circleCommentInfo = allTopics[i].circleCommentInfo;
				var answnick;
				for ( var j = 0; j < circleCommentInfo.length; j++) {
					answnick = circleCommentInfo[j].userinfo.nick;
				}
				if(circleCommentInfo.length<=0)
				{
					answnick="";
				}
				var circleDetailImg = allTopics[i].circleDetailImg;
//				var comcount = circleCommentInfo.length;
				var comcount = allTopics[i].comcount;
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,nowCount,i,1,answnick,circleDetailImg,rows,pageNumber,count,userid);
			}
			var t="";
			var endHTML="";
			if(allTopics.length>=3)
			{
				t="更多最新话题";
				endHTML="<div id='m1"+ni+"' style=\"text-align: center;\" class='pro_more'><p onclick=''><a onclick='newPageTopics("+rows+","+pageNumber+","+ttp+")'>"+t+"</a></p></div>";
			}else{
				t="没有更多最新话题了";
				endHTML="<div id='m1"+ni+"' style=\"text-align: center;\" class='pro_more'><p onclick=''><a>"+t+"</a></p></div>";
			}
			$("#newTopics").append(endHTML);
//			var endHTML = "<div class=\"pro_more\" >"+
//			   "<ul id=\"a"+type+"\">"+
//			   "</ul>"+
//			   "</div>";
			
		},
		error:function(data)
		{
		}
	});
}
var hi = 1;
function hotPageTopics(rows,pageNumber,ttp)
{
	hi = hi+1;
	pageNumber = hi;
	var hotPageTopics="topicAction_hotPageTopics.do";
	$.ajax({
		url:hotPageTopics,
		data:{pageSize : rows,pageNum : pageNumber},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var s = hi-1;
			$("#m2"+s).html("");
//			$("#hotTopics").html("");
			var hotTopics = data.data.hotTopics;
			var count = data.data.count;
			if(count >10){
				var paginate = new pagsination();
				paginate.printPagination(pageNumber, count, rows, 'a2',1,ttp);
			}else{
				$("#a2").html("");
			}
			for ( var h = 0; h < hotTopics.length; h++) {
				/**
				 * 判断用户头像
				 */
				var userimg = hotTopics[h].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotTopics[h].userinfo.otheraccountuserimage;
				}
				if(userimg.length<2)
				{
					userimg="images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk=  hotTopics[h].def2;
				/**
				 * 判断内容
				 */
				var content = hotTopics[h].circlecontent;
				if(content.length>70)
				{
					content = content.substring(0,70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz =hotTopics[h].isgz;
				if(isgz==1)
				{
					isgzimg = "images/pro_importan_1t.gif";
				}else{
					isgzimg = "images/pro_important.gif";
				}
				var tid = hotTopics[h].circledetailid;
				var title = hotTopics[h].title;
				var nick = hotTopics[h].userinfo.nick;
				var userid = hotTopics[h].userinfo.userid;
				var datetime = hotTopics[h].createdatetime;
//				var comcount = hotTopics[h].comcount;
				var html = "hotTopics";
				var nowCount = hotTopics.length;
				
				var circleCommentInfo = hotTopics[h].circleCommentInfo;
				var answnick;
				for ( var j = 0; j < circleCommentInfo.length; j++) {
					answnick = circleCommentInfo[j].userinfo.nick;
				}
				if(circleCommentInfo.length<=0)
				{
					answnick="";
				}
//				var comcount = circleCommentInfo.length;
				var comcount = hotTopics[h].comcount;
				var circleDetailImg = hotTopics[h].circleDetailImg;
				writeHTML(tid,title,isgz,isgzimg,content,ispk,userimg,nick,datetime,comcount,html,nowCount,h,2,answnick,circleDetailImg,rows,pageNumber,count,userid);
			}
			var t="";
			var endHTML="";
			if(hotTopics.length>=3)
			{
				t="更多热门话题";
				endHTML="<div id='m2"+hi+"' style=\"text-align: center;\" class='pro_more'><p onclick=''><a onclick='hotPageTopics("+rows+","+pageNumber+","+ttp+")'>"+t+"</a></p></div>";
			}else{
				t="没有更多热门话题了";
				endHTML="<div id='m2"+hi+"' style=\"text-align: center;\" class='pro_more'><p onclick=''><a>"+t+"</a></p></div>";
			}
			$("#hotTopics").append(endHTML);
		},
		error:function(data)
		{
		}
	});
}
/**
 * 分页
 * @param rows
 * @param pageNumber
 * @param id
 * @param ttp
 */
function flipPage(rows,pageNumber,id,ttp) 
{
	if(ttp==1)
	{
		newPageTopics(rows,pageNumber,id,ttp);
	}
	if(ttp==2)
	{
		hotPageTopics(rows,pageNumber,id,ttp);
	}
	if(ttp==3)
	{
		pkPageTopics(rows,pageNumber,id,ttp);
	}
}
