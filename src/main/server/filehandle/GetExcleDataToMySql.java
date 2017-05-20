package main.server.filehandle;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.ExcelReader;
import main.funtion.TimeString;

/**
 * 讲用户用例excle 文件导入到数据库
 */
@WebServlet("/GetExcleDataToMySql")
public class GetExcleDataToMySql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   ConnectMySQL mysql;

	private    List<HashMap<String, String>> rs;
    public GetExcleDataToMySql() {
        super();
      
    }

	


	
	 String doPost(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException {
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
        
        String  user = (String)session.getAttribute("trueName");
        String  res="";
        String  res1="";
        try {
        	   File fle =new File(path);
        	   String realPath=fle.getAbsolutePath();
        	   
        	   if (fle!=null)
        	   {
        		   
//        	        读取数据文件
        		   
        		   ExcelReader ex = new ExcelReader(realPath,"data");      
        		   String aString =ex.getCellData(3, "casename");
//            	    创建数据连接 插入数据库
        	   	     mysql =new ConnectMySQL();
        	     	  res=res+"文件路径："+realPath;
        	   	      res=res+"项目："+project;
        	   	     mysql.connect("localhost:3306/AutoTest", "root", "root");  
//        	     	 用例名称
        	   	     String caseName="";
        	    	 int size =ex.getMapData.size();
        	 	  res=res+"数据长度："+size;
        	 	 for (int i = 2; i <=size; i++)
        	 	 {
        	 		 if (i==2)
        	 		 {
//        	 			 获取第一个用例名称
        	 			caseName=ex.getCellData(i, "casename");
        	 			res=res+"用例名称"+caseName;
					}
        	 		 else
        	 		 {
//        	 			 如果为空
						if (ex.getCellData(i, "casename").equals(""))
						{
							
						}
//						更新用例名称
						else
						{
							caseName=ex.getCellData(i, "casename");
						}
					 }
        	String date=TimeString.getyMDHMS();
//        	    	插入步骤表
 mysql.getSqlResault("insert into "+caseStepTable+" (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,"
+ "androidlocatype,androidlocatstring,pars,"+ "expet,action,asser,addman,adddate,updatedate)"
+ "values('"+caseName+"','"+ex.getCellData(i, "step")+"','"+ex.getCellData(i, "elementtype")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "weblocatype")+"',"
+ "'"+ex.getCellData(i, "weblocatstring")+"','"+ex.getCellData(i, "ioslocatype")+"','"+ex.getCellData(i, "ioslocatstring")+"','"+ex.getCellData(i, "androidlocatype")+"','"
+ex.getCellData(i, "androidlocatstring")+"','"+ex.getCellData(i, "pars")+"','"+ex.getCellData(i, "expet")+"','"+ex.getCellData(i, "action")+"','"+ex.getCellData(i, "asser")+"','"
+user+"','"+date+"','"+date+"')", false);
 res1="insert into "+caseStepTable+" (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,"
+ "androidlocatype,androidlocatstring,pars,"+ "expet,action,asser,addman,adddate,updatedate)"
+ "values('"+caseName+"','"+ex.getCellData(i, "step")+"','"+ex.getCellData(i, "elementtype")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "weblocatype")+"',"
+ "'"+ex.getCellData(i, "weblocatstring")+"','"+ex.getCellData(i, "ioslocatype")+"','"+ex.getCellData(i, "ioslocatstring")+"','"+ex.getCellData(i, "androidlocatype")+"','"
+ex.getCellData(i, "androidlocatstring")+"','"+ex.getCellData(i, "pars")+"','"+ex.getCellData(i, "expet")+"','"+ex.getCellData(i, "action")+"','"+ex.getCellData(i, "asser")+"','"
+user+"','"+date+"','"+date+"')";
        	   	  res=res+"插入步骤表成功轮数"+i;
//        	   	插入主表
        	   	     rs=mysql.getSqlResault("select * from testcasemain where casename ='"+caseName+"'", true);
        	// 第一次添加用例
        	   	     if (rs.size()==0)
        	    {
        	   	    	
        	   	  mysql.getSqlResault("insert into testcasemain (casename,step,addman,adddate,updateman,updatedate,project)"+ "values('"+caseName+"','"+1+"','"+user+"','"+date+"','"+user+"','"+date+"','"+project+"')", false);
        	   	 res=res+"插入主表成功轮数"+i;
        	    }
        	    else 
        	    {
//        	    	获取用例步骤数
        	    	
        	    	rs=mysql.getSqlResault("select  count(step) from "+caseStepTable+" where casename ='"+caseName+"'",true);
        	    	String s=rs.get(0).get("count(step)");
        	     
//        	    	 stream.write(("s:"+s).getBytes("UTF-8"));
        	    	int ste=DataHandle.getInt(s);
        	    
//        	    	插入主表
        	    	String e="update testcasemain set step="+ste+" where casename='"+caseName+"'";
//        	        stream.write(aString.getBytes("UTF-8"));
        	    	mysql.getSqlResault(" update testcasemain set updatedate='"+date+"', updateman='"+user+"',step="+ste+" where casename='"+caseName+"'", false);
        	        res=res+"更新主表成功轮数"+i;
        	     	res="update testcasemain set updatedate='"+date+"', updateman='"+user+",step="+ste+" where casename='"+caseName+"'";

        	    }

//        	 		 System.out.println(ex.getCellData(i, "elementname"));
        	 	 }
        	     	res="文件上传成功！文件解析成功，测试用例已经导入数据库，请在测试用例页面刷新查看";
        	   
			}
        		return res;
        }
        catch (Exception ex) 
        {
        	res="文件上传失败！请使用页面添加模式添加用例";
        	return	res;
//			  return "导入用例到数据库失败，reason是:"+ex.toString();

		}
//    	return res;
      
	}

}
