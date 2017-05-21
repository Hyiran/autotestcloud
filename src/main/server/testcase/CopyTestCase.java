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
 * 复制侧测试用例
 */
@WebServlet("/CopyTestCase")
public class CopyTestCase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;
	private    List<HashMap<String, String>> rs;
    public CopyTestCase() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

   	public int totalNo=0;
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
     String sqlString="";
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
//   	     获得用例名称
   	  String oldCasename=request.getParameter("oldCasename");
   	  String newCasename=request.getParameter("newCasename");
   	  String caseStepTable="testcase"+project;
//   	  记录原用例有多少步
   	  int oldStepNo=0;
//   	  新用例加入主表的部数

   	  String res="";
   	     try 
   	     {
//   	    	sqlString="select * from "+caseStepTable +" where casename ='"+oldCasename+"'"+"--";
//   	    	 原用例是否存在
   	      rs=mysql.getSqlResault("select * from "+caseStepTable +" where casename ='"+oldCasename+"'", true);
   	   totalNo=rs.size();
   	      if (rs.size()==0)
   	      {
			res="当前用例不存在";
		  }
//   	      存在
   	      else 
   	      {
   	    	  int size=rs.size();
//   	    	  新用例是否存在
			 int step=1;
			
//sqlString=sqlString+"select * from "+caseStepTable +" where casename ='"+newCasename+"' order by step desc"+"--";
			 List<HashMap<String, String>>  rs2=mysql.getSqlResault("select * from "+caseStepTable +" where casename ='"+newCasename+"' order by step desc" , true);
			 totalNo=totalNo+rs2.size();
			 oldStepNo=rs2.size();
			  
			 if (rs2.size()!=0) 
			 {
//				 更新主表
				 //如果存在获取最大步骤数

mysql.getSqlResault("update testcasemain set updatedate='"+TimeString.getyMDHMS()+"',  updateman='"+user+"',step="+totalNo+" where casename='"+newCasename+"'", false);
sqlString="update testcasemain set updatedate='"+TimeString.getyMDHMS()+"',  updateman='"+user+"',step="+totalNo+" where casename='"+newCasename+"'";
			}
			 else 
			 {
//					插入主表
mysql.getSqlResault("insert into testcasemain (project,casename,step,addman,adddate)"+ "values('"+project+"','"+newCasename+"',"+totalNo+",'"+user+"','"+TimeString.getyMDHMS()+"')", false);

sqlString="insert into testcasemain (project,casename,step,addman,adddate)"+ "values('"+project+"','"+newCasename+"',"+totalNo+",'"+user+"','"+TimeString.getyMDHMS()+"')";
			 }
//				插入步骤表
			 for (int i = 0; i < size; i++) 
			 {

 mysql.getSqlResault("insert into "+caseStepTable+" (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,pars,"
+ "expet,action,asser,addman,adddate,updatedate)"
+ "values('"+newCasename+"',"+oldStepNo+",'"+rs.get(i).get("elementtype")+"','"+rs.get(i).get("elementname")+"','"+rs.get(i).get("weblocatype")+"','"+rs.get(i).get("weblocatstring")+"','"+rs.get(i).get("ioslocatype")+"'"
		+ ",'"+rs.get(i).get("ioslocatstring")+"','"+rs.get(i).get("androidlocatype")+"','"+rs.get(i).get("androidlocatstring")+"','"+rs.get(i).get("pars")+"','"+rs.get(i).get("expet")+"',"
				+ "'"+rs.get(i).get("action")+"','"+rs.get(i).get("asser")+"','"+rs.get(i).get("addman")+"','"+rs.get(i).get("adddate")+"','"+rs.get(i).get("updatedate")+"')", false);
 oldStepNo++;
			 }
   	      }


   	      res="用例复制成功，请刷新页面查看";
//  	      stream.write(res.getBytes("UTF-8"));
  	    stream.write(res.getBytes("UTF-8"));
  	    
   	     }
   	     catch (Exception e)
   	     {
   	   	   
   	      res =  e.toString();
  		 stream.write(res.getBytes("UTF-8"));	
//  		  stream.write(sqlString.getBytes("UTF-8"));	
		}
   	  
   	     
		}
      //否则，说明用户没有登录，跳转到登录页面让用户登录
      		else
      		{
      			 String url = "/autotestcloud/webpro/login/login.html";
      				stream.write(url.getBytes("UTF-8"));

      		} 
	}

}
