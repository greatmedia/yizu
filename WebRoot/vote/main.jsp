<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>投票列表</title>
<link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/voteall.js" type="text/javascript"></script>
<script src="<%=basePath%>js/main.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/public_min.js" type="text/javascript"></script> 
<script src="js/scroll.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
    </script>

<style type="text/css">
    body
    {
        font-size:12px;
        color:#474747;
    }
    .pagenumber
    {
        border-style:solid;
        border-width:1px;
        border-color:Orange;
        margin-left:10px;
        margin-top:10px;
        padding:4px;
        text-align:center;
        float:left;
        cursor:hand;
        background-color:White;
        color:Black;
    }
    .pagenumberselected
    {
        background-color:#CCCCCC;
        color:White;
    }
    </style>
    <script type="text/javascript">
	
		function isLogin(){
    		if(user != null && user != ""){
    		location.href="<%=basePath%>vote/createVote.jsp";
    		}else{
    			alert("请先登录吧，再去创建投票！");
    			myScroll();
    			$("#window_dl").css("display","block");
    		}
    	}
    </script>
    <script type="text/javascript">
/*
EASY TABS 1.2 Produced and Copyright by Koller Juergen
www.kollermedia.at | www.austria-media.at
Need Help? http:/www.kollermedia.at/archive/2007/07/10/easy-tabs-12-now-with-autochange
You can use this Script for private and commercial Projects, but just leave the two credit lines, thank you.
*/

//EASY TABS 1.2 - MENU SETTINGS
//Set the id names of your tablinks (without a number at the end)
var tablink_idname = new Array("tablink","anotherlink")
//Set the id names of your tabcontentareas (without a number at the end)
var tabcontent_idname = new Array("tabcontent","anothercontent") 
//Set the number of your tabs in each menu
var tabcount = new Array("4","6")
//Set the Tabs wich should load at start (In this Example:Menu 1 -> Tab 2 visible on load, Menu 2 -> Tab 5 visible on load)
var loadtabs = new Array("2","5")  
//Set the Number of the Menu which should autochange (if you dont't want to have a change menu set it to 0)
var autochangemenu = 2;
//the speed in seconds when the tabs should change
var changespeed = 3;
//should the autochange stop if the user hover over a tab from the autochangemenu? 0=no 1=yes
var stoponhover = 0;
//END MENU SETTINGS


/*Swich EasyTabs Functions - no need to edit something here*/
function easytabs(menunr, active) {if (menunr == autochangemenu){currenttab=active;}if ((menunr == autochangemenu)&&(stoponhover==1)) {stop_autochange()} else if ((menunr == autochangemenu)&&(stoponhover==0))  {counter=0;} menunr = menunr-1;for (i=1; i <= tabcount[menunr]; i++){document.getElementById(tablink_idname[menunr]+i).className='tab'+i;document.getElementById(tabcontent_idname[menunr]+i).style.display = 'none';}document.getElementById(tablink_idname[menunr]+active).className='tab'+active+' tabactive';document.getElementById(tabcontent_idname[menunr]+active).style.display = 'block';}var timer; counter=0; var totaltabs=tabcount[autochangemenu-1];var currenttab=loadtabs[autochangemenu-1];function start_autochange(){counter=counter+1;timer=setTimeout("start_autochange()",1000);if (counter == changespeed+1) {currenttab++;if (currenttab>totaltabs) {currenttab=1}easytabs(autochangemenu,currenttab);restart_autochange();}}function restart_autochange(){clearTimeout(timer);counter=0;start_autochange();}function stop_autochange(){clearTimeout(timer);counter=0;}

window.onload=function(){
var menucount=loadtabs.length; var a = 0; var b = 1; do {easytabs(b, loadtabs[a]);  a++; b++;}while (b<=menucount);
if (autochangemenu!=0){start_autochange();}
}
</script>

<style>
.min_right1 { background: none repeat scroll 0 0 #FFFFFF;float: right;margin-left: 700px; padding: 10px; position: fixed;width: 240px;z-index: 1;top:63px;
}
</style>

<script type="text/javascript">
    window.onscroll = function () {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        var top_div = document.getElementById("min_right");
        if (t >= 100) {
            top_div.className = "min_right1 fillet";
        }
        if (t == 0) {
            top_div.className = "min_right fillet";
        }
    }
</script>
</head>
<body>
<!--头部 -->
  <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
		
  <!--中间 -->
  <div class="center_bj">
  		<div id="left" style="display:none;"></div>
     <div class="center" id="main">
     <div class="center_1" id="allVote">
     	<a href="javascript:void(0);" onclick="getAll(8,1,1)" id="all">所有的投票</a>
     </div>
     <div class="center_1" id="voting">
     	<a href="javascript:void(0);" onclick="getVoting(8,1,1)">进行中的投票</a>
     </div>
     
<!--      <div class="center_1" id="beforevote">-->
<!--     	<a href="javascript:void(0);" onclick="getBeforeVoting(8,1,1)">即将开始的投票</a>-->
<!--     </div>-->
<!--     -->
<!--      <div class="center_1" id="endvote">-->
<!--     	<a href="javascript:void(0);" onclick="getEndVote(8,1,1)">已结束的投票</a>-->
<!--     </div>-->
     <div class="center_1" >
     	<a target="_blank" href="quanzi/about.jsp?tag=3">关于投票</a>
     </div>
     <div class="hot_ht">
     	<a href="javascript:void(0);" onclick="isLogin()">创建投票</a>
     </div>
     
     
         <div class="center_mian" >
             <div class="min_left" >
              	<div class="mim_clear"  id="showDiv">
<!--	             	<div class="min_middle">-->
<!--	             		 <div class="s_pic">-->
<!--	              			<div class="s_pic_left">-->
<!--		              			<p><h1>Casio 月相功能男士电子腕表月相功能男士电子腕表 W753-3AV</h1></p>-->
<!--		              			<p><strong>¥380-580</strong></p>-->
<!--		              			<p><a href="#"><img src="images/101.jpg" /></a></p>              -->
<!--		              			<p>喜欢<span>0</span>想买<span>0</span>评论<span>0</span></p>-->
<!--	              			</div>-->
<!--	              			<div class="s_pic_right">-->
<!--	              				<a href="#"><img src="images/20130306170429-700598984 (1).png" /></a>-->
<!--	              			</div>-->
<!--	              		</div>-->
<!--	              	</div> -->
	              </div>	 
				<div class="pl_page" id="pl_page"></div>
             </div>
			<div class="min_right fillet" id="min_right">
                <h2>入驻品牌</h2>
                  <div class="tab_logo">
                  	<div class="menu">
						<ul>
							<li class="tab_menu1"><a href="javascript:void(0);" onmouseover="easytabs('1', '1');" onfocus="easytabs('1', '1');" onclick="return false;"  title="" id="tablink1">今天<br /><span id="now"></span></a></li>
							<li class="tab_menu2"><a href="javascript:void(0);" onmouseover="easytabs('1', '2');" onfocus="easytabs('1', '2');" onclick="return false;"  title="" id="tablink2">明天<br /><span id="tow"></span></a></li>
							<li class="tab_menu3"><a href="javascript:void(0);" onmouseover="easytabs('1', '3');" onfocus="easytabs('1', '3');" onclick="return false;"  title="" id="tablink3">后天<br /><span id="hou"></span></a></li>
							<li><a href="#" onmouseover="easytabs('1', '4');" onfocus="easytabs('1', '4');" onclick="return false;"  title="" id="tablink4">Tab 4 </a></li>
						</ul>
						<script  language="javascript" type="text/javascript">
                  		 Date.prototype.format = function(fmt)
                  		  	{ 
                  		 		var o = { 
                  		    		"M+": this.getMonth()+1, //月份 
                  		    		"d+": this.getDate(),    //日 
                  		   			"h+": this.getHours(),   //小时 
                  		   			"m+": this.getMinutes(), //分 
                  		   			"s+": this.getSeconds(), //秒 
	                  		   		"q+": Math.floor((this.getMonth()+3)/3), //季度 
                  		   		 	"S" : this.getMilliseconds()      //毫秒 
                  		 		}; 
                  		 	if(/(y+)/.test(fmt)) 
                  		   	fmt=fmt.replace(RegExp.$1,
                  		 	(this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
                  		 	for(var k in o) 
                  		    	if(new RegExp("("+ k +")").test(fmt)) 
                  		  	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
                  		  	return fmt; 
                  			}
                  		function getDate(day){
                            var zdate=new Date();
                            var sdate=zdate.getTime()-(1*24*60*60*1000);
                            var edate=new Date(sdate-(day*24*60*60*1000)).format("MM/dd");
                            return edate;
                  		}
                  		var now= getDate(-1)//今天
<%--                  		alert(now);--%>
						var tow = getDate(-2);//明天        
						var hou = getDate(-3);  
						$("#now").html(now); 
						$("#tow").html(tow);
						$("#hou").html(hou);
                  		</script>
					</div>
					<div id="tabcontent1">
						<a href="http://mall.1mily.com/product/list-1002-1142-1475-0-0-0-0-0-0-0-1.html"><img src="../images/logo/A&F.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-adidas-0-0-0-0-0-1.html"><img src="../images/logo/Adidas.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-anne-0-0-0-0-0-1.html"><img src="../images/logo/Anne Klein.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-%E5%A7%8B%E7%A5%96%E9%B8%9F-0-0-0-0-0-1.html"><img src="../images/logo/ARC'TERYX .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-ARNOLD%20PALMER-0-0-0-0-0-1.html"><img src="../images/logo/ARNOLD PALMER.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-BALENCIAGA-0-0-0-0-0-1.html"><img src="../images/logo/BALENCIAGA.PARIS .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-BALLY-0-0-0-0-0-1.html"><img src="../images/logo/BALLY .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Bottega-0-0-0-0-0-1.html"><img src="../images/logo/Bottega Veneta.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Bulova-0-0-0-0-0-1.html"><img src="../images/logo/Bulova .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-BURBERRY-0-0-0-0-0-1.html"><img src="../images/logo/BURBERRY.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Calvin-0-0-0-0-0-1.html"><img src="../images/logo/Calvin Klein.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-CASIO-0-0-0-0-0-1.html"><img src="../images/logo/CASIO.jpg"/></a>
					</div>
					<div id="tabcontent2">
						<a href="http://mall.1mily.com/product/list-0-0-0-0-CHANEL-0-0-0-0-0-1.html"><img src="../images/logo/CHANEL.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-CHANEL-0-0-0-0-0-1.html"><img src="../images/logo/Chloe.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-christi-0-0-0-0-0-1.html"><img src="../images/logo/christian louboutin .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-CITIZEN-0-0-0-0-0-1.html"><img src="../images/logo/CITIZEN.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-COACH-0-0-0-0-0-1.html"><img src="../images/logo/COACH.png"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-COLUMBIA-0-0-0-0-0-1.html"><img src="../images/logo/COLUMBIA .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Croton-0-0-0-0-0-1.html"><img src="../images/logo/Croton.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Crumpler-0-0-0-0-0-1.html"><img src="../images/logo/Crumpler.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-DANIEL-0-0-0-0-0-1.html"><img src="../images/logo/DANIEL HECHTER .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-%E6%9D%9C%E5%98%89%E7%8F%AD%E7%BA%B3-0-0-0-0-0-1.html"><img src="../images/logo/DG.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Diesel-0-0-0-0-0-1.html"><img src="../images/logo/Diesel.gif"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-DIOR-0-0-0-0-0-1.html"><img src="../images/logo/DIOR.jpg"/></a>
					</div>
					<div id="tabcontent3">
						<a href="http://mall.1mily.com/product/list-0-0-0-0-DKNY-0-0-0-0-0-1.html"><img src="../images/logo/DKNY.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-%E7%99%BB%E5%96%9C%E8%B7%AF-0-0-0-0-0-1.html"><img src="../images/logo/Dunhill .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-DVF-0-0-0-0-0-1.html"><img src="../images/logo/DVF.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-EMPORIO-0-0-0-0-0-1.html"><img src="../images/logo/EMPORIO ARMANI.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-PALACE-0-0-0-0-0-1.html"><img src="../images/logo/E-PALACE .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-ESQ-0-0-0-0-0-1.html"><img src="../images/logo/ESQ by Movado.png"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-FABI-0-0-0-0-0-1.html"><img src="../images/logo/FABI.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-FENDI-0-0-0-0-0-1.html"><img src="../images/logo/FENDI.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-Folli-0-0-0-0-0-1.html"><img src="../images/logo/Folli Follie .jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-FOSSIL-0-0-0-0-0-1.html"><img src="../images/logo/FOSSIL.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-GIVENCHY-0-0-0-0-0-1.html"><img src="../images/logo/GIVENCHY.jpg"/></a>
						<a href="http://mall.1mily.com/product/list-0-0-0-0-gucci-0-0-0-0-0-1.html"><img src="../images/logo/gucci.png"/></a>
					</div>

					<div id="tabcontent4">
						<a href="#"><img src="../images/logo/DKNY.jpg"/></a>
						<a href="#"><img src="../images/logo/Dunhill .jpg"/></a>
						<a href="#"><img src="../images/logo/DVF.jpg"/></a>
						<a href="#"><img src="../images/logo/EMPORIO ARMANI.jpg"/></a>
						<a href="#"><img src="../images/logo/E-PALACE .jpg"/></a>
						<a href="#"><img src="../images/logo/ESQ by Movado.png"/></a>
						<a href="#"><img src="../images/logo/FABI.jpg"/></a>
						<a href="#"><img src="../images/logo/FENDI.jpg"/></a>
						<a href="#"><img src="../images/logo/Folli Follie .jpg"/></a>
						<a href="#"><img src="../images/logo/FOSSIL.jpg"/></a>
						<a href="#"><img src="../images/logo/GIVENCHY.jpg"/></a>
						<a href="#"><img src="../images/logo/gucci.png"/></a></div>
					</div>
             	</div>
			</div>
     	</div>    
  	</div>
<div>
</div>
</body>
</html>
