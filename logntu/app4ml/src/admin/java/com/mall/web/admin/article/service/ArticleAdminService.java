package com.mall.web.admin.article.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.article.dao.ArticleAdminDao;
import com.mall.web.mall.domain.WebArticle;


@Service("articleAdminService")
public class ArticleAdminService {
	@Resource(name = "articleAdminDao")
	private ArticleAdminDao articleAdminDao;

	/**
	 * 通过ID获取数据
	 * 
	 * @param Id
	 * @return
	 * @throws FrameworkException
	 */
	public WebArticle findArticleById(String id) throws FrameworkException {
		return this.articleAdminDao.get(id);
	}


	/**
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public int getArticlesTotalRows(Map<String, Object> params)
			throws FrameworkException {
		return this.articleAdminDao.getArticleRows_(params);

	}

	public int getArticlesCount(String name, String categoryName)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		params.put("categoryName", categoryName);
		return this.articleAdminDao.getArticleRows(params);
	}

	/**
	 * @param params
	 * @param sTime
	 * @param eTime
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebArticle> findArticles(Map<String, Object> params,
			int curPage, int pageSize) throws FrameworkException {
		return this.articleAdminDao.findArticles_(params, curPage, pageSize);
	}

	public WebArticle findArticleByName(String articleName)
			throws FrameworkException {
		if (BaseUtil.isEmpty(articleName))
			return null;
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", articleName);
		List<WebArticle> list = this.articleAdminDao.findArticles(params, 0, 100);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 添加药物
	 * 
	 * @param article
	 * @throws FrameworkException
	 */
	@Transactional
	public void saveArticle(WebArticle article) throws FrameworkException {
		this.articleAdminDao.save(article);
	}


	/**
	 * 修改药物
	 * 
	 * @param article
	 * @throws FrameworkException
	 */
	@Transactional
	public void updateArticle(WebArticle article) throws FrameworkException {
		this.articleAdminDao.update(article);
	}


	/**
	 * 删除药物信息
	 * 
	 * @param String
	 *            ids ID形成的字符串
	 * @return
	 */
	@Transactional
	public void deleteArticle(String[] ids) {
		String idStr = "";
		for (String id : ids) {
			idStr += "'" + id + "',";
		}
		if (idStr.length() <= 0)
			return;
		idStr = idStr.substring(0, idStr.length() - 1);
		this.articleAdminDao.deleteArticle(idStr);
	}

}
