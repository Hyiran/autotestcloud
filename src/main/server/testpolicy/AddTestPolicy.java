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

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;

/**新增测试策略
 * Servlet implementation class AddTestPolicy
 */
@WebServlet("/AddTestPolicy")
public class AddTestPolicy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
 
    public AddTestPolicy() {
        super();
        
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
//        HttpSession session = request.getSession(false);
//		   String  project=(String) session.getAttribute("project");
		   String  project="xiaoying";
        //如果session能够取到，说明用户已经登录
        if(true)
//        	session!=null
		{
        	String ids=request.getParameter("id");
        	
        	 String caseName=request.getParameter("caseName");
        	 String platformName=request.getParameter("platformName");
        	 String policyName=request.getParameter("policyName");
        	 String timeouts=request.getParameter("timeout");
        	 int timeout=DataHandle.getInt(timeouts);
        	 String deviceName=request.getParameter("deviceName");
        	 String plantversion=request.getParameter("plantversion");
        	 String filename=request.getParameter("filename");
        	 String androidappActivity=request.getParameter("androidappActivity");
        	 String androidappPackage=request.getParameter("androidappPackage");
        	 String iosbundleid=request.getParameter("iosbundleid");
        	 String iosudid=request.getParameter("iosudid");
        	 String elementtimeouts=request.getParameter("elementtimeout");
        	 int elementtimeout=10;
        	 String  removeApp=request.getParameter("removeApp");
//     	    创建数据连接
       	     mysql =new ConnectMySQL();
       	     mysql.connect("localhost:3306/AutoTest", "root", "root");
       	     
       	     if (ids.equals(""))
       	     {
//       	     mysql.getSqlResault("insert  into  runconfig  (caseName,platformName,policyName) values('"+caseName+"','"+platformName+"' ", false);
       	     mysql.getSqlResault("insert into runconfig (project,caseName,platformName,policyName,timeout,deviceName,plantversion,"+ "filename,androidappActivity,androidappPackage,iosbundleid,iosudid,"+ "elementtimeout,removeApp)"
        	     		+ "values('"+project+"','"+caseName+"','"+platformName+"','"+policyName+"',"+timeout+",'"+deviceName+"','"+plantversion+"','"+filename+"','"+androidappActivity+"','"+androidappPackage+"','"+iosbundleid+"','"+iosudid+"',"+elementtimeout+",'"+removeApp+"')", false);

       	     String  aString="测试策略添加成功";
       	 
       	     stream.write(aString.getBytes("UTF-8"));
			}
       	     else
       	     {
				int id=DataHandle.getInt(ids);
				
				mysql.getSqlResault("update runconfig  set  caseName='"+caseName+"', platformName='"+platformName+"',policyName='"+policyName+"',timeout="+timeout+", deviceName='"+deviceName+"'"
						+ ", plantversion='"+plantversion+"', filename='"+filename+"'"
								+ ", androidappActivity='"+androidappActivity+"', androidappPackage='"+androidappPackage+"', elementtimeout='"+elementtimeout+"'"
								+ ", iosbundleid='"+iosbundleid+"', iosudid='"+iosudid+"',timeout="+timeout+" where id="+id+"", false);
				 String  aString="测试策略更新成功";		       	 
	       	     stream.write(aString.getBytes("UTF-8"));
			}
		}
	       
        //否则，说明用户没有登录，跳转到登录页面让用户登录
    		else
    		{
    			 String url = "/autotestcloud/webpro/login/login.html";
    				stream.write(url.getBytes("UTF-8"));
//    			  response.sendRedirect(url);
    		} 
	}

}
