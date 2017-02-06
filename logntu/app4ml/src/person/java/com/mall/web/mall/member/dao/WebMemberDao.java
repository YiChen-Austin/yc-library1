package com.mall.web.mall.member.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.mail.MailActivationCodeUtil;
import com.mall.web.mall.domain.Member;

@Repository("webMemberDao")
public class WebMemberDao extends BaseDao<Member> {
	/**
	 * 删除用户信息
	 * 
	 * @param userId 用户编号
	 */
	public void removeUserById(int userId) {
		final String sql = "delete from ml_member where user_id=" + userId;
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				Statement stmt = connection.createStatement();
				stmt.executeUpdate(sql);
				connection.commit(); // 事务提交
			}
		});
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return 会员信息
	 */
	public Member getUserInfo(int userId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userId };
		hql.append("from Member where userId=? ");
		Member bean = this.getSingleEntity(hql.toString(), condition);
		return bean;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return 会员信息
	 */
	public Member getUserByToken(String token) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { token };
		hql.append("from Member m where exists (from MemberAutoLogin b where b.cookieValue=? and b.userId=m.userId) ");
		List<Member> list = this.list(hql.toString(), 0, 1, condition);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return 会员信息
	 */
	public Member getUserInfo(String userName) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userName };
		hql.append("from Member where userName=? ");
		Member bean = this.getSingleEntity(hql.toString(), condition);
		return bean;
	}
	

	public Member getUserInfo(String userName, String password) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userName, password };
		hql.append("from Member where userName=? and password=?");
		Member bean = this.getSingleEntity(hql.toString(), condition);
		return bean;
	}
	/**
	 * 更新手机号码
	 * 
	 * @author sol
	 * @param userId
	 *            用户id
	 * @param phone
	 *            手机号码
	 * @throws FrameworkException
	 */
	public boolean updatePhone(int userId, String phone) throws FrameworkException {
		Member member = this.get(userId);
		if (member == null) {
			return false;
		}

		member.setPhoneMob(phone);
		this.update(member);
		return true;
	}
	
	/**
	 * 更新邮箱
	 * 
	 * @author sol
	 * @param userId
	 *            用户id
	 * @param email
	 *            邮箱
	 * @throws FrameworkException
	 */
	public boolean updateEmail(int userId, String email) throws FrameworkException {
		Member member = this.get(userId);
		if (member == null) {
			return false;
		}

		member.setEmail(email);
		this.update(member);
		return true;
	}
	/**
	 * 更新`邮箱是否激活`标识。
	 * 
	 * @author sol
	 * @param userId
	 *            用户ID。
	 * @param isEmailActivated
	 *            `邮箱是否激活`标识。
	 * @return 操作是否成功。
	 * @throws FrameworkException
	 */
	public boolean updateIsEmailActivated(int userId, String isEmailActivated) throws FrameworkException {
		Member member = this.get(userId);
		if (member == null) {
			return false;
		}
		member.setIsActivated(isEmailActivated);
		if ("1".equalsIgnoreCase(isEmailActivated)) {
			// 验证成功后立即重置激活码
			member.setActivateCode(MailActivationCodeUtil.generate());
		}
		this.update(member);
		return true;
	}

	/**
	 * 获取登录密码。
	 * 
	 * @param userId
	 *            用户编号
	 * @return 操作是否成功。
	 */
	public String getPassword(int userId) {
		Member member = getUserInfo(userId);
		return member != null ? member.getPassword() : "";
	}

	/**
	 * 获取登录密码。
	 * 
	 * @param userId
	 *            用户编号
	 * @return 操作是否成功。
	 * @throws FrameworkException
	 */
	public boolean updatePassword(int userId, String password) throws FrameworkException {
		Member member = this.get(userId);
		if (member != null) {
			member.setPassword(password);
			this.update(member);
			return true;
		}
		return false;
	}

	/**
	 * @Description(功能描述) : 升级用户级别 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int plusUgrade(int ugrade, int ugradeValue) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_member member set ugrade=ugrade+1 ");
		sql.append(" where ugrade_value>=? and ugrade<?");
		return this.jdbcTemplate.update(sql.toString(), new Object[] { ugradeValue, ugrade });
	}
}
