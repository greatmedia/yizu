<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美图秀秀开放平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery.js" type="text/javascript"></script>
	 <script src="http://xiuxiu.web.meitu.com/plugin/open/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    $("#xiuxius").css("display","none");
    var basePath = "<%=basePath%>";
	window.onload=function(){
        xiuxiu.embedSWF("altContent", 2);
        xiuxiu.setUploadURL(basePath+"/circleInfoAction_swfUpload.do");//修改为您自己的上传接收图片程序
        xiuxiu.onInit = function ()
        {
            //xiuxiu.loadPhoto("http://xiuxiu.web.meitu.com/images/example/04.jpg");
        }	
        xiuxiu.setUploadType(2);
        xiuxiu.onUploadResponse = function (data)
        {
            var str = data;
            str=str.substring((str.indexOf(':')+2),(str.indexOf(',')-1));
           // str = basePath+str;
            str ="/"  + str;
            $("#meitu").css("display","none");
            $("#imgs").append("<img src="+str+">");
        }
	}
	var i = 0;
	function isshowxiuxiu()
	{
		if(i%2==0)
		{
		$("#meitu").css("display","block");
		}else{
		$("#meitu").css("display","none");
		}
		i++;
	}
    </script>
    <style type="text/css">
        html, body { height:100%; overflow:hidden; }
        body { margin:0; }
    </style>
</head>
  
 <body>
 <a href="javascript:void(0);" onclick="isshowxiuxiu()">上传图片</a>
<div id="meitu" style="display: none;">
<div id="altContent">
    <h1>美图秀秀</h1>
</div>
</div>
<div id="imgs"></div>
</body>
</html>
