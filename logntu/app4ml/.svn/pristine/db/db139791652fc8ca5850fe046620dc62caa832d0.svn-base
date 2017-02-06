package com.mall.web.mobile.common.service;

import javax.annotation.Resource;

import jxl.common.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.web.mall.domain.Member;
import com.mall.web.mobile.member.dao.MobMemberDao;

/**
 * 移动端通用业务层
 * 
 * @author ty
 *
 */
@Service("mobCommonService")
public class MobCommonService {
	private static Logger logger = Logger.getLogger(MobCommonService.class);

	@Resource(name = "mobMemberDao")
	private MobMemberDao mobMemberDao;

	/**
	 * 更新短信验证码
	 * 
	 * @param userId
	 *            用户id
	 * @param validCode
	 *            验证码
	 */
	@Transactional
	public Boolean updateActivation(int userId, String validCode) {
		try {
			mobMemberDao.updateActivation(userId, validCode);
			return true;
		} catch (Exception e) {
			logger.warn(e);
			return false;
		}

	}

	/**
	 * 检测当前手机是否已被绑定
	 * 
	 * @param phone
	 *            手机号码
	 * @return
	 */
	@Transactional(readOnly=true)
	public boolean checkPhoneIsBind(String phone) {
		Member bean = mobMemberDao.checkPhoneIsBind(phone);
		if (bean != null) {
			return true;
		}
		return false;
	}
}
