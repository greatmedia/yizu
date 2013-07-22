var g_aImgSrc=[];
var g_aImgTop=[];
var g_iImgHeight=198;
var g_aImgVisible=[];

var g_aImg=[];

function loadimg()
{
	var oDiv=document.getElementById('box');
	
	g_aImg=oDiv.getElementsByTagName('img');
	
//	window.onscroll=updateVisible;
//	window.onresize=updateVisible;
	
	updateVisible();
};

function updateVisible()
{
	var top=document.body.scrollTop || document.documentElement.scrollTop;
	var bottom=top+document.documentElement.clientHeight;
	
	var i=0;
	
	for(i=0;i<g_aImgTop.length;i++)
	{
		if
		(
			(g_aImgTop[i]>=top && g_aImgTop[i]<=bottom) ||
			((g_aImgTop[i]+g_iImgHeight)>=top && (g_aImgTop[i]+g_iImgHeight)<=bottom)
		)
		{
			showImg(i);
		}
	}
}

function showImg(index)
{
	var oNewImg=null;
	
	if(g_aImgVisible[index])
	{
		return;
	}
	
	oNewImg=new Image();
	oNewImg.onload=function ()
	{
		startShowAnimation(index);
		g_aImg[index].src=g_aImgSrc[index];
		
		oNewImg=null;
	}
	
	oNewImg.src=g_aImgSrc[index];
	
	g_aImgVisible[index]=true;
}

function startShowAnimation(index)
{
	var iAlpha=0;
	
	var oTimer=null;
	
	g_aImg[index].style.filter="alpha(opacity:0)";
	g_aImg[index].style.opacity='0';
	
	oTimer=setInterval
	(
		function ()
		{
			iAlpha+=10;
			
			if(iAlpha!=100)
			{
				g_aImg[index].style.filter="alpha(opacity:"+iAlpha+")";
				g_aImg[index].style.opacity=iAlpha/100;
			}
			else
			{
				clearInterval(oTimer);
				
				g_aImg[index].style.filter="";
				g_aImg[index].style.opacity="";
			}
		},30
	);
}

function strippeImgSrc()
{
	var oDiv=document.getElementById('box');
	var aImg=oDiv.getElementsByTagName('img');
//	var i=0;
	
	for(i=0;i<aImg.length;i++)
	{
		g_aImgSrc[i]=aImg[i].src;
		aImg[i].src='';
		
		g_aImgTop[i]=aImg[i].offsetTop;
		
		g_aImgVisible[i]=false;
	}
}