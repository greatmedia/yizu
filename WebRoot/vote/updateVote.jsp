<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>" />
<title>修改投票</title>
<link href="<%=basePath %>css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/om-fileupload.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/voteall.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script src="js/om-core.js" type="text/javascript"></script>
<script src="js/getDate.js" type="text/javascript"></script>
<script src="js/om-fileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="js/om-validate.js"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
<script type="text/javascript" src="js/createVote.js"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script>
<style type="text/css">
	label.error {
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
</style>
<script type="text/javascript">
		$(function(){
		 $("#reg").validate({
            rules : {
                "myVoteInfo.title" : {
                	required : true
                },
                "myVoteInfo.summary" : {
                    required : true
                }
                ,
                "myVoteInfo.voteTag" : {
                    required : true
                },
                "myVoteInfo.price" : {
                    required : true
                },
                "myVoteInfo.pricess" : {
                    required : true
                },
                "endTime" : {
                    required : true
                },
                "startTime" :{
                 	required : true
                }
            },
            messages : {
                "myVoteInfo.title" : {
                    required : "标题必填"
                },
                "myVoteInfo.summary" : {
                    required : "简介必填"
                },
                "myVoteInfo.voteTag" : {
                    required : "标签必填"
                },
                 "myVoteInfo.price" : {
                    required : "最低价格必填"
                },
                "myVoteInfo.pricess" : {
                    required : "最高价格必填"  
                },
                 "endTime" : {
                    required : "结束时间必填"
                },
                 "startTime" :{
                 	required : "开始时间必填"
                }
            },
            submitHandler : function(){
            	if($("#uploadImages").find("img").length == 0 ){
            		alert("你还没有上传图片,马上上传图片吧！");
            	}else{
            		
            		$("#uploadImages").find("img").each(function(index, obj){
            			if(index != 0){
            				$("#images").val($("#images").val() + ",");
            			}
            			$("#images").val($("#images").val() + $(obj).attr("src"));
            		});
            		
            		
            		$("#reg").ajaxSubmit({
		                url:"myVoteInfoAction_ajaxUpdateVote.do",
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
									location.href="<%=basePath%>/votes/<%=request.getParameter("id") %>";
			                    	
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
            	}
                return false;
            }
        });
		 KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="myVoteInfo.summary"]', {
				cssPath : '<%=basePath%>kindeditor-4.1.1/plugins/code/prettify.css',
				items : [
				    'source', '|', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
				    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				    'insertunorderedlist','|', 'link']
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
	</script>
</head>

<body>
		<div class="main_cont">
			<div class="main" id="min">

				<div class="line1"></div>
				<div class="line2"></div>

				<div class="feedwall">
					<div class="details_cont_1">
						<div class="details-left">
							<!-- 标题部分 -->
							<form id="reg" name="reg" action="" method="post">
								<div class="create_value">
									<h3>
										投票产品名称
									</h3>
									<div class="creat-con" style="height: 30px;">
										<input name="myVoteInfo.voteId" id="voteId" type="hidden"
											class="m_input" value="${id }" />
										<input name="myVoteInfo.title" id="title" type="text"
											class="cterat-value1" value="${myVoteInfo.title }" />
									</div>
								</div>
								<div class="create_value">
									<!--结束时间 -->
								<ul class="create_value_ul">	
									<li>
										开始时间
									</li>
									<li style="padding-left:10px; width:320px;">
										结束时间
									</li>
								</ul>	
									<div class="creat-con" style="height: 30px;">
										<input style="width:298px;" name="startTime" id="startTime" type="text"
											class="cterat-value1" onfocus="HS_setDate(this)"
											readonly="readonly" value="${myVoteInfo.starttime }" />&nbsp;-&nbsp;
										<input style="width:298px;" name="endTime" id="endTime" type="text"
											class="cterat-value1" onfocus="HS_setDate(this)"
											readonly="readonly" value="${myVoteInfo.endtime }" />
									</div>
								</div>
								<div class="create_value">
									<!--价格范围 -->
								<ul class="create_value_ul">	
									<li>
									             最低价格
									</li>
									<li style="padding-left:10px; width:320px;">
										  最高价格
									</li>
								</ul>	
									<div class="creat-con" style="height: 30px;">
										<input style="width:298px;" name="myVoteInfo.price" id="price" type="text"
											class="cterat-value1"  onkeyup="this.value=this.value.replace(/[^\d\.\d\d]/g,'')" 
											onafterpaste="this.value=this.value.replace(/[^\d\.\d\d]/g,'')" value="${myVoteInfo.price }" />&nbsp;-&nbsp;
										<input style="width:298px;" name="myVoteInfo.pricess" id="pricess" type="text"
											class="cterat-value1"  onkeyup="this.value=this.value.replace(/[^\d\.\d\d]/g,'')" 
											onafterpaste="this.value=this.value.replace(/[^\d\.\d\d]/g,'')" value="${myVoteInfo.pricess }" />
									</div>
								</div>
								<div class="create_value">
									<h3>
										 产品介绍
									</h3>

									<div class="creat-con">
										<textarea name="myVoteInfo.summary" id="circlecontent"
											cols="79"
											style="width: 650px; height: 200px; visibility: hidden;">${myVoteInfo.summary }</textarea>
									</div>
								</div>
								
									<div class="creat-photo" style="height: 50px;"></div>

									<!--图片部分 -->

									<div class="creat-photo">
										<div class="creat-img">
											<input type="file" id="file_upload" name="file_upload" />
											<input type="hidden" id="images" name="images" />
										</div>
										<div class="img_right">
											<font color="red">*</font>JPG、GIG、PNG或BMP，不超过20MB，一次可上传多张。
										</div>
									</div>

									<div class="cteat-post-yin" id="yin">
										<ul id="uploadImages">
											<c:if test="${myVoteInfo.myVoteInfoImage != null}">
											<c:forEach var="img" items="${myVoteInfo.myVoteInfoImage}" varStatus="s">
												 <li><img src='<%=basePath %>images/close_ico.gif' style="color: blue;position: absolute;" onclick="ajaxDeleteOldFile('${img.imageId }', this);"/><img src="${img.imgAddress }"/></li>
											</c:forEach>
										</c:if>
										</ul>
									</div>

									<div id="creat_submit" class="creat_submit">
										<img src="images/btn_11.jpg" onclick="history.back();" />
										<input id="formSubmit" type="image" src="images/btn_09.jpg" />
									</div>
									<div id="loadding" class="creat_submit" style="display: none;">
										<img src='<%=basePath%>images/loading_32.gif' />
										正在保存投票内容，请稍候......
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

