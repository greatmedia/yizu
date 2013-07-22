<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>圈子成员列表</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<%--<script src="js/circle_left.js" type="text/javascript"></script>--%>
<script src="js/circle_user.js" type="text/javascript"></script>

<script>
 //鼠标滑过弹出层	
	function ddd(obj, sType) { 
	var oDiv = document.getElementById(obj); 
	if (sType == 'show') { oDiv.style.display = 'block';} 
	if (sType == 'hide') { oDiv.style.display = 'none';} 
	
} 
</script>
</head>

<body>

<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<label style="display:none" id="jr_tuichu"></label>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<!----登录窗口----->
<div class="main_cont">

<div class="main" id="min">
    <div class="user_name">
        <ul>
            <li class="name1" id="circleName">${circleleft.circlename }</li>
        	<li class="name1"><span id="quanzi_title"></span></li>
<!--            <li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.location.href='<%=basePath%>circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>'"/></li>-->
<!--            <li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.open('<%=basePath%>circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>')" style="display: none;"/></li>-->
<!--            <li class="tcqz"><img src="images/btn_01.gif" id="quit" onclick="join_or_quit_quanzi('<%=request.getParameter("id") %>',2)" style="display: none;"/></li>-->
        </ul>
    </div>
    <div class="line1"></div>
    <div class="line2"></div>

	<div class="feedwall">
   	<!---第一列数据-----> 
 
     <!---第一列数据截止-----> 
     <jsp:include flush="false" page="circle_left.jsp"></jsp:include>
     
     <!---中间的数据-----> 
      <div class="cire_algin">
      	<div id="circle_user">
      		<div align="center"><img src="images/loading.gif" alt="数据加载中请稍等..." />数据加载中请稍等...</div>
	     </div>
	   
      	</div>
      	 <div class="pl_page" id="pl_page" style="float: right;"></div>
	           
     </div>
      
	
 </div>
</div>
<%--</div>--%>
<script>
//var realurl = location.href;var idindexof = realurl.lastIndexOf("/");var cid = realurl.substring(idindexof + 1,realurl.search('.html'));getCircle(cid);
</script>
<!--<a href="javascript:(function(){window.open('http://t.163.com/article/user/checkLogin.do?link=http://news.163.com/&source='+'土豆网'+ '&info='+encodeURIComponent(document.title)+' '+encodeURIComponent(location.href),'_blank','width=510,height=300');})()" title="分享到网易微博"><img src="http://filer.blogbus.com/1054487/resource_10544871274700952v.png"  alt="网易微博分享" border="0"></a>-->
<!-- <div class="pl_page" id="downPage"><span id="firstPage"><a onclick="showData(1);return false" href="javascript:void(0);">首页</a></span>&nbsp;&nbsp;<span id="breakPage"></span>&nbsp;&nbsp;<span id="numPage"></span>&nbsp;&nbsp;<span id="nextPage" ></span></span></div>-->
<!-----页尾部分---->
<%--<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>--%>
</body>
</html>

