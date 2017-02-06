/**
 * 
 */
package com.mall.web.admin.system.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.admin.common.dao.SysDesktopDefendDao;
import com.mall.web.admin.common.dao.SysSelfDesktopDefendDao;
import com.mall.web.admin.common.domain.SysDesktopDefend;
import com.mall.web.admin.common.domain.SysSelfDesktopDefend;
import com.mall.web.admin.common.utils.SessionUtils;
import com.mall.web.admin.security.dao.SysUserDao;
import com.mall.web.admin.security.domain.SysUser;
import com.mall.web.admin.security.vo.SysMngUserLoginBean;
import com.mall.web.common.dynamicds.DataSource;

/**
 * @功能说明：桌面管理业务层Service
 * @作者： xgyin
 * @创建日期： 2010-12-22
 */
@Service("sysDesktopDefendService")
public class SysDesktopDefendService {
	@Resource(name = "sysDesktopDefendDao")
	private SysDesktopDefendDao sysDesktopDefendDao;
	@Resource(name = "sysUserDao")
	private SysUserDao sysUserDao;
	@Resource(name = "sysSelfDesktopDefendDao")
	private SysSelfDesktopDefendDao sysSelfDesktopDefendDao;

	/**
	 * 通过桌面管理ID删除桌面管理实体
	 * 
	 * @param desktopDefendId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteDesktopDefendById(String desktopDefendId)
			throws FrameworkException {
		this.sysDesktopDefendDao.deleteById(desktopDefendId);
	}

	@DataSource(value = "admin")
	@Transactional
	public void deleteDesktopDefends(String[] desktopDefendIds)
			throws FrameworkException {
		for (String desktopDefendId : desktopDefendIds) {
			this.deleteDesktopDefendById(desktopDefendId);
		}
	}

	/**
	 * 通过桌面管理ID获取桌面管理实体
	 * 
	 * @param desktopDefendId
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDesktopDefend findDesktopDefendById(String desktopDefendId)
			throws FrameworkException {
		return this.sysDesktopDefendDao.get(desktopDefendId);
	}

	/**
	 * 保存桌面管理
	 * 
	 * @param desktopDefend
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void saveDesktopDefend(SysDesktopDefend desktopDefend)
			throws FrameworkException {
		this.sysDesktopDefendDao.save(desktopDefend);

	}

	/**
	 * 修改桌面管理
	 * 
	 * @param desktopDefend
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void updateDesktopDefend(SysDesktopDefend desktopDefend)
			throws FrameworkException {
		this.sysDesktopDefendDao.update(desktopDefend);

	}

	/**
	 * 根据条件获取桌面管理总记录数
	 * 
	 * @param params
	 * @return
	 */
	@DataSource(value = "admin")
	public int getDesktopDefendTotalRows(Map<String, Object> params)
			throws FrameworkException {
		return this.sysDesktopDefendDao.getDesktopDefendTotalRows(params);
	}

	/**
	 * 根据条件获取桌面管理记录
	 * 
	 * @param params
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	@DataSource(value = "admin")
	public List<SysDesktopDefend> findAllDesktopDefends(
			Map<String, Object> params, int curPage, int pageSize)
			throws FrameworkException {
		return this.sysDesktopDefendDao.findAllDesktopDefends(params, curPage,
				pageSize);
	}

	/**
	 * 通过桌面管理英文名称和中文名称查找桌面管理
	 * 
	 * @param name
	 * @param cnName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDesktopDefend findDesktopDefendyByName(String title, String divId)
			throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("AND__title__e", title);
		params.put("OR__divId__e", divId);
		List<SysDesktopDefend> desktopDefends = this.sysDesktopDefendDao
				.findAllEntitiesByCondition(params);
		if (!BaseUtil.isEmpty(desktopDefends) && desktopDefends.size() > 0)
			return desktopDefends.get(0);
		return null;
	}

	/**
	 * 通过桌面管理ID、英文名称和中文名称查找桌面管理
	 * 
	 * @param id
	 * @param name
	 * @param cnName
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public SysDesktopDefend findDesktopDefendByCon(String id, String title,
			String divId) throws FrameworkException {
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("title", title);
		params.put("divId", divId);
		params.put("id", id);
		return this.sysDesktopDefendDao.findDesktopDefendByCon(params);

	}

	/**
	 * 获取自己的桌面业务模块
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysDesktopDefend> findSelfDesktopDefends()
			throws FrameworkException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		return this.sysDesktopDefendDao.findSelfDesktopDefends(loginUser
				.getId());
	}

	/**
	 * 设置自己的桌面菜单
	 * 
	 * @param desktopDefendIds
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void setSelfDesktopDefends(String[] desktopDefendIds)
			throws FrameworkException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);
		SysUser sysUser = this.sysUserDao.getUserById(loginUser.getId());
		this.sysSelfDesktopDefendDao
				.deleteSelfDesktopDefendsByLoginUserId(loginUser.getId());
		if (BaseUtil.isEmpty(desktopDefendIds))
			return;
		int len = desktopDefendIds.length;
		for (int i = 0; i < len; i++) {
			String desktopDefendId = desktopDefendIds[i];
			SysDesktopDefend defend = this.sysDesktopDefendDao
					.get(desktopDefendId);
			SysSelfDesktopDefend self = new SysSelfDesktopDefend();
			if (i % 2 == 0)
				self.setDefaultPosition("1");
			else
				self.setDefaultPosition("2");
			self.setDefendUser(sysUser);
			self.setDesktopDefend(defend);
			this.sysSelfDesktopDefendDao.save(self);
		}
	}

	/**
	 * 获取自己主页的桌面模块
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	public List<SysSelfDesktopDefend> findSelfDesktopCenters()
			throws FrameworkException {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SysMngUserLoginBean loginUser = SessionUtils.getUser(request);

		return this.sysSelfDesktopDefendDao.findSelfDesktopCenters(loginUser
				.getId());
	}

	/**
	 * 改变桌面区域块的位置
	 * 
	 * @param placeId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void setDesktopPositionds(String placeId) throws FrameworkException {
		String[] places = placeId.split("\\|");
		for (int i = 1; i < places.length; i++) {
			String widges = places[i - 1];
			String[] widgePlaces = widges.split("=");
			if (widgePlaces.length != 2)
				continue;
			String position = widgePlaces[0];
			String selfDesktopIds = widgePlaces[1];
			if (!BaseUtil.isEmpty(selfDesktopIds)) {
				String[] selfDesktopIdStr = selfDesktopIds.split(",");
				for (int j = 1; j <= selfDesktopIdStr.length; j++) {
					String selfDesktopId = selfDesktopIdStr[j - 1];
					SysSelfDesktopDefend self = this.sysSelfDesktopDefendDao
							.get(selfDesktopId);
					self.setOrderNo(j);
					self.setDefaultPosition(position);
					this.sysSelfDesktopDefendDao.update(self);
				}
			}

		}

	}

	/**
	 * 删除自己桌面的桌面项
	 * 
	 * @param selfDesktopDenfendId
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public void deleteSelfDesktopDefendById(String selfDesktopDenfendId)
			throws FrameworkException {
		this.sysSelfDesktopDefendDao.deleteById(selfDesktopDenfendId);
	}
}
