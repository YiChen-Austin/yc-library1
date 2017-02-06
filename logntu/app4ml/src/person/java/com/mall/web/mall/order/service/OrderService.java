package com.mall.web.mall.order.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.domain.OrderTimeout;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mall.order.dao.OrderDao;
import com.mall.web.mall.order.dao.OrderTimeoutDao;
import com.mall.web.mall.order.vo.WebOrderVo;

/**
 * @Description(描述) : 商品订单 @author(作者) : ventrue
 * 
 * @date (开发日期) : 2015年9月1日 上午11:18:28
 */
@Service("orderService")
public class OrderService {
	private static Logger logger = Logger.getLogger(OrderService.class);
	// 订单
	@Resource(name = "orderDao")
	private OrderDao orderDao;

	@Resource(name = "orderTimeoutDao")
	private OrderTimeoutDao orderTimeoutDao;

	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单信息
	 */

	public Order getOrderBySn(String sn) {
		List<Order> list = orderDao.findOrderBySn(sn);
		return list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * @param sns
	 *            订单编号
	 * @return 返回订单信息组
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getOrderBySn(String[] sns) {
		return orderDao.findOrderBySn(sns);
	}
	/**
	 * @Description(功能描述) :更新 @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月6日 下午4:30:20
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	public void updateOrder(Order order){
		try {
			orderDao.update(order);
		} catch (FrameworkException e) {
			logger.warn(e);
		}
	}

	/**
	 * @Description(功能描述) : 统计众筹商品参与人次 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2016年1月22日 下午4:22:51
	 */
	public List<WebOrderVo> findZcOrder(int goodsId) {
		return orderDao.findZcOrder(goodsId);
	}

	/**
	 * @Description(功能描述) : 查询订单状态数量(卖家) @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId
	 *            : 用户id
	 * @return
	 */
	public int getOrderBySellerId(int status, int sellerId) {
		return orderDao.getOrderBySellerId(status, sellerId);
	}

	/**
	 * @Description(功能描述) : 查询订单状态数量(买家) @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId
	 *            : 用户id
	 * @return
	 */
	public int getOrderByBuyerId(Byte status, int buyerId) {
		return orderDao.getOrderByBuyerId(status, buyerId);
	}

	/**
	 * 生成订单号
	 * 
	 * @author Garbin
	 * @return string
	 */
	public String genOrderSn(int sellerId, int buyerId) {
		/* 选择一个随机的方案 */
		String sn = Long.toString(System.currentTimeMillis() + sellerId + buyerId);

		if (orderDao.getOrderSnNum(sn) <= 0) {
			/* 否则就使用这个订单号 */
			return sn;
		}
		/* 如果有重复的，则重新生成 */
		return genOrderSn(sellerId, buyerId);
	}

	/**
	 * @Description(功能描述) : 查询订单状态数量(买家) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param buyerId
	 *            : 买家id
	 * @return
	 */
	public List<WebOrderVo> findOrderByBuy(int buyerId, int status) {
		List<WebOrderVo> list = new LinkedList<WebOrderVo>();
		List<Order> orders = orderDao.findOrderByBuy(buyerId, status);
		for (Order order : orders) {
			WebOrderVo vo = WebOrderVo.entity2vo(order); // 
			list.add(vo);
		}
		return list;
	}

	/**
	 * @Description(功能描述) : 查询订单状态数量(买家) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param buyerId
	 *            : 买家id
	 * @return
	 */
	public List<WebOrderVo> findOrderBySell(int sellerId, int status) {
		List<WebOrderVo> list = new LinkedList<WebOrderVo>();
		List<Order> orders = orderDao.findOrderBySell(sellerId, status);
		for (Order order : orders) {
			WebOrderVo vo = WebOrderVo.entity2vo(order); // 
			list.add(vo);
		}
		return list;
	}
	/**
	 * @Description(功能描述) : 查询消费订单状态 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param buyerId
	 *            : 买家id
	 * @return
	 */
	public List<WebOrderVo> findConsumeOrder(int buyerId) {
		List<WebOrderVo> list = new LinkedList<WebOrderVo>();
		List<Order> orders = orderDao.findOrderByBuy(buyerId, null,MallEnum.GoodsType.recharge2account.getIndex());
		for (Order order : orders) {
			WebOrderVo vo = WebOrderVo.entity2vo(order); // 
			list.add(vo);
		}
		return list;
	}
	/**
	 * @Description(功能描述) : 查询充值订单状态 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param buyerId
	 *            : 买家id
	 * @return
	 */
	public List<WebOrderVo> findRechargeOrder(int buyerId) {
		List<WebOrderVo> list = new LinkedList<WebOrderVo>();
		List<Order> orders = orderDao.findOrderByBuy(buyerId,MallEnum.GoodsType.recharge2account.getIndex(),null);
		for (Order order : orders) {
			WebOrderVo vo = WebOrderVo.entity2vo(order); // 
			list.add(vo);
		}
		return list;
	}
	/**
	 * @Description(功能描述) : 订单详情(订单、产品、收货人地址) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param orderId
	 *            : 订单id
	 * @return 订单信息
	 * @throws FrameworkException
	 */
	public Order getOrderById4Normal(int orderId) throws FrameworkException {
		Order order = orderDao.get(new Long(orderId)); // 原来的为啥要弄成long?
		return order;
	}

	// 判断退款状态中是否已经有了该订单 3.2
	public boolean getReturnTrueOrderById(int orderId) throws FrameworkException {
		boolean tf = orderDao.getIsTrueOrderByReturn(new Long(orderId));
		return tf;
	}


	/**
	 * @Description(功能描述) : 删除订单(订单、产品、收货人地址) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param orderId
	 *            : 订单id
	 * @return 订单信息
	 * @throws FrameworkException
	 */
	public int delOrderById(int orderId) throws FrameworkException {
		int del = orderDao.delOrder4Cancel(orderId);
		return del;
	}

	/**
	 * @Description(功能描述) : 修改为已收货(30卖家已发货-> 40交易成功) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param orderId
	 *            : 订单id
	 * @return 订单信息
	 * @throws FrameworkException
	 */
	public int updateOrder4Rec(int orderId) throws FrameworkException {
		return orderDao.updateOrderStatus(orderId, 30, 40);
	}

	/**
	 * @Description(功能描述) : 修改订单状态(使用于退款/退货) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @param orderId
	 *            : 订单id
	 * @param refundStatus
	 *            : 退(款与货)状态
	 * @param refundType
	 *            : 退(款与货)类型
	 * @param refundId
	 *            : 退(款与货)id
	 * @return
	 */
	public int updateOrderStatus4refund(int orderId, int refundStatus, int refundType, int refundId) {
		return orderDao.updateOrderStatus4refund(orderId, refundStatus, refundType, refundId);
	}

	/**
	 * @Description(功能描述) : 订单详情(基本) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param orderId
	 *            : 订单id
	 * @return 订单信息
	 * @throws FrameworkException
	 */
	public Order getOrderBaseById(int orderId) throws FrameworkException {
		return orderDao.get(new Long(orderId));
	}

	

	/**
	 * @Description(功能描述) : 处理余额支付成功订单 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年8月6日 下午4:30:20
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	public void succBalanceOrder(String[] orderSns) throws FrameworkException {
		for (String sn : orderSns) {
			// 修改订单状态
			List<Order> list = orderDao.findOrderBySn(sn);
			if (list == null || list.size() <= 0)
				return;
			for (Order order : list) {
				order.setStatus(20);// 订单状态，支付成功
				orderDao.update(order);
			}
		}
	}

	/**
	 * @Description(功能描述) : 批量保存超时订单信息 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年8月6日 下午4:30:20
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchTimeoutOrder(List<OrderTimeout> dataList) {
		orderTimeoutDao.batchSaveData(dataList, 20);
	}

	/**
	 * @Description(功能描述) :删除关闭订单和超时未付款订单 @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	@Transactional
	public int[] batchRemoveOrders(String ids) {
		return orderDao.batchRemoveOrders(ids);
	}

	/**
	 * @Description(功能描述) :修改订单状态 @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	@Transactional
	public int[] batchUpdateOrders(String ids, int status, String timeCol, int day) {
		return orderDao.batchUpdateOrders(ids, status, timeCol, day);
	}

	/**
	 * @Description(功能描述) :根据订单状态获取订单信息 @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	@Transactional(readOnly = true)
	public List<Order> findOrderByStatus(int status, String timeCol, int day) {
		return orderDao.findOrderByStatus(status, timeCol, day);
	}
	
	/**
	 * @param primaryId
	 *            支付id
	 * @return 返回订单列表
	 */
	public List<Order> findOrderByPayId(String primaryId) {
		return orderDao.findOrderByPayId(primaryId);
	}
	/***********************************/
	/**
	 * 形成訂單
	 * 
	 *@param payer 支付方
	 *@param benefit 收益方
	 *@param orderVo 页面所传订单信息
	 *@param payer 订单类型
	 */
	@Transactional
	public Order saveOrder4Game(WebMemberVo payer,WebMemberVo benefit ,WebOrderVo orderVo){
		//1、默认对账户充值
		String type=MallEnum.GoodsType.recharge2account.getIndex();
		//2、购买游戏商品
		if(!BaseUtil.isEmpty(orderVo.getServerCode())&&!BaseUtil.isEmpty(orderVo.getRoleCode())&&!BaseUtil.isEmpty(orderVo.getProductCode())){
			type=MallEnum.GoodsType.consumption2product.getIndex();						
		}else
		//3、购买游戏元宝
		if(!BaseUtil.isEmpty(orderVo.getServerCode())&&!BaseUtil.isEmpty(orderVo.getRoleCode())){
			type=MallEnum.GoodsType.consumption2game.getIndex();						
		}
		
		Order order=new Order();
		//处理支付方
		order.setBuyerId(payer.getUserId());
		order.setBuyerName(payer.getUserName());
		//处理收益方
		order.setSellerId(benefit.getUserId());
		order.setSellerName(benefit.getUserName());
		//充值方式
		order.setType(type);
		order.setStatus((int)MallEnum.OrderStatus.ORDER_PENDING.getIndex());//待付款
		order.setOrderSn(genOrderSn(payer.getUserId(),benefit.getUserId()));
		order.setAddTime(DateUtil.getGMTDate());
		order.setOrderAmount(BaseUtil.isEmpty(orderVo.getOrderAmount())?BigDecimal.ZERO:orderVo.getOrderAmount());
		order.setGoodsAmount(BaseUtil.isEmpty(orderVo.getGoodsAmount())?BigDecimal.ZERO:orderVo.getGoodsAmount());
		order.setDiscount(BaseUtil.isEmpty(orderVo.getDiscount())?BigDecimal.ZERO:orderVo.getDiscount());
		//通知相关信息
		order.setServerCode(orderVo.getServerCode());
		order.setUserCode(benefit.getUguid());
		order.setRoleCode(orderVo.getRoleCode());
		order.setProductCode(orderVo.getProductCode());
		
		try {
			orderDao.save(order);
		} catch (FrameworkException e) {
			e.printStackTrace();
			logger.warn(e);
		}		
		return order;
	}
}