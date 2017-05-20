package main.funtion;

import org.testng.annotations.Test;

public class SqlLog
{
	private   ConnectMySQL mysqlLog;
	private String policyName="";
	private String project="";
	private String platformName="";
	
	
	public void  changeTestCaseName(String caseName)
	{
		
	}
	public void init(String policyNameP,String projectP,String platformNameP) 
	{
		mysqlLog =new ConnectMySQL();
		mysqlLog.connect("localhost:3306/AutoTest", "root", "root");
		policyName=policyNameP;
		project=projectP;
		platformName=platformNameP;
	
	}
	
	public void deadly(String logData,String caseName) 
	{
		String	genTime=TimeString.getyMDHMS();
	
		mysqlLog.getSqlResault("insert into reportlog (caseName,logType,logData,genTime,policyName,project,platformName)"
   	     		+ "values('"+caseName+"','致命','"+logData+"','"+genTime+"','"+policyName+"','"+project+"','"+platformName+"')", false);
	}
	public void  serious(String logData,String caseName) 
	{
		String	genTime=TimeString.getyMDHMS();
		mysqlLog.getSqlResault("insert into reportlog (caseName,logType,logData,genTime,policyName,project,platformName)"
   	     		+ "values('"+caseName+"','严重','"+logData+"','"+genTime+"','"+policyName+"','"+project+"','"+platformName+"')", false);
	
	}
	public void warming(String logData,String caseName,String minScreenLink,String maxScreenLink) 
	{
		System.out.println("执行警告sql");
		String	genTime=TimeString.getyMDHMS();
		mysqlLog.getSqlResault("insert into reportlog (caseName,logType,logData,genTime,policyName,project,platformName,minScreenLink,maxScreenLink)"
   	     		+ "values('"+caseName+"','警告','"+logData+"','"+genTime+"','"+policyName+"','"+project+"','"+platformName+"','"+minScreenLink+"','"+maxScreenLink+"')", false);
	
	}
	/**
	 * 一般信息打印
	 * @param logData  输出数据
	 * @param caseName  用例名称
	 */
	public void infor(String logData,String caseName) 
	{
		String	genTime=TimeString.getyMDHMS();
		mysqlLog.getSqlResault("insert into reportlog (caseName,logType,logData,genTime,policyName,project,platformName)"
   	     		+ "values('"+caseName+"','一般','"+logData+"','"+genTime+"','"+policyName+"','"+project+"','"+platformName+"')", false);
	
	}
	
//	@Test
	public void t1()
	{
//		ConnectMySQL mysql =new ConnectMySQL();
//	    mysql.connect("localhost:3306/Config", "root", "root");
	    
	    SqlLog sqlLog=new SqlLog();
	    sqlLog.init("登录", "xiaoying", "Android");
	    sqlLog.infor("测试数据", "测试用例1");
	    sqlLog.warming("警告", "警告用例", "截图1", "截图2");
	    sqlLog.serious("错误", "错误测试用例");
	    sqlLog.deadly("致命", "致命测试用例");
	}
}
