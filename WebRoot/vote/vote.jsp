<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/voteall.js" type="text/javascript"></script>
<script src="js/vote.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script> 
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/show_window.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.cookies.2.2.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script>
<style type="text/css">
#banner {position:relative; width:668px; height:258px; overflow:hidden; font-size:16px} 
#banner_list img {border:0px;} 
#banner_bg {position:absolute; bottom:0;background-color:#000;height:30px;filter: Alpha(Opacity=30);opacity:0.3;z-index:1000;cursor:pointer; width:1024px; } 
#banner_info{position:absolute; bottom:4px; left:5px;height:22px;color:#fff;z-index:1001;cursor:pointer} 
#banner_text {position:absolute;width:120px;z-index:1002; right:3px; bottom:3px;} 
#banner ul {position:absolute;list-style-type:none;filter: Alpha(Opacity=80);opacity:0.8; z-index:970; 
margin:0; padding:0; bottom:3px; right:5px; height:20px} 
#banner ul li { padding:0 8px; line-height:18px;float:left;display:block;color:#FFF;border:#e5eaff 1px solid;background-color:#6f4f67;cursor:pointer; margin:0; font-size:16px;} 
#banner_list a{position:absolute;}
</style> 
 
</head>

<body>

  <!--头部 -->
  <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  <!--中间 -->
   <div class="center_bj">
       <div class="center">
       		<span id="show" style="display:none;"></span>
         <div class="center_mian">
             <div class="center_left fillet">
                 <div class="center_left_top" id="votetime">
                     <div class="center_time">
                         <p>仅剩&nbsp;<span id="day"></span>&nbsp;天</p>
                     </div>
                      <div class="center_time1">
                        <p><span id="hour"></span>&nbsp;小时&nbsp;<span id="minute"></span>分</p>
                     </div>
                 </div>
                 <div class="center_left_min">
                      <div class="center_left_mon">
                       <div class="center_l" id="">
                       <label id="userHead"></label>
                    </div>
                    <div class="center_r">
                        <h2><a href="###" id="userNick"></a></h2>
                        <p>
                           话题 <span>（<label id="countTopic"></label>）</span>
                        </p>
                        <p>
                           投票 <span>（<label id="countVote"></label>）</span>
                        </p>
                    </div>
                      </div>
                      
                      <div class="center_bqian">
<%--                        <p>标签:&nbsp;&nbsp;<a id="tag"></a></p>--%>
                      </div>
                      
                      <div class="center_bottom">
<%--                         <p><span>简介：</span><label id="summary"></label></p>--%>
                      </div>
                      
                 </div>
             </div>
             <div class="center_right fillet">
                <div class="center_right_top">
                   <h1><a><label id="title"></label></a></h1>
                   <div class="timen" id="time"></div>
                </div>
                
                <div class="mew"  id="nav">
                	<div id="banner">
						<ul id="imgNum">
						</ul> 
	                	<div id="banner_list"> 
	                		 <div align="center" style="margin-top: 100px;" >
	           			 		<img src="images/loading.gif" alt="数据加载中请稍等..." />
	            			</div>
						</div> 
					</div> 
                </div>
                
                
                <div class="context">
                   <p id="summarys"></p>
                </div>
             
                <div class="con_ping">
                    <div class="pont_1" onclick="vote(1);"><p>关注</p><p>（<a id="one" href="javascript:void();">0</a>）</p></div>
                    <div class="pont_2" onclick="vote(2);"><p>喜欢</p><p>（<a id="two" href="javascript:void();">0</a>）</p></div>
                    <div class="pont_3" onclick="vote(3);"><p>不喜欢</p><p>（<a id="three" href="javascript:void();">0</a>）</p></div>
                    <div class="pont_4" onclick="vote(4);"><p>想买</p><p>（<a id="four" href="javascript:void();">0</a>）</p></div>
                    
                    <div class="sub_mint">
                       <p>￥:&nbsp;<span id="price"></span>&nbsp;元</p>
                    </div>
                </div>
                
                <div class="d_pl_cont">
            	<div class="pl_top"></div>
                <div class="pl_cont">
                	<div><input type="text" class="pl_input" id="contentText" /></div>
                    <ul class="d_pl"><li class="lp_bq"></li><li class="pl_btn" id="commentButton"><img src="images/sub_pingl.gif" onclick="commentVote();" /></li></ul>
                    <label id="msg"></label>
                    <label id="voteComment">
                    	<div style="text-align: center;"><img src="images/loading.gif" alt="数据加载中请稍等..." /></div>
                   </label>
						<div class="pl_page" id="pl_page"></div>
             		<div id="page"></div>
                </div>
                </div>
             </div>
         </div>
       </div>
   </div>
  <!--尾部 --> 
<!-----页尾部分---->
</body>
</html>
