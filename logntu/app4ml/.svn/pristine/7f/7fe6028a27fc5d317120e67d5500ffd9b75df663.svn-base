package com.mall.web.pay.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 支付银行列表
 * 
 * @author ventrue
 * @category 支付银行列表
 */
@Entity
@Table(name = "ml_pay_banks")
public class PayBanks implements Serializable {
	private static final long serialVersionUID = -997688998285826668L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;
	/**********************/
	@Column(name = "bank_name", unique = true, nullable = false, length = 100)
	private String bankName;// 银行名称
	@Column(name = "bank_code", nullable = false, length = 30)
	private String bankCode;// 银行编号
	@Column(name = "bank_image", nullable = false, length = 100)
	private String bankImg;// 银行图片
	@Column(name = "sequence", length = 4)
	private float sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	}

	public String getBankImg() {
		return bankImg;
	}

	public void setBankImg(String bankImg) {
		this.bankImg = bankImg;
	}

	public float getSequence() {
		return sequence;
	}

	public void setSequence(float sequence) {
		this.sequence = sequence;
	}

}
