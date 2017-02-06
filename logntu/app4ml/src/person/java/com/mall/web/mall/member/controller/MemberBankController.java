package com.mall.web.mall.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.util.BaseUtil;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.common.utils.CkSessionUtils;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.member.service.MemberBankCardService;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.MemberBankCardVo;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.mall.web.pay.service.PayDataService;
import com.mall.web.pay.vo.BankPayVo;

/**
 * 移动端会员控制层
 * 
 * @author ty
 * 
 */
@Controller
@RequestMapping("/member/*")
public class MemberBankController {
	private static Logger logger = Logger
			.getLogger(MemberBankController.class);

	@Resource(name = "memberBankCardService")
	private MemberBankCardService memberBankCardService;
	@Autowired
	protected WebMemberService webMemberService;// 会员
	@Autowired
	protected PayDataService payDataService;// 银行信息

	/**
	 * 绑定银行卡
	 */
	@RequestMapping("bindcard")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Map<String, Object> bindCard(HttpServletRequest request,
			MemberBankCardVo bank) {
		//TODO： 1、验证验证码
		Map<String, Object> result = new HashMap<String, Object>();
		String vcode = CkSessionUtils.getSmValidateCode(request);
		String code = request.getParameter("code");
		CkSessionUtils.removeSmValidateCode(request);
		if (BaseUtil.isEmpty(vcode) || BaseUtil.isEmpty(code) || !vcode.equalsIgnoreCase(code)) {
			result.put(MallEnum.ServiceCodeType.ServiceCode, MallEnum.ServiceCodeType.DataErr.getIndex());
			result.put("msg", "失败");
		}
		WebMemberVo user = CkSessionUtils.getUser(request);
		//2、绑定银行卡，保存用户银行卡信息
		if (BaseUtil.isNotEmpty(user)) {
			try {
				memberBankCardService.saveMemberBankCard(user.getUserId(),
						bank);
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.Success.getIndex());
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.DataErr.getIndex());
			}
		} else {
			result.put(ServiceCodeType.ServiceCode,
					ServiceCodeType.UnLogin.getIndex());
		}
		return result;
	}

	/**
	 * 获取绑定银行卡信息
	 */
	@RequestMapping("getCard")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Map<String, Object> getCard(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo user = CkSessionUtils.getUser(request);
		if (BaseUtil.isNotEmpty(user)) {
			try {
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.Success.getIndex());
				MemberBankCardVo vo = memberBankCardService
						.findMemberBankCard(user.getUserId());
				result.put("card", vo);
			} catch (Exception e) {
				logger.warn(e);
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.DataErr.getIndex());
			}
		} else {
			result.put(ServiceCodeType.ServiceCode,
					ServiceCodeType.UnLogin.getIndex());
		}
		return result;
	}
	
	/**
	 * 银行信息
	 */
	@RequestMapping("findBanks")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Object findBanks(HttpServletRequest request) {
		List<BankPayVo> panks=payDataService.findBankPays();
		return panks;
	}
}