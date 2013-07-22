<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>创建圈子</title>
    <link href="css/style.css" type="text/css" rel="stylesheet"/>
    <script src="js/alljs.js" type="text/javascript"></script>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.form.js" type="text/javascript"></script>
<%--    <script src="js/show_window.js" type="text/javascript"></script>--%>
	<script src="js/create.js" type="text/javascript"></script>
    
   	<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
	<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
</head>

<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script type="text/javascript">
    cssdropdown.startchrome("chromemenu");
var basePath = "<%=basePath%>";

    $(function(){
         $("#images").val("");
         $("#circlename").bind("blur", function(){
             if($.trim($(this).val()) == ""){
                 $(this).val("给你的圈子起个名字吧");
            	 $(this).css("color", "#CCCCCC");
             }else{
             	$(this).css("color", "#000");
             }
         }).bind("focus", function(){
             if($.trim($(this).val()) == "给你的圈子起个名字吧"){
                 $(this).val("");
             }
             $(this).css("color", "#000");
         });

        $("#circletag").bind("blur", function(){
            if($.trim($(this).val()) == ""){
                $(this).val("用关键词来描述一下你的圈子。多个关键词可用空格分开");
            	$(this).css("color", "#CCCCCC");
             }else{
             	$(this).css("color", "#000");
             }
        }).bind("focus", function(){
            if($.trim($(this).val()) == "用关键词来描述一下你的圈子。多个关键词可用空格分开"){
                $(this).val("");
            }
            $(this).css("color", "#000");
        });

        $("#upFile").bind("change", function(){
            var img =  $(this).val();
            if( !img.match( /.jpeg|.jpg|.gif|.png|.bmp/i ) ){
                alert('图片格式无效,请选择其他格式文件，支持.jpeg|.jpg|.gif|.png|.bmp');
                return;
            }
            $("#uploadFileForm").ajaxSubmit({
                url:"<%=basePath%>circleInfoAction_uploadFile.do",
                type: 'post',
                dataType: 'json',
                beforeSend:function(){
                	 $("#imglist").html("<li><img src='<%=basePath%>images/loading_32.gif'/></li>");
                },
                success:function(data){
                    if(data.flag){
                        $("#imglist").html("<li><img style='max-width:400px;' src='<%=basePath%>"+data.data+"'/></li>");
                        $("#images").val(data.data);
                    }else{
                    	alert(data.msg);
                    }
                },
                error:function(){
                    alert("图片上传失败，可考虑使用小一些的图片");
                }
            });
        });
        
        KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="instance.summary"]', {
				cssPath : '<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css',
				items : [
				    'source', '|', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
				    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				    'insertunorderedlist', '|', 'link']
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

    function formSubmit(){

        if($.trim($("#circlename").val()) == "" || $.trim($("#circlename").val()) == "给你的圈子起个名字吧"){
            alert("给你的圈子起个名字吧！");
            $("#circlename").focus();
            return;
        }
        if($.trim($("#circletag").val()) == "" || $.trim($("#circletag").val()) == "用关键词来描述一下你的圈子。多个关键词可用空格分开"){
            alert("添加点标签名吧！");
            $("#circletag").focus();
            return;
        }
        if($.trim($("#catgoryinput").val()) == "" || $.trim($("#catgoryinput").val()) == "点击标签加入分类使圈子更鲜明！"){
            alert("点击标签加入分类使圈子更鲜明！");
            $("#catgoryinput").focus();
            return;
        }
        if($.trim($("#summary").val()) == ""){
            alert("您还没填写简介，为了圈子更吸引人，请填写一些吧！");
            $("#summary").focus();
            return;
        }
        if($.trim($("#images").val()) == ""){
            alert("您还未上传圈子的形象照片，这样可以更加吸引人！");
            return;
        }
        
        $.ajax({
            url:"<%=basePath%>circleInfoAction_ajaxCreate.do",
            type: 'post',
            dataType: 'json',
            data : {
                "instance.circlename":$("#circlename").val(),
                "instance.circletag":$("#circletag").val(),
                "instance.circlebigimg":$("#images").val(),
                "instance.summary":$("#summary").val(),
                "instance.def1":$("#catgoryinput").val(),
                "instance.def3":$("#def3").val(),
            },
            beforeSend:function(){
            	$("#creat_submit").hide();
            	$("#loadding").show();
            },
            success:function(data){
            	$("#loadding").hide();
                alert(data.msg);
                var ccid = data.data.ccid;
                if(data.flag){
                    location.href=basePath+"circle/"+ccid+".html";
                }else{
                    $("#creat_submit").show();
                }
            },
            error:function(){
                alert("保存失败");
                $("#creat_submit").show();
            	$("#loadding").hide();
            }
        });
    }
    
    function tagit(inputid,def3){
		 var targetCatgoryinput = document.getElementById('catgoryinput');
		 targetCatgoryinput.value = inputid.innerHTML;
		 targetCatgoryinput.style.color='black';
		 $("#def3").val(def3);
<%--		 alert(inputid.innerHTML+"   "+$("#def3").val());--%>
  	}
    

</script>
<div class="main_cont">
    <div class="main">
        <div class="user_name">
            <ul>
                <li class="name1">创建圈子</li>
            </ul>
        </div>
        <div class="line1"></div>
        <div class="line2"></div>

        <div class="feedwall">
            <div class="details_cont_1">
                <div class="details-left">
                    <div class="create_value">
                        <h3>圈子名称</h3>
                        <input type="text" value="给你的圈子起个名字吧" id="circlename" name="instance.circlename" class="cterat-value1"/>
                    </div>
                    <div class="create_value">
                        <h3>圈子分类</h3>
                        <select id="cirtag" name="instance.def1">
                        	<c:forEach var="tag" items="${tag}" varStatus="t">
                        	<c:if test="${t.index > 0}">
                        	<option id="${tag.ttid }">${tag.tagname }</option>
                        	</c:if>
                        	</c:forEach>
                        </select>
<%--                        <input type="text" value="给你的圈子起个名字吧" id="circlename" name="instance.circlename" class="cterat-value1"/>--%>
                    </div>
                    <div class="create_value">
                        <h3>圈子关键词</h3>
                        <input type="text" value="用关键词来描述一下你的圈子。多个关键词可用空格分开" id="circletag" name="instance.circletag" class="cterat-value1" 
                        	/>
                    </div>
                    
                    <div class="create_value">
                        <h3>圈子标签</h3>
                        <input type="text" id="tag" name="" class="cterat-value1" 
                        	/>
                    </div>
                    
                     <div class="create_value">
                        <h3>圈子简介</h3>
                    </div>
                    <div class="creat-con">
                         <textarea id="summary" name="instance.summary" rows="8" class="creat_con1" style="overflow-y:auto; overflow:auto;visibility:hidden;"></textarea>
						<%--                         <div id="simple"><input type="hidden" name="instance.summary" id="summary"/></div>--%>
						<%--<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>--%>
                    </div>
                    
                    <div class="creat-photo">
                        <div class="creat-img">
                            <form id="uploadFileForm" enctype="multipart/form-data" method="post">
                                <input type="file" name="upFile" id="upFile" size="20" class="ifile"/>
                                <input class="fire_1" type="text" name="uploadFile" size="20" readonly />
                            </form>
                            <input id="images" type="hidden"/>
                        </div>
                        <div class="img_right"> 请添加一张能够表达你圈子特点的形象照片吧。不超过  2 MB (格式为JPG, GIF, PNG, BMP)</div>
                    </div>
					<div class="cteat-po-yin" style="">
                        <ul id="imglist">
                        </ul>
                    </div>

                    <div id="creat_submit" class="creat_submit">
                         <input id="formSubmit" onclick="formSubmit();return false;" type="image" src="images/btn_09.jpg"/>
                    </div>
                    <div id="loadding" class="creat_submit" style="display: none;">
                        <img src='<%=basePath%>images/loading_32.gif'/>正在创建圈子，请稍候......
                    </div>
                     

                </div>
            </div>
            <!--右边部分代码-->

            <div class="details-right">
		        <h3>圈子分类</h3>
		        <div class="details-center">
		         	<input id="catgoryinput" name="instance.def1" readonly="readonly" type="text" class="dl_title" value="点击标签加入分类使圈子更鲜明！"/>
		        </div>
		        <div class="details-xia">
	                <a href="javascript:void(0);" onclick="tagit(this,10)">休闲时光</a>
	                <a href="javascript:void(0);" onclick="tagit(this,10)">运动</a>
	                <a href="javascript:void(0);" onclick="tagit(this,12)">职场</a>
	
	                <a href="javascript:void(0);" onclick="tagit(this,2)">家是温暖的巢</a>
	                <a href="javascript:void(0);" onclick="tagit(this,10)">旅游</a>
	                <a href="javascript:void(0);" onclick="tagit(this,16)">一日不可无书</a>
	                <a href="javascript:void(0);" onclick="tagit(this,10)">时尚潮流</a>
	                <a href="javascript:void(0);" onclick="tagit(this,16)">音乐戏剧电影</a>
	
	                <a href="javascript:void(0);" onclick="tagit(this,16)">文学艺术</a>
	                <a href="javascript:void(0);" onclick="tagit(this,43)">爱车人</a>
	                <a href="javascript:void(0);" onclick="tagit(this,16)">城市</a>
	                <a href="javascript:void(0);" onclick="tagit(this,2)">美食</a>
	                
	
	                <a href="javascript:void(0);" onclick="tagit(this,2)">生活百科</a>
	                <a href="javascript:void(0);" onclick="tagit(this,10)">爱摄影</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,16)">故乡</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,10)">云游海外</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">童真童趣</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">校园逸事</a>
	
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">美女</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">爱情</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,10)">星座</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,10)">益智游戏</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">宠物</a>
	
	                 <a href="javascript:void(0);" onclick="tagit(this,12)">媒体</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">幽默笑话</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,12)">企业企业家</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,16)">国学</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,10)">凡人大事名人小事</a>
	
	                 <a href="javascript:void(0);" onclick="tagit(this,2)">亲子教育</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,16)">未知世界</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">网络时代</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">创意天地</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,10)">视频</a>
	                 <a href="javascript:void(0);" onclick="tagit(this,43)">其他</a>
	                 <input type="hidden" id="def3" name="instance.def3" />
	              </div>
    		</div>

        </div>
    </div>
    
</div>
    
    <!-----页尾部分---->
    <jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

</body>
</html>
