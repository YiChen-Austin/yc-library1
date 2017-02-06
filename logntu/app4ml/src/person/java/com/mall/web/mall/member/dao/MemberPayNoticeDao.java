package com.mall.web.mall.member.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.domain.MemberPayNotice;

@Repository("memberPayNoticeDao")
public class MemberPayNoticeDao extends BaseDao<MemberPayNotice> {
	/**
	 * 获取待通知信息
	 * 
	 * @return List<MemberPayNotice>
	 */
	public List<MemberPayNotice> findPayNotices(int startPos, int length) {
		String hql = "from MemberPayNotice where noticeStatus<>? and lastTime<=? and noticeCount<=5";
		return list(hql, startPos,length, new Object[] { MallEnum.WorkStats.Worked.getIndex(), DateUtil.getGMTDate() });
	}
	/**
	 * 批量处理待通知信息
	 * 
	 * @return int[]
	 */
	public int[] batchPayNotices(final List<MemberPayNotice> list) {
		String sql = "UPDATE ml_pay_notice SET last_time = DATE_ADD(now(), INTERVAL ? second),notice_count=notice_count+1 where notice_id=? and notice_status<>?";
		//String sql = "UPDATE ml_pay_notice SET last_time = ?,notice_count=notice_count+1 where notice_id=? and notice_status<>?";
		return this.jdbcTemplate.batchUpdate(sql,
				new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int pos)
					throws SQLException {
				MemberPayNotice notice=list.get(pos);
				ps.setInt(1, notice.getNoticeCount()*30);
				ps.setString(2, notice.getNoticeId());
				ps.setInt(3, MallEnum.WorkStats.Worked.getIndex());
			}
		});
	}
	/**
	 * 单条处理成功通知
	 * 
	 * @return int
	 */
	public int payNoticesSucc(String orderSn) {
		String sql = "UPDATE ml_pay_notice SET notice_status=? where order_sn=? and notice_status<>?";
		return this.jdbcTemplate.update(sql, new Object[]{MallEnum.WorkStats.Worked.getIndex(),orderSn,MallEnum.WorkStats.Worked.getIndex()});
	}
}
