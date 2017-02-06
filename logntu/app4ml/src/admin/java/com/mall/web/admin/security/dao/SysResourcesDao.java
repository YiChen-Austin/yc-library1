package com.mall.web.admin.security.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.admin.security.domain.SysResources;

/**
 * @功能说明：系统资源页面资源数据访问层
 * @作者： 练书锋
 * @创建日期： 2010-6-8 @
 */
@Repository("sysResourcesDao")
public class SysResourcesDao extends BaseDao<SysResources> {

	/**
	 * 根据页面ID查找对应组件
	 * 
	 * @param pageres_id
	 * @return
	 * @throws Exception
	 */
	public List<SysResources> findResourceByPage(String pageres_id)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sys_page_res_id", pageres_id);
		List<SysResources> list = this.findAllEntitiesByCondition(map);
		return list;
	}

	/**
	 * 根据页面ID查找对应组件
	 * 
	 * @param pageres_id
	 * @return
	 * @throws Exception
	 */
	public List<SysResources> findResource(String pageres_id,
			String resourceId, String resourceName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sys_page_res_id", pageres_id);
		map.put("res_id", resourceId);
		map.put("name", resourceName);
		List<SysResources> list = this.findAllEntitiesByCondition(map);
		return list;
	}

	/**
	 * 根据资源ID查找对应组件
	 * 
	 * @param pageres_id
	 * @return
	 * @throws Exception
	 */
	public List<SysResources> findResourceByResId(String resourceId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res_id", resourceId);
		List<SysResources> list = this.findAllEntitiesByCondition(map);
		return list;
	}

	/**
	 * 根据页面ID、资源ID、用户ID查找对应组件数
	 * 
	 * @param userId
	 * @param pageId
	 * @param resourceId
	 * @return
	 * @throws Exception
	 */
	public int getResouceCount(String userId, String pageId, String resourceId) {
		String sql = "SELECT count(*) from "
				+ "sys_page_res p,sys_resources r, "
				+ "sys_resources_sys_role rs,sys_role_sys_user ss "
				+ "where r.id=rs.resources_id and rs.sysRoles_id=ss.sysRoles_id and p.id=r.sys_page_res_id "
				+ "and ss.sysUsers_id='" + userId + "' and r.res_id='"
				+ resourceId + "'  and p.page_id='" + pageId + "' ";
		return ((Long) jdbcTemplate.queryForLong(sql)).intValue();
	}

	public int getResouceCount(String userId, String resourceId) {
		String sql = "SELECT count(*) from "
				+ "sys_resources r, "
				+ "sys_resources_sys_role rs,sys_role_sys_user ss "
				+ "where r.id=rs.resources_id and rs.sysRoles_id=ss.sysRoles_id "
				+ "and ss.sysUsers_id='" + userId + "' and r.res_id='"
				+ resourceId + "'";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		return ((Long) sqlQuery.uniqueResult()).intValue();
	}
}
