package main.server.testcase;

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

import org.bouncycastle.asn1.icao.CscaMasterList;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.TimeString;

/**新增测试用例
 * 添加到 用例步骤表 和用例表
 * 
 */
@WebServlet("/AddTestCase")
public class AddTestCase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;

	private    List<HashMap<String, String>> rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTestCase() {
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
//     
     
        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        
		{
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
   	     
//   	     获得用户名
   	     String user = (String) session.getAttribute("trueName");
//   	     依次获得参数值：
   	     String project = (String) session.getAttribute("project");
   	     try 
   	     {
   	    	String casename=request.getParameter("casename");
   	    	String step=request.getParameter("step");
   	    	String elementtype=request.getParameter("elementtype");
   	    	String elementname=request.getParameter("elementname");
   	    	String weblocatype=request.getParameter("weblocatype");
   	    	String weblocatstring=request.getParameter("weblocatstring");
   	    	String ioslocatype=request.getParameter("ioslocatype");
   	    	String ioslocatstring=request.getParameter("ioslocatstring");
   	    	String androidlocatype=request.getParameter("androidlocatype");
   	    	String androidlocatstring=request.getParameter("androidlocatstring");
   	    	String pars=request.getParameter("pars");
   	    	String expet=request.getParameter("expet");
   	    	String action=request.getParameter("action");
   	    	String asser=request.getParameter("asser");
   	    	String addman=user;
   	    	String adddate=TimeString.getyMDHMS();
   	    	String caseStepTable="testcase"+project;
//   	    	插入步骤表
   	     mysql.getSqlResault("insert into "+caseStepTable+" (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,pars,"
   	     		+ "expet,action,asser,addman,adddate,updatedate)"
   	     		+ "values('"+casename+"','"+step+"','"+elementtype+"','"+elementname+"','"+weblocatype+"','"+weblocatstring+"','"+ioslocatype+"','"+ioslocatstring+"','"+androidlocatype+"','"+androidlocatstring+"','"+pars+"','"+expet+"','"+action+"','"+asser+"','"+addman+"','"+adddate+"','"+adddate+"')", false);

//   	插入主表
   	     rs=mysql.getSqlResault("select * from testcasemain where casename ='"+casename+"'", true);
// 第一次添加用例
   	     if (rs.size()==0)
    {
   	  mysql.getSqlResault("insert into testcasemain (project,casename,step,addman,adddate)"+ "values('"+project+"','"+casename+"','"+1+"','"+addman+"','"+adddate+"')", false);
	}
    else 
    {
//    	获取用例步骤数
    	rs=mysql.getSqlResault("select  count(step) from "+caseStepTable+" where casename ='"+casename+"'",true);
    	String s=rs.get(0).get("count(step)");
     
//    	 stream.write(("s:"+s).getBytes("UTF-8"));
    	int ste=DataHandle.getInt(s);
    
//    	插入主表
    	String aString="update testcasemain set step="+ste+" where casename='"+casename+"'";
//        stream.write(aString.getBytes("UTF-8"));
//    	mysql.getSqlResault(" update testcasemain set step="+ste+" where casename='"+casename+"'", false);
    	mysql.getSqlResault(" update testcasemain set updatedate='"+adddate+"',  updateman='"+addman+"'，step="+ste+" where casename='"+casename+"'", false);

    }

   	     String aString="insert into testcasemain (casename,step,addman,adddate)"+ "values('"+casename+"','"+step+"','"+addman+"','"+adddate+"')";
   	     String  res="用例添加成功";
   	     stream.write(res.getBytes("UTF-8"));
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
      			 String url = "/autotestcloud/webpro/login/login.html";
      				stream.write(url.getBytes("UTF-8"));
//      			  response.sendRedirect(url);
      		} 
	}

}
