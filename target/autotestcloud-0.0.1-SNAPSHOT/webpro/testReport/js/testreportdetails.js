
 // 存储服务端用例列到数组
     var casename = new Array(); 
     var totalAsser = new Array(); 
     var passAsser = new Array(); 
     var loseAsser = new Array(); 
     var passrat=new Array(); 
     var isStop = new Array(); 
  
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
            url:"/autotestcloud/GetReportDetails",
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
                     // var obj = new Array(); 
                      // id[a]=obj.id;
                      casename[a]=obj.casename;
                      totalAsser[a]=obj.totalAsser;
                      passAsser[a]=obj.passAsser;
                      loseAsser[a]=obj.loseAsser;
                      passrat[a]=obj.passrat;
                      isStop[a]=obj.isStop;
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


var pie=function(rat)
{
var b=100-rat;
  $(document).ready(function() {
    var rand = function() {
      return Math.floor((Math.random() * 100) + 1)
      }

      $('*[data-behavior="pie-chart"]').each(function() {
      $(this).svgPie({
        percentage: b
      });

      });
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
     // var tr=$(" <tr > <th colspan=\"7\" class=\"header suite\"> 测试用例执行情况</th> </tr>");
     //  tr.appendTo(table);
    var tr=$(" <tr bgcolor=\"#ADADAD\">    <th>用例名称</th> <th >是否异常退出</th>  <th >总断言数</th> <th >断言成功</th> <th >断言失败失败</th>  <th>断言通过率</th> </tr>");

     tr.appendTo(table);

      // 遍历 tr  td
      var  a=parseInt("0");
      var   stop=0;
      var  totalAsserNo=0;
      var  totalPassNo=0;
      var  totalfailNo=0;
      var rat=0;
     for(var i=0;i<casename.length;i++)
     {  
      var tr=$("<tr class=\" test\" id=tr"+a+" ></tr>");
        tr.appendTo(table);
         a++;
           td=$("<td id=casename"+a+"  align=\"center\">"+casename[i]+ "</td>");
           td.appendTo(tr);
           if (isStop[i]=="是")
            {
              stop++;
            }
           td=$("<td id=isStop"+a+" align=\"center\" >"+isStop[i]+ "</td>");
           td.appendTo(tr);
           totalAsserNo=totalAsserNo+parseInt(totalAsser[i]);
           td=$("<td    id=totalAsser"+a+"  align=\"center\">"+totalAsser[i]+ "</td>");
           td.appendTo(tr);
           totalPassNo=totalPassNo+parseInt(passAsser[i]);
           td=$("<td  id=passAsser"+a+"  align=\"center\">"+passAsser[i]+ "</td>");
           td.appendTo(tr);
           totalfailNo=totalfailNo+parseInt(loseAsser[i]);
           td=$("<td  id=loseAsser"+a+" align=\"center\" >"+loseAsser[i]+ "</td>");
           td.appendTo(tr);
           td=$("<td  id=passrat"+a+" align=\"center\" >"+passrat[i]+ "</td>");
           td.appendTo(tr);  
     }
     rat= totalPassNo/totalAsserNo;
     rat=rat.toFixed(4);
     rat=rat*100+"%";
      tr=$("<tr bgcolor=\"green\" id=\"total\" ></tr>");
        tr.appendTo(table);
           td=$("<td id=\"totalcasename\" align=\"center\" >总计用例："+casename.length+ "</td>");
           td.appendTo(tr);  
           if (stop>0)
            {
        td=$("<td bgcolor=\"red\" id=\"totalisStop\" align=\"center\" >异常用例："+stop+ "</td>");
           td.appendTo(tr); 
            }
            else
            {
                td=$("<td id=\"totalisStop\" align=\"center\" >异常用例："+stop+ "</td>");
           td.appendTo(tr); 
            }
           
            td=$("<td id=\"totaltotalAsser\" align=\"center\" >总计断言："+totalAsserNo+ "</td>");
           td.appendTo(tr);  
            td=$("<td id=\"totalpassAsser\" align=\"center\" >总通过数："+totalPassNo+ "</td>");
           td.appendTo(tr);  
              if (totalfailNo>0)
            {
                td=$("<td bgcolor=\"red\" id=\"totalloseAsser\" align=\"center\" >总失败数："+totalfailNo+ "</td>");
   
           td.appendTo(tr); 
            }
            else
            {
               td=$("<td  id=\"totalloseAsser\" align=\"center\" >总失败数："+totalfailNo+ "</td>");
              td.appendTo(tr); 
            }
          
        
          // rat= parsefloat(rat);
           // rat=rat.toFixed(2);
            td=$("<td id=\"totalpassrat\" align=\"center\" >总成功率："+rat+ "</td>");
           td.appendTo(tr);  
        var white = "white";

   document.getElementById("createtable").style.color = white;
     $("#createtable").append("</table>");
        var white = "black";

   document.getElementById("createtable").style.color = white;
  var rats= (casename.length-stop)/casename.length;
  rats=rats*100;
  rats=rats.toFixed(2);
  // alert(rats);
// rats=parseInt(rats)*100;
 
   t(rats);
 
}

    var t=function(rats)
{
  // alert(rats);
//  var b=100;
// b=100-rats;
  $(document).ready(function() {
    var rand = function() {
      return Math.floor((Math.random() * 100) + 1)
      }

      $('*[data-behavior="pie-chart"]').each(function() {
      $(this).svgPie({
        percentage: rats
        
      });

      });
});
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
 // Height=Height*0.5;

   document.getElementById("createtable").setAttribute("style","overflow-y:auto; overflow-x:auto; width:"+Width+"px; height:"+500+"px");
}

