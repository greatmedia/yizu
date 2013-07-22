<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>在线用户</title>
    <link href="css/style.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/userline.js" type="text/javascript"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <div>
  	<input type="text" id="uid" />
  	<input type="button" value="查询" onclick="findUser()" />
  	<br/>
  	<div id="userValue"></div>
  	<br/>
  	<br/>
  	<div id="usercount"></div>
  	<table id="alluser">
  		<tr><td>ID</td><td>昵称</td><td>Email</td></tr>
  	</table>
    <div class="pl_page" id="pl_page"></div>
  </div>
  </body>
</html>
