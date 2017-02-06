package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.Order;

@Repository("payOrderDao")
public class PayOrderDao extends BaseDao<Order> {
	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单列表
	 */
	public Order findOrderById(Long orderId) {
		String hql = "from Order where orderId=?";
		List<Order> list = list(hql,
				new Object[] { orderId });
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单列表
	 */
	public List<Order> findOrderBySn(String sn) {
		String hql = "from Order where orderSn=?";
		return list(hql, new Object[] { sn });
	}

	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单列表
	 */
	@SuppressWarnings("rawtypes")
	public List findOrderBySn(String[] sns) {
		StringBuffer sb = new StringBuffer();
		for (String sn : sns) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("'" + sn + "'");
		}
		String hql = "from Order where orderSn in (" + sb.toString() + ")";
		return list(hql);
	}
}
