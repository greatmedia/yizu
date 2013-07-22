$(document).ready(function() {
	$("#upFile").bind("change", function() {
		var img = $(this).val();
		if (!img.match(/.jpeg|.jpg|.gif|.png|.bmp/i)) {
			alert('图片格式无效,请选择其他格式文件，支持.jpeg|.jpg|.gif|.png|.bmp！');
			return;
		}
		sc();

	});
});
function isValidMail(sText) {
	  var reMail = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
//	  alert(reMail.test(sText))
//	  var re = reMail.test(sText);
	
}
var isbj = true;
function setData()
{
		var email = $("#user_email").val();
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
		var nick = $("#user_nick").val();
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
		
		
		var about = $("#user_about").val();
		if(about.length>70)
		{
			alert("关于自己内容过长..");
			return false;
		}
		$("#saveSettingButton").css("display","none");
		$("#saveSetting").css("display","block");
		
		$.ajax({
			 url : "centerAction_setData.do", 
			 type : 'post', 
			 data:{id : nick,ids : about, image: $("#image").val(),email : $("#user_email").val()},
			 dataType : 'json', 
			 beforeSend : function(data) 
			 { 
				 $("#showSS").attr("src", "images/loading_32.gif"); 
			 }, 
			 success : function(data) 
			 { 
//				 $("#saveSettingButton").css("display","block");
//				$("#saveSetting").css("display","none");
//				 $("#showSS").css("display","none");
				 var result = data.data.result;
				 alert(result);
				 location.href = location.href;
//				 $("#user_nick").val(nick);
//				 $("#user_about").val(about);
//				 $("#userSummary").val(about);
//				 $("#user_email").val(email);
			 }, 
			 error : function(data) 
			 { 
//				 $("#showSS").css("display","none");
				 alert(result);
				 location.href = location.href;
			 }
		 });
}
