package main.funtion;

public class GetClassMethodName {

	public String classMethodeName="";
	public String clasName="";
	public String MethodeName="";
	public static  String GetClassMethodName()
	{	
		
			StackTraceElement[] stackTraceElements=Thread.currentThread().getStackTrace();
			String className=stackTraceElements[2].getClassName();
			int index=className.lastIndexOf(".");
			index++;
			className =className.substring(index, className.length())+"."+stackTraceElements[2].getMethodName()+"."+stackTraceElements[2].getLineNumber()+":";
			return className;
	

	}
	public static  String GetDiaoYongClassMethodName()
	{	
		
			StackTraceElement[] stackTraceElements=Thread.currentThread().getStackTrace();
			String className=stackTraceElements[1].getClassName();
			int index=className.lastIndexOf(".");
			index++;
			className =className.substring(index, className.length())+"."+stackTraceElements[1].getMethodName()+"."+stackTraceElements[1].getLineNumber()+":";
			return className;
	

	}
}
