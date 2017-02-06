/**
 * 
 */
package com.mall.www4lt.article.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.mall.domain.WebArticle;


/**
 * @author 印鲜刚
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("articleDao")
public class ArticleDao extends BaseDao<WebArticle> {
	/**
	 * 
	 * @param categoryId
	 * @return
	 * @throws FrameworkException
	 */
	public int getArticleRows(String categoryId) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT count(DISTINCT model.id) FROM WebArticle model where 1=1 ");
		if (!"all".equals(categoryId) && !BaseUtil.isEmpty(categoryId)) {
			hql.append("and model.category.id=:categoryId ");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!"all".equals(categoryId) && !BaseUtil.isEmpty(categoryId)) {
			params.put("categoryId", categoryId);
		}
		return this.getTotalRows(hql.toString(), params);

	}

	/**
	 * 
	 * @param categoryId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	public List<WebArticle> findAritcles(String categoryId, int currentPage, int pageSize) throws FrameworkException {

		StringBuffer hql = new StringBuffer();
		hql.append("SELECT model FROM WebArticle model where 1=1 ");
		if (!"all".equals(categoryId) && !BaseUtil.isEmpty(categoryId)) {
			hql.append("and model.category.id=:categoryId ");
		}
		hql.append("ORDER BY model.publishTime DESC ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!"all".equals(categoryId) && !BaseUtil.isEmpty(categoryId)) {
			params.put("categoryId", categoryId);
		}
		return this.findAllEntitiesByCondition(hql.toString(), params, currentPage, pageSize);

	}

}
