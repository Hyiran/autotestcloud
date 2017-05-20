package main.server.test;

import org.testng.annotations.Test;

import main.funtion.ExcelReader;

public class GetExcleData
{
@Test 
	public void getva()
	{
	ExcelReader Excelreader = new ExcelReader("/Users/fujiaxi/Desktop/model.xlsx","data");
////获得第一行 第一列的值
//	System.out.println(Excelreader.getCellData(1, "casename"));
//	System.out.println(Excelreader.getCellData(3, "casename"));
	String aString =Excelreader.getCellData(3, "casename");
	if (aString.equals(""))
	{
//		 System.out.println("为空");
	}
//	获得测试列2列第一行值
	System.out.println(Excelreader.getMapData.size());
	 int size =Excelreader.getMapData.size();
	 for (int i = 2; i <=size; i++)
	 {
		 System.out.println(Excelreader.getCellData(i, "elementname"));
	 }
	}
}
