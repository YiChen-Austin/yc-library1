/**
 * 
 */
package com.mall.common.exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;

/**
 * @功能说明：应用框架统一异常处理类
 * @作者： 印鲜刚
 * @创建日期： 2010-4-30
 */
@SuppressWarnings("serial")
public class FrameworkException extends Exception {
	private static Logger logger = Logger.getLogger(FrameworkException.class);
	//产生系统日志文件的默认路径
	private static final String defaultSystemLogPath = CommonConstant.SYSTEM_LOG_PATH_DEFAULT;
	//系统日志默认DAO扩展信息
	private String expandMessage = CommonConstant.SYSTEM_LOG_DAO_MESSAGE;
	//将所有业务异常上塑造型到Exception类型的异常
	private Exception exception;
	//日志文件输出流
	private PrintWriter printWriter;

	/**
	 * 默认无参构造方法
	 */
	public FrameworkException() {
		this(null, null);
	}

	/**
	 * 默认消息参数构造方法
	 * @param message
	 *		-异常消息
	 */
	public FrameworkException(String message) {
		this(null, message);
	}

	/**
	 * 默认Exception对象参数构造方法
	 * @param e
	 *		-异常类型
	 */
	public FrameworkException(Exception e) {
		this(e, null);
	}

	/**
	 * 默认Exception对象参数，消息参数构造方法
	 * @param e
	 * 		-异常类型
	 * @param message
	 * 		-异常消息
	 */

	public FrameworkException(Exception e, String message) {
		if (!BaseUtil.isEmpty(e))
			this.exception = e;
		else
			this.exception = this;
		if (!BaseUtil.isEmpty(message))
			this.expandMessage = message;
	}

	/**
	 * 向日志文件写入异常日志。
	 * @throws Exception
	 */
	public void log() {
		this.getPrintWriter();
		StackTraceElement[] elements = exception.getStackTrace();
		for (StackTraceElement element : elements) {
			if (element.getClassName().indexOf(CommonConstant.SYSTEM_LOG_INTERCEPTOR_PACKAGE) < 0)
				continue;
			String fileName = element.getFileName();
			StringBuffer sb = new StringBuffer();
			sb.append(DateUtil.dateToString());
			sb.append("【");
			sb.append(this.extendErrorType(fileName));
			sb.append("】");
			sb.append(CommonConstant.SYSTEM_LOG_CLASS_POSITION);
			sb.append(element.getClassName());
			sb.append("；");
			sb.append(CommonConstant.SYSTEM_LOG_METHOD_POSITION);
			sb.append(element.getMethodName());
			sb.append("； ");
			sb.append(CommonConstant.SYSTEM_LOG_LINENUMBER_POSITION);
			sb.append(element.getLineNumber());
			sb.append("；");
			sb.append(CommonConstant.SYSTEM_LOG_ERRORTYPE_POSITION);
			sb.append(exception.getMessage());
			this.printWriter.println(sb.toString());
			this.printWriter.flush();
		}
		this.printWriter.close();
	}

	/**
	 * 初始化指向日志文件的PrintWriter输出流
	 */
	private void getPrintWriter() {
		try {
			String systemLogPath = null;//SystemResourceUtil.getInstance().getResourceAsString("SYSTEM_LOG_PATH");
			if (BaseUtil.isEmpty(systemLogPath))
				systemLogPath = defaultSystemLogPath;
			logger.debug("systemLogPath=" + systemLogPath);
			File directory = new File(systemLogPath);
			if (!directory.exists())
				directory.mkdirs();
			File logFile = new File(directory.getAbsolutePath() + File.separator + CommonConstant.SYSTEM_LOG_PREFIX
					+ DateUtil.dateToString(CommonConstant.DATE_SHORT_FORMAT) + CommonConstant.SYSTEM_LOG_SUFFIX);
			if (!logFile.exists())
				logFile.createNewFile();
			this.printWriter = new PrintWriter(new FileOutputStream(logFile, true));
		} catch (Exception agpe) {
			logger.debug("加载日志文件出错");

		}
	}

	/**
	 * 返回指定JAVA文件发生的异常类型
	 * @param fileName
	 * 		-发生异常的JAVA文件
	 * @return String
	 * 		-系统日志文件定为的异常描述信息
	 */
	private String extendErrorType(String fileName) {
		String errorType = this.expandMessage;
		if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_DAO) > 0) {
			errorType = CommonConstant.SYSTEM_LOG_DAO_MESSAGE;
		} else if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_SERVICE) > 0) {
			errorType = CommonConstant.SYSTEM_LOG_SERVICE_MESSAGE;
		} else if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_ACTION) > 0) {
			errorType = CommonConstant.SYSTEM_LOG_ACTION_MESSAGE;
		} else {
			errorType = CommonConstant.SYSTEM_LOG_OTHER_MESSAGE;
		}
		return errorType;
	}
}
