<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>加入逸族网</title>
		<link href="css/css/zhucestyle.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/join.js" type="text/javascript"></script>
		<script>var basePath = "<%=basePath%>";</script>
<%--		<script src="js/alljs.js" type="text/javascript"></script>--%>
<%--		<script type="text/javascript" src="<%=basePath%>js/om-validate.js"></script>--%>
<%--		<script type="text/javascript">--%>
<%--		if(isck)--%>
<%--		{--%>
<%--	        $.validator.addMethod("userNameCheck", function(value) {--%>
<%--	        --%>
<%--	        	if(!(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value))){--%>
<%--	        		$("#checkEmailResult").hide();--%>
<%--	        		return false;--%>
<%--	        	}--%>
<%--	        	$("#checkEmailResult").show();--%>
<%--		        $.ajax({--%>
<%--		            url:"<%=basePath%>userInfoAction_ajaxCheckEmailNotExists.do",--%>
<%--		            type: 'post',--%>
<%--		            dataType: 'json',--%>
<%--		            data : {--%>
<%--		                "instance.email":value--%>
<%--		            },--%>
<%--		            beforeSend:function(){--%>
<%--		            	$("#checkEmailResult").html("正在验证...");--%>
<%--		            },--%>
<%--		            success:function(data){--%>
<%--		            	if(data.flag){--%>
<%--		            		$("#checkEmailResult").html(data.msg);--%>
<%--		            	}--%>
<%--		            },--%>
<%--		            error:function(){--%>
<%--		               $("#checkEmailResult").html("验证失败，请检测网络连接是否正常。");--%>
<%--		            }--%>
<%--		        });--%>
<%--		        return true;--%>
<%--	        }, '邮箱格式不正确！');--%>
<%--    --%>
<%--	        $(function() {--%>
<%--	        	 $("#upFile").bind("change", function(){--%>
<%--		            var img =  $(this).val();--%>
<%--		            if( !img.match( /.jpeg|.jpg|.gif|.png|.bmp/i ) ){--%>
<%--		                alert('图片格式无效,请选择其他格式文件，支持.jpeg|.jpg|.gif|.png|.bmp！');--%>
<%--		                return;--%>
<%--		            }--%>
<%--		            --%>
<%--		            $("#reg").ajaxSubmit({--%>
<%--		                url:"<%=basePath%>userInfoAction_uploadFile.do",--%>
<%--		                type: 'post',--%>
<%--		                dataType: 'json',--%>
<%--		                beforeSend:function(){--%>
<%--		                	 $("#showPhoto").attr("src","<%=basePath%>images/loading_32.gif");--%>
<%--		                },--%>
<%--		                success:function(data){--%>
<%--		                    if(data.flag){--%>
<%--		                        $("#showPhoto").attr("src","<%=basePath%>"+data.data);--%>
<%--		                        $("#image").val(data.data);--%>
<%--		                    }else{--%>
<%--		                   	 	alert(data.msg);--%>
<%--		                    }--%>
<%--		                },--%>
<%--		                error:function(){--%>
<%--		                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");--%>
<%--		                }--%>
<%--		            });--%>
<%--		        });--%>
<%--	        --%>
<%--	            $("#reg").validate({--%>
<%--	                rules : {--%>
<%--	                    "instance.email" : {--%>
<%--	                    	required : true,--%>
<%--	                    	email:true--%>
<%--	                    	userNameCheck : true--%>
<%--	                    },--%>
<%--	                    "instance.nick" : {--%>
<%--	                    	required : true,--%>
<%--		                    maxlength : 18--%>
<%--		                },--%>
<%--	                    "instance.password" : {--%>
<%--	                        required : true,--%>
<%--	                        minlength : 6,--%>
<%--	                        maxlength : 18--%>
<%--	                    }--%>
<%--	                },--%>
<%--	                messages : {--%>
<%--	                    "instance.email" : {--%>
<%--	                        required : "请输入邮箱",--%>
<%--	                        email:"邮件格式不正确",--%>
<%--	                    },--%>
<%--	                    "instance.password" : {--%>
<%--	                        required : "请输入密码",--%>
<%--	                        minlength : "密码长度必须为6-18位数字或字母组合！",--%>
<%--	                        maxlength : "密码长度必须为6-18位数字或字母组合！"--%>
<%--	                    },--%>
<%--	                    "instance.nick" : {--%>
<%--	                    	required : "请输入昵称",--%>
<%--	                    	maxlength : "长度必须为小于18位数字或字母组合！"--%>
<%--		                }--%>
<%--	                },--%>
<%--	                submitHandler : function(){--%>
<%--		                $("#upFile").val("");--%>
<%--		                is = false;--%>
<%--		                 $("#reg").ajaxSubmit({--%>
<%--			                url:"<%=basePath%>userInfoAction_ajaxSettingAccount.do",--%>
<%--			                type: 'post',--%>
<%--			                dataType: 'json',--%>
<%--			                beforeSend:function(){--%>
<%--			                	$("#creat_submit").hide();--%>
<%--            					$("#loadding").show();--%>
<%--			                },--%>
<%--			                success:function(data){--%>
<%--			                	$("#loadding").hide();--%>
<%--			                	alert(data.msg);--%>
<%--			                    if(data.flag){--%>
<%--			                    	location.href="<%=basePath%>";--%>
<%--			                    }else{--%>
<%--				                    $("#creat_submit").show();--%>
<%--				                }--%>
<%--			                },--%>
<%--			                error:function(){--%>
<%--			                 	$("#loadding").hide();--%>
<%--				                $("#creat_submit").show();--%>
<%--			                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");--%>
<%--			                }--%>
<%--			            });--%>
<%--	                    return false;--%>
<%--	                }--%>
<%--	            });--%>
<%--	            --%>
<%--	            $("#formSubmit").attr("disabled", false);--%>
<%--	        });--%>
<%--	    </script>--%>
	</head>

	<body>
		<c:choose>
			<c:when test="${user != null}">
				<c:if test="${user.email != '' && user.email != null}">
					<script type="text/javascript">
		    			location.href="<%=basePath%>";
		    		</script>
				</c:if>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
	    			location.href="<%=basePath%>";
	    		</script>
			</c:otherwise>
		</c:choose>
	<div class="box_zhuce">
  		<div class="top_out">
    		<div class="top"><p>现在就加入逸族网</p></div>
  		</div>
		<div class="join11">
		   	<ul>
		   	 	<li>
		        	<p>从逸族网得到你的昵称是：${user.nick }</p>
		      	</li>
		      	<li>
		      		<img src="${user.otheraccountuserimage }" />
		      	</li>
		   </ul>
		</div>
  		<div class="join">
	    <form>
	      	<ul>
		        <li><h1>昵称：</h1></li>
	        	<li><input type="text" class="text" value="${user.nick }" id="join_name"/><span class="yes"></span></li>
	        	<li><h1>电子邮箱：</h1></li>
	        	<li><input type="text" class="text"  id="join_email" /><span class="yes">我们将会给你发送确认邮箱。</span></li>
	        	<li><h1>创建密码：</h1></li>
		        <li><input type="password" class="text"  id="join_pwd" onkeydown="checkpwd()"/><span class="no" id="checkpwd">密码长度不能小于6位</span></li>
		        <li><h1>确认密码：</h1></li>
		        <li><input type="password" class="text"  id="join_repwd"/><span class="yes"></span></li>
	        	<li class="margin">
          <textarea> 1.	接受条款
逸族网根据以下服务条款为您提供服务。这些条款可由逸族网随时更新，且毋须另行通知。逸族网使用协议（以下简称“使用协议”）一旦发生变动，逸族网将在网页上公布修改内容。修改后的使用协议一旦在网页上公布即有效代替原来的使用协议。此外，当您使用逸族网特殊服务时，您和逸族网应遵守逸族网随时公布的与该服务相关的指引和规则。前述所有的指引和规则，均构成本使用协议的一部分。 

您在使用逸族网提供的各项服务之前，应仔细阅读本使用协议。如您不同意本使用协议及/或随时对其的修改，请您立即停止使用逸族网所提供的全部服务；您一旦使用逸族网服务，即视为您已了解并完全同意本使用协议各项内容，包括逸族网对使用协议随时所做的任何修改，并成为逸族网用户（以下简称“用户”）。 

2.	服务说明
逸族网目前向用户提供如下服务：发布并分享兴趣、爱好、购物体验；参与发布和评论产品投票；在逸族网中创建圈子、发布消息、话题、上传图片；在逸族网商城网购。除非本使用协议另有其它明示规定，增加或强化目前本服务的任何新功能，包括所推出的新产品，均受到本使用协议之规范。您了解并同意，本服务仅依其当前所呈现的状况提供，对于任何用户信息或个人化设定之时效、删除、传递错误、未予储存或其它任何问题，逸族网均不承担任何责任。逸族网保留不经事先通知为维修保养、升级或其它目的暂停本服务任何部分的权利。 

3.	遵守法律
您同意遵守中华人民共和国相关法律法规的所有规定，并对以任何方式使用您的密码和您的帐号使用本服务的任何行为及其结果承担全部责任。如您的行为违反国家法律和法规的任何规定，有可能构成犯罪的，将被追究刑事责任，并由您承担全部法律责任。 
同时如果逸族网有理由认为您的任何行为，包括但不限于您的任何言论和其它行为违反或可能违反国家法律和法规的任何规定，逸族网可在任何时候不经任何事先通知终止向您提供服务。 

4.	您的注册义务 
为了能使用本服务，您同意以下事项：依本服务注册提示请您填写正确的注册邮箱、密码和昵称，并确保今后更新的登录邮箱、昵称、头像等资料的有效性和合法性。若您提供任何违法、不道德或逸族网认为不适合在逸族网上展示的资料；或者逸族网有理由怀疑你的资料属于程序或恶意操作，逸族网有权暂停或终止您的帐号，并拒绝您于现在和未来使用本服务之全部或任何部分。 
逸族网无须对任何用户的任何登记资料承担任何责任，包括但不限于鉴别、核实任何登记资料的真实性、正确性、完整性、适用性及/或是否为最新资料的责任。 

5. 用户帐号、密码及安全 
完成本服务的注册程序并成功注册之后，您可使用您的Email和密码，登录到您在逸族网的帐号（下称“帐号”）。保护您的帐号安全，是您的责任。 
您应对所有使用您的密码及帐号的活动负完全的责任。您同意： 
1).	您的逸族网帐号遭到未获授权的使用，或者发生其它任何安全问题时，您将立即通知逸族网； 
2).	如果您未保管好自己的帐号和密码，因此而产生的任何损失或损害，逸族网无法也不承担任何责任； 
3).	每个用户都要对其帐号中的所有行为和事件负全责。如果您未保管好自己的帐号和密码而对您、逸族网或第三方造成的损害，您将负全部责任。 
6.	提供者的责任 
根据有关法律法规，逸族网在此郑重提请您注意，任何经由本服务而发布、上传的文字、资讯、资料、音乐、照片、图形、视讯、信息或其它资料（以下简称“内容”），无论系公开还是私下传送，均由内容提供者承担责任。逸族网仅为用户提供内容存储空间，无法控制经由本服务传送之内容，因此不保证内容的正确性、完整性或品质。您已预知使用本服务时，可能会接触到令人不快、不适当或令人厌恶之内容。在任何情况下，逸族网均不为任何内容负责，但逸族网有权依法停止传输任何前述内容并采取相应行动，包括但不限于暂停用户使用本服务的全部或部分，保存有关记录，并向有关机关报告。 

7.	用户行为 
用户同意将不会利用本服务进行任何违法或不正当的活动，包括但不限于下列行为∶ 
1).	发布或以其它方式传送含有下列内容之一的信息：
　　　* 反对宪法所确定的基本原则的；
　　　* 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；
　　　* 损害国家荣誉和利益的；
　　　* 煽动民族仇恨、民族歧视、破坏民族团结的；
　　　* 破坏国家宗教政策，宣扬邪教和封建迷信的；
　　　* 散布谣言，扰乱社会秩序，破坏社会稳定的；
　　　* 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；
　　　* 侮辱或者诽谤他人，侵害他人合法权利的；
　　　* 含有虚假、诈骗、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、猥亵、或其它道德上令人反感的内容；
　　　* 含有中国法律、法规、规章、条例以及任何具有法律效力之规范所限制或禁止的其它内容的；
　　　* 含有逸族网认为不适合在逸族网展示的内容；

2).	以任何方式危害他人的合法权益；

3).	冒充其他任何人或机构，或以虚伪不实的方式陈述或谎称与任何人或机构有关；

4).	依据任何法律或合约或法定关系（例如由于雇佣关系和依据保密合约所得知或揭露之内部资料、专属及机密资料）知悉但无权传送之任何内容加以发布、发送电子邮件或以其它方式传送； 

5).	将侵害他人著作权、专利权、商标权、商业秘密、或其它专属权利（以下简称“专属权利”）之内容加以发布或以其它方式传送； 

6).	将任何广告信函、促销资料、“垃圾邮件”、““滥发信件”、“连锁信件”、“直销”或其它任何形式的劝诱资料加以发布、发送或以其它方式传送； 

7).	将设计目的在于干扰、破坏或限制任何计算机软件、硬件或通讯设备功能之计算机病毒（包括但不限于木马程序（trojan horses）、蠕虫（worms）、定时炸弹、删除蝇（cancelbots）（以下简称“病毒”）或其它计算机代码、档案和程序之任何资料，加以发布、发送或以其它方式传送； 

8).	干扰或破坏本服务或与本服务相连线之服务器和网络，或违反任何关于本服务连线网络之规定、程序、政策或规范； 

9).	跟踪、人肉搜索或以其它方式骚扰他人； 

10).	故意或非故意地违反任何适用的当地、国家法律，以及任何具有法律效力的规则； 

11).	未经合法授权而截获、篡改、收集、储存或删除他人个人信息、站内邮件或其它数据资料，或将获知的此类资料用于任何非法或不正当目的。 

您已认可逸族网未对用户的使用行为进行全面控制，您使用任何内容时，包括依赖前述内容之正确性、完整性或实用性时，您同意将自行加以判断并承担所有风险，而不依赖于逸族网。但逸族网依其自行之考虑，拒绝和删除可经由本服务提供之违反本条款的或其它引起逸族网反感的任何内容。 

您了解并同意，逸族网依据法律法规的要求，或基于诚信为了以下目的或在合理必要范围内，认定必须将内容加以保存或揭露时，得加以保存或揭露： 

a）遵守法律程序；
b）执行本使用协议；
c）回应任何第三人提出的权利主张；
d）保护逸族网、其用户及公众之权利、财产或个人安全；
e）其它逸族网认为有必要的情况。

9.	国际使用的特别警告
您已了解国际互联网的无国界性，同意遵守当地所有关于网上行为及内容之法律法规。您特别同意遵守有关从中国或您所在国家或地区输出信息之传输的所有适用法律法规。 

10.	在逸族网发布的公开信息
1).	在本使用协议中，“本服务公开使用区域”系指一般公众可以使用的区域； 

2).	用户在逸族网上传或发布的内容，用户应保证其为著作权人或已取得合法授权，并且该内容不会侵犯任何第三方的合法权益，用户同意授予逸族网所有上述内容在全球范围内的免费、不可撤销的、永久的、可再许可或转让的独家使用权许可，据该许可逸族网将有权以展示、推广及其他不为我法律所禁止的方式使用前述内容。 

11.	免责声明 
1).	用户明确同意其将文字、资讯、资料、音乐、照片、图形、视讯、信息或其它个人资料上传到互联网上，有可能被其他组织或个人复制、转载、或做其它用途的风险完全由其自己承担；因其使用逸族网服务而产生的一切后果也由其自己承担，我们对用户不承担任何责任。 

2).	微博、人人网等官方帐号对用户的相关文字、资讯、资料、音乐、照片、图形、视讯、信息或其它个人资料进行推广发布的，不属于对用户相关权益的侵权行为、逸族网不承担任何责任。 

3).	网站上针对用户的恶意评论，经用户的举报我们会采取删除处理，但不保证服务一定能满足用户的要求，也不对该恶意评论负任何法律责任。 

12.	赔偿 
由于您通过本服务提供、发布或传送之内容、您与本服务连线、您违反本使用协议、或您侵害他人任何权利因而衍生或导致任何第三人提出任何索赔或请求，包括合理的律师费，您同意赔偿逸族网及其子公司、关联企业、高级职员、代理人、品牌共有人或其它合作伙伴及员工，并使其免受损害，并承担由此引发的全部法律责任。 

13.	禁止商业行为 
您同意不对本服务任何部分或本服务之使用或获得，进行复制、拷贝、出售、转售或用于任何其它商业目的。 

14.	关于使用及储存之一般措施 
您承认关于本服务的使用逸族网有权制订一般措施及限制，包含但不限于本服务将保留所发布内容或其它发布内容之最长期间，以及一定期间内您使用本服务之次数上限（及每次使用时间之上限）。通过本服务发布或传送之任何信息、通讯资料和其它内容，如被删除或未予储存，您同意蘑菇街毋须承担任何责任。您也同意，逸族网有权依其自行之考虑，不论通知与否，随时变更这些一般措施及限制。 

15.	服务的修改 
逸族网有权于任何时间暂时或永久修改或终止本服务（或其任何部分），且无论通知与否。您同意对于本服务所作的任何修改、暂停或终止，蘑菇街对您和任何第三人均无需承担任何责任。 

16.	终止服务 
您同意逸族网基于其自行之考虑，因任何理由，包含但不限于缺乏使用，或逸族网认为您已经违反本使用协议的文字及精神，终止您的帐号或本服务之使用（或服务之任何部分），并将您在本服务内任何内容加以移除并删除。您同意依本使用协议任何规定提供之本服务，无需进行事先通知即可中断或终止，您承认并同意，逸族网可立即关闭或删除您的帐号及您帐号中所有相关信息及文件，及/或禁止继续使用前述文件或本服务。此外，您同意若本服务之使用被中断或终止或您的帐号及相关信息和文件被关闭或删除，逸族网对您或任何第三人均不承担任何责任。 

17.	与广告商及其他第三方进行交易 
您通过本网站与广告商及其他第三方进行任何形式的通讯或商业往来，或参与促销活动，包含相关商品或服务之付款及交付，以及达成的其它任何相关条款、条件、保证或声明，完全为您与广告商及其他第三方之间之行为。您因前述任何交易或前述广告商及其他第三方而遭受的任何性质的损失或损害，逸族网对此不负任何法律责任。 

18.	逸族网的专属权利 
您了解并同意，本服务及本服务所使用之相关软件（以下简称“软件”）含有受到相关知识产权及其它法律保护之专有保密资料。您也了解并同意，经由本服务或广告商向您呈现之赞助广告或信息所包含之内容，亦受到著作权、商标权、服务商标、专利权或其它专属权利之法律保护。未经逸族网或广告商明示授权，您不得修改、出租、出借、出售、散布本服务或软件之任何部份或全部，或据以制作衍生著作，或使用擅自修改后的软件，包括但不限于为了未经授权而使用本服务之目的。蘑菇街仅授予您个人、不可移转及非专属之使用权，使您得于单机计算机使用其软件之目的码，但您不得（并不得允许任何第三人）复制、修改、创作衍生著作、进行还原工程、反向组译，或以其它方式发现原始码，或出售、转让、再授权或提供软件设定担保，或以其它方式移转软件之任何权利。您同意将通过由逸族网所提供的界面而非任何其它途径使用本服务。 

19.	担保与保证 
您明确了解并同意∶ 

1).	本使用协议的任何规定不会免除逸族网对造成您人身伤害的、或因故意或重大过失造成您财产损失的任何责任； 

2).	您使用本服务之风险由您个人负担。本服务系依“现状”及“现有”基础提供。逸族网对本服务不提供任何明示或默示的担保或保证，包含但不限于商业适售性、特定目的之适用性及未侵害他人权利等担保或保证； 

3).	逸族网不保证以下事项∶
　　　* 本服务将符合您的要求；
　　　* 本服务将不受干扰、及时提供、安全可靠或不会出错；
　　　* 使用本服务取得之结果正确可靠；
　　　* 您经由本服务购买或取得之任何产品、服务、资讯或其它信息将符合您的期望；


4).	是否使用本服务下载或取得任何资料应由您自行考虑且自负风险，因任何资料之下载而导致的您电脑系统之任何损坏或数据流失等后果，由您自行承担； 

5).	您自逸族网或经由本服务取得的任何建议或信息，无论是书面或口头形式，除非本使用协议有明确规定，将不构成本使用协议以外之任何保证。 

6).	如果逸族网和/或合作单位使用您提供的肖像、姓名或其他合法权益，您同意将您的肖像、姓名和/或其他合法权益一并授权给逸族网和/或合作单位使用。 

20.	责任限制


您明确了解并同意，基于以下原因而造成的，包括但不限于利润、信誉、应用、数据损失或其它无形损失，逸族网不承担任何直接、间接、附带、特别、衍生性或惩罚性赔偿责任： 

1).	本服务之使用或无法使用；
2).	为替换从或通过本服务购买或取得之任何商品、数据、信息、服务，收到的讯息，或缔结之交易而发生的成本；
3).	您的传输或数据遭到未获授权的存取或变造；
4).	任何第三方在本服务中所作之声明或行为；
5).	与本服务相关的其它事宜，但本使用协议有明确规定的除外；
6).	第三方以任何方式发布或投递欺诈性信息，或诱导用户受到经济损失，逸族网不承担任何责任。 

21.	逸族网商标信息
逸族网、逸族以及其它逸族网注册商标、标志及产品、服务名称，均为逸族网公司之商标（以下简称“逸族网标记”）。未经逸族网事先书面同意，您同意不将逸族网标记以任何方式展示或使用或作其它处理，或表示您有权展示、使用或另行处理逸族网标记。 

22.	用户专属权利 
逸族网尊重他人知识产权，呼吁用户也要同样尊重知识产权。 

逸族网之服务与资料是基于“现状”提供，而且逸族网明确地表示拒绝对于“服务”、“资料”或“产品”给予任何明示或暗示之保证，包括但不限于，得为商业使用或适合于特定目的之保证。逸族网对于因“服务”、“资料”或“产品”所生之任何直接、间接、附带的或因此而导致之衍生性损失概不负责。 

如果您对他人的知识产权造成了侵害，逸族网将依国家法律法规的规定，或在适当的情形下，将依其服务条款或其相关规范性规定，删除特定内容或以至终止您对账户的使用。 

逸族网尊重他人的任何权利（包括知识产权），同时也要求我们的使用者也尊重他人之权利。逸族网在适当情况下，得自行决定终止侵害或违反他人权利之使用者的帐号。 

若您认为您的作品的著作权遭到侵害或您的知识产权被侵犯，根据《信息网络传播权保护条例》的规定，您需及时向逸族网联系并提供详实的举证材料。或请到中华人民共和国国家版权局下载《要求删除或断开链接侵权网络内容的通知》（下称“删除通知”）的示范格式，如果您不明白“删除通知”的内容，请登录中华人民共和国国家版权局查看《要求删除或断开链接侵权网络内容的通知》填写说明。 

23.	一般条款 
1).	本使用协议、社区指导原则和免责声明构成您与逸族网之全部协议，并规范您对于本服务之使用行为。在您使用相关服务、使用第三方提供的内容或软件时，亦应遵从所适用之附加条款及条件； 

2).	本使用协议及您与逸族网之关系，均受到中华人民共和国法律管辖。您与逸族网就本服务、本使用协议或其它有关事项发生的争议，应首先友好协商解决，协商不成时应提请中国国际经济贸易仲裁委员会仲裁，仲裁裁决是终局性的，对双方均有约束力； 

3).	逸族网未行使或执行本使用协议任何权利或规定，不构成对前述权利或权利之放弃； 

4).	倘本使用协议之任何规定因与中华人民共和国法律抵触而无效，您依然同意应依照法律，努力使该规定所反映之当事人意向具备效力，且本使用协议其它规定仍应具有完整的效力及效果； 

5).	本使用协议之标题仅供方便而设，不具任何法律或契约效果； 

6).	只要您登录了逸族网，就代表您认可以上所有协议。 

7).	逸族网对本使用协议享有最终解释权。</textarea >
        </li>
        <li class="float"><input type="checkbox" id="agreeornot" onclick="checkedxieyi()" checked="checked"/><span class="checkbox">我已阅读且同意</span></li>
        <li style="margin-top:14px;margin-left:1px;" id="join_submit"><a id="sub" href="javascript:joinUserLogined()"><span id="loadding" style="display:none;">注册中···</span></a></li>
        <li style="margin-top:10px;">注：其他用户能根据名字，用户名或电子邮箱中到你，但你的电子邮箱不会公开显示，你可以随时更改隐私设置。</li>
      </ul>
    </form>
  </div>
</div>
	
	
	
	
	
	
	
<%--		<div class="main_cont">--%>
<%--			<div class="main">--%>
<%--				<div class="line2"></div>--%>
<%----%>
<%--				<div class="feedwall" style="">--%>
<%--					<div class="shezhi">--%>
<%--						<form id="reg" name="reg" method="post"--%>
<%--							enctype="multipart/form-data">--%>
<%--							<div class="shezhi_t">--%>
<%--								补全个人信息<span class="num_h3">请补充必要的个人信息，加入逸族吧！</span>--%>
<%--							</div>--%>
<%--						--%>
<%--							<ul>--%>
<%--								<li class="s_name">--%>
<%--									邮箱地址--%>
<%--									<input type="hidden" id="accountEmail" name="accountEmail" value="${user.email}" />--%>
<%--								</li>--%>
<%--								<li>--%>
<%--									<c:choose>--%>
<%--										<c:when test="${user.email != null && user.email != ''}">--%>
<%--											<input value="${user.email }" type="text"--%>
<%--												class="s_input" style="width: 273px;" autocomplete="off" />--%>
<%--											<input id="email" name="instance.email"--%>
<%--												value="${user.email }" type="text" class="s_input"--%>
<%--												style="width: 273px; " autocomplete="off" />--%>
<%--										</c:when>--%>
<%--										<c:otherwise>--%>
<%--											<input id="email" name="instance.email" type="text"--%>
<%--												class="s_input" style="width: 273px;" autocomplete="off" />--%>
<%--										</c:otherwise>--%>
<%--									</c:choose>--%>
<%--								</li>--%>
<%--								<li>--%>
<%--									<span id="checkEmailResult"></span>--%>
<%--								</li>--%>
<%--							</ul>--%>
<%--							<ul>--%>
<%--								<li class="s_name">--%>
<%--									密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码--%>
<%--								</li>--%>
<%--								<li>--%>
<%--									<input type="password" id="password" name="instance.password"--%>
<%--										class="s_input" autocomplete="off" />--%>
<%--								</li>--%>
<%--								<li>--%>
<%--																		<img src="images/btn_14.jpg" onclick="location.href='<%=basePath %>setting/password.jsp'"/>--%>
<%--								</li>--%>
<%--							</ul>--%>
<%--							<ul>--%>
<%--								<li class="s_name">--%>
<%--									昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称--%>
<%--								</li>--%>
<%--								<li>--%>
<%--									<input type="text" id="nick" name="instance.nick"--%>
<%--										value="${user.nick}" class="s_input" autocomplete="off" />--%>
<%--								</li>--%>
<%--							</ul>--%>
<%--							<ul>--%>
<%--								<li class="s_name">--%>
<%--									头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像--%>
<%--								</li>--%>
<%--								<li class="s_user">--%>
<%--									<c:choose>--%>
<%--										<c:when test="${user.image != null && user.image != ''}">--%>
<%--											<img style="width: 120px; height: 120px;" id="showPhoto"--%>
<%--												src="<%=basePath%>${user.image}" />--%>
<%--										</c:when>--%>
<%--										<c:otherwise>--%>
<%--											<img style="width: 120px; height: 120px;" id="showPhoto"--%>
<%--												src="images/nobody.gif" />--%>
<%--										</c:otherwise>--%>
<%--									</c:choose>--%>
<%----%>
<%--								</li>--%>
<%--								<li>--%>
<%--																	style="background-image:url(images/btn_15.jpg); width:79px; height:30px; border:none;" --%>
<%--									<input id="upFile" name="upFile" type="file" size="20" />--%>
<%--																		<input type="submit" value="上传头像" onclick="document.getElementById('upFile').click()" class="bot_2words" align="absmiddle" cursor=""/>--%>
<%--									<input id="image" name="instance.image" value="${user.image}"--%>
<%--										type="text" style="display: none;" autocomplete="off" />--%>
<%--								</li>--%>
<%--							</ul>--%>
<%--							<ul style="background: none;">--%>
<%--								<li class="s_name">--%>
<%--									关于自己--%>
<%--								</li>--%>
<%--								<li>--%>
<%--									<textarea id="what" name="instance.what" rows="7" cols="50"--%>
<%--										style="border: 1px #DEDEDE solid;" autocomplete="off">${user.what}</textarea>--%>
<%--								</li>--%>
<%--							</ul>--%>
<%--							<div class="clearfloat"></div>--%>
<%----%>
<%--							<div id="creat_submit" class="s_btn">--%>
<%--								<input id="formSubmit" type="submit" value="保存设置"--%>
<%--									/>--%>
<%--										                         <input id="formSubmit" type="image" src="images/btn_16.gif"  disabled="disabled"/>--%>
<%--							</div>--%>
<%--							<div id="loadding" class="s_btn"--%>
<%--								style="display: none; width: 200px;">--%>
<%--								<img src='<%=basePath%>images/loading_32.gif' />--%>
<%--								保存中，请稍候......--%>
<%--							</div>--%>
<%--						</form>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<script>var email = $("#accountEmail").val();if(email.length>2){location="<%=basePath%>";}</script>--%>
		<!-----页尾部分---->
<%--		<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>--%>
	</body>
</html>
