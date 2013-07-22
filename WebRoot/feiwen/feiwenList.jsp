<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>飞闻列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" type="text/css" rel="stylesheet" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/feiwenList.js" type="text/javascript"></script>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
	</script>
  </head>
  
  <body>
  <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  <br/><br/><br/><br/>
   <div style="text-align: center">
	   	<table width="960">
	   	 <tr>
	   	 	<td>创建人</td>
	   	 	<td>创建时间</td>
	   	 	<td>类型</td>
	   		<td>标题</td>
	   		<td>操作</td>
	   	</tr>
	   	<tbody id="list">
	   	</tbody>
	   	</table>
   </div>
  </body>
</html>
