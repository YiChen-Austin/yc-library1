package com.mall.web.admin.security.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.admin.security.domain.SysAuthorize;

/**
 * @功能说明：组件Action数据访问层
 * @作者： 练书锋
 * @创建日期： 2010-6-8 @
 */

@Repository("sysAuthorizeDao")
public class SysAuthorizeDao extends BaseDao<SysAuthorize> {
	/**
	 * 根据资源ID查找对应组件
	 * 
	 * @param pageres_id
	 * @return
	 * @throws Exception
	 */
	public List<SysAuthorize> findAuthorizeByUrl(String url) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		List<SysAuthorize> list = this.findAllEntitiesByCondition(map);
		return list;
	}

	/**
	 * 根据资源ID、用户ID，action链接url查找对应组件数
	 * 
	 * @param userId
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public int getUrlCount(String userId, String url) {
		String sql = "select count(*) from "
				+ "sys_authorize sa,sys_resources_sys_role rs,sys_role_sys_user ru "
				+ "where sa.sys_resources_id=rs.resources_id and rs.sysRoles_id=ru.sysUsers_id  "
				+ "and ru.sysUsers_id='" + userId + "' and sa.url='" + url
				+ "' ";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		return ((java.math.BigInteger) sqlQuery.uniqueResult()).intValue();
	}
}
