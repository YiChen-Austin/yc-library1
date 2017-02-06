package com.mall.common.util;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartUpload;

/**
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class FileUploadUtil {
	

	public void uploadFile(HttpServletRequest request,HttpServletResponse response) {
		JspFactory fac=JspFactory.getDefaultFactory();
		PageContext pageContext=fac.getPageContext((Servlet) this, request,response, null, false, JspWriter.DEFAULT_BUFFER, true);
		 //新建一个SmartUpload对象   
	    SmartUpload su = new SmartUpload();   
	  
	    //上传初始化   
	    try {
			su.initialize(pageContext);
		} catch (ServletException e1) {
			e1.printStackTrace();
		}   
	  
	    // 设定上传限制   
	    //1.限制每个上传文件的最大长度。   
	    su.setMaxFileSize(10000000);   
	  
	    //2.限制总上传数据的长度。   
	    su.setTotalMaxFileSize(20000000);   
	  
	    //3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。   
	    su.setAllowedFilesList("doc,txt,jpg,rar,mid,waw,mp3,gif");   
	       
	    boolean sign = true;   
	       
	    //4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。   
	    try {   
	        su.setDeniedFilesList("exe,bat,jsp,htm,html");   
	  
	        //上传文件   
	        su.upload();   
	        //将上传文件保存到指定目录   
	        su.save("c:\\");  
	 
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        sign = false;  
	    }  
	    if(sign==true)  
	    {  
	        System.out.println("＜script＞parent.callback('upload file success')＜/script＞");  
	    }else  
	    {  
	    	System.out.println("＜script＞parent.callback('upload file error')＜/script＞");   
	    }   
	}
}
