<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的发言</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/profile.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script> 
</head>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="main_cont">
<div class="main">
    <div class="user_name">
        <ul>
<!--            <li class="tcqz"><img src="images/btn_02.gif" /></li>-->
<!--            <li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.location.href='circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>'"/></li>-->
<!--            <li class="tcqz"><img src="images/btn_01.gif" /></li>-->
        </ul>
    </div>
    <div class="line1"></div>
    <div class="line2"></div>

	<div class="feedwall">
    <!---第一列数据----->
   	  <jsp:include flush="false" page="profile_left.jsp"></jsp:include>
   <!---右边部分-----> 
        
     <div class="profile_cont">
            
        <div class="profile_center">
           
           <div class="profile_nav">
               <ul>
                   <li><a id="active" href="profit/profile.jsp">我的发言</a></li>
                   <li><a style="border-left:none; border-right:none;" href="profit/profile_circle.jsp">我加入的圈子</a></li>
                   <li><a href="profit/profile_mycircle.jsp">我创建的圈子</a></li>
                   <li><a style="border-left:none;" href="profit/profile_vote.jsp">我创建的投票</a></li>
                   <li><a style="border-left:none; border-right:none;" href="profit/profile_gz.jsp">我关注的商品</a></li>
                   <li><a href="profit/profile_setting.jsp">账号设置</a></li>
               </ul>
           </div>
           
            <div class="profile_titer">
               <ul id="topic">
               <div class="profile_page" align="center">
	           			 <img src="images/loading.gif" />数据加载中请稍等...
	            </div>
               </ul>
           </div>
           <div class="profile_page">
           <div class="pl_page" id="pl_page"></div>
           </div>
           
        </div>
      
    </div>
</div>
</div>
</div>
<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>
</body>
</html>
