package com.mall.web.admin.attachment.vo;

public class AttachmentBean {
	private String id;
	// 数据ID
	private String dataId;
	// 数据类型
	private String dataType;
	// 文件类型
	private String fileContentType;
	// 摘要
	private String digest;
	// 文件名（实际存放文件名）
	private String vFileName;
	// 文件名（真实文件名）
	private String fileName;
	//附件逻辑序号
    private int sequence;
    
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

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
