//城市三级联动
function initProvince(){
	var initProvinceURl = "userInfoAction_getProvinceAndCity.do";
	$.ajax({
		url:initProvinceURl,
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var provinceList = data.data.provinceList;
			$("#province").html("");
			for ( var p = 0; p < provinceList.length; p++) {
				var str = "<option value=\""+provinceList[p].provinceid+"\">" +
				""+provinceList[p].name+"" +
				"</option>";
				$("#province").append(str);
			}
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
//保存用户信息
var habby = [];
function saveUserInfo(){
	var sex = $("input:radio[name=sex][checked]").val();//性别
	var birthday = $("#year").val()+"-"+$("#month").val()+"-"+$("#day").val();
	var ul= document.getElementById("habby");
	var li = ul.getElementsByTagName("li");
	for(var j = 1;j<=li.length;j++){
		if(document.getElementById("check_interest"+j+"").className == "yes_d_no"){
			habby.push(document.getElementById("text"+j+"").value);
		}
	}
//	alert(habby);
//	return;
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var town = $("#town").find("option:selected").text();
	var address;
	if(city == "市辖区" || city == "县"){
		address =province+"-"+town;
	}else if(town == "市辖区"){
		address =province+"-"+city;
	}else{
		address =province+"-"+city+"-"+town;
	}
	var what = $("#userwhat").val();
	if($.trim(what).length > 140){
		alert("请简短的描述自己，140字以内！")
	}
	var saveUserInfoURL = "userInfoAction_saveUserInfo.do";
	$.ajax({
		url:saveUserInfoURL,
		data : {
			sex : sex, 
			birthday : birthday,
			habby : habby.join(","),
			address : address,
			what : what},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var flag = data.flag;
			if(flag){
				location.href = "centerAction_uploadphoto.do";
			}
		},error:function(data)
		{
		}
	});
	
}
function insertmorehabby(){
	var habbymore = $("#habbymore").val();
	habby.push(habbymore);
}
