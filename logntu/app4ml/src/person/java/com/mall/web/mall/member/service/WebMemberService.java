package com.mall.web.mall.member.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.domain.Data;
import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.mail.Mail4Reg;
import com.mall.common.mail.Mail4RegUtil;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.CryptoTools;
import com.mall.common.util.DateUtil;
import com.mall.common.util.IdentityUtil;
import com.mall.common.util.RandomGUID;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.common.utils.MallEnum.UgradeValue;
import com.mall.web.mall.distribut.service.MemberDistService;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.domain.MemberDeposit;
import com.mall.web.mall.member.dao.WebMemberDao;
import com.mall.web.mall.member.dao.WebMemberDepositDao;
import com.mall.web.mall.member.dao.WebMemberPayRecordDao;
import com.mall.web.mall.member.vo.MemberPayRecordVo;
import com.mall.web.mall.member.vo.WebMemberDepositVo;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.pay.domain.PayRecord;

@Service("webMemberService")
public class WebMemberService {
	private static Logger logger = Logger.getLogger(WebMemberService.class);

	@Resource(name = "webMemberDao")
	private WebMemberDao webMemberDao;

	@Resource(name = "webMemberDepositDao")
	private WebMemberDepositDao webMemberDepositDao;

	@Resource(name = "webMemberPayRecordDao")
	private WebMemberPayRecordDao webMemberPayRecordDao;

	@Resource(name = "memberDistService")
	private MemberDistService memberDistService;// 多级会员关系处理
	@Autowired
	protected WebMemberDepositService webMemberDepositService;// 會員賬戶信息

	/**
	 * 根据用户id获取用户头像
	 * 
	 * @param userId
	 *            用户id
	 * @return 会员信息
	 */
	@Transactional(readOnly = true)
	public String getUserPortrait(int userId) {
		Member member = webMemberDao.getUserInfo(userId);
		return WebMemberVo.bean2Vo(member).getPortrait();
	}

	/**
	 * 根据用户名称获取会员信息 caokw
	 * 
	 * @param userName
	 *            用户名称
	 * @return 会员信息
	 */
	@Transactional(readOnly = true)
	public WebMemberVo getUserInfo(int userId) {
		Member member = webMemberDao.getUserInfo(userId);
		return WebMemberVo.bean2Vo(member);
	}

	/**
	 * 根据用户名称获取会员信息 caokw
	 * 
	 * @param userName
	 *            用户名称
	 * @return 会员信息
	 */
	@Transactional(readOnly = true)
	public WebMemberVo getUserInfo(String userName) {
		Member member = webMemberDao.getUserInfo(userName);
		if (member == null)
			return null;
		return WebMemberVo.bean2Vo(member);
	}

	/**
	 * 登录处理
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public WebMemberVo login(WebMemberVo memberVo) throws Exception {
		Member member = webMemberDao.getUserInfo(memberVo.getUserName());
		if (member == null)
			return null;
		// 未激活，不能登陸
		if (MallEnum.ActivatedStatus.UnActivated.getIndex().equalsIgnoreCase(member.getIsActivated())) {
			return null;
		}
		if (CryptoTools.getInstance().encode2HexStrDelimi(memberVo.getPassword(), member.getSalt())
				.equalsIgnoreCase(member.getPassword())) {
			return WebMemberVo.bean2Vo(member);
		}
		return null;
	}

	/**
	 * 注册处理(邮件注册)
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Member regist4Em(WebMemberVo memberVo) throws Exception {
		if (validRegData(memberVo) == false)
			return null;
		Member member = webMemberDao.getUserInfo(memberVo.getUserName());
		if (member != null) {
			if (MallEnum.ActivatedStatus.Activated.getIndex().equalsIgnoreCase(member.getIsActivated())) {
				return null;
			}
			webMemberDao.removeUserById(member.getUserId());// 删除原来未激活数据
		}
		member = new Member();
		BeanUtils.copyProperties(memberVo, member);

		// 用于密码加密处理的盐值
		String salt = Long.toHexString(System.currentTimeMillis());
		salt = salt.substring(salt.length() - 4).toLowerCase();
		String passwd = CryptoTools.getInstance().encode2HexStrDelimi(memberVo.getPassword(), salt);
		member.setSalt(salt);
		member.setIsActivated(MallEnum.ActivatedStatus.UnActivated.getIndex());// 未激活
		member.setPassword(passwd);
		member.setRegTime(new Date());
		member.setUgrade(MallEnum.MemberUgrade.TIEPAI.getIndex());
		webMemberDao.save(member);
		return member;
	}

	/**
	 * 注册处理(短信注册)
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Member regist4Sm(WebMemberVo memberVo) throws Exception {
		if (validRegData(memberVo) == false)
			return null;
		Member member = webMemberDao.getUserInfo(memberVo.getUserName());
		if (member != null)
			return null;
		member = new Member();
		BeanUtils.copyProperties(memberVo, member);

		// 用于密码加密处理的盐值
		String salt = Long.toHexString(System.currentTimeMillis());
		salt = salt.substring(salt.length() - 4).toLowerCase();
		String passwd = CryptoTools.getInstance().encode2HexStrDelimi(memberVo.getPassword(), salt);
		member.setSalt(salt);
		member.setIsActivated(MallEnum.ActivatedStatus.Activated.getIndex());// 激活
		member.setPassword(passwd);
		member.setRegTime(new Date());
		member.setUgrade(MallEnum.MemberUgrade.TIEPAI.getIndex());
		webMemberDao.save(member);
		return member;
	}

	/**
	 * 注册处理(数据有效性判断和配套处理)
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean validRegData(WebMemberVo memberVo) {
		// 1、判断用户名是否存在（邮件或者手机号是否同时为空）
		if (BaseUtil.isEmpty(memberVo.getEmail()) && BaseUtil.isEmpty(memberVo.getPhoneMob())) {
			return false;
		}
		// 赋值用户名
		if (!BaseUtil.isEmpty(memberVo.getEmail()))
			memberVo.setUserName(memberVo.getEmail());
		if (!BaseUtil.isEmpty(memberVo.getPhoneMob()))
			memberVo.setUserName(memberVo.getPhoneMob());
		// 2、判断密码是否为空
		if (BaseUtil.isEmpty(memberVo.getPassword())) {
			return false;
		}
		// 3、判断用户姓名是否为空
		if (BaseUtil.isEmpty(memberVo.getRealName())) {
			return false;
		}
		// 4、判断身份是否为空或者无效
		if (BaseUtil.isEmpty(memberVo.getIdCard()) || IdentityUtil.checkIDCard(memberVo.getIdCard()) == false) {
			return false;
		}
		String birthday = IdentityUtil.getIDDate(memberVo.getIdCard(),
				memberVo.getIdCard().length() == 18 ? true : false);
		// 生日无效
		if (IdentityUtil.checkDate(birthday) == false)
			return false;
		memberVo.setBirthday(birthday);
		// 性別
		String gender = IdentityUtil.getGender(memberVo.getIdCard());
		if (!BaseUtil.isEmpty(gender) && gender.equalsIgnoreCase("男")) {
			memberVo.setGender(0);
		} else if (!BaseUtil.isEmpty(gender) && gender.equalsIgnoreCase("女")) {
			memberVo.setGender(1);
		}

		// 防沉迷处理
		Date bd = DateUtil.stringToDate(birthday, CommonConstant.DATE_SHORT_SIMPLE_FORMAT);
		// 小于18岁(纳入放沉迷)
		if (DateUtil.getDays(DateUtil.getGMTDate(), bd) < 365 * 18) {
			memberVo.setIsHooked(MallEnum.HookedStatus.Hooked.getIndex());
		} // 大于18岁(不纳入放沉迷)
		else {
			memberVo.setIsHooked(MallEnum.HookedStatus.UnHooked.getIndex());
		}

		// 自动生成用户guid
		memberVo.setUguid("{" + (new RandomGUID()).toString() + "}");
		return true;
	}

	/**
	 * 注册处理(发送邮件)
	 * 
	 * 更新激活码 发送邮件
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public void sendRegEmail(Member member, String baseUrl) throws Exception {
		// 邮件激活码处理
		String activateCode = Long.toHexString(System.currentTimeMillis() + System.currentTimeMillis());
		activateCode = activateCode.substring(activateCode.length() - 6).toLowerCase();
		member.setActivateCode(activateCode);
		// 邮件激活链接处理
		int userId = member.getUserId();
		long validTime = System.currentTimeMillis() + 1 * 60 * 60 * 1000;// 有效期1小时
		String contextId = java.net.URLEncoder
				.encode("MLT" + CryptoTools.getInstance().encode2Base64(userId + "v" + validTime) + "DNE");
		String activation = "member/confirmEmail?contextId=" + contextId + "&checkCode=" + activateCode;
		member.setActivation(activation);
		webMemberDao.update(member);

		// 发邮件
		Mail4Reg mail = new Mail4Reg();
		mail.setReceiver(member.getEmail());
		if (baseUrl.endsWith("/")) {
			mail.setMessage4Reg(baseUrl + member.getActivation());
		} else {
			mail.setMessage4Reg(baseUrl + "/" + member.getActivation());
		}
		Mail4RegUtil.send(mail);
	}

	/**
	 * 注册处理(获取id)
	 * 
	 * 返回-1，表明無效(數據無效、時間無效)
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Integer getRegValidId(String contextId) {
		try {
			if (BaseUtil.isEmpty(contextId) || contextId.length() <= 7)
				return 0;
			// contextId=java.net.URLDecoder.decode(contextId);
			contextId = contextId.substring(3, contextId.length() - 3);
			contextId = CryptoTools.getInstance().decodeBase64(contextId);
			long validTime = Long.parseLong(contextId.substring(contextId.indexOf("v") + 1));
			// 大于有效时间
			if (validTime < System.currentTimeMillis())
				return 0;
			int userId = Integer.parseInt(contextId.substring(0, contextId.indexOf("v")));
			return userId;
		} catch (Exception e) {
			logger.warn(e);
			return -1;
		}
	}

	/**
	 * 解码有效推广码
	 * 
	 * 返回-1，表明無效(數據無效)
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static Integer decodeSpread(String code) {
		try {
			if (BaseUtil.isEmpty(code) || code.length() <= 7)
				return 0;
			code = code.substring(3, code.length() - 3);
			code = CryptoTools.getInstance().decodeBase64(code);
			int userId = Integer.parseInt(code);
			return userId;
		} catch (Exception e) {
			logger.warn(e);
			return -1;
		}
	}

	/**
	 * 编码推广码
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static String encodeSpread(Integer userId) {
		try {
			return java.net.URLEncoder
					.encode("MLT" + CryptoTools.getInstance().encode2Base64(Integer.toString(userId)) + "DNE");
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			return null;
		}
	}

	/**
	 * 注册处理(激活处理)
	 * 
	 * 邮件有效激活处理
	 * 
	 * @param userName
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@Transactional
	public boolean activateRegEm(Integer userid, String code) throws Exception {
		if (userid <= 0 || BaseUtil.isEmpty(code))
			return false;
		Member member = webMemberDao.get(userid);
		if (member == null || !code.equalsIgnoreCase(member.getActivateCode())
				|| MallEnum.ActivatedStatus.Activated.getIndex().equalsIgnoreCase(member.getIsActivated()))
			return false;
		member.setIsActivated(MallEnum.ActivatedStatus.Activated.getIndex());// 激活
		webMemberDao.update(member);

		// 建立会员关系（多级推广），邮件方式，放到邮件激活时
		if (BaseUtil.isNotEmpty(member.getPhoneTel()) && BaseUtil.isDigit(member.getPhoneTel())
				&& BaseUtil.isNotEmpty(webMemberDao.get(Integer.parseInt(member.getPhoneTel())))) {
			memberDistService.doMemberDist(member.getUserId(), Integer.parseInt(member.getPhoneTel()), null, null);
		}
		// 对新用户进行开户
		webMemberDepositService.openNewAccount(member.getUserId(), member.getPhoneMob());
		return true;
	}

	/**
	 * 修改密码
	 * 
	 * @param memberVo
	 *            用戶登陸信息
	 * @param oldPw
	 *            原密碼
	 * @param newPw
	 *            新密碼
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean updatePw(WebMemberVo memberVo, String oldPw, String newPw) {
		if (BaseUtil.isEmpty(memberVo) || BaseUtil.isEmpty(oldPw) || BaseUtil.isEmpty(newPw))
			return false;
		try {
			Member member = webMemberDao.get(memberVo.getUserId());
			if (BaseUtil.isEmpty(member))
				return false;

			String salt = member.getSalt();
			String oldPwS = CryptoTools.getInstance().encode2HexStrDelimi(oldPw, salt);
			if (!oldPwS.equals(member.getPassword()))
				return false;
			String passwd = CryptoTools.getInstance().encode2HexStrDelimi(newPw, salt);
			member.setPassword(passwd);
			webMemberDao.update(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			return false;
		}
	}
	
	
	/**
	 * 更新用户密码
	 * @author yichen
	 * @param userame 当前用户名
	 * @param newPw  新密码
	 * @return
	 */
	@Transactional
	public boolean updatePw(WebMemberVo user, String newPw) {
		try {
			if(BaseUtil.isEmpty(newPw))
				return false;
			Member member = webMemberDao.getUserInfo(user.getUserId());
			if (BaseUtil.isEmpty(member))
				return false;
			//加密加盐操作
			String salt = member.getSalt();
			String passwd = CryptoTools.getInstance().encode2HexStrDelimi(newPw, salt);
			member.setPassword(passwd);
			webMemberDao.update(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			return false;
		}
	}
	

	/**
	 * 根据用户名称获取会员信息 caokw
	 * 
	 * @param userName
	 *            用户名称
	 * @return 会员信息
	 */
	@Transactional(readOnly = true)
	public Member getMember(String userName) {
		return webMemberDao.getUserInfo(userName);
	}

	/**
	 * 更新手机号码
	 * 
	 * @param userId
	 *            用户id
	 * @param phone
	 *            手机号码
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean updatePhone(int userId, String phone) throws FrameworkException {
		return webMemberDao.updatePhone(userId, phone);
	}
	/**
	 * 更新邮箱号
	 * 
	 * @param userId
	 *            用户id
	 * @param email
	 *            邮箱号
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean updateEmail(int userId, String email) throws FrameworkException {
		return webMemberDao.updateEmail(userId, email);
	}

	/**
	 * 更新`邮箱是否激活`标识。
	 * 
	 * @author sol
	 * @param userId
	 *            用户ID。
	 * @param isEmailActivated
	 *            邮箱是否激活。可选值为0或1。
	 * @return 操作是否成功
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean updateIsEmailActivated(int userId, String isEmailActivated) throws FrameworkException {
		return webMemberDao.updateIsEmailActivated(userId, isEmailActivated);
	}

	/**
	 * 更新`邮箱是否激活`标识。
	 * 
	 * @author sol
	 * @param userId
	 *            用户ID。
	 * @param isEmailActivated
	 *            `邮箱是否激活`标识。
	 * @return 操作是否成功。
	 * @throws FrameworkException
	 */
	public boolean updateLogin(int userId, String ip) throws FrameworkException {
		Member member = webMemberDao.get(userId);
		if (member == null) {
			return false;
		}
		member.setLastIp(ip);
		member.setLastLogin(DateUtil.getGMTDate());
		member.setLogins(member.getLogins() + 1);
		webMemberDao.update(member);
		return true;
	}

	/**
	 * 获取登录密码。（MD5加密）
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 */
	@Transactional(readOnly = true)
	public String getPassword(int userId) {
		return webMemberDao.getPassword(userId);
	}

	/**
	 * 更新登录密码。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @param paymentPassword
	 *            新的支付密码（MD5加密）
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean updatePassword(int userId, String password) throws FrameworkException {
		return webMemberDao.updatePassword(userId, password);
	}

	/**
	 * 获取支付密码。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @return 用户支付密码
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public String getPaymentPassword(int userId) throws FrameworkException {
		return webMemberDepositDao.getPaymentPassword(userId);
	}

	@Transactional(readOnly = true)
	public String getPaymentSalt(int userId) throws FrameworkException {
		return webMemberDepositDao.getPaymentSalt(userId);
	}

	/**
	 * 更新支付密码。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @param paymentPassword
	 *            新的支付密码
	 * @throws FrameworkException
	 */
	@Transactional
	public boolean updatePaymentPassword2(int userId, String paymentPassword, String salt) throws FrameworkException {
		return webMemberDepositDao.updatePaymentPassword2(userId, paymentPassword, salt);
	}

	public boolean updatePaymentPassword(int userId, String paymentPassword) throws FrameworkException {
		return webMemberDepositDao.updatePaymentPassword(userId, paymentPassword);
	}

	/*******************************************************************************
	 * 获取余额。
	 * 
	 * @author sol
	 * @param userId
	 *            用户编号
	 * @return 用户余额
	 * @throws FrameworkException
	 */
	public double getBlance(int userId) throws FrameworkException {
		return webMemberDepositDao.showbalance(userId);
	}

	/*******************************************************************************
	 * 获取会员账户详细信息！！！。
	 * 
	 * @param userId
	 *            用户编号
	 * @return 会员支付账户实例
	 * @throws FrameworkException
	 */
	@Transactional
	public WebMemberDepositVo getMemberDeposit(int userId) throws FrameworkException {
		MemberDeposit memberDeposit = webMemberDepositDao.get(userId);
		WebMemberDepositVo res = WebMemberDepositVo.bean2Vo(memberDeposit); // 封装
		return res;
	}

	/**
	 * @Description(功能描述) : 返回帐户信息 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月29日 下午3:01:49
	 */
	public MemberDeposit findMemberDeposit(int userId) throws FrameworkException {
		return webMemberDepositDao.get(userId);
	}

	/**
	 * @Description(功能描述) : 保存或更新用户帐户信息 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月29日 下午2:55:03
	 */
	// @Transactional
	// public void saveOrUpdayeDeposit(MobMemberVo user, BigDecimal faceValue)
	// throws FrameworkException {
	// MemberDeposit deposit = webMemberDepositDao.get(user.getUserId());
	// if (BaseUtil.isNotEmpty(deposit)) { // 更新余额
	// // deposit.setBalance(deposit.getBalance()+faceValue.doubleValue());
	// // webMemberDepositDao.update(deposit);
	// webMemberDepositDao.plusBalance(user.getUserId(),
	// faceValue.doubleValue());
	// } else {
	// deposit = new MemberDeposit();
	// deposit.setUserId(user.getUserId());
	// deposit.setBalance(faceValue.doubleValue());
	// deposit.setInitialBalance(0);
	// deposit.setLastInitialBalance(0);
	// deposit.setLastLastInitialBalance(0);
	// deposit.setOperateTime(DateUtil.getGMTDate());
	// deposit.setPhone(user.getPhoneMob());
	// deposit.setAccumulatedPoint(0);
	// webMemberDepositDao.save(deposit);
	// }
	// }

	/**
	 * @Description(功能描述) : 保存或更新用户帐户信息 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月29日 下午2:55:03
	 */
	// @Transactional
	// public void saveOrUpdayeDeposit(int userId, BigDecimal faceValue) throws
	// FrameworkException {
	// MemberDeposit deposit = webMemberDepositDao.get(userId);
	// if (BaseUtil.isNotEmpty(deposit)) { // 更新余额
	// webMemberDepositDao.plusBalance(userId, faceValue.doubleValue());
	// } else {
	// Member member = webMemberDao.get(userId);
	// deposit = new MemberDeposit();
	// deposit.setUserId(userId);
	// deposit.setBalance(faceValue.doubleValue());
	// deposit.setInitialBalance(0);
	// deposit.setLastInitialBalance(0);
	// deposit.setLastLastInitialBalance(0);
	// deposit.setOperateTime(DateUtil.getGMTDate());
	// deposit.setPhone(member.getPhoneMob());
	// deposit.setAccumulatedPoint(0);
	// webMemberDepositDao.save(deposit);
	// }
	// }

	/**
	 * @Description(功能描述) : 开通用户账户（为新用户开通资金账户） @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月29日 下午2:55:03
	 */
	@Transactional
	public void openNewAccount(int userid, String mobile) {
		try {
			MemberDeposit deposit = webMemberDepositDao.get(userid);
			if (BaseUtil.isNotEmpty(deposit)) {
				return;
			} else {
				deposit = new MemberDeposit();
				deposit.setUserId(userid);
				deposit.setBalance(0);
				deposit.setInitialBalance(0);
				deposit.setLastInitialBalance(0);
				deposit.setLastLastInitialBalance(0);
				deposit.setOperateTime(DateUtil.getGMTDate());
				deposit.setPhone(mobile);
				deposit.setAccumulatedPoint(0);
				webMemberDepositDao.save(deposit);
			}
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
		}
	}

	/*******************************************************************************
	 * 获取资金变动信息
	 * 
	 * @param PageBean
	 *            不了解，
	 * @return 会员资金变动信息
	 * @throws FrameworkException
	 */

	// 一：含参数
	// @Transactional(readOnly = true)
	// public List<MemberPayRecordVo> showMemberPayRecord(PageBean page) throws
	// FrameworkException {
	// List<MemberPayRecordVo> list = new ArrayList<MemberPayRecordVo>();
	// List<YzmPayRecord> beans =
	// WebMemberPayRecordDao.showMemberPayRecord((page.getCurPage()>0?page.getCurPage()-1:page.getCurPage())*page.getPageSize(),
	// page.getPageSize());
	//
	// if(beans != null) {
	// for(YzmPayRecord bean: beans) {
	// MemberPayRecordVo vo = MemberPayRecordVo.bean2Vo(bean);
	// list.add(vo);
	// }
	// }
	// return list;
	// }

	// 二、：不含参数
	@Transactional(readOnly = true)
	public List<MemberPayRecordVo> showMemberPayRecord() throws FrameworkException {
		List<MemberPayRecordVo> list = new ArrayList<MemberPayRecordVo>();
		List<PayRecord> beans = webMemberPayRecordDao
				.showMemberPayRecord(DateUtils.addDays(DateUtil.getGMTDate(), -90)); // DateUtils.addDays
		// 结果是个？

		if (beans != null) {
			for (PayRecord bean : beans) {
				MemberPayRecordVo vo = MemberPayRecordVo.bean2Vo(bean);
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * @Description(功能描述) : 保存用户 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月16日 下午5:48:24
	 * @param member
	 */
	@Transactional
	public int saveMember(Member member) {
		try {
			member.setRegTime(DateUtil.getGMTDate());
			return (Integer) webMemberDao.save(member);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @Description(功能描述) : 保存用户 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月16日 下午5:48:24
	 * @param member
	 */
	@Transactional
	public void updateMember(Member member) {
		try {
			webMemberDao.update(member);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description(功能描述) : 根据用户名和密码查询用户 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月16日 下午5:51:54
	 * @param userName
	 *            : 用户名
	 * @param password
	 *            : 密码
	 * @return
	 */
	public Member getUserInfo(String userName, String password) {
		return webMemberDao.getUserInfo(userName, password);
	}

	/**
	 * @Description(功能描述) : 根据用户id查询用户 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:15:44
	 * @param userId
	 *            : 用户id
	 */
	public Member getMember(int userId) {
		try {
			return webMemberDao.get(userId);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description(功能描述) : 升级用户级别 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	@Transactional
	public void plusUgrade() {
		// 钻石
		webMemberDao.plusUgrade(UgradeValue.ZHUANSHI.getIndex(), UgradeValue.ZHUANSHI.getName().intValue());
		// 金牌
		webMemberDao.plusUgrade(UgradeValue.JINGPAI.getIndex(), UgradeValue.JINGPAI.getName().intValue());
		// 银牌
		webMemberDao.plusUgrade(UgradeValue.YINGPAI.getIndex(), UgradeValue.YINGPAI.getName().intValue());
		// 铜牌
		webMemberDao.plusUgrade(UgradeValue.TONGPAI.getIndex(), UgradeValue.TONGPAI.getName().intValue());
	}

	

   public static void main(String[] args) {
	   System.out.println(WebMemberService.encodeSpread(3253773));
   }

	
	@Transactional(readOnly = true)
	public List<MemberPayRecordVo> getCommission(Integer  userId,String data){
		return webMemberPayRecordDao.getCommission( userId, data);
	}
   
   
/*public List<PayRecord> getCommission(  int userId, String data) {
	// TODO Auto-generated method stub
	return null;
}*/
}