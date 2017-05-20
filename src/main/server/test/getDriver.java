package main.server.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class getDriver {
	public static  AndroidDriver driver=null;
	public static  DesiredCapabilities capabilities=null ;
@Test
public void t1()
{
	capabilities = new DesiredCapabilities();
	 if (true) 
	 { 
//			创建adnroiddriver
				try {		
//					初始化并创建adnroid对象		
					capabilities.setCapability("platformName", "Android");	
					capabilities.setCapability("deviceName","Samsung Galaxy S5 ");
					capabilities.setCapability("ignoreUnimportantViews","true");	
					capabilities.setCapability("unicodeKeyboard", "true");//使用 Unicode 输入法  
					capabilities.setCapability("resetKeyboard", "true");  //重置输入法到原有状态  
					capabilities.setCapability("appActivity","cn.cooperative.activity.LoginActivity");				
					capabilities.setCapability("appPackage","cn.cooperative" );
					capabilities.setCapability("appWaitActivity","cn.cooperative.activity.LoginActivity" );
					capabilities.setCapability("appWaitPackage","cn.cooperative");
//					capabilities.setCapability("appActivity","com.android.calculator2.Calculator");				
//					capabilities.setCapability("appPackage","com.android.calculator2" );
//					capabilities.setCapability("appWaitActivity","com.android.calculator2.Calculator" );
//					capabilities.setCapability("appWaitPackage","com.android.calculator2");
//				
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
				
					} 
				catch (Exception e) 
				{
					try {
						
						System.out.print("开始安装应用");
//						非系统命令要用绝对路径
						   Process p = Runtime.getRuntime().exec("/project/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb install /Users/fujiaxi/Downloads/xy.apk"); 
						   InputStreamReader ir = new InputStreamReader(p.getInputStream());  
						   LineNumberReader input = new LineNumberReader (ir);  
						   String line;   
						   while ((line = input.readLine ()) != null){     //按行打印输出内容 
						   System.out.println(line);    }
							System.out.print("应用安装成功，开启启动应用");
						   driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
						   System.out.print("应用启动成功");
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				
					
				}

//				try {
//					System.out.println("安装1！");
//					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
//					System.out.println("安装2！");
////					判断当前应用是否安装
//					Boolean isInstall=driver.isAppInstalled("cn.cooperative");
////					如果没安装则安装应用
//						if (isInstall.equals(false) ) 
//						{
//							
//							try {
//								driver.installApp("/Users/fujiaxi/Downloads/xy.apk");
//							
//								capabilities.setCapability("appActivity","cn.cooperative.activity.LoginActivity");				
//								capabilities.setCapability("appPackage","cn.cooperative" );
//								capabilities.setCapability("appWaitActivity","cn.cooperative.activity.LoginActivity" );
//								capabilities.setCapability("appWaitPackage","cn.cooperative");
//								driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
//								} catch (Exception e) 
//								{
//							
//								}
//		
//						}
//						
//						else 
//						{
//							System.out.println("已经安装过了无需安装！");
//						}
//				} catch (MalformedURLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}


	 }
	}
}
