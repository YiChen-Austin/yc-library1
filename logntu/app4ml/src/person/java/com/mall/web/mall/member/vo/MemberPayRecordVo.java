package com.mall.web.mall.member.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.mall.web.pay.domain.PayRecord;

/**
 * @Description(描述) :资金变动记录表 
 * @author(作者) : chenhongxu
 * @date (开发日期) : 2015年10月7日 上午10:00
 */

public class MemberPayRecordVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int recordId; // 记录编号
	private int userId; // 会员id
	private int busiType; // 业务类型
	private String busiDesc; // 业务描述
	private String fundDirect; // 资金方向
	private BigDecimal money; // 金额（变化金额，带符号+-）
	private BigDecimal balance; // 结余
	private int point; // 消费积分
	private int status; // 状态
	private String remark; // 备注
	private Date createTime; // 创建时间
	private String createTimeStr;//字符串形式创建时间

	public static MemberPayRecordVo bean2Vo(PayRecord yzmPayRecord) {
		MemberPayRecordVo payRecord = new MemberPayRecordVo();

		payRecord.setRecordId(yzmPayRecord.getRecordId());
		payRecord.setUserId(yzmPayRecord.getUserId());
		payRecord.setBusiType(yzmPayRecord.getBusiType());
		payRecord.setBusiDesc(yzmPayRecord.getBusiDesc());
		payRecord.setFundDirect(yzmPayRecord.getFundDirect());
		payRecord.setMoney(yzmPayRecord.getMoney());
		payRecord.setBalance(yzmPayRecord.getBalance());
		payRecord.setPoint(yzmPayRecord.getPoint());
		payRecord.setStatus(yzmPayRecord.getStatus());
		payRecord.setCreateTime(yzmPayRecord.getCreateTime());
		payRecord.setRemark(yzmPayRecord.getRemark());
		return payRecord;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBusiType() {
		return busiType;
	}

	public void setBusiType(int busiType) {
		this.busiType = busiType;
	}

	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	public String getFundDirect() {
		return fundDirect;
	}

	public void setFundDirect(String fundDirect) {
		this.fundDirect = fundDirect;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}