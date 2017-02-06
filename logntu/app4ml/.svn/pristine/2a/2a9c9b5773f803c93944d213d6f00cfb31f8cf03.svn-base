package com.mall.web.mall.distribut.vo;

import java.util.Date;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.DateUtil;

/**
 * @Description(描述) :v商会员分销代理关系表
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年8月6日 下午2:01:23
 */
public class MemberDistRelationVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id; // 主键
	private int userId; // 会员id
	private int superiorId; // 上级会员id
	private int lowerNum; // 下级会员数
	private String channel; // 形成渠道1：微信、2：APP、3：PC
	private String createTime; // 形成时间
	private String phoneMob;// 移动电话号码
	private String userName;// 用户名
	private String gouId;// 狗友帐号
	private String weixin;// 微信帐号
	private String qq;// QQ号码
	private String zhifubao;
	private Integer storeType;// 经营类型 经销：0 批发：1 微商：2

	public String getZhifubao() {
		return zhifubao;
	}

	public void setZhifubao(String zhifubao) {
		this.zhifubao = zhifubao;
	}

	public String getGouId() {
		return gouId;
	}

	public void setGouId(String gouId) {
		this.gouId = gouId;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhoneMob() {
		return phoneMob;
	}

	public void setPhoneMob(String phoneMob) {
		this.phoneMob = phoneMob;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(int superiorId) {
		this.superiorId = superiorId;
	}

	public int getLowerNum() {
		return lowerNum;
	}

	public void setLowerNum(int lowerNum) {
		this.lowerNum = lowerNum;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = DateUtil.dateToString(createTime,
				CommonConstant.DATE_WITHSECOND_FORMAT);
	}

	public Integer getStoreType() {
		return storeType;
	}

	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}

}
