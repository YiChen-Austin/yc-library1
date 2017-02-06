package com.mall.web.admin.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.common.util.VerificationUtil;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.SessionUtils;

/**
 * 
 * @功能：产生验证码登录界面
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 * 
 */
@Controller
@RequestMapping("/admin/")
public class VerificationAction {
	/**
	 * 验证码生成接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("vcode")
	@Auth(verifyLogin = false, verifyURL = false)
	public void verification(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String validateCode = VerificationUtil.getInstance().getCheckCodeImage(
				4);
		SessionUtils.setValidateCode(request, validateCode);
		VerificationUtil.getInstance().writeCheckCodeImage(validateCode,
				response.getOutputStream());

	}
}
