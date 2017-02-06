package com.mall.web.admin.payrecord.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.util.BaseUtil;
import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.payrecord.vo.AdminPayRecordVo;
import com.mall.web.pay.domain.PayRecord;

/**
 * @Description(描述) : 支付报告信息
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:03:02
 */
@Repository("adminPayRecordDao")
public class AdminPayRecordDao extends BaseDao<PayRecord> {
	/**
	 * 平台运营后台管理
	 * 
	 * 支付报告统计
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回数量
	 */
	public int getPayRecords(String requestTimeS, String requestTimeE,
			Long in_user_id, String appendSql) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select ");
		sql.append(" count(*) as _count ");
		sql.append(" from ml_pay_record where 1=1 ");

		// 时间
		if (BaseUtil.isNotEmpty(requestTimeS)) {
			sql.append(" and create_time>=? ");
			condition.add(requestTimeS);
		}
		if (BaseUtil.isNotEmpty(requestTimeE)) {
			sql.append(" and create_time<=? ");
			condition.add(requestTimeE);
		}
		if (BaseUtil.isNotEmpty(in_user_id)) {
			sql.append(" and in_user_id=? ");
			condition.add(in_user_id);
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
	 * 支付报告统计
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回支付信息数量
	 */
	public List<AdminPayRecordVo> findPayRecords(String requestTimeS,
			String requestTimeE, Long in_user_id, String appendSql,
			PageBean4UI pageBean) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select ");
		sql.append(" record_id,primary_id,service_type,type_id,out_user_id,in_user_id,money,out_balance,in_balance,point,status,date_format(create_time,'%Y-%m-%d %H:%i:%S') as create_time,description ");
		sql.append(" from ml_pay_record where 1=1 ");

		// 时间
		if (BaseUtil.isNotEmpty(requestTimeS)) {
			sql.append(" and create_time>=? ");
			condition.add(requestTimeS);
		}
		if (BaseUtil.isNotEmpty(requestTimeE)) {
			sql.append(" and create_time<=? ");
			condition.add(requestTimeE);
		}
		if (BaseUtil.isNotEmpty(in_user_id)) {
			sql.append(" and in_user_id=? ");
			condition.add(in_user_id);
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		if (BaseUtil.isNotEmpty(pageBean.getSort())
				&& BaseUtil.isNotEmpty(pageBean.getOrder())) {
			sql.append(" order by " + pageBean.getSort() + " "
					+ pageBean.getOrder());
		}
		sql.append(" limit "
				+ (pageBean.getPage() > 1 ? pageBean.getPage() - 1 : 0)
				* pageBean.getRows() + "," + pageBean.getRows());
		List<AdminPayRecordVo> list = jdbcTemplate.query(sql.toString(),
				condition.toArray(),
				new BeanPropertyRowMapper<AdminPayRecordVo>(
						AdminPayRecordVo.class));
		return list;
	}
}
