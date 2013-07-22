<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改头像</title>
<link href="css/user.css" type="text/css" rel="stylesheet" />
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.Jcrop.js"></script>
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
	$("#uploadimg").html("保存头像中...");
	$.ajax({
		url:cutpicURL,
		data:{imgx : imgx,imgy: imgy,imgwidth: imgwidth,imgheight: imgheight,imgpath: imgpath},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var saveUrl = data.data.saveUrl;
			var loc = location.href;
			if(imgpath==saveUrl)
			{
				alert("修改失败,图片不符合规范，请换一张图片!");
			}else{
				alert("修改成功!");
			}
			window.location.href = loc;
			$("#uploadimg").html("<a href=\"javascript:void(0)\" onclick=\"cuthead()\"></a>");
		},
		error:function(data){
			alert("网络异常!");
			$("#uploadimg").html("<a href=\"javascript:void(0)\" onclick=\"cuthead()\"></a>");
		}
	});
}

</script>
<link rel="stylesheet" href="../css/jquery.Jcrop.css" type="text/css" />
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="submit_middle">
<div class="submit_middle_left">
<!--<ul>-->
<!--                    <li><a href="###">Ta的发言</a></li>-->
<!--                    <li><a href="###">Ta的创建投票</a></li>-->
<!--                    <li><a href="###">Ta加入的圈子</a></li>-->
<!--                    <li><a href="###">Ta创建的圈子</a></li>-->
<!--                </ul>-->
                 <ul class="submit_middle_left_ul2">
                    <li><a href="userInfoAction_updatedata.do">修改资料</a></li>
                    <li><a href="userInfoAction_updatehobby.do">兴趣爱好</a></li>
                    <li><a href="userInfoAction_updatehead.do">修改头像</a></li>
                    
                </ul>
</div>
<div class="submit_middle_right">
<div class="tittle_submit">
      <p>修改你的个人头像</p>
    </div>


<div class="submit_middle_right1" style="border-bottom:none;">
	
	<div class="middle_photo">
    <div class="middle_photo_left">
    <p>（<span>温馨小贴士</span>：上传真实照片可以让你更迅速找到同好知己。）</p>
    <jsp:include page="/newuser/upload.jsp"></jsp:include>
    <ul class="photo_big">
    	<li>
    		<img src="${user.userimage }"  id="target" style="float:left;"  />
    	</li>
    	<li>
	    	<div id="preview-pane">
		    	<div class="preview-container">
		      		<img id="imgsm" src="${user.userimage }" style="width:60px; height:60px;" class="jcrop-preview" />
		    	</div>
	  		</div>
    	</li>
    </ul>
    
<!--    <div id="preview-pane">-->
<!--    <div class="preview-container" style="padding-top:0px;">-->
<!--    	<img id="imgsm" src="${user.userimage }" alt="Preview" class="jcrop-preview"  />-->
<!--    </div>-->
<!--     </div>-->
	<div style="clear:both;"></div>
     <div class="bottom_photo" id="uploadimg"><a href="javascript:void(0)" onclick="cuthead()"></a></div> 
    <input type="hidden" id="imgpath" name="imgpath" value="${user.userimage }" />
    <input type="hidden" name="imgx" id="x"/>  
    <input type="hidden" name="imgy" id="y"/>  
    <input type="hidden" name="imgwidth" id="width"/>  
    <input type="hidden" name="imgheight" id="height"/>
    </div>

</div>
</div>
</body>
