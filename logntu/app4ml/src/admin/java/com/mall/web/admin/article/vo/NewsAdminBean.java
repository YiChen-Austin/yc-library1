package com.mall.web.admin.article.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 药物
 */
public class NewsAdminBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8050742900264963213L;
	private String id;
	// 类别
	private String categoryId;
	private String categoryName;

	// 阅读次数
	private Integer readCount;
	// 赞次数
	private Integer supportCount;
	// 踩次数
	private Integer unSupportCount;
	// 投票次数
	private Integer voteCount;

	// 发布时间
	private Date publishTime;

	// 标题
	private String title;
	// 内容
	private String artContent;
	// 摘要
	private String artAbstract;

	private Date startTime;
	private Date endTime;

	private String imgUrl;
	
	// 删除的ids
	private String deleteIds;
	//标签
	private String tag;
	private String publishOrgId;
	private String publishOrgName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(Integer supportCount) {
		this.supportCount = supportCount;
	}

	public Integer getUnSupportCount() {
		return unSupportCount;
	}

	public void setUnSupportCount(Integer unSupportCount) {
		this.unSupportCount = unSupportCount;
	}


	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public String getArtAbstract() {
		return artAbstract;
	}

	public void setArtAbstract(String artAbstract) {
		this.artAbstract = artAbstract;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPublishOrgId() {
		return publishOrgId;
	}

	public void setPublishOrgId(String publishOrgId) {
		this.publishOrgId = publishOrgId;
	}

	public String getPublishOrgName() {
		return publishOrgName;
	}

	public void setPublishOrgName(String publishOrgName) {
		this.publishOrgName = publishOrgName;
	}

}
