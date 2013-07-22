<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>发布内容</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/om-fileupload.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<%--<script src="js/om-core.js" type="text/javascript"></script>--%>

<%--<script type="text/javascript" src="js/om-validate.js"></script>--%>
<script src="js/public_min.js" type="text/javascript"></script>
<%--<script src="js/circle_left.js" type="text/javascript"></script>--%>

<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
<style type="text/css">
	label.error {
		background: #fff6bf url(images/alert.png) center no-repeat;
		background-position: 5px 50%;
		text-align: left;
		padding: 2px 20px 2px 25px;
		border: 1px solid #ffd324;
		display: none; <%--
		width: 200px; --%>
		margin-left: 10px;
		font-size: 14px;
	}

	.ke-container {
		height: 250px;
	}
	
	.ke-edit {
		height: 250px;
	}
	
	.ke-edit-iframe {
		height: 250px;
	}
</style>
	</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script src="js/om-fileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<script type="text/javascript">
			var sizeCount = 0;
			$(document).ready(function() {
				sizeCount = 0;
	            $('#file_upload').omFileUpload({
	            	swf : '<%=basePath%>js/om-fileupload.swf',
	                action : '<%=basePath%>circleInfoAction_swfUpload.do',
	                buttonImg : '<%=basePath%>images/cjqz_02.jpg',
	                multi : false,
	                autoUpload : true,
	                queueSizeLimit : 10,
	                fileExt : '*.jpg;*.png;*.gif;*.jpeg',
	                fileDesc : "选择图片 *.jpg;*.png;*.gif;*.jpeg",
	                sizeLimit : 1024*1024*20,
	                onSelect : function(ID, fileObj, event){
		                alert(fileObj.getWidth());
	                	if(sizeCount != 0 && sizeCount/1024/1024 > 20){
	                		alert("亲，您上传的图片已经超过20MB了，重新发布一个内容再添加把！");
	                	}
	                },
	                onComplete : function(ID,fileObj,response,data,event){
	                	
	                	sizeCount += fileObj.size;
	                	$('#file_upload').omFileUpload({sizeLimit:1024*1024*20-sizeCount});
	                	
	                    var jsonData = eval('(' + $.trim(response) + ')');
				  		var imgHtml = ""+
							"<img class=\"avatar\" src='"+jsonData.fileUrl+"' />"+
							"";
						$("#uploadImages").html(imgHtml);
<%--			            $("#uploadImages").html($("#uploadImages").html() + imgHtml);--%>
						$("#images").val(jsonData.fileUrl);
	                }
	            });
	            
	            $("#reg").validate({
	                rules : {
	                    "circleDetail.title" : {
	                    	required : true
	                    },
	                    "circleDetail.circlecontent" : {
	                        required : true
	                    }
	                    ,
	                    "circleDetail.ctag" : {
	                        required : true
	                    },
	                },
	                messages : {
	                    "circleDetail.title" : {
	                        required : "必填"
	                    },
	                    "circleDetail.circlecontent" : {
	                        required : "必填"
	                    },
	                    "circleDetail.ctag" : {
	                        required : "必填"
	                    },
	                },
	                submitHandler : function(){
		                var sel=document.getElementById("topictype");
		                var val=sel.options[sel.selectedIndex].value;  
<%--		                alert(val);--%>
		              	//1 普通圈子被容  2话题   3 PK huati
		                if(val == 2 || val ==3){
		                	if($("#uploadImages").find("img").length <= 0 ){
	                			alert("请至少上传一图片");
	                			return false;
	                		}
				        }
<%--	                	if($("#uploadImages").find("img").length > 0 ){--%>
<%--	                		$("#uploadImages").find("img").each(function(index, obj){--%>
<%--	                			if(index != 0){--%>
<%--	                				$("#images").val($("#images").val() + ",");--%>
<%--	                			}--%>
<%--	                			$("#images").val($("#images").val() + $(obj).attr("src"));--%>
<%--	                		});--%>
<%--	                	}--%>
	                	$("#reg").ajaxSubmit({
				                url:"<%=basePath%>circleInfoAction_ajaxCreateDetail.do",
				                data:{topictype:val},
					            type: 'post',
				                dataType: 'json',
				                beforeSend:function(){
					                $("#formSubmit").hide();
				                },
				                success:function(data){
				                    if(data.flag){
										if(val == 2 || val ==3)
				                   		{
											location.href="<%=basePath%>quanzi/newTopics.jsp";
				                   		}else{
				                   			var realurl = location.href;
				                   			var idindexof = realurl.lastIndexOf("=");
				                   			if(realurl.indexOf(".html")<=0)
				                   			{
				                   				did = realurl.substring(idindexof + 1);
				                   			}else{
				                   				did = realurl.substring(idindexof + 1,realurl.search(".html"));
				                   			}
				                    		location.href="<%=basePath%>cir2/1/"+did+".html";
				                   		}
				                    }else{
				                    	alert("创建失败");
				                    	$("#formSubmit").show();
					                }
				                },
				                error:function(){
				                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
				                    $("#creat_submit").show();
            						$("#loadding").hide();
				                }
				            });
	                	//}
	                   // return false;
	                }
	            }) 	;
	            //'source', '|',
	             KindEditor.ready(function(K) {
					var editor1 = K.create('textarea[name="circleDetail.circlecontent"]', {
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
		                    });
						}
					});
<%--					prettyPrint();--%>
				});
	        });
	        
	        function ajaxDeleteCurrentFile(url, obj){
	        	$.ajax({
		            url:"<%=basePath%>circleInfoAction_ajaxDeleteCurrentFile.do",
		            type: 'post',
		            dataType: 'json',
		            data : {
		                "filename":url
		            },
		            beforeSend:function(){
		            	$(obj).attr("src","<%=basePath%>images/loading_32.gif");
		            },
		            success:function(data){
		            	if(data.flag){
<%--		            		$(obj).parent().remove();--%>
							$(obj).parent().html("");
		            	}else{
		            		alert(data.msg);
		            		$(obj).attr("src","<%=basePath%>images/close_ico.gif");
		            	}
		            },
		            error:function(){
		               alert("删除失败，请稍后重试。");
		               $(obj).attr("src","<%=basePath%>images/close_ico.gif");
		            }
		        });
	        }
		</script>
<div class="main_cont">
	<div class="tjnr">
		<div class="tjnr_left">
			<div class="tjnr_left_head"><p>发布内容</p></div>
			<div class="tjnr_left_middle">
				<form id="reg" name="reg" action="" method="post">
				<ul>
					<li>
						<div class="item_title">标题：</div>
						<div class="item_tjnr">
							<input name="circleDetail.circleid" id="circleid" type="hidden" class="m_input" value="${id }"/>
							<input type="text" class="ui-text skin-text-willwhite" id="title" name="circleDetail.title"  style="overflow: auto; width:520px;" />
						</div>
					</li>
					<li>
						<div class="item_title">关键字：</div>
						<div class="item_tjnr">
							<input type="text" class="ui-text skin-text-willwhite" id="ctag" name="circleDetail.ctag"  style="overflow: auto; width:520px;" />
						</div>
					</li>
					<li>
						<div class="item_title">内容：</div>
						<div class="item_tjnr">
							<textarea class="ui-textarea skin-textarea-willwhite" style="height: 92px; overflow: auto; width: 535px; height: 250px;" name="circleDetail.circlecontent" id="circlecontent"></textarea>
						</div>
					</li>
					<li>
						<div class="item_title">类型：</div>
						<div class="item_tjnr">
							<select class="ui-text skin-text-willwhite" style="height: 38px; overflow: auto; width:162px;" id="topictype">
								<option value="1">圈子</option><option value="2">话题</option><option value="3">pk话题</option>
							</select>
						</div>
					</li>
					<li>
						<div class="item_title">标签：</div>
						<div class="item_tjnr">
							<select class="ui-text skin-text-willwhite" style="height: 38px; overflow: auto; width:162px;" id="circletag" name="circleDetail.def4">
								<c:forEach var="circletag" items="${circletagrelateList}" varStatus="c">
									<option value="${circletag.ctid}">${circletag.cirtag.tagname}</option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<div class="item_title">上传图片：</div>
						<div class="item_tjnr">
							<a href="javascript:void(0);" onclick="$('#file_upload').click()" id="file_upload"><img src="images/cjqz_02.jpg" /></a>
							<input  type="file" style="display:none;" />
							<input type="hidden" id="images" name="images"/>
							<p style="font-size:12px; line-height:50px;">JPG, GIF, PNG或BMP，不超过20mb，只可上传一张</p>
							<div id="uploadImages">
							</div>
						</div>
					</li>
					<li>
						<div class="panel_tjnr">
						<input id="formSubmit" type="image" src="../images/cjqz_03.jpg" />
						<a href="javascript:void(0);"><img src="images/tjnr_01.jpg" onclick="history.back()"/></a>
						</div> 
					</li>
				</ul>
				</form>
			</div>
		</div>
	</div>
</div>
<%--<div class="main_cont">--%>
<%--<div class="main" id="min" style="position:relative; padding-top:42px;">--%>
<%--<div class="user_name">--%>
<%--        <ul>--%>
<%--            <li class="name1" id="circleName">${circleleft.circlename }</li>--%>
<%--<!--            <li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.open('<%=basePath%>circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>')" style="display: none;"/></li>-->--%>
<%--<!--            <li class="tcqz"><img src="images/btn_01.gif" id="quit" onclick="join_or_quit_quanzi('<%=request.getParameter("id") %>',2)" style="display: none;"/></li>-->--%>
<%--        </ul>--%>
<%--</div>--%>
<%----%>
<%-- <div class="line1"></div>--%>
<%--    <div class="line2"></div>--%>
<%----%>
<%--	<div class="feedwall">--%>
<%--     <jsp:include flush="false" page="circle_left.jsp"></jsp:include>--%>
<%--          <div class="details_cont_n">--%>
<%--            <div class="details-left">--%>
<%--          <!-- 标题部分 -->--%>
<%--          <form id="reg" name="reg" action="" method="post">--%>
<%--                   <div class="create_value">--%>
<%--                       <h3>标题</h3>--%>
<%--                       <div class="creat-con" style="height: 30px;">--%>
<%--						<input name="circleDetail.circleid" id="circleid" type="hidden" class="m_input" value="${id }"/>--%>
<%--						<input name="circleDetail.title" id="title" type="text" class="cterat-value1" />--%>
<%--						<input type="checkbox" id="def1" name="circleDetail.def1"  onclick="checkedsT()" value="1"/>话题--%>
<%--						<input type="checkbox" id="def2" name="circleDetail.def2" onclick="checkeds()" value="1"/>PK话题--%>
<%--					</div>--%>
<%--					--%>
<%--					<!-- 描述部分 -->--%>
<%--					<br />--%>
<%--					<div class="create_value">--%>
<%--						<h3>内容</h3>--%>
<%--					</div>--%>
<%--					<div class="creat-con">--%>
<%--						<textarea name="circleDetail.circlecontent" id="circlecontent" cols="79" style="width:650px;height: 250px;visibility:hidden;"></textarea>--%>
<%--					</div>--%>
<%--					<div class="creat-photo" style="height: 50px;">--%>
<%--						--%>
<%--					</div>--%>
<%----%>
<%--					<!--图片部分 -->--%>
<%----%>
<%--					<div class="creat-photo">--%>
<%--						<div class="creat-img">--%>
<%--							<input type="file" id="file_upload" name="file_upload"/>--%>
<%--							<input type="hidden" id="images" name="images"/>--%>
<%--						</div>--%>
<%--						<div class="img_right">--%>
<%--							<font color="red">*</font>JPG、GIF、PNG或BMP，不超过20MB，一次可上传多张。--%>
<%--						</div>--%>
<%--					</div>--%>
<%--					--%>
<%--					 <div class="cteat-post-yin" id="yin">--%>
<%--                        <ul id="uploadImages">--%>
<%--						--%>
<%--						</ul>--%>
<%--                     </div> --%>
<%--					--%>
<%--					<div  id="creat_submit" class="creat_submit">--%>
<%--						<img src="images/btn_11.jpg" onclick="history.back();"/>--%>
<%--						<input id="formSubmit" type="image" src="images/btn_09.jpg" />--%>
<%--					</div>--%>
<%--                    <div id="loadding" class="creat_submit" style="display: none;">--%>
<%--                        <img src='<%=basePath%>images/loading_32.gif'/>正在创建圈子内容，请稍候......--%>
<%--                    </div>--%>
<%--	       	 </div>--%>
<%--		</form>--%>
<%--   		 </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<script>function getQueryStringRegExp(name){var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";}var id = getQueryStringRegExp("id");getCircle(id);</script>--%>
<%--<script >--%>
<%--function checkeds()--%>
<%--{--%>
<%--	--%>
<%--	$("#def1").attr("checked")==true;--%>
<%--	document.getElementById("def1").checked = true;--%>
<%--if(document.getElementById("def2").checked == true)--%>
<%--{--%>
<%--	document.getElementById("def1").checked = true;--%>
<%--}--%>
<%--if(document.getElementById("def2").checked == false)--%>
<%--{--%>
<%--	document.getElementById("def1").checked = false;--%>
<%--}--%>
<%--}--%>
<%--function checkedsT()--%>
<%--{--%>
<%--	if(document.getElementById("def1").checked == false)--%>
<%--	{--%>
<%--		document.getElementById("def2").checked = false;--%>
<%--	}--%>
<%--}--%>
<%--</script>--%>
</body>
</html>

