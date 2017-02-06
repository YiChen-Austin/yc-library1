package com.mall.web.mall.common.vo;

import java.math.BigDecimal;


/**
 * @Description(描述)	: 商品搜索条件
 * @author(作者)			: wangliyou
 * @date (开发日期)		: 2015年9月24日 下午4:40:39
 */

public class SearchGoodsBean {
	
    private String brandId;			//品牌
    private String seriesId;		//系列
	private String modelId;			//型号
	private String keyword;			//关键字
	private String cateId;			//分类
	private BigDecimal sPayPrice;	// 价格
	private BigDecimal ePayPrice;	// 价格
	private int storeId;			//店铺id
	private String attrValue;		//属性值
	
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public BigDecimal getsPayPrice() {
		return sPayPrice;
	}
	public void setsPayPrice(BigDecimal sPayPrice) {
		this.sPayPrice = sPayPrice;
	}
	public BigDecimal getePayPrice() {
		return ePayPrice;
	}
	public void setePayPrice(BigDecimal ePayPrice) {
		this.ePayPrice = ePayPrice;
	}	
}
