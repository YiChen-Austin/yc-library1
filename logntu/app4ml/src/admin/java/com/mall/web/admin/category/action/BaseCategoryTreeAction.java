package com.mall.web.admin.category.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.category.service.BaseCategoryService;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.common.utils.TreeUtil;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.security.vo.TreeNodeBean;
import com.mall.web.mall.domain.WebCategory;


/**
 * 
 * 功能说明：根据登录用户ID显示该用户首页菜单
 * 
 * @作者： 张可贵 创建日期： 2010-5-13
 */
@Controller
@RequestMapping("/*")
public class BaseCategoryTreeAction extends BaseAction {
	private static Logger logger = Logger
			.getLogger(BaseCategoryTreeAction.class);
	@Autowired
	private BaseCategoryService baseCategoryService;


	/**
	 * 根据选择的头部菜单，取得其相应的左侧菜单
	 * 
	 * @return
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("getLeftCategory")
	@ResponseBody
	public Map<String, Object> findLeftCategory(HttpServletRequest request,
			String parentId) throws FrameworkException, Exception {
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		logger.debug("loginUser=" + loginUser.getId());
		List<WebCategory> baseCategorys = this.baseCategoryService
				.findBaseCategoryAndSubBaseCategorys(parentId);
		TreeNodeBean treeNodeBean = TreeUtil.convertMenuTreeNode(
				this.baseCategoryService.findBaseCategoryById(parentId),
				baseCategorys);

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
	 * 取得头部菜单列表
	 * 
	 * @return
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("initTopCategory")
	@ResponseBody
	public Map<String, Object> initTopCategory() throws FrameworkException {
		ArrayList<Map<String, String>> baseCategoryList = new ArrayList<Map<String, String>>();
		List<WebCategory> baseCategorys = this.baseCategoryService
				.findOneLayerBaseCategorys();
		for (WebCategory baseCategory : baseCategorys) {
			Map<String, String> oneLevel = new HashMap<String, String>();
			oneLevel.put("menuId", baseCategory.getId());
			oneLevel.put("menuName", baseCategory.getName());
			baseCategoryList.add(oneLevel);

		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("baseCategoryList", baseCategoryList);
		return result;
	}
}
