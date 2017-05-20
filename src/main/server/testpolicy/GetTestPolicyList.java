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
 * 获取测试策略列表
 */
@WebServlet("/GetTestPolicyList")
public class GetTestPolicyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  ConnectMySQL mysql;
	private static   List<HashMap<String, String>> rs;
 
 
    public GetTestPolicyList() {
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
        HttpSession session = request.getSession(false);
     String project = (String) session.getAttribute("project");
 	String caseStepTable="testcase"+project;
        //如果session能够取到，说明用户已经登录
        if(session!=null)
//        	
		{
//    	    创建数据连接
   	     mysql =new ConnectMySQL();
   	     mysql.connect("localhost:3306/AutoTest", "root", "root");
//   	     String   casename =request.getParameter("casename");
   	     rs=mysql.getSqlResault("select * from runconfig  where project='"+project+"'   order by id  asc", true);
   	  String json="";
//   	  如果用例个数不足10个，直接获取
   	     if (rs.size()<=10)
   	     {
   	    	  json=DataHandle.getJosnObjectArry(rs);
   	     }
   	     else
   	     {
			String  page=request.getParameter("page");
//			获取客户端想要的页数
			int start=Integer.valueOf(page);
//			定义开始的数据
			start=start*10;
//			定义结束的数据
		    int end=start+10; 
//		    如果想要的数据不大于数据总数 且数据比开始的数据多 获取（最后一页）
		    if (rs.size()<end && rs.size()>start) 
		    {
		    	  json=DataHandle.getJosnObjectArry(rs,start,rs.size());
			}
//		    如果超过数据总数（想要数据不存在）
		    else if (rs.size()<start)
		    {
				json="超出数据行数";
			}
//		    （截取中间的数据）
		    else 
		    {
		    	  json=DataHandle.getJosnObjectArry(rs,start,end);
			}
		 
   	     }
 		stream.write(json.getBytes("UTF-8"));	
		}
        
        else {
        	 String url = "/autotestcloud/webpro/login/login.html";
				stream.write(url.getBytes("UTF-8"));
		}
	}

}
