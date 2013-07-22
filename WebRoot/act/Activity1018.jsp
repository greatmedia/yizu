<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.apache.struts2.components.Include"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>校园活动</title>
    <link href="css/style.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/scroll.js" type="text/javascript"></script>
<style type="text/css">
*{ margin:0px; padding:0px;}
</style>
</head>

<body>


<div>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  <div style="background: url(act/img/banner_20121018.jpg) repeat-x; height:201px; width:100%; position:relative; top:43px;">
      <div style="width:960px;  height:224px; margin:auto;"><img src="act/img/zuce_bj.jpg" name="banner" usemap="#bannerMap" border="0" />
        <map name="bannerMap" id="bannerMap">
          <area shape="rect" coords="25,153,206,226" onclick="$('#window_dl').css('display','block');" href="javascript:void(0);"/>
        </map>
     </div>
    <div style="width:960px; margin:auto; background:url(act/img/zuce_bj1.jpg) no-repeat; height:350px;"></div>
    <div style="width:925px; margin:auto; background:url(act/img/zuce_bj2.jpg) no-repeat; height:390px; padding-top:70px; padding-left:35px">
         <div style="width:377px; height:295px;"></div>
      </div>
   </div>
</div>
</body>
</html>