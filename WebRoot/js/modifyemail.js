function getQueryStringRegExp(i)
{
	var reg = new RegExp("(^|\\?|&)"+ i +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
var id = getQueryStringRegExp('id');
var uid = getQueryStringRegExp('uid');
modifyEmail(id,uid);
function modifyEmail(id,uid)
{
	var centerAction_modifyEmail="centerAction_modifyEmail.do";
	$.ajax({
	url:centerAction_modifyEmail,
	data:{id : id,uid : uid},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		var result = data.data.result;
		alert(result);
		location.href='http://1mily.com';
	},error:function(data)
	{
		location.href='http://1mily.com';
	}
});
}