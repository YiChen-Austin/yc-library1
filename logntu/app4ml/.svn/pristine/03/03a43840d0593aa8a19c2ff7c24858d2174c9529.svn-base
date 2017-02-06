package com.mall.web.mall.third.qq.util;

import java.io.Serializable;

import com.qq.connect.QQConnectException;
import com.qq.connect.QQConnectResponse;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class QQuserInfoBeanEx extends QQConnectResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161954064274438410L;
	private int ret;
	private String msg;
	private String nickname;
	private String figureurl;
	private String figureurl1;
	private String figureurl2;
	private String figureurlQq1;
	private String figureurlQq2;
	private String gender;
	private boolean yellowVip;
	private boolean vip;
	private int yellowVipLevel;
	private int level;
	private boolean yellowYearVip;

	public String getNickname() {
		return nickname;
	}

	public String getGender() {
		return gender;
	}

	public boolean isVip() {
		return vip;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isYellowYearVip() {
		return yellowYearVip;
	}

	public boolean isYellowVip() {
		return yellowVip;
	}

	public int getRet() {
		return ret;
	}

	public String getMsg() {
		return msg;
	}

	public String getFigureurl() {
		return figureurl;
	}

	public String getFigureurl1() {
		return figureurl1;
	}

	public String getFigureurl2() {
		return figureurl2;
	}

	public String getFigureurlQq1() {
		return figureurlQq1;
	}

	public String getFigureurlQq2() {
		return figureurlQq2;
	}

	public int getYellowVipLevel() {
		return yellowVipLevel;
	}

	public QQuserInfoBeanEx(JSONObject json) throws QQConnectException {
		init(json);
	}

	private void init(JSONObject json) throws QQConnectException {
		if (json != null)
			try {
				ret = json.getInt("ret");
				if (0 != ret) {
					msg = json.getString("msg");
				} else {
					msg = "";
					nickname = json.getString("nickname");
					figureurl = json.getString("figureurl");
					figureurl1 = json.getString("figureurl_1");
					figureurl2 = json.getString("figureurl_2");
					figureurlQq1 = json.getString("figureurl_qq_1");
					figureurlQq2 = json.getString("figureurl_qq_2");
					gender = json.getString("gender");
					yellowVip = json.getInt("is_yellow_vip") == 1;
					vip = json.getInt("vip") == 1;
					yellowVipLevel = json.getInt("yellow_vip_level");
					level = json.getInt("level");
					yellowYearVip = json.getInt("is_yellow_year_vip") == 1;
				}
			} catch (JSONException jsone) {
				throw new QQConnectException((new StringBuilder())
						.append(jsone.getMessage()).append(":")
						.append(json.toString()).toString(), jsone);
			}
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (nickname != null ? nickname.hashCode() : 0);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QQuserInfoBeanEx other = (QQuserInfoBeanEx) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	public String toString() {
		return (new StringBuilder()).append("UserInfo [nickname : ")
				.append(nickname).append(" , ").append("figureurl30 : ")
				.append(figureurl).append(" , ").append("figureurl50 : ")
				.append(figureurl1).append(" , ").append("figureurl100 : ")
				.append(figureurl2).append(" , ").append("gender : ")
				.append(gender).append(" , ").append("vip : ").append(vip)
				.append(" , ").append("level : ").append(level).append(" , ")
				.append("isYellowYeaarVip : ").append(yellowYearVip)
				.append("]").toString();
	}

}
