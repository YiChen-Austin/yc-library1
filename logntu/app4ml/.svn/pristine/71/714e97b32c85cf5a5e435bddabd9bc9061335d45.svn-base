package com.mall.web.mall.common.vo;

import java.util.List;

/**
 * 服務器配置数据
 * 
 * @author ventrue
 * 
 */
public class ServerZoneBean {
	private String serverId;// 服務器id
	private String serverName;// 服務器名稱
	private int subLayer;// 子服務器層次
	private String subCate;// 子服務器類型
	private List<ServerZoneBean> subZones;// 子服務器

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getSubLayer() {
		return subLayer;
	}

	public void setSubLayer(int subLayer) {
		this.subLayer = subLayer;
	}

	public String getSubCate() {
		return subCate;
	}

	public void setSubCate(String subCate) {
		this.subCate = subCate;
	}

	public List<ServerZoneBean> getSubZones() {
		return subZones;
	}

	public void setSubZones(List<ServerZoneBean> subZones) {
		this.subZones = subZones;
	}

	/**
	 * 简单拷贝
	 */
	public void copySplValue(ServerZoneBean srcZone) {
		this.serverId = srcZone.getServerId();
		this.serverName = srcZone.getServerName();
		this.subLayer = srcZone.getSubLayer();
		this.subCate = srcZone.getSubCate();
	}
}
