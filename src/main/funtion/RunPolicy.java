package main.funtion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import main.zson.ZSON;
import main.zson.result.ZsonResult;

public class RunPolicy 
{
	public boolean runAction(AppiumDriver driver,Action action,String actionName,String par,WebElement element)
	{
//		System.out.println("操作名称："+actionName);
		System.out.println("参数："+par);
	    ZsonResult zr1 = ZSON.parseJson(par);
//	    String aString2="{ \"sendkeystring\": \"xuan.liu\"}";
		boolean run=true;
		if (actionName.equals("点击-元素")) 
		{
			try {
				String time=(String) zr1.getValue("/time");
				System.out.println("点击时间："+time);
				int timeInt=DataHandle.getInt(time);
				run =	action.tapWithSingelFinger(driver, element, timeInt);
			} catch (Exception e) {
				// TODO: handle exception
				run=false;
			}
			
			return run;
		}
		else if ( actionName.equals("点击-坐标")) 
		{
			try {
				String x=(String) zr1.getValue("/x");
				int xInt=DataHandle.getInt(x);
				String y=(String) zr1.getValue("/y");
				int yInt=DataHandle.getInt(y);
				String time=(String) zr1.getValue("/time");
				int timeInt=DataHandle.getInt(time);
				action.tapWithSingelFinger(driver, xInt, yInt, timeInt);
			} catch (Exception e) {
				// TODO: handle exception
				run=false;
			}
		
			return run;
		}
		else if (actionName.equals("输入")) 
		{
			try {
				String sendkeystring=(String) zr1.getValue("/sendkeystring");
				System.out.println("输入内容："+sendkeystring);
				run =action.inputString(driver, element, sendkeystring);
				
			} catch (Exception e) {
				// TODO: handle exception
				run=false;
			}
			
			return run;
		}
		else {
			return true;
		}
		
	}
	public void runAssert(GenReport genReport,AppiumDriver driver,Assertion assertion,String asserType,String expet,WebElement element,String elementName,String step,String snapShootFloder,int screenWhide)
	{
		if (asserType.equals("是否相同"))
		{
			String actual="";
			try {
				 actual=element.getText();
				 
				} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
//			System.out.println("开始断言");
			assertion.verifyEquals(genReport,element, elementName, step, expet, actual,snapShootFloder,screenWhide);
//			System.out.println("断言结束");
		}
		else if (asserType.equals("是否为空")) 
		{
			assertion.verifyIsNull(genReport, element, elementName, step, snapShootFloder, screenWhide);
		}
		else if (asserType.equals("是否不为空")) 
		{
			assertion.verifyIsNotNull(genReport, element, elementName, step, snapShootFloder, screenWhide);
		}
		else if (asserType.equals("是否存在")) 
		{
			assertion.verifyIsExist(genReport, element, elementName, step, snapShootFloder, screenWhide);
		}
		else if (asserType.equals("是否消失")) 
		{
			assertion.verifyIsDisappear(genReport, element, elementName, step, snapShootFloder, screenWhide);
		}
		
		else {
			
		}
	}
	
}
