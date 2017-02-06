package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.pay.domain.PayOrders;

@Repository("payOrdersDao")
public class PayOrdersDao extends BaseDao<PayOrders> {
	/**
	 * 返回制定支付下的支付订单对应信息
	 * 
	 * @return List<PayOrders>
	 */
	public List<PayOrders> findPayOrdersByPay(String primaryId) {
		String hql = "from PayOrders model where model.pay.primaryId=?";
		return list(hql, new Object[] { primaryId });
	}
}
