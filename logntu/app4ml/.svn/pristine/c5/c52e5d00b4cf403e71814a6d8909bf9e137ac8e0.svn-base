package com.mall.web.mobile.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.util.BaseUtil;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.service.WebMemberSignService;
import com.mall.web.mall.member.vo.WebMemberSignVo;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.vo.MobMemberVo;

/**
 * 移动端会员控制层
 * 
 * @author ty
 * 
 */
@Controller
@RequestMapping("/mobile/member/*")
public class MobMemberSignController {
	private static Logger logger = Logger
			.getLogger(MobMemberSignController.class);

	@Resource(name = "webMemberSignService")
	private WebMemberSignService webMemberSignService;
	@Autowired
	protected WebMemberService webMemberService;// 会员

	/**
	 * 签到（送影响值）
	 */
	@RequestMapping("signin")
	@ResponseBody
	public Map<String, Object> signin(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo user = UserUtil.getInstatnce().getUser(request);
		if (BaseUtil.isNotEmpty(user)) {
			int maxSigns = 7;
			try {
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.Success.getIndex());
				WebMemberSignVo vo = webMemberSignService.memberSign(
						user.getUserId(), maxSigns);
				// 已经签到
				if (vo == null) {
					result.put("sign", 0);
					return result;
				}
				int ugrade = 0;
				// 连续超过7天，送影响值70
				if (vo.getContinuous() >= maxSigns) {
					ugrade = 70 + 10;
				} else if (vo.getContinuous() > 0) {
					ugrade = 10;
				}
				result.put("sign", user.getUgradeValue()+ugrade);
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
	 * 获取签到信息
	 */
	@RequestMapping("getSign")
	@ResponseBody
	public Map<String, Object> getSign(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		MobMemberVo user = UserUtil.getInstatnce().getUser(request);
		if (BaseUtil.isNotEmpty(user)) {
			try {
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.Success.getIndex());
				WebMemberSignVo vo = webMemberSignService.findMemberSign(user
						.getUserId());
				result.put("sign", vo);
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

}
