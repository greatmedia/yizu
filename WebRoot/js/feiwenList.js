$(function(){
	getFeiwenList();
});

function getFeiwenList(){
	var indexFeiwenURL="feiWenAction_selectFeiWen.do";
	$.ajax({
		url : indexFeiwenURL,
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#list").html("");
			var feiWenTypes = data.data.feiWenTypes;
			for ( var i = 0; i < feiWenTypes.length; i++) {
				var tid = feiWenTypes[i].tid;
				var type = feiWenTypes[i].title;
				var feiwen = feiWenTypes[i].feiWen;
				
				if(feiwen.length>0){
					for ( var f = 0; f < feiwen.length; f++) {
						var title = feiwen[f].title;
						var titleStr = feiwen[f].title;
						var content = feiwen[f].content;
						var createdatatime = feiwen[f].createdatetime;
						var fwid = feiwen[f].fwid;
						var tid = feiwen[f].tid;
						var unick = feiwen[f].userinfo.nick;
						if(title.length>40){
							title = title.substr(0,40);
							title = title+"..";
						}
						
						var feiwenHTML = 
							"<tr>"+
							"<td>"+unick+"</td>"+
							"<td>"+createdatatime+"</td>"+
							"<td>"+type+"</td>"+
							"<td><a href='javascript:void(0)' onclick=\"openFeiwen('"+fwid+"','"+tid+"')\">"+title+"</a></td>"+
							"<td><a href='javascript:void(0)' onclick=\"updateFeiwen('"+fwid+"')\">编辑</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"deleteFeiwen('"+fwid+"')\">删除</a></td>"+
							"</tr>";
					   		
    					$("#list").append(feiwenHTML);
					}
				}
			}
		},
		error : function(data) {
			location.href=basePath;
		}
	});
}
function openFeiwen(fwid,tid)
{
	var url = basePath+"feiwenshow/"+fwid+"?id="+tid;
	window.open(url); 
}
//删除飞闻
function deleteFeiwen(fwid){
	var detelteFeiwenURL = "feiWenAction_deleteFeiwen.do";
	$.ajax({
		url : detelteFeiwenURL,
		data : {id : fwid },
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var res = data.data.res;
			if(res==1){
				getFeiwenList();
			}else{
				var result = data.data.res;
				alert(result);
			}
		},
		error : function(data) {
			alert("删除失败！");
		}
	});
}

//编辑飞闻
function updateFeiwen(fwid){
	window.open(basePath+"feiWenAction_getFeiwen.do?id="+fwid); 
//	location.href=basePath;
}