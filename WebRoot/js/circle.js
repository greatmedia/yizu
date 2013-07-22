var details;
function getmoredetail(pageNumber,cid,ctid)
{
//	alert(pageNumber+"  "+cid+"  "+ctid);
	var circleURL = "circleDetails_circleDetail.do";
	//圈子的信息
	$.ajax({
		url:circleURL,
		data:{id : cid,pageNum : pageNumber,pageSize : 10,pid:ctid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
//			if(pageNumber == 1 && ctid != 0 ){
//				$("#circle_center_detail").html("");
//			}
			$("#next_page").html("");
			details = data.data.circleDetailInfos;
			pageNumber = pageNumber+1;
			var center_circleInfo = data.data.center_circleInfo;
			var quanzhuid = center_circleInfo.userid;
			var tagrelate = data.data.tagrelate;
			for(var i = 0; i < details.length; i++)
			{
				var content = details[i].circlecontent;
				var createdate = details[i].createdatetime;
				if(createdate.length>11)
				{
					createdate = createdate.substring(0,11);
				}
				
				if(content.indexOf("embed ")<0){
				if(content.length>200)
				{
					content = content.substring(0,200);
					content = content+"";
				}
				}
				var title = details[i].title;
				var image = details[i].circleDetailImg;
				var img ;
				for(var m = 0; m < image.length; m++){
					if(m>=1){break;}
					if(image[0].bigimg != null && image[0].bigimg != ""){
						img = image[0].bigimg;
					}else{
						img = "../images/003.jpg";
					}
				}
				
				var islongin = $("#islogin").val();
				var str ;
				if(islongin == "not"){
					str = "" +
						"<div class=\"personal_middle2_wenzi\">" +
							"<dl>" +
								"<dt><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\"><img src=\""+img+"\" /></a></dt>" +
								"<dd>" +
									"<div class=\"personal_middle2_wenzi_top\">" +
										"<p><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">"+title+"</a></p>" +
										"<p><span>"+createdate+" • "+details[i].comcount+"条评论</span></p>" +
									"</div>" +
									"<div class=\"clear\"></div>" +
									"<div class=\"personal_middle2_wenzi_middle\">" +
										"<p>"+content+"</p>" +
									"</div>" +
									"<div class=\"personal_middle2_wenzi_middle\"><span><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">查看更多>></a></span></div>" +
								"</dd>" +
							"</dl>" +
						"</div>";
					$("#circle_center_detail").append(str);
				}else{
					var userid = $("#userid").val();
					if(userid == quanzhuid){
						str = ""+
							"<div class=\"personal_middle2_wenzi\">" +
								"<dl>" +
									"<dt><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\"><img src=\""+img+"\" /></a></dt>" +
									"<dd>" +
										"<div class=\"personal_middle2_wenzi_top\">" +
											"<p><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">"+title+"</a>" +
											"<select id=\"changetag"+details[i].circledetailid+"\" onchange=\"changetag('"+details[i].circledetailid+"',$(this).val())\">"+
											"</select></p>" +
											"<p><span>"+createdate+" • "+details[i].comcount+"条评论</span></p>" +
										"</div>" +
										"<div class=\"clear\"></div>" +
										"<div class=\"personal_middle2_wenzi_middle\">" +
											"<p>"+content+"</p>" +
										"</div>" +
										"<div class=\"personal_middle2_wenzi_middle\"><span><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">查看更多>></a></span></div>" +
									"</dd>" +
								"</dl>" +
							"</div>";
						$("#circle_center_detail").append(str);
						var sel ;
						for(var t = 0; t < tagrelate.length; t++){
							var ctid1 = tagrelate[t].ctid;
//							alert(ctid);
							if(ctid1 == details[i].def4){
								sel = "<option value=\""+ctid1+"\" selected=\"selected\">" +
									""+tagrelate[t].cirtag.tagname+"" +
									"</option>";
							}else{
								sel = "<option value=\""+ctid1+"\">" +
								""+tagrelate[t].cirtag.tagname+"" +
								"</option>";
							}
							$("#changetag"+details[i].circledetailid+"").append(sel);
						}
					}else{
					str = 
						"<div class=\"personal_middle2_wenzi\">" +
							"<dl>" +
								"<dt><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\"><img src=\""+img+"\" /></a></dt>" +
								"<dd>" +
									"<div class=\"personal_middle2_wenzi_top\">" +
										"<p><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">"+title+"</a></p>" +
										"<p><span>"+createdate+" • "+details[i].comcount+"条评论</span></p>" +
									"</div>" +
									"<div class=\"clear\"></div>" +
									"<div class=\"personal_middle2_wenzi_middle\">" +
										"<p>"+content+"</p>" +
									"</div>" +
									"<div class=\"personal_middle2_wenzi_middle\"><span><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">查看更多>></a></span></div>" +
								"</dd>" +
							"</dl>" +
						"</div>";
					$("#circle_center_detail").append(str);
					}
				}
			}
//			alert(details.length);
			if(details.length >= 10){
				$("#next_page").html("<a href=\"javascript:void(0);\" onclick=\"getmoredetail("+pageNumber+",'"+cid+"','"+ctid+"')\">查看更多</a>");
			}
		},
		error:function(data){
		}
	});
}

function showtalk(since_id){
//	alert(since_id);
	var div = document.getElementById('talklist');
	div.scrollTop = div.scrollHeight-10;
	var circleid = $("#circleid").val();
	var showtalkURL = "circleDetails_showtalk.do";
	var loginuser = $("#userid").val();
	var talkabout_id = $("#talkabout_id").val();
//	alert(talkabout_id.length);
	if(talkabout_id.length < 1){
		talkabout_id = '0';
	}
//	alert(talkabout_id);
	//圈子的信息
	$.ajax({
		url:showtalkURL,
		data:{id : circleid,uid :since_id,pid:talkabout_id},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var usertalk = data.data.usertalk;
			if(since_id == 1 && usertalk.length <= 0 ){$("#talklist").html("<p class=\"liaotianba\">快来聊天吧!</p>");}
			if(usertalk.length　> 0){
			for(var u = usertalk.length-1; u >=0 ; u--){
				if(u == 0){
					since_id = usertalk[0].id ;
				}
				if($("#temp").val() != null ){$("#temp_next").parent().remove();}
				var content = usertalk[u].content;
				var userid = usertalk[u].userid;
				var talktime = usertalk[u].talktime;
				talktime = talktime.substring(11);
				var str;
				if(loginuser == userid){
					str= 
						"<div class=\"quanzi_right2_right\">" +
							"<dl>" +
								"<dt>" +
									"<a href=\"user/"+userid+".html\" target='_blank'><img src=\""+usertalk[u].userinfo.image+"\" title=\""+usertalk[u].userinfo.nick+"\"/></a>" +
								"</dt>" +
								"<dd>" +
									"<div class=\"dd_right\">" +
										"<img src=\"images/dd_right.png\" />" +
									"</div>" +
									"<div class=\"dd_wenzi_right\">"+usertalk[u].content+"</div>" +
									"<span>"+talktime+"</span>" +
								"</dd>" +
							"</dl>" +
						"</div>" ;
				}else{
					str= 
						"<div class=\"quanzi_right2_left\">" +
							"<dl>" +
								"<dt>" +
									"<a href=\"user/"+userid+".html\" target='_blank'><img src=\""+usertalk[u].userinfo.image+"\" title=\""+usertalk[u].userinfo.nick+"\"/></a>" +
								"</dt>" +
								"<dd>" +
									"<div class=\"dd_left\">" +
										"<img src=\"images/dd_left.png\" />" +
									"</div>" +
									"<div class=\"dd_wenzi\">"+usertalk[u].content+"</div>" +
									"<span>"+talktime+"</span>" +
								"</dd>" +
							"</dl>" +
						"</div>" ;
				}
				$("#talklist").append(str);
			}
			}
			setTimeout("showtalk("+since_id+")",10000);
		},
		error:function(data){
			
		}
	});
	
}
function sendMessage(){
	var circleid = $("#circleid").val();
	var islogin = $("#islogin").val();
	if(islogin == "not"){
		alert("您还没有登录，请先登录吧！");
		$("#window_dl").show();
		return;
	}
	var talkabout_id = $("#talkabout_id").val();
	if(talkabout_id.length < 1){
		alert("圈主还没有添加热议话题");
		return;
	}
	var talkcontent = $("#talkcontent").val();
	
	if(talkcontent.trim().length<=0 || talkcontent == ""){
		alert("请输入内容");
		return;
	}
	if(talkcontent.length > 500){
		alert("您输入的内容过长");
		return;
	}
	
	var savetalkURL = "circleDetails_savetalk.do";
	//圈子的信息
	$.ajax({
		url:savetalkURL,
		data:{id : circleid,content:talkcontent,pid : talkabout_id},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			if(data.flag){
				var user = data.data.user;
				$("#talkcontent").val("");
				
				var str= 
					"<div class=\"quanzi_right2_right\" id=\"temp\">" +
						"<dl id='temp_next'>" +
							"<dt>" +
								"<a href=\"user/"+user.userid+".html\" target='_blank'><img src=\""+user.image+"\" title=\""+user.nick+"\"/></a>" +
							"</dt>" +
							"<dd>" +
								"<div class=\"dd_right\">" +
									"<img src=\"images/dd_right.png\" />" +
								"</div>" +
								"<div class=\"dd_wenzi_right\">"+talkcontent+"</div>" +
								"<span>...</span>" +
							"</dd>" +
						"</dl>" +
					"</div>" ;
				$("#talklist").append(str);
				var div = document.getElementById('talklist');
				div.scrollTop = div.scrollHeight-10;
			}
		},
		error:function(data){
		}
	});
}

function changetalkabout(circleid){
	var talktitle = $("#circletalkabout").val();
	var changetalkaboutURL = "circleDetails_changetalkabout.do"
	$.ajax({
		url:changetalkaboutURL,
		data:{id : circleid,content:talktitle},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var talkabout = data.data.talkabout;
			$("#talkabout_id").html(talkabout.id);
			$("#talklist").html("");
			alert("更改成功");
		},error:function(data){
			
		}
	});	
}

 
 function changetag(detailid,ctid){
	 var changetagURL = "circleDetails_changecircletag.do";
	 $.ajax({
			url:changetagURL,
			data:{id : detailid,pid:ctid},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
				alert("chenggong");
			},error:function(data){
				
			}
		});	
	 
 }
 
 
 function join_or_quit_quanzi(did,ttyp){
		if(did.indexOf('.html')<=0)
		{
			
		}else{
			var realurl = did,ttyp;
			var idindexof = realurl.lastIndexOf(".html");
			did = realurl.substring(0,idindexof);
		}
		var islogin = $("#islogin").val();
//		alert(islogin);
		if(islogin=='not')
		{
			alert("您还没有登录，登录之后才能加入圈子..");
			$("#window_dl").css("display","block");
			return;
		}
		var joinquanziURL = "jsonInfoAction_joinOrQuitQuanzi.do";
		$.ajax({                 
			url: joinquanziURL,  
			data : {id : did ,type : ttyp},
			type: "POST",     
			dataType : "json",
			async:false,
			success: function(data) {
				var flag = data.flag;
				if(ttyp == 1){
					//加入圈子
					if(flag){
						alert("加入圈子成功！");
						location.href = location.href;
					}else{
						alert("你已经加入该圈子了！");
					}
				}else if(ttyp == 2){
					//退出圈子
					if(flag){
						alert("退出圈子成功！");
						location.href = location.href;
					}else{
						alert("退出圈子失败！");
						location.href = location.href;
					}
				}
			},
			error:function(data){
				$("#wait").css("display","none");
			}
		});
	}
 
 function addFavorite(cid){
	var islogin = $("#islogin").val();
 	if(islogin == 'not')
 	{
 		alert("您还没有登录，登录之后才能收藏圈子..");
 		$("#window_dl").css("display","block");
 		return;
 	}
 	var addFavoriteURL = "jsonInfoAction_addFavorite.do";
 	$.ajax({                 
 		url: addFavoriteURL,  
 		data : {id : cid},
 		type: "POST",     
 		dataType : "json",
 		success: function(data) {
 			var flag = data.flag;
 			if(flag){
 				alert("收藏成功！");
 			}else{
 				alert("已经收藏过了");
 			}
 		},
 		error:function(data){
 			
 		}
 	});
 }
 
 function showmoretalkabout(pageSize,pageNumber,ttp){
//	 alert(pageSize+"  "+pageNumber+"  "+ttp);
	 var circleid = $("#circleid").val();
	 var showmoretalkaboutURL = "circleDetails_showmoretalkabout.do";
	 	$.ajax({                 
	 		url: showmoretalkaboutURL,  
	 		data : {id : circleid,pageSize:pageSize,pageNum:pageNumber},
	 		type: "POST",     
	 		dataType : "json",
	 		success: function(data) {
	 			var talkcount = data.data.talkcount;
	 			var about = data.data.moretalkabout;
//	 			alert(moretalkabout.length);
	 			if(ttp == 1){
	 				if(talkcount > 5 ){
	 					$("#moretalkabout").html("<a href=\"javascript:showmoretalkabout(5,2,2)\">加载更多</a>");
	 					$("#moretalkabout").show();
	 				}
	 			}else{
	 				if(about.length >=5){
	 					var n = pageNumber+1;
	 					$("#moretalkabout").html("");
	 					$("#moretalkabout").html("<a href=\"javascript:showmoretalkabout(5,"+n+",2)\">加载更多</a>");
	 				}else{
	 					$("#moretalkabout").html("");
	 					$("#moretalkabout").hide();
	 				}
	 			}
	 			var quanzhuid = $("#quanzhuid").val();
	 			for(var m = 0; m < about.length ; m++){
	 				var talk = about[m].talk;
	 				var str;
	 				if(talk != null){
	 					str = "" +
	 					"<ul>" +
	 						"<li style=\"float:left;\"><h2><a href=\"talkdetail/"+about[m].circleid+"/"+about[m].id+".html\">"+about[m].talktitle+"</a>(<span>"+about[m].visitCount+"</span>人参与了讨论)</h2></li>" +
	 						"<li style=\"float:right;\"><span><a href=\"user/"+quanzhuid+".html\">圈主</a>发表于："+about[m].updatetime+"</span></li>" +
	 						"<div class=\"clear\"></div>" +
	 						"<li style=\"float:left; color:#fd5e02; padding-top:5px; margin-right:10px;\"><a href=\"user/"+about[m].talk.userinfo.userid+".html\">"+about[m].talk.userinfo.nick+"</a></li>" +
	 						"<li style=\"float:left; width:auto; height:auto; padding:5px; background-color:#e8e8e8; line-height:24px;\">"+about[m].talk.content+"</li>" +
	 					"</ul>";
	 				}else{
	 					str = "" +
	 					"<ul>" +
	 						"<li style=\"float:left;\"><h2><a href=\"talkdetail/"+about[m].circleid+"/"+about[m].id+".html\">"+about[m].talktitle+"</a>(<span>"+about[m].visitCount+"</span>人参与了讨论)</h2></li>" +
	 						"<li style=\"float:right;\"><span><a href=\"user/"+quanzhuid+".html\">圈主</a>发表于："+about[m].updatetime+"</span></li>" +
	 						"<div class=\"clear\"></div>" +
	 						"<li style=\"float:left; width:auto; height:auto; padding:5px; background-color:#e8e8e8; line-height:24px;\">没有发言</li>" +
	 					"</ul>";
	 				}
	 				
		          	$("#moretalk").append(str);
	 			}
	 			
	 		},
	 		error:function(data){
	 			
	 		}
	 	});
 }
 
 function showtalkdetail(pageSize,pageNumber,ttp){
	 var circleid = $("#circleid").val();
	 var talkdetailid = $("#tdid").val();
	 var showtalkdetailURL = "circleDetails_showtalkdetail.do";
	 	$.ajax({                 
	 		url: showtalkdetailURL,  
	 		data : {id : circleid,pid:talkdetailid,pageSize:pageSize,pageNum:pageNumber},
	 		type: "POST",     
	 		dataType : "json",
	 		success: function(data) {
	 			var talkcount = data.data.talkcount;
	 			var talklist = data.data.talklist;
	 			if(ttp == 1){
	 				if(talkcount < 1){
	 					$("#talkdetail").html("<ul><li style=\"float:left; width:auto; height:auto; padding:5px; background-color:#e8e8e8;\">没有发言</li></ul>");
	 				}
	 				if(talkcount > 20){
	 					$("#moretalkdetail").html("<a href=\"javascript:showtalkdetail(20,2,2)\">加载更多</a>");
	 					$("#moretalkdetail").show();
	 				}
	 			}else{
	 				if(talklist >= 20 ){
	 					var n = pageNumber+1;
	 					$("#moretalkdetail").html("");
	 					$("#moretalkdetail").html("<a href=\"javascript:showtalkdetail(20,"+n+",2)\">加载更多</a>");
	 				}else{
	 					$("#moretalkdetail").html("");
	 					$("#moretalkdetail").hide();
	 				}
	 			}
//	 			alert(talkcount+"  "+talklist.length);
	 			for(var t = 0 ; t < talklist.length ; t++){
	 				var str = "" +
	 					"<ul>" +
	 						"<li style=\"float:left; color:#fd5e02; padding-top:5px; margin-right:10px;\"><a href=\"user/"+talklist[t].userinfo.userid+".html\">"+talklist[t].userinfo.nick+"</a></li>" +
	 						"<li style=\"float:left; width:auto; height:auto; padding:5px; background-color:#e8e8e8;\">"+talklist[t].content+"</li>" +
	 						"<div class=\"clear\" ></div>" +
	 						"<li style=\"float:right; color:#ccc;\">"+talklist[t].talktime+"</li>" +
	 					"</ul>";
	 				$("#talkdetail").append(str);
	 			}
	 		},
	 		error:function(data){
	 			
	 		}
	 	});
 }
