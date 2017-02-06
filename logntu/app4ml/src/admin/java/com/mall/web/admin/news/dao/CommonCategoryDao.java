/**
 * 
 */
package com.mall.web.admin.news.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mall.common.constant.CommonConstant;
import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.news.domain.SysCommonCategory;
import com.mall.web.admin.security.domain.SysOrganization;

/**
 * @功能说明：栏目管理数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-9-18 @
 */
@Repository("commonCategoryDao")
public class CommonCategoryDao extends BaseDao<SysCommonCategory> {
	Logger logger = Logger.getLogger(CommonCategoryDao.class);

	/**
	 * 根据条件获取论坛栏目实体
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public List<SysCommonCategory> findAllDiscussionCategories()
			throws FrameworkException, Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT cc FROM SysCommonCategory cc WHERE cc.flag = :flag");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", CommonConstant.COMMON_CATEGORY_DISCUSSION);
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 通过栏目parentId获取其所有直接子栏目集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonCategory> findDirectSubCommonCategories(String parentId)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysCommonCategory cc ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE cc.commonCategory.id = :parentId ");
			params.put("parentId", parentId);
		}
		hql.append("ORDER BY cc.id,cc.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据条件获取信息栏目实体
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public List<SysCommonCategory> findAllNewsCategories()
			throws FrameworkException, Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT cc FROM SysCommonCategory cc WHERE cc.flag = :flag ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", CommonConstant.COMMON_CATEGORY_NEWS);
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据信息栏目ID及名字,所属公司查找栏目
	 * 
	 * @param id
	 * @param name
	 * @param company
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonCategory> findDiscussionCategoryByIdAndName(String id,
			String name, SysOrganization company) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT cc FROM SysCommonCategory cc WHERE cc.name = :name AND cc.flag = :flag AND cc.company.id = :companyId AND cc.id != :id");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", CommonConstant.COMMON_CATEGORY_DISCUSSION);
		params.put("companyId", company.getId());
		params.put("name", name);
		params.put("id", id);
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据信息栏目ID及名字,所属公司查找栏目
	 * 
	 * @param id
	 * @param name
	 * @param company
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonCategory> findNewsCategoryByIdAndName(String id,
			String name, SysOrganization company) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT cc FROM SysCommonCategory cc WHERE cc.name = :name AND cc.flag = :flag AND cc.company.id = :companyId AND cc.id != :id");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", CommonConstant.COMMON_CATEGORY_NEWS);
		params.put("companyId", company.getId());
		params.put("name", name);
		params.put("id", id);
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据栏目名字查找栏目
	 * 
	 * @param name
	 * @param company
	 * @return
	 */
	public List<SysCommonCategory> findDiscussionCategoryByName(String name,
			SysOrganization company) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT cc FROM SysCommonCategory cc WHERE cc.name = :name AND cc.flag = :flag AND cc.company.id = :companyId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", CommonConstant.COMMON_CATEGORY_DISCUSSION);
		params.put("companyId", company.getId());
		params.put("name", name);
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 通过信息名称判断是否有重复的信息名称
	 * 
	 * @param name
	 * @param company
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonCategory> findNewsCategoryByName(String name,
			SysOrganization company) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT cc FROM SysCommonCategory cc WHERE cc.name = :name AND cc.flag = :flag AND cc.company.id = :companyId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", CommonConstant.COMMON_CATEGORY_NEWS);
		params.put("companyId", company.getId());
		params.put("name", name);
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}
}
