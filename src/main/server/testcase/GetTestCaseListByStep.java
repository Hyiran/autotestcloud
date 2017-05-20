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
import main.funtion.DataHandle;

/**
 * Servlet 获得单条测试用例所有步骤
 */
@WebServlet("/GetTestCaseListByStep")
public class GetTestCaseListByStep extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTestCaseListByStep() {
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
        String  project=(String) session.getAttribute("project");
        String caseStepTable="testcase"+project;
        //如果session能够取到，说明用户已经登录
        if(	session!=null)
//        
		{
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//   	  String   casename;
   	 String   casename =request.getParameter("casename");
//   	     casename="费用报销查询";
   	     rs=mysql.getSqlResault("select * from "+caseStepTable+" where casename ='"+casename+"'  and project='"+project+"' order by step  asc ", true);
   	     
   	     String json=DataHandle.getJosnObjectArry(rs);
 		 stream.write(json.getBytes("UTF-8"));	
		}
        else {
        	 String url = "/autotestcloud/webpro/login/login.html";
				stream.write(url.getBytes("UTF-8"));
//			  response.sendRedirect(url);
		}
	}


}
