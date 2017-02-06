/**
 * 
 */
package com.mall.web.admin.news.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysUser;


/**
 * @功能说明：栏目管理实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-9-16
 * @
 */
@Entity
@Table(name = "sys_common_category")
public class SysCommonCategory implements Serializable {
	private static final long serialVersionUID = 2215658811001820773L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	// 名称
	private String name;
	// 备注
	@Column(length = 4000)
	private String remark;
	// 显示顺序号
	private Integer orderNo;
	//标志位:discussion:讨论;news:信息
	private String flag = CommonConstant.COMMON_CATEGORY_DISCUSSION;
	//所属公司
	@ManyToOne
	private SysOrganization company;
	//管理人员
	@ManyToOne
	private SysUser administrator;
	//发布人员
	@ManyToOne
	private SysUser publisher;
	/**
	 * 2011-4-28增加
	 */
	//管理人员ID
	private String administratorIds;
	private String administratorName;
	//发布人ID
	private String publisherIds;
	private String publisherName;

	@ManyToOne
	private SysCommonCategory commonCategory;
	@OneToMany(mappedBy = "commonCategory", fetch = FetchType.EAGER)
	private List<SysCommonCategory> commonCategories = new ArrayList<SysCommonCategory>();
	@OneToMany(mappedBy = "commonCategory", cascade = { CascadeType.ALL })
	private List<SysCommonArticle> commonArticles = new ArrayList<SysCommonArticle>();

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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public SysCommonCategory getCommonCategory() {
		return commonCategory;
	}

	public void setCommonCategory(SysCommonCategory commonCategory) {
		this.commonCategory = commonCategory;
	}

	public List<SysCommonCategory> getCommonCategories() {
		return commonCategories;
	}

	public void setCommonCategories(List<SysCommonCategory> commonCategories) {
		this.commonCategories = commonCategories;
	}

	public SysOrganization getCompany() {
		return company;
	}

	public void setCompany(SysOrganization company) {
		this.company = company;
	}

	public SysUser getAdministrator() {
		return administrator;
	}

	public void setAdministrator(SysUser administrator) {
		this.administrator = administrator;
	}

	public SysUser getPublisher() {
		return publisher;
	}

	public void setPublisher(SysUser publisher) {
		this.publisher = publisher;
	}

	public String getParentId() {
		if (BaseUtil.isEmpty(this.getCommonCategory()))
			return null;
		else
			return this.getCommonCategory().getId();
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getAdministratorId() {
		return this.getAdministratorIds();
	}

	public String getPublisherId() {
		return this.getPublisherIds();
	}

	public List<SysCommonArticle> getCommonArticles() {
		return commonArticles;
	}

	public void setCommonArticles(List<SysCommonArticle> commonArticles) {
		this.commonArticles = commonArticles;
	}

	public String getAdministratorIds() {
		return administratorIds;
	}

	public void setAdministratorIds(String administratorIds) {
		this.administratorIds = administratorIds;
	}

	public String getPublisherIds() {
		return publisherIds;
	}

	public void setPublisherIds(String publisherIds) {
		this.publisherIds = publisherIds;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysCommonCategory))
			return false;
		SysCommonCategory castOther = (SysCommonCategory) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
