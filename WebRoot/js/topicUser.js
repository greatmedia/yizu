// JavaScript Document
window.onload=function ()
{
	tanChu()
	touPiao()
	var oDiv=document.getElementById('div1');
	var aBtn=document.getElementById('btn');
	var aL=aBtn.getElementsByTagName('ul')[0].getElementsByTagName('li');
	var aA=aBtn.getElementsByTagName('a');
	var aDiv=getByClass(oDiv,'topic_subqie');
	for(var i=0; i<aA.length; i++){
		aA[i].strive=i;
		aA[i].onmouseover=function(){
			for(var i=0; i<aA.length; i++){
				aA[i].className='';
				aDiv[i].style.display='none';
			}
			this.className='topic_active';
			aDiv[this.strive].style.display='block';
			
		}
	}
	
	
	var oPicnav=document.getElementById('pic_nav');
	var oUl=oPicnav.getElementsByTagName('ul')[0];
	var aLi=oUl.getElementsByTagName('li');
	var oOl=document.getElementById('pic_roav');
	var aLi1=oOl.getElementsByTagName('li');
	
    var num=0;
	
	for(var i=0;i<aLi1.length;i++)
	{
		aLi1[i].index=i;
		aLi1[i].onmouseover=function()
		{
			num=this.index;
			for(var i=0;i<aLi1.length;i++)
			{
				aLi1[i].className='';
				aLi[i].style.display='none';
			}
			aLi[num].style.display='block';
			
		};
	}
	
	
	
	
	
}
function getByClass(oParent,sClass){
	var aResult=[];
	var aEle=oParent.getElementsByTagName('*');
	for(var i=0; i<aEle.length; i++){
		if(aEle[i].className==sClass){
			aResult.push(aEle[i]);
		}	
	}
	return aResult;
}


function tab()
	{
		for(var i=0;i<aLi1.length;i++)
		{
			aLi1[i].className='';
			aLi[i].style.display='none';
		}
		aLi[num].style.display='block';
		
	}
	
	
	
	
	
	
function tanChu()   //弹出层
{
	var oDivTan =document.getElementById('ropic_lent');
	var oDivA =document.getElementById('box_B');
	
	var oDivL =document.getElementById('pro_pk_red');
	var oDivR =document.getElementById('pro_pk_blue');
	oDivA.style.width =viewWidth()+'px';
	oDivA.style.height =documentHeight()+'px';
	
	
	
	function tanKuan()
	{
		oDivTan.style.left =(viewWidth()-oDivTan.offsetWidth)/2+'px';
		oDivTan.style.top =(viewHeight()-oDivTan.offsetHeight)/2+'px';
	}
	
	
	oDivL.onclick =function()
	{
		oDivA.style.display ='block';
		tanKuan()	
		
	}
	oDivR.onclick =function()
	{
		oDivA.style.display ='block';
		tanKuan()
	}
	
	
	
	window.onscroll = window.onresize = function()
	{
		var oDivTan =document.getElementById('ropic_lent');
		if(oDivTan)
		{
			oDivTan.style.left =(viewWidth()-oDivTan.offsetWidth)/2+'px';
			oDivTan.style.top =(viewHeight()-oDivTan.offsetHeight)/2+'px';
		}
		
	}
	
	//弹出结束
}



//投票开始
function touPiao()
{
	var oDiv =document.getElementById('pro_pk_onu');
	var oDivA =document.getElementById('pro_span_1')
	var oDivB =document.getElementById('pro_span_2')
	var numA =parseInt(oDivA.innerHTML);
	var numB =parseInt(oDivB.innerHTML);
	var nubA =0;
	var nubB =0;
	var numM =numA+numB
	var scale =oDiv.offsetWidth/numM
	oDivA.style.width =oDivA.offsetWidth+'px';
	oDivB.style.width =oDivB.offsetWidth+'px';
	
	
	oDivA.onclick =function()
	{
		numA++
		numM =numA+numB
		scale =oDiv.offsetWidth/numM
		if(oDivA.offsetWidth>=oDiv.offsetWidth)
		{
			alert('亲，满票耶')
			oDivA.innerHTML=numA;	
		}
		
		else{
				oDivA.style.width =numA*scale+'px';
				oDivB.style.width =numB*scale+'px';
				oDivA.innerHTML=numA;
			}
	}
	oDivB.onclick =function()
	{
		numB++
		numM =numA+numB
		scale =oDiv.offsetWidth/numM
		if(oDivB.offsetWidth>=oDiv.offsetWidth)
		{
			alert('亲，满票耶')
			oDivB.innerHTML=numB;
		}
		else{
			oDivA.style.width =numA*scale+'px';
			oDivB.style.width =numB*scale+'px';
			oDivB.innerHTML=numB;
			
			}
	}
		
}
//投票结束



function viewWidth(){
	return document.documentElement.clientWidth;
}
function viewHeight(){
	return document.documentElement.clientHeight;
}

function documentHeight(){
	return Math.max(document.documentElement.scrollHeight || document.body.scrollHeight,document.documentElement.clientHeight);
}	
	
	
	
	
	
	
	
	
	
	
