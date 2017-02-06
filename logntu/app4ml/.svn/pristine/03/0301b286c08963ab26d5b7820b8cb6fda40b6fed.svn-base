/**
 * 
 */
package com.mall.web.admin.system.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.dao.SysMessageDao;
import com.mall.web.admin.common.domain.SysMessage;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：消息业务层
 * @作者： xgyin
 * @创建日期： 2010-11-14 @
 */
@Service("sysMessageService")
public class SysMessageService {
	@Resource(name = "sysMessageDao")
	private SysMessageDao sysMessageDao;
	public static final String SYSTEM_MESSAGE = "0";

	/**
	 * 获取待处理数据（按照人员区分）
	 * 
	 * 返回数据：人员姓名、登录名称、邮箱、待处理数
	 */
	@DataSource(value = "admin")
	public List<Object[]> findMsgNotice() throws FrameworkException {
		List<Map<String, Object>> listM = sysMessageDao.findMsgNotice();
		List<Object[]> list = new LinkedList<Object[]>();
		for (Map<String, Object> map : listM) {
			Object[] objs = new Object[3];
			objs[0] = map.get("realname");
			objs[1] = map.get("identitycard");
			objs[2] = ((BigDecimal) map.get("total")).intValue();
			list.add(objs);
		}
		return list;
	}

	/**
	 * 获取发给给定人员,给定状态的信息集合
	 * 
	 * @param toId
	 * @param isRead
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysMessage> getPersonMsg(String toId, String isRead)
			throws FrameworkException {
		return this.sysMessageDao.getPersonMsg(toId, isRead);
	}

	@DataSource(value = "admin")
	public List<SysMessage> getPersonReadMsg(String toId)
			throws FrameworkException {
		return getPersonMsg(toId, "yes");
	}

	@DataSource(value = "admin")
	public List<SysMessage> getPersonNotReadMsg(String toId)
			throws FrameworkException {
		return getPersonMsg(toId, "no");
	}

	/**
	 * 获取发送人到接收人的所有历史消息
	 * 
	 * @param fromId
	 * @param toId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysMessage> getHistoryMsg(String fromId, String toId)
			throws FrameworkException {
		return this.sysMessageDao.getHistoryMsg(fromId, toId);

	}

	/**
	 * 保存消息
	 * 
	 * @param toId
	 *            -人员ID
	 * @param content
	 *            -提醒内容
	 * @param href
	 *            -业务操作URL
	 * @param taskID
	 *            -标示ID
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void saveMsg(String toId, String content, String href, String taskID)
			throws FrameworkException {
		// TODO 暂时屏蔽
		this.saveMsg(null, toId, content, href, taskID);
	}

	/**
	 * 根据标示ID删除提醒消息
	 * 
	 * @param taskID
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteMessageByTaskID(String taskID) throws FrameworkException {
		this.sysMessageDao.deleteMessageByTaskID(taskID);

	}

	/**
	 * 保存消息
	 * 
	 * @param fromId
	 * @param toId
	 * @param content
	 * @param href
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	@Transactional
	private void saveMsg(String fromId, String toId, String content,
			String href, String taskID) throws FrameworkException {
		if (BaseUtil.isEmpty(toId))
			throw new FrameworkException("必须有接收人!");
		String[] toIds = toId.split(",");
		for (int i = 0; i < toIds.length; i++) {
			SysMessage msg = new SysMessage();
			if (BaseUtil.isEmpty(fromId))
				msg.setFromId(SYSTEM_MESSAGE);
			else
				msg.setFromId(fromId);
			msg.setEdate(new Date());
			msg.setContent(content);
			msg.setHref(href);
			// create get msg
			msg.setToId(toIds[i]);
			msg.setIsRead("no");
			msg.setOwnerId(toIds[i]);
			msg.setTaskID(taskID);
			this.saveMsg(msg);
			// create sender msg
			if (!BaseUtil.isEmpty(fromId) && !fromId.equals(toIds[i])) {
				SysMessage tempMsg = new SysMessage();
				BeanUtils.copyProperties(msg, tempMsg);
				tempMsg.setId(null);
				tempMsg.setOwnerId(fromId);
				tempMsg.setIsRead("yes");
				this.saveMsg(tempMsg);
			}
		}
	}

	/**
	 * 保存消息
	 * 
	 * @param message
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void saveMsg(SysMessage message) throws FrameworkException {
		if (BaseUtil.isEmpty(message.getToId()))
			throw new FrameworkException("必须有接收人!");
		if (BaseUtil.isEmpty(message.getOwnerId()))
			message.setOwnerId(message.getToId());
		if (BaseUtil.isEmpty(message.getIsRead()))
			message.setIsRead("no");
		if (BaseUtil.isEmpty(message.getFromId()))
			message.setFromId(SYSTEM_MESSAGE);
		if (BaseUtil.isEmpty(message.getEdate()))
			message.setEdate(new Date());
		this.sysMessageDao.save(message);
	}

	/**
	 * 删除消息
	 * 
	 * @param msgIds
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteMsgs(String[] msgIds) throws FrameworkException {
		for (String msgId : msgIds) {
			this.deleteMsgById(msgId);
		}
	}

	/**
	 * 读取消息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DataSource(value = "admin")
	@Transactional
	public void readMsg(String id) throws FrameworkException {
		SysMessage msg = this.sysMessageDao.get(id);
		if (BaseUtil.isEmpty(msg))
			return;
		String taskID = msg.getTaskID();
		if (!BaseUtil.isEmpty(taskID))
			this.sysMessageDao.deleteMessageByTaskID(taskID);
		else
			this.sysMessageDao.delete(msg);
	}

	/**
	 * 删除消息
	 * 
	 * @param msgId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	private void deleteMsgById(String msgId) throws FrameworkException {
		this.sysMessageDao.deleteById(msgId);
	}

	/**
	 * 清空历史消息
	 * 
	 * @param fromId
	 * @param toId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void clearHistoryMsgs(String fromId, String toId)
			throws FrameworkException {
		this.sysMessageDao.clearHistoryMsgs(fromId, toId);
	}
}
