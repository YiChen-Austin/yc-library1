/**
 * 
 */
package com.mall.web.admin.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;


/**
 * @功能说明：业务字典明细
 * @作者： 印鲜刚
 * @创建日期： 2010-5-5
 * @
 */
@Entity
@Table(name = "sys_business_dictionary_detail")
public class SysBusinessDictionaryDetail {
	private static final long serialVersionUID = 4881605025456652496L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//名称
	@Column(nullable = false)
	private String name;
	//值
	@Column(nullable = false)
	private String value;
	//备注
	@Column(length = 4000)
	private String remark;
	//业务字典
	@ManyToOne
	private SysBusinessDictionary businessDictionary;

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

	public SysBusinessDictionary getBusinessDictionary() {
		return businessDictionary;
	}

	public void setBusinessDictionary(SysBusinessDictionary businessDictionary) {
		this.businessDictionary = businessDictionary;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysBusinessDictionaryDetail))
			return false;
		SysBusinessDictionaryDetail castOther = (SysBusinessDictionaryDetail) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
