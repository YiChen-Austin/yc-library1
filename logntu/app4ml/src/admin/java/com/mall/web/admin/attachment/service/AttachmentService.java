/**
 * 
 */
package com.mall.web.admin.attachment.service;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.attachment.dao.AttachmentDao;
import com.mall.web.admin.common.domain.SysAttachment;


/**
 * @功能说明：传输共建业务层
 * @作者： 白勇红
 * @创建日期： 2010-10-17 @
 */
@Service("attachmentService")
public class AttachmentService {
	@Resource(name = "attachmentDao")
	private AttachmentDao attachmentDao;

	/**
	 * 添加附件信息
	 * 
	 * @param sysRole
	 * @throws FrameworkException
	 */
	@Transactional
	public void saveAttachment(SysAttachment attachment, File file)
			throws FrameworkException {
		this.attachmentDao.saveAttachment(attachment, file);
	}

	/**
	 * 附件信息
	 * 
	 * @param sysRoleId
	 * @return
	 * @throws FrameworkException
	 */
	public SysAttachment findAttachment(String attachmentId)
			throws FrameworkException {
		return this.attachmentDao.get(attachmentId);
	}

	@Transactional
	public void deleteAttachment(String attachmentId) throws FrameworkException {
		//SysAttachment attachment = this.findAttachment(attachmentId);
		this.attachmentDao.deleteById(attachmentId);
	}
}
