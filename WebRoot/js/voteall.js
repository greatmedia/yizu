$(function(){
  var str = "<input type=\"text\" class=\"input\" name=\"keywords\" id=\"keywords\" onkeydown=\"subkeydownVotes(event);\" onfocus=\"focusandblurVote(1)\" onblur=\"focusandblurVote(2)\"/><input type=\"button\" onclick=\"searchVote()\" class=\"input_1\"/>";
	$("#searchtype").html(str);
	$("#keywords").val("搜索投票");
});
var keyinfovote = "搜索投票";
function focusandblurVote(tp){
	var keywords = document.getElementById("keywords");
	if(tp == 1){
		if(keywords.value == keyinfovote){
			keywords.value="";
		}
	}else{
		if(keywords.value.length == 0){
			keywords.value = keyinfovote;
		}
	}
}
function searchVote()
{
	var keywords = $("#keywords").val()
	if(keywords=='搜索投票')
	{
		$("#keywords").val("");
		alert("请输入搜索内容！");
		return false;
	}else{
		window.open(basePath+"vote/searchVote.jsp?keyword="+$("#keywords").val());
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
function subkeydownVotes(e)
{
	var key = window.event ? e.keyCode : e.which;
	if(key==13){
		searchVote();
	 }
}