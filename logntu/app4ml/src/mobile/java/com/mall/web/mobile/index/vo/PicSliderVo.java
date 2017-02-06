package com.mall.web.mobile.index.vo;

import com.mall.common.util.BaseUtil;
import com.mall.web.mall.common.tag.MallJstlFunction;
import com.mall.web.mobile.index.vo.MobEnum.Url2TypeEnum;

/**
 * @author ventrue
 * 
 *         滑动（轮播）图片信息
 */
public class PicSliderVo {
	/**
	 * 图片名称
	 */
	private String picName;
	/**
	 * 图片url
	 */
	private String picUrl;
	/**
	 * 界面跳转类型
	 * 
	 * @see Url2TypeEnum
	 */
	private int toType = Url2TypeEnum.ToProList.index;
	/**
	 * wap版跳转连接
	 */
	private String wapUrl;
	/**
	 * android跳转连接或数据
	 */
	private String adUrl;
	/**
	 * IOS跳转连接或数据
	 */
	private String iosUrl;
	/**
	 * 轮播顺序
	 */
	private int picOrder;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setPicUrlEx(String picUrl, Integer method, Integer width,
			Integer height) {
		if (BaseUtil.isNotEmpty(picUrl)) {
			this.picUrl = "http://pic.qqgo.cc/"
					+ MallJstlFunction.PictureServerChange(picUrl, 1, width,
							height);
			;
		}
	}

	public int getPicOrder() {
		return picOrder;
	}

	public void setPicOrder(int picOrder) {
		this.picOrder = picOrder;
	}

	public int getToType() {
		return toType;
	}

	public void setToType(int toType) {
		this.toType = toType;
	}

	public String getWapUrl() {
		return wapUrl;
	}

	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public String getIosUrl() {
		return iosUrl;
	}

	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}
}
