package com.mall.web.mobile.index.vo;

import java.util.List;

/**
 * @author ventrue
 * 
 *         楼层部分(商品信息)
 */
public class FloorVo {
	/**
	 * 分类id
	 */
	private int cateId;
	/**
	 * 分类名称
	 */
	private String cateName;
	/**
	 * 分类显示颜色
	 */
	private String rgb;
	/**
	 * 产品列表信息
	 */
	private List<HotSaleProVo> listPro;

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getRgb() {
		return rgb;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}

	public List<HotSaleProVo> getListPro() {
		return listPro;
	}

	public void setListPro(List<HotSaleProVo> listPro) {
		this.listPro = listPro;
	}

}
