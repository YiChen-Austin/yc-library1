/**
 * 
 */
package com.mall.web.admin.category.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.mall.domain.WebCategory;


/**
 * @功能说明：系统菜单数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-8 @
 */
@Repository("baseCategoryDao")
public class BaseCategoryDao extends BaseDao<WebCategory> {
	Logger logger = Logger.getLogger(BaseCategoryDao.class);

	/**
	 * 通过菜单parentId获取其所有直接子菜单集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findDirectSubBaseCategorys(String parentId)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM WebCategory sm ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE sm.category.id = :parentId ");
			params.put("parentId", parentId);
		} else {
			hql.append("WHERE sm.category.id is null ");
		}
		hql.append("ORDER BY sm.id,sm.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	@SuppressWarnings("unchecked")
	public List<WebCategory> findBaseCategorys4Ids(String idStr) {
		String hql = "FROM WebCategory WHERE id IN (" + idStr + ")";
		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	/**
	 * 分类id串转成分类名称
	 * 
	 * @return String
	 */
	public String cate2CateStr(String ids) {
		String sql = "SELECT group_concat(c.`name` SEPARATOR '-') as str from sys_category c where  c.id in ("
				+ ids + ")";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		return list.size() > 0 ? (String) list.get(0).get("str") : "";
	}

	/**
	 * 获取头部菜单列表
	 * 
	 * @param parentId
	 * @param sysUserId
	 * @return
	 */
	public List<WebCategory> findSubBaseCategorys(String parentId)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT sm FROM WebCategory sm ");
		hql.append("WHERE sm.category.id = :parentId ");
		hql.append("AND su.id = :sysUserId ORDER BY sm.orderNo ASC ");
		params.put("parentId", parentId);
		logger.debug("findSubBaseCategorys__hql=" + hql.toString());
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 通过菜单parentId获取其所有直接子菜单集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findDirectSubBaseCategorys(String parentId,
			String menuUrl) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM WebCategory sm ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE sm.category.id = :parentId ");
			params.put("parentId", parentId);
		}
		if (!BaseUtil.isEmpty(menuUrl)) {
			hql.append("WHERE sm.pageUrl= :pageUrl ");
			params.put("pageUrl", menuUrl);
		}
		hql.append("ORDER BY sm.id,sm.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 通过菜单父pageUrl获取其所有直接子菜单集合
	 * 
	 * @param pageUrl
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findDirectSubBaseCategorysBypageUrl(
			String pageUrl) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM WebCategory sm ");
		if (!BaseUtil.isEmpty(pageUrl)) {
			hql.append("WHERE sm.category.pageUrl= :pageUrl ");
			params.put("pageUrl", pageUrl);
		}
		hql.append("ORDER BY sm.id,sm.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	public List<WebCategory> findDirectSubBaseCategorysBypageUrl2(
			String pageUrl) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM WebCategory sm ");
		if (!BaseUtil.isEmpty(pageUrl)) {
			hql.append("WHERE sm.category.pageUrl= :pageUrl ");
			params.put("pageUrl", pageUrl);
		}
		hql.append("ORDER BY sm.id desc,sm.orderNo desc ");
		return this.findAllEntitiesByCondition(hql.toString(), params, 1, 12);
	}

	public List<WebCategory> findDirectSubBaseCategorysBypageUrl_p(
			String pageUrl) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM WebCategory sm ");
		if (!BaseUtil.isEmpty(pageUrl)) {
			hql.append("WHERE sm.category.ck360Category.pageUrl= :pageUrl ");
			params.put("pageUrl", pageUrl);
		}
		hql.append("ORDER BY sm.category.id,sm.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	public List<WebCategory> findDirectSubBaseCategorysBypageUrl(
			String pageUrl, String pPageUrl, String name, String inName)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct sm FROM WebCategory sm WHERE 1=1 ");
		if (!BaseUtil.isEmpty(pageUrl)) {
			hql.append(" and sm.category.pageUrl= :pageUrl ");
			params.put("pageUrl", pageUrl);
		}
		if (!BaseUtil.isEmpty(pPageUrl)) {
			hql.append(" and sm.category.ck360Category.pageUrl= :pPageUrl ");
			params.put("pPageUrl", pPageUrl);
		}
		if (!BaseUtil.isEmpty(name)) {
			hql.append(" and sm.name= :name ");
			params.put("name", name);
		}
		if (!BaseUtil.isEmpty(inName)) {
			hql.append(" and sm.name in (" + inName + ") ");
		}
		hql.append("ORDER BY sm.id,sm.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据条件查询菜单实体
	 * 
	 * @param params
	 * @return
	 */
	public List<WebCategory> findAllBaseCategorys(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		if (!BaseUtil.isEmpty(params)) {
			if (params.containsKey("sysRole")) {
				hql.append("SELECT DISTINCT sm FROM WebCategory sm LEFT JOIN sm.sysRoles sr where 1 = 1 ");
				Object sysRoleValue = params.get("sysRole");
				String sysRoleType = sysRoleValue.getClass().getSimpleName();
				if (sysRoleType.equalsIgnoreCase("String")) {
					params.put("sysRoleId", sysRoleValue);
					hql.append("AND sr.id = :sysRoleId ");
					params.remove("sysRole");
				} else if (sysRoleType.equalsIgnoreCase("String[]")) {
					hql.append("AND sr.id in( ");
					String[] vs = (String[]) sysRoleValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysRole");
				}
			} else
				hql.append("SELECT DISTINCT sm FROM WebCategory sm WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name"))
				hql.append("AND sm.name = :name ");
			logger.debug("findAllBaseCategorys:hql=" + hql.toString());
		} else {
			hql.append("SELECT DISTINCT sm FROM WebCategory sm ");
			logger.debug("findAllBaseCategorys:hql=" + hql.toString());
		}
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据条件统计菜单总数
	 * 
	 * @param params
	 * @return
	 */
	public int getBaseCategoryTotalRows(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		if (!BaseUtil.isEmpty(params)) {
			if (params.containsKey("sysRole")) {
				hql.append("SELECT COUNT(*) FROM WebCategory sm LEFT JOIN sm.sysRoles sr where 1 = 1 ");
				Object sysRoleValue = params.get("sysRole");
				String sysRoleType = sysRoleValue.getClass().getSimpleName();
				if (sysRoleType.equalsIgnoreCase("String")) {
					params.put("sysRoleId", sysRoleValue);
					hql.append("AND sr.id = :sysRoleId ");
					params.remove("sysRole");
				} else if (sysRoleType.equalsIgnoreCase("String[]")) {
					hql.append("AND sr.id in( ");
					String[] vs = (String[]) sysRoleValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysRole");
				}
			} else
				hql.append("SELECT COUNT(*) FROM WebCategory sm WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name"))
				hql.append("AND sm.name = :name ");
			hql.append("AND sm.id<>'0' ORDER BY sm.id,sm.orderNo ASC ");
			logger.debug("getBaseCategoryTotalRows:hql=" + hql.toString());
		} else {
			hql.append("SELECT COUNT(*) FROM WebCategory sm WHERE ");
			hql.append("sm.id<>'0' ORDER BY sm.id,sm.orderNo ASC ");
			logger.debug("getBaseCategoryTotalRows:hql=" + hql.toString());
		}
		return this.getTotalRows(hql.toString(), params);
	}

	/**
	 * 子节点顺序号 批量更新
	 * 
	 * @param List
	 * @throws FrameworkException
	 */
	public void updateAll(List<WebCategory> list, Integer value)
			throws FrameworkException {
		for (WebCategory ck360Category : list) {

			logger.debug("实体修改:" + ck360Category.getName() + "   order:"
					+ ck360Category.getOrderNo());
			ck360Category.setOrderNo(ck360Category.getOrderNo() + value);
			this.update(ck360Category);
		}
	}

	/**
	 * 获取指定节点的子节点
	 * 
	 * @param nodeId
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> getSubAllNode(String nodeId)
			throws FrameworkException {
		String hql = "FROM WebCategory sm WHERE sm.category.id=:parentId";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("parentId", nodeId);
		List<WebCategory> list = this.findAllEntitiesByCondition(hql,
				condition);
		return list;
	}

}
