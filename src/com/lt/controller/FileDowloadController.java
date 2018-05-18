package com.lt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FileDowloadController {
	Logger logger = Logger.getLogger("FileUSLogger"); 
	
	@RequestMapping("/downloadZip")
	public void downloadZip(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		
		try {
			String filename=request.getParameter("filename");
			String lot=request.getParameter("lot");
			if(lot!=null&&!lot.equals("")){
				filename=filename==null?"download":URLEncoder.encode(filename, "UTF-8");
	            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流 
	            response.setHeader("Content-Disposition", "attachment;filename=" + filename+".zip");// 设置在下载框默认显示的文件名
	            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
	           
	            String filepath=request.getSession().getServletContext().getRealPath("/")
						+"qrcode/"+lot+"/";
	            logger.info("打包下载文件："+filepath);
	            File fileDir = new File(filepath);  
	            File[] listfile = fileDir.listFiles();  
	            for (int i = 0; i < listfile.length; i++) {  
	                if (!listfile[i].isDirectory()) {  
	                	File file=listfile[i];
	                	FileInputStream fis = new FileInputStream(file);  
	 	               zos.putNextEntry(new ZipEntry(file.getName())); 
	 	               
	 	               int len;  
	 	               byte[] buffer = new byte[1024];  
	 	               while ((len = fis.read(buffer)) > 0) {  
	 	            	   zos.write(buffer, 0, len);  
	 	               }  
	 	               zos.closeEntry();  
	 	               fis.close();  
	                }  
	            }  
	              
	            zos.flush();     
	            zos.close();
			}
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("打包下载文件异常：UnsupportedEncodingException");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("打包下载文件异常：IOException");
        }  
		
		
	}
	
	
}
