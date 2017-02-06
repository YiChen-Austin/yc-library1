package com.mall.web.admin.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.domain.SysBusinessDictionary;


/**
 * 
 * @功能说明：业务字典数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-5
 */
@Repository("sysBusinessDictionaryDao")
public class SysBusinessDictionaryDao extends BaseDao<SysBusinessDictionary> {

	/**
	 * 根据条件获取业务字典参数总记录数
	 * 
	 * @param params
	 * @throws FrameworkException
	 * @return int
	 */
	public int getBusinessDictionaryTotalRows(Map<String, Object> params) throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT COUNT(*) FROM SysBusinessDictionary sd WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "cnName")) {
				params.put("cnName", "%" + params.get("cnName") + "%");
				hql.append("AND sd.cnName like :cnName ");
			}
			if (!BaseUtil.isEmpty(params, "enName")) {
				params.put("enName", "%" + params.get("enName") + "%");
				hql.append("AND sd.enName like :enName ");
			}
			return this.getTotalRows(hql.toString(), params);
		} else
			return this.getTotalRows(params);
	}

	/**
	 * 根据条件获取业务字典参数记录
	 * 
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @throws FrameworkException
	 * @return
	 */
	public List<SysBusinessDictionary> findAllBusinessDictionaries(Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT DISTINCT sd FROM SysBusinessDictionary sd WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "cnName")) {
				params.put("cnName", "%" + params.get("cnName") + "%");
				hql.append("AND sd.cnName like :cnName ");
			}
			if (!BaseUtil.isEmpty(params, "enName")) {
				params.put("enName", "%" + params.get("enName") + "%");
				hql.append("AND sd.enName like :enName ");
			}
			return this.findAllEntitiesByCondition(hql.toString(), params, curPage, pageSize);
		} else
			return this.findAllEntitiesByCondition(params, curPage, pageSize);
	}

	/**
	 * 通过业务字典ID、英文名称和中文名称查找业务字典
	 * 
	 * @param params
	 * @return  SysBusinessDictionary
	 * @throws FrameworkException
	 */
	public SysBusinessDictionary findBusinessDictionaryByIdAndName(Map<String, Object> params) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysBusinessDictionary WHERE id<> :id AND (enName = :enName OR cnName = :cnName )");
		List<SysBusinessDictionary> businessDictionaries = this.findAllEntitiesByCondition(hql.toString(), params);
		if (!BaseUtil.isEmpty(businessDictionaries) && businessDictionaries.size() > 0)
			return businessDictionaries.get(0);
		return null;

	}
}
