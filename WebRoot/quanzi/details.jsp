<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${circleleft.circlename }-${circleDetailInfo.title }</title>
		<meta name="description"
			content="${circleleft.circlename}-${circleDetailInfo.title } -话题" />
		<meta name="Keywords"
			content="${circleleft.circlename}-${circleDetailInfo.ctag }" />

		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<script src="js/alljs.js" type="text/javascript"></script>
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="js/public_min.js" type="text/javascript"></script>
		<script src="js/scroll.js" type="text/javascript"></script>
		<script src="js/detail.js" type="text/javascript"></script>
		<script> var basePath = "<%=basePath%>"; </script>
	</head>
	<body style="font-family:'宋体'; font-size:12px; color:#626262; background:url(../images/bg.jpg) repeat;">
		<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
		<div class="main_cont">
			<div class="circle3_middle2">
				<div class="circle3_middle2_left_out">
					<div class="circle3_middle2_left">
						<div class="circle3_middle2_left_wenzi">
							<input type="hidden" id="islogin" value="${islogin}"/>
							<p style="text-indent: 0;">
								<strong>${circleDetailInfo.title }</strong>
							</p>
							<p style="text-indent: 0; line-height: 50px;">
								<a href="<%=basePath%>user/${circleDetailInfo.userinfo.userid}.html">${circleDetailInfo.userinfo.nick}</a>发表于  <fmt:formatDate value="${circleDetailInfo.updatedatetime }"  type="both" />
							</p>
							<br />
							<p>
								${circleDetailInfo.circlecontent }
							</p>
							<c:forEach var="detailimg" items="${circleDetailInfo.circleDetailImg}" >
								<img src="${ detailimg.bigimg }"/>
							</c:forEach>
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
						</div>
						<div class="clear"></div>
						<div class="circlr_shuru0">
							<div class="circlr_shuru">
								<dl>
									<Dt>
										<div class="circlr_item_shuru">
											<textarea id="commentValue" style="width: 595px; height: 30px; display: inline; float: left; overflow: auto; line-height: 30px; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px; outline: none; font-size: 14px; overflow: auto; color: #666; background: #f7f8f8; border: 1px solid #C9CACC; border-bottom: 1px solid #B1B3B5; box-shadow: 0 1px 1px #C9CACC;"></textarea>
										</div>
									</Dt>
									<dd>
										<a href="javascript:subjectContent('${circleDetailInfo.circledetailid}','${circleDetailInfo.userid}');">评论</a>
									</dd>
								</dl>
								<label id="msg"></label>
							</div>
						</div>
					</div>
					<div class="circle3_middle2_left2" id="detailComment">
					</div>
					
					<div class="circle3_middle2_left3" id="morecomment" style="display:none;">
					</div>

				</div>
				<div class="circle3_middle2_right">
					<h4>
						推荐阅读
					</h4>
					<ul>
						<c:forEach var="recommend" items="${circleDetailInfos}">
							<li>
								<a style="color:#626262;" href="<%=basePath%>det/${recommend.circleid}/${recommend.circledetailid}.html">
								<c:choose>
		                    		<c:when test="${fn:length(recommend.title) > 14}">
		                    			${fn:substring(recommend.title, 0, 14)}...
		                    		</c:when>
		                    		<c:otherwise> 
		     							${recommend.title }
		    						</c:otherwise>
		                		</c:choose>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<script>showComment(10,1,1,'${circleDetailInfo.circledetailid }')</script>
	</body>

</html>

