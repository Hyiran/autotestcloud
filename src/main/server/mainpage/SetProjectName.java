package main.server.mainpage;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *设置 用户进入项目的工程菜单
 */
@WebServlet("/SetProjectName")
public class SetProjectName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SetProjectName() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
		HttpSession session = request.getSession();//没有Session就新建一个
//		获得真实用户名
			String  projectCode=request.getParameter("projectCode");
		    session.setAttribute("project",projectCode);
//		    

//		    String getString=(String) session.getAttribute("projectCode");
//		    String resString="创建projectCode："+getString;
//	    	stream.write(resString.getBytes("UTF-8"));
	};

}
