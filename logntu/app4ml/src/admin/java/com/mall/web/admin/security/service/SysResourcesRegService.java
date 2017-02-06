package com.mall.web.admin.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.web.admin.security.dao.SysAuthorizeDao;
import com.mall.web.admin.security.dao.SysMenuDao;
import com.mall.web.admin.security.dao.SysPageResDao;
import com.mall.web.admin.security.dao.SysResourcesDao;
import com.mall.web.admin.security.domain.SysAuthorize;
import com.mall.web.admin.security.domain.SysMenu;
import com.mall.web.admin.security.domain.SysPageRes;
import com.mall.web.admin.security.domain.SysResources;
import com.mall.web.admin.security.domain.SysRole;
import com.mall.web.admin.security.vo.SysResourcesRegBean;
import com.mall.web.common.dynamicds.DataSource;


/**
 * @功能说明：系统资源注册业务层
 * @作者： 练书锋
 * @创建日期： 2010-6-8 @
 */
@Service("sysResourcesRegService")
public class SysResourcesRegService {
	// 菜单
	@Resource(name = "sysMenuDao")
	private SysMenuDao sysMenuDao;
	// JSP页面
	@Resource(name = "sysPageResDao")
	private SysPageResDao sysPageResDao;
	// JSP页面资源
	@Resource(name = "sysResourcesDao")
	private SysResourcesDao sysResourcesDao;
	// 资源对应的Action地址
	@Resource(name = "sysAuthorizeDao")
	private SysAuthorizeDao sysAuthorizeDao;
	

	/**
	 * 增加指定组件的Action
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String addTargetActionURL(String targeID,
			List<SysResourcesRegBean> sysResourcesRegBeans)
			throws FrameworkException {
		SysResources resources = sysResourcesDao.get(targeID);
		for (SysResourcesRegBean srb : sysResourcesRegBeans) {
			SysAuthorize authorize = new SysAuthorize();
			// Action地址
			authorize.setUrl(srb.getName());
			authorize.setDescription(srb.getDescription());
			authorize.setRemark(srb.getRemark());
			authorize.setResources(resources);
			sysAuthorizeDao.save(authorize);
			resources.getAuthorize().add(authorize);
		}
		return "保存成功";
	}

	/**
	 * 根据条件添加指JSP页面的组件
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String addTargetcomponent(String targeID,
			List<SysResourcesRegBean> sysResourcesRegBeans)
			throws FrameworkException {
		// 获取组件所在的JSP页面
		SysPageRes pageRes = sysPageResDao.get(targeID);
		for (SysResourcesRegBean srb : sysResourcesRegBeans) {
			SysResources resources = new SysResources();
			resources.setName(srb.getName());
			resources.setRes_id(srb.getName_id());
			resources.setRemark(srb.getRemark());
			resources.setDescription(srb.getDescription());
			sysResourcesDao.save(resources);
			resources.setPageres(pageRes);
			pageRes.getResouce().add(resources);
		}
		return "保存成功";
	}

	/**
	 * 根据条件修改指定菜单的JSP页面地址
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String updataTargetJSP(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {

		SysMenu sysMenu = sysPageResDao.get(targeID).getMenu();
		if ("0".equals(sysMenu.getInternalCommand()))
			return "该链接地址只能在菜单设置页面进行修改";

		SysPageRes pr = sysPageResDao.get(targeID);
		pr.setPage_id(sysResourcesRegBean.getName());
		pr.setRemark(sysResourcesRegBean.getRemark());
		pr.setDescription(sysResourcesRegBean.getDescription());
		sysPageResDao.update(pr);
		return "保存成功";
	}

	/**
	 * 根据条件修改指定组件
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String updataTargetcomponent(String targeID,
			SysResourcesRegBean sysResourcesRegBean) throws FrameworkException {

		if ("".equals(targeID) || targeID==null )
			return "请先选择组件在执行修改组件操作";

		SysResources resources = sysResourcesDao.get(targeID);
		
		resources.setName(sysResourcesRegBean.getName());
		resources.setRemark(sysResourcesRegBean.getRemark());
		resources.setDescription(sysResourcesRegBean.getDescription());
		resources.setRes_id(sysResourcesRegBean.getName_id());
		sysResourcesDao.update(resources);
		return "保存成功";
	}
	
	
	
	/**
	 * 根据条件添加指定菜单的JSP页面地址
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String addTargetJSP(String targeID,
			List<SysResourcesRegBean> sysResourcesRegBeans)
			throws FrameworkException {
		SysMenu sysMenu = sysMenuDao.get(targeID);
		if ("0".equals(sysMenu.getInternalCommand()))
			return "该链接地址只能在菜单设置,不能添加！";

		for (SysResourcesRegBean srb : sysResourcesRegBeans) {
			SysPageRes pr = new SysPageRes();
			pr.setPage_id(srb.getName());
			pr.setRemark(srb.getRemark());
			pr.setDescription(srb.getDescription());
			sysPageResDao.save(pr);
			pr.setMenu(sysMenu);
			sysMenu.getPageres().add(pr);
		}
		return "保存成功";
	}

	/**
	 * 获取指定菜单的所有jsp列表
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public List<SysResourcesRegBean> getTargetJSP(String targeID)
			throws FrameworkException {
		// 指定的菜单
		SysMenu sysMenu = sysMenuDao.get(targeID);

		List<SysPageRes> list = new ArrayList<SysPageRes>();
		// 判断menu是否为内部命令，如果不为内部命令则最多只有一个返回的JSP页面
		// 且增加此记录到JSP表中
		if ("0".equals(sysMenu.getInternalCommand())) {
			// 将菜单对象转换为JSP页面表记录对象
			SysPageRes tempPageRes = new SysPageRes();
			tempPageRes.setDescription("菜单名为： " + sysMenu.getName());
			tempPageRes.setRemark(sysMenu.getRemark());
			tempPageRes.setPage_id(sysMenu.getPageUrl());
			// tempPageRes.setId(sysMenu.getId());
			tempPageRes.setMenu(sysMenu);
			// 如果在JSP页面表没对象，则创建一个对象
			int listSize = sysMenu.getPageres().size();

			if (listSize == 0) {
				sysPageResDao.save(tempPageRes);
				sysMenu.getPageres().add(tempPageRes);
				sysMenuDao.update(sysMenu);
			} else if (listSize > 1) {
				// 删除多余的实体
				for (SysPageRes pageRes : sysMenu.getPageres()) {
					for (SysResources resources : pageRes.getResouce()) {
						for (SysAuthorize authorize : resources.getAuthorize()) {
							sysAuthorizeDao.delete(authorize);
						}
						//解除角色对组件的关联
						for(SysRole sysRole : resources.getSysRoles()){
							sysRole.getResources().remove(resources);
						}
						
						sysResourcesDao.delete(resources);
					}
					sysPageResDao.delete(pageRes);
				}
				sysPageResDao.save(tempPageRes);
				sysMenu.getPageres().add(tempPageRes);
				sysMenuDao.update(sysMenu);
			}
			if (listSize != 1) {
				list.clear();
				list.add(tempPageRes);
			}
			if (listSize == 1) {
				// 保证同步
				sysMenuDao.get(targeID).getPageres().get(0).setPage_id(
						tempPageRes.getPage_id());
				
				sysMenuDao.get(targeID).getPageres().get(0).setDescription(
						tempPageRes.getDescription());
				
				sysMenuDao.get(targeID).getPageres().get(0).setRemark(
						tempPageRes.getRemark());
				
				list = sysMenuDao.get(targeID).getPageres();

			}

		} else {
			// 非内部命令
			list = sysMenuDao.get(targeID).getPageres();
		}

		// 类型转换
		List<SysResourcesRegBean> sysResourcesRegBeans = new ArrayList<SysResourcesRegBean>();
		for (SysPageRes pr : list) {
			SysResourcesRegBean srrb = new SysResourcesRegBean();
			srrb.setDescription(pr.getDescription());
			srrb.setName(pr.getPage_id());
			srrb.setRemark(pr.getRemark());
			srrb.setId(pr.getId());
			sysResourcesRegBeans.add(srrb);
		}
		return sysResourcesRegBeans;
	}

	/**
	 * 获取指定JSP对应的组件
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public List<SysResourcesRegBean> getTargetComponent(String targeID)
			throws FrameworkException {
		List<SysResources> list = new ArrayList<SysResources>();
		SysPageRes pageRes = sysPageResDao.get(targeID);
		list = pageRes.getResouce();

		// 类型转换
		List<SysResourcesRegBean> sysResourcesRegBeans = new ArrayList<SysResourcesRegBean>();
		for (SysResources resources : list) {
			SysResourcesRegBean srrb = new SysResourcesRegBean();
			srrb.setDescription(resources.getDescription());
			srrb.setName(resources.getName());
			srrb.setRemark(resources.getRemark());
			srrb.setId(resources.getId());
			srrb.setName_id(resources.getRes_id());
			sysResourcesRegBeans.add(srrb);
		}

		return sysResourcesRegBeans;
	}

	/**
	 * 获取指定JSP对应的组件
	 * 
	 * @param params
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public List<SysResourcesRegBean> findAcrionUrls(String targeID)
			throws FrameworkException {
		List<SysAuthorize> list = new ArrayList<SysAuthorize>();
		SysResources resources = sysResourcesDao.get(targeID);
		list = resources.getAuthorize();

		// 类型转换
		List<SysResourcesRegBean> sysResourcesRegBeans = new ArrayList<SysResourcesRegBean>();
		for (SysAuthorize authorize : list) {
			SysResourcesRegBean srrb = new SysResourcesRegBean();
			srrb.setName(authorize.getUrl());
			srrb.setId(authorize.getId());
			srrb.setDescription(authorize.getDescription());
			srrb.setRemark(authorize.getRemark());
			sysResourcesRegBeans.add(srrb);
		}
		return sysResourcesRegBeans;
	}

	/**
	 * 删除指定的Action地址
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String delActionUrl(String targeID) throws FrameworkException {
		sysAuthorizeDao.delete(sysAuthorizeDao.get(targeID));
		return "Action地址删除成功";
	}

	/**
	 * 删除指定的组件
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String delComponent(String targeID) throws FrameworkException {
		// 删除该组件对应的所有的action地址
		
		if(sysResourcesDao.get(targeID).getAuthorize()!=null){
			for (SysAuthorize authorize : sysResourcesDao.get(targeID).getAuthorize()) {
				sysAuthorizeDao.delete(authorize);
			}
		}
		//解除角色与组件之间的关系
		for(SysRole sysRole : sysResourcesDao.get(targeID).getSysRoles()){
			sysRole.getResources().remove(sysResourcesDao.get(targeID));
		}
		
		// 删除这个组件实体
		sysResourcesDao.delete(sysResourcesDao.get(targeID));

		return "组件删除成功";
	}

	/**
	 * 删除指定的JSP
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@DataSource(value = "admin")
	@Transactional
	public String delJspUrl(String targeID) throws FrameworkException {

		if ("0".equals(sysPageResDao.get(targeID).getMenu().getInternalCommand())) {
			return "不能删除该链接地址";
		}

		// 删除JSP对应的组件，删除组件对应的Action
		for (SysResources resources : sysPageResDao.get(targeID).getResouce()) {
			for (SysAuthorize authorize : resources.getAuthorize()) {
				sysAuthorizeDao.delete(authorize);
			}
			
			//解除角色与组件自建的关系
			for(SysRole sysRole :resources.getSysRoles())
			{
				sysRole.getResources().remove(resources.getSysRoles());
			}	
			
			
			sysResourcesDao.delete(resources);
		}
		// 删除这个JSP实体
		sysPageResDao.delete(sysPageResDao.get(targeID));
		return "删除成功";
	}

}
