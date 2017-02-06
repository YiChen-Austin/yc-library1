/**
 * 
 */
package com.mall.web.admin.news.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.news.dao.CommonArticleDao;
import com.mall.web.admin.news.domain.SysCommonArticle;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：论坛、信息业务层
 * @作者： 印鲜刚
 * @创建日期： 2010-9-23 @
 */
@Service("commonArticleService")
public class CommonArticleService {
	private Logger logger = Logger.getLogger(CommonArticleService.class);
	@Resource(name = "commonArticleDao")
	private CommonArticleDao commonArticleDao;

	/**
	 * ************************************************************公共论坛*********
	 * **************************************************************
	 */
	/**
	 * 根据条件统计论坛、信息总数
	 * 
	 * @param searchCondition
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getCommonArticleTotalRows(Map<String, Object> searchCondition)
			throws FrameworkException {
		return this.commonArticleDao.getCommonArticleTotalRows(searchCondition);
	}

	/**
	 * 根据条件查询信息、论坛列表
	 * 
	 * @param searchCondition
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysCommonArticle> findAllCommonArticles(
			Map<String, Object> searchCondition, int curPage, int pageSize)
			throws FrameworkException {
		return this.commonArticleDao.findAllCommonArticles(searchCondition,
				curPage, pageSize);
	}

	/**
	 * 通过ID获取所有的直接子论坛信息列表
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysCommonArticle> getAllChildCommonAritcles(String id)
			throws FrameworkException {
		SysCommonArticle ca = this.commonArticleDao.get(id);
		return sort(ca.getCommonArticles());
	}

	/*
	 * 排序功能
	 * 
	 * @param list
	 */
	@DataSource(value = "admin")
	private List<SysCommonArticle> sort(List<SysCommonArticle> list) {

		Collections.sort(list, new Comparator<SysCommonArticle>() {
			public int compare(SysCommonArticle commonArticle0,
					SysCommonArticle commonArticle1) {
				Date oldPublishTime = commonArticle0.getPublishTime();
				Date newPublishTime = commonArticle1.getPublishTime();
				return oldPublishTime.compareTo(newPublishTime);
			}
		});
		return list;
	}

	/**
	 * 通过ID获取论坛、信息等详细信息
	 * 
	 * @param id
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysCommonArticle findCommonArticleById(String id)
			throws FrameworkException {
		return this.commonArticleDao.get(id);
	}

	/**
	 * 通过多个信息或论坛ID，系统删除多信息或论坛
	 * 
	 * @param commonArticleIds
	 * @throws FacesException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteCommonArticles(String[] commonArticleIds)
			throws FrameworkException {
		for (String commonArticleId : commonArticleIds) {
			this.deleteCommonArticleById(commonArticleId);
		}

	}

	/**
	 * 通过信息或论坛ID删除信息或论坛
	 * 
	 * @param commonArticleId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteCommonArticleById(String commonArticleId)
			throws FrameworkException {
		SysCommonArticle ca = this.commonArticleDao.get(commonArticleId);
		// LogUtil.getInstance()
		// .deleteExecution(ca, ("news".equals(ca.getFlag()) ? "新闻标题" : "论坛标题")
		// + "：" + ca.getTitle());
		this.commonArticleDao.delete(ca);

	}

	/**
	 * 保存论坛或信息
	 * 
	 * @param commonArticle
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void save(SysCommonArticle commonArticle) throws FrameworkException {

		this.commonArticleDao.save(commonArticle);

	}

	/**
	 * 修改帖子或信息
	 * 
	 * @param ca
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void update(SysCommonArticle ca) throws FrameworkException {
		this.commonArticleDao.update(ca);

	}

	/**
	 * ************************************************************信息中心*********
	 * **************************************************************
	 */
	/**
	 * 获取信息中心记录数
	 * 
	 * @param searchCondition
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public int getNewsCommonArticleTotalRows(Map<String, Object> searchCondition)
			throws FrameworkException {
		return this.commonArticleDao
				.getNewsCommonArticleTotalRows(searchCondition);
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
	@DataSource(value = "admin")
	public List<SysCommonArticle> findAllNewsCommonArticles(
			Map<String, Object> searchCondition, int curPage, int pageSize)
			throws FrameworkException {
		return this.commonArticleDao.findAllNewsCommonArticles(searchCondition,
				curPage, pageSize);
	}

	/**
	 * 桌面公告
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysCommonArticle> findDesktopNews(SysMngUserLoginBean loginUser)
			throws FrameworkException {
		return this.commonArticleDao.findDesktopNews(loginUser.getId(),
				loginUser.getOrganizationId());
	}

	/**
	 * 锁定论坛不再允许回复
	 * 
	 * @param articleId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void lockedCommonArticles(String articleId)
			throws FrameworkException {
		SysCommonArticle article = this.commonArticleDao.get(articleId);
		article.setLockedPost("Y");
		this.commonArticleDao.update(article);
	}

	/**
	 * 恢复锁定论坛允许回复
	 * 
	 * @param articleId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void unlockedCommonArticles(String articleId)
			throws FrameworkException {
		SysCommonArticle article = this.commonArticleDao.get(articleId);
		article.setLockedPost("N");
		this.commonArticleDao.update(article);

	}
}
