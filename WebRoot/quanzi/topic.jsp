<%@ page contentType="text/html;charset=utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>话题</title>
    <link href="css/index.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/topicall.js" type="text/javascript"></script>
    <script src="js/scroll.js" type="text/javascript"></script>
    <script src="js/topic.js"   type="text/javascript"></script>
</head>
<body>
<!--头部-->
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="center_bj">
     <div class="center">
          <div class="h_center">
              <div class="h_biaoqian">
                  <ul id="tag">
<!--                     <li><a href="###">苹果</a></li>-->
<!--                     <li><a href="###">手机</a></li>-->
<!--                     <li><a href="###">诺基亚</a></li>-->
<!--                     <li><a href="###">摩托罗拉</a></li>-->
<!--                     <li><a href="###">索尼</a></li>-->
                  </ul>
                  <h2 id="title"><a></a></h2>
                  <p id="content"></p>
                  <div class="h_img" id="topicImg"></div>
              </div>
              <div id="commentDiv">
<!--               <div class=" h_pinglun" >-->
<!--                 <div class="h_name">-->
<!--                     <a href="###" id="commentUser"></a> -->
<!--                     <span id="commentTile">,逸族网：从这里，开启你的第二人生</span>-->
<!--                 </div>-->
<!--                 <p>逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第</p>-->
<!--                 <div class="h_time"><span>8:21</span>&nbsp;&nbsp;&nbsp;<span>赞同(<strong>&nbsp;<a href="###">2311</a>&nbsp;</strong>)</span></div>-->
<!--              <div class="n_ding"></div>-->
<!--              <div class="n_ding1"></div>-->
<!--              </div>-->
<!--              -->
<!--              -->
<!--               <div class=" h_pinglun">-->
<!--                 <div class="h_name">-->
<!--                     <a href="###">逸族网</a> -->
<!--                     ,逸族网：从这里，开启你的第二人生-->
<!--                 </div>-->
<!--                 <p>逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第逸族网：从这里，开启你的第</p>-->
<!--                 <div class="h_time"><span>8:21</span>&nbsp;&nbsp;&nbsp;<span>赞同(<strong>&nbsp;<a href="###">2311</a>&nbsp;</strong>)</span></div>-->
<!--              <div class="n_ding"></div>-->
<!--              <div class="n_ding1"></div>-->
<!--              </div>-->
<!--           -->
              </div>
          </div>
   
 <!--右侧部分
  -->
 <div class="h_right fillet">
         
             <div class="h_right_s">
                     <h4>热门圈子</h4>
                    <ul class="h_right_q" id="hotCircle">
                       
                    </ul>
               </div>
               
               
               
               
               
               
         
             <div class="h_right_s">
<!--                     <h4>热门话题</h4>-->
<!--                     <ul class="h_right_q">-->
<!--                       <li>-->
<!--                           <p><a href="###">#创意广告#</a></p>-->
<!--                           <span></span>-->
<!--                       </li>-->
<!--                       <li>-->
<!--                           <p><a href="###">#创意广告#</a></p>-->
<!--                           <span></span>-->
<!--                       </li>-->
<!--                    </ul>-->
               </div>
         
         </div>
         
         
    </div>       
</div>     
</body>
</html>
