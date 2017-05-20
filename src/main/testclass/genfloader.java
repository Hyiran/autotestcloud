package main.testclass;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import main.funtion.TimeString;

@Test
public class genfloader 
{
public void a() throws IOException
{
	 String name=TimeString.getyMDHMS();
	File outfile    = new File("./WebContent/"+name);                 
	  //如果文件不存在，则创建一个新文件
	  if(!outfile.isFile()){
	      outfile.mkdir();
	}
}
}
