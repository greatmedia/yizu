/**
 * 热门用户
 * @param userimg 用户头像
 * @param nick 用户昵称
 * @param count 关注数量
 * @param count 用户ID
 */
function moreUserHTML(userimg,nick,count,userid)
{
	var hotuserHTML="<li>"+
    "<div class=\"topic_r_img\"><a href=\"user/"+userid+"\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
    "<p>"+
        "<span><a href=\"user/"+userid+"\" target=\"_blank\">"+nick+"</a></span>"+
        "<strong>参与了"+count+"个话题讨论</strong>"+
    "</p>"+
"</li>";
	$("#hotUser").append(hotuserHTML);
}
/**
 * 热门话题
 * @param tid 话题ID
 * @param uimg 用户头像
 * @param title 标题
 * @param def3 关注数量
 * @param isgzImg 是否已经关注图片
 * @param userid 用户ID
 */
function hoteHTML(tid,isgz,uimg,title,def3,isgzImg,userid)
{
	var hotuHTML = "<li>"+
               "<div class=\"pk_about_l\"><a href=\"user/"+userid+"\" target=\"_blank\"><img onclick=\"gz('"+tid+"')\" src=\""+uimg+"\" width='25px' /></a></div>"+
               "<div class=\"pk_about_r\">"+
                   "<h4><a href=\"topic_tu/"+userid+"\" target=\"_blank\">"+title+"</a></h4>"+
                   "<span><a href=\"javascript:void();\">"+def3+"人</a>&nbsp;关注</span>"+
//                   "<p><a href=\"javascript:void()\">杨坤</a> &nbsp;关注了 &nbsp;该话题</p>"+
               "</div>"+
               "<div class=\"pk_guanzhu1\"><a href=\"javascript:void()\" id=\"gzid_a"+tid+"\"><img src=\""+isgzImg+"\"/></a></div>"+
            "</li>";
	$("#hotTopics").append(hotuHTML);
	var gzImg = document.createElement("img");
	gzImg.id="gzid"+tid;
	if(isgz==1)
	{
		gzImg.src = "images/pro_importan_1t.gif";
	}else{
		gzImg.setAttribute("onclick","gzTopic('"+tid+"')");
		gzImg.src = "images/pro_important.gif";
	}
	$("#gzid_a"+tid).html(gzImg);
}