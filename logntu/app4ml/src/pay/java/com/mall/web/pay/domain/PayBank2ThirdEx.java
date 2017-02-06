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
 * 银行所选择支付接口列表
 * 
 * @author ventrue
 * @category 银行所选择支付接口列表
 */
@Entity
@Table(name = "ml_pay_bank_third_ex")
public class PayBank2ThirdEx implements Serializable {
	private static final long serialVersionUID = -997688998285826668L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;
	/**********************/
	@ManyToOne
	private PayBanks payBank;// 银行信息

	@ManyToOne
	private PayThirdPays tPayQuickDebit;// 快捷支付（借记卡）
	@ManyToOne
	private PayThirdPays tPayQuickCredit;// 快捷支付（信用卡）

	@ManyToOne
	private PayThirdPays tPayCommonDebit;// 网银支付（借记卡）
	@ManyToOne
	private PayThirdPays tPayCommonCredit;// 网银支付（信用卡）
	@ManyToOne
	private PayThirdPays tPayEnterpriseNet;// 网银支付（企业网银）

	/**********************/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PayBanks getPayBank() {
		return payBank;
	}

	public void setPayBank(PayBanks payBank) {
		this.payBank = payBank;
	}

	public PayThirdPays gettPayQuickDebit() {
		return tPayQuickDebit;
	}

	public void settPayQuickDebit(PayThirdPays tPayQuickDebit) {
		this.tPayQuickDebit = tPayQuickDebit;
	}

	public PayThirdPays gettPayQuickCredit() {
		return tPayQuickCredit;
	}

	public void settPayQuickCredit(PayThirdPays tPayQuickCredit) {
		this.tPayQuickCredit = tPayQuickCredit;
	}

	public PayThirdPays gettPayCommonDebit() {
		return tPayCommonDebit;
	}

	public void settPayCommonDebit(PayThirdPays tPayCommonDebit) {
		this.tPayCommonDebit = tPayCommonDebit;
	}

	public PayThirdPays gettPayCommonCredit() {
		return tPayCommonCredit;
	}

	public void settPayCommonCredit(PayThirdPays tPayCommonCredit) {
		this.tPayCommonCredit = tPayCommonCredit;
	}

	public PayThirdPays gettPayEnterpriseNet() {
		return tPayEnterpriseNet;
	}

	public void settPayEnterpriseNet(PayThirdPays tPayEnterpriseNet) {
		this.tPayEnterpriseNet = tPayEnterpriseNet;
	}

}
