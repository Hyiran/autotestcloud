package main.testfuntion;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class driver
{
	@Test
public void getdriver() {
	DesiredCapabilities capabilities =new DesiredCapabilities();
	capabilities.setCapability("platformName", "Android");	
	capabilities.setCapability("deviceName","Samsung Galaxy S5 ");
	capabilities.setCapability("appActivity","cn.cooperative.activity.LoginActivity");				
	capabilities.setCapability("appPackage","cn.cooperative" );
	capabilities.setCapability("appWaitActivity","cn.cooperative.activity.LoginActivity" );
	capabilities.setCapability("appWaitPackage","cn.cooperative");
	capabilities.setCapability("ignoreUnimportantViews","true");	
	capabilities.setCapability("unicodeKeyboard", "true");//使用 Unicode 输入法  
	capabilities.setCapability("resetKeyboard", "true");  //重置输入法到原有状态  
	try {
		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//
}
}
