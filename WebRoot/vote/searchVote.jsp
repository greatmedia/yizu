<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>逸族网 - 开启你的第二人生</title>
<link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/voteall.js" type="text/javascript"></script>
<script src="js/searchVote.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script>
</head>
<body>
<!--头部 -->
  <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  <!--中间 -->
  <div class="center_bj">
  		<div id="left"></div>
     <div class="center" id="main">
         <div class="center_mian" >
             <div class="min_left" >
                <div class="mim_clear"  id="showDiv">
                </div>
				<div class="pl_page" id="pl_page"></div>
             </div>

         </div>
<%--          <div class="min_right fillet">--%>
<%--                 <h2>热门投票</h2>--%>
<%--                 <div  id="hotVote">--%>
<%--                  </div>--%>
<%--             </div>--%>
     </div>    
  </div>
<div>
</div>
</body>
</html>
