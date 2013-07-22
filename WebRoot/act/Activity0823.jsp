<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="textml; charset=utf-8">
    <base href="<%=basePath%>">
    
    <title>城市大搜索</title>
    <link href="css/style.css" type="text/css" rel="stylesheet" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/scroll.js" type="text/javascript"></script>
	<style>
	*{ margin:0; padding:0;}
	img{ border:0;}
	</style>
  </head>
  
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
	<div style="width: 960px; margin: auto;">
		<div>
		<img src="act/img/banner20120823.gif" border="0" usemap="#Map">
		<map name="Map">
			<area shape="rect" coords="37,115,167,154" href="javascript:void(0);" onclick="ck();">
		</map>
		</div>
		<div style="margin-top: -4px;">
			<img src="act/img/center20120823.gif" border="0" usemap="#Map2">
			<map name="Map2">
				<area shape="rect" coords="35,20,121,45"
					href="http://www.1mily.com/" target="_blank">
				<area shape="rect" coords="125,18,205,46"
					href="http://www.1mily.com/topic.html" target="_blank">
				<area shape="rect" coords="207,17,295,46"
					href="http://www.1mily.com/vote.html" target="_blank">
			</map>
		</div>
		</div>
<script type="text/javascript">
function ck()
{
	var ss = "myVoteInfoAction_getUser.do";
	$.ajax({
		url : ss,
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var u = data.data.user;
			if(u==null)
			{
				alert("您还没有登录..");
				myScroll();
				$("#window_dl").css("display","block");
				return false;
			}else{
				window.location.href="<%=basePath%>circleInfoAction_create.do";
			}
		},
		error : function(data) {
		}
	});
}
</script>
</body>
</html>
