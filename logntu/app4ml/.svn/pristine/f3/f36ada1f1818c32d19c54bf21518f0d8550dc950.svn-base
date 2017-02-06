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
import com.mall.web.admin.security.domain.SysOrganization;

/**
 * @功能说明：组织机构数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */
@Repository("sysOrganizationDao")
public class SysOrganizationDao extends BaseDao<SysOrganization> {
	private Logger logger = Logger.getLogger(SysOrganizationDao.class);

	/**
	 * 通过组织机构parentId获取其所有直接子组织机构集合
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysOrganization> findDirectSubSysOrganizations(String parentId)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysOrganization so ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE so.sysOrganization.id = :parentId ");
			params.put("parentId", parentId);
		}
		hql.append("ORDER BY so.id,so.orderNo ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	public List<SysOrganization> findDirectSubSysOrganizationsEx(String parentId)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysOrganization so ");
		if (!BaseUtil.isEmpty(parentId)) {
			hql.append("WHERE so.sysOrganization.id = :parentId ");
			params.put("parentId", parentId);
		}
		hql.append("ORDER BY so.shortName ASC ");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	public SysOrganization getSysOrganizationByNum(String organizationNumber)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysOrganization so ");
		hql.append("WHERE so.organizationNumber = :organizationNumber ");
		params.put("organizationNumber", organizationNumber);
		List<SysOrganization> list = this.findAllEntitiesByCondition(
				hql.toString(), params);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 根据条件查询组织机构实体
	 * 
	 * @param params
	 * @return
	 */
	public List<SysOrganization> findAllSysOrganizations(
			Map<String, Object> params) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		if (!BaseUtil.isEmpty(params)) {
			if (params.containsKey("sysUser")) {
				hql.append("SELECT DISTINCT so FROM SysOrganization so LEFT JOIN so.sysUsers su where 1 = 1 ");
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
				hql.append("SELECT DISTINCT so FROM SysOrganization so WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name"))
				hql.append("AND so.name = :name ");
			if (!BaseUtil.isEmpty(params, "shortName"))
				hql.append("AND so.shortName = :shortName ");
			hql.append("AND so.id<>'0' ORDER BY so.id,so.orderNo ASC ");
			logger.debug("findAllSysOrganizations:hql=" + hql.toString());
		} else {
			hql.append("SELECT DISTINCT so FROM SysOrganization so WHERE ");
			hql.append("so.id<>'0' ORDER BY so.id,so.orderNo ASC ");
			logger.debug("findAllSysOrganizations:hql=" + hql.toString());
		}
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据条件查询组织机构实体
	 * 
	 * @param params
	 * @return
	 */
	public List<SysOrganization> findSysOrganizationByName(String prfName,
			String name) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("SELECT DISTINCT so FROM SysOrganization so where so.name like :prfName and so.name like :name");
		params.put("prfName", "%" + prfName + "%");
		params.put("name", "%" + name + "%");
		return this.findAllEntitiesByCondition(hql.toString(), params);
	}

	/**
	 * 根据条件统计组织机构总数
	 * 
	 * @param params
	 * @return
	 */
	public int getSysOrganizationTotalRows(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		if (!BaseUtil.isEmpty(params)) {
			if (params.containsKey("sysUser")) {
				hql.append("SELECT COUNT(*) FROM SysOrganization so LEFT JOIN so.sysUsers su where 1 = 1 ");
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
				hql.append("SELECT COUNT(*) FROM SysOrganization so WHERE 1 = 1 ");
			if (!BaseUtil.isEmpty(params, "name"))
				hql.append("AND so.name = :name ");
			if (!BaseUtil.isEmpty(params, "shortName"))
				hql.append("AND so.shortName = :shortName ");
			hql.append("AND so.id<>'0' ORDER BY so.id,so.orderNo ASC ");
			logger.debug("getSysOrganizationTotalRows:hql=" + hql.toString());
		} else {
			hql.append("SELECT COUNT(*) FROM SysOrganization so WHERE ");
			hql.append("so.id<>'0' ORDER BY so.id,so.orderNo ASC ");
			logger.debug("getSysOrganizationTotalRows:hql=" + hql.toString());
		}
		return this.getTotalRows(hql.toString(), params);
	}

	/**
	 * 子节点顺序号 批量更新
	 * 
	 * @param List
	 * @throws FrameworkException
	 */
	public void updateAll(List<SysOrganization> list, Integer value)
			throws FrameworkException {
		for (SysOrganization sysOrganization : list) {
			sysOrganization.setOrderNo(sysOrganization.getOrderNo() + value);
			this.update(sysOrganization);
		}
	}

	/**
	 * 获取指定节点的子节点
	 * 
	 * @param nodeId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysOrganization> getSubAllNode(String nodeId)
			throws FrameworkException {
		String hql = "FROM SysOrganization so WHERE so.sysOrganization.id=:parentId";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("parentId", nodeId);
		List<SysOrganization> list = this.findAllEntitiesByCondition(hql,
				condition);
		return list;
	}

	/**
	 * 获取市公司及设计院组织机构
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysOrganization> findCompanyAndDesignInstitute()
			throws FrameworkException {
		String hql = "FROM SysOrganization so WHERE (so.sysOrganization is not null and so.sysOrganizations is not empty) OR so.name like :shejiyuan";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("shejiyuan", "%设计院%");
		List<SysOrganization> list = this.findAllEntitiesByCondition(hql,
				condition);
		return list;
	}

}
