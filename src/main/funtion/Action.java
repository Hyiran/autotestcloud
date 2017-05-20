package main.funtion;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


public class Action extends GetClassMethodName {

//  测试用例名称
   private String caseName="";
//	策略名
	private String policyName="";
//	项目名
	private String project="";
//	日志对象
	private SqlLog sqlLog=null;
//	获得横坐标长度，x轴
	public int  Width;
//	获得横坐标长度，y轴
	public int Height;
//	滑动次数
	int  Times;
WebElement EndElement;
//@Test
public void t1()
{	
	String deviceName="Samsung Galaxy S5";
	String  androidappActivity="cn.cooperative.activity.LoginActivity";
	String  androidappPackage="cn.cooperative";
	int  timeout=5;
//	 stream.write("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。".getBytes("UTF-8"));
//	 创建webdriver
	 genAndroidDriver gen=new genAndroidDriver();
   AndroidDriver  androidDriver=gen.configAndroid("",deviceName, androidappActivity, androidappPackage, timeout);				
   
   //    		创建sqllog
   SqlLog sql=new SqlLog(); 
	 sql.init("登录", "xiaoying", "Android");


//	用户名输如
	 FindWebElement findWebElement=new FindWebElement();
	 findWebElement.init(androidDriver, timeout, sql, "登录","登录综合",  "xiaoying");
//	 findWebElement.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 Action action=new Action();
	 action.init(androidDriver, sql, "登录","登录综合",  "xiaoying");
	 WebElement element=findWebElement.getElement("tt","id", "cn.cooperative:id/username");
	 action.inputString(androidDriver, element, "xuan.liu");
//	 element.sendKeys("xuan.liu");
	 element=findWebElement.getElement("tt","id",  "cn.cooperative:id/password");
	 element.sendKeys("123");
	 element=findWebElement.getElement("tt","id","cn.cooperative:id/loginbutton");
	 element.click();
//	 boolean a=findWebElement.getElementDisappear("id", "cn.cooperative:id/loginbutton");
//	 System.out.println("--"+a);
//	创建断言对象
	Assertion assertion=new Assertion();
	GenReport report=new GenReport();
	assertion.init("./", androidDriver,  sql, "登录","登录综合",  "xiaoying");
	
//	assertion.verifyEquals(report,element, "用户名文本框", "2", "刘萱", "刘萱");
//	assertion.verifyEquals(report,element, "用户名文本框", "2", "刘萱", "刘萱1");
}

	/**
	 * 构造函数初始化三个对象
	 * @param driver
	 */
	public void init(WebDriver driver,SqlLog log,String testCaseName,String testPolicyName,String testProject)
	{	
		sqlLog=log;
		caseName=testCaseName;
		policyName=testPolicyName;
		project=testProject;		
//		获得屏幕宽高
		Width=driver.manage().window().getSize().width;
		Height=driver.manage().window().getSize().height;
//		Log.logInfo("获取屏幕宽高成功,高："+Height+"宽："+Width,  GetClassMethodName());
	}
	public void changeTestCaseName(String testCaseName)
	{
		caseName=testCaseName;
	}
	/**
	 * 移动端-页面对象-单点
	 * @param element  Webelement
	 * @param  Millisecond 持续时间
	 * @return 是否退出当前用例执行，false 退出
	 */
	public boolean tapWithSingelFinger(AppiumDriver driver,WebElement element , int Millisecond)
	{	
		if (element==null) 
		{
			System.out.println("元素没找到进入");
//			sqlLog.infor("对象没找到", caseName);
			sqlLog.serious("对象为空无法点击！", caseName);
			return false;
		}
		else {
			driver.tap(1, element, Millisecond);
//			PageFuntion.exceptionLogOutAndroid();
			sqlLog.infor("对象被点击", caseName);
			return true;
			
			}
	}
	
	/**移动端-页面对象-多手指点
	 * @param figNo 手指数
	 * @param x x坐标
	 * @param y y坐标
	 * @param Millisecond 持续时间
	 */
		public void tapWithManyFin(AppiumDriver driver,int figNo,int x ,int y ,int Millisecond)
		{	
		
			
			driver.tap(figNo, x, y, Millisecond);
			sqlLog.infor("坐标被点击", caseName);
				
		}
		/**移动端-一手指-点击坐标
		 * 
		 * @param x x坐标
		 * @param y y坐标
		 * @param Millisecond 持续时间
		 */
			public void tapWithSingelFinger(AppiumDriver driver ,int x,int y ,int Millisecond)
			{	
				driver.tap(1, x, y, Millisecond);
		
			}
			/**移动端-一手指-点击坐标
			 * 
			 * @param x x坐标
			 * @param y y坐标
			 * @param Millisecond 持续时间
			 */
				public boolean inputString(WebDriver driver ,WebElement element,String inputData)
				{	
					if (element==null) 
					{
						
						sqlLog.serious("Action.inputString："+element.toString()+"对象为空无法输入！", caseName);
						return false;
					}
					else 
					{
						element.click();
						element.sendKeys(inputData);
						sqlLog.infor("输入内容:"+inputData, caseName);
						return true;
					}
						
				}
			
	/**
	 *清除文本框内容，重新输入
	 * @param element 元素对象
	 * 
	 */
	public void clearInputTextbox(AppiumDriver driver, WebElement element ){
//		点击进入文本框
//		tapWithSingelFinger(driver, element, 500)
//
////		获得当前文本长度
//		int size = element.getText().length();
//		Log.logInfo("文本框当前字符长度为："+size, classMethodeName);
////		光标定位到最后一个字符
//		androidDriver.pressKeyCode(KeyEvent.TOLASTCHAR);
//		for(int i =0;i<size;i++){
////			依次退格直到完全清除
//			androidDriver.pressKeyCode(KeyEvent.DEL);
//		}
//		   
		
	}

	/**
	 * 键盘事件（安卓）
	 * @param key
	 */
//	public void  pressKeyCode(int key)
//	{	
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		androidDriver.pressKeyCode(key);
//	}
///**
// * 将 app 置于后台
// */
//	public void Background()
//	{	
////		driver.runAppInBackground(5);
//	}
//	/**
//	 *收起键盘
//	 */
//	public void hideKeyboard()
//		{	
////			driver.hideKeyboard();
//		}
//	/**
//	 *在当前应用中打开一个 activity 或者启动一个新应用并打开一个 activity 。 只能在 Android 上使用
//	*@param  appWaitPackage 如果不用 就传null
//		*/
//	public void startActivity(String appPackage,String appActivity,String appWaitPackage,String appWaitActivity)
//			{	
//			if (appWaitActivity !=null) 
//			{
//				androidDriver.startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity);
//			}
//				
//			else {
//				androidDriver.startActivity(appPackage, appActivity);
//			}
//			}	
//			/**
//	 *打开通知栏 (Notifications)
//			 */
//	public void openNotifications()
//				{	
//					androidDriver.openNotifications();
//				}
//	/**
//	 *检查应用是否已经安装
//	 *@param 包名
//	 */
//	public boolean isAppInstalled(String packageName)
//		{
//			try {
//				boolean AppInstalled =androidDriver.isAppInstalled(packageName);
//				return AppInstalled;
//			} catch (Exception e) 
//			{
//				Log.logDeadly("无法判断应用是否安装:"+e.toString(), GetClassMethodName());
//				// TODO: handle exception
//			}
//	return false;
//		}
//				/**
//				 *安装应用到设备中去
//				 *@param appPath 应用的绝对路径
//				 *@param packageName  应用包名
//				 *@return 是否安装成功
//				 */
//	public boolean installApp(String appPath,String packageName)
//	{	
//		try {
//			androidDriver.installApp(appPath);
//			boolean AppInstalled =androidDriver.isAppInstalled(packageName);
//			return AppInstalled;
//		} catch (Exception e) 
//		{
//			Log.logDeadly("无法判断应用是否安装:"+e.toString(), GetClassMethodName());
//			// TODO: handle exception
//		}
//	return false;
//		}
//	/**
//	*卸载应用
//	 *@param packageName  应用包名
//	 *
//	 */
//	public void removeApp(String packageName)
//	{	
//	androidDriver.removeApp(packageName);				
//	}
//	/**
//	 *摇晃 只试用ios
//	 *@param packageName  应用包名
//	 *
//	 */
//	public void shake(String packageName)
//	{	
//	iosDriver.shake();				
//	}		
//	/**
//	 *关闭app
//	 *
//	 *
//	*/
//	public void closedApp()
//	{	
//	androidDriver.closeApp();
//	}	
//		
//	/**启动 (Launch)
//	 * 根据服务关键字 (desired capabilities) 启动会话 (session) 。
//	 * 请注意这必须在设定 autoLaunch=false 关键字时才能生效。这不是用于启动指定的 app/activities ————你可以使用 start_activity 做到这个效果————这是用来继续进行使用了 autoLaunch=false 关键字时的初始化 (Launch) 流程的。
//	 */
//	public void launchApp()
//	{	
//		androidDriver.launchApp();
//	}	
//	/**
//	 *列出所有的可用上下文
//	 *翻译备注：context可以理解为 可进入的窗口 。例如，对于原生应用，可用的context和默认context均为NATIVE_APP
//	*/
//
//	public void getContextHandles()
//	{	
//		androidDriver.getContextHandles();
//	}				
//	
//
//	
//	}
//	/**封装sendkey对象
//	 * @param element  Webelement
//	 * @param  string 输入的字符串
//	 */
//	public void sendKey(WebElement element,String string){	
//		if (element!=null)
//		{
//			element.sendKeys(string);
////			BeginTest.assertion.verifyEquals("向文本框输入字符串",string, element, "测试文本框数据是否输入成功", androidDriver);
//		}
//		else 
//		{
//			Log.logWarn("WebElement:"+element.toString()+"未被定位无法输入字符串！",GetClassMethodName(),"");
//		}
//	}
//	
//	
//		
//	/**
//	 * 页面上下滑动
//	 * @param endBy 最终要得到的对象
//	 * @param times 滑动次数
//	 * @param direction =滑动方向
//	 * @throws InterruptedException 
//	 * 
//	 */
//
//		
//	public WebElement  swipe(By by,String  direction,int times ,boolean swipeAgain) {
//		Boolean findElement=false;	
//		Times=times;
//		try {
//			 	EndElement=driver.findElement(by);		
//			 	findElement=true;
//			 	Log.logInfo("action.swip:数据默认存在,无需滑动",GetClassMethodName()); 	
//			 	return EndElement;			
//		} catch (Exception e) 
//		{
//			Log.logInfo("默认找不到数据，即将开始滑动", GetClassMethodName());
//		}
//					
//				 if(direction=="up")
//					{
//					 Log.logInfo("页面开始向上滑动",GetClassMethodName());
////						滑动页面,向上滑动，根据动times次
//						for(int i=0;i<=Times;i++)
//						{
//							try {
////								向上滑动每次划区屏幕长度的10%，最多滑动i次
//									androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
//									androidDriver.swipe((int)(0.5*Width), (int)(0.6*Height), (int)0.5*Width, (int)(0.4*Height), 1500);
//								    EndElement=driver.findElement(by);	
////									}
//								    findElement=true;
//									Log.logInfo("第："+i+" 次滑动已找到元素对象！",GetClassMethodName());
//									if (swipeAgain)
//								 	{
//										androidDriver.swipe((int)(0.5*Width), (int)(0.6*Height), (int)0.5*Width, (int)(0.4*Height), 1500);	
//									 EndElement=driver.findElement(by);	
//										Log.logInfo("swipe多滑动一次",GetClassMethodName());
//									}
//									driver.manage().timeouts().implicitlyWait(Config.implicitlyWaitTime, TimeUnit.SECONDS);				
//																
//									break;					
//								   						
//							} catch (Exception e)
//							{
//								System.out.println("第："+i+" 次滑动没有找到元素对象！");
//								Log.logInfo("第："+i+" 次滑动没有找到元素对象！", GetClassMethodName());
//							}
//						
//						}
//						return EndElement;
//					}
//					else if(direction=="down")
//					{
//						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
////						向下滑动每次划区屏幕长度的10%，最多滑动20次
//						for(int i=0;i<=Times;i++)
//						{
//							try {
////								向上滑动每次划区屏幕长度的10%，最多滑动20次
//								
//								androidDriver.swipe((int)(0.5*Width), (int)(0.3*Height), (int)0.5*Width, (int)(0.5*Height), 1500);					
//								EndElement=driver.findElement(by);
//								Log.logInfo("第："+i+" 次滑动已找到元素对象！",GetClassMethodName());
//								findElement=true;
//								driver.manage().timeouts().implicitlyWait(Config.implicitlyWaitTime, TimeUnit.SECONDS);
//								break;
//							} catch (Exception e) {
//								Log.logInfo("第："+i+" 次滑动没有找到元素对象！",GetClassMethodName());
//										
//							}
//						
//						}
//						return EndElement;
//						
//					}
//					else if(direction=="left")
//					{
//						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
////						向下滑动每次划区屏幕长度的10%，最多滑动20次
//						for(int i=0;i<=Times;i++)
//						{
//							try {
////								向左动每次划区屏幕宽度的40%，最多滑动20次
////								driver.swipe(startx, starty, endx, endy, duration);
//								androidDriver.swipe((int) (0.8*Width), (int)(0.5*Height),(int) (0.05*Width), (int)(0.5*Height),1000);		
//								EndElement=driver.findElement(by);
//								Log.logInfo("第："+i+" 次滑动已找到元素对象！",GetClassMethodName());
//								findElement=true;
//								driver.manage().timeouts().implicitlyWait(Config.implicitlyWaitTime, TimeUnit.SECONDS);
//								break;
//							} catch (Exception e) {
//								Log.logInfo("第："+i+" 次滑动没有找到元素对象！",GetClassMethodName());
//											
//							}
//						
//						}
//						return EndElement;
//					}
//					else if(direction=="right")
//					{
//						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
////						向下滑动每次划区屏幕长度的10%，最多滑动20次
//						for(int i=0;i<=Times;i++)
//						{
//							try {
////								向左动每次划区屏幕宽度的40%，最多滑动20次
//								androidDriver.swipe((int) (0.05*Width), (int)(0.5*Height),(int) (0.8*Width), (int)(0.5*Height), 1000);		
//								EndElement=driver.findElement(by);
//								Log.logInfo("第："+i+" 次滑动已找到元素对象！",GetClassMethodName());
//								findElement=true;
//								driver.manage().timeouts().implicitlyWait(Config.implicitlyWaitTime, TimeUnit.SECONDS);
//								break;
//							} catch (Exception e) {
//								Log.logInfo("第："+i+" 次滑动没有找到元素对象！",GetClassMethodName());
//								i++;				
//							}
//						
//						}
//						return EndElement;
//					}
//					else 
//					{
//						driver.manage().timeouts().implicitlyWait(Config.implicitlyWaitTime, TimeUnit.SECONDS);
//						Log.logError("Class:Action-Method:swipe:滑动方向传入错误 请检查！",GetClassMethodName());
//						return EndElement;
//					}
//		
//	}
//	
// public void swipByTimes(int times,String to)
// {
//	 if (to.equals("up"))
//	 {
//		 for (int i = 0; i < times; i++) 
//		 {
//			 androidDriver.swipe(Width/2, Height/2, Width/2, Height/2-300, 1000);
//		}
//	
//	}
//	 if (to.equals("down"))
//	 {
//		 for (int i = 0; i < times; i++) 
//		 {
//			 androidDriver.swipe(Width/2, Height/2, Width/2, Height/2+300, 1000);
//		}
//	} else
//	{
//Log.logError(" 滑动方向错误！", GetClassMethodName());
//	}
// }
}
	

	
	
