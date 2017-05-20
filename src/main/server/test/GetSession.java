package main.server.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetSession
 */
@WebServlet("/GetSession")
public class GetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
			//允许跨域访问

         response.addHeader("Access-Control-Allow-Origin", "*");
         PrintWriter out = response.getWriter();

         String user = "";
         //此处不新建session，只是去取已经创建的session
         HttpSession session = request.getSession(false);
         //如果session能够取到，说明用户已经登录
         if(session!=null)
         {
        		//允许跨域访问

             response.addHeader("Access-Control-Allow-Origin", "*");
         user = (String)session.getAttribute("abc");
         out.println("获得创建的Session");
         out.println("<BR>");
         out.println("登录名："+user);
         }
         //否则，说明用户没有登录，跳转到登录页面让用户登录
         else
         {
        

        	//设置输出的编码为 UTF-8

        response.setCharacterEncoding("UTF-8");

        	//返回给浏览器

//        response.getWriter().write("跨域成功");	 
         response.sendRedirect("http://www.baidu.com");
         } 

				}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
