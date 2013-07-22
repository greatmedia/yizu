allOnUserLinePage(1,2);
var pageNumber,rows;
/**
 * 根据分页，查询在线用户
 * @return
 */
function allOnUserLinePage(number,row)
{
	var allOnUserLinePageURL="userInfoAction_allOnUserLinePage.do";
	$.ajax({
	url:allOnUserLinePageURL,
	data:{pageNum : number,pageSize : row},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		pageNumber = number;
		rows = row;
		$("#alluser").html("");
		var userLineCount = data.data.userLineCount;
		$("#usercount").html("在线用户数量："+userLineCount);
		if(userLineCount>0)
		{
			var userLines = data.data.userLines;
			for ( var i = 0; i < userLines.length; i++) {
				$("#alluser").append("<tr><td>"+userLines[i].userid+"</td><td>"+userLines[i].nick+"</td><td>"+userLines[i].email+"</td></tr>");
			}
		}
	},error:function(data)
	{
		$("#alluser").html("网络异常！");
	}
	});
//	setTimeout("allOnUserLinePage()",10000);
}
/**
 * 查找用户
 * @return
 */
function findUser()
{
	var s = checkUid(); 
	if(!s)
	{
		alert("输入数据不正确！");
		return false;
	}else{
		var checkOnUserLineURL = "userInfoAction_checkOnUserLine.do";
		$.ajax({
		url:checkOnUserLineURL,
		data:{id : $("#uid").val()},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var userline = data.data.userline;
			if(userline==null)
			{
				$("#userValue").html($("#uid").val()+":用户不存在！");
				return flase;
			}
			var user = data.data.user;
			if(userline) {
				$("#userValue").html($("#uid").val()+":用户在线，昵称："+user.nick+";Email:"+user.email);
			}else{
				$("#userValue").html($("#uid").val()+":用户离线，昵称："+user.nick+";Email:"+user.email);
			}
			
		},error:function(data)
		{
			$("#userValue").html("网络异常！");
		}
		});
	}
}
/**
 * 检查用户ID是否输入正确
 * @return
 */
function checkUid()
{
	var r = /^\+?[1-9][0-9]*$/;
	return  ck = r.test($("#uid").val());
}