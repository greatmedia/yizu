getType();
function getid()
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
					$("#feiwenType").append("<option id="+feiWenType[i].tid+">"+feiWenType[i].title+"</option>");
				}
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
function createFeiwen()
{
	var title = $("#title").val();
	if(title.length<=1)
	{
		alert("请填写标题！");
		return false;
	}
	
	var type = document.getElementById("feiwenType");
    var typeId = type.options [type.selectedIndex].id;
    var content = $("#content").val();
    var tag = $("#feiwentag").val();
    if(content.length<=1)
	{
		alert("请填写飞闻内容！");
		return false;
	}
	$("#isfabu").html("内容发布中，请稍等...");
	var gettypeURL="feiWenAction_createFeiwen.do";
	$.ajax({
		url : gettypeURL,
		data:{id : typeId,title : title,content : content,feiwentag: tag},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#isfabu").html("<input type=\"button\"  id=\"formSubmit\" value=\"发布\"/>");
			var islogin = data.data.islogin;
			if(islogin)
			{
				var result = data.data.result;
				if(result==1){
					alert("创建成功");
					location.href=basePath;
				}else{
					alert("创建失败,您发布的内容过长！");
				}
				
//				location.href=basePath+"feiwen/feiwenList.jsp";
//				location.href=basePath;
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
	