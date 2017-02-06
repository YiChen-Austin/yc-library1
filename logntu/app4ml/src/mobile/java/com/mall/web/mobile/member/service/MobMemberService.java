package com.mall.web.mobile.member.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.MD5Util;
import com.mall.common.vo.PageBean;
import com.mall.web.mall.domain.MemberAutoLogin;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.dao.MemberAutoLoginDao;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.dao.MobMemberDao;
import com.mall.web.mobile.member.dao.MobMemberDepositDao;
import com.mall.web.mobile.member.vo.MobMemberVo;

/**
 * 移动端会员业务逻辑层
 * 
 * @author ty
 * 
 */

@Service("mobMemberService")
public class MobMemberService {

	@Resource(name = "mobMemberDao")
	private MobMemberDao mobMemberDao;
	@Resource(name = "mobMemberDepositDao")
	private MobMemberDepositDao mobMemberDepositDao;

	// token
	@Resource(name = "autoLoginDao")
	private MemberAutoLoginDao autoLoginDao;

	/*
	 * @Resource(name = "baseDao") private BaseDao baseDao;
	 */

	/**
	 * 根据用户名和密码检验登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 会员信息或匹配失败信息
	 */
	@Transactional
	public Map<String, Object> login(String userName, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Member bean = mobMemberDao.login(userName, password);
			MobMemberVo member = new MobMemberVo();
			if (bean == null) { // 匹配不成功
				Member beanTem = mobMemberDao.checkMember(userName); // 检测帐号是否存在
				if (beanTem != null) { // 匹配失败信息,7代表用户名不存在，8代表密码错误
					map.put("result", "8");
				} else {
					map.put("result", "7");
				}
			} else { // 验证成功，生成token信息
				MemberAutoLogin autoLogin = new MemberAutoLogin();
				String token = MD5Util.MD5(bean.getUserId()
						+ bean.getUserName() + System.currentTimeMillis());
				autoLogin.setAutoDate(DateUtil.getDateAfterDays(
						DateUtil.getGMTDate(), 2));// 延期两天时间
				autoLogin.setTokenValue(token);
				autoLogin.setDomain("");
				autoLogin.setPath("/");
				autoLogin.setUserId(Integer.toString(bean.getUserId()));
				autoLogin.setUserName(bean.getUserName());
				autoLoginDao.save(autoLogin);
				member = MobMemberVo.bean2Vo(bean);
				member.setPassword("");
				map.put("vo", member);
				member.setToken(token);
				map.put("token", token);
				UserUtil.getInstatnce().setUser(token, member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 用户密码修改
	 * 
	 * @param oldPwd
	 *            原密码,加密后的
	 * @param newPwd
	 *            新密码,加密后的
	 * @return 匹配失败信息
	 */
	@Transactional
	public Boolean loginEdit(String userName, String oldPwd, String newPwd) {
		Member bean = mobMemberDao.login(userName, oldPwd);
		// 匹配不成功
		if (bean == null) {
			return false;
		} else {
			bean.setPassword(newPwd);
			try {
				mobMemberDao.update(bean);
				return true;
			} catch (FrameworkException e) {
				return false;
			}
		}
	}



	/**
	 * 根据登录会员ID获取会员可用余额
	 * 
	 * @param userId
	 *            登录会员ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getAvailableBalanceByUserId(Integer userId) {
		String balance = mobMemberDepositDao
				.getAvailableBalanceByUserId(userId);
		// 未开通支付功能
		if (balance == null) {
			balance = "未开通";
		} else if (balance.equals("0")) {
			balance = "￥0.00";
		}
		return balance;
	}

	/**
	 * 更新手机号码
	 * 
	 * @param userId
	 *            用户id
	 * @param phone
	 *            手机号码
	 */
	@Transactional
	public boolean updateMobile(int userId, String phone) {
		return mobMemberDao.updateMobile(userId, phone);
	}

	/**
	 * 检查用户是否存在
	 * 
	 * @param userName
	 *            用户名称
	 * @return 返回业务用户对象
	 */
	@Transactional
	public MobMemberVo findcheckMember(String userName) {
		Member beanTem = mobMemberDao.checkMember(userName);
		if (beanTem != null) {
			return MobMemberVo.bean2Vo(beanTem);
		}
		return null;
	}

	/**
	 * 通过用户id获取用户
	 * 
	 * @param userId
	 *            用户名称
	 * @return 返回业务用户对象
	 * @throws FrameworkException
	 */
	public MobMemberVo getMemberByid(int userId) throws FrameworkException {
		Member beanTem = mobMemberDao.get(userId);
		if (beanTem != null) {
			return MobMemberVo.bean2Vo(beanTem);
		}
		return null;
	}

	/**
	 * 注册新用户
	 * 
	 * @param phone
	 *            绑定手机号码，同时也是用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws FrameworkException
	 */
	@Transactional
	public MobMemberVo register(String phone, String password, String realName)
			throws FrameworkException {
		Member bean = new Member();
		bean.setUserName(phone);
		bean.setRealName(realName);
		bean.setEmail(phone + "@qqgo.cc");
		bean.setPassword(MD5Util.MD5(password));
		bean.setGender(1);
		bean.setLogins(1);
		bean.setUgrade(1);
		bean.setPhoneMob(phone);
		bean.setRegTime(DateUtil.getGMTDate());
		mobMemberDao.save(bean);
		if (bean.getUserId() > 0) {
			MobMemberVo vo = MobMemberVo.bean2Vo(bean);
			vo.setPassword("");
			return vo;
		}
		return null;
	}

	/**
	 * 批量保存
	 * 
	 * @return
	 */
	@Transactional
	public Member batchSaveMember4one(Integer supperId, String contactCode,
			String contactName) {

		if (BaseUtil.isEmpty(contactCode) || contactCode.length() != 11)
			return null;
		try {
			Member bean = new Member();
			bean.setUserName(contactCode);
			bean.setRealName(contactName);
			bean.setEmail(contactCode + "@qqgo.cc");
			bean.setPassword(MD5Util.MD5(contactCode.substring(6)));
			bean.setGender(1);
			bean.setLogins(1);
			bean.setUgrade(1);
			bean.setPhoneMob(contactCode);
			bean.setRegTime(DateUtil.getGMTDate());
			mobMemberDao.save(bean);

			return bean;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 使用绑定手机登录
	 * 
	 * @param phone
	 *            手机号码
	 * @return
	 */
	@Transactional(readOnly = true)
	public MobMemberVo phoneLogin(String phone) {
		Member bean = mobMemberDao.getMemberByPhone(phone);
		MobMemberVo vo = MobMemberVo.bean2Vo(bean);
		if (vo != null) {
			vo.setPassword("");
		}
		return vo;
	}

	/**
	 * 批量查询判断存在用户
	 * 
	 * @return
	 */
	public List<String> batchFindMember(String insql) {
		List<String> list = new LinkedList<String>();
		List<Map<String, Object>> lm = mobMemberDao.batchFindMember(insql);
		for (Map<String, Object> m : lm) {
			list.add((String) m.get("user_name"));
		}
		return list;
	}
}
