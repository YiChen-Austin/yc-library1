package com.mall.web.pay.domain;

// Generated 2014-7-18 13:09:35 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum;

/**
 * 会员账号资金变化日志
 */
@Entity
@Table(name = "ml_pay_record")
public class PayRecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2393011450216665187L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "record_id")
	private int recordId; // 记录编号

	@Column(name = "user_id")
	private int userId; // 会员id

	@Column(name = "busi_type")
	private int busiType; // 业务类型

	@Column(name = "busi_desc")
	private String busiDesc; // 业务描述

	@Column(name = "fund_direct")
	private String fundDirect; // 资金方向

	@Column(name = "money")
	private BigDecimal money; // 金额（变化金额，带符号+-）

	@Column(name = "balance")
	private BigDecimal balance; // 结余

	@Column(name = "point")
	private int point; // 消费积分

	@Column(name = "status")
	private int status; // 状态

	@Column(name = "remark")
	private String remark; // 备注

	@Column(name = "create_time")
	private Date createTime; // 创建时间

	public PayRecord() {
	}

	public PayRecord(int userId, int busiType, String busiDesc, String fundDirect, BigDecimal money, BigDecimal balance,
			int point, int status, String remark) {
		this.userId = userId;
		this.busiType = busiType;
		this.busiDesc = busiDesc;
		this.fundDirect = fundDirect;
		this.money = money;
		this.balance = balance;
		this.status = status;
		this.point = point;
		this.remark = remark;
		this.createTime = DateUtil.getGMTDate();
	}

	public PayRecord(int userId, int busiType, String busiDesc, String fundDirect, BigDecimal money, BigDecimal balance,
			int point, String remark) {
		this.userId = userId;
		this.busiType = busiType;
		this.busiDesc = busiDesc;
		this.fundDirect = fundDirect;
		this.money = money;
		this.balance = balance;
		this.status = MallEnum.PayRecordType.Success.getIndex();
		this.point = point;
		this.remark = remark;
		this.createTime = DateUtil.getGMTDate();
	}

	public PayRecord(int userId, int busiType, String busiDesc, String fundDirect, BigDecimal money, BigDecimal balance,
			String remark) {
		this.userId = userId;
		this.busiType = busiType;
		this.busiDesc = busiDesc;
		this.fundDirect = fundDirect;
		this.money = money;
		this.balance = balance;
		this.point = 0;
		this.status = MallEnum.PayRecordType.Success.getIndex();
		this.remark = remark;
		this.createTime = DateUtil.getGMTDate();
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBusiType() {
		return busiType;
	}

	public void setBusiType(int busiType) {
		this.busiType = busiType;
	}

	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFundDirect() {
		return fundDirect;
	}

	public void setFundDirect(String fundDirect) {
		this.fundDirect = fundDirect;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
