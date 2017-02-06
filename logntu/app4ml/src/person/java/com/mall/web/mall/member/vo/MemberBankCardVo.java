package com.mall.web.mall.member.vo;

import java.io.Serializable;

import com.mall.web.mall.domain.MemberBankCard;

/**
 * 会员银行卡绑定
 */

public class MemberBankCardVo implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;

	private int userId;
	private String bankCardNo;// 银行卡卡号
	private String bankRegCode;// 开户行编码
	private String bankRegName;// 开户行名称
	private String bankRegPri;// 开户省市
	private String bankRegSub;// 开户支行
	private String cardOwner;// 户主姓名
	private String cardType = "0";// 卡片类型:0-未设定卡片类型,1-银行卡,2-支付宝
	private String payPw;// 支付密码

	public static MemberBankCardVo entry2bean(MemberBankCard entity) {
		MemberBankCardVo bean = new MemberBankCardVo();
		bean.setUserId(entity.getUserId());
		bean.setBankCardNo(entity.getBankCardNo());
		bean.setBankRegCode(entity.getBankRegCode());
		bean.setBankRegName(entity.getBankRegName());
		bean.setBankRegPri(entity.getBankRegPri());
		bean.setBankRegSub(entity.getBankRegSub());
		bean.setCardOwner(entity.getCardOwner());
		bean.setCardType(Integer.toString(entity.getCardType()));
		return bean;
	}

	public MemberBankCard bean2entry(MemberBankCard entity) {
		entity.setUserId(this.getUserId());
		entity.setBankCardNo(this.getBankCardNo());
		entity.setBankRegCode(this.getBankRegCode());
		entity.setBankRegName(this.getBankRegName());
		entity.setBankRegPri(this.getBankRegPri());
		entity.setBankRegSub(this.getBankRegSub());
		entity.setCardOwner(this.getCardOwner());
		entity.setCardType(Integer.parseInt(this.getCardType()));
		return entity;
	}

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

	public String getPayPw() {
		return payPw;
	}

	public void setPayPw(String payPw) {
		this.payPw = payPw;
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
