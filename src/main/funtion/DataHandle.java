package main.funtion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;

/**
 * 生成随机数
 * @author Administrator
 *
 */
public class DataHandle extends GetClassMethodName
{     
//	返回一个0-count的随机数
		public int  getRadom(int count)
		{
			return (int)Math.round(Math.random()*(count));
			
		}
		private String string ="1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
//		
		private String Number="1234567890";
		
		public String getRadomString(int length)
		{
			StringBuffer sb= new StringBuffer();
			int len =string.length();
			for(int i=0;i<length;i++)
			{
				sb.append(string.charAt(this.getRadom(len-1)));
			}
			return  sb.toString();
		}
		public long getRadomNumber(int length)
		{
			StringBuffer sb= new StringBuffer();
			int len =Number.length();
			for(int i=0;i<length;i++)
			{
				sb.append(string.charAt(this.getRadom(len-1)));
			}
			return  Long.parseLong(sb.toString());
		}
//		长整型转整形
		public  static int getInt(long long1)
		{
			int i=(int) long1;
		   return	i;
		}
		
//		长整型转整形
		public  static int getInt(double double1)
		{
			int i=(int) double1;
		   return	i;
		}
//		字符串转换整形
		public  static int getInt(String string)
		{
			int i=Integer.parseInt(string);
		   return	i;
		}
//		整形转化字符型
		public  static String getString(int i)
		{
			String s=String.valueOf(i);
			return s;
		}
//		双精度转化字符串
		public  static String getString(Double i)
		{
			String s=String.valueOf(i);
			return s;
		}
//		字符串转化双精度
		public  static double getDouble(String string)
		{
			double s=Double.parseDouble(string);
			return s;
		}
//		双精度按精确位数转化字符串
		/**
		 * 
		 * @param digit  精确位数
		 * @param d     精度数据
		 * @return
		 */
		public  static String getStringDigit(int digit,Double d)
		{
			String s;
			 DecimalFormat df=null;
			 switch (digit) {
			case 1:
				df = new DecimalFormat("#.0");
				break;
			case 2:
				df = new DecimalFormat("#.00");
				break;
			case 3:
				df = new DecimalFormat("#.000");
				break;
			case 4:
				df = new DecimalFormat("#.0000");
				break;
			case 5:
				df = new DecimalFormat("#.00000");
				break;
			case 6:
				df = new DecimalFormat("#.000000");
				break;
			default:
//				Log.logError("传入的double类型精确位数不合法，请检查！",GetClassMethodName());
				break;
			}
			 s=df.format(d);
			 return s;
		}

		public static Map<String, String>	getMergeMapData(Map<String, String> map1,Map<String,String> map2)
		{
		Iterator<String> it = map2.keySet().iterator();
		while(it.hasNext())
			{
				String key =it.next();
				String value=map2.get(key);
				if(!map1.containsKey(key))
				{
					map1.put(key, value);
				}
			}
		return map1;
		}
		 /**
		  * 文本框填入值
		  * @param element  页面对象
		  * @param Key  传入的值
		  */
			public static void elementSedKey(WebElement element,String Key)
			{
			if (element.equals(null)) {
//				Log.logError(element.toString()+"没有定位到无法填入值！",GetClassMethodName());
			}
			else {
				element.sendKeys(Key);
				}
			}
		/**
		 * 传入参数获得 by 字符串 用于滑动屏幕所要定位的元素
		 * @param keyWord  用那种定位方式
		 * @param replace  替换参数1
		 * @param replace2  替换参数2
		 * @return  
		 */
		public static String getBy(String keyWord,String replace,String replace2 )
		{
			if(true)
			{
				String by="//android.widget.TextView[starts-with(@text,'"+replace+"')]";
				return by;
			}
////			else if (keyWord.equals(Location.TextView_Ptext_Pindex))
//			{
//				String by="//android.widget.TextView[starts-with(@text,'"+replace+"')]["+replace2+"]";
//				return by;
//			} 
////			else if (keyWord.equals(Location.Button_Ptext))
//			{
//				String by="//android.widget.Button[starts-with(@text,'"+replace+"')]";
//				return by;
//			} 
//			
////			else if (keyWord.equals("TextView_Ptext1"))
//			{
//				String by="//android.widget.TextView[@text=,'"+replace+"')]";
//				return by;
//			} 
			else {
				return new String("Tools.GetBy传入定位方式错误 请检查");
						
			}
			
		}
	/**
	 * 获得数据库审批人表中的数据
	 * @param tableNama
	 * @return
	 */
		public  static String getAppMan(String tableNama)
		{
		String aString="select * from "+tableNama;
		return aString;		
		}
//		@Test
		public void testgetSelectSqlLeavlScript()
		{
			String mString=getAppMan("FYBXApp");
			System.out.println(mString);
			}
		/**
		 * 
		 * @param tableNama  表明
		 * @param terminalid  终端名
		 * @return
		 */
		public  static String getBookNO(String tableNama,String terminalid)
		{
//			select feiyongbaoxiao from WebGenerateData where terminal='ios' and datano=3
		String aString="select * from "+tableNama+" where terminal='"+terminalid+"'";
		return aString;		
		}
//		@Test
		public void testgetSelectSqlTestDataScript()
		{
			String mString=getBookNO("WebGenerateData","ios'");
			System.out.println("审批数据：-----"+mString);
			}
		/**
		 * 批审数据查询
		 * @param tableNama 表明
		 * @param terminalid  平台名
		 * @return
		 */
		public  static String getSelectSqlManyApp(String tableNama,String terminalid)
		{
//		select feiyongbaoxiao from WebGenerateData where terminal='ios' and datano<=1
//			一条数据进行批审
		String aString="select * from "+tableNama+" where terminal='"+terminalid+"'"+" and datano=1";
		return aString;		
		}
//		@Test
		public void testgSelectSqlManyApp()
		{
			String mString=getSelectSqlManyApp("WebGenerateData","ios'");
			System.out.println("getSelectSqlManyApp------"+mString);
			}
		/**
		 * 得到element到文本，用于处理定位不到到元素null报错的情况
		 * @param element  定位后的页面元素
		 * @return
		 */
		public  static String getElementText(WebElement element)
		{
		try {
			String text=element.getText();
			return text;
		} catch (Exception e) {
			// TODO: handle exception
			return "无法定位元素";
		}
		}
		
		/**
		 * 将数据驱动Map<String, String> 数据转化为ArrayList；
		 * @param param Map<String, String> 
		 * @return
		 */
		public  static ArrayList<String> changeToArry(Map<String, String> param)
		{
		ArrayList<String>  list =new ArrayList<String>();
		  int x=0;
		  for (Map.Entry<String, String> entry : param.entrySet()) {
			
			 list.add(entry.getValue());
			 x++;
		  }
		return list;
		}
		
		/**
		 * 返回JSON布尔 
		 * @param object
		 * @param istrue
		 * @return
		 */
	public static String getJosnBoolean(String object ,boolean istrue)
	{
		String data="";
		if (istrue) {
			 data= "{\""+object+"\":true} " ;
		}
		else {
			 data= "{\""+object+"\":false} " ;	
		}
		
		return data;
	}	
	
	/**
	 * josnObject  返回单个josn对象
	 * @param object  数据头
	 * @param map  数据集合
	 * @return
	 */
	public static String getJosnObject( Map<String,String> map)
	{
		String data="{";

		for (Iterator it =  map.keySet().iterator();it.hasNext();)
	    {
			
	     String key = (String) it.next();
	     String a= map.get(key);
	     if (it.hasNext())
	     {
	    	 data=data+"\""+key+"\":\""+a+"\",";
	     }
	     else 
	     {
	    	 data=data+"\""+key+"\":\""+a+"\"";
		 }
	     
	    }
		data=data+"}";
		return data;
	}	
	
	

	
	
//	@Test
	public void t1()
	{ 
		Map<String,String> map = new LinkedHashMap<String,String>();
	    map.put("语文" , "22");   
	    map.put("数学" , "3");   
	    map.put("英语" ,"3");  
	    String key = DataHandle.getJosnObject(map);
		System.out.println(key);
	}
	/**
	 * josnObject  返回多个josn对象
	 * @param datas  数据集合
	 * @return
	 */
	public static String getJosnObjects(   List<HashMap<String, String>>  datas)
	{
//	返回的josn格式数据
		String data="{";
		for (int i = 0; i < datas.size(); i++) 
		{
//			数据头自动生成，一次为 0  1 2.。。
			data=data+"\""+i+"\":{";
//		获得对象的行数，决定由几个数据头
				for (Iterator it =  datas.get(i).keySet().iterator();it.hasNext();)
			    {
//					获得指定行的键 及键值
			     String key = (String) it.next();
			    
			     String a= datas.get(i).get(key);
			     if (a.equals(null)) {
					a="";
				}
			     if (it.hasNext())
			     {
//			    	 如果不是该行的最后一个数据，那么后面加逗号{
			    	 data=data+"\""+key+"\":\""+a+"\",";
			     }
			     else 
			     {
			    	 if (i!=(datas.size()))
			    	 {
//			    		 如果是该行的最后一个数据，且不是所有数据行最后的，那么后面加}及逗号
			    		 data=data+"\""+key+"\":\""+a+"\"},";
			    	 }
			    	 else {
//			    		 如果是该行的最后一个数据，且是所有数据行最后的，那么后面加}
			    		 data=data+"\""+key+"\":\""+a+"\"}";
					}
			    	 
				 }
			     
			    }
		}
		data=data+"}";
		return data;
	}
	@Test 
	public void t2()
	{
		ConnectMySQL mysql= new ConnectMySQL();
	 	mysql.connect("localhost:3306/Config", "root", "root");
	    List<HashMap<String, String>> rs =mysql.getSqlResault("select * from runconfig  where res='成功' ",true);       
	    
//		System.out.println(rs);
	    String aString=DataHandle.getJosnObjectArry(rs);
		System.out.println(aString);
	}
	
//	 [  
//      {"id":"1","tagName":"apple"},  
//      {"id":"2","tagName":"orange"},  
//      {"id":"3","tagName":"banana"},  
//      {"id":"4","tagName":"watermelon"},  
//      {"id":"5","tagName":"pineapple"}  
//  ];  
	/**
	 * josnObject  返回多个josn  数组对象
	 * @param datas  数据集合
	 * @return
	 */
	public static String getJosnObjectArry(  List<HashMap<String, String>>  datas)
	{
//	返回的josn格式数据
		String data="[";
		for (int i = 0; i < datas.size(); i++) 
		{
//			数据头自动生成，一次为 0  1 2.。。
			data=data+"{";
//		获得对象的行数，决定由几个数据头
				for (Iterator it =  datas.get(i).keySet().iterator();it.hasNext();)
			    {
//					获得指定行的键 及键值
			     String key = (String) it.next();
			     
			     String mid="";
			    
//			     System.out.println(mid);
			     if (datas.get(i).get(key)==null) 
			     {
			    	 mid="";
			    	
//			     System.out.println(mid);
			     }
			     else if (datas.get(i).get(key).contains("\"") ) {
			    	 mid= datas.get(i).get(key).replace("\"", "\\\"");	
				}
			     else 
			     {
			    	 mid= datas.get(i).get(key);
//			    	 System.out.println(mid);
				}
			    
			     String a= mid;
			     if (it.hasNext())
			     {
//			    	 如果不是该行的最后一个数据，那么后面加逗号{
			    	 data=data+"\""+key+"\":\""+a+"\",";
			     }
			     else 
			     {
			    	 if (i!=(datas.size()-1))
			    	 {
//			    		 如果是该行的最后一个数据，且不是所有数据行最后的，那么后面加}及逗号
			    		 data=data+"\""+key+"\":\""+a+"\"},";
			    	 }
			    	 else {
//			    		 如果是该行的最后一个数据，且是所有数据行最后的，那么后面加}
			    		 data=data+"\""+key+"\":\""+a+"\"}";
					}
			    	 
				 }
			     
			    }
				
		}
		data=data+"]";
		return data;
	}
	
	
	/**
	 * josnObject  返回多个josn  数组对象 不带\
	 * @param datas  数据集合
	 * @return
	 */
	public static String getJosnObjectArryNo(  List<HashMap<String, String>>  datas)
	{
//	返回的josn格式数据
		String data="[";
		for (int i = 0; i < datas.size(); i++) 
		{
//			数据头自动生成，一次为 0  1 2.。。
			data=data+"{";
//		获得对象的行数，决定由几个数据头
				for (Iterator it =  datas.get(i).keySet().iterator();it.hasNext();)
			    {
//					获得指定行的键 及键值
			     String key = (String) it.next();
			     
			     String mid="";
			    
//			     System.out.println(mid);
			     if (datas.get(i).get(key)==null) 
			     {
			    	 mid="";
			    	
//			     System.out.println(mid);
			     }
			    
			     else 
			     {
			    	 mid= datas.get(i).get(key);
//			    	 System.out.println(mid);
				}
			    
			     String a= mid;
			     if (it.hasNext())
			     {
//			    	 如果不是该行的最后一个数据，那么后面加逗号{
			    	 data=data+"\""+key+"\":\""+a+"\",";
			     }
			     else 
			     {
			    	 if (i!=(datas.size()-1))
			    	 {
//			    		 如果是该行的最后一个数据，且不是所有数据行最后的，那么后面加}及逗号
			    		 data=data+"\""+key+"\":\""+a+"\"},";
			    	 }
			    	 else {
//			    		 如果是该行的最后一个数据，且是所有数据行最后的，那么后面加}
			    		 data=data+"\""+key+"\":\""+a+"\"}";
					}
			    	 
				 }
			     
			    }
				
		}
		data=data+"]";
		return data;
	}
	
	public static String getJosnObjectArry(  List<HashMap<String, String>>  datas,int  start,int end)
	{
//	返回的josn格式数据
		String data="[";
		for (int i = start; i < end; i++) 
		{
//			数据头自动生成，一次为 0  1 2.。。
			data=data+"{";
//		获得对象的行数，决定由几个数据头
				for (Iterator it =  datas.get(i).keySet().iterator();it.hasNext();)
			    {
//					获得指定行的键 及键值
			     String key = (String) it.next();
			     
			     String a= datas.get(i).get(key);
			     if (it.hasNext())
			     {
//			    	 如果不是该行的最后一个数据，那么后面加逗号{
			    	 data=data+"\""+key+"\":\""+a+"\",";
			     }
			     else 
			     {
			    	 if (i!=end-1)
			    	 {
//			    		 如果是该行的最后一个数据，且不是所有数据行最后的，那么后面加}及逗号
			    		 data=data+"\""+key+"\":\""+a+"\"},";
			    	 }
			    	 else {
//			    		 如果是该行的最后一个数据，且是所有数据行最后的，那么后面加}
			    		 data=data+"\""+key+"\":\""+a+"\"}";
					}
			    	 
				 }
			     
			    }
				
		}
		data=data+"]";
		return data;
	}
}