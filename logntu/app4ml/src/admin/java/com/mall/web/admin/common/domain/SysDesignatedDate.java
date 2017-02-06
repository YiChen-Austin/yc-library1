/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @功能说明：特定日期处理
 * @作者： 郭明军
 * @创建日期：2010年6月4日
 * 
 */
@Entity
@Table(name = "sys_designated_date")
public class SysDesignatedDate implements Serializable {
	private static final long serialVersionUID = -9140849782783514598L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;// 序号
	@Column(nullable = false, unique = true)
	private Date edate;// 日期
	private String name;// 名称
	@Column(nullable = false, length = 2)
	private String identifer;// 标识符: 'W'为特定工作日,'H'为特定节假日
	// 备注
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifer() {
		return identifer;
	}

	public void setIdentifer(String identifer) {
		this.identifer = identifer;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysDesignatedDate))
			return false;
		SysDesignatedDate castOther = (SysDesignatedDate) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
