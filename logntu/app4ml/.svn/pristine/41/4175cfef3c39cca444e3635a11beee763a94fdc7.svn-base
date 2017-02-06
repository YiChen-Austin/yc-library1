package com.mall.web.mall.third.qq.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.util.BaseUtil;
import com.mall.web.mall.domain.MemberWeichatSubscribe;
import com.mall.web.mall.third.qq.dao.WeichatSubscribeDao;

/**
 * @Description(描述) : 绑定用户
 * @author(作者) : ventrue
 * @date (开发日期) : 2016年9月9日 上午11:25:26
 */
@Service("weichatSubscribeService")
public class WeichatSubscribeService {

	@Resource(name = "weichatSubscribeDao")
	private WeichatSubscribeDao weichatSubscribeDao;

	/**
	 * @Description(功能描述) : 绑定用户
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 上午10:53:53
	 * @param memberBound
	 */
	@Transactional
	public void doWeichatSubscribe(String toUser, String fromUser,
			String createTime, String event, Integer userId) {
		if (BaseUtil.isEmpty(event))
			return;
		if (!event.equalsIgnoreCase("subscribe")
				&& !event.equalsIgnoreCase("unsubscribe"))
			return;
		try {
			if (weichatSubscribeDao.updateWeichatSubscribe(toUser,
					fromUser, createTime, event) <= 0) {
				MemberWeichatSubscribe scs = new MemberWeichatSubscribe();
				scs.setAddTime(new Date());
				scs.setCreateTime(createTime);
				scs.setEvent(event);
				scs.setFromUser(fromUser);
				scs.setToUser(toUser);
				scs.setUserId(userId);
				weichatSubscribeDao.save(scs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
