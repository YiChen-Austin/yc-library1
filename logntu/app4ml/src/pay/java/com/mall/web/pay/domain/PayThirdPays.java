package com.mall.web.pay.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 第三方支付接口列表
 * 
 * @author ventrue
 * @category 第三方支付接口列表
 */
@Entity
@Table(name = "ml_pay_third_pay")
public class PayThirdPays implements Serializable {
	private static final long serialVersionUID = -997688998285826668L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;
	/**********************/
	/**
	 * 第三方名称
	 */
	@Column(name = "third_name", nullable = false, length = 100)
	private String thirdName;// 第三方名称
	/**
	 * 第三方类型
	 */
	@Column(name = "third_Type", nullable = false, length = 30)
	private String thirdType;// 第三方类型
	/**
	 * 第三方图片
	 */
	@Column(name = "third_image", nullable = false, length = 100)
	private String thirdImg;// 第三方图片
	/**
	 * 是否银行
	 */
	@Column(name = "is_bank")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean isBank = false;// 是否银行
	/**
	 * 记录账号等关键信息
	 */
	@Column(name = "third_keys", nullable = false, length = 1000)
	private String thirdKeys;// 第三方图片

	/**
	 * 系统目前是否支持
	 */
	@Column(name = "is_support")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean isSupport = false;
	/**
	 * 排序
	 */
	@Column(name = "sequence", length = 4)
	private Float sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Float getSequence() {
		return sequence;
	}

	public void setSequence(Float sequence) {
		this.sequence = sequence;
	}

}
