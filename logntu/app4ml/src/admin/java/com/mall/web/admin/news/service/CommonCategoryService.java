/**
 * 
 */
package com.mall.web.admin.news.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.news.dao.CommonCategoryDao;
import com.mall.web.admin.news.domain.SysCommonCategory;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.common.dynamicds.DataSource;


/**
 * @功能说明：栏目管理业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-9-18
 * @
 */
@Service("commonCategoryService")
public class CommonCategoryService {
	@Resource(name = "commonCategoryDao")
	private CommonCategoryDao commonCategoryDao;
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;

	/**
	 * 添加栏目管理
	 * @param commonCategory
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public void saveCommonCategory(SysCommonCategory commonCategory) throws FrameworkException {
		this.commonCategoryDao.save(commonCategory);

	}

	/**
	 * 根据条件获获取栏目实体
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public List<SysCommonCategory> findAllDiscussionCategories() throws FrameworkException, Exception {
		return this.commonCategoryDao.findAllDiscussionCategories();
	}

	/**
	 * 根据信息栏目ID及名字,所属公司查找栏目
	 * @param id
	 * @param name
	 * @param company 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysCommonCategory findNewsCategoryByIdAndName(String id, String name, SysOrganization company)
			throws FrameworkException {
		List<SysCommonCategory> commonCategories = this.commonCategoryDao.findNewsCategoryByIdAndName(id, name, company);
		if (!BaseUtil.isEmpty(commonCategories) && commonCategories.size() > 0)
			return commonCategories.get(0);
		return null;
	}

	/**
	 * 根据栏目ID及名字,所属公司查找栏目
	 * @param id
	 * @param name
	 * @param company 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysCommonCategory findDiscussionCategoryByIdAndName(String id, String name, SysOrganization company)
			throws FrameworkException {
		List<SysCommonCategory> commonCategories = this.commonCategoryDao.findDiscussionCategoryByIdAndName(id, name,
				company);
		if (!BaseUtil.isEmpty(commonCategories) && commonCategories.size() > 0)
			return commonCategories.get(0);
		return null;
	}

	/**
	 * 根据ID获取栏目实体
	 * @param id
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysCommonCategory findCommonCategoryById(String id) throws FrameworkException {
		return this.commonCategoryDao.get(id);
	}

	/**
	 * 修改栏目
	 * @param commonCategory
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifyDiscussionCategory(SysCommonCategory commonCategory) throws FrameworkException {
		this.commonCategoryDao.update(commonCategory);

	}

	/**
	 * 根据栏目名字查找栏目
	 * @param name
	 * @param company 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysCommonCategory findDiscussionCategoryByName(String name, SysOrganization company) throws FrameworkException {
		List<SysCommonCategory> commonCategories = this.commonCategoryDao.findDiscussionCategoryByName(name, company);
		if (!BaseUtil.isEmpty(commonCategories) && commonCategories.size() > 0)
			return commonCategories.get(0);
		return null;

	}

	/**
	 * 同级新增栏目
	 * @param commonCategory
	 * @param selectId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void sameSaveDiscussionCategory(SysCommonCategory commonCategory, String selectId) throws FrameworkException {
		// 选中栏目节点
		SysCommonCategory selectCommonCategory = this.findCommonCategoryById(selectId);
		// 父栏目
		SysCommonCategory parentCommonCategory = selectCommonCategory.getCommonCategory();
		commonCategory.setCommonCategory(parentCommonCategory);
		// 选中栏目节点的排序号
		Integer orderNo = selectCommonCategory.getOrderNo();
		commonCategory.setOrderNo(orderNo + 1);
		List<SysCommonCategory> commonCategories = this.findDirectSubCommonCategories(parentCommonCategory.getId());
		for (SysCommonCategory childCommonCategory : commonCategories) {
			if (childCommonCategory.getId().equalsIgnoreCase(selectCommonCategory.getId()))
				continue;
			int oldOrderNo = childCommonCategory.getOrderNo();
			if (oldOrderNo >= orderNo) {
				int newOrderNo = oldOrderNo + 1;
				childCommonCategory.setOrderNo(newOrderNo);
				this.modifyDiscussionCategory(childCommonCategory);
			}
		}
		// 添加栏目数
		this.commonCategoryDao.save(commonCategory);

	}

	/**
	 * 通过栏目parentId获取其所有直接子栏目集合
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	private List<SysCommonCategory> findDirectSubCommonCategories(String parentId) throws FrameworkException {
		return this.commonCategoryDao.findDirectSubCommonCategories(parentId);
	}

	/**
	 * 次级新增栏目
	 * @param commonCategory
	 * @param selectId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void secondarySaveDiscussionCategory(SysCommonCategory commonCategory, String selectId)
			throws FrameworkException {
		// 选中栏目节点作为新增栏目节点的父节点
		SysCommonCategory selectCommonCategory = this.findCommonCategoryById(selectId);
		commonCategory.setCommonCategory(selectCommonCategory);
		List<SysCommonCategory> commonCategories = this.findDirectSubCommonCategories(selectCommonCategory.getId());
		int size = commonCategories.size();
		if (commonCategories.size() > 0) {
			commonCategories = orderSort(commonCategories);
			commonCategory.setOrderNo(commonCategories.get(size - 1).getOrderNo() + 1);
		} else {
			commonCategory.setOrderNo(1);
		}
		// 添加栏目
		this.commonCategoryDao.save(commonCategory);

	}

	/**
	 * 排序功能
	 * @param commonCategories
	 * @return
	 */
	@DataSource(value = "admin")
	private List<SysCommonCategory> orderSort(List<SysCommonCategory> commonCategories) {
		Collections.sort(commonCategories, new Comparator<SysCommonCategory>() {
			public int compare(SysCommonCategory arg0, SysCommonCategory arg1) {
				Integer oldseat = (arg0.getOrderNo());
				Integer newseat = (arg1.getOrderNo());
				return oldseat.compareTo(newseat);
			}
		});
		return commonCategories;
	}

	/**
	 * 通过ID删除栏目及其子栏目集合
	 * @param id
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteAllDiscussionCategoriesById(String id) throws FrameworkException {
		this.deleteSubDiscussionCategoriesById(id);
		this.deleteDiscussionCategoryById(id);

	}

	/**
	 * 通过ID删除栏目节点
	 * @param id
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteDiscussionCategoryById(String id) throws FrameworkException {
		SysCommonCategory commonCategory = this.findCommonCategoryById(id);
		int orderNo = commonCategory.getOrderNo();
		SysCommonCategory parentCommonCategory = commonCategory.getCommonCategory();
		List<SysCommonCategory> commonCategories = parentCommonCategory.getCommonCategories();
		for (SysCommonCategory childCommonCategory : commonCategories) {
			int oldOrderNo = childCommonCategory.getOrderNo();
			// 如果兄弟节点的orderNo比新插入节点的orderNo大，就将原来的orderNo加1
			if (oldOrderNo > orderNo) {
				int newOrderNo = oldOrderNo - 1;
				childCommonCategory.setOrderNo(newOrderNo);
				this.modifyDiscussionCategory(childCommonCategory);
			}
		}
//		LogUtil.getInstance()
//				.deleteExecution(commonCategory, "news".equals(commonCategory.getFlag()) ? "信息栏目" : "论坛栏目");
		this.commonCategoryDao.delete(commonCategory);
	}

	/**
	 * 通过栏目ID递归删除所有直接和间接子栏目节点
	 * @param id
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteSubDiscussionCategoriesById(String id) throws FrameworkException {
		List<SysCommonCategory> subCommonCategories = this.commonCategoryDao.findDirectSubCommonCategories(id);
		for (SysCommonCategory commonCategory : subCommonCategories) {
			// LogUtil.getInstance().deleteExecution(commonCategory,
			// "news".equals(commonCategory.getFlag()) ? "信息栏目" : "论坛栏目");
			this.deleteSubDiscussionCategoriesById(commonCategory.getId());
			this.commonCategoryDao.delete(commonCategory);
		}
	}

	/**
	 * 根据条件获获取信息实体
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	public List<SysCommonCategory> findAllNewsCategories() throws FrameworkException, Exception {
		return this.commonCategoryDao.findAllNewsCategories();
	}

	/**
	 * 修改信息
	 * @param commonCategory
	 * @param administratorId
	 * @param publisherId
	 * @param publisherName 
	 * @param administratorName 
	 * @FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void modifyNewsCategory(SysCommonCategory commonCategory, String administratorId, String publisherId,
			String administratorName, String publisherName) throws FrameworkException {
		commonCategory.setAdministratorIds(administratorId);
		commonCategory.setPublisherIds(publisherId);
		commonCategory.setAdministratorName(administratorName);
		commonCategory.setPublisherName(publisherName);
		this.commonCategoryDao.update(commonCategory);

	}

	/**
	 * 通过信息名称判断是否有重复的信息名称
	 * @param name
	 * @param company 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysCommonCategory findNewsCategoryByName(String name, SysOrganization company) throws FrameworkException {
		List<SysCommonCategory> commonCategories = this.commonCategoryDao.findNewsCategoryByName(name, company);
		if (!BaseUtil.isEmpty(commonCategories) && commonCategories.size() > 0)
			return commonCategories.get(0);
		return null;
	}

	/**
	 * 同级新增信息树功能
	 * @param commonCategory
	 * @param administratorId
	 * @param publisherId
	 * @param selectId
	 * @param publisherName 
	 * @param administratorName 
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void sameSaveNewsCategory(SysCommonCategory commonCategory, String administratorId, String publisherId,
			String selectId, String administratorName, String publisherName) throws FrameworkException {
		// 选中信息节点
		SysCommonCategory selectCommonCategory = this.findCommonCategoryById(selectId);
		// 父信息
		SysCommonCategory parentCommonCategory = selectCommonCategory.getCommonCategory();
		commonCategory.setCommonCategory(parentCommonCategory);
		// 选中信息节点的排序号
		Integer orderNo = selectCommonCategory.getOrderNo();
		commonCategory.setOrderNo(orderNo + 1);
		List<SysCommonCategory> commonCategories = this.findDirectSubCommonCategories(parentCommonCategory.getId());
		for (SysCommonCategory childCommonCategory : commonCategories) {
			if (childCommonCategory.getId().equalsIgnoreCase(selectCommonCategory.getId()))
				continue;
			int oldOrderNo = childCommonCategory.getOrderNo();
			if (oldOrderNo >= orderNo) {
				int newOrderNo = oldOrderNo + 1;
				childCommonCategory.setOrderNo(newOrderNo);
				this.modifyDiscussionCategory(childCommonCategory);
			}
		}
		commonCategory.setAdministratorIds(administratorId);
		commonCategory.setPublisherIds(publisherId);
		commonCategory.setAdministratorName(administratorName);
		commonCategory.setPublisherName(publisherName);
		// 添加信息
		this.commonCategoryDao.save(commonCategory);

	}

	/**
	 * 次级新增信息树功能
	 * @param commonCategory
	 * @param administratorId
	 * @param publisherId 
	 * @param selectId 
	 * @param publisherName 
	 * @param administratorName 
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void secondarySaveNewsCategory(SysCommonCategory commonCategory, String administratorId, String publisherId,
			String selectId, String administratorName, String publisherName) throws FrameworkException {
		// 选中栏目节点作为新增信息节点的父节点
		SysCommonCategory selectCommonCategory = this.findCommonCategoryById(selectId);
		commonCategory.setCommonCategory(selectCommonCategory);
		List<SysCommonCategory> commonCategories = this.findDirectSubCommonCategories(selectCommonCategory.getId());
		int size = commonCategories.size();
		if (commonCategories.size() > 0) {
			commonCategories = orderSort(commonCategories);
			commonCategory.setOrderNo(commonCategories.get(size - 1).getOrderNo() + 1);
		} else {
			commonCategory.setOrderNo(1);
		}
		commonCategory.setAdministratorIds(administratorId);
		commonCategory.setPublisherIds(publisherId);
		commonCategory.setAdministratorName(administratorName);
		commonCategory.setPublisherName(publisherName);
		// 添加信息
		this.commonCategoryDao.save(commonCategory);

	}
}
