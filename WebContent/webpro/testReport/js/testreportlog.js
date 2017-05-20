
 // 存储服务端用例列到数组
     var id = new Array(); 
     var caseName = new Array(); 
     var logType = new Array(); 
     var logData=new Array(); 
     var minScreenLink = new Array(); 
     var maxScreenLink = new Array(); 
     var genTime= new Array(); 
var platformName= new Array(); 
// 获取测试用例列表
var  getTestCaseList=function()
{
var policyName=getCookie("policyName");
// alert(policyName);
 /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetReporLog",
            //提交的数据
            data:{"policyName":policyName},
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
             
                     data= XMLHttpRequest.responseText;
                // alert(data);
                    // 遍历数组赋值
                      var a=0;
                    $.each(JSON.parse(data), function(i, obj)
                    { 
                    
                      // id[a]=obj.id;
                      platformName[a]=obj.platformName;
                      caseName[a]=obj.caseName;
                      logType[a]=obj.logType;
                      logData[a]=obj.logData;
                      minScreenLink[a]=obj.minScreenLink;
                      maxScreenLink[a]=obj.maxScreenLink;
                      genTime[a]=obj.genTime;
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



// 页面生成表格
var  genTestCaseToTable=function()
{

$('#table1').remove();

    // 生成一个表格，没有边框 border=0
     var table=$("<table border=\"0\"  id=\"table1\" class=\"table\"  font size=\"200\"  >");
     table.appendTo($("#createtable"));
     // 设置列头
    var tr=$("<thead > <tr > <th width=\"10\" height=\"30\" >序号</th> <th width=\"30\" height=\"30\" >测试平台</th> <th width=\"40\" height=\"30\" >生成时间</th>  <th width=\"50\" height=\"30\" >用例名称</th> <th width=\"40\" height=\"30\" >日志类型</th> <th width=\"300\" height=\"30\" >日志内容</th>  <th width=\"30\" height=\"30\" style=\"width: 26px;\">截&nbsp图</th></thead></tr>");
     tr.appendTo(table);

      // 遍历 tr  td
      var  a=parseInt("0");
     for(var i=0;i<caseName.length;i++)
     {
      a++;
      // alert(a);  
        var tr=$("<tr  id=tr"+a+" ></tr>");
        tr.appendTo(table);
       
           var td=$("<td id=id"+a+">"+(i+1)+ "</td>");
           td.appendTo(tr );
      

            td=$("<td id=platformName"+a+"  align=\"center\">"+platformName[i]+ "</td>");
           td.appendTo(tr);
           var time=genTime[i].substring(0,genTime[i].length-2);
            td=$("<td    id=genTime"+a+"  align=\"center\">"+time+ "</td>");
            td.appendTo(tr);

           td=$("<td    id=caseName"+a+"  align=\"center\">"+caseName[i]+ "</td>");
           td.appendTo(tr);
           var color="";
           if (logType[i]=="致命")
            {

              color="#FF0000";
              // alert("红色");
            }
             else if (logType[i]=="严重")
            {
              color="#FF0000";
             
              // alert(color);
            }
             else if (logType[i]=="警告")
            {
           
              color="#FFD306";
              // alert(color);
            }
            else
            {
              color="green";
            }
             // alert(color);
           td=$("<td  bgcolor="+color+" id=logType"+a+"  align=\"center\">"+logType[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td id=logData"+a+" align=\"center\" >"+logData[i]+ "</td>");
           td.appendTo(tr);
           var link1=id[i];
           // alert(aa);
           td=$("<td id=link"+a+"> <a href=\"#\"<i  id="+maxScreenLink[(a-1)]+ " onclick=\"getmaxScreen();\" class=\"icon-camera\"></i></a>&nbsp   &nbsp  <a href=\"#\" <i  id="+minScreenLink[(a-1)]+" onclick=\"getminScreen();\"   class=\"icon-picture\"></i></a></td>");   
           td.appendTo(tr);         


     }

     
        var white = "white";

   document.getElementById("createtable").style.color = white;
     $("#createtable").append("</table>");
        var white = "black";

   document.getElementById("createtable").style.color = white;
     // alert("ddd");

}

     var getmaxScreen =function()
     {
      var  linkMax=event.target.id; 
      // alert(linkMax);
  if (linkMax!="") 
      {
         window.open (linkMax,'newwindow','height=500,width=300,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no, location=no,status=no') ;

      }
      
    
     
        // alert(linkMax);
     }

     var getminScreen =function()
     {
      var  linkMin=event.target.id; 
      if (linkMin!="") 
      {
                window.open (linkMin,'newwindow','height=300,width=300,top=100,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no, location=no,status=no') ;

      }
        

     }

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

