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
import javax.servlet.http.HttpSession;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;

/**
 * 用户注册
 * 
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
 
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//		设置编码  
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
	    	    
	    
	    try {
//			获取参数
		    String userName=request.getParameter("userName");
		    String passWord=request.getParameter("passWord");  
		    String email=request.getParameter("email");  
		    String  trueName=request.getParameter("trueName");  
//		    创建数据连接
		     mysql =new ConnectMySQL();
		     mysql.connect("localhost:3306/AutoTest", "root", "root");
		   
		     DataHandle dataHandle=new DataHandle();
		     
////		     生成八位随机码
//		     String auth=dataHandle.getRadomString(8);
//		     存入数据表
		     mysql.getSqlResault("insert into user (user,password,email,newpassword,trueName)values('"+userName+"','"+passWord+"','"+email+"','123','"+trueName+"')", false);
//		    存入权限表
		     mysql.getSqlResault("insert into authorization (user)values('"+userName+"')", false);
		     
		    
		 	HttpSession session = request.getSession();//没有Session就新建一个
			session.setAttribute("userName",userName);
			session.setAttribute("trueName",trueName);//在服务器端存储"键-值对"
		     String data = "用户创建成功！";
		     String  a="insert into user (user,password,email,newpassword,trueName)values('"+userName+"','"+passWord+"','"+email+"','123','"+trueName+"')--insert into authorization (user,xiaoying,xiaoyingtestcase,xiaoyingpolicy,xiaoyingreport)values('"+userName+"','0','0','0','0')";
		     stream.write(data.getBytes("UTF-8"));
				
				    
		
		   
	    } catch (Exception e) {
	    	String aString="失败："+e.toString();
	    	 stream.write(aString.getBytes("UTF-8"));
	    }
	}

}
