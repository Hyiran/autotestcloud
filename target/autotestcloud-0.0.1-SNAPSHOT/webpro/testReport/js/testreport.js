// table 加滚动条
var Height=0;
var Width=0;
// 获取屏幕 宽高，添加滚动条
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




var num="";

 // 读取服务端的数据
     var id = new Array(); 
     var platformName = new Array(); 
     var policyName = new Array(); 
     var deviceName=new Array(); 
     var project = new Array(); 
     var res = new Array(); 
     var useTime= new Array(); 

// 获取测试用例列表
var  GetReportList=function()
{

 /* body... */
    $.ajax({
 
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetReportList",
            //提交的数据
            data:{"page":"2"},
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
              // platformName=null;
              // platformName= new Array(); 
              // 获取返回参数
                          
                  var   data= XMLHttpRequest.responseText;
                // alert(data);
                    // 遍历数组赋值
                      var a=0;
                    $.each(JSON.parse(data), function(i, obj)
                    { 
                       
                      // id[a]=obj.id;
                      id[a]=obj.id;
                      platformName[a]=obj.platformName;
                      policyName[a]=obj.policyName;
                      deviceName[a]=obj.deviceName;
                      project[a]=obj.project;
                      res[a]=obj.res;
                      useTime[a]=obj.useTime;
                      // genTime[a]=obj.genTime;
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
    var tr=$("<thead > <tr > <th width=\"30\" height=\"30\" >序号</th> <th width=\"30\" height=\"30\" >项目名称</th>  <th width=\"30\" height=\"30\" >测试平台</th> <th width=\"100\" height=\"30\" >策略名称</th>  <th width=\"50\" height=\"30\" >运行设备</th> <th width=\"30\" height=\"30\" >测试结果</th> <th width=\"60\" height=\"30\" >运行时间</th> <th width=\"30\" height=\"30\" >操&nbsp &nbsp &nbsp作</th></thead></tr>");
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
                
           td=$("<td  id=project"+a+"  align=\"center\">"+project[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td  id=platformName"+a+"  align=\"center\">"+platformName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=policyName"+a+"  align=\"center\">"+policyName[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=deviceName"+a+" align=\"center\" >"+deviceName[i]+ "</td>");
           td.appendTo(tr);
            td=$("<td id=res"+a+" align=\"center\" >"+res[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=useTime"+a+" align=\"center\" >"+useTime[i]+ "</td>");
           td.appendTo(tr);
           b=a-1;
td=$("<td id=set"+a+"> <a href=\"#\"<i onclick=\"setLogDataId();reportDetails(); \"  id=mac"+a+"  class=\"icon-zoom-in\"></i></a>&nbsp <a href=\"#\" <i id=lok"+a+" class=\"icon-book\" onclick=\"setLogDataId();reportLog();\" ></i></a>&nbsp     <a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"><i  onclick=\" setPolicyId(); \"  id="+b+" class=\"icon-trash\"></i></a></td>");   
           td.appendTo(tr);   
                  


     }

    
        var white = "white";

   document.getElementById("createtable").style.color = white;
     $("#createtable").append("</table>");
        var white = "black";

   document.getElementById("createtable").style.color = white;
     // alert("ddd");

}

// 跳转到报告详情
var  reportDetails =function()
{
  window.open("testreportdetails.html");
}

// 跳转到日志页面
var  reportLog =function()
{
  window.open("testreportlog.html");
}
var log=0;
// 点击查看报告详情及查看日志时获取策略名称到cookia
var setLogDataId=function()
{
 log =event.target.id; 
 // alert(log);
var length =log.length;
log=log.substring(length-1, length);

var policyName=document.getElementById("policyName"+log).innerHTML;
// alert(policyName);
setCookie("policyName",policyName);
// alert(getCookie("policyName"));
// alert(log);

// // deleTestCase(policId);
// alert(getCookie("policId"));
//    console.log(a);
}

var Id="";
// 获取点击删除td行数据的 数据库的id值
var setPolicyId=function()

{
var policId =event.target.id; 
Id=parseInt(policId);
Id=id[Id];
// alert(Id);
}
var GetReporLogPolicyName=function()
{

  }
// 删除测试报告
var DelReport=function()
{
  
// alert(policId);
 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/DelReport",
            //提交的数据
            data:{"id":Id},
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






  

