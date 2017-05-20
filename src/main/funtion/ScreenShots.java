package main.funtion;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;


/**
 * 创建页面截图类
 * @author Administrator
 *
 */
public class ScreenShots extends GetClassMethodName
{

//	创建webdriver的对象
	private WebDriver webDriver;
//	截图后剪裁
	OperateImage  oi=null;
//	截图存储位置
	String screenShotsPath="";
//	存储数据库对象
	private   ConnectMySQL mysql;
//	存储数据库对象
	private   SqlLog sqlLog=null;
//	存储数据库对象
	private   String caseName="";
//	构造函数 初始化webdriver
	public void init( WebDriver driver,String FilePath,SqlLog log,String testCaseName )
		{
		 sqlLog=log;	
		 screenShotsPath=FilePath;
		 webDriver=driver;
		 oi=new OperateImage();
		 caseName=testCaseName;
//		
		
		}
	  public  void   changeTestCaseName(String testCaseName)
	    {
	    	caseName=testCaseName;
	    }
	/**
	 * 
	 * @param element  页面元素
	 * @param screenFileName  截图名称
	 */
//	被调用截图方法
	public  String takeScreenshots (WebElement element,String filePath,String screenFileName,int screenWhide) 
	{
		
		try{
//			
//			File dir=new File(filePath);
//			if(!dir.exists())
//			{
//				dir.mkdirs();			
//			}
		
			screenShotsPath=filePath+"/"+screenFileName+".png";
			String tempPath=screenShotsPath;
//			sqlLog.infor("生成截图文件绝对路径："+screenShotsPath, caseName);
		
			File screenshotFile =((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(screenShotsPath));
//			采用相对路径存储文件地址
			int a=screenShotsPath.lastIndexOf("webapps/");
			a=a+8;
			screenShotsPath="http://127.0.0.1:8081/"+screenShotsPath.substring(a, screenShotsPath.length());
			
			if (element!=null) 
			{	
				
				oi.cropImage(tempPath,tempPath.replace(".png", "Cut.png"), 0,element.getLocation().getY() , screenWhide, 100,"png","png");//剪切图片
//				sqlLog.infor("截图裁剪完毕", caseName);
//				追加截图文件的地址
				screenShotsPath=screenShotsPath+","+screenShotsPath.replace(".png", "Cut.png");
		
			}
			else {
				sqlLog.infor("元素未被定位，无法精确截图", caseName);
				  }
			return screenShotsPath;
		}
		catch(Exception e)
		{	
			sqlLog.serious("截图发生异常！"+e.toString(), caseName);
	
		return "";
		}
	}
//	屏幕截图方法å
	/*/
	public String takeScreenshot(String CaseNo)
		{
//		创建日期格式对象
		SimpleDateFormat  dateformat=new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
//		新增字符串变量，内容是已日期格式.png
		String screenName=String.valueOf(CaseNo+":"+dateformat.format(new Date().getTime()))+".png";
//		创建一个目录用于存放截图文件，如果目录没有就创建
		File dir=new File(Config.SnapshotLib);
		if(!dir.exists())
		{
			dir.mkdirs();
		}
//		定义一个字符串变量，用于返回截图文件的绝对路径文件名，给日志方法调用
		String screenPath=dir.getAbsolutePath()+"/"+screenName;
//		执行截图方法
//		this.takeScreenshots(screenPath);
//		返回文件名
		return screenName;
		}

*/
	
//	@Test
	public void t1()
	{
		String deviceName="Samsung Galaxy S5";
		String  androidappActivity="cn.cooperative.activity.LoginActivity";
		String  androidappPackage="cn.cooperative";
		int  timeout=10;
//		 stream.write("测试运行中，请稍后，运行完毕后会发送测试报告到您邮件中，请查收。".getBytes("UTF-8"));
		 
		 genAndroidDriver gen=new genAndroidDriver();
	     AndroidDriver  androidDriver=gen.configAndroid("",deviceName, androidappActivity, androidappPackage, timeout);				
		 SqlLog sql=new SqlLog(); 
		 sql.init("登录", "xiaoying", "Android");
		ScreenShots screenShots=new ScreenShots();
		screenShots.init(androidDriver, "./", sql, "登录");
		WebElement element=	androidDriver.findElement(By.id("cn.cooperative:id/username"));
		element.sendKeys("xuan.liu");
//		String string=screenShots.takeScreenshots(element, "截图文件1");
//		System.out.println(string);
		
	}

}
