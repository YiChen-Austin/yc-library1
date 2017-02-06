package com.mall.web.common.dynamicds;

/**
 * 数据源切换类
 * 
 * @author ty
 * 
 */
public class DataSourceSwitcher {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	/**
	 * 切换到主数据源
	 */
	public static void setMaster() {
		setDataSource("master");
	}

	/**
	 * 切换到默认据源
	 */
	public static void setDefault() {
		clearDataSource();
	}

	/**
	 * 切换到从数据源
	 */
	public static void setSlave() {
		setDataSource("slave");
	}

	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}
