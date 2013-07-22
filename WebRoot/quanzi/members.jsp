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
<script src="js/quanzi_chengyuan.js" type="text/javascript"></script>
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
            <li class="name1"><span id="quanzi_title"></span><img src="images/ico_02.gif" width="15" height="15" /></li>
            <li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.location.href='<%=basePath%>quanzi/quanzi_fabu.jsp?id=<%=request.getParameter("id") %>'" style="display: none;" /></li>
            <li class="tcqz"><img src="images/btn_01.gif" id="quit" onclick="join_or_quit_quanzi(<%=request.getParameter("id") %>,2)" style="display: none;"/></li>
        </ul>
    </div>
	<div class="line1"></div>
    <div class="line2"></div>
    
	<div class="feedwall">
    	<!---第一列数据----->
   	  <div class="col" id="ones">
        	<div class="box">            
            <div class="items">
                <div class="feed_container"><a href="javascript:void(0);" id="a_img"><img id="q_img" src="images/jz.gif" width="198" /></a></div>
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
                    	<li><img src="images/jr_btna.gif" onclick="join_or_quit_quanzi(<%=request.getParameter("id") %>,1)" id="join"  style="display: none;"/></li>
                        <li><img src="images/jr_btn1.gif" id="a_join" style="display: none;" /></li>
                    </ul>
                </div>
                <p class="c_grey2">
                <a href="javascript:void(0);"><span id="u_name2"></span><span id="summary"></span></a>
            </p>
            </div>            
            <div class="summary">
                <div class="mbs">
                    <a href="#"><img id="u_img" src="images/jz.gif" /></a>
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
                    <span><a href="#">全部成员<<</a></span>
                </div>               
            </div>
            <div class="qnht">
            	 <p class="c_grey">
                    <a href="#">#花瓣花语录#瓜叶菊Florlsts Cinerarla，叶子像瓜叶，花朵很小，似菊，故名瓜叶菊；春天到来的时候开红黄蓝紫各色的花，静静的，不修饰，不矫情。花语是：                          喜悦，快活，快乐，持久的喜悦，长久的光辉。</a>
                </p>
            </div>
        </div>
        
        <div id="one"></div>
        
   	  </div>
      
      <script type="text/javascript"> 
			$(function(){ 
				getQuanzi(); 
			});  
	  </script>
        
        <!---第二列数据----->
      <div class="col" id="twos">
		<div id="two"></div>

<!--         <div class="box"> -->
<!--         	<div style=" z-index:100; position:absolute; left:15px; top:15px;"><img src="images/gz_btn1.gif" /></div> -->
<!--             <div class="items"> -->
<!--                 <div class="feed_container"><img src="images/img_03.jpg" width="198" /></div> -->
<!--             </div> -->
<!--             <div class="shenfen"> -->
<!--     		  <ul> -->
<!--               	<li class="shenfen_name">卡卡咿呀</li> -->
<!--                 <li class="shenfen_btn"><img src="images/btn_05.gif" /></li> -->
<!--               </ul> -->
<!--             </div> -->
<!--         </div> -->
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
    
    <div class="pl_page" id="pl_page">
<!--            	<span class="disabled">上一页&lt; </span> -->
<!--            	<span class="current">1</span> -->
<!--            	<a href="#">2</a> -->
<!--            	<a href="#?page=3">3</a> -->
<!--            	<span class="current">4</span> -->
<!--            	<a href="#">5</a> -->
<!--            	<a href="#">6</a> -->
<!--            	<a href="#">7</a> -->
<!--            	... -->
<!--            	<a href="#">199</a> -->
<!--            	<a href="#">200</a> -->
<!--            	<a href="#">下一页&gt; </a> -->
    </div>
</div>
</div>
<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

</body>
</html>
