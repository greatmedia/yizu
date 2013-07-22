$(function(){
    $("#upFile").bind("change", function(){
        var img =  $(this).val();
        if( !img.match( /.jpeg|.jpg|.gif|.png|.bmp/i ) ){
            alert('图片格式无效,请选择其他格式文件，支持.jpeg|.jpg|.gif|.png|.bmp！');
            return;
        }
        uploadPic();
    });
});

function saveUserImg(){
	var userid = $("#userid").val();
	var img = $("#userimg").val();
	var saveUserImgURL = "userInfoAction_saveUserImg.do";
	$.ajax({
		url : saveUserImgURL,
		data : {ref:img},
		type : 'POST',
		dataType : 'json',
		beforeSend : function(){
			$("#waitsave").html("正在保存头像，请稍等···");
		},
		success : function(data){
			$("#waitsave").html("");
			$("#waitsave").append("<a href=\"javascript:void(0);\" onclick=\"saveUserImg()\"></a>");
			if(data.flag){
				location.href = basePath+"usercenter_me/"+userid+".html";
			}
		},
		error : function(){
			$("#waitsave").append("<a href=\"javascript:void(0);\" onclick=\"saveUserImg()\"></a>");
		}
	});
}



