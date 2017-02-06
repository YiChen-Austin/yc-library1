package com.mall.web.mall.third.qq.util;

import com.qq.connect.QQConnect;
import com.qq.connect.QQConnectException;
import com.qq.connect.utils.QQConnectConfig;
import com.qq.connect.utils.http.PostParameter;

public class QQuserInfoEx extends QQConnect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8371971574768206600L;

	// private String url = Configuration.getClientURL()
	// + "?access_token=%s&oauth_consumer_key=%s&openid=%s&format=json";

	public QQuserInfoEx(String accessToken, String openId) {
		super(accessToken, openId);
	}

	private QQuserInfoBeanEx getUserInfo(String openid) throws QQConnectException {
		return new QQuserInfoBeanEx(client.get(
				QQConnectConfig.getValue("getUserInfoURL"),
				new PostParameter[] {
						new PostParameter("openid", openid),
						new PostParameter("oauth_consumer_key", QQConnectConfig
								.getValue("app_ID")),
						new PostParameter("access_token", client.getToken()),
						new PostParameter("format", "json") }).asJSONObject());
	}

	public QQuserInfoBeanEx getUserInfoByAppid(String appid)
			throws QQConnectException {
		return new QQuserInfoBeanEx(client.get(
				QQConnectConfig.getValue("getUserInfoURL"),
				new PostParameter[] {
						new PostParameter("openid", client.getOpenID()),
						new PostParameter("oauth_consumer_key", appid),
						new PostParameter("access_token", client.getToken()),
						new PostParameter("format", "json") }).asJSONObject());
	}

	public QQuserInfoBeanEx getUserInfo() throws QQConnectException {
		return getUserInfo(client.getOpenID());
	}
}
