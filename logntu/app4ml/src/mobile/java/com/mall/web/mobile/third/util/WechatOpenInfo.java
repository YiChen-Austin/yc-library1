package com.mall.web.mobile.third.util;

import net.sf.json.JSONObject;

public class WechatOpenInfo {
	private String scope;
	private String unionid;
	private String openid;
	private String expiresIn;
	private String accessToken;

	public String getScope() {
		return scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public WechatOpenInfo(JSONObject obj) {
		scope = obj.getString("scope");
		//unionid = obj.getString("unionid");
		openid = obj.getString("openid");
		expiresIn = obj.getString("expires_in");
		accessToken = obj.getString("access_token");
	}

}
