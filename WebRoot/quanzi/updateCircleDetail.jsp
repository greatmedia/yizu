<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>逸族网-修改圈子内容</title>
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<link href="css/om-fileupload.css" type="text/css" rel="stylesheet" />
<%--		<script src="js/alljs.js" type="text/javascript"></script>--%>
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/om-core.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/om-fileupload.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=basePath%>js/om-validate.js"></script>
		
		<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
		<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
		<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
		<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
		<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
		<%--	<img src='<%=basePath %>images/close_ico.gif' style='color: blue;position: absolute;' onclick='ajaxDeleteCurrentFile(\""+jsonData.fileUrl+"\", this);'/>	--%>
		<script type="text/javascript">
			var sizeCount = 0;
			$(document).ready(function() {
				sizeCount = 0;
	            $('#file_upload').omFileUpload({
	            	swf : '<%=basePath%>js/om-fileupload.swf',
	                action : '<%=basePath%>circleInfoAction_swfUpload.do',
	                buttonImg : '<%=basePath%>images/cjqz_02.jpg',
	                multi : true,
	                autoUpload : true,
	                queueSizeLimit : 10,
	                fileExt : '*.jpg;*.png;*.gif;*.jpeg',
	                fileDesc : "选择图片 *.jpg;*.png;*.gif;*.jpeg",
	                sizeLimit : 1024*1024*20,
	                onSelect : function(ID, fileObj, event){
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
			            
<%--			            if($("#images").val() != ""){--%>
<%--               				$("#images").val($("#images").val() + ",");--%>
<%--               			}--%>
<%--               			$("#images").val($("#images").val() + jsonData.fileUrl);--%>
						$("#images").val("");
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
	                    }
	                },
	                messages : {
	                    "circleDetail.title" : {
	                        required : "标题必填"
	                    },
	                    "circleDetail.circlecontent" : {
	                        required : "内容必填"
	                    },
	                    "circleDetail.ctag" : {
	                        required : "关键字必填"
	                    }
	                },
	                submitHandler : function(){
	                	 	var sel=document.getElementById("topictype");
			                var val=sel.options[sel.selectedIndex].value;  
			              	//1 普通圈子被容  2话题   3 PK huati
			                if(val == 2 || val ==3){
			                	if($("#uploadImages").find("img").length <= 0 ){
		                			alert("请至少上传一图片");
		                			return false;
		                		}
					        }
<%--			                if($("#uploadImages").find("img").length > 0 ){--%>
<%--		                		$("#uploadImages").find("img").each(function(index, obj){--%>
<%--		                			if(index != 0){--%>
<%--		                				$("#images").val($("#images").val() + ",");--%>
<%--		                			}--%>
<%--		                			$("#images").val($("#images").val() + $(obj).attr("src"));--%>
<%--		                		});--%>
<%--		                	}--%>
                		$("#reg").ajaxSubmit({
			                url:"<%=basePath%>circleInfoAction_ajaxUpdateDetail.do",
			                data:{topictype:val},
			                type: 'post',
			                dataType: 'json',
			                beforeSend:function(){
				            	$("#creat_submit").hide();
				            	$("#loadding").show();
			                },
			                success:function(data){
			                	$("#loadding").hide();
			                	alert(data.msg);
			                    if(data.flag){
			                   		document.getElementById("reg").reset();
										if(val == 2 || val ==3)
				                   		{
											location.href="<%=basePath%>quanzi/newTopics.jsp";
				                   		}else{
					                   		var circleid = $("#circleid").val();
				                    		location.href="<%=basePath%>cir2/1/"+circleid+".html";
				                   		}
			                    }else{
				                    $("#creat_submit").show();
				                }
			                },
			                error:function(){
			                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
			                    $("#creat_submit").show();
           						$("#loadding").hide();
			                }
			            });
	                    return false;
	                }
	            });
	            
	            KindEditor.ready(function(K) {
					var editor1 = K.create('textarea[name="circleDetail.circlecontent"]', {
						cssPath : '<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css',
						items : [
						    'undo', 'redo','source', '|', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
						    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						    'insertunorderedlist', '|', 'link','image','emoticons','preview']
						,
						allowFileManager : false,
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
					prettyPrint();
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
		            		$(obj).parent().remove();
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
	        
	        function ajaxDeleteOldFile(id, obj){
	        	$.ajax({
		            url:"<%=basePath%>circleInfoAction_ajaxDeleteOldFile.do",
		            type: 'post',
		            dataType: 'json',
		            data : {
		                "id":id
		            },
		            beforeSend:function(){
		            	$(obj).attr("src","<%=basePath%>images/loading_32.gif");
		            },
		            success:function(data){
		            	if(data.flag){
		            		$(obj).parent().remove();
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
            	height: 250px;
            }
            .ke-edit{
            	height: 250px;
            }
            .ke-edit-iframe{
            	height: 250px;
            }
    	</style>
	</head>

<body>
	<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
	<script src="js/om-fileupload.js" type="text/javascript"></script>
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
								<input name="circleDetail.circleid" id="circleid" type="hidden" value="${circleDetail.circleid }"/>
								<input name="circleDetail.circledetailid" id="circledetailid" type="hidden" value="${circleDetail.circledetailid }"/>
								<input type="text" class="ui-text skin-text-willwhite" id="title" name="circleDetail.title"  style="overflow: auto;  height: 38px; width:520px;" value="${circleDetail.title }"/>
							</div>
						</li>
						<li>
							<div class="item_title">关键字：</div>
							<div class="item_tjnr">
								<input type="text" class="ui-text skin-text-willwhite" id="ctag" name="circleDetail.ctag"  style="overflow: auto;  height: 38px; width:520px;" value="${circleDetail.ctag }"/>
							</div>
						</li>
						<li>
							<div class="item_title">内容：</div>
							<div class="item_tjnr">
								<textarea class="ui-textarea skin-textarea-willwhite" style="width: 520px;height: 450px;" name="circleDetail.circlecontent" id="circlecontent" >${circleDetail.circlecontent }</textarea>
							</div>
						</li>
						<li>
							<div class="item_title">类型：</div>
							<div class="item_tjnr">
								<select class="ui-text skin-text-willwhite" style="height: 38px; overflow: auto; width:162px;" id="topictype">
										<c:if test="${circleDetail.def1 == 1 && circleDetail.def2 ==1}">
											<option value="3" selected="selected">pk话题</option>
											<option value="1">圈子</option><option value="2">话题</option>
										</c:if>
										<c:if test="${circleDetail.def1 == 1 && circleDetail.def2 == null}">
											<option value="2" selected="selected">话题</option>
											<option value="1">圈子</option><option value="3">pk话题</option>
										</c:if>
										<c:if test="${circleDetail.def1 == null && circleDetail.def2 == null}">
											<option value="1" selected="selected">圈子</option>
											<option value="2">话题</option><option value="3">pk话题</option>
										</c:if>
								</select>
							</div>
						</li>
						<li>
							<div class="item_title">标签：</div>
							<div class="item_tjnr">
								<select class="ui-text skin-text-willwhite" style="height: 38px; overflow: auto; width:162px;" id="circletag" name="circleDetail.def4">
									<c:forEach var="circletag" items="${circletagrelateList}" varStatus="c">
										<c:choose>
											<c:when test="${circletag.ctid == circleDetail.def4 }">
												<option value="${circletag.ctid}" selected="selected">${circletag.cirtag.tagname}</option>
											</c:when>
											<c:otherwise>
												<option value="${circletag.ctid}">${circletag.cirtag.tagname}</option>
											</c:otherwise>
										</c:choose>
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
									<%--<img src='<%=basePath %>images/close_ico.gif' style="color: blue;position: absolute;" onclick="ajaxDeleteOldFile('${img.cdid }', this);"/>--%>
									<c:if test="${circleDetail.circleDetailImg != null}">
										<c:forEach var="img" items="${circleDetail.circleDetailImg}" varStatus="s">
											<c:if test="${img.middleimg != null}">
											 	<img class="avatar" src="${img.middleimg }"/>
											</c:if>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</li>
						<li>
							<div class="panel_tjnr">
							<input id="formSubmit" type="image" src="../images/fabu.gif" />
							<a href="javascript:void(0);"><img src="images/tjnr_01.jpg" onclick="history.back()"/></a>
							</div> 
							<div id="loadding" class="creat_submit" style="display: none;">
			                      <img src='<%=basePath%>images/loading_32.gif'/>正在保存圈子内容，请稍候......
			                </div>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
</div>
		<!-----页尾部分---->
		<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

	</body>
</html>
