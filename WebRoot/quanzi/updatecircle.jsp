<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改圈子</title>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script src="js/om-core.js" type="text/javascript"></script>

<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/updatecircle.js" type="text/javascript"></script>
<link href="css/style2.css" type="text/css" rel="stylesheet" />
<link href="css/om-fileupload.css" type="text/css" rel="stylesheet" />
<style type="text/css">
.browseBox{position:relative; top:-240px;left:232px;z-index: 10000;width:320px;}
.browseBox .funBox{position:relative;width:70px;overflow:hidden}
.browseBox .openFile{position:absolute;right:0;width:100px;height:24px;filter:alpha(opacity:0);opacity:0}
.talkinGroup .btnHasStr{ background-position:-52px -210px; width:99px; height:30px;}
.talkinGroup .btnHasStr.hover,.talkinGroup .btnHasStr:hover{background-position:-151px -210px;}
.userName .ico_admin,.userName .ico_master,.userName .ico_user{ display:inline-block; width:16px; height:16px; overflow:hidden;}
.tbList .inputArea{ width:290px;}
.microGroupTags .selectTag{ width:270px;border: 1px solid #7F9DB9; height:auto; padding:0; clear:both;*zoom:1}
.microGroupTags ul li{ float:left; background:#EEE; margin:7px 2px; height:20px; line-height:20px; display:inline; vertical-align:middle; padding:0 3px;}
.microGroupTags ul li .delete{ font-size:14px; margin-left:3px;cursor:pointer; font-weight:400;}
.microGroupTags ul li .delete:hover{ color:#C50401}
.microGroupTags ul li a{ text-decoration:none; cursor:default;white-space:nowrap;}
.microGroupTags ul li .newtags{ border:0; font-size:12px; width:36px;height:12px; padding:4px 0 2px; line-height:12px;margin-top:1px; margin-top:1px\9;  *margin-top:0; outline:none; background:none;}
.microGroupTags ul .clear{ clear:both; line-height:0; font-size:0;}
.microGroupTags ul li.newtagsinput{width:96px; overflow:hidden; background:none}
.microGroupTags .recomTag{ clear:both;height:24px; line-height:24px; line-height:28px\0;}
.microGroupTags .recomTag a{ background:#D9ECEF; color:#52878F; margin:0 2px; padding:2px 5px;+padding:4px 5px 2px;_padding:2px 5px;}
.microGroupTags a.disabled {background: none repeat scroll 0 0 #E9EAEA;color: #999999;}
.newsTips2 {margin: 10px auto 0; padding: 5px 0;color: #999;}
.mg_type label{ cursor:pointer;}
.hiddenselecttags,.hiddenselecttags b.pass{	height:0;line-height:0;font-size:0;}
.wqSets .cNote{zoom:1;}
</style>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script src="js/om-fileupload.js" type="text/javascript"></script>
<div class="main_cont">
  <div class="cjqz">

<div class="cjqz_left">
<div class="cjqz_left_head"><p>创建圈子</p></div>
<div class="cjqz_left_middle">
<ul>
<form>
<li>
<div class="item_title">圈子名称：</div>
<div class="item">
<input type="text" id="text2" value="${instance.circlename }" class="ui-text skin-text-willwhite" style="overflow: auto; width: 420px;" />
<script>
var basePath = "<%=basePath%>";
var el = document.getElementById("text2");
if (el.value == "")
  el.value = "给你的圈子取个名字吧";
   
el.onfocus = function() {
  if (this.value == "给你的圈子取个名字吧")
    this.value = "";
};
el.onblur = function() {
  if (this.value == "")
    this.value = "给你的圈子取个名字吧";
}
</script>
</div>
</li>
<li>
<div class="item_title">关键字：</div>
<div class="item">
<input type="text" id="text1" class="ui-text skin-text-willwhite" value="${instance.circletag }" style="overflow: auto; width: 420px;" />
<div id="search_suggest" style="left: 44px; width: 155px; position: absolute; top: 86px; height: 100px; z-index:1;"></div> 
<script>
var el = document.getElementById("text1");
if (el.value == "")
  el.value = "每个关键字用空格隔开";
   
el.onfocus = function() {
  if (this.value == "每个关键字用空格隔开")
    this.value = "";
};
el.onblur = function() {
  if (this.value == "")
    this.value = "每个关键字用空格隔开";
}
</script>
</div>
</li>
<li>
<div class="item_title">圈子简介：</div>
<div class="item">
<textarea id="summary" class="ui-textarea skin-textarea-willwhite" style="height: 92px; overflow: auto; width: 420px;">${instance.summary }</textarea>
</div>
</li>
<li>
<div class="item_title">圈子分类：</div>
<div class="item">
<select id="cirtag" class="ui-text skin-text-willwhite" style="height: 38px; overflow: auto; width:162px;">
	<c:choose>
		<c:when test="${instance.def3 == 0}">
			<option id="0" selected="selected">推荐圈子</option>
		</c:when>
		<c:otherwise>
			<c:forEach var="tag" items="${tag}" varStatus="t">
				<c:choose>
					<c:when test="${tag.tagname==instance.def1 }">
						<option id="${tag.ttid }" value="${tag.tagname }" selected="selected">${tag.tagname }</option>
					</c:when>
					<c:otherwise>
						<option id="${tag.ttid }" value="${tag.tagname }">${tag.tagname }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:otherwise>
  	</c:choose>
</select>
</div>
</li>
<li>
<div class="item_title">上传图片：</div>
<div class="item">
<form id="reg" name="reg" action="" method="post">
  <input type="file" id="file_upload" name="file_upload" />
<input type="hidden" id="images" name="images" />
</form>
<p style="font-size:12px; line-height:50px;">JPG, GIF, PNG或BMP，图片大小不超过2M</p>
<input type="hidden" id="imgvalue" />
<div id="divimgs">
<img src="${instance.circlebigimg }" width="146px" class="avatar" />
</div>
<%--<img src="images/img_01.jpg" class="avatar" />--%>
<div id="imgs"></div>
<input id="circleimg" type="hidden" />
</div>
</li>
<li>
<div class="panel">
<div id="wait" style="display: none;">正在保存..</div>
<a id="setting" href="javascript:void(0);"><input type="button" value="保存" onclick="updatequanzi()" style="width: 70px;height: 35px; font-size: 20px" /></a>
</div> 
</li>
</form>
</ul>
</div>
</div>


<div class="cjqz_right">
<div class="cjqz_right_head"><p>圈子标签</p></div>
<div class="cjqz_right_middle">
<div class="microGroupTags">
 <ul class="cjqz_right_middle_link" id="tagsli" style="float:left;width: 265px;">
 	
 	<c:forEach var="tag" items="${circletagrelateList}" varStatus="t">
		<li onclick="removeli(this)"><a href="javascript:void(0);" id="${tag.cirtag.ctid }">${tag.cirtag.tagname }</a><em class="delete">×</em></li>
    </c:forEach>
    <li class="newtagsinput" style="width:120px; background:none;">
    <c:choose>
    	<c:when test="${fn:length(circletagrelateList) < 4 }">
    		<input type="text" onkeyup="searchTag()" id="newTagsInput" class="newtags" style="width:120px; background:none;" autocomplete="off" value="" />
    	</c:when>
    	<c:otherwise>
    		<input type="hidden" onkeyup="searchTag()" id="newTagsInput" class="newtags" style="width:120px; background:none;" autocomplete="off" value="" />
    	</c:otherwise>
    </c:choose>
    </li>
 </ul>
</div>
<ul id="searchTag" onmouseout="onmouseoutUL()" onmouseover="onmouseoverUL()" style="display: none; width: 278px;"></ul>
<div class="cjqz_right_middle_biaoqian">
<div style="background-color: red;"></div>
<input type="hidden" id="def1" />
<input type="hidden" id="def3" />
<input type="hidden" id="circleid" value="${instance.circleid }" />
<input type="hidden" id="imageaddress" value="${instance.circlebigimg }" />
</div>
</div>
</div>
		<div class="cjqz_right">
			<div class="cjqz_right_head">
				<p>相关圈子推荐</p>
			</div>
			<div class="cjqz_right_middle">
				<div class="cjqz_right_middle_biaoqian" id="recommendId">
					<c:choose>
						<c:when test="${fn:length(circlerecommend) < 1}">
							<c:forEach begin="0" end="3" varStatus="i">
								<h5>第<span>${i.index+1}</span>个</h5>
								<select id="recommendId${i.index }">
									<c:forEach var="circle" items="${circleInfoList}">
										<option value="${circle.circleid}">${circle.circlename }</option>
									</c:forEach>
								</select><br />
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="recommend" items="${circlerecommend}" varStatus="r">
								<h5>第<span>${r.index+1}</span>个</h5>
								<select>
									<c:forEach var="circle" items="${circleInfoList}">
										<c:choose>
											<c:when test="${recommend.selectcircleinfo.circleid == circle.circleid}">
												<option value="${recommend.selectcircleinfo.circleid}" selected="selected">${recommend.selectcircleinfo.circlename }</option>
											</c:when>
											<c:otherwise>
												<option value="${circle.circleid}">${circle.circlename }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

</body>
</html>
