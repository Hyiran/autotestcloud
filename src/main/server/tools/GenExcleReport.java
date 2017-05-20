package main.server.tools;

import java.io.File;
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
import main.funtion.ExcelHandle;
import main.funtion.TimeString;

/**
 * 生成测试报告
 */
@WebServlet("/GenExcleReport")
public class GenExcleReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public 		ExcelHandle excelHandle=null;
	
    public GenExcleReport() {
        super();
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		设置编码  
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
		  //此处不新建session，只是去取已经创建的session
//        HttpSession session = request.getSession(false);
//     
//        session!=null
        //如果session能够取到，说明用户已经登录
        if(true)
//        
		{
//        	  String  project=(String) session.getAttribute("project");
//        	  String  user=  (String)session.getAttribute("userName");
         	  String  project="xiaoying";
        	  String  user=  "fujiaxi";

   	     

//   	     依次获得参数值：
   	     
   	     try 
   	     {
   	    	
   	      String p="/report/"+project+"/"+user;
	        //获取文件需要上传到的路径  
	        String path = this.getServletContext().getRealPath(p);
	        File filepath=new File(path);
	        if(!filepath.exists())
	        {
	        filepath.mkdirs();
	        }
	         excelHandle=new ExcelHandle();
//	         excelHandle.updateFile("report.xls", p+"/report.xls");
//	        String aString=p+"/report.xls";
//	     	stream.write(aString.getBytes("UTF-8"));
	  
//   	     stream.write(res.getBytes("UTF-8"));
   	     } 
   	     
   	     catch (Exception e)
   	     {
   	   	   
   	     String res =  e.toString();
   	     
  		 stream.write(res.getBytes("UTF-8"));	
			// TODO: handle exception
		}
   	  
   	     
		}
      //否则，说明用户没有登录，跳转到登录页面让用户登录
      		else
      		{
      			 String url = "http://127.0.0.1:8081/autotestcloud/webpro/login/login.html";
      				stream.write(url.getBytes("UTF-8"));
//      			  response.sendRedirect(url);
      		} 
	}

}
