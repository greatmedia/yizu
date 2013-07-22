//回到顶部
//怎么获取网页的高度 让页面一出来就滚动条在最底端 
function myScroll() 
{ 
//前边是获取chrome等一般浏览器 如果获取不到就是ie了 就用ie的办法获取 
var x=document.body.scrollTop||document.documentElement.scrollTop; 
var timer=setInterval(function(){ 
x=x-100; 
if(x<100) 
{ 
	x=0; 
	window.scrollTo(x,x); 
	clearInterval(timer); 
	} 
	window.scrollTo(x,x); 
	},"0"); 
	document.getElementsByTagName('body')[0].scrollTop = 0;
//	document.documentElement.scrollTop=0;
}