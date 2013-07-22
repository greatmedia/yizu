//var  userid =   location.search;
//userid   =   userid.substr(1,userid.length-1);
//userid = userid.substr(3,userid.length);
function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
//var userid = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var userid = realurl.substring(idindexof + 1);
getUser();
function getUser()
{
	var userURL = "centerAction_getUser.do";
	$.ajax({
		url:userURL,
		data:{id : userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var user = data.data.user;
			$("#userName").html(user.nick);
			$("#userSummary").html(user.declaration);
			if(user.image.length>2)
			{
				$("#userImg").html("<img src="+user.image+" width='198' />");
			}else{
				$("#userImg").html("<img src='images/nobody.gif' width='198' />");
			}
			if(user=='null')
			{
				$("#userImg").html("该用户不存在...");
			}
		},
		error:function(data)
		{
		}
	});
}