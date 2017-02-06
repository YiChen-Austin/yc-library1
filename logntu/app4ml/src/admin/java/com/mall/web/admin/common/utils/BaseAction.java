package com.mall.web.admin.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.util.BaseUtil;

public class BaseAction {

	public final static String SUCCESS = "success";

	public final static String MSG = "msg";

	public final static String DATA = "data";

	public final static String LOGOUT_FLAG = "logoutFlag";

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// SimpleDateFormat datetimeFormat = new SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss");
		// datetimeFormat.setLenient(false);
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(
		// datetimeFormat, true));
		binder.registerCustomEditor(int.class, new IntegerEditor());
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 所有ActionMap 统一从这里获取
	 * 
	 * @return
	 */
	public Map<String, Object> getRootMap() {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		// 添加url到 Map中
		// rootMap.putAll(URLUtils.getUrlMap());
		return rootMap;
	}

	public ModelAndView forword(String viewName, Map<String, Object> context) {
		return new ModelAndView(viewName, context);
	}

	public ModelAndView forword(ModelAndView mv, Map<String, Object> context) {
		return new ModelAndView(mv.getViewName(), context);
	}

	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}

	/**
	 * 
	 * 提示成功信息
	 * 
	 * @param message
	 * 
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 
	 * 提示失败信息
	 * 
	 * @param message
	 * 
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 
	 * 保存上传图片
	 * 
	 * @param message
	 * 
	 */
	public String saveFile(String pre, String name, MultipartFile file)
			throws IOException {
		if (BaseUtil.isEmpty(file)||file.isEmpty()) {
			return null;
		}
		String fix = file.getOriginalFilename().indexOf(".") >= 0 ? file
				.getOriginalFilename().substring(
						file.getOriginalFilename().indexOf(".")) : "";
		String rand = name + new Date().getTime();
		File f = new File(pre);
		if (!f.exists()) {
			f.mkdirs();
		}
		String path = pre + "/" + rand + fix;
		File uploadFile = new File(path);
		FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
		return rand + fix;
	}

}
