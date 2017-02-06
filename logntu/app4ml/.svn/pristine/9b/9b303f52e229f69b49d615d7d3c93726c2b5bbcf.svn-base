package com.mall.web.pay.notice;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import com.mall.web.mall.domain.MemberPayNotice;
import com.mall.web.mall.member.service.MemberPayNoticeService;


public class PayNoticeGmTask implements Runnable {
	private Logger logger = Logger.getLogger(this.getClass());
	//@Autowired
	protected MemberPayNoticeService memberPayNoticeService;//通知处理
	
	public PayNoticeGmTask(){
		memberPayNoticeService=(MemberPayNoticeService) ContextLoader.getCurrentWebApplicationContext()
				.getBean("memberPayNoticeService");
	}
	//通知游戏服务器
	private boolean noticeGmServer(MemberPayNotice notice){
		return false;
	}
	//同步通知状态
	private void synNotice(MemberPayNotice notice){
		memberPayNoticeService.payNoticesSucc(notice.getOrderSn());
	}
	@Override
	public void run() {
		while (true) {
			try {
				MemberPayNotice notice=PayNoticUtil.pop2Notice();
				if(notice==null){
					Thread.sleep(100);	
					continue;
				}else{
					if(noticeGmServer(notice)){
						synNotice(notice);
					}
				}				
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				break;
			}
		}
	}

}
