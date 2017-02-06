/**
 * 
 */
package com.mall.web.admin.news.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.admin.common.utils.BaseAction;

/**
 * @功能说明：eWebEditor HTML在线系统文件上传功能
 * @作者：xgyin
 * @创建日期： 2010-10-30 @
 */

@Controller
@RequestMapping("/editor/")
public class WebEditorAction extends BaseAction {
	private static final long serialVersionUID = -8947110789644502324L;
	private static Logger logger = Logger.getLogger(WebEditorAction.class);

	@RequestMapping("eWebEditorUpload")
	public ModelAndView eWebEditorUpload(HttpServletRequest request,
			HttpServletResponse response, File uploadfile,
			String uploadfileContentType, String uploadfileFileName)
			throws FrameworkException, IOException {
		ServletContext sc = request.getServletContext();
		String fileName = DateUtil.dateToString("yyyyMMddhhmmssS");
		String sOriginalFileName = uploadfileFileName;
		String sSaveFileName = fileName
				+ "."
				+ sOriginalFileName.substring(
						sOriginalFileName.indexOf(".") + 1,
						sOriginalFileName.length());
		String sPathFileName = sc.getRealPath("/") + "eWebEditor/admin/upload/"
				+ sSaveFileName;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File file = new File(sPathFileName);
		try {
			fis = new FileInputStream(uploadfile);
			fos = new FileOutputStream(file);
			byte[] readData = new byte[1024];
			while (fis.read(readData) != -1)
				fos.write(readData);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (!BaseUtil.isEmpty(fis))
				fis.close();
			if (!BaseUtil.isEmpty(fos))
				fos.close();
		}
		// request.setAttribute("sSaveFileName", sSaveFileName);
		// request.setAttribute("sOriginalFileName", sOriginalFileName);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sSaveFileName", sSaveFileName);
		result.put("sOriginalFileName", sOriginalFileName);
		return forword("eWebEditor/upload", result);
	}
}
