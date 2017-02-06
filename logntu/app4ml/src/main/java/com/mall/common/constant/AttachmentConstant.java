package com.mall.common.constant;

import java.io.File;

import javax.activation.FileTypeMap;

/**
 * 附件的配置常量类
 * 
 * @author 潘瑞峥
 * @date 2010-12-11
 */
public final class AttachmentConstant {

	private AttachmentConstant() {
	}

	/** 系统存放附件properties的路径 */
	public static final String PROPS_CONF_PATH = "attachment/attachment.properties";

	/** 文件上传存放路径的属性名 */
	public static final String ATTR_UPLOAD_PATH = "attachment.root";

	/** 文件路径与文件之间的分隔(右斜杠) */
	public static final String SIGN_FILE_RIGHT_SPACE = "\\";

	/** 文件路径与文件之间的分隔(左斜杠) */
	public static final String SIGN_FILE_LEFT_SPACE = "/";

	/** 缓冲大小 */
	public static final int BUFFER_SIZE = 4 * 1024;

	/**
	 * 文件下载等信息参数
	 */
	public static final String UTF_ENCODING = "UTF-8";
	public static final String ISO_ENCODING = "ISO8859-1";
	public static final String CONTENT_TYPE = "application/x-download;charset=utf-8";
	public static final String HEADER = "Content-Disposition";

	/** 打开文件 */
	public static final String OUTPUTSTREAM_OPEN = "inline";

	/** 下载文件 */
	public static final String OUTPUTSTREAM_DOWN = "attachment";

	/** header 浏览器内核信息的key */
	public static final String HEADER_USER_AGENT = "USER-AGENT";

	/** header ie */
	public static final String HEADER_BROWSER_MSIE = "MSIE";

	/** header Mozilla */
	public static final String HEADER_BROWSER_MOZILLA = "MOZILLA";

	/** header Safari */
	public static final String HEADER_BROWSER_SAFARI = "SAFARI";

	/** header Opera */
	public static final String HEADER_BROWSER_OPERA = "OPERA";
	
	/** 文件上传的最大大小(B为单位) */
	public static final long FILE_SIZE_MAX_B = 10 * 1024 * 1024;
	
	/** 文件上传的最大大小(M为单位) */
	public static final long FILE_SIZE_MAX_M = 10;

	//	public static void main(String[] args) {
	//		String path = "E:\\temDesk\\资源\\2010\\ff8080812cd5939d012cd59491a50003";
	//		File file = new File(path);
	//		System.err.println(file.length());
	//	}

	//	public static String bytesToHexString(byte[] src) {
	//		StringBuilder stringBuilder = new StringBuilder();
	//		if (src == null || src.length <= 0) {
	//			return null;
	//		}
	//		for (int i = 0; i < src.length; i++) {
	//			int v = src[i] & 0xFF;
	//			String hv = Integer.toHexString(v);
	//			if (hv.length() < 2) {
	//				stringBuilder.append(0);
	//			}
	//			stringBuilder.append(hv);
	//		}
	//		return stringBuilder.toString();
	//	}
	//
	//	public static void main(String[] args) throws Exception {
	//		FileInputStream is = new FileInputStream("E:\\temDesk\\资源\\2010\\ff8080812cd5939d012cd59491a50003");
	//		byte[] b = new byte[3];
	//		is.read(b, 0, b.length);
	//		System.out.println(bytesToHexString(b));
	//
	//	}

	public static void main(String args[]) {
		FileTypeMap map = FileTypeMap.getDefaultFileTypeMap();
		String path = "E:\\temDesk\\资源\\2010";
		File dir = new File(path);
		System.err.println("===" + dir.listFiles());
		File files[] = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			System.out.println(file.getName() + ": " + map.getContentType(file));
		}
	}

}