package com.mall.web.mall.third.wechat.config;

public class JsapiTicket {
	private String ticket;
	private long validTime = 0;

	public JsapiTicket() {
		validTime = System.currentTimeMillis();
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public long getValidTime() {
		return validTime;
	}

	public boolean isValid() {
		return System.currentTimeMillis() - validTime >= 7200 ? false : true;
	}
}
