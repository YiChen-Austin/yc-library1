package com.mall.web.mall.common.vo;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

public class DataJsonBean {
	private static Logger logger = Logger.getLogger(DataJsonBean.class);

	/********************************/
	public static String server_zone_info;// 服务器配置数据
	public static ServerZoneBean serverZoneBean; // 服务器列表
	public static Map<String, ServerZoneBean> serverZoneMap; // 服务器列表映射

	public static String autoMsgJson = "";
	public static String autoMsgJson4p = "";
	public static String autoMsgJson4n = "";

	private static Map<String, Long> modifyMap = null;
	static {
		modifyMap = new HashMap<String, Long>();
		syncAutoMsg();
	}

	/**
	 * 递归方式进行区域数据初始化
	 * 
	 * @param zoneBean
	 *            当前服务器区域数据
	 * @param array
	 *            json列表
	 */
	private static void doSubZone(ServerZoneBean zoneBean, JSONArray array) {
		if (array == null || array.size() <= 0 || zoneBean.getSubLayer() <= 0)
			return;
		if (zoneBean.getSubZones() == null) {
			zoneBean.setSubZones(new LinkedList<ServerZoneBean>());
		}
		for (int i = 0; i < array.size(); i++) {
			JSONObject object = (JSONObject) array.get(i);
			ServerZoneBean bean = new ServerZoneBean();
			zoneJson2bean(object, bean);
			zoneBean.getSubZones().add(bean);
			if (object.get("subZones") != null && bean.getSubLayer() > 0) {
				JSONArray arr = object.getJSONArray("subZones");
				doSubZone(bean, arr);
			}
		}

	}

	/**
	 * 服务器区域数据转bean
	 * 
	 * @param zoneBean
	 *            当前服务器区域数据
	 * @param object
	 *            json数据
	 */
	private static void zoneJson2bean(JSONObject object, ServerZoneBean bean) {
		if (object.get("serverId") != null)
			bean.setServerId(object.getString("serverId"));
		if (object.get("serverName") != null)
			bean.setServerName(object.getString("serverName"));
		if (object.get("subLayer") != null)
			bean.setSubLayer(object.getInt("subLayer"));
		if (object.get("subCate") != null)
			bean.setSubCate(object.getString("subCate"));
		serverZoneMap.put(bean.getServerId(), bean);
	}

	public static void syncAutoMsg() {
		try {
			long lastModified = 0;
			File file = null;
			String sign = "";

			sign = "classpath:mall/messages/server_zone_info.json";
			lastModified = modifyMap.get(sign) == null ? 0L : modifyMap.get(sign);
			file = ResourceUtils.getFile(sign);
			if (file.lastModified() > lastModified) {
				server_zone_info = FileUtils.readFileToString(file, "UTF-8");
				modifyMap.put(sign, file.lastModified());
				if (serverZoneMap == null)
					serverZoneMap = new HashMap<String, ServerZoneBean>();
				serverZoneMap.clear();
				// 解析
				try {
					JSONObject object = JSONObject.fromObject(server_zone_info);
					serverZoneBean = new ServerZoneBean();
					zoneJson2bean(object, serverZoneBean);
					// 递推创建子区域服务器
					if (object.get("subZones") != null && serverZoneBean.getSubLayer() > 0) {
						JSONArray arr = object.getJSONArray("subZones");
						doSubZone(serverZoneBean, arr);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			sign = "classpath:mall/weixin/weixin_auto_msg.xml";
			lastModified = modifyMap.get(sign) == null ? 0L : modifyMap.get(sign);
			file = ResourceUtils.getFile(sign);
			if (file.lastModified() > lastModified) {
				autoMsgJson = FileUtils.readFileToString(file, "UTF-8");
				modifyMap.put(sign, file.lastModified());
			}

			sign = "classpath:mall/weixin/weixin_auto_msg_p.xml";
			lastModified = modifyMap.get(sign) == null ? 0L : modifyMap.get(sign);
			file = ResourceUtils.getFile(sign);
			if (file.lastModified() > lastModified) {
				autoMsgJson4p = FileUtils.readFileToString(file, "UTF-8");
				modifyMap.put(sign, file.lastModified());
			}

			sign = "classpath:mall/weixin/weixin_auto_msg_n.xml";
			lastModified = modifyMap.get(sign) == null ? 0L : modifyMap.get(sign);
			file = ResourceUtils.getFile(sign);
			if (file.lastModified() > lastModified) {
				autoMsgJson4n = FileUtils.readFileToString(file, "UTF-8");
				modifyMap.put(sign, file.lastModified());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(DataJsonBean.serverZoneBean.getSubZones().get(0).getSubZones());
	}
}
