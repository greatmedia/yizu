<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${circleleft.circlename}</title>
<meta name="description" content="${circleleft.circlename} -圈子"/>
<meta name="Keywords" content="${circleleft.circletag}"/>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/circle1.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script> 
</head>

<body  onkeydown="if(event.keyCode==13){return false;}">
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<div class="main_cont" >
	<div class="main" id="min" >
    	<div class="user_name" style="display:none;">
        	<ul>
            	<li class="name1" id="circleName">${circleleft.circlename}</li>
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
		<input type="hidden" id="circleid" value="${circleleft.circleid}"/>
		<c:choose>
			<c:when test="${user != null}">
				<input type="hidden" id="islongin" value="${user.userid }"/>
				<input type="hidden" id="userid" value="${user.userid }"/>
				<input type="hidden" id="quanzhuid" value="${circleleft.userid }"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="islongin"/>
			</c:otherwise>
		</c:choose>
		<div class="quanzi">
     	<jsp:include flush="false" page="circle_left.jsp"></jsp:include>
     	<!---中间的数据-----> 
     	<div class="quanzi_right">
			<div class="quanzi_right1_0" >
				<div class="quanzi_right1_2">
					<p>
						<span>热门话题讨论：</span>
						<c:choose>
							<c:when test="${user.userid == circleleft.userid}">
								<c:choose>
									<c:when test="${talkabout != null}">
										<input value="${talkabout.talktitle }" class="ui-text skin-text-willwhite" 
										  style="overflow: auto; width: 420px;" type="text" id="circletalkabout"/>
									</c:when>
									<c:otherwise>
										<input value="请尽快添加热议话题吧！" type="text" id="circletalkabout" class="ui-text skin-text-willwhite"
										 style="overflow: auto; width: 420px;" onfocus="if($(this).val()=='请尽快添加热议话题吧！') $(this).val('')" onblur="if($(this).val().length==0) $(this).val('请尽快添加热议话题吧！')"/>
									</c:otherwise>
								</c:choose>
								<a href="javascript:void(0);" style="margin-right:30px; float:right; color:#999;" onclick="changetalkabout('${circleleft.circleid}')">更换</a>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${talkabout != null}">
										${talkabout.talktitle }
									</c:when>
									<c:otherwise>
										圈主还没及时添加热议话题
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
			</div>
			<div class="quanzi_right2">
				<div class="quanzi_gundong">
					<div class="quanzi_gundong_left">
						<div class="liaotian fillet"  id="liaotian">
							<ul>
								<!-- 圈主 -->
								<li><a href="user/${circleleft.userinfo.userid }.html" target="_blank"><img src="${circleleft.userinfo.image}" title="${circleleft.userinfo.nick }" /></a></li>
								<c:forEach var="usercir" items="${userCircleRlss}" varStatus="u" >
									<li><a target="_blank" href="user/${usercir.userinfo.userid }.html"><img src="${usercir.userinfo.image }" title="${usercir.userinfo.nick }" /></a></li>
								</c:forEach>
								
							</ul>
						</div>
					</div>
					<div class="quanzi_gundong_right" id="talklist"></div>
				</div>
				<div class="clear"></div>
				<div class="shuru0">
					<div class="shuru">
						<dl>
							<Dt>
								<div class="item_shuru">
									<textarea id="talkcontent" onkeydown="if(event.keyCode==13)sendMessage() "  style="width: 562px; height: 30px; display: inline; float: left; overflow: auto; line-height: 30px; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px; outline: none; font-size: 14px; overflow: auto; color: #666; background: #f7f8f8; border: 1px solid #C9CACC; border-bottom: 1px solid #B1B3B5; box-shadow: 0 1px 1px #C9CACC;"></textarea>
								</div>
							</Dt>
							<dd>
								<a href="javascript:void(0);" onclick="sendMessage()">发送</a>
							</dd>
						</dl>
					</div>
				</div>
			</div>
			
			
			<div id="circle_center_detail" style="display:none;">
				<c:forEach var="detail" items="${circleDetailInfos}" varStatus="in" >
					<div class="quanzi_right3">
						<dl>
							<c:forEach var="img" items="${detail.circleDetailImg}" varStatus="jn">
								<c:if test="${jn.index == 0}">
									<c:choose>
										<c:when test="${img.middleimg !=null && img.middleimg != ''}">
				                    	<dt><a target="_blank" href="det/${detail.circleid }/${detail.circledetailid }.html"><img src="${img.middleimg }"/></a></dt>
				                    	</c:when>
				                    	<c:otherwise>
				                    	<dt><a target="_blank" href="det/${detail.circleid }/${detail.circledetailid }.html"><img src="../images/003.jpg"/></a></dt>
				                    	</c:otherwise>
									</c:choose>
				                </c:if>
							</c:forEach>
							<dd>
							<div class="quanzi_right3_top">
								<p><a target="_blank" href="det/${circleleft.circleid }/${detail.circledetailid }.html">${detail.title }</a>
								<c:if test="${user.userid == circleleft.userid}">
									<select id="changetag" onchange="changetag('${detail.circledetailid}',$(this).val())">
										<c:forEach var="relate" items="${circletagrelate}" varStatus="c">
											<c:choose>
											<c:when test="${relate.ctid == detail.def4 }">
												<option value="${relate.ctid}" selected="selected">${relate.cirtag.tagname}</option>
											</c:when>
											<c:otherwise>
												<option value="${relate.ctid}">${relate.cirtag.tagname}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
									</select> 
								</c:if>
								</p>
								<p><span><fmt:formatDate value="${detail.createdatetime}"  type="date" dateStyle="default"/></span></p>
							</div>
							<div class="clear"></div>
							<div class="quanzi_right3_middle">
								<p>
									<c:choose>
		                    		<c:when test="${fn:startsWith(detail.circlecontent,'<embed')}">
		                    			${detail.circlecontent}
		                    		</c:when>
		                    		<c:otherwise>
		                    			<c:choose>
		                    				<c:when test="${fn:length(detail.circlecontent) > 300}">
		                    					${fn:substring(detail.circlecontent, 0, 300)}......
		                    				</c:when>
		                    				<c:otherwise> 
		     									${detail.circlecontent}
		    								 </c:otherwise>
		                    			</c:choose>
		                    		</c:otherwise>
		                    		</c:choose>
								</p>
							</div>
							<div class="quanzi_right3_middle">
								<span><a href="det/${circleleft.circleid }/${detail.circledetailid }.html">继续阅读>></a></span>
							</div>
							</dd>
						</dl>
					</div>
			</c:forEach>
		</div>
		</div>
 		</div>
 		<div class="themore" id="next_page"  style="display:none;">
 			<a href="javascript:void(0);" onclick="getmoredetail(${pageNum+1},'${circleleft.circleid }','0');">查看更多</a>
 		</div>
 		<div class="quanzi_right1_0" style="margin-top:15px; width:956px;">
				<div class="quanzi_right1" style="width:956px;">
					<div class="quanzi_biaoqian1" style="width:956px;">
						<div class="quanzi_biaoqian1_left" style="width:956px;">
							<p><strong>标签</strong></p>
							<ul>
								<li><a target="_blank" href="<%=basePath %>cirtag/1/${circleleft.circleid }/0.html" >全部</a></li>
							<c:forEach var="relate" items="${circletagrelate}" varStatus="c">
<%--								<li><a href="javascript:void(0);" onclick="getmoredetail(1,'${circleleft.circleid }','${relate.cirtag.ctid }')">${relate.cirtag.tagname }</a></li>--%>
								<li><a target="_blank" href="<%=basePath %>cirtag/1/${circleleft.circleid }/${relate.cirtag.ctid }.html" >${relate.cirtag.tagname }</a></li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			 <div class="Leisure">
        <h2>编辑特荐</h2>
        <div class="Leisure_more"></div>
        <ul>
        	<c:forEach var="recommend" items="${circlerecommend}" varStatus="r">
        		<c:choose>
        			<c:when test="${r.index == 5}">
        				<li  class="le_list">
			                <a href="<%=basePath %>cir2/1/${recommend.recommendid }.html"><img src="${recommend.selectcircleinfo.circlebigimg }" style="height:195px; width:146px;"/></a>
			                <div class="le_yin"></div>
			                <p>
			                     <a href="<%=basePath %>cir2/1/${recommend.recommendid }.html">${recommend.selectcircleinfo.circlename }</a>
			               </p>
            			</li>
        			</c:when>
        			<c:otherwise>
        				<li>
		               		<a href="<%=basePath %>cir2/1/${recommend.recommendid }.html"><img src="${recommend.selectcircleinfo.circlebigimg }" style="height:195px; width:146px;"/></a>
			                <div class="le_yin"></div>
			                <p>
			                     <a href="<%=basePath %>cir2/1/${recommend.recommendid }.html">${recommend.selectcircleinfo.circlename }</a>
			               </p>
            			</li>
        			</c:otherwise>
        		</c:choose>
        	</c:forEach>
        </ul>
      </div>
			
			
	</div>
</div>
<script type="text/javascript">
	showtalk(1);
</script>
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>
<%--<script type="text/javascript" src="http://v1.ujian.cc/code/ujian.js?type=slide"></script>--%>
</body>
</html>

