package com.mall.web.admin.news.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mall.web.admin.common.utils.BaseAction;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;

@Controller
@RequestMapping("/editor/")
public class CkEditorAction extends BaseAction {

	// @RequestMapping("uploadPicture")
	// @ResponseBody
	public Map<String, Object> uploadPicture(HttpServletRequest request,
			HttpServletResponse response, MultipartFile myPic)
			throws IOException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		Map<String, Object> result = new HashMap<String, Object>();

		String fix = myPic.getOriginalFilename().indexOf(".") >= 0 ? myPic
				.getOriginalFilename().substring(
						myPic.getOriginalFilename().indexOf(".")) : "";
		String newName = new Date().getTime() + sysUserLoginBean.getId() + fix;

		String path1 = request.getServletContext().getRealPath("/upload")
				+ "/img/";
		File f = new File(path1);
		if (!f.exists()) {
			f.mkdirs();
		}
		String path = request.getServletContext().getRealPath("/upload")
				+ "/img/" + newName;

		String path2 = request.getContextPath();
		String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path2 + "/upload/img/"
				+ newName;

		File uploadFile = new File(path);
		FileUtils.copyInputStreamToFile(myPic.getInputStream(), uploadFile);

		result.put("url", url);
		return result;
	}

	@RequestMapping("uploadPicture")
	@ResponseBody
	public Map<String, Object> uploadPicture(HttpServletRequest request,
			MultipartFile myPic) throws IOException, Exception {
		SysMngUserLoginBean sysUserLoginBean = SessionUtils.getUser(request);
		Map<String, Object> result = new HashMap<String, Object>();
		String path = request.getServletContext().getRealPath("/upload")
				+ "/img/";
		String lpath = "/upload/img/"
				+ saveFile(path, sysUserLoginBean.getId(), myPic);
		String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + lpath;
		result.put("url", url);
		return result;
	}
}
