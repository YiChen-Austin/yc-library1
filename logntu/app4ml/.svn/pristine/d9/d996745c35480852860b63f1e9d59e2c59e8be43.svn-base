package com.mall.web.admin.article.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.HqlUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.article.service.ArticleAdminService;
import com.mall.web.admin.article.vo.ArticleAdminBean;
import com.mall.web.admin.category.service.BaseCategoryService;
import com.mall.web.admin.category.vo.BaseCategoryBean;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.service.SysOrganizationService;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.mall.domain.WebArticle;

@Controller
@RequestMapping("/articleAdmin/*")
public class ArticleAdminAction extends BaseAction {

	@Autowired
	protected ArticleAdminService articleAdminService;
	@Autowired
	protected SysUserService sysUserService;
	@Autowired
	public BaseCategoryService baseCategoryService;
	@Autowired
	protected SysOrganizationService sysOrganizationService;
	
	/************************************************/
	/**
	 * 所有文章信息
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws UnsupportedEncodingException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("findArticleAll")
	@ResponseBody
	public Map<String, Object> findArticleBaseAll(HttpServletRequest request,ArticleAdminBean articleBean,
			PageBean pageBean) throws FrameworkException,
			IllegalArgumentException, IllegalAccessException,
			UnsupportedEncodingException {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		BaseUtil.decodeObject(articleBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(articleBean,
				WebArticle.class);
		Map<String, Object> searchConditionPage = HqlUtil.beanToMap(
				articleBean, WebArticle.class);

		if (!BaseUtil.isEmpty(articleBean)
				&& !BaseUtil.isEmpty(articleBean.getCategoryId())) {
			searchCondition.put("categoryId", articleBean.getCategoryId());
			searchConditionPage.put("categoryId", articleBean.getCategoryId());
		}
		SysOrganization org = sysOrganizationService
				.findSysOrganizationById(sysUserLoginBean
						.getOrganizationId());
		if (!BaseUtil.isEmpty(org.getOrganizationNumber())
				&& !"0".equalsIgnoreCase(org.getOrganizationNumber())) {
			searchCondition.put("earaId", org.getOrganizationNumber());
			searchConditionPage.put("earaId", org.getOrganizationNumber());
		}
		pageBean.init(articleAdminService
				.getArticlesTotalRows(searchConditionPage));
		List<WebArticle> articles = articleAdminService.findArticles(
				searchCondition, pageBean.getCurPage(), pageBean.getPageSize());
		List<ArticleAdminBean> articleBeans = pojos2Beans(articles);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("articleBeans", articleBeans);
		result.put("pageBean", pageBean);
		return result;
	}

	/**
	 * 获取文章分类数据
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("findArticleCategorys")
	@ResponseBody
	public Map<String, Object> findArticleCategorys() throws FrameworkException {
		List<BaseCategoryBean> list = baseCategoryService
				.findArticleCategorys();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("baseCategoryBeans", list);
		result.put("result", true);
		return result;
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("createArticle")
	public ModelAndView createArticle() throws FrameworkException {
		List<BaseCategoryBean> list = baseCategoryService
				.findArticleCategorys();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("baseCategoryBeans", list);
		return forword("article/article_lr_detail", context);
	}

	/**
	 * 添加或修改药物
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("saveArticle")
	public ModelAndView saveArticle(HttpServletRequest request,
			ArticleAdminBean articleBean, MultipartFile dataFile)
			throws FrameworkException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		if (BaseUtil.isEmpty(articleBean.getId())) {
			WebArticle article = new WebArticle();
			String path = request.getServletContext().getRealPath("/upload")
					+ "/article/";
			String lpath = saveFile(path, sysUserLoginBean.getId(), dataFile);
			article.setTitle(articleBean.getTitle());
			article.setCategory(baseCategoryService
					.findBaseCategoryById(articleBean.getCategoryId()));
			article.setArtAbstract(articleBean.getArtAbstract());
			article.setContent(BaseUtil.isEmpty(articleBean.getArtContent()) ? "&nbsp;"
					: articleBean.getArtContent());
			article.setPublishTime(new Date());
			article.setPublishAuthorId(sysUserLoginBean.getId());
			article.setReadCount(0);
			article.setSupportCount(0);
			article.setUnSupportCount(0);
			article.setTag(articleBean.getTag());
			SysOrganization org = sysOrganizationService
					.findSysOrganizationById(sysUserLoginBean.getOrganizationId());			
			article.setPublishOrgId(org.getOrganizationNumber());
			if (lpath != null)
				article.setImgUrl("/upload/article/" + lpath);
			articleAdminService.saveArticle(article);
		} else {
			WebArticle article = articleAdminService
					.findArticleById(articleBean.getId());
			String path = request.getServletContext().getRealPath("/upload")
					+ "/article/";
			String lpath = saveFile(path, sysUserLoginBean.getId(), dataFile);
			article.setTitle(articleBean.getTitle());
			article.setCategory(baseCategoryService
					.findBaseCategoryById(articleBean.getCategoryId()));
			article.setArtAbstract(articleBean.getArtAbstract());
			article.setContent(BaseUtil.isEmpty(articleBean.getArtContent()) ? "&nbsp;"
					: articleBean.getArtContent());
			article.setPublishTime(new Date());
			article.setPublishAuthorId(sysUserLoginBean.getId());
			article.setTag(articleBean.getTag());
			if (lpath != null)
				article.setImgUrl("/upload/article/" + lpath);
			articleAdminService.updateArticle(article);
		}
		return forword("article/article_lr_list", null);
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SQLException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("updateArticle")
	public ModelAndView updateArticle(ArticleAdminBean articleBean)
			throws FrameworkException, SQLException {
		WebArticle article = articleAdminService.findArticleById(articleBean
				.getId());
		BeanUtils.copyProperties(article, articleBean);
		if (!BaseUtil.isEmpty(article.getCategory())) {
			articleBean.setCategoryId(article.getCategory().getId());
			articleBean.setCategoryName(article.getCategory().getName());
		}
		if (!BaseUtil.isEmpty(article.getContent())) {
			articleBean.setArtContent(article.getContent());
		}
		List<BaseCategoryBean> list = baseCategoryService
				.findArticleCategorys();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("baseCategoryBeans", list);
		context.put("articleBean", articleBean);
		return forword("article/article_lr_detail", context);
	}

	/**
	 * 通过多个药物ID
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("deleteArticle")
	@ResponseBody
	public Map<String, Object> deleteArticle(ArticleAdminBean articleBean)
			throws FrameworkException {
		if (BaseUtil.isEmpty(articleBean.getDeleteIds())
				|| articleBean.getDeleteIds().length() <= 0) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("result", false);
			return result;
		}
		try {
			String[] ids = articleBean.getDeleteIds().split(",");
			articleAdminService.deleteArticle(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", true);
		return result;
	}

	/**
	 * 进入查看页面
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SQLException
	 */
	@Auth(verifyLogin = true, verifyURL = false)
	@RequestMapping("viewArticle")
	public ModelAndView viewArticle(ArticleAdminBean articleBean)
			throws FrameworkException, SQLException {
		WebArticle article = articleAdminService.findArticleById(articleBean
				.getId());
		BeanUtils.copyProperties(article, articleBean);
		if (!BaseUtil.isEmpty(article.getCategory())) {
			articleBean.setCategoryId(article.getCategory().getId());
			articleBean.setCategoryName(article.getCategory().getName());
		}
		if (!BaseUtil.isEmpty(article.getContent())) {
			articleBean.setArtContent(article.getContent());
		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("articleBean", articleBean);
		return forword("article/article_lr_view", context);
	}

	/*******************************************************/
	private List<ArticleAdminBean> pojos2Beans(List<WebArticle> articles) {
		List<ArticleAdminBean> beans = new ArrayList<ArticleAdminBean>();
		for (WebArticle article : articles) {
			ArticleAdminBean bean = new ArticleAdminBean();
			BeanUtils.copyProperties(article, bean);
			if (!BaseUtil.isEmpty(article.getCategory())) {
				bean.setCategoryId(article.getCategory().getId());
				bean.setCategoryName(article.getCategory().getName());
				SysOrganization org = null;
				try {
					org = sysOrganizationService.getSysOrganizationByNum(bean
							.getPublishOrgId());
					bean.setPublishOrgName(org == null ? "" : org.getName());
				} catch (FrameworkException e) {
					//logger.warn(e);
				}
			}
			beans.add(bean);
		}
		return beans;
	}

}
