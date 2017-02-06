package com.mall.web.mall.order.sync;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mall.web.mall.domain.Order;
import com.mall.web.mall.domain.OrderSucc;
import com.mall.web.mall.member.service.WebMemberDepositService;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.mall.order.service.OrderSuccService;

/**
 * 支付成功(状态为20)后处理
 * 
 * 每个5s同步一次
 * 
 * 1、获取待处理数据
 * 
 * 2、将获取的待处理数据状态立即改为处理中
 * 
 * 3、减少库存
 * 
 * 4、将已完成同步的数据状态改为处理完成
 */
@Component("order20SuccWorker")
public class Order20SuccWorker {

	private static Logger logger = Logger.getLogger(Order20SuccWorker.class);
	@Autowired
	protected OrderSuccService orderSuccService;// 订单成功
	@Autowired
	protected WebMemberDepositService webMemberDepositService;// 账户情况
	@Autowired
	protected OrderService ordeService;// 订单

	/**
	 * 支撑成功后处理
	 * 
	 * 每个5s同步一次
	 * 
	 * 1、获取待处理数据
	 * 
	 * 2、将获取的待处理数据状态立即改为处理中
	 * 
	 * 3、减少库存
	 * 
	 * 4、将已完成同步的数据状态改为处理完成
	 */
	public void syncTasks() {
		try {
			// 1、获取待处理数据
			List<OrderSucc> list = orderSuccService.findPaySuccs();
			if (list == null || list.size() <= 0)
				return;
			while (list.size() > 0) {
				// 2、将获取的待处理数据状态立即改为处理中
				orderSuccService.batchPaySuccing(list);
				for (OrderSucc succ : list) {
					// 3、同步数据
					try {
						doSync(succ);
					} catch (Exception e) {
						logger.warn(e);
						e.printStackTrace();
					}

				}
				// 4、将已完成同步的数据状态改为处理完成
				try {
					orderSuccService.batchPaySucced(list);
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn(e);
				}
				// 获取待处理数据
				list = orderSuccService.findPaySuccs();
			}
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}

	private void doSync(OrderSucc succ) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
//		try {
//			// 三级代理
//			List<MemberDistRelationVo> list = webMemberDistService
//					.findSupersDist(succ.getBuyerId());
//			// 计算和分配佣金
//			doCommission(succ, list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.warn(e);
//		}
	}

}
