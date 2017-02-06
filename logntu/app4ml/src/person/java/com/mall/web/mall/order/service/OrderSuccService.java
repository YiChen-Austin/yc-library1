package com.mall.web.mall.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.mall.domain.OrderSucc;
import com.mall.web.mall.order.dao.OrderSuccDao;

/**
 * @Description(描述) : 订单成功
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:18:28
 */
@Service("orderSuccService")
public class OrderSuccService {
	// 物流详情
	@Resource(name = "orderSuccDao")
	private OrderSuccDao orderSuccDao;

	/**
	 * @return 返回支付成功的订单成功信息
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public List<OrderSucc> findPaySuccs() throws FrameworkException {
		return orderSuccDao.findPaySuccs();
	}

	/**
	 * @return 返回订单完结的订单成功信息
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public List<OrderSucc> findOverSuccs() throws FrameworkException {
		return orderSuccDao.findOverSuccs();
	}

	/**
	 * @return 返回订单完结的订单成功信息(需要第三方处理的订单)
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public List<OrderSucc> findThirdSuccs() throws FrameworkException {
		return orderSuccDao.findThirdSuccs();
	}

	/**
	 * @return 批量处理成功状态(处理中)
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchPaySuccing(List<OrderSucc> list)
			throws FrameworkException {
		orderSuccDao.batchOrderSuccs(list, 200);
	}

	/**
	 * @return 批量处理成功状态(处理完毕)
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchPaySucced(List<OrderSucc> list)
			throws FrameworkException {
		orderSuccDao.batchOrderSuccs(list, 2000);
	}

	/**
	 * @return 批量处理成功状态(处理中)
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchOverSuccing(List<OrderSucc> list)
			throws FrameworkException {
		orderSuccDao.batchOrderSuccs(list, 400);
	}

	/**
	 * @return 批量处理成功状态(处理中)
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchThirdSuccing(List<OrderSucc> list)
			throws FrameworkException {
		orderSuccDao.batchOrderSuccs4mult(list, 10);
	}

	/**
	 * @return 批量处理成功状态(处理完毕)
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchOverSucced(List<OrderSucc> list)
			throws FrameworkException {
		orderSuccDao.batchOrderSuccs(list, 4000);
	}

	/**
	 * @return 批量处理成功状态(处理完毕)
	 * @throws FrameworkException
	 */
	@Transactional
	public void batchThirdSucced(List<OrderSucc> list)
			throws FrameworkException {
		orderSuccDao.batchOrderSuccs4mult(list, 100);
	}

	/**
	 * @return 返回订单完结的订单成功信息
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public int getOverSuccSize(int userId) throws FrameworkException {
		return orderSuccDao.getOverSuccSize(userId);
	}

	/**
	 * @Description(功能描述) :修改订单成功状态
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	@Transactional
	public int[] batchUpdateOrderSuccs2000(String ids, int status) {
		return orderSuccDao.batchUpdateOrderSuccs(ids, status, 2000);
	}

	/**
	 * @Description(功能描述) :修改订单成功状态
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	@Transactional
	public int[] batchUpdateOrderSuccs4000(String ids, int status) {
		return orderSuccDao.batchUpdateOrderSuccs(ids, status, 4000);
	}
}
