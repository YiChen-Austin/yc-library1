/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.mall.common.util.BaseUtil;


/**
 * @功能说明：系统业务字典类别
 * @作者： 印鲜刚
 * @创建日期： 2010-5-5
 * @
 */
@Entity
@Table(name = "sys_business_dictionary")
public class SysBusinessDictionary implements Serializable {
	private static final long serialVersionUID = 4881605025456652496L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//标志位:'0':不可编辑;'1':可编辑;
	@Column(nullable = false)
	private Character flag;
	//字典中文名称
	@Column(nullable = false, unique = true)
	private String cnName;
	//字典英文名称
	@Column(nullable = false, unique = true)
	private String enName;
	//备注
	@Column(length = 4000)
	private String remark;
	@OneToMany(mappedBy = "businessDictionary", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SysBusinessDictionaryDetail> businessDictionaryDetails = new ArrayList<SysBusinessDictionaryDetail>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SysBusinessDictionaryDetail> getBusinessDictionaryDetails() {
		return businessDictionaryDetails;
	}

	public void setBusinessDictionaryDetails(List<SysBusinessDictionaryDetail> businessDictionaryDetails) {
		this.businessDictionaryDetails = businessDictionaryDetails;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysBusinessDictionary))
			return false;
		SysBusinessDictionary castOther = (SysBusinessDictionary) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
