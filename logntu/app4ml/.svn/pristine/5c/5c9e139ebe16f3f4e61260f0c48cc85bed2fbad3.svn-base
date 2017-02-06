package com.mall.web.pay.notice;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.ContextLoader;

import com.mall.web.mall.domain.MemberPayNotice;

/**
 * 类名：支付成功通知游戏
 */

public class PayNoticUtil {
	private static PayNoticUtil payNoticeUtil;
	/**********************/
	// 队列
	private static Vector<String> orderQueue;// 订单号队列
	private static Hashtable<String, MemberPayNotice> noticeMap;// 通知信息

	private PayNoticUtil() {
		orderQueue = new Vector<String>(50);
		noticeMap = new Hashtable<String, MemberPayNotice>();
		taskExecutor=(TaskExecutor) ContextLoader.getCurrentWebApplicationContext().getBean("threadPool");
	}

	public static PayNoticUtil getInstance() {
		if (payNoticeUtil == null)
			payNoticeUtil = new PayNoticUtil();
		return payNoticeUtil;
	}

	/**
	 * 生产通知消息
	 * 
	 * @param notices
	 *            消息队列
	 */
	public static void push2Notices(List<MemberPayNotice> notices) {
		if (notices == null || notices.size() <= 0)
			return;
		for (MemberPayNotice notice : notices) {
			// 判断是否已经在队列中
			if (noticeMap.get(notice.getOrderSn()) != null)
				continue;
			System.out.println(">>>>:" + notice.getOrderSn() + ":" + notice.getPayValue().doubleValue());
			noticeMap.put(notice.getOrderSn(), notice);
			orderQueue.add(notice.getOrderSn());
		}
	}

	/**
	 * 生产通知消息
	 * 
	 * @param notices
	 *            消息队列
	 */
	public static void push2Notice(MemberPayNotice notice) {
		if (notice == null)
			return;
		// 判断是否已经在队列中
		if (noticeMap.get(notice.getOrderSn()) != null)
			return;
		System.out.println(">>>>:" + notice.getOrderSn() + ":" + notice.getPayValue().doubleValue());
		noticeMap.put(notice.getOrderSn(), notice);
		orderQueue.add(notice.getOrderSn());
	}

	/**
	 * 消费通知消息
	 * 
	 * @param notices
	 *            消息队列
	 */
	public static synchronized MemberPayNotice pop2Notice() {
		if (noticeMap.size() <= 0 && orderQueue.size() <= 0)
			return null;
		if (orderQueue.size() <= 0) {
			noticeMap.clear();
			return null;
		}
		String sn = orderQueue.remove(0);
		return noticeMap.remove(sn);
	}

	/*******************************************/
	// 多线程处理通知信息
	private TaskExecutor taskExecutor;

	public void startNoticeServer() {
		taskExecutor.execute(new PayNoticeGmTask());
		taskExecutor.execute(new PayNoticeGmTask());
		taskExecutor.execute(new PaySyncNoticeTask());
	}
}
