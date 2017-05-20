package main.server.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;

/**
 * 删除测试报告
 */
@WebServlet("/DelReport")
public class DelReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;
	private    List<HashMap<String, String>> rs;
   
    public DelReport() {
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
//        HttpSession session = request.getSession(false);

        //如果session能够取到，说明用户已经登录
        if(true)
//        	session!=null
		{
        	String idString=request.getParameter("id");
        	int id=DataHandle.getInt(idString);
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//   	     String   casename =request.getParameter("casename");
//   	     获取策略名
   	     rs= mysql.getSqlResault("select *  from report where id="+id, true);
   	  String policyName="";
   	     if (rs.size()==1) 
   	     {
   	    	policyName=rs.get(0).get("policyName");
   	     }
//   	     删除主表
   	     mysql.getSqlResault("delete from report where id="+id, false);
//   	     删除字表
   	    mysql.getSqlResault("delete from repordetails where policyName='"+policyName+"'", false);
   	     
//   	    删除日志表
   	 mysql.getSqlResault("delete from reportlog where policyName='"+policyName+"'", false);
   	     String json="delete from report where id="+id;
   	     String success="测试报告删除成功";
   	     stream.write(success.getBytes("UTF-8"));	
		}
	}

}
