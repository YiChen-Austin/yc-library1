package com.mall.www4lt.article.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.category.service.BaseCategoryService;
import com.mall.web.admin.category.vo.BaseCategoryBean;
import com.mall.web.mall.common.annotation.MemberAuth;
import com.mall.web.mall.domain.WebArticle;
import com.mall.www4lt.article.service.ArticleService;
import com.mall.www4lt.article.vo.ArticleBean;

@Controller
@RequestMapping("/article/")
public class ArticleAction {

	private static Logger logger = Logger.getLogger(ArticleAction.class);
	@Autowired
	private BaseCategoryService baseCategoryService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping("list")
	@MemberAuth(verifyLogin = false)
	public ModelAndView list() throws FrameworkException {
		//获取文章分类数据
		List<BaseCategoryBean> list = baseCategoryService.findArticleCategorys();
		ModelAndView mav = new ModelAndView();
		mav.addObject("artCgy", list);
		mav.setViewName("article/list");
		return mav;
	}

	@RequestMapping("loadArticles")
	@MemberAuth(verifyLogin = false)
	@ResponseBody
	public Map<String, Object> loadArticles(String categoryId, PageBean pageBean) throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		int total = articleService.getArticleRows(categoryId);
		pageBean.init(total);
		List<WebArticle> articles = articleService.findAritcles(categoryId, pageBean.getCurPage(),
				pageBean.getPageSize());
		List<ArticleBean> articleBeans = pojos2Beans(articles);
		result.put("articles", articleBeans);
		result.put("pageBean", pageBean);
		result.put("hasNext", total > (pageBean.getCurPage() * pageBean.getPageSize()) ? "true" : "false");
		return result;
	}

	@RequestMapping("detail/{articleId}")
	@MemberAuth(verifyLogin = false)
	public ModelAndView findArticleById(@PathVariable String articleId) throws FrameworkException {
		WebArticle article = this.articleService.findArticleById(articleId);
		ArticleBean articleBean = pojos2Bean(article);
		article.setReadCount(article.getReadCount()+1);
		this.articleService.updateArticle(article);
		List<WebArticle> articles = articleService.findAritcles(article.getCategory().getId(), 1, 10);
		articles.remove(article);
		List<ArticleBean> articleBeans = pojos2Beans(articles);
		ModelAndView mav = new ModelAndView();
		mav.addObject("articleBean", articleBean);
		mav.addObject("articleBeans", articleBeans);
		mav.setViewName("www4lt/announ/content");
		return mav;
	}
	
	@RequestMapping("activity/{articleId}")
	@MemberAuth(verifyLogin = false)
	public ModelAndView findArticleById1(@PathVariable String articleId) throws FrameworkException {
		WebArticle article = this.articleService.findArticleById(articleId);
		ArticleBean articleBean = pojos2Bean(article);
		article.setReadCount(article.getReadCount()+1);
		this.articleService.updateArticle(article);
		List<WebArticle> articles = articleService.findAritcles(article.getCategory().getId(), 1, 10);
		articles.remove(article);
		List<ArticleBean> articleBeans = pojos2Beans(articles);
		ModelAndView mav = new ModelAndView();
		mav.addObject("articleBean", articleBean);
		mav.addObject("articleBeans", articleBeans);
		mav.setViewName("www4lt/activity/content");
		return mav;
	}
	
	@RequestMapping("support")
	@MemberAuth(verifyLogin = false)
	@ResponseBody
	public Map<String, Object> ArticleSupport(String articleId) throws FrameworkException {
		Map<String, Object> result = new HashMap<String, Object>();
		WebArticle article = this.articleService.findArticleById(articleId);
		article.setSupportCount(article.getSupportCount()+1);
		this.articleService.updateArticle(article);
		result.put("support", article.getSupportCount());
		return result;
	}

	/*******************************************************/
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

	private List<ArticleBean> pojos2Beans(List<WebArticle> articles) {
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		for (WebArticle article : articles) {
			ArticleBean bean = pojos2Bean(article);
			beans.add(bean);
		}
		return beans;
	}
}
