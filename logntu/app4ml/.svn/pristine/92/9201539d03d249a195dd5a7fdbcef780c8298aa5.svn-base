package com.mall.web.admin.order.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.util.BaseUtil;
import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.order.vo.AdminOrderGoodsVo;
import com.mall.web.admin.order.vo.AdminOrderVo;
import com.mall.web.mall.common.utils.MallEnum.OrderRefundStatus;
import com.mall.web.mall.domain.Order;

/**
 * @Description(描述) : 商品订单信息
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:03:02
 */
@Repository("adminOrderDao")
public class AdminOrderDao extends BaseDao<Order> {
	/**
	 * 平台运营后台管理
	 * 
	 * 订单所有信息数量
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public int getAllOrderRow(boolean isReturn, Map<String, Object> params,
			String appendSql) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select count(*) as _count ");
		// 有退款需要判断
		// if (isReturn) {
		// sql.append(" from ml_order g inner join ml_goods_return r on g.order_id=r.order_id ");
		// } else {
		sql.append(" from ml_order g ");
		// }

		sql.append(" where 1=1 ");
		// 无退款需要判断
		// if (isReturn) {
		// sql.append(" and g.refund_status>0 ");
		// }
		// 购买会员id
		if (BaseUtil.isNotEmpty(params.get("buyer_id"))
				&& ((Integer) params.get("buyer_id")) >= 0) {
			sql.append(" and g.buyer_id=? ");
			condition.add(params.get("buyer_id"));
		}
		// 商家会员id
		if (BaseUtil.isNotEmpty(params.get("seller_id"))
				&& ((Integer) params.get("seller_id")) >= 0) {
			sql.append(" and g.seller_id=? ");
			condition.add(params.get("seller_id"));
		}
		// 订单id
		if (BaseUtil.isNotEmpty(params.get("order_id"))
				&& ((Integer) params.get("order_id")) >= 0) {
			sql.append(" and g.order_id=? ");
			condition.add(params.get("order_id"));
		}
		// 客户
		if (BaseUtil.isNotEmpty(params.get("buyer_name"))) {
			sql.append(" and g.buyer_name like '%" + params.get("buyer_name")
					+ "%'");
		}
		// 商户
		if (BaseUtil.isNotEmpty(params.get("seller_name"))) {
			sql.append(" and g.seller_name like '%" + params.get("seller_name")
					+ "%'");
		}
		// 订单编号
		if (BaseUtil.isNotEmpty(params.get("order_sn"))) {
			sql.append(" and g.order_sn like '%" + params.get("order_sn")
					+ "%'");
		}
		// 退款状态
		// if (BaseUtil.isNotEmpty(params.get("refund_status"))
		// && ((Integer) params.get("refund_status")) >= 0) {
		// sql.append(" and g.refund_status=? ");
		// condition.add(params.get("refund_status"));
		// }
		// 订单状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and g.status=? ");
			condition.add(params.get("status"));
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(params.get("addTimeS"))) {
			sql.append(" and from_unixtime(g.add_time)>=? ");
			condition.add(params.get("addTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("addTimeE"))) {
			sql.append(" and from_unixtime(g.add_time)<=? ");
			condition.add(params.get("addTimeE"));
		}
		// 支付时间
		if (BaseUtil.isNotEmpty(params.get("payTimeS"))) {
			sql.append(" and from_unixtime(g.pay_time)>=? ");
			condition.add(params.get("payTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("payTimeE"))) {
			sql.append(" and from_unixtime(g.pay_time)<=? ");
			condition.add(params.get("payTimeE"));
		}
		// 发货物流时间
		if (BaseUtil.isNotEmpty(params.get("shipTimeS"))) {
			sql.append(" and from_unixtime(g.ship_time)>=? ");
			condition.add(params.get("shipTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("shipTimeE"))) {
			sql.append(" and from_unixtime(g.ship_time)<=? ");
			condition.add(params.get("shipTimeE"));
		}
		// 收货结束时间
		if (BaseUtil.isNotEmpty(params.get("finishedTimeS"))) {
			sql.append(" and from_unixtime(g.finished_time)>=? ");
			condition.add(params.get("finishedTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("finishedTimeE"))) {
			sql.append(" and from_unixtime(g.finished_time)<=? ");
			condition.add(params.get("finishedTimeE"));
		}
		// 评价关闭时间
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeS"))) {
			sql.append(" and from_unixtime(g.evaluation_time)>=? ");
			condition.add(params.get("evaluationTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeE"))) {
			sql.append(" and from_unixtime(g.evaluation_time)<=? ");
			condition.add(params.get("evaluationTimeE"));
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
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderVo> findAllOrders(boolean isReturn,
			Map<String, Object> params, String appendSql, PageBean4UI pageBean) {
		// StringBuilder hql = new StringBuilder();
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select g.order_id, g.order_sn, g.type, g.extension, g.seller_id,g.seller_name, g.buyer_id,"
				+ "g.buyer_name, g.buyer_email, g.status, from_unixtime(g.add_time) as add_time, g.payment_id, g.payment_name ");
		// 有退款需要判断
		// if (isReturn) {
		// sql.append(" ,case when r.status is null then 0 else r.status end as returnStatus,r.record_time,r.goods_return_time from ml_order g "
		// + "inner join ml_goods_return r on g.order_id=r.order_id ");
		// } else {
		sql.append(" from ml_order g ");
		// }
		sql.append(" left join ml_pay_order po on po.order_order_id=g.order_id and po.pay_status=2");
		sql.append(" left join ml_pay_primary pp on po.pay_primary_id=pp.primary_id and po.pay_status=2 and pp.status=2");

		sql.append(" where 1=1 ");
		// 订单id
		if (BaseUtil.isNotEmpty(params.get("order_id"))
				&& ((Integer) params.get("order_id")) >= 0) {
			sql.append(" and g.order_id=? ");
			condition.add(params.get("order_id"));
		}
		// 客户
		if (BaseUtil.isNotEmpty(params.get("buyer_name"))) {
			sql.append(" and g.buyer_name like '%" + params.get("buyer_name")
					+ "%'");
		}
		// 订单编号
		if (BaseUtil.isNotEmpty(params.get("order_sn"))) {
			sql.append(" and g.order_sn like '%" + params.get("order_sn")
					+ "%'");
		}
		// 退款状态
		if (BaseUtil.isNotEmpty(params.get("refund_status"))
				&& ((Integer) params.get("refund_status")) >= 0) {
			sql.append(" and g.refund_status=? ");
			condition.add(params.get("refund_status"));
		}
		// 订单状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and g.status=? ");
			condition.add(params.get("status"));
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(params.get("addTimeS"))) {
			sql.append(" and from_unixtime(g.add_time)>=? ");
			condition.add(params.get("addTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("addTimeE"))) {
			sql.append(" and from_unixtime(g.add_time)<=? ");
			condition.add(params.get("addTimeE"));
		}
		// 支付时间
		if (BaseUtil.isNotEmpty(params.get("payTimeS"))) {
			sql.append(" and from_unixtime(g.pay_time)>=? ");
			condition.add(params.get("payTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("payTimeE"))) {
			sql.append(" and from_unixtime(g.pay_time)<=? ");
			condition.add(params.get("payTimeE"));
		}
		// 发货物流时间
		if (BaseUtil.isNotEmpty(params.get("shipTimeS"))) {
			sql.append(" and from_unixtime(g.ship_time)>=? ");
			condition.add(params.get("shipTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("shipTimeE"))) {
			sql.append(" and from_unixtime(g.ship_time)<=? ");
			condition.add(params.get("shipTimeE"));
		}
		// 收货结束时间
		if (BaseUtil.isNotEmpty(params.get("finishedTimeS"))) {
			sql.append(" and from_unixtime(g.finished_time)>=? ");
			condition.add(params.get("finishedTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("finishedTimeE"))) {
			sql.append(" and from_unixtime(g.finished_time)<=? ");
			condition.add(params.get("finishedTimeE"));
		}
		// 评价关闭时间
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeS"))) {
			sql.append(" and from_unixtime(g.evaluation_time)>=? ");
			condition.add(params.get("evaluationTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeE"))) {
			sql.append(" and from_unixtime(g.evaluation_time)<=? ");
			condition.add(params.get("evaluationTimeE"));
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		if (BaseUtil.isNotEmpty(pageBean.getSort())
				&& BaseUtil.isNotEmpty(pageBean.getOrder())) {
			sql.append(" order by g." + pageBean.getSort() + " "
					+ pageBean.getOrder());
		}
		sql.append(" limit "
				+ (pageBean.getPage() > 1 ? pageBean.getPage() - 1 : 0)
				* pageBean.getRows() + "," + pageBean.getRows());
		// System.out.println(sql.toString());
		return jdbcTemplate.query(sql.toString(), condition.toArray(),
				new BeanPropertyRowMapper<AdminOrderVo>(AdminOrderVo.class));
	}

	/**
	 * 平台运营后台管理(基于店铺统计)
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderVo> findAllOrders4stat(boolean isReturn,
			Map<String, Object> params, String appendSql) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select  g.seller_name,count(*) as order_count,sum(g.order_amount) as order_sum");
		sql.append(" from ml_order g ");
		// 有退款需要判断
		// if (isReturn) {
		// sql.append(" inner join ml_goods_return r on g.order_id=r.order_id ");
		// }
		sql.append(" where 1=1 ");
		// 无退款需要判断
		// if (isReturn) {
		// sql.append(" and g.refund_status>0 ");
		// }
		// 购买会员id
		if (BaseUtil.isNotEmpty(params.get("buyer_id"))
				&& ((Integer) params.get("buyer_id")) >= 0) {
			sql.append(" and g.buyer_id=? ");
			condition.add(params.get("buyer_id"));
		}
		// 商家会员id
		if (BaseUtil.isNotEmpty(params.get("seller_id"))
				&& ((Integer) params.get("seller_id")) >= 0) {
			sql.append(" and g.seller_id=? ");
			condition.add(params.get("seller_id"));
		}
		// 订单id
		if (BaseUtil.isNotEmpty(params.get("order_id"))
				&& ((Integer) params.get("order_id")) >= 0) {
			sql.append(" and g.order_id=? ");
			condition.add(params.get("order_id"));
		}
		// 订单编号
		if (BaseUtil.isNotEmpty(params.get("order_sn"))) {
			sql.append(" and g.order_sn like '%" + params.get("order_sn")
					+ "%'");
		}
		// 订单状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and g.status=? ");
			condition.add(params.get("status"));
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(params.get("addTimeS"))) {
			sql.append(" and from_unixtime(g.add_time)>=? ");
			condition.add(params.get("addTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("addTimeE"))) {
			sql.append(" and from_unixtime(g.add_time)<=? ");
			condition.add(params.get("addTimeE"));
		}
		// 支付时间
		if (BaseUtil.isNotEmpty(params.get("payTimeS"))) {
			sql.append(" and from_unixtime(g.pay_time)>=? ");
			condition.add(params.get("payTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("payTimeE"))) {
			sql.append(" and from_unixtime(g.pay_time)<=? ");
			condition.add(params.get("payTimeE"));
		}
		// 发货物流时间
		if (BaseUtil.isNotEmpty(params.get("shipTimeS"))) {
			sql.append(" and from_unixtime(g.ship_time)>=? ");
			condition.add(params.get("shipTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("shipTimeE"))) {
			sql.append(" and from_unixtime(g.ship_time)<=? ");
			condition.add(params.get("shipTimeE"));
		}
		// 收货结束时间
		if (BaseUtil.isNotEmpty(params.get("finishedTimeS"))) {
			sql.append(" and from_unixtime(g.finished_time)>=? ");
			condition.add(params.get("finishedTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("finishedTimeE"))) {
			sql.append(" and from_unixtime(g.finished_time)<=? ");
			condition.add(params.get("finishedTimeE"));
		}
		// 评价关闭时间
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeS"))) {
			sql.append(" and from_unixtime(g.evaluation_time)>=? ");
			condition.add(params.get("evaluationTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeE"))) {
			sql.append(" and from_unixtime(g.evaluation_time)<=? ");
			condition.add(params.get("evaluationTimeE"));
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		sql.append(" group by seller_name ");
		return jdbcTemplate.query(sql.toString(), condition.toArray(),
				new BeanPropertyRowMapper<AdminOrderVo>(AdminOrderVo.class));
	}

	/**
	 * 平台运营后台管理(基于店铺统计,汇总统计)
	 * 
	 * 订单所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public AdminOrderVo findAllOrders4statSum(boolean isReturn,
			Map<String, Object> params, String appendSql) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select count(*) as order_count,sum(g.order_amount) as order_sum");
		sql.append(" from ml_order g ");
		sql.append(" where 1=1 ");		// 订单id
		if (BaseUtil.isNotEmpty(params.get("order_id"))
				&& ((Integer) params.get("order_id")) >= 0) {
			sql.append(" and g.order_id=? ");
			condition.add(params.get("order_id"));
		}
		// 订单编号
		if (BaseUtil.isNotEmpty(params.get("order_sn"))) {
			sql.append(" and g.order_sn like '%" + params.get("order_sn")
					+ "%'");
		}
		// 订单状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and g.status=? ");
			condition.add(params.get("status"));
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(params.get("addTimeS"))) {
			sql.append(" and from_unixtime(g.add_time)>=? ");
			condition.add(params.get("addTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("addTimeE"))) {
			sql.append(" and from_unixtime(g.add_time)<=? ");
			condition.add(params.get("addTimeE"));
		}
		// 支付时间
		if (BaseUtil.isNotEmpty(params.get("payTimeS"))) {
			sql.append(" and from_unixtime(g.pay_time)>=? ");
			condition.add(params.get("payTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("payTimeE"))) {
			sql.append(" and from_unixtime(g.pay_time)<=? ");
			condition.add(params.get("payTimeE"));
		}
		// 发货物流时间
		if (BaseUtil.isNotEmpty(params.get("shipTimeS"))) {
			sql.append(" and from_unixtime(g.ship_time)>=? ");
			condition.add(params.get("shipTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("shipTimeE"))) {
			sql.append(" and from_unixtime(g.ship_time)<=? ");
			condition.add(params.get("shipTimeE"));
		}
		// 收货结束时间
		if (BaseUtil.isNotEmpty(params.get("finishedTimeS"))) {
			sql.append(" and from_unixtime(g.finished_time)>=? ");
			condition.add(params.get("finishedTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("finishedTimeE"))) {
			sql.append(" and from_unixtime(g.finished_time)<=? ");
			condition.add(params.get("finishedTimeE"));
		}
		// 评价关闭时间
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeS"))) {
			sql.append(" and from_unixtime(g.evaluation_time)>=? ");
			condition.add(params.get("evaluationTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeE"))) {
			sql.append(" and from_unixtime(g.evaluation_time)<=? ");
			condition.add(params.get("evaluationTimeE"));
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		List<AdminOrderVo> list = jdbcTemplate.query(sql.toString(), condition
				.toArray(), new BeanPropertyRowMapper<AdminOrderVo>(
				AdminOrderVo.class));
		return list.size() > 0 ? list.get(0) : null;
	}

	/**************************************/
	// 处理成功退款信息
	/**
	 * 平台运营后台管理
	 * 
	 * 处理财务人员提交的成功退款信息
	 * 
	 * @return 返回影响数量
	 */
	public int[] batchRefund(final List<AdminOrderVo> refunds) {
		StringBuilder hql = new StringBuilder();
		hql.append("update ml_order set refund_status=?,refundable_amount=?,express_company=? where order_sn=? ");
		return this.jdbcTemplate.batchUpdate(hql.toString(),
				new BatchPreparedStatementSetter() {
					@Override
					public int getBatchSize() {
						return refunds.size();
					}

					@Override
					public void setValues(PreparedStatement ps, int pos)
							throws SQLException {
						ps.setInt(1, OrderRefundStatus.ORDER_REFUND_FINISHED
								.getIndex());
						ps.setBigDecimal(2, refunds.get(pos).getOrder_amount());
						ps.setString(3, refunds.get(pos).getConsignee());// 退款综合信息
						ps.setString(4, refunds.get(pos).getOrder_sn());
					}
				});
	}

	/**************************************/
	// 订单产品

	/**
	 * 平台运营后台管理
	 * 
	 * 订单产品所有信息
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单信息
	 */
	public List<AdminOrderGoodsVo> findAllOrderGoods(boolean isReturn,
			Map<String, Object> params, String appendSql, PageBean4UI pageBean) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append(" select g.cate_id,gc.cate_name,og.goods_name,og.price,og.quantity,o.seller_id,o.seller_name,o.order_sn,from_unixtime(o.add_time) as add_time,from_unixtime(o.pay_time) as pay_time,from_unixtime(o.ship_time) as ship_time,from_unixtime(o.finished_time) as finished_time,from_unixtime(o.evaluation_time) as evaluation_time,o.status");
		sql.append(" from ml_order_goods og");
		// 有退款需要判断
		if (isReturn) {
			sql.append(" INNER JOIN ml_order o on o.order_id=og.order_id and o.refund_status>0");
		} else {
			sql.append(" INNER JOIN ml_order o on o.order_id=og.order_id ");
		}
		sql.append(" LEFT JOIN ml_goods g on g.goods_id=og.goods_id ");
		sql.append(" LEFT JOIN ml_gcategory gc on g.cate_id=gc.cate_id ");
		sql.append(" where 1=1 ");
		// 订单id
		if (BaseUtil.isNotEmpty(params.get("order_id"))
				&& ((Integer) params.get("order_id")) >= 0) {
			sql.append(" and o.order_id=? ");
			condition.add(params.get("order_id"));
		}
		// 订单编号
		if (BaseUtil.isNotEmpty(params.get("order_sn"))) {
			sql.append(" and o.order_sn like '%" + params.get("order_sn")
					+ "%'");
		}
		// 商家名称
		if (BaseUtil.isNotEmpty(params.get("seller_name"))) {
			sql.append(" and o.seller_name like '%" + params.get("seller_name")
					+ "%'");
		}
		// 产品名称
		if (BaseUtil.isNotEmpty(params.get("goods_name"))) {
			sql.append(" and og.goods_name like '%" + params.get("goods_name")
					+ "%'");
		}
		// 订单状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and o.status=? ");
			condition.add(params.get("status"));
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(params.get("addTimeS"))) {
			sql.append(" and from_unixtime(o.add_time)>=? ");
			condition.add(params.get("addTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("addTimeE"))) {
			sql.append(" and from_unixtime(o.add_time)<=? ");
			condition.add(params.get("addTimeE"));
		}
		// 支付时间
		if (BaseUtil.isNotEmpty(params.get("payTimeS"))) {
			sql.append(" and from_unixtime(o.pay_time)>=? ");
			condition.add(params.get("payTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("payTimeE"))) {
			sql.append(" and from_unixtime(o.pay_time)<=? ");
			condition.add(params.get("payTimeE"));
		}
		// 发货物流时间
		if (BaseUtil.isNotEmpty(params.get("shipTimeS"))) {
			sql.append(" and from_unixtime(o.ship_time)>=? ");
			condition.add(params.get("shipTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("shipTimeE"))) {
			sql.append(" and from_unixtime(o.ship_time)<=? ");
			condition.add(params.get("shipTimeE"));
		}
		// 收货结束时间
		if (BaseUtil.isNotEmpty(params.get("finishedTimeS"))) {
			sql.append(" and from_unixtime(o.finished_time)>=? ");
			condition.add(params.get("finishedTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("finishedTimeE"))) {
			sql.append(" and from_unixtime(o.finished_time)<=? ");
			condition.add(params.get("finishedTimeE"));
		}
		// 评价关闭时间
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeS"))) {
			sql.append(" and from_unixtime(o.evaluation_time)>=? ");
			condition.add(params.get("evaluationTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeE"))) {
			sql.append(" and from_unixtime(o.evaluation_time)<=? ");
			condition.add(params.get("evaluationTimeE"));
		}
		// 添加附件条件
		if (BaseUtil.isNotEmpty(appendSql)) {
			sql.append(" and " + appendSql);
		}
		if (BaseUtil.isNotEmpty(pageBean.getSort())
				&& BaseUtil.isNotEmpty(pageBean.getOrder())) {
			sql.append(" order by og." + pageBean.getSort() + " "
					+ pageBean.getOrder());
		}
		sql.append(" limit "
				+ (pageBean.getPage() > 1 ? pageBean.getPage() - 1 : 0)
				* pageBean.getRows() + "," + pageBean.getRows());
		// System.out.println(sql.toString());
		return jdbcTemplate.query(sql.toString(), condition.toArray(),
				new BeanPropertyRowMapper<AdminOrderGoodsVo>(
						AdminOrderGoodsVo.class));
	}

	/**
	 * 平台运营后台管理
	 * 
	 * 订单产品所有信息数量
	 * 
	 * @param returnStatus
	 *            退款状态（>=0进行退款判断，<0则不进行退款处理）
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public int getAllOrderGoodsRow(boolean isReturn,
			Map<String, Object> params, String appendSql) {
		StringBuilder sql = new StringBuilder();
		List<Object> condition = new ArrayList<Object>();
		sql.append("select count(*) as _count ");
		sql.append(" from ml_order_goods og");
		// 有退款需要判断
		if (isReturn) {
			sql.append(" INNER JOIN ml_order o on o.order_id=og.order_id and o.refund_status>0");
		} else {
			sql.append(" INNER JOIN ml_order o on o.order_id=og.order_id ");
		}
		sql.append(" where 1=1 ");
		// 订单id
		if (BaseUtil.isNotEmpty(params.get("order_id"))
				&& ((Integer) params.get("order_id")) >= 0) {
			sql.append(" and o.order_id=? ");
			condition.add(params.get("order_id"));
		}
		// 产品名称
		if (BaseUtil.isNotEmpty(params.get("goods_name"))) {
			sql.append(" and og.goods_name like '%" + params.get("goods_name")
					+ "%'");
		}
		// 订单编号
		if (BaseUtil.isNotEmpty(params.get("order_sn"))) {
			sql.append(" and o.order_sn like '%" + params.get("order_sn")
					+ "%'");
		}
		// 订单状态
		if (BaseUtil.isNotEmpty(params.get("status"))
				&& ((Integer) params.get("status")) >= 0) {
			sql.append(" and o.status=? ");
			condition.add(params.get("status"));
		}
		// 下单时间
		if (BaseUtil.isNotEmpty(params.get("addTimeS"))) {
			sql.append(" and from_unixtime(o.add_time)>=? ");
			condition.add(params.get("addTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("addTimeE"))) {
			sql.append(" and from_unixtime(o.add_time)<=? ");
			condition.add(params.get("addTimeE"));
		}
		// 支付时间
		if (BaseUtil.isNotEmpty(params.get("payTimeS"))) {
			sql.append(" and from_unixtime(o.pay_time)>=? ");
			condition.add(params.get("payTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("payTimeE"))) {
			sql.append(" and from_unixtime(o.pay_time)<=? ");
			condition.add(params.get("payTimeE"));
		}
		// 发货物流时间
		if (BaseUtil.isNotEmpty(params.get("shipTimeS"))) {
			sql.append(" and from_unixtime(o.ship_time)>=? ");
			condition.add(params.get("shipTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("shipTimeE"))) {
			sql.append(" and from_unixtime(o.ship_time)<=? ");
			condition.add(params.get("shipTimeE"));
		}
		// 收货结束时间
		if (BaseUtil.isNotEmpty(params.get("finishedTimeS"))) {
			sql.append(" and from_unixtime(o.finished_time)>=? ");
			condition.add(params.get("finishedTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("finishedTimeE"))) {
			sql.append(" and from_unixtime(o.finished_time)<=? ");
			condition.add(params.get("finishedTimeE"));
		}
		// 评价关闭时间
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeS"))) {
			sql.append(" and from_unixtime(o.evaluation_time)>=? ");
			condition.add(params.get("evaluationTimeS"));
		}
		if (BaseUtil.isNotEmpty(params.get("evaluationTimeE"))) {
			sql.append(" and from_unixtime(o.evaluation_time)<=? ");
			condition.add(params.get("evaluationTimeE"));
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
}
