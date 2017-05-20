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
 * Servlet implementation class SetSession
 */
@WebServlet("/SetSession")
public class SetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证登录者身份，验证过程此处省略
        //如果合法用户就产生一个session来放置其登录名
        //如果用户输入了用户名，则将其放在session中
      
        HttpSession session = request.getSession();//没有Session就新建一个
        session.setAttribute("username","fujiaxi");//在服务器端存储"键-值对"
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        out.println("Session已经创建");
        out.println("<BR>");
        out.println("转到其他<A HREF=\"chap03.GetSession\">页面</A>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
