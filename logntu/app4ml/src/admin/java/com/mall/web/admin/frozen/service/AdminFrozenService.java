package com.mall.web.admin.frozen.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.frozen.dao.AdminFrozenDao;
import com.mall.web.admin.frozen.vo.AdminFrozenVo;

/**
 * @Description(描述) : 商品冻结
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:18:28
 */
@Service("adminFrozenService")
public class AdminFrozenService {

	// 冻结
	@Resource(name = "adminFrozenDao")
	private AdminFrozenDao adminFrozenDao;

	/********************************************/
	/**
	 * 平台运营后台管理
	 * 
	 * 冻结所有信息数量
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回冻结数量
	 */
	public int getAllFrozenRow(Map<String, Object> params) {
		return adminFrozenDao.getAllFrozenRow(params, null);
	}

	/**
	 * 平台运营后台管理
	 * 
	 * 冻结所有信息
	 * 
	 * @param params
	 *            查询参数
	 * @return 返回冻结信息
	 */
	public List<AdminFrozenVo> findAllFrozens(Map<String, Object> params,
			PageBean4UI pageBean) {
		return adminFrozenDao.findAllFrozens(params, null, pageBean);
	}

	/********************************************/

	// 处理成功提现信息
	/**
	 * 平台运营后台管理
	 * 
	 * 处理财务人员提交的成功提现信息
	 * 
	 * @return 返回影响数量
	 */
	@Transactional
	public int[] batchFrozen(final List<AdminFrozenVo> frozens) {
		return adminFrozenDao.batchFrozen(frozens);
	}
}
