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
<title>圈子</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/quanzi_list.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
<script src="js/hot_1.js" type="text/javascript"></script>
<script>
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
<input type="hidden" name="id" id="id" value="<%=request.getParameter("id") %>" />
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>

<div class="main_cont">
<div class="main">
    <div class="user_name">
        <ul>
            <li class="name1"><span id="quanzi_title"></span></li>
            <li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.location.href='<%=basePath%>circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>'"/></li>
            <li class="tcqz"><img src="images/btn_01.gif" id="quit" onclick="join_or_quit_quanzi('<%=request.getParameter("id") %>',2)" style="display: none;"/></li>
        </ul>
    </div>
	<div class="line1"></div>
    <div class="line2"></div>

	<div class="feedwall">
    	<!---第一列数据----->
   	  <div class="col" id="one">
        	<div class="box">            
            <div class="items">
                <div class="feed_container"><img id="q_img" src="images/nobody.gif" width="198" /></div>
                <p class="username" id="q_title"></p>
                <div class="xinxi_dxt">
                    <ul>
                        <li>成员<br /><font color="#e67300" id="joincount"></font></li>
                        <li style="border:none;">发言<br /><font color="#e67300" id="visitcount"></font></li>
                    </ul>
                </div>
                <div class="biaoqian" id="tags">标签：</div>
                <div class="caozuo">
                	<ul>
                        <li><img src="images/jr_btna.gif" onclick="join_or_quit_quanzi('<%=request.getParameter("id") %>',1)" id="join" style="display: none;"/></li>
                        <li><img src="images/jr_btn1.gif" id="a_join" style="display: none;"/></li>
                    </ul>
                </div>
                <p class="c_grey2">
                <a href="javascript:void(0);"><span id="u_name2"></span><span id="summary"></span></a>
            </p>
            </div>            
            <div class="summary">
                <div class="mbs">
                    <a href="#"><img id="u_img" src="images/nobody_1.gif" /></a>
            	</div>
                <div class="feed_text"><div class="u_name" id="u_name"></div><div class="u_class">圈主</div></div>
            </div>
            <div class="summary">
                <div class="mbs">
                    <a href="#"> <img src="images/user_02.gif" /></a>
            	</div>
                <div class="feed_text"><div class="u_name">Demolarry</div><div class="u_class">管理员</div></div>
            </div>
            <div class="summary2">
                <div class="zxcy">在线成员</div>
                <div class="zxcy_list">
                	<ul>
                    <li><a href="#"><img src="images/user_02.gif" /></a></li>
                    <li><a href="#"><img src="images/user_02.gif" /></a></li>
                    <li><a href="#"><img src="images/user_02.gif" /></a></li>
                    <li><a href="#"><img src="images/user_02.gif" /></a></li>
                    <li style="margin:0px;"><a href="#"><img src="images/user_02.gif" /></a></li>
                    </ul>
                    <span><a href="#" id="member">全部成员<<</a></span>
                </div>               
            </div>
<!--             <div class="qnht"> -->
<!--             	 <p class="c_grey"> -->
<!--                     <a href="#">#花瓣花语录#瓜叶菊Florlsts Cinerarla，叶子像瓜叶，花朵很小，似菊，故名瓜叶菊；春天到来的时候开红黄蓝紫各色的花，静静的，不修饰，不矫情。花语是：                          喜悦，快活，快乐，持久的喜悦，长久的光辉。</a> -->
<!--                 </p> -->
<!--             </div> -->
        </div>
            
        <script type="text/javascript"> 
			$(function(){ 
				getQuanzi(); 
			});  
		</script> 
<!--         <div class="box" onmouseover="ddd('aa', 'show');" onmouseout="ddd('aa', 'hide');"> -->
<!--         	<div class="fenxiang" id="aa" onmouseover="ddd('aa', 'show');" onmouseout="ddd('aa', 'hide');"> -->
<!--                 <ul> -->
<!--                     <li><img src="images/sc_btn.gif" /></li> -->
<!--                     <li><img src="images/xh_btn.gif" /></li> -->
<!--                     <li><img src="images/pl_btn.gif" /></li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--             <div class="items"> -->
<!--                 <div class="feed_container"><img src="images/img_01.jpg" width="198" /></div> -->
<!--                 <p class="c_grey"> -->
<!--                     <a href="#">Adobe公司CS5宣传视频，超有创意！</a> -->
<!--                 </p> -->
<!--             </div> -->
<!--             <div class="fabuzhe"> -->
<!--     <div class="mbs"> -->
<!--                     <a href="#"> <img src="images/user_01.gif"></a> -->
<!--               </div> -->
<!--                 <p class="fabuzhe_text"><a><font color="#f78d00">Demolarry:</font>发布</a><br /> -->
<!--                 <a href="#"><font color="#666666">884</font>&nbsp;收藏</a><a href="#"><font color="#666666">148</font>&nbsp;喜欢</a><a href="#"><font color="#666666">5</font>&nbsp;评论</a> -->
<!--               </p> -->
<!--             </div> -->
<!--             <div class="summary"> -->
<!--                 <div class="mbs"> -->
<!--                     <a href="#"> <img src="images/user_01.gif"></a> -->
<!--                 </div> -->
<!--                 <p class="feed_text"><a href="#"><font color="#3fbdec">Demolarry:</font>啊啊啊啊斯顿</a><br /> -->
<!--                 <a>2012-05-09</a> -->
<!--               </p> -->
<!--             </div> -->
<!--             <div class="summary"> -->
<!--                 <div class="mbs"> -->
<!--                     <a href="#"> <img src="images/user_02.gif"></a> -->
<!--             </div> -->
<!--                 <p class="feed_text"><a href="#"><font color="#3fbdec">XOXOSunnyXOXO:</font>啊啊啊啊啊，喜欢他干净的笑容。</a></p> -->
<!--             </div> -->
<!--             <div class="summary1"> -->
<!--                 <p class="feed_text1"><input type="text" class="input_txt"/></p> -->
<!--                 <p class="feed_ok"><input type="image" src="images/ok_btn.jpg" /></p> -->
<!--             </div> -->
<!--         </div> -->

   	  </div>
        
      <!---第二列数据----->
      <div class="col" id="two"></div>
        
      <!---第三列数据----->
      <div class="col" id="three"></div>
        
      <!---第四列数据----->
      <div class="col" id="four"></div>
      
      <!---第五列数据----->
      <div class="col_last" id="five">
        <div class="box">
        	<div class="fenxiang">
                <ul>
                    <li><img src="images/sc_btn.gif" /></li>
                    <li><img src="images/xh_btn.gif" /></li>
                    <li><img src="images/pl_btn.gif" /></li>
                </ul>
            </div>
            <div class="items1">
                <div class="feed_container"><strong style="color:#f79000; font-size:14px; line-height:25px; margin-left:10px; margin-top:15px;">逸族网公告</strong></div>
                <p class="c_grey1">
                    <a href="#">>去逛街对吧？主人，我准备好了~</a>
                </p>
                <p class="c_grey1">
                    <a href="#">>去逛街对吧？主人，我准备好了~</a>
                </p>
                <p class="c_grey1" style="border:none;">
                    <a href="#">>去逛街对吧？主人，我准备好了~</a>
                </p>
            </div>
        </div>
            
        
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
