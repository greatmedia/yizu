// JavaScript Document

function _inputPost(form, obj)
{
	 obj.value == '评论...';  		
}
	
function _inputOnKeyDown(obj1, divid, imageid, did,e) 
{
			var parentDiv = document.getElementById(divid);
			var avatarImage = document.getElementById(imageid);
			var key = window.event ? e.keyCode : e.which;
			if(key==13)
			 {
				if(!obj1.value.length==0)
				{ 
					 addComment(did);
					 parentDiv.style.height='30px';
					 avatarImage.style.display='none';
					 obj1.value='评论...';
					 obj1.style.height=22+'px';
					 obj1.style.width=204+'px';
					 obj1.style.color='gray';
				}
				 
			}
}
function _inputOnFocus(obj1, divid, imageid)
{
			
			var parentDiv = document.getElementById(divid);
			var avatarImage = document.getElementById(imageid);
			
			if(obj1.value=='评论...')
			{
				obj1.value='';
			}
			obj1.style.height=28+'px';
			obj1.style.width=164+'px';
			obj1.style.color='black';
			
			parentDiv.style.height=40+'px';
			avatarImage.style.display='block';
}
		
		
function _inputOnBlur(obj1, divid, imageid)
{
			var parentDiv = document.getElementById(divid);
			var avatarImage = document.getElementById(imageid);
			
			if(obj1.value.length==0)
			{ 
			   obj1.value='评论...';
			   parentDiv.style.height='30px';
			   avatarImage.style.display='none';
			   obj1.style.height=22+'px';
			   obj1.style.width=204+'px';
			   obj1.style.color='gray'
		    }

}


	
