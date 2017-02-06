package com.mall.web.mall.member.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum.BalanceReserve;
import com.mall.web.mall.domain.MemberDeposit;
import com.mall.web.mall.member.dao.WebMemberDepositDao;
import com.mall.web.mall.member.dao.WebMemberFrozenDao;
import com.mall.web.mall.member.dao.WebMemberPayRecordDao;
import com.mall.web.mall.member.vo.WebMemberDepositVo;
import com.mall.web.pay.domain.PayRecord;

/**
 * 账户信息
 * 
 */
@Service("webMemberDepositService")
public class WebMemberDepositService {

	private static Logger logger = Logger.getLogger(WebMemberDepositService.class);
	@Resource(name = "webMemberDepositDao")
	private WebMemberDepositDao webMemberDepositDao;
	@Resource(name = "webMemberPayRecordDao")
	private WebMemberPayRecordDao webMemberPayRecordDao;
	@Resource(name = "webMemberFrozenDao")
	private WebMemberFrozenDao webMemberFrozenDao;// 冻结

	/*******************************************************************************/

	/**
	 * @Description(功能描述) : 新用户开通帐户信息 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2016年10月19日 下午2:55:03
	 */
	@Transactional
	public void openNewAccount(int userId, String phone) throws FrameworkException {
		MemberDeposit deposit = webMemberDepositDao.get(userId);
		if (BaseUtil.isEmpty(deposit)) {
			deposit = new MemberDeposit();
			deposit.setUserId(userId);
			deposit.setOperateTime(DateUtil.getGMTDate());
			deposit.setPhone(phone);
			webMemberDepositDao.save(deposit);
		}
	}

	/**
	 * 
	 * @param userId
	 *            用户编号
	 * @return 会员支付账户实例
	 * @throws FrameworkException
	 */
	public MemberDeposit getMemberDepositById(int userId) throws FrameworkException {
		MemberDeposit memberDeposit = webMemberDepositDao.get(userId);
		return memberDeposit;
	}

	/**
	 * 获取会员账户详细信息！！！。
	 * 
	 * @param userId
	 *            用户编号
	 * @return 会员支付账户实例
	 * @throws FrameworkException
	 */
	public WebMemberDepositVo getMemberDeposit(int userId) {
		try {
			MemberDeposit memberDeposit = webMemberDepositDao.get(userId);
			WebMemberDepositVo res = WebMemberDepositVo.bean2Vo(memberDeposit); // 封装
			return res;
		} catch (Exception e) {
			logger.warn(e);
		}
		return null;
	}

	/**
	 * @Description(功能描述) : 获取提现余额 @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public double getWithdrawDeposit(int userId) throws FrameworkException {
		MemberDeposit memberDeposit = webMemberDepositDao.get(userId);
		if (memberDeposit == null)
			return 0;
		return memberDeposit.getAvailableBalance() > BalanceReserve.YUAN100.getName()
				? memberDeposit.getAvailableBalance() - BalanceReserve.YUAN100.getName() : 0;
	}

	/**
	 * @Description(功能描述) : 余额支付 @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public boolean reduceBalance(int userId, double reduce) {
		int u = webMemberDepositDao.reduceBalance(userId, reduce);
		try {
			if (u > 0) {
				// 获取余额
				MemberDeposit mdp = webMemberDepositDao.getMemberDeposit(userId);
				webMemberPayRecordDao.reduceBalance(userId, reduce, mdp.getBalance());
			}
		} catch (FrameworkException e) {
			logger.warn(e);
		}
		return u > 0 ? true : false;
	}

	/**
	 * @Description(功能描述) : 扣除积分 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public int reducePoint(int userId, int point) {
		return webMemberDepositDao.reducePoint(userId, point);
	}

	/**
	 * @Description(功能描述) : 扣除积分 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public int plusPoint(int userId, int point) {
		return webMemberDepositDao.plusPoint(userId, point);
	}

	/**
	 * @Description(功能描述) : 余额转冻结(提现) @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public boolean reduceBlance2Frozen(int userId, double reduce) {
		int u = 0;
		try {
			u = webMemberDepositDao.reduceBalance(userId, reduce, BalanceReserve.YUAN100.getName());// 预留
			if (u > 0) {
				// 获取余额
				MemberDeposit mdp = webMemberDepositDao.getMemberDeposit(userId);
				webMemberPayRecordDao.reduceFrozenPay(userId, reduce, mdp.getBalance());
				webMemberDepositDao.plusFrozen(userId, reduce);
				webMemberFrozenDao.saveBalance2Frozen(userId, BigDecimal.valueOf(reduce));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return u > 0 ? true : false;
	}

	/**
	 * @Description(功能描述) : 增加金额(充值) @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public boolean plusBalance4Recharge(int userId, double plus) {
		int u = webMemberDepositDao.plusBalance(userId, plus);
		try {
			if (u > 0) {
				// 获取余额
				MemberDeposit mdp = webMemberDepositDao.getMemberDeposit(userId);
				webMemberPayRecordDao.plusBalance4Recharge(userId, plus, mdp.getBalance());
			}
		} catch (FrameworkException e) {
			logger.warn(e);
		}
		return u > 0 ? true : false;
	}

	/**
	 * @Description(功能描述) : 增加金额(销售结算) @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public boolean plusBalance4Settlement(int userId, double plus, int orderId) {
		int u = webMemberDepositDao.plusBalanceAvailable(userId, plus);
		try {
			if (u > 0) {
				// 获取余额
				MemberDeposit mdp = webMemberDepositDao.getMemberDeposit(userId);
				webMemberPayRecordDao.plusBalance4Settlement(userId, plus, mdp.getBalance(), orderId);
			}
		} catch (FrameworkException e) {
			logger.warn(e);
		}
		return u > 0 ? true : false;
	}

	/**
	 * @Description(功能描述) : (销售结算)数量 @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional(readOnly = true)
	public int getPaySize4Settlement(int userId, int orderId) throws FrameworkException {
		return webMemberPayRecordDao.getPaySize4Settlement(userId, orderId);
	}

	/**
	 * @Description(功能描述) : 增加金额(佣金) @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public boolean plusBalance4Commission(int userId, double plus, int orderId) throws FrameworkException {
		int u = webMemberDepositDao.plusBalanceAvailable(userId, plus);
		try {
			if (u > 0) {
				// 获取余额
				MemberDeposit mdp = webMemberDepositDao.getMemberDeposit(userId);
				webMemberPayRecordDao.plusBalance4Commission(userId, plus, mdp.getBalance(), orderId);
			}
		} catch (FrameworkException e) {
			logger.warn(e);
		}
		return u > 0 ? true : false;
	}

	/**
	 * @Description(功能描述) : (佣金结算)数量 @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional(readOnly = true)
	public int getPaySize4Commission(int userId, int orderId) throws FrameworkException {
		return webMemberPayRecordDao.getPaySize4Commission(userId, orderId);
	}
	
	

}