package com.mall.web.mall.member.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.domain.MemberPayNotice;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.member.dao.MemberPayNoticeDao;
import com.mall.web.pay.notice.PayNoticUtil;

/**
 *@author ventrue 
 *@category 支付通知处理记录
 */
@Service("memberPayNoticeService")
public class MemberPayNoticeService {
	@Resource(name = "memberPayNoticeDao")
	private MemberPayNoticeDao memberPayNoticeDao;

	/**
	 * 支付通知记录
	 * 
	 * 游戏通知
	 * 
	 * //1、往库中放置通知记录
	 * //2、往内存队里中放置通知记录
	 * @param Order 订单
	 * @return 返回处理成功结果
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean payNoticeQueue(Order order)
			throws FrameworkException {
		//1、往库中放置通知记录
		MemberPayNotice notice=new MemberPayNotice();
		notice.setOrderSn(order.getOrderSn());
		notice.setNoticeType(order.getType());
		notice.setGameValue(order.getGoodsAmount());
		notice.setPayValue(order.getOrderAmount());		
		notice.setNoticeCount(1);
		notice.setRemark(order.getRemark());
		
		notice.setUserCode(order.getUserCode());
		notice.setServerCode(order.getServerCode());
		notice.setRoleCode(order.getRoleCode());
		notice.setProductCode(order.getProductCode());
		
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.getGMTDate());
		c.add(Calendar.SECOND, notice.getNoticeCount()*30);//延迟30秒		
		notice.setLastTime(c.getTime());
		
		notice.setNoticeStatus(MallEnum.WorkStats.Working.getIndex());
		
		notice.setInsertTime(DateUtil.getGMTDate());
		memberPayNoticeDao.save(notice);
		//2、往内存队里中放置通知记录
		PayNoticUtil.push2Notice(notice);
		return true;
	}
	
	/**
	 * 获取待通知信息
	 * 
	 * @return List<MemberPayNotice>
	 */
	@Transactional(readOnly = true)
	public List<MemberPayNotice> findPayNotices() {
		return memberPayNoticeDao.findPayNotices(0, 50);
	}
	/**
	 * 批量更新（待发送和时间）
	 * 
	 */
	@Transactional
	public void batchPayNotices(final List<MemberPayNotice> list) {
		memberPayNoticeDao.batchPayNotices(list);
	}
	
	/**
	 * 单条处理成功通知
	 * 
	 */
	@Transactional
	public void payNoticesSucc(String orderSn) {
		memberPayNoticeDao.payNoticesSucc(orderSn);
	}
}
