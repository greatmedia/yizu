<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人头像</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.Jcrop.js"></script>
<script type="text/javascript">
  jQuery(function($){

    // Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    
    console.log('init',[xsize,ysize]);
    $('#target').Jcrop({
      onChange: updatePreview,
      onSelect: updatePreview,
      aspectRatio: xsize / ysize
    },function(){
      // Use the API to get the real image size
      var bounds = this.getBounds();
      boundx = bounds[0];
      boundy = bounds[1];
      // Store the API in the jcrop_api variable
      jcrop_api = this;

      // Move the preview into the jcrop container for css positioning
      $preview.appendTo(jcrop_api.ui.holder);
    });

    function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
        var rx = xsize / c.w;
        var ry = ysize / c.h;

        $pimg.css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };

  });


</script>
</head>
<body>
<div class="box">
  <div class="inner_box">
    <div class="nav">
      <ul>
        <li><a href="#">1  推荐你的朋友</a></li>
        <li style="margin:0px 3px;"><a href="#" >2  完善个人资料</a></li>
        <li><a href="#"style="background-color:#ff8a00;color:#fff;">3  上传个人头像</a></li>
      </ul>
    </div>
    <div class="tittle">
      <p>上传你的头像</p>
    </div>
    
    <div class="middle_photo">
    <div class="middle_photo_left">
    <p>（<span>温馨小贴士</span>：上传真实照片可以让你更迅速找到同好直击。）</p>
    <ul>
    <li class="photo_big"><img src="img/photo_big.jpg"  id="target" alt="[Jcrop Example]" /></li>
    <li>
    
    <div id="preview-pane">
    <div class="preview-container">
    <img src="img/photo_s.jpg" alt="Preview" class="jcrop-preview"  />
    </div>
     </div>

    </li>
    </ul>
    </div>
    <div class="middle_photo_right">
    <ul>
    <li><p>照片尺寸大小在600k之内，支持jpg，bmp，jpeg等格式。</p></li>
    <li><a href="#">本地预览</a></li>
    </ul>
    </div>
    </div>
    
    
   <div class="bottom_photo"><a href="#"></a></div> 
  </div>
  
</div>
</body>
</html>
