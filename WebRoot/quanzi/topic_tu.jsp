
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>话题列表</title>
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/topicall.js" type="text/javascript"></script>
<script src="<%=basePath%>js/scroll.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>css/topic.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/topic_tu.js"></script>
<script src="<%=basePath%>js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
<script src="<%=basePath%>js/imgChange.js" type="text/javascript"></script>


<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>


<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script> 
<script type="text/javascript">
var s = true;
KindEditor.ready(function(K) {
	var editor1 = K.create('textarea[name="plContent"]', {
		cssPath : '<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css',
		uploadJson : '<%=basePath %>kindeditor-4.1.1/jsp/upload_json.jsp',
        fileManagerJson : '<%=basePath %>kindeditor-4.1.1/jsp/file_manager_json.jsp',
        allowFileManager : true,
		items : [
		    'undo', 'redo','source','fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
		    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		    'insertunorderedlist', '|', 'link','image','emoticons','preview'],
		'fontsize' : '14px',
		afterCreate : function() {
			var self = this;
			K.ctrl(document, 13, function() {
				self.sync();
				document.forms['example'].submit();
			});
			K.ctrl(self.edit.doc, 13, function() {
				self.sync();
				document.forms['example'].submit();
			});
			$("#submitPl").click(function () {
				self.sync();
				if(s)
				{
					submitComment(s);
					s = false;
				}
            });
		},
		afterBlur : function(){
			editor1.sync();
		}
	});
	editor1.exec('fontsize', '14px');
	prettyPrint();
});

 </script> 
<!--[if IE 6]>
<script src="js/DD_belatedPNG_0.0.8a.js"></script>
<script>
DD_belatedPNG.fix('*');
</script>
<![endif]-->

</head>

<body>
<!--头部代码 -->
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>

<!--中间内容代码 -->
 <form id="reg" name="reg" action="" method="post">
<div class="topic_center">
    <div class="topic1_center" >
        <div class="topic_pk_left">
            <div class="topic_subnav" style="margin-top:0;">
                <div class="topic_l"><a id="userImg_a"><img width="48px" height="48px" id="userImg" /></a></div>
                   <div class="topic_r1" >
                        <div class="topic_r">
                             <div class="pro_tiilt">
                                <h2 id="topicTitle"></h2>
                                <div class="top_impo"><a id="isgzImg_a"></a></div>
                                  
                                 <span id="topicCreateDateTime"><strong><a id="userNick"></a></strong>&nbsp;&nbsp;发表于 </span>
                               </div> 
                            <div id="topicContent" class="topic_r_p">
	                            <div class="profile_page" align="center">
					            <img src="images/loading.gif" />
					            </div>
                            </div>
                            </div>

                            <div class="topic_pk_img" id="topicImg">
                            </div>
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
								<a href="http://www.jiathis.com/share?uid=1639347" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
							</div>
							<div id="fenxiang"></div>
							<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1338348908887337" charset="utf-8"></script>
                           </div>
                          <br />
                            <div class="topic_pk_pinglun" >
									<textarea id="plContent" name="plContent" cols="79" style="width:625px;height: 50px;visibility:hidden;">&nbsp;</textarea>
                            </div>
                            <div class="pk_pl"><a><img id="submitPl" src="images/pro_important.png" /></a></div>
                           
                  </div>
                  
             </div>
             
            <!--二级页面 -->
            
            <div class="pk_center" id="pk_center" style="padding-top:0px;">
               
               <div id="comment">
             <div class="profile_page" align="center">
             	<br/>
	            <img src="images/loading.gif" />
	            </div>
                <!--第一个循环-->
                 </div>   
             
               </div>
             
                
                <!--尾部代码的代码 -->
                    <div class="pro_more" id="pro_more_com">
                       <p id="moreComment"><a href="javascript:void(0);" onclick="moreComment()">更多评论</a></p>
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
					<div class="topic_more" id="topic_more"><a href="<%=basePath %>quanzi/newTopics.jsp" id="topic_more_a" >查看更多</a></div>
                    
                </div>
           <!--热门用户 -->  
            <div class="topic_r_center">
                    <h3>热门用户:</h3>
                    <!--热门用户7个-->
                    <ul id="hotUser">
                        
                    </ul>
<%--                    <div class="topic_more" id="user_more"><a href="javascript:void(0);" onclick="moreUser()" id="user_more_a" >查看更多</a></div>--%>
						<div class="topic_more" id="topic_more"><a target="_blank" href="quanzi/topicHotUserBig.jsp" >查看更多</a></div>
                    
                </div>
        </div>
    </div>
</div>
</form>
</body>
</html>
