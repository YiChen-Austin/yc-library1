/**
 * 
 */
package com.mall.web.admin.news.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysUser;

/**
 * @功能说明：论坛、新闻发布实体类
 * @作者： 印鲜刚
 * @创建日期： 2010-9-23
 * @
 */
@Entity
@Table(name = "sys_common_article")
public class SysCommonArticle implements Serializable {
	private static final long serialVersionUID = 617379206831244399L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//发布范围
	private String scope = "no";
	// 标题
	private String title;
	// 阅读次数
	private Integer readCount = 0;
	// 心情
	private String emotion;
	// 发布时间
	private Date publishTime;
	// 发布作者
	@ManyToOne
	private SysUser publishAuthor;
	// 发布部门
	@ManyToOne
	private SysOrganization publishDepartment;
	// 内容
	private String content;
	// 附件
	@Lob 
	@Basic(fetch=FetchType.LAZY) 
	@Column(name="affix", columnDefinition="BLOB", nullable=true)
	private Blob affix;
	// 附件名称
	private String affixName;
	// 红头文件名称
	private String redDocumentName;
	// 文件字号
	private String documentNumber;
	// 接收部门
	@OneToMany(fetch = FetchType.LAZY)
	private List<SysOrganization> receiveDeparts = new ArrayList<SysOrganization>();
	// 接收员工
	@OneToMany(fetch = FetchType.LAZY)
	private List<SysUser> receiveEmps = new ArrayList<SysUser>();
	@ManyToOne
	private SysCommonArticle commonArticle;
	@OneToMany(mappedBy = "commonArticle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SysCommonArticle> commonArticles = new ArrayList<SysCommonArticle>();
	@ManyToOne
	private SysCommonCategory commonCategory;
	//是否匿名:匿名Y;非匿名N
	private String anonymous;
	//是否锁帖:锁帖Y;未锁帖N
	private String lockedPost;
	private String flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public SysUser getPublishAuthor() {
		return publishAuthor;
	}

	public void setPublishAuthor(SysUser publishAuthor) {
		this.publishAuthor = publishAuthor;
	}

	public SysOrganization getPublishDepartment() {
		return publishDepartment;
	}

	public void setPublishDepartment(SysOrganization publishDepartment) {
		this.publishDepartment = publishDepartment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blob getAffix() {
		return affix;
	}

	public void setAffix(Blob affix) {
		this.affix = affix;
	}

	public String getRedDocumentName() {
		return redDocumentName;
	}

	public void setRedDocumentName(String redDocumentName) {
		this.redDocumentName = redDocumentName;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getAffixName() {
		return affixName;
	}

	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}

	public List<SysOrganization> getReceiveDeparts() {
		return receiveDeparts;
	}

	public void setReceiveDeparts(List<SysOrganization> receiveDeparts) {
		this.receiveDeparts = receiveDeparts;
	}

	public List<SysUser> getReceiveEmps() {
		return receiveEmps;
	}

	public void setReceiveEmps(List<SysUser> receiveEmps) {
		this.receiveEmps = receiveEmps;
	}

	public SysCommonArticle getCommonArticle() {
		return commonArticle;
	}

	public void setCommonArticle(SysCommonArticle commonArticle) {
		this.commonArticle = commonArticle;
	}

	public List<SysCommonArticle> getCommonArticles() {
		return commonArticles;
	}

	public void setCommonArticles(List<SysCommonArticle> commonArticles) {
		this.commonArticles = commonArticles;
	}

	public SysCommonCategory getCommonCategory() {
		return commonCategory;
	}

	public void setCommonCategory(SysCommonCategory commonCategory) {
		this.commonCategory = commonCategory;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public String getLockedPost() {
		return lockedPost;
	}

	public void setLockedPost(String lockedPost) {
		this.lockedPost = lockedPost;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysCommonArticle))
			return false;
		SysCommonArticle castOther = (SysCommonArticle) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}
