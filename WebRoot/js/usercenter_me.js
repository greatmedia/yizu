var user;
$(function(){
	showData(18,1,1);
});
var cir = [];

//function changeLi(num){
//	if(num==1){$("#jiaru").removeClass("active").addClass("li"); $("#chuangjian").removeClass("li").addClass("active"); $("#ue_jiaru").css("display","block"); $("#ue_chuangjian").css("display","none");$("#pilianginsert").css("display","block");}
//	if(num==2){$("#chuangjian").removeClass("active").addClass("li"); $("#jiaru").removeClass("li").addClass("active"); $("#ue_jiaru").css("display","none"); $("#ue_chuangjian").css("display","block");$("#pilianginsert").css("display","none");}
//}

function showData(rows,pageNumber,ttp)
{
	var centerAction_topic="centerAction_getMEusercenter.do";
	$.ajax({
	url:centerAction_topic,
	data:{pageNum : pageNumber,pageSize : rows},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		var isLogin = data.data.isLogin;
		if(isLogin==0){
			alert("还没有登录，请登录后再查看！");
			location.href = basePath;
		}
		user = data.data.user;
		if(user.website != null && user.website != ""){
			$("#user_bj").attr({"src": user.website,"width":"720","height":"295"});
		}else{
			$("#user_bj").attr("src", "images/gerenzhongxin_bj.jpg");
		}
		var img = user.image;
		if(img == "" || img == null){
			img = "../images/nobody.gif";
		}
		$("#userimg").attr({"src":img,"width":"80"});//头像
		$("#nick").html(user.nick);//名字 
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
		//加入的圈子
		var add_circles = data.data.add_circles;
		var addCount = data.data.addcircleCount;
		if(addCount<=0){location.href = basePath+"usercenter/1"; return false;}
		if(addCount > 18){$("#morecircle_join").attr("href","javascript:getMoreJoin()"); $("#morecircle_join").show();}
		for(var i = 0;i<add_circles.length;i++){
			if(add_circles[i].circleInfo != null){
				var str = "" +
					"<dl>" +
						"<dt><a target='_blank' href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\"><img src=\""+add_circles[i].circleInfo.circlebigimg+"\" width='80px' height='80px'/></a></dt>" +
						"<dd style=\"text-align:center;\"><a target='_blank' style=\"font-size:12px; color:#626262;\" href=\""+basePath+"cir2/1/"+add_circles[i].circleInfo.circleid+".html\">"+add_circles[i].circleInfo.circlename+"</a></dd>" +
					"</dl>";
				$("#jiaruli").append(str);
			}
		}
		//创建的圈子
//		var createCirlce = data.data.createCircle;
//		if(data.data.circleCount<=0){$("#chuangjianli").html("<div class='u_cont'><h3>还没有创建圈子哦！</h3></div>");}
//		if(data.data.circleCount<=6){$("#u_right_chuangjian").html("<img src=\"images/u_right.gif\" />");}
//		for(var j = 0; j<createCirlce.length;j++){
//			var str ="<li><a target='_blank' href=\""+basePath+"cir2/1/"+createCirlce[j].circleid+".html\"><img src=\""+createCirlce[j].circlebigimg+"\" width='80px' height='80px' /></a><span>"+createCirlce[j].circlename+"</span></li>";
//			$("#chuangjianli").append(str);
//		}
		//我的发言
		var hishuati = data.data.circleDetailInfos;
		if(data.data.count>12){
			pageNumber+=1;
			$("#u_more_topic").html("<a href=\"javascript:memoresaid("+pageNumber+","+user.userid+");\">显示更多</a>");
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
				"<div class=\"personal_middle2_wenzi\" onmouseover=\"$('#div"+k+"').show()\" onmouseout=\"$('#div"+k+"').hide()\">" +
					"<dl>" +
						"<dt><a href=\""+url+"\"><img src=\""+img+"\" /></a></dt>" +
						"<dd>" +
							"<div class=\"personal_middle2_wenzi_top\">" +
								"<p><a href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
								"<p><span>"+hishuati[k].createdatetime+" • "+hishuati[k].comcount+"条评论</span></p>" +
								"<p id=\"div"+k+"\" style='float: left; margin-left: 30px; position: relative; display: none;'>" +
									"<a href=\"circleInfoAction_updateCircleDetail.do?id="+hishuati[k].circledetailid+"&cid="+cid+"\" style='color: #808080; font-size: 12px;'>编辑</a>&nbsp;" +
									"<a href=\"javascript:void(0);\" onclick=\"deleteDid('"+cid+"','"+hishuati[k].circledetailid+"')\" style='color: #808080; font-size: 12px;'>删除</a>"+
								"</p>" +
							"</div>" +
							"<div class=\"clear\"></div>" +
							"<div class=\"personal_middle2_wenzi_middle\">" +
								"<p>"+content+"</p>" +
							"</div>" +
						"</dd>" +
					"</dl>" +
				"</div>";
			$("#mesaid").append(fayan);;
		}
	},error:function(data)
	{
	}
});
}

function memoresaid(pageNumber,userid){
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
				$("#u_more_topic").html("<a href=\"javascript:memoresaid("+pageNumber+","+userid+");\">显示更多</a>");
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
					"<div class=\"personal_middle2_wenzi\" onmouseover=\"$('#div"+k+"').show()\" onmouseout=\"$('#div"+k+"').hide()\">" +
						"<dl>" +
							"<dt><a href=\""+url+"\"><img src=\""+img+"\" /></a></dt>" +
							"<dd>" +
								"<div class=\"personal_middle2_wenzi_top\">" +
									"<p><a href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
									"<p><span>"+hishuati[k].createdatetime+" • "+hishuati[k].comcount+"条评论</span></p>" +
									"<p id=\"div"+k+"\" style='float: left; margin-left: 30px; position: relative; display: none;'>" +
										"<a href=\"circleInfoAction_updateCircleDetail.do?id="+hishuati[k].circledetailid+"&cid="+cid+"\" style='color: #808080; font-size: 12px;'>编辑</a>&nbsp;" +
										"<a href=\"javascript:void(0);\" onclick=\"deleteDid('"+cid+"','"+hishuati[k].circledetailid+"')\" style='color: #808080; font-size: 12px;'>删除</a>"+
									"</p>" +
								"</div>" +
								"<div class=\"clear\"></div>" +
								"<div class=\"personal_middle2_wenzi_middle\">" +
									"<p>"+content+"</p>" +
								"</div>" +
							"</dd>" +
						"</dl>" +
					"</div>";
				$("#mesaid").append(fayan);;
			}
			
		},error:function(data){
			
		}
	});
}
/**
 * <a onclick="userCreateCircle('1');" href='javascript:void(0);'>
 * */
function userCreateCircle(num,pageSize,ttp)
{
	var centerAction_topic="centerAction_pageUserCreateCircle.do";
	$.ajax({
		url:centerAction_topic,
		data:{pageNum : num,pageSize : pageSize},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			//TA创建的圈子
			var createCirlce = data.data.user_create_circle
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
function getMoreCreate(){
//	alert("sssss");
	$("#joincircle").hide();
	$("#mesaid").hide();
	$("#selectcircle").hide();
	$("#chuangjiancircle").show();
	$("#chuangjianli").html("");
	userCreateCircle(1,36,1)
}
function userJoinCircle(num,pageSize,ttp)
{
	var centerAction_topic="centerAction_pageUserJoinCircle.do";
	$.ajax({
		url:centerAction_topic,
		data:{pageNum : num,pageSize : pageSize},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var add_circles = data.data.add_circles;
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
		},error:function(data)
		{
		}
	});
}

function getMoreJoin(){
//	alert("ssss");
	$("#morecircle_join").hide();
	$("#jiaruli").html("");
	$("#mesaid").hide();
	$("#u_more_topic").hide();
	userJoinCircle(1,36,1);
}



var num = 1;
function moreinsert(){
	$("#joincircle").hide();
	$("#chuangjiancircle").hide();
	$("#mesaid").hide();
	if(num!=1){
		var ul= document.getElementById("showcircle");
		var li = ul.getElementsByTagName("li");
		for(var j = 0;j<li.length;j++){
			if(li[j].className != ""){
				cir.push(li[j].id);
			}
		}
	}
	var selectcircleURL = "centerAction_selectcircleall.do";
	$.ajax({
		url:selectcircleURL,
		data:{pageNum : num,pageSize : 24},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#selectcircle").css("display","block");
			$("#showcircle").html("");
			
			var circleList = data.data.circleList;
			if(circleList.length<24){$("#nextpage").css("display","none");}
			var addcircle = data.data.addcircle;
			for(var i = 0;i<circleList.length;i++){
//				var userid = circleList[i].userid;
				var str = "<li  onmouseover=\"$(this).addClass('active_me');\" onmouseout=\"$(this).removeClass('active_me');\" onclick=\"choose(this);\" id=\""+circleList[i].circleid+"\">"+circleList[i].circlename+"</li>";
				$("#showcircle").append(str);
			}
			for(var c = 0; c<addcircle.length;c++){
				var liid = document.getElementById(""+addcircle[c].circleid+"");
				if(liid){
					liid.className="active_click";
				}
			}
			$("#selectcircle").css("display","block");
			$("#user_lunbo").css("display","none");
			$("#unlist").css("display","none");
		},error:function(data)
		{
		}
	});
	num = num + 1;
}

function nextStep(){
	var ul= document.getElementById("showcircle");
	var li = ul.getElementsByTagName("li");
	for(var j = 0;j<li.length;j++){
		if(li[j].className != ""){
			cir.push(li[j].id);
		}
	}
	if(cir.length<=0){
		location.href = basePath+"usercenter_me/1"
	}else{
		var confimURL = "centerAction_confimcircle.do";
		$.ajax({
			url:confimURL,
			data : {cir : cir.join(",")},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
				var user = data.data.user;
				location.href = basePath+"usercenter_me/"+user.userid+".html";
			},error:function(data)
			{
			}
		});
	}
}
function choose(li){
	var cname = li.className.substring(0,12);
	if(cname =="active_click"){
		li.className = "";
	}else{
		li.className = "active_click";
	}
}
function showorhide(mdiv,i){
	var ul = document.getElementById("coverimg");
	var li = ul.getElementsByTagName("li");
	for(var u = 1; u <= li.length ; u++){
		var d = u + (i-1)*4; 
		document.getElementById("cover"+d).className = 'user_no';
	}
	document.getElementById(mdiv).className = 'user_yes';
}

/**
 * 2.27  个人中心 图片更改。
 * */
function changeViewImg(){
	var ul = document.getElementById("coverimg");
	var li = ul.getElementsByTagName("li");
	var path;
	for(var u = 0; u < li.length ; u++){
		var d = li[u].id;
		if(document.getElementById("cover"+d).className == 'user_yes'){
			var path = document.getElementById("moban"+d).src;
		}
	}
	if(path == "" || path == null){alert("您尚未选择图片");return;}
	$.ajax({
		url: "centerAction_setUserImg.do",
		data : {image: path},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			if(data.flag){
				location.href = location.href;
//				BOX_remove('usercover');
//				$("#user_bj").attr({"src":path,"width":"720","height":"295"});
			}else{
				alert("操作失败请重试");
				$("#usercover").hide();
			}
		},error:function(data)
		{
		}
	});
}
//删除话题
function deleteDid(cid,did)
{
	if(window.confirm('你确定要删除该记录?')){
		var deleteDidURL = "circleDetails_deleteDid.do";
		$.ajax({
			url:deleteDidURL,
			data:{id : did, ids : cid},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
				var islogin = data.data.islogin; 
				var result = data.data.result;
				if(!islogin)
				{
//					alert(result);
					$("#window_dl").css("display","block");
					return false;
				}else{
					location.href = location.href;
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

function changeUserBj(i){
	$("#coverimg").html("");
	for(var j = 1; j < 5; j++){
		var d = j+(i-1)*4;
		var str = "" +
			"<li id=\""+d+"\">" +
				"<a href=\"javascript:showorhide('cover"+d+"',"+i+");\"><img id=\"moban"+d+"\" src=\"images/usercover/bj"+d+".jpg\" /></a>" +
				"<div id=\"cover"+d+"\" class=\"user_no\"><img src=\"images/register/03.png\"/></div>" +
			"</li>";
		$("#coverimg").append(str);
	}
	var ul = document.getElementById("coverbottom");
	var li = ul.getElementsByTagName("li");
	for(var b = 1; b <= li.length ; b++){
		document.getElementById("but"+b).style.backgroundColor = "#CCCCCC";
	}
	document.getElementById("but"+i).style.backgroundColor = "#ffa200";
}
