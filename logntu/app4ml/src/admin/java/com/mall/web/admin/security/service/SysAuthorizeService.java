package com.mall.web.admin.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.web.admin.security.dao.SysAuthorizeDao;
import com.mall.web.admin.security.dao.SysResourcesDao;
import com.mall.web.admin.security.domain.SysAuthorize;
import com.mall.web.admin.security.domain.SysResources;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @页面资源管理
 * @author: 聂庆童
 * @创建日期: 2010-6-8
 */
@Service("sysAuthorizeService")
public class SysAuthorizeService {
	@Resource(name = "sysResourcesDao")
	private SysResourcesDao sysResourcesDao;
	@Resource(name = "sysAuthorizeDao")
	private SysAuthorizeDao sysAuthorizeDao;

	/**
	 * @throws Exception
	 * @category 初始化(action链接)资源注入系统
	 * 
	 */
	@DataSource(value = "admin")
	@Transactional
	public void initAuthorizeUrl(String resourceKey, String url,
			String description, String remark) throws Exception {
		List<SysAuthorize> lAuth = sysAuthorizeDao.findAuthorizeByUrl(url);
		if (lAuth != null && lAuth.size() > 0)
			return;
		List<SysResources> lRes = sysResourcesDao
				.findResourceByResId(resourceKey);
		if (lRes == null || lRes.size() <= 0)
			return;
		SysResources resources = lRes.get(0);

		SysAuthorize auth = new SysAuthorize();
		auth.setDescription(description);
		auth.setRemark(remark);
		auth.setResources(resources);
		auth.setUrl(url);
		sysAuthorizeDao.save(auth);
	}

	/**
	 * @throws Exception
	 * @category 判断(action链接)授权给该用户
	 * 
	 */
	@DataSource(value = "admin")
	public int getUrlCount(String userId, String url) throws Exception {
		return sysAuthorizeDao.getUrlCount(userId, url);
	}
}
