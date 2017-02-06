package com.mall.web.mall.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 支付通知记录
 * 
 * @author ventrue
 * @category 支付通知记录日志
 */
@Entity
@Table(name = "ml_pay_notice")
public class MemberPayNotice implements Serializable {
	private static final long serialVersionUID = -997688998285826668L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "notice_id", length = 32)
	private String noticeId;
	/**********************/
	@Column(name = "order_sn")
	private String orderSn;// 订单编号
	@Column(name = "notice_type")
	private String noticeType;// 通知类型（来源于订单类型）
	@Column(name = "server_code")
	private String serverCode;// 服务器编号
	@Column(name = "user_code")
	private String userCode;// 用户编号
	@Column(name = "role_code")
	private String roleCode;// 角色编号
	@Column(name = "product_code")
	private String productCode;// 产品编号
	@Column(name = "game_value")
	private BigDecimal gameValue;// 游戏值（元宝）
	@Column(name = "pay_value")
	private BigDecimal payValue;// 支付值（RMB）
	@Column(name = "notice_status")
	private int noticeStatus;// 订单编号
	@Column(name = "notice_count")
	private int noticeCount;// 订单编号
	@Column(name = "last_time")
	private Date lastTime;// 订单编号
	@Column(name = "remark")
	private String remark;// 备注，额外数据
	@Column(name = "insert_time")
	private Date insertTime;// 订单编号

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getGameValue() {
		return gameValue;
	}

	public void setGameValue(BigDecimal gameValue) {
		this.gameValue = gameValue;
	}

	public BigDecimal getPayValue() {
		return payValue;
	}

	public void setPayValue(BigDecimal payValue) {
		this.payValue = payValue;
	}

	public int getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(int noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
