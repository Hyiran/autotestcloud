package main.server.tools;

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
 * 获取设备是否在用
 */
@WebServlet("/GetMachineIsUse")
public class GetMachineIsUse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;

	private    List<HashMap<String, String>> rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMachineIsUse() {
        super();
        // TODO Auto-generated constructor stub
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
		  //此处不新建session，只是去取已经创建的session
        HttpSession session = request.getSession(false);


        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        	
		{
        	String machineName=request.getParameter("machineName");
       	 
//    	    创建数据连接
      	     mysql =new ConnectMySQL();
      	     mysql.connect("localhost:3306/AutoTest", "root", "root");    	     
      	   	rs= mysql.getSqlResault("select *  from machine  where  machineName = '"+machineName+"'  ", true);
      	   	 
      	  String res=rs.get(0).get("isuse");
      	  if (res.equals("1")) 
      	  {
      		res="设备使用中";
      	  }
      	
   	   	 stream.write(res.getBytes("UTF-8"));	
		}
        else {
        	 String url = "/autotestcloud/webpro/login/login.html";
				stream.write(url.getBytes("UTF-8"));
		     }
	}

}
