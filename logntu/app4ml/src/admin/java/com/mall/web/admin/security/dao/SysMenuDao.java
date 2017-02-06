/**
 * 
 */
package com.mall.web.admin.security.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.domain.SysMenu;


/**
 * @功能说明：系统菜单数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-8 @
 */
@Repository("sysMenuDao")
public class SysMenuDao extends BaseDao<SysMenu> {
	Logger logger = Logger.getLogger(SysMenuDao.class);

	/**
	 * 通过菜单parentId获取其所有直接子菜单集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysMenu> findDirectSubSysMenus(String parentId)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysMenu sm ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE sm.sysMenu.id = :parentId ");
			params.put("parentId", parentId);
		} else {
			hql.append("WHERE sm.sysMenu.id is null ");
		}
		hql.append("ORDER BY sm.id,sm.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 通过菜单parentId获取其所有直接子菜单集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysMenu> findDirectSubSysMenus(String parentId, String menuUrl)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysMenu sm ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE sm.sysMenu.id = :parentId ");
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
	 * 根据条件查询菜单实体
	 * 
	 * @param params
	 * @return
	 */
	public List<SysMenu> findAllSysMenus(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		if (!BaseUtil.isEmpty(params)) {
			if (params.containsKey("sysRole")) {
				hql.append("SELECT DISTINCT sm FROM SysMenu sm LEFT JOIN sm.sysRoles sr where 1 = 1 ");
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
				hql.append("SELECT DISTINCT sm FROM SysMenu sm WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name"))
				hql.append("AND sm.name = :name ");
			logger.debug("findAllSysMenus:hql=" + hql.toString());
		} else {
			hql.append("SELECT DISTINCT sm FROM SysMenu sm ");
			logger.debug("findAllSysMenus:hql=" + hql.toString());
		}
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据条件统计菜单总数
	 * 
	 * @param params
	 * @return
	 */
	public int getSysMenuTotalRows(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		if (!BaseUtil.isEmpty(params)) {
			if (params.containsKey("sysRole")) {
				hql.append("SELECT COUNT(*) FROM SysMenu sm LEFT JOIN sm.sysRoles sr where 1 = 1 ");
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
				hql.append("SELECT COUNT(*) FROM SysMenu sm WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name"))
				hql.append("AND sm.name = :name ");
			hql.append("AND sm.id<>'0' ORDER BY sm.id,sm.orderNo ASC ");
			logger.debug("getSysMenuTotalRows:hql=" + hql.toString());
		} else {
			hql.append("SELECT COUNT(*) FROM SysMenu sm WHERE ");
			hql.append("sm.id<>'0' ORDER BY sm.id,sm.orderNo ASC ");
			logger.debug("getSysMenuTotalRows:hql=" + hql.toString());
		}
		return this.getTotalRows(hql.toString(), params);
	}

	/**
	 * 获取头部菜单列表
	 * 
	 * @param parentId
	 * @param sysUserId
	 * @return
	 */
	public List<SysMenu> findSubSysMenusByLoginUserId(String parentId,
			String sysUserId) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT DISTINCT sm FROM SysMenu sm LEFT JOIN sm.sysRoles so LEFT JOIN so.sysUsers su ");
		hql.append("WHERE sm.sysMenu.id = :parentId ");
		hql.append("AND su.id = :sysUserId ORDER BY sm.orderNo ASC ");
		params.put("sysUserId", sysUserId);
		params.put("parentId", parentId);
		logger.debug("findOneLayerSysMenuByLoginUserId__hql=" + hql.toString());
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 子节点顺序号 批量更新
	 * 
	 * @param List
	 * @throws FrameworkException
	 */
	public void updateAll(List<SysMenu> list, Integer value)
			throws FrameworkException {
		for (SysMenu sysMenu : list) {

			logger.debug("实体修改:" + sysMenu.getName() + "   order:"
					+ sysMenu.getOrderNo());
			sysMenu.setOrderNo(sysMenu.getOrderNo() + value);
			this.update(sysMenu);
		}
	}

	/**
	 * 获取指定节点的子节点
	 * 
	 * @param nodeId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysMenu> getSubAllNode(String nodeId) throws FrameworkException {
		String hql = "FROM SysMenu sm WHERE sm.sysMenu.id=:parentId";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("parentId", nodeId);
		List<SysMenu> list = this.findAllEntitiesByCondition(hql, condition);
		return list;
	}

}
