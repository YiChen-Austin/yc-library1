/**
 * 
 */
package com.mall.web.admin.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.HqlUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.annotation.Auth;
import com.mall.web.admin.common.domain.SysDesktopDefend;
import com.mall.web.admin.common.domain.SysMessage;
import com.mall.web.admin.common.domain.SysSelfDesktopDefend;
import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.news.domain.SysCommonArticle;
import com.mall.web.admin.news.service.CommonArticleService;
import com.mall.web.admin.news.vo.CommonArticleBean;
import com.mall.web.admin.security.vo.DesktopDefendBean;
import com.mall.web.admin.security.vo.MessageBean;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.admin.system.service.SysDesktopDefendService;
import com.mall.web.admin.system.service.SysMessageService;

/**
 * @功能说明：桌面管理控制层
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */
@Controller
@RequestMapping("/page/")
public class DesktopDefendAction extends BaseAction {
	private static Logger logger = Logger.getLogger(DesktopDefendAction.class);
	@Autowired
	private SysDesktopDefendService sysDesktopDefendService;
	@Autowired
	private SysMessageService sysMessageService;
	@Autowired
	private CommonArticleService commonArticleService;

	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("desktop/weather")
	public ModelAndView weather() {
		return forword("desktop/weather", null);
	}

	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("desktop/google")
	public ModelAndView google() {
		return forword("desktop/google", null);
	}

	/**
	 * 桌面代办事项
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("findDesktopMessages")
	@ResponseBody
	public Map<String, Object> findDesktopMessages(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException {
		Map<String, Object> context = new HashMap<String, Object>();
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);

		List<SysMessage> msgs = this.sysMessageService
				.getPersonNotReadMsg(loginUser.getId());
		List<MessageBean> messageBeans = new ArrayList<MessageBean>();
		for (SysMessage msg : msgs) {
			MessageBean bean = new MessageBean();
			BeanUtils.copyProperties(msg, bean);
			messageBeans.add(bean);
		}
		context.put("messageBeans", messageBeans);
		return context;
	}

	/**
	 * 桌面公告
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("findDesktopNews")
	@ResponseBody
	public Map<String, Object> findDesktopNews(HttpServletRequest request)
			throws FrameworkException {
		Map<String, Object> context = new HashMap<String, Object>();
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		List<SysCommonArticle> articles = this.commonArticleService
				.findDesktopNews(loginUser);
		List<CommonArticleBean> commonArticleBeans = new ArrayList<CommonArticleBean>();
		for (SysCommonArticle article : articles) {
			CommonArticleBean bean = new CommonArticleBean();
			bean.setId(article.getId());
			bean.setTitle("<a href=findNewsById?commonArticleBean.id="
					+ article.getId() + ">" + article.getTitle() + "</a>");
			bean.setPublishDepartmentName(BaseUtil.isEmpty(article
					.getPublishDepartment()) ? "" : article
					.getPublishDepartment().getName());
			bean.setPublishAuthorName(article.getPublishAuthor().getRealName());
			bean.setReadCount(article.getReadCount());
			bean.setPublishTime(article.getPublishTime());
			commonArticleBeans.add(bean);
		}
		context.put("commonArticleBeans", commonArticleBeans);
		return context;
	}

	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("findAlldesktopDefends")
	@ResponseBody
	public Map<String, Object> findAlldesktopDefends(
			DesktopDefendBean desktopDefendBean) throws FrameworkException,
			Exception {
		// jqGrid查询转码-utf-8
		BaseUtil.decodeObject(desktopDefendBean);
		Map<String, Object> searchCondition = HqlUtil.beanToMap(
				desktopDefendBean, SysDesktopDefend.class);

		Map<String, Object> context = new HashMap<String, Object>();

		PageBean pageBean = new PageBean();
		pageBean.init(this.sysDesktopDefendService
				.getDesktopDefendTotalRows(searchCondition));

		List<SysDesktopDefend> desktopDefends = this.sysDesktopDefendService
				.findAllDesktopDefends(searchCondition, pageBean.getCurPage(),
						pageBean.getPageSize());
		List<DesktopDefendBean> desktopDefendBeans = new ArrayList<DesktopDefendBean>();
		for (SysDesktopDefend defend : desktopDefends) {
			DesktopDefendBean bean = new DesktopDefendBean();
			BeanUtils.copyProperties(defend, bean);
			desktopDefendBeans.add(bean);
		}

		context.put("pageBean", pageBean);
		context.put("desktopDefendBeans", desktopDefendBeans);

		return context;
	}

	/**
	 * 通过桌面管理ID删除桌面管理信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteDesktopDefends")
	@ResponseBody
	public Map<String, Object> deleteSysDictionary(
			DesktopDefendBean desktopDefendBean) throws Exception {
		this.sysDesktopDefendService.deleteDesktopDefends(desktopDefendBean
				.getDeleteIDs().split(","));
		return findAlldesktopDefends(desktopDefendBean);
	}

	/**
	 * 通过桌面管理ID获取桌面管理信息
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("findDesktopDefend")
	public ModelAndView findDesktopDefend(DesktopDefendBean desktopDefendBean)
			throws FrameworkException, Exception {
		SysDesktopDefend desktopDefend = this.sysDesktopDefendService
				.findDesktopDefendById(desktopDefendBean.getId());
		BeanUtils.copyProperties(desktopDefend, desktopDefendBean);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("desktopDefendBean", desktopDefendBean);
		return forword("system/desktop_manage_edit", context);
	}

	/**
	 * 进入桌面管理添加页面
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("createDesktopDefend")
	public ModelAndView createDesktopDefend() throws FrameworkException {
		return forword("system/desktop_manage_edit", null);
	}

	/**
	 * 添加桌面管理
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("saveDesktopDefend")
	public ModelAndView saveDesktopDefend(DesktopDefendBean desktopDefendBean)
			throws FrameworkException, Exception {
		if (!BaseUtil.isEmpty(this.sysDesktopDefendService
				.findDesktopDefendyByName(desktopDefendBean.getTitle(),
						desktopDefendBean.getDivId()))) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("nameAndCnName", "已有区域业务名称或DIV标签ID!");
			return forword("system/desktop_manage_edit", context);
		}
		SysDesktopDefend desktopDefend = new SysDesktopDefend();
		BeanUtils.copyProperties(desktopDefendBean, desktopDefend);
		this.sysDesktopDefendService.saveDesktopDefend(desktopDefend);
		return forword("system/desktop_manage_list", null);
	}

	/**
	 * 修改桌面管理
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("modifyDesktopDefend")
	public ModelAndView modifyDesktopDefend(DesktopDefendBean desktopDefendBean)
			throws FrameworkException, Exception {
		if (!BaseUtil.isEmpty(this.sysDesktopDefendService
				.findDesktopDefendByCon(desktopDefendBean.getId(),
						desktopDefendBean.getTitle(),
						desktopDefendBean.getDivId()))) {

			Map<String, Object> context = new HashMap<String, Object>();
			context.put("nameAndCnName", "已有区域业务名称或DIV标签ID!");
			return forword("system/desktop_manage_edit", context);
		}
		SysDesktopDefend desktopDefend = this.sysDesktopDefendService
				.findDesktopDefendById(desktopDefendBean.getId());
		BeanUtils.copyProperties(desktopDefendBean, desktopDefend);
		this.sysDesktopDefendService.updateDesktopDefend(desktopDefend);
		return forword("system/desktop_manage_list", null);
	}

	@RequestMapping("findSelfDesktopDefends")
	@ResponseBody
	public Map<String, Object> findSelfDesktopDefends()
			throws FrameworkException {

		Map<String, Object> context = new HashMap<String, Object>();

		List<SysDesktopDefend> desktopDefends = this.sysDesktopDefendService
				.findSelfDesktopDefends();
		List<DesktopDefendBean> desktopDefendBeans = new ArrayList<DesktopDefendBean>();
		for (SysDesktopDefend defend : desktopDefends) {
			DesktopDefendBean bean = new DesktopDefendBean();
			BeanUtils.copyProperties(defend, bean);
			desktopDefendBeans.add(bean);
		}
		context.put("desktopDefendBeans", desktopDefendBeans);
		return context;
	}

	/**
	 * 设置自己的桌面菜单
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setSelfDesktopDefends")
	@ResponseBody
	public Map<String, Object> setSelfDesktopDefends(
			DesktopDefendBean desktopDefendBean) throws FrameworkException {
		this.sysDesktopDefendService.setSelfDesktopDefends(BaseUtil
				.isEmpty(desktopDefendBean.getDeleteIDs()) ? null
				: desktopDefendBean.getDeleteIDs().split(","));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("result", true);
		return context;
	}

	/**
	 * 改变桌面区域块的位置
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("setDesktopPositionds")
	@ResponseBody
	public String setDesktopPositionds(String placeId)
			throws FrameworkException {
		this.sysDesktopDefendService.setDesktopPositionds(placeId);
		return SUCCESS;
	}

	/**
	 * 删除自己桌面的桌面项
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping("deleteSelfDesktopDefendById")
	@ResponseBody
	public String deleteSelfDesktopDefendById(String selfDesktopDenfendId)
			throws FrameworkException {
		this.sysDesktopDefendService
				.deleteSelfDesktopDefendById(selfDesktopDenfendId);
		return SUCCESS;

	}

	/**
	 * 显示主页
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@Auth(verifyLogin = true,verifyURL=false)
	@RequestMapping("desktopCenter")
	public ModelAndView desktopCenter() throws FrameworkException {
		// 显示自己的桌面
		Map<String, List<DesktopDefendBean>> centerMap = new TreeMap<String, List<DesktopDefendBean>>();

		List<SysSelfDesktopDefend> desktopDefends = this.sysDesktopDefendService
				.findSelfDesktopCenters();
		centerMap.put("1", new ArrayList<DesktopDefendBean>());
		centerMap.put("2", new ArrayList<DesktopDefendBean>());
		for (SysSelfDesktopDefend selfDefind : desktopDefends) {
			SysDesktopDefend defend = selfDefind.getDesktopDefend();
			DesktopDefendBean bean = new DesktopDefendBean();
			bean.setId(selfDefind.getId());
			bean.setDefaultPosition(selfDefind.getDefaultPosition());
			bean.setDivId(defend.getDivId());
			bean.setRemark(defend.getRemark());
			bean.setTitle(defend.getTitle());
			List<DesktopDefendBean> beans = centerMap.get(bean
					.getDefaultPosition());
			beans.add(bean);
			centerMap.put(bean.getDefaultPosition(), beans);

		}
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("centerMap", centerMap);
		return forword("center", context);
	}

	/**
	 * 桌面公告
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	// @RequestMapping("findDesktopNews")
	// @ResponseBody
	// public String findDesktopNews() throws FrameworkException {
	// List<CommonArticle> articles = this.commonArticleService
	// .findDesktopNews();
	// for (CommonArticle article : articles) {
	// CommonArticleBean bean = new CommonArticleBean();
	// bean.setId(article.getId());
	// bean.setTitle("<a href=findNewsById?commonArticleBean.id="
	// + article.getId() + ">" + article.getTitle() + "</a>");
	// bean.setPublishDepartmentName(BaseUtil.isEmpty(article
	// .getPublishDepartment()) ? "" : article
	// .getPublishDepartment().getName());
	// bean.setPublishAuthorName(article.getPublishAuthor().getRealName());
	// bean.setReadCount(article.getReadCount());
	// bean.setPublishTime(article.getPublishTime());
	// this.commonArticleBeans.add(bean);
	// }
	// return SUCCESS;
	//
	// }
}
