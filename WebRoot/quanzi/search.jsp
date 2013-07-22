<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>逸族网 - 开启你的第二人生</title>
<%--<link href="css/style.css" type="text/css" rel="stylesheet" />--%>
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/show_window.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/hot_1.js" type="text/javascript"></script>
<script src="js/search.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script>
var basePath = "<%=basePath%>";
 	//鼠标滑过弹出层	
	function dis(obj, sType) { 
		var oDiv = document.getElementById(obj); 
		if (sType == 'show') { oDiv.style.display = 'block';} 
		if (sType == 'hide') { oDiv.style.display = 'none';} 
	} 
</script>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<input type="hidden" name="keyword" id="keyword" value="<%=request.getParameter("keyword") %>" />
<input type="hidden" name="s_type" id="s_type" value="1"/>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>

<div class="main_cont">
<div class="main">
    <div class="hot_nav" id="hot_nav" >
    	<a href="javascript:void(0);" id="search_circle"></a>&nbsp;&nbsp;<a href="javascript:void(0);" id="search_circledetail"></a>&nbsp;&nbsp;<b id="search_key"></b>
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
      <div class="col" id="fours">
      <div id="four"></div>
        
      </div>
        <!---第五列数据----->
      <div class="col_last" id="fives">
      <div id="five"></div>

      </div>
      
      
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
    
    <div id="end" class="jiazai" style="text-align: center;display: none;">
           <a onclick="createquanzi();" href='javascript:void(0);'><img src="images/dibu.GIF" /></a>
    </div>
<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>
<div style="display:none"><script src="http://s21.cnzz.com/stat.php?id=3921665&web_id=3921665" language="JavaScript"></script></div>
</body>
</html>
