package com.mall.www4lt.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.domain.WebArticle;
import com.mall.web.mobile.common.util.UserUtil;
import com.mall.web.mobile.member.vo.MobMemberVo;
import com.mall.www4lt.article.service.ArticleService;
import com.mall.www4lt.article.vo.ArticleBean;

/**
 * 
 *官网导航 
 */
@Controller
public class Www4ltNavController extends BaseAction {
	private static Logger logger = Logger.getLogger(Www4ltNavController.class);
	
	@Autowired
	private ArticleService articleService;
	

	/**
	 * 网站首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	@MemberAuth(verifyLogin = false)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/main");
		return mav;
	}
	/**
	 * 活动中心列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws FrameworkException 
	 */
	@RequestMapping("activity_list/{categoryId}")
	@MemberAuth(verifyLogin = false)
	public ModelAndView activity_list(@PathVariable String categoryId,PageBean pageBean,HttpServletRequest request, HttpServletResponse response, String service)  throws FrameworkException{
		ModelAndView mav = new ModelAndView();
		List<WebArticle> articles = articleService.findAritcles(categoryId, 1,9);
		List<ArticleBean> articleList = pojos2Beans(articles);
		int total = articleService.getArticleRows(categoryId);
		pageBean.init(total);
		mav.addObject("pageBean", pageBean);
		mav.addObject("articleList", articleList);
		mav.setViewName("www4lt/activity/list");
		return mav;
	}
	
	/**
	 * 活动中心内容
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("activity_con")
	@MemberAuth(verifyLogin = false)
	public ModelAndView activity_con(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/activity/content");
		
		return mav;
	}
	/**
	 * 公告列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws FrameworkException 
	 */
	@RequestMapping("announ_list/{categoryId}")
	@MemberAuth(verifyLogin = false)
	public ModelAndView announ_list(@PathVariable String categoryId,PageBean pageBean,HttpServletRequest request, HttpServletResponse response, String service) throws FrameworkException {
		ModelAndView mav = new ModelAndView();
		List<WebArticle> articles = articleService.findAritcles(categoryId, 1,10);
		List<ArticleBean> articleList = pojos2Beans(articles);
		int total = articleService.getArticleRows(categoryId);
		pageBean.init(total);
		mav.addObject("articleList", articleList);
		mav.addObject("pageBean", pageBean);
		mav.setViewName("www4lt/announ/list");
		return mav;
	}
	
	private List<ArticleBean> pojos2Beans(List<WebArticle> articles) {
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		for (WebArticle article : articles) {
			ArticleBean bean = pojos2Bean(article);
			beans.add(bean);
		}
		return beans;
	}
	
	private ArticleBean pojos2Bean(WebArticle article) {
		ArticleBean bean = new ArticleBean();
		bean.setId(article.getId());
		bean.setArtAbstract(article.getArtAbstract());
		bean.setCategoryId(article.getCategory() == null ? "" : article.getCategory().getId());
		bean.setCategoryName(article.getCategory() == null ? "" : article.getCategory().getName());
		bean.setContent(article.getContent());
		bean.setImgUrl(article.getImgUrl());
		bean.setPublishAuthorId(article.getPublishAuthorId());
		bean.setPublishAuthorName("龙途");
		bean.setPublishTime(article.getPublishTime() == null ? "" : DateUtil.dateToString(article.getPublishTime(),
				CommonConstant.DATE_SHORT_FORMAT));
		bean.setReadCount(article.getReadCount());
		bean.setSupportCount(article.getSupportCount());
		bean.setTag(article.getTag());
		bean.setTitle(article.getTitle());
		bean.setUnSupportCount(article.getUnSupportCount());
		return bean;
	}

	/**
	 * 公告列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("announ_con")
	@MemberAuth(verifyLogin = false)
	public ModelAndView announ_con(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/announ/content");
		return mav;
	}
	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("download")
	@MemberAuth(verifyLogin = false)
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/download/download");
		return mav;
	}
	/**
	 * 游戏资料
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("data")
	@MemberAuth(verifyLogin = false)
	public ModelAndView data(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/data");
		return mav;
	}
	/**
	 * 游戏操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("game_operation")
	@MemberAuth(verifyLogin = false)
	public ModelAndView game_operation(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/game_operation");
		return mav;
	}
	/**
	 * 新手指南
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("operation")
	@MemberAuth(verifyLogin = false)
	public ModelAndView operation(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/operation");
		return mav;
	}
	/**
	 * 防具
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("armor")
	@MemberAuth(verifyLogin = false)
	public ModelAndView armor(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/armor");
		return mav;
	}
	/**
	 * NPC
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("npc")
	@MemberAuth(verifyLogin = false)
	public ModelAndView npc(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/npc");
		return mav;
	}
	/**
	 * 武器
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("weapoin")
	@MemberAuth(verifyLogin = false)
	public ModelAndView weapoin(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/weapoin");
		return mav;
	}
	/**
	 * 饰品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("jewelry")
	@MemberAuth(verifyLogin = false)
	public ModelAndView jewelry(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/jewelry");
		return mav;
	}
	/**
	 * 怪物
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("monster")
	@MemberAuth(verifyLogin = false)
	public ModelAndView monster(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/monster");
		return mav;
	}
	/**
	 * Boss
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("boss")
	@MemberAuth(verifyLogin = false)
	public ModelAndView boss(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/boss");
		return mav;
	}
	
	/**
	 * 职业特点战士
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("occupation_zs")
	@MemberAuth(verifyLogin = false)
	public ModelAndView occupation_zs(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/occupation_zs");
		return mav;
	}
	/**
	 * 职业特点道士
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("occupation_ds")
	@MemberAuth(verifyLogin = false)
	public ModelAndView occupation_ds(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/occupation_ds");
		return mav;
	}
	/**
	 * 职业特点法师
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("occupation_fs")
	@MemberAuth(verifyLogin = false)
	public ModelAndView occupation_fs(HttpServletRequest request, HttpServletResponse response, String service) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www4lt/data/occupation_fs");
		return mav;
	}
	
}
