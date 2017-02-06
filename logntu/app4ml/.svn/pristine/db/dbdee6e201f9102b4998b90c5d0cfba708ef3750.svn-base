package com.mall.web.mall.third.qq.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.MemberWeichatSubscribe;

/**
 * @Description(描述) : 用户微信订阅信息
 * @author(作者) : ventrue
 * @date (开发日期) : 2016年6月17日 上午10:51:57
 */
@Repository("weichatSubscribeDao")
public class WeichatSubscribeDao extends BaseDao<MemberWeichatSubscribe> {

	/**
	 * @Description(功能描述) : 根据openid查询绑定信息
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月17日 上午11:45:57
	 * @param openId
	 *            : openid
	 * @param chanel
	 *            : 来源渠道 0：qq ，1：微信 ，2：支付宝
	 * @return
	 */
	public int updateWeichatSubscribe(String toUser, String fromUser,
			String createTime, String event) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { event, createTime, new Date(), toUser, fromUser };
		sql.append("update ml_weichat_subscribe set event=?,create_time=?,add_time=? where to_user=? and from_user=?");
		return this.jdbcTemplate.update(sql.toString(), condition);
	}

}
