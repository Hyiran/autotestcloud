package main.funtion;

import java.util.HashMap;
import java.util.Map;
import jxl.StringFormulaCell;

/**
 * 解析各页面对象定位文件 及等待时间 
 * @author Administrator
 */
public class Config  extends GetClassMethodName
{	

	public static String configFilePath;
	public static String xmlLib;
	public static String excleLib;
	public static String yamlLib;
	public static String SnapshotLib;
	public static String AppFileLib;
	
	public static String platformName;
	public static String URL;
	public static String Browser;
	//隐式等待时间  找元素默认时间
	public static int implicitlyWaitTime ;
	//显示等待时间-  用户指定时间
	public static int ElementWaitTime;
	public static String ignoreUnimportantViews;
	public static String newCommandTimeout;
	public static String plamntform;
	
//	ios
	public static String platformVersion;
	public static String IOSDeviceName;
	public static String bundleld;
	public static String calendarFormat;
	public static String locationServicesEnabled;
	public static String autoAcceptAlerts;
	public static String nativeInstrumentsLib;
	public static String Iosapp;


//android
	public static String AndroidDeviceName;
	public static String appActivity;
	public static String appPackage;
	public static String appWaitActivity;
	public static String appWaitPackage;
	public static String  Androidapp;
	public void  getConfig(HashMap<String, String> map)
	{
		
//		excle 文件路径
		excleLib="./File/dataExcles/";
//		截图文件路径
		SnapshotLib="./File/Screenshots/";	
//		测试app文件路径
		AppFileLib="./File/Screenshots/apps/";
		
//		public  公用对象
		plamntform=map.get("plamntform");	
		Browser=map.get("Browser");	
		URL=map.get("URL");
		ElementWaitTime=Integer.valueOf(map.get("ElementWaitTime"));
		implicitlyWaitTime=Integer.valueOf(map.get("implicitlyWaitTime"));
		newCommandTimeout=map.get("newCommandTimeout");
		
		
//		Android
		AndroidDeviceName=map.get("AndroidDeviceName");
		appActivity=map.get("appActivity");
		appWaitActivity=map.get("appWaitActivity");
		appPackage=map.get("appPackage");
		appWaitPackage=map.get("appWaitPackage");
		ignoreUnimportantViews=map.get("ignoreUnimportantViews");
		Androidapp=map.get("Androidapp");

//		IOS
		IOSDeviceName=map.get("IOSDeviceName");
		calendarFormat=map.get("calendarFormat");
		autoAcceptAlerts=map.get("autoAcceptAlerts");
		platformVersion=map.get("platformVersion");
		Iosapp=map.get("Iosapp");
	}


	
}