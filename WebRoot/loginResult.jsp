<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--<script>--%>
<%--	alert("accountTypeID:" + '${accountTypeID}');--%>
<%--	alert("user:" + '${user}');--%>
<%--	alert("login_before_url:" + '${login_before_url}');--%>
<%--	alert("user.email:" + '${user.email}');--%>
<%--</script>--%>
<c:choose>
	<c:when test="${accountTypeID == 'tencent' && user == null}">
		<script>
			var hash = window.location.hash;
			hash = hash.replace("#", "");
			location.href='<%=basePath%>authorizeAction_tencentLoginResult.do?' + hash;
		</script>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${accountTypeID == 'tencent' || accountTypeID == 'sina'}">
				<c:choose>
					<c:when test="${user.email == '' || user.email == null }">
						<script type="text/javascript">
<%--							alert("email");--%>
							location.href="<%=basePath%>tencentOrSinaResult.html";
						</script>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${login_before_url != null && login_before_url != ''}">
								<script type="text/javascript">
<%--									alert("before");--%>
									location.href="${login_before_url}";
								</script>
							</c:when>
							<c:otherwise>
								<script type="text/javascript">
<%--									alert("index");--%>
									location.href="<%=basePath%>index.jsp";
								</script>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${login_before_url != null && login_before_url != ''}">
						<script type="text/javascript">
							location.href="${login_before_url}";
						</script>
					</c:when>
					<c:otherwise>
						<script type="text/javascript">
							location.href="<%=basePath%>index.jsp";
						</script>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

