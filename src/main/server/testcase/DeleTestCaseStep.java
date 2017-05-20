package main.server.testcase;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.cookie.Cookie;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.TimeString;



/**
 * 删除用例步骤
 */
@WebServlet("/DeleTestCaseStep")
public class DeleTestCaseStep extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;
	private    List<HashMap<String, String>> rs;
   
 
    public DeleTestCaseStep() {
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
        HttpSession session = request.getSession(false);
        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        	
		{
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//   	  String   casename;
   	 String   casename =request.getParameter("casename");
     String  project=(String) session.getAttribute("project");
     String caseStepTable="testcase"+project;
	 String   steps =request.getParameter("step");
     int step=Integer.valueOf(steps);
// 	获得用户名
	   String user ="";
      user = (String) session.getAttribute("trueName");
	String updateman=user;
	String updatedate=TimeString.getyMDHMS();
	
//	 删除字表数据
	 mysql.getSqlResault("delete from "+caseStepTable+" where casename='"+casename+"' and step="+step+" ", false); 
	 
//		获取用例步骤数
		rs=mysql.getSqlResault("select  count(step) from "+caseStepTable+" where casename ='"+casename+"'",true);
		String s=rs.get(0).get("count(step)");

//		 stream.write(("s:"+s).getBytes("UTF-8"));
		int stepmain=DataHandle.getInt(s);
		
	 mysql.getSqlResault("update testcasemain  set updatedate='"+updatedate+"',updateman='"+updateman+"',step="+stepmain+" where casename='"+casename+"'", false);
	
//	 更新主更新日期及人步骤
     stream.write("用例步骤删除成功".getBytes("UTF-8"));	
		}
        //否则，说明用户没有登录，跳转到登录页面让用户登录
   		else
   		{
   			 String url = "/autotestcloud/webpro/login/login.html";
   				stream.write(url.getBytes("UTF-8"));
//   			  response.sendRedirect(url);
   		} 
	}

}
