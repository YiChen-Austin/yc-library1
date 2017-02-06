package com.mall.web.mall.order.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.util.BaseUtil;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.order.vo.WebOrderVo;

/**
 * @Description(描述) : 商品订单 @author(作者) : ventrue
 * 
 * @date (开发日期) : 2015年9月1日 上午11:03:02
 */
@Repository("orderDao")
public class OrderDao extends BaseDao<Order> {
	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单列表
	 */
	public Order findOrderById(Long orderId) {
		String hql = "from Order where orderId=?";
		List<Order> list = list(hql, new Object[] { orderId});
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单列表
	 */
	public List<Order> findOrderBySn(String sn) {
		String hql = "from Order where orderSn=?";
		return list(hql, new Object[] { sn });
	}

	/**
	 * @Description(功能描述) :根据sn获取订单信息（待支付） @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 * @param sn
	 *            : 订单号
	 * @return : 订单信息
	 */
	public List<Order> findUnsellOrderBySn(String sn, int buyerId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { sn, buyerId };
		hql.append("from Order where order_sn=? and buyerId=? and status=11");
		return this.list(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) :根据订单状态获取订单信息 @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	public List<Order> findOrderByStatus(int status, String timeCol, int day) {
		StringBuilder hql = new StringBuilder();
		if (day > 0) {
			// Object[] condition = { new Integer(status), new Integer(day *
			// 3600 * 24)};
			Object[] condition = { status };
			hql.append("from Order where status=? and refundStatus=0 and unix_timestamp(now( ))-" + timeCol + ">"
					+ (day * 3600 * 24));
			return this.list(hql.toString(), 0, 50, condition);
		} else {
			Object[] condition = { status };
			hql.append("from Order where status=? and refundStatus=0 ");
			return this.list(hql.toString(), 0, 50, condition);
		}
	}

	/**
	 * @Description(功能描述) :删除关闭订单和超时未付款订单 @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	public int[] batchRemoveOrders(String ids) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ml_order where order_id in (" + ids + ")");
		return this.jdbcTemplate.batchUpdate(sql.toString());
	}

	/**
	 * @Description(功能描述) :修改订单状态 @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 */
	public int[] batchUpdateOrders(String ids, int status, String timeCol, int day) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ml_order set status=" + status + "," + timeCol + "=" + day + " where order_id in (" + ids
				+ ")");
		return this.jdbcTemplate.batchUpdate(sql.toString());
	}

	/**
	 * @Description(功能描述) : 判断判断订单号是否已使用（订单号使用次数） @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年9月1日 上午11:14:30
	 * @param sn
	 *            : 订单号
	 * @return : 评论数量
	 */
	public int getOrderSnNum(String sn) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { sn };
		hql.append("SELECT count(*) from Order where order_sn=?");
		return this.getTotal(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) : 查询订单状态数量(卖家) @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId
	 *            : 用户id
	 * @return
	 */
	public int getOrderBySellerId(int status, int sellerId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { status, sellerId };
		hql.append("SELECT count(*) from Order where status=? and sellerId=?");
		return this.getTotal(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) : 查询订单状态数量(买家) @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId
	 *            : 用户id
	 * @return
	 */
	public int getOrderByBuyerId(int status, int buyerId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { status, buyerId };
		hql.append("SELECT count(*) from Order where status=? and buyerId=?");
		return this.getTotal(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) : 删除已取消订单 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @param orderId
	 *            : 订单id
	 * @return
	 */
	public int delOrder4Cancel(int orderId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { orderId, 0 };
		hql.append("delete from ml_order where order_id=? and status=?");
		return this.jdbcTemplate.update(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) : 修改订单状态 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @param orderId
	 *            : 订单id
	 * @return
	 */
	public int updateOrderStatus(int orderId, int srcStatus, int tgtStatus) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { tgtStatus, orderId, srcStatus };
		hql.append("update ml_order set status=? where order_id =? and status=?");
		return this.jdbcTemplate.update(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) : 修改订单状态(使用于退款/退货) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年11月6日 上午9:56:57
	 * @param orderId
	 *            : 订单id
	 * @param refundStatus
	 *            : 退(款与货)状态
	 * @param refundType
	 *            : 退(款与货)类型
	 * @param refundId
	 *            : 退(款与货)id
	 * @return
	 */
	public int updateOrderStatus4refund(int orderId, int refundStatus, int refundType, int refundId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { refundStatus, refundType, refundId, orderId };
		hql.append("update ml_order set refund_status=?,refund_type=?,is_return=? where order_id =?");
		return this.jdbcTemplate.update(hql.toString(), condition);
	}

	/**
	 * @Description(功能描述) : 订单信息(买家) @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId
	 *            : 用户id
	 * @return List<Order> 订单列表
	 */
	public List<Order> findOrderByBuy(int buyerId, int status) {
		StringBuilder hql = new StringBuilder();
		if (status > 0) {
			Object[] condition = { buyerId, status };
			hql.append("from Order where buyerId=? and status=?");
			return this.list(hql.toString(), condition);
		} else {
			Object[] condition = { buyerId };
			hql.append("from Order where buyerId=?");
			return this.list(hql.toString(), condition);
		}

	}



	/**
	 * @Description(功能描述) : 订单信息(买家) @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId
	 *            : 用户id
	 * @return List<Order> 订单列表
	 */
	public List<Order> findOrderBySell(int sellerId, int status) {
		StringBuilder hql = new StringBuilder();
		if (status > 0) {
			Object[] condition = { sellerId, status };
			hql.append("from Order where sellerId=? and status=?");
			return this.list(hql.toString(), condition);
		} else {
			Object[] condition = { sellerId };
			hql.append("from Order where sellerId=?");
			return this.list(hql.toString(), condition);
		}
	}

	/**
	 * @Description(功能描述) : 订单信息(买家) @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月30日 下午3:07:41
	 * @param status
	 *            : (10已提交（货到付款） 11等待买家付款（非货到付款） 20等待卖家发货 30卖家已发货 40交易成功 0交易取消)
	 * @param sellerId : 用户id
	 * @param cType:消费类型
	 * @param cType:充值类型
	 * @return List<Order> 订单列表
	 */
	public List<Order> findOrderByBuy(int buyerId,String cType,String rType) {
		StringBuilder hql = new StringBuilder();
		Object[] condition =null;
		//充值
		if (BaseUtil.isNotEmpty(rType)) {
			condition =new Object[]{ buyerId, cType };
			hql.append("from Order where buyerId=? and type=? ");
			return this.list(hql.toString(), condition);
		}//消费 
		else if (BaseUtil.isNotEmpty(cType)) {
			condition = new Object[]{ buyerId,cType };
			hql.append("from Order where buyerId=? and type<>? ");
			return this.list(hql.toString(), condition);
		}
		return new ArrayList<Order>();
	}
	/**
	 * @Description(功能描述) : 统计众筹商品参与人次 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2016年1月22日 下午4:22:51
	 */
	public List<WebOrderVo> findZcOrder(int goodsId) {
		StringBuilder hql = new StringBuilder();
		Object[] condition = { goodsId };
		hql.append(" SELECT o.buyer_id,a.user_name,o.pnumber,o.add_time from  ");
		hql.append(" (SELECT a.buyer_id,count(buyer_id) as pnumber,a.add_time  from ml_order a where a.order_id in");
		hql.append(
				" (SELECT a.order_id from ml_order_goods a where a.goods_id=?) and a.status=40 GROUP BY buyer_id) o ");
		hql.append(" LEFT JOIN ml_member a on o.buyer_id=a.user_id");
		return jdbcTemplate.query(hql.toString(), condition, new RowMapper<WebOrderVo>() {
			public WebOrderVo mapRow(ResultSet rs, int arg1) throws SQLException {
				WebOrderVo vo = new WebOrderVo();
				vo.setBuyerName(rs.getString("user_name"));
				vo.setPnumber(rs.getInt("pnumber"));
				vo.setAddTime(rs.getDate("add_time"));
				return vo;
			}
		});
	}

	// 判断是否已有
	public boolean getIsTrueOrderByReturn(long orderId) {
		// StringBuilder hql = new StringBuilder();
		// StringBuilder sql = new StringBuilder();
		String hql = "select count(*) from GoodsReturn where order_id=?";
		return this.getTotal(hql, new Object[] { orderId }) > 0 ? true : false;
	}


	/**
	 * @param primaryId
	 *            支付id
	 * @return 返回订单列表
	 */
	public List<Order> findOrderByPayId(String primaryId) {
		String sql = "select order_id as orderId,order_sn as orderSn from ml_order where order_id in (select order_order_id from ml_pay_order where pay_primary_id=?)";
		return this.jdbcTemplate.query(sql, new Object[] { primaryId }, new BeanPropertyRowMapper<Order>(Order.class));
	}

	/**
	 * @param sn
	 *            订单编号
	 * @return 返回订单列表
	 */
	@SuppressWarnings("rawtypes")
	public List findOrderBySn(String[] sns) {
		StringBuffer sb = new StringBuffer();
		for (String sn : sns) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("'" + sn + "'");
		}
		String hql = "from Order where orderSn in (" + sb.toString() + ")";
		return list(hql);
	}
}
