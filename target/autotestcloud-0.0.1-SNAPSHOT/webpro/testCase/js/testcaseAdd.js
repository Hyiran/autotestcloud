var getPar2=function()
{
    var selVal=document.getElementById("funtionName").selectedIndex;
      
             if(selVal==1)
            {
              document.getElementById("parsfuntion").value="{ \"time\":\"休眠时间（毫秒）\"}";
                // document.getElementById("parsfuntion").value="{\"elemntspar\": [{\"user\": \"用户名\",\"password\": \"密码\", \"commit\":\"是否点击登陆\"}]}";
            }
            else if(selVal==2)
            {
                // document.getElementById("parsfuntion").value="{\"elemntspar\": [{\"allow\": \"是否点击通过\",\"infor\": \"审批信息\"}]}";
               
            }
}
var getPar=function()
{
     var selVal=document.getElementById("elementName").selectedIndex;
      
             if(selVal==1)
            {
                document.getElementById("parselement").value="{\"sendkeystring\": \"输入的数据\"}";


            }
            else if(selVal==2)
            {
                document.getElementById("parselement").value="{ \"time\":\"点击时间（毫秒）\"}";
               
            }
            else if(selVal==3)  
            {
                document.getElementById("parselement").value="{ \"time\":\"点击时间（毫秒）\" ,\"x\": \"宽度值\", \"y\":\"高度值\"}";
               
            }
             else if(selVal==4)
            {
                document.getElementById("parselement").value="{\"swip-times\": [{ \"times\": \"滑动次数\",\"to\":\"滑动方向(上，下，左，右)\"}]}";
               
            }
              else if(selVal==5)
            {
                document.getElementById("parselement").value="{\"swip-element\": [{ \"times\": \"滑动次数\", \"to\":\"滑动方向(上，下，左，右)\",\"androidlotype\": \"安卓定位方式\",\"androidlottring\": \"安卓定位器\"}]}";
               }
}



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
  
    document.getElementById("testCase").value="";
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

AddTestCase(casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,action,pars,expet,asser);

                  <!-- 赋值用例，步骤++-->            
 }
        
 }



// 增加用例步骤
var AddTestCase=function (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,action,pars,expet,asser) 

{  
    /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/AddTestCase",
            //提交的数据
             data:{"casename":casename,"step":step,"elementtype":elementtype,"elementname":elementname,"weblocatype":weblocatype,"weblocatstring":weblocatstring,"ioslocatype":ioslocatype,"ioslocatstring":ioslocatstring,"androidlocatype":androidlocatype,"androidlocatstring":androidlocatstring,"action":action,"pars":pars,"expet":expet,"asser":asser},
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
                  <!-- 赋值用例，步骤++ 到cooki-->                    
                 var ste=parseInt(step)+1; 
                 setCookie("newCase",casename);
                 setCookie("newStep",ste); 
                <!-- 刷新--> 
               refesh();
                }
             
                    
            
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}

// 添加多条数据时，赋值用例名 几step
var getNameStep=function()
{

      try
      {
        // // 当是从编辑页面新增的时候，
        // var editAdd =getCookie("testCaseName");  
        //   if (editAdd!="") 
        //   {
        //   document.getElementById("testCase").value=editAdd;
        //   }
        //   // 当是从用例页面，跳转新增页面，再次新增步骤时
        // else
        // {
          // alert(getCookie("newCase"));
          document.getElementById("testCase").value=getCookie("newCase");
          document.getElementById("step").value=getCookie("newStep");
        // }
           
      }
      catch(err)
      {
         //在此处理错误
      }
   
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
