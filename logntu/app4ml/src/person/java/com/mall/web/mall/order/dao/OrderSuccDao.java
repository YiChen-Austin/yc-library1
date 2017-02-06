package com.mall.web.mall.order.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.domain.OrderSucc;

@Repository("orderSuccDao")
public class OrderSuccDao extends BaseDao<OrderSucc> {

	/**
	 * @param Order
	 *            订单信息
	 * @throws FrameworkException
	 */
	public void saveOrderSucc(Order order) throws FrameworkException {
		OrderSucc succ = new OrderSucc();
		succ.setOrderId(order.getOrderId().intValue());
		succ.setBuyerId(order.getBuyerId());
		succ.setBuyerName(order.getBuyerName());
		succ.setStatus(order.getStatus());
		succ.setType(order.getType());
		succ.setAddTime(DateUtil.getGMTDate());
		this.save(succ);
	}

	/**
	 * @return 返回支付成功的订单成功信息
	 * @throws FrameworkException
	 */
	public List<OrderSucc> findPaySuccs() throws FrameworkException {
		String hql = "from OrderSucc where status=20";
		return this.list(hql, 0, 50);
	}

	/**
	 * @return 返回订单完结的订单成功信息
	 * @throws FrameworkException
	 */
	public List<OrderSucc> findOverSuccs() throws FrameworkException {
		String hql = "from OrderSucc where status=40";
		return this.list(hql, 0, 50);
	}

	/**
	 * @return 返回订单完结的订单成功信息(需要第三方处理的订单)
	 * @throws FrameworkException
	 */
	public List<OrderSucc> findThirdSuccs() throws FrameworkException {
		String hql = "from OrderSucc where status=60 or status=70";
		return this.list(hql, 0, 50);
	}

	/**
	 * @return 返回订单完结的订单成功信息
	 * @throws FrameworkException
	 */
	public int getOverSuccSize(int userId) throws FrameworkException {
		String hql = "select count(*) from OrderSucc where (status=400 or status=4000) and buyerId=?";
		return this.getTotal(hql, new Object[] { userId });
	}

	/**
	 * @return 批量处理成功状态
	 * @throws FrameworkException
	 */
	public void batchOrderSuccs(final List<OrderSucc> list, final int status)
			throws FrameworkException {
		String sql = "update ml_order_succ set status=? where order_id=?";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				OrderSucc succ = list.get(i);
				ps.setInt(1, status);
				ps.setInt(2, succ.getOrderId());
			}
		});
	}

	/**
	 * @return 批量处理成功状态(相乘)
	 * @throws FrameworkException
	 */
	public void batchOrderSuccs4mult(final List<OrderSucc> list,
			final int multiplier) throws FrameworkException {
		String sql = "update ml_order_succ set status=? where order_id=?";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return list.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				OrderSucc succ = list.get(i);
				ps.setInt(1, succ.getStatus() * multiplier);
				ps.setInt(2, succ.getOrderId());
			}
		});
	}

	/**
	 * @Description(功能描述) :修改订单成功状态
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	public int[] batchUpdateOrderSuccs(String ids, int status, int wstatus) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_order_succ set status=" + status
				+ " where order_id in (" + ids + ") and status=" + wstatus);
		return this.jdbcTemplate.batchUpdate(sql.toString());
	}
}
