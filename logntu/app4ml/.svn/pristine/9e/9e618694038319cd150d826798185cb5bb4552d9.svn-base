package com.mall.web.admin.frozen.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.util.BaseUtil;
import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.frozen.vo.AdminFrozenVo;
import com.mall.web.mall.common.utils.MallEnum.PayFrozenType;
import com.mall.web.mall.domain.Order;

/**
 * @Description(描述) : 冻结信息
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:03:02
 */
@Repository("adminFrozenDao")
public class AdminFrozenDao extends BaseDao<Order> {
	/**
	 * 平台运营后台管理
	 * 
	 * 冻结所有信息数量
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回冻结数量
	 */
	public int getAllFrozenRow(Map<String, Object> params, String appendSql) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select count(*) as _count from ml_member_frozen f");
		sql.append(" where 1=1 ");
		// 状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and f.status=? ");
			condition.add(params.get("status"));
		}
		// 备注
		if (BaseUtil.isNotEmpty(params.get("remark"))) {
			sql.append(" and f.remark=? ");
			condition.add(params.get("remark"));
		}
		// 会员
		if (BaseUtil.isNotEmpty(params.get("user_id"))
				&& ((Integer) params.get("user_id")) >= 0) {
			sql.append(" and f.user_id=? ");
			condition.add(params.get("user_id"));
		}
		// 冻结时间
		if (BaseUtil.isNotEmpty(params.get("frozenTimeS"))) {
			sql.append(" and f.frozen_time>=? ");
			condition.add(params.get("frozenTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("frozenTimeE"))) {
			sql.append(" and f.frozen_time<=? ");
			condition.add(params.get("frozenTimeE"));
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		Map<String, Object> map = jdbcTemplate.queryForMap(sql.toString(),
				condition.toArray());
		Object obj = map.get("_count");
		if (obj instanceof Integer) {
			return (Integer) obj;
		}
		if (obj instanceof Long) {
			return ((Long) obj).intValue();
		}
		return (Integer) obj;
	}

	/**
	 * 平台运营后台管理
	 * 
	 * 冻结所有信息
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回冻结信息
	 */
	public List<AdminFrozenVo> findAllFrozens(Map<String, Object> params,
			String appendSql, PageBean4UI pageBean) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select f.frozen_id,f.user_id,f.frozen_time,f.frozen,f.valid_frozen,f.`status`,f.last_time,f.remark "
				+ " ,b.bank_reg_name,b.bank_card_no,b.card_owner,b.bank_reg_pri,b.bank_reg_sub "
				+ " from ml_member_frozen f "
				+ " LEFT JOIN ml_member_bank_card b on b.user_id=f.user_id ");
		sql.append(" where 1=1 ");
		// 状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and f.status=? ");
			condition.add(params.get("status"));
		}
		// 备注
		if (BaseUtil.isNotEmpty(params.get("remark"))) {
			sql.append(" and f.remark=? ");
			condition.add(params.get("remark"));
		}
		// 会员
		if (BaseUtil.isNotEmpty(params.get("user_id"))
				&& ((Integer) params.get("user_id")) >= 0) {
			sql.append(" and f.user_id=? ");
			condition.add(params.get("user_id"));
		}
		// 冻结时间
		if (BaseUtil.isNotEmpty(params.get("frozenTimeS"))) {
			sql.append(" and f.frozen_time>=? ");
			condition.add(params.get("frozenTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("frozenTimeE"))) {
			sql.append(" and f.frozen_time<=? ");
			condition.add(params.get("frozenTimeE"));
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		if (BaseUtil.isNotEmpty(pageBean.getSort())
				&& BaseUtil.isNotEmpty(pageBean.getOrder())) {
			sql.append(" order by f." + pageBean.getSort() + " "
					+ pageBean.getOrder());
		}
		sql.append(" limit "
				+ (pageBean.getPage() > 1 ? pageBean.getPage() - 1 : 0)
				* pageBean.getRows() + "," + pageBean.getRows());
		// System.out.println(sql.toString());
		return jdbcTemplate.query(sql.toString(), condition.toArray(),
				new BeanPropertyRowMapper<AdminFrozenVo>(AdminFrozenVo.class));
	}

	/**************************************/
	// 处理成功提现信息
	/**
	 * 平台运营后台管理
	 * 
	 * 处理财务人员提交的成功提现信息
	 * 
	 * @return 返回影响数量
	 */
	public int[] batchFrozen(final List<AdminFrozenVo> frozens) {
		StringBuilder hql = new StringBuilder();
		hql.append("update ml_member_frozen set status=?,last_time=? where frozen_id=? and status=? ");
		return this.jdbcTemplate.batchUpdate(hql.toString(),
				new BatchPreparedStatementSetter() {
					@Override
					public int getBatchSize() {
						return frozens.size();
					}

					@Override
					public void setValues(PreparedStatement ps, int pos)
							throws SQLException {
						ps.setInt(1, PayFrozenType.UnFrozen.getIndex());
						ps.setObject(2, new Date());
						ps.setInt(3, frozens.get(pos).getFrozen_id());
						ps.setInt(4, PayFrozenType.Frozen.getIndex());
					}
				});
	}
}
