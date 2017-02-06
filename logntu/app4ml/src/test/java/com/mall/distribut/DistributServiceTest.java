/**
 * 
 */
package com.mall.distribut;

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
import com.mall.web.mall.distribut.service.MemberDistService;
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
 * @功能说明：系统人员业务层测试 @作者： 印鲜刚 @创建日期： 2010-5-6 @
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mall/spring/applicationContext.xml",
		"classpath:mall/spring/spring-hibernate.xml" })
public class DistributServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static Logger logger = Logger.getLogger(DistributServiceTest.class);

	// @Autowired
	// protected MallPayDataService mallPayService;
	// @Autowired
	// protected YzmOrderService orderService;
	// @Autowired
	// protected SystemRegionCacheUtil systemRegionCacheUtil;
	@Autowired
	protected MemberDistService memberDistService;
	@Autowired
	protected WebMemberDepositService webMemberDepositService;

	@Test
	@Rollback(false)
	public void doMemberDist() throws FrameworkException {
		System.out.println("result0:" + memberDistService.doMemberDist(3253022, 3253012, null, "yes"));
		System.out.println("result1:" + memberDistService.doMemberDist(3253031, 3253022, null, "yes"));
		System.out.println("result2:" + memberDistService.doMemberDist(3253032, 3253031, null, null));
		System.out.println("result3:" + memberDistService.doMemberDist(3253034, 3253031, null, null));
		System.out.println("result4:" + memberDistService.doMemberDist(3253035, 3253034, null, null));
		System.out.println("result5:" + memberDistService.doMemberDist(3253036, 3253035, null, null));
		System.out.println("result6:" + memberDistService.doMemberDist(3253037, 3253035, null, null));
		System.out.println("result7:" + memberDistService.doMemberDist(3253038, 3253035, null, null));
		System.out.println("result8:" + memberDistService.doMemberDist(3253039, 3253035, null, null));
		System.out.println("result9:" + memberDistService.doMemberDist(3253040, 3253035, null, null));
		System.out.println("result10:" + memberDistService.doMemberDist(3253041, 3253040, null, null));
	}

	//@Test
	//@Rollback(false)
	public void openNewAccount() throws FrameworkException {
		webMemberDepositService.openNewAccount(25364, "13101358516");
		webMemberDepositService.openNewAccount(25365, null);
		webMemberDepositService.openNewAccount(25366, "13101358517");
	}
}