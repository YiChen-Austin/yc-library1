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
 * 会员银行卡绑定
 */
@Entity
@Table(name = "ml_member_bank_card")
public class MemberBankCard implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;
	// 序号
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name = "user_id")
	private int userId;
	// name
	@Column(name = "bank_card_no")
	private String bankCardNo;// 银行卡卡号
	@Column(name = "bank_reg_code")
	private String bankRegCode;// 开户行编码
	@Column(name = "bank_reg_name")
	private String bankRegName;// 开户行名称
	@Column(name = "bank_reg_pri")
	private String bankRegPri;// 开户省市
	@Column(name = "bank_reg_sub")
	private String bankRegSub;// 开户支行
	@Column(name = "card_owner")
	private String cardOwner;// 户主姓名
	@Column(name = "card_type")
	private int cardType;// 卡片类型:0-未设定卡片类型
	@Column(name = "bind_type")
	private int bindType;// 绑定类型:0-用于提现
	@Column(name = "operate_time")
	private Date operateTime;// 操作时间

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankRegCode() {
		return bankRegCode;
	}

	public void setBankRegCode(String bankRegCode) {
		this.bankRegCode = bankRegCode;
	}

	public String getBankRegName() {
		return bankRegName;
	}

	public void setBankRegName(String bankRegName) {
		this.bankRegName = bankRegName;
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getBindType() {
		return bindType;
	}

	public void setBindType(int bindType) {
		this.bindType = bindType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getBankRegPri() {
		return bankRegPri;
	}

	public void setBankRegPri(String bankRegPri) {
		this.bankRegPri = bankRegPri;
	}

	public String getBankRegSub() {
		return bankRegSub;
	}

	public void setBankRegSub(String bankRegSub) {
		this.bankRegSub = bankRegSub;
	}

}
