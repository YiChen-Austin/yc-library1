package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.pay.domain.PayBank2Third;

@Repository("payBank2ThirdDao")
public class PayBank2ThirdDao extends BaseDao<PayBank2Third> {
	/**
	 * 返回该银行被支持第三方接口信息
	 * 
	 */
	public List<PayBank2Third> findPayBank2Thirds(String bankId) {
		String hql = "from PayBank2Third model where model.payBanks.id=?";
		return list(hql, new Object[] { bankId });
	}

	/**
	 * 返回该银行被支持第三方接口信息
	 * 
	 */
	public PayBank2Third findPayBank2Third(String bankId, String thirdId) {
		String hql = "from PayBank2Third model where model.payBanks.id=? and model.payThirdPays.id=?";
		List<PayBank2Third> list = list(hql,
				new Object[] { bankId, thirdId });
		return list.size() > 0 ? list.get(0) : null;
	}
}
