package com.mall.web.mall.domain;

import java.math.BigDecimal;

public abstract class GeneralOrder {
	public abstract Long getOrderId();

	public abstract String getOrderSn();

	public abstract BigDecimal getOrderAmount();
	
	public abstract Integer getStatus();
}
