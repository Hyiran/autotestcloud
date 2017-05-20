package main.server.test;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.ExcelReader;
import main.funtion.TimeString;

public class asd
{
	private    List<HashMap<String, String>> rs;
	private   ConnectMySQL mysql;
	public void insertData(String project, String  user ,String FilePath) 
	{

	        String  res="";
	        try {
	        	   File fle =new File(FilePath);
	        	   String realPath=fle.getAbsolutePath();
	        	   
	        	   if (fle!=null)
	        	   {
	        		   
//	        	        读取数据文件
	        		   ExcelReader ex = new ExcelReader(realPath,"data"); 
	        		   
	        			String aString =ex.getCellData(3, "casename");
//	        			System.out.println(ex.getMapData.size());
//	            	    创建数据连接 插入数据库
	        	   	     mysql =new ConnectMySQL();
	        	     	  res=res+"文件路径："+realPath;
	        	     	  res=res+"项目："+project;
	        	   	     mysql.connect("localhost:3306/"+project, "root", "root");  
//	        	     	 用例名称
	        	   	     String caseName="";
	        	   	  int size =ex.getMapData.size();
	        	 	  res=res+"数据长度："+size;
	        	 	 for (int i = 2; i <=size; i++)
	        	 	 {
	        	 		 if (i==2)
	        	 		 {
//	        	 			 获取第一个用例名称
	        	 			caseName=ex.getCellData(i, "casename");
	        	 			res=res+"用例名称"+caseName;
						}
	        	 		 else
	        	 		 {
//	        	 			 如果为空
							if (ex.getCellData(i, "casename").equals(""))
							{
								
							}
//							更新用例名称
							else
							{
								caseName=ex.getCellData(i, "casename");
							}
						 }
	        	String date=TimeString.getyMDHMS();
//	        	    	插入步骤表
	        	   	     mysql.getSqlResault("insert into testcase (casename,step,elementtype,elementname,weblocatype,weblocatstring,ioslocatype,ioslocatstring,androidlocatype,androidlocatstring,pars,"
	        	   	     		+ "expet,action,asser,addman,adddate,updatedate)"
	        	   	     		+ "values('"+caseName+"','"+ex.getCellData(i, "step")+"','"+ex.getCellData(i, "elementtype")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+ex.getCellData(i, "elementname")+"','"+user+"','"+date+"','"+date+"')", false);
	        	   	  res=res+"插入步骤表成功轮数"+i;
//	        	   	插入主表
	        	   	     rs=mysql.getSqlResault("select * from testcasemain where casename ='"+caseName+"'", true);
	        	// 第一次添加用例
	        	   	     if (rs.size()==0)
	        	    {
	        	   	    	
	        	   	  mysql.getSqlResault("insert into testcasemain (casename,step,addman,adddate)"+ "values('"+caseName+"','"+1+"','"+user+"','"+date+"')", false);
	        	   	 res=res+"插入主表成功轮数"+i;
	        	    }
	        	    else 
	        	    {
//	        	    	获取用例步骤数
	        	    	
	        	    	rs=mysql.getSqlResault("select  count(step) from testcase where casename ='"+caseName+"'",true);
	        	    	String s=rs.get(0).get("count(step)");
	        	     
//	        	    	 stream.write(("s:"+s).getBytes("UTF-8"));
	        	    	int ste=DataHandle.getInt(s);
	        	    
//	        	    	插入主表
	        	    	String b="update testcasemain set step="+ste+" where casename='"+caseName+"'";
//	        	        stream.write(aString.getBytes("UTF-8"));
	        	    	mysql.getSqlResault(" update testcasemain set step="+ste+" where casename='"+caseName+"'", false);
	        	    	 res=res+"更新主表成功轮数"+i;
	        	    }

//	        	 		 System.out.println(ex.getCellData(i, "elementname"));
	        	 	 }
//	        	     	res="文件上传成功！文件解析成功，测试用例已经导入数据库，请刷新页面查看";
	        	     	
				}
	        	   System.out.println(res);
	        }
	        catch (Exception ex) 
	        {
	        	ex.printStackTrace();
//	        	 System.out.println(ex.toString());
//				  return "导入用例到数据库失败，reason是:"+ex.toString();

			}
//	    	return res;
	}
	@Test
	public void name() {
		asd asd1=new asd();
		asd1.insertData("xiaoying", "fujiaxi", "/Users/fujiaxi/Desktop/model.xlsx");
	}
	
	
}
