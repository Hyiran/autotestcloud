package main.funtion;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;



public class FindWebElement  extends GetClassMethodName

{
	private String caseName="";
//	private  ConnectMySQL mysql;
	private String policyName="";
	private String project="";
//	private String platformName="";
	private WebDriver driver =null ;

	private String WebObject="";
//	元素等待时间
	private int timeout =20 ;

	private SqlLog sqlLog;
//	public static boolean needFound=true;
	//构造函数参数,初始化webdriver对象
/**
 * 初始化webdriver对象
 * @param driver
 */
	public  void init(WebDriver webDriver,int time, SqlLog Log,String testCaseName,String policyNameP,String projectP)
	{
		
		driver=webDriver;
		policyName=policyNameP;
		project=projectP;
//		platformName=platformNameP;
		timeout=time;
//		mysql=connectMySQL;
		sqlLog=Log;
		caseName=testCaseName;
//		WebObject=webElement;
	}
//	修改值
	public  void changCaseName(String testCaseName)
	{
	
		caseName=testCaseName;
	}
/**
 * 对by对象进行封装
 * @param ByType 定位方式（xpath /id等）
 * @param locatString  定位器字符串
 * @return by对象
 */
private By getBy(String ByType,String locatString)
{

	By by =null;
	
	if (ByType.equals("id"))
		{
	//	如果type的值是id  就将by封装成by。id
		by=By.id(locatString);
		}
//	如果type的值是xpath  就将by封装成by。xpath
	else if (ByType.equals("xpath"))
		{
			by=By.xpath(locatString);
		}
	else
// 如果都不是报错
		{
		
		sqlLog.serious("页面元素:"+WebObject+"-传入无效的定位方式："+ByType+"，请检查", caseName);
			by=null;
		}
//	返回封装好的by对象
	return by;
}


/**
 * 等待页面元素出现
 * @param by
 * @return webelement
 */
public WebElement waitForElement(final By by)
	{
	WebElement element=null;
		try  {
			element = new WebDriverWait(driver, timeout).until(new ExpectedCondition<WebElement>()
			
				 { 
					public WebElement apply(WebDriver d)
						{
						return d.findElement(by);
						}
				 }	);
			}		
		catch (Exception e)
		
		{
//				System.out.println(e.toString());
				sqlLog.serious("页面元素:"+WebObject+"-无法被定位到!超时时间:"+timeout, caseName);

	
		}
		return element;
	}

/**
 * 判断页面元素是否可用
 * @param element 页面元素
 * @return  
 */
private boolean waitForElementToDisplayed(final WebElement element)
{
	boolean wait=false;
	if (element==null)
	return wait;
		try  {
			wait = new WebDriverWait(driver,timeout)
			.until(new ExpectedCondition<Boolean>()
				{
					public Boolean apply(WebDriver d)
					{					
						return element.isDisplayed();
					}
				
				});
			}
		catch (Exception e)
		{
			sqlLog.serious("页面元素:"+WebObject+"-当前不可用！", caseName);
		}
		return wait;
	}

		

//判断页面元素是否消失
  public boolean getElementDisappear(final WebElement element)
  {
		boolean wait=false;
		if (element==null)
		return wait;
	  try  {
			wait = new WebDriverWait(driver,timeout)
			.until(new ExpectedCondition<Boolean>()
				{
					public Boolean apply(WebDriver d)
					{						
						return !element.isDisplayed();
					}
				
				});
			}
		catch (Exception e)
		{
			
		}
return wait;

  }
 
//获取页面元素
  public WebElement getElement(String webElementName,String locatType,String locatString )
  {
		return this.getLocator(webElementName,locatType, locatString);
  }




//获得webelements
public  List<WebElement> getElemens(String webElementName, String locatType,String locatString)
{
	return this.getLocators(webElementName ,locatType, locatString);
}


/**
 * 定位元素 
 * @param e  excle对象（testbase。PageElmentExcle）
 * @param wait 是否等
 * @param ByTypeColumn  定位方式
 * @param locatStringColumn 定位器
 * @return
 */
private WebElement getLocator( String webElementName, String locatType,String locatString)
{
	WebObject=	webElementName;
	WebElement element =null;

		if (locatType !="" && locatString!="")
		{
			try {
				
//				找到元素

					sqlLog.infor("页面元素："+WebObject+"-定位方式："+locatType+";定位器："+locatString, caseName);
//					获取by 对象
					By by=this.getBy(locatType, locatString);	
					
//						找到页面元素
						element =this.waitForElement(by);
//						如果找到的页面元素是不可用的，返回null；
						boolean falg= this.waitForElementToDisplayed(element);
							if(!falg)
								element=null;
							if (element.equals(null)) 
							{
								sqlLog.infor("页面元素："+WebObject+"-被成功定位到，但是不可用", caseName);
							}
							else 
							{
//								sqlLog.infor("页面元素："+WebObject+"-被成功定位到，可用", caseName);
							}
				
			
			}
			catch (Exception e)
			{
//				sqlLog.serious("页面元素："+WebObject+"-无法被定位，超时时间："+timeout, caseName);
				
			}
		}
		else 
		{
			sqlLog.serious("页面元素："+WebObject+"：定位器或定位方式没有传入参数，请检查", caseName);
		
		}
		
	return element;
}
/**
 * 
 * @param key 定位器名称
 * @param a  替换的参数1
 * @param b  替换的参数2
 * @return
 */
private List<WebElement>  getLocators(String webElementName,String locatType,String locatString) 
{
	WebObject =webElementName;
	List<WebElement> elements =null;
	

	
		By by=this.getBy(locatType, locatString);	
		try {
			elements =driver.findElements(by);
			sqlLog.infor("页面元素结合："+WebObject+"-被成功定位到", caseName);
			} 
		catch (Exception e3) 
			{
			sqlLog.serious("页面元素列表："+WebObject+"-无法用定位方式:"+locatType+"-定位器："+locatString+"-定位到！，请检查定位器", caseName);
				
				return null;
			}			
	return elements;

}


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
	 findWebElement.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 WebElement element=findWebElement.getElement("用户名文本框","id", "cn.cooperative:id/username");
	 element.sendKeys("xuan.liu");
	 element=findWebElement.getElement("用户密码","id",  "cn.cooperative:id/password");
	 element.sendKeys("123");
	 element=findWebElement.getElement("提交","id","cn.cooperative:id/loginbutton");
	 element.click();
//	 boolean a=findWebElement.getElementDisappear("id", "cn.cooperative:id/loginbutton");
//		System.out.println("--"+a);
//	创建断言对象
	Assertion assertion=new Assertion();
	assertion.init("./", androidDriver,  sql, "登录","登录综合",  "xiaoying");
	GenReport report=new GenReport();
//	assertion.verifyEquals(report,element, "用户名文本框", "2", "刘萱", "刘萱");
//	assertion.verifyEquals(report,element, "用户名文本框", "2", "刘萱", "刘萱1");
}


}