package com.lt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lt.utils.QrUtils;
@Controller
public class QrcodeController {
	
	Logger logger = Logger.getLogger("FileUSLogger"); 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/createQrcode")  
    @ResponseBody
    public Map filesUpload(HttpServletRequest request) { 
    	Map resultMap=new HashMap();
    
		try {
			String lot=request.getParameter("lot");
			String code=request.getParameter("code");
			if(lot!=null&&!lot.equals("")){
				if(code!=null&&!code.equals("")){
					String path=request.getSession().getServletContext().getRealPath("/")
							+"qrcode/"+lot+"/";
					String base = QrUtils.class.getResource("/").getPath();
					String resultPath=request.getScheme()+"://"+ request.getServerName()+
							":"+request.getServerPort()+request.getContextPath()+"/qrcode/"+lot+"/";
					JSONArray codeJA=JSONArray.fromObject(code);
					Map<String,String> codeMap=new HashMap<String,String>();
					for(int index=0;index<codeJA.size();index++){
						String codeStr= codeJA.getString(index);
						boolean result=QrUtils.encode(codeStr,  
		                        425, 425, base+"logos/logo1.png", path+codeStr+".jpg");
						if(result){
							codeMap.put(codeStr, resultPath+codeStr+".jpg");
						}
						else{
							codeMap.put(codeStr, "");
						}
						
					}
					 
					resultMap.put("status", "0");
					resultMap.put("data", codeMap);
				}
				else{
					resultMap.put("status", "-1");
					resultMap.put("data", "编号不能为空!");
				}
				 
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("data", "批号不能为空!");
			}
			
		}
		catch (Exception e)
		{
			resultMap.put("status", "-1");
			resultMap.put("data", "上传失败!");
			e.printStackTrace();
			logger.info("生成二维码异常");
		}  
        return resultMap;  
    }  
    
}
