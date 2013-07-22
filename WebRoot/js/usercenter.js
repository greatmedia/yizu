$(function(){
	showData();
	var userid;
});

function showData()
{
	var centerAction_usercenter="centerAction_getusercenter.do";
	$.ajax({
	url:centerAction_usercenter,
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		var isLogin = data.data.isLogin;
		if(isLogin==0){
			alert("您还没有登录！");
			location.href = basePath;
			return false;
		}
		var user = data.data.user;
		userid = user.userid;
		var img = user.image;
		if(img == "" || img == null){
			img = "../images/nobody.gif";
		}
		$("#userimg").attr({"src":img,"width":"80","height":"80"});//头像
		if(user.website != null && user.website != ""){
			$("#user_bj").attr({"src": user.website,"width":"720","height":"295"});
		}else{
			$("#user_bj").attr("src", "images/gerenzhongxin_bj.jpg");
		}
		if(user.sex !=null && user.sex != "" ){
			if(user.sex == "2"){$("#usersex").html("逸千金");}else{$("#usersex").html("逸骑士");}
		}else{
			$("#usersex").html("逸骑士");
		}
		$("#nick").html(user.nick)//名字
		if(user.address !=null && user.address != "" ){$("#useraddress").html(""+user.address+"")}
		if(user.hobby !=null && user.hobby != "" ){$("#userhobby").html(""+user.hobby+"")}
		$("#aboutme").html(user.what);//关于自己
		$("#huatiCount").html(data.data.count);	//发言数
		$("#circleCount").html(data.data.circleCount);//我创建的圈子数
		$("#myvoteCount").html(data.data.myvoteCount);//我创建的投票数
		//加入的圈子数
		var addcircleCount = data.data.addcircleCount;
//		if(addcircleCount<=0){
			selectcircle();
//		}else{
//			location.href = basePath+"usercenter_me/"+userid;
//		}
	},error:function(data)
	{
	}
});
}

function selectcircle(){
	var selectcircleURL = "centerAction_selectcircle.do";
	$.ajax({
		url:selectcircleURL,
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#selectcircle").css("display","block");
			var circleList = data.data.circleList;
			for(var i = 0;i<circleList.length;i++){
				var str = "<li onmouseover=\"$(this).addClass('active_me');\" onmouseout=\"$(this).removeClass('active_me');\" onclick=\"$(this).addClass('active_click');\" id=\""+circleList[i].circleid+"\">"+circleList[i].circlename+"</li>";
				$("#showcircle").append(str);
			}
		},error:function(data)
		{
		}
	});
}
var cir = [];
function nextStep(){
	var ul= document.getElementById("showcircle");
	var li = ul.getElementsByTagName("li");
	for(var j = 0;j<li.length;j++){
		if(li[j].className != ""){
			cir.push(li[j].id);
		}
	}
	if(cir.length<=0){
		alert("请选择你感兴趣的圈子！");
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
				location.href = basePath+"usercenter_me/"+user.userid;
			},error:function(data)
			{
			}
		});
	}
//	location.href = basePath+"usercenter_me/"+userid;
}