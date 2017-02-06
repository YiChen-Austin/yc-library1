/**
 * 
 */
package com.mall.web.admin.security.vo;

import java.io.Serializable;

/**
 * @功能说明：存放系统参数和业务字典的Bean对象
 * @作者： 印鲜刚
 * @创建日期： 2010-5-30
 * @
 */
public class DictionaryBean implements Serializable {
	private static final long serialVersionUID = 897816884310009892L;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
