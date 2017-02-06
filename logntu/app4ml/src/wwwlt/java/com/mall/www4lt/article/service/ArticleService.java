/**
 * 
 */
package com.mall.www4lt.article.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.experimental.theories.Theory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.mall.domain.WebArticle;
import com.mall.www4lt.article.dao.ArticleDao;


/**
 * @author 印鲜刚
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("articleService")
public class ArticleService {
	@Resource(name = "articleDao")
	private ArticleDao articleDao;

	/**
	 * 
	 * @param categoryId
	 * @return
	 * @throws FrameworkException
	 */
	public int getArticleRows(String categoryId) throws FrameworkException {
		return this.articleDao.getArticleRows(categoryId);
	}

	public List<WebArticle> findAritcles(String categoryId, int currentPage, int pageSize) throws FrameworkException {
		return this.articleDao.findAritcles(categoryId, currentPage, pageSize);
	}

	public WebArticle findArticleById(String id) throws FrameworkException {
		return this.articleDao.get(id);
	}
	
	@Transactional
	public void updateArticle(WebArticle article) throws FrameworkException {
		 this.articleDao.update(article);
	}
}
