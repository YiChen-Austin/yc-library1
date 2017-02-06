package com.mall.common.constant;

import com.mall.common.util.DateUtil;

/**
 * 
 * @功能：应用框架常量类
 * @作者：印鲜刚
 * @创建日期：2004-4-24
 * 
 */
public class CommonConstant {
	/**
	 * 应用框架DES密钥相关常量
	 */
	// DES密钥默认常量
	public static final String DES_KEY_DEFAULT = "8uhvsoft";

	/**
	 * 应用框架验证码常量
	 */
	// 系统默认不启用验证码机制(系统字典相关参数值，只能是‘enabled’，‘disable’)
	public static final String VERIFICATION_STATUS_ENABLED = "enabled";
	// 验证码随机数临界范围常量
	// public static final String VERIFICATION_CHECK_CODE_DEFAULT =
	// "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String VERIFICATION_CHECK_CODE_DEFAULT = "0123456789";

	/**
	 * 应用框架系统日志及异常常量
	 */
	// 文件分隔符
	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");
	// 用户当前工作目录
	public static final String USER_DIR = System.getProperty("user.dir");
	// 系统日志文件默认路径
	public static final String SYSTEM_LOG_PATH_DEFAULT = USER_DIR + FILE_SEPARATOR + "logs";
	// 系统日志文件中Dao层发生异常时描述信息
	public static final String SYSTEM_LOG_DAO_MESSAGE = "Dao错误";
	// 系统日志文件中Service层发生异常时描述信息
	public static final String SYSTEM_LOG_SERVICE_MESSAGE = "Service错误";
	// 系统日志文件中Action层发生异常时描述信息
	public static final String SYSTEM_LOG_ACTION_MESSAGE = "Action错误";
	// 系统日志文件中其他地方发生异常时描述信息
	public static final String SYSTEM_LOG_OTHER_MESSAGE = "其他错误";
	// 系统日志文件定位类文件描述符
	public static final String SYSTEM_LOG_CLASS_POSITION = "className:";
	// 系统日志文件定位类方法描述符
	public static final String SYSTEM_LOG_METHOD_POSITION = "methodName:";
	// 系统日志文件定位类行号描述符
	public static final String SYSTEM_LOG_LINENUMBER_POSITION = "lineNumber:";
	// 系统日志文件定位具体异常信息描述符
	public static final String SYSTEM_LOG_ERRORTYPE_POSITION = "errorDescription:";
	// 显示给用户的解决应用错误的方式描述信息
	public static final String SYSTEM_LOG_CONTACT_DESCRIPTION = "应用程序内部错误,请与开发商联系!";
	// 系统日志文件后最
	public static final String SYSTEM_LOG_SUFFIX = ".log";
	// 系统日志文件前缀名称
	public static final String SYSTEM_LOG_PREFIX = "framework";
	// 系统日志文件只记录拦截包发生的异常信息
	public static final String SYSTEM_LOG_INTERCEPTOR_PACKAGE = "com.unicom";
	// 定位Dao层发生异常常量
	public static final String SYSTEM_LOG_USER_DAO = "Dao";
	// 定位Service层发生异常常量
	public static final String SYSTEM_LOG_USER_SERVICE = "Service";
	// 定位Action层发生异常常量
	public static final String SYSTEM_LOG_USER_ACTION = "Action";
	// 定位其他地方发生异常常量
	public static final String SYSTEM_LOG_USER_OTHER = "Other";
	// 定位cookie标示
	public static final String SYSTEM_COOKIE_TOKEN = "cookieToken";

	/**
	 * 应用框架日期常量
	 */
	// 年
	public static final String DATE_YEAR = "yyyy";
	// 年月
	public static final String DATE_YEAR_MONTH = "yyyyMM";
	// 简单年月日日期格式
	public static final String DATE_SHORT_SIMPLE_FORMAT = "yyyyMMdd";
	// 简单年月日日期格式
    public static final String DATE_SHORT_SIMPLE_FORMAT_4_HH_MM_SS = "HHmmss";
	// 年月日日期格式
	public static final String DATE_SHORT_FORMAT = "yyyy-MM-dd";
	// 中文年月日日期格式
	public static final String DATE_SHORT_CHN_FORMAT = "yyyy年MM月dd日";
	// 年月日时日期格式
	public static final String DATE_WITHHOUR_FORMAT = "yyyy-MM-dd HH";
	// 中文年月日时日期格式
	public static final String DATE_WITHHOUR_CHN_FORMAT = "yyyy年MM月dd日 HH";
	// 年月日时分日期格式
	public static final String DATE_YYYY_MM_DDhhmmss = "yyyy-MM-dd HH:mm:ss";
	// 中文年月日时分日期格式
	public static final String DATE_WITHMINUTE_CHN_FORMAT = "yyyy年MM月dd日 HH:mm";
	// 年月日时分秒日期格式
	public static final String DATE_WITHSECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 中文年月日时分秒日期格式
	public static final String DATE_WITHSECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
	//紧凑格式
	public static final String DATE_WITHSECOND_G_FORMAT = "yyyyMMddHHmmss";
	// 年月日时分秒毫秒日期格式
	public static final String DATE_WITHMILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	// 中文年月日时分秒毫秒日期格式
	public static final String DATE_WITHMILLISECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss.S";

	/**
	 * 系统标志位
	 */
	// 表示不可编辑
	public static final Character FLAG_IMMUTABLE = '0';
	// 表示可编辑
	public static final Character FLAG_VARIABLE = '1';
	/**
	 * Session中保存的用户信息
	 */
	public static final String SESSION_USER = "loginUser";
	/**
	 * servletcontext中存储的在线用户列表
	 */
	public static final String SERVLETCONTEXT_ONLINE_USER = "onlineUserList";
	/**
	 * 字符编码
	 */
	public static final String UTF8 = "utf-8";
	/**
	 * 邮件异常类型
	 */
	// 主送目标地址错误
	public static final String MAIL_EXCEPTION_TO = "1";
	// 抄送目标地址错误
	public static final String MAIL_EXCEPTION_CC = "2";
	// 密送目标地址错误
	public static final String MAIL_EXCEPTION_BCC = "3";
	/**
	 * 信息管理类型
	 */
	// 论坛
	public static final String COMMON_CATEGORY_DISCUSSION = "discussion";
	// 信息
	public static final String COMMON_CATEGORY_NEWS = "news";

	// 移动网站
	public static final String MOBILE_URL = "http://m.qqgo.cc";
	
	/**
	 * 第三方登录
	 */
	public static final String Oauth_QQ = "1";
	public static final String Oauth_Weibo = "2";
	/**
	 * cookie
	 */
	public static final String COOKIE_AUTH = "MARKAUTH";
	
	public static void main(String[] args){
		System.out.println(DateUtil.dateToString(DateUtil.getGMTDate(),CommonConstant.DATE_WITHSECOND_G_FORMAT));
	}
}