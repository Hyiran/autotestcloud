

var submitclick=function (username,password) {


    /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"http://127.0.0.1:8081/autotestcloud/LogIn",
            //提交的数据
            data:{"userName":username,"passWord":password},
            //返回数据的格式
            datatype: "JSONP",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function()
            {           

            },
            //成功返回之后调用的函数             
            success:function(data){
                    
            } ,
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus)
            {
             

                if (XMLHttpRequest.responseText=="密码错误")
                 {
                    alert(XMLHttpRequest.responseText);
             
                 }
                  else if (XMLHttpRequest.responseText=="用户不存在")
                 {
                    alert(XMLHttpRequest.responseText);
                  }

                 else
                 {
                  
                   // var auth=XMLHttpRequest.responseText;                 
                // 跳转到主页
                // window.location.href="../html/menu.html?auth="+auth;
                  window.location.href="../../mainPage/menu.html";
                 }
                // console.log(XMLHttpRequest.responseText);
               // alert(XMLHttpRequest.responseText);
               // alert(textStatus);

                //HideLoading();
            },
            //调用出错执行的函数
            error: function()
            {
                //请求出错处理
            }         
         });
}

var getuser=function ()
{
    var userName=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    // 用户名为空
    if (userName=="")
     {
       alert('请输入用户名'); 
     }
     else
     {
        // 密码为空
                 if (password=="")
             {
                alert('请输入密码'); 
             }
     }
     // 均不为空请求登录接口
    if (userName && password !="") 
    {
        submitclick(userName,password);
    }
    
}

var  register=function ()
{
     // 跳转到主页
      window.location.href="../newuser/index.html";
}


var link=function () 
{

    /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"http://127.0.0.1:8081/autotestcloud/GetMenu",
            //提交的数据
            data:{"userName":username,"passWord":password},
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
             

                if (XMLHttpRequest.responseText=="密码错误")
                 {
                    alert(XMLHttpRequest.responseText);
             
                 }
                  else if (XMLHttpRequest.responseText=="用户不存在")
                 {
                    alert(XMLHttpRequest.responseText);
                  }

                 else
                 {
                  
                   // var auth=XMLHttpRequest.responseText;                 
                // 跳转到主页
                // window.location.href="../html/menu.html?auth="+auth;
                  window.location.href=XMLHttpRequest.responseText;
                 }
                // console.log(XMLHttpRequest.responseText);
               // alert(XMLHttpRequest.responseText);
               // alert(textStatus);

                //HideLoading();
            },
            //调用出错执行的函数
            error: function()
            {
                //请求出错处理
            }         
         });
}