package main.server.tools;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.testng.annotations.Test;

import main.funtion.GetIp;


/**
 * Title: 使用javamail发送邮件 Description: 演示如何使用javamail包发送电子邮件。这个实例可发送多附件
 * 
 * @version 1.0
 */
public class SendMail {
String to = "";// 收件人
String from = "";// 发件人
String host = "";// smtp主机
String username = "";
String password = "";
String filename = "";// 附件文件名
String subject = "";// 邮件主题
String content = "";// 邮件正文
Vector file = new Vector();// 附件文件集合


/**
* <br>
* 方法说明：默认构造器 <br>
* 输入参数： <br>
* 返回类型：
*/
public SendMail() {
}


/**
* <br>
* 方法说明：构造器，提供直接的参数传入 <br>
* 输入参数： <br>
* 返回类型：
*/
public SendMail(String to, String from, String smtpServer, String username, String password, String subject,
String content) {
this.to = to;
this.from = from;
this.host = smtpServer;
this.username = username;
this.password = password;
this.subject = subject;
this.content = content;
}


/**
* <br>
* 方法说明：设置邮件服务器地址 <br>
* 输入参数：String host 邮件服务器地址名称 <br>
* 返回类型：
*/
public void setHost(String host) {
this.host = host;
}


/**
* <br>
* 方法说明：设置登录服务器校验密码 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setPassWord(String pwd) {
this.password = pwd;
}


/**
* <br>
* 方法说明：设置登录服务器校验用户 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setUserName(String usn) {
this.username = usn;
}


/**
* <br>
* 方法说明：设置邮件发送目的邮箱 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setTo(String to) {
this.to = to;
}


/**
* <br>
* 方法说明：设置邮件发送源邮箱 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setFrom(String from) {
this.from = from;
}


/**
* <br>
* 方法说明：设置邮件主题 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setSubject(String subject) {
this.subject = subject;
}


/**
* <br>
* 方法说明：设置邮件内容 <br>
* 输入参数： <br>
* 返回类型：
*/
public void setContent(String content) {
this.content = content;
}


/**
* <br>
* 方法说明：把主题转换为中文 <br>
* 输入参数：String strText <br>
* 返回类型：
*/
public String transferChinese(String strText) {
try {
strText = MimeUtility.encodeText(new String(strText.getBytes(), "UTF-8"), "UTF-8", "B");
} catch (Exception e) {
e.printStackTrace();
}
return strText;
}


/**
* <br>
* 方法说明：往附件组合中添加附件 <br>
* 输入参数： <br>
* 返回类型：
*/
public void attachfile(String fname) {
file.addElement(fname);
}


/**
* <br>
* 方法说明：发送邮件 <br>
* 输入参数： <br>
* 返回类型：boolean 成功为true，反之为false
*/
public boolean sendMail() {
// 构造mail session
Properties props = System.getProperties();
props.put("mail.smtp.host", host);
props.put("mail.smtp.auth", "true");
Session session = Session.getDefaultInstance(props, new Authenticator() {
public PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// 构造MimeMessage 并设定基本的值
MimeMessage msg = new MimeMessage(session);
msg.setFrom(new InternetAddress(from));
InternetAddress[] address = { new InternetAddress(to) };
msg.setRecipients(Message.RecipientType.TO, address);
subject = transferChinese(subject);
msg.setSubject(subject);
// 构造Multipart
Multipart mp = new MimeMultipart();
// 向Multipart添加正文
MimeBodyPart mbpContent = new MimeBodyPart();
mbpContent.setText(content);
// 向MimeMessage添加（Multipart代表正文）
mp.addBodyPart(mbpContent);
// 向Multipart添加附件
Enumeration efile = file.elements();
while (efile.hasMoreElements()) {
MimeBodyPart mbpFile = new MimeBodyPart();
filename = efile.nextElement().toString();
FileDataSource fds = new FileDataSource(filename);
mbpFile.setDataHandler(new DataHandler(fds));
mbpFile.setFileName(fds.getName());
// 向MimeMessage添加（Multipart代表附件）
mp.addBodyPart(mbpFile);
}
file.removeAllElements();
// 向Multipart添加MimeMessage
msg.setContent(mp);
msg.setSentDate(new Date());
// 发送邮件
Transport.send(msg);
System.out.println("...");
} catch (MessagingException mex) {
mex.printStackTrace();
Exception ex = null;
if ((ex = mex.getNextException()) != null) {
ex.printStackTrace();


}
System.out.println("" + mex.getMessage());
return false;
}
return true;
}


/**
* <br>
* 方法说明：主方法，用于测试 <br>
* 输入参数： <br>
* 返回类型：
*/
public static void main(String[] args) {
	
	System.out.println("...");
	SendMail sendmail = new SendMail();
	sendmail.setHost("smtp.163.com");// smtp.mail.yahoo.com.cn/smtp.163.com
	sendmail.setUserName("jurryfu@163.com");// 您的邮箱用户名
	sendmail.setPassWord("love525131417");// 您的邮箱密码
	sendmail.setTo("635134117@qq.com");// 接收者
	sendmail.setFrom("jurryfu@163.com");// 发送者
	sendmail.setSubject("测试完成");
	sendmail.setContent("您的测试完毕，请到测试报告页面查看");
	sendmail.sendMail();
	System.out.println("end");
	

}

/**
 * 发送邮件
 * @param sendMail 邮件对象
 * @param sendManEmail  发件人邮箱地址
 * @param sendManPassword  发件人邮箱授权码
 * @param receivedManEmail  收件人邮箱地址
 * @param Subject    主题
 * @param Content   内容
 * @return
 */
public boolean sendMailToUser( String sendManEmail,String sendManPassword,String receivedManEmail,String Subject,String Content)
{
	try {

		SendMail sendMail =new SendMail();
		sendMail.setHost("smtp.163.com");
		sendMail.setUserName(sendManEmail);
		sendMail.setPassWord(sendManPassword);
		sendMail.setTo(receivedManEmail);
		sendMail.setFrom(sendManEmail);
		sendMail.setSubject(Subject);
		sendMail.setContent(Content);
		sendMail.sendMail();
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}

}

//@Test
public void t1()
{

	
  	String Subject="自动化测试执行";
   	GetIp getIp =new GetIp();
   	String ip=getIp.getMyIp();
   	String Content="您的自动化测试已经执行完毕，请到测试报告页面查看测试详情。网站地址:http://"+ip+":8081/autotestcloud";
//	System.out.println();
    SendMail sendMail =new SendMail();
   	boolean sendSuccess= sendMail.sendMailToUser( "jurryfu@163.com", "love525131417", "635134117@qq.com", Subject, Content);
   	
}
}