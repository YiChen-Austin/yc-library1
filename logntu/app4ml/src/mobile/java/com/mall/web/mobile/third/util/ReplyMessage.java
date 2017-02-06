package com.mall.web.mobile.third.util;

public class ReplyMessage {
	private String FuncFlag;// 消息编号
	private String fromUserName;// 发送人
	private String toUserName;// 接收人
	private String content;// 内容
	private String messageType;// 消息类型
	private String createTime;// 创建日期

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(String funcFlag) {
		FuncFlag = funcFlag;
	}

	public String toString() {
		return "createTime:" + getCreateTime() + "\ntoUserName:"
				+ getToUserName() + "\n FromUserName:" + getFromUserName()
				+ "\nmessageType:" + getMessageType() + "\ncontent:"
				+ getContent();
	}
}
