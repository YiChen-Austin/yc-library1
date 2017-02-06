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
import com.mall.web.admin.security.domain.SysUserOrganization;


/**
 * @功能说明：组织机构数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */
@Repository("sysUserOrganizationDao")
public class SysUserOrganizationDao extends BaseDao<SysUserOrganization> {
	private Logger logger = Logger.getLogger(SysUserOrganizationDao.class);

	/**
	 * 通过用户ID获得用户所对应的驻地网机构
	 * 
	 * @param String
	 *            userId 用户ID
	 * @return
	 */
	public List<SysUserOrganization> findSysUserOrganizationByUserId(
			String userId) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("SELECT DISTINCT suo from SysUserOrganization suo where suo.id.sysUserId='"
				+ userId + "'");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}
}
