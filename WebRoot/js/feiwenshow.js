$(function(){
	var realurl = location.href;
	var fstart = realurl.lastIndexOf("/");
	var tstart = realurl.lastIndexOf("=");
	var fwid = realurl.substring(fstart + 1,realurl.search(".html"));
	var tid =  realurl.substring(tstart + 1);
	feiwenType(tid);
	getFeiwenList(fwid,tid);
	var feiwenByTid;
});
function feiwenType(tid){
	switch (tid) {
	case '1':
		document.title = "飞闻·娱乐";
		break;
	case '2':
		document.title = "飞闻·运动";
		break;
	case '3':
		document.title = "飞闻·社会";
		break;
	case '4':
		document.title = "飞闻·商业";
		break;
	case '5':
		document.title = "飞闻·自然";
		break;
	case '6':
		document.title = "飞闻·才俊";
		break;
	}
}

function getFeiwenList(fwid,tid){
	var getFeiwenListURL = "feiWenAction_getFeiwenByTid.do";
	$.ajax({
		url : getFeiwenListURL,
		data :{tid : tid},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var count = data.data.count;
//			alert(count);
			if(count <= 0){
				alert("该分类下还没有飞闻！");
				location.href = basepath;
			}
			feiwenByTid = data.data.feiwenByTid;
			var n = 0;
			changefeiwen(n);
			autobox(n);
		},
		error :function(data){
			alert("error");
		}
	});
}

function changefeiwen(n){
	$("#box_ul").html("");
	for ( var i = 0; i <feiwenByTid.length; i++) {
		var title = feiwenByTid[i].title;
		var content = feiwenByTid[i].content;
		var m_createTime = feiwenByTid[i].createdatetime;
		var m_week = getDayOfWeek(m_createTime);
		var m_date = m_createTime.replace("-","年").replace("-","月").replace("-","日");
		var m_time = m_date.substring(0,m_date.indexOf(" "))+"日";
		
		//中间
		if(i == n){
			$("#pa"+n+"").addClass("active");
			var str = " <li id=\"centerbox"+i+"\" style=\"display:block;\">"+
			"<div class=\"fei_title\">"+
           	"<div class=\"fei_su\">"+
              "<h2>"+title+"</h2>"+
           "</div>"+
           "<div class=\"fei_time\">"+m_time+"<br />"+m_week+"</div>"+
           "</div>"+
           "<div class=\"fei_wenzi\">"+feiwenByTid[n].content+"</div>"+
           "</li>";
			$("#box_ul").append(str);
//			var b= feiwenByTid[n].content.lastIndexOf("src");
//			var e = feiwenByTid[n].content.lastIndexOf("alt");
//			var path = feiwenByTid[n].content.substring((b+5),(e-2));
			var str =
				"<script type=\"text/javascript\" >"+
				"var jiathis_config={"+
				"title:\""+title+"\","+
//				"pic:\""+path+"\","+
				"data_track_clickback:false,"+
				"appkey:{"+
				"\"tsina\":\"2614308697\",\"renren\":\"184905\",\"t163\":\"mBjMmCCPvbKOvvQI\",\"tqq\":\"100255656\" "+
				"}"+
				"}"+
				"</script>";
				$("#fenxiang").append(str);
		}else{
			$("#pa"+i+"").removeClass("active");
			var str = " <li id=\"centerbox"+i+"\" style=\"display:none;\">"+
			"<div class=\"fei_title\">"+
           	"<div class=\"fei_su\">"+
              "<h2>"+title+"</h2>"+
           "</div>"+
           "<div class=\"fei_time\">"+m_time+"<br />"+m_week+"</div>"+
           "</div>"+
           "<div class=\"fei_wenzi\">"+content+"</div>"+
           "</li>";
			$("#box_ul").append(str);
		}
		
		//左边
		if(n == 0){
			$("#fei_left").css("display","none");
		}else{
//			var l_createTime = feiwenByTid[n-1].createdatetime;
			var l_week = getDayOfWeek(feiwenByTid[n-1].createdatetime);
			var l_date = feiwenByTid[n-1].createdatetime.replace("-","年").replace("-","月").replace("-","日");
			var l_time = l_date.substring(0,l_date.indexOf(" "))+"日";
			$("#fei_left").css("display","block");
			var left = "<div class=\"fei_leftime\" onclick=\"changefeiwen("+(n-1)+")\">"+
			""+l_time+"<br />"+l_week+" "+ 
			"</div>"+
			"<div class=\"fei_letitle\" onclick=\"changefeiwen("+(n-1)+")\">"+feiwenByTid[n-1].content+"</div>";
			$("#fei_left").html(left);
		}
		//右边
		if(n ==(feiwenByTid.length-1)){
			$("#fei_right").css("display","none");
		}else{
			$("#fei_right").css("display","block");
			var right = "<div class=\"fei_rightime\" onclick=\"changefeiwen("+(n+1)+")\">"+
			"<h2>"+feiwenByTid[n+1].title+"</h2>"+
			"</div>"+
			"<div class=\"fei_rigtitle\" onclick=\"changefeiwen("+(n+1)+")\">"+feiwenByTid[n+1].content+"</div>";
			$("#fei_right").html(right);
		}
	}
	
}
function getDayOfWeek(dayValue){ 
//	var dayValue = obj.value; 
	var day = new Date(Date.parse(dayValue.replace(/-/g, '/'))); //将日期值格式化 
	var today = new Array("星期天","星期一","星期二","星期三","星期四","星期五","星期六"); 
	return today[day.getDay()];
	//day.getDay();根据Date返一个星期中的某其中0为星期日 
//	document.getElementById("show").innerHTML = today[day.getDay()]; 
	} 