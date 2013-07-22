<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传个人头像</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="">
	<link href="<%=basePath%>css/css/zhucestyle.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>css/css/jquery.Jcrop.css" type="text/css" />
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/jquery.Jcrop.js" type="text/javascript"></script>
	
<%--	<script src="<%=basePath%>js/om-validate.js" type="text/javascript"></script>--%>
	<style type="text/css">

/* Apply these styles only when #preview-pane has
   been placed within the Jcrop widget */
.jcrop-holder #preview-pane {
  display: block;
  position: absolute;
  z-index: 2000;
  top: 10px;
  right: -100px;
  padding: 6px;
  border: 1px rgba(0,0,0,.4) solid;
  background-color: white;

  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;

  -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
  box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
}
/* The Javascript code will set the aspect ratio of the crop
   area based on the size of the thumbnail preview,
   specified here */
#preview-pane .preview-container {
  width: 60px;
  height: 60px;
  overflow: hidden;
}


.photo_big{width:350px; height:220px; overflow:hidden; margin-top:20px;}

</style>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var jcrop_api,
        boundx,
        boundy;
  jQuery(function($){
	var $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),
        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    
    // How easy is this??
    $('#target').Jcrop({
      onChange: updatePreview,
      onSelect: updatePreview,
      boxWidth:230,
	  boxHeight:230,
      dragEdges:true,
      handleSize:1,
      setSelect:[0,0,50,50],
      aspectRatio: xsize / ysize
    },function(){
      // Use the API to get the real image size
      var bounds = this.getBounds();
      boundx = bounds[0];
      boundy = bounds[1];
      // Store the API in the jcrop_api variable
      jcrop_api = this;

      // Move the preview into the jcrop container for css positioning
      $preview.appendTo(jcrop_api.ui.holder);
    });
	function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
        var rx = xsize / c.w;
        var ry = ysize / c.h;
		$('#width').val(c.w);  //c.w 裁剪区域的宽  
        $('#height').val(c.h); //c.h 裁剪区域的高  
        $('#x').val(c.x);  //c.x 裁剪区域左上角顶点相对于图片左上角顶点的x坐标  
        $('#y').val(c.y);  //c.y 裁剪区域顶点的y坐标</span>
        $pimg.css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };
  });
		
function cuthead()
{
	var cutpicURL = "userInfoAction_updatecuthead.do";
	var imgx = $("#x").val();
	var imgy = $("#y").val();
	var imgwidth = $("#width").val();
	var imgheight = $("#height").val();
	var imgpath = $("#imgpath").val();
	if(imgwidth<=0)
	{	
		alert("请剪切头像！");
		return false;
	}
	$("#waitsave").html("保存头像中...");
	$.ajax({
		url:cutpicURL,
		data:{imgx : imgx,imgy: imgy,imgwidth: imgwidth,imgheight: imgheight,imgpath: imgpath},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var saveUrl = data.data.saveUrl;
			$("#waitsave").html("<a href=\"javascript:void(0)\" onclick=\"cuthead()\"></a>");
			if(imgpath==saveUrl)
			{
				alert("修改失败,图片不符合规范，请换一张图片!");
			}else{
				alert("修改成功!");
				var userid = $("#userid").val();
				location.href = basePath+"usercenter_me/"+userid+".html";
			}
			
		},
		error:function(data){
			alert("网络异常!");
			$("#waitsave").html("<a href=\"javascript:void(0)\" onclick=\"cuthead()\"></a>");
		}
	});
}
		
		function uploadPic(){
			$("#form1").ajaxSubmit({
	        	url:"<%=basePath%>centerAction_uploadFile.do",
	         	type: 'post',
	         	dataType: 'json',
	         	beforeSend:function(){
	        	 	$("#target").attr("src","<%=basePath%>images/loading.gif");
	        	 	$("#smallimg").attr("src","<%=basePath%>images/loading.gif");
				},
				success : function(data) {
					if (data.flag) {
						$("#target").attr("src",data.data);
						$("#smallimg").attr("src",data.data);
						$("#userimg").attr("value",data.data);
					}
				},
				error : function() {
					alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
				}
			});
		}
		function saveUserImg(){
	var userid = $("#userid").val();
	var img = $("#userimg").val();
	var saveUserImgURL = "userInfoAction_saveUserImg.do";
	$.ajax({
		url : saveUserImgURL,
		data : {ref:img},
		type : 'POST',
		dataType : 'json',
		beforeSend : function(){
			$("#waitsave").html("正在保存头像，请稍等···");
		},
		success : function(data){
			$("#waitsave").html("");
			$("#waitsave").append("<a href=\"javascript:void(0);\" onclick=\"saveUserImg()\"></a>");
			if(data.flag){
				location.href = basePath+"usercenter_me/"+userid+".html";
			}
		},
		error : function(){
			$("#waitsave").append("<a href=\"javascript:void(0);\" onclick=\"saveUserImg()\"></a>");
		}
	});
}
		
	</script>

	</head>
  
  <body>
 		 <c:if test="${user == null}">
			<script type="text/javascript">
    			location.href="<%=basePath%>";
    		</script>
		</c:if>
  		<jsp:include page="../inc/header.jsp"></jsp:include>
		<div class="box_zhuce">
			<div class="inner_box">
				<div class="nav_zhuce">
					<ul>
						<li>
							<a href="/registerinfo.html">1 完善个人资料</a>
						</li>
						<li>
							<a href="/uploadphoto.html" style="background-color: #ff8a00; color: #fff; margin-left:10px;">2
								上传个人头像</a>
						</li>
					</ul>
				</div>
				<div class="tittle">
					<p>
						上传你的头像
					</p>
				</div>

				<div class="middle_photo">
					<div class="middle_photo_left">
						<p>
							（
							<span>温馨小贴士</span>：上传真实照片可以让你更迅速找到同好知己。）
						</p>
						<ul>
							<li class="photo_big">
								<img src="${user.userimage }" id="target" />
							</li>
							<li>

								<div id="preview-pane">
									<div class="preview-container">
										<img src="${user.userimage }" id="smallimg" class="jcrop-preview" width="60" height="60"/>
									</div>
								</div>

							</li>
						</ul>
					</div>
					<div class="middle_photo_right">
						<ul>
							<li>
								<p>
									照片尺寸大小在2M之内，支持jpg，bmp，jpeg等格式。
								</p>
							</li>
							<li>
								<jsp:include page="/setting/setting_upload.jsp"></jsp:include>
								<input type="hidden" id="userimg" value="${user.userimage }"  />
							</li>
						</ul>
					</div>
				</div>


				<div class="bottom_photo" id="waitsave">
					<a href="javascript:void(0);" onclick="cuthead()"></a>
				</div>
			</div>

		</div>
	<input type="hidden" id="imgpath" name="imgpath" value="${user.userimage }" />
    <input type="hidden" name="imgx" id="x"/>  
    <input type="hidden" name="imgy" id="y"/>  
    <input type="hidden" name="imgwidth" id="width"/>  
    <input type="hidden" name="imgheight" id="height"/>
	</body>
</html>
