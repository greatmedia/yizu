<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>审核投票</title>
<link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/voteall.js" type="text/javascript"></script>
<script src="<%=basePath%>js/checkVote.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/public_min.js" type="text/javascript"></script> 
<script src="js/scroll.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script>
<style type="text/css">
    body
    {
        font-size:12px;
        color:#474747;
        margin:0px; 
        padding:0px;
    }
    .pagenumber
    {
        border-style:solid;
        border-width:1px;
        border-color:Orange;
        margin-left:10px;
        margin-top:10px;
        padding:4px;
        text-align:center;
        float:left;
        cursor:hand;
        background-color:White;
        color:Black;
    }
    .pagenumberselected
    {
        background-color:#CCCCCC;
        color:White;
    }
	#login {height:100%;  position:absolute; width:100%; padding-top:250px;} 
    </style>
    <script type="text/javascript">
		function isLogin(){
    		if(user != null && user != ""){
    		location.href="<%=basePath%>vote/createVote.jsp";
    		}else{
    			alert("请先登录吧，再去创建投票！");
    			myScroll();
    			$("#window_dl").css("display","block");
    		}
    	}
    </script>
</head>
<body>
<!--头部 -->
  <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>

  <div id="login" style="z-index:999; text-align:center; background:#CDCDB4;" >
	<div>
	<h2>请输入密码</h2><br />
		密码：<input type="password" id="pwd" size="30" style="height: 30px;"/><br /><br />
		<a href="javascript:void(0)"><img src="images/dl_btn1.jpg" onclick="loginSub()"/></a>
	</div>
</div>
  <!--中间 -->
  <div class="center_bj" id="showall" style="display:none;">
  		<div id="left"></div>
     <div class="center" id="main">
     <div class="center_1" id="allDiv">
     	<a href="javascript:void(0);" onclick="getAllVote(8,1,1)" id="all">所有的投票</a>
     </div>
     <div class="center_1" id="nocheck">
     	<a href="javascript:void(0);" onclick="noThoughCheck(8,1,1)">未审核的投票</a>
     </div>
     
      <div class="center_1" id="checkThr">
     	<a href="javascript:void(0);" onclick="throughCheckVote(8,1,1)">通过的投票</a>
     </div>
         <div class="center_mian" >
             <div class="min_left" >
                <div class="mim_clear"  id="showDiv">
                </div>
				<div class="pl_page" id="pl_page"></div>
             </div>
         </div>
     </div>    
  </div>
<div>
</div>
</body>
</html>
