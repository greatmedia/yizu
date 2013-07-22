<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>逸族网 - 开启你的第二人生</title>
<meta name="description" content="来吧，从此刻开始，开辟或加入专属自己的逸族圈子吧。哪怕你的爱好再小众，在这里也可以找到你的知己。让我们的身边簇拥着知己，一起分享，一起品位生活的点点滴滴。"/>
<meta name="Keywords" content="逸族,逸族网,逸族社区,逸族商城,第二人生,圈子,交友社区,社区购物,图片分享,话题,热门话题,今日话题，热点话题"/>
<link href="css/newindex.css" rel="stylesheet" type="text/css" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/show_window.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/type.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>



<div class="main_cont">
<div class="main">
	<div class="banner" id="banner">
		<img id="bannerimg"/>
	</div>
	<div id="ggu1184724" style="display: none;">
		<script type="text/javascript">
		/*960*90，创建于2013-1-8*/
		var cpro_id = "u1184724";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
	</div>
	<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	var hot = realurl.substring(realurl.search("type/")+5,idindexof);
	var type = realurl.substring(idindexof + 1,realurl.search(".html"));
	if(hot == 5){
		$("#bannerimg").attr("src","images/banner/"+type+".gif");
	}else{
		$("#bannerimg").attr("src","images/banner/"+type+".jpg");
	}
	if(type==12)
	{
		$("#banner").html("<a target='_blank' href='http://esf.tj.soufun.com/?utm_source=tjqq&utm_medium=click&utm_term=chg&utm_content=esfsy&utm_campaign=yizuwang'><img width='960' src='gg/soufun.gif' /></a>");
	}
	if(type==10)
	{
		$("#banner").html("<a target='_blank' href='http://d.ddk.hk/API/try/play.aspx?gameId=20&serverId=503&recmd_uid=750018'><img width='960' src='gg/rexuesanguo.jpg' /></a>");
	}
	if(type==22)
	{
		$("#banner").css("display","none");
		$("#ggu1184724").css("display","block");
	}
	if(type==2)
	{
		$("#banner").css("display","none");
		$("#ggu1184724").css("display","block");
	}
</script>
<%--	<a href="/" class="recent">首页&nbsp;>></a>--%>
    <div class="hot_nav" id="hot_nav" style="display: none;">
    	<div class="categories"><a class="recent" style="padding:0 1px;" href="javascript:void(0);" id="tagname"></a></div>
    </div>
	
	<div class="feedwall">
    	<!---第一列数据----->
   	  <div class="col" id="ones">
        <div id="one"></div>
   	  </div>
        
        <!---第二列数据----->
      <div class="col" id="twos">
      <div id="two"></div>
        
      </div>
        
        <!---第三列数据----->
      <div class="col" id="threes">
      <div id="three"></div>
        
      </div>
        
        <!---第四列数据----->
      <div class="col_last" id="fours">
      <div id="four"></div>
        
      </div>
        <!---第五列数据----->
<%--      <div  id="fives">--%>
<%--      <div id="five"></div>--%>

<%--      </div>--%>
      
      
    </div>
</div>
</div>

	<div id="loading" class="jiazai" >
    	<ul>
        	<li><a href="javascript:void(0);"><img src="images/jz.gif" /></a></li>
        	<li>正在加载...</li>
        </ul>
    </div>
    
    
    <div id="load" class="jiazai" style="display: none;">
    	<ul>
            <li><a href="javascript:void(0);">继续加载...</a></li>
        </ul>
    </div>
<%--    <img src="images/dibu.GIF" />--%>
    <div id="end" class="" style="display: none;  background: url(../images/bg.jpg) repeat scroll 0 0 transparent;">
    	<ul style="margin: auto auto auto 500px; width:50%;">
            <li><h2><a href="<%=basePath%>circleInfoAction_create.do" style="color:black;">没有找到心仪的圈子？请<span style="color:#F78D00;">创建一个</span>吧！</a></h2></li>
        </ul>
    </div>

<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>
<div style="display:none"><script src="http://s21.cnzz.com/stat.php?id=3921665&web_id=3921665" language="JavaScript"></script></div>
</body>
</html>
