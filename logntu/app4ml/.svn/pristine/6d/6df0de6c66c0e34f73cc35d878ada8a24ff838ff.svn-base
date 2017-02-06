package com.mall.web.admin.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.dao.SysBusinessDictionaryDao;
import com.mall.web.admin.common.domain.SysBusinessDictionary;
import com.mall.web.admin.common.domain.SysBusinessDictionaryDetail;
import com.mall.web.admin.common.utils.SystemResourceUtil;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：业务字典业务层
 * @作者：雷运梅、潘瑞峥
 * @创建日期：2010-6-4
 */
@Service("sysBusinessDictionaryService")
public class SysBusinessDictionaryService {
	@Resource(name = "sysBusinessDictionaryDao")
	private SysBusinessDictionaryDao sysBusinessDictionaryDao;
	@Resource(name = "sysBusinessDictionaryDetailService")
	private SysBusinessDictionaryDetailService sysBusinessDictionaryDetailService;

	/**
	 * 获取所有的业务字典实体
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysBusinessDictionary> findAllBusinessDictionaries() throws FrameworkException {
		return this.sysBusinessDictionaryDao.findAll();
	}

	/**
	 * 通过业务字典ID删除业务字典实体
	 * 
	 * @param businessDictionaryId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteBusinessDictionaryById(String businessDictionaryId) throws FrameworkException {
		this.sysBusinessDictionaryDao.deleteById(businessDictionaryId);
		SystemResourceUtil.getInstance().loadBusinessDictionary();
	}

	/**
	 * 根据条件获取业务字典参数记录
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	@DataSource(value = "admin")
	public List<SysBusinessDictionary> findAllBusinessDictionaries(Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		return this.sysBusinessDictionaryDao.findAllBusinessDictionaries(params, curPage, pageSize);
	}

	/**
	 * 通过业务字典ID获取业务字典实体
	 * 
	 * @param businessDictionaryId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysBusinessDictionary findBusinessDictionaryById(String businessDictionaryId) throws FrameworkException {
		return this.sysBusinessDictionaryDao.get(businessDictionaryId);
	}

	/**
	 * 保存业务字典
	 * 
	 * @param businessDictionary
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public void save(SysBusinessDictionary businessDictionary) throws FrameworkException {
		sysBusinessDictionaryDao.save(businessDictionary);
	}

	/**
	 * 修改业务字典
	 * 
	 * @param businessDictionary
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void updateBusinessDictionary(SysBusinessDictionary businessDictionary) throws FrameworkException {
		this.sysBusinessDictionaryDao.update(businessDictionary);
	}

	/**
	 * 根据条件获取业务字典参数总记录数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getBusinessDictionaryTotalRows(Map<String, Object> params) throws FrameworkException {
		return this.sysBusinessDictionaryDao.getBusinessDictionaryTotalRows(params);
	}

	/**
	 * 通过业务字典英文名称和中文名称查找业务字典
	 * (如果为空，表示可使用；不为空，表示已经存在此名称)
	 * 
	 * @param enName
	 * @param cnName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysBusinessDictionary findBusinessDictionaryByName(String enName, String cnName) throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("AND__enName__e", enName);
		params.put("OR__cnName__e", cnName);
		List<SysBusinessDictionary> businessDictionaries = this.sysBusinessDictionaryDao.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(businessDictionaries) && businessDictionaries.size() > 0)
			return businessDictionaries.get(0);
		return null;
	}

	/**
	 * 通过业务字典ID、英文名称和中文名称查找业务字典
	 * 检索除此id外已有的名称
	 * (如果为空，表示可使用；不为空，表示已经存在此名称)
	 * 
	 * @param id
	 * @param enName
	 * @param cnName
	 * @return
	 * @throws FrameworkException 
	 */
	@DataSource(value = "admin")
	public SysBusinessDictionary findBusinessDictionaryByIdAndName(String id, String enName, String cnName)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("enName", enName);
		params.put("cnName", cnName);
		params.put("id", id);
		return this.sysBusinessDictionaryDao.findBusinessDictionaryByIdAndName(params);
	}

	/**
	 * 新增业务字典及其字典明细
	 * 
	 * @param businessDictionary
	 * @param list
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void saveBusinessDictionary(SysBusinessDictionary businessDictionary, List<SysBusinessDictionaryDetail> list)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(list)) {
			List<SysBusinessDictionaryDetail> businessDictionaryDetails = new ArrayList<SysBusinessDictionaryDetail>();
			for (SysBusinessDictionaryDetail businessDictionaryDetail : list) {
				businessDictionaryDetail.setBusinessDictionary(businessDictionary);
				businessDictionaryDetails.add(businessDictionaryDetail);
			}
			businessDictionary.setBusinessDictionaryDetails(businessDictionaryDetails);
		}
		this.save(businessDictionary);
		SystemResourceUtil.getInstance().loadBusinessDictionary();
	}

	/**
	 * 修改业务字典及其字典明细
	 * 
	 * @param bd
	 * @param insertVal
	 * @param updateVal
	 * @param deleteVal
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	@Transactional
	public void updateBusinessDictionaryDetail(SysBusinessDictionary bd, String insertVal, String updateVal,
			String deleteVal) throws FrameworkException, Exception {
		// 业务字典明细list
		List<SysBusinessDictionaryDetail> businessDictionaryDetails = new ArrayList<SysBusinessDictionaryDetail>();
		// 给业务字典赋新值
		SysBusinessDictionary businessDictionary = this.findBusinessDictionaryById(bd.getId());
		businessDictionary.setCnName(bd.getCnName());
		businessDictionary.setEnName(bd.getEnName());
		businessDictionary.setFlag(bd.getFlag());
		businessDictionary.setRemark(bd.getRemark());
		// 保存业务字典
		this.updateBusinessDictionary(businessDictionary);

		// 新增明细
		if (!"".equals(insertVal) && null != insertVal) {
			for (String inVal : insertVal.split(",")) {
				if (!"".equals(inVal) && null != inVal) {
					String[] val = inVal.split(";");
					// 明细name、value、remark
					String name = null;
					String value = null;
					String remark = null;
					if (!"".equals(val[0]) && null != val[0]) {
						name = val[0].trim();
					}
					if (!"".equals(val[1]) && null != val[1]) {
						value = val[1].trim();
					}
					// 明细remark存在
					if (3 == val.length) {
						if (!"".equals(val[2]) && null != val[2]) {
							remark = val[2].trim();
						}
					}
					// 给明细赋值
					SysBusinessDictionaryDetail businessDictionaryDetail = new SysBusinessDictionaryDetail();
					businessDictionaryDetail.setName(name);
					businessDictionaryDetail.setValue(value);
					businessDictionaryDetail.setRemark(remark);
					businessDictionaryDetail.setBusinessDictionary(businessDictionary);
					businessDictionaryDetails.add(businessDictionaryDetail);
					businessDictionary.setBusinessDictionaryDetails(businessDictionaryDetails);
					this.updateBusinessDictionary(businessDictionary);
				}
			}
		}

		// 修改明细
		if (!"".equals(updateVal) && null != updateVal) {
			for (String upVal : updateVal.split(",")) {
				if (!"".equals(upVal) && null != upVal) {
					String[] val = upVal.split(";");
					// 明细id、name、value、remark
					String id = null;
					String name = null;
					String value = null;
					String remark = null;
					if (!"".equals(val[0]) && null != val[0]) {
						id = val[0].trim();
					}
					if (!"".equals(val[1]) && null != val[1]) {
						name = val[1].trim();
					}
					if (!"".equals(val[2]) && null != val[2]) {
						value = val[2].trim();
					}
					// 明细remark存在
					if (4 == val.length) {
						if (!"".equals(val[3]) && null != val[3]) {
							remark = val[3].trim();
						}
					}
					// 给明细赋值
					SysBusinessDictionaryDetail businessDictionaryDetail = sysBusinessDictionaryDetailService
							.findBusinessDictionaryDetailById(id);
					businessDictionaryDetail.setName(name);
					businessDictionaryDetail.setValue(value);
					businessDictionaryDetail.setRemark(remark);
					businessDictionaryDetail.setBusinessDictionary(businessDictionary);
					businessDictionaryDetails.add(businessDictionaryDetail);
					businessDictionary.setBusinessDictionaryDetails(businessDictionaryDetails);
					this.updateBusinessDictionary(businessDictionary);
				}
			}
		}

		// 删除明细
		if (!"".equals(deleteVal) && null != deleteVal) {
			String id = deleteVal.trim();
			for (String delVal : deleteVal.split(",")) {
				if (!"".equals(delVal) && null != delVal) {
					id = delVal.trim();
					SysBusinessDictionaryDetail businessDictionaryDetail = sysBusinessDictionaryDetailService
							.findBusinessDictionaryDetailById(id);
					businessDictionary.getBusinessDictionaryDetails().remove(businessDictionaryDetail);
					sysBusinessDictionaryDetailService.deleteById(id);
				}
			}
		}
		SystemResourceUtil.getInstance().loadBusinessDictionary();
	}

}