package com.mall.web.admin.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.mall.common.exception.FrameworkException;


public class FormValidate {
	
	/**
	 * 参数验证
	 * @param value
	 * @param msg
	 * @throws FrameworkException 
	 */
	public static void validNull(String value,String msg) throws FrameworkException{
		if(StringUtils.isBlank(value)){
			throw new FrameworkException(msg);
		}
	} 
	
	/**
	 * 判断字符串长度
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws FrameworkException
	 */
	public static void  validLen(String value,Integer minLen,Integer maxLen,String msg) throws FrameworkException{
		int len =StringUtils.isNotBlank(value) ? value.length(): 0; 
		if( len < minLen ||  len > maxLen){
			throw new FrameworkException(msg);
		}
	}
	
	/**
	 * 验证是否数字
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws FrameworkException
	 */
	public static void  validNumber(String value,String msg) throws FrameworkException{
		if(!NumberUtils.isNumber(value)){
			throw new FrameworkException(msg);
		}
	}
		
	
	/**
	 * 验证是否int 数字
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws FrameworkException
	 */
	public static void  validInt(String value,String msg) throws FrameworkException{
		try{
			Integer.parseInt(value);
		}catch(Exception e){
			throw new FrameworkException(msg);
		}
	}
	
	
	
	/**
	 * 验证是否Float 数字
	 * @param value
	 * @param minLen
	 * @param maxLen
	 * @param msg
	 * @throws FrameworkException
	 */
	public static void  validFloat(String value,String msg) throws FrameworkException{
		try{
			Float.parseFloat(value);
		}catch(Exception e){
			throw new FrameworkException(msg);
		}
	}
}
