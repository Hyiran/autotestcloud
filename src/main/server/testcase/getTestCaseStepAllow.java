package main.server.testcase;

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

import main.funtion.ConnectMySQL;

/**
 * 判断当前测试用例步骤是否可用(测试用例步骤是唯一的)
 * request 包含测试用例名称及步骤
 */
@WebServlet("/getTestCaseStepAllow")
public class getTestCaseStepAllow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTestCaseStepAllow() {
        super();
        // TODO Auto-generated constructor stub
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
	        String  project=(String) session.getAttribute("project");
	        String caseStepTable="testcase"+project;
        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        	
		{

   	     try 
   	     {
//     	    创建数据连接
   	   	     mysql =new ConnectMySQL();
   	   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//   	   	   获得用例名称
   	   	     String casename=request.getParameter("testCase");
   	   	     String step=request.getParameter("step");
   	   	     rs=mysql.getSqlResault("select * from "+caseStepTable+" where casename ='"+casename+"' and step='"+step+"'", true);
   	   	     if (rs.size()!=0) 
   	   	     {
   	    	   String ERR="执行步骤已存在，请更换新编号";
   	   	   	   stream.write(ERR.getBytes("UTF-8"));	
			}
   	   	     else
   	   	     {
   	   	   String aString="编号可用";
	   	    	 stream.write(aString.getBytes("UTF-8"));	
   	  
			}
   	
   	     } 
   	     
   	     catch (Exception e) {
   	     String res =  e.toString();
  		stream.write(res.getBytes("UTF-8"));	
			// TODO: handle exception
		}
   	  
   	     
		}
      //否则，说明用户没有登录，跳转到登录页面让用户登录
      		else
      		{
      			 String url = "/autotestcloud/webpro/login/login.html";
      				stream.write(url.getBytes("UTF-8"));
//      			  response.sendRedirect(url);
      		} 
	}



}
