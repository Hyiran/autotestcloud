package main.server.filehandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import main.funtion.ConnectMySQL;
import main.funtion.DataHandle;
import main.funtion.TimeString;

/**
 *  程序app 上传
 */
@WebServlet("/AppFileUpLoad")
public class AppFileUpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String appPath="";
	//default maximum allowable file size is 1000k  
		static final int MAX_SIZE = 1024000;  
		//instance variables to store root and success message  
		String rootPath, successMessage;  
    public AppFileUpLoad() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		boolean runSql=true;
		//		设置编码  
		request.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
		response.addHeader("Access-Control-Allow-Origin", "*");
		OutputStream stream = response.getOutputStream();
		  //此处不新建session，只是去取已经创建的session
				HttpSession session=null;
		         session = request.getSession(false);
		        String  project=(String) session.getAttribute("project");
		        String filename="";
        //获得磁盘文件条目工厂  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
       
        
        String p="/appfile/";
        //获取文件需要上传到的路径  
        String path = this.getServletContext().getRealPath(p);
        File filepath=new File(path);
        if(!filepath.exists())
        {
        filepath.mkdirs();
        appPath=filepath+"/";
        }
     
          
        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，  
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同  
        /** 
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
         * 然后再将其真正写到 对应目录的硬盘上 
         */  
        factory.setRepository(new File(path));  
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
        factory.setSizeThreshold(1024*1024) ;  
          
        //高水平的API文件上传处理  
        ServletFileUpload upload = new ServletFileUpload(factory);  
          
          
        try {  
            //可以上传多个文件  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
              
            for(FileItem item : list)  
            {  
                //获取表单的属性名字  
                String name = item.getFieldName();  
                appPath=appPath+name;
                //如果获取的 表单信息是普通的 文本 信息  
                if(item.isFormField())  
                {                     
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
                    String value = item.getString() ;  
                      
                    request.setAttribute(name, value);  
                }  
                //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些  
                else  
                {  
                    /** 
                     * 以下三步，主要获取 上传文件的名字 
                     */  
                    //获取路径名  
                    String value = item.getName() ;  
                    //索引到最后一个反斜杠  
                    int start = value.lastIndexOf("\\");  
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
                     filename = value.substring(start+1);  
                      
                    request.setAttribute(name, filename);  
                      
                    //真正写到磁盘上  
                    //它抛出的异常 用exception 捕捉  
                      
                    //item.write( new File(path,filename) );//第三方提供的  
                      
                    //手动写的  
                    OutputStream out = new FileOutputStream(new File(path,filename));  
                      
                    InputStream in = item.getInputStream() ;  
                      
                    int length = 0 ;  
                    byte [] buf = new byte[1024] ;  
                      
                    System.out.println("获取上传文件的总共的容量："+item.getSize());  
  
                    // in.read(buf) 每次读到的数据存放在   buf 数组中  
                    while( (length = in.read(buf) ) != -1)  
                    {  
                        //在   buf 数组中 取出数据 写到 （输出流）磁盘上  
                        out.write(buf, 0, length);  
                          
                    }  
                      
                    in.close();  
                    out.close();  
                }  
            }  
              
              
              
        } catch (FileUploadException e) 
        {  
           
            e.printStackTrace();  
        }  
        catch (Exception e) {  
          
        }  
        String  res ="附加上传成功";
//         将落地文件 存入数据库
//        获取文件路径
        String exclePath =path+"/"+filename;
        File file =new File(exclePath);
        String ablePath=file.getAbsolutePath();
        if (ablePath!="")
        {
        	ConnectMySQL mysql =new ConnectMySQL();
            mysql.connect("localhost:3306/AutoTest", "root", "root");
            String  time=TimeString.getyMDHMS();
            String plant=filename.substring(filename.length()-3, filename.length());
            if (plant.equals("apk"))
            {
            	
            	plant="Android";
			}
            else 
            {
            	plant="Ios";
			}
            
            List<HashMap<String, String>> rs=mysql.getSqlResault(" select * from  application where project='"+project+"' order by  insertdate", true);
//            最多允许一个项目10个程序
            if(rs.size()==10 || rs.size()>10) 
            {
//            	删除历史最先生成的数据
            	String ids=rs.get(0).get("id");
            	int id=DataHandle.getInt(ids);
            	 mysql.getSqlResault("delete from application where id ="+id+" ", false);
            	
            	 mysql.getSqlResault(" update   application set insertdate='"+time+"' where project='"+project+"' and filename='"+filename+"'", false);
 
			}
            rs=mysql.getSqlResault(" select * from  application where project='"+project+"' and filename='"+filename+"' order by  insertdate", true);
           
            if (rs.size()==0) 
            {
            	mysql.getSqlResault("insert into application (filename,plantform,insertdate,insertman,project)"
            			+ "values('"+filename+"','"+plant+"','"+time+"','管理员','"+project+"')", false);
            	
//            			res="insert into application (filename,plantform,insertdate,insertman,project)"
//            			+ "values('"+filename+"','Android','"+time+"','管理员','"+project+"')";
			}
           
            else {


//res="update   application set insertdate='"+time+"' where project='"+project+"' and filename='filename'";
mysql.getSqlResault(" update   application set insertdate='"+time+"' where project='"+project+"' and filename='"+filename+"'", false);

			}
        }
  
        res="应用上传成功,您可以在新增策略-应用文件下拉列表中查看";
        stream.write(res.getBytes("UTF-8"));
  
    }  
	

}
