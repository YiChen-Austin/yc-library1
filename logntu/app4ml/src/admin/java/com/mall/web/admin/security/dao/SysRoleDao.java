/**
 * 
 */
package com.mall.web.admin.security.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.domain.SysRole;


/**
 * @功能说明：角色数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6
 * @
 */
@Repository("sysRoleDao")
public class SysRoleDao extends BaseDao<SysRole> {
	private static Logger logger = Logger.getLogger(SysRoleDao.class);

	/**
	 * 根据条件查询角色
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<SysRole> findAllSysRoles(Map<String, Object> params, int currentPage, int pageSize)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			if (params.containsKey("sysMenu") && params.containsKey("sysUser")) {
				hql
						.append("SELECT DISTINCT sr FROM SysRole sr LEFT JOIN sr.sysMenus sm LEFT JOIN sr.sysUsers su where 1 = 1 ");
				Object sysMenuValue = params.get("sysMenu");
				String sysMenuType = sysMenuValue.getClass().getSimpleName();
				Object sysUserValue = params.get("sysUser");
				String sysUserType = sysUserValue.getClass().getSimpleName();
				if (sysMenuType.equalsIgnoreCase("String")) {
					params.put("sysMenuId", sysMenuValue);
					hql.append("AND sm.id = :sysMenuId ");
					params.remove("sysMenu");
				} else if (sysMenuType.equalsIgnoreCase("String[]")) {
					hql.append("AND sm.id in( ");
					String[] vs = (String[]) sysMenuValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysMenu");
				}
				if (sysUserType.equalsIgnoreCase("String")) {
					params.put("sysUserId", sysUserValue);
					hql.append("AND su.id = :sysUserId ");
					params.remove("sysUser");
				} else if (sysUserType.equalsIgnoreCase("String[]")) {
					hql.append("AND su.id in( ");
					String[] vs = (String[]) sysUserValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysUser");
				}
			} else if (params.containsKey("sysMenu")) {
				hql.append("SELECT DISTINCT sr FROM SysRole sr LEFT JOIN sr.sysMenus sm where 1 = 1 ");
				Object sysMenuValue = params.get("sysMenu");
				String sysMenuType = sysMenuValue.getClass().getSimpleName();
				if (sysMenuType.equalsIgnoreCase("String")) {
					params.put("sysMenuId", sysMenuValue);
					hql.append("AND sm.id = :sysMenuId ");
					params.remove("sysMenu");
				} else if (sysMenuType.equalsIgnoreCase("String[]")) {
					hql.append("AND sm.id in( ");
					String[] vs = (String[]) sysMenuValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysMenu");
				}
			} else if (params.containsKey("sysUser")) {
				hql.append("SELECT DISTINCT sr FROM SysRole sr LEFT JOIN sr.sysUsers su where 1 = 1 ");
				Object sysUserValue = params.get("sysUser");
				String sysUserType = sysUserValue.getClass().getSimpleName();
				if (sysUserType.equalsIgnoreCase("String")) {
					params.put("sysUserId", sysUserValue);
					hql.append("AND su.id = :sysUserId ");
					params.remove("sysUser");
				} else if (sysUserType.equalsIgnoreCase("String[]")) {
					hql.append("AND su.id in( ");
					String[] vs = (String[]) sysUserValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysUser");
				}
			} else
				hql.append("SELECT DISTINCT sr FROM SysRole sr WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name")) {
				params.put("name", "%" + params.get("name") + "%");
				hql.append("AND sr.name like :name ");
			}
			if (!BaseUtil.isEmpty(params, "code")) {
				params.put("code", "%" + params.get("code") + "%");
				hql.append("AND sr.code like :code ");
			}
			logger.debug("findAllSysRoles:hql=" + hql.toString());
			return this.findAllEntitiesByCondition(hql.toString(), params, currentPage, pageSize);
		} else
			return this.findAllEntitiesByCondition(params, currentPage, pageSize);
	}

	/**
	 * 根据条件统计角色总数
	 * @param params
	 * @return
	 */
	public int getSysRoleTotalRows(Map<String, Object> params) throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			if (params.containsKey("sysMenu") && params.containsKey("sysUser")) {
				hql
						.append("SELECT COUNT(*) FROM SysRole sr LEFT JOIN sr.sysMenus sm LEFT JOIN sr.sysUsers su where 1 = 1 ");
				Object sysMenuValue = params.get("sysMenu");
				String sysMenuType = sysMenuValue.getClass().getSimpleName();
				Object sysUserValue = params.get("sysUser");
				String sysUserType = sysUserValue.getClass().getSimpleName();
				if (sysMenuType.equalsIgnoreCase("String")) {
					params.put("sysMenuId", sysMenuValue);
					hql.append("AND sm.id = :sysMenuId ");
					params.remove("sysMenu");
				} else if (sysMenuType.equalsIgnoreCase("String[]")) {
					hql.append("AND sm.id in( ");
					String[] vs = (String[]) sysMenuValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysMenu");
				}
				if (sysUserType.equalsIgnoreCase("String")) {
					params.put("sysUserId", sysUserValue);
					hql.append("AND su.id = :sysUserId ");
					params.remove("sysUser");
				} else if (sysUserType.equalsIgnoreCase("String[]")) {
					hql.append("AND su.id in( ");
					String[] vs = (String[]) sysUserValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysUser");
				}
			} else if (params.containsKey("sysMenu")) {
				hql.append("SELECT COUNT(*) FROM SysRole sr LEFT JOIN sr.sysMenus sm where 1 = 1 ");
				Object sysMenuValue = params.get("sysMenu");
				String sysMenuType = sysMenuValue.getClass().getSimpleName();
				if (sysMenuType.equalsIgnoreCase("String")) {
					params.put("sysMenuId", sysMenuValue);
					hql.append("AND sm.id = :sysMenuId ");
					params.remove("sysMenu");
				} else if (sysMenuType.equalsIgnoreCase("String[]")) {
					hql.append("AND sm.id in( ");
					String[] vs = (String[]) sysMenuValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysMenu");
				}
			} else if (params.containsKey("sysUser")) {
				hql.append("SELECT COUNT(*) FROM SysRole sr LEFT JOIN sr.sysUsers su where 1 = 1 ");
				Object sysUserValue = params.get("sysUser");
				String sysUserType = sysUserValue.getClass().getSimpleName();
				if (sysUserType.equalsIgnoreCase("String")) {
					params.put("sysUserId", sysUserValue);
					hql.append("AND su.id = :sysUserId ");
					params.remove("sysUser");
				} else if (sysUserType.equalsIgnoreCase("String[]")) {
					hql.append("AND su.id in( ");
					String[] vs = (String[]) sysUserValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysUser");
				}
			} else
				hql.append("SELECT COUNT(*) FROM SysRole sr WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name")) {
				params.put("name", "%" + params.get("name") + "%");
				hql.append("AND sr.name like :name ");
			}
			if (!BaseUtil.isEmpty(params, "code")) {
				params.put("code", "%" + params.get("code") + "%");
				hql.append("AND sr.code like :code ");
			}
			logger.debug("getSysRoleTotalRows:hql=" + hql.toString());
			return this.getTotalRows(hql.toString(), params);
		} else
			return this.getTotalRows(params);
	}

	/**
	 * 分公司查询角色
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysRole> findBranchSysRoles(Map<String, Object> params) throws FrameworkException {

		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			if (params.containsKey("sysUser")) {
				hql.append("SELECT DISTINCT sr FROM SysRole sr LEFT JOIN sr.sysUsers su where 1 = 1 ");
				Object sysUserValue = params.get("sysUser");
				String sysUserType = sysUserValue.getClass().getSimpleName();
				if (sysUserType.equalsIgnoreCase("String")) {
					params.put("sysUserId", sysUserValue);
					hql.append("AND su.id = :sysUserId ");
					params.remove("sysUser");
				} else if (sysUserType.equalsIgnoreCase("String[]")) {
					hql.append("AND su.id in( ");
					String[] vs = (String[]) sysUserValue;
					int len = vs.length;
					for (int i = 0; i < len; i++) {
						if (i != len - 1)
							hql.append(":param" + i + ", ");
						else
							hql.append(":param" + i + " ");
						params.put("param" + i, vs[i]);
					}
					hql.append(") ");
					params.remove("sysUser");
				}
			} else
				hql.append("SELECT DISTINCT sr FROM SysRole sr WHERE 1 = 1 ");

			logger.debug("findBranchSysRoles:hql=" + hql.toString());
			return this.findAllEntitiesByCondition(hql.toString(), params);
		} else
			return this.findAllEntitiesByCondition(params);

	}

	/**
	 * 
	 * @param orgType
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysRole> findOtherTwoSysRoles(String orgType) throws FrameworkException {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(SysRole.class);
		criteria.add(Restrictions.or(Restrictions.like("name", orgType + "区县录入"), Restrictions.like("name", orgType
				+ "区县审核")));
		return criteria.list();
	}

}
