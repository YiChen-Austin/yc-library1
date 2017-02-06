/**
 * 
 */
package com.mall.web.admin.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.vo.TreeNodeEasyBean;

/**
 * @功能说明：树形结构处理工具类
 * @作者： 印鲜刚
 * @创建日期： 2010-5-15 @
 */
public class TreeEasyUtil {
	private static Logger logger = Logger.getLogger(TreeEasyUtil.class);

	/**
	 * 将domain对象类型的List树节点进行排序整理转换成VO[MenuTreeNode]对象返回
	 * 
	 * @param root
	 * @param entityTreeList
	 * @return treeNodeBean
	 */
	public static TreeNodeEasyBean convertMenuTreeNode(Object root,
			List<?> entityTreeList) throws Exception {

		TreeNodeEasyBean treeNodeBean = new TreeNodeEasyBean();
		List<TreeNodeEasyBean> menuTreeNodes = new ArrayList<TreeNodeEasyBean>();
		for (Object entity : entityTreeList) {
			menuTreeNodes.add(entityConvertTreeNodeEasyBean(entity));
		}
		TreeNodeEasyBean rootNode = entityConvertTreeNodeEasyBean(BaseUtil
				.isEmpty(root) ? getRootNode(entityTreeList) : root);
		if (!BaseUtil.isEmpty(root) && rootNode.getAttr().get("rel").equals("")) {
			rootNode.getAttr().put("rel", "root");
		}
		treeNodeBean = getSubNode(menuTreeNodes, rootNode);
		return treeNodeBean;
	}

	/**
	 * 将domain对象类型的List树节点进行排序整理转换成VO[MenuTreeNode]对象返回
	 * 
	 * @param entityTreeList
	 * @return treeNodeBean
	 */
	public static TreeNodeEasyBean convertMenuTreeNode(List<?> entityTreeList)
			throws Exception {
		return convertMenuTreeNode(null, entityTreeList);
	}

	/**
	 * 获取所有节点的根节点
	 * 
	 * @param entityTreeList
	 * @return
	 */
	private static Object getRootNode(List<?> entityTreeList) throws Exception {
		for (Object entity : entityTreeList) {
			Method getParentIdMethod = entity.getClass().getMethod(
					"getParentId", new Class[] {});
			String parentId = (String) getParentIdMethod.invoke(entity,
					new Object[] {});
			if (BaseUtil.isEmpty(parentId))
				return entity;
		}
		return null;
	}

	/**
	 * 根据根节点获取直接和间接子节点[迭代方法实现]
	 * 
	 * @param list
	 *            所有树节点
	 * @param parent
	 *            根节点
	 * @return
	 */
	private static TreeNodeEasyBean getSubNode(final List<TreeNodeEasyBean> list,
			TreeNodeEasyBean parent) {
		TreeNodeEasyBean treeNodeBean = new TreeNodeEasyBean();
		List<TreeNodeEasyBean> templist = new ArrayList<TreeNodeEasyBean>();
		// 查找满足条件的的节点,并加入临时集合中
		for (TreeNodeEasyBean treebean : list) {
			if (treebean.getAttr().get("parentId")
					.equals(parent.getAttr().get("id"))) {
				templist.add(treebean);
			}
		}
		if (templist.size() != 0) {
			// 查询完毕排序
			templist = orderNoSort(templist);
		}

		List<TreeNodeEasyBean> children = new ArrayList<TreeNodeEasyBean>();
		for (TreeNodeEasyBean temptreebean : templist) {
			// 递归调用获取子节点
			TreeNodeEasyBean tempTreebean = getSubNode(list, temptreebean);
			children.add(tempTreebean);
		}
		treeNodeBean.setChildren(children);
		treeNodeBean.setData(parent.getData());
		treeNodeBean.setAttr(parent.getAttr());
		return treeNodeBean;
	}

	/**
	 * 排序功能
	 * 
	 * @param list
	 */
	private static List<TreeNodeEasyBean> orderNoSort(List<TreeNodeEasyBean> list) {

		Collections.sort(list, new Comparator<TreeNodeEasyBean>() {
			public int compare(TreeNodeEasyBean treeNodeBean0,
					TreeNodeEasyBean treeNodeBean1) {
				Integer oldOrderNo = Integer.parseInt(treeNodeBean0.getAttr()
						.get("orderNo"));
				Integer newOrderNo = Integer.parseInt(treeNodeBean1.getAttr()
						.get("orderNo"));
				return oldOrderNo.compareTo(newOrderNo);
			}
		});
		return list;
	}

	/**
	 * 类型转换[实体转换成树节点TreeNodeEasyBean对象]
	 * 
	 * @param entityNode
	 * @return
	 * @throws Exception
	 */
	private static TreeNodeEasyBean entityConvertTreeNodeEasyBean(Object entityNode)
			throws Exception {
		Method getNameMethod = entityNode.getClass().getMethod("getName",
				new Class[] {});
		String name = (String) getNameMethod
				.invoke(entityNode, new Object[] {});
		Method getIdMethod = entityNode.getClass().getMethod("getId",
				new Class[] {});
		String id = (String) getIdMethod.invoke(entityNode, new Object[] {});
		Method getOrderNoMethod = entityNode.getClass().getMethod("getOrderNo",
				new Class[] {});
		Integer orderNo = (Integer) getOrderNoMethod.invoke(entityNode,
				new Object[] {});
		Method getParentIdMethod = entityNode.getClass().getMethod(
				"getParentId", new Class[] {});
		String parentId = (String) getParentIdMethod.invoke(entityNode,
				new Object[] {});
		Method getRemarkMethod = entityNode.getClass().getMethod("getRemark",
				new Class[] {});
		String remark = (String) getRemarkMethod.invoke(entityNode,
				new Object[] {});
		TreeNodeEasyBean treeNodeBean = new TreeNodeEasyBean();
		treeNodeBean.setData(name);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("parentId", BaseUtil.isEmpty(parentId) ? "" : parentId);
		map.put("orderNo",
				BaseUtil.isEmpty(orderNo) ? "" : String.valueOf(orderNo));
		map.put("remark", BaseUtil.isEmpty(remark) ? "" : remark);
		try {
			Method getNodeImage = entityNode.getClass().getMethod(
					"getNodeImage", new Class[] {});
			String nodeImage = (String) getNodeImage.invoke(entityNode,
					new Object[] {});
			if (BaseUtil.isEmpty(nodeImage))
				map.put("rel", "");
			else {
				if (nodeImage.indexOf(".") > -1)
					map.put("rel",
							nodeImage.substring(0, nodeImage.indexOf(".")));
				else
					map.put("rel", nodeImage);
			}
		} catch (Exception e) {
			map.put("rel", "");
		}
		try {
			Method getFlagMethod = entityNode.getClass().getMethod("getFlag",
					new Class[] {});
			String flag = (String) getFlagMethod.invoke(entityNode,
					new Object[] {});
			map.put("flag", flag);
		} catch (Exception e) {
			map.put("flag", "");
		}

		try {
			Method getFlagMethod = entityNode.getClass().getMethod(
					"getInternalCommand", new Class[] {});
			String internalCommand = (String) getFlagMethod.invoke(entityNode,
					new Object[] {});
			map.put("internalCommand", internalCommand);
		} catch (Exception e) {
			map.put("internalCommand", "");
		}

		try {
			Method getPageUrlMethod = entityNode.getClass().getMethod(
					"getPageUrl", new Class[] {});
			String pageUrl = (String) getPageUrlMethod.invoke(entityNode,
					new Object[] {});
			map.put("pageUrl", BaseUtil.isEmpty(pageUrl) ? "" : pageUrl);
		} catch (Exception e) {
			map.put("pageUrl", "");
		}
		try {
			Method getShortNameMethod = entityNode.getClass().getMethod(
					"getShortName", new Class[] {});
			String shortName = (String) getShortNameMethod.invoke(entityNode,
					new Object[] {});
			map.put("shortName", BaseUtil.isEmpty(shortName) ? "" : shortName);
		} catch (Exception e) {
			map.put("shortName", "");
		}
		try {
			Method getOrganizationNumberMethod = entityNode.getClass()
					.getMethod("getOrganizationNumber", new Class[] {});
			String organizationNumber = (String) getOrganizationNumberMethod
					.invoke(entityNode, new Object[] {});
			map.put("organizationNumber",
					BaseUtil.isEmpty(organizationNumber) ? ""
							: organizationNumber);
		} catch (Exception e) {
			map.put("organizationNumber", "");
		}
		// 信息管理栏目数部分
		try {
			Method getAdministratorNameMethod = entityNode.getClass()
					.getMethod("getAdministratorName", new Class[] {});
			String administratorName = (String) getAdministratorNameMethod
					.invoke(entityNode, new Object[] {});
			map.put("administratorName",
					BaseUtil.isEmpty(administratorName) ? ""
							: administratorName);
		} catch (Exception e) {
			map.put("administratorName", "");
		}

		try {
			Method getAdministratorIdMethod = entityNode.getClass().getMethod(
					"getAdministratorId", new Class[] {});
			String administratorId = (String) getAdministratorIdMethod.invoke(
					entityNode, new Object[] {});
			map.put("administratorId", BaseUtil.isEmpty(administratorId) ? ""
					: administratorId);
		} catch (Exception e) {
			map.put("administratorId", "");
		}
		try {
			Method getPublisherNameMethod = entityNode.getClass().getMethod(
					"getPublisherName", new Class[] {});
			String publisherName = (String) getPublisherNameMethod.invoke(
					entityNode, new Object[] {});
			map.put("publisherName", BaseUtil.isEmpty(publisherName) ? ""
					: publisherName);
		} catch (Exception e) {
			map.put("publisherName", "");
		}
		try {
			Method getPublisherIdMethod = entityNode.getClass().getMethod(
					"getPublisherId", new Class[] {});
			String publisherId = (String) getPublisherIdMethod.invoke(
					entityNode, new Object[] {});
			map.put("publisherId", BaseUtil.isEmpty(publisherId) ? ""
					: publisherId);
		} catch (Exception e) {
			map.put("publisherId", "");
		}
		treeNodeBean.setAttr(map);
		return treeNodeBean;
	}
}
