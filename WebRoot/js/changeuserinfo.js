var habby = [];
function updateuserinfo(){
	var nick = $("#usernick").val();
	var sex = $("input:radio[name=sex][checked]").val();//性别
	var birthday = $("#year").val()+"-"+$("#month").val()+"-"+$("#day").val();
	var ul= document.getElementById("habby");
	var li = ul.getElementsByTagName("li");
	for(var j = 1;j<=li.length;j++){
		if(document.getElementById("check_interest"+j+"").className == "yes_d_no"){
			habby.push(document.getElementById("text"+j+"").value);
		}
	}
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var town = $("#town").find("option:selected").text();
	var address;
	if(city == "市辖区" || city == "县"){
		address =province+""+town;
	}else if(town == "市辖区"){
		address =province+""+city;
	}else{
		address =province+""+city+""+town;
	}
	var what = $("#userwhat").val();
	if($.trim(what).length > 140){
		alert("请简短的描述自己，140字以内！")
	}
	var updateuserinfoURL = "userInfoAction_updateuserinfo.do";
	$.ajax({
		url:updateuserinfoURL,
		data : {
			name :nick,
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
				location.href = "/uploadphoto.html";
			}
		},error:function(data)
		{
		}
	});
	
}