package main.funtion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**构造函数初始化 文件路径和sheet页
 * 
 * @param filePath  excle 文件路径
 * @param sheetName sheet 页
 */
public class ExcelReader extends GetClassMethodName {
	private String filePath=Config.excleLib;
	private String sheetName;
	private Workbook workBook;	
	private Sheet sheet;
	private List<String> columnHeaderList;
	private List<List<String>> listData;
	public List<Map<String,String>> getMapData;
	private boolean flag;
	
	/**
	 * 所需jar包：poi-3.8.jar,poi-ooxml.jar,poi-ooxml-schemas.jar,xmlbeans.jar
	 * 提供解析excel，兼容excel2003及2007+版本
	 * @param filePath excel本地路径
	 * @param sheetName excel的sheet名称
	 * @throws Exception 
	 */
	public ExcelReader(String fileName, String sheetName) 
	{
		try {
			this.filePath = fileName;
			this.sheetName = sheetName;
			this.flag = false;
			this.load();			
			getCellData(0, 0);
		} catch (Exception e) {
//			Log.logError("EXCLE文件不存在请检查",GetClassMethodName());
		}
		
	}	
	
	/**
	 * 加载EXCEL文件内容，产生WorkBook对象，再产生Sheet对象
	 */
	private void load()  throws Exception{
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(filePath));
			workBook = WorkbookFactory.create(inStream);
			sheet = workBook.getSheet(sheetName);			
		} catch (Exception e) {
//			Log.logError(e.toString(),GetClassMethodName());
		
		}finally{
			try {
				if(inStream!=null){
					inStream.close();
				}				
			} catch (IOException e) {			
//				Log.logError(e.toString(),GetClassMethodName());
			}
		}
	}
	
	/**
	 * 根据cell对象，来取得每个cell的值，所有的值的数据类型都转化为了String类型
	 * @param cell Cell对象
	 * @return
	 */
	private String getCellValue(Cell cell) {
		String cellValue = "";
		DataFormatter formatter = new DataFormatter();
		if (cell != null) {
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						cellValue = formatter.formatCellValue(cell);
					} else {
						double value = cell.getNumericCellValue();
						int intValue = (int) value;
						cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
					}
					break;
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue = String.valueOf(cell.getCellFormula());
					break;
				case Cell.CELL_TYPE_BLANK:
					cellValue = "";
					break;
				case Cell.CELL_TYPE_ERROR:
					cellValue = "";
					break;
				default:
					cellValue = cell.toString().trim();
					break;
			}
		}
		return cellValue.trim();
	}
	
	/**mapData 用法：mapData。get（0），指向文件第二行数据，假设第一行是列名不是数据
	 * map。size：实际是总行数－1个数，也就是不计算列名
	 * 
	 */

	private void getSheetData() {
		listData = new ArrayList<List<String>>();
		getMapData = new ArrayList<Map<String, String>>();	
		columnHeaderList = new ArrayList<String>();
		int numOfRows =0;
		try {
			 numOfRows = sheet.getLastRowNum() + 1;
		} catch (Exception e) {
//		Log.logError("excle文件"+filePath+"sheet名字错误，请检查",GetClassMethodName());
		}
		
		for (int i = 0; i < numOfRows; i++) {
			Row row =null;
			try {
				 row = sheet.getRow(i);
			} catch (Exception e) {
//			Log.logError("sheetname："+sheetName+"不存在请检查文件！",GetClassMethodName());
			}
			
			Map<String, String> map = new HashMap<String, String>();
			List<String> list = new ArrayList<String>();
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (i == 0){
						columnHeaderList.add(getCellValue(cell));
					}
					else{						
						map.put(columnHeaderList.get(j), this.getCellValue(cell));
					}
					list.add(this.getCellValue(cell));
				}
			}
			if (i > 0){
				getMapData.add(map);
			}
			listData.add(list);
		}
		flag = true;
//		System.out.println(mapData.get(1).size());
		for(int i=0 ;i<getMapData.size();i++)
		{	
//			System.out.println(mapData.get(i));
			for(int a=0;a<1;a++)
			{
//				System.out.println(mapData.get(i).get("test1"));
				
			}
		}
	}
	
	/**
	 * 根据行与列的索引活着列名获得指定数据 索引都是从1开始
	 * @param row 从1开始
	 * @param col 从1开始
	 * @return
	 */
	public String getCellData(int row, int col){
		if(row<=0 || col<=0){
			return null;
		}
		if(!flag){
			this.getSheetData();
		}		
		if(listData.size()>=row && listData.get(row-1).size()>=col){
			return listData.get(row-1).get(col-1);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据行数及第一列的列名，取得相应的cell的值
	 * @param row 从1开始
	 * @param headerName 第一列的列名
	 * @return
	 */
	public String getCellData(int row, String headerName){
		if(row<=0){
			return null;
		}
		if(!flag){
			this.getSheetData();
		}		
		if(getMapData.size()>=row && getMapData.get(row-1).containsKey(headerName)){
			return getMapData.get(row-1).get(headerName);
		}else{
			return null;
		}
	}
	

//@Test
public static void t133()
{
	ExcelReader excelreader1 = new ExcelReader("登陆.xlsx","选择数据");
//	获得第一行 第一列的值
//	Log.logInfo(excelreader1.getCellData(1, 1),GetClassMethodName()); 
////	获得测试列2列第一行值
//	Log.logInfo(excelreader.getCellData(1, "测试列2"));
////	得到数据的行数大小（总行数－1）
//	System.out.println(excelreader.getMapData.size());
////	得到第零行数据
//	System.out.println(excelreader.getMapData.get(0));	
////	得到第零行数据测试列1列数据
//	System.out.println(excelreader.getMapData.get(0).get("测试列1"));
}
		public static void  main(String[]args)
		{
			
		
			ExcelReader Excelreader = new ExcelReader("/Users/fujiaxi/Downloads/model.xlsx","data");
			
////			获得第一行 第一列的值
//			System.out.println(Excelreader.getCellData(1, 1));
			
////			获得测试列2列第一行值
//			System.out.println(Excelreader.getMapData.size());
			
////			得到数据的行数大小（总行数－1）
//			System.out.println(Excelreader.getMapData.size());
////			得到第零行数据
//			System.out.println(Excelreader.getMapData.get(0));	
////			得到第零行数据测试列1列数据
//			System.out.println(Excelreader.getMapData.get(0).get("测试列1"));
		}
}