

function startMove(obj, json, fnEnd)
{
//	alert(obj+" "+json+"  "+fnEnd);
	if(obj.timer)
	{
		clearInterval(obj.timer);
	}
	obj.timer=setInterval(function (){
		doMove(obj, json, fnEnd);

		document.getElementById('box').style.left=(document.documentElement.clientWidth-document.getElementById('box').offsetWidth)/2+'px';
		
	}, 30);
}

function getStyle(obj, attr)
{
	if(obj.currentStyle)
	{
		return obj.currentStyle[attr];
	}
	else
	{
		return getComputedStyle(obj, false)[attr];
	}
}

function doMove(obj, json, fnEnd)
{
	var iCur=0;
	var attr='';
	var bStop=true;	//假设运动已经该停止了
	
	for(attr in json)
	{
		if(attr=='opacity')
		{
			iCur=parseInt(100*parseFloat(getStyle(obj, 'opacity')));
		}
		else
		{
			iCur=parseInt(getStyle(obj, attr));
		}
		
		if(isNaN(iCur))
		{
			iCur=0;
		}
		
		var iSpeed=(json[attr]-iCur)/6;
		iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
		
		if(json[attr]!=iCur)
		{
			bStop=false;
		}
		
		if(attr=='opacity')
		{
			obj.style.filter="alpha(opacity:"+(iCur+iSpeed)+")";
			obj.style.opacity=(iCur+iSpeed)/100;
		}
		else
		{
			obj.style[attr]=iCur+iSpeed+'px';
		}
	}
	
	if(bStop)
	{
		clearInterval(obj.timer);
		obj.timer=null;
		
		if(fnEnd)
		{
			fnEnd();
		}
	}
}