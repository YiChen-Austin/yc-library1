package com.mall.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;


/**
 * 文件操作方法集合。
 * @author sol
 * @since 2015/4/13
 */
public class FileUtil
{
	public static Logger logger = Logger.getLogger(FileUtil.class);
	
	/** 
	 * 删除单个文件 
	 * @param path	被删除文件的文件名 
	 * @return		单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean removeFile(String path)
	{  
	    boolean res = false;  
	    File file = new File(path);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        res = true;  
	    }  
	    return res;
	}
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param path	被删除目录的文件路径 
	 * @return		目录删除成功返回true，否则返回false 
	 */  
	public static boolean removeDir(String path)
	{  
	    // 如果path不以文件分隔符结尾，自动添加文件分隔符  
	    if (!path.endsWith(File.separator)) {  
	        path = path + File.separator;  
	    }  
	    File dirFile = new File(path);  
	    // 如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean res = true;  
	    // 删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        // 删除子文件  
	        if (files[i].isFile()) {  
	            res = removeFile(files[i].getAbsolutePath());  
	            if (!res) {
	            	break;  
	            }
	        } // 删除子目录  
	        else {  
	            res = removeDir(files[i].getAbsolutePath());  
	            if (!res) {
	            	break;  
	            }
	        }  
	    }  
	    if (!res) {
	    	return false;  
	    }
	    // 删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    }
	    else {  
	        return false;  
	    }  
	}
	
 	/**
 	 * 复制单个文件
 	 * 
 	 * @param srcFileName		待复制的文件名
 	 * @param destFileName		目标文件名
 	 * @param overwrite			如果目标文件存在，是否覆盖
 	 * @return 		如果复制成功返回true，否则返回false
 	 */
 	public static boolean copyFile(String srcFileName, String destFileName, boolean overwrite)
 	{
 		File srcFile = new File(srcFileName);

 		// 判断源文件是否存在
 		if (!srcFile.exists()) {
 			return false;
 		}
 		else if (!srcFile.isFile()) {
 			return false;
 		}

 		// 判断目标文件是否存在
 		File destFile = new File(destFileName);
 		if (destFile.exists()) {
 			// 如果目标文件存在并允许覆盖
 			if (overwrite) {
 				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
 				new File(destFileName).delete();
 			}
 		}
 		else {
 			// 如果目标文件所在目录不存在，则创建目录
 			if (!destFile.getParentFile().exists()) {
 				// 目标文件所在目录不存在
 				if (!destFile.getParentFile().mkdirs()) {
 					// 复制文件失败：创建目标文件所在目录失败
 					return false;
 				}
 			}
 		}

 		// 复制文件
 		int byteread = 0; // 读取的字节数
 		InputStream in = null;
 		OutputStream out = null;

 		try {
 			in = new FileInputStream(srcFile);
 			out = new FileOutputStream(destFile);
 			byte[] buffer = new byte[1024];

 			while ((byteread = in.read(buffer)) != -1) {
 				out.write(buffer, 0, byteread);
 			}
 			return true;
 		}
 		catch (FileNotFoundException e) {
 			return false;
 		}
 		catch (IOException e) {
 			return false;
 		}
 		finally {
 			try {
 				if (out != null) {
 					out.close();
 				}
 				if (in != null) {
 					in.close();
 				}
 			}
 			catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 	}

 	/**
 	 * 复制整个目录的内容
 	 * 
 	 * @param srcDirName		待复制目录的目录名
 	 * @param destDirName		目标目录名
 	 * @param overwrite			如果目标目录存在，是否覆盖
 	 * @return		如果复制成功返回true，否则返回false
 	 */
 	public static boolean copyDir(String srcDirName, String destDirName, boolean overwrite)
 	{
 		// 判断源目录是否存在
 		File srcDir = new File(srcDirName);
 		if (!srcDir.exists()) {
 			return false;
 		}
 		else if (!srcDir.isDirectory()) {
 			return false;
 		}

 		// 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符
 		if (!destDirName.endsWith(File.separator)) {
 			destDirName = destDirName + File.separator;
 		}
 		File destDir = new File(destDirName);
 		// 如果目标文件夹存在
 		if (destDir.exists()) {
 			// 如果允许覆盖则删除已存在的目标目录
 			if (overwrite) {
 				new File(destDirName).delete();
 			}
 			else {
 				return false;
 			}
 		}
 		else {
 			// 创建目的目录
 			if (!destDir.mkdirs()) {
 				return false;
 			}
 		}

 		boolean flag = true;
 		File[] files = srcDir.listFiles();
 		for (int i = 0; i < files.length; i++) {
 			// 复制文件
 			if (files[i].isFile()) {
 				flag = copyFile(files[i].getAbsolutePath(), destDirName + files[i].getName(), overwrite);
 				if (!flag) {
 					break;
 				}
 			}
 			else if (files[i].isDirectory()) {
 				flag = copyDir(files[i].getAbsolutePath(), destDirName + files[i].getName(), overwrite);
 				if (!flag) {
 					break;
 				}
 			}
 		}
 		return flag;
 	}
 	
 	/**
 	 * @Description(功能描述)		: 重命名上传文件
 	 * @author(作者)				: wangliyou
 	 * @date (开发日期)			: 2015年10月3日 下午1:41:32 
 	 * @param fileName
 	 * @return
 	 */
 	public static String reName(String fileName) {
		String formatDate = new SimpleDateFormat("yyyyMMddHHmmssssss").format(DateUtil.getGMTDate());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}
}
