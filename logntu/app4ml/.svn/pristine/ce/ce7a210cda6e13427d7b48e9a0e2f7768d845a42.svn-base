/**
 * 
 */
package com.mall.web.admin.security.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.domain.SysUser;

/**
 * @功能说明：系统人员数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */
@Repository("sysUserDao")
public class SysUserDao extends BaseDao<SysUser> {
	private static Logger logger = Logger.getLogger(SysUserDao.class);

	/**
	 * 通过角色代码获取拥有该角色的用户
	 * 
	 * @param code
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysUser> findSysUserBySysRoleCode(String code)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT su FROM SysUser su LEFT JOIN su.sysRoles sr WHERE sr.code = :code");
		return this.findAllEntitiesByCondition(sb.toString(), params);

	}

	/**
	 * 通过用户名查找用户
	 * 
	 * @param userName
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public SysUser findSysUserByName(String userName) throws FrameworkException {
		Criteria criteria = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createCriteria(SysUser.class);
		if (!BaseUtil.isEmpty(userName)) {
			criteria.add(Restrictions.eq("userName", userName));
		}
		criteria.add(Restrictions.eq("disabled", true));
		List<SysUser> sysUsers = criteria.list();
		if (!BaseUtil.isEmpty(sysUsers) && sysUsers.size() > 0)
			return sysUsers.get(0);
		return null;
	}

	/**
	 * 系统登录功能
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, SysUser> login(final Map<String, Object> params)
			throws FrameworkException {
		Map<String, SysUser> sysUsers = null;
		// params.put("isOperator", true);20140101 由于非管理员操作，无需登录系统，注销
		params.put("disabled", true);
		Query query = null;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT su,CASE WHEN (su.effectStartDate is null AND su.effectEndDate is null) THEN ('sucess') ");
		sb.append("WHEN (su.effectStartDate is not null AND su.effectEndDate is null AND CURRENT_DATE()>= su.effectStartDate) THEN ('startSuccess')");
		sb.append("WHEN (su.effectStartDate is not null AND su.effectEndDate is null AND su.effectStartDate > CURRENT_DATE()) THEN ('startError')");
		sb.append("WHEN (su.effectStartDate is null AND su.effectEndDate is not null AND su.effectEndDate>=CURRENT_DATE()) THEN ('endSuccess') ");
		sb.append("WHEN (su.effectStartDate is null AND su.effectEndDate is not null AND CURRENT_DATE()> su.effectEndDate) THEN ('endError') ");
		sb.append("WHEN (su.effectStartDate is not null AND su.effectEndDate is not null AND CURRENT_DATE()>=su.effectStartDate AND su.effectEndDate>=CURRENT_DATE()) THEN ('betweenSuccess') ");
		sb.append("ELSE ('betweenError') END ");
		sb.append("FROM SysUser su WHERE su.disabled = :disabled AND "
		// + "su.isOperator = :isOperator AND" 20140101
		// 由于非管理员操作，无需登录系统，注销
				+ "su.userName = :userName AND su.password = :password ");
		query = this.sessionFactory.getCurrentSession().createQuery(
				sb.toString());
		Set<String> keys = params.keySet();
		for (String key : keys) {
			query.setParameter(key, params.get(key));
		}
		List loginObject = query.list();
		if (!BaseUtil.isEmpty(loginObject) && loginObject.size() > 0) {
			sysUsers = new HashMap<String, SysUser>();
			Object[] objs = (Object[]) loginObject.get(0);
			sysUsers.put(objs[1].toString(), (SysUser) objs[0]);
			return sysUsers;

		}
		return null;
	}

	/**
	 * 根据条件查询人员
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysUser> findAllSysUsers(Map<String, Object> params,
			int currentPage, int pageSize) throws FrameworkException {
		// SysUserLoginBean sysUserLoginBean = (SysUserLoginBean) ActionContext
		// .getContext().getSession().get(CommonConstant.SESSION_USER);
		String orgName = null; // sysUserLoginBean.getOrganizationName();
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			if (params.containsKey("sysOrganization")
					&& params.containsKey("sysRole")) {
				hql.append("SELECT DISTINCT su FROM SysUser su LEFT JOIN su.sysOrganizations so LEFT JOIN su.sysRoles sr where (1 = 1 ");
				if (!"通信管理局".equals(orgName)) {
					Object sysOrganizationValue = params.get("sysOrganization");
					String sysOrganizationType = sysOrganizationValue
							.getClass().getSimpleName();
					if (sysOrganizationType.equalsIgnoreCase("String")) {
						params.put("sysOrganizationId", sysOrganizationValue);
						hql.append("AND so.id = :sysOrganizationId ");
					} else if (sysOrganizationType.equalsIgnoreCase("String[]")) {
						hql.append("AND so.id in( ");
						String[] vs = (String[]) sysOrganizationValue;
						int len = vs.length;
						for (int i = 0; i < len; i++) {
							if (i != len - 1)
								hql.append(":param" + i + ", ");
							else
								hql.append(":param" + i + " ");
							params.put("param" + i, vs[i]);
						}
						hql.append(") ");
					}
				}
				params.remove("sysOrganization");
				Object sysRoleValue = params.get("sysRole");
				String sysRoleType = sysRoleValue.getClass().getSimpleName();
				if (sysRoleType.equalsIgnoreCase("String")) {
					params.put("roleName", "%" + sysRoleValue + "%");
					hql.append("AND sr.name like :roleName ");
					params.remove("sysRole");
				} else if (sysRoleType.equalsIgnoreCase("String[]")) {
					hql.append("AND sr.name in( ");
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
			} else if (params.containsKey("sysOrganization")) {
				hql.append("SELECT DISTINCT su FROM SysUser su LEFT JOIN su.sysOrganizations so where (1 = 1 ");
				if (!"通信管理局".equals(orgName)) {
					Object sysOrganizationValue = params.get("sysOrganization");
					String sysOrganizationType = sysOrganizationValue
							.getClass().getSimpleName();
					if (sysOrganizationType.equalsIgnoreCase("String")) {
						params.put("sysOrganizationId", sysOrganizationValue);
						hql.append("AND so.id = :sysOrganizationId ");
					} else if (sysOrganizationType.equalsIgnoreCase("String[]")) {
						hql.append("AND so.id in( ");
						String[] vs = (String[]) sysOrganizationValue;
						int len = vs.length;
						for (int i = 0; i < len; i++) {
							if (i != len - 1)
								hql.append(":param" + i + ", ");
							else
								hql.append(":param" + i + " ");
							params.put("param" + i, vs[i]);
						}
						hql.append(") ");
					}
				}
				params.remove("sysOrganization");
			} else if (params.containsKey("sysRole")) {
				hql.append("SELECT DISTINCT su FROM SysUser su LEFT JOIN su.sysRoles sr where (1 = 1 ");
				Object sysRoleValue = params.get("sysRole");
				String sysRoleType = sysRoleValue.getClass().getSimpleName();
				if (sysRoleType.equalsIgnoreCase("String")) {
					params.put("roleName", "%" + sysRoleValue + "%");
					hql.append("AND sr.name like :roleName ");
					params.remove("sysRole");
				} else if (sysRoleType.equalsIgnoreCase("String[]")) {
					hql.append("AND sr.name in( ");
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
				hql.append("SELECT DISTINCT su FROM SysUser su WHERE (1 = 1 ");
			if (!BaseUtil.isEmpty(params, "userName")) {
				params.put("userName", "%" + params.get("userName") + "%");
				hql.append("AND su.userName like :userName ");
			}
			if (!BaseUtil.isEmpty(params, "realName")) {
				params.put("realName", "%" + params.get("realName") + "%");
				hql.append("AND su.realName like :realName ");
			}
			if (!BaseUtil.isEmpty(params, "gender"))
				hql.append("AND su.gender = :gender ");
			if (!BaseUtil.isEmpty(params, "workYears"))
				hql.append("AND su.workYears = :workYears ");
			if (!BaseUtil.isEmpty(params, "birthDay"))
				hql.append("AND su.birthDay = :birthDay ");
			if (!BaseUtil.isEmpty(params, "staffKind"))
				hql.append("AND su.staffKind = :staffKind ");
			if (!BaseUtil.isEmpty(params, "effectStartDate"))
				hql.append("AND su.effectStartDate >= :effectStartDate ");
			if (!BaseUtil.isEmpty(params, "effectEndDate"))
				hql.append("AND su.effectEndDate <= :effectEndDate ");
			if (!BaseUtil.isEmpty(params, "disabled"))
				hql.append("AND su.disabled = :disabled ");
			if (!BaseUtil.isEmpty(params, "isOperator"))
				hql.append("AND su.isOperator = :isOperator ");
			if (!BaseUtil.isEmpty(params, "identityCard")) {
				params.put("identityCard", "%" + params.get("identityCard")
						+ "%");
				hql.append("AND su.identityCard like :identityCard ");
			}
			if (!"通信管理局".equals(orgName)
					&& !BaseUtil.isEmpty(params.get("createOrg"))) {
				hql.append("AND su.createOrg.id = :createOrg ");

			} else {
				params.remove("createOrg");
			}
			hql.append(") ");
			logger.debug("findAllSysUsers:hql=" + hql.toString());
			return this.findAllEntitiesByCondition(hql.toString(), params,
					currentPage, pageSize);
		} else
			return this.findAllEntitiesByCondition(params, currentPage,
					pageSize);
	}

	/**
	 * 根据条件统计人员总数
	 * 
	 * @param params
	 * @return
	 */
	public int getSysUserTotalRows(Map<String, Object> params)
			throws FrameworkException {
		// SysUserLoginBean sysUserLoginBean = (SysUserLoginBean) ActionContext
		// .getContext().getSession().get(CommonConstant.SESSION_USER);
		String orgName = null;// sysUserLoginBean.getOrganizationName();
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			if (params.containsKey("sysOrganization")
					&& params.containsKey("sysRole")) {
				hql.append("SELECT COUNT(*) FROM SysUser su LEFT JOIN su.sysOrganizations so LEFT JOIN su.sysRoles sr where (1 = 1 ");
				if (!"通信管理局".equals(orgName)) {
					Object sysOrganizationValue = params.get("sysOrganization");
					String sysOrganizationType = sysOrganizationValue
							.getClass().getSimpleName();
					if (sysOrganizationType.equalsIgnoreCase("String")) {
						params.put("sysOrganizationId", sysOrganizationValue);
						hql.append("AND so.id = :sysOrganizationId ");
					} else if (sysOrganizationType.equalsIgnoreCase("String[]")) {
						hql.append("AND so.id in( ");
						String[] vs = (String[]) sysOrganizationValue;
						int len = vs.length;
						for (int i = 0; i < len; i++) {
							if (i != len - 1)
								hql.append(":param" + i + ", ");
							else
								hql.append(":param" + i + " ");
							params.put("param" + i, vs[i]);
						}
						hql.append(") ");
					}
				}
				params.remove("sysOrganization");
				Object sysRoleValue = params.get("sysRole");
				String sysRoleType = sysRoleValue.getClass().getSimpleName();
				if (sysRoleType.equalsIgnoreCase("String")) {
					params.put("roleName", "%" + sysRoleValue + "%");
					hql.append("AND sr.name like :roleName ");
					params.remove("sysRole");
				} else if (sysRoleType.equalsIgnoreCase("String[]")) {
					hql.append("AND sr.name in( ");
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
			} else if (params.containsKey("sysOrganization")) {
				hql.append("SELECT COUNT(*) FROM SysUser su LEFT JOIN su.sysOrganizations so where (1 = 1 ");
				if (!"通信管理局".equals(orgName)) {
					Object sysOrganizationValue = params.get("sysOrganization");
					String sysOrganizationType = sysOrganizationValue
							.getClass().getSimpleName();
					if (sysOrganizationType.equalsIgnoreCase("String")) {
						params.put("sysOrganizationId", sysOrganizationValue);
						hql.append("AND so.id = :sysOrganizationId ");
					} else if (sysOrganizationType.equalsIgnoreCase("String[]")) {
						hql.append("AND so.id in( ");
						String[] vs = (String[]) sysOrganizationValue;
						int len = vs.length;
						for (int i = 0; i < len; i++) {
							if (i != len - 1)
								hql.append(":param" + i + ", ");
							else
								hql.append(":param" + i + " ");
							params.put("param" + i, vs[i]);
						}
						hql.append(") ");
					}
				}
				params.remove("sysOrganization");

			} else if (params.containsKey("sysRole")) {
				hql.append("SELECT COUNT(*) FROM SysUser su LEFT JOIN su.sysRoles sr where (1 = 1 ");
				Object sysRoleValue = params.get("sysRole");
				String sysRoleType = sysRoleValue.getClass().getSimpleName();
				if (sysRoleType.equalsIgnoreCase("String")) {
					params.put("roleName", "%" + sysRoleValue + "%");
					hql.append("AND sr.name like :roleName ");
					params.remove("sysRole");
				} else if (sysRoleType.equalsIgnoreCase("String[]")) {
					hql.append("AND sr.name in( ");
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
				hql.append("SELECT COUNT(*) FROM SysUser su WHERE (1 = 1 ");
			if (!BaseUtil.isEmpty(params, "realName")) {
				params.put("realName", "%" + params.get("realName") + "%");
				hql.append("AND su.realName like :realName ");
			}
			if (!BaseUtil.isEmpty(params, "gender"))
				hql.append("AND su.gender = :gender ");
			if (!BaseUtil.isEmpty(params, "userName")) {
				params.put("userName", "%" + params.get("userName") + "%");
				hql.append("AND su.userName like :userName ");
			}
			if (!BaseUtil.isEmpty(params, "workYears"))
				hql.append("AND su.workYears = :workYears ");
			if (!BaseUtil.isEmpty(params, "birthDay"))
				hql.append("AND su.birthDay = :birthDay ");
			if (!BaseUtil.isEmpty(params, "staffKind"))
				hql.append("AND su.staffKind = :staffKind ");
			if (!BaseUtil.isEmpty(params, "effectStartDate"))
				hql.append("AND su.effectStartDate >= :effectStartDate ");
			if (!BaseUtil.isEmpty(params, "effectEndDate"))
				hql.append("AND su.effectEndDate <= :effectEndDate ");
			if (!BaseUtil.isEmpty(params, "disabled"))
				hql.append("AND su.disabled = :disabled ");
			if (!BaseUtil.isEmpty(params, "isOperator"))
				hql.append("AND su.isOperator = :isOperator ");
			if (!BaseUtil.isEmpty(params, "identityCard")) {
				params.put("identityCard", "%" + params.get("identityCard")
						+ "%");
				hql.append("AND su.identityCard like :identityCard ");
			}
			if (!"通信管理局".equals(orgName)) {
				hql.append("AND su.createOrg.id = :createOrg ");

			} else {
				params.remove("createOrg");
			}
			hql.append(") ");

			logger.debug("getSysUserTotalRows:hql=" + hql.toString());
			return this.getTotalRows(hql.toString(), params);
		} else
			return this.getTotalRows(params);
	}

	/**
	 * 通过组织机构ID查询未与该组织机构关联的人员总数
	 * 
	 * @param params
	 * @return
	 */
	public int getNotSelectedSysUserTotalRows(Map<String, Object> params)
			throws FrameworkException {
		// 支持组织机构与人员多对多关系
		// StringBuffer hql = new StringBuffer();
		// hql.append("SELECT COUNT(*) FROM SysUser su LEFT JOIN su.sysOrganizations so where (1 = 1 ");
		// hql.append("AND (so.id != :sysOrganizationId OR so.id is null) ");
		// if (!BaseUtil.isEmpty(params, "sysOrgIds")) {
		// Object sysOrganizationValue = params.get("sysOrgIds");
		// String sysOrganizationType =
		// sysOrganizationValue.getClass().getSimpleName();
		// if (sysOrganizationType.equalsIgnoreCase("String[]")) {
		// hql.append("AND (so.id in( ");
		// String[] vs = (String[]) sysOrganizationValue;
		// int len = vs.length;
		// for (int i = 0; i < len; i++) {
		// if (i != len - 1)
		// hql.append(":param" + i + ", ");
		// else
		// hql.append(":param" + i + " ");
		// params.put("param" + i, vs[i]);
		// }
		// hql.append(") ");
		// if (!BaseUtil.isEmpty(params, "createUser"))
		// hql.append("OR su.createUser.id = :createUser) ");
		// else
		// hql.append(") ");
		// params.remove("sysOrgIds");
		// }
		// }
		// if (!BaseUtil.isEmpty(params)) {
		// if (!BaseUtil.isEmpty(params, "realName")) {
		// params.put("realName", "%" + params.get("realName") + "%");
		// hql.append("AND su.realName like :realName ");
		// }
		// if (!BaseUtil.isEmpty(params, "identityCard")) {
		// params.put("identityCard", "%" + params.get("identityCard") + "%");
		// hql.append("AND su.identityCard like :identityCard ");
		// }
		// }
		// hql.append(") ");
		// logger.debug("getSysUserTotalRows:hql=" + hql.toString());
		// return this.getTotalRows(hql.toString(), params);
		// 组织机构与人员为一对多关系
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT COUNT(*) FROM SysUser su where su.disabled = :disabled ");
		if (!BaseUtil.isEmpty(params)) {
			if (!BaseUtil.isEmpty(params, "createOrg")) {
				// SysUserLoginBean sysUserLoginBean = (SysUserLoginBean)
				// ActionContext
				// .getContext().getSession()
				// .get(CommonConstant.SESSION_USER);
				String orgName = null;// sysUserLoginBean.getOrganizationName();
				if (!"通信管理局".equals(orgName)) {
					hql.append("AND su.createOrg.id = :createOrg AND su.sysOrganizations is empty ");
				} else {
					params.remove("createOrg");
					hql.append("AND su.sysOrganizations is empty ");
				}
			}
			if (!BaseUtil.isEmpty(params, "realName")) {
				params.put("realName", "%" + params.get("realName") + "%");
				hql.append("AND su.realName like :realName ");
			}
			if (!BaseUtil.isEmpty(params, "identityCard")) {
				params.put("identityCard", "%" + params.get("identityCard")
						+ "%");
				hql.append("AND su.identityCard like :identityCard ");
			}
		}
		logger.debug("getSysUserTotalRows:hql=" + hql.toString());
		return this.getTotalRows(hql.toString(), params);
	}

	/**
	 * 通过组织机构ID查询未与该组织机构关联的人员
	 * 
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<SysUser> findNotSelectedSysUsers(Map<String, Object> params,
			int currentPage, int pageSize) throws FrameworkException {
		// 支持组织机构与人员多对多关系
		// StringBuffer hql = new StringBuffer();
		// hql.append("SELECT DISTINCT su FROM SysUser su LEFT JOIN su.sysOrganizations so where (1 = 1 ");
		// hql.append("AND (so.id != :sysOrganizationId OR so.id is null) ");
		// if (!BaseUtil.isEmpty(params, "sysOrgIds")) {
		// Object sysOrganizationValue = params.get("sysOrgIds");
		// String sysOrganizationType =
		// sysOrganizationValue.getClass().getSimpleName();
		// if (sysOrganizationType.equalsIgnoreCase("String[]")) {
		// hql.append("AND (so.id in( ");
		// String[] vs = (String[]) sysOrganizationValue;
		// int len = vs.length;
		// for (int i = 0; i < len; i++) {
		// if (i != len - 1)
		// hql.append(":param" + i + ", ");
		// else
		// hql.append(":param" + i + " ");
		// params.put("param" + i, vs[i]);
		// }
		// hql.append(") ");
		// if (!BaseUtil.isEmpty(params, "createUser"))
		// hql.append("OR su.createUser.id = :createUser) ");
		// else
		// hql.append(") ");
		// params.remove("sysOrgIds");
		// }
		// }
		// if (!BaseUtil.isEmpty(params)) {
		// if (!BaseUtil.isEmpty(params, "realName")) {
		// params.put("realName", "%" + params.get("realName") + "%");
		// hql.append("AND su.realName like :realName ");
		// }
		// if (!BaseUtil.isEmpty(params, "identityCard")) {
		// params.put("identityCard", "%" + params.get("identityCard") + "%");
		// hql.append("AND su.identityCard like :identityCard ");
		// }
		// }
		// hql.append(") ");
		// return this.findAllEntitiesByCondition(hql.toString(), params,
		// currentPage, pageSize);
		// 组织机构与人员为一对多关系
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT su FROM SysUser su where su.disabled = :disabled ");
		if (!BaseUtil.isEmpty(params)) {
			if (!BaseUtil.isEmpty(params, "createOrg")) {
				// SysUserLoginBean sysUserLoginBean = (SysUserLoginBean)
				// ActionContext
				// .getContext().getSession()
				// .get(CommonConstant.SESSION_USER);
				String orgName = null;// sysUserLoginBean.getOrganizationName();
				if (!"通信管理局".equals(orgName)) {
					hql.append("AND su.createOrg.id = :createOrg AND su.sysOrganizations is empty ");
				} else {
					params.remove("createOrg");
					hql.append("AND su.sysOrganizations is empty ");
				}
			}
			if (!BaseUtil.isEmpty(params, "realName")) {
				params.put("realName", "%" + params.get("realName") + "%");
				hql.append("AND su.realName like :realName ");
			}
			if (!BaseUtil.isEmpty(params, "identityCard")) {
				params.put("identityCard", "%" + params.get("identityCard")
						+ "%");
				hql.append("AND su.identityCard like :identityCard ");
			}
		}
		hql.append(") ");
		return this.findAllEntitiesByCondition(hql.toString(), params,
				currentPage, pageSize);
	}

	/**
	 * 根据用户ID获取该用户
	 * 
	 * @param id
	 * @return
	 */
	public SysUser getUserById(String id) {
		SysUser user = (SysUser) this.hibernateTemplate.get(SysUser.class, id);
		return user;
	}

	/**
	 * 根据用户id 获取该用户的权限菜单
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysMenu> queryRightsMenus(String id) throws FrameworkException {
		SysUser sysUser = getUserById(id);
		List<SysRole> roles = sysUser.getSysRoles();
		// 存放用户权限菜单
		Set<SysMenu> menus = new HashSet<SysMenu>();
		for (SysRole sysRole : roles) {
			List<SysMenu> tempMenus = sysRole.getSysMenus();
			for (SysMenu sysMenu : tempMenus) {
				menus.add(sysMenu);
			}
		}
		List<SysMenu> list = new ArrayList<SysMenu>();
		Iterator<SysMenu> Imenus = menus.iterator();
		while (Imenus.hasNext()) {
			SysMenu sysMenu = Imenus.next();
			list.add(sysMenu);
		}
		return list;
	}

	/**
	 * 根据地区名字和角色代码获取用户信息
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysUser> findSysUsersByCondition(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT su FROM SysUser su LEFT JOIN su.sysOrganizations so LEFT JOIN su.sysRoles sr WHERE ");
		if (!BaseUtil.isEmpty(params, "cnName"))
			hql.append("so.name like :cnName AND sr.code = :roleCode ");
		else if (!BaseUtil.isEmpty(params, "areaCode")) {
			hql.append("so.shortName = :areaCode AND sr.code = :roleCode ");
		} else if (!BaseUtil.isEmpty(params, "onlyRoleCode")) {
			hql.append("sr.code = :roleCode ");
			params.remove("onlyRoleCode");
		} else if (!BaseUtil.isEmpty(params, "sysOrganization")) {
			hql.append("sr.code = :roleCode ");
			hql.append("AND so.id in( ");
			String[] vs = (String[]) params.get("sysOrganization");
			int len = vs.length;
			for (int i = 0; i < len; i++) {
				if (i != len - 1)
					hql.append(":param" + i + ", ");
				else
					hql.append(":param" + i + " ");
				params.put("param" + i, vs[i]);
			}
			hql.append(") ");
			params.remove("sysOrganization");

		}
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 
	 * @param sysUserId
	 * @throws FrameworkException
	 */
	public void removeSysOrgAndSysRole(String sysUserId)
			throws FrameworkException {
		this.jdbcTemplate
				.execute("delete from sys_role_sys_user where sysusers_id='"
						+ sysUserId + "'");
		this.jdbcTemplate
				.execute("delete from sys_user_sys_organization where sysusers_id='"
						+ sysUserId + "'");

	}
}
