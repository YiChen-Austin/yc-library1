package com.mall.web.pay.vo;

/**
 * 订单信息
 * 
 * @author ventrue
 * @category 订单信息
 */
public class OrderVo {
	private String orderNo;// 订单号（批次号）
	private String amount;// 金额

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

}
