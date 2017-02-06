package com.mall.web.mall.member.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.order.vo.AdminOrderVo;
import com.mall.web.mall.common.utils.MallEnum;
import com.mall.web.mall.distribut.vo.MemberDistRelationVo;
import com.mall.web.mall.member.vo.MemberPayRecordVo;
import com.mall.web.pay.domain.PayRecord;

/**
 * 资金变动
 */
@Repository("webMemberPayRecordDao")
public class WebMemberPayRecordDao extends BaseDao<PayRecord> {

	/*******************************************************************************
	 * 获取资金变动信息
	 * 
	 * @author chenhongxu
	 * @param userId
	 *            用户编号
	 * @return 返回存储账目
	 * @throws FrameworkException
	 */

	// 原Sql: select * from ml_pay_record where date_sub(curdate(), interval 90
	// day) <= date(create_time);
	// 原Sql: select * from ml_pay_record where out_user_id=2 and
	// date_sub(curdate(), interval 90 day) <= date(create_time);
	// 原Sql: select * from ml_pay_record where record_id=1112 and
	// date_sub(curdate(), interval 90 day) <= date(create_time);
	//
	// hql
	// public List<PayRecord> showMemberPayRecord(int startPos,int pageSize)
	// throws FrameworkException {
	// StringBuilder hql = new StringBuilder();
	// hql.append("from PayRecord where date_sub(curdate(), interval 90 day) <=
	// date(createTime)");
	// return this.list(hql.toString(),startPos, pageSize);
	// }

	// hql无参
	public List<PayRecord> showMemberPayRecord(Date date) throws FrameworkException {
		StringBuilder hql = new StringBuilder();
		hql.append("from PayRecord model where outUserId=2 and model.createTime>=?");
		return this.list(hql.toString(), new Object[] { date });
	}


	/**
	 * @Description(功能描述) : 余额支付
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void reduceBalance(int userId, double reduce, double balance) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.BalancePay.getIndex(),
				MallEnum.PayRecordServiceType.BalancePay.getName(), MallEnum.PayFundDirect.Reduce.getName(),
				BigDecimal.valueOf(-reduce), BigDecimal.valueOf(balance), "余额支付");
		this.save(record);
	}

	/**
	 * @Description(功能描述) : 在线支付
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void reduceOnlinePay(int userId, double reduce, double balance) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.OnlinePay.getIndex(),
				MallEnum.PayRecordServiceType.OnlinePay.getName(), MallEnum.PayFundDirect.Other.getName(),
				BigDecimal.valueOf(-reduce), BigDecimal.valueOf(balance), "在线支付");
		this.save(record);
	}

	/**
	 * @Description(功能描述) : 余额转冻结
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void reduceFrozenPay(int userId, double reduce, double balance) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.Blance2Frozen.getIndex(),
				MallEnum.PayRecordServiceType.Blance2Frozen.getName(), MallEnum.PayFundDirect.Reduce.getName(),
				BigDecimal.valueOf(-reduce), BigDecimal.valueOf(balance), "余额转冻结");
		this.save(record);
	}

	/**
	 * @Description(功能描述) : 增加金额 
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
//	public void reduceBalance(int userId, double plus, double balance) throws FrameworkException {
//		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.BalancePay.getIndex(),
//				MallEnum.PayRecordServiceType.BalancePay.getName(), MallEnum.PayFundDirect.Reduce.getName(),
//				BigDecimal.valueOf(plus), BigDecimal.valueOf(balance), "增加金额");
//		this.save(record);
//	}

	/**
	 * @Description(功能描述) : 增加金额(佣金) 
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void plusBalance4Commission(int userId, double plus, double balance, int orderId) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.Commission.getIndex(),
				MallEnum.PayRecordServiceType.Commission.getName(), MallEnum.PayFundDirect.Increase.getName(),
				BigDecimal.valueOf(plus), BigDecimal.valueOf(balance), "佣金发放,充值订单:"+orderId);
		this.save(record);
	}

	/**
	 * @Description(功能描述) : (佣金结算)数量 
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int getPaySize4Commission(int userId, int orderId) throws FrameworkException {
		String hql = "select count(*) from PayRecord where primaryId=? and serviceType=? and typeId=?"; 
		return this.getTotal(hql, new Object[] { Integer.toString(userId),
				MallEnum.PayRecordServiceType.Commission.getIndex(), (long) orderId });
	}
	
	/**
	 * @Description 获取佣金结算记录
	 * @author yichen
	 * 
	 */
/*	public List<PayRecord> getCommission(int userid,String date){
		String hql = "from PayRecord where userId =? and busiType = ? and SUBSTR(DATE_FORMAT( createTime, '%Y-%m'),1,7)";
		return this.list(hql, new Object[]{userid,MallEnum.PayRecordServiceType.Commission.getIndex(),date});
	}*/

	
	public List<MemberPayRecordVo> getCommission(int userId,String date){
		StringBuilder sql = new StringBuilder();
		Object[] condition = null;
		if("".equals(date) || date == ""){
			sql.append("select record_id,user_id,busi_type,busi_desc,fund_direct,money,"
					+ "balance,point,status,remark,date_format(create_time,'%Y-%m-%d')  as create_time "
					+ "from ml_pay_record where user_id =? and busi_type = ?");
			condition = new Object []{ userId, MallEnum.PayRecordServiceType.Commission.getIndex()};
		}else{
			sql.append(" select record_id,user_id,busi_type,busi_desc,fund_direct,money,"
					+ "balance,point,status,remark,date_format(create_time,'%Y-%m-%d')  as create_time "
					+ "from ml_pay_record where user_id =? and busi_type = ? and date_format(create_time,'%Y-%m') = ?");
			condition = new Object []{ userId, MallEnum.PayRecordServiceType.Commission.getIndex(),date};
		}
		return jdbcTemplate.query(sql.toString(), condition, /*new BeanPropertyRowMapper<MemberPayRecordVo>(MemberPayRecordVo.class)*/new RowMapper<MemberPayRecordVo>() {
			public MemberPayRecordVo mapRow(ResultSet rs, int arg1) throws SQLException {
				MemberPayRecordVo vo = new MemberPayRecordVo();
				vo.setRecordId(rs.getInt("record_id"));
				vo.setUserId(rs.getInt("user_id"));
				vo.setBusiType(rs.getInt("busi_type"));
				vo.setBusiDesc(rs.getString("busi_desc"));
				vo.setFundDirect(rs.getString("fund_direct"));
				vo.setMoney(rs.getBigDecimal("money"));
				vo.setBalance(rs.getBigDecimal("balance"));
				vo.setPoint(rs.getInt("point"));
				vo.setStatus(rs.getInt("status"));
				vo.setRemark(rs.getString("remark"));
				vo.setCreateTimeStr(rs.getString("create_time"));
				return vo;
			}
		});
	}
	
	/**
	 * @Description(功能描述) : 增加金额(账户充值) 
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void plusBalance4Recharge(int userId, double plus, double balance) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.RechargePlus.getIndex(),
				MallEnum.PayRecordServiceType.RechargePlus.getName(), MallEnum.PayFundDirect.Increase.getName(),
				BigDecimal.valueOf(plus), BigDecimal.valueOf(balance), "账户充值");
		this.save(record);
	}

	/**
	 * @Description(功能描述) : 增加金额(销售结算) 
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void plusBalance4Settlement(int userId, double plus, double balance, int orderId) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.SettlementPlus.getIndex(),
				MallEnum.PayRecordServiceType.SettlementPlus.getName(), MallEnum.PayFundDirect.Increase.getName(),
				BigDecimal.valueOf(plus), BigDecimal.valueOf(balance), "佣金发放,充值订单:"+orderId);
		this.save(record);
	}

	/**
	 * @Description(功能描述) : 冻结转余额
	 * @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public void plusBalance4UntFrozen(int userId, double plus, double balance, int orderId) throws FrameworkException {
		PayRecord record = new PayRecord(userId, MallEnum.PayRecordServiceType.Frozen2Blance.getIndex(),
				MallEnum.PayRecordServiceType.Frozen2Blance.getName(), MallEnum.PayFundDirect.Increase.getName(),
				BigDecimal.valueOf(plus), BigDecimal.valueOf(balance), "冻结转余额,订单号:"+orderId);
		this.save(record);
	}

	/**
	 * @Description(功能描述) : (销售结算)数量 @author(作者) : ventrue
	 * 
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public int getPaySize4Settlement(int userId, int orderId) throws FrameworkException {
		String hql = "select count(*) from PayRecord where primaryId=? and serviceType=? and typeId=?";
		return this.getTotal(hql, new Object[] { Integer.toString(userId),
				MallEnum.PayRecordServiceType.SettlementPlus.getIndex(), (long) orderId });
	}

	/**
	 * @Description(功能描述) :获取用户佣金列表 @author(作者) : ventrue
	 * @throws FrameworkException
	 * @date (开发日期) : 2015年12月19日 上午10:38:19
	 */
	public List<PayRecord> findPayRecord(long userId) throws FrameworkException {
		StringBuilder hql = new StringBuilder();
		hql.append("from PayRecord model where serviceType=2 and model.inUserId=? order by recordId desc");
		return this.list(hql.toString(), new Object[] { userId });
	}

}
