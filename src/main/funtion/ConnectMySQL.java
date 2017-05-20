package main.funtion;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


/**
 * 链接mysql数据库
 * @author Administrator
 *
 */
public class ConnectMySQL extends GetClassMethodName {

	  //加载MYSQL JDBC驱动程序   
   public static String driver = "com.mysql.jdbc.Driver";
   //获取数据地址
   private static String host;
//   	链接数据库的用户名
   private static String user;
//   用来存储sql查询结果的条数
   private  static int  row=0;
//   用来存储sql查询表的列数
   private  static  int column=0;
// 链接数据库的密码
   private static String pwd;
//用于链接数据的实例
   private static Connection conn = null;
//   存储sql查询表的列名
   private static String [] columnname;

//用于执行sql语句的的实例
   private static Statement stmt = null;
   public static int columnsize;
// 初始化数据看的地址 用户名 密码
   /**
    * 链接数据库
    * @param host  数据库地址
    * @param user  用户名
    * @param pwd  密码
    */
   public static void connect(String host, String user, String pwd)
   {
       ConnectMySQL.close();
       ConnectMySQL.host = host;
       ConnectMySQL.user = user;
       ConnectMySQL.pwd = pwd;
   }

   public static synchronized void close() {
       try {
           if (stmt != null) {
               stmt.close();
               stmt = null;
           }
           if (conn != null) {
               conn.close();
               conn = null;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
//构造方法 用于链接数据库
   private static void connectMySQL() {
       try {
           Class.forName(driver).newInstance();
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://"
                   + host + "?useUnicode=true&characterEncoding=UTF8", user,
                   pwd);
       } catch (InstantiationException e) {
//       	Log.logInfo("error:calss:ConnectMySQL,Method:connectMySQL"+e.toString(),GetClassMethodName());
           e.printStackTrace();
       } catch (IllegalAccessException e) {
//       	Log.logInfo("error:calss:ConnectMySQL,Method:connectMySQL"+e.toString(),GetClassMethodName());

           e.printStackTrace();
       } catch (ClassNotFoundException e) {
//       	Log.logInfo("error:calss:ConnectMySQL,Method:connectMySQL"+e.toString(),GetClassMethodName());

           e.printStackTrace();
       } catch (SQLException e) {
//       	Log.logInfo("error:calss:ConnectMySQL,Method:connectMySQL"+e.toString(),GetClassMethodName());

           e.printStackTrace();
       }

   }
//封装执行sql语句对象实例
   private static void statement() {
       if (conn == null) {
           ConnectMySQL.connectMySQL();
       }
       try {
//       	用于执行sql语句的对象实例
           stmt = (Statement) conn.createStatement();
       } catch (SQLException e) 
       {
//       	Log.logError("error:calss:ConnectMySQL,Method:statement"+e.toString(),GetClassMethodName());
           e.printStackTrace();
       }
   }
//封住sql语句的执行结果
   private static ResultSet resultSet (String sql) {
       ResultSet rs = null;
       if (stmt == null) {
           ConnectMySQL.statement();
       }
       try {
//       	执行sql语句 并把语句的结果返回
           rs = stmt.executeQuery(sql);
//           statement.executeUpdate(sql);
//           int	a=stmt.executeUpdate(sql); ;
//           Log.logInfo("smtp!!!");
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return rs;
   }
/**
* 执行sql语句
* @param sql   sql语句
* @param select  语句是否是查询语句，如果查询语句返回HashMap，如果更新 删除传false 返回null
* @return
*/
  
   public static synchronized List<HashMap<String, String>> getSqlResault(String sql,boolean select)
   {
   	if (select) {
   		return ConnectMySQL.result(sql);
		}
   	else {
   		updata(sql);
   		return null;
		}
   }
   public static synchronized List<HashMap<String, String>> quer2y(String sql)
   {
   	  return ConnectMySQL.result(sql);
   }
   
    /**
     * 把查询结果封装到obect[][]对象中 ，数据驱动
     * @param sql  sql语句
     * @return obect[][]
     */
   public static Object[][] getDataProvideData(String sql)
   {
   	
   	 List<HashMap<String, String>> rs = ConnectMySQL.getSqlResault(sql,true);    

        int myrow =row;
   	 int mycolumn=column;
   	 row=0;
   	 column=0;
   	  Object [][] data= new Object [myrow][mycolumn];
      
   	
		for (int i=0;i<myrow;i++)
		{
			
			for (int m=0;m<mycolumn;m++)
			{
			
				
				data[i][m]=rs.get(i).get(columnname[m]);
//				System.out.println("查询结果：第："+(i+1)+"行"+columnname[m]+"列的列值为："+data[i][m]);	
			}
		}
//		重置columnname对象
		columnname=null;

  return data;
   }
   private static List<HashMap<String, String>> result(String sql) 
   {
//   	得到sql语句执行结果
   	 ResultSet rs =null;
   	try {
   		  rs = ConnectMySQL.resultSet(sql);
		} catch (Exception e)
   	{
//			Log.logError("执行查询语句失败！",GetClassMethodName());
		}
      

       List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
       try {
       	
           ResultSetMetaData md = rs.getMetaData();
//           获取当前表有多少列
           int cc = md.getColumnCount();
           
//           获得列数
           column=cc;
         

//           获得当前表各列的列名，注意
           String[] Columnname = new String[cc];
           
          for (int i=0;i<cc;i++)
          {
//       	  md.getColumnName(i+1)的索引是从1开始的
       	   Columnname[i] = md.getColumnName(i+1);
//       	   name[i]=md.getColumnName(i+1);
//       	   System.out.println("当前表第"+(i+1)+"列的列名为"+columnname[i]);
          }
//          把查询结果的列面存入数组
          columnname=Columnname;
          
           while (rs.next()) {
               HashMap<String, String> columnMap = new HashMap<String, String>();
               for (int i = 1; i <= cc; i++) {
                   columnMap.put(md.getColumnName(i), rs.getString(i));
//               	  columnMap.put(md.getColumnName(i), rs.ge));
               }
               result.add(columnMap);             
           }
       } catch (SQLException e) {
//       StringWriter sw =new StringWriter();
//       PrintWriter pw= new PrintWriter(sw);
//           e.printStackTrace(pw);
//         System.out.println(sw.toString());
           e.printStackTrace();
       }
//       获得行数
        row =result.size();
       return result;
   }
	private static int updata(String sql) 
   {
		int a=0;
		  if (stmt == null) {
	            ConnectMySQL.statement();
	        }
	        try {
//	        	执行sql语句 并把语句的结果返回
	             a = stmt.executeUpdate(sql);
//	            statement.executeUpdate(sql);
//	            int	a=stmt.(sql); ;
//	            Log.logInfo("smtp!!!");
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return a;
		
   }
   /**
    * 测试封装好的类功能
    * @param args
    */
   public static void main (String args[])
   {
   
	 	ConnectMySQL mysql= new ConnectMySQL();
	 	mysql.connect("localhost:3306/Config", "root", "root");
//	 	  DataHandle dataHandle=new DataHandle();
//	     String auth=dataHandle.getRadomString(8);
//	     String userName="3";
//	     String passWord="3";
//	     String email="3";
//	     mysql.getSqlResault(, false);
//	    List<HashMap<String, String>> rs =mysql.getSqlResault("update testcase  set step=4, elementtype='功能',elementname='',weblocatype='id',weblocatstring='asdasd',ioslocatype='id',ioslocatstring='阿萨德',androidlocatype='xpath',androidlocatstring='asd',pars='你就爱上动脑筋',expet='',action='退出',asser='',updateman='hah',updatedate='2017-04-09  16:50:18' where id=14",false);
	 String projectName="projectName";	
	 String projectCode="projectCode";	
//	 	List<HashMap<String, String>>   rs=mysql.getSqlResault("CREATE TABLE `testcasemain` (`id` int(20) NOT NULL AUTO_INCREMENT, `casename` varchar(40) DEFAULT '',`step` int(10) DEFAULT '1', `addman` varchar(20) DEFAULT '',`adddate` datetime DEFAULT CURRENT_TIMESTAMP, `updatedate` datetime DEFAULT CURRENT_TIMESTAMP,`updateman` varchar(20) DEFAULT '',`lastruntime` datetime DEFAULT CURRENT_TIMESTAMP, `resault` varchar(10) DEFAULT '', PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8", false);
	 String user1="admin";	
//	 mysql.getSqlResault("alter table authorization add "+projectCode+" varchar(2)",false);
	 
	 List<HashMap<String, String>>    rs=mysql.getSqlResault("select * from authorization where user='admin'", true);	 
	 List<HashMap<String, String>>    rs2=mysql.getSqlResault("SHOW columns from authorization ", true);
//	 System.out.println(rs2s.size());
	 String json ="[";
	 for (int i = 2; i < rs2.size(); i++) 
	 {
		String col=rs2.get(i).get("COLUMN_NAME");
		String resString=rs.get(0).get(col);
		
		if (resString !=null && resString.equals("1") )
		{
			 List<HashMap<String, String>>    rs3=mysql.getSqlResault("select projectName from project where projectCode='"+col+"'", true);
			String projectName1=rs3.get(0).get("projectName");
			json=json+"{\"projectName\":\""+projectName1+"\",\"projectCode\":\""+col+"\"}";
			if (i!=rs2.size()-1)
			{
				json=json+",";
			}
//	        {"id":"1","tagName":"apple"},  
//			 System.out.println(col+":"+projectName1);
//			 System.out.println(col+":"+projectName1);
		}
		
		}
	 json=json+"]";
	 System.out.println(json);
	}
   
//	 System.out.println(rs.get(1).get("COLUMN_NAME"));
	 //	   	   	   rs=mysql.getSqlResault("", true);
//	   	   String s=rs.get(0).get("count(step)");
//	 	 String s=rs.get(0).get("count(step)");
////	    获得指定行指定列数据
////	 	System.out.println(rs.get(0).get("ESSP_GongShi"));
////	    获得指定行数据
//		System.out.println(rs.size());
	
   
	
}