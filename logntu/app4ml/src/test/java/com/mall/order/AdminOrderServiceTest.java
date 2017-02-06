/**
 * 
 */
package com.mall.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.order.dao.AdminOrderDao;
import com.mall.web.admin.order.vo.AdminOrderVo;

/**
 * @功能说明：系统人员业务层测试
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:admin/spring/applicationContext.xml",
		"classpath:admin/spring/spring-hibernate.xml",
		"classpath:admin/spring/spring-elasticsearch.xml",
		"classpath:mall/spring/spring-picture.xml" })
public class AdminOrderServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private static Logger logger = Logger.getLogger(MallOrderServiceTest.class);

	@Autowired
	protected AdminOrderDao adminOrderDao;

	@Test
	@Rollback(false)
	public void testAllOrder() throws FrameworkException {
		boolean isReturn = true;
		Map<String, Object> params = new HashMap<String, Object>();
		/*
		 * params.put("buyerId", 3253022); params.put("sellerId", 3253022);
		 * params.put("orderId", 3253022); params.put("orderSn", 3253022);
		 * params.put("buyerId", 3253022); params.put("refundStatus", 3253022);
		 * params.put("addTimeS", new Date()); params.put("addTimeE", new
		 * Date()); params.put("payTimeS", new Date()); params.put("payTimeE",
		 * new Date()); params.put("shipTimeS", new Date());
		 * params.put("shipTimeE", new Date()); params.put("finishedTimeS", new
		 * Date()); params.put("finishedTimeE", new Date());
		 * params.put("evaluationTimeS", new Date());
		 * params.put("evaluationTimeE", new Date());
		 */

		// int count = adminOrderDao.getAllOrderRow(isReturn, params);
		// List<AdminOrderVo> list = adminOrderDao.findAllOrders(isReturn,
		// params,
		// 0, 100);
		List<AdminOrderVo> list = adminOrderDao.findAllOrders4stat(isReturn,
				params, null);
		System.out.println(">>>>>:" + list.size());
	}
}
