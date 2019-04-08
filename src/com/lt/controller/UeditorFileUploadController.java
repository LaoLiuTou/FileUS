package com.lt.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;
@Controller
public class UeditorFileUploadController {
	 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    Logger logger = Logger.getLogger("DeerShopLogger");

    /*
     * 上传图片文件夹
     */
    private static final String UPLOAD_PATH = "upload/UeditorImg/";

    /*
     * 上传图片
     */
    @RequestMapping("/UeditorUploadImg")
    public void uplodaImg(HttpServletRequest request,
    		HttpServletResponse response) { 
    	try {
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding( "utf-8" );
			response.setHeader("Content-Type" , "text/html");
			String middleStr=UPLOAD_PATH+"/"+sdf.format(new Date())+"/";
			String rootPath=request.getSession().getServletContext().getRealPath("/")
					+middleStr; 
			 
			out.write( new ActionEnter( request, rootPath ).exec() );
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    }
}
