$(function(){
	var islogin;
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	var type = realurl.substring(idindexof + 1,realurl.search(".html"));
	switch (type) {
		case '1':
			$("#cirlcetop").html("名人逸族");
			break;
		case '2':
			$("#cirlcetop").html("运动逸族");
			break;
		case '3':
			$("#cirlcetop").html("休闲逸族");
			break;
		case '4':
			$("#cirlcetop").html("文化逸族");
			break;
		case '5':
			$("#cirlcetop").html("职场逸族");
			break;
		case '6':
			$("#cirlcetop").html("女性逸族");
			break;
		case '7':
			$("#cirlcetop").html("生活逸族");
			break;
		case '8':
			$("#cirlcetop").html("缤纷逸族");
			break;
	}
	moreCircleFormTag(type);
});

function moreCircleFormTag(type){
	var moreCircleFormTagURL = "jsonInfoAction_moreCircleFormTag.do";
	$.ajax({
		url : moreCircleFormTagURL,
		data :{ id : type},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			islogin = data.data.islogin;
			var circleinfos = data.data.morecircleList;
			for ( var j = 0; j < circleinfos.length; j++) {
	    		var circleName = circleinfos[j].circlename;
	    		var d = (j+1);
	    		var str = "" +
	    			"<li style=\"margin-top:30px;\">" +
	    				"<div class=\"ch-item ch-img-"+d+"\" style=\"background-image: url("+circleinfos[j].circlebigimg+");\">" +
	    					"<span style=\"position:absolute; width:220px; font-weight:bold; text-align:center;color: #000;text-shadow: #fff 0 1px 2px;line-height:220px;font-size: 16px;\">"+circleName+"</span>" +
	    					"<div class=\"ch-info\">" +
	    						"<h3><a href=\"cir2/1/"+circleinfos[j].circleid+".html\" style=\"color: #fff;\"\>"+circleName+"</a></h3>" +
	    						"<h2>" +
	    							"<a href=\"javascript:join_quanzi('"+circleinfos[j].circleid+"');\"><img src=\"images/shouye/jia.png\"/></a>" +
	    							"<a href=\"javascript:addFavorite('"+circleinfos[j].circleid+"');\"><img src=\"images/shouye/xin.png\"/></a>" +
	    							"<a href=\"cir2/1/"+circleinfos[j].circleid+".html\"><img src=\"images/shouye/hua.png\"/></a>" +
	    						"</h2>" +
	    						"<p>圈主：<a href=\"user/"+circleinfos[j].userinfo.userid+".html\" style=\"color: #fff;\">"+circleinfos[j].userinfo.nick+"</a></p>" +
	    						"<h1><a href=\"user/"+circleinfos[j].userinfo.userid+".html\"><img src=\""+circleinfos[j].userinfo.image+"\" style=\"width:80px;height:80px;\"/></a></h1>" +
	    					"</div>" +
	    				"</div>" +
	    			"</li>";
	    		$("#morecircleinfo").append(str);
			}
		},error : function(data){
			
		}
	});
}