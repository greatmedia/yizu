
function oProMinMouseOut(obj , cuant1 )
	{
	  var oCutant = document.getElementById(cuant1);
	  oCutant.style.display = "none";
	}
function onProMinMouseOver(obj, cuant1 )
	{
		var oCutant = document.getElementById(cuant1);
		oCutant.style.display = "block";
	}
var sizeCount = 0;
function users(){
	var getUser="myVoteInfoAction_getUser.do";
	$.ajax({
		url : getUser,
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var user=data.data.user;
			if(user==null||user==""){
				location.href=""+basePath+"vote/main.jsp";
			}
		},
		error : function(data) {
			alert(data);
		}
	});
}
$(function(){
		users();
		sizeCount = 0;
        $("#file_upload").omFileUpload({
        	swf : 'js/om-fileupload.swf',
        	width: 80,
            action : '../myVoteInfoAction_swfUpload.do',
            buttonImg : 'images/btn_10.jpg',
            multi : true,
            autoUpload : true,
            queueSizeLimit : 4,
            fileExt : '*.jpg;*.png;*.gif;*.jpeg',
            fileDesc : "选择图片 *.jpg;*.png;*.gif;*.jpeg",
            sizeLimit : 1024*1024*20,
            onSelect : function(ID, fileObj, event){
            	if(sizeCount != 0 && sizeCount/1024/1024 > 20){
            		alert("亲，您上传的图片已经超过20MB了，重新发布一个内容再添加把！");
            	}
            },
            onComplete : function(ID,fileObj,response,data,event){
            	
            	sizeCount += fileObj.size;
            	$('#file_upload').omFileUpload({sizeLimit:1024*1024*20-sizeCount});
            	
                var jsonData = eval('(' + $.trim(response) + ')');
                if(jsonData.msg=="h")
                {
                    alert("图片高度应大于240像素...");
                    $("#file_upload"+ID+"").css("display","none");
                    return false;
                }
                if(jsonData.msg=="w")
                {
                    alert("宽度大于630像素...");
                   $("#file_upload"+ID+"").css("display","none");
                    return false;
                }
                if(jsonData.msg=="ex")
                {
                    alert("图片高度应大于240像素，宽度大于630像素...");
                   $("#file_upload"+ID+"").css("display","none");
                    return false;
                }
                $("#yin").css("display","block");
		  		var imgHtml = "<li>"+
					"<img src='images/close_ico.gif' style='color: blue;position: absolute;' onclick='ajaxDeleteCurrentFile(\""+jsonData.fileUrl+"\", this);'/><img src='"+jsonData.fileUrl+"' />"+
					"</li>";
	            $("#uploadImages").html($("#uploadImages").html() + imgHtml);
            }
        });
    });
    
    function ajaxDeleteCurrentFile(url, obj){
    	$.ajax({
            url:"circleInfoAction_ajaxDeleteCurrentFile.do",
            type: 'post',
            dataType: 'json',
            data : {
                "filename":url
            },
            beforeSend:function(){
            	$(obj).attr("src","images/loading_32.gif");
            },
            success:function(data){
            	if(data.flag){
            		$(obj).parent().remove();
            	}else{
            		alert(data.msg);
            		$(obj).attr("src","images/close_ico.gif");
            	}
            },
            error:function(){
               alert("删除失败，请稍后重试。");
               $(obj).attr("src","images/close_ico.gif");
            }
        });
    }

