package com.mall.web.mall.member.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.MemberBankCard;

@Repository("memberBankCardDao")
public class MemberBankCardDao extends BaseDao<MemberBankCard> {
	/**
	 * @Description(功能描述) : 判断用于是否提现(满足条件的提现数量)
	 * 
	 *  0、客户必须已设置提现账户、支付密码；
	 *  1、客户账户余额必须>${minBalance}元(RMB),才允许单次提现
	 * 
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public Map<String, Object> withdraw(int userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select d.user_id,d.balance,d.pay_pass,c.bank_card_no from ml_member_deposit d ");
		sql.append(" LEFT JOIN ml_member_bank_card c on d.user_id=c.user_id ");
		sql.append(" where d.user_id=? ");
		Map<String, Object> map = this.jdbcTemplate.queryForMap(sql.toString(),
				new Object[] { userId });
		return map;
	}
}
