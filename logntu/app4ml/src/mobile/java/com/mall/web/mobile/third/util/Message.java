package com.mall.web.mobile.third.util;

public class Message {
	private String msgId;// 消息编号
	private String fromUserName;// 发送人
	private String toUserName;// 接收人
	private String content;// 内容
	private String messageType;// 消息类型
	private String event;//事件类型
	private String eventKey;//事件KEY值，qrscene_为前缀，后面为二维码的参数值
	private String ticket;//二维码的ticket，可用来换取二维码图片
	private String createTime;// 创建日期
	/////////////////////////
	private String latitude;
	private String longitude;
	private String precision;
	////////////////////////
	private String menuId;

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

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String toString() {
		return "msgId:" + getMsgId() + "\ncreateTime:" + getCreateTime()
				+ "\ntoUserName:" + getToUserName() + "\n FromUserName:"
				+ getFromUserName() + "\nmessageType:" + getMessageType()
				+ "\ncontent:" + getContent();
	}
}
