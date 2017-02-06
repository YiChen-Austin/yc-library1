package com.mall.web.mall.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 微信订阅
 */
@Entity
@Table(name = "ml_weichat_subscribe")
public class MemberWeichatSubscribe implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;
	// 序号
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	private String id;
	// name
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "to_user")
	private String toUser;

	@Column(name = "from_user")
	private String fromUser;

	@Column(name = "create_time")
	private String createTime;

	@Column(name = "event")
	private String event;

	// 点击时间
	@Column(name = "add_time")
	private Date addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
