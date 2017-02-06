package com.mall.web.pay.notice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import com.mall.web.mall.domain.MemberPayNotice;
import com.mall.web.mall.member.service.MemberPayNoticeService;

/**
 * @author ventrue 同步数据库通知状态
 */
public class PaySyncNoticeTask implements Runnable {
	private Logger logger = Logger.getLogger(this.getClass());
	// @Autowired
	protected MemberPayNoticeService memberPayNoticeService;// 通知处理

	public PaySyncNoticeTask() {
		memberPayNoticeService = (MemberPayNoticeService) ContextLoader.getCurrentWebApplicationContext()
				.getBean("memberPayNoticeService");
	}

	@Override
	public void run() {
		while (true) {
			try {
				List<MemberPayNotice> list = memberPayNoticeService.findPayNotices();
				if (list == null || list.size() <= 0) {
					Thread.sleep(500);
					continue;
				} else {
					memberPayNoticeService.batchPayNotices(list);
					PayNoticUtil.push2Notices(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				break;
			}
		}
	}

}
