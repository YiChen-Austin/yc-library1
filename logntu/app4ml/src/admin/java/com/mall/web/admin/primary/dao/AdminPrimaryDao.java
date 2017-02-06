package com.mall.web.admin.primary.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.primary.vo.PayPrimaryVo;
import com.mall.web.pay.domain.PayPrimary;

/**
 * @Description(描述) : 支付信息
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:03:02
 */
@Repository("adminPrimaryDao")
public class AdminPrimaryDao extends BaseDao<PayPrimary> {

	/**
	 * 平台运营后台管理
	 * 
	 * 支付统计
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public PayPrimaryVo findPayPrimaryStat(String requestTimeS,
			String requestTimeE, Integer status) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select ");
		sql.append(" SUM(case when identity_id REGEXP '^ali' then money else 0 END) as money4ali , ");
		sql.append(" SUM(case when identity_id REGEXP '^up' then money else 0 END) as money4up , ");
		sql.append(" SUM(case when identity_id REGEXP '^20' then money else 0 END) as money4wx ");
		sql.append(" from ml_pay_primary where 1=1 ");

		// 状态
		if (BaseUtil.isNotEmpty(status) && status >= 0) {
			sql.append(" and status=? ");
			condition.add(status);
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(requestTimeS)) {
			sql.append(" and request_time>=? ");
			condition.add(requestTimeS);
		}
		if (BaseUtil.isNotEmpty(requestTimeE)) {
			sql.append(" and request_time<=? ");
			condition.add(requestTimeE);
		}

		List<PayPrimaryVo> list = jdbcTemplate.query(sql.toString(), condition
				.toArray(), new BeanPropertyRowMapper<PayPrimaryVo>(
				PayPrimaryVo.class));
		return list.size() > 0 ? list.get(0) : null;
	}
}
