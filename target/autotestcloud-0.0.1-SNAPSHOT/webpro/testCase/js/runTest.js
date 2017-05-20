var getCaseName=function()
{

	document.getElementById("testcase").value=getCookie("runCase");
	caseName=document.getElementById("testcase").value;
}

// 当元素类型是功能还是元素时 触发联动机制
var steRule =function()
{



	var submit="0";
   var al="1";
  var selVal=document.getElementById("platformName").value;
  // alert(selVal);
	if(selVal == "请选择")
	{
		al="0";
		 submit="0";
		alert("请选择测试平台")
	  // 清空 功能信息并设置只读
	}
	else if(selVal == "Web")
	{
 		 document.getElementById("androidappPackage").readOnly = true; 
		 document.getElementById("androidappActivity").readOnly = true; 
		  document.getElementById("androidappPackage").value = ""; 
		 document.getElementById("androidappActivity").value = ""; 
		 document.getElementById("filename1").readOnly = true; 
		  document.getElementById("deviceName1").selectedIndex=0;
		 document.getElementById('deviceName1').disabled=true;
		 document.getElementById("plantversion1").selectedIndex=0;
		 document.getElementById('plantversion1').disabled=true;

	   // 修改其他为只读 
  // 修改其他为只读 
		 document.getElementById('elementtimeout1').disabled=true;
		 document.getElementById("elementtimeout1").selectedIndex=0;
		 document.getElementById("iosbundleid").readOnly = true; 
		 document.getElementById("iosbundleid").value = ""; 
		 document.getElementById("filename2").readOnly = true; 
		 document.getElementById("filename2").value = ""; 
		 document.getElementById('deviceName2').disabled=true;
		 document.getElementById("deviceName2").selectedIndex=0;
		 document.getElementById('plantversion2').disabled=true;
		 document.getElementById("plantversion2").selectedIndex=0;



	   // 修改其他为只读 
		 document.getElementById('elementtimeout2').disabled=false;
		 document.getElementById("weblogin").readOnly = false; 
		 document.getElementById('webmaxwindow').disabled=false;
		 document.getElementById('deviceName3').disabled=false;
		 document.getElementById('plantversion3').disabled=false;
	}

	else if(selVal == "Ios")
	{
	// 修改Android 相关为可选择

		 document.getElementById("androidappPackage").readOnly = true; 
		 document.getElementById("androidappActivity").readOnly = true; 
		  document.getElementById("androidappPackage").value = ""; 
		 document.getElementById("androidappActivity").value = ""; 
		 document.getElementById("filename1").readOnly = true; 
		  document.getElementById("deviceName1").selectedIndex=0;
		 document.getElementById('deviceName1').disabled=true;
		 document.getElementById("plantversion1").selectedIndex=0;
		 document.getElementById('plantversion1').disabled=true;


	   // 修改其他为只读 
		 document.getElementById('elementtimeout1').disabled=false;
		 document.getElementById("iosbundleid").readOnly = false; 
		 document.getElementById("filename2").readOnly = false; 
		 document.getElementById('deviceName2').disabled=false;
		 document.getElementById('plantversion2').disabled=false;



	  // 修改其他为只读 
		 document.getElementById('elementtimeout2').disabled=true;
		 document.getElementById("elementtimeout2").selectedIndex=0;
		 document.getElementById("weblogin").readOnly = true; 
		 document.getElementById("weblogin").value = ""; 
		 document.getElementById('webmaxwindow').disabled=true;
		 document.getElementById("webmaxwindow").selectedIndex=0;
		 document.getElementById('deviceName3').disabled=true;
		 document.getElementById("deviceName3").selectedIndex=0;
		 document.getElementById('plantversion3').disabled=true;
		 document.getElementById("plantversion3").selectedIndex=0;		
		}
	else if(selVal == "Android")
	{
		// 修改Android 相关为可选择

		 document.getElementById("androidappPackage").readOnly = false; 
		 document.getElementById("androidappActivity").readOnly = false; 
		 document.getElementById("filename1").readOnly = false; 
		 document.getElementById('deviceName1').disabled=false;
		 document.getElementById('plantversion1').disabled=false;


	   // 修改其他为只读 
		 document.getElementById('elementtimeout1').disabled=true;
		 document.getElementById("elementtimeout1").selectedIndex=0;
		 document.getElementById("iosbundleid").readOnly = true; 
		 document.getElementById("iosbundleid").value = ""; 
		 document.getElementById("filename2").readOnly = true; 
		 document.getElementById("filename2").value = ""; 
		 document.getElementById('deviceName2').disabled=true;
		 document.getElementById("deviceName2").selectedIndex=0;
		 document.getElementById('plantversion2').disabled=true;
		 document.getElementById("plantversion2").selectedIndex=0;


	   // 修改其他为只读 
		 document.getElementById('elementtimeout2').disabled=true;
		 document.getElementById("elementtimeout2").selectedIndex=0;
		 document.getElementById("weblogin").readOnly = true; 
		 document.getElementById("weblogin").value = ""; 
		 document.getElementById('webmaxwindow').disabled=true;
		 document.getElementById("webmaxwindow").selectedIndex=0;
		 document.getElementById('deviceName3').disabled=true;
		 document.getElementById("deviceName3").selectedIndex=0;
		 document.getElementById('plantversion3').disabled=true;
		 document.getElementById("plantversion3").selectedIndex=0;		 

		 var va= document.getElementById("deviceName1");
		 var index=document.getElementById("deviceName1").selectedIndex;
		 	va=va.options[index].value;
		 // alert(va);

		 if (va=="Samsung Galaxy S5" )
		  {
		  	 document.getElementById('plantversion1').disabled=true;
		    document.getElementById("plantversion1").selectedIndex=1;
		  }
		  if (va=="Samsung Galaxy Note 3")
		   {
		   	 document.getElementById('plantversion1').disabled=true;
		    document.getElementById("plantversion1").selectedIndex=2;
		   }
		 // var va= document.getElementById("deviceName1").selectedIndex=0;		 
	// document.getElementById("parsfuntion").value = "";
	// document.getElementById("parsfuntion").readOnly = true;
	// document.getElementById("funtionName").selectedIndex=0;
	// document.getElementById("funtionName").setAttribute('disabled','true');
	//  document.getElementById("elementname").readOnly = false;

	      
	//      document.getElementById("parselement").readOnly = false;
	//      document.getElementById("expet").readOnly = false;
	//      document.getElementById('elementName').disabled=false;
	//      document.getElementById('asser').disabled=false;
	}

}
 var caseName="";
 var platformName="";
  var policyName="";
  var timeout="";
var deviceName="";
var plantversion="";
var filename="";
var androidappActivity="";
var androidappPackage="";
var iosbundleid="";
var iosudid="";
var elementtimeout="";
var weblogin="";
var webmaxwindow="";

// 检查表单
var commitForm=function()
{
	submit="0";
var  al="1";
caseName=document.getElementById("testcase").value;
   platformName=document.getElementById("platformName").value;
   policyName=document.getElementById("policyName").value;
   timeout=document.getElementById("timeout").value;


 if (platformName=="请选择")
  {
  	submit="0";
  	if (al=="1") 
  	{
  		al="0";
  		alert("请选择测试平台");
  	}
  }
  else
  {
  		submit="1";
  }


    if (timeout=="请选择")
  {
  	submit="0";
  	if (al=="1") 
  	{
  		alert("请选择超时时间")
  		al="0";
  	}
  
  }
    if (caseName=="")
  {
  	submit="0";
  	if (al=="1") 
  	{
  		alert("请输入测试用例名称")
  		al="0";
  	}
  
  }

   else
  {
  		submit="1";
  }


  if (platformName=="Android" && submit=="1" ) 
  {
	 deviceName=document.getElementById("deviceName1").value;
	 plantversion=document.getElementById("plantversion1").value;
	 filename=document.getElementById("filename1").value;
	 androidappActivity=document.getElementById("androidappActivity").value;
	 androidappPackage=document.getElementById("androidappPackage").value;



 	if (androidappActivity=="")
		  {
		  	submit="0";
		  	if (al=="1") 
		  	{
		  		alert("输入安卓登录页activepPckage名")
		  		al="0";
		  	}
		  
		  }

		  
		   else
		  {
		  		submit="1";
		  }

 	if (androidappPackage=="")
		  {
		  	submit="0";
		  	if (al=="1") 
		  	{
		  		alert("输入安卓测试包名")
		  		al="0";
		  	}
		  
		  }

		  
		   else
		  {
		  		submit="1";
		  }

 	if (filename=="")
		  {
		  	submit="0";
		  	if (al=="1") 
		  	{
		  		alert("请输入安卓文件名")
		  		al="0";
		  	}
		  
		  }

		  
		   else
		  {
		  		submit="1";
		  }

	 if (plantversion=="请选择")
		  {
		  	submit="0";
		  	if (al=="1") 
		  	{
		  		alert("请选择安卓版本")
		  		al="0";
		  	}
		  
		  }

		  
		   else
		  {
		  		submit="1";
		  }

	  if (deviceName=="请选择")
	  {
	  	submit="0";
	  	if (al=="1") 
	  	{
	  		alert("请选择安卓设备")
	  		al="0";
	  	}
	  
	  }

	  
	   else
	  {
	  		submit="1";
	  }

  }

  if (platformName=="Ios" && submit=="1" ) 
  {
 	 deviceName=document.getElementById("deviceName2").value;
	 plantversion=document.getElementById("plantversion2").value;
	 iosbundleid=document.getElementById("iosbundleid").value;
	 filename=document.getElementById("filename2").value;
	 elementtimeout=document.getElementById("elementtimeout1").value;
  }
   if (platformName=="Web" && submit=="1" ) 
  {
	 deviceName=document.getElementById("deviceName3").value;
	 plantversion=document.getElementById("plantversion3").value;
	 webloginn=document.getElementById("webloginn").value;
	 webmaxwindow=document.getElementById("webmaxwindow").value;
	 elementtimeout=document.getElementById("elementtimeout1").value;
  }
  if (submit=="1")
   {
   	 runTest();
   }


}
// 获取列表
var GetMachineList=function(plantform)
{
  
 // /* body... */
    $.ajax({

            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"http://127.0.0.1:8081/autotestcloud/GetMachineList",
            //提交的数据
            data:{"plantform":plantform},
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
                if (url =="http://127.0.0.1:8081/autotestcloud/webpro/login/login.html") 
                {
                  window.location.href=url;

                }
                else
                {
                	// 获取返回json
             		var j=url;  
                    // alert(json2);
                    json2=JSON.parse(j);
                  
                  // 遍历
        		 for(var o in json2)
				         { 
				         	// 安卓平台
				        	var plant=json2[o].plantform;
				        	if (plant=="android") 
				        	{
				        		// 设备1
				        		var isuse=json2[o].isuse;
				        		var name=json2[o].machineName;
				        		if (isuse=="0" && name=="Samsung Galaxy S5") 
				        		{
				        			document.getElementById("deviceName1").options[1].style="display: block;"; 
				       //  			 var submenu=document.getElementsByTagName("ul")[0]; 
 										// submenu.style.display="block"; 
				        		}
				        		// 设备2
				        		if (isuse=="0" && name=="Samsung Galaxy Note 3") 
				        		{
				        			document.getElementById("deviceName1").options[2].style="display: block;"; 
				       //  			 var submenu=document.getElementsByTagName("ul")[0]; 
 										// submenu.style.display="block"; 
				        		}
				        	
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


// 运行测试
var runTest=function()
{
	 alert("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。");
	document.getElementById("run").disabled=true;

  
 // /* body... */
    $.ajax({


            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:"http://127.0.0.1:8081/autotestcloud/RunTestCase",
            //提交的数据
            data:{"caseName":caseName,"platformName":platformName,"policyName":policyName,"timeout":timeout,"deviceName":deviceName,"plantversion":plantversion,"filename":filename,"androidappActivity":androidappActivity,"androidappPackage":androidappPackage,"iosbundleid":iosbundleid,"iosudid":iosudid,"elementtimeout":elementtimeout,"weblogin":weblogin,"webmaxwindow":webmaxwindow},
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
	                if (url =="http://127.0.0.1:8081/autotestcloud/webpro/login/login.html") 
	                {
	                  window.location.href=url;

	                }
	                else
	                {
	                	
					       // alert(url);    
				 	} 
               		
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            }         
         });

}