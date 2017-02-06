package com.mall.web.pay.conf;

/* *
 *类名：PayConfig
 *功能：基础配置类
 */

public class PayConfig {

	/************************************/
	// 银联在线
	// public final static String CP_PAY_GATE =
	// "http://payment-test.chinapay.com/pay/TransGet";// 测试环境
	public final static String CP_PAY_GATE = "https://payment.chinapay.com/pay/TransGet";// 生产环境
	public final static String CP_PAY_GATE_2016 = "https://payment.chinapay.com/CTITS/service/rest/page/nref/000000000017/0/0/0/0/0";// 生产环境

	// public final static String CP_MerId = "808080070894073";// 测试
	public final static String CP_MerId = "802500048160556";//
	// MerId为ChinaPay统一分配给商户的商户号，15位长度
	public final static String CP_PublicId = "999999999999999";
	public final static String CP_CuryId = "156";// （订单交易币种，3位长度，固定为人民币156 必填）
	public final static String CP_Version = "20070129";
	public final static String CP_Version_2016 = "5.0.0";
	public final static String CP_GateId = "8607";
	// 服务器异步通知页面路径
	public final static String CP_notify_url = "http://pay.cqlongtu.com/cpNotice";
	// 页面跳转同步通知页面路径
	// public final static String CP_receive_url =
	// "http://pay.cqlongtu.com/cpReceive";
	public final static String CP_receive_url = "/cpReceive";
	////////////////////////////
	//银联（APP）
	public final static String CP_MerId4App = "802500053110592";//测试
	public final static String CP_notify_url4App = "http://pay.cqlongtu.com/cpNotice4app";
	
	/************************************/
	// 网银在线
	public final static String CB_PAY_GATE = "https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8";//
	// public final static String CB_PAY_GATE =
	// "https://payment.chinapay.com/pay/TransGet";// 生产环境
	public final static String CB_v_mid = "23004968";// 商户注册后，由网银在线自动分配
	public final static String CB_v_moneytype = "CNY";// 商户注册后，由网银在线自动分配
	public final static String CB_v_md5key = "1234567890147258369";// 详情见md5相关说明

	// 服务器异步通知页面路径
	public final static String CB_notify_url = "[url:=http://pay.cqlongtu.com/cbNotice]";
	// 页面跳转同步通知页面路径
	// public final static String CB_receive_url =
	// "http://pay.cqlongtu.com/cbReceive";
	public final static String CB_receive_url = "http://pay.cqlongtu.com/cbReceive";
	/************************************/
	// 快钱
	public final static String BILL_PAY_GATE = "https://www.99bill.com/gateway/recvMerchantInfoAction.htm";// 生产
	// public final static String BILL_PAY_GATE =
	// "https://sandbox.99bill.com/gateway/recvMerchantInfoAction.htm";// 测试
	public final static String BILL_inputCharset = "1";// 字符集(1代表UTF-8; 2代表GBK;
														// 3代表GB2312)
	public final static String BILL_version = "v2.0";// 固定值：v2.0
	public final static String BILL_language = "1";// 固定值：1,1代表中文显示
	public final static String BILL_signType = "4";// 4代表DSA戒者RSA签名方式
	public final static String BILL_merchantAcctId = "1002373499101";// 人民币账号
	public final static String BILL_merchantAcctId_KJ = "1002386360501";// 人民币账号(快捷支付)
	// public final static String BILL_merchantAcctId = "1001213884201";// 测试

	public final static String BILL_notify_url = "http://pay.cqlongtu.com/billNotice";// 服务器异步通知页面路径
	// public final static String BILL_receive_url =
	// "http://pay.cqlongtu.com/yzmall/billReceive";// 页面跳转同步通知页面路径
	public final static String BILL_receive_url = "http://pay.cqlongtu.com/billReceive";// 测试

	/************************************/
	// 微信支付
	public final static String WEIXIN_receive_url = "http://pay.cqlongtu.com/wxnotic";// 微信回调
	
}
