var  UserInit=function ()
{

    var username=document.getElementById("userName").value;
    /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/UserInit",
            //提交的数据
            data:{"userName":username},
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
      
                if (XMLHttpRequest.responseText == '用户已存在！')
                 {
                // 修改图标为红叉
               document.getElementById("showuser").setAttribute("class","icon into");
               // 显示图标
             document.getElementById("showuser").style.display="block";    

             var stamp= document.getElementById("commit"); 
             stamp.disabled=true;
                // 
                 // alert(XMLHttpRequest.responseText);

                 }
             else
             {
                // var a=document.getElementById("showuser").getAttribute("class");               
                // a=document.getElementById("showuser").getAttribute("class");
              // 修改图标为绿色
               document.getElementById("showuser").setAttribute("class","icon ticker");
               // 显示绿色
             document.getElementById("showuser").style.display="block";
              var stamp= document.getElementById("commit"); 
             stamp.disabled=false;

                // 用户可用
               
             }
                // console.log(XMLHttpRequest.responseText);
               // alert(XMLHttpRequest.responseText);
               // alert(textStatus);

                //HideLoading();
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
}

var  compart=function ()
{
    // 获得密码
  var password1=document.getElementById("passWord").value;
  // 获得确认密码
  var password2=document.getElementById("surepassWord").value;

if (password1==password2) 
    {
        document.getElementById("showpss2").setAttribute("class","icon ticker");
         // 显示图标
         document.getElementById("showpss2").style.display="block";    

          document.getElementById("showuser").style.display="block";
              var stamp= document.getElementById("commit"); 
             stamp.disabled=false;

     }
  else
  {
     document.getElementById("showpss2").setAttribute("class","icon into");
         // 显示图标
         document.getElementById("showpss2").style.display="block";    
          document.getElementById("showuser").style.display="block";
              var stamp= document.getElementById("commit"); 
             stamp.disabled=true;
   }
}

var  password=function ()
{
    // 获得密码
  var password1=document.getElementById("passWord").value;

if (password1=="") 
    {
        document.getElementById("showpss3").setAttribute("class","icon into");
         // 显示图标
         document.getElementById("showpss3").style.display="block";    
     }
  else
  {
     document.getElementById("showpss3").setAttribute("class","icon ticker");
         // 显示图标
         document.getElementById("showpss3").style.display="block";    
   }
}

var  em=function ()
{
   
    // 获得密码
  var e=document.getElementById("email").value;

if (e=="") 
    {
        document.getElementById("showpss4").setAttribute("class","icon into");
         // 显示图标
         document.getElementById("showpss4").style.display="block";    
     }
  else
  {
     document.getElementById("showpss4").setAttribute("class","icon ticker");
         // 显示图标
         document.getElementById("showpss4").style.display="block";    
   }
}

var  name2=function ()
{
   
    // 获得密码
   var name=document.getElementById("trueName").value;
  // alert(name);

if (name=="") 
    {
        document.getElementById("showuser2").setAttribute("class","icon into");
         // 显示图标
         document.getElementById("showuser2").style.display="block";    
     }
  else
  {
     document.getElementById("showuser2").setAttribute("class","icon ticker");
         // 显示图标
         document.getElementById("showuser2").style.display="block";    
   }
}

var  submit1=function ()
{
    // 是否允许提交
    var sub="no";
    var username=document.getElementById("userName").value;
    var usernames=document.getElementById("showuser").getAttribute("class");

  
       // 获得密码
    var password=document.getElementById("passWord").value;
    var surpassword=document.getElementById("surepassWord").value;
    var email=document.getElementById("email").value;
    var name=document.getElementById("trueName").value;
    // alert(name);
    if (username=="") 
{
 alert('用户名不能为空');
}
      
   else 
        {
          if (password=="") 
              {
                alert('密码不能为空');
              }
          else
              {

                if (surpassword=="")
                 {
                  alert('确认密码不能为空');
                 }
                  else
                  {
                        if (surpassword=="") 
                        {
                      alert('确认密码不能为空');
                         }
                else
                {
                   if (name=="") 

                    { 
                      alert('真实姓名不能为空');
                    }
                    else
                    {

                      // alert(name);
                      sub="yes";
                    } 
                }

                  }
             
               
               }
    }
    

    if (sub=="yes")
    {
         $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"/autotestcloud/UserRegister",
            //提交的数据
            data:{"userName":username,"passWord":password,"email":email,"trueName":name},
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
      
                if (XMLHttpRequest.responseText == '用户创建成功！')
                 {
              alert(XMLHttpRequest.responseText);
              // var auth="dds";
                 // 跳转到主页
                 window.location.href="../mainPage/menu.html";
                 }
             else
             {

               alert(XMLHttpRequest.responseText);

                // 用户可用 
             }
                // console.log(XMLHttpRequest.responseText);
               // alert(XMLHttpRequest.responseText);
               // alert(textStatus);

                //HideLoading();
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });
    }
    /* body... */
   
}