// 当元素类型是功能还是元素时 触发联动机制
var steRule =function()
{
  var selVal=document.getElementById("elementtype").value;
  // alert(selVal);
if(selVal == "元素")
{
  // 清空 功能信息并设置只读
document.getElementById("parsfuntion").value = "";
document.getElementById("parsfuntion").readOnly = true;
document.getElementById("funtionName").selectedIndex=0;
document.getElementById("funtionName").setAttribute('disabled','true');
 document.getElementById("elementname").readOnly = false;
      
     document.getElementById("parselement").readOnly = false;
     document.getElementById("expet").readOnly = false;
     document.getElementById('elementName').disabled=false;
     document.getElementById('asser').disabled=false;

}
  // 清空 元素信息并设置只读
  else
  {
    document.getElementById("parsfuntion").readOnly = false;
     document.getElementById('funtionName').disabled=false;
     document.getElementById("elementname").readOnly = true;
     document.getElementById("elementname").value = "";
     document.getElementById("parselement").value = "";
     document.getElementById("parselement").readOnly = true;
     document.getElementById("expet").readOnly = true;
     document.getElementById("expet").value = "";
      document.getElementById("elementName").selectedIndex=0;
     document.getElementById("elementName").setAttribute('disabled','true');
     document.getElementById("asser").selectedIndex=0;
     document.getElementById("asser").setAttribute('disabled','true');
  }
}

// 重置页面数据
var Reset =function()
{
  var casename= getCookie("casename");
  // 不为空 说明是编辑页面
  if (casename!="")
   {

   }
   // 新增页面
   else
   {
   document.getElementById("testCase").value="";
   }

    document.getElementById("elementtype").selectedIndex=0;
    document.getElementById("step").value="";
    document.getElementById("elementname").value="";
    document.getElementById("weblocatype").selectedIndex=0;
    document.getElementById("weblocatstring").value="";
    document.getElementById("ioslocatype").selectedIndex=0;
    document.getElementById("ioslocatstring").value="";
    document.getElementById("androidlocatype").selectedIndex=0;
    document.getElementById("androidlocatstring").value="";
    document.getElementById("elementName").selectedIndex=0;
    document.getElementById("funtionName").selectedIndex=0;
    document.getElementById("parselement").value="";
    document.getElementById("parsfuntion").value="";
    document.getElementById("asser").selectedIndex=0;
    document.getElementById("expet").value="";
}


// 读取上级页面生成的缓存数据到form中
var getFormData =function()
{
// alert(getCookie("casename"));
document.getElementById("testCase").value=getCookie("casename");
document.getElementById("testCase").readOnly = true;

document.getElementById("step").value=getCookie("step");

var type=getCookie("elementtype");
// alert(type);
if (type=="功能") 
{
  document.getElementById("elementtype").selectedIndex=2;
}
else
{
   document.getElementById("elementtype").selectedIndex=1;
}

// alert(getCookie("elementnameByStep"));
document.getElementById("elementname").value=getCookie("elementname");


 type=getCookie("weblocatype");
// alert(type);
if (type=="id") 
{
  document.getElementById("weblocatype").selectedIndex=1;
}
else if (type=="xpath") 
  {
   document.getElementById("weblocatype").selectedIndex=2;
  }
  else if (type=="css") 
  {
   document.getElementById("weblocatype").selectedIndex=3;
  }
  else if (type=="class") 
  {
   document.getElementById("weblocatype").selectedIndex=4;
  }
else
{
   document.getElementById("weblocatype").selectedIndex=0;
}

// alert(getCookie("weblocatstring"));
document.getElementById("weblocatstring").value=getCookie("weblocatstring");




 type=getCookie("ioslocatype");
// alert(type);
if (type=="id") 
{
  document.getElementById("ioslocatype").selectedIndex=1;
}
else if (type=="xpath") 
  {
   document.getElementById("ioslocatype").selectedIndex=2;
  }
  else if (type=="css") 
  {
   document.getElementById("ioslocatype").selectedIndex=3;
  }
  else if (type=="class") 
  {
   document.getElementById("ioslocatype").selectedIndex=4;
  }
else
{
   document.getElementById("ioslocatype").selectedIndex=0;
}

// alert(getCookie("weblocatstring"));
document.getElementById("ioslocatstring").value=getCookie("ioslocatstring");




 type=getCookie("androidlocatype");
// alert(type);
if (type=="id") 
{
  document.getElementById("androidlocatype").selectedIndex=1;
}
else if (type=="xpath") 
  {
   document.getElementById("androidlocatype").selectedIndex=2;
  }
  else if (type=="css") 
  {
   document.getElementById("androidlocatype").selectedIndex=3;
  }
  else if (type=="class") 
  {
   document.getElementById("androidlocatype").selectedIndex=4;
  }
else
{
   document.getElementById("androidlocatype").selectedIndex=0;
}

// alert(getCookie("weblocatstring"));
document.getElementById("androidlocatstring").value=getCookie("androidlocatstring");

document.getElementById("expet").value=getCookie("expet");

 type=getCookie("asser");
// alert(type);
if (type=="是否相同") 
{
  document.getElementById("asser").selectedIndex=1;
}
else if (type=="是否存在") 
  {
   document.getElementById("asser").selectedIndex=2;
  }
  else if (type=="是否消失") 
  {
   document.getElementById("asser").selectedIndex=3;
  }
  else if (type=="是否为真") 
  {
   document.getElementById("asser").selectedIndex=4;
  }
else
{
   document.getElementById("asser").selectedIndex=0;
}


if (getCookie("elementtype")=="功能")
 {
  document.getElementById("parsfuntion").value=getCookie("pars");

     document.getElementById("parselement").readOnly = true;
     document.getElementById("expet").readOnly = true;
     document.getElementById("elementName").setAttribute('disabled','true');
     document.getElementById("asser").setAttribute('disabled','true');
    type=getCookie("action");
        // alert(type);
        if (type=="登录") 
        {
          document.getElementById("funtionName").selectedIndex=1;
        }
        else if (type=="退出") 
          {
           document.getElementById("funtionName").selectedIndex=2;
          }
          else if (type=="审批-通过") 
          {
           document.getElementById("funtionName").selectedIndex=3;
          }
          else if (type=="审批-拒绝") 
          {
           document.getElementById("funtionName").selectedIndex=4;
          }
          else if (type=="批审") 
          {
           document.getElementById("funtionName").selectedIndex=5;
          }
        else
        {
           document.getElementById("funtionName").selectedIndex=0;
        }
 }
 else
 {
  document.getElementById("parselement").value=getCookie("pars");
   document.getElementById("parsfuntion").readOnly = true;

    document.getElementById("funtionName").setAttribute('disabled','true');
            type=getCookie("action");
          // alert(type);
          if (type=="点击") 
          {
            document.getElementById("elementName").selectedIndex=1;
          }
          else if (type=="输入") 
            {
             document.getElementById("elementName").selectedIndex=2;
            }
            else if (type=="滑动-次数") 
            {
             document.getElementById("elementName").selectedIndex=3;
            }
            else if (type=="滑动-元素") 
            {
             document.getElementById("elementName").selectedIndex=4;
            }
          else
          {
             document.getElementById("elementName").selectedIndex=0;
          }
 }

}

 // <!-- 提交时 获取参数址-->
       var  checkdata=function()
   {     
            // alert('dd');
            // 是否允许提交
             var commit="true";
               // 是否提醒
             var icon="true";

             var casename="";
             var step="";
             var elementtype="";
             var elementname="";
             var weblocatype="";
             var weblocatstring="";
             var ioslocatype="";
             var ioslocatstring="";
             var androidlocatype="";
             var androidlocatstring="";
             var action="";
             var pars="";
             var expet="";
             var asser="";
  var selVal=document.getElementById("elementtype").value;
             <!-- 判断是否输入了用例名称-->
           casename=document.getElementById("testCase").value;
            if (casename=="") 
            {    
                 if (icon=="true")
                      {             
                      commit="false";
                      alert("用例名称不能为空");
                      icon="false"
                        // alert(icon);
                      }
                   
            }
             elementtype=document.getElementById("elementtype").value;
                if (elementtype=="请选择") 
                {
                      // alert(icon);
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请选择元素类型");
                        icon="false"
                      }
                   
                }

                  step=document.getElementById("step").value;
                        if (step=="") 
                  {
                                if (icon=="true")
                              {
                                commit="false";
                                alert("请输入执行步骤");
                                icon="false"
                              }
                            
                  }
      
                  if (selVal=="元素")
                   {
                     elementname=document.getElementById("elementname").value;
                      if (elementname=="") 
                      {
                         
                          if (icon=="true")
                            {
                              commit="false";
                              alert("请输入元素名称");
                              icon="false"
                              // alert(icon);
                            }
                         
                      }
                   }
               

                  weblocatype=document.getElementById("weblocatype").value;
                if (weblocatype!="请选择") 
                {
                   
                   weblocatstring=document.getElementById("weblocatstring").value;

                    if (weblocatstring=="") 
                            {
                              if (icon=="true")
                                {
                                  alert(weblocatype);
                                  commit="false";
                                  alert("您已选择网页类型，请填写网页定位");
                                  icon="false"
                                  // alert(icon);
                                }
                             }
                }
              else
                {
                  weblocatype="";
                }
                   androidlocatype=document.getElementById("androidlocatype").value;
                if (androidlocatype!="请选择") 
                {
                   
                   androidlocatstring=document.getElementById("androidlocatstring").value;

                    if (androidlocatstring=="") 
                            {
                              if (icon=="true")
                                {
                                  commit="false";
                                  alert("您已选择安卓类型，请填写安卓定位");
                                  icon="false"
                                  // alert(icon);
                                }
                             }
                }
                else
                {
                  androidlocatype="";
                }
                ioslocatype=document.getElementById("ioslocatype").value;
                if (ioslocatype!="请选择") 
                {
                   
                   ioslocatstring=document.getElementById("ioslocatstring").value;

                    if (ioslocatstring=="") 
                            {
                              if (icon=="true")
                                {
                                  commit="false";
                                  alert("您已选择苹果类型，请填写苹果定位");
                                  icon="false"
                                  // alert(icon);
                                }
                             }
                }       
                 else
                {
                  ioslocatype="";
                }

 if (selVal=="元素") 
 {
         action=document.getElementById("elementName").value;
                if (action=="请选择") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请选择操作名称");
                        icon="false"
                        // alert(icon);
                      }
                   
                }

           pars=document.getElementById("parselement").value;
                if (pars=="") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请输入元素参数");
                        icon="false"
                        // alert(icon);
                      }
                   
                }

            asser=document.getElementById("asser").value;
                if (asser=="请选择") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请选择判断方式");
                        icon="false"
                        // alert(icon);
                      }
                   
                }

            expet=document.getElementById("expet").value;
                if (expet=="") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请输入预期结果");
                        icon="false"
                        // alert(icon);
                      }
                   
                }
 }
else
{
   action=document.getElementById("funtionName").value;
                if (action=="请选择") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请选择功能名称");
                        icon="false"
                        // alert(icon);
                      }
                   
                }

           pars=document.getElementById("parsfuntion").value;
                if (pars=="") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("请输入功能参数");
                        icon="false"
                        // alert(icon);
                      }
                   
                } 
}

            
if (commit=="true")
{
                // 如果达到允许条件                
        // subelemnte(casename,elementstep,"0",elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,pars1,expet,action);

updtateTestCase(casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,action,pars,expet,asser);
                  <!-- 赋值用例，步骤++-->            
 }
        
 }

// 编辑用例步骤
var updtateTestCase=function  (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,action,pars,expet,asser) 

{  

  var data=getCookie("Dataid");
  // alert(data);
    /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/UpdateTestCaseStep",
            //提交的数据
             data:{"casename":casename,"step":step,"elementtype":elementtype,"elementname":elementname,"weblocatype":weblocatype,"weblocatstring":weblocatstring,"ioslocatype":ioslocatype,"ioslocatstring":ioslocatstring,"androidlocatype":androidlocatype,"androidlocatstring":androidlocatstring,"action":action,"pars":pars,"expet":expet,"asser":asser,"Dataid":data},
            //返回数据的格式
            datatype: "jsonp",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function()
            {
            // alert("提交");
            },
       //成功返回之后调用的函数             
            success:function(data)
            {
          
            } ,
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus)
            {
                var url=XMLHttpRequest.responseText;
                // alert(url);
                if (url =="/autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
                else
                {
                        var res=XMLHttpRequest.responseText;            
                alert(res);  
                }
          

            
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}


// 元素步骤只可以是数字,查询数据库是否有重复
var checkedstep=function()
{

       var reg = new RegExp("^[0-9]*$");
       var obj = document.getElementById("step");
     
    if(!reg.test(obj.value))
    {
        // alert("请输入数字!");
    }
    if(!/^[0-9]+$/.test(obj.value))
    {
        alert("执行步骤：请输入数字");
        document.getElementById("step").value="";
    }
    else
    {
      // // alert(obj.value);
      // if (obj.value!="1") 
      // {
        checkeTestCaseStep(obj.value);
      // }
          
    }
  
}


var  checkeTestCaseStep=function (step)
{

    var mytestCase=document.getElementById("testCase").value;
    /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/getTestCaseStepAllow",
            //提交的数据
            data:{"testCase":mytestCase,"step":step},
            //返回数据的格式
            datatype: "JSONP",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function()
            {
            },
            //成功返回之后调用的函数             
            success:function(data)
            {        
            } ,
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus)
            {
                var url=XMLHttpRequest.responseText;
                // alert(url);
                if (url =="/autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
                else
                {
                          // 获取返回数据
                             // alert(XMLHttpRequest.responseText);
                        if (XMLHttpRequest.responseText == "编号可用")
                         {
                        
                         }
                     else
                         {
                             alert(XMLHttpRequest.responseText);
                           
                         }
                }
                
                
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
}