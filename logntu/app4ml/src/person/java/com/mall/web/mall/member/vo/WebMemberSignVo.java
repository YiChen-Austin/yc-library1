package com.mall.web.mall.member.vo;

import java.io.Serializable;

/**
 * 连续签到
 */
public class WebMemberSignVo implements Serializable {
	private static final long serialVersionUID = -7914159008194586780L;
	private int userId;
	private int signs;// 有效签到次数
	private int continuous;// 有效连续次数
	private String currDate;
	
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

}
