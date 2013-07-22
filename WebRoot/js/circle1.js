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
			if(pageNumber == 1 && ctid != 0 ){
				$("#circle_center_detail").html("");
			}
			details = data.data.circleDetailInfos;
			pageNumber = pageNumber+1;
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
				if(content.length>300)
				{
					content = content.substring(0,300);
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
				var islongin = $("#islongin").val();
				var str ;
				if(islongin == "" || islongin == null){
					str = 
						"<div class=\"quanzi_right3\">" +
							"<dl>" +
								"<dt>" +
									"<a target=\"_blank\" href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\"><img src=\""+img+"\"/></a>" +
								"</dt>" +
								"<dd>" +
									"<div class=\"quanzi_right3_top\">" +
										"<p><a target=\"_blank\" href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">"+title+"</a></p>" +
										"<p><span>"+createdate+"</span></p>" +
									"</div>" +
									"<div class=\"clear\"></div>" +
									"<div class=\"quanzi_right3_middle\">" +
										"<p>"+content+"</p>" +
									"</div>" +
									"<div class=\"quanzi_right3_middle\">"+
										"<span><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">继续阅读>></a></span>"+
									"</div>" +
								"</dd>" +
							"</dl>" +
						"</div>";
					$("#circle_center_detail").append(str);
				}else{
					var userid = $("#userid").val();
					var quanzhuid = $("#quanzhuid").val();
					if(userid == quanzhuid){
						str = 
						"<div class=\"quanzi_right3\">" +
							"<dl>" +
								"<dt>" +
									"<a target=\"_blank\" href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\"><img src=\""+img+"\"/></a>" +
								"</dt>" +
								"<dd>" +
									"<div class=\"quanzi_right3_top\">" +
										"<p><a target=\"_blank\" href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">"+title+"</a>" +
												"<select id=\"changetag"+details[i].circledetailid+"\" onchange=\"changetag('"+details[i].circledetailid+"',$(this).val())\">"+
												"</select>" +
												"</p>" +
										"<p><span>"+createdate+"</span></p>" +
									"</div>" +
									"<div class=\"clear\"></div>" +
									"<div class=\"quanzi_right3_middle\">" +
										"<p>"+content+"</p>" +
									"</div>" +
									"<div class=\"quanzi_right3_middle\">"+
										"<span><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">继续阅读>></a></span>"+
									"</div>" +
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
						"<div class=\"quanzi_right3\">" +
							"<dl>" +
								"<dt>" +
									"<a target=\"_blank\" href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\"><img src=\""+img+"\"/></a>" +
								"</dt>" +
								"<dd>" +
									"<div class=\"quanzi_right3_top\">" +
										"<p><a target=\"_blank\" href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">"+title+"</a></p>" +
										"<p><span>"+createdate+"</span></p>" +
									"</div>" +
									"<div class=\"clear\"></div>" +
									"<div class=\"quanzi_right3_middle\">" +
										"<p>"+content+"</p>" +
									"</div>" +
									"<div class=\"quanzi_right3_middle\">"+
										"<span><a href=\"det/"+details[i].circleid+"/"+details[i].circledetailid+".html\">继续阅读>></a></span>"+
									"</div>" +
								"</dd>" +
							"</dl>" +
						"</div>";
					$("#circle_center_detail").append(str);
					}
				}
			}
			$("#next_page").html("<a href=\"javascript:void(0);\" onclick=\"getmoredetail("+pageNumber+",'"+cid+"','"+ctid+"')\">查看更多</a>");
		},
		error:function(data){
		}
	});
}
function showImg(i,detaId,num)
{
	var d = details[i];
	var dimg = d.circleDetailImg;
	for(var j=0;j<dimg.length;j++){
		if(j>=1)
		{
			break;
		}
		if(dimg[0].bigimg != null && dimg[0].bigimg != ""){
			var result="<li><a href=\"javascript:void(0);\" onclick=\"showZom('"+dimg[0].bigimg+"',"+num+","+i+","+j+")\"><img src=\"" + dimg[0].middleimg + "\" id=\"showImg"+num+""+j+"\" style=\"margin-top:5px; width:120px;\" /><div id=\"box"+num+""+i+"\"></div></a></li>";
			$("#details_img"+num+""+i+"").append(result);
		}else{
			$("#details_img"+num+""+i+"").css("display","none");
		}
	}
}
//点击放大图片并显示遮罩层，单击消失
function showZom(imageurl,num,i,j){
	
	var img=document.createElement('img');//创建一个img元素
	img.src=""+imageurl+"";//指定src
	img.style.position="absolute";//防止正常的内容变形
	img.style.visibility='hidden';//藏起来
	var inj=document.getElementById("box"+num+""+i+"").appendChild(img);//插入到box中。当然插入到document.body也可以
	//然后就可以通过 offset 取得宽和高了
	
	if(inj.offsetWidth>590){
		$("#dialog"+num+""+i+"").css({"display":"block","z-index":"110","overflow":"hidden", "background-color":"#ffffff", "padding-top": "10px"}).append("<a href=\"javascript:void(0);\" id=\"div_pic"+num+""+i+"\"><img src=\"" + imageurl + "\" width=\"590px\" /></a>").fadeIn(500).show();
		$("#details_img"+num+""+i).css("display","none");
	}else{
		
		$("#dialog"+num+""+i+"").css({"display":"block","z-index":"110","overflow":"hidden", "background-color":"#ffffff", "padding-top": "10px"}).html("<a href=\"javascript:void(0);\" id=\"div_pic"+num+""+i+"\"><img src=\"" + imageurl + "\" /></a>").fadeIn(500).show();
		$("#details_img"+num+""+i).css("display","none");
	}
	
	$("#div_pic"+num+""+i+"").click(function(){
		$("#dialog"+num+""+i+"").css("display","none");
		$("#dialog"+num+""+i+"").html("");
		$("#details_img"+num+""+i).css("display","block");
		});
}

function showtalk(since_id){
	var div = document.getElementById('talklist');
	div.scrollTop = div.scrollHeight-10;
	var circleid = $("#circleid").val();
	var showtalkURL = "circleDetails_showtalk.do";
	var islogin = $("#islongin").val();
	//圈子的信息
	$.ajax({
		url:showtalkURL,
		data:{id : circleid,uid :since_id},
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
				if(islogin == userid){
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
	var islogin = $("#islongin").val();
	if(islogin == ""){
		alert("您还没有登录，请先登录吧！");
		$("#window_dl").show();
		return false;
	}
	var talkcontent = $("#talkcontent").val();
	
	if(talkcontent.trim().length<=0 || talkcontent == ""){
		alert("请输入内容");
		return false;
	}
	if(talkcontent.length > 500){
		alert("您输入的内容过长");
		return false;
	}
	
	var savetalkURL = "circleDetails_savetalk.do";
	//圈子的信息
	$.ajax({
		url:savetalkURL,
		data:{id : circleid,content:talkcontent},
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
			
		},error:function(data){
			
		}
	});	
}

function nowuser(uuid,ccid)
{
	if(uuid==ccid)
		{
			$("#quit").css("display","none");
			$("#join").css("display","none");
			$("#a_join").css("display","none");
		}
}
 function showjiaru_tuichu()
 {
 	document.getElementById('jr_tuichu').style.display='block';
 	setTimeout("document.getElementById('jr_tuichu').style.display = 'none';", 2222);
 }
 function showdd(i)
 {
 	document.getElementById("commentsuccess"+i+"").style.display='block';
 	setTimeout("document.getElementById(\"commentsuccess"+i+"\").style.display = 'none';", 2222);
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
