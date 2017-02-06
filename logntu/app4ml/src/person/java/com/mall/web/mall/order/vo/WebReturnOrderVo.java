package com.mall.web.mall.order.vo;

import java.math.BigDecimal;
import java.util.List;

import com.mall.web.mall.domain.Order;

public class WebReturnOrderVo implements java.io.Serializable {

	
	private Long orderId;
	private String orderSn;
	private String type;
	private String extension;
	private int sellerId;
	private String sellerName;
	private int buyerId;
	private String buyerName;
	private String buyerEmail;
	private byte status;
	
	private byte returnStatus;	//新增
	private BigDecimal returnMoney;
	private int recordTime;
	
	public int getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(int recordTime) {
		this.recordTime = recordTime;
	}



	private int addTime;
	private String addDate;
	private Integer paymentId;
	private String paymentName;
	private String paymentCode;
	private String outTradeSn;
	private Integer payTime;
	private String payDate;
	private String payMessage;
	private Integer shipTime;
	private String shipDate;
	private String invoiceNo;
	private int finishedTime;
	private String finishedDate;
	private BigDecimal goodsAmount;
	private BigDecimal discount;
	private BigDecimal orderAmount;
	private boolean evaluationStatus;
	private int evaluationTime;
	private byte anonymous;
	private String postscript;
	private boolean payAlter;
	// private long payId;
	private int refundStatus;
	private Integer refundType;
	private String expressCompany;
	private int moneyLocation;
	private BigDecimal refundableAmount;
	private Integer receiveType;
	private short isAppeal;
	private int pnumber;//众筹参与次数
	// 地址
	private Integer addressId;
	// 运费
	private String goodsIds;// 产品id，必有
	private String specIds;// 产品规格id，可无
	private String quantities;// 产品数量，必有
	private String promoIds;// 产品优惠，可无
	private String couponIds;// 优惠券抵扣，可无
	private String redenveIds;// 红包抵扣，可无
	private String logisticsIds;// 运费,必有

	private String balancePay;// 是否余额支付(1,是余额支付)
	private String payPass;// 支付密码
	
	
	
//	public static WebReturnOrderVo entity2vo(YzmOrder entity) {
//		WebReturnOrderVo vo = new WebReturnOrderVo();
//		vo.setOrderId(entity.getOrderId());
//		vo.setOrderSn(entity.getOrderSn());
//		vo.setType(entity.getType());
//		vo.setExtension(entity.getExtension());
//		vo.setSellerId(entity.getSellerId());
//		vo.setSellerName(entity.getSellerName());
//		vo.setBuyerId(entity.getBuyerId());
//		vo.setBuyerEmail(entity.getBuyerEmail());
//		vo.setBuyerName(entity.getBuyerName());
//		vo.setStatus(entity.getStatus().byteValue());
//		vo.setAddTime(entity.getAddTime());// 八小时时差
//		vo.setPayTime(entity.getPayTime());
//		vo.setPaymentId(entity.getPaymentId());
//		vo.setPaymentName(entity.getPaymentName());
//		vo.setPaymentCode(entity.getPaymentCode());
//		vo.setOutTradeSn(entity.getOutTradeSn());
//		vo.setPayMessage(entity.getPayMessage());
//		vo.setShipTime(entity.getShipTime());
//		vo.setInvoiceNo(entity.getInvoiceNo());
//		vo.setFinishedTime(entity.getFinishedTime());
//		vo.setGoodsAmount(entity.getGoodsAmount());
//		vo.setDiscount(entity.getDiscount());
//		vo.setOrderAmount(entity.getOrderAmount());
//		// vo.setEvaluationStatus(entity.getE);
//		vo.setEvaluationTime(entity.getEvaluationTime());
//		vo.setAnonymous(entity.getAnonymous());
//		vo.setPostscript(entity.getPostscript());
//		// vo.setPayAlter(entity.getP);
//		vo.setRefundStatus(entity.getRefundStatus());
//		vo.setRefundType(entity.getRefundType());
//		vo.setExpressCompany(entity.getExpressCompany());
//		vo.setMoneyLocation(entity.getMoneyLocation());
//		vo.setRefundableAmount(entity.getRefundableAmount());
//		vo.setReceiveType(entity.getReceiveType());
//		vo.setIsAppeal(entity.getIsAppeal());
////		vo.setReturnStatus(entity.getOrderId());
//		// vo.setAddressId(entity.getA);
//		return vo;
//	}
	
	public WebReturnOrderVo(){
		
	}
	
	public WebReturnOrderVo(String orderSn, String type, String extension,
			int sellerId, int buyerId, String buyerEmail, byte status,
			int addTime, String paymentCode, String outTradeSn,
			String payMessage, int finishedTime, BigDecimal goodsAmount,
			BigDecimal discount, BigDecimal orderAmount,
			boolean evaluationStatus, int evaluationTime, byte anonymous,
			String postscript, boolean payAlter, long payId, int refundStatus,
			int moneyLocation, BigDecimal refundableAmount, short isAppeal) {
		this.orderSn = orderSn;
		this.type = type;
		this.extension = extension;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.buyerEmail = buyerEmail;
		this.status = status;
		this.addTime = addTime;
		this.paymentCode = paymentCode;
		this.outTradeSn = outTradeSn;
		this.payMessage = payMessage;
		this.finishedTime = finishedTime;
		this.goodsAmount = goodsAmount;
		this.discount = discount;
		this.orderAmount = orderAmount;
		this.evaluationStatus = evaluationStatus;
		this.evaluationTime = evaluationTime;
		this.anonymous = anonymous;
		this.postscript = postscript;
		this.payAlter = payAlter;
		// this.payId = payId;
		this.refundStatus = refundStatus;
		this.moneyLocation = moneyLocation;
		this.refundableAmount = refundableAmount;
		this.isAppeal = isAppeal;
	}

	public WebReturnOrderVo(String orderSn, String type, String extension,
			int sellerId, String sellerName, int buyerId, String buyerName,
			String buyerEmail, byte status, int addTime, Integer paymentId,
			String paymentName, String paymentCode, String outTradeSn,
			Integer payTime, String payMessage, Integer shipTime,
			String invoiceNo, int finishedTime, BigDecimal goodsAmount,
			BigDecimal discount, BigDecimal orderAmount,
			boolean evaluationStatus, int evaluationTime, byte anonymous,
			String postscript, boolean payAlter, /* long payId, */
			int refundStatus, Integer refundType, String expressCompany,
			int moneyLocation, BigDecimal refundableAmount,
			Integer receiveType, short isAppeal) {
		this.orderSn = orderSn;
		this.type = type;
		this.extension = extension;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.status = status;
		this.addTime = addTime;
		this.paymentId = paymentId;
		this.paymentName = paymentName;
		this.paymentCode = paymentCode;
		this.outTradeSn = outTradeSn;
		this.payTime = payTime;
		this.payMessage = payMessage;
		this.shipTime = shipTime;
		this.invoiceNo = invoiceNo;
		this.finishedTime = finishedTime;
		this.goodsAmount = goodsAmount;
		this.discount = discount;
		this.orderAmount = orderAmount;
		this.evaluationStatus = evaluationStatus;
		this.evaluationTime = evaluationTime;
		this.anonymous = anonymous;
		this.postscript = postscript;
		this.payAlter = payAlter;
		// this.payId = payId;
		this.refundStatus = refundStatus;
		this.refundType = refundType;
		this.expressCompany = expressCompany;
		this.moneyLocation = moneyLocation;
		this.refundableAmount = refundableAmount;
		this.receiveType = receiveType;
		this.isAppeal = isAppeal;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public String getOrderSn() {
		return orderSn;
	}



	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getExtension() {
		return extension;
	}



	public void setExtension(String extension) {
		this.extension = extension;
	}



	public int getSellerId() {
		return sellerId;
	}



	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}



	public String getSellerName() {
		return sellerName;
	}



	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}



	public int getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}



	public String getBuyerName() {
		return buyerName;
	}



	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}



	public String getBuyerEmail() {
		return buyerEmail;
	}



	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}



	public byte getStatus() {
		return status;
	}



	public void setStatus(byte status) {
		this.status = status;
	}



	public byte getReturnStatus() {
		return returnStatus;
	}



	public void setReturnStatus(byte returnStatus) {
		this.returnStatus = returnStatus;
	}



	public BigDecimal getReturnMoney() {
		return returnMoney;
	}



	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}



	public int getAddTime() {
		return addTime;
	}



	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}



	public String getAddDate() {
		return addDate;
	}



	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}



	public Integer getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}



	public String getPaymentName() {
		return paymentName;
	}



	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}



	public String getPaymentCode() {
		return paymentCode;
	}



	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}



	public String getOutTradeSn() {
		return outTradeSn;
	}



	public void setOutTradeSn(String outTradeSn) {
		this.outTradeSn = outTradeSn;
	}



	public Integer getPayTime() {
		return payTime;
	}



	public void setPayTime(Integer payTime) {
		this.payTime = payTime;
	}



	public String getPayDate() {
		return payDate;
	}



	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}



	public String getPayMessage() {
		return payMessage;
	}



	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}



	public Integer getShipTime() {
		return shipTime;
	}



	public void setShipTime(Integer shipTime) {
		this.shipTime = shipTime;
	}



	public String getShipDate() {
		return shipDate;
	}



	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}



	public String getInvoiceNo() {
		return invoiceNo;
	}



	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}



	public int getFinishedTime() {
		return finishedTime;
	}



	public void setFinishedTime(int finishedTime) {
		this.finishedTime = finishedTime;
	}



	public String getFinishedDate() {
		return finishedDate;
	}



	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}



	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}



	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}



	public BigDecimal getDiscount() {
		return discount;
	}



	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}



	public BigDecimal getOrderAmount() {
		return orderAmount;
	}



	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}



	public boolean isEvaluationStatus() {
		return evaluationStatus;
	}



	public void setEvaluationStatus(boolean evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}



	public int getEvaluationTime() {
		return evaluationTime;
	}



	public void setEvaluationTime(int evaluationTime) {
		this.evaluationTime = evaluationTime;
	}



	public byte getAnonymous() {
		return anonymous;
	}



	public void setAnonymous(byte anonymous) {
		this.anonymous = anonymous;
	}



	public String getPostscript() {
		return postscript;
	}



	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}



	public boolean isPayAlter() {
		return payAlter;
	}



	public void setPayAlter(boolean payAlter) {
		this.payAlter = payAlter;
	}



	public int getRefundStatus() {
		return refundStatus;
	}



	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}



	public Integer getRefundType() {
		return refundType;
	}



	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}



	public String getExpressCompany() {
		return expressCompany;
	}



	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}



	public int getMoneyLocation() {
		return moneyLocation;
	}



	public void setMoneyLocation(int moneyLocation) {
		this.moneyLocation = moneyLocation;
	}



	public BigDecimal getRefundableAmount() {
		return refundableAmount;
	}



	public void setRefundableAmount(BigDecimal refundableAmount) {
		this.refundableAmount = refundableAmount;
	}



	public Integer getReceiveType() {
		return receiveType;
	}



	public void setReceiveType(Integer receiveType) {
		this.receiveType = receiveType;
	}



	public short getIsAppeal() {
		return isAppeal;
	}



	public void setIsAppeal(short isAppeal) {
		this.isAppeal = isAppeal;
	}



	public int getPnumber() {
		return pnumber;
	}



	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}



	public Integer getAddressId() {
		return addressId;
	}



	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}



	public String getGoodsIds() {
		return goodsIds;
	}



	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}



	public String getSpecIds() {
		return specIds;
	}



	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}



	public String getQuantities() {
		return quantities;
	}



	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}



	public String getPromoIds() {
		return promoIds;
	}



	public void setPromoIds(String promoIds) {
		this.promoIds = promoIds;
	}



	public String getCouponIds() {
		return couponIds;
	}



	public void setCouponIds(String couponIds) {
		this.couponIds = couponIds;
	}



	public String getRedenveIds() {
		return redenveIds;
	}



	public void setRedenveIds(String redenveIds) {
		this.redenveIds = redenveIds;
	}



	public String getLogisticsIds() {
		return logisticsIds;
	}



	public void setLogisticsIds(String logisticsIds) {
		this.logisticsIds = logisticsIds;
	}


	public String getBalancePay() {
		return balancePay;
	}



	public void setBalancePay(String balancePay) {
		this.balancePay = balancePay;
	}



	public String getPayPass() {
		return payPass;
	}



	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	
	
	
	
	
	
	
	
	
	
}
