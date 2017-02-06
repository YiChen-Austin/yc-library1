package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.pay.domain.PayPrimary;

@Repository("payPrimaryDao")
public class PayPrimaryDao extends BaseDao<PayPrimary> {
	/**
	 * 返回制定支付好的支付信息
	 * 
	 * @return List<PayOrders>
	 */
	public List<PayPrimary> findPayPrimaryBySn(String sn) {
		String hql = "from PayPrimary model where model.identityId=?";
		return list(hql, new Object[] { sn });
	}
}
