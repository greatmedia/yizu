<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://resource.app.qq.com/api/scripts/sjqqapi.js"> </script> 
 <link rel="stylesheet" type="text/css" href="http://resource.app.qq.com/api/styles/sjqqapi.css"/>
 <script src="http://wandoujia.com/api/wdapi.js" type="text/javascript"></script>
  <script type="text/javascript" src="http://zs.91.com/script/api/key121121.js" charset="utf-8"></script>
    <script type="text/javascript">
  function changs()
  {
	  /*
	   var str = $("#nums").val();
	   var stridof = str.lastIndexOf(".");
	   var strlength = str.substring(stridof + 1);
	   if(strlength==2)
	   {
		   $("#values").val(str);
	   }else if(strlength==1){
		   $("#values").val(str+"0");
	   }else{
		   $("#values").val(str);
	   }*/

	   
	   //正则表达式
	  var str = $("#nums").val();
	  var reg = new RegExp(/.\b./);
	  if(reg.test(str))
	  {
		  alert(true);
	  }else{
		  alert(false);
	  }
  }
    </script>

</head>
  
 <body>
 <div style="display: none;">
 <input id="nums" type="text"/><input type="button" onclick="changs()" value="确定" /><input id="values" type="text"/>
 </div>
 <br/>
<div style="text-align: center;">
<span title="使用腾讯手机管家(PC版)一键安装到手机" asistanturlid="990599" appname="逸族飞闻" ex_url="<%=basePath%>/app/feiwen/feiwen.apk" onclick="qqapp_dl_apk(this);" style="cursor:pointer"><img src="http://img2.sj.qq.com/res/sj.qq.com/images/api8.png"/> </span>
<br/>


<a href="<%=basePath%>/app/feiwen/feiwen.apk" name="逸族飞闻" onclick="return wdapi_apkdl_m(this, 'source');" title="通过豌豆荚下载">
    <img alt="豌豆荚一键安装" src="http://img.wdjimg.com/logo/oneclick-4.png"/>
</a>

 <br/>
 <a href="<%=basePath%>/app/feiwen/feiwen.apk?p=android&f_name=%u9038%u65CF%u98DE%u95FB&f_version=1"  name="逸族飞闻" onclick="return Key.Open(this, 'android');" SoftIcon="" title="通过91助手下载" ><img id="imgPreview" alt="91助手一键安装" src="http://img5.91huo.cn/zs/images/api/btn_01.jpg"  /></a>
</div>
</body>
</html>
