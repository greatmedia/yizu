var urlinfo = window.location.href;// 獲取url
var url = decodeURI(urlinfo)
var idindexof = url.lastIndexOf("=");
var str = url.substring(idindexof + 1);
searchTopics();
function searchTopics() {
	var searchTopicsURl = "topicAction_searchTopic.do";
	$.ajax( {
		url : searchTopicsURl,
		data : {
			pageNum : 0,
			pageSize : 50,
			content : str
		},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			var islogin = data.data.islogin;
			var titlecount = data.data.titlecount;
			var titleTopics = data.data.titleTopics;
			$("#keywords").val(str);
			if(titleTopics.length<1)
			{
				$("#searchTopic").html("没有你要搜索的话题信息！");
				return false;
			}
			for ( var k = 0; k < titleTopics.length; k++) {
				/**
				 * 判断用户头像
				 */
				var userimg = titleTopics[k].userinfo.image;
				if (userimg.length < 5) {
					userimg = titleTopics[k].userinfo.otheraccountuserimage;
				}
				if (userimg.length < 2) {
					userimg = "images/nobody.gif";
				}
				/**
				 * p判断是否是PK图片
				 */
				var ispk = titleTopics[k].def2;
				/**
				 * 判断内容
				 */
				var content = titleTopics[k].circlecontent;
				if (content.length > 70) {
					content = content.substring(0, 70);
				}
				/**
				 * 是否已经是关注的图片
				 */
				var isgzimg = "";
				var isgz = titleTopics[k].isgz;
				if (isgz == 1) {
					isgzimg = "images/pro_importan_1t.gif";
				} else {
					isgz = 0;
					isgzimg = "images/pro_important.gif";
				}

				var tid = titleTopics[k].circledetailid;
				var title = titleTopics[k].title;
				var nick = titleTopics[k].userinfo.nick;
				var userid = titleTopics[k].userinfo.userid;
				var datetime = titleTopics[k].createdatetime;
				// var comcount = pkTopics[k].comcount;
		var html = "searchTopic";
		var nowCount = titleTopics.length;

		var circleCommentInfo = titleTopics[k].circleCommentInfo;
		var answnick;
		for ( var j = 0; j < circleCommentInfo.length; j++) {
			answnick = circleCommentInfo[j].userinfo.nick;
		}
		if (circleCommentInfo.length <= 0) {
			answnick = "";
		}
		// var comcount = circleCommentInfo.length;
		var comcount = titleTopics[k].comcount;
		var circleDetailImg = titleTopics[k].circleDetailImg;
		writeHTML(tid, title, isgz, isgzimg, content, ispk, userimg, nick,
				datetime, comcount, html, nowCount, k, 3, answnick,
				circleDetailImg, 1, 1, 50, userid);

	}
},
error : function(data) {
}
	});
}

/**
 * 写入HTML
 * 
 * @param tid
 *            话题ID
 * @param title
 *            话题标题
 * @param isgz
 *            是否已经关注该话题
 * @param isgzimg
 *            是否是PK话题图片
 * @param content
 *            话题内容
 * @param ispk
 *            是否是PK话题
 * @param userimg
 *            发表话题的头像
 * @param nick
 *            发表话题的用户昵称
 * @param datetime
 *            发表时间
 * @param comcount
 *            话题评论的数量
 * @param html
 *            要写入到的div
 * @param count
 *            话题数量
 * @param index
 *            索引
 * @param type
 *            类型
 * @param answnick
 *            回答问题的用户昵称
 * @param circleDetailImg
 *            图片
 * @param rows
 *            多少数据
 * @param pageNumber
 *            当前页
 * @param counts
 *            数量
 * @param userid
 *            用户ID
 */
function writeHTML(tid, title, isgz, isgzimg, content, ispk, userimg, nick,
		datetime, comcount, html, count, index, type, answnick,
		circleDetailImg, rows, pageNumber, counts, userid) {
	var topic_subnav = document.createElement("div");
	topic_subnav.className = "topic_subnav";
	$("#" + html + "").append(topic_subnav);

	var topic_l = document.createElement("div");
	topic_l.className = "topic_l";
	topic_subnav.appendChild(topic_l);

	var topic_l_a = document.createElement("a");
	topic_l_a.href = "user/" + userid+".html";
	topic_l_a.target = "_blank";
	topic_l.appendChild(topic_l_a);

	var topic_l_a_img = document.createElement("img");
	topic_l_a_img.src = userimg;
	topic_l_a_img.wdith = "50";
	topic_l_a_img.height = "50";
	topic_l_a.appendChild(topic_l_a_img);

	var topic_r1 = document.createElement("div");
	topic_r1.className = "topic_r1";
	topic_subnav.appendChild(topic_r1);

	var topic_r = document.createElement("div");
	topic_r.className = "topic_r";
	topic_r1.appendChild(topic_r);
	if (ispk != 1) {
		var pro_tiilt_a = document.createElement("a");
		pro_tiilt_a.target = "_blank";
		pro_tiilt_a.href = "topic_tu/" + tid+".html";
		topic_r.appendChild(pro_tiilt_a);

		var pro_tiilt = document.createElement("div");
		pro_tiilt.className = "pro_tiilt";
		pro_tiilt_a.appendChild(pro_tiilt);

		var pro_tiilt_h2 = document.createElement("h2");
		pro_tiilt_h2.innerHTML = title;
		pro_tiilt.appendChild(pro_tiilt_h2);

		var top_impo = document.createElement("div");
		top_impo.className = "top_impo";
		pro_tiilt.appendChild(top_impo);

		var top_impo_a = document.createElement("a");
		top_impo_a.id = "top_impo_a" + type + tid;
		top_impo_a.href = "javascript:void(0)";
		// top_impo_a.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
		top_impo.appendChild(top_impo_a);

		// var top_impo_a_img = document.createElement("img");
		// top_impo_a_img.id="isimg"+type+tid;
		// top_impo_a_img.src=isgzimg;
		// top_impo_a_img.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
		// // top_impo_a_img.title="ssssssssssssssssssssssssss";
		// top_impo_a.appendChild(top_impo_a_img);
		var img = "<img src=\"" + isgzimg + "\" id=\"isimg" + type + tid
				+ "\" onclick=\"gzTopic('" + tid + "'," + isgz + "," + type
				+ ");\"  />";
		// alert(img);
		$("#top_impo_a" + type + tid).append(img);

		var topic_r_span = document.createElement("span");

		topic_r_span.id = "userTopic" + type + tid;

		topic_r.appendChild(topic_r_span);

		var topic_r_span_strong = document.createElement("strong");
		topic_r_span.appendChild(topic_r_span_strong);

		var topic_r_span_strong_a = document.createElement("a");
		topic_r_span_strong_a.href = "user/" + userid+".html";
		topic_r_span_strong_a.target = "_blank";
		topic_r_span_strong_a.innerHTML = nick;
		topic_r_span_strong.appendChild(topic_r_span_strong_a);

		$("#userTopic" + type + tid).append("&nbsp;&nbsp;发表于" + datetime + "");

		var p = document.createElement("div");
		p.className = "topic_r_p";
		p.innerHTML = content;
		topic_r.appendChild(p);
		if (circleDetailImg.length > 0) {
			for ( var z = 0; z < circleDetailImg.length; z++) {
				if (z > 0) {
					break;
				}
				var topic_move = document.createElement("div");
				topic_move.className = "topic_move";
				topic_r1.appendChild(topic_move);

				var topic_move_div = document.createElement("div");
				topic_move_div.id = "topic_move_div" + type + tid;
				topic_move.appendChild(topic_move_div);
				var topic_move_div_img = "<a href=\"topic_tu/" + tid
						+ ".html\" target=\"_blank\"><img src="
						+ circleDetailImg[z].bigimg + " onload="
						+ scaleImage(this, 645, 10000)
						+ " width = '645px' /></a>";
				$("#topic_move_div" + type + tid).html(topic_move_div_img);
				// var topic_move_div_img = document.createElement("img");
				// topic_move_div_img.src=circleDetailImg[z].bigimg;
				// topic_move_div_img.setAttribute("onload","scaleImage(this,645,10000)");
				// topic_move_div_img.width="645px";
				// topic_move_div.appendChild(topic_move_div_img);
				// topic_move_div.appendChild(topic_move_div_img);
			}
		}
		var topic_b = document.createElement("div");
		topic_b.className = "topic_b";
		topic_r1.appendChild(topic_b);

		var topic_b_p = document.createElement("p");

		topic_b_p.id = "answ" + type + tid;

		topic_b.appendChild(topic_b_p);

		var topic_b_p_span = document.createElement("span");
		topic_b_p.appendChild(topic_b_p_span);

		var topic_b_p_span_a = document.createElement("a");
		topic_b_p_span_a.innerHTML = answnick;
		topic_b_p_span_a.target = "_blank";
		topic_b_p_span_a.href = "user/" + userid+".html";
		topic_b_p_span.appendChild(topic_b_p_span_a);
		$("#answ" + type + tid).append("&nbsp;&nbsp;参与了此话题讨论");

		var pro_b_pingl = document.createElement("div");
		pro_b_pingl.id = "ad" + type + tid;
		pro_b_pingl.className = "pro_b_pingl";
		topic_b.appendChild(pro_b_pingl);

		var pro_b_pingl_imgs = document.createElement("img");
		pro_b_pingl_imgs.src = "images/pro_pinglun.gif";
		pro_b_pingl.appendChild(pro_b_pingl_imgs);

		$("#ad" + type + tid).append("&nbsp;&nbsp;" + comcount + "&nbsp;条评论");
	} else {
		var topic_r_pk = document.createElement("div");
		topic_r_pk.className = "topic_r_pk";
		topic_r.appendChild(topic_r_pk);
		var topic_r_pk_img = document.createElement("img");
		topic_r_pk_img.src = "images/pro_PK.png";
		topic_r_pk.appendChild(topic_r_pk_img);

		var topic_r_pinglun = document.createElement("div");
		topic_r_pinglun.className = "topic_r_pinglun";
		topic_r.appendChild(topic_r_pinglun);

		var pro_tiilt = document.createElement("div");
		pro_tiilt.className = "pro_tiilt";
		topic_r_pinglun.appendChild(pro_tiilt);

		var pro_tiilt_a = document.createElement("a");
		pro_tiilt_a.target = "_blank";
		pro_tiilt_a.href = "topic_pk/" + tid+".html";
		pro_tiilt.appendChild(pro_tiilt_a);

		var pro_tiilt_h2 = document.createElement("h2");
		pro_tiilt_h2.innerHTML = title;
		pro_tiilt_a.appendChild(pro_tiilt_h2);

		var top_impo = document.createElement("div");
		top_impo.className = "top_impo";
		pro_tiilt.appendChild(top_impo);

		var top_impo_a = document.createElement("a");
		top_impo_a.href = "javascript:void(0)";
		// top_impo_a.setAttribute("onclick","gzTopic('"+tid+"','"+isgz+"',"+type+")");
		top_impo.appendChild(top_impo_a);

		var top_impo_a_img = document.createElement("img");
		top_impo_a_img.id = "isimg" + type + tid;
		top_impo_a_img.setAttribute("onclick", "gzTopic('" + tid + "','" + isgz
				+ "'," + type + ")");
		top_impo_a_img.src = isgzimg;
		top_impo_a.appendChild(top_impo_a_img);

		var topic_r_span = document.createElement("span");
		topic_r_span.id = "userTopic" + type + tid;
		topic_r_pinglun.appendChild(topic_r_span);

		var topic_r_span_strong = document.createElement("strong");
		topic_r_span.appendChild(topic_r_span_strong);

		var topic_r_span_strong_a = document.createElement("a");
		topic_r_span_strong_a.target = "_blank";
		topic_r_span_strong_a.href = "user/" + userid+".html";
		topic_r_span_strong_a.innerHTML = nick;

		topic_r_span_strong.appendChild(topic_r_span_strong_a);

		$("#userTopic" + type + tid).append("&nbsp;&nbsp;发表于" + datetime + "");

		var topic_r_p = document.createElement("div");
		topic_r_p.className = "topic_r_p";
		topic_r_p.innerHTML = content;
		topic_r.appendChild(topic_r_p);

		for ( var z = 0; z < circleDetailImg.length; z++) {
			if (z > 0) {
				break;
			}

			var topic_move = document.createElement("div");
			topic_move.className = "topic_move";
			// topic_r_p.appendChild(topic_move);
			// topic_r.appendChild(topic_move);topic_r1
			topic_r1.appendChild(topic_move);

			var topic_move_div = document.createElement("div");
			topic_move_div.id = "move" + type + tid;
			topic_move.appendChild(topic_move_div);

			topic_move.appendChild(topic_move_div);
			var topic_move_div_img = "<a href=\"topic_pk/" + tid
					+ ".html\" target=\"_blank\"><img src="
					+ circleDetailImg[z].bigimg + " onload="
					+ scaleImage(this, 645, 10000) + " width = '645px' /></a>";
			$("#move" + type + tid).html(topic_move_div_img);

			// topic_move_div.appendChild(topic_move_div_img);

		}

		var topic_b = document.createElement("div");
		topic_b.className = "topic_b";
		topic_r1.appendChild(topic_b);

		var topic_b_p = document.createElement("p");
		topic_b_p.id = "answ" + type + tid;
		topic_b.appendChild(topic_b_p);

		var topic_b_p_span = document.createElement("span");
		topic_b_p.appendChild(topic_b_p_span);

		var topic_b_p_span_a = document.createElement("a");
		topic_b_p_span_a.innerHTML = answnick;
		// topic_b_p_span.href="javascript:void()";
		topic_b_p_span_a.target = "_blank";
		topic_b_p_span_a.href = "user/" + userid+".html";
		topic_b_p_span.appendChild(topic_b_p_span_a);

		$("#answ" + type + tid).append("&nbsp;&nbsp;参与了此话题讨论");

		var pro_b_pingl = document.createElement("div");
		pro_b_pingl.id = "ad" + type + tid;
		pro_b_pingl.className = "pro_b_pingl";
		topic_b.appendChild(pro_b_pingl);

		var pro_b_pingl_imgs = document.createElement("img");
		pro_b_pingl_imgs.src = "images/pro_pinglun.gif";
		pro_b_pingl.appendChild(pro_b_pingl_imgs);

		$("#ad" + type + tid).append("&nbsp;&nbsp;" + comcount + "&nbsp;条评论");
	}
}
/**
 * 关注话题
 * @param tid
 */
function gzTopic(tid,isgz,type)
{
	if(isgz==1)
	{
		alert("你已经关注该话题了！");
		return false;
	}
	var gzTopicURl="gztopicsAction_gzTopic.do";
	$.ajax({
		url:gzTopicURl,
		data:{tid : tid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			islogin = data.data.islogin;
			var result = data.data.result;
			if(islogin)
			{
				$("#isimg"+1+tid).attr("src","images/pro_importan_1t.gif");
				$("#isimg"+2+tid).attr("src","images/pro_importan_1t.gif");
				$("#isimg"+3+tid).attr("src","images/pro_importan_1t.gif");
				alert(data.data.result);
			}else{
				alert(data.data.result);
				$("#window_dl").css("display","block");
			}
		},
		error:function(data)
		{
			alert("关注失败！");
		}
	});
}