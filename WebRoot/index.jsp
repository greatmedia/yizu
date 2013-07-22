<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="qc:admins" content="43403661276151416375" />
<title>逸族网 | 都市精英兴趣社交第一平台</title>
<meta name="description" content="来吧，从此刻开始，开辟或加入专属自己的逸族圈子吧。哪怕你的爱好再小众，在这里也可以找到你的知己。让我们的身边簇拥着知己，一起分享，一起品位生活的点点滴滴。"/>
<meta name="Keywords" content="逸族,逸族网,逸族社区,逸族商城,第二人生,圈子,交友社区,社区购物,图片分享,话题,热门话题,今日话题，热点话题"/>
<meta name="baidu_union_verify" content="365ba86b7d4081ab1ba1e6e8968ddf47">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="Bookmark" href="favicon.ico" type="image/x-icon">
<link href="css/newindex.css" rel="stylesheet" type="text/css" />
<!--圈子-->






<%--<script src="js/1.js" type="text/javascript"></script>--%>
<%--<script src="js/scroll.js" type="text/javascript"></script>--%>


<script>var basePath = "<%=basePath%>";</script>

</head>
<body>
<jsp:include flush="false" page="inc/header.jsp"></jsp:include>
<script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
<script src="js/show_window.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<SCRIPT src="js/lanrentuku.js" type=text/javascript></SCRIPT>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
<input type="hidden" name="tagval" id="tagval" value="1" />
<input type="hidden" name="hotval" id="hotval" value="2" />
<input type="hidden" name="tagvals" id="tagvals" value="1" />
<input type="hidden" name="hotvals" id="hotvals" value="2" />
		<div class="main_cont" id="main_cont">
			<div class="main">
				 <div class="main_context">
				        <!---轮换视窗----->  
						<div class="main_nav" id="main_nav">
					        <div id="imgPlay">
          						<ul class="imgs" id="actor">
            						<li>
            							<a href="/register.html" target="_blank"><img src="images/mian_001.jpg" _src="images/mian_001.jpg"></a>
              							<div class="btn"></div>
            						</li>
						            <li><a href="<%=basePath%>act/Activity0823.jsp" target="_blank"><img src="images/mian_003.jpg" _src="images/mian_003.jpg"></a>
						              <div class="btn"></div>
						            </li>
						            <li><a href="/feiwenshowone/1/1/0.html" target="_blank"><img src="images/mian_002.jpg" _src="images/mian_002.jpg"></a>
						              <div class="btn"></div>
						            </li>
						            <li><a href="<%=basePath%>act/activity0329.jsp" target="_blank"><img src="images/mian_004.gif" _src="images/mian_004.gif"></a>
						              <div class="btn"></div>
						            </li>
						            <li><a href="http://yitaocom.taobao.com/" target="_blank"><img src="images/mian_005.jpg" _src="images/mian_005.jpg"></a>
						              <div class="btn"></div>
						            </li>
         						 </ul>
						          <div class="num">
						            <P class="lc"></P>
						            <P class="mc"></P>
						            <P class="rc"></P>
						          </div>
						          <div class="num" id="numInner"></div>
						          <div class="prev">上一张</div>
						          <div class="next">下一张</div>
        					</div>
      					</div>
						
				        <!---飞闻-----> 
				       <div class="main_fei">
				           <h4>逸族飞闻</h4>
				           <div class="fei-bottom" >
				              <ul id="feiwen">
				              </ul>
				            </div>
				       </div>
				  </div>

				<div class="newindex_box">
					<div class="newindex_box1">
						<div class="newindex_box1_top" id="mrtop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/1.html">名人逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="mrCircleinfo">
							
							</ul>
						</div>
					</div>

					<div class="newindex_box1">
						<div class="newindex_box1_top" id="ydtop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/2.html">运动逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="ydCircleinfo">
							</ul>
						</div>
					</div>


					<div class="newindex_box1">
						<div class="newindex_box1_top" id="xxtop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/3.html">休闲逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="xxCircleinfo">
							</ul>
						</div>
					</div>


					<div class="newindex_box1">
						<div class="newindex_box1_top" id="whtop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/4.html">文化逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="whCircleinfo">
							</ul>
						</div>
					</div>


					<div class="newindex_box1">
						<div class="newindex_box1_top" id="zctop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/5.html">职场逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="zcCircleinfo">
							</ul>
						</div>
					</div>


					<div class="newindex_box1">
						<div class="newindex_box1_top" id="nxtop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/6.html">女性逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="nxCircleinfo">
							</ul>
						</div>
					</div>





					<div class="newindex_box1">
						<div class="newindex_box1_top" id="shtop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/7.html">生活逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="shCircleinfo">
							</ul>
						</div>
					</div>


					<div class="newindex_box1">
						<div class="newindex_box1_top" id="bftop_div" style="display:none;">
							<div class="newindex_box1_top_left"></div>
							<div class="newindex_box1_top_middle">
								<p><a href="type/8.html">缤纷逸族</a></p>
							</div>
							<div class="newindex_box1_top_right"></div>
						</div>
						<div class="newindex_middle1">
							<ul class="ch-grid" id="bfCircleinfo">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="xuanfu" class="xuanfu" style="display:none;">
	    	<ul style="margin: auto auto auto 400px; width:50%;">
	            <li><h2 style="text-align: center;"><a href="javascript:void(0);" onclick="createquanzi();" style="color:black;"><span style="color:#F78D00;">没有找到心仪的圈子？请创建一个吧！</span></a></h2></li>
	        </ul>
	        <div class="fixed-bottom" id="toTop" style="display: block;"></div>
   		</div>
   		<jsp:include flush="false" page="inc/footer.jsp"></jsp:include>
</body>
	<style type="text/css">
		.bottom_out{padding-bottom: 80px;}
	</style>
</html>
