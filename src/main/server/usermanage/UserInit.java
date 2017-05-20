package main.server.usermanage;

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

/**
 * 用户注册时 校验类，存在的用户不可注册
 * 
 */
@WebServlet("/UserInit")
public class UserInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
   
    public UserInit() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		/		设置编码  
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
	    	    
	    
	    try {
//			获取参数
		    String userName=request.getParameter("userName");
//		    创建数据连接
		     mysql =new ConnectMySQL();
		     mysql.connect("localhost:3306/AutoTest", "root", "root");
		     rs=mysql.getSqlResault("select * from user where user= '"+userName+"'", true);
		     if (rs.size()!=0)
		     {
		    	 String data = "用户已存在！";
				 stream.write(data.getBytes("UTF-8"));
			}
		     else {
		    	 String data = "用户可注册";
				 stream.write(data.getBytes("UTF-8"));
			}
			
				
				    
		
		   
	    } catch (Exception e) {
	    	String aString="失败："+e.toString();
	    	 stream.write(aString.getBytes("UTF-8"));
	    }
	}

}
