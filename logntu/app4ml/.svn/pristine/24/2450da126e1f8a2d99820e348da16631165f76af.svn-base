package com.mall.web.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.WordFilter;

@Repository("sysWordFilterDao")
public class SysWordFilterDao extends BaseDao<WordFilter> {
	
	/**
	 * 获取所有的脏字关键字
	 * @return
	 */
	public List<WordFilter> findWords() {
		StringBuilder hql = new StringBuilder();
		hql.append("from WordFilter");
		  return this.list(hql.toString());
	}
}
