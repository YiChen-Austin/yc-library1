package com.mall.web.mall.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 连续签到
 */
@Entity
@Table(name = "ml_member_sign")
public class MemberSign implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;
	// 序号
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name = "user_id")
	private int userId;
	@Column(name = "signs")
	private int signs;// 有效签到次数
	@Column(name = "continuous")
	private int continuous;// 有效连续次数
	@Column(name = "curr_date")
	private String currDate;
	@Column(name = "next_date")
	private String nextDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSigns() {
		return signs;
	}

	public void setSigns(int signs) {
		this.signs = signs;
	}

	public int getContinuous() {
		return continuous;
	}

	public void setContinuous(int continuous) {
		this.continuous = continuous;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

}
