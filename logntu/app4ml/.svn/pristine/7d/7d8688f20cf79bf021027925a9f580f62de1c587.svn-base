package com.mall.web.mall.distribut.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.distribut.vo.MemberDistRelationVo;
import com.mall.web.mall.domain.MemberDistRelation;

/**
 * @Description(描述) :会员分销代理关系数据层 @author(作者) : ventrue
 * @date (开发日期) : 2015年8月6日 下午2:01:23
 */
@Repository("memberDistRelationDao")
public class MemberDistRelationDao extends BaseDao<MemberDistRelation> {

	/**
	 * @Description(功能描述) : 根据用户查找分销信息 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:30:24
	 * @param userId
	 *            : 用户信息
	 */
	public MemberDistRelation findDistRelation(int userId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { userId };
		hql.append(" from MemberDistRelation a where a.userId=?");
		List<MemberDistRelation> list = this.list(hql.toString(), condition);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * @Description(功能描述) : 调整下线（直接下线、所有下线）数量（jdbc）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2016年10月18日 下午2:30:24
	 * @param dist:用户多級关系
	 */
	public void batchDistNum(final MemberDistRelation dist) {
		if(dist.getSup01()<=0)
			return ;
		String sql = " update ml_member_dist_relation set a_lower_num=a_lower_num+1 where user_id=? ";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				int num = 0;
				for (int i = 1; i <= 15; i++) {
					if (dist.getSup(i) <= 0)
						break;
					num += 1;
				}
				return num;
			}
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, dist.getSup(i+1));
			}
		});
		String dsql="update ml_member_dist_relation set d_lower_num=d_lower_num+1 where user_id="+dist.getSup01();
		jdbcTemplate.batchUpdate(dsql);
	}

	/**
	 * @Description(功能描述) : 判断分销关系是否存在 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:30:24
	 * @param userId
	 *            : 用户id
	 * @param supperId
	 *            : 上级id
	 */
	public MemberDistRelation findDistRelationByUS(int userId, int supperId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { supperId, userId };
		hql.append(" from MemberDistRelation a where a.superiorId=? and a.userId=?");
		List<MemberDistRelation> list = this.list(hql.toString(), condition);
		return list.size() <= 0 ? null : list.get(0);
	}

	/**
	 * @Description(功能描述) : 查找上级分销 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月7日 上午11:08:42
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findSuperior(int userId) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { userId };
		sql.append(
				"  SELECT a.user_id as id,a.user_id,a.sup_01,a.d_lower_num,a.create_time,a.create_channel,b.user_name,b.phone_mob,b.im_weichat,b.im_Alipay,b.im_qq ");
		sql.append(" from ml_member_dist_relation a LEFT JOIN ml_member b ON a.user_id=b.user_id where a.user_id= ");
		sql.append(" (SELECT a.sup_01 from ml_member_dist_relation a where a.user_id=?) ");
		
		return jdbcTemplate.query(sql.toString(), condition, new RowMapper<MemberDistRelationVo>() {
			public MemberDistRelationVo mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDistRelationVo vo = new MemberDistRelationVo();
				vo.setChannel(rs.getString("create_channel"));
				vo.setCreateTime(rs.getTimestamp("create_time"));
				vo.setId(rs.getInt("id"));
				vo.setLowerNum(rs.getInt("d_lower_num"));
				vo.setPhoneMob(rs.getString("phone_mob"));
				vo.setSuperiorId(rs.getInt("sup_01"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setWeixin(rs.getString("im_weichat"));
				vo.setQq(rs.getString("im_qq"));
				vo.setZhifubao(rs.getString("im_Alipay"));
				return vo;
			}
		});
	}

	/**
	 * @Description(功能描述) : 一级分销列表 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月7日 下午2:51:24
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findOneDist(int userId) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { userId };
		sql.append(
				"  SELECT a.user_id as id,a.user_id,a.sup_01,a.d_lower_num,a.create_time,a.create_channel,b.user_name,b.phone_mob,b.im_weichat,b.im_Alipay,b.im_qq ");
		sql.append(
				"  from ml_member_dist_relation a LEFT JOIN ml_member b ON a.user_id=b.user_id where a.sup_01=? ");
		return jdbcTemplate.query(sql.toString(), condition, new RowMapper<MemberDistRelationVo>() {
			public MemberDistRelationVo mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDistRelationVo vo = new MemberDistRelationVo();
				vo.setChannel(rs.getString("create_channel"));
				vo.setCreateTime(rs.getTimestamp("create_time"));
				vo.setId(rs.getInt("id"));
				vo.setLowerNum(rs.getInt("d_lower_num"));
				vo.setPhoneMob(rs.getString("phone_mob"));
				vo.setSuperiorId(rs.getInt("sup_01"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setWeixin(rs.getString("im_weichat"));
				vo.setQq(rs.getString("im_qq"));
				vo.setZhifubao(rs.getString("im_Alipay"));
				return vo;
			}
		});
	}

	/**
	 * @Description(功能描述) : 一级分销中成为微商人数 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月7日 下午2:51:24
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public int getOneDistCount4Store(int userId) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { userId };
		sql.append("  SELECT count(*) as _count from yzm_store where store_id in  ");
		sql.append("  (select user_id from ml_member_dist_relation where superior_id=?) ");
		Map<String, Object> map = jdbcTemplate.queryForMap(sql.toString(), condition);
		return map.size() <= 0 ? 0 : ((Long) map.get("_count")).intValue();
	}

	/**
	 * @Description(功能描述) : 二级分销列表 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月8日 下午1:20:10
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findTwoDist(int userId) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { userId };
		sql.append(
				"  SELECT a.user_id as id,a.user_id,a.sup_01,a.d_lower_num,a.create_time,a.create_channel,b.user_name,b.phone_mob,b.im_weichat,b.im_Alipay,b.im_qq ");
		sql.append(
				"  from ml_member_dist_relation a LEFT JOIN ml_member b ON a.user_id=b.user_id where a.sup_02=? ");
		return jdbcTemplate.query(sql.toString(), condition, new RowMapper<MemberDistRelationVo>() {
			public MemberDistRelationVo mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDistRelationVo vo = new MemberDistRelationVo();
				vo.setChannel(rs.getString("create_channel"));
				vo.setCreateTime(rs.getTimestamp("create_time"));
				vo.setId(rs.getInt("id"));
				vo.setLowerNum(rs.getInt("d_lower_num"));
				vo.setPhoneMob(rs.getString("phone_mob"));
				vo.setSuperiorId(rs.getInt("sup_01"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setWeixin(rs.getString("im_weichat"));
				vo.setQq(rs.getString("im_qq"));
				vo.setZhifubao(rs.getString("im_Alipay"));
				return vo;
			}
		});
	}

	/**
	 * @Description(功能描述) : 三级分销列表 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月8日 下午1:20:10
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findThreeDist(int userId) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { userId };
		sql.append(
				"  SELECT a.user_id as id,a.user_id,a.sup_01,a.d_lower_num,a.create_time,a.create_channel,b.user_name,b.phone_mob,b.im_weichat,b.im_Alipay,b.im_qq ");
		sql.append(
				"  from ml_member_dist_relation a LEFT JOIN ml_member b ON a.user_id=b.user_id where a.sup_03=? ");
		return jdbcTemplate.query(sql.toString(), condition, new RowMapper<MemberDistRelationVo>() {
			public MemberDistRelationVo mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDistRelationVo vo = new MemberDistRelationVo();
				vo.setChannel(rs.getString("create_channel"));
				vo.setCreateTime(rs.getTimestamp("create_time"));
				vo.setId(rs.getInt("id"));
				vo.setLowerNum(rs.getInt("d_lower_num"));
				vo.setPhoneMob(rs.getString("phone_mob"));
				vo.setSuperiorId(rs.getInt("sup_01"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setWeixin(rs.getString("im_weichat"));
				vo.setQq(rs.getString("im_qq"));
				vo.setZhifubao(rs.getString("im_Alipay"));
				return vo;
			}
		});
	}

	/**
	 * @Description(功能描述) : 查找上三级代理 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月8日 下午1:20:10
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findSuperDist(final int userId) {
		StringBuilder sql = new StringBuilder();
		Object[] condition = { userId, userId, userId };
		sql.append(
				" select member.id,member.superior_id,case when s.owner_type is null then -1 else s.owner_type end as owner_type from ( ");
		sql.append(" select 1 as id,superior_id from ml_member_dist_relation where user_id=? and superior_id<>0");
		sql.append(" UNION ");
		sql.append(" select 2 as id,superior_id from ml_member_dist_relation where superior_id<>0 and user_id in( ");
		sql.append("    select superior_id from ml_member_dist_relation where user_id=? ");
		sql.append("  ) ");
		sql.append(" UNION ");
		sql.append(" select 3 as id,superior_id from ml_member_dist_relation where superior_id<>0 and user_id in( ");
		sql.append("   select superior_id from ml_member_dist_relation where user_id in ( ");
		sql.append("     select superior_id from ml_member_dist_relation where user_id=? ");
		sql.append("   ) ");
		sql.append("  ) ");
		sql.append(" ) member ");
		sql.append(" LEFT JOIN yzm_store s on s.store_id=member.superior_id");
		return jdbcTemplate.query(sql.toString(), condition, new RowMapper<MemberDistRelationVo>() {
			public MemberDistRelationVo mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberDistRelationVo vo = new MemberDistRelationVo();
				vo.setId(rs.getInt("id"));
				vo.setSuperiorId(rs.getInt("superior_id"));
				vo.setUserId(userId);
				vo.setStoreType(rs.getInt("owner_type"));
				return vo;
			}
		});
	}
}
