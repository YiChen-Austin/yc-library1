/**
 * 
 */
package com.mall.web.admin.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.domain.SysSelfDesktopDefend;


/**
 * @功能说明：自己桌面维护数据访问层
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */
@Repository("sysSelfDesktopDefendDao")
public class SysSelfDesktopDefendDao extends BaseDao<SysSelfDesktopDefend> {

	/**
	 * 通过用户ID删除用户所有的桌面维护数据
	 * @param loginUserId
	 * @throws FrameworkException
	 */
	public void deleteSelfDesktopDefendsByLoginUserId(String loginUserId) throws FrameworkException {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM SysSelfDesktopDefend sdd WHERE sdd.defendUser.id='" + loginUserId + "'");
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(sb.toString());
		query.executeUpdate();

	}

	/**
	 * 获取自己主页的桌面模块
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysSelfDesktopDefend> findSelfDesktopCenters(String loginUserId) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginUserId", loginUserId);
		StringBuffer hql = new StringBuffer();
		hql
				.append("SELECT DISTINCT sdd FROM SysSelfDesktopDefend sdd WHERE sdd.defendUser.id = :loginUserId ORDER BY sdd.orderNo ASC");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

}
