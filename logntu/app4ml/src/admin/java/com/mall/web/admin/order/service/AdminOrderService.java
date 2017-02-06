package com.mall.web.admin.order.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.order.dao.AdminOrderDao;
import com.mall.web.admin.order.vo.AdminOrderGoodsVo;
import com.mall.web.admin.order.vo.AdminOrderVo;

/**
 * @Description(描述) : 商品订单
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:18:28
 */
@Service("adminOrderService")
public class AdminOrderService {

	// 订单
	@Resource(name = "adminOrderDao")
	private AdminOrderDao adminOrderDao;

	/********************************************/
	// 普通订单（非退货退款）
	/**
	 * 平台运营后台管理
	 * 
	 * 订单所有信息数量
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public int getAllOrderRow(boolean isReturn, Map<String, Object> params) {
		return adminOrderDao.getAllOrderRow(isReturn, params, null);
	}

	/**
	 * 平台运营后台管理
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderVo> findAllOrders(boolean isReturn,
			Map<String, Object> params, PageBean4UI pageBean) {
		return adminOrderDao.findAllOrders(isReturn, params, null, pageBean);
	}

	/**
	 * 平台运营后台管理(基于店铺统计)
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderVo> findAllOrders4stat(boolean isReturn,
			Map<String, Object> params) {
		return adminOrderDao.findAllOrders4stat(isReturn, params, null);
	}

	/**
	 * 平台运营后台管理(基于店铺统计,汇总统计)
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public AdminOrderVo findAllOrders4statSum(boolean isReturn,
			Map<String, Object> params) {
		return adminOrderDao.findAllOrders4statSum(isReturn, params, null);
	}

	/********************************************/
	// 退货退款
	// 普通订单（非退货退款）
	/**
	 * 平台运营后台管理(待退货退款)
	 * 
	 * 订单所有信息数量
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public int getAllUnRefundOrderRow(Map<String, Object> params) {
		String sql = " ((g.refund_status=3 and g.refund_type=1) ";// 仅退款
		sql += " or (g.refund_status=4 and g.refund_type=2)) ";// 退货退款
		return adminOrderDao.getAllOrderRow(true, params, sql);
	}

	/**
	 * 平台运营后台管理(待退货退款)
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderVo> findAllUnRefundOrders(Map<String, Object> params,
			PageBean4UI pageBean) {
		String sql = " ((g.refund_status=3 and g.refund_type=1) ";// 仅退款
		sql += " or (g.refund_status=4 and g.refund_type=2)) ";// 退货退款
		return adminOrderDao.findAllOrders(true, params, sql, pageBean);
	}

	/**
	 * 平台运营后台管理(已经退货退款)
	 * 
	 * 订单所有信息数量
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public int getAllRefundOrderRow(Map<String, Object> params) {
		params.put("refund_status", 5);// 退款成功
		return adminOrderDao.getAllOrderRow(true, params, null);
	}

	/**
	 * 平台运营后台管理(已经退货退款)
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderVo> findAllRefundOrders(Map<String, Object> params,
			PageBean4UI pageBean) {
		params.put("refund_status", 5);// 退款成功
		return adminOrderDao.findAllOrders(true, params, null, pageBean);
	}

	/****************************************/
	// 处理成功退款信息
	/**
	 * 平台运营后台管理
	 * 
	 * 处理财务人员提交的成功退款信息
	 * 
	 * @return 返回影响数量
	 */
	@Transactional
	public int[] batchRefund(final List<AdminOrderVo> refunds) {
		return adminOrderDao.batchRefund(refunds);
	}

	/****************************************/
	/**
	 * 平台运营后台管理
	 * 
	 * 订单产品所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderGoodsVo> findAllOrderGoods(boolean isReturn,
			Map<String, Object> params, PageBean4UI pageBean) {
		return adminOrderDao
				.findAllOrderGoods(isReturn, params, null, pageBean);
	}

	/**
	 * 平台运营后台管理
	 * 
	 * 订单产品所有信息数量
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public int getAllOrderGoodsRow(boolean isReturn, Map<String, Object> params) {
		return adminOrderDao.getAllOrderGoodsRow(isReturn, params, null);
	}
}
