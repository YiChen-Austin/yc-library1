package com.mall.web.admin.payrecord.vo;

// Generated 2014-7-18 13:09:35 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

public class AdminPayRecordVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1886342381473954673L;
	private Long record_id; // 记录编号
	private String primary_id; // 支付主表编号
	private Integer service_type; // 支付主表编号
	private Long type_id; // 支付类别
	private Long out_user_id; // 支出方
	private Long in_user_id; // 收入方
	private BigDecimal money; // 金额
	private BigDecimal out_balance; // 支出方账户余额
	private BigDecimal in_balance; // 收入方账户余额
	private BigDecimal point; // 消费积分
	private Integer status; // 状态
	private String create_time; // 创建时间
	private String description; // 描述

	private String requestTimeS;
	private String requestTimeE;

	public Long getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Long record_id) {
		this.record_id = record_id;
	}

	public String getPrimary_id() {
		return primary_id;
	}

	public void setPrimary_id(String primary_id) {
		this.primary_id = primary_id;
	}

	public Integer getService_type() {
		return service_type;
	}

	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getOut_user_id() {
		return out_user_id;
	}

	public void setOut_user_id(Long out_user_id) {
		this.out_user_id = out_user_id;
	}

	public Long getIn_user_id() {
		return in_user_id;
	}

	public void setIn_user_id(Long in_user_id) {
		this.in_user_id = in_user_id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getOut_balance() {
		return out_balance;
	}

	public void setOut_balance(BigDecimal out_balance) {
		this.out_balance = out_balance;
	}

	public BigDecimal getIn_balance() {
		return in_balance;
	}

	public void setIn_balance(BigDecimal in_balance) {
		this.in_balance = in_balance;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestTimeS() {
		return requestTimeS;
	}

	public void setRequestTimeS(String requestTimeS) {
		this.requestTimeS = requestTimeS;
	}

	public String getRequestTimeE() {
		return requestTimeE;
	}

	public void setRequestTimeE(String requestTimeE) {
		this.requestTimeE = requestTimeE;
	}

}
