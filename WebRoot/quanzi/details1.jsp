<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${circleleft.circlename }-${circleDetailInfo.title }</title>
<meta name="description" content="${circleleft.circlename}-${circleDetailInfo.title } -话题"/>
<meta name="Keywords" content="${circleleft.circlename}-${circleDetailInfo.ctag }"/>

<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/detail.js" type="text/javascript"></script>
<script>var basePath = "<%=basePath%>"; </script>
				
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="main_cont">
<label style="display:none" id="jr_tuichu"></label>
<div class="main" >
   <div class="user_name" style="display:none;">
     <ul>
            <li class="name1" id="circleName">${circleleft.circlename }</li>
        	<li class="name1"><span id="quanzi_title"></span></li>
        	<c:if test="${user != null}">
			<c:choose>
				<c:when test="${isAddCircle>0}">
					<li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.open('<%=basePath%>circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>')" style="display: block;"/></li>
            		<li class="tcqz"><img src="images/btn_01.gif" id="quit" onclick="join_or_quit_quanzi('<%=request.getParameter("id") %>',2)" style="display: block;"/></li>
				</c:when>
        	</c:choose>
        	</c:if>
        </ul>
    </div>
    <div class="line2"></div>
    
	<div class="feedwall">
    	<!---第一列数据----->
      <jsp:include flush="false" page="circle_left.jsp"></jsp:include>
      <div class="details_cont" id="details_cont">
      	<div class="d_title">${circleDetailInfo.title }</div>
		<div class="d_chuchu"></div>
			<div class="clearfloat"></div>
			<div class="d_txt">${circleDetailInfo.circlecontent }</div>
			<div class="d_img" id='detailImgs'>
				<c:forEach var="detailimg" items="${circleDetailInfo.circleDetailImg}" >
					<div><img src="${ detailimg.bigimg }"/></div>
				</c:forEach>
			</div>
			<div class="clearfloat"><label style="display:none;" id="commentsuccess" ></label></div>
			<div class="enjoy" >
				<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0&amp;btn=r5.gif&amp;uid=1338348908887337" charset="utf-8"></script>
					  <script type="text/javascript" >
							var jiathis_config={
								data_track_clickback:false,
								appkey:{
									"tsina":"2614308697",
									"tqq":"100255656",
									"t163":"mBjMmCCPvbKOvvQI",
									"tpeople":"184905"
								}	
							}
					  </script>
<%--				<a href="javascript:void(0);"><font color="#e67300" size="+1" id='commentCount'></font>条评论</a>--%>
<%--				<div class="d_fenxiang">--%>
<%--					<div id="ckepop">--%>
<%--						<span id="broadreceiver" class="jiathis_txt">分享到：</span> --%>
<%--							<div id="ckepop">--%>
<%--								<a class="jiathis_button_qzone"></a> --%>
<%--								<a class="jiathis_button_tsina"></a>--%>
<%--								<a class="jiathis_button_tqq"></a>--%>
<%--								<a class="jiathis_button_renren"></a>--%>
<%--								<a class="jiathis_button_kaixin001"></a>--%>
<%--								<a class="jiathis_button_t163"></a>--%>
<%--								<a href="http://www.jiathis.com/share?uid=1639347" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>--%>
<%--							</div>--%>
<%--						<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1338348908887337" charset="utf-8"></script>--%>
<%--						<script type="text/javascript" >--%>
<%--							var jiathis_config={--%>
<%--								data_track_clickback:false,--%>
<%--								appkey:{--%>
<%--									"tsina":"2614308697",--%>
<%--									"tqq":"100255656",--%>
<%--									"t163":"mBjMmCCPvbKOvvQI",--%>
<%--									"tpeople":"184905"--%>
<%--								}--%>
<%--							}--%>
<%--						</script>--%>
<%--						</div>--%>
<%--					</div>--%>
				<div class="d_pl_cont"> 
					<div class="pl_top"></div>
					<div class="pl_cont">
						<div><input type="text" class="pl_input" id="commentValue" value=""/></div>
						<ul class="d_pl">
							<li class="lp_bq"></li>
							<li class="pl_btn"><a href='javascript:void(0);'><img src="images/sub_pingl.gif" onclick="subjectContent('${circleDetailInfo.circledetailid}','${circleDetailInfo.userid}');" /></a></li>
							<li id="msg"></li>
						</ul>
						<label id='detailComment'></label>
					</div>
				</div>
			</div>
      </div>
    </div>
</div>
</div>
<script>showComment(10,1,1)</script>
</body>

</html>

