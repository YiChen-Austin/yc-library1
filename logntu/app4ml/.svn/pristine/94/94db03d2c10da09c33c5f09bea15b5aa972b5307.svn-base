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

/**
 * 功能说明：定时调度任务中的 工件 实体类
 * @作者： 张可贵
 * 创建日期： 2010-5-24
 */
@Entity
@Table(name = "sys_job_details")
public class SysJobDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3782455045664103916L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//计划名称
	@Column(length = 200, nullable = false)
	private String jobName;
	//计划描述
	@Column(length = 250, nullable = true)
	private String description;
	//工件BeanID
	@Column(length = 250, nullable = false)
	private String beanId;
	//要执行的Bean的方法
	@Column(length = 250, nullable = false)
	private String executeMethod;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getExecuteMethod() {
		return executeMethod;
	}

	public void setExecuteMethod(String executeMethod) {
		this.executeMethod = executeMethod;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysJobDetails))
			return false;
		SysJobDetails castOther = (SysJobDetails) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
}
