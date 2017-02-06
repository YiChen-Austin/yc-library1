/**
 * 
 */
package com.mall.web.admin.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.dao.SysDictionaryDao;
import com.mall.web.admin.common.domain.SysDictionary;
import com.mall.web.admin.common.utils.SystemResourceUtil;
import com.mall.web.common.dynamicds.DataSource;


/**
 * 
 * @功能：系统字典业务层
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 * 
 */
@Service("sysDictionaryService")
public class SysDictionaryService {

	@Resource(name = "sysDictionaryDao")
	private SysDictionaryDao sysDictionaryDao;

	/**
	 * 通过系统字典ID删除系统字典实体
	 * @param sysDictionaryId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSysDictionaryById(String sysDictionaryId) throws FrameworkException {
		this.sysDictionaryDao.deleteById(sysDictionaryId);
		SystemResourceUtil.getInstance().loadSysDictionary();

	}

	/**
	 * 获取所有的系统字典实体
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysDictionary> findAllSysDictionaries() throws FrameworkException {
		return this.sysDictionaryDao.findAll();
	}

	/**
	 * 通过系统字典ID获取系统字典实体
	 * @param sysDictionaryId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDictionary findSysDictionaryById(String sysDictionaryId) throws FrameworkException {
		return this.sysDictionaryDao.get(sysDictionaryId);
	}

	/**
	 * 保存系统字典
	 * @param sysDictionary
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void saveSysDictionary(SysDictionary sysDictionary) throws FrameworkException {
		this.sysDictionaryDao.save(sysDictionary);
		SystemResourceUtil.getInstance().loadSysDictionary();

	}

	/**
	 * 修改系统字典
	 * @param sysDictionary
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void updateSysDictionary(SysDictionary sysDictionary) throws FrameworkException {
		this.sysDictionaryDao.update(sysDictionary);
		SystemResourceUtil.getInstance().loadSysDictionary();

	}

	/**
	 * 根据条件获取系统参数总记录数
	 * @param params
	 * @return
	 */
	@DataSource(value = "admin")
	public int getSysDictionaryTotalRows(Map<String, Object> params) throws FrameworkException {
		return this.sysDictionaryDao.getSysDictionaryTotalRows(params);
	}

	/**
	 * 根据条件获取系统参数记录
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	@DataSource(value = "admin")
	public List<SysDictionary> findAllSysDictionaries(Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		return this.sysDictionaryDao.findAllSysDictionaries(params, curPage, pageSize);
	}

	/**
	 * 通过系统字典英文名称和中文名称查找系统字典
	 * @param name
	 * @param cnName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDictionary findSysDictionaryByName(String name, String cnName) throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("AND__name__e", name);
		params.put("OR__cnName__e", cnName);
		List<SysDictionary> sysDictionaries = this.sysDictionaryDao.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(sysDictionaries) && sysDictionaries.size() > 0)
			return sysDictionaries.get(0);
		return null;
	}

	/**
	 * 通过系统字典ID、英文名称和中文名称查找系统字典
	 * @param id
	 * @param name
	 * @param cnName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDictionary findSysDictionaryByIdAndName(String id, String name, String cnName) throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("name", name);
		params.put("cnName", cnName);
		params.put("id", id);
		return this.sysDictionaryDao.findSysDictionaryByIdAndName(params);

	}

	/**
	 * 获取数据库时间
	 * @return
	 */
	@DataSource(value = "admin")
	public Date getDBTime() throws FrameworkException {
		return this.sysDictionaryDao.getDBTime();
	}

}
