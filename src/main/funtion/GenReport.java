package main.funtion;

import java.util.HashMap;
import java.util.List;
/**
 * 生成测试报告
 * @author fujiaxi
 *
 */
public class GenReport 
{	public    List<HashMap<String, String>> rs;
	int totalAsser=0;
	int passAsser=0;
	int loseAsser=0;
	String passrat="";
	String isStop="否";
	String policyName="";
	String project="";
	String casename="";
	String genTime="";
	/**
	 * 生产数据到报告表中（每个用例执行完，或者异常中断时调用）
	 * @param isStop1  是否停止测试
	 * @param policyName1  策略名称
	 * @param project1  项目名称
	 * @param casename1  用例名称
	 */
public void inserTestCase( String isStop1,String policyName1,String project1,String casename1) 
	{
	
	ConnectMySQL mysql =new ConnectMySQL();
    mysql.connect("localhost:3306/AutoTest", "root", "root");
  
	genTime=TimeString.getyMDHMS();
	isStop=isStop1;
	policyName=policyName1;
	project=project1;
	casename=casename1;

//	计算通过率
	if (totalAsser!=0)
	{
		double rat=((double)passAsser)/(double)totalAsser*100;
		passrat=DataHandle.getStringDigit(2, rat)+"%";

	}
	
//	System.out.println("insert into repordetails (project,policyName,casename,totalAsser,passAsser,loseAsser,passrat,isStop,genTime)"
//   	     		+ "values('"+project+"','"+policyName+"','"+casename+"',"+totalAsser+","+passAsser+","+loseAsser+",'"+passrat+"',"
//   	     				+ "'"+isStop+"',"+genTime+")");
	
	mysql.getSqlResault("insert into repordetails (project,policyName,casename,totalAsser,passAsser,loseAsser,passrat,isStop,genTime)"
   	     		+ "values('"+project+"','"+policyName+"','"+casename+"',"+totalAsser+","+passAsser+","+loseAsser+",'"+passrat+"',"
   	     				+ "'"+isStop+"','"+genTime+"')", false);
	
	}
/**
 * 更新测试用例主表
 * @param project1 项目名
 * @param casename1  用例名称
 * @param res  结果
 */
public void inserTestCaseDetails( String project1,String casename1,String res) 
{
//String name="testcase"+;
ConnectMySQL mysql =new ConnectMySQL();
mysql.connect("localhost:3306/AutoTest", "root", "root");
String time=TimeString.getyMDHMS();
mysql.getSqlResault("update testcasemain  set resault='"+res+"',lastruntime='"+time+"'  where casename='"+casename1+"' ", false);
//mysql.connect("localhost:3306/Config", "root", "root");

}
}
