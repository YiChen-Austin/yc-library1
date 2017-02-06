package com.mall.web.mall.domain;

import java.util.Date;
/**
 *会员个人资金账户 
 */
public class MemberDeposit
{
	private int userId;			//用户id
	private double balance;			//余额
	private double availableBalance; //可用余额
	private double expectedRevenue; //预期收益
	private int accumulatedPoint;//积分
	private double initialBalance;	//期初金额
	private Date initialOperateTime;	//期初操作时间
	private double lastInitialBalance;	//上次期初金额
	private Date lastOperateTime;		//上次期初操作时间
	private double lastLastInitialBalance;	//上上次期初金额
	private Date lastLastOperateTime;		//上上次期初操作时间
	private double lastLastLastInitialBalance;	//上上上次期初金额
	private Date lastLastLastOperateTime;		//上上上次期初操作时间
	private String phone;			//手机号
	private String payPass;			//支付密码
	private String salt;			//盐值
	private Date operateTime;		//操作时间
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }	

	public double getBalance() { return balance; }
	public void setBalance(double balance) { this.balance = balance; }

	public double getAvailableBalance() { return availableBalance; }
	public void setAvailableBalance(double availableBalance) { this.availableBalance = availableBalance; }

	public Date getOperateTime() { return operateTime; }
	public void setOperateTime(Date operateTime) { this.operateTime = operateTime; }

	public String getPayPass() { return payPass; }
	public void setPayPass(String payPass) { this.payPass = payPass; }

	public String getSalt() { return salt; }
	public void setSalt(String salt) { this.salt = salt; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }

	public double getInitialBalance() { return initialBalance; }
	public void setInitialBalance(double initialBalance) { this.initialBalance = initialBalance; }

	public Date getInitialOperateTime() { return initialOperateTime; }
	public void setInitialOperateTime(Date initialOperateTime) { this.initialOperateTime = initialOperateTime; }

	public double getLastInitialBalance() { return lastInitialBalance; }
	public void setLastInitialBalance(double lastInitialBalance) { this.lastInitialBalance = lastInitialBalance; }

	public Date getLastOperateTime() { return lastOperateTime; }
	public void setLastOperateTime(Date lastOperateTime) { this.lastOperateTime = lastOperateTime; }

	public double getLastLastInitialBalance() { return lastLastInitialBalance; }
	public void setLastLastInitialBalance(double lastLastInitialBalance) { this.lastLastInitialBalance = lastLastInitialBalance; }

	public Date getLastLastOperateTime() { return lastLastOperateTime; }
	public void setLastLastOperateTime(Date lastLastOperateTime) { this.lastLastOperateTime = lastLastOperateTime; }

	public double getLastLastLastInitialBalance() { return lastLastLastInitialBalance; }
	public void setLastLastLastInitialBalance(double lastLastLastInitialBalance) { this.lastLastLastInitialBalance = lastLastLastInitialBalance; }

	public Date getLastLastLastOperateTime() { return lastLastLastOperateTime; }
	public void setLastLastLastOperateTime(Date lastLastLastOperateTime) { this.lastLastLastOperateTime = lastLastLastOperateTime; }
	public double getExpectedRevenue() {
		return expectedRevenue;
	}
	public void setExpectedRevenue(double expectedRevenue) {
		this.expectedRevenue = expectedRevenue;
	}
	public int getAccumulatedPoint() {
		return accumulatedPoint;
	}
	public void setAccumulatedPoint(int accumulatedPoint) {
		this.accumulatedPoint = accumulatedPoint;
	}
}
