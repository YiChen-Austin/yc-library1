package com.mall.web.pay.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 第三方支付接口银行接口对应表列表
 * 
 * @author ventrue
 * @category 第三方支付接口列表
 */
@Entity
@Table(name = "ml_pay_bank_third")
public class PayBank2Third implements Serializable {
	private static final long serialVersionUID = -997688998285826668L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;
	/**********************/
	@ManyToOne
	private PayBanks payBanks;// 银行列表
	@ManyToOne
	private PayThirdPays payThirdPays;// 第三方接口列表
	/**
	 * 是否支持个人借记卡
	 */
	@Column(name = "debit")
	private String debit;//
	/**
	 * 是否支持个人信用卡
	 */
	@Column(name = "credit")
	private String credit;//
	/**
	 * 是否支持企业网银
	 */
	@Column(name = "epNetPay")
	private String epNetPay;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PayBanks getPayBanks() {
		return payBanks;
	}

	public void setPayBanks(PayBanks payBanks) {
		this.payBanks = payBanks;
	}

	public PayThirdPays getPayThirdPays() {
		return payThirdPays;
	}

	public void setPayThirdPays(PayThirdPays payThirdPays) {
		this.payThirdPays = payThirdPays;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getEpNetPay() {
		return epNetPay;
	}

	public void setEpNetPay(String epNetPay) {
		this.epNetPay = epNetPay;
	}

}
