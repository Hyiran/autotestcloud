
var alertPro=function () 
{
    var url="addpro.html";
 window.open (url,'newwindow','height=200,width=400,top=200,left=450,toolbar=no,menubar=no,scrollbars=no,resizable=no, location=no,status=no') ;

}
var tologin=function () 
{
   /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetloginPage",
            //提交的数据
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
                 // 修改标签的外部的值
          
                 var url=XMLHttpRequest.responseText;
          
                window.location.href=url;
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}
var GenProject=function()
{

var projectName=document.getElementById("projectName").value;
// alert(projectName);
var projectCode=document.getElementById("projectCode").value;
    if (projectName !="" && projectCode !="") 
    {
 /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GenProject",
            //提交的数据
            data:{"projectName":projectName,"projectCode":projectCode},
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
                 // 修改标签的外部的值
          
                 var res=XMLHttpRequest.responseText;
          
                alert(res);
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
    }
    else if(projectName=="")
    {
      alert("请输入项目名称");
    }
    else if(projectCode=="")
    {
      alert("请输入项目标志");
    }
    else
    {
    alert("请输入项目标志及项目名称");
    }
   
  
}

 // 跳转页面规定项目
function setProject(name)
{

 // var name=document.getElementById(id).value;
// setCookie("project",name);
// alert(getCookie("project"));
 $.ajax({
            type:"POST",
            //提交的网址
            url:"/autotestcloud/SetProjectName",
            //提交的数据
            data:{"project":name},
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

                // var url=XMLHttpRequest.responseText;
                // alert(url);
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}

// 跳转页面规定项目
function setProject2()
{
var  projectCode1 = event.target.id; 
// alert(projectCode1);

 $.ajax({
            type:"POST",
            //提交的网址
            url:"/autotestcloud/SetProjectName",
            //提交的数据
            data:{"projectCode":projectCode1},
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

                // var url=XMLHttpRequest.responseText;
                // alert(url);
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
}


  var getSize=function()
  {
   //   var  Width=document.body.clientWidth;
   //   var Height =document.body.scrollHeight;
   //   Width=parseInt(Width*0.999); 
   //   Height=parseInt(Height*0.66); 
   
   // document.getElementById("tq1").setAttribute("width", Width);
   // document.getElementById("tq1").setAttribute("height", Height);
   // document.getElementById("tq2").setAttribute("width", Width);
   // document.getElementById("tq2").setAttribute("height", Height);
  // alert(Width);
  // alert(Height);
  }

var pageNo=1;

  var fanye=function(page)
  {
    if (page=="last")
     {
          if (pageNo==1)
           {

           }
           else
           {
                 // var link =document.getElementById("tq1").getAttribute("src");
                 pageNo--;
                 document.getElementById("tq1").setAttribute("src", "images/p"+pageNo+".jpg");
           }
     }



  else
  {
       if (pageNo==10)
           {

           }
           else
           {
             pageNo++;
               document.getElementById("tq1").setAttribute("src", "images/p"+pageNo+".jpg");
            }
  }
   

   alert(link);
   

  }
 var  getUser=function () 
  {
   
     /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetUserName",
            //提交的数据
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
                 // 修改标签的外部的值
          

            document.getElementById('user').innerHTML="欢迎您："+XMLHttpRequest.responseText;
            var userName= document.getElementById('user').innerHTML;
            // userName=document.setElementById('user').innerHTML="hshs";
           
            },
            //调用出错执行的函数
            error: function(){

                //请求出错处理
            }         
         });
  
    
  }
var GetMenu=function () 
{  
    /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetMenu",
            //提交的数据
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
                if (url =="autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
           
           else
             {
                    var json2=XMLHttpRequest.responseText;  
                    // alert(json2);
                    json2=JSON.parse(json2);
                  
           
                      for(var js2 in json2)
                      {  
                          if (json2[js2]=="1") 
                          {
                            // alert(js2);
                               showVivew (js2);
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

var GetMenu2=function () 
{  
    /* body... */
    $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/GetMenu",
            //提交的数据
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
                if (url =="autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
                else  if (url!="") 
                {
                    var json2=XMLHttpRequest.responseText;  
                    // alert(json2);
                    json2=JSON.parse(json2);
                  
                     var idindex=0;
                      for(var js2 in json2)
                      {  
                        var projectName=json2[js2].projectName;
                        var projectCode=json2[js2].projectCode;
                        // alert(projectName);
                        // alert(projectCode);
                        document.getElementById("a"+idindex).text=projectName;
                        document.getElementById("pro"+idindex).style.display="block";
                        document.getElementById("policy"+idindex).id=projectCode;
                        document.getElementById("case"+idindex).id=projectCode;
                        document.getElementById("report"+idindex).id=projectCode;
                       idindex++;
                    }  
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

function text(j)
{  
  // var json = {"options":"[{/"text/":/"王家湾/",/"value/":/"9/"},{/"text/":/"李家湾/",/"value/":/"10/"},{/"text/":/"邵家湾/",/"value/":/"13/"}]"}   
  json = eval(j.options)  
  for(var i=0; i<json.length; i++)  
  {  
     // alert(json[i].text+" " + json[i].value)  
  }  
}  
function showVivew (id)
{
    
     document.getElementById(id).style.display="block";
  }