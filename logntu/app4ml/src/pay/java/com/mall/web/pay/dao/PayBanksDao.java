package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.pay.domain.PayBanks;

@Repository("payBanksDao")
public class PayBanksDao extends BaseDao<PayBanks> {
	/**
	 * 返回所有系统支持的有效银行卡信息
	 * 
	 * @return List<PayBanks>
	 */
	public List<PayBanks> findValidPayBanks() {
		String hql = "from PayBanks where bankCode is not null and bankCode!='' order by sequence asc";
		return list(hql);
	}
}
