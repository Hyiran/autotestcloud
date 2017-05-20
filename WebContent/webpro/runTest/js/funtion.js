function setCookie(name,value)
 {


var Days = 30;
var exp = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
    // document.cookie = name + '=' + escape(value);
    // alert(name);
    // alert(value);
}

// 获取cookis
function getCookie(name)
{
  
  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
} 

function getCaseList()
{
 alert(getCookie("runCase"));
} 

