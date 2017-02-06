package com.mall.www4lt.article.vo;

import java.io.Serializable;

import com.mall.common.util.BaseUtil;

/**
 * 药物
 */
public class ArticleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8050742900264963213L;
	private String id;
	// 摘要
	private String artAbstract;
	//内容
	private String content;
	// 发布时间
	private String publishTime;
	// 阅读次数
	private Integer readCount;
	// 赞次数
	private Integer supportCount;
	// 踩次数
	private Integer unSupportCount;
	// 标题
	private String title;
	// 类别
	private String categoryId;

	private String categoryName;
	//发布人
	private String publishAuthorId;
	private String publishAuthorName;

	private String imgUrl;

	//标签
	private String tag;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the artAbstract
	 */
	public String getArtAbstract() {
		return artAbstract;
	}

	/**
	 * @param artAbstract the artAbstract to set
	 */
	public void setArtAbstract(String artAbstract) {
		this.artAbstract = artAbstract;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the publishTime
	 */
	public String getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the readCount
	 */
	public Integer getReadCount() {
		return BaseUtil.null2zero(readCount);
	}

	/**
	 * @param readCount the readCount to set
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	/**
	 * @return the supportCount
	 */
	public Integer getSupportCount() {
		return BaseUtil.null2zero(supportCount);
	}

	/**
	 * @param supportCount the supportCount to set
	 */
	public void setSupportCount(Integer supportCount) {
		this.supportCount = supportCount;
	}

	/**
	 * @return the unSupportCount
	 */
	public Integer getUnSupportCount() {
		return BaseUtil.null2zero(unSupportCount);
	}

	/**
	 * @param unSupportCount the unSupportCount to set
	 */
	public void setUnSupportCount(Integer unSupportCount) {
		this.unSupportCount = unSupportCount;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the publishAuthorId
	 */
	public String getPublishAuthorId() {
		return publishAuthorId;
	}

	/**
	 * @param publishAuthorId the publishAuthorId to set
	 */
	public void setPublishAuthorId(String publishAuthorId) {
		this.publishAuthorId = publishAuthorId;
	}

	/**
	 * @return the publishAuthorName
	 */
	public String getPublishAuthorName() {
		return publishAuthorName;
	}

	/**
	 * @param publishAuthorName the publishAuthorName to set
	 */
	public void setPublishAuthorName(String publishAuthorName) {
		this.publishAuthorName = publishAuthorName;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

}
