/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DESUtil;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.common.utils.SystemResourceUtil;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

/**
 * @功能说明：登录控制层
 * @作者： 赵欢欢，周红
 * @创建日期： 2010-5-7 @
 */
@Controller
@RequestMapping("/admin/")
public class LoginAction extends BaseAction {
	private static Logger logger = Logger.getLogger(LoginAction.class);
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 登录界面
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("index")
	@Auth(verifyLogin = false, verifyURL = false)
	public ModelAndView index() {
		return forword("login", null);
	}

	/**
	 * 普通方式登录接口
	 * 
	 * @return
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	@ResponseBody
	@Auth(verifyLogin = false, verifyURL = false)
	public Map<String, Object> login(HttpServletRequest request,
			HttpServletResponse response, SysMngUserLoginBean sysUserLoginBean)
			throws FrameworkException, Exception {
		return logins(request, response, sysUserLoginBean);
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("exit")
	@Auth(verifyLogin = false, verifyURL = false)
	public ModelAndView exitLogin(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException, Exception {
		logger.debug("LoginAction exitLogin()");
		// 登陆用户信息
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		// LogUtil.getInstance().logoutExecution();
		if (loginUser != null)
			SessionUtils.removeOnlineUser(request, loginUser.getUserName());
		SessionUtils.removeUser(request);
		return forword("login", null);
	}

	/**
	 * 登陆通用方法,为普通登陆和ssl登陆公用
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	private Map<String, Object> logins(HttpServletRequest request,
			HttpServletResponse response, SysMngUserLoginBean sysUserLoginBean)
			throws FrameworkException, Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		String verificationStatus = SystemResourceUtil.getInstance()
				.getResourceAsString("VERIFICATION_STATUS");
		if (!BaseUtil.isEmpty(verificationStatus)
				&& verificationStatus.equalsIgnoreCase("enabled")) {
			String verification = SessionUtils.getValidateCode(request);
			SessionUtils.removeValidateCode(request);

			if (BaseUtil.isEmpty(verification)) {
				// sendFailureMessage(response, "验证码过期,请再次登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "验证码过期,请再次登录!");
				return result;
			} else {
				if (BaseUtil.isEmpty(sysUserLoginBean.getVerification())) {
					// sendFailureMessage(response, "请输入验证码!");
					result.put(SUCCESS, false);
					result.put(MSG, "请输入验证码!");
					return result;
				} else if (!verification.equals(sysUserLoginBean
						.getVerification())) {
					// sendFailureMessage(response, "输入验证码不正确,请重新输入!");
					result.put(SUCCESS, false);
					result.put(MSG, "输入验证码不正确,请重新输入!");
					return result;
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", sysUserLoginBean.getUserName());
		params.put("password",
				DESUtil.getInstance().encrypt(sysUserLoginBean.getPassword()));
		// Map<String, SysUser> loginStatus =
		// this.sysUserService.login(sysUserLoginBean.getUserName(), DESUtil
		// .getInstance().encrypt(sysUserLoginBean.getPassword()));
		Map<String, SysUser> loginStatus = this.sysUserService.login(params);
		if (!BaseUtil.isEmpty(loginStatus)) {
			String status = loginStatus.keySet().iterator().next();
			SysUser sysUser = loginStatus.get(status);
			if (!sysUser.getDisabled()) {
				// sendFailureMessage(response, "该用户已禁用,不能登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "该用户已禁用,不能登录!");
				return result;
			} else if ("startError".equalsIgnoreCase(status)) {
				// sendFailureMessage(response, "生效开始日期大于当前日期,不能登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "生效开始日期大于当前日期,不能登录!");
				return result;
			} else if ("endError".equalsIgnoreCase(status)) {
				// sendFailureMessage(response, "生效结束日期小于当前日期,不能登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "生效结束日期小于当前日期,不能登录!");
				return result;
			} else if ("betweenError".equalsIgnoreCase(status)) {
				// sendFailureMessage(response, "生效日期已失效,不能登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "生效日期已失效,不能登录!");
				return result;
			}

			BeanUtils.copyProperties(sysUser, sysUserLoginBean);
			sysUserLoginBean.setPassword(DESUtil.getInstance().decrypt(
					sysUserLoginBean.getPassword()));
			List<SysMenu> sysMenus = sysUserService.queryRightsMenus(sysUser
					.getId());
			if (sysMenus == null || sysMenus.size() == 0) {
				// sendFailureMessage(response, "该用户无任何权限,请授权后再登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "该用户无任何权限,请授权后再登录!");
				return result;
			}
			sysUserLoginBean.setFlag(sysUser.getFlag());
			sysUserLoginBean.setRealName(sysUser.getRealName());
			List<SysOrganization> sysOrgs = sysUser.getSysOrganizations();
			if (!BaseUtil.isEmpty(sysOrgs) && sysOrgs.size() > 0) {
				sysUserLoginBean.setOrganizationId(sysOrgs.get(0).getId());
				sysUserLoginBean.setOrganizationName(sysOrgs.get(0).getName());
			} else {
				// sendFailureMessage(response, "该用户无任何组织机构,请分配后再登录!");
				result.put(SUCCESS, false);
				result.put(MSG, "该用户无任何组织机构,请分配后再登录!");
				return result;
			}
			// 获取菜单
			sysUserLoginBean.setMenuList(sysMenus);
			// 登录人IP
			sysUserLoginBean.setIp(this.getIpAddr(request));
			// 将登录信息放入session
			SessionUtils.setUser(request, sysUserLoginBean);
			// LogUtil.getInstance().loginExecution();

			SessionUtils.setOnlineUser(request, sysUserLoginBean.getUserName(),
					request.getSession().getId());

			// sendSuccessMessage(response, "登录成功!");
			result.put(SUCCESS, true);
			result.put(MSG, "登录成功!");
			return result;
		} else {
			// sendFailureMessage(response, "用户名或密码错误!");
			result.put(SUCCESS, false);
			result.put(MSG, "用户名或密码错误!");
			return result;
		}
	}
}
