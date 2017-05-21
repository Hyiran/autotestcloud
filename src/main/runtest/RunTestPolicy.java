package main.runtest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.appium.java_client.android.AndroidDriver;
import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.GetIp;
import main.funtion.TimeString;
import main.server.tools.SendMail;


/**
 * 运行测试策略 页面运行调用
 */
@WebServlet("/RunTestPolicy")
public class RunTestPolicy extends HttpServlet {
	public static final long serialVersionUID = 1L;
       
	public   ConnectMySQL mysql;
	
		public    List<HashMap<String, String>> rs;
		public    List<HashMap<String, String>> rs2;
    public RunTestPolicy()
    {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	  String tetString="";
	String tESTLOGString="";
	String user="";
//	测试开始时间
	public String  startTestTime;
//	测试结束时间
	public String  endTestTime;
//	耗时
	public String  useTime;
//	运行结果
	public String  resault;
//	程序是否异常退出
	public boolean  stopTest=false;
//	项目名称
	String  project="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
		  //此处不新建session，只是去取已经创建的session
		HttpSession session=null;
         session = request.getSession(false);
//         获得项目
        project=(String) session.getAttribute("project");
        user=(String) session.getAttribute("userName");
//        	初始化测试开始时间
        	startTestTime=TimeString.getyMDHMS();
//        	获取策略数据id值
        	String ids=request.getParameter("id");
        	int id=DataHandle.getInt(ids);
  
//    	    创建数据连接
      	     mysql =new ConnectMySQL();
      	     mysql.connect("localhost:3306/AutoTest", "root", "root");
      	   
      	   RunTestCaseByPolicy run=null;
//      	  查询测试策略 数据
      	   	rs= mysql.getSqlResault("select *  from runconfig where id ="+id+" ", true);      	  
//      	   	如果找到了一条记录(策略存在)
	      	   	if (rs.size()==1)
	      	   	{
//	      	   		删除此条策略历史记录-报告日志 
	      	   		this.delAlredReport();
	      	   	try {
	      	     	    List<HashMap<String, String>> rs= mysql.getSqlResault("select *  from runconfig where id = "+id, true);
	    	    	    String policy=rs.get(0).get("policyName");
	      	     	    run =new RunTestCaseByPolicy();	
//	      	     	    获取当前项目根路径
	      	     	   	String proPath= this.getServletContext().getRealPath("/");
	    	//	    	   初始化
	    		    	   run.init(rs,proPath);
	    	//	    	  执行测试
	    		    	   run.getTestPolicy(project);	
	    		    	   
//	    		    	   更新策略表 结果及运行日期
	    		    	   if (run.success)
	    		    	   {
	    		    		   resault="成功";
	    		    		  
	    		    	   }
	    		    	   else 
	    		    	   {
	    		    		   resault="失败";
	    		    	   }
//	    		    	   生成最后运行时间
	    		    	   String time=TimeString.getyMDHMS();
	    		    	   
//	    		    	   mysql.connect("localhost:3306/AutoTest", "root", "root");
	    		    	   mysql.getSqlResault("update runconfig  set res='"+resault+"',lastruntime='"+time+"'  where policyName ='"+policy+"' ", false);
					} catch (Exception e)
	      	   		{
						tESTLOGString=e.getMessage();
						stopTest=true;
					}
	 
	      	  
      	 	
	      	  	//获取测试结束时间
	      	   	endTestTime =TimeString.getyMDHMS();
//	      	  耗时
	       	    useTime=TimeString.timeMinus(endTestTime, startTestTime);
	       	    if (stopTest) 
	       	    {
	       	    	resault="失败";
				}
	       	    else {
	       	    	resault="成功";
				}
//	       	    mysql=new ConnectMySQL();
//	       	    插入测试报告  	
//	       	    mysql.connect("localhost:3306/AutoTest", "root", "root");
	       	    mysql.getSqlResault("insert into report (project,platformName,deviceName,policyName,res,startTime,endTime,useTime)"
   	     		+ "values('"+project+"','"+run.platformName+"','"+run.deviceName+"','"+run.policyName+"','"+resault+"','"+startTestTime+"','"+endTestTime+"',"
   	     				+ "'"+useTime+"')", false);
//	       	    获取收件人邮箱地址
	       	   rs= mysql.getSqlResault(" select * from user where user ='"+user+"'", true);
	       	   String receivedManEmail=rs.get(0).get("email");
	       	String Subject="自动化测试执行"+resault;
	       	GetIp getIp =new GetIp();
	       	String ip=getIp.getMyIp();
	       	String Content="您的自动化测试已经执行完毕，请到测试报告页面查看测试详情。网站地址:http://"+ip+":8081/autotestcloud";
	        SendMail sendMail =new SendMail();
	       	boolean sendSuccess= sendMail.sendMailToUser(sendMail, "jurryfu@163.com", "love525131417", receivedManEmail, Subject, Content);
	       	  if (sendSuccess)
	       	  {
	       		tESTLOGString="测试完毕，邮件已发送到您的邮箱，您可以在测试报告页面查看测试结果";
	       	  }
	       	  else 
	       	  {
	       		tESTLOGString="测试完毕，请在测试报告页面查看测试结果";
			  }
	       	  
	       	    stream.write(tESTLOGString.getBytes("UTF-8"));
		}
			
       	 
		}

//   删除存在的报告
	private void delAlredReport()
	{
//	   		删除已经存在的同名策略报告
	   	String policy1 =rs.get(0).get("policyName");
	   	rs2=mysql.getSqlResault(" select * from report where project ='"+project+"' and policyName ='"+policy1+"'", true);
//	   tetString="select * from report where project ='"+project+"' and policyName ='"+policy1+"'";
	   	if (rs2.size()>0)
	   	{
//	      mysql.connect("localhost:3306/"+AutoTest, "root", "root");
//	     删除主表
	   	     mysql.getSqlResault("delete from report where policyName='"+policy1+"'", false);
//	   	     删除字表
	   	    mysql.getSqlResault("delete from repordetails where policyName='"+policy1+"'", false);
	   	     
//	   	    删除日志表
	   	 mysql.getSqlResault("delete from reportlog where policyName='"+policy1+"'", false);
//	   	 mysql.connect("localhost:3306/Config", "root", "root");
	}
	   	
	}

}
