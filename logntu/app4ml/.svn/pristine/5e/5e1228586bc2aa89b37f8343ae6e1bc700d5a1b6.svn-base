package com.mall.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

/**
 * 
 * @author cqdxk
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class ZipFileUtil {
	public static Logger logger = Logger.getLogger(ZipFileUtil.class);

	/** WEB项目的根目录 */
	public static String webRootPath = ContextLoader.getCurrentWebApplicationContext()
			.getServletContext().getRealPath("/") + File.separator;

	/**
	 * 压缩多个文件
	 * @param fileName 生成的文件名称
	 * @param files 文件名称
	 * @return 压缩文件的物理路径
	 */
	public static String zipMiltiFile4Web(String fileName, List<String> files) {
		byte[] buffer = new byte[1024];
		String strZipPath = webRootPath + fileName;
		OutputStream os = null;
		try {
            os = new FileOutputStream(strZipPath);  
            BufferedOutputStream bos = new BufferedOutputStream(os);  
			ZipOutputStream zos = new ZipOutputStream(bos);
			for (int i = 0; i < files.size(); i++) {
				File file = new File(webRootPath + files.get(i)); 
				InputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);  
                zos.putNextEntry(new ZipEntry(file.getName()));
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = bis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				fis.close();
			}
			zos.closeEntry();
			zos.close();
		} catch (Exception e) {
			logger.error("压缩文件出错");
			e.printStackTrace();
		} finally{
			try {
				if(os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strZipPath;
	}
	/**
	 * 压缩多个文件
	 * @param fileName 生成的文件名称
	 * @param files 文件名称
	 * @return 压缩文件的物理路径
	 */
	public static String zipMiltiFile4Web2(String fileName, List<String> files) {
		byte[] buffer = new byte[1024];
		String strZipPath = webRootPath + fileName;
		OutputStream os = null;
		try {
            os = new FileOutputStream(strZipPath);  
            BufferedOutputStream bos = new BufferedOutputStream(os);  
            org.apache.tools.zip.ZipOutputStream zos = new org.apache.tools.zip.ZipOutputStream(bos);
			for (int i = 0; i < files.size(); i++) {
				File file = new File(webRootPath + files.get(i)); 
				InputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);  
                org.apache.tools.zip.ZipEntry ze=new org.apache.tools.zip.ZipEntry(file.getName());
                ze.setSize(file.length());
                ze.setTime(file.lastModified());
                zos.putNextEntry(ze);
                zos.setEncoding("gbk");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = bis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				fis.close();
			}
			zos.closeEntry();
			zos.close();
		} catch (Exception e) {
			logger.error("压缩文件出错");
			e.printStackTrace();
		} finally{
			try {
				if(os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strZipPath;
	}
	/**
	 * 压缩多个文件
	 * @param fileName 生成的文件名称
	 * @param files 文件名称
	 * @return 压缩文件的物理路径
	 */
	public static String zipMiltiFile4Web(String fileName, String[] files) {
		byte[] buffer = new byte[1024];
		String strZipPath = webRootPath + fileName;
		OutputStream os = null;
		try {
            os = new FileOutputStream(strZipPath);  
            BufferedOutputStream bos = new BufferedOutputStream(os);  
			ZipOutputStream zos = new ZipOutputStream(bos);
			for (int i = 0; i < files.length; i++) {
				File file = new File(webRootPath + files[i]); 
				InputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);  
                zos.putNextEntry(new ZipEntry(file.getName()));
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = bis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				fis.close();
			}
			zos.closeEntry();
			zos.close();
		} catch (Exception e) {
			logger.error("压缩文件出错");
			e.printStackTrace();
		} finally{
			try {
				if(os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strZipPath;}

}