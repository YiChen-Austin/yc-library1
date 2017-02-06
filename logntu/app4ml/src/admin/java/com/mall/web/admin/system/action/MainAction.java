/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

/**
 * @功能说明：主框架控制层
 * @作者： 赵欢欢，周红
 * @创建日期： 2010-5-7 @
 */
@Controller
@RequestMapping("/admin/")
public class MainAction extends BaseAction {
	private static Logger logger = Logger.getLogger(MainAction.class);
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 登录界面
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@Auth(verifyLogin=true, verifyURL = false)
	@RequestMapping("main")
	public ModelAndView index(HttpServletRequest request)
			throws FrameworkException, Exception {
		Map<String, Object> context = new HashMap<String, Object>();

		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		context.put("loginUser", sysUserLoginBean);

		return forword("main", context);
	}
}
