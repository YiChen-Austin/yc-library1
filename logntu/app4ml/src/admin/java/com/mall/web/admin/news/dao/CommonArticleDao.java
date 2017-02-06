/**
 * 
 */
package com.mall.web.admin.news.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.news.domain.SysCommonArticle;

/**
 * @功能说明：论坛、信息发布数据访问层
 * @作者： 印鲜刚
 * @创建日期： 2010-9-23 @
 */
@Repository("commonArticleDao")
public class CommonArticleDao extends BaseDao<SysCommonArticle> {
	Logger logger = Logger.getLogger(CommonArticleDao.class);

	/**
	 * ************************************************************公共论坛*********
	 * **************************************************************
	 */
	/**
	 * 根据条件查询人员
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public int getCommonArticleTotalRows(Map<String, Object> params)
			throws FrameworkException {

		StringBuffer hql = new StringBuffer(
				"SELECT COUNT(*) FROM SysCommonArticle ca where ca.commonArticle is null AND ca.flag=:flag ");
		if (!BaseUtil.isEmpty(params, "commonCategoryId"))
			hql.append("AND ca.commonCategory.id = :commonCategoryId ");
		else
			params.remove("commonCategoryId");
		if (!BaseUtil.isEmpty(params, "title")) {
			params.put("title", "%" + params.get("title") + "%");
			hql.append("AND ca.title like :title ");
		} else
			params.remove("title");
		if (!BaseUtil.isEmpty(params, "publishDepartmentId"))
			hql.append("AND ca.publishDepartment.id = :publishDepartmentId ");
		else
			params.remove("publishDepartmentId");
		if (!BaseUtil.isEmpty(params, "startTime"))
			hql.append("AND ca.publishTime >= :startTime ");
		else
			params.remove("startTime");
		if (!BaseUtil.isEmpty(params, "endTime"))
			hql.append("AND ca.publishTime <= :endTime ");
		else
			params.remove("endTime");
		params.put("flag", "discussion");
		logger.debug("commonCategoryId=" + params.get("commonCategoryId"));
		logger.debug("getCommonArticleTotalRows:hql=" + hql.toString());
		return this.getTotalRows(hql.toString(), params);

	}

	/**
	 * 根据条件查询信息、论坛列表
	 * 
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonArticle> findAllCommonArticles(
			Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		StringBuffer hql = new StringBuffer(
				"SELECT ca FROM SysCommonArticle ca where ca.commonArticle is null AND ca.flag=:flag ");
		if (!BaseUtil.isEmpty(params, "commonCategoryId"))
			hql.append("AND ca.commonCategory.id = :commonCategoryId ");
		else
			params.remove("commonCategoryId");
		if (!BaseUtil.isEmpty(params, "title")) {
			params.put("title", "%" + params.get("title") + "%");
			hql.append("AND ca.title like :title ");
		} else
			params.remove("title");
		if (!BaseUtil.isEmpty(params, "publishDepartmentId"))
			hql.append("AND ca.publishDepartment.id = :publishDepartmentId ");
		else
			params.remove("publishDepartmentId");
		if (!BaseUtil.isEmpty(params, "startTime"))
			hql.append("AND ca.publishTime >= :startTime ");
		else
			params.remove("startTime");
		if (!BaseUtil.isEmpty(params, "endTime"))
			hql.append("AND ca.publishTime <= :endTime ");
		else
			params.remove("endTime");
		hql.append("ORDER BY ca.publishTime DESC");
		params.put("flag", "discussion");
		logger.debug("findAllCommonArticles:hql=" + hql.toString());
		return this.findAllEntitiesByCondition(hql.toString(), params, curPage,
				pageSize);
	}

	/**
	 * ************************************************************信息中心*********
	 * **************************************************************
	 */
	/**
	 * 获取信息中心记录数
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	public int getNewsCommonArticleTotalRows(Map<String, Object> params)
			throws FrameworkException {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from (select article.id, article.title, article.publishtime, cate.id cateid  from SysCommonArticle article, commoncategory cate where article.commoncategory_id = cate.id and article.scope = 'no' AND article.flag='news' ");
		if (!BaseUtil.isEmpty(params, "commonCategoryId"))
			sql.append("and cate.id = '" + params.get("commonCategoryId")
					+ "' ");
		if (!BaseUtil.isEmpty(params, "title"))
			sql.append("and article.title like '%" + params.get("title")
					+ "%' ");
		if (!BaseUtil.isEmpty(params, "startTime"))
			sql.append("and article.publishtime >=:startTime ");
		else
			params.remove("startTime");
		if (!BaseUtil.isEmpty(params, "endTime"))
			sql.append("and article.publishtime <=:endTime ");
		else
			params.remove("endTime");

		sql.append("union  select article.id, article.title, article.publishtime, cate.id cateid ");
		sql.append("from SysCommonArticle article left join commoncategory cate on article.commoncategory_id = cate.id ");
		sql.append("left join commonarticle_sysuser sysuser on article.id = sysuser.commonarticle_id ");
		sql.append("left join  commonarticle_sysorganization sysorg on article.id = sysorg.commonarticle_id ");
		sql.append("where article.scope = 'yes' AND article.flag='news' ");
		if (!BaseUtil.isEmpty(params, "commonCategoryId"))
			sql.append("and cate.id = '" + params.get("commonCategoryId")
					+ "' ");
		if (!BaseUtil.isEmpty(params, "title"))
			sql.append("and article.title like '%" + params.get("title")
					+ "%' ");
		if (!BaseUtil.isEmpty(params, "startTime"))
			sql.append("and article.publishtime >=:startTime ");
		else
			params.remove("startTime");
		if (!BaseUtil.isEmpty(params, "endTime"))
			sql.append("and article.publishtime <=:endTime ");
		else
			params.remove("endTime");

		if (!BaseUtil.isEmpty(params, "userId")
				&& !BaseUtil.isEmpty(params, "orgId"))
			sql.append("and (sysuser.receiveemps_id ='" + params.get("userId")
					+ "' or sysorg.receivedeparts_id ='" + params.get("orgId")
					+ "') ");
		sql.append(") ");
		Query query = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(sql.toString());

		if (!BaseUtil.isEmpty(params, "startTime"))
			query.setParameter("startTime", params.get("startTime"));
		if (!BaseUtil.isEmpty(params, "endTime"))
			query.setParameter("endTime", params.get("endTime"));

		Object rs = query.list().get(0);
		String count = rs.toString();
		return Integer.valueOf(count).intValue();
	}

	/**
	 * 获取信息中心记录
	 * 
	 * @param searchCondition
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonArticle> findAllNewsCommonArticles(
			Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		StringBuffer sql = new StringBuffer();
		sql.append("select tt.id from (select article.id, article.title, article.publishtime, cate.id cateid  from SysCommonArticle article, commoncategory cate where article.commoncategory_id = cate.id and article.scope = 'no' AND article.flag='news' ");
		if (!BaseUtil.isEmpty(params, "commonCategoryId"))
			sql.append("and cate.id = '" + params.get("commonCategoryId")
					+ "' ");
		if (!BaseUtil.isEmpty(params, "title"))
			sql.append("and article.title like '%" + params.get("title")
					+ "%' ");
		if (!BaseUtil.isEmpty(params, "startTime"))
			sql.append("and article.publishtime >=:startTime ");
		else
			params.remove("startTime");
		if (!BaseUtil.isEmpty(params, "endTime"))
			sql.append("and article.publishtime <=:endTime ");
		else
			params.remove("endTime");

		sql.append("union  select article.id, article.title, article.publishtime, cate.id cateid ");
		sql.append("from SysCommonArticle article left join commoncategory cate on article.commoncategory_id = cate.id ");
		sql.append("left join commonarticle_sysuser sysuser on article.id = sysuser.commonarticle_id ");
		sql.append("left join  commonarticle_sysorganization sysorg on article.id = sysorg.commonarticle_id ");
		sql.append("where article.scope = 'yes' AND article.flag='news' ");
		if (!BaseUtil.isEmpty(params, "commonCategoryId"))
			sql.append("and cate.id = '" + params.get("commonCategoryId")
					+ "' ");
		if (!BaseUtil.isEmpty(params, "title"))
			sql.append("and article.title like '%" + params.get("title")
					+ "%' ");
		if (!BaseUtil.isEmpty(params, "startTime"))
			sql.append("and article.publishtime >=:startTime ");
		else
			params.remove("startTime");
		if (!BaseUtil.isEmpty(params, "endTime"))
			sql.append("and article.publishtime <=:endTime ");
		else
			params.remove("endTime");

		if (!BaseUtil.isEmpty(params, "userId")
				&& !BaseUtil.isEmpty(params, "orgId"))
			sql.append("and (sysuser.receiveemps_id ='" + params.get("userId")
					+ "' or sysorg.receivedeparts_id ='" + params.get("orgId")
					+ "') ");
		sql.append(") tt order by publishtime desc");
		Query query = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(sql.toString());

		if (!BaseUtil.isEmpty(params, "startTime"))
			query.setParameter("startTime", params.get("startTime"));
		if (!BaseUtil.isEmpty(params, "endTime"))
			query.setParameter("endTime", params.get("endTime"));

		if (curPage > 0 && pageSize > 0) {
			query.setFirstResult((curPage - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		List<Object> rs = query.list();
		List<SysCommonArticle> articles = new ArrayList<SysCommonArticle>();
		for (Object id : rs) {
			articles.add(this.get((String) id));
		}
		return articles;
	}

	/**
	 * 桌面公告
	 * 
	 * @param loginUserId
	 * @param organizationId
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysCommonArticle> findDesktopNews(String loginUserId,
			String organizationId) throws FrameworkException {
		StringBuffer sql = new StringBuffer();
		sql.append("select tt.id from (select article.id, article.title, article.publishtime, cate.id cateid  from sys_common_article article, sys_common_category cate where article.commoncategory_id = cate.id and article.scope = 'no' AND article.flag='news' ");
		sql.append("union  select article.id, article.title, article.publishtime, cate.id cateid ");
		sql.append("from sys_common_article article left join sys_common_category cate on article.commoncategory_id = cate.id ");
		sql.append("left join sys_common_article_sys_user sysuser on article.id = sysuser.commonarticle_id ");
		sql.append("left join  sys_common_article_sys_organization sysorg on article.id = sysorg.commonarticle_id ");
		sql.append("where article.scope = 'yes' AND article.flag='news' ");
		if (!BaseUtil.isEmpty(loginUserId) && !BaseUtil.isEmpty(organizationId))
			sql.append("and (sysuser.receiveemps_id ='" + loginUserId
					+ "' or sysorg.receivedeparts_id ='" + organizationId
					+ "') ");
		sql.append(") tt order by publishtime desc");
		Query query = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(sql.toString());
		query.setFirstResult(0);
		query.setMaxResults(7);
		List<Object> rs = query.list();
		List<SysCommonArticle> articles = new ArrayList<SysCommonArticle>();
		for (Object id : rs) {
			articles.add(this.get((String) id));
		}
		return articles;
	}

}
