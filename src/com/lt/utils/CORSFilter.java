package com.lt.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

 

public class CORSFilter implements Filter {  
	  
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
    	HttpServletRequest request = (HttpServletRequest) req;
    	HttpServletResponse response = (HttpServletResponse) res;  
    	request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		//response.setContentType("application/json;charset=UTF-8"); 
	  //CORS跨域
	    response.setHeader("Access-Control-Allow-Origin", "*");  
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
	    response.setHeader("Access-Control-Max-Age", "3600");  
	    //response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type,X_Requested_With");
	    //response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type，requesttype");
        
	    //String uri = request.getServletPath();
	    chain.doFilter(req, res);  
	    
    }  
  
    public void init(FilterConfig filterConfig) {}  
  
    public void destroy() {}  
  
} 