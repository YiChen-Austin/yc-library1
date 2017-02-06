/**
 * 
 */
package com.mall.web.admin.category.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.category.service.BaseCategoryService;
import com.mall.web.admin.category.vo.BaseCategoryBean;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.TreeUtil;
import com.mall.web.admin.security.vo.TreeNodeBean;
import com.mall.web.mall.domain.WebCategory;


/**
 * @功能说明：菜单管理控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-5-15 @
 */
@Controller
@RequestMapping("/category/*")
public class BaseCategoryAction extends BaseAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private BaseCategoryService baseCategoryService;

	// private String data;
	// private Map<String, String> attributes;
	// private List<TreeNodeBean> children;
	// private String state;
	// // 移动菜单需要用到的参数
	// private String oldNodeId, newNodeId, changeType;
	//
	// private BaseCategoryBean baseCategoryBean;
	// // 处理结果
	// private boolean result = false;
	// // 新增节点ID值
	// private String newId = null;
	// // 选中节点ID值
	// private String selectId = null;
	//
	// private List<BaseCategoryBean> listBaseCategoryBean = null;

	/********************************/
	

	// 应用于pad搜索
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("wpFindFdCategorys")
	@ResponseBody
	public Map<String, Object> wpFindFdCategorys() throws FrameworkException,
			Exception {
		List<BaseCategoryBean> listBaseCategoryBean = new ArrayList<BaseCategoryBean>();
		List<WebCategory> baseCategorys = baseCategoryService
				.findFoodAllCategorys();
		for (WebCategory bs : baseCategorys) {
			BaseCategoryBean bean = new BaseCategoryBean();
			BeanUtils.copyProperties(bs, bean);

			List<BaseCategoryBean> listBean = new ArrayList<BaseCategoryBean>();
			bean.setListBaseCategoryBean(listBean);
			Set<WebCategory> list = bs.getCategories();// bs.getBaseCategorys();
			for (WebCategory b : list) {
				BaseCategoryBean beanS = new BaseCategoryBean();
				BeanUtils.copyProperties(b, beanS);
				listBean.add(beanS);
			}
			listBaseCategoryBean.add(bean);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("listBaseCategoryBean", listBaseCategoryBean);
		return result;
	}

	// 应用于pad搜索
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("wpFindEfCategorys")
	@ResponseBody
	public Map<String, Object> wpFindEfCategorys() throws FrameworkException,
			Exception {
		List<BaseCategoryBean> listBaseCategoryBean = new ArrayList<BaseCategoryBean>();
		// 心血疾病
		List<WebCategory> baseCategorys = baseCategoryService
				.findEfficacyCategorys();
		for (WebCategory bs : baseCategorys) {
			BaseCategoryBean bean = new BaseCategoryBean();
			BeanUtils.copyProperties(bs, bean);
			listBaseCategoryBean.add(bean);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("listBaseCategoryBean", listBaseCategoryBean);
		return result;
	}

	/********************************/

	/**
	 * 显示树形菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("findAllBaseCategorys")
	@ResponseBody
	public Map<String, Object> findAllBaseCategorys()
			throws FrameworkException, Exception {
		List<WebCategory> baseCategorys = baseCategoryService
				.findAllBaseCategorys(null);
		TreeNodeBean treeNodeBean = TreeUtil.convertMenuTreeNode(baseCategorys);
		List<TreeNodeBean> children = treeNodeBean.getChildren();
		// 父节点
		String data = treeNodeBean.getData();
		Map<String, String> attributes = treeNodeBean.getAttr();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("children", children);
		result.put("data", data);
		result.put("attributes", attributes);
		return result;
	}

	/**
	 * 修改菜单功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("modifyBaseCategory")
	@ResponseBody
	public Map<String, Object> modifyBaseCategory(
			BaseCategoryBean baseCategoryBean) throws FrameworkException {

		Map<String, Object> result = new HashMap<String, Object>();
		if (!BaseUtil.isEmpty(this.baseCategoryService
				.findBaseCategoryByIdAndName(baseCategoryBean.getId(),
						baseCategoryBean.getName()))) {
			result.put("newId", "none");
			return result;
		}
		if (BaseUtil.isEmpty(baseCategoryBean.getInternalCommand())) {
			baseCategoryBean.setInternalCommand("0");
		}

		logger.debug("getInternalCommand:\t"
				+ baseCategoryBean.getInternalCommand());
		WebCategory baseCategory = this.baseCategoryService
				.findBaseCategoryById(baseCategoryBean.getId());
		baseCategoryBean.setOrderNo(baseCategory.getOrderNo());
		baseCategoryBean.setFlag(baseCategory.getFlag());
		BeanUtils.copyProperties(baseCategoryBean, baseCategory);
		this.baseCategoryService.modifyBaseCategory(baseCategory);

		result.put("result", true);
		return result;
	}

	/**
	 * 同级菜单新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("sameSaveBaseCategory")
	@ResponseBody
	public Map<String, Object> sameSaveBaseCategory(
			BaseCategoryBean baseCategoryBean, String selectId)
			throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!BaseUtil.isEmpty(this.baseCategoryService
				.findBaseCategoryByName(baseCategoryBean.getName()))) {
			result.put("newId", "none");
			return result;
		}
		if (BaseUtil.isEmpty(baseCategoryBean.getInternalCommand())) {
			baseCategoryBean.setInternalCommand("0");
		}
		WebCategory baseCategory = new WebCategory();
		BeanUtils.copyProperties(baseCategoryBean, baseCategory);
		baseCategory.setFlag("1");
		logger.debug("selectId=" + selectId);
		this.baseCategoryService.sameSaveBaseCategory(baseCategory, selectId);
		result.put("newId", baseCategory.getId());
		result.put("result", true);
		return result;
	}

	/**
	 * 次级菜单新增功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("secondarySaveBaseCategory")
	@ResponseBody
	public Map<String, Object> secondarySaveBaseCategory(
			BaseCategoryBean baseCategoryBean, String selectId)
			throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!BaseUtil.isEmpty(this.baseCategoryService
				.findBaseCategoryByName(baseCategoryBean.getName()))) {
			result.put("newId", "none");
			return result;
		}
		if (BaseUtil.isEmpty(baseCategoryBean.getInternalCommand())) {
			baseCategoryBean.setInternalCommand("0");
		}
		WebCategory baseCategory = new WebCategory();
		BeanUtils.copyProperties(baseCategoryBean, baseCategory);
		baseCategory.setFlag("1");
		logger.debug("selectId=" + selectId);
		this.baseCategoryService.secondarySaveBaseCategory(baseCategory,
				selectId);
		result.put("newId", baseCategory.getId());
		result.put("result", true);
		return result;
	}

	/**
	 * 菜单树删除功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("deleteBaseCategory")
	@ResponseBody
	public Map<String, Object> deleteBaseCategory(String id)
			throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		this.baseCategoryService.deleteAllBaseCategorysById(id);
		result.put("result", true);
		return result;
	}

	/**
	 * 菜单树移动功能
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("moveBaseCategory")
	@ResponseBody
	public Map<String, Object> moveBaseCategory(String oldNodeId,
			String newNodeId, String changeType) throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.debug("→→→↓↓→→拖动测试←←↓↓←←←←←");
		logger.debug("oldid:" + oldNodeId);
		logger.debug("newid:" + newNodeId);
		logger.debug("TYPE:" + changeType);
		logger.debug("→→→↑↑→→拖动测试←←↑↑←←←←←");
		// 做校验，判断变更类型
		if ("inside".equals(changeType) == false
				&& "before".equals(changeType) == false
				&& "after".equals(changeType) == false) {
			result.put("result", false);
			return result;
		}

		this.baseCategoryService.moveMenu(oldNodeId, newNodeId, changeType);
		result.put("result", true);
		return result;

	}
}
