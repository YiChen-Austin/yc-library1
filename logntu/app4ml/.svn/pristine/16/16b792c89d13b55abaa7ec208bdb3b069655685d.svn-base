package com.mall.web.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.pay.domain.PayThirdPays;

@Repository("payThirdPaysDao")
public class PayThirdPaysDao extends BaseDao<PayThirdPays> {
	/**
	 * 返回所有系统支持的有效第三方接口信息
	 * 
	 */
	public List<PayThirdPays> findValidPayThirdPays() {
		String hql = "from PayThirdPays where isSupport='Y' and isBank='N' order by sequence asc";
		return list(hql);
	}
}
