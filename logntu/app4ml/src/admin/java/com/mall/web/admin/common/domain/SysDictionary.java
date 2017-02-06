/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;


/**
 * @功能说明：系统字典实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-4-30
 */
@Entity
@Table(name = "sys_dictionary")
public class SysDictionary implements Serializable {
	private static final long serialVersionUID = 4881605025456652496L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//字典名称
	@Column(nullable = false, unique = true)
	private String name;
	//字典中文名称
	@Column(nullable = false, unique = true)
	private String cnName;
	//字典值
	@Column(nullable = false)
	private String value;
	//备注
	@Column(length = 4000)
	private String remark;
	//标志位:'0':不可编辑;'1':可编辑;
	@Column(nullable = false)
	private Character flag = CommonConstant.FLAG_IMMUTABLE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getFlag() {
		return flag;
	}

	public void setFlag(Character flag) {
		if (!BaseUtil.isEmpty(flag))
			this.flag = flag;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysDictionary))
			return false;
		SysDictionary castOther = (SysDictionary) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
