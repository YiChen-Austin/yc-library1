package com.mall.web.mall.member.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum.BalanceReserve;
import com.mall.web.mall.domain.MemberBankCard;
import com.mall.web.mall.member.dao.MemberBankCardDao;
import com.mall.web.mall.member.vo.MemberBankCardVo;

@Service("memberBankCardService")
public class MemberBankCardService {
	@Resource(name = "memberBankCardDao")
	private MemberBankCardDao memberBankCardDao;

	/**
	 * 保存或更新银行卡
	 * 
	 * @param userId
	 *            会员id
	 * @throws FrameworkException
	 */
	@Transactional
	public void saveMemberBankCard(int userId, MemberBankCardVo bean)
			throws FrameworkException {

		MemberBankCard entity = memberBankCardDao.get(userId);
		if (entity == null) {
			entity = new MemberBankCard();
			entity = bean.bean2entry(entity);
			entity.setUserId(userId);
			entity.setOperateTime(DateUtil.getGMTDate());
			entity.setBindType(0);
			memberBankCardDao.save(entity);
		} else {
			entity = bean.bean2entry(entity);
			entity.setUserId(userId);
			entity.setOperateTime(DateUtil.getGMTDate());
			memberBankCardDao.update(entity);
		}
	}

	/**
	 * 银行卡信息
	 * 
	 * @param userId
	 *            会员id
	 * @return 银行卡信息
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public MemberBankCardVo findMemberBankCard(int userId)
			throws FrameworkException {
		MemberBankCard entity = memberBankCardDao.get(userId);
		if (entity == null)
			return null;
		MemberBankCardVo bean = MemberBankCardVo.entry2bean(entity);
		return bean;
	}

	/**
	 * @Description(功能描述) : 判断用于是否提现(满足条件的提现数量)
	 * 
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional(readOnly = true)
	public int withdrawNum(int userId) {
		Map<String, Object> map = memberBankCardDao.withdraw(userId);
		// 支付信息不存在
		if (map == null || map.size() <= 0)
			return -1;
		// 判断是否有余额需要提现
		BigDecimal balance = (BigDecimal) map.get("balance");
		if (balance == null
				|| balance.doubleValue() < BalanceReserve.YUAN100.getName())
			return -2;
		// 判断支付密码是否设置
		String payPass = (String) map.get("pay_pass");
		if(BaseUtil.isEmpty(payPass))
			return -3;
		// 判断提现银行卡是否设置
		String bankCardNo = (String) map.get("bank_card_no");
		if (BaseUtil.isEmpty(bankCardNo))
			return -4;
		return 1;
	}
}
