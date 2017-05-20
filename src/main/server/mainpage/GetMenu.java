package main.server.mainpage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
 * 获得主页面菜单权限 
 */
@WebServlet("/GetMenu")
public class GetMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;
	private    List<HashMap<String, String>> rs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMenu() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	response.addHeader("Access-Control-Allow-Origin", "*");
	doPost(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		设置编码  
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();

		String user = "";
        //此处不新建session，只是去取已经创建的session
        HttpSession session = request.getSession(false);
        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        	
		{
        	user = (String)session.getAttribute("userName");
//	    创建数据连接
	     mysql =new ConnectMySQL();
	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//	     读取权限表数据
	     List<HashMap<String, String>>    rs1=mysql.getSqlResault("select * from authorization where user='"+user+"'", true);	 
//		 读取权限表字段
	     List<HashMap<String, String>>    rs2=mysql.getSqlResault("SHOW columns from authorization ", true);
//		 System.out.println(rs2s.size());
//	     创建json 字符串
		 String json ="[";
		 for (int i = 2; i < rs2.size(); i++) 
		 {
//			 数据从第二列开始
//			 获取第二列的列名
			String col=rs2.get(i).get("COLUMN_NAME");
			String resString=rs1.get(0).get(col);
			
//			如果列（项目）有权限 找到项目名称 和编码返回
			if (resString !=null && resString.equals("1") )
			{
				List<HashMap<String, String>>    rs3=mysql.getSqlResault("select projectName from project where projectCode='"+col+"'", true);
				String projectName1=rs3.get(0).get("projectName");
				json=json+"{\"projectName\":\""+projectName1+"\",\"projectCode\":\""+col+"\"}";
				if (i!=rs2.size()-1)
				{
					json=json+",";
				}

			}
			
			}
		 json=json+"]";

		 if (json.equals("[")) 
		 {
			 json="";
		}
		 stream.write(json.getBytes("UTF-8"));	
		}
		//否则，说明用户没有登录，跳转到登录页面让用户登录
		else
		{
			 String url = "/autotestcloud/webpro/login/login.html";
				stream.write(url.getBytes("UTF-8"));
//			  response.sendRedirect(url);
		} 

	}

}
