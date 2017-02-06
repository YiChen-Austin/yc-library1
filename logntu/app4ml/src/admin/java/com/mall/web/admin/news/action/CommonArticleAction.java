/**
 * 
 */
package com.mall.web.admin.news.action;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.news.domain.SysCommonArticle;
import com.mall.web.admin.news.service.CommonArticleService;
import com.mall.web.admin.news.service.CommonCategoryService;
import com.mall.web.admin.news.vo.CommonArticleBean;
import com.mall.web.admin.security.domain.SysOrganization;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.admin.security.service.SysOrganizationService;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

/**
 * @功能说明：论坛、信息发布控制层
 * @作者： 印鲜刚
 * @创建日期： 2010-9-23 @
 */
@Controller
@RequestMapping("/news/")
public class CommonArticleAction extends BaseAction {
	private static final long serialVersionUID = 6745245745328673050L;
	private static Logger logger = Logger.getLogger(CommonArticleAction.class);
	@Resource(name = "commonArticleService")
	private CommonArticleService commonArticleService;
	@Resource(name = "sysUserService")
	private SysUserService sysUserService;
	@Resource(name = "sysOrganizationService")
	private SysOrganizationService sysOrganizationService;
	@Resource(name = "commonCategoryService")
	private CommonCategoryService commonCategoryService;

	/**
	 * ************************************************************公共论坛*********
	 * **************************************************************
	 */
	/**
	 * 通过ID获取其所有的直接子论坛列表
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws SQLException
	 */
	@RequestMapping("findChildDiscussionsById")
	public ModelAndView findChildDiscussionsById(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws FrameworkException, Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		List<CommonArticleBean> commonArticleBeans = new ArrayList<CommonArticleBean>();

		SysCommonArticle ca = commonArticleService
				.findCommonArticleById(commonArticleBean.getId());
		ca.setReadCount(ca.getReadCount() + 1);
		commonArticleService.update(ca);
		CommonArticleBean cab = new CommonArticleBean();
		cab.setId(ca.getId());
		cab.setEmotion(ca.getEmotion());
		cab.setTitle(ca.getTitle());
		cab.setPublishAuthorName(BaseUtil.isEmpty(ca.getPublishAuthor()) ? "匿名"
				: ca.getPublishAuthor().getRealName());
		cab.setPublishDepartmentName(BaseUtil.isEmpty(ca.getPublishDepartment()) ? ""
				: ca.getPublishDepartment().getName());
		cab.setPublishTime(ca.getPublishTime());
		cab.setContent(ca.getContent());
		cab.setAffixName(ca.getAffixName());
		cab.setImage(isImage(ca.getAffixName()) ? "yes" : "no");
		cab.setLockedButton(ca.getLockedPost());
		cab.setCommonCategoryId(ca.getCommonCategory().getId());
		commonArticleBeans.add(cab);
		List<SysCommonArticle> cas = commonArticleService
				.getAllChildCommonAritcles(commonArticleBean.getId());
		for (SysCommonArticle article : cas) {
			cab = new CommonArticleBean();
			cab.setId(article.getId());
			cab.setCommonCategoryId(ca.getCommonCategory().getId());
			cab.setEmotion(article.getEmotion());
			cab.setTitle(BaseUtil.isEmpty(article.getTitle()) ? ca.getTitle()
					: article.getTitle());
			cab.setPublishAuthorName(article.getAnonymous().equalsIgnoreCase(
					"Y") ? "匿名" : article.getPublishAuthor().getRealName());
			cab.setPublishDepartmentName(BaseUtil.isEmpty(ca
					.getPublishDepartment()) ? "" : article
					.getPublishDepartment().getName());
			cab.setPublicTimeStr(DateUtil.dateToString(
					article.getPublishTime(),
					CommonConstant.DATE_WITHSECOND_FORMAT));
			cab.setContent(article.getContent());
			cab.setAffixName(article.getAffixName());
			cab.setImage(isImage(article.getAffixName()) ? "yes" : "no");
			commonArticleBeans.add(cab);
		}
		request.setAttribute("commonCategoryId", ca.getCommonCategory().getId());

		result.put("commonCategoryId", ca.getCommonCategory().getId());
		result.put("commonArticleBeans", commonArticleBeans);
		return forword("news/bbs_detail", result);
	}

	/**
	 * 根据栏目类别获取论坛列表
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findAllCommonArticlesByCommonCategory")
	@ResponseBody
	public Map<String, Object> findAllCommonArticlesByCommonCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonArticleBean commonArticleBean, PageBean pageBean)
			throws FrameworkException, Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		SysMngUserLoginBean loginBean = SessionUtils.getUser(request);

		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(commonArticleBean);
		Map<String, Object> searchCondition = new TreeMap<String, Object>();
		if (!BaseUtil.isEmpty(commonArticleBean)) {
			searchCondition.put("commonCategoryId",
					commonArticleBean.getCommonCategoryId());
			searchCondition.put("title", commonArticleBean.getTitle());
			searchCondition.put("publishDepartmentId",
					commonArticleBean.getPublishDepartmentId());
			searchCondition.put("startTime", commonArticleBean.getStartTime());
			searchCondition.put("endTime", commonArticleBean.getEndTime());
		}
		logger.debug("searchCondition=" + searchCondition);
		pageBean = BaseUtil.isEmpty(pageBean) ? new PageBean() : pageBean;
		pageBean.init(commonArticleService
				.getCommonArticleTotalRows(searchCondition));
		List<SysCommonArticle> commonArticles = commonArticleService
				.findAllCommonArticles(searchCondition, pageBean.getCurPage(),
						pageBean.getPageSize());
		List<CommonArticleBean> commonArticleBeans = new ArrayList<CommonArticleBean>();
		for (SysCommonArticle article : commonArticles) {
			CommonArticleBean bean = new CommonArticleBean();
			bean.setId(article.getId());
			bean.setEmotion("<img src='" + request.getContextPath()
					+ "/res_admin/images/bbs/" + article.getEmotion()
					+ ".gif'/>");
			bean.setTitle("<a href=" + request.getContextPath()
					+ "findChildDiscussionsById?id=" + article.getId() + ">"
					+ article.getTitle() + "</a>");
			bean.setPublishAuthorName(article.getAnonymous().equalsIgnoreCase(
					"Y") ? "匿名" : article.getPublishAuthor().getRealName());
			bean.setPublishAuthorId(article.getPublishAuthor().getId());
			bean.setDeleteButton("<input type='button' name='deleteButton' value='删除帖子'"
					+ (loginBean.getId().equals(
							article.getPublishAuthor().getId()) ? ""
							: "disabled='disabled'")
					+ "/>"
					+ (article.getLockedPost().equalsIgnoreCase("N") ? "<input type='button' name='lockedButton' value='锁定帖子'"
							+ (loginBean.getId().equals(
									article.getPublishAuthor().getId()) ? ""
									: "disabled='disabled'") + "/>"
							: "<input type='button' name='unlockedButton' value='恢复帖子'"
									+ (loginBean.getId().equals(
											article.getPublishAuthor().getId()) ? ""
											: "disabled='disabled'") + "/>"));
			bean.setReadCount(article.getReadCount());
			bean.setPublishTime(article.getPublishTime());
			List<SysCommonArticle> tempArticle = commonArticleService
					.getAllChildCommonAritcles(article.getId());
			int size = tempArticle.size();
			bean.setLastTimeAuthorName(size == 0 ? "" : (tempArticle.get(
					size - 1).getPublishTime()
					+ "|" + (tempArticle.get(size - 1).getAnonymous()
					.equalsIgnoreCase("Y") ? "匿名" : tempArticle.get(size - 1)
					.getPublishAuthor().getRealName())));

			commonArticleBeans.add(bean);
		}
		result.put("commonArticleBeans", commonArticleBeans);
		result.put("pageBean", pageBean);
		return result;
	}

	/**
	 * 通过多个论坛ID，系统删除论坛
	 * 
	 * @throws Exception
	 */
	@RequestMapping("deleteCommonArticles")
	@ResponseBody
	public Map<String, Object> deleteCommonArticles(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean,
			PageBean pageBean) throws Exception {
		commonArticleService.deleteCommonArticles(commonArticleBean
				.getDeleteIDs().split(","));
		return findAllCommonArticlesByCommonCategory(request, response,
				commonArticleBean, pageBean);
	}

	/**
	 * 通过论坛ID，系统锁定帖子
	 * 
	 * @throws Exception
	 */
	@RequestMapping("lockedCommonArticles")
	@ResponseBody
	public Map<String, Object> lockedCommonArticles(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean,
			PageBean pageBean) throws Exception {
		commonArticleService.lockedCommonArticles(commonArticleBean.getId());
		return findAllCommonArticlesByCommonCategory(request, response,
				commonArticleBean, pageBean);
	}

	/**
	 * 通过论坛ID，系统恢复锁定的帖子
	 * 
	 * @throws Exception
	 */
	@RequestMapping("unlockedCommonArticles")
	@ResponseBody
	public Map<String, Object> unlockedCommonArticles(
			HttpServletRequest request, HttpServletResponse response,
			CommonArticleBean commonArticleBean, PageBean pageBean)
			throws Exception {
		commonArticleService.unlockedCommonArticles(commonArticleBean.getId());
		return findAllCommonArticlesByCommonCategory(request, response,
				commonArticleBean, pageBean);
	}

	/**
	 * 通过论坛信息ID删除论坛信息
	 */
	@RequestMapping("deleteCommonArticleById")
	public ModelAndView deleteCommonArticleById(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws FrameworkException {
		if (BaseUtil.isEmpty(commonArticleBean))
			return forword("news/bbs_index", null);
		commonArticleService.deleteCommonArticleById(commonArticleBean.getId());
		request.setAttribute("commonCategoryId",
				commonArticleBean.getCommonCategoryId());
		return forword("news/bbs_index", null);
	}

	/**
	 * 论坛INDEX界面
	 */
	@RequestMapping("discussionIndex")
	public ModelAndView discussionIndex(HttpServletRequest request,
			CommonArticleBean commonArticleBean) throws FrameworkException {
		if (BaseUtil.isEmpty(commonArticleBean))
			return null;
		request.setAttribute("commonCategoryId",
				commonArticleBean.getCommonCategoryId());

		return forword("news/bbs_index", null);
	}

	/**
	 * 进入用户添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("createCommonArticle")
	public ModelAndView createCommonArticle() throws FrameworkException {
		return forword("news/bbs_add", null);
	}

	/**
	 * 回复论坛
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("saveCommonArticleForReply")
	public ModelAndView saveCommonArticleForReply(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws Exception {
		if (BaseUtil.isEmpty(commonArticleBean))
			return forword("news/bbs_detail", null);
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		SysCommonArticle commonArticle = new SysCommonArticle();
		commonArticle.setFlag("discussion");
		commonArticle.setEmotion(commonArticleBean.getEmotion());
		commonArticle.setTitle(commonArticleBean.getTitle());
		commonArticle.setReadCount(new Integer(0));
		commonArticle.setPublishTime(new Date());
		if (BaseUtil.isEmpty(commonArticleBean.getPublishAuthorName()))
			commonArticle.setAnonymous("N");
		else
			commonArticle.setAnonymous("Y");
		commonArticle.setLockedPost("N");
		commonArticle.setPublishAuthor(sysUserService
				.findSysUserById(sysUserLoginBean.getId()));
		commonArticle.setPublishDepartment(sysOrganizationService
				.findSysOrganizationById(sysUserLoginBean.getOrganizationId()));
		commonArticle.setContent(BaseUtil.isEmpty(commonArticleBean
				.getContent()) ? "&nbsp;" : commonArticleBean.getContent());
		commonArticle
				.setCommonCategory(commonCategoryService
						.findCommonCategoryById(commonArticleBean
								.getCommonCategoryId()));
		if (!BaseUtil.isEmpty(commonArticleBean.getId())) {
			commonArticle.setCommonArticle(commonArticleService
					.findCommonArticleById(commonArticleBean.getId()));
		}
		commonArticleService.save(commonArticle);
		request.setAttribute("commonCategoryId",
				commonArticleBean.getCommonCategoryId());
		return findChildDiscussionsById(request, response, commonArticleBean);
	}

	/**
	 * 保存论坛
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("saveCommonArticle")
	public ModelAndView saveCommonArticle(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws FrameworkException {
		if (BaseUtil.isEmpty(commonArticleBean))
			return forword("news/bbs_index", null);
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		SysCommonArticle commonArticle = new SysCommonArticle();
		commonArticle.setFlag("discussion");
		commonArticle.setEmotion(commonArticleBean.getEmotion());
		commonArticle.setTitle(commonArticleBean.getTitle());
		commonArticle.setReadCount(new Integer(0));
		commonArticle.setPublishTime(new Date());
		if (BaseUtil.isEmpty(commonArticleBean.getPublishAuthorName()))
			commonArticle.setAnonymous("N");
		else
			commonArticle.setAnonymous("Y");
		commonArticle.setLockedPost("N");
		commonArticle.setPublishAuthor(sysUserService
				.findSysUserById(sysUserLoginBean.getId()));
		commonArticle.setPublishDepartment(sysOrganizationService
				.findSysOrganizationById(sysUserLoginBean.getOrganizationId()));
		commonArticle.setContent(BaseUtil.isEmpty(commonArticleBean
				.getContent()) ? "&nbsp;" : commonArticleBean.getContent());
		commonArticle
				.setCommonCategory(commonCategoryService
						.findCommonCategoryById(commonArticleBean
								.getCommonCategoryId()));
		if (!BaseUtil.isEmpty(commonArticleBean.getId())) {
			commonArticle.setCommonArticle(commonArticleService
					.findCommonArticleById(commonArticleBean.getId()));
		}
		commonArticleService.save(commonArticle);
		// LogUtil.getInstance().addExecution(commonArticle,
		// "论坛标题：" + commonArticle.getTitle());
		request.setAttribute("commonCategoryId",
				commonArticleBean.getCommonCategoryId());
		return forword("news/bbs_index", null);
	}

	private boolean isImage(String affixName) {
		if (BaseUtil.isEmpty(affixName))
			return false;
		if (affixName.lastIndexOf(".gif") > -1
				|| affixName.lastIndexOf(".jpg") > -1
				|| affixName.lastIndexOf(".bmp") > -1)
			return true;
		return false;
	}

	/**
	 * ************************************************************信息中心*********
	 * **************************************************************
	 */
	/**
	 * 根据信息id导出附件
	 */
	@RequestMapping("downImageOrAffix")
	@ResponseBody
	public String downImageOrAffix(CommonArticleBean commonArticleBean)
			throws FrameworkException, Exception {
		SysCommonArticle ca = commonArticleService
				.findCommonArticleById(commonArticleBean.getId());
		// AttachmentUtil.download(ca.getAffixName(), ca.getAffix());
		return null;
	}

	/**
	 * 根据信息中心类别获取信息列表
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findAllNewsCommonArticlesByCommonCategory")
	@ResponseBody
	public Map<String, Object> findAllNewsCommonArticlesByCommonCategory(
			HttpServletRequest request, HttpServletResponse response,
			CommonArticleBean commonArticleBean, PageBean pageBean)
			throws FrameworkException, Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		// jqGrid查询转码-utf-8
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);

		BaseUtil.decodeObject(commonArticleBean);
		Map<String, Object> searchCondition = new TreeMap<String, Object>();
		if (!BaseUtil.isEmpty(commonArticleBean)) {
			searchCondition.put("commonCategoryId",
					commonArticleBean.getCommonCategoryId());
			searchCondition.put("title", commonArticleBean.getTitle());
			searchCondition.put("startTime", commonArticleBean.getStartTime());
			searchCondition.put("endTime", commonArticleBean.getEndTime());
		}
		searchCondition.put("userId", sysUserLoginBean.getId());
		searchCondition.put("orgId", sysUserLoginBean.getOrganizationId());
		logger.debug("searchCondition=" + searchCondition);
		pageBean = BaseUtil.isEmpty(pageBean) ? new PageBean() : pageBean;
		pageBean.init(commonArticleService
				.getNewsCommonArticleTotalRows(searchCondition));
		List<SysCommonArticle> commonArticles = commonArticleService
				.findAllNewsCommonArticles(searchCondition,
						pageBean.getCurPage(), pageBean.getPageSize());
		List<CommonArticleBean> commonArticleBeans = new ArrayList<CommonArticleBean>();
		for (SysCommonArticle article : commonArticles) {
			CommonArticleBean bean = new CommonArticleBean();
			bean.setId(article.getId());
			bean.setTitle("<a href=findNewsById?commonArticleBean.id="
					+ article.getId() + ">" + article.getTitle() + "</a>");
			bean.setDeleteButton("<input type='button' name='deleteButton' value='删除'"
					+ (sysUserLoginBean.getId().equals(
							article.getPublishAuthor().getId()) ? ""
							: "disabled='disabled'") + "/>");
			bean.setPublishDepartmentName(BaseUtil.isEmpty(article
					.getPublishDepartment()) ? "" : article
					.getPublishDepartment().getName());
			bean.setPublishAuthorName(article.getPublishAuthor().getRealName());
			bean.setReadCount(article.getReadCount());
			bean.setPublishTime(article.getPublishTime());
			commonArticleBeans.add(bean);
		}
		result.put("pageBean", pageBean);
		result.put("commonArticleBeans", commonArticleBeans);
		return result;
	}

	@RequestMapping("createNewsCommonArticle")
	public ModelAndView createNewsCommonArticle() throws FrameworkException {
		return forword("news/news_add", null);
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@RequestMapping("saveNewsCommonArticle")
	public ModelAndView saveNewsCommonArticle(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws FrameworkException, Exception {
		if (BaseUtil.isEmpty(commonArticleBean))
			return forword("news/news_index", null);
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		SysUser sysUser = sysUserService.findSysUserById(sysUserLoginBean
				.getId());
		SysCommonArticle commonArticle = new SysCommonArticle();
		commonArticle.setTitle(commonArticleBean.getTitle());
		commonArticle.setReadCount(new Integer(0));
		commonArticle.setPublishTime(new Date());
		commonArticle.setFlag("news");
		commonArticle.setPublishAuthor(sysUserService
				.findSysUserById(sysUserLoginBean.getId()));
		List<SysOrganization> sysOrgs = sysUser.getSysOrganizations();
		SysOrganization sysOrg = null;
		if (!BaseUtil.isEmpty(sysOrgs) && sysOrgs.size() > 0)
			sysOrg = sysOrgs.get(0);
		commonArticle.setPublishDepartment(sysOrg);
		commonArticle.setContent(BaseUtil.isEmpty(commonArticleBean
				.getContent()) ? "&nbsp;" : commonArticleBean.getContent());
		commonArticle.setScope(commonArticleBean.getScope());
		if (commonArticleBean.getScope().equals("yes")) {
			if (!BaseUtil.isEmpty(commonArticleBean.getReceiveDepartIDS())) {
				String[] receiveDepartIDS = commonArticleBean
						.getReceiveDepartIDS().split(",");
				for (String receiveDepartID : receiveDepartIDS) {
					SysOrganization receiveDepart = sysOrganizationService
							.findSysOrganizationById(receiveDepartID);
					commonArticle.getReceiveDeparts().add(receiveDepart);
				}

			}
			if (!BaseUtil.isEmpty(commonArticleBean.getReceiveEmpIDS())) {
				String[] receiveEmpIDS = commonArticleBean.getReceiveEmpIDS()
						.split(",");
				for (String receiveEmpID : receiveEmpIDS) {
					SysUser receiveEmp = sysUserService
							.findSysUserById(receiveEmpID);
					commonArticle.getReceiveEmps().add(receiveEmp);
				}
				SysUser sendSysUser = sysUserService
						.findSysUserById(sysUserLoginBean.getId());
				commonArticle.getReceiveEmps().add(sendSysUser);

			}
		}
		commonArticle
				.setCommonCategory(commonCategoryService
						.findCommonCategoryById(commonArticleBean
								.getCommonCategoryId()));
//		if (!BaseUtil.isEmpty(getMyFile())) {
//			commonArticle.setAffix(Hibernate.(new FileInputStream(
//					getMyFile())));
//			commonArticle.setAffixName(getMyFileFileName());
//		}
		commonArticleService.save(commonArticle);
		// LogUtil.getInstance().addExecution(commonArticle,
		// "新闻标题：" + commonArticle.getTitle());
		request.setAttribute("commonCategoryId",
				commonArticleBean.getCommonCategoryId());
		return forword("news/news_index", null);
	}

	/**
	 * 通过信息ID删除信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping("deleteNewsCommonArticles")
	@ResponseBody
	public Map<String, Object> deleteNewsCommonArticles(
			HttpServletRequest request, HttpServletResponse response,
			CommonArticleBean commonArticleBean, PageBean pageBean)
			throws Exception {
		commonArticleService.deleteCommonArticles(commonArticleBean
				.getDeleteIDs().split(","));
		return findAllNewsCommonArticlesByCommonCategory(request, response,
				commonArticleBean, pageBean);

	}

	/**
	 * 通过ID获取信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findNewsById")
	public ModelAndView findNewsById(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws Exception {
		SysCommonArticle ca = commonArticleService
				.findCommonArticleById(commonArticleBean.getId());
		ca.setReadCount(ca.getReadCount() + 1);
		commonArticleService.update(ca);
		commonArticleBean.setId(ca.getId());
		commonArticleBean.setTitle(ca.getTitle());
		commonArticleBean.setPublishAuthorName(ca.getPublishAuthor()
				.getRealName());
		commonArticleBean.setPublishTime(ca.getPublishTime());
		commonArticleBean.setPublishDepartmentName(BaseUtil.isEmpty(ca
				.getPublishDepartment()) ? "" : ca.getPublishDepartment()
				.getName());
		commonArticleBean.setAffixName(ca.getAffixName());
		String tempUser = "";
		List<SysUser> receiveUsers = ca.getReceiveEmps();
		for (SysUser sysUser : receiveUsers) {
			tempUser += sysUser.getRealName() + "|";
		}
		if (!BaseUtil.isEmpty(tempUser))
			tempUser = tempUser.substring(0, tempUser.length() - 1);
		commonArticleBean.setReceiveEmps(tempUser);
		String tempOrg = "";
		List<SysOrganization> receiveOrgs = ca.getReceiveDeparts();
		for (SysOrganization org : receiveOrgs) {
			tempOrg += org.getName() + "|";
		}
		if (!BaseUtil.isEmpty(tempOrg))
			tempOrg = tempOrg.substring(0, tempOrg.length() - 1);
		commonArticleBean.setReceiveDeparts(tempOrg);
		commonArticleBean.setContent(ca.getContent());
		request.setAttribute("commonCategoryId", ca.getCommonCategory().getId());
		return forword("news/news_detai", null);
	}

	@RequestMapping("newsIndex")
	public ModelAndView newsIndex(HttpServletRequest request,
			HttpServletResponse response, CommonArticleBean commonArticleBean)
			throws FrameworkException {
		if (BaseUtil.isEmpty(commonArticleBean))
			return null;
		request.setAttribute("commonCategoryId",
				commonArticleBean.getCommonCategoryId());

		return forword("news/news_index", null);
	}

	/**
	 * 下载文件
	 * 
	 * @param servletOutputStream
	 * @param blob
	 * @throws Exception
	 */
	private void commonDownAffix(ServletOutputStream servletOutputStream,
			Blob blob) throws Exception {
		InputStream in = null;
		try {
			in = new BufferedInputStream(blob.getBinaryStream());
			// byte[] buf = new byte[AttachmentConstant.BUFFER_SIZE];
			// int hasRead = 0;
			// while ((hasRead = in.read(buf)) > 0) {
			// servletOutputStream.write(buf, 0, hasRead);
			// }
			// servletOutputStream.flush();
		} finally {
			if (!BaseUtil.isEmpty(in))
				in.close();
			if (!BaseUtil.isEmpty(servletOutputStream))
				servletOutputStream.close();

		}
		// 回收下
		System.gc();

	}

	private String doClobToString(Clob clob) {
		if (null == clob)
			return null;
		Reader reader = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader bfReader = null;
		try {
			reader = clob.getCharacterStream();
			// 得到流
			bfReader = new BufferedReader(reader);
			String s = bfReader.readLine();
			while (null != s) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = bfReader.readLine();
			}
		} catch (Exception e) {
			e.getCause();
		} finally {
			try {
				if (null != reader)
					reader.close();
				if (null != bfReader)
					bfReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (sb.toString().equals(""))
			return null;
		else
			return sb.toString();
	}

}
