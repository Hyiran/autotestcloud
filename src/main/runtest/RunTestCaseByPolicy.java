package main.runtest;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.funtion.Action;
import main.funtion.Assertion;
import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.FindWebElement;
import main.funtion.GenReport;
import main.funtion.RunPolicy;
import main.funtion.SqlLog;
import main.funtion.TimeString;
import main.funtion.genAndroidDriver;
import main.zson.ZSON;
import main.zson.result.ZsonResult;

/**
 * 运行测试（核心类）
 * @author fujiaxi
 *
 */
public class RunTestCaseByPolicy 
{	
	public boolean isStop=true;

	public boolean success=true;
	public WebDriver webDriver=null;
	public AndroidDriver androidDriver=null;
	public IOSDriver  iosDriver=null;
	public WebElement  element=null;
	public   ConnectMySQL projectSql=null;

	public List<HashMap<String, String>> mysqlRes;
	public ZsonResult zr1=null;
	public SqlLog sqlLog;
	
	GenReport report;
//	记录用例是否异常退出
	String stop="否";
//	测试平台
	String platformName="";
//	策略名称
	String  policyName="";
	String project="";
//	超时时间
	int timeout=5;
//	用例名称列表
	String  caseName="";
//	运行设备
	String  deviceName="";
//	正在运行的用例
	 String runingCase="";
	/**
	 * 初始化
	 * @param sql  必须链接业务模块库
	 * @param rs  为runconfig指定id查询结果
	 */
//	 断言类
	Assertion assertion;
//	执行类
	Action action;
//	找元素类
	FindWebElement findWebElement;
//	过程处理类
	RunPolicy runPolicy;

	
	String snapShootFloder="";
//	初始化
	public void init(List<HashMap<String, String>> rs,String filePath)
	{
		
//		获取策略信息 及数据库对象
		projectSql=new ConnectMySQL();
//			获取策略查询信息	
//		configSql=configSql1;
		mysqlRes=rs;
//		创建日志对象
		sqlLog=new SqlLog();
//		获得根目录目录
		snapShootFloder=filePath;
//		sqlLog.infor("路径："+snapFilePath, "测试截图");
		System.out.println("初始化完成！");
	}
//	函数重载 创建各自的driver对象-getAndroidDriver
boolean removeApp=false;
String  androidappPackage="";
	public void getAndroidDriver(  )
	{
		
//		获取生成AndroidDriver对象相关参数
		platformName=mysqlRes.get(0).get("platformName");
		policyName=mysqlRes.get(0).get("policyName");
		project=mysqlRes.get(0).get("project");
//		获取截图的路径地址
		 snapShootFloder=snapShootFloder+"Screenshots/"+project+"/"+policyName;
		File dir=new File(snapShootFloder);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
	
	    deviceName=mysqlRes.get(0).get("deviceName");
		String  plantversion=mysqlRes.get(0).get("plantversion");
		String  filename=mysqlRes.get(0).get("filename");
		String  androidappActivity=mysqlRes.get(0).get("androidappActivity");
		  androidappPackage=mysqlRes.get(0).get("androidappPackage");
		 String removeApps=mysqlRes.get(0).get("removeApp");
		 if (removeApps.equals("是")) 
		 {
			 removeApp=true;
		}
//		初始化日志对象
		sqlLog.init(policyName, project, platformName);
//		新建报告对象
		report=new GenReport();
			   
//		过程处理对象
	    runPolicy=new RunPolicy();
//		创建androiddriver
		
	    genAndroidDriver gen=new genAndroidDriver();
	    System.out.println("开始创建androidDriver！");

	
//	    System.out.println("文件绝对路径"+ablFilePath);
	    androidDriver=gen.configAndroid(filename,deviceName, androidappActivity, androidappPackage, timeout);
//	    创建断言对象
		assertion=new Assertion();
	   
	    assertion.init(snapShootFloder, androidDriver, sqlLog, caseName,policyName,  project);
//		创建操作对象
		action=new Action();
		action.init(androidDriver, sqlLog,  policyName,"",  project);	
//		创建找到元素
		 findWebElement=new FindWebElement();
		 findWebElement.init(androidDriver, timeout, sqlLog, policyName,"", project);
		 
	}
	public void getDriver( IOSDriver driver)
	{
		iosDriver=driver;
	}
	public void getDriver( WebDriver driver)
	{
		webDriver=driver;
	}
	
//	遍历策略信息
	public void getTestPolicy(String project)
	{
		
//		获取运行参数
//		测试平台
		 platformName=	mysqlRes.get(0).get("platformName");
//		策略名称
		 policyName=mysqlRes.get(0).get("policyName");
//		超时时间
		 String timeouts=mysqlRes.get(0).get("timeout");
		 timeout=DataHandle.getInt(timeouts);
//		用例名称列表
		  caseName=mysqlRes.get(0).get("caseName");
		  sqlLog.infor("包含用例:"+caseName, caseName);
//		  System.out.println("用例列表:"+caseName);
//		根据平台生产测试对象
//		 如果测试平台是安卓
		if (platformName.equals("Android"))
		{
			try {
//				获得driver对象
				getAndroidDriver();
				
				int i=1;
//					遍历用例名称
					for (String testCaseName : caseName.split(",")) 
					{
						  System.out.println("第"+i+"条用例："+testCaseName);
						  i++;
//						  System.out.println("select *  from testcase where casename ='"+testCaseName+"'  order by step asc");
//		 			    获取当前用例执行列表,按步骤正序排列
						projectSql.connect("localhost:3306/AutoTest", "root", "root");
//						获取当前用例名所有操作步骤
//						用例表名
						String caseTable="testcase"+project;
						mysqlRes= projectSql.getSqlResault("select *  from "+caseTable+" where casename ='"+testCaseName+"'  order by step asc", true); 	 
						if (mysqlRes.toString()=="[]") 
						{
//						如果参数为空不执行	
						}
						else 
						{
//							获取用例名称
							String testCasename=DataHandle.getJosnObjectArryNo(mysqlRes);
//							数据库切换到配置库
//							projectSql.connect("localhost:3306/Config", "root", "root");
//						ZSON 解析
							zr1 = ZSON.parseJson(testCasename);
	 			
//		 			   		  遍历用例执行步骤,并执行		    	  
		 			     	getTestCaseStep(mysqlRes.size());
//		 			     	均正常运行完毕
		 			     	if (isStop)
		 			     	{
//		 			     		生成测试报告
		 			     		sqlLog.infor("测试策略："+policyName+"正常执行完毕生成测试报告", runingCase);
			 			     	report.inserTestCase(stop, policyName, project, runingCase);
							}
		 			     	
		 			     
						}
						
						}
				
 			   	     
				} 
			catch (Exception e)
			{
				e.printStackTrace();
				success=false;
				// TODO: handle exception
			}
			if (removeApp)
		     	{
		     		androidDriver.removeApp(androidappPackage);
			}
			 androidDriver.quit();
		   	 androidDriver=null;
//	 	
				}
				

		}
/**
 * 执行单条用例
 * @param size 用例步骤数
 */

	public void getTestCaseStep(  int size)
	{
//		用于更新用例主表 状态
		String case1=(String) zr1.getValue("/*["+0+"]/casename");
//		判断当前用例是否异常终止
		boolean StopTest=false;
//		    遍历用例步骤
		    for (int i = 0; i < size; i++)
		    {		
		    	  String  casename=(String) zr1.getValue("/*["+i+"]/casename");
		    	  String  step=(String) zr1.getValue("/*["+i+"]/step");
		    	  String  elementtype=(String) zr1.getValue("/*["+i+"]/elementtype");
		     	  String  elementname=(String) zr1.getValue("/*["+i+"]/elementname");
		    	  String androidlocatype=(String) zr1.getValue("/*["+i+"]/androidlocatype");
		    	  String  androidlocatstring=(String) zr1.getValue("/*["+i+"]/androidlocatstring");
		    	  String actionName=(String) zr1.getValue("/*["+i+"]/action");
		    	  String  pars=(String) zr1.getValue("/*["+i+"]/pars");
		    	  String expet=(String) zr1.getValue("/*["+i+"]/expet");
		    	  String  asser=(String) zr1.getValue("/*["+i+"]/asser");
		    	   runingCase=casename;
//		    	执行测试
		    	  if (elementtype.equals("元素"))
		    	  {
		    		  
			    		 if (androidDriver!=null) 
			    		 {
//			    			 System.out.println("用例名称："+casename+"步骤："+i);
//			    			 修改用例名
			    			 findWebElement.changCaseName(casename);
			    			 assertion.changeTestCaseName(casename);
			    			 action.changeTestCaseName(casename);
			    			
//			    	   找到页面元素
			    		 WebElement element= findWebElement.getElement(elementname, androidlocatype, androidlocatstring);		    					    			 
//			    		执行操作
			    		 isStop=runPolicy.runAction(androidDriver, action, actionName, pars, element);	    			 
//			    		断言结果	
			    		 if (asser.equals("是否消失"))
		    			 {
		    				 boolean display=findWebElement.getElementDisappear(element);
		    				 if (display)
		    				 {
		    					 element=null;
							 }
						 }
			    		 runPolicy.runAssert(report,androidDriver, assertion, asser, expet, element, elementname, step,snapShootFloder, action.Width);
//			    		 制空对象
			    		 element=null;
//			    	生成报告

//			    		 System.out.println("是否终止"+isStop);
			    		if (isStop)
			    		{
//			    			用例正常运行
//			    			stop="否";
			    			
						}
			    		else
			    		{
//			    			System.out.println("异常终止进入");
//			    			异常 停止测试
			    			StopTest=true;
//			    			如果元素操作遇到问题，终止当前用例执行，到下一条
			    			stop="是";
//			    			sqlLog.serious("用例阻断，终止当前用例", runingCase);
			    			report.inserTestCase(stop, policyName, project, runingCase);
			    			break;
			    			
				    		 
			    		}
			    		
			    		 }
			    		 else if(iosDriver!=null)
			    		 {
							
			    		 }
			    		 else if(webDriver!=null)
			    		 {
			    			 
			    		 }
			
		    	  	}
//			    	 对象为功能的处理方式
		    	  else 
		    	  {
//		    		  	没有创建driver
		    	  }
		    	  
//		 	
		}
//		    插入测试结果 到用例主表
		    String re="成功";
		    if (StopTest) 
		    {
		    	re="失败";
			}
		   
		 report.inserTestCaseDetails(project, case1, re);
	}

	

//	@Test 
	public void t2()
	{ 
//		创建链接获取获取测试策略数据
		ConnectMySQL mysql =new ConnectMySQL();
	    mysql.connect("localhost:3306/AutoTest", "root", "root");
	    List<HashMap<String, String>> rs= mysql.getSqlResault("select *  from runconfig where id =105 ", true);
	    RunTestCaseByPolicy run =new RunTestCaseByPolicy();  
//	   初始化
	   run.init(rs,"./");
//	  执行测试
	   run.getTestPolicy("xybg");
	}

}
