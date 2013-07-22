var centerURL = "centerAction_center.do";
var uid; 
$.ajax({
	url:centerURL,
	data:{id : $("#id").val()},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		var user = data.data.user;
		if(user==null)
		{
//			alert("你还没有登录..");
//			myScroll();
//			$("#window_dl").css("display","block");
			var indexURL = "index.jsp";
//			window.open(basePath + indexURL); 
			window.location.href=""+basePath+"index.jsp";
			return false;
		}
		$("#userName").html(user.nick);
		$("#userSummary").html(user.declaration);
		uid = user.userid;
		if(user.image.length>2)
		{
			$("#userImg").html("<img src="+user.image+" width='198' />");
		}else{
			$("#userMsg").html("您还没有上传属于您自己的头像！");
			$("#userImg").html("<img src='images/nobody.gif' width='198' />");
		}
	},
	error:function(data)
	{
		
	}
});