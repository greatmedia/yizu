<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改资料</title>
<link href="<%=basePath%>css/user.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script>
var days;
function loaded()
{
	var sex = $("#sexhidden").val();
	if(sex==1)
	{
		$("#sex1")[0].checked=true;
	}
	if(sex==2)
	{
		$("#sex2")[0].checked=true;
	}else{
		$("#sex1")[0].checked=true;
	}
	var birthdayhidden = $("#birthdayhidden").val();
	var birs= new Array(); //定义一数组
	
 	birs=birthdayhidden.split("-"); //字符分割   
 	if(birs.length>1)
 	{
	 	strYYYY = document.theForm.YYYY.outerHTML;
	    strMM = document.theForm.MM.outerHTML;
	    strDD = document.theForm.DD.outerHTML;
	    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	 	
	 	for (s=0;s<birs.length ;s++ )    
	 	{    
	    	var title = birs[s];
	    	if(s==0)
	    	{
	    	 	//先给年下拉框赋内容
			    var y = new Date().getFullYear();
			    var str = strYYYY.substring(0, strYYYY.length - 9);
			    
			    for (var i = (y-101); i < (y+1); i++)
			    {//以今年为准，前30年，后30年
		            if(i==title)
		            {
		           		str += "<option selected=\"selected\" value='" + i + "'> " + i + "年" + "</option>";
		            }else
		            {
		            	str += "<option value='" + i + "'> " + i + "年" + "</option>";
		            }
			    }
			    document.theForm.YYYY.outerHTML = str +"</select>";
	    	}
	   		if(s==1)
	   		{
	   			//赋月份的下拉框
			    var str = strMM.substring(0, strMM.length - 9);
			    for (var i = 1; i < 13; i++){
			    		var months = new Date().getMonth() + 1
			    		if(i==title)
			    		{
			            	str += "<option selected=\"selected\" value='" + i + "'> " + i + "月" + "</option>\r\n";
			            }else{
			            	str += "<option value='" + i + "'> " + i + "月" + "</option>\r\n";
			            }
			    }
			    document.theForm.MM.outerHTML = str +"</select>";
	   		}
	  
	    	if(s==2)
	   		{
	   			days=birs[s];
			    //document.theForm.YYYY.value = y;
			    //document.theForm.MM.value = new Date().getMonth() + 1;
			    var n = MonHead[new Date().getMonth()];
			    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
			    writeDay(n); //赋日期下拉框
			    //document.theForm.DD.value = new Date().getDate();
			    
	    	}
	    }
    }else{
    	strYYYY = document.theForm.YYYY.outerHTML;
    strMM = document.theForm.MM.outerHTML;
    strDD = document.theForm.DD.outerHTML;
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    
    //先给年下拉框赋内容
    var y = new Date().getFullYear();
    var str = strYYYY.substring(0, strYYYY.length - 9);
    
    for (var i = (y-101); i < (y+1); i++){//以今年为准，前30年，后30年
            str += "<option value='" + i + "'> " + i + "年" + "</option>";
    }
    document.theForm.YYYY.outerHTML = str +"</select>";
    
    
    //赋月份的下拉框
    var str = strMM.substring(0, strMM.length - 9);
    for (var i = 1; i < 13; i++){
            str += "<option value='" + i + "'> " + i + "月" + "</option>\r\n";
    }
    document.theForm.MM.outerHTML = str +"</select>";
    
    document.theForm.YYYY.value = y;
    document.theForm.MM.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
    writeDay(n); //赋日期下拉框
    document.theForm.DD.value = new Date().getDate();
    }
    
}

function getCityFromProvince(){
	var type = document.getElementById("province");
    var provinceId = type.options [type.selectedIndex].value;
    var getCityFromProvinceURL = "userInfoAction_getCityFromProvince.do";
    $.ajax({
		url:getCityFromProvinceURL,
		data:{id : provinceId},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var cityList = data.data.cityList;
			$("#city").html("");
			for ( var i = 0; i < cityList.length; i++) {
				var str = "<option value=\""+cityList[i].cityid+"\">" +
						""+cityList[i].name+"" +
						"</option>";
				$("#city").append(str);
			}
			var townList = data.data.townList;
			$("#town").html("");
			for ( var t = 0; t < townList.length; t++) {
				var str = "<option value=\""+townList[t].townid+"\">" +
				""+townList[t].name+"" +
				"</option>";
				$("#town").append(str);
			}
		},error:function(data){
			
		}
	});	
}

function getTownFromCity(){
	var type = document.getElementById("city");
    var cityid = type.options [type.selectedIndex].value;
    var getTownFromCityURL = "userInfoAction_getTownFromCity.do";
    $.ajax({
		url:getTownFromCityURL,
		data:{id : cityid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var townList = data.data.townList;
			$("#town").html("");
			for ( var t = 0; t < townList.length; t++) {
				var str = "<option value=\""+townList[t].townid+"\">" +
				""+townList[t].name+"" +
				"</option>";
				$("#town").append(str);
			}
		},error:function(data){
			
		}
	});	
}


                
function YYYYMM(str){ //年发生变化时日期发生变化(主要是判断闰平年)
        var MMvalue = document.theForm.MM.options[document.theForm.MM.selectedIndex].value;
        if (MMvalue == ""){
                DD.outerHTML = strDD; 
                return;
        }
        var n = MonHead[MMvalue - 1];
        if (MMvalue ==2 && IsPinYear(str)) n++;
        writeDay(n)
}

function MMDD(str){ //月发生变化时日期联动
        var YYYYvalue = document.theForm.YYYY.options[document.theForm.YYYY.selectedIndex].value;
        if (str == ""){DD.outerHTML = strDD; return;}
        var n = MonHead[str - 1];
        if (str ==2 && IsPinYear(YYYYvalue)) n++;
        writeDay(n)
}

function writeDay(n){ //据条件写日期的下拉框
        var s = strDD.substring(0, strDD.length - 9);
        for (var i=1; i<(n+1); i++)
        {
        	if(i==days)
        	{
        		s += "<option selected=\"selected\" value='" + i + "'> " + i + "日" + "</option>\r\n";
        	}else{
        		s += "<option value='" + i + "'> " + i + "日" + "</option>\r\n";
        	}
        }
        document.theForm.DD.outerHTML = s +"</select>";
}


function IsPinYear(year){//判断是否闰平年
 return(0 == year%4 && (year%100 !=0 || year%400 == 0))
 
}
function saveuserdata()
{
	//var sex = $("input:radio[name='sex'][checked]").val();
	var sex =  $('input[name="sex"]:checked').val();
	var birthday = $("#year").val()+"-"+$("#month").val()+"-"+$("#day").val();
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var town = $("#town").find("option:selected").text();
	var nick = $("#nick").val();
	if(nick.length>50)
	{
		alert("昵称太长,不能大于50个字符!");
		return false;
	}
	var what = $("#what").val();
	if(what.length>=200)
	{
		alert("个人描述太长,不能大于200个字符!");
		return false;
	}
	//var address = province+"-"+city+"-"+town;
	var address;
	if(city == "市辖区" || city == "县"){
		address =province+"-"+town;
	}else if(town == "市辖区"){
		address =province+"-"+city;
	}else{
		address =province+"-"+city+"-"+town;
	}
	$("#saveuserdataDIV").css("display","none");
	var saveuserdataURL = "userInfoAction_updatedatasave.do";
    $.ajax({
		url:saveuserdataURL,
		data : {
			sex : sex, 
			nick : nick,
			birthday : birthday,
			address : address,
			what : what},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#saveuserdataDIV").css("display","block");
			var islogin = data.data.islogin;
			if(islogin)
			{
				var status = data.data.status;
				if(status>0)
				{
					var loc = location.href;
					alert("修改成功!");
					window.location.href = loc;
				}else{
					alert("修改失败!");
				}
			}else{
				alert("你还没有登录!");
				window.location.href = "http://1mily.com/";
			}
		},error:function(data){
			$("#saveuserdataDIV").css("display","block");
			alert("网络异常!");
		}
	});	
}
</script>
</head>

<body onload="loaded();">
<input type="hidden" id="sexhidden" value="${user.sex }" />
<input type="hidden" id="birthdayhidden" value="${user.birthday }" />
<input type="hidden" id="addresshidden" value="${user.address }" />
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
      <p>修改你的个人资料</p>
    </div>
<div class="submit_middle_right1">
      <div class="submit_middle_right1_left">
        <p>你的身份：</p>
      </div>
      <div class="submit_middle_right1_right">
        <dl>
          <dt><img src="images/04.jpg" /></dt>
          <dd>
            <input id="sex1" name="sex" type="radio" value="1" />
            <span class="checkbox">逸骑士</span></dd>
        </dl>
        <dl>
          <dt><img src="images/03.jpg" /></dt>
          <dd>
            <input id="sex2" name="sex" type="radio" value="2" />
            <span class="checkbox">逸千金</span></dd>
        </dl>
      </div>
</div>

<div class="submit_middle_right1">
      <div class="submit_middle_right1_left">
        <p>昵称：</p>
      </div>
      <div class="submit_middle_right1_right">
        <input type="text" class="submit_text" id="nick" value="${user.nick }" />
      </div>
</div>

<div class="submit_middle_right1">
      <div class="submit_middle_right1_left">
        <p>你的生日：</p>
      </div>
      <form name="theForm">
      <div class="submit_middle_right1_right">
       <select id="year" class="skin-text-willwhite_zhuce" name="YYYY" onchange="YYYYMM(this.value)"
							style="height: 30px; overflow: auto; width: 85px;">
		</select>
		<select id="month" class="skin-text-willwhite_zhuce" name="MM" onchange="MMDD(this.value)"
			style="height: 30px; overflow: auto; width: 85px;">
		</select>
		<select id="day" class="skin-text-willwhite_zhuce" name="DD"
			style="height: 30px; overflow: auto; width: 85px;">
		</select>
        <p>* 请填写生日信息，我们将会在生日送出逸族网的意外惊喜！</p>
      </div>
      </form>
</div>

<div class="submit_middle_right1">
      <div class="submit_middle_right1_left">
        <p>你来自：</p>
      </div>
      <div class="submit_middle_right1_right">
       <select id="province" class="skin-text-willwhite_zhuce" onchange="getCityFromProvince()"
							style="height: 30px; overflow: auto;">
				<c:forEach items="${listProvinces}" var="list">
					<c:choose>
						<c:when test="${province.provinceid == list.provinceid}">
							<option value="${province.provinceid }" selected="selected">${province.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${list.provinceid }">${list.name }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		</select>
		<select id="city" class="skin-text-willwhite_zhuce" onchange="getTownFromCity()"
			style="height: 30px; overflow: auto;">
			<c:forEach var="citylist" items="${listCitys}">
				<c:choose>
					<c:when test="${city.cityid == citylist.cityid}">
						<option value="${city.cityid }" selected="selected">${city.name }</option>
					</c:when>
					<c:otherwise>
						<option value="${citylist.cityid}">${citylist.name }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<select id="town" class="skin-text-willwhite_zhuce"
			style="height: 30px; overflow: auto;">
			<c:forEach var="townlist" items="${listTowns}">
				<c:choose>
					<c:when test="${town.townid == townlist.townid}">
						<option value="${town.townid }" selected="selected">${town.name }</option>
					</c:when>
					<c:otherwise>
						<option value="${townlist.townid}">${townlist.name }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
      </div>
</div>

<div class="submit_middle_right1">
      <div class="submit_middle_right1_left">
        <p>个人素描：</p>
      </div>
      <div class="submit_middle_right1_right">
      <textarea id="what">${user.what }</textarea >
      <h5>* 请认真填写，最多200个字。</h5>
      </div>
</div>

<div class="submit_middle_right1" style="border-bottom:none;">
      <div class="cj" id="saveuserdataDIV"><a href="javascript:void(0);" onclick="saveuserdata()"></a></div>
    </div>
</div>
</div>
</body>
</html>
