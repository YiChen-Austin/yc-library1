package com.mall.web.admin.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.domain.SysBusinessDictionaryDetail;


/**
 * 
 * @功能说明：业务字典明细数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-5
 */
@Repository("sysBusinessDictionaryDetailDao")
public class SysBusinessDictionaryDetailDao extends
		BaseDao<SysBusinessDictionaryDetail> {
	public List<SysBusinessDictionaryDetail> findBusinessDictionaryDetails(
			Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT model FROM SysBusinessDictionaryDetail model where 1=1 ");
		if (BaseUtil.isEmpty(params)) {
			return this.findAllEntitiesByCondition(hql.toString(), params,
					curPage, pageSize);
		}
		if (!BaseUtil.isEmpty(params.get("name"))) {
			hql.append("and model.name=:name ");
		}
		if (!BaseUtil.isEmpty(params.get("value"))) {
			hql.append("and model.value=:value ");
		}
		return this.findAllEntitiesByCondition(hql.toString(), params, curPage,
				pageSize);
	}

}
