/**
 * 
 */
package com.mall.web.admin.common.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @功能说明：附件信息
 * @作者： 白勇红
 * @创建日期： 2010-10-17 @
 */
@Entity
@Table(name = "sys_attachment")
public class SysAttachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1041145864594127020L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	// 数据ID
	private String dataId;
	// 数据类型
	private String dataType;
	//文件类型
	  private String fileContentType;
	// 摘要
	private String digest;
	// 文件名（实际存放文件名）
	private String vFileName;
	// 文件名（真实文件名）
	private String fileName;
	// 文件内容
	private Blob fileValue;

	/***********************************************/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getVFileName() {
		return vFileName;
	}

	public void setVFileName(String fileName) {
		vFileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getFileValue() {
		return fileValue;
	}

	public void setFileValue(Blob fileValue) {
		this.fileValue = fileValue;
	}
	
	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof SysAttachment))
			return false;
		SysAttachment castOther = (SysAttachment) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
}
