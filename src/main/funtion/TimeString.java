package main.funtion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;


public class TimeString  extends GetClassMethodName
{
	private static String valueOfString(String str,int len)
	{
		String  string ="";
		for (int i =0;i<len-str.length();i++)
		{
			string = string + "0";
		}
		return (str.length() ==len)?(str):(string + str);
	}

	/**
	 * 返回当前时间：格式为：YYYY-MM-DD  HH:MM:SS
	 * @return
	 */
	public static String  getyMDHMS()
	{
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		return df.format(new Date());
	}
	
	/**
	 * 返回当前时间：格式为：yyyy/MM/dd
	 * @return
	 */
	public static String  getYMD()
	{
		SimpleDateFormat df= new SimpleDateFormat("yyyy/MM/dd");
		return df.format(new Date());
	}
	/**
	 * 返回当前时间：格式为：yyyyMMdd
	 * @return
	 */
	public static String  getYMD2()
	{
		SimpleDateFormat df= new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	/**
	 * 返回当前时间：格式为：yyyy-MM-dd
	 * @return
	 */
	public static String  getY_M_D()
	{
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	public  static String getTime()
	{
		return String.valueOf(new Date().getTime());
	}
//	获得时间差
	
	public static String  timeMinus(String endTime,String startTime) 
	{
	try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		   java.util.Date now = df.parse(endTime);   
		   java.util.Date date=df.parse(startTime);   
		   long l=now.getTime()-date.getTime();   
		   long day=l/(24*60*60*1000);   
		   long hour=(l/(60*60*1000)-day*24);   
		   long min=((l/(60*1000))-day*24*60-hour*60);   
		   long s=(l/1000-day*24*60*60-hour*60*60-min*60);   
		   Long.toString(day);
		   Long.toString(hour);
		   Long.toString(min);
		   Long.toString(s);   
//		   System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		  String minnus=day+"天"+hour+"小时"+min+"分"+s+"秒";
		   return minnus;
	} catch (Exception e)
	{	
//		Log.logWarn("无法做时间减法，原因:"+endTime.toString(), GetClassMethodName(), "");
		return "";
	}
		
//		   System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒"); 
	  
	    
	}
	@Test
	public void  y()
	{
//	String aString=	timeMinus("2017-04-17 21:50:29", "2017-04-16 21:50:29");
//	System.out.println("时间减法："+aString);
	
	
//	String b=	getYMD2();
//	System.out.println("chaizhi:"+a);
//	System.out.println("date:"+b.substring(0, 10));
//	System.out.println("year:"+b.substring(0, 4));
//	System.out.println("month:"+b.substring(5, 7));
	}
}
