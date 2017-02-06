package com.mall.web.mall.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.common.utils.CkSessionUtils;
import com.mall.web.mall.common.utils.MallEnum.ServiceCodeType;
import com.mall.web.mall.distribut.vo.MemberDistRelationVo;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.service.WebMemberSignService;
import com.mall.web.mall.member.vo.MemberPayRecordVo;
import com.mall.web.mall.member.vo.WebMemberSignVo;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.mall.order.service.OrderService;
import com.mall.web.pay.domain.PayRecord;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static Logger logger = Logger.getLogger(MemberController.class);

	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;

	// 订单
	@Resource(name = "orderService")
	private OrderService orderservice;
	

	/**
	 * 个人资料（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/base")
	@MemberAuth(verifyLogin = true)
	public ModelAndView baseinfo(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/base");
		return mav;
	}

	/**
	 * 个人资料（修改密码）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/passwd")
	@MemberAuth(verifyLogin = true)
	public ModelAndView passwd(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/passwd");
		return mav;
	}

	/**
	 * 客服服务（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/client")
	@MemberAuth(verifyLogin = true)
	public ModelAndView client(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/service/client");
		return mav;
	}

	/**
	 * 我要反馈（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/feedback")
	@MemberAuth(verifyLogin = true)
	public ModelAndView feedback(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/service/feedback");
		return mav;
	}

	/**
	 * 充值记录（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/recharge")
	@MemberAuth(verifyLogin = true)
	public ModelAndView recharge(HttpServletRequest request, HttpServletResponse response, String returnUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/deposit/recharge");
		return mav;
	}
	/**
	 * 防沉迷（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addiction")
	@MemberAuth(verifyLogin = true)
	public ModelAndView addiction(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/info/addiction");
		return mav;
	}
	/**
	 * 推广链接（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("href")
	@MemberAuth(verifyLogin = true)
	public ModelAndView href(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/spread/href");
		return mav;
	}
	/**
	 * 绑定银行卡（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("bank")
	@MemberAuth(verifyLogin = true)
	public ModelAndView bank(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/spread/bank");
		return mav;
	}
	/**
	 * 推广收益（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="payrecordCommission",method=RequestMethod.GET)
	@MemberAuth(verifyLogin = true)
	public ModelAndView payrecordCommissionView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/spread/payrecordCommission");
		return mav;
	}
	
	/**
	 * 推广收益
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("payrecordCommission")
	@MemberAuth(verifyLogin = true)
	public @ResponseBody String payrecordCommission(HttpServletRequest request, HttpServletResponse response,String data){
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		List<MemberPayRecordVo> list = webMemberService.getCommission(user.getUserId(), data);
		return JSONArray.fromObject(list).toString();
	}
	
	
	/*@ResponseBody
	@RequestMapping(value="payrecordCommission",method=RequestMethod.POST)
	@MemberAuth(verifyLogin = true)
	public String payrecordCommission(HttpServletRequest request, HttpServletResponse response,String data){
		Map<String,List<PayRecord>> result = new HashMap<String,List<PayRecord>>();
		WebMemberVo user = CkSessionUtils.getUser(request.getSession());
		List<MemberPayRecordVo> records = webMemberService.getCommission(user.getUserId(), data);
		
		return JSONArray.fromObject(records).toString();
		return  result;
	}*/
	
	/**
	 * 会员推广（导航）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("extension")
	@MemberAuth(verifyLogin = true)
	public ModelAndView extension(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("person/spread/extension");
		return mav;
	}
	/*************************************************/
	@Resource(name = "webMemberSignService")
	private WebMemberSignService webMemberSignService;

	/**
	 * 签到（送影响值）  
	 */
	@RequestMapping("signin")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Map<String, Object> signin(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo user = CkSessionUtils.getUser(request);
		if (BaseUtil.isNotEmpty(user)) {
			int maxSigns = 7;
			try {
				result.put(ServiceCodeType.ServiceCode,
						ServiceCodeType.Success.getIndex());
				WebMemberSignVo vo = webMemberSignService.memberSign(
						user.getUserId(), maxSigns);
				// 已经签到
//				if (vo == null) {
//					result.put("sign", vo);
//					return result;
//				}
//				int ugrade = 0;
//				// 连续超过7天，送影响值70
//				if (vo.getContinuous() >= maxSigns) {
//					ugrade = 70 + 10;
//				} else if (vo.getContinuous() > 0) {
//					ugrade = 10;
//				}
				result.put("sign",vo);
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
	@MemberAuth(verifyLogin = true)
	public Map<String, Object> getSign(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo user = CkSessionUtils.getUser(request);
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
	/***********************************/
	/**
	 * 添加描述图片(头像)
	 * 
	 * @param imageFile
	 *            图片文件
	 * @param parentId
	 *            父节点ID
	 * @param request
	 *            请求
	 * @return
	 */
	@RequestMapping("/uUportrait")
	@ResponseBody
	@MemberAuth(verifyLogin = true)
	public Map<String, Object> uUportrait(@RequestParam MultipartFile upfile,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		WebMemberVo vo = CkSessionUtils.getUser(request);
		// 未登录
		if (vo == null) {
			result.put(ServiceCodeType.ServiceCode, "9");// 未登陆
			return result;
		} else {
			Member userInfo = webMemberService.getMember(vo.getUserId());
			if (userInfo == null) {
				result.put(ServiceCodeType.ServiceCode, "8"); // 数据错误
				return result;
			}			
			try {
				String path = request.getServletContext().getRealPath("/upload");
				String lpath = "/upload/"+saveFile(path, Integer.toString(vo.getUserId()), upfile);
				userInfo.setPortrait(lpath);
				webMemberService.updateMember(userInfo);
				result.put(ServiceCodeType.ServiceCode, ServiceCodeType.Success.getIndex()); // 成功
				result.put("url",lpath);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn(e);
				result.put("serviceCode", "8");
				return result;
			}
		}
	}
	
	/**
	 * 
	 * 保存上传图片
	 * 
	 * @param message
	 * 
	 */
	public String saveFile(String pre, String name, MultipartFile file)
			throws IOException {
		if (BaseUtil.isEmpty(file)||file.isEmpty()) {
			return null;
		}
		String fix = file.getOriginalFilename().indexOf(".") >= 0 ? file
				.getOriginalFilename().substring(
						file.getOriginalFilename().indexOf(".")) : "";
		String rand = name + new Date().getTime();
		File f = new File(pre);
		if (!f.exists()) {
			f.mkdirs();
		}
		String path = pre + "/" + rand + fix;
		File uploadFile = new File(path);
		FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
		return rand + fix;
	}
}