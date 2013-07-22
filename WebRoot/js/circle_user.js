var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var cid = realurl.substring(idindexof + 1,realurl.search(".html"));
showUser(49,1,1);
function showUser(rows,pageNumber,ttp)
{
	var circleUserURL = "circleDetails_getCircleAllUser.do";
		$.ajax({
			url:circleUserURL,
			data:{id : cid,pageNum : pageNumber,pageSize : rows,type : ttp},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
//				$("#allcircleuser").html("");
				var alluser = data.data.circleAlluser;
				for(var i =0;i<alluser.length;i++)
				{
					if(alluser[i].userinfo!=null)
					{
						var img = alluser[i].userinfo.image;
						if(img.length<=1)
						{
							img = "../images/nobody.gif";
						}
						var sex = alluser[i].userinfo.sex;
						var xingbieclass;
						var xingbie;
						if(sex == 2){
							xingbieclass = "nvhuiyuan"
							xingbie = "女";
						}else{
							xingbieclass = "nanhuiyuan"
							xingbie = "男";
						}
						var hobby = alluser[i].userinfo.hobby;
						var str = "" +
							"<dl>" +
								"<dt><a href=\"user/"+alluser[i].userinfo.userid+".html\"><img src=\""+img+"\" title=\""+alluser[i].userinfo.nick+"\"/></a></dt>" +
								"<dd>" +
									"<a href=\"user/"+alluser[i].userinfo.userid+".html\" style=\"color:#626262;\">"+alluser[i].userinfo.nick+"</a><br />" +
									"<ul>" +
										"<li class=\""+xingbieclass+"\">性别："+xingbie+"</li>" +
										"<li class=\"dizhi\">地址："+alluser[i].userinfo.address+"</li>" +
										"<li class=\"aihao\">兴趣爱好："+hobby+"</li>" +
									"</ul>" +
									"<div class=\"clear\"></div>" +
									"<p>个人素描："+alluser[i].userinfo.what+"</p>" +
								"</dd>" +
							"</dl>";
						$("#allcircleuser").append(str);
					}
				}
				if(ttp == 1){
					var count = data.data.count;
					if(count<1)
					{
						$("#allcircleuser").append("<h1>本圈子还没有最新的成员..</h1>");
					}
					if(count >49){
						$("#allcircleuser_more").show();
					}
				}else{
					var n = pageNumber+1;
					if(alluser.length == 49){
						$("#allcircleuser_more").html("");
						$("#allcircleuser_more").html("<a href=\"javascript:showUser(49,"+n+",2)\">加载更多</a>");
					}else{
						$("#allcircleuser_more").html("");
						$("#allcircleuser_more").hide();
					}
				}
			},
			error:function(data)
			{
				$("#allcircleuser").html("<h1>成员加载失败，请重新刷新页面试试..</h1>");
			}
	});
}
function flipPage(rows,pageNumber,id,ttp) {
	showUser(rows,pageNumber,id,ttp);
}