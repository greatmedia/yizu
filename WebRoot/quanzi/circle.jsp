<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  	<head>
	    <base href="<%=basePath%>">
	    
	    <title>${circleleft.circlename}</title>
	    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="${circleleft.circletag}">
		<meta http-equiv="description" content="${circleleft.circlename} -圈子">
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<link href="css/newindex.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
		<script src="js/circle.js" type="text/javascript"></script>
		<script type="text/javascript">var basePath = "<%=basePath%>";</script>
  	</head>
  
  	<body  style="background: url('../images/bg.jpg') repeat scroll 0 0 transparent;color: #626262;font-family: '宋体';font-size: 12px;">
    	<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  		<jsp:include flush="false" page="circletop.jsp"></jsp:include>	
			<div class="circle_middle2">
				<div class="circle_middle2_left">
					<div class="circle_middle2_top">
				      	<div class="circle_middle2_top_taolun">
				        	<ul>
				        		<c:choose>
									<c:when test="${user.userid == circleleft.userid}">
				          				<c:choose>
											<c:when test="${talkabout != null}">
												<li>
	            									<h1>热门话题讨论：</h1>
	            									<h2><input value="${talkabout.talktitle }" class="ui-text skin-text-willwhite" 
													  style="overflow: auto; width: 380px; float:left; margin-right:10px;" type="text" id="circletalkabout"/>
													  <a href="javascript:void(0);" style="float:left; color:#fff;" onclick="changetalkabout('${circleleft.circleid}')">更换</a>
														<input type="hidden" id="talkabout_id" value="${talkabout.id }"/>
														<a style="margin-right:0px;" href="circletalk/${circleleft.circleid}.html">更多</a>
													</h2>
												</li>
												<div class="clear"></div>
          										<li><span><a href="user/${circleleft.userinfo.userid }.html" style=" color:#fd5e02;">圈主</a>发表于：<fmt:formatDate value="${talkabout.updatetime }"  type="both" /></span></li>
											</c:when>
											<c:otherwise>
												<li>
	            									<h1>热门话题讨论：</h1>
													<h2><input value="请尽快添加热议话题吧！" type="text" id="circletalkabout" class="ui-text skin-text-willwhite"
													 style="overflow: auto; width: 380px; float:left; margin-right:10px; " onfocus="if($(this).val()=='请尽快添加热议话题吧！') $(this).val('')" onblur="if($(this).val().length==0) $(this).val('请尽快添加热议话题吧！')"/>
													 <a href="javascript:void(0);" style=" float:left; color:#fff;" onclick="changetalkabout('${circleleft.circleid}')">更换</a>
													<input type="hidden" id="talkabout_id"/>
													</h2>
												</li>	
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${talkabout != null}">
												<li>
	            									<h1>热门话题讨论：</h1>
	            									<h2><p>${talkabout.talktitle }</p>
	            										<input type="hidden" id="talkabout_id" value="${talkabout.id }"/>
	            										<a style="margin-right:0px;" href="circletalk/${circleleft.circleid}.html">更多</a>
	            									</h2>
	            									
												</li>
												<div class="clear"></div>
          										<li><span><a href="user/${circleleft.userinfo.userid }.html" style=" color:#fd5e02;">圈主</a>发表于：<fmt:formatDate value="${talkabout.updatetime }"  type="both" /></span></li>
											</c:when>
											<c:otherwise>
												<li>
	            									<h1>热门话题讨论：</h1>
	            									<h2>圈主还没有添加热议话题
	            									<input type="hidden" id="talkabout_id"/>
	            									</h2>
												</li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
        					</ul>
      					</div>
      					<div id="talklist">
    					</div>
    				</div>
    				<div class="clear"></div>
    				<div class="circlr_shuru0">
      					<div class="circlr_shuru">
        					<dl>
          						<Dt>
            						<div class="circlr_item_shuru">
              						<textarea id="talkcontent" onkeydown="if(event.keyCode==13)sendMessage() " style=" width:595px; height:30px; display: inline;  float:left; overflow:auto;line-height:30px; border-radius: 4px;-moz-border-radius: 4px; -webkit-border-radius: 4px;outline: none;font-size: 14px;overflow: auto; color: #666;background: #f7f8f8;border: 1px solid #C9CACC;border-bottom: 1px solid #B1B3B5;box-shadow: 0 1px 1px #C9CACC;"></textarea>
            						</div>
          						</Dt>
          						<dd><a href="javascript:sendMessage();">发送</a></dd>
        					</dl>
      					</div>
    				</div>
  				</div>
  				<div class="circle_middle2_right">
    				<div class="circle_middle2_right1">
      					<p>最新加入</p>
      					<c:forEach var="usercir" items="${userCircleRlss}" varStatus="u" >
							<a target="_blank" href="user/${usercir.userinfo.userid }.html">
							<c:choose>
								<c:when test="${usercir.userinfo.image == null || usercir.userinfo.image =='' }">
									<img src="../images/nobody.gif" title="${usercir.userinfo.nick }" />
								</c:when>
								<c:otherwise>
									<img src="${usercir.userinfo.image }" title="${usercir.userinfo.nick }" />
								</c:otherwise>
							</c:choose>
							</a>
						</c:forEach>
      				</div>
<%--    				<div class="circle_middle2_right2" style="display:none;">--%>
<%--      					<p>圈子成员生日</p>--%>
<%--      					<span><a>今天</a>|<a>5月25日</a></span>--%>
<%--      					<div class="clear"></div>--%>
<%--				      	<dl>--%>
<%--					        <dt><img src="images/photo.jpg" /></dt>--%>
<%--					        <dd>贵贵贵--珍</dd>--%>
<%--				      	</dl>--%>
<%--    				</div>--%>
				    <div class="circle_middle2_right3">
				      	<p style="float:left;">圈子成员（${count}）</p><p style="float:right; margin-right:10px;  font-size:12px;">
				      	<c:if test="${count > 12}">
				      		<a href="cir_user/${circleleft.circleid }.html" style="color:#626262;">更多</a>
				      	</c:if>
				      	</p> 
				    </div>
    				<div class="circle_middle2_right4">
    					<c:forEach var="cUser" items="${circleAlluser}">
    						<dl>
				        		<dt><a href="user/${cUser.userid}.html">
				        			<c:choose>
				        				<c:when test="${cUser.userinfo.image == null || cUser.userinfo.image == ''}">
				        					<img src="../images/nobody.gif" />
				        				</c:when>
				        				<c:otherwise>
				        					<img src="${cUser.userinfo.image}" />
				        				</c:otherwise>
				        			</c:choose>
				        			</a></dt>
				        		<dd><a href="user/${cUser.userid}.html">${cUser.userinfo.nick }</a></dd>
			      			</dl>
    					</c:forEach>
    				</div>
  				</div>
			</div>
			<div class="circle_middle4">
  				<div class="circle_middle4_top"><img src="images/tuijian.jpg" /></div>
				<ul class="ch-grid">
					<c:forEach var="recommend" items="${circlerecommend}" varStatus="r">
						<c:if test="${r.index <= 3 }">  
	        				<li>
	        					<a href="<%=basePath %>cir2/1/${recommend.recommendid }.html">
	        					<div class="ch-item ch-img-1" style="background-image: url(${recommend.selectcircleinfo.circlebigimg});">
	        						<span style="position:absolute; width:220px; font-weight:bold; text-align:center;color: #000;text-shadow: #fff 0 1px 2px;line-height:220px;font-size: 16px";>${recommend.selectcircleinfo.circlename }</span>
	        						<div class="ch-info">
	        							<h3><a href="<%=basePath %>cir2/1/${recommend.recommendid }.html" style="color: #fff;">${recommend.selectcircleinfo.circlename }</a></h3>
	        							<h2><a href="javascript:join_or_quit_quanzi('${recommend.recommendid }',1)"><img src="images/shouye/jia.png" /></a><a href="javascript:addFavorite('${recommend.recommendid }');"><img src="images/shouye/xin.png" /></a><a href="<%=basePath %>cir2/1/${recommend.recommendid }.html"><img src="images/shouye/hua.png" /></a></h2>
			               				<p>圈主：<a href="user/${recommend.selectcircleinfo.userinfo.userid}.html" style="color: #fff;">${recommend.selectcircleinfo.userinfo.nick}</a></p>
			               				<h1><a href="user/${recommend.selectcircleinfo.userinfo.userid}.html"><img src="${recommend.selectcircleinfo.userinfo.image}" /></a></h1>
			               			</div>
			               		</div>
			               		</a>
	            			</li>
            			 </c:if>
        			</c:forEach>
				</ul>
			</div>
		<script type="text/javascript">
			showtalk(1);
		</script>
  	</body>
</html>
