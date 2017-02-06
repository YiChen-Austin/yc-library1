package com.mall.web.mall.member.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.domain.MemberFrozen;

/**
 * 用户冻结金额信息
 * 
 * @author ventrue
 */
@Repository("webMemberFrozenDao")
public class WebMemberFrozenDao extends BaseDao<MemberFrozen> {
	/**
	 * 余额不足导致在线支付失败，蔣在線支付金額進行凍結
	 */
	public void saveOnlineFrozen(int userId, BigDecimal frozen)
			throws FrameworkException {
		MemberFrozen mf = new MemberFrozen();
		mf.setUserId(userId);
		mf.setFrozen(frozen);
		mf.setValidFrozen(frozen);
		mf.setFrozenTime(DateUtil.getGMTDate());
		mf.setFromType("00");
		mf.setFrozenBusiId("online");
		mf.setRemark("余额不足，导致在线支付失败");
		mf.setStatus(Integer.toString(MallEnum.PayFrozenType.Frozen.getIndex()));// 冻结
		this.save(mf);
	}

	/**
	 * 提现部分资金进行冻结
	 */
	public void saveBalance2Frozen(int userId, BigDecimal frozen)
			throws FrameworkException {
		MemberFrozen mf = new MemberFrozen();
		mf.setUserId(userId);
		mf.setFrozen(frozen);
		mf.setValidFrozen(frozen);
		mf.setFrozenTime(DateUtil.getGMTDate());
		mf.setFromType("00");
		mf.setFrozenBusiId("online");
		mf.setRemark("提现冻结");
		mf.setStatus(Integer.toString(MallEnum.PayFrozenType.Frozen.getIndex()));// 冻结
		this.save(mf);
	}

	/**
	 * 用户申请，对申请部分进行冻结
	 */
	public void saveWithdrawFrozen(int userId, BigDecimal frozen)
			throws FrameworkException {
		MemberFrozen mf = new MemberFrozen();
		mf.setUserId(userId);
		mf.setFrozen(frozen);
		mf.setValidFrozen(frozen);
		mf.setFrozenTime(DateUtil.getGMTDate());
		mf.setFromType("01");
		mf.setFrozenBusiId("withdraw");
		mf.setRemark("用户申请提现");
		mf.setStatus(Integer.toString(MallEnum.PayFrozenType.Frozen.getIndex()));// 冻结
		this.save(mf);
	}
}
