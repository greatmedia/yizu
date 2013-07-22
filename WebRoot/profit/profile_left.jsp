<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--这个页面我改过了，引用他的页面已经引入了jquery这个插件，这里面就不需要了，还有就是引用给他的页面已经定义了html head标签，这里面就不要定义了--%>
<link href="<%=basePath%>css/style.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/profile_left.js" type="text/javascript"></script>
<script src="<%=basePath%>js/scroll.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script> 
<div class="col">
	<div class="box">
		<div class="items">
			<div class="feed_container" id="userImg"></div>
			<div id='userMsg'></div>
			<p class="username" id="userName"></p>
		</div>
		<div class="summary3">
			<p class="c_grey3">
				关于自己：
				<br>
				<label id="userSummary"></label>
			</p>
		</div>
	</div>
</div>
