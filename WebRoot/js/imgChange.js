


var scaleImage = function(o, w, h){
var img = new Image();
img.src = o.src;
if(img.width >0 && img.height>0)
{
if(img.width/img.height >= w/h)
{
if(img.width > w)
{
o.width = w;
o.height = (img.height*w) / img.width;
}
else
{
o.width = img.width;
o.height = img.height;
}
o.alt = img.width + "x" + img.height;
}
else
{
if(img.height > h)
{
o.height = h;
o.width = (img.width * h) / img.height;
}
else
{
o.width = img.width;
o.height = img.height;
}
o.alt = img.width + "x" + img.height;
}
}
} 