/**
 * 
 */
package com.mall.web.admin.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.ContextLoader;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.domain.SysBusinessDictionary;
import com.mall.web.admin.common.domain.SysBusinessDictionaryDetail;
import com.mall.web.admin.common.domain.SysDictionary;
import com.mall.web.admin.security.vo.DictionaryBean;
import com.mall.web.admin.system.service.SysBusinessDictionaryService;
import com.mall.web.admin.system.service.SysDictionaryService;


/**
 * @功能说明：加载的系统参数、业务字典类
 * @作者： 印鲜刚
 * @创建日期： 2010-4-30
 */
@SuppressWarnings("unchecked")
public class SystemResourceUtil {
	private static Logger logger = Logger.getLogger(SystemResourceUtil.class);
	@Resource(name = "sysDictionaryService")
	private SysDictionaryService sysDictionaryService;
	@Resource(name = "sysBusinessDictionaryService")
	private SysBusinessDictionaryService sysBusinessDictionaryService;
	private static SystemResourceUtil instance;
	//存放系统参数和业务字典
	private static Map dictionaryParams = new HashMap();

	/**
	 * 利用单利模式获取一个SystemResource对象
	 * @return SystemResource
	 * 		-SystemResource对象
	 * @throws FrameworkException
	 */
	public static synchronized SystemResourceUtil getInstance() throws FrameworkException {
		if (BaseUtil.isEmpty(instance))
			instance = (SystemResourceUtil) ContextLoader.getCurrentWebApplicationContext().getBean("systemResource");
		return instance;
	}

	/**
	 * 获取数据库时间
	 * @return
	 * @throws FrameworkException
	 */
	public Date getDBTime() throws FrameworkException {
		return sysDictionaryService.getDBTime();
	}

	/**
	 * 加载系统参数到内存中
	 * @param servletContext 
	 * @throws FrameworkException
	 */
	public void loadSysDictionary() throws FrameworkException {
		logger.info("开始加载系统参数......");
		List<SysDictionary> sysDictionaries = sysDictionaryService.findAllSysDictionaries();
		for (SysDictionary sysDictionary : sysDictionaries) {
			dictionaryParams.put("SYS_" + sysDictionary.getName(), sysDictionary.getValue());
		}
		logger.info("加载系统参数成功完成......");
	}

	/**
	 * 加载业务字典到内存中
	 * @throws FrameworkException
	 */

	public void loadBusinessDictionary() throws FrameworkException {
		logger.info("开始加载业务字典......");
		List<SysBusinessDictionary> businessDictionaries = sysBusinessDictionaryService.findAllBusinessDictionaries();
		for (SysBusinessDictionary businessDictionary : businessDictionaries) {
			List<DictionaryBean> paramBeans = new ArrayList<DictionaryBean>();
			List<SysBusinessDictionaryDetail> details = businessDictionary.getBusinessDictionaryDetails();
			for (SysBusinessDictionaryDetail detail : details) {
				DictionaryBean bean = new DictionaryBean();
				BeanUtils.copyProperties(detail, bean);
				paramBeans.add(bean);
			}
			dictionaryParams.put("BUS_" + businessDictionary.getEnName(), paramBeans);
		}
		logger.info("加载业务字典成功完成......");
	}

	/**
	 * 从内存中获取指定系统参数的值
	 * @param key
	 * 		-系统字典key
	 * @return String
	 * 		-返回系统字典value
	 */
	public String getResourceAsString(String key) throws FrameworkException {
		if (!dictionaryParams.keySet().contains("SYS_" + key))
			return null;
		return (String) dictionaryParams.get("SYS_" + key);
	}

	/**
	 * 从内存中获取指定业务类型的业务字典集合
	 * @param key
	 * 		-业务字典类别
	 * @return
	 * 		-返回特定类别的值集合
	 * @throws FrameworkException
	 */
	public List<DictionaryBean> getResourceAsList(String key) throws FrameworkException {
		if (!dictionaryParams.keySet().contains("BUS_" + key))
			return new ArrayList<DictionaryBean>();
		return (List<DictionaryBean>) dictionaryParams.get("BUS_" + key);

	}

	/**
	 * 获取业务字典对应type,value的中文名称
	 * @param type
	 * 		-业务字典类型
	 * @param value
	 * 		-业务字典值
	 * @return
	 * @throws FrameworkException
	 */
	public String getCnNameOfBusinessDictionary(String type, String value) throws FrameworkException {
		if (BaseUtil.isEmpty(value))
			return null;
		List<DictionaryBean> paramBeans = this.getResourceAsList(type);
		for (DictionaryBean paramBean : paramBeans) {
			if (value.equalsIgnoreCase(paramBean.getValue()))
				return paramBean.getName();
		}
		return null;
	}
}
