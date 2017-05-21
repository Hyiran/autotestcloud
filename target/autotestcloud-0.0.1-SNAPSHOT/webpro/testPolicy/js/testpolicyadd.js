
 var checkFile=function()
 {
 var filename= document.getElementById("file").value;
 var name=filename.substring((filename.length-4),filename.length); 
 // alert(name);
 if (name!=".apk" && name!=".app" )
  {
     alert("上传的应用不正确，请重新上传");
    document.getElementById("file").value="";
  }

 }
 // 关闭当前页面
var  closedWin=function()
{
    
     window.close();
}
var getCaseName=function()
{

  document.getElementById("testcase").value=getCookie("runCase");
  caseName=document.getElementById("testcase").value;
  // 判断是否是编辑页面
  setFromValue();
}

var getApp=function()
{
 // /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestApp",
            //提交的数据
            data:{"plantform":"Android"},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
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
          //        // 获取返回json
                var j=url;  
          //           // alert(json2);
                    json2=JSON.parse(j);
                  
                  var index=1;
                  var  androidselect=document.getElementById("filename1");
          //         // 遍历
             for(var o in json2)
                 { 
                  // 安卓平台
           //  myselect.options[index].style='display: block;'; 
            // var app=json2[o].filename;
            // myselect.options[index].value=app;
            // myselect.options[index].text=app;
                  var app=json2[o].filename;
                 androidselect.options.add(new Option(app,app)); //这个兼容IE与firefox
            index++;         
                
              } 
                } 
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

 
 // /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestApp",
            //提交的数据
            data:{"plantform":"Ios"},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
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
          
                } 
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
}

// 当元素类型是功能还是元素时 触发联动机制
var steRule =function()
{
  var submit="0";
   var al="1";
  var selVal=document.getElementById("platformName").value;
  // alert(selVal);
  if(selVal == "请选择")
  {
    al="0";
     submit="0";
    alert("请选择测试平台")
    // 清空 功能信息并设置只读
  }
  else if(selVal == "Web")
  {
     document.getElementById("androidappPackage").readOnly = true; 
     document.getElementById("androidappActivity").readOnly = true; 
      document.getElementById("androidappPackage").value = ""; 
     document.getElementById("androidappActivity").value = ""; 
     document.getElementById("filename1").selectedIndex=0;
      document.getElementById("deviceName1").selectedIndex=0;
     document.getElementById('deviceName1').disabled=true;
     document.getElementById("plantversion1").selectedIndex=0;
     document.getElementById('plantversion1').disabled=true;

     // 修改其他为只读 
  // 修改其他为只读 
     document.getElementById('elementtimeout1').disabled=true;
     document.getElementById("elementtimeout1").selectedIndex=0;
     document.getElementById("iosbundleid").readOnly = true; 
     document.getElementById("iosbundleid").value = ""; 
     document.getElementById("filename2").selectedIndex=0;
     // document.getElementById("filename2").value = ""; 
     document.getElementById('deviceName2').disabled=true;
     document.getElementById("deviceName2").selectedIndex=0;
     document.getElementById('plantversion2').disabled=true;
     document.getElementById("plantversion2").selectedIndex=0;



     // 修改其他为只读 
     document.getElementById('elementtimeout2').disabled=false;
     document.getElementById("weblogin").readOnly = false; 
     document.getElementById('webmaxwindow').disabled=false;
     document.getElementById('deviceName3').disabled=false;
     document.getElementById('plantversion3').disabled=false;
  }

  else if(selVal == "Ios")
  {
  // 修改Android 相关为可选择

     document.getElementById("androidappPackage").readOnly = true; 
     document.getElementById("androidappActivity").readOnly = true; 
      document.getElementById("androidappPackage").value = ""; 
     document.getElementById("androidappActivity").value = ""; 
     document.getElementById("filename1").selectedIndex=0;
      document.getElementById("deviceName1").selectedIndex=0;
     document.getElementById('deviceName1').disabled=true;
     document.getElementById("plantversion1").selectedIndex=0;
     document.getElementById('plantversion1').disabled=true;


     // 修改其他为只读 
     document.getElementById('elementtimeout1').disabled=false;
     document.getElementById("iosbundleid").readOnly = false; 
     // document.getElementById("filename2").selectedIndex=0;
     document.getElementById('deviceName2').disabled=false;
     document.getElementById('plantversion2').disabled=false;



    // 修改其他为只读 
     document.getElementById('elementtimeout2').disabled=true;
     document.getElementById("elementtimeout2").selectedIndex=0;
     document.getElementById("weblogin").readOnly = true; 
     document.getElementById("weblogin").value = ""; 
     document.getElementById('webmaxwindow').disabled=true;
     document.getElementById("webmaxwindow").selectedIndex=0;
     document.getElementById('deviceName3').disabled=true;
     document.getElementById("deviceName3").selectedIndex=0;
     document.getElementById('plantversion3').disabled=true;
     document.getElementById("plantversion3").selectedIndex=0;    
    }
  else if(selVal == "Android")
  {
    // 修改Android 相关为可选择

     document.getElementById("androidappPackage").readOnly = false; 
     document.getElementById("androidappActivity").readOnly = false; 
     // document.getElementById("filename1").readOnly = false; 
     document.getElementById('deviceName1').disabled=false;
     document.getElementById('plantversion1').disabled=false;


     // 修改其他为只读 
     document.getElementById('elementtimeout1').disabled=true;
     document.getElementById("elementtimeout1").selectedIndex=0;
     document.getElementById("iosbundleid").readOnly = true; 
     document.getElementById("iosbundleid").value = ""; 
     document.getElementById("filename2").selectedIndex=0;;
     // document.getElementById("filename2").value = ""; 
     document.getElementById('deviceName2').disabled=true;
     document.getElementById("deviceName2").selectedIndex=0;
     document.getElementById('plantversion2').disabled=true;
     document.getElementById("plantversion2").selectedIndex=0;


     // 修改其他为只读 
     document.getElementById('elementtimeout2').disabled=true;
     document.getElementById("elementtimeout2").selectedIndex=0;
     document.getElementById("weblogin").readOnly = true; 
     document.getElementById("weblogin").value = ""; 
     document.getElementById('webmaxwindow').disabled=true;
     document.getElementById("webmaxwindow").selectedIndex=0;
     document.getElementById('deviceName3').disabled=true;
     document.getElementById("deviceName3").selectedIndex=0;
     document.getElementById('plantversion3').disabled=true;
     document.getElementById("plantversion3").selectedIndex=0;     

     var va= document.getElementById("deviceName1");
     // 获取设备名称选择的选项
     var index=document.getElementById("deviceName1").selectedIndex;
     // 同步版本并设备不可读
    document.getElementById('plantversion1').disabled=true;
    document.getElementById("plantversion1").selectedIndex=index;
    

  }

}

var DataId ="";
var setFromValue=function()
{
  var policId=getCookie("policId");
  var edit=getCookie("editTestPolicy");
  if (edit=="true")
   {
    // * body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestPolicyById",
            //提交的数据
            data:{"id":policId},
            //返回数据的格式
            datatype: "jsonp",//"xml", "html", "script", "json", "jsonp", "text".
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

                setCookie("editTestPolicy","false");
                var j=url;  
                    // alert(json2);
                    json2=JSON.parse(j);
                 for(var o in json2)
                 { 
                    DataId=json2[o].id;
                    caseName=json2[o].caseName;  
                    timeout=json2[o].timeout;  
                    platformName=json2[o].platformName;  
                    policyName=json2[o].policyName;  
                    deviceName=json2[o].deviceName;  
                    plantversion=json2[o].plantversion;  
                    androidappActivity=json2[o].androidappActivity;  
                    androidappPackage=json2[o].androidappPackage;  
                    filename=json2[o].filename;  
                    iosbundleid=json2[o].iosbundleid;  
                    iosudid=json2[o].iosudid;  
                    elementtimeout=json2[o].elementtimeout;  
                    weblogin=json2[o].weblogin;  
                    webmaxwindow=json2[o].webmaxwindow;  
                } 
                  

                    document.getElementById("policyName").value=policyName;
                    document.getElementById("timeout").value=timeout;
              
                    document.getElementById("testcase").value=caseName;
                

                   if (platformName=="Android")
                    {


                      // 获得设备下拉框对象
                     var select= document.getElementById("deviceName1");
                      for (var i = 0; i <select.options.length; i++) 
                      {
                        var value=select.options[i].innerHTML;
                        if (value==deviceName)
                         {
                         select.options[i].selected = true;
                         break;
                         }
                      }
                      // 获取版本
                       var select= document.getElementById("plantversion1");
                      for (var i = 0; i <select.options.length; i++) 
                      {
                        var value=select.options[i].innerHTML;
                        if (value==plantversion)
                         {
                         select.options[i].selected = true;
                         break;
                         }
                      }

                       // 获取文件名
                       var select= document.getElementById("filename1");
                      for (var i = 0; i <select.options.length; i++) 
                      {
                        var value=select.options[i].innerHTML;
                        if (value==filename)
                         {
                         select.options[i].selected = true;
                         break;
                         }
                      }
                       document.getElementById("platformName").selectedIndex=1;
                       document.getElementById("filename1").value=filename;
                       document.getElementById("androidappActivity").value=androidappActivity;
                       document.getElementById("androidappPackage").value=androidappPackage;
                    }
                    else if (platformName=="Ios") 
                    {
                          // 获得设备下拉框对象
                     var select= document.getElementById("deviceName2");
                      for (var i = 0; i <select.options.length; i++) 
                      {
                        var value=select.options[i].innerHTML;
                        if (value==deviceName)
                         {
                         select.options[i].selected = true;
                         break;
                         }
                      }
                      // 获取版本
                       var select= document.getElementById("plantversion2");
                      for (var i = 0; i <select.options.length; i++) 
                      {
                        var value=select.options[i].innerHTML;
                        if (value==plantversion)
                         {
                         select.options[i].selected = true;
                         break;
                         }
                      }

                      document.getElementById("platformName").selectedIndex=2;
                      document.getElementById("filename2").value=filename;
                      document.getElementById("iosbundleid").value=iosbundleid;
                    }
                    else
                    {
                      document.getElementById("platformName").selectedIndex=3;
                      document.getElementById("weblogin").value=weblogin;
              
                    }


              caseName="";  
              timeout="";   
                    platformName=""; 
                    policyName=""; 
                    deviceName=""; 
                    plantversion=""; 
                    androidappActivity=""; 
                    androidappPackage="";  
                    filename=""; 
                    iosbundleid=""; 
                    iosudid=""; 
                    elementtimeout="";  
                    weblogin="";  
                    webmaxwindow=""; 
                }

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
   }
  
}
 var caseName="";
 var platformName="";
 var policyName="";
 var timeout="";
var deviceName="";
var plantversion="";
var filename="";
var androidappActivity="";
var androidappPackage="";
var iosbundleid="";
var iosudid="";
var elementtimeout="";
var weblogin="";
var webmaxwindow="";
var removeApp="";
// 检查表单
var commitForm=function()
{
  submit="0";
var  al="1";
  caseName=document.getElementById("testcase").value;
   platformName=document.getElementById("platformName").value;
   policyName=document.getElementById("policyName").value;
   timeout=document.getElementById("timeout").value;


 if (platformName=="请选择")
  {
    submit="0";
    if (al=="1") 
    {
      al="0";
      alert("请选择测试平台");
    }
  }
  else
  {
      submit="1";
  }


    if (timeout=="请选择")
  {
    submit="0";
    if (al=="1") 
    {
      alert("请选择超时时间")
      al="0";
    }
  
  }
    if (caseName=="")
  {
    submit="0";
    if (al=="1") 
    {
      alert("请输入测试用例名称")
      al="0";
    }
  
  }

   else
  {
      submit="1";
  }


  if (platformName=="Android" && submit=="1" ) 
  {
   removeApp=document.getElementById("remove1").value;
   deviceName=document.getElementById("deviceName1").value;
   plantversion=document.getElementById("plantversion1").value;
   filename=document.getElementById("filename1").value;
   // alert(filename);
   androidappActivity=document.getElementById("androidappActivity").value;
   androidappPackage=document.getElementById("androidappPackage").value;



  if (androidappActivity=="")
      {
        submit="0";
        if (al=="1") 
        {
          alert("输入安卓登录页activepPckage名")
          al="0";
        }
      
      }

      
       else
      {
          submit="1";
      }

  if (androidappPackage=="")
      {
        submit="0";
        if (al=="1") 
        {
          alert("输入安卓测试包名")
          al="0";
        }
      
      }

      
       else
      {
          submit="1";
      }

  if (filename=="")
      {
        submit="0";
        if (al=="1") 
        {
          alert("请输入安卓文件名")
          al="0";
        }
      
      }

      
       else
      {
          submit="1";
      }

   if (plantversion=="请选择")
      {
        submit="0";
        if (al=="1") 
        {
          alert("请选择安卓版本")
          al="0";
        }
      
      }

      
       else
      {
          submit="1";
      }

    if (deviceName=="请选择")
    {
      submit="0";
      if (al=="1") 
      {
        alert("请选择安卓设备")
        al="0";
      }
    
    }

    
     else
    {
        submit="1";
    }

  }

  if (platformName=="Ios" && submit=="1" ) 
  {
    removeApp=document.getElementById("remove2").value;
   deviceName=document.getElementById("deviceName2").value;
   plantversion=document.getElementById("plantversion2").value;
   iosbundleid=document.getElementById("iosbundleid").value;
   filename=document.getElementById("filename2").value;
   elementtimeout=document.getElementById("elementtimeout1").value;
  }
   if (platformName=="Web" && submit=="1" ) 
  {
     removeApp=document.getElementById("remove3").value;
   deviceName=document.getElementById("deviceName3").value;
   plantversion=document.getElementById("plantversion3").value;
   webloginn=document.getElementById("webloginn").value;
   webmaxwindow=document.getElementById("webmaxwindow").value;
   elementtimeout=document.getElementById("elementtimeout1").value;
  }
  if (submit=="1")
   {
     runTest();
   }


}
// 获取设备列表列表
var GetMachineList=function(plantform)
{
  
 // /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestMachineList",
            //提交的数据
            data:{"plantform":""},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
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
                
                var j=url;  
                json2=JSON.parse(j);
                  
                  var index=1;
                  var  androidDevice=document.getElementById("deviceName1");
                  var  androidPlant=document.getElementById("plantversion1");
                  var  iosDevice=document.getElementById("deviceName2");
                  var  iosPlant=document.getElementById("plantversion2");
             for(var o in json2)
                 { 

                  var plantform=json2[o].plantform;
                    if (plantform=="android") 
                    {
                        var  device=json2[o].machineName;
                        var  version=json2[o].version;
                        androidDevice.options.add(new Option(device,device));
                        androidPlant.options.add(new Option(version,version));
                            
                    }
                    else
                    {
                        var  device=json2[o].machineName;
                        var  version=json2[o].version;
                        iosDevice.options.add(new Option(device,device));
                        iosPlant.options.add(new Option(version,version));
                    }
                 
              
                
                  
                }

              } 
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

}


// 运行测试
var runTest=function()
{
  //  alert("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。");
  document.getElementById("run").disabled=true;

  
 // /* body... */
    $.ajax({


            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/AddTestPolicy",
            //提交的数据
            data:{"id":DataId,"caseName":caseName,"platformName":platformName,"policyName":policyName,"timeout":timeout,"deviceName":deviceName,"plantversion":plantversion,"filename":filename,"androidappActivity":androidappActivity,"androidappPackage":androidappPackage,"iosbundleid":iosbundleid,"iosudid":iosudid,"elementtimeout":elementtimeout,"weblogin":weblogin,"webmaxwindow":webmaxwindow,"removeApp":removeApp},
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
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
                    
                 alert(url);  
                 refesh();  
          } 
                  
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

}