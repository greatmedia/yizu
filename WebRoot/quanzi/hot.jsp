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
<title>话题--列表</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/hot_1.js" type="text/javascript"></script>
<script src="js/hot.js" type="text/javascript"></script>
<script>
 //鼠标滑过弹出层	
	function ddd(obj, sType) { 
	var oDiv = document.getElementById(obj); 
	if (sType == 'show') { oDiv.style.display = 'block';} 
	if (sType == 'hide') { oDiv.style.display = 'none';} 
	
} 
</script>

</head>

<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<input type="hidden" name="hot_tag" id="hot_tag" value="0" />
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<!----登录窗口----->
<div class="main_cont">
<div class="main">
	<div class="banner"><img src="images/banner_1.gif" /></div>
    
	<div class="hot_nav" id="hot_nav">
<!--         <ul> -->
<!--             <li class="hot_ht">热门话题</li> -->
<!--             <li class="hot_ht1">话题分类</li> -->
<!--             <li>魅力逸族</li> -->
<!--             <li>工作逸族</li> -->
<!--             <li>逸家—逸佳人</li> -->
<!--             <li>邂逅美好</li> -->
<!--             <li>大有逸思</li> -->
<!--             <li style=" float:right; color:#ed4839;">我加入的圈子</li> -->
<!--         </ul> -->
    </div>
    
<!--          <script type="text/javascript">  -->
<!-- 		    $(function(){  -->
<!-- 		    	getTagTopic();  -->
<!-- 		     });   -->
		    
<!-- 		</script>  -->
<div class="feedwall">
<!----第一列数据---->
  <div class="col" id="ones">
  		<div id="one"></div>
  		
<!--         <div class="box"> -->
        
<!--             <div class="items"> -->
<!--                 <div class="feed_container"><img src="images/img_06.jpg" width="198" /></div> -->
<!--                 <p class="c_grey"> -->
<!--                     <a href="#">长久的光辉。</a> -->
<!--                 </p> -->
<!--             </div> -->
            
<!--             <div class="fabuzhe"> -->
<!--                 <div class="mbs"> -->
<!--                     <a href="#"> <img src="images/user_01.gif"></a> -->
<!--                 </div> -->
<!--                 <p class="fabuzhe_text"><a><font color="#f78d00">Demolarry:</font>发布</a><br /> -->
<!--                 <a href="#"><font color="#666666">884</font>&nbsp;收藏</a><a href="#"><font color="#666666">148</font>&nbsp;喜欢</a><a href="#"><font color="#666666">5</font>&nbsp;评论</a> -->
<!--               </p> -->
<!--             </div> -->
            
<!--             <div class="summary"> -->
<!--                 <div class="mbs"> -->
<!--                     <a href="#"> <img src="images/user_01.gif"></a> -->
<!--                 </div> -->
<!--                 <p class="feed_text"><a href="#"><font color="#3fbdec">XOXOSunnyXOXO:</font>啊啊啊啊啊，喜欢他干净的笑容。</a><br /> -->
<!--                 <a>2012-05-09</a> -->
<!--                 </p> -->
<!--             </div> -->
            
<!--              <div class="summary1" id="sumry1"> -->
<!--                <div class="sumimg" id="sumimg1"><img src="images/user_02.gif" /></div> -->
<!--                <form id="formm1"> -->
<!--     	       <input name="textD" id="textD" value="评论..." onblur="_inputOnBlur(this, 'sumry1', 'sumimg1')" onkeydown="_inputOnKeyDown(this, 'sumry1', 'sumimg1', 'formm1')" onfocus="_inputOnFocus(this, 'sumry1', 'sumimg1')"/> -->
<!--                </form> -->
<!--             </div> -->
            
<!--          </div> -->
         
   </div>
   <!----第二列数据---->
   <div class="col" id="twos">
   		<div id="two"></div>
   </div>
   
   <!----第三列数据---->
   <div class="col" id="threes">
   		<div id="three"></div>
   </div>
   
   <!----第四列数据---->
   <div class="col" id="fours">
   		<div id="four"></div>
   </div>
   
   <!----第五列数据---->
   <div class="col" id="fives">
   		<div id="five"></div>
   </div>
   
</div>
</div>
</div>

	<div id="loading" class="jiazai" >
    	<ul>
        	<li><a href="javascript:void(0);"><img src="images/jz.gif" /></a></li>
        	<li>正在加载……</li>
        </ul>
    </div>
    
    
    <div id="load" class="jiazai" style="display: none;">
    	<ul>
            <li><a href="javascript:void(0);">继续加载......</a></li>
        </ul>
    </div>
    
    <div id="end" class="jiazai" style="display: none;">
    	<ul>
            <li><img src="images/end.png" /></li>
        </ul>
    </div>

<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

</body>
</html>
