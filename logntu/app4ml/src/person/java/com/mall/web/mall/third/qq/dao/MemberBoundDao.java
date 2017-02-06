package com.mall.web.mall.third.qq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.MemberBound;

/**
 * @Description(描述) : 用户第三方登录绑定
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年10月17日 上午10:51:57
 */
@Repository("memberBoundDao")
public class MemberBoundDao extends BaseDao<MemberBound> {

	/**
	 * @Description(功能描述) : 根据openid查询绑定信息
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月17日 上午11:45:57
	 * @param openId
	 *            : openid
	 * @param chanel
	 *            : 来源渠道 0：qq ，1：微信 ，2：支付宝
	 * @return
	 */
	public MemberBound findBoundByOpenId(String openId, int chanel) {
		StringBuilder hql = new StringBuilder();
		// Object[] condition = { openId, chanel };
		Object[] condition = { openId };
		// hql.append("from MallMemberBound where openId=? and channel=?");
		hql.append("from MemberBound where openId=?");
		List<MemberBound> list = this.list(hql.toString(), condition);
		return list.size() > 0 ? list.get(0) : null;
	}

}
