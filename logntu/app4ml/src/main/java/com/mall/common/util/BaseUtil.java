package com.mall.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.mall.common.constant.CommonConstant;
import com.mall.common.constant.JXLConstant;
import com.mall.web.mall.security.vo.CustomUserDetails;

/**
 * 
 * @功能：常用工具类
 * @作者：印鲜刚 @创建日期：2010-04-20
 * 
 */
public class BaseUtil {

	/**
	 * @Description(功能描述) : 返回用户对象 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年9月9日 下午12:11:48
	 * @return
	 */
	public static CustomUserDetails getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object object = auth.getPrincipal();
		if (object != null && object instanceof CustomUserDetails) {
			CustomUserDetails user = (CustomUserDetails) object;
			return user;
		} else {
			return null;
		}
	}

	/**
	 * 判断Map<String,Object>里指定key的value值是否为空
	 * 
	 * @param params
	 * @param key
	 * @return boolean -true:表示value为空;false:表示value为非空
	 */
	public static boolean isEmpty(Map<String, Object> params, String key) {
		if (isEmpty(params))
			return true;
		else {
			if (params.containsKey(key) && !isEmpty(params.get(key)))
				return false;
			return true;
		}
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *            -参数对象
	 * @return boolean -true:表示对象为空;false:表示对象为非空
	 */
	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().equalsIgnoreCase("null") || obj.toString().length() == 0;
	}

	/**
	 * @Description(功能描述) : 是否不为空 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年8月26日 下午2:52:00
	 * @param pObj
	 *            : 判断对象
	 * @return : 不为空返回true 反之false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if ("".equals(pObj)) {
			return false;
		}
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断 {@a lhs} 的值是否等于 {@a rhs} 的值。
	 * 
	 * @author sol
	 */
	public static boolean objectEquals(Object lhs, Object rhs) {
		return (lhs == null && rhs == null) || (lhs != null && lhs.equals(rhs));
	}

	/**
	 * 使用模型驱动的时候将模型驱动vo进行字符串解码
	 * 
	 * @param obj
	 *            待转码的vo
	 * @throws IllegalArgumentException
	 *             参数错误！参数不能为空。。。
	 * @throws IllegalAccessException
	 *             参数错误！
	 * @throws UnsupportedEncodingException
	 *             不支持的编码方式
	 */
	public static void decodeObject(Object obj)
			throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		if (BaseUtil.isEmpty(obj)) {
			return;
		}
		// 取得该对象里面所有定义的字段,并对每个字段进行转码
		for (Field field : obj.getClass().getDeclaredFields()) {
			// 将此对象的 accessible 标志设置为指示的布尔值。(即,当该字段为private时,也可以访问)
			field.setAccessible(true);
			// 回指定对象上此 Field 表示的字段的值。(即,取得传入对象中改字段的值)
			Object fieldObj = field.get(obj);
			if (!BaseUtil.isEmpty(fieldObj)) {
				// 只有在字段为String类型的时候才有中文乱码,因为如果是其他类型的话,在类型转换的时候就出错了
				if (field.getType() == String.class) {
					// 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。(即,将传入的对象里面的这个字段设置为转码后的值)
					field.set(obj, !fieldObj.toString().trim().isEmpty()
							? URLDecoder.decode(fieldObj.toString(), CommonConstant.UTF8) : null);
				}
			}
		}
	}

	/**
	 * 字符串解码
	 * 
	 * @param str
	 *            待转码的字符串
	 * @throws UnsupportedEncodingException
	 *             不支持的编码方式
	 */
	public static void decodeString(String str) throws UnsupportedEncodingException {
		str = URLDecoder.decode(str, CommonConstant.UTF8);
	}

	/**
	 * 将boolean类型转换成中文字符串
	 * 
	 * @param whether
	 *            -boolean类型
	 * @return
	 */
	public static String booleanToChineseString(boolean whether) {
		if (whether)
			return "是";
		else
			return "否";
	}

	/**
	 * 将boolean类型转换成中指定文字符串
	 * 
	 * @param whether
	 * @param trueString
	 * @param falseString
	 * @return
	 */
	public static String booleanToChineseString(boolean whether, String trueString, String falseString) {
		if (whether)
			return trueString;
		else
			return falseString;
	}

	/**
	 * 用于处理编号（应用与公文等）
	 * 
	 */
	public static String idCode(String profix, String type, String year, String seq, int len) {
		String idCode = profix + type + year;
		for (int i = seq.length(); i < len; i++) {
			seq = "0" + seq;
		}
		return idCode + seq;
	}

	public static ServletOutputStream getRespOutputStream(HttpServletResponse response, String fileName)
			throws IOException {
		response.setCharacterEncoding(JXLConstant.UTF_ENCODING);
		response.setContentType(JXLConstant.CONTENT_TYPE);
		response.setHeader(JXLConstant.HEADER, new StringBuffer(JXLConstant.OUTPUTSTREAM_DOWN).append(";filename=")
				.append(fileName).append(JXLConstant.EXCEL_SUFFIX).toString());
		return response.getOutputStream();
	}

	public static WritableCellFormat getWcFormat() throws WriteException {
		WritableCellFormat wcFormat = new WritableCellFormat();
		wcFormat.setBorder(JXLConstant.BORDER_ALL, JXLConstant.BORDER_LINE_THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		return wcFormat;
	}

	public static WritableWorkbook getWritableWorkbook(Workbook workBook, ServletOutputStream outputStream)
			throws IOException {
		// 复制workbook并保存到outputStream
		WritableWorkbook copy = Workbook.createWorkbook(outputStream, workBook, getWorkbookSettings());
		return copy;
	}

	public static WorkbookSettings getWorkbookSettings() {
		WorkbookSettings settings = new WorkbookSettings();
		settings.setWriteAccess(null);
		return settings;
	}

	public static boolean isDigit(String strTemp) {
		if(strTemp==null)
			return false;
		for (int i = 0; i < strTemp.length(); i++) {
			if (!Character.isDigit(strTemp.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static int strToInt(String strTemp) {
		int i = 0;
		if (!BaseUtil.isEmpty(strTemp)) {
			if (BaseUtil.isDigit(strTemp)) {
				i = Integer.parseInt(strTemp);
			}
		}
		return i;
	}

	public static int objectToInt(Object strTemp) {
		int i = 0;
		if (!BaseUtil.isEmpty(strTemp)) {
			if (BaseUtil.isDigit(strTemp.toString())) {
				i = Integer.parseInt(strTemp.toString());
			}
		}
		return i;
	}

	public static byte objectToByte(Object strTemp) {
		byte i = 0;
		if (!BaseUtil.isEmpty(strTemp)) {
			if (BaseUtil.isDigit(strTemp.toString())) {
				i = Byte.parseByte(strTemp.toString());
			}
		}
		return i;
	}

	/**
	 * 字符串转unicode
	 * 
	 * @param src
	 * @return
	 */
	public static String str2Unicode(String src) {
		String[] temp = new String[src.length()];
		String unicodeStr = "";
		for (int i = 0; i < src.length(); i++) {
			temp[i] = Integer.toHexString(src.charAt(i) & 0xffff);
			unicodeStr += "\\u" + temp[i];
		}
		return unicodeStr;
	}

	/**
	 * @Description(功能描述) : 获取某字符存在个数 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2016年1月13日 上午9:36:25
	 */
	public static int getOccur(String src, String find) {
		int o = 0;
		int index = -1;
		while ((index = src.indexOf(find, index)) > -1) {
			++index;
			++o;
		}
		return o;
	}
	public static int null2zero(Integer obj) {
		return obj == null ? 0 : obj.intValue();
	}
}
