 var checkFile=function()
 {
 var filename= document.getElementById("file").value;
 var name=filename.substring((filename.length-5),filename.length); 
 // alert(name);
 if (name!=".xlsx" )
  {
    alert("请上传excle文件！");
    document.getElementById("file").value="";
  }

 }
var newTestPolicy =function()
{
  window.open("testpolicyadd.html");
}
var FileDownLoad=function()
{

window.open("/autotestcloud/FileDownLoad");
  
}

// 记录上一次用户请求页面
var nowpage=0;
// 记录数据库有多少数据/10
var  DataNo=0;
// 请求接口时调用（请求那页数据）
var pageNo=0;
// 根据数据数量显示链接按钮


var showPageLink =function()
{
  // 设置是否显示
  // 一条都没有
  if (DataNo==0)
   {

   }
  // 根据数据量处理显示问题
  // 不足十条

 else if (0<DataNo  &&  DataNo<=1)
   {
   document.getElementById("li1").style.background="yellow";   
   document.getElementById("li1").style.display="block";
   }
    // 十条以上 或者20条
   else if (1<DataNo && DataNo<=2)
  {
    document.getElementById("li1").style.background="yellow";   
      document.getElementById("liTop").style.display="block";   
     document.getElementById("li1").style.display="block";
    document.getElementById("li2").style.display="block";
    document.getElementById("liFinal").style.display="block";
    
  }
   // 二十条以上 或者30条
   else if (2<DataNo && DataNo<=3)
  {

document.getElementById("li1").style.background="yellow";   
      document.getElementById("liTop").style.display="block";   
     document.getElementById("li1").style.display="block";
    document.getElementById("li2").style.display="block";
     document.getElementById("li3").style.display="block";
    document.getElementById("liFinal").style.display="block";

    
   
  }
    // 三十条以上 或者40条
   else if (3<DataNo && DataNo<=4)
  {document.getElementById("li1").style.background="yellow";   
      document.getElementById("liTop").style.display="block";   
     document.getElementById("li1").style.display="block";
    document.getElementById("li2").style.display="block";
     document.getElementById("li3").style.display="block";
      document.getElementById("li4").style.display="block";
    document.getElementById("liFinal").style.display="block";

   
   
  }
    // 四十条以上 或者50条
   else if (4<DataNo && DataNo<=5)
  {
document.getElementById("li1").style.background="yellow";   
    document.getElementById("liTop").style.display="block";   
     document.getElementById("li1").style.display="block";
    document.getElementById("li2").style.display="block";
     document.getElementById("li3").style.display="block";
     document.getElementById("li4").style.display="block";
      document.getElementById("li5").style.display="block";
    document.getElementById("liFinal").style.display="block";


  }
    // 50以上
   else if (DataNo>5)
  {
    document.getElementById("li1").style.background="yellow";   
    document.getElementById("liTop").style.display="block";   
     document.getElementById("li1").style.display="block";
    document.getElementById("li2").style.display="block";
     document.getElementById("li3").style.display="block";
      document.getElementById("li4").style.display="block";
  
  document.getElementById("li5").style.display="block";
  document.getElementById("li1Next").style.display="block";
    document.getElementById("liFinal").style.display="block";
  }
  else
  {
  alert("数据异常");
  }

}


// 点击页 弹出当页数据
var getPage =function(par)
{
   // alert("dd1");

          //  如果请求的是首页，当前页为0，请求接口
            if (par=="liTop") 
             {
             var da=parseInt(DataNo);
              if (da>5 || da==5)
               {
                document.getElementById("li5").innerHTML="5"; 
                 
                 document.getElementById("li4").innerHTML=4; 
               
                 document.getElementById("li3").innerHTML=3; 
                 
                 document.getElementById("li2").innerHTML=2;
                
                 document.getElementById("li1").innerHTML=1;


                    document.getElementById("li1").style.background="yellow";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
               }
                

              // alert("dd");
              nowpage=0;
              pageNo=0;
              getTestCaseList();
             }
             //  如果请求的是尾页，当前页为DataNo，如果DataNo为整数显示当页 否则显示下一页
               else if(par=="liFinal")
           {
                var da=parseInt(DataNo);
                if (da>5) 
                {
                  document.getElementById("li5").innerHTML=da; 
                 
                 document.getElementById("li4").innerHTML=da-1; 
               
                 document.getElementById("li3").innerHTML=da-2; 
                 
                 document.getElementById("li2").innerHTML=da-3;
                
                 document.getElementById("li1").innerHTML=da-4;

                 document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="yellow";   

                 nowpage=parseInt(DataNo);
                 // alert(nowpage);
                }
                

            // var a=isInteger(DataNo);
             // alert(parseInt(DataNo));
             //    alert(DataNo);
             var intdata=parseInt(DataNo);
             // 整数
                if (intdata==DataNo)
                 {
                  // gen="1";
                   // alert("dd2");
                   nowpage=DataNo;
                   pageNo=intdata-1;
                   // alert(pageNo);
                   getTestCaseList();
                 }
                 // 小数
                 else
                 {
                   // alert("dd1");
                   nowpage=DataNo;
                   pageNo=intdata;
                    // alert(pageNo);
                   getTestCaseList();
                 }
           }

            // 参数1
          else if(par=="li1")
         {
              document.getElementById("li1").style.background="yellow";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
            // 获取页数文本
            var v= document.getElementById(par).innerHTML;           
            var va=parseInt(v); 
                  
                   nowpage=va-1
                   pageNo=va-1;

            getTestCaseList();
         }
            // 参数2
          else if(par=="li2")
         {
           document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="yellow";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
            // 获取页数文本
            var v= document.getElementById(par).innerHTML;           
            var va=parseInt(v); 
                   nowpage=va-1
                   pageNo=va-1;
            getTestCaseList();
         }
           // 参数3
          else if(par=="li3")
         {
                  document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="yellow";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";
            // 获取页数文本
            var v= document.getElementById(par).innerHTML;           
            var va=parseInt(v); 
                   nowpage=va-1
                   pageNo=va-1;
                
            getTestCaseList();
         }
          // 参数4
          else if(par=="li4")
         {
          document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="yellow";   
                 document.getElementById("li5").style.background="white";
            // 获取页数文本
            var v= document.getElementById(par).innerHTML;           
            var va=parseInt(v); 
                   nowpage=va-1
                   pageNo=va-1;
                  
            getTestCaseList();
         }
           // 参数5
          else if(par=="li5")
         {
                 document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="yellow";
            // 获取页数文本
            var v= document.getElementById(par).innerHTML;           
            var va=parseInt(v); 
                   nowpage=va-1
                   pageNo=va-1;
                   // alert(pageNo);
                   getTestCaseList();
            
         }
          // 如果请求的是上一页，
          else if(par=="li1Last")
         {

            
             var p1=nowpage;
      
             if (nowpage==0)
              {
                // alert("首页不执行");
              }
              // 依次减少
              else
              {
                p1=parseInt(nowpage);
                // alert(p1);
                if (nowpage>5)
                 {
                  p1--;
                 document.getElementById("li5").innerHTML=p1.toString(); 
                 p1--;
                 document.getElementById("li4").innerHTML=p1.toString(); 
                  p1--;
                 document.getElementById("li3").innerHTML=p1.toString(); 
                 p1--;
                 document.getElementById("li2").innerHTML=p1.toString(); 
                 p1--;
                 document.getElementById("li1").innerHTML=p1.toString();

                 document.getElementById("li5").style.background="yellow";   
                 }
              else if (nowpage==5)
               {
                 document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="yellow";   
               }
                 else if (nowpage==4)
               {   
        

                 document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="yellow";   
                 document.getElementById("li5").style.background="white";   
               }
                  else if (nowpage==3)
               {  
                  document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="yellow";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
               }
                  else if (nowpage==2)
               {  
                document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="yellow";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
               }
                 else if (nowpage==1)
               {  
                document.getElementById("li1").style.background="yellow";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
               }
                 pageNo--;
                 nowpage--;
                  getTestCaseList();
              }
          
           
          
         }
          // 如果请求的是下一页，请求接口
          else if(par=="li1Next")
         {
         var p1=parseInt(DataNo);
          // alert(p1);
             if (nowpage==p1 || nowpage>p1)
              {
                // alert("最后一页");
              }
              // 依次增加
              else
              {
                 pageNo++;
                 nowpage++;

                // alert(nowpage);
                if (nowpage>5 || nowpage==5)
                 {
                  
                 var d=parseInt(document.getElementById("li1").innerHTML);
                 d++;
                 document.getElementById("li1").innerHTML=d;
                    d++;
                 document.getElementById("li2").innerHTML=d;
                  d++;
                 document.getElementById("li3").innerHTML=d; 
                 d++;
                 document.getElementById("li4").innerHTML=d;  
                  d++;
                 document.getElementById("li5").innerHTML=d; 
                
                 document.getElementById("li5").style.background="yellow";   
                 }
           
                 else if (nowpage==4)
               {   
        

                 document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="yellow";   
               }
                  else if (nowpage==3)
               {  
                  document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="yellow";   
                 document.getElementById("li5").style.background="white";   
               }
                  else if (nowpage==2)
               {  
                document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="white";   
                 document.getElementById("li3").style.background="yellow";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
               }
                 else if (nowpage==1)
               {  
                document.getElementById("li1").style.background="white";   
                 document.getElementById("li2").style.background="yellow";   
                 document.getElementById("li3").style.background="white";   
                 document.getElementById("li4").style.background="white";   
                 document.getElementById("li5").style.background="white";   
               }
                

                  getTestCaseList();
              }
           
         }

         else
         {
          alert("参数错误！");
         }
}
// 获取上一组是否显示
var getLastNext =function()
{
  var a= document.getElementById("li1").innerHTML;
  a=parseInt(a);
  // alert(a);
  if (DataNo<a)
   {
    document.getElementById("li1Last").style.display="block";
   }
   else
   {
     document.getElementById("li1Last").style.display="none";
   }

  var b= document.getElementById("li1").innerHTML;
     b=parseInt(b);
  if (DataNo>b)
   {
    document.getElementById("li1Next").style.display="block";
   }
   else
   {
     document.getElementById("li1Next").style.display="none";
   }

}
// 当用户点击页链接的时候，body onload 不加载getTestPolicyNo，只有在第一次进入的时候加载
var  isLoad =function()
{
   setCookie("isLoad","true");
   var a=getCookie("isLoad");
   if (a=="true") 
   {
    getTestPolicyNo();

   }
}
// 获取有多少条数据
var getTestPolicyNo =function()
{
//   alert("dd");
// // var project =getCookie("project");
// setCookie("pi","sss");
// alert(getCookie("pi"));

    // // * body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/getTestPolicyNo",
            //提交的数据
            data:{"p":""},
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
              DataNo=parseInt(url);
              DataNo=DataNo/10;
                 // alert(DataNo);
              // 执行展示功能
              showPageLink();
           
             }
             
            
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
}
var num="";

 // 存储服务端用例列到数组
      var id = new Array(); 
     var platformName = new Array(); 
     var policyName = new Array(); 
     var deviceName=new Array(); 
     var res=new Array(); 
     var project = new Array(); 
    var caseName = new Array(); 
// 存储请求页
var pageNo=0;
// 获取测试用例列表
var  getTestCaseList=function()
{

 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestPolicyList",
            //提交的数据
            data:{"page":pageNo},
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
              // 如果当前页不足10个数据 重置
              platformName=null;
              platformName= new Array(); 
              // 获取返回参数
                          
                     data= XMLHttpRequest.responseText;
                // alert(data);
                    // 遍历数组赋值
                      var a=0;
                    $.each(JSON.parse(data), function(i, obj)
                    { 
                      id[a]=obj.id;
                      platformName[a]=obj.platformName;
                      policyName[a]=obj.policyName;
                      deviceName[a]=obj.deviceName;
                      res[a]=obj.res;
                      project[a]=obj.project;
                     caseName[a]=obj.caseName;
                     a++;
                        }); 
                             // alert(casename[0]);
                          
                          // alert("1");
                           genTestCaseToTable(); 

                          }
             
            
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
  

}


// 是否生成数据了
var gen="1";
// 页面生成表格
var  genTestCaseToTable=function()
{

$('#table1').remove();

    // 生成一个表格，没有边框 border=0
     var table=$("<table border=\"0\"  id=\"table1\" class=\"table\"  font size=\"200\"  >");
     table.appendTo($("#createtable"));
     // 设置列头
    var tr=$("<thead > <tr > <th width=\"30\" height=\"30\" >序号</th> <th width=\"30\" height=\"30\" >测试平台</th><th width=\"50\" height=\"30\" >策略名称</th> <th width=\"80\" height=\"30\" >设备名称</th> <th width=\"30\" height=\"30\" >测试结果</th> <th width=\"30\" height=\"30\" >测试系统</th><th width=\"100\" height=\"30\" >包含用例</th> <th width=\"30\" height=\"30\" style=\"width: 26px;\">操&nbsp &nbsp作</th></thead></tr> ");
      tr.appendTo(table);

      // 遍历 tr  td
      var  a=parseInt("0");
     for(var i=0;i<platformName.length;i++)
     {
      a++;
      // alert(a);
        var tr=$("<tr  id=tr"+a+" ></tr>");
        tr.appendTo(table);
       
           var td=$("<td id=id"+a+">"+(i+1)+ "</td>");
           td.appendTo(tr );
         
           td=$("<td    id=platformName"+a+"  value=\"ok\" align=\"center\">"+platformName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=policyName"+a+"  align=\"center\">"+policyName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=deviceName"+a+">"+deviceName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=res"+a+">"+res[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=project"+a+">"+project[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=caseName"+a+">"+caseName[i]+ "</td>");
           td.appendTo(tr);
           
           var aa=id[i];
           // alert(aa);
           td=$("<td id=set"+a+"> <a href=\"#\"<i  id="+aa+ " onclick=\"setPolicyId();GetMachineIsUse();\" class=\"icon-play\"></i></a>&nbsp    <a href=\"#\" <i  id="+aa+ " class=\"icon-pencil\" onclick=\"setPolicyId();toTestPolicyEdit();\" ></i></a>&nbsp     <a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i  onclick=\" setPolicyId();\"  id="+aa+ " class=\"icon-trash\"></i></a></td>");   
           td.appendTo(tr);         


     }
      // alert(casename.length);
     // // 根据数据行数显示数据
         // alert(platformName.length);
     // if (platformName.length<10 && pageNo!=0)
     //  {
  
     //    var b=10-platformName.length;
     //    for (var i = b; i <10; i++)
     //     {
     //           document.getElementById("tr"+i).style.display="none";
     //         // alert("aa");
     //      }
      
     //  // 
     //  }
     // trend.appendTo(table);
        var white = "white";

   document.getElementById("createtable").style.color = white;
     $("#createtable").append("</table>");
        var white = "black";

   document.getElementById("createtable").style.color = white;
     // alert("ddd");

}
// 获取当前点击的测试用例名称到cooike
 // 跳转到用例运行页面
var SetRunTestCaseName=function(e)
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
           platformName=tname.substring(3, 4);
         }
       else{
         platformName=tname.substring(3, 5);
          }
       platformName="casename"+casename;
       // alert(casename);
       platformName=document.getElementById(platformName).innerHTML;
       // alert(casename);
       // alert(casename);
      
       // alert(n);
       setCookie("runCase",casename);
       // alert(getCookie("runCase"));
      window.open("runTest.html");
}

// 跳转到步骤页面
var toTestPolicyEdit =function()
{
   setCookie("editTestPolicy","true");
    newTestPolicy();
}

// 运行的策略中的设备名
var deviceName1="";
var policId="";
var setPolicyId=function()

{
 policId =event.target.id; 
// alert(policId);
setCookie("policId",policId);
// 获取父节点的父节点的id（所在trid）
deviceId=document.getElementById(policId).parentNode.parentNode.id;
// 获取行号
deviceId=deviceId.substring(2,deviceId.length);
// 拼接设备id
deviceId="deviceName"+deviceId;
// 获取文本
deviceName1=document.getElementById(deviceId).innerHTML;
// alert(deviceName1);
// alert(policId);

// // deleTestCase(policId);
// alert(getCookie("policId"));
//    console.log(a);
}
// 判断运行设备是否在空闲中
var GetMachineIsUse=function()
{
  
 $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetMachineIsUse",
            //提交的数据
            data:{"machineName":deviceName1},
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
                
                if (url =="设备使用中") 
                {
                 alert("设备："+deviceName1+",正在运行中! 请更换测试设备或者稍后重试")

                }
                else
                {
                  runPolicy();
                }

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
}
var runPolicy=function()
{
    // alert("策略编号："+policId); 
    alert("测试运行中，请稍后...");
 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/RunTestPolicy",
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
                alert(url);
                if (url =="/autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
                else
                {
                  refesh();
               // 获取最新数据
                  genTestCaseToTable();
                }

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

}
// 删除测试用例
var deleTestCase=function()
{
  
// alert(policId);
 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/DeleteTestPolicy",
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

                               // 获取返回参数
            var  res= XMLHttpRequest.responseText;
            // alert(res);
            // 获取最新数据
            refesh();
         // 获取最新数据
            genTestCaseToTable();
                }

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

}

// 存储id
var  dataID="";

// 根据点击的删除 编辑
var  getClickButtenIdNo =function(e)
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

var Height=0;
var Width=0;

var getscree=function()
{
   var Width=document.body.clientWidth;
  // alert(Width);
   Width=parseInt(Width);
  // Width=Width*0.98;
  // 高度
   Height=document.body.clientHeight;
// alert(Height);
 Height=parseInt(Height);
 // Height=Height*0.9;

   document.getElementById("createtable").setAttribute("style","overflow-y:auto; overflow-x:auto; width:"+Width+"px; height:"+500+"px");
}

var checkGetTestCaseListByString=function()
{
    var  lastruntime=document.getElementById("lastruntimes").value;
    var  to=document.getElementById("tos").value;
     if (lastruntime !=""  && to=="请选择")
     {
     alert("您已经选择时间，请选择时间关系")
     }
   else if (lastruntime ==""  && to!="请选择")
     {
    alert("您已经选择时间关系，请选择指定时间")
     }
     else
     {
       // alert("ok")
      GetTestCaseListByString();
     }
}
var GetTestCaseListByString =function()
{
        // 宽度
 



  var  casename=document.getElementById("casenames").value;
  // var  addman=document.getElementById("addmans").value;
  var  resault=document.getElementById("resaults").value;
  if (resault=="请选择")
   {
    resault="";
   }
  var  lastruntime=document.getElementById("lastruntimes").value;
  var  to=document.getElementById("tos").value;
  // if (to=="请选择")
  //  {
  //   resault="";
  //  }
   // alert(resault);
   if (casename!="" || resault!="" || lastruntime!="" )
    {



       $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestPolicyListByString",
            //提交的数据
            data:{"casename":casename,"resault":resault,"lastruntime":lastruntime,"to":to},
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
                     id=null;
                     id = new Array(); 
                     platformName=null;
                     platformName = new Array(); 
                     policyName=null;
                     policyName = new Array(); 
                     deviceName=null;
                     deviceName = new Array(); 
                     res=null;
                     res = new Array(); 
                     project=null;
                     project = new Array(); 
                     caseName=null;
                     caseName = new Array();
                     data= XMLHttpRequest.responseText;

                      var a=0;
                    $.each(JSON.parse(data), function(i, obj)
                    { 
                      id[a]=obj.id;
                      platformName[a]=obj.platformName;
                      policyName[a]=obj.policyName;
                      deviceName[a]=obj.deviceName;
                      res[a]=obj.res;
                      // alert(res[a]);
                      project[a]=obj.project;
                     caseName[a]=obj.caseName;
                     a++;
                        }); 

                                                 
                         
                    $('#table1').remove();

    // 生成一个表格，没有边框 border=0
     var table=$("<table border=\"0\"  id=\"table1\" class=\"table\"  font size=\"200\"  >");
     table.appendTo($("#createtable"));
     // 设置列头
    var tr=$("<thead > <tr > <th width=\"30\" height=\"30\" >序号</th> <th width=\"30\" height=\"30\" >测试平台</th><th width=\"50\" height=\"30\" >策略名称</th> <th width=\"80\" height=\"30\" >设备名称</th> <th width=\"30\" height=\"30\" >测试结果</th> <th width=\"30\" height=\"30\" >测试系统</th><th width=\"100\" height=\"30\" >包含用例</th> <th width=\"30\" height=\"30\" style=\"width: 26px;\">操&nbsp &nbsp作</th></thead></tr> ");
      tr.appendTo(table);
      // alert(platformName.length);
      // 遍历 tr  td
      var  a=parseInt("0");
     for(var i=0;i<platformName.length;i++)
     {
      a++;
      // alert(a);
        var tr=$("<tr  id=tr"+a+" ></tr>");
        tr.appendTo(table);
       
           var td=$("<td id=id"+a+">"+(i+1)+ "</td>");
           td.appendTo(tr );
         
           td=$("<td    id=platformName"+a+"  value=\"ok\" align=\"center\">"+platformName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=policyName"+a+"  align=\"center\">"+policyName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=deviceName"+a+">"+deviceName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=res"+a+">"+res[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=project"+a+">"+project[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=caseName"+a+">"+caseName[i]+ "</td>");
           td.appendTo(tr);
           
           var aa=id[i];
           // alert(aa);
           td=$("<td id=set"+a+"> <a href=\"#\"<i  id="+aa+ " onclick=\"setPolicyId();GetMachineIsUse();\" class=\"icon-play\"></i></a>&nbsp    <a href=\"#\" <i  id="+aa+ " class=\"icon-pencil\" onclick=\"setPolicyId();toTestPolicyEdit();\" ></i></a>&nbsp     <a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i  onclick=\" setPolicyId();\"  id="+aa+ " class=\"icon-trash\"></i></a></td>");   
           td.appendTo(tr);         


     }
        var white = "white";

       document.getElementById("createtable").style.color = white;
     $("#createtable").append("</table>");
        var white = "black";

   document.getElementById("createtable").style.color = white;
                           
                  document.getElementById("li1").style.display="none";   
                  document.getElementById("li2").style.display="none";   
                  document.getElementById("li3").style.display="none";   
                  document.getElementById("li4").style.display="none";   
                  document.getElementById("li5").style.display="none";  
                  document.getElementById("liTop").style.display="none";   
                  document.getElementById("li1Last").style.display="none";  
                  document.getElementById("li1Next").style.display="none";   
      
                  document.getElementById("liFinal").style.display="none";  
                }

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
    }
// alert(casename+addman+resault+lastruntime+to);
  

}