package com.mall.web.mobile.member.dao;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.MemberDeposit;

/**
 * 用户钱包DAO
 * @author ty
 */
@Repository("mobMemberDepositDao")
public class MobMemberDepositDao extends BaseDao<MemberDeposit>
{
	/**
	 * 根据当前登录会员ID查看钱包可用余额
	 * 
	 * @param userId	登录会员ID
	 * @return
	 */
	public String getAvailableBalanceByUserId(Integer userId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userId };
		hql.append("select availableBalance from MemberDeposit where userId=? and isActivated=1");
		return (String)this.getSimpleValue(hql.toString(), condition);
	}
}
