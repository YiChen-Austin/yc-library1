/**
 * 
 */
package com.mall.web.admin.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.domain.SysDesktopDefend;


/**
 * @功能说明：桌面维护数据访问层
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */
@Repository("sysDesktopDefendDao")
public class SysDesktopDefendDao extends BaseDao<SysDesktopDefend> {
	/**
	 * 根据条件获取桌面管理总记录数
	 * @param params
	 * @return
	 */
	public int getDesktopDefendTotalRows(Map<String, Object> params) throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT COUNT(*) FROM SysDesktopDefend sd WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "title")) {
				params.put("title", "%" + params.get("title") + "%");
				hql.append("AND sd.title like :title ");
			}
			if (!BaseUtil.isEmpty(params, "divId")) {
				params.put("divId", "%" + params.get("divId") + "%");
				hql.append("AND sd.divId like :divId ");
			}
			return this.getTotalRows(hql.toString(), params);
		} else
			return this.getTotalRows(params);
	}

	/**
	 * 根据条件获取桌面管理记录
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<SysDesktopDefend> findAllDesktopDefends(Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		if (!BaseUtil.isEmpty(params)) {
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT DISTINCT sd FROM SysDesktopDefend sd WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "title")) {
				params.put("title", "%" + params.get("title") + "%");
				hql.append("AND sd.title like :title ");
			}
			if (!BaseUtil.isEmpty(params, "divId")) {
				params.put("divId", "%" + params.get("divId") + "%");
				hql.append("AND sd.divId like :divId ");
			}
			return this.findAllEntitiesByCondition(hql.toString(), params, curPage, pageSize);
		} else
			return this.findAllEntitiesByCondition(params, curPage, pageSize);
	}

	/**
	 * 通过桌面管理ID、区域业务名称和DIV标签的ID查找桌面管理
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public SysDesktopDefend findDesktopDefendByCon(Map<String, Object> params) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysDesktopDefend WHERE id<> :id AND (title = :title OR divId = :divId )");
		List<SysDesktopDefend> desktopDefends = this.findAllEntitiesByCondition(hql.toString(), params);
		if (!BaseUtil.isEmpty(desktopDefends) && desktopDefends.size() > 0)
			return desktopDefends.get(0);
		return null;

	}

	/**
	 * 获取自己的桌面业务模块
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysDesktopDefend> findSelfDesktopDefends(String loginUserId) throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginUserId", loginUserId);
		StringBuffer hql = new StringBuffer();
		hql
				.append("SELECT DISTINCT sdd.desktopDefend FROM SysSelfDesktopDefend sdd WHERE sdd.defendUser.id = :loginUserId");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

}
