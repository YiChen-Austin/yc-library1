package com.mall.web.pay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.domain.GeneralOrder;
import com.mall.web.mall.domain.Member;
import com.mall.web.mall.domain.Order;
import com.mall.web.mall.member.dao.WebMemberDao;
import com.mall.web.mall.member.vo.WebMemberVo;
import com.mall.web.pay.dao.PayBank2ThirdDao;
import com.mall.web.pay.dao.PayBank2ThirdExDao;
import com.mall.web.pay.dao.PayBanksDao;
import com.mall.web.pay.dao.PayInteractionDao;
import com.mall.web.pay.dao.PayOrdersDao;
import com.mall.web.pay.dao.PayPrimaryDao;
import com.mall.web.pay.dao.PayThirdPaysDao;
import com.mall.web.pay.domain.PayBank2ThirdEx;
import com.mall.web.pay.domain.PayBanks;
import com.mall.web.pay.domain.PayInteraction;
import com.mall.web.pay.domain.PayOrders;
import com.mall.web.pay.domain.PayPrimary;
import com.mall.web.pay.domain.PayThirdPays;
import com.mall.web.pay.vo.BankPayVo;
import com.mall.web.pay.vo.BankThirdPayVo;
import com.mall.web.mall.order.dao.OrderDao;
import com.mall.web.mall.order.service.OrderService;


/**
 * 主要负责直接充值和购买行为的业务逻辑处理
 * 
 * @category 支付业务逻辑处理
 * 
 */
@Service("payBusinessService")
public class PayBusinessService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "payThirdPaysDao")
	private PayThirdPaysDao payThirdPaysDao;
	@Resource(name = "payBanksDao")
	private PayBanksDao payBanksDao;
	@Resource(name = "payBank2ThirdDao")
	private PayBank2ThirdDao payBank2ThirdDao;
	@Resource(name = "payBank2ThirdExDao")
	private PayBank2ThirdExDao payBank2ThirdExDao;
	@Resource(name = "payPrimaryDao")
	private PayPrimaryDao payPrimaryDao;
	@Resource(name = "payOrdersDao")
	private PayOrdersDao payOrdersDao;
	@Resource(name = "payInteractionDao")
	private PayInteractionDao payInteractionDao;
	@Resource(name = "orderDao")
	private OrderDao orderDao;
	@Resource(name = "webMemberDao")
	private WebMemberDao webMemberDao;

	@Autowired
	private OrderService orderService;
	/*****************************************************/
	// 会员信息判断
	/**
	 * @param userName 会员名称
	 * @return 会员信息
	 */
	@Transactional(readOnly = true)
	public WebMemberVo getMemberByun(String userName) {
		Member member = webMemberDao.getUserInfo(userName);
		if(member==null)
			return null;
		return WebMemberVo.bean2Vo(member);
	}
	
	
	
	/*****************************************************/
	// 订单相关处理
	/**
	 * @param sns
	 *            会员缴费编号
	 * @return 返回会员缴费信息组
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<GeneralOrder> getOrdersBySn(String[] sns) {
		// 普通订单
		return orderDao.findOrderBySn(sns);
	}
	/*****************************************************/
	/**
	 * 处理成功响应
	 * 
	 * @param orderId
	 *            支付标示号
	 * @param status响应状态
	 * @throws FrameworkException
	 */
	@Transactional
	public String payResponse(String orderId, String status) throws Exception {
		return payResponse(orderId, status, null);
	}

	/**
	 * 获取订单支付信息
	 * 
	 * @param orderSn
	 *            订单交易号
	 * 
	 */
	public PayPrimary getPayPrimaryBySn(String orderSn) {
		List<PayPrimary> list = payPrimaryDao.findPayPrimaryBySn(orderSn);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 处理成功响应
	 * 
	 * @param orderId
	 *            支付标示号
	 * @param status响应状态
	 * @throws FrameworkException
	 */
	@Transactional
	public String payResponse(String orderId, String status, String thridOrderSn)
			throws Exception {
		List<PayPrimary> list = payPrimaryDao.findPayPrimaryBySn(orderId);
		if (list.size() <= 0)
			throw new Exception("no orders.");
		PayPrimary payPri = list.get(0);
		// 已经处理成功
		if (payPri.getStatus() == 2)
			return null;
		// 1、处理订单状态
		String orderNo = "";
		List<PayOrders> lPays = payOrdersDao.findPayOrdersByPay(payPri
				.getPrimaryId());
		for (PayOrders pOrder : lPays) {
			// 普通订单
			if ("00".equalsIgnoreCase(pOrder.getOrderType())) {
				// 修改订单状态
				Order order = orderDao.findOrderById(pOrder.getOrderId());
				orderNo += orderNo.length() > 0 ? "," + order.getOrderSn()
						: order.getOrderSn();
				order.setStatus(20);// 订单状态，支付成功
				orderDao.update(order);
			}

			pOrder.setIsPaySucc(true);
			pOrder.setPayStatus("2");// 支付结束
			pOrder.setPayOverTime(DateUtil.getGMTDate());
			payOrdersDao.update(pOrder);
		}
		// 2、处理成功响应报
		payPri.setResponseTime(DateUtil.getGMTDate());
		payPri.setHandleTime(DateUtil.getGMTDate());
		payPri.setServiceResponseStatus(status);
		payPri.setStatus(2);// 支付成功
		if (thridOrderSn != null && thridOrderSn.length() > 0) {
			payPri.setThridOrderSn(thridOrderSn);
		}// 记录微信支付交易号
		payPrimaryDao.update(payPri);
		return orderNo;
	}

	/**********************************/
	/**
	 * 系统支持的所有银行卡接第三方支付信息
	 * 
	 * @return List<BankPayVo>
	 */
	@Transactional(readOnly = true)
	public List<BankThirdPayVo> findBankThirdPays() {
		List<BankThirdPayVo> bankThirdPayVo = new LinkedList<BankThirdPayVo>();
		List<PayThirdPays> list = payThirdPaysDao.findValidPayThirdPays();
		for (PayThirdPays ptp : list) {
			BankThirdPayVo vo = new BankThirdPayVo();
			BeanUtils.copyProperties(ptp, vo);
			bankThirdPayVo.add(vo);
		}
		return bankThirdPayVo;
	}

	/**
	 * 系统支持的所有银行(第三方支付)信息
	 * 
	 * @return List<BankPayVo>
	 */
	@Transactional(readOnly = true)
	public List<BankPayVo> findBankPays() {
		List<BankPayVo> bankPayVos = new LinkedList<BankPayVo>();
		List<PayBanks> list = payBanksDao.findValidPayBanks();
		for (PayBanks payBank : list) {
			// 1、记录银行信息
			BankPayVo vo = new BankPayVo();
			BeanUtils.copyProperties(payBank, vo);
			// 2、第三方接口信息
			PayBank2ThirdEx pbtEx = payBank2ThirdExDao
					.findPayBank2ThirdEx(payBank.getId());
			third2PayUtil(pbtEx, vo);
			bankPayVos.add(vo);
		}
		return bankPayVos;
	}

	/*******************************/
	// 数据交互记录
	/**
	 * 新增支付数据交互记录
	 * 
	 */
	@Transactional
	public void saveYzmPayInteraction(PayInteraction entity)
			throws FrameworkException {
		payInteractionDao.save(entity);
	}

	/*******************************/
	// 支付信息
	/**
	 * 返回制定支付好的支付信息
	 * 
	 * @param 支付标识
	 * @return YzmPayPrimary
	 */
	public PayPrimary findPayPrimaryBySn(String sn) {
		List<PayPrimary> list = payPrimaryDao.findPayPrimaryBySn(sn);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 修改支付订单对应关系表
	 * 
	 */
	@Transactional
	public void updatePayPrimary(PayPrimary entity)
			throws FrameworkException {
		payPrimaryDao.update(entity);
	}

	/**
	 * 新增支付订单对应关系表
	 * 
	 */
	@Transactional
	public void savePayPrimary(PayPrimary entity) throws FrameworkException {
		payPrimaryDao.save(entity);
	}

	/*******************************/
	// 支付与订单的对应关系
	/**
	 * 返回制定支付下的支付订单对应信息
	 * 
	 * @return List<PayOrders>
	 */
	public List<PayOrders> findPayOrdersByPay(String orderId) {
		return payOrdersDao.findPayOrdersByPay(orderId);
	}

	/**
	 * 修改支付订单对应关系表
	 * 
	 */
	@Transactional
	public void updatePayOrders(PayOrders entity) throws FrameworkException {
		payOrdersDao.update(entity);
	}

	/**
	 * 新增支付订单对应关系表
	 * 
	 */
	@Transactional
	public void savePayOrders(PayOrders entity) throws FrameworkException {
		payOrdersDao.save(entity);
	}

	@Transactional
	public void savePayOrders(List<GeneralOrder> list, String orderType,
			PayPrimary pay) throws FrameworkException {
		for (GeneralOrder order : list) {
			PayOrders payOrders = new PayOrders();
			// payOrders.setOrder(order);
			payOrders.setOrderId(new Long(order.getOrderId()));
			payOrders.setPay(pay);
			payOrders.setOrderType(orderType);
			payOrders.setIsPaySucc(false);
			payOrders.setPayStatus("1");
			payOrders.setPayApplytime(DateUtil.getGMTDate());
			payOrders.setCreateTime(DateUtil.getGMTDate());
			payOrdersDao.save(payOrders);
		}
	}

	// ///////////////////////////////////////////////////////
	/**
	 * 判断订单信息是否正确（订单号是哦福存在、订单总金额是否箱单、订单不能重复）
	 * 
	 * @param orderNo
	 *            订单编号字符串
	 * @param orderNo
	 *            订单金额字符串
	 * @return 本地订单数据是否有效
	 */
	public boolean validOrders(String orderNo, String orderType, String amount) {
		try {
			String[] orders = orderNo.split(",");
			Map<String, BigDecimal> map = new TreeMap<String, BigDecimal>();
			double _amount = 0;
			for (String order : orders) {
				GeneralOrder o = "00".equalsIgnoreCase(orderType) ? orderService
						.getOrderBySn(order) : null;
				// 无效订单号（不存在订单）
				if (o == null) {
					return false;
				}
				// 状态不对(不是待付款);等待买家付款11
				if (o.getStatus() != 11)
					return false;
				// 已经存在相同订单号
				if (!BaseUtil.isEmpty(map.get(order)))
					return false;
				map.put(order, o.getOrderAmount());
				_amount += o.getOrderAmount().doubleValue();
			}
			// 金額不等，所传订单金额不等于订单总金额
			if (_amount != Double.parseDouble(amount)) {
				return false;
			}
		} catch (Exception e1) {
			logger.warn(e1);
			return false;
		}
		return true;
	}

	// ///////////////////////////////////////////////////////
	/**
	 * 辅助生成支付json信息
	 * 
	 * @param pbts
	 *            第三方信息
	 * @param bankPayVo
	 */
	public void third2PayUtil(PayBank2ThirdEx pbtEx, BankPayVo bankPayVo) {
		StringBuffer sb = new StringBuffer();
		if (!BaseUtil.isEmpty(pbtEx.gettPayCommonDebit())) {
			sb.append("'commonDebit':{'tid':'"
					+ pbtEx.gettPayCommonDebit().getId()
					+ "','tBankCode':'"
					+ payBank2ThirdDao.findPayBank2Third(bankPayVo.getId(),
							pbtEx.gettPayCommonDebit().getId()).getDebit()
					+ "'}");
			if (pbtEx.gettPayCommonDebit().getIsBank()) {
				bankPayVo.setIsBank("true");
			}
		}
		if (!BaseUtil.isEmpty(pbtEx.gettPayCommonCredit())) {
			if (sb.length() > 0)
				sb.append(",");
			sb.append("'commonCredit':{'tid':'"
					+ pbtEx.gettPayCommonCredit().getId()
					+ "','tBankCode':'"
					+ payBank2ThirdDao.findPayBank2Third(bankPayVo.getId(),
							pbtEx.gettPayCommonCredit().getId()).getCredit()
					+ "'}");
			if (pbtEx.gettPayCommonCredit().getIsBank()) {
				bankPayVo.setIsBank("true");
			}
		}
		if (!BaseUtil.isEmpty(pbtEx.gettPayQuickDebit())) {
			if (sb.length() > 0)
				sb.append(",");
			sb.append("'quickDebit':{'tid':'"
					+ pbtEx.gettPayQuickDebit().getId()
					+ "','tBankCode':'"
					+ payBank2ThirdDao.findPayBank2Third(bankPayVo.getId(),
							pbtEx.gettPayQuickDebit().getId()).getDebit()
					+ "'}");
			if (pbtEx.gettPayQuickDebit().getIsBank()) {
				bankPayVo.setIsBank("true");
			}
		}
		if (!BaseUtil.isEmpty(pbtEx.gettPayQuickCredit())) {
			if (sb.length() > 0)
				sb.append(",");
			sb.append("'quickCredit':{'tid':'"
					+ pbtEx.gettPayQuickCredit().getId()
					+ "','tBankCode':'"
					+ payBank2ThirdDao.findPayBank2Third(bankPayVo.getId(),
							pbtEx.gettPayQuickCredit().getId()).getCredit()
					+ "'}");
			if (pbtEx.gettPayQuickCredit().getIsBank()) {
				bankPayVo.setIsBank("true");
			}
		}
		bankPayVo.setBankThirdPay(sb.toString());
	}
}