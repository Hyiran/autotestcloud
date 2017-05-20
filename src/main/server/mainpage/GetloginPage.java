package main.server.mainpage;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.funtion.GetIp;


/**
 * 回传登录页面地址
 */
@WebServlet("/GetloginPage")
public class GetloginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetloginPage() {
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
		GetIp getIp=new GetIp();
		String ip=getIp.getMyIp();
		String url="http://"+ip+":8081/autotestcloud";
		stream.write(url.getBytes("UTF-8"));
	}

}
