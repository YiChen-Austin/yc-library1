package com.mall.web.mall.third.qq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.mall.third.qq.dao.MemberBoundDao;
import com.mall.web.mall.domain.MemberBound;


/**
 * @Description(描述)	: 绑定用户
 * @author(作者)			: wangliyou
 * @date (开发日期)		: 2015年9月9日 上午11:25:26
 */
@Service("memberBoundService")
public class MemberBoundService {
	
	@Resource(name = "memberBoundDao")
	private MemberBoundDao memberBoundDao;
	
	/**
	 * @Description(功能描述)		: 绑定用户
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月17日 上午10:53:53 
	 * @param memberBound
	 */
	@Transactional
	public void saveMemberBound(MemberBound memberBound){
		try {
			memberBoundDao.save(memberBound);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description(功能描述)		: 根据openid查询绑定信息
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月17日 上午11:45:57 
	 * @param openId			: openid
	 * @param chanel			: 来源渠道 0：qq ，1：微信 ，2：支付宝	
	 * @return
	 */
	public MemberBound findMemberBound(String openId,int chanel){
		return memberBoundDao.findBoundByOpenId(openId,chanel);
	}
}
