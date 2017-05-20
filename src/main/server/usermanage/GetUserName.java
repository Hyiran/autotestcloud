package main.server.usermanage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 获取当前用户真实姓名
 */
@WebServlet("/GetUserName")
public class GetUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetUserName() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");  
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
         //此处不新建session，只是去取已经创建的session
         HttpSession session = request.getSession(false);
         //如果session能够取到，说明用户已经登录
         if(session!=null)
         {
       String user = (String)session.getAttribute("trueName");
       stream.write(user.getBytes("UTF-8"));
      
         }
         //否则，说明用户没有登录，跳转到登录页面让用户登录
         else
         {
        	 String errString= "获取不到用户";
        	 stream.write(errString.getBytes("UTF-8"));
         } 
	}

}
