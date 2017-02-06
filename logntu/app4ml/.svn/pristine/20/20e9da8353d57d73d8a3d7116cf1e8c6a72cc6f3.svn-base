package com.mall.web.mobile.member.vo;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.tag.MallJstlFunction;
import com.mall.web.mall.domain.Member;

public class MobMemberVo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5355467048525833945L;
	private Integer userId;
	private String token;
	private String userName;
	private String email;
	private String password;
	private String realName;
	private int gender;
	private int birthday;
	private String birthdayEx;
	private String phoneTel;
	private String phoneMob;
	private String imQq;
	private String imMsn;
	private String imSkype;
	private String imYahoo;
	private String imAliww;
	private Integer regTime;
	private Integer lastLogin;
	private String lastIp;
	private int logins;
	private int ugrade;
	private int ugradeValue;
	private String portrait;
	private String portraitEx;
	private int outerId;
	private String activation;
	private String feedConfig;
	private String imWechat;	//微信帐号
	private String imAlipay;	//支付宝帐号
	private String wxQrCode;	//微信推广二维码
	private String myQrCode;	//我的二维码
	
	private  long overduePayTime;
	private  int confirmCount;
	private  boolean isSign;//当天是否签到
	
	
	
	public static MobMemberVo bean2Vo(Member bean) {
		if (bean == null) {
			return null;
		}
		MobMemberVo mobMemberVo = new MobMemberVo();
		mobMemberVo.setActivation(bean.getActivation());
		mobMemberVo.setEmail(bean.getEmail());
		mobMemberVo.setGender(bean.getGender());
		mobMemberVo.setImQq(bean.getImQq());
		mobMemberVo.setImSkype(bean.getImSkype());
		mobMemberVo.setImYahoo(bean.getImYahoo());
		mobMemberVo.setLastIp(bean.getLastIp());
		mobMemberVo.setLogins(bean.getLogins());
		mobMemberVo.setPassword(bean.getPassword());
		mobMemberVo.setPhoneMob(bean.getPhoneMob());
		mobMemberVo.setPhoneTel(bean.getPhoneTel());
		mobMemberVo.setPortrait(bean.getPortrait());
		mobMemberVo.setRealName(bean.getRealName());
		mobMemberVo.setUgrade(bean.getUgrade());
		mobMemberVo.setUserId(bean.getUserId());
		mobMemberVo.setUserName(bean.getUserName());
		mobMemberVo.setImAlipay(bean.getImAlipay());
		return mobMemberVo;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
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

	public String getBirthdayEx() {
		return birthdayEx;
	}

	public void setBirthdayEx(String birthday) {
		this.birthdayEx = birthday;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
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

	public String getImQq() {
		return imQq;
	}

	public void setImQq(String imQq) {
		this.imQq = imQq;
	}

	public String getImMsn() {
		return imMsn;
	}

	public void setImMsn(String imMsn) {
		this.imMsn = imMsn;
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

	public String getImAliww() {
		return imAliww;
	}

	public void setImAliww(String imAliww) {
		this.imAliww = imAliww;
	}

	public Integer getRegTime() {
		return regTime;
	}

	public void setRegTime(Integer regTime) {
		this.regTime = regTime;
	}

	public Integer getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Integer lastLogin) {
		this.lastLogin = lastLogin;
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

	public void setPortrait(String portrait) {
		this.portrait = portrait;
		if (BaseUtil.isNotEmpty(portrait)) {
			this.portraitEx = MallJstlFunction.PictureServerChange(portrait, 0,
					null, null);
		}
	}

	public String getPortraitEx() {
		return portraitEx;
	}

	public void setPortraitEx(String portraitEx) {
		this.portraitEx = portraitEx;
	}

	public int getOuterId() {
		return outerId;
	}

	public void setOuterId(int outerId) {
		this.outerId = outerId;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getFeedConfig() {
		return feedConfig;
	}

	public void setFeedConfig(String feedConfig) {
		this.feedConfig = feedConfig;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getWxQrCode() {
		return wxQrCode;
	}

	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
	}

	public String getMyQrCode() {
		return myQrCode;
	}

	public void setMyQrCode(String myQrCode) {
		this.myQrCode = myQrCode;
	}

	public String getImWechat() {
		return imWechat;
	}

	public void setImWechat(String imWechat) {
		this.imWechat = imWechat;
	}

	public String getImAlipay() {
		return imAlipay;
	}

	public void setImAlipay(String imAlipay) {
		this.imAlipay = imAlipay;
	}

	public long getOverduePayTime() {
		return overduePayTime;
	}

	public void setOverduePayTime(Long overduePayTime) {
		this.overduePayTime = overduePayTime;
	}

	public int getConfirmCount() {
		return confirmCount;
	}
	
	public int getReduceCount() {
		return 6-confirmCount<=0?0:6-confirmCount;
	}
	
	public void setConfirmCount(int confirmCount) {
		this.confirmCount = confirmCount;
	}

	public int getUgradeValue() {
		return ugradeValue;
	}

	public void setUgradeValue(int ugradeValue) {
		this.ugradeValue = ugradeValue;
	}

	public boolean isSign() {
		return isSign;
	}

	public void setSign(boolean isSign) {
		this.isSign = isSign;
	}


}
