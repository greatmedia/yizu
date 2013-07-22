//http://blog.sina.com.cn/s/blog_6f2b4e370100m1fb.html
//http://blog.csdn.net/ajaxchen_615/article/details/5733371
$(function(){
	sizeCount = 0;
    $("#file_upload").omFileUpload({
    	swf : 'js/om-fileupload.swf',
    	width: '85',
        action : 'userInfoAction_swfUpload.do',
        buttonImg : 'images/cjqz_02.jpg',
        multi : true,
        autoUpload : true,
        queueSizeLimit : 4,
        fileExt : '*.jpg;*.png;*.gif;*.jpeg',
        fileDesc : "选择图片 *.jpg;*.png;*.gif;*.jpeg",
        sizeLimit : 1024*1024*20,
        onSelect : function(ID, fileObj, event){
        	if(sizeCount != 0 && sizeCount/1024/1024 > 20){
        		alert("您上传的图片已经超过20MB了，重新发布一个内容再添加把！");
        	}
        },
        onComplete : function(ID,fileObj,response,data,event){
        	sizeCount += fileObj.size;
        	$('#file_upload').omFileUpload({sizeLimit:1024*1024*20-sizeCount});
            var jsonData = eval('(' + $.trim(response) + ')');
            userAgent=window.navigator.userAgent.toLowerCase();
//        	if(userAgent.indexOf("firefox")>=1)
//        	{
        		firefoxUserimage(jsonData.fileUrl);
//        		alert("剪切头像，不支持火狐浏览器，请换其它浏览器！");
//        		Findex=userAgent.indexOf("firefox/");
//        		versionName=userAgent.substr(Findex+"Firefox/".length,3);
//        		jcrop_api.setImage(jsonData.fileUrl);
//             	$("#imgsm").attr("src",jsonData.fileUrl);
//             	$("#imgpath").attr("src",jsonData.fileUrl);
//             	$("#target").attr("src",jsonData.fileUrl);
//        	}else {
//        		//jcrop_api.setImage(jsonData.fileUrl);
//        		$("#smallimg").attr("src",jsonData.fileUrl);
//             	$("#userimg").attr("src",jsonData.fileUrl);
//             	$("#target").attr("src",jsonData.fileUrl);
//            	window.location.href = "centerAction_uploadphoto.do?id=uploadimg";
//        	}
        },
		error:function(data){
			alert("网络异常!");
		}
    });
});
/**
 * 对火狐浏览器上传图片处理
 * @param firefoxUserimage  图片路径
 * @return
 */
function firefoxUserimage(firefoxUserimage)
{
	var firefoxUserimageURL = "userInfoAction_firefoxUserimage.do";
	$.ajax({
		url:firefoxUserimageURL,
		data:{id : firefoxUserimage},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			window.location.href = "centerAction_uploadphoto.do?id=uploadimg";
		},
		error:function(data){
			alert("网络异常");
		}
	});
}
