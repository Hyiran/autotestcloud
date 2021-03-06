package main.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo1
 */
@WebServlet("/ServletDemo1")
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");  
	        response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("Served at:付佳喜 ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");  
	        response.setContentType("text/html;charset=utf-8");
	        
	        String action = request.getParameter("action");  
	        if("login_input".equals(action)) {  
	            request.getRequestDispatcher("login.jsp").forward(request , response);  
	        } else if("login".equals(action)) {  
	            String name = request.getParameter("name");  
	            String password = request.getParameter("password");  
	              
	            System.out.println("name->" + name + ",password->" + password);
	        }
	}

}
