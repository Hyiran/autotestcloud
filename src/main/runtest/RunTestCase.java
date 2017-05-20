package main.runtest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import main.funtion.Action;
import main.funtion.Assertion;
import main.funtion.Config;
import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.Driver;

import main.funtion.ScreenShots;
import main.funtion.genAndroidDriver;

/**
 * 运行单条测试用例
 */
@WebServlet("/RunTestCase")
public class RunTestCase extends HttpServlet {
//	 是否停止测试
	public  boolean  StopRun=false;
//	 存储测试平台
	public  String  plantform="";
//	驱动
	public  WebDriver driver;
//	获得action
	public  Action action=null;
//	测试用例编号
	public  String testCaseNo="";
	
//	截图
	public  ScreenShots screenShots=null;	
//	断言
	public   Assertion assertion=null ;
//	数据库名
	public  String dabaBase;	
//	mysal对象
	public  ConnectMySQL mySql;
//	查询数据库结果
	protected  List<HashMap<String, String>> rs=null;
	
	private static final long serialVersionUID = 1L;
       
	private  DesiredCapabilities capabilities=null ;
    public RunTestCase() 
    {
        super();


    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		设置编码  
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
		  //此处不新建session，只是去取已经创建的session
//        HttpSession session = request.getSession(false);
        //如果session能够取到，说明用户已经登录
        if(true)
//        	session!=null
		{
        	
             String caseName=request.getParameter("caseName");
        	 String platformName=request.getParameter("platformName");
        	 String policyName=request.getParameter("policyName");
        	 String timeouts=request.getParameter("timeout");
        	 int timeout=DataHandle.getInt(timeouts);
        	 String deviceName=request.getParameter("deviceName");
        	 String plantversion=request.getParameter("plantversion");
        	 String filename=request.getParameter("filename");
        	 String androidappActivity=request.getParameter("androidappActivity");
        	 String androidappPackage=request.getParameter("androidappPackage");
        	 String iosbundleid=request.getParameter("iosbundleid");
        	 String iosudid=request.getParameter("iosudid");
        	 String elementtimeouts=request.getParameter("elementtimeout");
        	 int elementtimeout=10;
        	 if (elementtimeouts!="")
        	 {
        		 elementtimeout=DataHandle.getInt(elementtimeouts);
			}
        	  
        	 String weblogin=request.getParameter("weblogin");
           	 String webmaxwindow=request.getParameter("webmaxwindow");
           	 String  aString=platformName+caseName+timeouts;
        	 if (platformName.equals("Android"))
        	 {
        		 deviceName="Samsung Galaxy S5";
        		 androidappActivity="cn.cooperative.activity.LoginActivity";
        		 androidappPackage="cn.cooperative";
        		 timeout=10;
        		 stream.write("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。".getBytes("UTF-8"));
        		 
        		 genAndroidDriver gen=new genAndroidDriver();
        	     AndroidDriver  androidDriver=gen.configAndroid("",deviceName, androidappActivity, androidappPackage, timeout);				
        		 
        	 
			}
        	 
        	 else if(platformName.equals("Ios"))
        	 {
        		  stream.write("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。".getBytes("UTF-8"));	
			}
        	 
        	 else if (platformName.equals("Web"))
        	 {
				
        		  stream.write("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。".getBytes("UTF-8"));
			}
        	 
        	 else {
//        		 stream.write(aString.getBytes("UTF-8"));
        		    stream.write("平台配置错误".getBytes("UTF-8"));
			}
		}
	       
        //否则，说明用户没有登录，跳转到登录页面让用户登录
    		else
    		{
    			 String url = "http://127.0.0.1:8081/autotestcloud/webpro/login/login.html";
    				stream.write(url.getBytes("UTF-8"));
//    			  response.sendRedirect(url);
    		} 
		
	}

}
