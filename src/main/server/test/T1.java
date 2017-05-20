package main.server.test;

import org.testng.annotations.Test;

import main.funtion.DataHandle;

public class T1 
{
@Test
public void t()
{
	int a=1;
	int b=3;
	double c=((double)a)/(double)b*100;
	String reString=DataHandle.getStringDigit(2, c)+"%";
System.out.print(reString);
}
}
