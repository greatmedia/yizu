//http://blog.sina.com.cn/s/blog_6f2b4e370100m1fb.html
//http://blog.csdn.net/ajaxchen_615/article/details/5733371
$(function(){
	sizeCount = 0;
    $("#file_upload").omFileUpload({
    	swf : 'js/om-fileupload.swf',
    	width: '85',
        action : 'circleInfoAction_swfUpload.do',
        buttonImg : 'images/cjqz_02.jpg',
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
            $("#divimgs").html("<img src=\""+jsonData.fileUrl+"\" width=\"220px\" height=\"354px\" class=\"avatar\" />");
            $("#imageaddress").val(jsonData.fileUrl);
        }
    });
});
/**
 * 创建圈子
 * 
 * @return
 */
function createquanzi()
{
    var imageaddress = $("#imageaddress").val();
	imageaddress = imageaddress.substring("15", imageaddress.length);
	var type = document.getElementById("cirtag");
    var typeId = type.options [type.selectedIndex].id;
    var typevalue = type.options [type.selectedIndex].text;
//    alert(typeId+"  " +typevalue);
    var title = $("#text2").val();
    if(title=="给你的圈子取个名字吧"|| title == "" || title == null || title.length <= 0){
        alert("请填写圈子名称");return false;
    }
    if(title.length>=60){
        alert("标题长度不能超过60个字");return false;
    }
    var circletag = $("#text1").val();
    if(circletag == "每个关键字用空格隔开" || circletag == "" || circletag == null || circletag.length <= 0){
    	alert("请填写关键字");return false; 
    }
//    var str = circletag.split(" ");
//    strs = ""
//    if(str.length>3){
//        alert("圈子关键字不能超过3个");return false;
//    }
    var summary = $("#summary").val();
    if(summary == "" || summary == null || summary == "每个关键字用空格隔开" || summary.length <= 0){
    	alert("请填写圈子简介");return false;
    }
    if(title.length>=2000){
        alert("标题长度不能超过200个字");return false;
    }
    var tagslis = document.getElementById("tagsli").getElementsByTagName("li").length;
	var idTag = "";  
    $("#tagsli li").each(function(i,obj){  
		 if(i<tagslis-1)
		 {
			 var ids = $(this).html();
			 var id = ids.substring(34, ids.search("\">"));
			 idTag = idTag+id+",";
		 }
		});
    var def3 = tagslis;
    if(def3==null || def3<=1 || def3==""){
        alert("请选择圈子标签!");return false;
    }
    if(imageaddress.length<10){
    	alert("请上传一张图片");
    	return false;
    }
    $("#wait").css("display","block");
    $("#setting").css("display","none");
	$.ajax({
        url:"circleInfoAction_ajaxCreatequanzi.do",
        type: 'post',
        dataType: 'json',
        data : {
            "instance.circlename":$("#text2").val(),
            "instance.circletag":$("#text1").val(),
            "instance.circlebigimg":$("#images").val(),
            "instance.summary":$("#summary").val(),
            "instance.circlesmallimg":imageaddress,
            "instance.def1":typevalue,
            "instance.def3":typeId,
            "ids":idTag,
        },
        beforeSend:function(){
        },
        success:function(data){
        	$("#wait").css("display","none");
            $("#setting").css("display","block");
            alert(data.msg);
            if(data.flag){
            	var circleid = data.data.circleid;
            	window.location.href = basePath+"cir2/1/"+circleid+".html";
            }
        },
        error:function(){
        	$("#wait").css("display","none");
            $("#setting").css("display","block");
            alert("保存失败");
        }
    });
}
/**
 * 搜索标签
 * 
 * @return
 */
function searchTag()
{
	var tagslis = document.getElementById("tagsli").getElementsByTagName("li").length;
	$.ajax({
        url:"circleInfoAction_ajaxSearchTag.do",
        type: 'post',
        dataType: 'json',
        data : {"id":$("#newTagsInput").val()},
        beforeSend:function(){
        },
        success:function(data){
        	$("#searchTag").html("");
        	var circletags = data.data.circletags;
        	if(circletags.length<=0){
        		$("#searchTag").html("<li style=\"background-color: #e4e4e4; padding-left:10px; line-height: 24px;\"><a href='javascript:void(0);' onclick='ajaxCreateTag();' style='color:#626262;' >创建标签</a></li>");
        		$("#searchTag").css("display","block");
        	}else{
        		var tagsize = circletags.length;
		        for ( var i = 0; i < circletags.length; i++) {
		        	$("#searchTag").css("display","block");
		        	$("#searchTag").append("<li style=\"background-color: #e4e4e4; line-height: 24px; padding-left:10px;\" onclick=\"selectTag("+circletags[i].ctid+",'"+circletags[i].tagname+"');\"><a href='javascript:void(0);' style='color:#626262;'>"+circletags[i].tagname+"</a></li>");
		    	}
        	}
        },
        error:function(){
        }
    });
}
/**
 * 创建标签
 * 
 * @return
 */
function ajaxCreateTag()
{
	var text = $("#newTagsInput").val();
//	alert(text);
	if(text!=null && text!="")
	{
		$.ajax({
	        url:"circleInfoAction_ajaxCreateTag.do",
	        type: 'post',
	        dataType: 'json',
	        data : {"id":text},
	        beforeSend:function(){
	        },
	        success:function(data){
	        	var circletags =  data.data.circletags;
	        	for ( var i = 0; i < circletags.length; i++) {
	        		selectTag(circletags[i].ctid,circletags[i].tagname);
				}
	        },
	        error:function(){
	        }
	    });
	}
}
/**
 * 选择标签
 * 
 * @param ctid
 * @param tagname
 * @return
 */
function selectTag(ctid,tagname)
{
	var div=document.getElementById('tagsli');
	var tagslis = document.getElementById("tagsli").getElementsByTagName("li").length;
//	alert(tagslis);
	if(tagslis>4){return false;}
	var li1 = "";
	$("#tagsli li").each(function(i,obj){if(i<tagslis-1){li1 = li1+ "<li onclick=\"removeli(this)\">"+$(this).html()+"</li>";}});
	var searli = li1.search(ctid);
	if(searli>0){return false;}
	$("#tagsli").html(li1);
	$("#tagsli").append("<li onclick=\"removeli(this)\"><a href=\"javascript:void(0);\" id='"+ctid+"'>"+tagname+"</a><em class=\"delete\">×</em></li>");
	if(tagslis>=4){
		$("#tagsli").append("<li class=\"newtagsinput\" style=\"width:77px\"><input type=\"hidden\" onkeyup=\"searchTag()\" id=\"newTagsInput\" class=\"newtags\" style=\"width:160px; height:15px; font-size:14px;\" autocomplete=\"off\"/></li>");
	}else{
		$("#tagsli").append("<li class=\"newtagsinput\" style=\"width:77px\"><input type=\"text\" onkeyup=\"searchTag()\" id=\"newTagsInput\" class=\"newtags\" style=\"width:160px; height:15px; font-size:14px;\" autocomplete=\"off\"/></li>");
	}
}
function tagit(def3,tagname){
	$("#catgoryinput").html(tagname);$("#def1").val(tagname);$("#def3").val(def3);
}
function onmouseoutUL(){
	window.setTimeout(function(){$("#searchTag").css("display","none");}, 5000)
}
function onmouseoverUL(){
	$("#searchTag").css("display","block");}
function removeli(e){
	$(e).remove();
	var lengths = document.getElementById("tagsli").getElementsByTagName("li").length;
	if(lengths < 6){var inp = document.getElementById("newTagsInput"); inp.type="text";}
}
