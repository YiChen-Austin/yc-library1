/**
 * 
 */
package com.mall.web.admin.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.dao.SysOrganizationDao;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：组织机构业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */
@Service("sysOrganizationService")
public class SysOrganizationService {
	@Resource(name = "sysOrganizationDao")
	private SysOrganizationDao sysOrganizationDao;
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;

	/**
	 * 通过组织机构ID获取组织机构实体
	 * 
	 * @param sysOrganizationId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findSysOrganizationById(String sysOrganizationId)
			throws FrameworkException {
		return this.sysOrganizationDao.get(sysOrganizationId);
	}

	public SysOrganization getSysOrganizationByNum(String organizationNumber)
			throws FrameworkException {
		return sysOrganizationDao.getSysOrganizationByNum(organizationNumber);
	}

	/**
	 * 修改组织机构
	 * 
	 * @param sysOrganization
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifySysOrganization(SysOrganization sysOrganization)
			throws FrameworkException {
		this.sysOrganizationDao.update(sysOrganization);
	}

	/**
	 * 通过组织机构parentId获取其所有直接子组织机构集合
	 * 
	 * @param parentId
	 * @return
	 */
	@DataSource(value = "admin")
	public List<SysOrganization> findDirectSubSysOrganizations(String parentId)
			throws FrameworkException {
		return this.sysOrganizationDao.findDirectSubSysOrganizations(parentId);
	}

	/**
	 * 通过组织机构ID删除组织机构实体
	 * 
	 * @param sysOrganizationId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSysOrganizationById(String sysOrganizationId)
			throws FrameworkException {
		SysOrganization sysOrganization = this
				.findSysOrganizationById(sysOrganizationId);
		int orderNo = sysOrganization.getOrderNo();
		SysOrganization parentSysOrganization = sysOrganization
				.getSysOrganization();
		List<SysOrganization> sysOrganizations = parentSysOrganization
				.getSysOrganizations();
		for (SysOrganization childSysOrganization : sysOrganizations) {
			int oldOrderNo = childSysOrganization.getOrderNo();
			// 如果兄弟节点的orderNo比新插入节点的orderNo大，就将原来的orderNo加1
			if (oldOrderNo > orderNo) {
				int newOrderNo = oldOrderNo - 1;
				childSysOrganization.setOrderNo(newOrderNo);
				this.modifySysOrganization(childSysOrganization);
			}
		}
		List<SysUser> sysUsers = sysOrganization.getSysUsers();
		for (SysUser sysUser : sysUsers) {
			sysUser.getSysOrganizations().remove(sysOrganization);
		}
		this.sysOrganizationDao.delete(sysOrganization);
	}

	/**
	 * 根据条件查询组织机构实体
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysOrganization> findAllSysOrganizations(
			Map<String, Object> params) throws FrameworkException {
		return this.sysOrganizationDao.findAllSysOrganizations(params);
	}

	/**
	 * 根据条件统计组织机构总数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getSysOrganizationTotalRows(Map<String, Object> params)
			throws FrameworkException {
		return this.sysOrganizationDao.getSysOrganizationTotalRows(params);
	}

	/**
	 * 根据组织机构ID修改原有人员
	 * 
	 * @param sysOrganizationId
	 * @param sysUserIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysOrganization setSysUser(String sysOrganizationId,
			String[] sysUserIds) throws FrameworkException {
		SysOrganization sysOrganization = this
				.findSysOrganizationById(sysOrganizationId);
		List<SysUser> sysUsers = sysOrganization.getSysUsers();
		for (SysUser sysUser : sysUsers) {
			sysUser.getSysOrganizations().remove(sysOrganization);
		}
		if (!BaseUtil.isEmpty(sysUserIds)) {
			for (String sysUserId : sysUserIds) {
				SysUser sysUser = this.sysUserDao.get(sysUserId);
				sysUser.getSysOrganizations().add(sysOrganization);
				sysOrganization.getSysUsers().add(sysUser);
			}
		}
		this.modifySysOrganization(sysOrganization);
		return sysOrganization;
	}

	/**
	 * 通过组织机构ID删除组织机构实体及其所有直接和间接子组织机构实体
	 * 
	 * @param sysOrganizationId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteAllSysOrganizationsById(String sysOrganizationId)
			throws FrameworkException {
		this.deleteSubSysOrganizationsById(sysOrganizationId);
		this.deleteSysOrganizationById(sysOrganizationId);

	}

	/**
	 * 通过组织机构ID递归删除所有子节点
	 * 
	 * @param sysOrganizationId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteSubSysOrganizationsById(String sysOrganizationId)
			throws FrameworkException {
		List<SysOrganization> subSysOrganizations = this.sysOrganizationDao
				.findDirectSubSysOrganizations(sysOrganizationId);
		for (SysOrganization sysOrganization : subSysOrganizations) {
			this.deleteSubSysOrganizationsById(sysOrganization.getId());
			List<SysUser> sysUsers = sysOrganization.getSysUsers();
			for (SysUser sysUser : sysUsers) {
				sysUser.getSysOrganizations().remove(sysOrganization);
			}
			this.sysOrganizationDao.delete(sysOrganization);
		}

	}

	/**
	 * 次级组织机构新增功能
	 * 
	 * @param sysMenu
	 * @param selectId
	 */
	@DataSource(value = "admin")
	@Transactional
	public void secondarySaveSysOrganization(SysOrganization sysOrganization,
			String selectId) throws FrameworkException {
		// 选中组织机构节点作为新增组织机构节点的父节点
		SysOrganization selectSysOrganization = this
				.findSysOrganizationById(selectId);
		sysOrganization.setSysOrganization(selectSysOrganization);
		List<SysOrganization> sysOrganizations = this
				.findDirectSubSysOrganizations(selectSysOrganization.getId());
		int size = sysOrganizations.size();
		if (sysOrganizations.size() > 0) {
			sysOrganizations = orderSort(sysOrganizations);
			sysOrganization.setOrderNo(sysOrganizations.get(size - 1)
					.getOrderNo() + 1);
		} else {
			sysOrganization.setOrderNo(1);
		}
		// 添加组织机构
		this.sysOrganizationDao.save(sysOrganization);

	}

	/**
	 * 同级组织机构新增功能
	 * 
	 * @param sysOrganization
	 * @param selectId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void sameSaveSysOrganization(SysOrganization sysOrganization,
			String selectId) throws FrameworkException {
		// 选中组织机构节点
		SysOrganization selectSysOrganization = this
				.findSysOrganizationById(selectId);
		// 父组织机构
		SysOrganization parentSysOrganization = selectSysOrganization
				.getSysOrganization();
		sysOrganization.setSysOrganization(parentSysOrganization);
		// 选中组织机构节点的排序号
		Integer orderNo = selectSysOrganization.getOrderNo();
		sysOrganization.setOrderNo(orderNo + 1);
		List<SysOrganization> sysOrganizations = this
				.findDirectSubSysOrganizations(parentSysOrganization.getId());
		for (SysOrganization childSysOrganization : sysOrganizations) {
			if (childSysOrganization.getId().equalsIgnoreCase(
					selectSysOrganization.getId()))
				continue;
			int oldOrderNo = childSysOrganization.getOrderNo();
			if (oldOrderNo >= orderNo) {
				int newOrderNo = oldOrderNo + 1;
				childSysOrganization.setOrderNo(newOrderNo);
				this.modifySysOrganization(childSysOrganization);
			}
		}
		// 添加组织机构
		this.sysOrganizationDao.save(sysOrganization);

	}

	/**
	 * 根据组织机构ID新增人员
	 * 
	 * @param sysOrganizationId
	 * @param sysUserIds
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public SysOrganization setNewSysUser(String sysOrganizationId,
			String[] sysUserIds) throws FrameworkException {
		SysOrganization sysOrganization = this
				.findSysOrganizationById(sysOrganizationId);
		if (!BaseUtil.isEmpty(sysUserIds)) {
			for (String sysUserId : sysUserIds) {
				SysUser sysUser = this.sysUserDao.get(sysUserId);
				sysUser.getSysOrganizations().add(sysOrganization);
				sysOrganization.getSysUsers().add(sysUser);
			}
		}
		this.modifySysOrganization(sysOrganization);
		return sysOrganization;

	}

	/**
	 * 调整菜节点的顺序
	 * 
	 * @param oldId
	 * @param newId
	 * @param type
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public boolean moveNode(String oldId, String newId, String type)
			throws FrameworkException {
		boolean flag = false;
		// 原菜单对象
		SysOrganization oldSysOrganization = this.sysOrganizationDao.get(oldId);
		// 新菜单对象
		SysOrganization newSysOrganization = this.sysOrganizationDao.get(newId);
		// 非法提交校验，判断父ID是否为空
		if (BaseUtil.isEmpty(newSysOrganization.getSysOrganization().getId())) {
			return flag;
		}

		// 在新位置中的顺序号
		int nowOrder = 0;

		// 重构满足条件的节点集合 new，等候后面批量更新
		List<SysOrganization> newList = new ArrayList<SysOrganization>();
		// 获取跟新的集合及目标节点的order
		nowOrder = updateNewNodes(newSysOrganization, type, nowOrder, newList);

		if ("inside".equals(type) == true) {
			oldSysOrganization.setSysOrganization(newSysOrganization);
		} else {
			oldSysOrganization.setSysOrganization(newSysOrganization
					.getSysOrganization());
		}

		// 批量更新，满足条件的节点所有顺序号 +1
		this.sysOrganizationDao.updateAll(newList, 1);

		oldSysOrganization.setOrderNo(nowOrder);
		this.sysOrganizationDao.update(oldSysOrganization);

		flag = true;
		return flag;
	}

	/**
	 * 更改组织机构顺序_处理新位置顺序
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int updateNewNodes(SysOrganization newSysOrganization, String type,
			Integer nowOrder, List<SysOrganization> newList)
			throws FrameworkException {

		// 取得父节点ID，如果不等于 inside 就说明是在同级节点更改
		String newParentId = "";

		// 说明是目标节点作为父节点
		if ("inside".equals(type) == false) {
			newParentId = newSysOrganization.getParentId();
			// 临时记录相对的顺序号
			int order = 0;
			if ("after".equals(type)) {
				nowOrder = newSysOrganization.getOrderNo() + 1;
				order = 0;
			} else {
				nowOrder = newSysOrganization.getOrderNo();
				order = -1;
			}
			// 将满足条件的记录的顺序号+1
			List<SysOrganization> newNodeList = this.sysOrganizationDao
					.getSubAllNode(newParentId);
			for (SysOrganization sysOrganization : newNodeList) {
				if (sysOrganization.getOrderNo() > newSysOrganization
						.getOrderNo() + order) {
					newList.add(sysOrganization);
				}
			}
		} else {

			List<SysOrganization> subList = new ArrayList<SysOrganization>();

			// 说明父节点ID就为new节点的ID
			newParentId = newSysOrganization.getId();
			// 取出该节点的所有节点
			subList = this.sysOrganizationDao.getSubAllNode(newParentId);
			// 集合排序
			subList = orderSort(subList);
			if (subList.size() > 0) {
				nowOrder = subList.get(subList.size() - 1).getOrderNo() + 1;
			} else {
				nowOrder = 0;
			}
		}

		return nowOrder;
	}

	/**
	 * 排序功能
	 * 
	 * @param list
	 * @return
	 */
	@DataSource(value = "admin")
	private List<SysOrganization> orderSort(List<SysOrganization> list) {

		Collections.sort(list, new Comparator<SysOrganization>() {
			public int compare(SysOrganization arg0, SysOrganization arg1) {
				Integer oldseat = (arg0.getOrderNo());
				Integer newseat = (arg1.getOrderNo());
				return oldseat.compareTo(newseat);
			}
		});
		return list;
	}

	/**
	 * 根据组织机构名字查找组织机构
	 * 
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findSysOrganizationByName(String name)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<SysOrganization> sysOrganizations = this.sysOrganizationDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysOrganizations) && sysOrganizations.size() > 0)
			return sysOrganizations.get(0);
		return null;
	}

	/**
	 * 根据组织机构名字查找组织机构
	 * 
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysOrganization> findSubSysOrganizationByName(String name)
			throws FrameworkException {
		SysOrganization sys = findSysOrganizationByName(name);
		return sys == null ? null : sys.getSysOrganizations();
	}

	@DataSource(value = "admin")
	public List<SysOrganization> findSubSysOrganizationByShrotName(
			String shortName) throws FrameworkException {
		SysOrganization sys = findSysOrganiztionByShortName(shortName);
		return sys == null ? null : sys.getSysOrganizations();
	}

	@DataSource(value = "admin")
	public List<SysOrganization> findSubSysOrganizationByNameEx(String name)
			throws FrameworkException {
		SysOrganization sys = findSysOrganizationByName(name);
		return sys == null ? null : sysOrganizationDao
				.findDirectSubSysOrganizationsEx(sys.getId());
	}

	/**
	 * 根据组织机构名字查找组织机构
	 * 
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findSysOrganizationByName(String prfame, String name)
			throws FrameworkException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<SysOrganization> sysOrganizations = this.sysOrganizationDao
				.findSysOrganizationByName(prfame, name);
		if (!BaseUtil.isEmpty(sysOrganizations) && sysOrganizations.size() > 0)
			return sysOrganizations.get(0);
		return null;
	}

	/**
	 * 根据组织机构ID及名字查找组织机构
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysOrganization findSysOrganizationByIdAndName(String id, String name)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		params.put("AND__id__ne", id);
		List<SysOrganization> sysOrganizations = this.sysOrganizationDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysOrganizations) && sysOrganizations.size() > 0)
			return sysOrganizations.get(0);
		return null;
	}

	/**
	 * 根据简称获取组织机构名称
	 * 
	 * @param shortName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public String findSysOrganiztionNameByShortName(String shortName)
			throws FrameworkException {
		SysOrganization sysOrg = findSysOrganiztionByShortName(shortName);
		if (!BaseUtil.isEmpty(sysOrg))
			return sysOrg.getName();
		return null;
	}

	@DataSource(value = "admin")
	public SysOrganization findSysOrganiztionByShortName(String shortName)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("shortName", shortName);
		List<SysOrganization> sysOrganizations = this.sysOrganizationDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysOrganizations) && sysOrganizations.size() > 0)
			return sysOrganizations.get(0);
		return null;
	}
}
