$(function(){
  var str = "<input type=\"text\" class=\"input\" name=\"keywords\" id=\"keywords\" onkeydown=\"subkeydownTopics(event);\" onfocus=\"focusandblurTopics(1)\" onblur=\"focusandblurTopics(2)\"/><input type=\"button\" onclick=\"searchTopic()\" class=\"input_1\"/>";
	$("#searchtype").html(str);
	$("#keywords").val("搜索话题");
});
var keyinfoTopic = "搜索话题";
function focusandblurTopics(tp){
	var keywords = document.getElementById("keywords");
	if(tp == 1){
		if(keywords.value == keyinfoTopic){
			keywords.value="";
		}
	}else{
		if(keywords.value.length == 0){
			keywords.value = keyinfoTopic;
		}
	}
}
function searchTopic()
{
	var keywords = $("#keywords").val()
	if(keywords=='搜索话题')
	{
		$("#keywords").val("");
		alert("请输入搜索内容！");
		return false;
	}else{
		window.open(basePath+"quanzi/searchTopic.jsp?keyword="+$("#keywords").val());
	}
}
function clearSearch()
{
	var keywords = $("#keywords").val();
	if(keywords=='搜索投票')
	{
		$("#keywords").val("");
	}
}
function subkeydownTopics(e)
{
	var key = window.event ? e.keyCode : e.which;
	if(key==13){
		searchTopic();
	 }
}