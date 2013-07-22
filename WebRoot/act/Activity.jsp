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
    
    <title>万人注册，豪礼大放送！</title>
    <link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet" />
<%--    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>--%>
	<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.4.2/jquery.js"></script>
    <script src="<%=basePath%>js/scroll.js" type="text/javascript"></script>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	
	<style type="text/css">
	*{ margin:0px; padding:0px;}
	img{ border:0px;}
	a{ text-decoration:none;}
	</style>
<script type="text/javascript">
function register()
{
	myScroll();
	$("#window_dl").css("display","block");
}
</script>
</head>
<body style="background:#e9e9e9">
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div style="width:100%; background:url(<%=basePath%>act/img/head_bj.jpg) repeat-x; height:368px;">
  <div style="width:999px; margin:auto; height:840px;">
      <img src="<%=basePath%>act/img/banner_1.jpg" border="0" usemap="#Map" />
      <map name="Map" id="Map">
        <area shape="rect" coords="67,384,217,441" onclick="register()" href="javascript:void()"  /> 
      </map>
  </div>
<div style="width:999px; margin:auto; position:relative;">
      <img src="<%=basePath%>act/img/center_1.jpg" />
    <div style="position:absolute; width:400px; height:450px; background:#fff; z-index:999; top:70px;; right:50px;">
    	<img alt="获奖名单" src="act/img/20120913001.jpg">
    </div>
  </div>
</div> 

</body>
</html>
