package com.mall.web.mobile.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.exception.FrameworkException;
import com.mall.web.mall.member.dao.WebMemberPayRecordDao;
import com.mall.web.mobile.member.dao.MobMember4MineDao;
import com.mall.web.pay.domain.PayRecord;

/**
 * 移动端会员业务逻辑层
 * 
 * @author ty
 * 
 */

@Service("mobMember4MineService")
public class MobMember4MineService {
	// 我的部分处理
	@Resource(name = "mobMember4MineDao")
	private MobMember4MineDao mobMember4MineDao;
	// 支付报告
	@Resource(name = "webMemberPayRecordDao")
	private WebMemberPayRecordDao webMemberPayRecordDao;

	/**
	 * 会员中心->我的钱包
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public Map<String, Object> getMywallet(int userId) {
		List<Map<String, Object>> list = mobMember4MineDao.getMywallet(userId);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 会员中心->我的钱包->钱包信息
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public Map<String, Object> getMywalletInfo(int userId) {
		List<Map<String, Object>> list = mobMember4MineDao
				.getMywalletInfo(userId);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 会员中心->东家中心->首页
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public Map<String, Object> getDistHome(int userId) {
		List<Map<String, Object>> list = mobMember4MineDao.getDistHome(userId);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * @Description(功能描述) :获取用户佣金列表
	 * @author(作者) : ventrue
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public List<PayRecord> findPayRecord(int userId)
			throws FrameworkException {
		return webMemberPayRecordDao.findPayRecord(userId);
	}
}
