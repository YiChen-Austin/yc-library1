package com.mall.web.admin.primary.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.mall.web.admin.primary.dao.AdminPrimaryDao;
import com.mall.web.admin.primary.vo.PayPrimaryVo;

/**
 * @Description(描述) : 支付服务
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:18:28
 */
@Service("adminPrimaryService")
public class AdminPrimaryService {

	// 支付
	@Resource(name = "adminPrimaryDao")
	private AdminPrimaryDao adminPrimaryDao;

	/********************************************/
	/**
	 * 平台运营后台管理
	 * 
	 * 支付统计
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回订单数量
	 */
	public PayPrimaryVo findPayPrimaryStat(String requestTimeS,
			String requestTimeE, Integer status) {
		return adminPrimaryDao.findPayPrimaryStat(requestTimeS, requestTimeE,
				status);
	}
}
