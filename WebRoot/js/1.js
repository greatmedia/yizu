function pos(obj)
{
	var l=0;
	var t=0;
	
	while(obj)
	{
		l+=obj.offsetLeft;
		t+=obj.offsetTop;
		
		obj=obj.offsetParent;
	}
	
	return {left: l, top: t};
}

//window.onload=window.onscroll=window.onresize=function ()
function imgLoadPre()
{
	var div = document.getElementById("main_cont");
	var aImg=div.getElementsByTagName('img');
	
	var scrollT=document.documentElement.scrollTop||document.body.scrollTop;
	var scrollB=scrollT+document.documentElement.clientHeight;
	scrollT += 1;
	for(var i=0;i<aImg.length;i++)
	{
		var imgT=pos(aImg[i]).top;
		var imgB=imgT+aImg[i].offsetHeight;
		if(
			(imgT>=scrollT && imgT<=scrollB) ||
			(imgB>=scrollT && imgB<=scrollB)
		)
		{
			aImg[i].src=aImg[i].getAttribute('_src');
		}
//		else{
//			aImg[i].src=aImg[i].getAttribute('_src');
//			startShowAnimation(aImg[i]);
//		}
	}
};

function startShowAnimation(img)
{
	var iAlpha=0;
	var oTimer=null;
//	img.style.filter="alpha(opacity:0)";
//	img.style.opacity='0';
	
	oTimer=setInterval
	(
		function ()
		{
			iAlpha+=10;
			if(iAlpha<=20)
			{
				img.style.filter="alpha(opacity:"+iAlpha+")";
				img.style.opacity=iAlpha/20;
			}
			else
			{
				clearInterval(oTimer);
				img.style.filter="";
				img.style.opacity="";
			}
		},30
	);
}