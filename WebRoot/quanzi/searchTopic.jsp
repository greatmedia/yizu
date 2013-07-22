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
<title>搜索话题</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/topicall.js" type="text/javascript"></script>
<script src="<%=basePath%>js/searchTopic.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath%>js/imgChange.js" type="text/javascript"></script>
<link href="<%=basePath%>css/topic.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script>
</head>

<body>
<!--头部代码 -->
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>

<div class="topic_center">
      <div class="topic1_center" >
         <div class="topic_new">
        <!--话题页面左边的代码 -->
             <div class="topic_left" id="div1">
                <div class="topic_subqie" id="searchTopic" style="display:block;" >
                </div> 
              </div>
             <!--话题页面右边的代码 -->
             <div class="topic_right">
             </div>
         </div>     
      </div>
</div>
</body>
</html>
