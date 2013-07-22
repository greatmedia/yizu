<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索飞闻</title>
<script src="<%=basePath%>app/feiwen/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>app/feiwen/js/address.js" type="text/javascript"></script>
<script src="<%=basePath%>app/feiwen/js/searchfeiwen.js" type="text/javascript"></script>
<style type="text/css">
.content {
	color: #727272;
}
.content_qw {
	color: #727272;
	display: none;
}
.title {
	color: #000;
}
.title {
	font-weight: bold;
}
.sec_body { color:#000;background:#ddd;text-shadow:#ddd 0 1px 0px;font-family:Helvetica;line-height:1.5;font-size:small; }
</style>
</head>

<body class="sec_body">
<div id="loading" style="text-align: center;"><img src="app/feiwen/images/loading.gif"/>正在加载数据</div>
<div id="indexfeiwen">

</div>
</body>
</html>