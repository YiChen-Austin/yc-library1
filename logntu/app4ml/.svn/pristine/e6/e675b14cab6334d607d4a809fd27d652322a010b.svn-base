package com.mall.web.mall.domain;
import java.util.Date;

/**
  * @Description(描述)	: 用户绑定
  * @author(作者)		: wangliyou
  * @date (开发日期)		: 2015年10月17日 上午10:45:16
 */
public class MemberBound implements java.io.Serializable {
	private static final long serialVersionUID = -1906673843236360944L;
	private Integer id;		//主键
	private Integer userId; //用户id
	private String openId;	//openid第三方平台绑定唯一标识
	private Date boundTime;	//绑定时间
	private int channel;	//来源渠道 0：qq ，1：微信 ，2：支付宝	
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getBoundTime() {
		return boundTime;
	}
	public void setBoundTime(Date boundTime) {
		this.boundTime = boundTime;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
}
