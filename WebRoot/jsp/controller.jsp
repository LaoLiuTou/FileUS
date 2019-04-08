<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	
	//String ip=request.getRemoteAddr();    
    //int port=request.getServerPort();   


	String rootPath = application.getRealPath( "/" );
	
	String action = request.getParameter("action");  
	String fileUrl=new ActionEnter( request, rootPath ).exec();
	if( action!=null &&   
	   (action.equals("listfile") || action.equals("listimage") ) ){  
	    rootPath = rootPath.replace("\\", "/");  
	    fileUrl = fileUrl.replaceAll(rootPath, "/");  
	}  
	System.out.println(fileUrl);
	out.write( fileUrl ); 
	//out.write( new ActionEnter( request, rootPath ).exec() );
	
%>