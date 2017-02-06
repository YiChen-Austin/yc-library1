package com.mall.web.mall.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.web.mall.domain.MemberAutoLogin;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.dao.MemberAutoLoginDao;
import com.mall.web.mall.member.dao.WebMemberDao;
import com.mall.web.mobile.member.vo.MobMemberVo;

@Service("autoLoginService")
public class AutoLoginService {
	@Resource(name = "autoLoginDao")
	private MemberAutoLoginDao autoLoginDao;
	@Resource(name = "webMemberDao")
	private WebMemberDao webMemberDao;

	/**
	 * 添加
	 * 
	 * @param client
	 * @throws FrameworkException
	 */
	@Transactional
	public void saveAutoLogin(MemberAutoLogin autoLogin)
			throws FrameworkException {
		this.autoLoginDao.save(autoLogin);
	}

	/**
	 * 根据用户名称获取会员信息 caokw
	 * 
	 * @param userName
	 *            用户名称
	 * @return 会员信息
	 */
	@Transactional(readOnly = true)
	public MobMemberVo getUserByToken(String token) {
		Member member = webMemberDao.getUserByToken(token);
		if (member == null)
			return null;
		MobMemberVo memberVo = MobMemberVo.bean2Vo(member);
		return memberVo;
	}

	/**
	 * @Description(功能描述) : 根据token值查找用户对象
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月31日 上午10:50:35
	 * @param cookieValue
	 *            : token值
	 * @return
	 * @throws FrameworkException
	 */
	public MemberAutoLogin findAutoLogins(String cookieValue)
			throws FrameworkException {
		List<MemberAutoLogin> list = this.autoLoginDao
				.findAutoLogins(cookieValue);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Transactional
	public String saveAutoLogin(String userId, String userName,
			String remoteAddr) throws FrameworkException {
		String token = MD5Util.MD5(userId + userName
				+ System.currentTimeMillis());
		MemberAutoLogin autoLogin = new MemberAutoLogin();
		autoLogin.setAutoDate(DateUtil.getGMTDate());
		autoLogin.setUserId(userId);
		autoLogin.setUserName(userName);
		autoLogin.setTokenValue(token);
		autoLogin.setPath("/");
		autoLogin.setDomain(remoteAddr);
		saveAutoLogin(autoLogin);
		return token;
	}

	/**
	 * @Description(功能描述) : 根据token值删除token数据
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月31日 上午10:50:00
	 * @param cookieValue
	 *            : token值
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	public int removeAutoLogin(String cookieValue) {
		try {
			return autoLoginDao.removeAutoLogin(cookieValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
