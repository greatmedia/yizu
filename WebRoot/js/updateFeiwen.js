getType();
function getids()
{
	var type = document.getElementById("feiwenType");
    var typeId = type.options [type.selectedIndex].id;
}
function getType(){
	var gettypeURL="feiWenAction_create.do";
	$.ajax({
		url : gettypeURL,
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var islogin = data.data.islogin;
			if(islogin)
			{
				var feiWenType = data.data.feiWenType
				for ( var i = 0; i < feiWenType.length; i++) {
					if(i==($("#tid").val()-1)){
						continue;
					}
					$("#feiwenType").append("<option id="+feiWenType[i].tid+">"+feiWenType[i].title+"</option>");
				}
			}else{
				alert("你还没有登录!");
				$("#window_dl").css("display","block");
				return false;
			}
		},
		error : function(data) {
			alert(data);
		}
	});
}

function updateFeiwen(){
	var title = $("#title").val();
	var fwid = $("#fwid").val();
	if(title.length<=1)
	{
		alert("请填写标题！");
		return false;
	}
	
	var type = document.getElementById("feiwenType");
    var typeId = type.options [type.selectedIndex].id;
    var content = $("#content").val();
    if(content.length<=1)
	{
		alert("请填写飞闻内容！");
		return false;
	}
	$("#isfabu").html("内容更新中，请稍等...");
	var updateFeiwenURL="feiWenAction_updateFeiwen.do";
	$.ajax({
		url : updateFeiwenURL,
		data:{tid : typeId,title : title,content : content,fid:fwid},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#isfabu").html("<input type=\"button\"  id=\"formSubmit\" value=\"发布\"/>");
			var islogin = data.data.islogin;
			if(islogin)
			{
				var result = data.data.result;
				alert(result);
				location.href=basePath+"feiwen/feiwenList.jsp";
			}else{
				alert("你还没有登录!");
				$("#window_dl").css("display","block");
				return false;
			}
		},
		error : function(data) {
			location.href=basePath;
		}
	});
}