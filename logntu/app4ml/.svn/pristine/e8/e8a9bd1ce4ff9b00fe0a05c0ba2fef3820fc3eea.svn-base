/**
 * 
 */
package com.mall.web.admin.attachment.action;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.web.admin.attachment.service.AttachmentService;
import com.mall.web.admin.attachment.vo.AttachmentBean;
import com.mall.web.admin.common.domain.SysAttachment;
import com.mall.web.admin.common.utils.BaseAction;

/**
 * @功能说明：共建共享控制层
 * @作者： 白勇红
 * @创建日期： 2010-5-7 @
 */
@Controller
@RequestMapping("/attachment/")
public class AttachmentAction extends BaseAction {
	@Autowired
	public AttachmentService attachmentService;

	/**
	 * 下载
	 * 
	 * @return
	 * @throws Exception
	 */
	// @Action(value = "downloadAttachment", results = { @Result(name =
	// "success", location = "/page/settlement/settlement_detail.jsp") })
	// @SkipValidation()
	public String downloadAttachment(AttachmentBean attachmentBean)
			throws Exception {
		SysAttachment attachment = attachmentService
				.findAttachment(attachmentBean.getId());
		if (attachment == null || attachment.getFileValue() == null)
			return null;

		// 转换为当前浏览器支持的中文
		// String fileName =
		// JXLUtil.getInstance().getEncodeText(attachment.getFileName());
		// HttpServletResponse response = ServletActionContext.getResponse();
		// response.setCharacterEncoding(JXLConstant.UTF_ENCODING);
		// response.setContentType(attachment.getFileContentType());
		// response.setHeader(JXLConstant.HEADER, new StringBuffer(
		// JXLConstant.OUTPUTSTREAM_DOWN).append(";filename=").append(
		// fileName).toString());
		// InputStream input = null;
		// OutputStream output = null;
		// try {
		// input = attachment.getFileValue().getBinaryStream();
		// output = response.getOutputStream();
		// byte[] _byte = new byte[516];
		// int length = 0;
		// while ((length = input.read(_byte)) > 0) {
		// output.write(_byte, 0, length);
		// output.flush();
		// }
		// output.flush();
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// if (output != null)
		// output.close();
		// if (input != null)
		// input.close();
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// }
		return null;
	}
}
