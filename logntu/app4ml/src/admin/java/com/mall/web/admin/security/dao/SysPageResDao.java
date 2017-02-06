package com.mall.web.admin.security.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.admin.security.domain.SysPageRes;


/**
 * 页面资源管理
 * 
 * @author Administrator
 * 
 */
@Repository("sysPageResDao")
public class SysPageResDao extends BaseDao<SysPageRes> {
	Logger logger = Logger.getLogger(SysMenuDao.class);

	/**
	 * 根据菜单ID查找对应页面
	 * 
	 * @param SysMenyID
	 * @return
	 * @throws Exception
	 */
	public List<String> findPageBySysMenu(String SysMenuID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menu_id", SysMenuID);
		List<SysPageRes> list = this.findAllEntitiesByCondition(map);
		List<String> li = new ArrayList<String>();
		for (SysPageRes pageres : list) {
			String i = pageres.getId();
			li.add(i);
		}
		return li;
	}

	/**
	 * 根据菜单ID查找对应页面
	 * 
	 * @param SysMenyID
	 * @return
	 * @throws Exception
	 */
	public SysPageRes findPageByPageId(String pageId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page_id", pageId);
		List<SysPageRes> list = this.findAllEntitiesByCondition(map);
		return list.size() > 0 ? list.get(0) : null;
	}
}
