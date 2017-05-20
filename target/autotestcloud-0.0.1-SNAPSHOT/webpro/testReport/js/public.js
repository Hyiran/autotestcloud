 // 关闭当前页面
var  closeTestStep=function()
{
    setCookie("newCase","");
     setCookie("newStep",""); 
     window.close();
}
 // 刷新页面
var refesh=function()
{
  location.reload(true);
}
// 获取td的值
function getTrText(id)
 {
 var text=document.getElementById(id).innerHTML;
 return text;
 }
 // 跳转到新增用例页面
var newTestCase =function()
{
  window.open("testcaseadd.html");
}



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
function testCooki()
{
  var key="testCaseName";
   var valu = "aaa";  
  setCookie(key,valu);
  alert(getCookie(key));
}