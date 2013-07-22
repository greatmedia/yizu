feiwen()
function feiwen()
{
	var icontentURL="phoneFeiwenAction_content.do";
	$.ajax({
		url : icontentURL,
		data:{id : id},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var result = data.data.result;
			if(result==ERROR)
			{
				return false;
			}else{
				var feiwen = data.data.feiwen;
				$("#title").html(feiwen.title);
				$("#datetime").html(feiwen.createdatetime);
				$("#content").html(feiwen.content);
			}
		},
		error : function(data) {
		}
	});
}