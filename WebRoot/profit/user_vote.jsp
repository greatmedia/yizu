<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName

()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>他创建的投票</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/user_vote.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script>
function oProMinMouseOut(obj , oPro_y)
{
	var oPro_y1 = document.getElementById(oPro_y);
	oPro_y1.style.display = "none";
}
function onProMinMouseOver(obj, oPro_y)
{
	var oPro_y1 = document.getElementById(oPro_y);
	oPro_y1.style.display = "block";
}
</script>
</head>

<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="main_cont">

<div class="main">
    <div class="user_name">
    </div>
    <div class="line1"></div>
    <div class="line2"></div>

	<div class="feedwall">
    <!---第一列数据----->
   	 <jsp:include flush="false" page="user_left.jsp"></jsp:include>
   <!---右边部分-----> 
        
     <div class="profile_cont">
            
        <div class="profile_center">
           
           <div class="profile_nav">
               <ul>
                   <li><a  href="user/<%=request.getParameter("id") %>">他的发言</a></li>
                   <li><a style="border-left:none;" href="user_circle/<%=request.getParameter("id") %>">他加入的圈子</a></li>
                   <li><a style="border-left:none;" href="user_mycircle/<%=request.getParameter("id") %>">他创建的圈子</a></li>
                   <li><a id="active" style="border-left:none;" href="user_vote/<%=request.getParameter("id") %>">他创建的投票</a></li>
               </ul>
           </div>
          		 
            <div class="profile_titer">
               <div class="prof_mine">
                  <div class="prof_mine_t" id="my_vote">
                  </div>
               </div> 
            </div>
			<label id="loading">
                 		 <div class="profile_page" align="center">
	           			 <img src="images/loading.gif" />数据加载中请稍等...
	            		</div>
                  </label>
           <div class="profile_page_1">
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