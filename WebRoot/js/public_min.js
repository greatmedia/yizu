	//回到顶部
    function goTop(acceleration,time) {
    	 acceleration = acceleration || 0.1;
    	 time = time || 16;

    	 var x1 = 0;
    	 var y1 = 0;
    	 var x2 = 0;
    	 var y2 = 0;
    	 var x3 = 0;
    	 var y3 = 0;

    	 if (document.documentElement) {
    	  x1 = document.documentElement.scrollLeft || 0;
    	  y1 = document.documentElement.scrollTop || 0;
    	 }
    	 if (document.body) {
    	  x2 = document.body.scrollLeft || 0;
    	  y2 = document.body.scrollTop || 0;
    	 }
    	 var x3 = window.scrollX || 0;
    	 var y3 = window.scrollY || 0;

    	 // 滚动条到页面顶部的水平距离
    	 var x = Math.max(x1, Math.max(x2, x3));
    	 // 滚动条到页面顶部的垂直距离
    	 var y = Math.max(y1, Math.max(y2, y3));

    	 // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
    	 var speed = 1 + acceleration;
    	 window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));

    	 // 如果距离不为零, 继续调用迭代本函数
    	 if(x > 0 || y > 0) {
    	  var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
    	  window.setTimeout(invokeFunction, time);
    	 }
    };
    
    
    function pagination() {
    	this.printPagination = function(pageNum, totalRecords, numPerPage, divObj,id,ttp) {  //当前页     总数     没有显示数
    		var pageHtml = '';
    		var pageNum = pageNum;
    		if (null == pageNum || '' == pageNum) {
    			$('#' + divObj).html('');
    			return;
    		}
    		if (pageNum < 1) {
    			$('#' + divObj).html('');
    			return;
    		}
    		var totalNum = totalRecords;
    		if (totalNum <= 0) {
    			$('#' + divObj).html('');
    			return;
    		}
    		var numPerPage = numPerPage;
    		var totalPages = (totalNum % numPerPage) == 0
    				? (totalNum / numPerPage)
    				: (totalNum / numPerPage + 1);
    		totalPages = parseInt(totalPages, 0);
    		if (pageNum > totalPages) {
    			pageNum = totalPages;
    		}
    		pageHtml +='<a href="javascript:void(0);" onclick="flipPage('+numPerPage+',1,\''+id+'\',\''+ttp+'\');" >首页</a>';
    		if (pageNum > 1) {
    			var prePageNum = pageNum - 1;
    			pageHtml += '<a href="javascript:void(0);" onclick="flipPage('+numPerPage+','+ prePageNum +',\''+id+'\',\''+ttp+'\');">上一页&gt; </a>';
    		} else if (pageNum == 1) {
    			pageHtml += '<a href="javascript:void(0);">上一页&lt; </a>';
    		}
    		if (0 < pageNum && pageNum <= 3) {
    			if (totalPages <= 7) {
    				for (var j = 1; j <= totalPages; j++) {
    					if (j == pageNum) {
    						pageHtml += '<span class="current">' + j+ '</span>';
    					} else {
    						pageHtml += '<a href="javascript:void(0);"  onclick="flipPage('+numPerPage+',' + j + ',\''+id+'\',\''+ttp+'\');">' + j + '</a>';
    					}
    				}
    			} else {
    				for (var j = 1; j <= 7; j++) {
    					if (j == pageNum) {
    						pageHtml += '<span class="current">' + j+ '</span>';
    					} else {
    						pageHtml += '<a href="javascript:void(0);"  onclick="flipPage('+numPerPage+','+ j + ',\''+id+'\',\''+ttp+'\');">' + j + '</a>';
    					}
    				}
    			}
    		}
    		if (pageNum >= 4) {
    			if (pageNum <= totalPages - 3) {
    				var p = 3;
    				for (var i = -p; i <= p; i++) {
    					var page = pageNum + i;
    					if (page > 0 && page <= totalPages) {
    						if (page == pageNum) {
    							pageHtml += '<span class="current">' + page+ '</span>';
    						} else {
    							pageHtml += '<a href="javascript:void(0);"  onclick="flipPage('+numPerPage+','+ page + ',\''+id+'\',\''+ttp+'\');">' + page + '</a>';
    						}
    					} else if (page > totalPages) {
    					}
    				}
    			} else if (pageNum <= totalPages && pageNum >= totalPages - 3) {
    				for (var i = totalPages - 7 + 1; i <= totalPages; i++) {
    					if (i > 0 && i <= totalPages) {
    						if (i == pageNum) {
    							pageHtml += '<span class="current">' + i+ '</span>';
    						} else {
    							pageHtml += '<a href="javascript:void(0);"  onclick="flipPage('+numPerPage+','+ i + ',\''+id+'\',\''+ttp+'\');">' + i + '</a>';
    						}
    					}
    				}
    			}
    		}
    		
    		if (pageNum < totalPages) {
    			var nextPage = pageNum + 1;
    			pageHtml += '<a href="javascript:void(0);" onclick="flipPage('+numPerPage+','+ nextPage + ',\''+id+'\',\''+ttp+'\');">下一页&gt; </a>';
    		} else if (pageNum == totalPages) {
    			pageHtml += '<a href="javascript:void(0);">下一页&gt; </a>';
    		}else{
    			pageHtml += '<a href="javascript:void(0);">下一页&gt; </a>';
    		}
    		pageHtml +='<a href="javascript:void(0);" onclick="flipPage('+numPerPage+','+totalPages+',\''+id+'\',\''+ttp+'\');" >尾页</span>';
    		$('#' + divObj).html(pageHtml);
    	};
    }
    
    /**
     * 截取数字
     * @param str
     * @param lent
     * @returns
     */
    function sub(str,lent){
    	var len = str.length;
    	if(len > lent){
    		return str = str.substring(0,lent)+"...";
    	}else{
    		return str;
    	}
    }