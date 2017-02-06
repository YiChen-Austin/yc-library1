package com.mall.web.mall.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.web.mall.domain.MemberAutoLogin;


@Repository("autoLoginDao")
public class MemberAutoLoginDao extends BaseDao<MemberAutoLogin> {
	
	/**
	 * @Description(功能描述)		: 根据token值查找用户对象
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月31日 上午10:50:35 
	 * @param cookieValue		: token值
	 * @return	
	 * @throws FrameworkException
	 */
	public List<MemberAutoLogin> findAutoLogins(String cookieValue) throws FrameworkException {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM MemberAutoLogin model where cookieValue='" + cookieValue + "'");
		return this.list(hql.toString(), 0, 1);
	}

	/**
	 * @Description(功能描述)		: 根据token值删除token数据
	 * @author(作者)				: wangliyou
	 * @date (开发日期)			: 2015年10月31日 上午10:50:00 
	 * @param cookieValue		: token值
	 * @return
	 * @throws FrameworkException
	 */
	public int removeAutoLogin(String cookieValue) throws FrameworkException {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ml_auto_login where cookie_value='" + cookieValue + "'");
		return this.jdbcTemplate.update(sql.toString());
	}
}
