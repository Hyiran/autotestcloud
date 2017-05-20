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

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.TimeString;

/**
 * 更新测试用例中的单条步骤
 */
@WebServlet("/UpdateTestCaseStep")
public class UpdateTestCaseStep extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;

    public UpdateTestCaseStep() 
    {
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
	        String  project=(String) session.getAttribute("project");
	        String caseStepTable="testcase"+project;
        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        	
		{	     
//        	获得用户名
        	   String user ="";
//      	      user = (String) session.getAttribute("trueName");
        	   user="hah";
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
   	     try {
   	    	String num=request.getParameter("Dataid");
   	     	int id=DataHandle.getInt(num);
   	     	
   	        String casename=request.getParameter("casename");
	    	String ste=request.getParameter("step");
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
	    	String updateman=user;
	    	String updatedate=TimeString.getyMDHMS();
	    	 int step=DataHandle.getInt(ste);
    try {
    	
    	 rs=mysql.getSqlResault(" select * from "+caseStepTable+" where id ="+id+"", true);
	} catch (Exception e)
    {
		
	}
	    		 
	    	  if (rs.size()==0)
	    	 {
	    		 stream.write("数据id找不到无法更新".getBytes("UTF-8"));	
			}
	    	 else {
//	    		 更新
 mysql.getSqlResault("update "+caseStepTable+"  set step="+step+", elementtype='"+elementtype+"',elementname='"+elementname+"',weblocatype='"+weblocatype+"',weblocatstring='"+weblocatstring+"',ioslocatype='"+ioslocatype+"',ioslocatstring='"+ioslocatstring+"',androidlocatype='"+androidlocatype+"',androidlocatstring='"+androidlocatstring+"',"
 		+ "pars='"+pars+"',expet='"+expet+"',action='"+action+"',asser='"+asser+"',updateman='"+updateman+"',updatedate='"+updatedate+"'"+ " where id="+id+"", false); 

//	获取用例步骤数
	rs=mysql.getSqlResault("select  count(step) from "+caseStepTable+" where casename ='"+casename+"'",true);
	String s=rs.get(0).get("count(step)");

//	 stream.write(("s:"+s).getBytes("UTF-8"));
	int stepmain=DataHandle.getInt(s);
	
 mysql.getSqlResault("update testcasemain  set updatedate='"+updatedate+"',updateman='"+updateman+"',step="+stepmain+" where casename='"+casename+"'", false);
	    						
			}


	   String success="用例步骤维护成功";
	   String c="update testcase  set step="+step+", elementtype='"+elementtype+"',elementname='"+elementname+"',weblocatype='"+weblocatype+"',weblocatstring='"+weblocatstring+"',ioslocatype='"+ioslocatype+"',ioslocatstring='"+ioslocatstring+"',androidlocatype='"+androidlocatype+"',androidlocatstring='"+androidlocatstring+"',"
 		+ "pars='"+pars+"',expet='"+expet+"',action='"+action+"',asser='"+asser+"',updateman='"+updateman+"',updatedate='"+updatedate+"'"+ " where id="+id+"";
		 stream.write(success.getBytes("UTF-8"));	
		} catch (Exception e)
   	     {
			   String bString=user.toString();
				 stream.write(bString.getBytes("UTF-8"));	
			// TODO: handle exception
		}
   	     	
		}
    	else
  		{
  			 String url = "/autotestcloud/webpro/login/login.html";
  				stream.write(url.getBytes("UTF-8"));
//  			  response.sendRedirect(url);
  		} 
        
	}

}
