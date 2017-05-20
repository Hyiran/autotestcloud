package main.server.testpolicy;

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
 * 条件查询 策略数据列表
 */
@WebServlet("/GetTestPolicyListByString")
public class GetTestPolicyListByString extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
   
    public GetTestPolicyListByString() {
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
		  //此处不新建session，只是去取已经创建的session
        HttpSession session = request.getSession(false);
        //如果session能够取到，说明用户已经登录
        if(	session!=null)
//        
		{
        	String sql="";
        	String casename=request.getParameter("casename");
        	if (casename!="") 
        	{
        		
        		sql=" and policyName like "+"'%"+casename+"%'";
			}
//        	String addman=request.getParameter("addman");
//        	if (addman!="") 
//        	{
//        		
//        		sql=sql+" and addman like "+"'%"+addman+"%'";
//			}
        	
        	String resault=request.getParameter("resault");
        	if (resault!="") 
        	{
        		sql=sql+" and res="+"'"+resault+"'";
			}
        	
        	String lastruntime=request.getParameter("lastruntime");
        	if (lastruntime!="") 
        	{
        		String to=request.getParameter("to");
        		String  date=request.getParameter("lastruntime");
        		String  time =date.substring(6, 10)+"-"+date.substring(3, 5)+"-"+date.substring(0, 2);
        		sql=sql+" and lastruntime "+to+" '"+time+" 00:00:00'";
        	
        		
			}
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//   	     String   casename =request.getParameter("casename");
   	     rs=mysql.getSqlResault("select * from runconfig  where 1=1  "+sql, true);
//   	     String aString="select * from runconfig  where 1=1  "+sql;
//   	    stream.write(aString.getBytes("UTF-8"));	
   	  String json="";
   	 json=DataHandle.getJosnObjectArry(rs);

		 
   	    
 		stream.write(json.getBytes("UTF-8"));	
		}
	
	}

}
