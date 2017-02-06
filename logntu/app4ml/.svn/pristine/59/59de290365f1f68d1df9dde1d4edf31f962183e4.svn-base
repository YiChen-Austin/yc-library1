package com.mall.web.mobile.third.util;

import net.sf.json.JSONObject;

public class WechatUserInfo {
	private String openid;
	private String nickname;
	private String sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String privilege;
	private String unionid;

	public String getOpenid() {
		return openid;
	}

	public String getNickname() {
		return nickname;
	}

	public String getSex() {
		return sex;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public WechatUserInfo(JSONObject obj) {
		openid = obj.getString("openid");
		nickname = obj.getString("nickname");
		sex = obj.getString("sex");
		province = obj.getString("province");
		city = obj.getString("city");
		country = obj.getString("country");
		headimgurl = obj.getString("headimgurl");
		privilege = obj.getString("privilege");
		unionid = obj.getString("unionid");
	}

}
