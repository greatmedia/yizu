var userid;
$(function(){
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	userid = realurl.substring(idindexof + 1,realurl.search(".html"));
	showData(userid,18,1,1);
});

function showData(userid,rows,pageNumber,ttp)
{
	var centerAction_topic="centerAction_getTAusercenter.do";
	$.ajax({
	url:centerAction_topic,
	data:{pageNum : pageNumber,pageSize : rows,id : userid},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		var user = data.data.user;
		document.title = user.nick+"的个人中心";
		var img = user.image;
		if(img == "" || img == null){
			img = "../images/nobody.gif";
		}
		$("#userimg").attr({"src":img,"width":"80"});//头像
		if(user.website != null && user.website != ""){
			$("#user_bj").attr({"src": user.website,"width":"720","height":"295"});
		}else{
			$("#user_bj").attr("src", "images/gerenzhongxin_bj.jpg");
		}
		
		$("#nick").html(user.nick)//名字
		if(user.sex !=null && user.sex != "" ){
			if(user.sex == "2"){$("#usersex").html("逸千金");}else{$("#usersex").html("逸骑士");}
		}else{
			$("#usersex").html("逸骑士");
		}
		if(user.address !=null && user.address != "" ){$("#useraddress").html(""+user.address+"")}
		if(user.hobby !=null && user.hobby != "" ){$("#userhobby").html(""+user.hobby+"")}
		$("#aboutme").html(user.what);//关于自己
		$("#huatiCount").html(data.data.count);	//发言数
		$("#circleCount").html(data.data.circleCount);//我创建的圈子数
//		$("#myvoteCount").html(data.data.myvoteCount);//我创建的投票数
		//他A 加入的圈子
		var add_circles = data.data.add_circles;
		var addCount = data.data.addcircleCount;
		if(addCount<=0){$("#jiaruli").html("<div class='u_cont'><h3>TA还没有加入任何圈子哦！</h3></div>");}
		if(addCount > 18 ){$("#morecircle_join").attr("href","javascript:getMoreJoin()").show();}
		for(var i = 0;i<add_circles.length;i++){
//			alert(i);
//			alert(add_circles[i].circleInfo.circlename);
			if(add_circles[i].circleInfo != null){
				var str = "" +
				"<dl>" +
					"<dt><a target='_blank' href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='80px' height='80px'/></a></dt>" +
					"<dd style=\"text-align:center;\"><a target='_blank' style=\"font-size:12px; color:#626262;\" href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\">"+add_circles[i].circleInfo.circlename+"</a></dd>" +
				"</dl>";
				$("#jiaruli").append(str);
			}
		}
		//TA创建的圈子
//		var createCirlce = data.data.createCircle;
//		if(data.data.circleCount<=0){$("#chuangjianli").html("<div class='u_cont'><h3>TA还没有创建圈子哦！</h3></div>");}
//		if(data.data.circleCount<=6){$("#u_right_chuangjian").html("<img src=\"images/u_right.gif\" />");}
//		for(var j = 0; j<createCirlce.length;j++){
//			var str ="<li><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='80px' height='80px' /></a><span>"+createCirlce[j].circlename+"</span></li>";
//			$("#chuangjianli").append(str);
//		}
		
		//ta的发言
		var hishuati = data.data.circleDetailInfos;
//		if(data.data.count<=0){
//			$("#hesaid").html("<li><div class='u_cont'><h3>TA还没有发言哦！</h3></div></li>");
//		}
		if(data.data.count>12){
			pageNumber+=1;
			$("#u_more_topic").html("<a href=\"javascript:hemoresaid("+pageNumber+","+userid+");\">显示更多</a>");
			$("#u_more_topic").show();
		}
		for(var k = 0;k<hishuati.length;k++){
			var def1 = hishuati[k].def1;//话题
			var def2 = hishuati[k].def2;//PK话题
			var cid = hishuati[k].circleid;
			var img;
			if(hishuati[k].circleDetailImg[0] ==null){
				img = "images/003.jpg";
			}else{
				img = hishuati[k].circleDetailImg[0].middleimg;
				if(img == ""){img = "images/003.jpg";}
			}
			var url ;
			if(def1==1){
				if(def2==1){
					url = basePath +"topic_pk/"+hishuati[k].circledetailid+".html";
				}else{
					url = basePath +"topic_tu/"+hishuati[k].circledetailid+".html";
				}
				
			}else{
				url = basePath + "det/" +cid+ "/" +hishuati[k].circledetailid+ ".html";
			}
			var content = hishuati[k].circlecontent;
			if(content.length > 120){
				content = content.substring(0,120);
				content = content+"";
			}
			var fayan = "" +
				"<div class=\"personal_middle2_wenzi\">" +
					"<dl>" +
						"<dt><a href=\""+url+"\"><img src=\""+img+"\" /></a></dt>" +
						"<dd>" +
							"<div class=\"personal_middle2_wenzi_top\">" +
								"<p><a href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
								"<p><span>"+hishuati[k].createdatetime+" • "+hishuati[k].comcount+"条评论</span></p>" +
							"</div>" +
							"<div class=\"clear\"></div>" +
							"<div class=\"personal_middle2_wenzi_middle\">" +
								"<p>"+content+"</p>" +
							"</div>" +
						"</dd>" +
					"</dl>" +
				"</div>";
			$("#hesaid").append(fayan);;
		}
	},error:function(data)
	{
	}
});
}

function hemoresaid(pageNumber,userid){
	var centerAction_moretopic="centerAction_hemoresaid.do";
	$.ajax({
		url:centerAction_moretopic,
		data:{pageNum : pageNumber,id : userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var hishuati = data.data.circleDetailInfos;
			if(data.data.count<(pageNumber*12)){
				$("#u_more_topic").hide();
			}
			if(data.data.count>(pageNumber*12)){
				pageNumber+=1;
				$("#u_more_topic").html("<a href=\"javascript:hemoresaid("+pageNumber+","+userid+");\">显示更多</a>");
				$("#u_more_topic").show();
			}
			for(var k = 0;k<hishuati.length;k++){
				var def1 = hishuati[k].def1;//话题
				var def2 = hishuati[k].def2;//PK话题
				var cid = hishuati[k].circleid;
				var img;
				if(hishuati[k].circleDetailImg[0] ==null){
					img = "images/003.jpg";
				}else{
					img = hishuati[k].circleDetailImg[0].middleimg;
					if(img == ""){img = "images/003.jpg";}
				}
				var url ;
				if(def1==1){
					if(def2==1){
						url = basePath +"topic_pk/"+hishuati[k].circledetailid+".html";
					}else{
						url = basePath +"topic_tu/"+hishuati[k].circledetailid+".html";
					}
					
				}else{
					url = basePath + "det/" +cid+ "/" +hishuati[k].circledetailid+ ".html";
				}
				var content = hishuati[k].circlecontent;
				if(content.length > 120){
					content = content.substring(0,120);
					content = content+"";
				}
				var fayan = "" +
					"<div class=\"personal_middle2_wenzi\">" +
						"<dl>" +
							"<dt><a href=\""+url+"\"><img src=\""+img+"\" /></a></dt>" +
							"<dd>" +
								"<div class=\"personal_middle2_wenzi_top\">" +
									"<p><a href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
									"<p><span>"+hishuati[k].createdatetime+" • "+hishuati[k].comcount+"条评论</span></p>" +
								"</div>" +
								"<div class=\"clear\"></div>" +
								"<div class=\"personal_middle2_wenzi_middle\">" +
									"<p>"+content+"</p>" +
								"</div>" +
							"</dd>" +
						"</dl>" +
					"</div>";
				$("#hesaid").append(fayan);;
			}
			
		},error:function(data){
			
		}
	});
}


function userCreateCircle(num,pageSize,ttp)
{
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	var uid = realurl.substring(idindexof + 1,realurl.search(".html"));
	var centerAction_topic="centerAction_TApageUserCreateCircle.do";
	$.ajax({
		url:centerAction_topic,
		data:{pageNum : num,pageSize : pageSize,id : uid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			//TA创建的圈子
			var createCirlce = data.data.user_create_circle;
			var count = createCirlce.length;
			if(count >= 36)
			{
				var n = num + 1;
				$("#pagediv_chuangjian").html("<a href=\"javascript:userCreateCircle("+n+","+pageSize+",2)\">查看更多</a>");
				$("#pagediv_chuangjian").show();
			}else{
				$("#pagediv_chuangjian").html("");
				$("#pagediv_chuangjian").hide();
			}
			for(var j = 0; j<createCirlce.length;j++){
				var str = "" +
				"<dl>" +
					"<dt><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='80px' height='80px'/></a></dt>" +
					"<dd style=\"text-align:center;\"><a target='_blank' style=\"font-size:12px; color:#626262;\" href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\">"+createCirlce[j].circlename+"</a></dd>" +
				"</dl>";
				$("#chuangjianli").append(str);
			}
			
			
		},error:function(data)
		{
		}
	});
}
function userJoinCircle(num,pageSize,ttp)
{
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	var uid = realurl.substring(idindexof + 1,realurl.search(".html"));
	var centerAction_topic="centerAction_TApageUserJoinCircle.do";
	$.ajax({
		url:centerAction_topic,
		data:{pageNum : num,pageSize : pageSize,id : uid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var add_circles = data.data.add_circles;
			var count = add_circles.length;
			if(count >= 36)
			{
				var n = num+1;
				$("#pagediv_join").html("<a href=\"javascript:userJoinCircle("+n+","+pageSize+",2)\">查看更多</a>");
				$("#pagediv_join").show();
			}else{
				$("#pagediv_join").html("");
				$("#pagediv_join").hide();
			}
			for(var i = 0;i<add_circles.length;i++){
				var cinfo = add_circles[i].circleInfo;
				if(cinfo!=null)
				{
					var cid = add_circles[i].circleInfo.circleid;
					var str = "" +
					"<dl>" +
						"<dt><a target='_blank' href=\""+basePath+"cir2/1/"+cid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='80px' height='80px'/></a></dt>" +
						"<dd style=\"text-align:center;\"><a target='_blank' style=\"font-size:12px; color:#626262;\" href=\""+basePath+"cir2/1/"+cid+".html\">"+add_circles[i].circleInfo.circlename+"</a></dd>" +
					"</dl>";
					$("#jiaruli").append(str);
				}else if(cinfo=="null")
				{
					
				}
			}
			
		},error:function(data)
		{
		}
	});
}

function getMoreJoin(){
//	alert("ssss");
	$("#morecircle_join").hide();
	$("#jiaruli").html("");
	$("#hesaid").hide();
	$("#u_more_topic").hide();
	userJoinCircle(1,36,1);
}
function getMoreCreate(){
	$("#join").hide();
	$("#hesaid").hide();
	$("#chuangjian").show();
	$("#chuangjianli").html("");
	userCreateCircle(1,36,1)
}



