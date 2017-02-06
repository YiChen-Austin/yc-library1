package com.mall.web.mall.domain;

import java.io.Serializable;
import java.util.Date;

public class FeedBack  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1906673843236360944L;
	private Integer id;
	private Integer userId;
	private String feedBacktext;
	private String feedBackimg;
	private Date feedBackdate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFeedBacktext() {
		return feedBacktext;
	}
	public void setFeedBacktext(String feedBacktext) {
		this.feedBacktext = feedBacktext;
	}
	public String getFeedBackimg() {
		return feedBackimg;
	}
	public void setFeedBackimg(String feedBackimg) {
		this.feedBackimg = feedBackimg;
	}
	public Date getFeedBackdate() {
		return feedBackdate;
	}
	public void setFeedBackdate(Date feedBackdate) {
		this.feedBackdate = feedBackdate;
	}
	
}
