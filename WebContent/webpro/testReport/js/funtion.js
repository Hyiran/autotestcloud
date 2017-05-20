// 测试方法
var test=function () 
{
   
   alert("ss");
    // var obj = document.getElementById("action");

    // var index=obj.selectedIndex;
    // alert(index);
    // // var valu= options[index].text;

    // var valu2= obj[index].text;
    //  alert(valu2);
        // alert(obj[index].value);
}
// 元素步骤只可以是数字
var checkedstep=function()
{

       var reg = new RegExp("^[0-9]*$");
       var obj = document.getElementById("userstep");
       var ob1 = document.getElementById("userstep2");
        if (obj.value==ob1.value) 
      {
        alert("功能与元素的步骤不可一致");
      }
    if(!reg.test(obj.value))
    {
        // alert("请输入数字!");
    }
    if(!/^[0-9]+$/.test(obj.value))
    {
        alert("请输入数字!");
        document.getElementById("userstep").value="";
    }
    else
    {
      // alert(obj.value);
      if (obj.value!="1") 
      {
        checkeTestCaseStep(obj.value);
      }
          
    }
  
}
// 功能步骤只能是数字
var checkedstep2=function()
{

       var reg = new RegExp("^[0-9]*$");
       var obj = document.getElementById("userstep2");
       var ob1 = document.getElementById("userstep");
      if (obj.value==ob1.value) 
      {
        alert("功能与元素的步骤不可一致");
      }
    if(!reg.test(obj.value))
    {
        // alert("请输入数字!");
    }
    if(!/^[0-9]+$/.test(obj.value))
    {
        alert("请输入数字!");
        document.getElementById("userstep2").value="";
    }
      else
    {
          if (obj.value!="1") 
      {
        checkeTestCaseStep(obj.value);
      }
          
         
    }
}
    function formCallback() 
        {
        document.getElementById("eleentcode").value="";
        document.getElementById("locatstring1").value="";
        document.getElementById("locatstring2").value="";
        document.getElementById("locatstring3").value="";
        document.getElementById("canshutt").value="";
        document.getElementById("expet").value="";
        document.getElementById("canshutt2").value="";
        document.getElementById("action").selectedIndex=0;
        document.getElementById("asser").selectedIndex=0;
        document.getElementById("action2").selectedIndex=0;
        document.getElementById("Weblocattype").selectedIndex=0;
        document.getElementById("Ioslocattype").selectedIndex=0;
        document.getElementById("Androidlocattype").selectedIndex=0;
        }


// 返回上级界面
    function returnlastpage()
    {
    window.history.back();location.reload();
    }


 var testcase="";
 // 获取测试用例
 function gettestcase()
 {
     if (testcase!="") 
           {
            document.getElementById("testCase").value=testcase;
            // alert(document.getElementById("testCase").value);
           }
           else
           {
             alert('ddd');
           }
 }
 // <!-- 用户输入用例名后，按钮可点击-->

       var  Selbtn=function() 
        {
           document.getElementById("bt1").disabled=false;
           document.getElementById("bt2").disabled=false;
        
        }

 var step="1";
        
        // <!-- 添加功能 获取最新步骤-->
       var  AddField=function()
        {
                 var ca= document.getElementById("testCase").value;
               if (ca=="")
                {
                    alert("请您输入测试用例名称")
                }
                else
                {
                 <!-- 显示 元素div-->
                document.getElementById("fieldAdd").style.display="block";
                 <!-- 显示 提交重置按钮-->
                document.getElementById("butten").style.display="block";
                   <!-- 获取用户输入的用例名称-->
                testcase= document.getElementById("testCase").value;
                 }

            

        }
         var  cancleField=function()
        {
            if (document.getElementById("fieldAdd2").style.display=="none")
             {
                alert("您还没有添加元素");
             }
             else
             {
                <!-- 隐藏元素div-->
            document.getElementById("fieldAdd2").style.display="none";
               <!-- 隐藏 提交重置按钮-->
            document.getElementById("butten").style.display="none"; 
             }
           
            

        }
         var  cancleField2=function()
        {
           if (document.getElementById("fieldAdd").style.display=="none")
             {
                alert("您还没有添加功能");
             }
             else
             {
                <!-- 隐藏元素div-->
            document.getElementById("fieldAdd").style.display="none";
               <!-- 隐藏 提交重置按钮-->
            document.getElementById("butten").style.display="none"; 
             }
           
            

        }
          // <!-- 添加元素 获取最新步骤-->
        var  AddField2=function()
        {

           var ca= document.getElementById("testCase").value;
           if (ca=="")
            {
                alert("请您输入测试用例名称")
            }
            else
            {
             <!-- 显示 元素div-->
            document.getElementById("fieldAdd2").style.display="block";
             <!-- 显示 提交重置按钮-->
            document.getElementById("butten").style.display="block";
               <!-- 获取用户输入的用例名称-->
            testcase= document.getElementById("testCase").value;
            // alert(testcase);
            }
          
   
        }


// !-- 修改操作参数列表 -->
       var   SelChange=function()
        {
            var selVal=document.getElementById("action").selectedIndex;
      
             if(selVal==1)
            {
                document.getElementById("canshutt").value="\"sendkey\":[{\"sendkeystring\": \"输入的数据\"}]";


            }
            else if(selVal==2)
            {
                document.getElementById("canshutt").value="{\"tap-element\": [\r\n{ \"fingers\": \"手指个数\", \"time\":\"点击时间(毫秒)\"}\r\n]}";
               
            }
            else if(selVal==3)
            {
                document.getElementById("canshutt").value="{\"tap-position\": [\r\n{ \"fingers\": \"手指个数\", \"x\": \"宽度值\", \"y\":\"高度值\"}\r\n]}";
               
            }
             else if(selVal==4)
            {
                document.getElementById("canshutt").value="{\"swip-times\": [\r\n{ \"times\": \"滑动次数\",\"to\":\"滑动方向(上，下，左，右)\"}\r\n]}";
               
            }
              else if(selVal==5)
            {
                document.getElementById("canshutt").value="{\"swip-element\": [\r\n{ \"times\": \"滑动次数\", \"to\":\"滑动方向(上，下，左，右)\",\"androidlotype\": \"安卓定位方式\",\"androidlottring\": \"安卓定位器\"}\r\n]}";
               }
        }


   // <!-- 修改功能操作参数列表 -->
   var  SelChange2=function()
        {
             
            var selVal=document.getElementById("action2").selectedIndex;
      
             if(selVal==1)
            {
                document.getElementById("canshutt2").value="\"elemntspar\": [{\"user\": \"用户名\",\"password\": \"密码\", \"commit\":\"是否点击登陆\"}]";
            }
            else if(selVal==2)
            {
                document.getElementById("canshutt2").value="\"elemntspar\": [{\"allow\": \"是否点击通过\",\"infor\": \"审批信息\"}]";
               
            }
          
        }

// 选择web平台
var websel=function () 
{
    // alert("点击web弹出web定位方式定位器");
   if(document.getElementById("web").checked)
   { 
     // alert("复选框已经被点击");
     document.getElementById("webforms").style.display="block";
     // alert("弹出web定位相关");
    
    }
    else
    {
           document.getElementById("webforms").style.display="none";
    }
}
// 选择ios平台
var iossel=function () 
{  
    // alert("点击ios弹出ios定位方式定位器");
   if(document.getElementById("ios").checked)
   { 
     // alert("复选框已经被点击");
     document.getElementById("iosforms").style.display="block";
     // alert("弹出ios定位相关");
    
    }
    else
    {
           document.getElementById("iosforms").style.display="none";
    }
}
// 选择android平台
var androidsel=function () 
{
      // alert("点击web弹出web定位方式定位器");
   if(document.getElementById("android").checked)
   { 
     // alert("复选框已经被点击");
     document.getElementById("androidforms").style.display="block";
     // alert("弹出web定位相关");
    
    }
    else
    {
           document.getElementById("androidforms").style.display="none";
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
             var elementstep="";
             var elementsstep="";
             var elementname="";
             var elementtype="0"
             var weblocatype="";
             var weblocatstring="";
             var ioslocatype="";
             var ioslocatstring="";
             var androidlocatype="";
             var androidlocatstring="";
             var pars1="";
             var expet="";
             var action="";
             var pars2="";
             var action2="";

             <!-- 判断是否点击了功能-->
            var p1=document.getElementById("fieldAdd").style.display;
            if (p1=="block") 
            {
                 casename=document.getElementById("testCase").value;
                if (casename=="") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("用例名称不能为空");
                        icon=="fals"
                        // alert(icon);
                      }
                   
                }
                elementsstep=document.getElementById("userstep2").value;
                if (elementsstep=="") 
                {
                      // alert(icon);
                    if (icon=="true")
                      {
                        commit="false";
                        alert("功能操作步骤不能为空");
                        icon="false"
                      }
                   
                }

                    var a1 = document.getElementById("action2");
                    var in1=a1.selectedIndex;
                    var va= a1[in1].text;
                        if (va=="请选择") 
                        {
                                if (icon=="true")
                              {
                                commit="false";
                                alert("没有选项操作名称");
                                icon="false"
                              }
                            
                        }
                        else
                        {
                            action2=va;
                        }

                pars2=document.getElementById("canshutt2").value;
                if (pars2=="") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("功能参数列表不能为空");
                        icon=="false"
                        // alert(icon);
                      }
                   
                }

            }
            


            <!-- 判断是否点击了元素-->

             var p2=document.getElementById("fieldAdd2").style.display;

             if (p2=="block") 
             {
                 
                casename=document.getElementById("testCase").value;
                if (casename=="") 
                {
                   
                    if (icon=="true")
                      {
                        commit="false";
                        alert("用例名称不能为空");
                        icon=="fals"
                        // alert(icon);
                      }
                   
                }
                elementstep=document.getElementById("userstep").value;
                if (elementstep=="") 
                {
                      // alert(icon);
                    if (icon=="true")
                      {
                        commit="false";
                        alert("元素操作步骤不能为空");
                        icon="false"
                      }
                   
                }
                elementname=document.getElementById("eleentcode").value;
                if (elementname=="") 
                {
                     if (icon=="true")
                      {
                        commit="false";
                        alert("元素标识不能为空");
                        icon="false"
                      }
                    
                }
                if (document.getElementById("webforms").style.display=="block") 
                {

                    var weblo = document.getElementById("Weblocattype");
                    var index=weblo.selectedIndex;
                    var valu= weblo[index].text;
                        if (valu=="请选择") 
                        {
                                if (icon=="true")
                              {
                                commit="false";
                                alert("Web定位方式没有选择");
                                icon="false"
                              }
                            
                        }
                        else
                        {
                            weblocatype=valu;
                        }
                    weblocatstring=document.getElementById("locatstring1").value;
                    if (weblocatstring=="") 
                    {
                              if (icon=="true")
                              {
                                commit="false";
                                alert("Web定位器不能为空");
                                icon="false"
                              }
                       
                    }
                     
                }
                  if (document.getElementById("androidforms").style.display=="block") 
                {

                    var weblo = document.getElementById("Androidlocattype");
                    var index=weblo.selectedIndex;
                    var valu= weblo[index].text;
                        if (valu=="请选择") 
                        {
                                if (icon=="true")
                              {
                                commit="false";
                                alert("Android定位方式没有选择");
                                icon="false"
                              }
                            
                        }
                        else
                        {
                            weblocatype=valu;
                        }
                    androidlottring=document.getElementById("locatstring2").value;
                    if (androidlottring=="") 
                    {
                              if (icon=="true")
                              {
                                commit="false";
                                alert("Android定位器不能为空");
                                icon="false"
                              }
                       
                    }
                     
                }


                 if (document.getElementById("iosforms").style.display=="block") 
                {

                    var weblo = document.getElementById("Ioslocattype");
                    var index=weblo.selectedIndex;
                    var valu= weblo[index].text;
                        if (valu=="请选择") 
                        {
                                if (icon=="true")
                              {
                                commit="false";
                                alert("ios定位方式没有选择");
                                icon="false"
                              }
                            
                        }
                        else
                        {
                            weblocatype=valu;
                        }
                    ioslocatstring=document.getElementById("locatstring3").value;
                    if (ioslocatstring=="") 
                    {
                              if (icon=="true")
                              {
                                commit="false";
                                alert("ios定位器不能为空");
                                icon="false"
                              }
                       
                    }
                     
                }
                    // 操作
                    var obj = document.getElementById("action");
                    var index=obj.selectedIndex;
                    var valu2= obj[index].text;
                   if (valu2=="请选择")
                    {
                        if (icon=="true")
                              {commit="false";
                                alert("操作没有选择");
                                icon="false"
                              }
                    }
                    else
                    {
                        action=valu2;
                    }

                    // 参数列表

                    pars1=document.getElementById("canshutt").value;
                    // alert(pars1);

                    // 判断方式
                      obj = document.getElementById("asser");
                     index=obj.selectedIndex;
                     valu2= obj[index].text;
                   if (valu2=="请选择")
                    {
                        if (icon=="true")
                              {commit="false";
                                alert("判断方式没有选择");
                                icon="false"
                              }
                    }
                    else
                    {
                        asser=valu2;
                        // alert(asser);
                    }

                         // 预期结果

                    expet=document.getElementById("expet").value;
                    if (expet=="")
                    {
                        if (icon=="true")
                              {commit="false";
                                alert("请输入预期结果");
                                icon="false"
                              }
                    }
                    // alert(expet);
           
        }         

            
              if (commit=="true")
               {
                // 如果达到允许条件
                    if (commit=="true") 
                    {
                        // 如果点击了元素

                        if (document.getElementById("fieldAdd2").style.display=="block")
                         {
                            // alert("提交元素")
                            // alert(pars1);
                            //  alert(pars2);
                            subelemnte(casename,elementstep,"0",elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,pars1,expet,action);
                            

                         }
                            // 如果点击了功能
                        if (document.getElementById("fieldAdd").style.display=="block")
                         {
                            // alert("提交功能")
                            // alert(elementtype);
                            subelemntes(casename,elementsstep,"1",action2,pars2);
                        }
                    }
                  <!-- 提交后隐藏按钮-->
                document.getElementById("fieldAdd2").style.display="none";
                document.getElementById("fieldAdd").style.display="none";
                document.getElementById("butten").style.display="none";
               }
        
 }

// 提交表单
var subelemnte=function (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,pars1,expet,action) 

{  
    /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/AddTestCase",
            //提交的数据
             data:{"casename":casename,"step":step,"elementtype":elementtype,"elementname":elementname,"weblocatype":weblocatype,"weblocatstring":weblocatstring,"ioslocatype":ioslocatype,"ioslocatstring":ioslocatstring,"androidlocatype":androidlocatype,"androidlocatstring":androidlocatstring,"pars1":pars1,"expet":expet,"action":action},
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
                var res=XMLHttpRequest.responseText;
                // alert(res);
                alert("元素："+res);
                // if (url =="用例添加成功") 
                // {
                 

                // }
                // else
                // {
                //     alert(res);
                // }
           
    
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}


// / 提交表单
var subelemntes=function (casename,step,elementtype,action2,pars2) 

{  
    /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/AddTestCase",
            //提交的数据
            data:{"casename":casename,"step":step,"elementtype":elementtype,"action2":action2,"pars2":pars2},
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

                var res=XMLHttpRequest.responseText;
                // alert(res);
                 alert("功能："+res);
                // if (url =="用例添加成功") 
                // {
                 

                // }
                // else
                // {
                //     alert(res);
                // }
           
    
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}

var  checkeTestCase=function ()
{
// 不对用例合法校验 此方法弃用
    var mytestCase=document.getElementById("testCase").value;
    /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/getTestCaseAllow",
            //提交的数据
            data:{"casename":mytestCase},
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
                // // 获取返回数据
                //      alert(XMLHttpRequest.responseText);
                if (XMLHttpRequest.responseText == "用例可用")
                 {
                
                 }
             else
                 {
                     alert(XMLHttpRequest.responseText);
                   
                 }
                
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
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
                // 获取返回数据
                     // alert(XMLHttpRequest.responseText);
                if (XMLHttpRequest.responseText == "编号可用")
                 {
                
                 }
             else
                 {
                     alert(XMLHttpRequest.responseText);
                   
                 }
                
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
}

 
var  getCaseList=function ()
{

  gendata(); 

 
}
  // 封装到数组
     var casename = new Array(); 
     var step = new Array(); 
     var lastruntime=new Array(); 
     var resault=new Array(); 
     var addman = new Array(); 
     var adddate = new Array(); 
     var updateman = new Array(); 
     var updatedate = new Array(); 
 // 获取服务端列表数据
var  post=function()
{

 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestCaseList",
            //提交的数据
            data:{"page":0},
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
             // 获取返回参数
       data= XMLHttpRequest.responseText;
 
      // 遍历数组赋值
        var a=0;
      $.each(JSON.parse(data), function(i, obj)
      { 
        casename[a]=obj.casename;
        step[a]=obj.step;
        lastruntime[a]=obj.lastruntime;
        resault[a]=obj.resault;
        addman[a]=obj.addman;
        adddate[a]=obj.adddate;
        updateman[a]=obj.updateman;
        updatedate[a]=obj.updatedate;
       a++;
          }); 
               // alert(casename[0]);
            
            // alert("1");
             getCaseList(); 

            
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
  

}
// 是否生成数据了
var gen='1';
// 页面生成表格
var  gendata=function()
{
// 如果为1就是初始化的时候，改值为2
if (gen=='1')
 {
  // alert(gen);
  gen='2';
 }
 // 如果不是 （手动查询），杀出原有表单，重新生成
 else
 {
  // alert("bef");
  // jqury 删除id对象
$('#table1').remove();
refesh();
  // alert("af");
 }

// refesh();
  // alert(2);
    // 生成一个表格，没有边框 border=0
     var table=$("<table border=\"0\"  id=\"table1\" class=\"table\"  font size=\"200\"  >");
     table.appendTo($("#createtable"));
     // 设置列头
    var tr=$("<thead> <tr > <th width=\"50\" height=\"30\" >序号</th> <th width=\"100\" height=\"30\" >用例名称</th><th width=\"50\" height=\"30\" >操作步数</th> <th width=\"50\" height=\"30\" >上次执行时间</th> <th width=\"10\" height=\"30\" >结果</th> <th width=\"20\" height=\"30\" >创建人</th> <th width=\"50\" height=\"30\" >创建日期</th> <th width=\"50\" height=\"30\">更新人</th><th width=\"50\" height=\"30\" >更新日期</th><th width=\"100\" height=\"30\" style=\"width: 26px;\">操&nbsp &nbsp作</th></thead></tr> ");
      tr.appendTo(table);

      // 获取列值
      var  a=parseInt("0");
     for(var i=0;i<casename.length;i++)
     {
      a++;
      // alert(a);
        var tr=$("<tr ></tr>");
        tr.appendTo(table);
       
           var td=$("<td id=id"+a+">"+(i+1)+ "</td>");
           td.appendTo(tr);
           td=$("<td  onclick=\"linkStep();toStepPage();\" id=casename"+a+"  value=\"ok\" align=\"center\">"+casename[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=step"+a+"  align=\"center\">"+step[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=lastruntime"+a+">"+lastruntime[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=resault"+a+">"+resault[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=addman"+a+">"+addman[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=adddate"+a+">"+adddate[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=updateman"+a+">"+updateman[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=updatedate"+a+">"+updatedate[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=set"+a+"><a href=\"../runTest/runtest.html\" target=\"_blank\" ><i onclick=\"getMacBytnId();\"  id=mac"+a+"  class=\"icon-play\"></i></a>&nbsp<a href=\"user.html\" target=\"_blank\"><i class=\"icon-pencil\"></i></a>&nbsp<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i  onclick=\"getDelBtnId();\"  id=rem"+a+" class=\"icon-trash\"></i></a></td>");           td.appendTo(tr);         
     }
     // trend.appendTo(table);
        var white = "white";

   document.getElementById("createtable").style.color = white;
     $("#createtable").append("</table>");
        var white = "black";

   document.getElementById("createtable").style.color = white;
     // alert("ddd");

}


// 封装到数组
     var idByStep=new Array(); 
     var casenameByStep = new Array(); 
     var stepByStep  = new Array(); 
     var elementtypeByStep =new Array(); 
     var elementnameByStep =new Array(); 
     var weblocatypeByStep = new Array(); 
     var weblocatstringByStep  = new Array(); 
     var ioslocatypeByStep  = new Array(); 
     var ioslocatstringByStep  = new Array(); 
     var androidlocatypeByStep  = new Array(); 
     var androidlocatstringByStep  = new Array(); 
     var pars1ByStep  = new Array(); 
     var expetByStep  = new Array(); 
     var actionByStep  = new Array(); 
     var pars2ByStep  = new Array(); 
     var action2ByStep  = new Array(); 
 // 获取服务端列表数据
 // 获取服务端列表数据
var  post2=function(del)
{
  var  name=getCookie("testCaseName");
  // alert("getCookie"+name);
   
 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestCaseListByStep",
            //提交的数据
            data:{"casename":name},
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
             // 获取返回参数
       data= XMLHttpRequest.responseText;
 // alert(data);
      // 遍历数组赋值
        var a=0;
      $.each(JSON.parse(data), function(i, obj)
      { 
        idByStep[a]=obj.id;
        // alert(idByStep[a]);
        casenameByStep[a]=obj.casename;
        stepByStep[a]=obj.step;
        elementtypeByStep[a]=obj.elementtype;
        elementnameByStep[a]=obj.elementname;
        weblocatypeByStep[a]=obj.weblocatype;
        weblocatstringByStep[a]=obj.weblocatstring;
        ioslocatypeByStep[a]=obj.ioslocatype;
        ioslocatstringByStep[a]=obj.ioslocatstring;
        androidlocatypeByStep[a]=obj.androidlocatype;
        androidlocatstringByStep[a]=obj.androidlocatstring;
        pars1ByStep[a]=obj.pars1;
        expetByStep[a]=obj.expet;
        actionByStep[a]=obj.action;
        pars2ByStep[a]=obj.pars2;
        action2ByStep[a]=obj.action2;
       a++;

          }); 
           // alert("执行完毕");
              gendata2(del);

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
  

}
// 是否生成数据了
var genis='1';
// 页面生成表格
var  gendata2=function(del)
{
// 为 false 删除表格
if (del=="false")
 {
 // alert("用例步骤表不删除");
 }
 // 如果不是 （手动查询），杀出原有表单，重新生成
 else
 {
   // alert("用例步骤表删除");
  // alert("bef");
  // jqury 删除id对象
$('#table2').remove();
refesh();
  // alert("af");
 }
  // alert("dnnj");
   var table=$("<table id =\"table2\"  border=\"0\"  class=\"table\"  font size=\"200\"  >");
     table.appendTo($("#createtable2"));
     // 设置列头
      var tr=$("<thead> <tr > <th width=\"20\" height=\"30\" >步骤</th> <th width=\"10\" height=\"30\" >类型</th><th width=\"50\" height=\"30\" >元素名称</th> <th width=\"40\" height=\"30\" >web类型</th> <th width=\"50\" height=\"30\" >web定位</th> <th width=\"20\" height=\"30\" >android类型</th> <th width=\"50\" height=\"30\" >android定位</th> <th width=\"30\" height=\"30\">ios类型</th><th width=\"50\" height=\"30\" >ios定位</th><th width=\"50\" height=\"30\" >元素参数</th><th width=\"10\" height=\"30\" >操作</th><th width=\"50\" height=\"30\" >预期结果</th>  <th width=\"30\" height=\"30\" >功能类型</th>  <th width=\"50\" height=\"30\" >功能参数</th> </thead></tr> ");
      tr.appendTo(table);
 // 获取列值
      var  a=parseInt("0");
for(var i=0;i<casenameByStep.length;i++)
     {
        a++;
      // alert(a);
     var tr=$("<tr bgcolor=\"white\" id=tr"+a+"  onclick=\"getclickTd();\"></tr>");
    tr.appendTo(table); 
    // 注释内容为 td 中添加 文本框方法
        // 在表格插入文本框，并生成td后改值
    // td=$("<td ><input id=\"f1\" style=\"border:0px;width:15px; height:15px;background-color:white \"  value=\"\"  readOnly=\"true\" value=stepByStep[i] type=\"text\" a /> </td>");
    // // 必须插入tr 后面才能找到
    // td.appendTo(tr);
    // // 修改值
    // document.getElementById("f1").setAttribute("value",stepByStep[i]);
    // // 修改id
    // document.getElementById("f1").setAttribute("id","stepByStep"+a);
   
    td=$("<td id=stepByStep"+a+"  align=\"center\">"+stepByStep[i]+ "</td>");
    td.appendTo(tr);
    td=$("<td id=elementtypeByStep"+a+"  align=\"center\">"+elementtypeByStep[i]+ "</td>");
    td.appendTo(tr);
     td=$("<td id=elementnameByStep"+a+"  align=\"center\">"+elementnameByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=weblocatypeByStep"+a+"  align=\"center\">"+weblocatypeByStep[i]+ "</td>");   
    td.appendTo(tr);
    td=$("<td id=weblocatstringByStep"+a+"  align=\"center\">"+weblocatstringByStep[i]+ "</td>");
    td.appendTo(tr);
     td=$("<td id=ioslocatypeByStep"+a+"  align=\"center\">"+ioslocatypeByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=ioslocatstringByStep"+a+"  align=\"center\">"+ioslocatstringByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=androidlocatypeByStep"+a+"  align=\"center\">"+androidlocatypeByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=androidlocatstringByStep"+a+"  align=\"center\">"+androidlocatstringByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=pars1ByStep"+a+"  align=\"center\">"+pars1ByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=expetByStep"+a+"  align=\"center\">"+expetByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=actionByStep"+a+"  align=\"center\">"+actionByStep[i]+ "</td>");
    td.appendTo(tr);
        td=$("<td id=pars2ByStep"+a+"  align=\"center\">"+pars2ByStep[i]+ "</td>");
    td.appendTo(tr);
        td=$("<td id=action2ByStep"+a+"  align=\"center\">"+action2ByStep[i]+ "</td>");
    td.appendTo(tr);

    // td=$("<td id=set"+a+"><a><i class=\"icon-pencil\" id=edi"+a+"  onclick=\" getEditId();showText();\"></i></a><a href=\"#\" target=\"_blank\"><i class=\"icon-ok\"></i></a>      <a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i onclick=\"getDelBtnId();\" id=rem"+a+" class=\"icon-remove\"></i></a></td>");           td.appendTo(tr);
    //   td.appendTo(tr);    
     
     }
    var white = "white";

   document.getElementById("createtable2").style.color = white;
     $("#createtable2").append("</table>");
        var white = "black";

   document.getElementById("createtable2").style.color = white;
     // alert("ddd");
  
}
// 从用例页跳转到步骤页面
var linkStep =function(v)
{
  
   var key="testCaseName";
   var valu = "";
   valu=window.event.srcElement.innerText;
  
  setCookie(key,valu);
  // alert(getCookie(key)); testCaseName
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
// 刷新页面
var refesh=function()
{
  location.reload(true);
}

var toStepPage =function()
{
  window.open("testcasebystep.html");
}
// 跳转到新增用例页面
var newTestCase =function()
{
  window.open("newTestCase.html");
}
// 存储id
var  dataID;

// 根据点击对象，获取当前tr的id值
var  getDelBtnId =function(e)
{
    if(!e){ 
          var e = window.event; 
        } 
        //获取事件点击元素 
        var targ = e.srcElement; 
        //获取元素名称 
        var tname = targ.id; 
        var l =tname.length;
        var data=tname.substring(l-2, l);
        if (l==4)
         {
           dataID=tname.substring(3, 4);
         }
       else{
         dataID=tname.substring(3, 5);
       }
        
        // alert(dataID);   

}

// 获取当前点击的td的id，并修改背景颜色
  var clickTd="";
var  getclickTd =function(e)
{
    if(!e){ 
          var e = window.event; 
         } 
        //获取事件点击元素 
        var targ = e.srcElement; 
        //获取元素名称 
        var tname = targ.id; 
        // var l =tname.length;
         
        // var data=tname.substring(l-2, l);
        // 用正则找到数字 获取id
       clickTd= tname.replace(/[^0-9]/ig,"");
       var trid="tr"+clickTd;
      // alert(clickTd);   
      // alert(trid);
      var now=document.getElementById(trid).getAttribute("bgcolor");
      if (now=="#D2E9FF")
       {
          document.getElementById(trid).setAttribute("bgcolor","white");
          // 置空选中对象td
          clickTd="";
       }
      else
      {
         document.getElementById(trid).setAttribute("bgcolor","#D2E9FF");
      }

}
  // var =stepEdit;
  // var =stepEdit;
  // var =stepEdit;
  // var =stepEdit;
  // var =stepEdit;
  // var =stepEdit;
// 跳转到用例编辑页面
var  getEditData =function(e)
{
    if (clickTd=="")
     {
      alert("请选择要编辑的用例步骤");
    }
    else
    {

      window.open("testcaseedit.html","编辑测试步骤","top=100,left=100,width=1000,height=800");
    }
}
var getMacBytnId=function(e)
{
  var casename="";
if(!e){ 
          var e = window.event; 
        } 
        //获取事件点击元素 
        var targ = e.srcElement; 
        //获取元素名称 
        var tname = targ.id; 
        var l =tname.length;
        if (l==4)
         {
           casename=tname.substring(3, 4);
         }
       else{
         casename=tname.substring(3, 5);
       }
       casename="casename"+casename;
       casename=document.getElementById(casename).innerHTML;
       // alert(casename);
       setCookie("runCase",casename);
       // alert(getCookie("runCase"));
}



// 关闭当前页面
var  closeTestStep=function()
{
window.close();
}


// 删除测试用例
var deleCase=function()
{
   // alert(dataID);
  // 获得测试用例名称
  var text="casename"+dataID;
  // alert(text);
    // 获得文本对象
 var name=document.getElementById(text).innerHTML;

 // alert(name);


 // /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/DeleTestCase",
            //提交的数据
            data:{"casename":name},
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
             // 获取返回参数
            var  res= XMLHttpRequest.responseText;
            alert(res);
            // 获取最新数据
            refesh();
         // 获取最新数据
            post();
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

}
// / 删除测试用例 步骤
var deleCaseStep=function()
{
  if (clickTd=="")
   {
    alert("请选择要删除的用例步骤");
   }
  // 获得步骤id
  var text="stepByStep"+clickTd;
  // alert(text);
    // 获得步骤文本对象
 var step=document.getElementById(text).innerHTML;
 // alert(step);
 var casename=getCookie("testCaseName");
 // alert(text);


 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/DeleTestCaseStep",
            //提交的数据
            data:{"step":step,"casename":casename},
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
             // 获取返回参数
            var  res= XMLHttpRequest.responseText;
            alert(res);
            // 获取最新数据
            refesh();
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
        
}
