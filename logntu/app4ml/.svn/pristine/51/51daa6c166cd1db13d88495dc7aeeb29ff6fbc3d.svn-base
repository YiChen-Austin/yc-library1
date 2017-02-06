/**
 * 
 */
package com.mall.web.admin.category.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.category.dao.BaseCategoryDao;
import com.mall.web.admin.category.vo.BaseCategoryBean;
import com.mall.web.mall.domain.WebCategory;


/**
 * @功能说明：系统菜单业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-8 @
 */
@Service("baseCategoryService")
public class BaseCategoryService {
	@Resource(name = "baseCategoryDao")
	private BaseCategoryDao baseCategoryDao;

	/**
	 * 通过菜单parentId获取其所有直接子菜单集合
	 * 
	 * @param parentId
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findDirectSubBaseCategorys(String parentId)
			throws FrameworkException {
		return this.baseCategoryDao.findDirectSubBaseCategorys(parentId);
	}

	/**
	 * 修改菜单
	 * 
	 * @param baseCategory
	 * @throws FrameworkException
	 */
	@Transactional
	public void modifyBaseCategory(WebCategory baseCategory)
			throws FrameworkException {
		this.baseCategoryDao.update(baseCategory);
	}

	/**
	 * 通过菜单ID删除菜单实体及其所有直接和间接子菜单实体
	 * 
	 * @param baseCategoryId
	 * @throws FrameworkException
	 */
	@Transactional
	public void deleteAllBaseCategorysById(String baseCategoryId)
			throws FrameworkException {
		this.deleteSubBaseCategorysById(baseCategoryId);
		this.deleteBaseCategoryById(baseCategoryId);
	}

	/**
	 * 通过菜单ID获取菜单实体
	 * 
	 * @param baseCategoryId
	 * @return
	 * @throws FrameworkException
	 */
	public WebCategory findBaseCategoryById(String baseCategoryId)
			throws FrameworkException {
		return this.baseCategoryDao.get(baseCategoryId);
	}

	/**
	 * 通过菜单ID递归删除所有子节点
	 * 
	 * @param baseCategoryId
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	private void deleteSubBaseCategorysById(String baseCategoryId)
			throws FrameworkException {
		List<WebCategory> subBaseCategorys = this.baseCategoryDao
				.findDirectSubBaseCategorys(baseCategoryId);
		for (WebCategory baseCategory : subBaseCategorys) {
			this.deleteSubBaseCategorysById(baseCategory.getId());
			this.baseCategoryDao.delete(baseCategory);
		}
	}

	/**
	 * 获取头部菜单列表
	 * 
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findOneLayerBaseCategorys()
			throws FrameworkException {
		WebCategory root = this.findDirectSubBaseCategorys(null).get(0);
		return this.baseCategoryDao.findSubBaseCategorys(root.getId());
	}

	/**
	 * 通过菜单ID删除菜单实体
	 * 
	 * @param baseCategoryId
	 * @throws FrameworkException
	 */
	@Transactional
	private void deleteBaseCategoryById(String baseCategoryId)
			throws FrameworkException {
		WebCategory baseCategory = this.findBaseCategoryById(baseCategoryId);
		int orderNo = baseCategory.getOrderNo();
		WebCategory parentBaseCategory = baseCategory.getCategory();
		Set<WebCategory> baseCategorys = parentBaseCategory
				.getCategories();
		for (WebCategory childBaseCategory : baseCategorys) {
			int oldOrderNo = childBaseCategory.getOrderNo();
			// 如果兄弟节点的orderNo比新插入节点的orderNo大，就将原来的orderNo加1
			if (oldOrderNo > orderNo) {
				int newOrderNo = oldOrderNo - 1;
				childBaseCategory.setOrderNo(newOrderNo);
				this.modifyBaseCategory(childBaseCategory);
			}
		}
		this.baseCategoryDao.delete(baseCategory);
	}

	/**
	 * 根据条件查询菜单实体
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findAllBaseCategorys(Map<String, Object> params)
			throws FrameworkException {
		return this.baseCategoryDao.findAllBaseCategorys(params);
	}

	/**
	 * 根据条件统计菜单总数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public int getBaseCategoryTotalRows(Map<String, Object> params)
			throws FrameworkException {
		return this.baseCategoryDao.getBaseCategoryTotalRows(params);
	}

	/**
	 * 同级菜单新增功能
	 * 
	 * @param baseCategory
	 * @param selectId
	 */
	@Transactional
	public void sameSaveBaseCategory(WebCategory baseCategory, String selectId)
			throws FrameworkException {
		// 选中菜单节点
		WebCategory selectBaseCategory = this.findBaseCategoryById(selectId);
		// 父菜单
		WebCategory parentBaseCategory = selectBaseCategory
				.getCategory();
		baseCategory.setCategory(parentBaseCategory);
		// 选中菜单节点的排序号
		Integer orderNo = selectBaseCategory.getOrderNo();
		baseCategory.setOrderNo(orderNo + 1);
		List<WebCategory> baseCategorys = this
				.findDirectSubBaseCategorys(parentBaseCategory.getId());
		for (WebCategory childBaseCategory : baseCategorys) {
			if (childBaseCategory.getId().equalsIgnoreCase(
					selectBaseCategory.getId()))
				continue;
			int oldOrderNo = childBaseCategory.getOrderNo();
			if (oldOrderNo >= orderNo) {
				int newOrderNo = oldOrderNo + 1;
				childBaseCategory.setOrderNo(newOrderNo);
				this.modifyBaseCategory(childBaseCategory);
			}
		}
		// 添加菜单
		this.baseCategoryDao.save(baseCategory);

	}

	/**
	 * 次级菜单新增功能
	 * 
	 * @param baseCategory
	 * @param selectId
	 */
	@Transactional
	public void secondarySaveBaseCategory(WebCategory baseCategory,
			String selectId) throws FrameworkException {
		// 选中菜单节点作为新增菜单节点的父节点
		WebCategory selectBaseCategory = this.findBaseCategoryById(selectId);
		baseCategory.setCategory(selectBaseCategory);
		List<WebCategory> baseCategorys = this
				.findDirectSubBaseCategorys(selectBaseCategory.getId());
		int size = baseCategorys.size();
		if (baseCategorys.size() > 0) {
			baseCategorys = orderSort(baseCategorys);
			baseCategory
					.setOrderNo(baseCategorys.get(size - 1).getOrderNo() + 1);
		} else {
			baseCategory.setOrderNo(1);
		}
		// 添加菜单
		this.baseCategoryDao.save(baseCategory);

	}

	/**
	 * 根据菜单ID获取菜单及其子菜单
	 * 
	 * @param baseCategoryId
	 * @param loginUserId
	 * @return
	 */
	public List<WebCategory> findBaseCategoryAndSubBaseCategorys(
			String baseCategoryId) throws FrameworkException {
		List<WebCategory> baseCategorys = this
				.findAllSubBaseCategorys(baseCategoryId);
		baseCategorys.add(this.baseCategoryDao.get(baseCategoryId));
		return baseCategorys;
	}

	/**
	 * 根据菜单ID获取所有直接及间接子菜单
	 * 
	 * @param baseCategoryId
	 * @param loginUserId
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findAllSubBaseCategorys(String baseCategoryId)
			throws FrameworkException {
		List<WebCategory> tempBaseCategorys = new ArrayList<WebCategory>();
		List<WebCategory> baseCategorys = this.baseCategoryDao
				.findSubBaseCategorys(baseCategoryId);
		for (WebCategory baseCategory : baseCategorys) {
			tempBaseCategorys.addAll(this.findAllSubBaseCategorys(baseCategory
					.getId()));
		}
		tempBaseCategorys.addAll(baseCategorys);
		return tempBaseCategorys;
	}

	/**
	 * 调整菜单位置
	 * 
	 * @param oldId
	 * @param newId
	 * @param type
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean moveMenu(String oldId, String newId, String type)
			throws FrameworkException {
		boolean flag = false;
		// 原菜单对象
		WebCategory oldBaseCategory = this.baseCategoryDao.get(oldId);
		// 新菜单对象
		WebCategory newBaseCategory = this.baseCategoryDao.get(newId);
		// 非法提交校验
		if (BaseUtil.isEmpty(newBaseCategory.getCategory().getId())) {
			return flag;
		}
		// 插入后节点的位置
		int nowOrder = 0;
		// 重构满足条件的节点集合 new
		List<WebCategory> newList = new ArrayList<WebCategory>();

		nowOrder = getNewNodes(newBaseCategory, type, newList);

		if ("inside".equals(type) == true) {
			oldBaseCategory.setCategory(newBaseCategory);
		} else {
			oldBaseCategory
					.setCategory(newBaseCategory.getCategory());
		}

		// 批量更新，满足条件的节点所有顺序号 +1
		this.baseCategoryDao.updateAll(newList, 1);

		oldBaseCategory.setOrderNo(nowOrder);
		this.baseCategoryDao.update(oldBaseCategory);

		flag = true;
		return flag;
	}

	/**
	 * 根据目标菜单，移动的类型，保存结果集，返回新节点的位置
	 * 
	 * @param newBaseCategory
	 * @param type
	 * @param newList
	 * @return
	 * @throws FrameworkException
	 */
	public int getNewNodes(WebCategory newBaseCategory, String type,
			List<WebCategory> newList) throws FrameworkException {
		// 新节点的位置
		int nowOrder = 0;

		// 子节点的集合
		List<WebCategory> subList = new ArrayList<WebCategory>();

		// 取得父节点ID，如果不等于 inside 就说明是在同级节点更改
		String newParentId = "";
		if ("inside".equals(type) == false) {
			newParentId = newBaseCategory.getParentId();
			int order = 0;
			if ("after".equals(type)) {
				nowOrder = newBaseCategory.getOrderNo() + 1;
				order = 0;
			} else {
				nowOrder = newBaseCategory.getOrderNo();
				order = -1;
			}

			List<WebCategory> newNodeList = this.baseCategoryDao
					.getSubAllNode(newParentId);
			for (WebCategory baseCategory : newNodeList) {
				if (baseCategory.getOrderNo() > newBaseCategory.getOrderNo()
						+ order) {
					newList.add(baseCategory);
				}
			}

		} else {
			// 说明父节点ID就为new节点的ID
			newParentId = newBaseCategory.getId();
			// 取出该节点的所有节点
			subList = this.baseCategoryDao.getSubAllNode(newParentId);
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
	private List<WebCategory> orderSort(List<WebCategory> list) {

		Collections.sort(list, new Comparator<WebCategory>() {
			public int compare(WebCategory arg0, WebCategory arg1) {
				Integer oldseat = (arg0.getOrderNo());
				Integer newseat = (arg1.getOrderNo());
				return oldseat.compareTo(newseat);
			}
		});
		return list;
	}

	/**
	 * 根据菜单名字查找菜单
	 * 
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	public WebCategory findBaseCategoryByName(String name)
			throws FrameworkException {
		if (BaseUtil.isEmpty(name))
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<WebCategory> baseCategorys = this.baseCategoryDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(baseCategorys) && baseCategorys.size() > 0)
			return baseCategorys.get(0);
		return null;

	}

	/**
	 * 根据菜单ID及名字查找菜单
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws FrameworkException
	 */
	public WebCategory findBaseCategoryByIdAndName(String id, String name)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		params.put("AND__id__ne", id);
		List<WebCategory> baseCategorys = this.baseCategoryDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(baseCategorys) && baseCategorys.size() > 0)
			return baseCategorys.get(0);
		return null;
	}

	/**
	 * 获取疾病分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findEfficacyCategorys()
			throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("EfficacyType");
	}

	/**
	 * 获取疾病分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findIllnessCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("IllnessType");
	}

	/**
	 * 获取药物分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findDrugCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("DrugType");
	}

	/**
	 * 获取基因分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findGenbankCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("GenbankType");
	}

	/**
	 * 获取常规体检分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findRoutineCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("RoutineType");
	}

	/**
	 * 获取供应商分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findSupplierCategorys()
			throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("SupplierType");
	}

	/**
	 * 获取产品分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findProductCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("ProductType");
	}

	public List<WebCategory> findDieticianCategorys()
			throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("DieticianType");
	}

	/**
	 * 获取文章分类期数
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	// public List<WebCategory> findArticleCategorys() throws
	// FrameworkException {
	// return this.baseCategoryDao
	// .findDirectSubBaseCategorysBypageUrl("ArticleType");
	// }

	public List<WebCategory> findArticleCategorys2()
			throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl2("ArticleType");
	}

	/**
	 * 获取食材分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebCategory> findFoodCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl_p("FoodType");
	}

	/**
	 * 
	 * 
	 */
	public List<WebCategory> findFoodAllCategorys() throws FrameworkException {
		return this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("FoodType");
	}

	/**
	 * 获取项目分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<BaseCategoryBean> findProjectCategorys()
			throws FrameworkException {
		List<WebCategory> list = this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("business_category");
		List<BaseCategoryBean> beans = new ArrayList<BaseCategoryBean>();
		for (WebCategory cate : list) {
			BaseCategoryBean bean = new BaseCategoryBean();
			org.springframework.beans.BeanUtils.copyProperties(cate, bean);
			beans.add(bean);
		}
		return beans;
	}

	/**
	 * 获取导师分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<BaseCategoryBean> findTutorCategorys()
			throws FrameworkException {
		List<WebCategory> list = this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("tutor_category");
		List<BaseCategoryBean> beans = new ArrayList<BaseCategoryBean>();
		for (WebCategory cate : list) {
			BaseCategoryBean bean = new BaseCategoryBean();
			org.springframework.beans.BeanUtils.copyProperties(cate, bean);
			beans.add(bean);
		}
		return beans;
	}

	/**
	 * 获取文章分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<BaseCategoryBean> findArticleCategorys()
			throws FrameworkException {
		List<WebCategory> list = this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("article_category");
		List<BaseCategoryBean> beans = new ArrayList<BaseCategoryBean>();
		for (WebCategory cate : list) {
			BaseCategoryBean bean = new BaseCategoryBean();
			org.springframework.beans.BeanUtils.copyProperties(cate, bean);
			beans.add(bean);
		}
		return beans;
	}

	/**
	 * 获取新闻分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<BaseCategoryBean> findNewsCategorys(String nCateId)
			throws FrameworkException {
		List<WebCategory> list = this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl("news_category");
		List<BaseCategoryBean> beans = new ArrayList<BaseCategoryBean>();
		for (WebCategory cate : list) {
			if (cate.getId().equalsIgnoreCase(nCateId))
				continue;
			BaseCategoryBean bean = new BaseCategoryBean();
			org.springframework.beans.BeanUtils.copyProperties(cate, bean);
			beans.add(bean);
		}
		return beans;
	}

	/**
	 * 根据父节点和名称确定分类
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public WebCategory findCategoryByName(String pKey, String name)
			throws FrameworkException {
		if (BaseUtil.isEmpty(name))
			return null;
		List<WebCategory> list = this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl(pKey, null, name, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<WebCategory> findCategoryByName(String pKey, String ppKey,
			String name, String inName) throws FrameworkException {
		if (BaseUtil.isEmpty(name) && BaseUtil.isEmpty(inName))
			return new ArrayList<WebCategory>();
		if (!BaseUtil.isEmpty(inName)) {
			inName = unitQuotStr(inName);
		}
		List<WebCategory> list = this.baseCategoryDao
				.findDirectSubBaseCategorysBypageUrl(pKey, ppKey, name, inName);
		return list;
	}

	public static String unitQuotStr(String strId) {
		String tempIds = null;
		if (strId != null) {
			tempIds = "";
			String[] ids = strId.trim().split("、|,|，|;|；|\\|");
			for (String id : ids) {
				tempIds += "'" + id + "',";
			}
			if (tempIds != null && tempIds.length() > 0) {
				tempIds = tempIds.substring(0, tempIds.length() - 1);
			}
		}
		return tempIds;
	}
}
