package com.mall.web.admin.security.vo;

import java.util.List;
import java.util.Map;

/**
 * 功能说明：菜单树VO
 * 
 * @author 沈国仿 创建时间： 2010-4-29
 */
public class TreeNodeEasyBean {

	/**
	 * 节点的属性
	 */
	private Map<String, String> attr;

	/**
	 * 树节点数据
	 */
	private String data;
	private String text;

	/**
	 * 节点的状态（是否展开）
	 */
	private String state;

	/**
	 * 子节点仍然为MenuNode，无限递归。
	 */
	private List<TreeNodeEasyBean> children;

	public Map<String, String> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, String> attr) {
		this.attr = attr;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
		setText(data);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeNodeEasyBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeEasyBean> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
