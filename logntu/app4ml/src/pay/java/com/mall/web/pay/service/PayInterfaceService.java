package com.mall.web.pay.service;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.domain.GeneralOrder;
import com.mall.web.mall.third.alipay.AlipayConfig;
import com.mall.web.mall.third.alipay.util.AlipaySubmit;
import com.mall.web.mall.third.alipay.util.AlipaySubmit4Wap;
import com.mall.web.mall.third.alipay.util.UtilDate;
import com.mall.web.mobile.third.util.alipay.Alipay4appConf;
import com.mall.web.pay.conf.PayConfig;
import com.mall.web.pay.domain.PayInteraction;
import com.mall.web.pay.domain.PayPrimary;
import com.mall.web.pay.util.PayUtil;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.protocol.qr.unifiedorder_protocol.UnifiedorderReqData;
import com.tencent.protocol.qr.unifiedorder_protocol.UnifiedorderResData;
import com.tencent.service.qr.UnifiedorderService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.unionpay.acp.sdk.AcpService;
import com.unionpay.acp.sdk.SDKConfig;

/**
 * 主要负责商城支与第三方支付的业务处理
 * 
 * @category 商城第三支付业务层
 * 
 */
@Service("payInterfaceService")
public class PayInterfaceService {
	// private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PayDataService mallPayDataService;

	/**
	 * 银联在线
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @throws Exception
	 * 
	 */
	public String transferChianpay(String[] orderSns, String orderType, float amount, long userId) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.getCpOrderNo();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 请求状态
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("MerId", PayConfig.CP_MerId);// （MerId为ChinaPay统一分配给商户的商户号，15位长度，必填
		sParaTemp.put("OrdId", orderId);// 商户提交给ChinaPay的交易订单号，16位长度，必填
		sParaTemp.put("TransAmt", PayUtil.leftAppendZero(Integer.toString((int) (amount * 100)), 12));// （订单交易金额，12位长度，左补0，
		// 必填,单位为分）
		sParaTemp.put("CuryId", PayConfig.CP_CuryId);// （订单交易币种，3位长度，固定为人民币156，
														// 必填）
		sParaTemp.put("TransDate", DateUtil.dateToString(CommonConstant.DATE_SHORT_SIMPLE_FORMAT));// （订单交易日期，8位长度，必填）
		sParaTemp.put("TransType", "0001");// （交易类型，4位长度，必填）
		sParaTemp.put("Version", PayConfig.CP_Version);// （支付接入版本号，必填）
		sParaTemp.put("BgRetUrl", PayConfig.CP_notify_url);// 后台交易接收URL，长度不要超过80个字节，必填）
		sParaTemp.put("PageRetUrl", PayConfig.CP_receive_url);// 页面交易接收URL，长度不要超过80个字节，必填）
		sParaTemp.put("GateId", PayConfig.CP_GateId);// （支付网关号，可选）
		sParaTemp.put("Priv1", Long.toString(System.currentTimeMillis()));// （商户私有域，长度不要超过60个字节）
		String chkValue = null;//PayUtil.buildCpChkValue(sParaTemp, "Priv1");
		sParaTemp.put("ChkValue", chkValue);// （256字节长的ASCII码,为此次交易提交关键数
											// 据的数字签名，必填）

		// 建立请求
		String sHtmlText = PayUtil.buildRequest(PayConfig.CP_PAY_GATE, sParaTemp, "POST", "确认", false);

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(sParaTemp.toString());
		mallPayDataService.saveYzmPayInteraction(pia);

		return sHtmlText;
	}

	/**
	 * 银联在线
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @throws Exception
	 * 
	 */
	public String transferChianpay2016(String[] orderSns, String orderType, double amount, double balance, long userId, String return_url)
			throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.getCpOrderNo();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setBalance(BigDecimal.valueOf(balance));
		payPri.setStatus(1);// 请求状态
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(new Date());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		Map<String, String> requestData = new HashMap<String, String>();

		/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
		requestData.put("version", PayConfig.CP_Version_2016); // 版本号，全渠道默认值
		requestData.put("encoding", CommonConstant.UTF8); // 字符集编码，可以使用UTF-8,GBK两种方式
		requestData.put("signMethod", "01"); // 签名方法，只支持 01：RSA方式证书加密
		requestData.put("txnType", "01"); // 交易类型 ，01：消费
		requestData.put("txnSubType", "01"); // 交易子类型， 01：自助消费
		requestData.put("bizType", "000201"); // 业务类型，B2C网关支付，手机wap支付
		requestData.put("channelType", "07"); // 渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板
												// 08：手机

		/*** 商户接入参数 ***/
		requestData.put("merId", PayConfig.CP_MerId); // 商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
		requestData.put("accessType", "0"); // 接入类型，0：直连商户
		requestData.put("orderId", orderId); // 商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
		requestData.put("txnTime",DateUtil.dateToString(DateUtil.getGMTDate(), CommonConstant.DATE_WITHSECOND_G_FORMAT)); // 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
		requestData.put("currencyCode", "156"); // 交易币种（境内商户一般是156 人民币）
		requestData.put("txnAmt", Integer.toString((int) (amount * 100))); // 交易金额，单位分，不要带小数点
		// requestData.put("reqReserved", "透传字段");
		// //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

		// 前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
		// 如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
		// 异步通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 网关支付产品接口规范 消费交易 商户通知
		requestData.put("frontUrl", return_url+PayConfig.CP_receive_url);

		// 后台通知地址（需设置为【外网】能访问 http
		// https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
		// 后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 网关支付产品接口规范 消费交易 商户通知
		// 注意:1.需设置为外网能访问，否则收不到通知 2.http https均可 3.收单后台通知后需要10秒内返回http200或302状态码
		// 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
		// 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d
		// 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
		requestData.put("backUrl", PayConfig.CP_notify_url);

		//////////////////////////////////////////////////
		//
		// 报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
		//
		//////////////////////////////////////////////////

		/** 请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面 **/
		Map<String, String> submitFromData = AcpService.sign(requestData, CommonConstant.UTF8); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl(); // 获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
		String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData,CommonConstant.UTF8); // 生成自动跳转的Html表单



		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(new Date());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(submitFromData.toString());
		mallPayDataService.saveYzmPayInteraction(pia);

		return html;
	}

	/**
	 * 银联在线(app)
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @throws Exception
	 * 
	 */
//	public String unionpay4app(String[] orderSns, String orderType, float amount, long userId) throws Exception {
//		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
//		if (orders == null || orders.size() <= 0)
//			throw new Exception("no orders");
//		// ///////////////////////////////////////////////////////
//		// 1、记录支付信息、订单、支付对应关系
//		String orderId = "up" + userId + Long.toString(
//				System.currentTimeMillis())/* PayUtil.getCpOrderNo() */;
//		PayPrimary payPri = new PayPrimary();
//		payPri.setIdentityId(orderId);// 支付编号
//		payPri.setThridOrderSn(orderId);// 支付编号
//		payPri.setMoney(BigDecimal.valueOf(amount));
//		payPri.setStatus(1);// 请求状态
//		payPri.setUserId(userId);// 用户ID
//		payPri.setRequestTime(DateUtil.getGMTDate());
//		mallPayDataService.savePayPrimary(payPri);
//
//		mallPayDataService.savePayOrders(orders, orderType, payPri);
//
//		// ////////////////////////////////////////////////////////////////////////////////
//		// 2、构造支付请求数据
//		String txnTime = DateUtil.dateToString(payPri.getRequestTime(), CommonConstant.DATE_WITHSECOND_G_FORMAT);
//		String txnAmt = Integer.toString((int) (amount * 100));
//
//		Map<String, String> contentData = new HashMap<String, String>();
//
//		/*** 银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改 ***/
//		//contentData.put("version", UnionpayBase.version); // 版本号 全渠道默认值
//		//contentData.put("encoding", UnionpayBase.encoding_UTF8); // 字符集编码
//																	// 可以使用UTF-8,GBK两种方式
//		contentData.put("signMethod", "01"); // 签名方法 目前只支持01：RSA方式证书加密
//		contentData.put("txnType", "01"); // 交易类型 01:消费
//		contentData.put("txnSubType", "01"); // 交易子类 01：消费
//		contentData.put("bizType", "000201"); // 填写000201
//		contentData.put("channelType", "08"); // 渠道类型 08手机
//
//		/*** 商户接入参数 ***/
//		contentData.put("merId", PayConfig.CP_MerId4App); // 商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
//		contentData.put("accessType", "0"); // 接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构
//											// 2：平台商户）
//		contentData.put("orderId", orderId); // 商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
//		contentData.put("txnTime", txnTime); // 订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
//		contentData.put("accType", "01"); // 账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
//		contentData.put("txnAmt", txnAmt); // 交易金额 单位为分，不能带小数点
//		contentData.put("currencyCode", "156"); // 境内商户固定 156 人民币
//		// contentData.put("reqReserved", "透传字段"); //商户自定义保留域，交易应答时会原样返回
//
//		// 后台通知地址（需设置为外网能访问 http
//		// https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，【支付失败的交易银联不会发送后台通知】
//		// 后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 网关支付产品接口规范 消费交易 商户通知
//		// 注意:1.需设置为外网能访问，否则收不到通知 2.http https均可 3.收单后台通知后需要10秒内返回http200或302状态码
//		// 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200或302，那么银联会间隔一段时间再次发送。总共发送5次，银联后续间隔1、2、4、5
//		// 分钟后会再次通知。
//		// 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d
//		// 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
//		contentData.put("backUrl", PayConfig.CP_notify_url4App);
//
//		/** 对请求参数进行签名并发送http post请求，接收同步应答报文 **/
//		Map<String, String> reqData = AcpService.sign(contentData, UnionpayBase.encoding_UTF8); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
//		String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl(); // 交易请求url从配置文件读取对应属性文件acp_sdk.properties中的
//																			// acpsdk.backTransUrl
//		Map<String, String> rspData = AcpService.post(reqData, requestAppUrl, UnionpayBase.encoding_UTF8); // 发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
//
//		/** 对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考-------------> **/
//		String tn = null;
//		// 应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录》
//		// System.out.println(">>>>>>>>>>>:"+reqData);
//		// System.out.println(">>>>>>>>>>>:"+rspData);
//		if (!rspData.isEmpty()) {
//			if (AcpService.validate(rspData, UnionpayBase.encoding_UTF8)) {
//				LogUtil.writeLog("验证签名成功");
//				String respCode = rspData.get("respCode");
//				if (("00").equals(respCode)) {
//					// 成功,获取tn号
//					tn = rspData.get("tn");
//				} else {
//					// 其他应答码为失败请排查原因或做失败处理
//					// TODO
//				}
//			} else {
//				LogUtil.writeErrorLog("验证签名失败");
//				// TODO 检查验证签名失败的原因
//			}
//		} else {
//			// 未返回正确的http状态
//			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
//		}
//		String reqMessage = UnionpayBase.genHtmlResult(reqData);
//		String rspMessage = UnionpayBase.genHtmlResult(rspData);
//		// ////////////////////////////////////////////////////////////////////////////////
//		// 3、记录支付请求数据
//		PayInteraction pia = new PayInteraction();
//		pia.setCreateTime(DateUtil.getGMTDate());
//		pia.setMoney(BigDecimal.valueOf(amount));
//		pia.setStatus(1);
//		pia.setOrderId(orderId);
//		pia.setReactionData(reqMessage + "<br>+" + rspMessage);
//		mallPayDataService.saveYzmPayInteraction(pia);
//
//		return tn;
//	}

	/**
	 * 网银在线
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @throws Exception
	 * 
	 */
	public String transferChianbank(String[] orderSns, String orderType, float amount, long userId) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.gntChinabankOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 请求状态
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("v_mid", PayConfig.CB_v_mid);// 商户编号
		sParaTemp.put("v_oid", orderId);// 订单编号
		DecimalFormat df = new DecimalFormat("#.00");
		sParaTemp.put("v_amount", df.format(amount));// 订单总金额
		sParaTemp.put("v_moneytype", PayConfig.CB_v_moneytype);// 币种
		sParaTemp.put("v_url", PayConfig.CB_receive_url);// 消费者完成购物后页面返回的商户页面

		sParaTemp.put("v_md5info", PayUtil.buildCbMd5(sParaTemp));// （订单交易日期，8位长度，必填）
		sParaTemp.put("remark2", PayConfig.CB_notify_url);// 结果通知页面

		// 建立请求
		String sHtmlText = PayUtil.buildRequest(PayConfig.CB_PAY_GATE, sParaTemp, "POST", "确认", false);

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(sParaTemp.toString());
		mallPayDataService.saveYzmPayInteraction(pia);

		return sHtmlText;
	}

	/****************************************/
	/**
	 * 快钱支付
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @throws Exception
	 * 
	 */
	public String transfer99Bill(String[] orderSns, String orderType, float amount, long userId) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.gntBillOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 请求状态
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new LinkedHashMap<String, String>();
		sParaTemp.put("inputCharset", PayConfig.BILL_inputCharset);// 字符集
		sParaTemp.put("pageUrl", PayConfig.BILL_receive_url);// 接叐支付结果的页面地址
		sParaTemp.put("bgUrl", PayConfig.BILL_notify_url);// 服务器接叐支付结果的后台地址
		sParaTemp.put("version", PayConfig.BILL_version);// 网关版本
		sParaTemp.put("language", PayConfig.BILL_language);// 网关页面显示诧言种类
		sParaTemp.put("signType", PayConfig.BILL_signType);// 签名类型

		sParaTemp.put("merchantAcctId", PayConfig.BILL_merchantAcctId);// 人民币账号
		sParaTemp.put("payerName", "");// 支付人姓名
		sParaTemp.put("payerContactType", "");// 支付人联系类型，1
												// 代表电子邮件方式；2代表手机联系方式。可以为空。
		sParaTemp.put("payerContact", "");// 支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。

		sParaTemp.put("orderId", orderId);// 订单号，不可谓空
		sParaTemp.put("orderAmount", Integer.toString((int) (amount * 100)));// 以分为单
		sParaTemp.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 商户订单提交时间
		sParaTemp.put("orderTimestamp", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 快钱时间戳
		sParaTemp.put("productName", "");
		sParaTemp.put("productNum", "");
		sParaTemp.put("productId", "");
		sParaTemp.put("productDesc", "");
		sParaTemp.put("ext1", "");
		sParaTemp.put("ext2", "");
		sParaTemp.put("payType", "00");// 支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
		sParaTemp.put("bankId", "");
		sParaTemp.put("redoFlag", "0");// 同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
		sParaTemp.put("pid", "");// 快钱合作伙伴的帐户号，即商户编号，可为空。
		String signMsg = PayUtil.gntSignMsg(sParaTemp);
		sParaTemp.put("signMsg", signMsg);

		// 建立请求
		String sHtmlText = PayUtil.buildRequest(PayConfig.BILL_PAY_GATE, sParaTemp, "POST", "确认", true);

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(sParaTemp.toString());
		mallPayDataService.saveYzmPayInteraction(pia);
		return sHtmlText;
	}

	/***************************************************************************************/
	/**
	 * 网银在线
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param pmode_id
	 *            银行编码
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public String bankDirectChianbank(String[] orderSns, String orderType, float amount, String pmode_id, int payType,
			long userId) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.gntChinabankOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 支付中
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("v_mid", PayConfig.CB_v_mid);// 商户编号
		sParaTemp.put("v_oid", orderId);// 订单编号
		DecimalFormat df = new DecimalFormat("#.00");
		sParaTemp.put("v_amount", df.format(amount));// 订单总金额
		sParaTemp.put("v_moneytype", PayConfig.CB_v_moneytype);// 币种
		sParaTemp.put("v_url", PayConfig.CB_receive_url);// 消费者完成购物后页面返回的商户页面

		sParaTemp.put("v_md5info", PayUtil.buildCbMd5(sParaTemp));// （订单交易日期，8位长度，必填）
		sParaTemp.put("pmode_id", pmode_id);// 网银在线定义的银行编码
		sParaTemp.put("remark2", PayConfig.CB_notify_url);// 结果通知页面

		// 建立请求
		String sHtmlText = PayUtil.buildRequest(PayConfig.CB_PAY_GATE, sParaTemp, "POST", "确认", false);

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(sParaTemp.toString());
		mallPayDataService.saveYzmPayInteraction(pia);

		return sHtmlText;
	}

	/****************************************/
	/**
	 * 快钱支付
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param bankId
	 *            银行编码
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public String bankDirect99Bill(String[] orderSns, String orderType, float amount, String bankId, int payType,
			long userId) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.gntBillOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 请求状态
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		payPri.setBusinessType("0");
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new LinkedHashMap<String, String>();
		sParaTemp.put("inputCharset", PayConfig.BILL_inputCharset);// 字符集
		sParaTemp.put("pageUrl", PayConfig.BILL_receive_url);// 接叐支付结果的页面地址
		sParaTemp.put("bgUrl", PayConfig.BILL_notify_url);// 服务器接叐支付结果的后台地址
		sParaTemp.put("version", PayConfig.BILL_version);// 网关版本
		sParaTemp.put("language", PayConfig.BILL_language);// 网关页面显示诧言种类
		sParaTemp.put("signType", PayConfig.BILL_signType);// 签名类型

		sParaTemp.put("merchantAcctId", PayConfig.BILL_merchantAcctId);// 人民币账号
		sParaTemp.put("payerName", "");// 支付人姓名
		sParaTemp.put("payerContactType", "");// 支付人联系类型，1
												// 代表电子邮件方式；2代表手机联系方式。可以为空。
		sParaTemp.put("payerContact", "");// 支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。

		sParaTemp.put("orderId", orderId);// 订单号，不可谓空
		sParaTemp.put("orderAmount", Integer.toString((int) (amount * 100)));// 以分为单
		sParaTemp.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 商户订单提交时间
		sParaTemp.put("orderTimestamp", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 快钱时间戳
		sParaTemp.put("productName", "");
		sParaTemp.put("productNum", "");
		sParaTemp.put("productId", "");
		sParaTemp.put("productDesc", "");
		sParaTemp.put("ext1", "");
		sParaTemp.put("ext2", "");
		// 借记卡(快捷支付)
		if (payType == 1) {
			sParaTemp.put("payType", "20-1");// 10-1 代表储蓄卡网银支付
		} else // 信用卡(快捷支付)
		if (payType == 2) {
			sParaTemp.put("payType", "20-2");// 10-2代表信用卡网银支付
		} else // 借记卡(网银支付)
		if (payType == 3) {
			sParaTemp.put("payType", "10-1");// 10-1 代表储蓄卡网银支付
		} else // 信用卡(网银支付)
		if (payType == 4) {
			sParaTemp.put("payType", "10-2");// 10-2代表信用卡网银支付
		} else // 企业网银
		if (payType == 5) {
			sParaTemp.put("payType", "14");// 14代表显示企业网银支付；
		} else
			sParaTemp.put("payType", "00");// 支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
		if (payType >= 1 && payType <= 5)
			sParaTemp.put("bankId", bankId);
		else
			sParaTemp.put("bankId", "");
		sParaTemp.put("redoFlag", "0");// 同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
		sParaTemp.put("pid", "");// 快钱合作伙伴的帐户号，即商户编号，可为空。
		String signMsg = PayUtil.gntSignMsg(sParaTemp);
		sParaTemp.put("signMsg", signMsg);

		// 建立请求
		String sHtmlText = PayUtil.buildRequest(PayConfig.BILL_PAY_GATE, sParaTemp, "POST", "确认", true);

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(sParaTemp.toString());
		pia.setReturnOrderId("0");
		pia.setReturnMoney(BigDecimal.valueOf(0));
		mallPayDataService.saveYzmPayInteraction(pia);
		return sHtmlText;
	}

	/**
	 * 快钱支付(快捷支付)
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param payerId
	 *            会员编号
	 * @throws Exception
	 * 
	 */
	public String bankQuick99BillKj(String[] orderSns, String orderType, float amount, String payerId, long userId)
			throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = PayUtil.gntBillOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 请求状态
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		payPri.setBusinessType("0");
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new LinkedHashMap<String, String>();
		sParaTemp.put("inputCharset", PayConfig.BILL_inputCharset);// 字符集
		sParaTemp.put("pageUrl", PayConfig.BILL_receive_url);// 接叐支付结果的页面地址
		sParaTemp.put("bgUrl", PayConfig.BILL_notify_url);// 服务器接叐支付结果的后台地址
		sParaTemp.put("version", PayConfig.BILL_version);// 网关版本
		sParaTemp.put("language", PayConfig.BILL_language);// 网关页面显示诧言种类
		sParaTemp.put("signType", PayConfig.BILL_signType);// 签名类型

		sParaTemp.put("merchantAcctId", PayConfig.BILL_merchantAcctId_KJ);// 人民币账号
		sParaTemp.put("payerName", "");// 支付人姓名
		sParaTemp.put("payerContactType", "");// 支付人联系类型，1
												// 代表电子邮件方式；2代表手机联系方式。可以为空。
		sParaTemp.put("payerContact", "");// 支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
		sParaTemp.put("payerIdType", "3");// 数字串 类型固定值0，1，2，3 0代表不指定
											// 1代表通过商户方ID指定付款人 2代表通过快钱账户指定付款人 3
											// 代表付款方在商户方的会员编号(当需要支持保存信息功能的快捷支付时，,需上送此项)
											// 4代表企业网银的交通银行直连 如果为空代表不需要指定
		sParaTemp.put("payerId", payerId);// 付款方在商户方的会员编号
		sParaTemp.put("orderId", orderId);// 订单号，不可谓空
		sParaTemp.put("orderAmount", Integer.toString((int) (amount * 100)));// 以分为单
		sParaTemp.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 商户订单提交时间
		sParaTemp.put("orderTimestamp", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));// 快钱时间戳
		sParaTemp.put("productName", "");
		sParaTemp.put("productNum", "");
		sParaTemp.put("productId", "");
		sParaTemp.put("productDesc", "");
		sParaTemp.put("ext1", "");
		sParaTemp.put("ext2", "");
		// 借记卡(快捷支付)
		// if (payType == 1) {
		// sParaTemp.put("payType", "20-1");// 10-1 代表储蓄卡网银支付
		// } else // 信用卡(快捷支付)
		// if (payType == 2) {
		// sParaTemp.put("payType", "20-2");// 10-2代表信用卡网银支付
		// } else // 借记卡(网银支付)
		// if (payType == 3) {
		// sParaTemp.put("payType", "10-1");// 10-1 代表储蓄卡网银支付
		// } else // 信用卡(网银支付)
		// if (payType == 4) {
		// sParaTemp.put("payType", "10-2");// 10-2代表信用卡网银支付
		// } else // 企业网银
		// if (payType == 5) {
		// sParaTemp.put("payType", "14");// 14代表显示企业网银支付；
		// } else
		sParaTemp.put("payType", "00");// 支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
		// if (payType >= 1 && payType <= 5)
		// sParaTemp.put("bankId", bankId);
		// else
		sParaTemp.put("bankId", "");
		sParaTemp.put("redoFlag", "0");// 同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
		sParaTemp.put("pid", "");// 快钱合作伙伴的帐户号，即商户编号，可为空。
		String signMsg = PayUtil.gntSignMsg(sParaTemp);
		sParaTemp.put("signMsg", signMsg);

		// 建立请求
		String sHtmlText = PayUtil.buildRequest(PayConfig.BILL_PAY_GATE, sParaTemp, "POST", "确认", true);

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(sParaTemp.toString());
		pia.setReturnOrderId("0");
		pia.setReturnMoney(BigDecimal.valueOf(0));
		mallPayDataService.saveYzmPayInteraction(pia);
		return sHtmlText;
	}

	/***************************************************************************************/
	/**
	 * 支付宝
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public String bankAlipay(String[] orderSns, String orderType, float amount, long userId, String subject,
			String desc, String showUrl, String phishingKey, String invokeIp, String return_url) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = "ali" + UtilDate.getOrderNum();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 支付中
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 支付类型
		String payment_type = AlipayConfig.payment_type;
		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = AlipayConfig.notify_url;
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		// 页面跳转同步通知页面路径
		return_url += AlipayConfig.return_url;
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/ //卖家支付宝帐户
		String seller_email = AlipayConfig.seller_email;
		// 必填 //商户订单号
		String out_trade_no = orderId;
		// 商户网站订单系统中唯一订单号，必填 //订单名称
		// String subject = "全球狗商城订单";
		// 必填 //付款金额
		// 付款金额
		String total_fee = null;//
		DecimalFormat df = new DecimalFormat("#0.00");
		total_fee = df.format(amount);
		// 必填 //订单描述
		String body = desc;
		// 商品展示地址
		String show_url = showUrl;

		// 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html //防钓鱼时间戳
		String anti_phishing_key = phishingKey;
		// 若要使用请调用类文件submit中的query_timestamp函数 //客户端的IP地址
		String exter_invoke_ip = invokeIp;
		// 非局域网的外网IP地址，如：221.0.0.1

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(new String(sParaTemp.toString().getBytes("GBK"), "utf8"));
		mallPayDataService.saveYzmPayInteraction(pia);

		return sHtmlText;
	}

	/**
	 * 支付宝
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public String bankAlipay4wap(String[] orderSns, String orderType, float amount, long userId, String subject)
			throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = "ali" + UtilDate.getOrderNum();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 支付中
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = AlipayConfig.notify_url;
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		// 页面跳转同步通知页面路径
		String return_url = AlipayConfig.return_url4wap;
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/ //卖家支付宝帐户
		String seller_email = AlipayConfig.seller_email;
		// 必填 //商户订单号
		String out_trade_no = orderId;
		// 商户网站订单系统中唯一订单号，必填 //订单名称
		// String subject = "全球狗商城订单";
		// 必填 //付款金额
		// 付款金额
		String total_fee = null;//
		DecimalFormat df = new DecimalFormat("#.00");
		total_fee = df.format(amount);

		// ////////////////////////////////////////////////////////////////////////////////

		// 请求业务参数详细
		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>"
				+ return_url + "</call_back_url><seller_account_name>" + seller_email
				+ "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject
				+ "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + AlipayConfig.merchant_url
				+ "</merchant_url></direct_trade_create_req>";
		// 必填

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
		sParaTempToken.put("partner", AlipayConfig.partner);
		sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
		sParaTempToken.put("sec_id", AlipayConfig.sign_type);
		sParaTempToken.put("format", AlipayConfig.format);
		sParaTempToken.put("v", AlipayConfig.v);
		sParaTempToken.put("req_id", UtilDate.getOrderNum());
		sParaTempToken.put("req_data", req_dataToken);

		// 建立请求
		String sHtmlTextToken = AlipaySubmit4Wap.buildRequest(AlipayConfig.ALIPAY_GATEWAY_NEW_4WAP, "", "",
				sParaTempToken);
		// URLDECODE返回的信息
		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, AlipayConfig.input_charset);
		// 获取token
		String request_token = AlipaySubmit4Wap.getRequestToken(sHtmlTextToken);
		// out.println(request_token);

		// //////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////

		// 业务详细
		String req_data = "<auth_and_execute_req><request_token>" + request_token
				+ "</request_token></auth_and_execute_req>";
		// 必填

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("sec_id", AlipayConfig.sign_type);
		sParaTemp.put("format", AlipayConfig.format);
		sParaTemp.put("v", AlipayConfig.v);
		sParaTemp.put("req_data", req_data);

		// 建立请求
		String sHtmlText = AlipaySubmit4Wap.buildRequest(AlipayConfig.ALIPAY_GATEWAY_NEW_4WAP, sParaTemp, "get", "确认");

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(new String(sParaTemp.toString().getBytes("GBK"), "utf8"));
		mallPayDataService.saveYzmPayInteraction(pia);

		return sHtmlText;
	}

	/**
	 * 支付宝(app)
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public String bankAlipay4app(String[] orderSns, String orderType, float amount, long userId, String subject)
			throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = "ali" + UtilDate.getOrderNum();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 支付中
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = AlipayConfig.notify_url;
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		// 页面跳转同步通知页面路径
		String return_url = AlipayConfig.return_url4wap;
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/ //卖家支付宝帐户
		String seller_email = AlipayConfig.seller_email;
		// 必填 //商户订单号
		String out_trade_no = orderId;
		// 商户网站订单系统中唯一订单号，必填 //订单名称
		// String subject = "全球狗商城订单";
		// 必填 //付款金额
		// 付款金额
		String total_fee = null;//
		DecimalFormat df = new DecimalFormat("#######0.00");
		total_fee = df.format(amount);

		String body = "全球狗商城订单";

		// ////////////////////////////////////////////////////////////////////////////////

		// 请求业务参数详细
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + seller_email + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + total_fee + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notify_url + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"" + return_url + "\"";
		// ////////////////////////////////////////////////////////////////////////////////
		// 签名
		String sign = Alipay4appConf.sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + Alipay4appConf.getSignType();

		// ////////////////////////////////////////////////////////////////////////////////
		// 3、记录支付请求数据
		PayInteraction pia = new PayInteraction();
		pia.setCreateTime(DateUtil.getGMTDate());
		pia.setMoney(BigDecimal.valueOf(amount));
		pia.setStatus(1);
		pia.setOrderId(orderId);
		pia.setReactionData(new String(payInfo.toString().getBytes("GBK"), "utf8"));
		mallPayDataService.saveYzmPayInteraction(pia);

		return payInfo;
	}

	/***************************************************************************************/
	/**
	 * 微信支付
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public UnifiedorderResData bankWeixin(String appId, String mchId, String key, java.lang.String certLocalPath,
			java.lang.String certPassword, String[] orderSns, String orderType, float amount, long userId,
			String notifyUrl, String invokeIp, String tradeType, String openid) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = UtilDate.getOrderNum() + UtilDate.getThree() + orders.get(0).getOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setStatus(1);// 支付中
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(DateUtil.getGMTDate());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		String deviceInfo = "WEB";
		String body = "龙途游戏充值订单";
		String detail = null;
		String attach = null;
		String outTradeNo = orderId;
		String feeType = "CNY";
		int totalFee = (int) (amount * 100);
		String spBillCreateIP = invokeIp;
		String timeStart = null;
		String timeExpire = null;
		String goodsTag = null;
		// String notifyUrl = "http://183.230.133.122:8081/wxnotic.action";
		// String tradeType = "NATIVE";
		String productId = payPri.getPrimaryId();
		String limitPay = null;
		// String openid = null;

		UnifiedorderReqData unifiedorderReqData = new UnifiedorderReqData(appId, mchId, key, deviceInfo, body, detail,
				attach, outTradeNo, feeType, totalFee, spBillCreateIP, timeStart, timeExpire, goodsTag, notifyUrl,
				tradeType, productId, limitPay, openid);

		// 解决XStream对出现双下划线的bug
		XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		// 将要提交给API的数据对象转换成XML格式数据Post给API
		String postDataXML = xStreamForRequestPostData.toXML(unifiedorderReqData);

		PayInteraction piaReq = new PayInteraction();
		piaReq.setCreateTime(DateUtil.getGMTDate());
		piaReq.setMoney(BigDecimal.valueOf(amount));
		piaReq.setStatus(1);
		piaReq.setOrderId(orderId);
		piaReq.setReactionData(new String(postDataXML.getBytes("GBK"), "utf8"));
		mallPayDataService.saveYzmPayInteraction(piaReq);

		UnifiedorderService service = new UnifiedorderService(certLocalPath, certPassword);
		String resp = service.request(unifiedorderReqData);
		UnifiedorderResData respData = (UnifiedorderResData) Util.getObjectFromXML(resp, UnifiedorderResData.class);

		// System.out.println(">>>>req:"+postDataXML);
		// System.out.println(">>>>rsp:"+resp);

		PayInteraction piaRsp = new PayInteraction();
		piaRsp.setCreateTime(DateUtil.getGMTDate());
		piaRsp.setMoney(BigDecimal.valueOf(amount));
		piaRsp.setStatus(1);
		piaRsp.setOrderId(orderId);
		piaRsp.setReactionData(new String(resp.getBytes("GBK"), "utf8"));
		mallPayDataService.saveYzmPayInteraction(piaRsp);

		respData.setDevice_info(orderId);// 借用设备信息，传递订单号
		return respData;
	}

	/**
	 * 微信支付
	 * 
	 * 1、记录支付信息、订单、支付对应关系
	 * 
	 * 2、构造支付请求数据
	 * 
	 * 3、记录支付请求数据
	 * 
	 * @param orderSns
	 *            订单组
	 * @param orderType
	 *            订单类型
	 * @param amount
	 *            订单总金额
	 * @param int
	 *            payType 支付类型
	 * @throws Exception
	 * 
	 */
	public UnifiedorderResData bankWeixin(String[] orderSns, String orderType, double amount, double balance,
			long userId, String notifyUrl, String invokeIp) throws Exception {
		List<GeneralOrder> orders = mallPayDataService.getOrdersBySn(orderSns);
		if (orders == null || orders.size() <= 0)
			throw new Exception("no orders");
		// ///////////////////////////////////////////////////////
		// 1、记录支付信息、订单、支付对应关系
		String orderId = UtilDate.getOrderNum() + UtilDate.getThree() + orders.get(0).getOrderId();
		PayPrimary payPri = new PayPrimary();
		payPri.setIdentityId(orderId);// 支付编号
		payPri.setThridOrderSn(orderId);// 支付编号
		payPri.setMoney(BigDecimal.valueOf(amount));
		payPri.setBalance(BigDecimal.valueOf(balance));
		payPri.setStatus(1);// 支付中
		payPri.setUserId(userId);// 用户ID
		payPri.setRequestTime(new Date());
		mallPayDataService.savePayPrimary(payPri);

		mallPayDataService.savePayOrders(orders, orderType, payPri);

		// ////////////////////////////////////////////////////////////////////////////////
		// 2、构造支付请求数据

		String deviceInfo = "WEB";
		String body = "龙途游戏充值订单";
		String detail = null;
		String attach = null;
		String outTradeNo = orderId;
		String feeType = "CNY";
		int totalFee = (int) (amount * 100);
		String spBillCreateIP = invokeIp;
		String timeStart = null;
		String timeExpire = null;
		String goodsTag = null;
		// String notifyUrl = "http://183.230.133.122:8081/wxnotic.action";
		String tradeType = "NATIVE";
		String productId = payPri.getPrimaryId();
		String limitPay = null;
		String openid = null;

		UnifiedorderReqData unifiedorderReqData = new UnifiedorderReqData(Configure.getAppID4mp(),
				Configure.getMchID4mp(), Configure.getPwd4mp(), deviceInfo, body, detail, attach, outTradeNo, feeType,
				totalFee, spBillCreateIP, timeStart, timeExpire, goodsTag, notifyUrl, tradeType, productId, limitPay,
				openid);

		// 解决XStream对出现双下划线的bug
		XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		// 将要提交给API的数据对象转换成XML格式数据Post给API
		String postDataXML = xStreamForRequestPostData.toXML(unifiedorderReqData);

		PayInteraction piaReq = new PayInteraction();
		piaReq.setCreateTime(new Date());
		piaReq.setMoney(BigDecimal.valueOf(amount));
		piaReq.setStatus(1);
		piaReq.setOrderId(orderId);
		piaReq.setReactionData(postDataXML);
		mallPayDataService.saveYzmPayInteraction(piaReq);

		UnifiedorderService service = new UnifiedorderService(Configure.getCertLocalPath4mp(),
				Configure.getCertPassword4mp());
		String resp = service.request(unifiedorderReqData);
		UnifiedorderResData respData = (UnifiedorderResData) Util.getObjectFromXML(resp, UnifiedorderResData.class);

		PayInteraction piaRsp = new PayInteraction();
		piaRsp.setCreateTime(new Date());
		piaRsp.setMoney(BigDecimal.valueOf(amount));
		piaRsp.setStatus(1);
		piaRsp.setOrderId(orderId);
		piaRsp.setReactionData(resp);
		mallPayDataService.saveYzmPayInteraction(piaRsp);

		respData.setDevice_info(orderId);// 借用设备信息，传递订单号
		return respData;
	}
}
