/**
 * 
 */
package com.mall.web.admin.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.domain.SysDictionary;

/**
 * @功能：系统字典数据访问层
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 */
@Repository("sysDictionaryDao")
public class SysDictionaryDao extends BaseDao<SysDictionary> {

	/**
	 * 根据条件获取系统参数总记录数
	 * 
	 * @param params
	 * @return
	 */
	public int getSysDictionaryTotalRows(Map<String, Object> params)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT COUNT(*) FROM SysDictionary sd WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "cnName")) {
				params.put("cnName", "%" + params.get("cnName") + "%");
				hql.append("AND sd.cnName like :cnName ");
			}
			if (!BaseUtil.isEmpty(params, "name")) {
				params.put("name", "%" + params.get("name") + "%");
				hql.append("AND sd.name like :name ");
			}
			return this.getTotalRows(hql.toString(), params);
		} else
			return this.getTotalRows(params);
	}

	/**
	 * 根据条件获取系统参数记录
	 * 
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<SysDictionary> findAllSysDictionaries(
			Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT DISTINCT sd FROM SysDictionary sd WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "cnName")) {
				params.put("cnName", "%" + params.get("cnName") + "%");
				hql.append("AND sd.cnName like :cnName ");
			}
			if (!BaseUtil.isEmpty(params, "name")) {
				params.put("name", "%" + params.get("name") + "%");
				hql.append("AND sd.name like :name ");
			}
			return this.findAllEntitiesByCondition(hql.toString(), params,
					curPage, pageSize);
		} else
			return this.findAllEntitiesByCondition(params, curPage, pageSize);
	}

	/**
	 * 通过系统字典ID、英文名称和中文名称查找系统字典
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public SysDictionary findSysDictionaryByIdAndName(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysDictionary WHERE id<> :id AND (name = :name OR cnName = :cnName )");
		List<SysDictionary> sysDictionaries = this.findAllEntitiesByCondition(
				hql.toString(), params);
		if (!BaseUtil.isEmpty(sysDictionaries) && sysDictionaries.size() > 0)
			return sysDictionaries.get(0);
		return null;

	}

	/**
	 * 获取数据库时间
	 * 
	 * @return
	 */
	public Date getDBTime() throws FrameworkException {
		return (Date) hibernateTemplate.find(
				"SELECT CURRENT_TIMESTAMP() FROM SysDictionary").get(0);
	}

}
