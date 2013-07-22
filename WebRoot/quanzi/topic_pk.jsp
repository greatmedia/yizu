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
<title>话题pk</title>
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/topicall.js" type="text/javascript"></script>
<script src="<%=basePath%>js/scroll.js" type="text/javascript"></script>
<link href="<%=basePath%>css/topic.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/topic_pk.js"></script>
<script src="<%=basePath%>js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
<script src="<%=basePath%>js/imgChange.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script> 
</head>

<body>
<!--头部代码 -->
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>

<!--中间内容代码 -->
<div class="topic_center">
    <div class="topic1_center" >
        <div class="topic_pk_left">
            <div class="topic_subnav" style="margin-top:0;">
                <div class="topic_l" id="topic_user"></div>
                   <div class="topic_r1" >
                        <div class="topic_r">
                               <!--有pk的代码 -->
                            <div class="topic_r_pk"><img src="images/pro_PK.png" /></div>
                            <div class="topic_r_pinglun"  id="topicTitle"> </div> 
                            <p id="content"></p>
                         </div>
                         <div class="topic_pk_img" id="topicimg"></div>
                         <br />
					<div>
						<div class="jiathis_style">
							<span class="jiathis_txt" style="font-size: 12px;">&nbsp;&nbsp;&nbsp;分享到：</span>
							<a class="jiathis_button_qzone"></a>
							<a class="jiathis_button_tsina"></a>
							<a class="jiathis_button_tqq"></a>
							<a class="jiathis_button_renren"></a>
							<a class="jiathis_button_kaixin001"></a>
							<a class="jiathis_button_t163"></a>
							<a href="http://www.jiathis.com/share?uid=1639347"
								class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						</div>
						<div id="fenxiang"></div>
						<script type="text/javascript"
							src="http://v3.jiathis.com/code/jia.js?uid=1338348908887337"
							charset="utf-8"></script>
					</div>
					<br />
                  	</div>
					</div>
             
            <!--pk -->
            
            <div class="pk_center">
                <div class="pk_contant">
                     <div class="pk_Ntext">
                         <div class="pk_Ntext_l"><p style="text-align:center">支持</p></div>
                         <div class="pk_Ntext_r"><p style="text-align:center">不支持</p></div>
                     </div>
                     <div class="pk_onmer">
                        <div class="pro_pk_red" id="pro_pk_red"><a href="javascript:void(0)" id="redimg" onmouseover="over('red1','red2');" onmouseout="out('red1','red2');"><img id="red1" src="images/pro_PK_red.png" /><img style="display: none;" id="red2" src="images/pk_qianfen.png" /></a></div>
                        <div class="pro_pk_onu" id="pro_pk_onu">
<%--                            <span class="pro-span_1" style="width: 50px" id="pro_span_1">50</span>--%>
<%--                            <span class="pro-span_2"  id="pro_span_2">50</span>--%>
                            
                            <span class="pro-span_1"  id="pro_span_1"></span>
                            <span class="pro-span_2"   id="pro_span_2"></span>
                        </div>
                        <div class="pro_pk_blue" id="pro_pk_blue"><a href="javascript:void(0)" id="blueimg" onmouseover="over('blue1','blue2');" onmouseout="out('blue1','blue2');"><img id="blue1" src="images/pro_PK_blue.png" /><img style="display: none;" id="blue2" src="pk-qianlan.png" /></a></div> 
                     </div>
                </div>
                <div class="pk_text">
                        <div class="pk_txt_left">
                           <div><img src="images/PK_contant_left.gif" /></div>
                           <div class="pk_txt_l" id="pkLeft">
                               <div class="pk_question">共有<span id="redCountHTML">0</span>个回复</div>
                               <ul id="redCommentHTML">
                               </ul>
                           </div>
                           
                       </div>
                        <div class="pk_txt_right">
                            <div style=" padding-left:15px;"><img src="images/PK_contant_right.gif" /></div>
                            <div class="pk_txt_r">
                               <div class="pk_question">共有<span id="blueCountHTML">0</span>个回复</div>
                               <ul id="blueCommentHTML">                                 
                               </ul>
                           </div>
                            
                       </div>
                </div>
                <div id="moreComment" style="text-align:center; display:none; font-size:12px; line-height:22px; margin-bottom:23px; color:#767675;"><a href="javascript:void(0);" onclick="pageComment();" style="text-decoration:underline; color:#767675;" >查看更多</a></div>
            </div>
            
         </div>
        <div class="topic_pk_right">
           <!--热门话题 -->
            <div class="pk_about">
                    <h3>热门话题:</h3>
                    <!--热门话题7个-->
                    <ul id="hotTopics">
                    </ul>
                    <!--热门话题7个-->
<div class="topic_more" id="topic_more"><a href="<%=basePath %>quanzi/newTopics.jsp" id="topic_more_a" id="moreUser" >查看更多</a></div>
                </div>
           <!--热门用户 -->  
            <div class="topic_r_center">
                    <h3>热门用户:</h3>
                    <!--热门用户7个-->
                    <ul id="hotUser">
                    </ul>
                    <!--热门用户7个-->
<%-- 					<div class="topic_more" id="user_more"><a href="javascript:void(0);" onclick="moreUser()" id="user_more_a" >查看更多</a></div>--%>
						<div class="topic_more" id="topic_more"><a target="_blank" href="<%=basePath %>quanzi/topicHotUserBig.jsp" >查看更多</a></div>
                    
                </div>
        </div>
    </div>
    </div>
 <div id="box_B"></div>
 <div class="ropic_lent" id="ropic_lent">
     <div class="ropic_nav">
        <p>发表观点</p>
        <a href="javascript:void(0);" onclick="closepl();">x</a>
     </div>
     <div class="ropic_tit">成功支持了“<span id="ropic_tit"></span>”观点</div>
     <div class="ropic_no" >发表观点给“<span id="ropic_lent_p">红方</span>”阵营加把劲吧！
     </div>
     <div >
        <textarea class="ropic_txt" id="idpk"></textarea>    
     </div>
     <div class="ropic_no_pl"><a href="javascript:void(0);"><img src="images/pro_important.png" onclick="pkComment();"/></a></div>
 </div>
 <script>function closepl() {$("#box_B").css("display","none");$("#ropic_lent").css("display","none");$("#redimg").html("<img id=\"red1\" src=\"images/pro_PK_red.png\" />");$("#blueimg").html("<img id=\"blue1\" src=\"images/pro_PK_blue.png\" />");}
 function over(obj1,obj2)
 {
	 $("#"+obj1).css("display","none");
	 $("#"+obj2).css("display","none");
 }
 /**
  * 鼠标
  * @param obj
  * @param ccid
  */
 function out(obj1,obj2)
 {
 	$("#"+obj1).css("display","block");
 	$("#"+obj2).css("display","none");
 }
 </script>
</body>
</html>
