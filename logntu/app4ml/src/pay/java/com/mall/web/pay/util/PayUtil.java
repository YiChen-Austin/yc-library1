package com.mall.web.pay.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mall.common.constant.CommonConstant;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.pay.conf.PayConfig;


/**
 * 类名：PaySubmit 功能：接口请求提交类 详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 */

public class PayUtil {
	/**********************/
	// 银联在线
	

	// 银联订单号
	private static int cpOrderNo = 1;

	/**
	 * 获取测试订单号
	 */
	public synchronized static String getCpOrderNo() {
		cpOrderNo = cpOrderNo >= 99 ? 1 : cpOrderNo + 1;
		String orderNo = Integer.toString(cpOrderNo);
		orderNo = orderNo.length() < 2 ? "0" + orderNo : orderNo;
		return DateUtil.dateToString("yyyyMMddHHmmss") + orderNo;
	}

	/**
	 * 金额左补0构成12位
	 */
	public static String leftAppendZero(String amount, int length) {
		for (int i = amount.length(); i < length; i++)
			amount = "0" + amount;
		return amount;
	}

	/**********************/
	// 网银在线
	private final static beartool.MD5 md5 = new beartool.MD5();

	/**
	 * 生成MD5签名结果
	 * 
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	public static String buildCbMd5(Map<String, String> sParaTemp) {
		Map<String, String> sPara = PayCore.paraFilter(sParaTemp);
		// v_amount v_moneytype v_oid v_mid v_url key
		StringBuffer sb = new StringBuffer();
		sb.append(sPara.get("v_amount"));
		sb.append(sPara.get("v_moneytype"));
		sb.append(sPara.get("v_oid"));
		sb.append(sPara.get("v_mid"));
		sb.append(sPara.get("v_url"));
		sb.append(PayConfig.CB_v_md5key);
		String md5Info = md5.getMD5ofStr(sb.toString());
		return md5Info;
	}

	/**
	 * 生成MD5签名结果(响应)
	 * 
	 * @param sPara
	 *            要签名的数组
	 * @return 签名结果字符串
	 */
	public static String buildCbMd5Resp(Map<String, String> sParaTemp) {
		Map<String, String> sPara = PayCore.paraFilter(sParaTemp);
		// v_oid+v_pstatus+v_amount+v_moneytype+key;
		// v_oid + v_pstatus + v_amount + v_moneytype + key; //拼凑加密串
		StringBuffer sb = new StringBuffer();
		sb.append(sPara.get("v_oid"));
		sb.append(sPara.get("v_pstatus"));
		sb.append(sPara.get("v_amount"));
		sb.append(sPara.get("v_moneytype"));
		sb.append(PayConfig.CB_v_md5key);
		return md5.getMD5ofStr(sb.toString());
	}

	/**
	 * 为网银在线生成订单号
	 * 
	 * 订单编号标准格式为：订单生成日期(yyyymmdd)-商户编号-商户流水号。订单编号所有字符总和不可超过64位。
	 * 
	 * 
	 * @return 订单号
	 */
	public static String gntChinabankOrderId() {
		cbOrderNo = cbOrderNo >= 10000000 ? 1 : cbOrderNo + 1;
		return DateUtil.dateToString(CommonConstant.DATE_SHORT_SIMPLE_FORMAT)
				+ "-" + PayConfig.CB_v_mid + "-"
				+ DateUtil.dateToString("HHmmss") + cbOrderNo;
	}

	// 网银订单号
	private static long cbOrderNo = 1;

	/**********************/
	// 快捷支付
	private final static Pkipair pki = new Pkipair();
	// 快钱订单号
	private static int billOrderNo = 1;

	/**
	 * 为网银在线生成订单号
	 * 
	 * 订单编号标准格式为：订单生成日期(yyyymmdd)-商户流水号。订单编号所有字符总和不可超过30位。
	 * 
	 * 
	 * @return 订单号
	 */
	public static String gntBillOrderId() {
		billOrderNo = billOrderNo >= 10000000 ? 1 : billOrderNo + 1;
		return DateUtil.dateToString("yyyyMMddHHmmss") + billOrderNo;
	}

	/**
	 * 签名字符串 适用于向快钱网关请求数据
	 * 
	 * 参数1={参数1}&参数2={参数2}&……&参数n={参数n}然后迚行商户私钥证书加密形成密文后迚行1024位的Base64转码。
	 * 
	 * @param params参数
	 * @return 签名字符串
	 */
	public static String gntSignMsg(Map<String, String> sParaTemp) {
		Map<String, String> params = PayCore.paraFilter(sParaTemp);
		params.remove("signMsg");
		String signMsgVal = PayCore.createLinkString(params);
		return pki.signMsg(signMsgVal);
	}

	/**
	 * 解析签名字符串 适用于接收快钱网关返回数据
	 * 
	 * @param params参数
	 * @param signMsg
	 *            签名串
	 * @return 判断数据是否被篡改
	 */
	public static boolean encodeByCer(Map<String, String> sParaTemp,
			String signMsg) {
		Map<String, String> params = PayCore.paraFilter(sParaTemp);
		Map<String, String> pmap = new LinkedHashMap<String, String>();
		if (!BaseUtil.isEmpty(params.get("merchantAcctId"))) {
			pmap.put("merchantAcctId", params.get("merchantAcctId"));
		}
		if (!BaseUtil.isEmpty(params.get("version"))) {
			pmap.put("version", params.get("version"));
		}
		if (!BaseUtil.isEmpty(params.get("language"))) {
			pmap.put("language", params.get("language"));
		}
		if (!BaseUtil.isEmpty(params.get("signType"))) {
			pmap.put("signType", params.get("signType"));
		}
		if (!BaseUtil.isEmpty(params.get("payType"))) {
			pmap.put("payType", params.get("payType"));
		}
		if (!BaseUtil.isEmpty(params.get("bankId"))) {
			pmap.put("bankId", params.get("bankId"));
		}
		if (!BaseUtil.isEmpty(params.get("orderId"))) {
			pmap.put("orderId", params.get("orderId"));
		}
		if (!BaseUtil.isEmpty(params.get("orderTime"))) {
			pmap.put("orderTime", params.get("orderTime"));
		}
		if (!BaseUtil.isEmpty(params.get("orderAmount"))) {
			pmap.put("orderAmount", params.get("orderAmount"));
		}
		if (!BaseUtil.isEmpty(params.get("dealId"))) {
			pmap.put("dealId", params.get("dealId"));
		}
		if (!BaseUtil.isEmpty(params.get("bankDealId"))) {
			pmap.put("bankDealId", params.get("bankDealId"));
		}
		if (!BaseUtil.isEmpty(params.get("dealTime"))) {
			pmap.put("dealTime", params.get("dealTime"));
		}
		if (!BaseUtil.isEmpty(params.get("payAmount"))) {
			pmap.put("payAmount", params.get("payAmount"));
		}
		if (!BaseUtil.isEmpty(params.get("fee"))) {
			pmap.put("fee", params.get("fee"));
		}
		if (!BaseUtil.isEmpty(params.get("ext1"))) {
			pmap.put("ext1", params.get("ext1"));
		}
		if (!BaseUtil.isEmpty(params.get("ext2"))) {
			pmap.put("ext2", params.get("ext2"));
		}
		if (!BaseUtil.isEmpty(params.get("payResult"))) {
			pmap.put("payResult", params.get("payResult"));
		}
		if (!BaseUtil.isEmpty(params.get("errCode"))) {
			pmap.put("errCode", params.get("errCode"));
		}
		return pki.enCodeByCer(PayCore.createLinkString(pmap), signMsg);
	}

	/**
	 * 建立请求，以表单HTML形式构造（默认）
	 * 
	 * @param PAY_GATEWAY
	 *            支付网关地址
	 * @param sParaTemp
	 *            请求参数数组
	 * @param strMethod
	 *            提交方式。两个值可选：post、get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @return 提交表单HTML文本
	 */
	public static String buildRequest(String PAY_GATEWAY,
			Map<String, String> sParaTemp, String strMethod,
			String strButtonName, boolean isnull) {
		// 待请求参数数组
		Map<String, String> sPara = isnull == true ? sParaTemp : PayCore
				.paraFilter(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"paysubmit\" name=\"paysubmit\" action=\""
				+ PAY_GATEWAY + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);
			sbHtml.append("<input type=\"hidden\" name=\"" + name
					+ "\" value=\"" + value + "\"/>");
		}

		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName
				+ "\" style=\"display:none;\"></form>");

		String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">";
		html += "\r\n<html>";
		html += "\r\n	<head>";
		html += "\r\n		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
		html += "\r\n		<title>在线支付</title>";
		html += "\r\n	</head>";
		html += "\r\n	<body onLoad=\"document.forms['paysubmit'].submit();\">";
		html += "\r\n" + sbHtml.toString();
		html += "\r\n	</body>";
		html += "\r\n</html>";
		return html;
	}

	/**
	 * 建立请求，以表单HTML形式构造，带文件上传功能
	 * 
	 * @param PAY_GATEWAY
	 *            支付网关地址
	 * @param sParaTemp
	 *            请求参数数组
	 * @param strMethod
	 *            提交方式。两个值可选：post、get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @param strParaFileName
	 *            文件上传的参数名
	 * @return 提交表单HTML文本
	 */
	public static String buildRequest(String PAY_GATEWAY,
			Map<String, String> sParaTemp, String strMethod,
			String strButtonName, String strParaFileName, boolean isbill) {
		// 待请求参数数组
		Map<String, String> sPara = isbill == true ? sParaTemp : PayCore
				.paraFilter(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"paysubmit\" name=\"paysubmit\"  enctype=\"multipart/form-data\" action=\""
				+ PAY_GATEWAY + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name
					+ "\" value=\"" + value + "\"/>");
		}

		sbHtml.append("<input type=\"file\" name=\"" + strParaFileName
				+ "\" />");

		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName
				+ "\" style=\"display:none;\"></form>");

		String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">";
		html += "\r\n<html>";
		html += "\r\n	<head>";
		html += "\r\n		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
		html += "\r\n		<title>在线支付</title>";
		html += "\r\n	</head>";
		html += "\r\n" + sbHtml.toString();
		html += "\r\n	<body>";
		html += "\r\n	</body>";
		html += "\r\n</html>";
		return html;
	}
}
