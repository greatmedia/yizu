<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>创建飞闻</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>

<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
<script charset="utf-8" src="<%=basePath %>js/createFeiwen.js"></script>
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
        				//'source', 
						items : [
							'undo', 'redo','source','fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
						    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						    'insertunorderedlist', '|', 'link','image','emoticons','preview']
						,
						htmlTags : {
        					div : [
        			                'align', '.border', '.margin', '.padding', '.text-align', '.color',
        			                '.background-color', '.font-size', '.font-family', '.font-weight', '.background',
        			                '.font-style', '.text-decoration', '.vertical-align', '.margin-left'
        			        ],
        			        table: [
        			                'border', 'cellspacing', 'cellpadding', 'width', 'height', 'align', 'bordercolor',
        			                '.padding', '.margin', '.border', 'bgcolor', '.text-align', '.color', '.background-color',
        			                '.font-size', '.font-family', '.font-weight', '.font-style', '.text-decoration', '.background',
        			                '.width', '.height', '.border-collapse'
        			        ],
        			        'td,th': [
        			                'align', 'valign', 'width', 'height', 'colspan', 'rowspan', 'bgcolor',
        			                '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.font-weight',
        			                '.font-style', '.text-decoration', '.vertical-align', '.background', '.border'
        			        ],
        			        a : ['href', 'target', 'name'],
        			        embed : ['src', 'width', 'height', 'type', 'loop', 'autostart', 'quality', '.width', '.height', 'align', 'allowscriptaccess'],
        			        img : ['src', 'width', 'height', 'border', 'alt', 'title', 'align', '.width', '.height', '.border'],
        			        'p,ol,ul,li,blockquote,h1,h2,h3,h4,h5,h6' : [
        			                'align', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.background',
        			                '.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.text-indent', '.margin-left'
        			        ],
        			        pre : ['class'],
        			        hr : ['class', '.page-break-after'],
        			        'br,tbody,tr,strong,b,sub,sup,em,i,u,strike,s,del' : []
    					},
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
		                        createFeiwen();
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
            .ke-container{
            	height: 200px;
            }
    	</style>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>

<br/><br/><br/><br/><br/><br/>
<div style="width:100%; height:100%; padding-left:350px;">
飞闻标题：<input id="title" maxlength="300" />
<br/><br/><br/>
飞闻标签：<input id="feiwentag" maxlength="500" style="width: 300px;"/>
<br/><br/><br/>
飞闻分类：<select id="feiwenType" onchange="getid()">
</select>
<br/><br/><br/>
<textarea name="content" id="content" cols="79" style="height:350px; width:650px; visibility:hidden;"></textarea>
<br/>
<div id="isfabu"><input type="button"  id="formSubmit" value="发布"/></div>
</div>
</body>
</html>

