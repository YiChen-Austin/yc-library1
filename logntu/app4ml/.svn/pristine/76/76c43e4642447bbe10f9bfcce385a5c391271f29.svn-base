package com.mall.web.admin.system.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.dao.SysBusinessDictionaryDetailDao;
import com.mall.web.admin.common.domain.SysBusinessDictionaryDetail;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：业务字典明细业务层
 * @作者：潘瑞峥
 * @创建日期：2010-6-4
 */
@Service("sysBusinessDictionaryDetailService")
public class SysBusinessDictionaryDetailService {
	@Resource(name = "sysBusinessDictionaryDetailDao")
	private SysBusinessDictionaryDetailDao sysBusinessDictionaryDetailDao;

	/**
	 * 通过id查询出字典明细，并返回此类
	 * 
	 * @param businessDictionaryDetailId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysBusinessDictionaryDetail findBusinessDictionaryDetailById(
			String businessDictionaryDetailId) throws FrameworkException {
		return sysBusinessDictionaryDetailDao.get(businessDictionaryDetailId);
	}
	@DataSource(value = "admin")
	public SysBusinessDictionaryDetail findBusinessDictionaryDetailByName(
			String name) throws FrameworkException {
		if (BaseUtil.isEmpty(name))
			return null;
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		List<SysBusinessDictionaryDetail> list = sysBusinessDictionaryDetailDao
				.findBusinessDictionaryDetails(params, 0, 1000);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 通过id删除业务字典明细
	 * 
	 * @param id
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public void deleteById(String id) throws FrameworkException {
		sysBusinessDictionaryDetailDao.deleteById(id);
	}
}