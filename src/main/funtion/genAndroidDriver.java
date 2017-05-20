package main.funtion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;




public class genAndroidDriver {


	/**
	 * 创建driver
	 * @param fileName  安装文件绝对路径
	 * @param deviceName  设备名称
	 * @param appActivity   软件主页
	 * @param appPackage  软件包
	 * @param timeout 超时时间
	 * @return
	 */
	AndroidDriver driver=null;
	public AndroidDriver  configAndroid(String fileName,String deviceName,String appActivity,String appPackage,int timeout)
	{
		DesiredCapabilities capabilities = null ;
		try {
		 capabilities =new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");	
		capabilities.setCapability("deviceName",deviceName);
		capabilities.setCapability("appActivity",appActivity);				
		capabilities.setCapability("appPackage",appPackage );
		capabilities.setCapability("appWaitActivity",appActivity);
		capabilities.setCapability("appWaitPackage",appPackage);
		capabilities.setCapability("ignoreUnimportantViews","true");	
		capabilities.setCapability("unicodeKeyboard", "true");//使用 Unicode 输入法  
		capabilities.setCapability("resetKeyboard", "true");  //重置输入法到原有状态  
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			} catch (Exception e1)
			{
				System.out.println("应用没安装");
				try {
					
					System.out.println("开始安装应用：执行命令 "+"/project/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb install "+fileName);
//					非系统命令要用绝对路径
					
					   Process p = Runtime.getRuntime().exec("/project/adt-bundle-mac-x86_64-20140702/sdk/platform-tools/adb install /project/apache-tomcat-9.0.0.M19/webapps/autotestcloud/appfile/"+fileName); 
					   InputStreamReader ir = new InputStreamReader(p.getInputStream());  
					   LineNumberReader input = new LineNumberReader (ir);  
					   String line;   
					   while ((line = input.readLine ()) != null){     //按行打印输出内容 
					   System.out.println(line);    }
						System.out.println("应用安装成功，开启启动应用");
					   driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
					   System.out.println("应用启动成功");
//						设置隐式等待
						driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
						return driver;
				} catch (IOException e2) {
					
					e1.printStackTrace();
				}
			}

		return driver;


			
	}
}
