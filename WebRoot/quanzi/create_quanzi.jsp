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
<title>创建圈子</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
</head>

<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<div class="main_cont">
<div class="main">
    <div class="user_name">
        <ul>
            <li class="name1">创建圈子</li>
            <li class="tcqz"><img src="images/btn_02.gif" /></li>
            <li class="tcqz"><img src="images/btn_01.gif" /></li>
        </ul>
    </div>
    <div class="line1"></div>
    <div class="line2"></div>

	<div class="feedwall">
    	<!---第一列数据----->
       <!--<div class="col">
       	<div class="box"> 
            <div class="items">
                <div class="feed_container"><img src="images/img_03.jpg" width="198" /></div>
                <p class="username">画眉深浅</p>
                <div class="xinxi_dxt">
                    <ul>
                        <li>成员<br /><font color="#e67300">8888</font></li>
                        <li style="border:none;">发言<br /><font color="#e67300">14683</font></li>
                    </ul>
                </div>
                <div class="biaoqian">标签：<a href="#">摄影</a><a href="#">驴友</a><a href="#">暴走族</a><a href="#">丽江</a></div>
            </div>            
            <div class="summary3">
            <p class="c_grey3">
                <font color="#e67300">简介：</font>花瓣花语录#瓜叶菊Florlsts Cinerarla，叶子像瓜叶，花朵很小，似菊，故名瓜叶菊；春天到来的时候开红黄蓝紫各色的花，静静的，不修饰，不矫情。花语是：                          喜悦，快活，快乐，持久的喜悦，长久的光辉。
            </p>
            	<ul>
            		<li class="t_1">我的话题</li>
                    <li>我加入的圈子</li>
                    <li>我创建的圈子</li>
                    <li>我的关注</li>
                    <li>账号设置</li>
            	</ul>
            </div>
            
        </div>
   	  </div> -->
        <!--原来左边部分，删除掉不再保留---->
        
     <div class="details_cont_1">
               <div class="details-left">
            <!--<ul><li class="m_name">标题</li><li class="m_input"><input type="text" class="m_input" /></li></ul>
                <ul><li class="m_name">内容</li><li><textarea cols="79" rows="15" style="width:650px;"></textarea></li></ul>
                <ul><li class="m_name">标签</li><li class="m_input2"><input type="text" class="m_input2" /></li></ul>
                <ul><li class="m_name">封面</li><li class="m_input2"><input type="file" style="height:30px; width:260px;" /></li></ul>
                <ul><li class="m_name">&nbsp;</li><li><img src="images/fm_img.jpg" width="390" height="390" /></li></ul>
                <ul><li class="m_name">&nbsp;</li><li class="m_input1"><input type="image" src="images/btn_09.jpg"/></li></ul> -->
                    <div class="create_value">
                        <h3>标题（可不填）</h3>
                        <form>
                        <input type="text" value="请写点标题内容吧！"  class="cterat-value1" />
                        </form>
                    </div>
                    <div class="cteat-po-yin" style="">
                       <ul>
                           <li><img /></li>
                           <li><img /></li>
                           <li><img /></li>
                           <li><img /></li>
                           <li><img /></li>
                       </ul>
                     </div>
                     
                      <div class="creat-photo">
                         <div class="create_value">
			             <h3>上传图片</h3>
			             </div>
			             <div class="creat-img">
					       <form enctype="multipart/form-data" method="post">
                             <input type="file" name="file" id="file" size="20" class="ifile"
onchange="upfile.value=this.value"/>
                             <input class="fire_1" type="text" name="upfile" size="20" readonly/>
                             
                             
                            </form>
                          <style type="text/css">
                            .ifile {position:absolute;opacity:0;filter:alpha(opacity=0); width:100px; height:37px;}
							.fire_1{ background: url(../../11111.gif) no-repeat; width:100px; height:37px; border:none;}
                          </style>
					      </div>
					    <div class="img_right"> JPG, GIF, PNG或BMP，不超过2MB~3MB，一次可上传多张</div>
			         </div>
                     
                    
                     <div class="create_value">
			         <h3>内容</h3>
			         </div>
			         <div class="creat-con">
                     <form>
			         <textarea class="creat_con1" style= "overflow-y:auto; overflow:auto;" ></textarea>
                     </form>
			         </div>
                     
                     <div class="creat_submit">
                     <form>
                     <input type="image" src="images/btn_09.jpg"/>
                     </form>
                     </div>
                     
               </div>     
           </div>     
           <!--右边部分代码-->  
               
           <div class="details-right">
	        <h3>标签名</h3>
	        <div class="details-center">
             <form>
	         <input type="text" class="dl_title" value="添加点标签名吧！"/>
             </form>
	         </div>
	         <div class="details-xia">
	        </div>
                
          
     
      
    </div>
</div>
</div>
<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

</body>
</html>
