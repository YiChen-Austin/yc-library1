package com.mall.web.pay.vo;

/**
 * 
 * 银行支付支持接口信息
 * 
 * @author ventrue
 * @category 银行支付接口信息
 * 
 */
public class BankThirdPayVo {
	private String id;
	private String thirdName;// 第三方名称
	/**
	 * 第三方类型
	 */
	private String thirdType;// 第三方类型
	/**
	 * 第三方图片
	 */
	private String thirdImg;// 第三方图片
	/**
	 * 是否银行
	 */
	private Boolean isBank = false;// 是否银行
	/**
	 * 记录账号等关键信息
	 */
	private String thirdKeys;// 第三方图片

	/**
	 * 系统目前是否支持
	 */
	private Boolean isSupport = false;

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getThirdType() {
		return thirdType;
	}

	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}

	public String getThirdImg() {
		return thirdImg;
	}

	public void setThirdImg(String thirdImg) {
		this.thirdImg = thirdImg;
	}

	public Boolean getIsBank() {
		return isBank;
	}

	public void setIsBank(Boolean isBank) {
		this.isBank = isBank;
	}

	public String getThirdKeys() {
		return thirdKeys;
	}

	public void setThirdKeys(String thirdKeys) {
		this.thirdKeys = thirdKeys;
	}

	public Boolean getIsSupport() {
		return isSupport;
	}

	public void setIsSupport(Boolean isSupport) {
		this.isSupport = isSupport;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
