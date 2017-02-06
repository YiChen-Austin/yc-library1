/**
 * 
 */
package com.mall.web.admin.news.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * @功能说明：公共论坛,信息发布VO对象
 * @作者： 印鲜刚
 * @创建日期： 2010-9-23
 * @
 */
public class CommonArticleBean implements Serializable {
	private static final long serialVersionUID = -3481354756868734426L;
	private String id;
	//删除多条记录时用
	private String deleteIDs;
	// 标题
	private String title;
	// 阅读次数
	private Integer readCount = 0;
	// 心情
	private String emotion;
	// 发布时间
	private Date publishTime;
	// 发布时间
	private String publicTimeStr;
	// 发布作者
	private String publishAuthorName;
	// 发布作者Id
	private String publishAuthorId;
	// 发布部门
	private String publishDepartmentName;
	// 发布部门
	private String publishDepartmentId;
	// 最后回复时间|最后回复人
	private String lastTimeAuthorName;
	// 内容
	private String content;
	// 红头文件名称
	private String redDocumentName;
	// 文件字号
	private String documentNumber;
	//父Id
	private String parentId;
	//栏目管理类别ID
	private String commonCategoryId;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	// 附件名称
	private String affixName;
	// 是否图片
	private String image = "no";
	private String scope = "no";
	// 接收部门
	private String receiveDeparts;
	//接收部门IDS
	private String receiveDepartIDS;
	// 接收员工
	private String receiveEmps;
	private String receiveEmpIDS;
	private String lockedButton;
	private String deleteButton;

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

	public String getPublishAuthorName() {
		return publishAuthorName;
	}

	public void setPublishAuthorName(String publishAuthorName) {
		this.publishAuthorName = publishAuthorName;
	}

	public String getPublishAuthorId() {
		return publishAuthorId;
	}

	public void setPublishAuthorId(String publishAuthorId) {
		this.publishAuthorId = publishAuthorId;
	}

	public String getPublishDepartmentName() {
		return publishDepartmentName;
	}

	public void setPublishDepartmentName(String publishDepartmentName) {
		this.publishDepartmentName = publishDepartmentName;
	}

	public String getPublishDepartmentId() {
		return publishDepartmentId;
	}

	public void setPublishDepartmentId(String publishDepartmentId) {
		this.publishDepartmentId = publishDepartmentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCommonCategoryId() {
		return commonCategoryId;
	}

	public void setCommonCategoryId(String commonCategoryId) {
		this.commonCategoryId = commonCategoryId;
	}

	public String getLastTimeAuthorName() {
		return lastTimeAuthorName;
	}

	public void setLastTimeAuthorName(String lastTimeAuthorName) {
		this.lastTimeAuthorName = lastTimeAuthorName;
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

	public String getAffixName() {
		return affixName;
	}

	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDeleteIDs() {
		return deleteIDs;
	}

	public void setDeleteIDs(String deleteIDs) {
		this.deleteIDs = deleteIDs;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getReceiveDeparts() {
		return receiveDeparts;
	}

	public void setReceiveDeparts(String receiveDeparts) {
		this.receiveDeparts = receiveDeparts;
	}

	public String getReceiveEmps() {
		return receiveEmps;
	}

	public void setReceiveEmps(String receiveEmps) {
		this.receiveEmps = receiveEmps;
	}

	public String getReceiveDepartIDS() {
		return receiveDepartIDS;
	}

	public void setReceiveDepartIDS(String receiveDepartIDS) {
		this.receiveDepartIDS = receiveDepartIDS;
	}

	public String getReceiveEmpIDS() {
		return receiveEmpIDS;
	}

	public void setReceiveEmpIDS(String receiveEmpIDS) {
		this.receiveEmpIDS = receiveEmpIDS;
	}

	public String getLockedButton() {
		return lockedButton;
	}

	public void setLockedButton(String lockedButton) {
		this.lockedButton = lockedButton;
	}

	public String getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(String deleteButton) {
		this.deleteButton = deleteButton;
	}

	public String getPublicTimeStr() {
		return publicTimeStr;
	}

	public void setPublicTimeStr(String publicTimeStr) {
		this.publicTimeStr = publicTimeStr;
	}

}
