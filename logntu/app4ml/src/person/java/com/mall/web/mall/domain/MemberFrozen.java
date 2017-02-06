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
 * 冻结金额明细
 */
@Entity
@Table(name = "ml_member_frozen")
public class MemberFrozen implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;
	// 序号
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(name = "frozen_id")
	private int frozenId;
	// 优惠券
	@Column(name = "user_id")
	private int userId;
	@Column(name = "from_type")
	private String fromType;
	@Column(name = "frozen_busi_id")
	private String frozenBusiId;
	@Column(name = "frozen")
	private BigDecimal frozen;
	// 冻结时间
	@Column(name = "frozen_time")
	private Date frozenTime;
	@Column(name = "valid_frozen")
	private BigDecimal validFrozen;
	@Column(name = "last_time")
	private Date lastTime;
	// 状态
	@Column(name = "status")
	private String status;
	@Column(name = "remark")
	private String remark;

	public int getFrozenId() {
		return frozenId;
	}

	public void setFrozenId(int frozenId) {
		this.frozenId = frozenId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getFrozenBusiId() {
		return frozenBusiId;
	}

	public void setFrozenBusiId(String frozenBusiId) {
		this.frozenBusiId = frozenBusiId;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public Date getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(Date frozenTime) {
		this.frozenTime = frozenTime;
	}

	public BigDecimal getValidFrozen() {
		return validFrozen;
	}

	public void setValidFrozen(BigDecimal validFrozen) {
		this.validFrozen = validFrozen;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
