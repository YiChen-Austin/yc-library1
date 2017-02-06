package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.pay.domain.PayBank2ThirdEx;

@Repository("payBank2ThirdExDao")
public class PayBank2ThirdExDao extends BaseDao<PayBank2ThirdEx> {
	/**
	 * 返回该银行被支持第三方接口信息
	 * 
	 */
	public PayBank2ThirdEx findPayBank2ThirdEx(String bankId) {
		String hql = "from PayBank2ThirdEx model where model.payBank.id=?";
		List<PayBank2ThirdEx> list = list(hql,
				new Object[] { bankId });
		return list.size() > 0 ? list.get(0) : null;
	}
}
