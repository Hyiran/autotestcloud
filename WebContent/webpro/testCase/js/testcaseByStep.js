
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
     var parsByStep  = new Array(); 
     var expetByStep  = new Array(); 
     var actionByStep  = new Array(); 
     var asserByStep  = new Array(); 
// 获取服务端列表数据
var  genTestCaseStepTable=function(del)
{

      // 宽度
  var Width=document.body.clientWidth;
   Width=parseInt(Width);
  Width=Width*0.98;
  // 高度
   Height=document.body.clientHeight;

 Height=parseInt(Height);
 Height=Height*0.88;
  // alert(vf);
  // ;
  // 添加滚动条
  document.getElementById("createtable2").setAttribute("style","overflow-y:auto; overflow-x:auto; width:"+Width+"px; height:"+Height+"px");
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

               var url=XMLHttpRequest.responseText;
                // alert(url);
                if (url =="/autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
                else
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
        actionByStep[a]=obj.action;
         // alert(actionByStep[a]);
        parsByStep[a]=obj.pars;
        // alert(obj.pars);
        expetByStep[a]=obj.expet;
        asserByStep[a]=obj.asser;
        // alert(asserByStep[a]);
       a++;

          }); 
           // alert("执行完毕");
              gendata2(del);
                  
                }
   

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
      var tr=$("<thead> <tr > <th width=\"20\" height=\"30\" >步骤</th> <th width=\"10\" height=\"30\" >类型</th><th width=\"50\" height=\"30\" >元素名称</th> <th width=\"40\" height=\"30\" >web类型</th> <th width=\"50\" height=\"30\" >web定位</th> <th width=\"20\" height=\"30\" >Ios类型</th> <th width=\"50\" height=\"30\" >Ios定位</th> <th width=\"30\" height=\"30\">Android类型</th><th width=\"50\" height=\"30\" >Android定位</th><th width=\"50\" height=\"30\" >操作/功能</th><th width=\"10\" height=\"30\" >参数</th><th width=\"50\" height=\"30\" >预期结果</th> <th width=\"50\" height=\"30\" >断言</th> </thead></tr> ");
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
      td=$("<td id=actionByStep"+a+"  align=\"center\">"+actionByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=parsByStep"+a+"  align=\"center\">"+parsByStep[i]+ "</td>");  
    td.appendTo(tr);
      td=$("<td id=expetByStep"+a+"  align=\"center\">"+expetByStep[i]+ "</td>");
    td.appendTo(tr);
      td=$("<td id=asserByStep"+a+"  align=\"center\">"+asserByStep[i]+ "</td>");
    td.appendTo(tr);
    //     td=$("<td id=pars2ByStep"+a+"  align=\"center\">"+pars2ByStep[i]+ "</td>");
    // td.appendTo(tr);
    //     td=$("<td id=action2ByStep"+a+"  align=\"center\">"+action2ByStep[i]+ "</td>");
    // td.appendTo(tr);

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
// 跳转到用例编辑页面
var  getEditData =function(e)
{
    if (clickTd=="")
     {
      alert("请选择要编辑的用例步骤");
    }
    else
    {

      


       var  name=getCookie("testCaseName");
       // 用于删除 编辑
       setCookie("casename",name);
       // 用于编辑页新增
       setCookie("testCase",name);
       
      // alert(getCookie("casename"));
       var tdId="stepByStep"+clickTd;
       var text=getTrText(tdId);
       setCookie("step",text);
    
        Dataid=idByStep[(clickTd-1)];
         // alert(Dataid)
       setCookie("Dataid",Dataid);


       tdId="elementtypeByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("elementtype",text);

       tdId="elementnameByStep"+clickTd;
       text=getTrText(tdId);
       // alert(text);
       setCookie("elementname",text);
      // alert(getCookie("elementname"));

        tdId="weblocatypeByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("weblocatype",text);

       tdId="weblocatstringByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("weblocatstring",text);


       tdId="ioslocatypeByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("ioslocatype",text);

       tdId="ioslocatstringByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("ioslocatstring",text);

       tdId="androidlocatypeByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("androidlocatype",text);

       tdId="androidlocatstringByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("androidlocatstring",text);

        tdId="actionByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("action",text);

      tdId="parsByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("pars",text);

      tdId="expetByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("expet",text);

        tdId="asserByStep"+clickTd;
       text=getTrText(tdId);
       setCookie("asser",text);
  // alert(getCookie("asser"));
      
 
     
      window.open("testcaseedit.html","编辑测试步骤","top=100,left=100,width=1000,height=800,toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no" );
    }
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
 // alert(casename);


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