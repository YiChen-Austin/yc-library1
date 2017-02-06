package com.mall.web.pay.vo;

import com.mall.common.util.BaseUtil;



/**
 * 
 * 银行支付支持接口信息
 * 
 * @author ventrue
 * @category 银行(支付接口)信息
 * 
 */
public class BankPayVo {
	private String id;
	private String bankName;// 银行名称
	private String bankCode;// 银行编号
	private String bankCodeL;// 银行编号(小写)
	private String bankImg;// 银行图片
	private String bankThirdPay;// 银行第三方支付信息（记录为json方式）
	private String isBank = "false";// 支付接口是否为银行

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
		this.bankCodeL = BaseUtil.isEmpty(bankCode) ? "" : bankCode
				.toLowerCase();
	}

	public String getBankCodeL() {
		return bankCodeL;
	}

	public void setBankCodeL(String bankCodeL) {
		this.bankCodeL = bankCodeL;
	}

	public String getBankImg() {
		return bankImg;
	}

	public void setBankImg(String bankImg) {
		this.bankImg = bankImg;
	}

	public String getBankThirdPay() {
		return bankThirdPay;
	}

	public void setBankThirdPay(String bankThirdPay) {
		this.bankThirdPay = bankThirdPay;
	}

	public String getIsBank() {
		return isBank;
	}

	public void setIsBank(String isBank) {
		this.isBank = isBank;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
