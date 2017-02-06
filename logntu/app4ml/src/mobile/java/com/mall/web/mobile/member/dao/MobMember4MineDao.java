package com.mall.web.mobile.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;

@Repository("mobMember4MineDao")
public class MobMember4MineDao extends BaseDao<Object> {
	/**
	 * 会员中心->我的钱包
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<Map<String, Object>> getMywallet(int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select m.user_id, ");
		hql.append(" case when md.balance is null then 0 else md.balance end  as balance, ");
		hql.append(" case when rr.rrnum is null then 0 else rr.rrnum end  as rednum, ");
		hql.append(" case when cr.crnum is null then 0 else cr.crnum end  as couponsnum, ");
		hql.append(" case when md.point is null then 0 else md.point end  as point ");
		hql.append(" from ml_member m ");
		hql.append(" LEFT JOIN ml_member_deposit md on m.user_id=md.user_id ");
		hql.append(" LEFT JOIN (select user_id,count(*) as rrnum from mall_redenvelope_receive where user_id=?) rr on rr.user_id=m.user_id ");
		hql.append(" LEFT JOIN (select user_id,count(*) as crnum from mall_coupons_receive where user_id=?) cr on cr.user_id=m.user_id ");
		hql.append(" where m.user_id=? ");
		Object[] condition = { userId, userId, userId };
		return this.jdbcTemplate.queryForList(hql.toString(), condition);
	}

	/**
	 * 会员中心->我的钱包->钱包信息
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<Map<String, Object>> getMywalletInfo(int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select m.user_id, ");
		hql.append(" case when md.balance is null then 0 else md.balance end  as balance, ");
		hql.append(" case when f.frozen is null then 0 else f.frozen end  as frozen, ");
		hql.append(" md.is_activated as activated ");
		hql.append(" from ml_member m ");
		hql.append(" LEFT JOIN ml_member_deposit md on m.user_id=md.user_id ");
		hql.append(" LEFT JOIN (select user_id,SUM(frozen) as frozen from ml_member_frozen where user_id=? and status=1 group by user_id) f on f.user_id=m.user_id ");
		hql.append(" where m.user_id=? ");
		Object[] condition = { userId, userId };
		return this.jdbcTemplate.queryForList(hql.toString(), condition);
	}
	
	/**
	 * 会员中心->东家中心->首页
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public List<Map<String, Object>> getDistHome(int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select s.store_id,s.store_name,s.sgrade,FROM_UNIXTIME(s.add_time,'%Y-%m-%d %H:%i:%S') as endTime, ");
		hql.append(" case when d.balance is null then 0 else d.balance end as balance, ");
		hql.append(" case when d.point is null then 0 else d.point end as point, ");
		hql.append(" case when x.oamount is null then 0 else x.oamount end as oamount ");
		hql.append(" from ml_store s ");
		// -- 账户信息
		hql.append("  LEFT JOIN ml_member_deposit d on d.user_id=s.store_id ");
		// -- 销售额
		hql.append(" LEFT JOIN ( ");
		hql.append(" select SUM(o.order_amount) as oamount,buyer_id as user_id from ml_order o where o.buyer_id in ( ");
		hql.append("  select user_id from ml_member_dist_relation where superior_id=? ");
		hql.append(" ) ");
		hql.append(" ) x on 1=1 ");
		hql.append(" where s.owner_type=2 and s.store_id=? ");
		Object[] condition = { userId, userId };
		return this.jdbcTemplate.queryForList(hql.toString(), condition);
	}
}
