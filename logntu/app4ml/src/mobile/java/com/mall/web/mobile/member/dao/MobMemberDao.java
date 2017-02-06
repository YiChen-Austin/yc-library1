package com.mall.web.mobile.member.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.Member;

@Repository("mobMemberDao")
public class MobMemberDao extends BaseDao<Member> {

	/**
	 * 根据用户名和密码验证登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 会员信息
	 */
	public Member login(String userName, String password) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userName, password };
		hql.append("from Member where userName=? and password=?");
		List<Member> list = this.list(hql.toString(), condition);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 检测当前输入用户名是否存在
	 * 
	 * @param userName
	 *            会员名
	 * @return
	 */
	public Member checkMember(String userName) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userName };
		hql.append("from Member where userName=?");
		List<Member> list = this.list(hql.toString(), condition);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * 更新短信验证码
	 * 
	 * @param userId
	 *            用户id
	 */
	public void updateActivation(int userId, String validCode) {
		StringBuilder sql = new StringBuilder();
		sql.append(String.format(
				"update ml_member set activation ='%1$s' where user_id=%2$d",
				validCode, userId));
		jdbcTemplate.batchUpdate(sql.toString());
	}

	/**
	 * 更新手机号码
	 * 
	 * @param userId
	 *            用户id
	 * @param phone
	 *            手机号码
	 * @param validCode
	 *            验证码
	 */
	public boolean updateMobile(int userId, String phone, String validCode) {
		StringBuilder sql = new StringBuilder();
		sql.append(String
				.format("update ml_member set phone_mob ='%1$s' where user_id=%2$d and activation='%3$s'",
						phone, userId, validCode));
		if (jdbcTemplate.update(sql.toString()) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 更新手机号码
	 * 
	 * @param userId
	 *            用户id
	 * @param phone
	 *            手机号码
	 * @param validCode
	 *            验证码
	 */
	public boolean updateMobile(int userId, String phone) {
		StringBuilder sql = new StringBuilder();
		sql.append(String.format(
				"update ml_member set phone_mob ='%1$s' where user_id=%2$d ",
				phone, userId));
		if (jdbcTemplate.update(sql.toString()) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检测当前手机是否已被绑定
	 * 
	 * @param phone
	 *            当前手机号码
	 * @return
	 */
	public Member checkPhoneIsBind(String phone) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { phone, phone };
		hql.append("from Member where phoneMob=? or userName=?");
		return this.getSingleEntity(hql.toString(), condition);
	}

	/**
	 * 通过绑定手机号码查找会员信息
	 * 
	 * @param phone
	 *            手机号码
	 * @return
	 */
	public Member getMemberByPhone(String phone) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { phone };
		hql.append("from Member where phoneMob=?");
		return this.getSingleEntity(hql.toString(), condition);
	}

	/**
	 * 批量保存
	 * 
	 * @return
	 */
	public Member batchSaveMember(final List<Member> dataList,
			final int batchCount) {
		return this.hibernateTemplate
				.execute(new HibernateCallback<Member>() {
					@Override
					public Member doInHibernate(Session session)
							throws HibernateException {
						for (int i = 0; i < dataList.size(); i++) {
							try {
								session.save(dataList.get(i));
								// 如果想每1000条一次批处理，调用方法时batchCount传入1000
								if (i % batchCount == 0) {
									session.flush();
									session.clear();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						// 保存剩余的数据
						session.flush();
						session.clear();
						return null;
					}
				});
	}

	/**
	 * 批量查询判断存在用户
	 * 
	 * @return
	 */
	public List<Map<String, Object>> batchFindMember(String insql) {
		String sql = "select user_name from ml_member where user_name in ("
				+ insql + ")";
		return this.jdbcTemplate.queryForList(sql);
	}
}
