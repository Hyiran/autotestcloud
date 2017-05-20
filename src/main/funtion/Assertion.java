package main.funtion;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;




public class Assertion extends GetClassMethodName
{	
//用于监听类监听
    public static boolean flag = true;
    //用于监听类监听
    public static List<Error> errors = new ArrayList<Error>();
//    日志存储路径
    private String     folderPath="";

//    WebDriver
	private WebDriver driver =null ;
//  测试用例名称
   private String caseName="";
//	策略名
	private String policyName="";
//	项目名
	private String project="";
//	日志对象
	private SqlLog sqlLog=null;
//  日志输入
  private   String logData="";
//	截图对象
	ScreenShots screenShots=null;
//    日志中的测试内容
    private  String outPutinfor="";
//    截图文件名
    private  String snapShootFileName="";

  
    /**
     * 初始化
     * @param path  截图文件所在目录地址
     * @param webDriver   webDriver
     * @param testCaseName  用例名称
     * @param Log  sqllog对象
     * @param policyNameP  策略名称
     * @param projectP  项目名称
     */
    public  void init(String path,WebDriver webDriver, SqlLog Log,String testCaseName,String policyNameP,String projectP)
	{
    	folderPath=path;
		driver=webDriver;
		policyName=policyNameP;
		project=projectP;	
		sqlLog=Log;
		caseName=testCaseName;
	    screenShots= new ScreenShots();
	    screenShots.init(driver, folderPath, sqlLog, caseName);
	}
    public  void changeTestCaseName(String testCaseName)
    {
    	caseName=testCaseName;
    	screenShots.changeTestCaseName(testCaseName);
    }
    private String  getOutputStr(String pageElement ,String step) 
    {

    	 outPutinfor="测试用例："+caseName+"-步骤:"+step+"-测试:"+pageElement+"-";
    	 return outPutinfor;
	}
//    /**
//     * 得到截图路径
//     * @param pinString 是否拼接字符串，为真用于日志输出，为假得到截图文件名
//     * @return
//     */
//    private String  getSnapFileName(boolean pinString) 
//    {
//    
//    	if (pinString)
//    	{	
//    		snapShootFileName="；截图文件："+assCaseNo+"-步骤:"+step;
//    		
//        	return snapShootFileName;
//		}
//    	else {
//    		
//    		
//    		snapShootFileName= assCaseNo+"-步骤:"+step;
//        	return snapShootFileName;
//		}
//    	
//	}

//    /**
//     * 得到测试结果包含 预期及 实际
//     * @param message 测试的对象 调用时从excle中的断言句列读
//     * @return
//     */
//    private String  getRes(boolean right,Object  expected, WebElement actual) 
//    {
//    	
//    	if (right) 
//    	{
//   		
//    	testResault="-测试结果:测试成功!预期结果："+expected+"；实际结果："+DataHandle.getElementText(actual);
//    	return testResault;
//    	
//		}
//    	else {
//    	testResault="-测试结果:测试失败!预期结果："+expected+"；实际结果："+DataHandle.getElementText(actual);
//		return testResault;
//		}
//	}
    
    /**判断元素的预期结果与实际结果是否一致
     * 
       * @param genReport 报告对象
     * @param elelemnt web元素
     * @param pageElement  元素中文名
     * @param step  测试步骤
     * @param expectedObject  预期对象
     * @param actualWebElement 实际对象
     * @param snapShootFloder  截图文件绝对路径
     * @param screenWhide  屏幕宽度
     */
    public  void verifyEquals( GenReport genReport,WebElement elelemnt,String pageElement,String step,Object  expectedObject, Object actualWebElement,String snapShootFloder,int screenWhide)
    {
    	genReport.totalAsser++;
        try{
        	
   
        	 logData=this.getOutputStr(pageElement, step);
//        	 System.out.println("进入断言："+expectedObject+"--"+actualWebElement);
//        	进行断言
        	 
            Assert.assertEquals(expectedObject, actualWebElement);
//            断言成功日志输出
            sqlLog.infor("断言成功！"+logData+"是否相同；预期结果："+expectedObject.toString()+"-实际结果："+actualWebElement.toString(), caseName);
            genReport.passAsser++;
        	}
        catch(Error e)
        {
        	
        	genReport.loseAsser++;
//        	失败操作
            errors.add(e);
        	flag = false; 
//        	命名测试文件名
        	snapShootFileName=logData;
//        	snapShootFileName="/project/apache-tomcat-9.0.0.M19/webapps/autotestcloud/Screenshots/";
//			执行截图操作，获取截图路径	
        	String screenlink=	screenShots.takeScreenshots(elelemnt, snapShootFloder,snapShootFileName,screenWhide);
     
//        	遍历局部截图和详细截图
        	ArrayList<String> list=new ArrayList<String>();
        	for (String linkPath : screenlink.split(","))
        	{
        		list.add(linkPath);
    			
    		}
        	String log="断言失败！"+logData+"是否相同；预期结果："+expectedObject.toString()+"-实际结果："+actualWebElement.toString();
        	String maxlin="";
        	String minlin="";
        	try {
        		minlin=list.get(0);
        		maxlin=list.get(1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
        	
        	System.out.println(log+caseName+maxlin+minlin);
        	sqlLog.warming(log, caseName,maxlin , minlin);
		
		
        }
    }
    
    /**判断元素是否为空
     * 
     * @param genReport 报告对象
     * @param elelemnt web元素
     * @param pageElement  元素中文名
     * @param step  测试步骤
      * @param snapShootFloder  截图文件绝对路径
     * @param screenWhide  屏幕宽度
     */
    public  void verifyIsNull( GenReport genReport,WebElement elelemnt,String pageElement,String step,String snapShootFloder,int screenWhide)
    {
    	genReport.totalAsser++;
        try{
        	logData=this.getOutputStr(pageElement, step);
        	String text= elelemnt.getText();
        	boolean isTrue=false;
//        	如果获取不到文本改为真
        	if (text.equals("")) 
        	{
        		isTrue=true;
			}
        	Assert.assertTrue(isTrue);
//            断言成功日志输出
            sqlLog.infor("断言成功！"+logData+"是否为空-对象已为空", caseName);
            genReport.passAsser++;
        	}
        catch(Error e)
        {
        	
        	genReport.loseAsser++;
//        	失败操作
            errors.add(e);
        	flag = false; 
//        	命名测试文件名
        	snapShootFileName=logData;
//			执行截图操作，获取截图路径	
        	String screenlink=	screenShots.takeScreenshots(elelemnt, snapShootFloder,snapShootFileName,screenWhide);
//        	遍历局部截图和详细截图
        	ArrayList<String> list=new ArrayList<String>();
        	for (String linkPath : screenlink.split(","))
        	{
        		list.add(linkPath);
    			
    		}
        	String log="断言失败！"+logData+"是否为空-对象不为空！";
        	String maxlin="";
        	String minlin="";
        	try {
        		minlin=list.get(0);
        		maxlin=list.get(1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
        	
        	System.out.println(log+caseName+maxlin+minlin);
        	sqlLog.warming(log, caseName,maxlin , minlin);
		
		
        }
    }
    
    
    /**判断元素是否不为空
     * 
     * @param genReport 报告对象
     * @param elelemnt web元素
     * @param pageElement  元素中文名
     * @param step  测试步骤
      * @param snapShootFloder  截图文件绝对路径
     * @param screenWhide  屏幕宽度
     */
    public  void verifyIsNotNull( GenReport genReport,WebElement elelemnt,String pageElement,String step,String snapShootFloder,int screenWhide)
    {
    	genReport.totalAsser++;
        try{
        	logData=this.getOutputStr(pageElement, step);
        	String text= elelemnt.getText();
        	boolean isTrue=true;
//        	如果获取不到文本改为真
        	if (text!="") 
        	{
        		isTrue=false;
			}
        	Assert.assertFalse(isTrue);
//            断言成功日志输出
            sqlLog.infor("断言成功！"+logData+"是否不为空：对象为非空", caseName);
            genReport.passAsser++;
        	}
        catch(Error e)
        {
        	
        	genReport.loseAsser++;
//        	失败操作
            errors.add(e);
        	flag = false; 
//        	命名测试文件名
        	snapShootFileName=logData;
//			执行截图操作，获取截图路径	
        	String screenlink=	screenShots.takeScreenshots(elelemnt, snapShootFloder,snapShootFileName,screenWhide);
//        	遍历局部截图和详细截图
        	ArrayList<String> list=new ArrayList<String>();
        	for (String linkPath : screenlink.split(","))
        	{
        		list.add(linkPath);
    			
    		}
        	String log="断言失败！"+logData+"是否不为空：对象为空！";
        	String maxlin="";
        	String minlin="";
        	try {
        		minlin=list.get(0);
        		maxlin=list.get(1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
        	
        	System.out.println(log+caseName+maxlin+minlin);
        	sqlLog.warming(log, caseName,maxlin , minlin);
		
		
        }
    }
    
    
    /**判断元素是否存在
     * 
     * @param genReport 报告对象
     * @param elelemnt web元素
     * @param pageElement  元素中文名
     * @param step  测试步骤
      * @param snapShootFloder  截图文件绝对路径
     * @param screenWhide  屏幕宽度
     */
    public  void verifyIsExist( GenReport genReport,WebElement elelemnt,String pageElement,String step,String snapShootFloder,int screenWhide)
    {
    	genReport.totalAsser++;
        try{
        	logData=this.getOutputStr(pageElement, step);
        	
			
        	Assert.assertNotNull(elelemnt);
//            断言成功日志输出
            sqlLog.infor("断言成功！"+logData+"是否存在：元素被成功定位！", caseName);
            genReport.passAsser++;
        	}
        catch(Error e)
        {
        	
        	genReport.loseAsser++;
//        	失败操作
            errors.add(e);
        	flag = false; 
//        	命名测试文件名
        	snapShootFileName=logData;
//			执行截图操作，获取截图路径	
        	String screenlink=	screenShots.takeScreenshots(elelemnt, snapShootFloder,snapShootFileName,screenWhide);
//        	遍历局部截图和详细截图
        	ArrayList<String> list=new ArrayList<String>();
        	for (String linkPath : screenlink.split(","))
        	{
        		list.add(linkPath);
    			
    		}
        	String log="断言失败！"+logData+"是否存在：元素未被定位！";
        	String maxlin="";
        	String minlin="";
        	try {
        		minlin=list.get(0);
        		maxlin=list.get(1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
        	
        	System.out.println(log+caseName+maxlin+minlin);
        	sqlLog.warming(log, caseName,maxlin , minlin);		
        }
    }
    
    /**判断元素是否存在
     * 
     * @param genReport 报告对象
     * @param elelemnt web元素
     * @param pageElement  元素中文名
     * @param step  测试步骤
      * @param snapShootFloder  截图文件绝对路径
     * @param screenWhide  屏幕宽度
     */
    public  void verifyIsDisappear( GenReport genReport,WebElement elelemnt,String pageElement,String step,String snapShootFloder,int screenWhide)
    {
    	genReport.totalAsser++;
        try{
        	logData=this.getOutputStr(pageElement, step);
        	
			
        	Assert.assertNull(elelemnt);
//            断言成功日志输出
            sqlLog.infor("断言成功！"+logData+"是否消失：元素已消失！", caseName);
            genReport.passAsser++;
        	}
        catch(Error e)
        {
        	
        	genReport.loseAsser++;
//        	失败操作
            errors.add(e);
        	flag = false; 
//        	命名测试文件名
        	snapShootFileName=logData;
//			执行截图操作，获取截图路径	
        	String screenlink=	screenShots.takeScreenshots(elelemnt, snapShootFloder,snapShootFileName,screenWhide);
//        	遍历局部截图和详细截图
        	ArrayList<String> list=new ArrayList<String>();
        	for (String linkPath : screenlink.split(","))
        	{
        		list.add(linkPath);
    			
    		}
        	String log="断言失败！"+logData+"是否消失：元素还在页面上！";
        	String maxlin="";
        	String minlin="";
        	try {
        		minlin=list.get(0);
        		maxlin=list.get(1);
			} catch (Exception e2) {
				// TODO: handle exception
			}
        	
        	System.out.println(log+caseName+maxlin+minlin);
        	sqlLog.warming(log, caseName,maxlin , minlin);		
        }
    }
    /**
     * 测试2个对象是否不相同
     * @param mytestCaseNo  测试用例编号
     * @param expected   预期结果
     * @param actual   实际结果
     * @param message   打印到log信息
     * @param driver   驱动
     */
//    public  void verifyNotEquals(String caseNo, Object expectedObject, WebElement actualWebElement,String message,WebDriver driver){
//        
//        try{
//        	assCaseNo=caseNo;
//        	GenReport.modelTotalAsser++;
//
////        	进行断言
//            Assert.assertNotEquals(expectedObject, DataHandle.getElementText(actualWebElement));
////            断言成功日志输出
//     	   Log.logInfo(getOutputStr(message)+getRes(true, expectedObject, actualWebElement), GetClassMethodName());
//        	}
//        catch(Error e)
//        {
//        	GenReport.commonlyNo++;
//        	GenReport.modelAsserFail++;
////        	失败操作
//            errors.add(e);
//        	flag = false; 
//        	String snapShootName= getSnapFileName(false);
////			执行截图操作			
//			BeginTest.screenShots.takeScreenshots(actualWebElement, snapShootName);			
////			使用log写入report及log文件中：
//			Log.logWarn(getOutputStr(message)+getRes(false, expectedObject, actualWebElement), GetClassMethodName(), snapShootName);
//		
//        }
//    } 
//   
//
// /**
//  * 判断页面元素是否存在，用于判断对象出现的情况
//  * @param element 页面对象
//  * @param message 输出语句
//  * @param driver weblement
//  */
//    public  void webElementIsNotNull(String caseNo, WebElement webElement,String message,WebDriver driver){
//        try{
//        	assCaseNo=caseNo;
//        	GenReport.modelTotalAsser++;
//
////        	进行断言
//            Assert.assertNotNull(webElement);
//           
//        	Log.logInfo(getOutputStr(message)+"；测试结果:测试成功!元素已被定位！", GetClassMethodName());				
//        }catch(Error e)
//        {
//        
//        	GenReport.commonlyNo++;
//        	GenReport.modelAsserFail++;
//        	String snapShootName= getSnapFileName(false);
////			    	失败操作
//            errors.add(e);
//        	flag = false; 
////			执行截图操作			
//			BeginTest.screenShots.takeScreenshots(webElement, snapShootName);		
//         	Log.logWarn(getOutputStr(message)+"；测试结果:测试失败!元素未被定位！", GetClassMethodName(), snapShootName);		
//        }
//    }
//    /**
//     * 判断页面元素是否存在，用于判断对象销消失情况
//     * @param element 页面对象
//     * @param message 输出语句
//     * @param driver weblement
//     */
//    public  void webElementIsNull(String caseNo,WebElement webElement,String message,WebDriver driver){
//    
//            try{
//            	assCaseNo=caseNo;
//            	GenReport.modelTotalAsser++;
//
////            	进行断言
//                Assert.assertNull(webElement);
//               
//            	Log.logInfo(getOutputStr(message)+"；测试结果:测试成功!元素消失！", GetClassMethodName());				
//            }catch(Error e)
//            {
//            	GenReport.commonlyNo++;
//            	GenReport.modelAsserFail++;
////    			     	失败操作
//            	String snapShootName= getSnapFileName(false);
//                errors.add(e);
//            	flag = false; 
////            	执行截图操作			
//    			BeginTest.screenShots.takeScreenshots(webElement,snapShootName);			
////    			使用log写入report及log文件中：
//    			Log.logWarn(getOutputStr(message)+"；测试结果:测试失败!元素还在页面上！", GetClassMethodName(), snapShootName);
//            }
//
//    }  
//    
////	判断按钮是否可以点击
//	public  void buttonisDisplayed(String mytestCaseNo,WebElement element ,String message,WebDriver driver){
//		boolean isDisplayed = false;
//		try{		
//			GenReport.modelTotalAsser++;
//			 if (BeginTest.testCaseNo.equals(mytestCaseNo))
//	      	   {
////	      		   如果传入的用例编号与当前一致，增加步骤不改用例编号
////	      		   BeginTest.testCaseNo=mytestCaseNo;
//	             	step++;	
//	      	   }
//	             else {
////	          	   获得当前编号，重置步骤
//	          	   BeginTest.testCaseNo=mytestCaseNo;
//	              	step=0;
//	 			}
//			isDisplayed = element.isDisplayed();
//			Assert.assertTrue(isDisplayed);
//			Log.logInfo("测试用例："+BeginTest.testCaseNo+"步骤:"+step+"-"+message+"；测试结果:测试成功！按钮可点击！", GetClassMethodName());	
//		}catch(Error e){
//			GenReport.commonlyNo++;
//			GenReport.modelAsserFail++;
////	     	失败操作
//            errors.add(e);
//        	flag = false; 
//         	Log.logWarn(getOutputStr(message)+"；测试结果:测试失败!按钮不可点击", GetClassMethodName(), getSnapFileName(false));		
//
//		}
//		
//	}	
//	
//	public  void buttonNotDisplayed(String mytestCaseNo,WebElement element ,String message,WebDriver driver){
//		boolean isDisplayed = false;
//		try{		
//			GenReport.modelTotalAsser++;
//			 if (BeginTest.testCaseNo.equals(mytestCaseNo))
//	      	   {
////	      		   如果传入的用例编号与当前一致，增加步骤不改用例编号
////	      		   BeginTest.testCaseNo=mytestCaseNo;
//	             	step++;	
//	      	   }
//	             else {
////	          	   获得当前编号，重置步骤
//	          	   BeginTest.testCaseNo=mytestCaseNo;
//	              	step=0;
//	 			}
//			isDisplayed = element.isDisplayed();
//			Assert.assertFalse(isDisplayed);
//			Log.logInfo("测试用例："+BeginTest.testCaseNo+"步骤:"+step+"-"+message+"；测试结果:测试成功！按钮无法点击！", GetClassMethodName());	
//		}catch(Error e){
//			GenReport.commonlyNo++;
//			GenReport.modelAsserFail++;
////			  	失败操作
//	            errors.add(e);
//	        	flag = false; 
//	         	Log.logWarn(getOutputStr(message)+"；测试结果:测试失败!按钮可点击", GetClassMethodName(), getSnapFileName(false));
//		}	
//	}
////	判断按钮是否可以点击
//	public  void isTrue(String testCaseNo,Boolean object ,String message,WebDriver driver){
//		boolean isDisplayed = false;
//		try{		
//			GenReport.modelTotalAsser++;
//			 if (BeginTest.testCaseNo.equals(testCaseNo))
//	      	   {
////	      		   如果传入的用例编号与当前一致，增加步骤不改用例编号
////	      		   BeginTest.testCaseNo=mytestCaseNo;
//	             	step++;	
//	      	   }
//	             else {
////	          	   获得当前编号，重置步骤
//	          	   BeginTest.testCaseNo=testCaseNo;
//	              	step=0;
//	 			}
//			
//			Assert.assertTrue(object);
//			Log.logInfo("测试用例："+BeginTest.testCaseNo+"步骤:"+step+"-"+message+"；测试结果:测试成功！对象为真！", GetClassMethodName());	
//		}catch(Error e){
//			GenReport.commonlyNo++;
//			GenReport.modelAsserFail++;
//			 errors.add(e);
////				定义截图文件名变量
//				String screenShotsfileName;
////				创建截图对象实例
//				ScreenShots myScreenShots=new ScreenShots(driver);		
////			执行截图操作。得到截图文件名
////				screenShotsfileName="测试用例编号："+BeginTest.testCaseNo+":"+myScreenShots.takeScreenshot(BeginTest.testCaseNo+":"+step);	
////				使用log体系输出:预期结果+实际结果+文件名称
////			 ErrorLog.logError("测试用例："+BeginTest.testCaseNo+"步骤:"+step+"-"+message+"；测试结果:测试失败！对象为假！失败截图名："+screenShotsfileName, GetClassMethodName()); 
//			 flag = false;
//		}
		
//	}	
	
  
//    	@Test
    	public void t1()
    	{
    		String deviceName="Samsung Galaxy S5";
    		String  androidappActivity="cn.cooperative.activity.LoginActivity";
    		String  androidappPackage="cn.cooperative";
    		int  timeout=2;
//    		 stream.write("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。".getBytes("UTF-8"));
//    		 创建webdriver
    		 genAndroidDriver gen=new genAndroidDriver();
    	     AndroidDriver  androidDriver=gen.configAndroid("",deviceName, androidappActivity, androidappPackage, timeout);				

    	     //    		创建sqllog
    	     SqlLog sql=new SqlLog(); 
    		 sql.init("登录", "xiaoying", "Android");

    	
//    		用户名输如
    		WebElement element=	androidDriver.findElement(By.id("cn.cooperative:id/username"));
    		element.sendKeys("xuan.liu");
//    		创建断言对象
    		Assertion assertion=new Assertion();
    		assertion.init("./", androidDriver,  sql, "登录","登录综合",  "xiaoying");
    		GenReport report=new GenReport();
//    		assertion.verifyEquals(report,element, "用户名文本框", "2", "刘萱", "刘萱");
//    		assertion.verifyEquals(report,element, "用户名文本框", "2", "刘萱", "刘萱1");
    		
    	
    }
    }