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
// 当用户点击页链接的时候，body onload 不加载getTestCaseNo，只有在第一次进入的时候加载
var  isLoad =function()
{
   setCookie("isLoad","true");
   var a=getCookie("isLoad");
   if (a=="true") 
   {
    getTestCaseNo();

   }
}
// 获取有多少条数据
var getTestCaseNo =function()
{

    // * body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/getTestCaseNo",
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
     var casename = new Array(); 
     var step = new Array(); 
     var lastruntime=new Array(); 
     var resault=new Array(); 
     var addman = new Array(); 
     var adddate = new Array(); 
     var updateman = new Array(); 
     var updatedate = new Array(); 
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
            url:"/autotestcloud/GetTestCaseList",
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
              casename=null;
              casename= new Array(); 
              // 获取返回参数
                          
                     data= XMLHttpRequest.responseText;
                // alert(data)
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
                           genTestCaseToTable(); 

                          }
             
            
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
  

}

var CopyTestCase=function()
{
  var newCasename=document.getElementById("newCase").value;
  if (newCasename=="")
   {
    alert("请输入新用例名称");
   }
   else
   {
    // alert(newCasename);
    // 读取缓存中的原用例名称
    var oldcaseName=getCookie("oldCasename");
    // alert(oldcaseName);
     $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/CopyTestCase",
            //提交的数据
            data:{"newCasename":newCasename,"oldCasename":oldcaseName},
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
                               // 获取返回参数
            var  res= XMLHttpRequest.responseText;
            alert(res);
              window.close();
            // 获取最新数据
            // refesh();
         // // 获取最新数据
         //    genTestCaseToTable();
                }

            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

   }
}

// 用例复制步骤1，获取原用例名称
var oldcaseName="";
var getOldCaseName =function()
{
  var policId =event.target.id; 

  // 获取父节点的父节点的id（所在trid）
  deviceId=document.getElementById(policId).parentNode.parentNode.id;
  // 获取行号
  deviceId=deviceId.substring(2,deviceId.length);
  // 拼接设备id
  deviceId="casename"+deviceId;
  // 获取文本
  oldcaseName=document.getElementById(deviceId).innerHTML;
  setCookie("oldCasename",oldcaseName);

  // alert(getCookie("oldCasename"));
  var url="copyTestcase.html";
 window.open (url,'newwindow','height=200,width=300,top=200,left=450,toolbar=no,menubar=no,scrollbars=no,resizable=no, location=no,status=no') ;

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
    var tr=$("<thead > <tr > <th width=\"30\" height=\"30\" >序号</th> <th width=\"100\" height=\"30\" >用例名称</th><th width=\"20\" height=\"30\" >步数</th> <th width=\"50\" height=\"30\" >上次执行时间</th> <th width=\"20\" height=\"30\" >结果</th> <th width=\"30\" height=\"30\" >创建人</th> <th width=\"50\" height=\"30\" >创建日期</th> <th width=\"50\" height=\"30\">更新人</th><th width=\"50\" height=\"30\" >更新日期</th><th width=\"50\" height=\"30\" style=\"width: 26px;\">操作</th></thead></tr> ");
      tr.appendTo(table);

      // 遍历 tr  td
      var  a=parseInt("0");
     for(var i=0;i<casename.length;i++)
     {
      a++;
      // alert(a);
        var tr=$("<tr  id=tr"+a+"  ></tr>");
        tr.appendTo(table);
       
           var td=$("<td id=id"+a+">"+(i+1)+ "</td>");
           td.appendTo(tr );
           td=$("<td    id=casename"+a+"  value=\"ok\" align=\"center\">"+casename[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=step"+a+"  align=\"center\">"+step[i]+ "</td>");
           td.appendTo(tr);
           var dat=lastruntime[i];
           if (dat!="")
            {
              dat=dat.substring(0,(dat.length-2));  
            }
          
           td=$("<td id=lastruntime"+a+">"+dat+ "</td>");
           td.appendTo(tr);
           td=$("<td id=resault"+a+">"+resault[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=addman"+a+">"+addman[i]+ "</td>");
           td.appendTo(tr);
           var date=adddate[i];
           date=date.substring(0,(date.length-2)); 
              // alert(date);
           td=$("<td id=adddate"+a+">"+date+ "</td>");
           td.appendTo(tr);
           td=$("<td id=updateman"+a+">"+updateman[i]+ "</td>");
           td.appendTo(tr);
           var data2=updatedate[i];
           data2=data2.substring(0,(data2.length-2)); 
           // alert(data2);
           td=$("<td id=updatedate"+a+">"+data2+ "</td>");
           td.appendTo(tr);
           // <a href=\"#\"<i onclick=\"SetRunTestCaseName();\"  id=mac"+a+"  class=\"icon-play\"></i></a>&nbsp 
           td=$("<td id=set"+a+">   <a href=\"#\"<i onclick=\"getOldCaseName();\"  id=mac"+a+"  class=\"icon-copy\"></i></a>&nbsp   <a href=\"#\" <i id=lok"+a+" class=\"icon-pencil\" onclick=\"toTestCaseStepPage();\" ></i></a>&nbsp     <a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i  onclick=\"  getClickButtenIdNo();\"  id=rem"+a+" class=\"icon-trash\"></i></a></td>");   
           td.appendTo(tr);         


     }
      // alert(casename.length);
     // // 根据数据行数显示数据
     if (casename.length<10)
      {

        var b=10-casename.length;
        for (var i = b; i <10; i++)
         {
               document.getElementById("tr"+i).style.display="none";
             // alert("aa");
          }
      
      // 
      }
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
           casename=tname.substring(3, 4);
         }
       else{
         casename=tname.substring(3, 5);
          }
       casename="casename"+casename;
       // alert(casename);
       casename=document.getElementById(casename).innerHTML;
       // alert(casename);
       // alert(casename);
      
       // alert(n);
       setCookie("runCase",casename);
       // alert(getCookie("runCase"));
      window.open("runTest.html");
}

// 跳转到步骤页面
var toTestCaseStepPage =function(e)
{
  
   var testCaseName="";
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
           testCaseName=tname.substring(3, 4);
         }
       else{
            testCaseName=tname.substring(3, 5);
          }
       testCaseName="casename"+testCaseName;
       // alert(casename);
       casename=document.getElementById(testCaseName).innerHTML;
       // alert(casename);
       // alert(casename);
      
       // alert(n);
       setCookie("testCaseName",casename);
       setCookie("newCase",casename);
       // alert(getCookie("testCaseName"));
       window.open("testcasebystep.html");
    
}
// 删除测试用例
var deleTestCase=function()
{
  // 获取数据id

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
            alert(res);
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
var  casename=document.getElementById("casenames").value;
var  addman=document.getElementById("addmans").value;
var  resault=document.getElementById("resaults").value;
if (resault=="请选择")
 {
  resault="";
 }
var  lastruntime=document.getElementById("lastruntimes").value;
var  to=document.getElementById("tos").value;
if (to=="请选择")
 {
  resault="";
 }
// alert(casename+addman+resault+lastruntime+to);
   $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetTestCaseListByString",
            //提交的数据
            data:{"casename":casename,"addman":addman,"resault":resault,"lastruntime":lastruntime,"to":to},
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


               casename=null;
               casename= new Array(); 
               step=null;
               step= new Array(); 
               lastruntime=null;
               lastruntime= new Array(); 
               resault=null;
               resault= new Array(); 
               addman=null;
               addman= new Array(); 
               adddate=null;
               adddate=new Array(); 
         
               updateman=null;
               updateman= new Array(); 
      
               updatedate=null;
               updatedate= new Array(); 
              // 获取返回参数     
                     data= XMLHttpRequest.responseText;
                // alert(data)
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
                                                 
                         
                          $('#table1').remove();

    // 生成一个表格，没有边框 border=0
     var table=$("<table border=\"0\"  id=\"table1\" class=\"table\"  font size=\"200\"  >");
     table.appendTo($("#createtable"));
     // 设置列头
    var tr=$("<thead > <tr > <th width=\"30\" height=\"30\" >序号</th> <th width=\"100\" height=\"30\" >用例名称</th><th width=\"20\" height=\"30\" >步数</th> <th width=\"50\" height=\"30\" >上次执行时间</th> <th width=\"20\" height=\"30\" >结果</th> <th width=\"30\" height=\"30\" >创建人</th> <th width=\"50\" height=\"30\" >创建日期</th> <th width=\"50\" height=\"30\">更新人</th><th width=\"50\" height=\"30\" >更新日期</th><th width=\"50\" height=\"30\" style=\"width: 26px;\">操&nbsp &nbsp作</th></thead></tr> ");
      tr.appendTo(table);

      // 遍历 tr  td
      var  a=parseInt("0");
     for(var i=0;i<casename.length;i++)
     {
      a++;
      // alert(a);
        var tr=$("<tr  id=tr"+a+"  ></tr>");
        tr.appendTo(table);
       
           var td=$("<td id=id"+a+">"+(i+1)+ "</td>");
           td.appendTo(tr );
           td=$("<td    id=casename"+a+"  value=\"ok\" align=\"center\">"+casename[i]+ "</td>");
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
           td=$("<td id=set"+a+"> <a href=\"#\"<i onclick=\"SetRunTestCaseName();\"  id=mac"+a+"  class=\"icon-play\"></i></a>&nbsp    <a href=\"#\" <i id=lok"+a+" class=\"icon-pencil\" onclick=\"toTestCaseStepPage();\" ></i></a>&nbsp     <a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i  onclick=\"  getClickButtenIdNo();\"  id=rem"+a+" class=\"icon-trash\"></i></a></td>");   
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