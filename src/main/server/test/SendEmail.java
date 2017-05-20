package main.server.test;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 发送邮件
 */
@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SendEmail() {
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
//			SendmailUtil se = new SendmailUtil();
//			String aString=se.doSendHtmlEmail("邮件头文件名", "cesi", "jiaxi.fu-klt@pcitc.com");
//			stream.write(aString.getBytes("UTF-8"));
			
		
	}

}
