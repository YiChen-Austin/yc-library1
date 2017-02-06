package com.mall.web.admin.payrecord.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.payrecord.dao.AdminPayRecordDao;
import com.mall.web.admin.payrecord.vo.AdminPayRecordVo;

/**
 * @Description(描述) : 支付记录服务
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:18:28
 */
@Service("adminPayRecordService")
public class AdminPayRecordService {

	// 支付
	@Resource(name = "adminPayRecordDao")
	private AdminPayRecordDao adminPayRecordDao;

	/********************************************/
	/**
	 * 平台运营后台管理
	 * 
	 * 支付报告统计
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回数量
	 */
	public int getPayRecords(String requestTimeS, String requestTimeE,
			Long in_user_id) {
		return adminPayRecordDao.getPayRecords(requestTimeS, requestTimeE,
				in_user_id, null);
	}

	/**
	 * 平台运营后台管理
	 * 
	 * 支付报告统计
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回支付信息数量
	 */
	public List<AdminPayRecordVo> findPayRecords(String requestTimeS,
			String requestTimeE, Long in_user_id, PageBean4UI pageBean) {
		return adminPayRecordDao.findPayRecords(requestTimeS, requestTimeE,
				in_user_id, null, pageBean);
	}
}
