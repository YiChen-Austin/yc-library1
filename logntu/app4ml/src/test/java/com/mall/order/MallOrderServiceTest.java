/**
 * 
 */
package com.mall.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.web.common.util.IpSearchUtil;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.mall.order.vo.WebOrderVo;
import com.mall.web.mobile.member.service.MobMember4MineService;
import com.mall.web.mobile.member.service.MobMemberService;
import com.mall.web.pay.service.PayDataService;
import com.mall.web.pay.vo.BankPayVo;
import com.mall.web.pay.vo.BankThirdPayVo;

/**
 * @功能说明：系统人员业务层测试
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:mall/spring/applicationContext.xml",
		"classpath:mall/spring/spring-hibernate.xml",
		"classpath:mall/spring/spring-cas.xml",
		"classpath:mall/spring/spring-elasticsearch.xml",
		"classpath:mall/spring/spring-picture.xml" })
public class MallOrderServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private static Logger logger = Logger.getLogger(MallOrderServiceTest.class);

	// @Autowired
	// protected MallPayDataService mallPayService;
	// @Autowired
	// protected YzmOrderService orderService;
	// @Autowired
	// protected SystemRegionCacheUtil systemRegionCacheUtil;
	@Autowired
	protected OrderService ordeService;
	@Autowired
	protected WebMemberDepositService webMemberDepositService;
	@Autowired
	protected MobMemberService mobMemberService;
	@Autowired
	protected MobMember4MineService mobMember4MineService;

	// 提现信息

	// @Autowired
	// protected GeoKeyService geoKeyService;
	// @Autowired
	// protected BusiCityCategoryDao busiCityCategoryDao;

	// @Test
	// public void findBankThirdPays() {
	// List<BankThirdPayVo> list = mallPayService.findBankThirdPays();
	// for (BankThirdPayVo vo : list) {
	// System.out.println(vo.getThirdName() + "," + vo.getThirdImg());
	// }
	// }
	//
	// // @Test
	// public void findBankPays() {
	// List<BankPayVo> list = mallPayService.findBankPays();
	// for (BankPayVo vo : list) {
	// System.out.println(vo.getBankName() + "," + vo.getBankCode() + ","
	// + vo.getBankImg() + "," + vo.getBankThirdPay());
	// }
	// }
	//
	// // @Test
	// public void getYzmOrderBySn() {
	// System.out.println(orderService.getYzmOrderBySn("1420392163"));
	// System.out.println(orderService.getYzmOrderBySn(new String[] {
	// "1417530160", "1418090416", "1420392163" }));
	// Calendar calendar = Calendar.getInstance();
	// System.out.println(calendar.getTimeInMillis());
	// calendar.setTimeInMillis(1406101629L * 1000);
	// System.out.println(calendar.get(Calendar.YEAR));
	// }
	//
	// // @Test
	// public void getOrdersBySn() {
	// System.out.println("00:"
	// + mallPayService.getOrdersBySn(
	// "1417530160,1418134396".split(","), "00"));
	// // System.out.println("01:"
	// // + mallPayService.getOrdersBySn(
	// // "sjjf1422657472,sjjf1422607515,sjjf1422635675"
	// // .split(","), "01"));
	// }
	//
	// //@Test
	// public void findIp() throws FrameworkException {
	// systemRegionCacheUtil.loadRegionCache();
	// String[] v = IpSearchUtil.searchIp138("123.147.168.25");
	// System.out.print(v[0]+","+v[v.length -
	// 1]+","+systemRegionCacheUtil.getCitySpyByPcname(v[0],
	// v[v.length - 1]));
	// // List<RegionVo> list = systemRegionCacheUtil.getProvinces();
	// // for (RegionVo vo : list) {
	// // List<RegionVo> cities = vo.getSubRegions();
	// // if (cities == null || cities.size() <= 0)
	// // continue;
	// // for (RegionVo city : cities) {
	// // System.out.println();
	// // List<RegionVo> zones = city.getSubRegions();
	// // if (zones == null || zones.size() <= 0)
	// // continue;
	// // System.out.print(vo.getRegionName()+"," + city.getRegionName());
	// // for (RegionVo zone : zones) {
	// // System.out.print("," + zone.getRegionName()+"," +
	// // zone.getSubMapRegions());
	// // }
	// // }
	// // }
	// }
	// //@Test
	// public void findGeoKeys() throws FrameworkException {
	// //System.out.println(geoKeyService.findSimpleGeoKeys(2146, "y").size());
	// }
	// @Test
	// public void findAllCityId() throws FrameworkException {
	// // System.out.println(busiCityCategoryDao.findAllCityIds().get(0));
	// //
	// System.out.println(((Object[])busiCityCategoryDao.findAllCitySubCata().get(0))[0]);
	// systemScategoryCacheUtil.loadScategoryCache();
	// WebScategoryVo vo = systemScategoryCacheUtil.getScategory(8181, 6);
	// System.out.println(vo.getCateName());
	// Map<String, List<WebScategoryVo>> map = vo.getSubMapScategorys();
	// for (String key : map.keySet())
	// System.out.println(key + ":" + map.get(key));
	// }
	// @Test
	public void findOrderByBuy() {
		System.out.println(ordeService.findOrderByBuy(3252911, 10));
	}

	// @Test
	public void findOrderBySell() {
		System.out.println(ordeService.findOrderBySell(3252920, 10));
	}

	

	// @Test
	// @Rollback(false)
	public void plusBalance() throws FrameworkException,
			JsonProcessingException {
		List<WebOrderVo> oGoodsList = ordeService.findOrderByBuy(2, 0);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(oGoodsList));
	}

	// @Test
	@Rollback(false)
	public void delOrderById() throws FrameworkException,
			JsonProcessingException {
		System.out.println(">>>>>:" + ordeService.delOrderById(101594));
	}

	// @Test
	@Rollback(false)
	public void updateOrder4Rec() throws FrameworkException,
			JsonProcessingException {
		System.out.println(">>>>>:" + ordeService.updateOrder4Rec(101593));
	}


	// @Test
	// @Rollback(false)
	public void testBatchTimeoutOrder() throws FrameworkException {
		// ordeService.testBatchTimeoutOrder();
		List<Order> list = ordeService.findOrderByStatus(0, null, 0);
		System.out.println("xxxx1:" + list.size());
		list = ordeService.findOrderByStatus(11, "addTime", 3);
		System.out.println("xxxx2:" + list.size());
	}
}
