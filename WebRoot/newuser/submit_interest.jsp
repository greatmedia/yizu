<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改兴趣</title>
<link href="<%=basePath%>css/user.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script>
function loaded()
{
 var hobby = $("#hobby").val();
 var hobbys= new Array(); //定义一数组
 hobbys=hobby.split(","); //字符分割    
 for (i=0;i<hobbys.length ;i++ )    
 {    
    var title = hobbys[i];

    if(title=="品牌")
    {
    	$("#check_interest1").removeClass("yes_d");
    	$("#check_interest1").addClass("yes_d_no");
    }
    if(title=="旅游")
    {
    	$("#check_interest2").removeClass("yes_d");
    	$("#check_interest2").addClass("yes_d_no");
    }
     if(title=="营销")
    {
    	$("#check_interest3").removeClass("yes_d");
    	$("#check_interest3").addClass("yes_d_no");
    }
     if(title=="电商")
    {
    	$("#check_interest4").removeClass("yes_d");
    	$("#check_interest4").addClass("yes_d_no");
    }
     if(title=="财经")
    {
    	$("#check_interest5").removeClass("yes_d");
    	$("#check_interest5").addClass("yes_d_no");
    }
     if(title=="文学")
    {
    	$("#check_interest6").removeClass("yes_d");
    	$("#check_interest6").addClass("yes_d_no");
    }
     if(title=="高尔夫")
    {
    	$("#check_interest7").removeClass("yes_d");
    	$("#check_interest7").addClass("yes_d_no");
    }
     if(title=="桥牌")
    {
    	$("#check_interest8").removeClass("yes_d");
    	$("#check_interest8").addClass("yes_d_no");
    }
     if(title=="桌球")
    {
    	$("#check_interest9").removeClass("yes_d");
    	$("#check_interest9").addClass("yes_d_no");
    }
     if(title=="骑马")
    {
    	$("#check_interest10").removeClass("yes_d");
    	$("#check_interest10").addClass("yes_d_no");
    }
     if(title=="钓鱼")
    {
    	$("#check_interest11").removeClass("yes_d");
    	$("#check_interest11").addClass("yes_d_no");
    }
     if(title=="棋类")
    {
    	$("#check_interest12").removeClass("yes_d");
    	$("#check_interest12").addClass("yes_d_no");
    }
     if(title=="摄影")
    {
    	$("#check_interest13").removeClass("yes_d");
    	$("#check_interest13").addClass("yes_d_no");
    }
     if(title=="绘画")
    {
    	$("#check_interest14").removeClass("yes_d");
    	$("#check_interest14").addClass("yes_d_no");
    }
     if(title=="舞蹈")
    {
    	$("#check_interest15").removeClass("yes_d");
    	$("#check_interest15").addClass("yes_d_no");
    }
     if(title=="音乐")
    {
    	$("#check_interest16").removeClass("yes_d");
    	$("#check_interest16").addClass("yes_d_no");
    }
     if(title=="电影")
    {
    	$("#check_interest17").removeClass("yes_d");
    	$("#check_interest17").addClass("yes_d_no");
    }
     if(title=="收藏")
    {
    	$("#check_interest18").removeClass("yes_d");
    	$("#check_interest18").addClass("yes_d_no");
    }
     if(title=="读书")
    {
    	$("#check_interest19").removeClass("yes_d");
    	$("#check_interest19").addClass("yes_d_no");
    }
     if(title=="话剧")
    {
    	$("#check_interest20").removeClass("yes_d");
    	$("#check_interest20").addClass("yes_d_no");
    }
    if(title=="手绘")
    {
    	$("#check_interest21").removeClass("yes_d");
    	$("#check_interest21").addClass("yes_d_no");
    }
    if(title=="瑜伽")
    {
    	$("#check_interest22").removeClass("yes_d");
    	$("#check_interest22").addClass("yes_d_no");
    }
    if(title=="泡吧")
    {
    	$("#check_interest23").removeClass("yes_d");
    	$("#check_interest23").addClass("yes_d_no");
    }
    if(title=="集邮")
    {
    	$("#check_interest24").removeClass("yes_d");
    	$("#check_interest24").addClass("yes_d_no");
    }
    if(title=="红酒")
    {
    	$("#check_interest25").removeClass("yes_d");
    	$("#check_interest25").addClass("yes_d_no");
    }
    
    if(title=="咖啡")
    {
    	$("#check_interest26").removeClass("yes_d");
    	$("#check_interest26").addClass("yes_d_no");
    }
    if(title=="美食")
    {
    	$("#check_interest27").removeClass("yes_d");
    	$("#check_interest27").addClass("yes_d_no");
    }
     if(title=="美容")
    {
    	$("#check_interest28").removeClass("yes_d");
    	$("#check_interest28").addClass("yes_d_no");
    }
    if(title=="游戏")
    {
    	$("#check_interest29").removeClass("yes_d");
    	$("#check_interest29").addClass("yes_d_no");
    }
    if(title=="时尚")
    {
    	$("#check_interest30").removeClass("yes_d");
    	$("#check_interest30").addClass("yes_d_no");
    }
    if(title=="探索")
    {
    	$("#check_interest31").removeClass("yes_d");
    	$("#check_interest31").addClass("yes_d_no");
    }
    if(title=="冒险")
    {
    	$("#check_interest32").removeClass("yes_d");
    	$("#check_interest32").addClass("yes_d_no");
    }
    if(title=="赚钱")
    {
    	$("#check_interest33").removeClass("yes_d");
    	$("#check_interest33").addClass("yes_d_no");
    }
    if(title=="购物")
    {
    	$("#check_interest34").removeClass("yes_d");
    	$("#check_interest34").addClass("yes_d_no");
    }
    if(title=="宅")
    {
    	$("#check_interest35").removeClass("yes_d");
    	$("#check_interest35").addClass("yes_d_no");
    }
    
 }
/*
str="2,2,3,5,6,6"; //这是一字符串
var strs= new Array(); //定义一数组

strs=str.split(","); //字符分割      
for (i=0;i<strs.length ;i++ )    
    {    
    	//alert(strs[i]);
        //document.write(strs[i]+"<br/>");    //分割后的字符输出
    }*/
}
function savehobby()
 {
 	var habby = [];
 	var ul= document.getElementById("hobbys");
 	var li = ul.getElementsByTagName("li");
	for(var j = 1;j<=li.length;j++){
		
		if(document.getElementById("check_interest"+j+"").className == "yes_d_no"){
			habby.push(document.getElementById("text"+j+"").value);
		}
	}
	//alert(habby.join(",").length);
	if(habby.join(",").length<=0)
	{
		alert("请选择兴趣爱好!");
		return false;
	}
	$("#savehobbyDIV").css("display","none");
	var hobbyURL = "userInfoAction_updatehobbysave.do";
	$.ajax({
		url:hobbyURL,
		data:{habby : habby.join(",")},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
		    $("#savehobbyDIV").css("display","block");
			var islogin = data.data.islogin;
			if(islogin)
			{
				var status = data.data.status;
				if(status>0)
				{
					alert("修改成功!");
				}else{
					alert("修改失败!");
				}
			}else{
				alert("你还没有登录!");
			}
		},
		error:function(data){
			$("#savehobbyDIV").css("display","block");
			alert("网络异常!");
			
		}
	});
 }
</script>
</head>

<body onload="loaded()">
<input type="hidden" id="hobby" value="${user.hobby }" />
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="submit_middle">
<div class="submit_middle_left">
<!--<ul>-->
<!--       <li><a href="###">Ta的发言</a></li>-->
<!--       <li><a href="###">Ta的创建投票</a></li>-->
<!--       <li><a href="###">Ta加入的圈子</a></li>-->
<!--       <li><a href="###">Ta创建的圈子</a></li>-->
<!--   </ul>-->
    <ul class="submit_middle_left_ul2">
       <li><a href="userInfoAction_updatedata.do">修改资料</a></li>
       <li><a href="userInfoAction_updatehobby.do">兴趣爱好</a></li>
       <li><a href="userInfoAction_updatehead.do">修改头像</a></li>
   </ul>
</div>
<div class="submit_middle_right">
<div class="tittle_submit">
      <p>修改你的兴趣爱好</p>
    </div>
<div class="submit_middle_right1">
     <!-- <div class="submit_middle_right1_left">
        <p>兴趣选项：</p>
      </div>-->
      
        
        
        <div class="interest_info11">
          <script type="text/javascript">
function showinterest(imgid,divid)
{
	var obj = document.getElementById(divid);
	var obj_img = document.getElementById(imgid);
	if (obj.className == "yes_d") {
		obj.className = "yes_d_no";
		obj_img.style.opacity = 0.7;
	} else {
		obj.className = "yes_d";
		obj_img.style.opacity = 1.0;
	}
	
}
</script>
          <ul id="hobbys">
           <li><a id="cell_interest1" href="javascript:void(0);" class="interest01" onclick="showinterest('cell_interest1', 'check_interest1');"></a>
              <div id="check_interest1" class="yes_d"><input type="hidden" id="text1" value="品牌"/></div>
            </li>
            <li><a id="cell_interest2"  href="javascript:void(0);" class="interest02" onclick="showinterest('cell_interest2', 'check_interest2');"></a>
              <div id="check_interest2" class="yes_d"><input type="hidden" id="text2" value="旅游"/></div>
            </li>
            <li><a id="cell_interest3"  href="javascript:void(0);" class="interest03" onclick="showinterest('cell_interest3', 'check_interest3');"></a>
              <div id="check_interest3" class="yes_d"><input type="hidden" id="text3" value="营销"/></div>
            </li>
            <li><a id="cell_interest4"  href="javascript:void(0);" class="interest04" onclick="showinterest('cell_interest4', 'check_interest4');"></a>
              <div id="check_interest4" class="yes_d"><input type="hidden" id="text4" value="电商"/></div>
            </li>
            <li><a id="cell_interest5"  href="javascript:void(0);" class="interest05" onclick="showinterest('cell_interest5', 'check_interest5');"></a>
              <div id="check_interest5" class="yes_d"><input type="hidden" id="text5" value="财经"/></div>
            </li>
            <li><a id="cell_interest6"  href="javascript:void(0);" class="interest06" onclick="showinterest('cell_interest6', 'check_interest6');"></a>
              <div id="check_interest6" class="yes_d"><input type="hidden" id="text6" value="文学"/></div>
            </li>
            <li><a id="cell_interest7"  href="javascript:void(0);" class="interest07" onclick="showinterest('cell_interest7', 'check_interest7');"></a>
              <div id="check_interest7" class="yes_d"><input type="hidden" id="text7" value="高尔夫"/></div>
            </li>
            <li><a id="cell_interest8"  href="javascript:void(0);" class="interest08" onclick="showinterest('cell_interest8', 'check_interest8');"></a>
              <div id="check_interest8" class="yes_d"><input type="hidden" id="text8" value="桥牌"/></div>
            </li>
            <li><a id="cell_interest9"  href="javascript:void(0);" class="interest09" onclick="showinterest('cell_interest9', 'check_interest9');"></a>
              <div id="check_interest9" class="yes_d"><input type="hidden" id="text9" value="桌球"/></div>
            </li>
            <li><a id="cell_interest10"  href="javascript:void(0);" class="interest10" onclick="showinterest('cell_interest10', 'check_interest10');"></a>
              <div id="check_interest10" class="yes_d"><input type="hidden" id="text10" value="骑马"/></div>
            </li>
            <li><a id="cell_interest11"  href="javascript:void(0);" class="interest11" onclick="showinterest('cell_interest11', 'check_interest11');"></a>
              <div id="check_interest11" class="yes_d"><input type="hidden" id="text11" value="钓鱼"/></div>
            </li>
            <li><a id="cell_interest12"  href="javascript:void(0);" class="interest12" onclick="showinterest('cell_interest12', 'check_interest12');"></a>
              <div id="check_interest12" class="yes_d"><input type="hidden" id="text12" value="棋类"/></div>
            </li>
            <li><a id="cell_interest13"  href="javascript:void(0);" class="interest13" onclick="showinterest('cell_interest13', 'check_interest13');"></a>
              <div id="check_interest13" class="yes_d"><input type="hidden" id="text13" value="摄影"/></div>
            </li>
            <li><a id="cell_interest14"  href="javascript:void(0);" class="interest14" onclick="showinterest('cell_interest14', 'check_interest14');"></a>
              <div id="check_interest14" class="yes_d"><input type="hidden" id="text14" value="绘画"/></div>
            </li>
            <li><a id="cell_interest15"  href="javascript:void(0);" class="interest15" onclick="showinterest('cell_interest15', 'check_interest15');"></a>
              <div id="check_interest15" class="yes_d"><input type="hidden" id="text15" value="舞蹈"/></div>
            </li>
            <li><a id="cell_interest16"  href="javascript:void(0);" class="interest16" onclick="showinterest('cell_interest16', 'check_interest16');"></a>
            <div id="check_interest16" class="yes_d"><input type="hidden" id="text16" value="音乐"/></div>
            </li>
            <li><a id="cell_interest17"  href="javascript:void(0);" class="interest17" onclick="showinterest('cell_interest17', 'check_interest17');"></a>
            <div id="check_interest17" class="yes_d"><input type="hidden" id="text17" value="电影"/></div>
            </li>
            <li><a id="cell_interest18"  href="javascript:void(0);" class="interest18" onclick="showinterest('cell_interest18', 'check_interest18');"></a>
            <div id="check_interest18" class="yes_d"><input type="hidden" id="text18" value="收藏"/></div>
            </li>
            <li><a id="cell_interest19"  href="javascript:void(0);" class="interest19" onclick="showinterest('cell_interest19', 'check_interest19');"></a>
            <div id="check_interest19" class="yes_d"><input type="hidden" id="text19" value="读书"/></div>
            </li>
            <li><a id="cell_interest20"  href="javascript:void(0);" class="interest20" onclick="showinterest('cell_interest20', 'check_interest20');"></a>
            <div id="check_interest20" class="yes_d"><input type="hidden" id="text20" value="话剧"/></div>
            </li>
            <li><a id="cell_interest21"  href="javascript:void(0);" class="interest21" onclick="showinterest('cell_interest21', 'check_interest21');"></a>
            <div id="check_interest21" class="yes_d"><input type="hidden" id="text21" value="手绘"/></div>
            </li>
            <li><a id="cell_interest22"  href="javascript:void(0);" class="interest22" onclick="showinterest('cell_interest22', 'check_interest22');"></a>
            <div id="check_interest22" class="yes_d"><input type="hidden" id="text22" value="瑜伽"/></div>
            </li>
            <li><a id="cell_interest23"  href="javascript:void(0);" class="interest23" onclick="showinterest('cell_interest23', 'check_interest23');"></a>
            <div id="check_interest23" class="yes_d"><input type="hidden" id="text23" value="泡吧"/></div>
            </li>
            <li><a id="cell_interest24"  href="javascript:void(0);" class="interest24" onclick="showinterest('cell_interest24', 'check_interest24');"></a>
            <div id="check_interest24" class="yes_d"><input type="hidden" id="text24" value="集邮"/></div>
            </li>
            <li><a id="cell_interest25"  href="javascript:void(0);" class="interest25" onclick="showinterest('cell_interest25', 'check_interest25');"></a>
            <div id="check_interest25" class="yes_d"><input type="hidden" id="text25" value="红酒"/></div>
            </li>
            <li><a id="cell_interest26"  href="javascript:void(0);" class="interest26" onclick="showinterest('cell_interest26', 'check_interest26');"></a>
            <div id="check_interest26" class="yes_d"><input type="hidden" id="text26" value="咖啡"/></div>
            </li>
            <li><a id="cell_interest27"  href="javascript:void(0);" class="interest27" onclick="showinterest('cell_interest27', 'check_interest27');"></a>
            <div id="check_interest27" class="yes_d"><input type="hidden" id="text27" value="美食"/></div>
            </li>
            <li><a id="cell_interest28"  href="javascript:void(0);" class="interest28" onclick="showinterest('cell_interest28', 'check_interest28');"></a>
            <div id="check_interest28" class="yes_d"><input type="hidden" id="text28" value="美容"/></div>
            </li>
            <li><a id="cell_interest29"  href="javascript:void(0);" class="interest29" onclick="showinterest('cell_interest29', 'check_interest29');"></a>
            <div id="check_interest29" class="yes_d"><input type="hidden" id="text29" value="游戏"/></div>
            </li>
            <li><a id="cell_interest30"  href="javascript:void(0);" class="interest30" onclick="showinterest('cell_interest30', 'check_interest30');"></a>
            <div id="check_interest30" class="yes_d"><input type="hidden" id="text30" value="时尚"/></div>
            </li>
            <li><a id="cell_interest31"  href="javascript:void(0);" class="interest31" onclick="showinterest('cell_interest31', 'check_interest31');"></a>
            <div id="check_interest31" class="yes_d"><input type="hidden" id="text31" value="探索"/></div>
            </li>
            <li><a id="cell_interest32"  href="javascript:void(0);" class="interest32" onclick="showinterest('cell_interest32', 'check_interest32');"></a>
            <div id="check_interest32" class="yes_d"><input type="hidden" id="text32" value="冒险"/></div>
            </li>
            <li><a id="cell_interest33"  href="javascript:void(0);" class="interest33" onclick="showinterest('cell_interest33', 'check_interest33');"></a>
            <div id="check_interest33" class="yes_d"><input type="hidden" id="text33" value="赚钱"/></div>
            </li>
            <li><a id="cell_interest34"  href="javascript:void(0);" class="interest34" onclick="showinterest('cell_interest34', 'check_interest34');"></a>
            <div id="check_interest34" class="yes_d"><input type="hidden" id="text34" value="购物"/></div>
            </li>
            <li><a id="cell_interest35"  href="javascript:void(0);" class="interest35" onclick="showinterest('cell_interest35', 'check_interest35');"></a>
            <div id="check_interest35" class="yes_d"><input type="hidden" id="text35" value="宅"/></div>
            </li>
          </ul>
        </div>
        <div class="interest_info_right"> <span>你的兴趣真广泛，你可以在这里补充你的兴趣，打造你的黄金人脉圈。</span>
          <div class="clear"></div>
          <p>我还喜欢：</p>
          <div class="clear"></div>
          <textarea></textarea >
          <p><a href="javascript:void(0);">添加</a></p>
        </div>
        
        
        
     
</div>


<div class="submit_middle_right1" style="border-bottom:none;">
      <div class="cj" id="savehobbyDIV"><a href="javascript:void(0);" onclick="savehobby()"></a></div>
    </div>


</div>
</div>
</body>
</html>
