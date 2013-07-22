<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>逸族飞闻</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<script src="app/feiwen/js/jquery.js" type="text/javascript"></script>
<script src="app/feiwen/js/address.js" type="text/javascript"></script>
<script src="app/feiwen/js/content.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- http://www.jb51.net/css/68228.html  -->
	

<style type="text/css">
.thumbImage img{ 
MAX-WIDTH: 100%!important;HEIGHT: auto!important;width:expression(this.width > auto ? "auto" : this.width)!important;
} 
.thumbImage {MARGIN: auto;WIDTH: auto;} 
*html.thumbImage img{ 
width:expression(this.width>100&&this.width>this.height?450:auto); 
height:expresion(this.height>450?450:auto); 
}
</style>
  </head>
  
  <body style="background-color: #grey">
	 <div style="margin: 10;">
	 	 <div style="margin: 5"><font size="4" style="color: #222222"><b id="title"></b></font></div>
	  	<div style="margin: 5"><font color="red" id="datetime" style="color: black;color: #7E7E7E"></font></div>
	  	<div class="thumbImage" ><font id="content" size="2" color="#383838">
			</font></div>
	  	<div style="text-align: center;">
	  		<img src="app/feiwen/images/yizuwx.png" width="130" height="130">
	  	</div>
	 </div>
  </body>
</html>
