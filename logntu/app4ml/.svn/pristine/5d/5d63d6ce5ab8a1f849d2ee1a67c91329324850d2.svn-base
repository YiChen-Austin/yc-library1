package com.mall.web.mall.member.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.web.mall.member.service.WebMemberService;
import com.mall.web.mall.member.vo.MemberPayRecordVo;
import com.mall.web.mall.member.vo.WebMemberDepositVo;
import com.mall.web.mall.member.vo.WebMemberVo;

/**
 * @Description(功能描述) : 
 * @author(作者) :chenhongxu
 * @date (开发日期) : 2015年10月9日 下午5:12:30
 */


@Controller
@RequestMapping("/member/balance*")
public class MemberDepositController {
		
	@Resource(name = "webMemberService")
	private WebMemberService webMemberService;
	
	
	/**
	 * 获取用户支付账户变量并追加到视图。
	 * @param mav	目标视图
	 * @throws FrameworkException
	 */
	@RequestMapping("/myBalance")
	private ModelAndView showMemberDeposit() throws FrameworkException
	{
		ModelAndView mav = new ModelAndView();
		WebMemberVo member =null;// webCommonService.getCurrentMember();		//获取当前登陆的用户
		WebMemberDepositVo memberDeposit = webMemberService.getMemberDeposit(member.getUserId()); //传人用户ID，获得账户信息	
		mav.addObject("memberDeposit", memberDeposit);
		List<MemberPayRecordVo> list = webMemberService.showMemberPayRecord();		//资金变动信息
		mav.addObject("MemberPayRecord", list);
		mav.setViewName("mall/member/balance/myBalance");		
		return mav;			 
	}
}
