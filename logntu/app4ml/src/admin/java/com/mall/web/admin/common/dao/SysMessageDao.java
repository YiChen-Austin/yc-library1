/**
 * 
 */
package com.mall.web.admin.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.common.domain.SysMessage;


/**
 * @功能说明：消息对象数据访问层
 * @作者： xgyin
 * @创建日期： 2010-11-14 @
 */
@Repository("sysMessageDao")
public class SysMessageDao extends BaseDao<SysMessage> {
	/**
	 * 获取待处理数据（按照人员区分）
	 * 
	 * 返回数据：人员姓名、登录名称、邮箱、待处理数
	 */
	public List<Map<String, Object>> findMsgNotice() throws FrameworkException {
		String msgSql = "select sysu.realname,sysu.username,sysu.identitycard,msg.total from ("
				+ " select m.ownerid,count(*) total from sys_message m where m.ownerid in(select su.id from sysuser su where su.identitycard is not null) group by m.ownerid"
				+ ") msg left join sysuser sysu on sysu.id=msg.ownerid";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(msgSql,
				new Object[] {});
		return result;

	}

	/**
	 * 根据人员ID,消息状态获取消息集合
	 * 
	 * @param toId
	 * @param isRead
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<SysMessage> getPersonMsg(String toId, String isRead)
			throws FrameworkException {
		String msgSql = "select message.id, message.fromId, message.toId, message.content, "
				+ "message.isRead, message.ownerId, message.edate, sysuser.realname as sendName, message.href  "
				+ "from sys_message as message left join sys_user as sysuser on message.fromId = sysuser.id "
				+ "where message.toId='"
				+ toId
				+ "' and message.ownerId = '"
				+ toId
				+ "' and message.isRead='"
				+ isRead
				+ "' order by edate desc";
		List<Object[]> result = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(msgSql).list();
		List<SysMessage> messages = new ArrayList<SysMessage>();
		for (Object[] obj : result) {
			SysMessage message = new SysMessage();
			message.setId((String) obj[0]);
			message.setFromId((String) obj[1]);
			message.setToId((String) obj[2]);
			message.setContent((String) obj[3]);
			message.setIsRead((String) obj[4]);
			message.setOwnerId((String) obj[5]);
			message.setEdate((Date) obj[6]);
			message.setSendName((String) obj[7]);
			message.setHref((String) obj[8]);
			messages.add(message);

		}
		return messages;

	}

	/**
	 * 
	 * @param fromId
	 * @param toId
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<SysMessage> getHistoryMsg(String fromId, String toId)
			throws FrameworkException {
		String msgSql = "select message.id, message.fromId, message.toId, message.content, "
				+ "message.isRead, message.ownerId, message.edate, sysuser.realname as sendName, message.href "
				+ "from sys_message left join sysuser on message.fromId = sysuser.id "
				+ "where ((message.toId = '"
				+ toId
				+ "' and message.fromId = '"
				+ fromId
				+ "')"
				+ " or (message.toId = '"
				+ fromId
				+ "' and message.fromId = '"
				+ toId
				+ "'))"
				+ " and message.ownerId = '"
				+ fromId
				+ "' and message.isRead='yes' order by edate desc";
		List<Object[]> result = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(msgSql).list();
		List<SysMessage> messages = new ArrayList<SysMessage>();
		for (Object[] obj : result) {
			SysMessage message = new SysMessage();
			message.setId((String) obj[0]);
			message.setFromId((String) obj[1]);
			message.setToId((String) obj[2]);
			message.setContent((String) obj[3]);
			message.setIsRead((String) obj[4]);
			message.setOwnerId((String) obj[5]);
			message.setEdate((Date) obj[6]);
			message.setSendName((String) obj[7]);
			message.setHref((String) obj[8]);
			messages.add(message);

		}
		return messages;
	}

	/**
	 * 清空历史信息
	 * 
	 * @param fromId
	 * @param toId
	 * @throws FrameworkException
	 */
	public void clearHistoryMsgs(String fromId, String toId)
			throws FrameworkException {
		String msgSql = "DELETE FROM SysMessage WHERE ((message.toId = '" + toId
				+ "' AND message.fromId = '" + fromId + "')"
				+ " OR (message.toId = '" + fromId + "' AND message.fromId = '"
				+ toId + "'))" + " AND message.ownerId = '" + fromId
				+ "' AND message.isRead='yes'";
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(msgSql).executeUpdate();

	}

	/**
	 * 根据 标示ID删除提醒信息
	 * 
	 * @param taskID
	 * @throws FrameworkException
	 */
	public void deleteMessageByTaskID(String taskID) throws FrameworkException {
		String msgSql = "DELETE FROM SysMessage WHERE taskID ='" + taskID + "'";
		this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery(msgSql).executeUpdate();

	}

}
