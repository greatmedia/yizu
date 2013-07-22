<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>编辑飞闻</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>

<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
<script charset="utf-8" src="<%=basePath %>js/updateFeiwen.js"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script> 
<script type="text/javascript">
			$(document).ready(function() {
	             KindEditor.ready(function(K) {
					var editor1 = K.create('textarea[name="content"]', {
						cssPath : '<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css',
						uploadJson : '<%=basePath %>kindeditor-4.1.1/jsp/upload_json.jsp',
        				fileManagerJson : '<%=basePath %>kindeditor-4.1.1/jsp/file_manager_json.jsp',
						items : [
							'undo', 'redo','source', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
						    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						    'insertunorderedlist', '|', 'link','image','emoticons','preview']
						,
						allowFileManager : true,
						afterCreate : function() {
							var self = this;
							K.ctrl(document, 13, function() {
								self.sync();
								document.forms['example'].submit();
							});
							K.ctrl(self.edit.doc, 13, function() {
								self.sync();
								document.forms['example'].submit();
							});
							$('#formSubmit').click(function () {
		                        self.sync();
		                        updateFeiwen();
		                    });
						}
					});
					prettyPrint();
				});
	        });
		</script>
		<style type="text/css">
	        label.error{
		        background: #fff6bf url(images/alert.png) center no-repeat;
				background-position: 5px 50%;
				text-align: left;
				padding: 2px 20px 2px 25px;
				border: 1px solid #ffd324;
				display: none;
				width: 200px;
				margin-left: 10px;
				font-size: 14px;
            }
            .ke-container{height:400px;}
    	</style>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div style="height: 100%; margin-left:300px;">
		<input id="fwid" maxlength="300" type="hidden" value="${feiWen.fwid }"/>
		<input id="tid" maxlength="300" type="hidden" value="${feiWen.tid }"/>
		<br/><br/><br/><br/><br/><br/>
		飞闻标题：<input id="title" maxlength="300"  value="${feiWen.title }"/>
		<br/><br/><br/>
		
		飞闻分类：<select id="feiwenType" onchange="getids()" >
		<c:choose>
			<c:when test="${feiWen.tid == 1}">
				<option selected="selected" id="1">娱乐</option>
			</c:when>
			<c:when test="${feiWen.tid == 2}">
				<option selected="selected" id="2">体育</option>
			</c:when>
			<c:when test="${feiWen.tid == 3}">
				<option selected="selected" id="3">社会</option>
			</c:when>
			<c:when test="${feiWen.tid == 4}">
				<option selected="selected" id="4">商务</option>
			</c:when>
			<c:when test="${feiWen.tid == 5}">
				<option selected="selected" id="5">自然</option>
			</c:when>
			<c:when test="${feiWen.tid == 6}">
				<option selected="selected" id="6">才俊</option>
			</c:when>
		</c:choose>
		</select>
		<br/><br/><br/>
		<textarea name="content" id="content" cols="79" style="width:650px; min-height: 450px;visibility:hidden;">${feiWen.content }</textarea>
		<br/>
		<div id="isfabu"><input type="button"  id="formSubmit" value="修改"/></div>
</div>
</body>
</html>

