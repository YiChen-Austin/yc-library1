package com.mall.web.mobile.index.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mall.web.mobile.index.vo.MobEnum.DataStatusEnum;
import com.mall.web.mobile.index.vo.MobEnum.Url2TypeEnum;

/**
 * @author ventrue
 * 
 *         首页数据
 */
public class IndexVo {

	/**
	 * 返回状态
	 */
	private int serviceCode;
	/**
	 * 轮播图信息
	 */
	private List<PicSliderVo> listSlider;
	/**
	 * 特卖信息
	 */
	private List<HotSaleProVo> listHot;
	/**
	 * 楼层信息
	 */
	private List<FloorVo> listFloor;

	public List<PicSliderVo> getListSlider() {
		return listSlider;
	}

	public void setListSlider(List<PicSliderVo> listSlider) {
		this.listSlider = listSlider;
	}

	public List<HotSaleProVo> getListHot() {
		return listHot;
	}

	public void setListHot(List<HotSaleProVo> listHot) {
		this.listHot = listHot;
	}

	public List<FloorVo> getListFloor() {
		return listFloor;
	}

	public void setListFloor(List<FloorVo> listFloor) {
		this.listFloor = listFloor;
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	/**
	 * 够着首页信息
	 */
	public static IndexVo d2Index() {
		IndexVo indexVo = new IndexVo();
		indexVo.setServiceCode(DataStatusEnum.SUCCESS.index);// 成功

		// 1、构造轮播图信息
		List<PicSliderVo> listSlider = new ArrayList<PicSliderVo>();
		indexVo.setListSlider(listSlider);

		PicSliderVo psVo1 = new PicSliderVo();
		psVo1.setPicName("轮播图1");
		psVo1.setPicUrlEx("2015/01/01/banner_slider_1.jpg", 1, 720, 324);
		psVo1.setToType(Url2TypeEnum.ToProList.index);
		psVo1.setPicOrder(0);
		psVo1.setWapUrl("/mobile/list?cateId=1");
		psVo1.setAdUrl("cateId=1512");
		psVo1.setIosUrl("cateId=1512");
		listSlider.add(psVo1);

		PicSliderVo psVo2 = new PicSliderVo();
		psVo2.setPicName("轮播图2");
		psVo2.setPicUrlEx("2015/01/01/banner_slider_2.jpg", 1, 720, 324);
		psVo2.setToType(Url2TypeEnum.ToProduct.index);
		psVo2.setPicOrder(1);
		psVo1.setWapUrl("/mobile/goods/pdetail?goodsId=10883");
		psVo2.setAdUrl("goodsId=10883");
		psVo2.setIosUrl("goodsId=10883");
		listSlider.add(psVo2);

		PicSliderVo psVo3 = new PicSliderVo();
		psVo3.setPicName("轮播图3");
		psVo3.setPicUrlEx("2015/01/01/banner_slider_3.jpg", 1, 720, 324);
		psVo3.setToType(Url2TypeEnum.ToActivity.index);
		psVo3.setPicOrder(3);
		psVo3.setWapUrl("/mobile/list?cateId=3");
		psVo1.setAdUrl("cateId=1512");
		psVo1.setIosUrl("cateId=1512");
		listSlider.add(psVo3);

		PicSliderVo psVo4 = new PicSliderVo();
		psVo4.setPicName("轮播图4");
		psVo4.setPicUrlEx("2015/01/01/banner_slider_4.jpg", 1, 720, 324);
		psVo4.setToType(Url2TypeEnum.ToWap.index);
		psVo4.setPicOrder(4);
		psVo4.setWapUrl("/mobile/goods/pdetail?goodsId=10884");
		psVo2.setAdUrl("goodsId=10884");
		psVo2.setIosUrl("goodsId=10884");
		listSlider.add(psVo4);

		PicSliderVo psVo5 = new PicSliderVo();
		psVo5.setPicName("轮播图5");
		psVo5.setPicUrlEx("2015/01/01/banner_slider_5.jpg", 1, 720, 324);
		psVo5.setToType(Url2TypeEnum.ToProduct.index);
		psVo5.setPicOrder(4);
		psVo5.setWapUrl("/mobile/goods/pdetail?goodsId=10885");
		psVo2.setAdUrl("goodsId=10885");
		psVo2.setIosUrl("goodsId=10885");
		listSlider.add(psVo5);

		PicSliderVo psVo6 = new PicSliderVo();
		psVo6.setPicName("轮播图6");
		psVo6.setPicUrlEx("2015/01/01/banner_slider_6.jpg", 1, 720, 324);
		psVo6.setToType(Url2TypeEnum.ToProduct.index);
		psVo6.setPicOrder(4);
		psVo6.setWapUrl("/mobile/goods/pdetail?goodsId=10886");
		psVo2.setAdUrl("goodsId=10886");
		psVo2.setIosUrl("goodsId=10886");
		listSlider.add(psVo6);
		/********************************************/
		// 2、特卖
		List<HotSaleProVo> listHot = new ArrayList<HotSaleProVo>();
		indexVo.setListHot(listHot);

		HotSaleProVo hsp1 = new HotSaleProVo();
		hsp1.setPicName("红包");
		hsp1.setPicUrlEx("2015/01/01/hot_sale_1.png", 1, 225, 320);
		hsp1.setToType(Url2TypeEnum.ToActivity.index);
		hsp1.setPicOrder(0);
		hsp1.setWapUrl("/mobile/list?cateId=1512");
		hsp1.setAdUrl("cateId=1512");
		hsp1.setAdUrl("cateId=1512");
		hsp1.setPrice(BigDecimal.valueOf(300));
		hsp1.setPayPrice(BigDecimal.valueOf(100));
		listHot.add(hsp1);

		HotSaleProVo hsp2 = new HotSaleProVo();
		hsp2.setPicName("活动");
		hsp2.setPicUrlEx("2015/01/01/hot_sale_2.png", 1, 225, 320);
		hsp2.setToType(Url2TypeEnum.ToActivity.index);
		hsp2.setPicOrder(1);
		hsp1.setWapUrl("/mobile/list?cateId=1512");
		hsp1.setAdUrl("cateId=1512");
		hsp1.setAdUrl("cateId=1512");
		hsp2.setPrice(BigDecimal.valueOf(400));
		hsp2.setPayPrice(BigDecimal.valueOf(200));
		listHot.add(hsp2);

		HotSaleProVo hsp3 = new HotSaleProVo();
		hsp3.setPicName("特卖3");
		hsp3.setPicUrlEx("2015/01/01/hot_sale_3.png", 1, 225, 320);
		hsp3.setToType(Url2TypeEnum.ToProduct.index);
		hsp3.setPicOrder(2);
		psVo6.setWapUrl("/mobile/goods/pdetail?goodsId=10889");
		psVo2.setAdUrl("goodsId=10889");
		psVo2.setIosUrl("goodsId=10889");
		hsp3.setPrice(BigDecimal.valueOf(0));
		hsp3.setPayPrice(BigDecimal.valueOf(100));
		listHot.add(hsp3);
		/********************************************/
		// 3、楼层
		/****************/
		List<FloorVo> listFloor = new ArrayList<FloorVo>();
		indexVo.setListFloor(listFloor);
		// 3.1手机
		FloorVo mobile = new FloorVo();
		mobile.setCateId(1);
		mobile.setCateName("手机");
		mobile.setRgb("#f5534a");
		List<HotSaleProVo> mListPro = new ArrayList<HotSaleProVo>();
		mobile.setListPro(mListPro);
		listFloor.add(mobile);

		HotSaleProVo mPsVo1 = new HotSaleProVo();
		mPsVo1.setPicName("vivoX5Max");
		mPsVo1.setPicTag("X5Max+清爽放价");
		mPsVo1.setPicUrlEx("2015/09/22/1442907938701.png", 1, 180, 180);
		mPsVo1.setToType(Url2TypeEnum.ToProduct.index);
		mPsVo1.setPicOrder(0);
		mPsVo1.setWapUrl("/mobile/goods/pdetail?goodsId=10882");
		mPsVo1.setAdUrl("goodsId=10882");
		mPsVo1.setIosUrl("goodsId=10882");
		mPsVo1.setPrice(BigDecimal.valueOf(3251));
		mPsVo1.setPayPrice(BigDecimal.valueOf(2598));
		mListPro.add(mPsVo1);

		HotSaleProVo mPsVo2 = new HotSaleProVo();
		mPsVo2.setPicName("华为 P8移动4G");
		mPsVo2.setPicTag("现货发售");
		mPsVo2.setPicUrlEx("2015/09/23/1442975026862.png", 1,180, 180);
		mPsVo2.setToType(Url2TypeEnum.ToProduct.index);
		mPsVo2.setPicOrder(1);
		mPsVo2.setWapUrl("/mobile/goods/pdetail?goodsId=10882");
		mPsVo2.setAdUrl("goodsId=10882");
		mPsVo2.setIosUrl("goodsId=10882");
		mPsVo2.setPrice(BigDecimal.valueOf(3251));
		mPsVo2.setPayPrice(BigDecimal.valueOf(2598));
		mListPro.add(mPsVo2);

		HotSaleProVo mPsVo3 = new HotSaleProVo();
		mPsVo3.setPicName("华为P8 移动4G青春版");
		mPsVo3.setPicTag("华为旗舰产品");
		mPsVo3.setPicUrlEx("2015/09/23/1442975472966.png", 1,180, 180);
		mPsVo3.setToType(Url2TypeEnum.ToProduct.index);
		mPsVo3.setPicOrder(3);
		mPsVo3.setWapUrl("/mobile/goods/pdetail?goodsId=10887");
		mPsVo3.setAdUrl("goodsId=10887");
		mPsVo3.setIosUrl("goodsId=10887");
		mPsVo3.setPrice(BigDecimal.valueOf(2999));
		mPsVo3.setPayPrice(BigDecimal.valueOf(2999));
		mListPro.add(mPsVo3);

		HotSaleProVo mPsVo4 = new HotSaleProVo();
		mPsVo4.setPicName("金立F103");
		mPsVo4.setPicTag("双卡双待");
		mPsVo4.setPicUrlEx("2015/09/23/1442976852619.png", 1,180, 180);
		mPsVo4.setToType(Url2TypeEnum.ToProduct.index);
		mPsVo4.setPicOrder(4);
		mPsVo4.setWapUrl("/mobile/goods/pdetail?goodsId=10888");
		mPsVo4.setAdUrl("goodsId=10888");
		mPsVo4.setIosUrl("goodsId=10888");
		mPsVo4.setPrice(BigDecimal.valueOf(999));
		mPsVo4.setPayPrice(BigDecimal.valueOf(799));
		mListPro.add(mPsVo4);

		HotSaleProVo mPsVo5 = new HotSaleProVo();
		mPsVo5.setPicName("金立ELIFE");
		mPsVo5.setPicTag("纤薄之美");
		mPsVo5.setPicUrlEx("2015/09/23/1442978685597.png", 1,180, 180);
		mPsVo5.setToType(Url2TypeEnum.ToProduct.index);
		mPsVo5.setPicOrder(5);
		mPsVo5.setWapUrl("/mobile/goods/pdetail?goodsId=10889");
		mPsVo5.setAdUrl("goodsId=10889");
		mPsVo5.setIosUrl("goodsId=10889");
		mPsVo5.setPrice(BigDecimal.valueOf(305));
		mPsVo5.setPayPrice(BigDecimal.valueOf(105));
		mListPro.add(mPsVo5);

		HotSaleProVo mPsVo6 = new HotSaleProVo();
		mPsVo6.setPicName("OPPO A11");
		mPsVo6.setPicTag("高清音质手机");
		mPsVo6.setPicUrlEx("2015/09/23/1442980170554.png", 1,180, 180);
		mPsVo6.setToType(Url2TypeEnum.ToProduct.index);
		mPsVo6.setPicOrder(6);
		mPsVo6.setWapUrl("/mobile/goods/pdetail?goodsId=10890");
		mPsVo6.setAdUrl("goodsId=10890");
		mPsVo6.setIosUrl("goodsId=10890");
		mPsVo6.setPrice(BigDecimal.valueOf(999));
		mPsVo6.setPayPrice(BigDecimal.valueOf(799));
		mListPro.add(mPsVo6);

		// 3.2配件
		FloorVo peijian = new FloorVo();
		peijian.setCateId(1);
		peijian.setCateName("配件");
		peijian.setRgb("#f5534a");
		List<HotSaleProVo> pListPro = new ArrayList<HotSaleProVo>();
		peijian.setListPro(pListPro);
		listFloor.add(peijian);

		HotSaleProVo pPsVo1 = new HotSaleProVo();
		pPsVo1.setPicName("苹果原装充电器");
		pPsVo1.setPicTag("苹果认证");
		pPsVo1.setPicUrlEx("2015/10/02/1443777667819.png", 1,180, 180);
		pPsVo1.setToType(Url2TypeEnum.ToProduct.index);
		pPsVo1.setPicOrder(0);
		pPsVo1.setWapUrl("/mobile/list?goodsId=11007");
		pPsVo1.setAdUrl("goodsId=11007");
		pPsVo1.setIosUrl("goodsId=11007");
		pPsVo1.setPrice(BigDecimal.valueOf(88));
		pPsVo1.setPayPrice(BigDecimal.valueOf(88));
		pListPro.add(pPsVo1);

		HotSaleProVo pPsVo2 = new HotSaleProVo();
		pPsVo2.setPicName("喬工iScreen2");
		pPsVo2.setPicTag("60天无条件换新");
		pPsVo2.setPicUrlEx("2015/10/02/1443777180141.png", 1,180, 180);
		pPsVo2.setToType(Url2TypeEnum.ToProduct.index);
		pPsVo2.setPicOrder(1);
		pPsVo1.setWapUrl("/mobile/list?goodsId=11006");
		pPsVo2.setAdUrl("goodsId=11006");
		pPsVo2.setIosUrl("goodsId=11006");
		pPsVo2.setPrice(BigDecimal.valueOf(99));
		pPsVo2.setPayPrice(BigDecimal.valueOf(99));
		pListPro.add(pPsVo2);

		HotSaleProVo pPsVo3 = new HotSaleProVo();
		pPsVo3.setPicName("iPhone6来电闪");
		pPsVo3.setPicTag("炫彩来电");
		pPsVo3.setPicUrlEx("2015/10/02/1443776291113.png", 1,180, 180);
		pPsVo3.setToType(Url2TypeEnum.ToProduct.index);
		pPsVo3.setPicOrder(3);
		pPsVo3.setWapUrl("/mobile/goods/pdetail?goodsId=11004");
		pPsVo3.setAdUrl("goodsId=11004");
		pPsVo3.setIosUrl("goodsId=11004");
		pPsVo3.setPrice(BigDecimal.valueOf(45));
		pPsVo3.setPayPrice(BigDecimal.valueOf(45));
		pListPro.add(pPsVo3);

		HotSaleProVo pPsVo4 = new HotSaleProVo();
		pPsVo4.setPicName("苹果6保护壳");
		pPsVo4.setPicTag("百日盛典");
		pPsVo4.setPicUrlEx("2015/10/02/1443775737507.png", 1,180, 180);
		pPsVo4.setToType(Url2TypeEnum.ToProduct.index);
		pPsVo4.setPicOrder(4);
		pPsVo4.setWapUrl("/mobile/goods/pdetail?goodsId=11003");
		pPsVo4.setAdUrl("goodsId=11003");
		pPsVo4.setIosUrl("goodsId=11003");
		pPsVo4.setPrice(BigDecimal.valueOf(304));
		pPsVo4.setPayPrice(BigDecimal.valueOf(104));
		pListPro.add(pPsVo4);

		HotSaleProVo pPsVo5 = new HotSaleProVo();
		pPsVo5.setPicName("三星NOTE2");
		pPsVo5.setPicTag("原装正品");
		pPsVo5.setPicUrlEx("2015/10/02/1443773633065.png", 1,180, 180);
		pPsVo5.setToType(Url2TypeEnum.ToProduct.index);
		pPsVo5.setPicOrder(5);
		pPsVo5.setWapUrl("/mobile/goods/pdetail?goodsId=11001");
		pPsVo5.setAdUrl("goodsId=11001");
		pPsVo5.setIosUrl("goodsId=11001");
		pPsVo5.setPrice(BigDecimal.valueOf(9.9));
		pPsVo5.setPayPrice(BigDecimal.valueOf(9.9));
		pListPro.add(pPsVo5);

		HotSaleProVo pPsVo6 = new HotSaleProVo();
		pPsVo6.setPicName("三星 note2");
		pPsVo6.setPicTag("三星 note2");
		pPsVo6.setPicUrlEx("2015/10/02/1443773140806.png", 1,180, 180);
		pPsVo6.setToType(Url2TypeEnum.ToProduct.index);
		pPsVo6.setPicOrder(6);
		pPsVo6.setWapUrl("/mobile/goods/pdetail?goodsId=11000");
		pPsVo6.setAdUrl("goodsId=11000");
		pPsVo6.setIosUrl("goodsId=11000");
		pPsVo6.setPrice(BigDecimal.valueOf(0));
		pPsVo6.setPayPrice(BigDecimal.valueOf(100));
		pListPro.add(pPsVo6);

		// 3.4合约机
		FloorVo heyuji = new FloorVo();
		heyuji.setCateId(1);
		heyuji.setCateName("合约机");
		heyuji.setRgb("#f5534a");
		List<HotSaleProVo> hyListPro = new ArrayList<HotSaleProVo>();
		heyuji.setListPro(hyListPro);
		listFloor.add(heyuji);

		HotSaleProVo hyPsVo1 = new HotSaleProVo();
		hyPsVo1.setPicName("华为GX1");
		hyPsVo1.setPicTag("双卡双待");
		hyPsVo1.setPicUrlEx("2015/10/01/1443683400078.png", 1,180, 180);
		hyPsVo1.setToType(Url2TypeEnum.ToProduct.index);
		hyPsVo1.setPicOrder(0);
		hyPsVo1.setWapUrl("/mobile/list?goodsId=10970");
		hyPsVo1.setAdUrl("goodsId=10970");
		hyPsVo1.setIosUrl("goodsId=10970");
		hyPsVo1.setPrice(BigDecimal.valueOf(1499));
		hyPsVo1.setPayPrice(BigDecimal.valueOf(1399));
		hyListPro.add(hyPsVo1);

		HotSaleProVo hyPsVo2 = new HotSaleProVo();
		hyPsVo2.setPicName("vivo32GB版");
		hyPsVo2.setPicTag("清爽放“价”");
		hyPsVo2.setPicUrlEx("2015/09/22/1442907938701.png", 1, 180, 180);
		hyPsVo2.setToType(Url2TypeEnum.ToProduct.index);
		hyPsVo2.setPicOrder(1);
		hyPsVo1.setWapUrl("/mobile/list?goodsId=10882");
		hyPsVo2.setAdUrl("goodsId=10882");
		hyPsVo2.setIosUrl("goodsId=10882");
		hyPsVo2.setPrice(BigDecimal.valueOf(3251));
		hyPsVo2.setPayPrice(BigDecimal.valueOf(2598));
		hyListPro.add(hyPsVo2);

		HotSaleProVo hyPsVo3 = new HotSaleProVo();
		hyPsVo3.setPicName("P84G合约机");
		hyPsVo3.setPicTag("卓然一体超");
		hyPsVo3.setPicUrlEx("2015/10/01/1443691654366.png", 1,180, 180);
		hyPsVo3.setToType(Url2TypeEnum.ToProduct.index);
		hyPsVo3.setPicOrder(3);
		hyPsVo3.setWapUrl("/mobile/goods/pdetail?goodsId=10981");
		hyPsVo3.setAdUrl("goodsId=10981");
		hyPsVo3.setIosUrl("goodsId=10981");
		hyPsVo3.setPrice(BigDecimal.valueOf(303));
		hyPsVo3.setPayPrice(BigDecimal.valueOf(103));
		hyListPro.add(hyPsVo3);

		HotSaleProVo hyPsVo4 = new HotSaleProVo();
		hyPsVo4.setPicName("合约机4");
		hyPsVo4.setPicTag("合约机买点4");
		hyPsVo4.setPicUrlEx("2015/10/01/1443691370762.png", 1,180, 180);
		hyPsVo4.setToType(Url2TypeEnum.ToProduct.index);
		hyPsVo4.setPicOrder(4);
		hyPsVo4.setWapUrl("/mobile/goods/pdetail?goodsId=4");
		hyPsVo4.setAdUrl("goodsId=4");
		hyPsVo4.setIosUrl("goodsId=4");
		hyPsVo4.setPrice(BigDecimal.valueOf(304));
		hyPsVo4.setPayPrice(BigDecimal.valueOf(104));
		hyListPro.add(hyPsVo4);

		HotSaleProVo hyPsVo5 = new HotSaleProVo();
		hyPsVo5.setPicName("华为Mate7");
		hyPsVo5.setPicTag("高清大屏");
		hyPsVo5.setPicUrlEx("2015/10/01/1443683400078.png", 1,180, 180);
		hyPsVo5.setToType(Url2TypeEnum.ToProduct.index);
		hyPsVo5.setPicOrder(5);
		hyPsVo5.setWapUrl("/mobile/goods/pdetail?goodsId=10980");
		hyPsVo5.setAdUrl("goodsId=10980");
		hyPsVo5.setIosUrl("goodsId=10980");
		hyPsVo5.setPrice(BigDecimal.valueOf(2899));
		hyPsVo5.setPayPrice(BigDecimal.valueOf(2899));
		hyListPro.add(hyPsVo5);

		HotSaleProVo hyPsVo6 = new HotSaleProVo();
		hyPsVo6.setPicName("iPhone6S");
		hyPsVo6.setPicTag("苹果6合约金");
		hyPsVo6.setPicUrlEx("2015/10/01/1443682758709.png", 1,180, 180);
		hyPsVo6.setToType(Url2TypeEnum.ToProduct.index);
		hyPsVo6.setPicOrder(6);
		hyPsVo6.setWapUrl("/mobile/goods/pdetail?goodsId=10969");
		hyPsVo6.setAdUrl("goodsId=10969");
		hyPsVo6.setIosUrl("goodsId=10969");
		hyPsVo6.setPrice(BigDecimal.valueOf(5288));
		hyPsVo6.setPayPrice(BigDecimal.valueOf(5288));
		hyListPro.add(hyPsVo6);

		// 3.4套餐
		FloorVo taocan = new FloorVo();
		taocan.setCateId(1);
		taocan.setCateName("套餐");
		taocan.setRgb("#f5534a");
		List<HotSaleProVo> tcListPro = new ArrayList<HotSaleProVo>();
		taocan.setListPro(tcListPro);
		listFloor.add(taocan);

		HotSaleProVo tcPsVo1 = new HotSaleProVo();
		tcPsVo1.setPicName("4G沃派36套餐");
		tcPsVo1.setPicTag("4G校园套餐");
		tcPsVo1.setPicUrlEx("2015/10/01/1443689162093.png", 1,180, 180);
		tcPsVo1.setToType(Url2TypeEnum.ToProduct.index);
		tcPsVo1.setPicOrder(0);
		tcPsVo1.setWapUrl("/mobile/list?goodsId=10977");
		tcPsVo1.setAdUrl("goodsId=10977");
		tcPsVo1.setIosUrl("goodsId=10977");
		tcPsVo1.setPrice(BigDecimal.valueOf(36));
		tcPsVo1.setPayPrice(BigDecimal.valueOf(36));
		tcListPro.add(tcPsVo1);

		HotSaleProVo tcPsVo2 = new HotSaleProVo();
		tcPsVo2.setPicName("套餐2");
		tcPsVo2.setPicTag("套餐买点2");
		tcPsVo2.setPicUrlEx("2015/10/01/1443689054233.png", 1,180, 180);
		tcPsVo2.setToType(Url2TypeEnum.ToProduct.index);
		tcPsVo2.setPicOrder(1);
		tcPsVo2.setAdUrl("goodsId=2");
		tcPsVo2.setIosUrl("goodsId=2");
		tcPsVo2.setPrice(BigDecimal.valueOf(302));
		tcPsVo2.setPayPrice(BigDecimal.valueOf(102));
		tcListPro.add(tcPsVo2);

		HotSaleProVo tcPsVo3 = new HotSaleProVo();
		tcPsVo3.setPicName("4G沃派26套餐 ");
		tcPsVo3.setPicTag("【校园专享】");
		tcPsVo3.setPicUrlEx("2015/10/01/1443685638897.jpg", 1,180, 180);
		tcPsVo3.setToType(Url2TypeEnum.ToProduct.index);
		tcPsVo3.setPicOrder(3);
		tcPsVo3.setWapUrl("/mobile/goods/pdetail?goodsId=10976");
		tcPsVo3.setAdUrl("goodsId=10976");
		tcPsVo3.setIosUrl("goodsId=10976");
		tcPsVo3.setPrice(BigDecimal.valueOf(26));
		tcPsVo3.setPayPrice(BigDecimal.valueOf(26));
		tcListPro.add(tcPsVo3);

		HotSaleProVo tcPsVo4 = new HotSaleProVo();
		tcPsVo4.setPicName("4G飞享套餐");
		tcPsVo4.setPicTag("预付费用");
		tcPsVo4.setPicUrlEx("2015/10/01/1443685576676.jpg", 1,180, 180);
		tcPsVo4.setToType(Url2TypeEnum.ToProduct.index);
		tcPsVo4.setPicOrder(4);
		tcPsVo4.setWapUrl("/mobile/goods/pdetail?goodsId=10972");
		tcPsVo4.setAdUrl("goodsId=10972");
		tcPsVo4.setIosUrl("goodsId=10972");
		tcPsVo4.setPrice(BigDecimal.valueOf(58));
		tcPsVo4.setPayPrice(BigDecimal.valueOf(58));
		tcListPro.add(tcPsVo4);

		HotSaleProVo tcPsVo5 = new HotSaleProVo();
		tcPsVo5.setPicName("4G享套餐");
		tcPsVo5.setPicTag("4G享套餐");
		tcPsVo5.setPicUrlEx("2015/10/01/1443685576676.jpg", 1,180, 180);
		tcPsVo5.setToType(Url2TypeEnum.ToProduct.index);
		tcPsVo5.setPicOrder(5);
		tcPsVo5.setWapUrl("/mobile/goods/pdetail?goodsId=10972");
		tcPsVo5.setAdUrl("goodsId=10972");
		tcPsVo5.setIosUrl("goodsId=10972");
		tcPsVo5.setPrice(BigDecimal.valueOf(58));
		tcPsVo5.setPayPrice(BigDecimal.valueOf(58));
		tcListPro.add(tcPsVo5);

		HotSaleProVo tcPsVo6 = new HotSaleProVo();
		tcPsVo6.setPicName("4G商旅套餐 ");
		tcPsVo6.setPicTag("全球通");
		tcPsVo6.setPicUrlEx("2015/10/01/1443685472567.jpg", 1,180, 180);
		tcPsVo6.setToType(Url2TypeEnum.ToProduct.index);
		tcPsVo6.setPicOrder(6);
		tcPsVo6.setWapUrl("/mobile/goods/pdetail?goodsId=10971");
		tcPsVo6.setAdUrl("goodsId=10971");
		tcPsVo6.setIosUrl("goodsId=10971");
		tcPsVo6.setPrice(BigDecimal.valueOf(58));
		tcPsVo6.setPayPrice(BigDecimal.valueOf(58));
		tcListPro.add(tcPsVo6);
		return indexVo;
	}
}
