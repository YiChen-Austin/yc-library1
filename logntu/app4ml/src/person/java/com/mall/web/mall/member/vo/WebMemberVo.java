package com.mall.web.mall.member.vo;

import java.util.Date;

import com.mall.common.util.BaseUtil;
import com.mall.common.util.StringUtil;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;

public class WebMemberVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2646476417870740750L;
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private String realName;
	private int gender;
	private String birthday;
	private String phoneTel;
	private String phoneMob;
	private String imQq;
	private String imWeichat;
	private String imSkype;
	private String imYahoo;
	private String imAlipa;
	private Date regTime;
	private Date lastLogin;
	private String lastIp;
	private int logins;
	private int ugrade;
	private String portrait;
	private String activation;
	private String imAlipay;
	private String idCard;
	private String isHooked;
	private String uguid;// 用户guid

	public static WebMemberVo bean2Vo(Member bean) {
		WebMemberVo mobMemberVo = new WebMemberVo();
		mobMemberVo.setActivation(bean.getActivation());
		mobMemberVo.setBirthday(bean.getBirthday());
		mobMemberVo.setEmail(bean.getEmail());
		mobMemberVo.setGender(bean.getGender());
		mobMemberVo.setImAlipay(bean.getImAlipay());
		mobMemberVo.setImWeichat(bean.getImWeichat());
		mobMemberVo.setImQq(bean.getImQq());
		mobMemberVo.setImSkype(bean.getImSkype());
		mobMemberVo.setImYahoo(bean.getImYahoo());
		mobMemberVo.setLastIp(bean.getLastIp());
		mobMemberVo.setLastLogin(bean.getLastLogin());
		mobMemberVo.setLogins(bean.getLogins());
		mobMemberVo.setPassword(bean.getPassword());
		mobMemberVo.setPhoneMob(bean.getPhoneMob());
		mobMemberVo.setPhoneTel(bean.getPhoneTel());
		mobMemberVo.setPortrait(bean.getPortrait());
		mobMemberVo.setRealName(bean.getRealName());
		mobMemberVo.setRegTime(bean.getRegTime());
		mobMemberVo.setUgrade(bean.getUgrade());
		mobMemberVo.setUserId(bean.getUserId());
		mobMemberVo.setIdCard(bean.getIdCard());
		mobMemberVo.setUserName(bean.getUserName());
		mobMemberVo.setUguid(bean.getUguid());
		return mobMemberVo;
	}

	public String getImAlipay() {
		return imAlipay;
	}

	public void setImAlipay(String imAlipay) {
		this.imAlipay = imAlipay;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/** 返回加星号的邮箱地址 */
	public String getEmailMasked() {
		String email = this.getEmail();
		if (BaseUtil.isEmpty(email)) {
			return email;
		}

		// split
		int atIdx = email.indexOf("@");
		if (atIdx <= 0) {
			// invalid email
			return email;
		}
		String emailUser = email.substring(0, atIdx); // somebody@qqgo.cc =>
														// somebody
		String emailHost = email.substring(atIdx); // somebody@qqgo.cc =>
													// @qqgo.cc
		// mask
		int start = 0;
		int count = -1;
		if (emailUser.length() >= 5) {
			// somebody => so****dy
			// users => us*rs
			start = 2;
			count = emailUser.length() - (2 * 2);
		} else if (emailUser.length() >= 3) {
			// usr => u*r
			// user => u**r
			start = 1;
			count = emailUser.length() - (1 * 2);
		}
		String emailUserMasked = StringUtil.maskText(emailUser, start, count);
		// join
		String emailMasked = emailUserMasked + emailHost;

		return emailMasked;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public String getRealNameEX() {
		if (realName != null) {
			StringBuilder name = new StringBuilder();
			for (int i = 0; i < realName.length() - 1; i++) {
				name.append("*");
			}
			return name.toString() + realName.substring(realName.length() - 1);
		}
		return null;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneTel() {
		return phoneTel;
	}

	public void setPhoneTel(String phoneTel) {
		this.phoneTel = phoneTel;
	}

	public String getPhoneMob() {
		return phoneMob;
	}

	public void setPhoneMob(String phoneMob) {
		this.phoneMob = phoneMob;
	}

	/** 返回加星号的手机号码 */
	public String getPhoneMobMasked() {
		return StringUtil.maskText(this.getPhoneMob(), 3, 4);
	}

	public String getImQq() {
		return imQq;
	}

	public void setImQq(String imQq) {
		this.imQq = imQq;
	}

	public String getImSkype() {
		return imSkype;
	}

	public void setImSkype(String imSkype) {
		this.imSkype = imSkype;
	}

	public String getImYahoo() {
		return imYahoo;
	}

	public void setImYahoo(String imYahoo) {
		this.imYahoo = imYahoo;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public int getLogins() {
		return logins;
	}

	public void setLogins(int logins) {
		this.logins = logins;
	}

	public int getUgrade() {
		return ugrade;
	}

	public void setUgrade(int ugrade) {
		this.ugrade = ugrade;
	}

	public String getPortrait() {
		return portrait;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getImWeichat() {
		return imWeichat;
	}

	public void setImWeichat(String imWeichat) {
		this.imWeichat = imWeichat;
	}

	public String getImAlipa() {
		return imAlipa;
	}

	public void setImAlipa(String imAlipa) {
		this.imAlipa = imAlipa;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getIdCardEx() {
		if (idCard != null && idCard.length() == 18) {
			return "**************" + idCard.substring(14, 18);
		} else if (idCard != null && idCard.length() == 15) {
			return "***********" + idCard.substring(11, 15);
		}
		return null;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIsHooked() {
		return isHooked;
	}

	public void setIsHooked(String isHooked) {
		this.isHooked = isHooked;
	}

	public String getUguid() {
		return uguid;
	}

	public void setUguid(String uguid) {
		this.uguid = uguid;
	}

	// 获取推广链接
	public String getMySpread(){
		return WebMemberService.encodeSpread(userId);
	}
}