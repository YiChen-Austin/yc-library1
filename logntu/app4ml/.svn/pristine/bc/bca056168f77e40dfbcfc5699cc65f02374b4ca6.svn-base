package com.mall.web.pay.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 订单支付对应表
 * 
 * @author ventrue
 * @category 订单支付对应表
 */
@Entity
@Table(name = "ml_pay_order")
public class PayOrders implements Serializable {
	private static final long serialVersionUID = -997688998285826668L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;
	/**********************/
	/**
	 * 订单
	 */
	// @ManyToOne
	// private YzmOrder order;
	@Column(name = "order_order_id")
	private Long orderId;
	/**
	 * 支付表
	 */
	@ManyToOne
	private PayPrimary pay;//
	/**
	 * 是否最终成功
	 */
	@Column(name = "is_pay_succ")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean isPaySucc = false;//

	/**
	 * 支付状态
	 */
	@Column(name = "pay_status", length = 2)
	private String payStatus;// 1、支付中，2、支付结束
	/**
	 * 订单类型
	 */
	@Column(name = "order_type", length = 2)
	private String orderType;// 订单类型
	/**
	 * 支付发起时间
	 */
	@Column(name = "pay_apply_time")
	private Date payApplytime;
	/**
	 * 支付结束时间
	 */
	@Column(name = "pay_over_time")
	private Date payOverTime;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// public YzmOrder getOrder() {
	// return order;
	// }
	//
	// public void setOrder(YzmOrder order) {
	// this.order = order;
	// }

	public PayPrimary getPay() {
		return pay;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setPay(PayPrimary pay) {
		this.pay = pay;
	}

	public Boolean getIsPaySucc() {
		return isPaySucc;
	}

	public void setIsPaySucc(Boolean isPaySucc) {
		this.isPaySucc = isPaySucc;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayApplytime() {
		return payApplytime;
	}

	public void setPayApplytime(Date payApplytime) {
		this.payApplytime = payApplytime;
	}

	public Date getPayOverTime() {
		return payOverTime;
	}

	public void setPayOverTime(Date payOverTime) {
		this.payOverTime = payOverTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
