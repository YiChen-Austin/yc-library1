package com.mall.web.admin.article.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.mall.domain.WebArticle;


@Repository("articleAdminDao")
public class ArticleAdminDao extends BaseDao<WebArticle> {
	public int getArticleRows_(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT count(DISTINCT model.id) FROM WebArticle model where 1=1 ");
		if (!BaseUtil.isEmpty(params.get("earaId"))) {
			hql.append("and model.publishOrgId=:earaId ");
		}
		if (BaseUtil.isEmpty(params)) {
			return this.getTotalRows(hql.toString(), params);
		}
		if (!BaseUtil.isEmpty(params.get("title"))) {
			hql.append("and model.title like :title ");
			params.put("title", "%" + params.get("title") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("artContent"))) {
			hql.append("and model.attending like :artContent ");
			params.put("artContent", "%" + params.get("artContent") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("categoryId"))) {
			hql.append("and model.category.id=:categoryId ");
		}
		if (!BaseUtil.isEmpty(params.get("categoryName"))) {
			hql.append("and model.category.name=:categoryName ");
		}
		return this.getTotalRows(hql.toString(), params);
	}

	public int getArticleRows(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT count(DISTINCT model.id) FROM WebArticle model where 1=1 ");
		if (!BaseUtil.isEmpty(params.get("earaId"))) {
			hql.append("and model.publishOrgId=:earaId ");
		}
		if (BaseUtil.isEmpty(params)) {
			return this.getTotalRows(hql.toString(), params);
		}
		if (!BaseUtil.isEmpty(params.get("title"))) {
			hql.append("and model.title like :title ");
			params.put("name", "%" + params.get("title") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("artContent"))) {
			hql.append("and model.attending like :artContent ");
			params.put("artContent", "%" + params.get("artContent") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("categoryId"))) {
			hql.append("and model.category.id=:categoryId ");
		}
		if (!BaseUtil.isEmpty(params.get("categoryName"))) {
			hql.append("and model.category.name=:categoryName ");
		}
		return this.getTotalRows(hql.toString(), params);
	}

	public List<WebArticle> findArticles_(Map<String, Object> params,
			int curPage, int pageSize) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT model FROM WebArticle model where 1=1 ");
		if (!BaseUtil.isEmpty(params.get("earaId"))) {
			hql.append("and model.publishOrgId=:earaId ");
		}
		if (BaseUtil.isEmpty(params)) {
			hql.append(" order by model.publishTime desc");
			return this.findAllEntitiesByCondition(hql.toString(), params,
					curPage, pageSize);
		}
		if (!BaseUtil.isEmpty(params.get("title"))) {
			hql.append("and model.title like :title ");
			params.put("title", "%" + params.get("title") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("artContent"))) {
			hql.append("and model.attending like :artContent ");
			params.put("artContent", "%" + params.get("artContent") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("categoryId"))) {
			hql.append("and model.category.id=:categoryId ");
		}
		if (!BaseUtil.isEmpty(params.get("categoryName"))) {
			hql.append("and model.category.name=:categoryName ");
		}
		if (!BaseUtil.isEmpty(params.get("readCount"))) {
			hql.append(" order by model.readCount desc");
			params.remove("readCount");
		} else
			hql.append(" order by model.publishTime desc");
		return this.findAllEntitiesByCondition(hql.toString(), params, curPage,
				pageSize);
	}

	public List<WebArticle> findArticles(Map<String, Object> params,
			int curPage, int pageSize) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT model FROM WebArticle model where 1=1 ");
		if (!BaseUtil.isEmpty(params.get("earaId"))) {
			hql.append("and model.publishOrgId=:earaId ");
		}
		if (BaseUtil.isEmpty(params)) {
			return this.findAllEntitiesByCondition(hql.toString(), params,
					curPage, pageSize);
		}
		if (!BaseUtil.isEmpty(params.get("title"))) {
			hql.append("and model.title like :title ");
			params.put("name", "%" + params.get("title") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("artContent"))) {
			hql.append("and model.attending like :artContent ");
			params.put("artContent", "%" + params.get("artContent") + "%");
		}
		if (!BaseUtil.isEmpty(params.get("categoryId"))) {
			hql.append("and model.category.id=:categoryId ");
		}
		if (!BaseUtil.isEmpty(params.get("categoryName"))) {
			hql.append("and model.category.name=:categoryName ");
		}
		return this.findAllEntitiesByCondition(hql.toString(), params, curPage,
				pageSize);
	}

	public void deleteArticle(String idStr) {
		String hql = "delete from sys_article  where id in (" + idStr + ")";
		jdbcTemplate.execute(hql);
	}

}
