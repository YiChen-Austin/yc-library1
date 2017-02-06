package com.mall.web.admin.common.tld;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.service.SysResourceService;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

public class ResourcePrivilegeTag extends RequestContextAwareTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -668071629236031745L;

	private String _userId;
	private HttpSession _session;
	/************************/
	private String resourceId;// 资源ID
	private String resouceType;// 资源类型
	private String resouceAction;// 资源功能
	private String jspId;// 资源所在jsp
	private String resouceName;// 资源名称
	// private String resouceValue;// 资源值
	private String resouceClass;// 资源样式
	private String resouceStyle;// 资源风格
	private String dataOption;// 扩展字段
	/************************/

	private SysResourceService sysResourceService;

	/************************/

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResouceType() {
		return resouceType;
	}

	public void setResouceType(String resouceType) {
		this.resouceType = resouceType;
	}

	public String getResouceAction() {
		return resouceAction;
	}

	public void setResouceAction(String resouceAction) {
		this.resouceAction = resouceAction;
	}

	public String getJspId() {
		return jspId;
	}

	public void setJspId(String jspId) {
		this.jspId = jspId;
	}

	public String getResouceName() {
		return resouceName;
	}

	public void setResouceName(String resouceName) {
		this.resouceName = resouceName;
	}

	public String getResouceClass() {
		return resouceClass;
	}

	public void setResouceClass(String resouceClass) {
		this.resouceClass = resouceClass;
	}

	public String getResouceStyle() {
		return resouceStyle;
	}

	public void setResouceStyle(String resouceStyle) {
		this.resouceStyle = resouceStyle;
	}

	public String getDataOption() {
		return dataOption;
	}

	public void setDataOption(String dataOption) {
		this.dataOption = dataOption;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		// 初始化
		initPara();
		// 计算，并生产html
		evaluateHtml(this.pageContext.getOut());
		return 0;
	}

	private void initPara() {
		// 初始化数据库链接
		sysResourceService = (SysResourceService) this.getRequestContext()
				.getWebApplicationContext().getBean("sysResourceService");
		// 初始化登录用户信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (request != null && request.getSession() != null) {
			_session = request.getSession();
		}
		if (_session != null) {
			SysMngUserLoginBean loginUser = SessionUtils.getUser(request
					.getSession());
			if (loginUser != null)
				_userId = loginUser.getId();
		}
	}

	private void evaluateHtml(Writer writer) {
		try {
			boolean _result = false;
			if (_session != null && SessionUtils.isAdmin(_session)) {
				_result = true;
			} else {
				if (sysResourceService.getResouceCount(_userId, getJspId(),
						getResourceId()) > 0)
					_result = true;
			}
			if (_result) {
				StringBuilder str = new StringBuilder();
				if ("A".equalsIgnoreCase(resouceType)) {
					str.append("<a ");
					str.append("id='" + resourceId + "' ");
					if (!BaseUtil.isEmpty(resouceAction))
						str.append("href='" + resouceAction + "' ");
					if (!BaseUtil.isEmpty(resouceClass))
						str.append("class='" + resouceClass + "' ");
					if (!BaseUtil.isEmpty(resouceStyle))
						str.append("style='" + resouceStyle + "' ");
					if (!BaseUtil.isEmpty(dataOption))
						str.append(dataOption + " ");
					str.append(">");
					if (!BaseUtil.isEmpty(resouceName))
						str.append(resouceName);
					str.append("</a>");
				} else {
					str.append("<input ");
					str.append("id='" + resourceId + "' ");
					str.append("type='" + resouceType + "' ");
					if (!BaseUtil.isEmpty(resouceAction))
						str.append("onClick='" + resouceAction + "' ");
					if (!BaseUtil.isEmpty(resouceName))
						str.append("value='" + resouceName + "' ");
					if (!BaseUtil.isEmpty(resouceClass))
						str.append("class='" + resouceClass + "' ");
					if (!BaseUtil.isEmpty(resouceStyle))
						str.append("style='" + resouceStyle + "' ");
					if (!BaseUtil.isEmpty(dataOption))
						str.append(dataOption + " ");
					str.append("/>");
				}
				//System.out.println(str.toString());
				writer.write(str.toString());
			}
		} catch (Exception ex) {
			Logger.getLogger(ResourcePrivilegeTag.class.getName()).warn(
					"ResourcePrivilege", ex);
		}
	}
}