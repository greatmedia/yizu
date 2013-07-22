//var basePath = "http://192.168.22.1:8080/yizu";
var basePath = "http://localhost:8080/yizu";
//var basePath = "http://192.168.22.1:8080/yizu";

function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
var id = getQueryStringRegExp("id");
var tid = getQueryStringRegExp("tid");
var pageNum = getQueryStringRegExp("pageNum");
var pageSize = getQueryStringRegExp("pageSize");
var urlinfo = window.location.href;
var url = decodeURI(urlinfo)
var idindexof = url.lastIndexOf("=");
var keyword = url.substring(idindexof + 1);	

var DATA = "data";
var RESULT = "result";
var USER = "user";
var FEIWENTYPES = "feiWenTypes";
var FEIWENS = "feiWens";
var ERROR = "0";
var SUCCESS = "1";
var LOGIN_SUCCESS = "2";
//var LOGIN_EMAIL_PWD_ERROR = "3";
var LOGIN_EMAIL_PWD_ERROR = "4";
var SEARCH_NULL = "5";
var TYPE_NULL = "6";
var EMAILEX = "7";
var REGISTER_SUCCESSED = "8";
var REGISTER_FAILED = "9";
var EMAIL_ERROR = "10";
var PWD_ERROR = "11";
var OAUTHE = "12";
var OAUTHN = "13";
var ROW_NULL = "14";
