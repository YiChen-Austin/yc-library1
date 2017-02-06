package com.mall.web.mall.member.dao;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.domain.MemberDeposit;

/**
 * 用户支付账户信息。
 * 
 * @author sol、 chenhongxu
 */
@Repository("webMemberDepositDao")
public class WebMemberDepositDao extends BaseDao<MemberDeposit> {

	/*******************************************************************************
	 * 获取余额。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @return 返回用户余额
	 * @throws FrameworkException
	 */
	public double showbalance(int userId) throws FrameworkException {
		MemberDeposit bean = this.get(userId);
		return (Double) (bean != null ? bean.getBalance() : 0);
	}

	/*******************************************************************************
	 * 获取用户账目信息
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @return 返回存储账目
	 * @throws FrameworkException
	 */
	public MemberDeposit getMemberDeposit(int userId) throws FrameworkException {
		return this.get(userId);
	}

	/**
	 * 获取支付密码。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @return 指定用户的支付密码
	 * @throws FrameworkException
	 */
	public String getPaymentPassword(int userId) throws FrameworkException {
		MemberDeposit bean = this.get(userId);
		return bean != null ? bean.getPayPass() : "";
	}

	// 获取盐值：
	public String getPaymentSalt(int userId) throws FrameworkException {
		MemberDeposit bean = this.get(userId);
		return bean != null ? bean.getSalt() : "";
	}

	/**
	 * 更新/保存支付密码。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @param paymentPassword
	 *            新的支付密码
	 * @return 操作是否成功
	 * @throws FrameworkException
	 */
	public boolean updatePaymentPassword2(int userId, String paymentPassword, String salt) throws FrameworkException {
		MemberDeposit entity = this.get(userId);
		if (entity != null) {
			entity.setPayPass(paymentPassword);
			entity.setSalt(salt);
			this.update(entity);
		} else {
			entity = new MemberDeposit();
			entity.setUserId(userId);
			entity.setPayPass(paymentPassword);
			entity.setSalt(salt);
			this.save(entity);
		}
		return true;
	}

	public boolean updatePaymentPassword(int userId, String paymentPassword) throws FrameworkException {
		MemberDeposit entity = this.get(userId);
		if (entity != null) {
			entity.setPayPass(paymentPassword);
			this.update(entity);
		} else {
			entity = new MemberDeposit();
			entity.setUserId(userId);
			entity.setPayPass(paymentPassword);
			this.save(entity);
		}
		return true;
	}
    /****************************************************/
	/**
	 * @Description(功能描述) : 余额支付 
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int reduceBalance(int userId, double reduce) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member_deposit deposit set balance=balance-?");
		sql.append(" ,last_last_last_initial_balance=last_last_initial_balance,last_last_last_operate_time=last_last_operate_time");
		sql.append(" ,last_last_initial_balance=last_initial_balance,last_last_operate_time=last_operate_time");
		sql.append(" ,last_initial_balance=initial_balance,last_operate_time=initial_operate_time");
		sql.append(" ,initial_balance=balance,initial_operate_time=?");
		sql.append(" where user_id=? and deposit.balance>=?");
		int num = this.jdbcTemplate.update(sql.toString(),
				new Object[] { reduce, DateUtil.getGMTDate(), userId, reduce });
		// 成功余额支付
		// 优先使用可提现余额支付
		// 如果可提现部分不足，则可提现金额为0
		// 如果可提现足够，则直接减少可提现部分
		if (num > 0) {
			// 可提现不足
			int available = jdbcTemplate.update("update ml_member_deposit deposit set available_balance=0 where user_id=? and deposit.available_balance<=?",
					new Object[] { userId, reduce });
			// 可提现足够
			if (available == 0) {
				jdbcTemplate.update("update ml_member_deposit deposit set available_balance=available_balance-? where user_id=? and deposit.available_balance>?",
						new Object[] { reduce, userId, reduce });
			}
		}
		return num;
	}

	/**
	 * @Description(功能描述) : 扣除积分 
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int reducePoint(int userId, int point) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member_deposit deposit set accumulated_point=accumulated_point-?");
		sql.append(" where user_id=? and deposit.accumulated_point>=?");
		return this.jdbcTemplate.update(sql.toString(), new Object[] { point, userId, point });
	}

	/**
	 * @Description(功能描述) : 扣除积分
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int plusPoint(int userId, int point) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member_deposit deposit set accumulated_point=accumulated_point+?");
		sql.append(" where user_id=?");
		return this.jdbcTemplate.update(sql.toString(), new Object[] { point, userId });
	}

	/**
	 * @Description(功能描述) : 余额支付(预留部分余额) 
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int reduceBalance(int userId, double reduce, double baseBlance) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member_deposit deposit set balance=balance-?");
		sql.append(" ,last_last_last_initial_balance=last_last_initial_balance,last_last_last_operate_time=last_last_operate_time");
		sql.append(" ,last_last_initial_balance=last_initial_balance,last_last_operate_time=last_operate_time");
		sql.append(" ,last_initial_balance=initial_balance,last_operate_time=initial_operate_time");
		sql.append(" ,initial_balance=balance,initial_operate_time=?");
		sql.append(" where user_id=? and deposit.balance>=?");
		int num=this.jdbcTemplate.update(sql.toString(),
				new Object[] { reduce, DateUtil.getGMTDate(), userId, reduce + baseBlance });
		// 成功余额支付
		// 优先使用可提现余额支付
		// 如果可提现部分不足，则可提现金额为0
		// 如果可提现足够，则直接减少可提现部分
		if (num > 0) {
			// 可提现不足
			int available = jdbcTemplate.update(
					"update ml_member_deposit deposit set available_balance=0 where user_id=? and deposit.available_balance<=?",
					new Object[] {userId, reduce });
			// 可提现足够
			if (available == 0) {
				jdbcTemplate.update(
						"update ml_member_deposit deposit set available_balance=available_balance-? where user_id=? and deposit.available_balance>?",
						new Object[] { reduce, userId, reduce });
			}
		}
		return num;
	}

	/**
	 * @Description(功能描述) : 增加金额 
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int plusBalance(int userId, double plus) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member_deposit deposit set balance=balance+?");
		sql.append(" ,last_last_last_initial_balance=last_last_initial_balance,last_last_last_operate_time=last_last_operate_time");
		sql.append(" ,last_last_initial_balance=last_initial_balance,last_last_operate_time=last_operate_time");
		sql.append(" ,last_initial_balance=initial_balance,last_operate_time=initial_operate_time");
		sql.append(" ,initial_balance=balance,initial_operate_time=?");
		sql.append(" where user_id=?");
		return this.jdbcTemplate.update(sql.toString(),
				new Object[] { plus, DateUtil.getGMTDate(), userId });
	}
	/**
	 * @Description(功能描述) : 增加金额和可提现金额
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int plusBalanceAvailable(int userId, double plus) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member_deposit deposit set balance=balance+?");
		sql.append(" ,last_last_last_initial_balance=last_last_initial_balance,last_last_last_operate_time=last_last_operate_time");
		sql.append(" ,last_last_initial_balance=last_initial_balance,last_last_operate_time=last_operate_time");
		sql.append(" ,last_initial_balance=initial_balance,last_operate_time=initial_operate_time");
		sql.append(" ,initial_balance=balance,initial_operate_time=?");
		sql.append(" where user_id=?");
		int num=this.jdbcTemplate.update(sql.toString(),
				new Object[] { plus, DateUtil.getGMTDate(), userId });
		//增加可提现金额
		jdbcTemplate.update("update ml_member_deposit deposit set available_balance=available_balance+? where user_id=?",new Object[] { plus, userId});
		//可提现金额不得大于余额
		jdbcTemplate.update("update ml_member_deposit deposit set available_balance=balance where user_id=? and available_balance>balance",new Object[] {userId});
		return num;
	}
	
	/**
	 * @Description(功能描述) : 减少预期收益
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int reduceRevenue(int userId, double reduce) {
		int available=jdbcTemplate.update("update ml_member_deposit deposit set expected_revenue=expected_revenue-? where user_id=?",
					new Object[] { reduce, userId});
		jdbcTemplate.update("update ml_member_deposit deposit set expected_revenue=0 where user_id=? and expected_revenue<0",
				new Object[] { reduce, userId});
		return available;
	}

	/**
	 * @Description(功能描述) : 增加预期收益 
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int plusRevenue(int userId, double plus) {
		return jdbcTemplate.update("update ml_member_deposit deposit set expected_revenue=expected_revenue+? where user_id=?",
				new Object[] { plus, userId});
	}

	/**
	 * @Description(功能描述) : 减少冻结
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int reduceFrozen(int userId, double reduce) {
		int available=jdbcTemplate.update("update ml_member_deposit deposit set frozen_fund=frozen_fund-? where user_id=?",
					new Object[] { reduce, userId});
		jdbcTemplate.update("update ml_member_deposit deposit set frozen_fund=0 where user_id=? and frozen_fund<0",
				new Object[] { reduce, userId});
		return available;
	}

	/**
	 * @Description(功能描述) : 增加冻结 
	 * @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int plusFrozen(int userId, double plus) {
		return jdbcTemplate.update("update ml_member_deposit deposit set frozen_fund=frozen_fund+? where user_id=?",
				new Object[] { plus, userId});
	}
}