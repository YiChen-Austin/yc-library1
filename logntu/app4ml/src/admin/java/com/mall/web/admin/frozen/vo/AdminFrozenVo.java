package com.mall.web.admin.frozen.vo;

// Generated 2014-7-18 13:09:35 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * Order generated by hbm2java
 */
public class AdminFrozenVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3549225880245149551L;

	private Integer frozen_id;
	private Integer user_id;
	private BigDecimal frozen;
	private BigDecimal valid_frozen;
	private Integer status;
	private String frozen_time;
	private String last_time;
	private String remark;

	private String bank_reg_name;
	private String bank_card_no;
	private String card_owner;
	private String bank_reg_pri;
	private String bank_reg_sub;

	private String frozenTimeS;
	private String frozenTimeE;

	public Integer getFrozen_id() {
		return frozen_id;
	}

	public void setFrozen_id(Integer frozen_id) {
		this.frozen_id = frozen_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public BigDecimal getValid_frozen() {
		return valid_frozen;
	}

	public void setValid_frozen(BigDecimal valid_frozen) {
		this.valid_frozen = valid_frozen;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFrozen_time() {
		return frozen_time;
	}

	public void setFrozen_time(String frozen_time) {
		this.frozen_time = frozen_time != null && frozen_time.length() > 10 ? frozen_time
				.substring(0, 10) : frozen_time;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time != null && last_time.length() > 10 ? last_time
				.substring(0, 10) : last_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBank_reg_name() {
		return bank_reg_name;
	}

	public void setBank_reg_name(String bank_reg_name) {
		this.bank_reg_name = bank_reg_name;
	}

	public String getBank_card_no() {
		return bank_card_no;
	}

	public void setBank_card_no(String bank_card_no) {
		this.bank_card_no = bank_card_no;
	}

	public String getCard_owner() {
		return card_owner;
	}

	public void setCard_owner(String card_owner) {
		this.card_owner = card_owner;
	}

	public String getBank_reg_pri() {
		return bank_reg_pri;
	}

	public void setBank_reg_pri(String bank_reg_pri) {
		this.bank_reg_pri = bank_reg_pri;
	}

	public String getBank_reg_sub() {
		return bank_reg_sub;
	}

	public void setBank_reg_sub(String bank_reg_sub) {
		this.bank_reg_sub = bank_reg_sub;
	}

	public String getFrozenTimeS() {
		return frozenTimeS;
	}

	public void setFrozenTimeS(String frozenTimeS) {
		this.frozenTimeS = frozenTimeS;
	}

	public String getFrozenTimeE() {
		return frozenTimeE;
	}

	public void setFrozenTimeE(String frozenTimeE) {
		this.frozenTimeE = frozenTimeE;
	}

}