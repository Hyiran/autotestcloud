package main.server.mainpage;

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
 * 新增一个项目
 */
@WebServlet("/GenProject")
public class GenProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;
	private    List<HashMap<String, String>> rs;
  
    public GenProject() {
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
        String projectName=request.getParameter("projectName");
        String projectCode=request.getParameter("projectCode");
//	    创建数据连接
	     mysql =new ConnectMySQL();
	     mysql.connect("localhost:3306/AutoTest", "root", "root");
	     
//	     查看项目是否存在
	     rs=mysql.getSqlResault("select * from project where projectName ='"+projectName+"'",true);
	     int a=rs.size();
	     rs=mysql.getSqlResault("select * from project where projectCode ='"+projectCode+"'",true);
	     
	     
	     int b=rs.size();
//	     项目不存在
	     if (a==0 && b==0) 
	     {
	    	 //	     生成项目步骤表
	    	 mysql.getSqlResault("CREATE TABLE `testcase"+projectCode+"` (`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据id',`casename` varchar(30) NOT NULL DEFAULT '' COMMENT '用例名称',`step` int(20) NOT NULL DEFAULT '1' COMMENT '用例步骤',`elementtype` varchar(10) DEFAULT '' COMMENT '对象类型',`elementname` varchar(30) DEFAULT '' COMMENT '元素名称',`weblocatype` varchar(10) DEFAULT '' COMMENT 'web定位方式',`weblocatstring` varchar(40) DEFAULT '' COMMENT 'web定位器',`ioslocatype` varchar(10) DEFAULT '',`ioslocatstring` varchar(40) DEFAULT '',`androidlocatype` varchar(10) DEFAULT '',`androidlocatstring` varchar(40) DEFAULT '',`action` varchar(20) DEFAULT '' COMMENT '操作/功能名称',`pars` varchar(200) DEFAULT '' COMMENT '参数列表',`expet` varchar(100) DEFAULT '' COMMENT '预期结果',`asser` varchar(50) DEFAULT '' COMMENT '判断方式（是否相同等）',`updateman` varchar(10) DEFAULT '',`updatedate` datetime DEFAULT NULL,`addman` varchar(10) DEFAULT '',`adddate` datetime DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8", false);	    	
	    	 //	     插入项目表
	    	 mysql.getSqlResault("insert into project (projectCode,projectName) values ('"+projectCode+"','"+projectName+"') ",false);
//	    	权限表添加字段
	    	 mysql.getSqlResault("alter table authorization add "+projectCode+" varchar(2)",false);
//	    	 授权用户该表的权限
	    	 String user=(String) session.getAttribute("userName");
	    	 mysql.getSqlResault("update authorization set "+projectCode+"='1' where user='"+user+"' ",false);
	    	 String success="项目已添加，请刷新页面查看";
	    	 stream.write(success.getBytes("UTF-8")); 
		}
//项目存在
	     else
	     {
	    	 String pro="该项目已经存在，请修改！";
	    	 stream.write(pro.getBytes("UTF-8")); 
	     }
	     
	
		}
		//否则，说明用户没有登录，跳转到登录页面让用户登录
		else
		{
			 String url = "http://127.0.0.1:8081/autotestcloud/webpro/login/login.html";
				stream.write(url.getBytes("UTF-8"));
//			  response.sendRedirect(url);
		} 

	}
	

}
