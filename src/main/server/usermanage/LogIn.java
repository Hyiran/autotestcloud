package main.server.usermanage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.funtion.ConnectMySQL;


/**用户登录
 * 
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn()
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
	    	    
	    try {
//			获取参数
		    String userName=request.getParameter("userName");
		    String passWord=request.getParameter("passWord");  
		    
//		    创建数据连接
		     mysql =new ConnectMySQL();
		     mysql.connect("localhost:3306/AutoTest", "root", "root");
		     rs=mysql.getSqlResault("select * from user where user='"+userName+"'", true);
   
//			    找不到用户
			    if (rs.size()<1) 
			    {
			    	String data = "用户不存在";
			    	stream.write(data.getBytes("UTF-8"));
				}
			    else {
			    	
//				    密码正确 ，正常登录跳转到首页
				    if (passWord.equals(rs.get(0).get("password"))) 
				    {
				    	//验证登录者身份，验证过程此处省略
						//如果合法用户就产生一个session来放置其登录名
						//如果用户输入了用户名，则将其放在session中
				    	
						if(request.getParameter("userName")!=null)
						{
						HttpSession session = request.getSession();//没有Session就新建一个
//						获得真实用户名
						String trueName=rs.get(0).get("trueName");
						
						session.setAttribute("userName",userName);
						session.setAttribute("trueName",trueName);//在服务器端存储"键-值对"
						String url="../mainPage/menu.html";
//						String success ="可以登录";
				    	stream.write(url.getBytes("UTF-8"));	
//				    	response.sendRedirect("webpro/html/menu.html");
//				    	GetIp geIp=new GetIp();
//						String ip=geIp.getMyIp();
//						String url=ip+":8081/autotestcloud/webpro/mainPage/menu.html";
						}	    		
				    }
//				    密码错误
				    else 
				    {
				     String  data = "密码错误";
			    	 stream.write(data.getBytes("UTF-8"));	
					}
				    
			    }
		   
	    } catch (Exception e) {
	    	String aString="失败："+e.toString();
	    	 stream.write(aString.getBytes("UTF-8"));
	    }
	}

}
