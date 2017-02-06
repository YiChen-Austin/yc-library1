package com.mall.web.pay.vo;

import com.mall.common.util.DESUtil;

/**
 * @category 支付交互vo
 * 
 */
public class PayOrderVo {
	// 订单信息
	private String orderNo;// 订单字符串
	private String amount;// 订单扣费总金额
	private String orderType;// 订单类型;
	private String payInfo = null;// 支付业务数据:orderNo+"\t"+amount
	/******************/
	private String bankId;// 银行ID
	private String bankImg;
	private String bankTCode;// 银行在第三方支付上的编码
	private String cardType;// 卡片类型 :支付类型(快钱借记卡1、快捷支付行用卡2、网银借记卡3、网银信用卡4、企业网银5)
	private String thirdId;// 第三方接口ID

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public String getBankTCode() {
		return bankTCode;
	}

	public void setBankTCode(String bankTCode) {
		this.bankTCode = bankTCode;
	}

	public void gntPayInfo() throws Exception {
		setPayInfo(DESUtil.getInstance().encryptMode(
				(orderNo + "\t" + amount + "\t" + orderType).getBytes()));
	}

	public String getBankImg() {
		return bankImg;
	}

	public void setBankImg(String bankImg) {
		this.bankImg = bankImg;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void parsePayInfo() throws Exception {
		String data = new String(DESUtil.getInstance()
				.decryptMode(getPayInfo()));
		String[] temp = data.split("\t");
		setOrderNo(temp[0]);
		setAmount(temp[1]);
		setOrderType(temp[2]);
	}
}
